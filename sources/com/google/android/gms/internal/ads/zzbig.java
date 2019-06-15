package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.webkit.WebViewClient;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.common.util.VisibleForTesting;

@zzark
public class zzbig extends zzbid implements zzbij {
    private boolean zzdas;
    private final zzbie zzfbf;
    private boolean zzfbg;

    public zzbig(Context context, zzbie zzbie) {
        super(context);
        zzbv.zzlj().zzyn();
        this.zzfbf = zzbie;
        super.setWebViewClient(zzbie);
    }

    public void setWebViewClient(WebViewClient webViewClient) {
    }

    /* Access modifiers changed, original: protected */
    public void zzba(boolean z) {
    }

    public final synchronized boolean isDestroyed() {
        return this.zzdas;
    }

    public synchronized void destroy() {
        if (!this.zzdas) {
            this.zzdas = true;
            this.zzfbf.zza((zzbij) this);
            zzba(false);
            zzaxz.v("Initiating WebView self destruct sequence in 3...");
            zzaxz.v("Loading blank page in WebView, 2...");
            try {
                super.loadUrl("about:blank");
            } catch (UnsatisfiedLinkError e) {
                zzbv.zzlj().zza(e, "AdWebViewImpl.loadUrlUnsafe");
                zzbbd.zzd("#007 Could not call remote method.", e);
            }
        }
    }

    public final synchronized void zza(zzbif zzbif) {
        zzaxz.v("Blank page loaded, 1...");
        zzadr();
    }

    /* Access modifiers changed, original: protected|declared_synchronized */
    @VisibleForTesting
    public synchronized void zzadr() {
        zzaxz.v("Destroying WebView!");
        zzyo();
        zzbcg.zzepo.execute(new zzbih(this));
    }

    /* Access modifiers changed, original: protected */
    public void finalize() throws Throwable {
        try {
            synchronized (this) {
                if (!isDestroyed()) {
                    zzba(true);
                }
                zzyo();
            }
            super.finalize();
        } catch (Throwable th) {
            super.finalize();
        }
    }

    private final synchronized void zzyo() {
        if (!this.zzfbg) {
            this.zzfbg = true;
            zzbv.zzlj().zzyo();
        }
    }

    /* JADX WARNING: Missing block: B:8:0x0013, code skipped:
            return;
     */
    @android.annotation.TargetApi(19)
    public synchronized void evaluateJavascript(java.lang.String r2, android.webkit.ValueCallback<java.lang.String> r3) {
        /*
        r1 = this;
        monitor-enter(r1);
        r0 = r1.isDestroyed();	 Catch:{ all -> 0x0019 }
        if (r0 == 0) goto L_0x0014;
    L_0x0007:
        r2 = "#004 The webview is destroyed. Ignoring action.";
        com.google.android.gms.internal.ads.zzbbd.zzeo(r2);	 Catch:{ all -> 0x0019 }
        if (r3 == 0) goto L_0x0012;
    L_0x000e:
        r2 = 0;
        r3.onReceiveValue(r2);	 Catch:{ all -> 0x0019 }
    L_0x0012:
        monitor-exit(r1);
        return;
    L_0x0014:
        super.evaluateJavascript(r2, r3);	 Catch:{ all -> 0x0019 }
        monitor-exit(r1);
        return;
    L_0x0019:
        r2 = move-exception;
        monitor-exit(r1);
        throw r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzbig.evaluateJavascript(java.lang.String, android.webkit.ValueCallback):void");
    }

    public synchronized void loadUrl(String str) {
        if (isDestroyed()) {
            zzbbd.zzeo("#004 The webview is destroyed. Ignoring action.");
        } else {
            super.loadUrl(str);
        }
    }

    public synchronized void loadData(String str, String str2, String str3) {
        if (isDestroyed()) {
            zzbbd.zzeo("#004 The webview is destroyed. Ignoring action.");
        } else {
            super.loadData(str, str2, str3);
        }
    }

    public synchronized void loadDataWithBaseURL(String str, String str2, String str3, String str4, String str5) {
        if (isDestroyed()) {
            zzbbd.zzeo("#004 The webview is destroyed. Ignoring action.");
        } else {
            super.loadDataWithBaseURL(str, str2, str3, str4, str5);
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        return !isDestroyed() && super.onTouchEvent(motionEvent);
    }

    /* Access modifiers changed, original: protected */
    @TargetApi(21)
    public void onDraw(Canvas canvas) {
        if (!isDestroyed()) {
            super.onDraw(canvas);
        }
    }

    public void onPause() {
        if (!isDestroyed()) {
            super.onPause();
        }
    }

    public void onResume() {
        if (!isDestroyed()) {
            super.onResume();
        }
    }

    public void stopLoading() {
        if (!isDestroyed()) {
            super.stopLoading();
        }
    }

    public /* bridge */ /* synthetic */ void addJavascriptInterface(Object obj, String str) {
        super.addJavascriptInterface(obj, str);
    }

    public /* bridge */ /* synthetic */ void zzcg(String str) {
        super.zzcg(str);
    }

    /* Access modifiers changed, original: final|synthetic */
    public final /* synthetic */ void zzaff() {
        super.destroy();
    }
}
