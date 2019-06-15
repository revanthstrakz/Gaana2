package com.android.volley;

import android.os.Process;
import android.os.SystemClock;
import android.text.TextUtils;
import com.android.volley.a.a;
import com.gaana.application.GaanaApplication;
import com.gaana.models.BusinessObject;
import com.gaana.models.GaanaThemeModel;
import com.gaana.models.SDKConfig;
import com.i.c;
import java.util.concurrent.BlockingQueue;

public class b extends Thread {
    private static final boolean a = l.b;
    private final BlockingQueue<Request<?>> b;
    private final BlockingQueue<Request<?>> c;
    private final a d;
    private final j e;
    private volatile boolean f = false;
    private GaanaApplication g;

    public b(BlockingQueue<Request<?>> blockingQueue, BlockingQueue<Request<?>> blockingQueue2, a aVar, j jVar) {
        this.b = blockingQueue;
        this.c = blockingQueue2;
        this.d = aVar;
        this.e = jVar;
        this.g = GaanaApplication.getInstance();
    }

    public void a() {
        this.f = true;
        interrupt();
    }

    public void run() {
        if (a) {
            l.a("start new dispatcher", new Object[0]);
        }
        Process.setThreadPriority(10);
        this.d.a();
        while (true) {
            try {
                final Request request = (Request) this.b.take();
                request.addMarker("cache-queue-take");
                if (request.isCanceled()) {
                    request.finish("cache-discard-canceled");
                } else {
                    a a = this.d.a(request.getCacheKey());
                    if (a == null) {
                        if (request.isCacheOnly()) {
                            VolleyError volleyError = new VolleyError(new Throwable("Image download request disabled"));
                            volleyError.a(SystemClock.elapsedRealtime());
                            this.e.a(request, volleyError);
                        } else {
                            request.addMarker("cache-miss");
                            this.c.put(request);
                        }
                    } else if ((request instanceof c) || request.isCacheOnly() || !a.a()) {
                        request.addMarker("cache-hit");
                        i parseNetworkResponse = request.parseNetworkResponse(new g(a.a, a.g));
                        request.addMarker("cache-hit-parsed");
                        Object obj = parseNetworkResponse.a;
                        if (!(!(obj instanceof BusinessObject) || ((BusinessObject) obj).getHashValue() == null || TextUtils.isEmpty(((BusinessObject) obj).getHashValue()))) {
                            request.setHashValue(((BusinessObject) obj).getHashValue());
                        }
                        if ((obj instanceof GaanaThemeModel) && !TextUtils.isEmpty(((GaanaThemeModel) obj).getHashValue())) {
                            request.setHashValue(((GaanaThemeModel) obj).getHashValue());
                        }
                        if ((obj instanceof SDKConfig) && !TextUtils.isEmpty(((SDKConfig) obj).getHashValue())) {
                            request.setHashValue(((SDKConfig) obj).getHashValue());
                        }
                        if (request.isDataToBeRefreshed()) {
                            request.addMarker("cache-miss");
                            this.c.put(request);
                        } else if (request.isDataToBeRefreshedAfterCacheResponse()) {
                            request.addMarker("cache-hit-refresh-needed");
                            request.setCacheEntry(a);
                            parseNetworkResponse.d = true;
                            this.e.a(request, parseNetworkResponse, new Runnable() {
                                public void run() {
                                    try {
                                        b.this.c.put(request);
                                    } catch (InterruptedException unused) {
                                    }
                                }
                            });
                        } else {
                            if (a.b()) {
                                if (!request.isCacheOnly()) {
                                    request.addMarker("cache-hit-refresh-needed");
                                    request.setCacheEntry(a);
                                    parseNetworkResponse.d = true;
                                    this.e.a(request, parseNetworkResponse, new Runnable() {
                                        public void run() {
                                            try {
                                                b.this.c.put(request);
                                            } catch (InterruptedException unused) {
                                            }
                                        }
                                    });
                                }
                            }
                            this.e.a(request, parseNetworkResponse);
                        }
                    } else {
                        request.addMarker("cache-hit-expired");
                        request.setCacheEntry(a);
                        this.c.put(request);
                    }
                }
            } catch (InterruptedException unused) {
                if (this.f) {
                    return;
                }
            }
        }
    }
}
