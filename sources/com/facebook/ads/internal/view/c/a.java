package com.facebook.ads.internal.view.c;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.RectF;
import android.net.http.SslError;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.util.Log;
import android.view.MotionEvent;
import android.webkit.ConsoleMessage;
import android.webkit.JavascriptInterface;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.facebook.ads.internal.s.a.k;
import com.facebook.ads.internal.s.a.w;
import com.facebook.ads.internal.s.a.y;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

public class a extends com.facebook.ads.internal.s.c.a {
    private static final String a = "a";
    private final WeakReference<b> b;
    private final AtomicBoolean c = new AtomicBoolean();
    private final AtomicBoolean d = new AtomicBoolean(true);
    private final Path e = new Path();
    private final RectF f = new RectF();
    @Nullable
    private WeakReference<d> g;
    @Nullable
    private com.facebook.ads.internal.t.a h;
    private w i = new w();
    private com.facebook.ads.internal.t.a.a j;
    private boolean k = true;
    private boolean l;
    private float m;

    static class a {
        private final String a = a.class.getSimpleName();
        private final WeakReference<a> b;
        private final WeakReference<b> c;
        private final WeakReference<com.facebook.ads.internal.t.a> d;
        private final WeakReference<AtomicBoolean> e;
        private final WeakReference<AtomicBoolean> f;
        private final boolean g;

        a(a aVar, b bVar, com.facebook.ads.internal.t.a aVar2, AtomicBoolean atomicBoolean, AtomicBoolean atomicBoolean2, boolean z) {
            this.b = new WeakReference(aVar);
            this.c = new WeakReference(bVar);
            this.d = new WeakReference(aVar2);
            this.e = new WeakReference(atomicBoolean);
            this.f = new WeakReference(atomicBoolean2);
            this.g = z;
        }

        @JavascriptInterface
        public void alert(String str) {
            Log.e(this.a, str);
        }

        @JavascriptInterface
        public String getAnalogInfo() {
            return k.a(com.facebook.ads.internal.i.a.a());
        }

        @JavascriptInterface
        public void onMainAssetLoaded() {
            if (!(this.b.get() == null || this.e.get() == null || this.f.get() == null || !this.g || !((AtomicBoolean) this.f.get()).get())) {
                ((AtomicBoolean) this.e.get()).set(true);
                if (((a) this.b.get()).isShown()) {
                    new Handler(Looper.getMainLooper()).post(new e(this.d));
                }
            }
        }

        @JavascriptInterface
        public void onPageInitialized() {
            a aVar = (a) this.b.get();
            if (aVar != null && !aVar.c()) {
                b bVar = (b) this.c.get();
                if (bVar != null) {
                    bVar.a();
                }
                if (!this.g && ((a) this.b.get()).isShown()) {
                    new Handler(Looper.getMainLooper()).post(new e(this.d));
                }
            }
        }
    }

    public interface b {
        void a();

        void a(int i);

        void a(@Nullable WebResourceError webResourceError);

        void a(String str, Map<String, String> map);

        void b();
    }

    public interface d {
        void b();
    }

    static class e implements Runnable {
        private final WeakReference<com.facebook.ads.internal.t.a> a;

        e(com.facebook.ads.internal.t.a aVar) {
            this.a = new WeakReference(aVar);
        }

        e(WeakReference<com.facebook.ads.internal.t.a> weakReference) {
            this.a = weakReference;
        }

        public void run() {
            com.facebook.ads.internal.t.a aVar = (com.facebook.ads.internal.t.a) this.a.get();
            if (aVar != null) {
                aVar.a();
            }
        }
    }

    static class f extends WebChromeClient {
        f() {
        }

        public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
            return true;
        }
    }

    static class g extends WebViewClient {
        private final WeakReference<b> a;
        private final WeakReference<com.facebook.ads.internal.t.a> b;
        private final WeakReference<w> c;
        private final WeakReference<AtomicBoolean> d;
        private final WeakReference<a> e;
        private boolean f = false;

        g(WeakReference<b> weakReference, WeakReference<com.facebook.ads.internal.t.a> weakReference2, WeakReference<w> weakReference3, WeakReference<AtomicBoolean> weakReference4, WeakReference<a> weakReference5) {
            this.a = weakReference;
            this.b = weakReference2;
            this.c = weakReference3;
            this.d = weakReference4;
            this.e = weakReference5;
        }

        private void a(@Nullable WebResourceError webResourceError) {
            if (this.a.get() != null) {
                ((b) this.a.get()).a(webResourceError);
            }
        }

        public void onPageFinished(WebView webView, String str) {
            if (!(this.e.get() == null || this.d.get() == null || ((AtomicBoolean) this.d.get()).get())) {
                ((a) this.e.get()).e();
            }
            this.f = true;
        }

        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            super.onPageStarted(webView, str, bitmap);
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    if (!g.this.f) {
                        g.this.a(null);
                    }
                }
            }, AdaptiveTrackSelection.DEFAULT_MIN_TIME_BETWEEN_BUFFER_REEVALUTATION_MS);
        }

        public void onReceivedError(WebView webView, WebResourceRequest webResourceRequest, WebResourceError webResourceError) {
            this.f = true;
            a(webResourceError);
        }

        public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
            sslErrorHandler.cancel();
        }

        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            Map hashMap = new HashMap();
            if (this.b.get() != null) {
                ((com.facebook.ads.internal.t.a) this.b.get()).a(hashMap);
            }
            if (this.c.get() != null) {
                hashMap.put("touch", k.a(((w) this.c.get()).e()));
            }
            if (this.a.get() != null) {
                ((b) this.a.get()).a(str, hashMap);
            }
            return true;
        }
    }

    public static class c implements b {
        public void a() {
        }

        public void a(int i) {
        }

        public void a(@Nullable WebResourceError webResourceError) {
        }

        public void a(String str, Map<String, String> map) {
        }

        public void b() {
        }
    }

    public a(Context context, WeakReference<b> weakReference, int i) {
        super(context);
        this.l = com.facebook.ads.internal.n.a.A(context);
        this.b = weakReference;
        this.j = new com.facebook.ads.internal.t.a.a() {
            public void a() {
                if (a.this.k || !a.this.i.b()) {
                    a.this.i.a();
                }
                if (a.this.b.get() != null) {
                    ((b) a.this.b.get()).b();
                }
            }
        };
        this.h = new com.facebook.ads.internal.t.a(this, i, this.j);
        setWebChromeClient(a());
        setWebViewClient(b());
        getSettings().setSupportZoom(false);
        getSettings().setCacheMode(1);
        addJavascriptInterface(new a(this, (b) weakReference.get(), this.h, this.c, this.d, this.l), "AdControl");
    }

    private void e() {
        this.c.set(true);
        new Handler(Looper.getMainLooper()).post(new e(this.h));
        if (this.g != null && this.g.get() != null) {
            ((d) this.g.get()).b();
        }
    }

    /* Access modifiers changed, original: protected */
    public WebChromeClient a() {
        return new f();
    }

    public void a(int i, int i2) {
        if (this.h != null) {
            this.h.a(i);
            this.h.b(i2);
        }
    }

    /* Access modifiers changed, original: protected */
    public WebViewClient b() {
        return new g(this.b, new WeakReference(this.h), new WeakReference(this.i), new WeakReference(this.d), new WeakReference(this));
    }

    /* Access modifiers changed, original: final */
    @VisibleForTesting
    public final boolean d() {
        return !this.l || this.c.get();
    }

    public void destroy() {
        if (this.h != null) {
            this.h.c();
            this.h = null;
        }
        y.b(this);
        this.j = null;
        this.i = null;
        com.facebook.ads.internal.s.c.b.a(this);
        super.destroy();
    }

    public Map<String, String> getTouchData() {
        return this.i.e();
    }

    public w getTouchDataRecorder() {
        return this.i;
    }

    public com.facebook.ads.internal.t.a getViewabilityChecker() {
        return this.h;
    }

    /* Access modifiers changed, original: protected */
    public void onDraw(Canvas canvas) {
        if (this.m > 0.0f) {
            this.f.set(0.0f, 0.0f, (float) getWidth(), (float) getHeight());
            this.e.reset();
            this.e.addRoundRect(this.f, this.m, this.m, Direction.CW);
            canvas.clipPath(this.e);
        }
        super.onDraw(canvas);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        this.i.a(motionEvent, this, this);
        return super.onTouchEvent(motionEvent);
    }

    /* Access modifiers changed, original: protected */
    public void onWindowVisibilityChanged(int i) {
        super.onWindowVisibilityChanged(i);
        if (this.b.get() != null) {
            ((b) this.b.get()).a(i);
        }
        if (this.h != null) {
            if (i == 0 && d()) {
                this.h.a();
                return;
            }
            if (i == 8) {
                this.h.c();
            }
        }
    }

    public void setCheckAssetsByJavascriptBridge(boolean z) {
        this.d.set(z);
    }

    public void setCornerRadius(float f) {
        this.m = f;
        invalidate();
    }

    public void setLogMultipleImpressions(boolean z) {
        this.k = z;
    }

    public void setOnAssetsLoadedListener(d dVar) {
        this.g = new WeakReference(dVar);
    }

    public void setWaitForAssetsToLoad(boolean z) {
        this.l = z;
    }
}
