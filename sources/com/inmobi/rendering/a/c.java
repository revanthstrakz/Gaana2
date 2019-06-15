package com.inmobi.rendering.a;

import android.annotation.TargetApi;
import android.content.ContentValues;
import android.graphics.Bitmap;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.google.android.gms.cast.HlsSegmentFormat;
import com.google.api.client.http.HttpMethods;
import com.inmobi.a.n;
import com.inmobi.ads.c.e;
import com.inmobi.commons.core.configs.h;
import com.inmobi.commons.core.network.NetworkError;
import com.inmobi.commons.core.network.NetworkError.ErrorCode;
import com.inmobi.commons.core.network.WebViewNetworkTask;
import com.inmobi.commons.core.network.WebViewNetworkTask.NetworkTaskWebView;
import com.inmobi.commons.core.utilities.g;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

public class c implements com.inmobi.commons.core.configs.b.c {
    private static final String a = "c";
    private static c b;
    private static final Object c = new Object();
    private static ExecutorService d;
    private static a e;
    private static HandlerThread f;
    private static List<a> g = new ArrayList();
    private static b h;
    private static AtomicBoolean i = new AtomicBoolean(false);
    private static e j;
    private static final Object k = new Object();
    private long l = 0;
    private final d m = new d() {
        public final void a(a aVar) {
            if (aVar != null) {
                c.a;
                StringBuilder stringBuilder = new StringBuilder("Processing click (");
                stringBuilder.append(aVar.b);
                stringBuilder.append(") completed");
                c.h;
                b.a(aVar);
                try {
                    HashMap hashMap = new HashMap();
                    hashMap.put("url", aVar.b);
                    hashMap.put("latency", Long.valueOf(SystemClock.elapsedRealtime() - c.this.l));
                    com.inmobi.commons.core.e.b.a();
                    com.inmobi.commons.core.e.b.a("ads", "PingLatency", hashMap);
                } catch (Exception e) {
                    c.a;
                    stringBuilder = new StringBuilder("Error in submitting telemetry event : (");
                    stringBuilder.append(e.getMessage());
                    stringBuilder.append(")");
                }
            }
        }

        public final void b(a aVar) {
            if (aVar != null) {
                c.a;
                StringBuilder stringBuilder = new StringBuilder("Pinging click (");
                stringBuilder.append(aVar.b);
                stringBuilder.append(") failed! Updating retry counts and timestamps ...");
                c.a(aVar);
                c.this.b();
            }
        }
    };

    /* renamed from: com.inmobi.rendering.a.c$1 */
    class AnonymousClass1 extends Thread {
        final /* synthetic */ String a;
        final /* synthetic */ boolean b;

        public AnonymousClass1(String str, boolean z) {
            this.a = str;
            this.b = z;
        }

        public final void run() {
            try {
                com.inmobi.commons.core.configs.a hVar = new h();
                com.inmobi.commons.core.configs.b.a().a(hVar, null);
                if (!hVar.g) {
                    a aVar = new a(this.a, this.b, false, c.j.a + 1);
                    c.a;
                    StringBuilder stringBuilder = new StringBuilder("Received click (");
                    stringBuilder.append(aVar.b);
                    stringBuilder.append(") for pinging over HTTP");
                    c.a(c.this, aVar);
                }
            } catch (Exception e) {
                c.a;
                new StringBuilder("SDK encountered unexpected error in pinging click; ").append(e.getMessage());
            }
        }
    }

    /* renamed from: com.inmobi.rendering.a.c$3 */
    class AnonymousClass3 extends Thread {
        final /* synthetic */ String a;
        final /* synthetic */ boolean b;

        public AnonymousClass3(String str, boolean z) {
            this.a = str;
            this.b = z;
        }

        public final void run() {
            try {
                com.inmobi.commons.core.configs.a hVar = new h();
                com.inmobi.commons.core.configs.b.a().a(hVar, null);
                if (!hVar.g) {
                    a aVar = new a(this.a, this.b, true, c.j.a + 1);
                    c.a;
                    StringBuilder stringBuilder = new StringBuilder("Received click (");
                    stringBuilder.append(aVar.b);
                    stringBuilder.append(") for pinging in WebView");
                    c.a(c.this, aVar);
                }
            } catch (Exception e) {
                c.a;
                new StringBuilder("SDK encountered unexpected error in pinging click over WebView; ").append(e.getMessage());
            }
        }
    }

    final class a extends Handler {
        public a(Looper looper) {
            super(looper);
        }

        public final void handleMessage(Message message) {
            Message message2 = message;
            try {
                a aVar;
                Message obtain;
                StringBuilder stringBuilder;
                int i;
                StringBuilder stringBuilder2;
                StringBuilder stringBuilder3;
                switch (message2.what) {
                    case 1:
                        com.inmobi.commons.core.configs.a hVar = new h();
                        com.inmobi.commons.core.configs.b.c cVar = null;
                        com.inmobi.commons.core.configs.b.a().a(hVar, null);
                        if (!hVar.g) {
                            c.h;
                            int i2 = c.j.e;
                            i = c.j.b;
                            List arrayList = new ArrayList();
                            com.inmobi.commons.core.d.b a = com.inmobi.commons.core.d.b.a();
                            if (a.a("click") != 0) {
                                if (-1 != i2) {
                                    cVar = Integer.toString(i2);
                                }
                                com.inmobi.commons.core.configs.b.c cVar2 = cVar;
                                String[] strArr = b.a;
                                String str = HlsSegmentFormat.TS;
                                stringBuilder2 = new StringBuilder("ts < ");
                                stringBuilder2.append(System.currentTimeMillis() - ((long) i));
                                com.inmobi.commons.core.d.b bVar = a;
                                List<ContentValues> a2 = a.a("click", strArr, null, null, str, stringBuilder2.toString(), "ts ASC ", cVar2);
                                bVar.b();
                                for (ContentValues a3 : a2) {
                                    arrayList.add(b.a(a3));
                                }
                            }
                            c.g = arrayList;
                            if (c.g.isEmpty()) {
                                c.h;
                                if (b.a()) {
                                    c.i.set(false);
                                    return;
                                }
                                message2 = Message.obtain();
                                message2.what = 1;
                                sendMessageDelayed(message2, (long) (c.j.b * 1000));
                                return;
                            }
                            aVar = (a) c.g.get(0);
                            obtain = Message.obtain();
                            obtain.what = aVar.h ? 3 : 2;
                            obtain.obj = aVar;
                            long currentTimeMillis = System.currentTimeMillis() - aVar.d;
                            if (currentTimeMillis < ((long) (c.j.b * 1000))) {
                                sendMessageDelayed(obtain, ((long) (c.j.b * 1000)) - currentTimeMillis);
                                return;
                            } else {
                                sendMessage(obtain);
                                return;
                            }
                        }
                        break;
                    case 2:
                        if (com.inmobi.commons.core.utilities.d.a()) {
                            aVar = (a) message2.obj;
                            if (aVar.f == 0) {
                                a(aVar, 1);
                                return;
                            } else if (aVar.a(c.j.f)) {
                                a(aVar, 2);
                                return;
                            } else {
                                i = (c.j.a - aVar.f) + 1;
                                if (i == 0) {
                                    c.a;
                                    stringBuilder = new StringBuilder("Pinging click (");
                                    stringBuilder.append(aVar.b);
                                    stringBuilder.append(") over HTTP");
                                } else {
                                    c.a;
                                    stringBuilder3 = new StringBuilder("Retry attempt #");
                                    stringBuilder3.append(i);
                                    stringBuilder3.append(" for click (");
                                    stringBuilder3.append(aVar.b);
                                    stringBuilder3.append(") over HTTP");
                                }
                                new c(new d() {
                                    public final void a(a aVar) {
                                        a.a(a.this, aVar);
                                    }

                                    public final void b(a aVar) {
                                        c.a;
                                        StringBuilder stringBuilder = new StringBuilder("Pinging click (");
                                        stringBuilder.append(aVar.b);
                                        stringBuilder.append(") via HTTP failed ...");
                                        c.a(aVar);
                                        a.b(a.this, aVar);
                                    }
                                }).a(aVar);
                                return;
                            }
                        }
                        c.i.set(false);
                        c.i();
                        return;
                    case 3:
                        if (com.inmobi.commons.core.utilities.d.a()) {
                            aVar = (a) message2.obj;
                            if (aVar.f == 0) {
                                a(aVar, 1);
                                return;
                            } else if (aVar.a(c.j.f)) {
                                a(aVar, 2);
                                return;
                            } else {
                                i = (c.j.a - aVar.f) + 1;
                                if (i == 0) {
                                    c.a;
                                    stringBuilder = new StringBuilder("Pinging click (");
                                    stringBuilder.append(aVar.b);
                                    stringBuilder.append(") in WebView");
                                } else {
                                    c.a;
                                    stringBuilder3 = new StringBuilder("Retry attempt #");
                                    stringBuilder3.append(i);
                                    stringBuilder3.append(" for click (");
                                    stringBuilder3.append(aVar.b);
                                    stringBuilder3.append(") using WebView");
                                }
                                new b(new d() {
                                    public final void a(a aVar) {
                                        a.a(a.this, aVar);
                                    }

                                    public final void b(a aVar) {
                                        c.a;
                                        StringBuilder stringBuilder = new StringBuilder("Pinging click (");
                                        stringBuilder.append(aVar.b);
                                        stringBuilder.append(") via WebView failed ...");
                                        c.a(aVar);
                                        a.b(a.this, aVar);
                                    }
                                }).a(aVar);
                                return;
                            }
                        }
                        c.i.set(false);
                        c.i();
                        return;
                    case 4:
                        break;
                    case 5:
                        a aVar2 = (a) message2.obj;
                        HashMap hashMap = new HashMap();
                        hashMap.put("pingUrl", aVar2.b);
                        switch (message2.arg1) {
                            case 1:
                                hashMap.put("errorCode", "MaxRetryCountReached");
                                break;
                            case 2:
                                hashMap.put("errorCode", "ExpiredClick");
                                break;
                            default:
                                break;
                        }
                        try {
                            com.inmobi.commons.core.e.b.a();
                            com.inmobi.commons.core.e.b.a("ads", "PingDiscarded", hashMap);
                            break;
                        } catch (Exception e) {
                            Exception exception = e;
                            c.a;
                            stringBuilder2 = new StringBuilder("Error in submitting telemetry event : (");
                            stringBuilder2.append(exception.getMessage());
                            stringBuilder2.append(")");
                            break;
                        }
                    default:
                        break;
                }
                aVar = (a) message2.obj;
                c.a;
                stringBuilder = new StringBuilder("Processing click (");
                stringBuilder.append(aVar.b);
                stringBuilder.append(") completed");
                c.h;
                b.a(aVar);
                c.g.remove(aVar);
                if (c.g.isEmpty()) {
                    c.h;
                    if (b.a()) {
                        c.a;
                        c.i.set(false);
                        return;
                    }
                    message2 = Message.obtain();
                    message2.what = 1;
                    sendMessage(message2);
                    return;
                }
                aVar = (a) c.g.get(0);
                obtain = Message.obtain();
                obtain.what = aVar.h ? 3 : 2;
                obtain.obj = aVar;
                sendMessage(obtain);
            } catch (Exception e2) {
                Exception exception2 = e2;
                c.a;
                new StringBuilder("SDK encountered unexpected error in processing ping; ").append(exception2.getMessage());
            }
        }

        private void a(a aVar, int i) {
            Message obtain = Message.obtain();
            obtain.what = 5;
            obtain.obj = aVar;
            obtain.arg1 = i;
            sendMessage(obtain);
        }

        static /* synthetic */ void b(a aVar, a aVar2) {
            int indexOf = c.g.indexOf(aVar2);
            if (-1 != indexOf) {
                aVar2 = (a) c.g.get(indexOf == c.g.size() + -1 ? 0 : indexOf + 1);
                Message obtain = Message.obtain();
                obtain.what = aVar2.h ? 3 : 2;
                obtain.obj = aVar2;
                if (System.currentTimeMillis() - aVar2.d < ((long) (c.j.b * 1000))) {
                    aVar.sendMessageDelayed(obtain, (long) (c.j.b * 1000));
                    return;
                }
                aVar.sendMessage(obtain);
            }
        }
    }

    static final class b {
        d a;

        public b(d dVar) {
            this.a = dVar;
        }

        public final void a(final a aVar) {
            aVar.g.set(false);
            final Handler handler = new Handler(Looper.getMainLooper());
            handler.post(new Runnable() {
                public final void run() {
                    com.inmobi.commons.core.network.c cVar = new com.inmobi.commons.core.network.c(HttpMethods.GET, aVar.b);
                    Map b = c.c(aVar);
                    if (!b.isEmpty()) {
                        cVar.a(b);
                    }
                    WebViewNetworkTask webViewNetworkTask = new WebViewNetworkTask(cVar, new WebViewClient() {
                        AtomicBoolean a;
                        boolean b;

                        public final void onPageStarted(final WebView webView, String str, Bitmap bitmap) {
                            this.a = new AtomicBoolean(false);
                            this.b = false;
                            new Thread(new Runnable() {
                                public final void run() {
                                    try {
                                        Thread.sleep((long) (c.j.c * 1000));
                                    } catch (InterruptedException unused) {
                                    }
                                    if (!AnonymousClass1.this.a.get()) {
                                        c.a;
                                        StringBuilder stringBuilder = new StringBuilder("Pinging click (");
                                        stringBuilder.append(aVar.b);
                                        stringBuilder.append(") via WebView timed out!");
                                        aVar.g.set(true);
                                        handler.post(new Runnable() {
                                            public final void run() {
                                                try {
                                                    NetworkTaskWebView networkTaskWebView = (NetworkTaskWebView) webView;
                                                    if (!(networkTaskWebView == null || networkTaskWebView.a)) {
                                                        webView.stopLoading();
                                                    }
                                                } catch (Throwable th) {
                                                    com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(th));
                                                }
                                            }
                                        });
                                        b.this.a.b(aVar);
                                    }
                                }
                            }).start();
                        }

                        public final void onPageFinished(WebView webView, String str) {
                            this.a.set(true);
                            if (!this.b && !aVar.g.get()) {
                                b.this.a.a(aVar);
                            }
                        }

                        @TargetApi(22)
                        public final void onReceivedError(WebView webView, int i, String str, String str2) {
                            this.b = true;
                            b.this.a.b(aVar);
                        }

                        @TargetApi(23)
                        public final void onReceivedError(WebView webView, WebResourceRequest webResourceRequest, WebResourceError webResourceError) {
                            this.b = true;
                            b.this.a.b(aVar);
                        }

                        @TargetApi(23)
                        public final void onReceivedHttpError(WebView webView, WebResourceRequest webResourceRequest, WebResourceResponse webResourceResponse) {
                            this.b = true;
                            b.this.a.b(aVar);
                        }

                        public final boolean shouldOverrideUrlLoading(WebView webView, WebResourceRequest webResourceRequest) {
                            if (VERSION.SDK_INT < 21 || aVar.i || webResourceRequest.getUrl().toString().equals(aVar.b)) {
                                return false;
                            }
                            return true;
                        }

                        public final boolean shouldOverrideUrlLoading(WebView webView, String str) {
                            return (aVar.i || str.equals(aVar.b)) ? false : true;
                        }
                    });
                    try {
                        webViewNetworkTask.c = new NetworkTaskWebView(com.inmobi.commons.a.a.b());
                        webViewNetworkTask.c.setWebViewClient(webViewNetworkTask.b);
                        webViewNetworkTask.c.getSettings().setJavaScriptEnabled(true);
                        webViewNetworkTask.c.getSettings().setCacheMode(2);
                        webViewNetworkTask.c.loadUrl(webViewNetworkTask.a.e(), webViewNetworkTask.a.d());
                    } catch (Exception e) {
                        new StringBuilder("SDK encountered unexpected error in WebViewNetworkTask.execute() method; ").append(e.getMessage());
                    }
                }
            });
        }
    }

    static final class c {
        private d a;

        public c(d dVar) {
            this.a = dVar;
        }

        public final void a(a aVar) {
            d dVar;
            NetworkError networkError;
            try {
                com.inmobi.commons.core.network.c cVar = new com.inmobi.commons.core.network.c(HttpMethods.GET, aVar.b);
                Map b = c.c(aVar);
                if (!b.isEmpty()) {
                    cVar.a(b);
                }
                cVar.u = false;
                cVar.b(aVar.c);
                cVar.t = aVar.i;
                cVar.r = c.j.c * 1000;
                cVar.s = c.j.c * 1000;
                long elapsedRealtime = SystemClock.elapsedRealtime();
                com.inmobi.commons.core.network.d a = new com.inmobi.commons.core.network.e(cVar).a();
                try {
                    n.a().a(cVar.g());
                    n.a().b(a.c());
                    n.a().c(SystemClock.elapsedRealtime() - elapsedRealtime);
                } catch (Exception e) {
                    c.a;
                    new StringBuilder("Error in setting request-response data size. ").append(e.getMessage());
                }
                if (a.a()) {
                    ErrorCode errorCode = a.b.a;
                    if (aVar.i || !(ErrorCode.HTTP_SEE_OTHER == errorCode || ErrorCode.HTTP_MOVED_TEMP == errorCode)) {
                        dVar = this.a;
                        networkError = a.b;
                        dVar.b(aVar);
                        return;
                    }
                    this.a.a(aVar);
                    return;
                }
                this.a.a(aVar);
            } catch (Exception e2) {
                c.a;
                new StringBuilder("SDK encountered unexpected error in executing ping over HTTP; ").append(e2.getMessage());
                dVar = this.a;
                networkError = new NetworkError(ErrorCode.UNKNOWN_ERROR, "Unknown error");
                dVar.b(aVar);
            }
        }
    }

    interface d {
        void a(a aVar);

        void b(a aVar);
    }

    public static c a() {
        c cVar = b;
        if (cVar == null) {
            synchronized (c) {
                cVar = b;
                if (cVar == null) {
                    cVar = new c();
                    b = cVar;
                }
            }
        }
        return cVar;
    }

    public final void a(com.inmobi.commons.core.configs.a aVar) {
        j = ((com.inmobi.ads.c) aVar).h;
    }

    public final void b() {
        try {
            if (com.inmobi.commons.core.utilities.d.a()) {
                synchronized (k) {
                    if (i.compareAndSet(false, true)) {
                        if (f == null) {
                            HandlerThread handlerThread = new HandlerThread("pingHandlerThread");
                            f = handlerThread;
                            handlerThread.start();
                        }
                        if (e == null) {
                            e = new a(f.getLooper());
                        }
                        if (b.a()) {
                            i.set(false);
                            i();
                        } else {
                            Message obtain = Message.obtain();
                            obtain.what = 1;
                            e.sendMessage(obtain);
                        }
                    }
                }
            }
        } catch (Exception e) {
            new StringBuilder("SDK encountered unexpected error in starting the ping component; ").append(e.getMessage());
        }
    }

    public final void a(final String str, final Map<String, String> map) {
        new Thread() {
            final /* synthetic */ boolean c = true;

            public final void run() {
                try {
                    com.inmobi.commons.core.configs.a hVar = new h();
                    com.inmobi.commons.core.configs.b.a().a(hVar, null);
                    if (!hVar.g) {
                        a aVar = new a(str, map, this.c, c.j.a + 1);
                        c.a;
                        StringBuilder stringBuilder = new StringBuilder("Received click (");
                        stringBuilder.append(aVar.b);
                        stringBuilder.append(") for pinging over HTTP");
                        c.a(c.this, aVar);
                    }
                } catch (Exception e) {
                    c.a;
                    new StringBuilder("SDK encountered unexpected error in pinging click; ").append(e.getMessage());
                    com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
                }
            }
        }.start();
    }

    private static void i() {
        try {
            i.set(false);
            synchronized (k) {
                if (!i.get()) {
                    if (f != null) {
                        f.getLooper().quit();
                        f.interrupt();
                        f = null;
                        e = null;
                    }
                    g.clear();
                }
            }
        } catch (Exception e) {
            new StringBuilder("SDK encountered unexpected error in stopping the ping component; ").append(e.getMessage());
        }
    }

    public c() {
        try {
            d = Executors.newFixedThreadPool(5);
            HandlerThread handlerThread = new HandlerThread("pingHandlerThread");
            f = handlerThread;
            handlerThread.start();
            e = new a(f.getLooper());
            com.inmobi.commons.core.configs.a cVar = new com.inmobi.ads.c();
            com.inmobi.commons.core.configs.b.a().a(cVar, (com.inmobi.commons.core.configs.b.c) this);
            j = cVar.h;
            h = new b();
            g.a().a(new com.inmobi.commons.core.utilities.g.b() {
                public final void a(boolean z) {
                    if (z) {
                        c.this.b();
                    }
                }
            });
            if (VERSION.SDK_INT >= 23) {
                g.a().a("android.os.action.DEVICE_IDLE_MODE_CHANGED", new com.inmobi.commons.core.utilities.g.b() {
                    public final void a(boolean z) {
                        if (!z) {
                            c.this.b();
                        }
                    }
                });
            }
        } catch (Exception e) {
            new StringBuilder("SDK encountered unexpected error in initializing the ping component; ").append(e.getMessage());
        }
    }

    private static HashMap<String, String> c(a aVar) {
        HashMap hashMap = new HashMap();
        try {
            int i = (j.a - aVar.f) + 1;
            if (i > 0) {
                hashMap.put("X-im-retry-count", String.valueOf(i));
            }
        } catch (Exception unused) {
        }
        return hashMap;
    }
}
