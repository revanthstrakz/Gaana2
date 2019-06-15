package com.android.volley;

import android.annotation.TargetApi;
import android.net.TrafficStats;
import android.os.Build.VERSION;
import android.os.Process;
import android.os.SystemClock;
import android.text.TextUtils;
import com.gaana.application.GaanaApplication;
import com.i.c;
import java.util.concurrent.BlockingQueue;

public class f extends Thread {
    private final BlockingQueue<Request<?>> a;
    private final e b;
    private final a c;
    private final j d;
    private volatile boolean e = false;

    public f(BlockingQueue<Request<?>> blockingQueue, e eVar, a aVar, j jVar) {
        this.a = blockingQueue;
        this.b = eVar;
        this.c = aVar;
        this.d = jVar;
    }

    public void a() {
        this.e = true;
        interrupt();
    }

    @TargetApi(14)
    private void a(Request<?> request) {
        if (VERSION.SDK_INT >= 14) {
            TrafficStats.setThreadStatsTag(request.getTrafficStatsTag());
        }
    }

    public void run() {
        VolleyError e;
        Process.setThreadPriority(10);
        while (true) {
            long elapsedRealtime = SystemClock.elapsedRealtime();
            try {
                Request request = (Request) this.a.take();
                try {
                    if (request.isCacheOnly()) {
                        VolleyError volleyError = new VolleyError(new Throwable("App is in offline mode"));
                        volleyError.a(SystemClock.elapsedRealtime() - elapsedRealtime);
                        this.d.a(request, volleyError);
                    } else {
                        request.addMarker("network-queue-take");
                        if (request.isCanceled()) {
                            request.finish("network-discard-cancelled");
                        } else {
                            a(request);
                            String url = request.getUrl();
                            if (request instanceof c) {
                                if (GaanaApplication.getInstance().getCurrentUser().getLoginStatus() && !url.contains("token=")) {
                                    StringBuilder stringBuilder;
                                    if (url.contains("?")) {
                                        stringBuilder = new StringBuilder();
                                        stringBuilder.append(url);
                                        stringBuilder.append("&token=");
                                        stringBuilder.append(GaanaApplication.getInstance().getCurrentUser().getAuthToken());
                                        url = stringBuilder.toString();
                                    } else {
                                        stringBuilder = new StringBuilder();
                                        stringBuilder.append(url);
                                        stringBuilder.append("?token=");
                                        stringBuilder.append(GaanaApplication.getInstance().getCurrentUser().getAuthToken());
                                        url = stringBuilder.toString();
                                    }
                                }
                                request.setUrl(url.replace(" ", "%20"));
                            }
                            if (request.shouldCache() && !TextUtils.isEmpty(request.getHashValue())) {
                                url = "?hv=";
                                String url2 = request.getUrl();
                                if (url2.contains("?")) {
                                    url = "&hv=";
                                }
                                StringBuilder stringBuilder2 = new StringBuilder();
                                stringBuilder2.append(url2);
                                stringBuilder2.append(url);
                                stringBuilder2.append(request.getHashValue());
                                request.setUrl(stringBuilder2.toString());
                            }
                            g a = this.b.a(request);
                            request.addMarker("network-http-complete");
                            if (a.d && request.hasHadResponseDelivered()) {
                                request.finish("not-modified");
                            } else {
                                i parseNetworkResponse = request.parseNetworkResponse(a);
                                request.addMarker("network-parse-complete");
                                parseNetworkResponse.e = true;
                                if ((request instanceof c ? ((GaanaApplication) GaanaApplication.getContext()).checkAuthTokenStatus(parseNetworkResponse.a, request) : true) && request.shouldCache() && parseNetworkResponse.b != null) {
                                    this.c.a(request.getCacheKey(), parseNetworkResponse.b);
                                    request.addMarker("network-cache-written");
                                }
                                request.markDelivered();
                                this.d.a(request, parseNetworkResponse);
                            }
                        }
                    }
                } catch (VolleyError e2) {
                    e2.a(SystemClock.elapsedRealtime() - elapsedRealtime);
                    a(request, e2);
                } catch (Exception e3) {
                    l.a(e3, "Unhandled exception %s", e3.toString());
                    e2 = new VolleyError(e3);
                    e2.a(SystemClock.elapsedRealtime() - elapsedRealtime);
                    this.d.a(request, e2);
                }
            } catch (InterruptedException unused) {
                if (this.e) {
                    return;
                }
            }
        }
    }

    private void a(Request<?> request, VolleyError volleyError) {
        this.d.a((Request) request, request.parseNetworkError(volleyError));
    }
}
