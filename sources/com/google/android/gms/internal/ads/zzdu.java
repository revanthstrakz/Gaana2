package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.app.Application;
import android.app.Application.ActivityLifecycleCallbacks;
import android.app.KeyguardManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.PowerManager;
import android.view.View;
import android.view.View.OnAttachStateChangeListener;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.ViewTreeObserver.OnScrollChangedListener;
import android.view.Window;
import java.lang.ref.WeakReference;

public final class zzdu implements ActivityLifecycleCallbacks, OnAttachStateChangeListener, OnGlobalLayoutListener, OnScrollChangedListener {
    private static final Handler zztu = new Handler(Looper.getMainLooper());
    private final zzdl zzqo;
    private Application zzsg;
    private final Context zztv;
    private final PowerManager zztw;
    private final KeyguardManager zztx;
    private BroadcastReceiver zzty;
    private WeakReference<ViewTreeObserver> zztz;
    private WeakReference<View> zzua;
    private zzcz zzub;
    private boolean zzuc = false;
    private int zzud = -1;
    private long zzue = -3;

    public zzdu(zzdl zzdl, View view) {
        this.zzqo = zzdl;
        this.zztv = zzdl.zzsp;
        this.zztw = (PowerManager) this.zztv.getSystemService("power");
        this.zztx = (KeyguardManager) this.zztv.getSystemService("keyguard");
        if (this.zztv instanceof Application) {
            this.zzsg = (Application) this.zztv;
            this.zzub = new zzcz((Application) this.zztv, this);
        }
        zzd(view);
    }

    /* Access modifiers changed, original: final */
    public final void zzd(View view) {
        View view2 = this.zzua != null ? (View) this.zzua.get() : null;
        if (view2 != null) {
            view2.removeOnAttachStateChangeListener(this);
            zzf(view2);
        }
        this.zzua = new WeakReference(view);
        if (view != null) {
            Object obj = (view.getWindowToken() == null && view.getWindowVisibility() == 8) ? null : 1;
            if (obj != null) {
                zze(view);
            }
            view.addOnAttachStateChangeListener(this);
            this.zzue = -2;
            return;
        }
        this.zzue = -3;
    }

    private final void zzap() {
        zztu.post(new zzdv(this));
    }

    public final void onViewAttachedToWindow(View view) {
        this.zzud = -1;
        zze(view);
        zzar();
    }

    public final void onViewDetachedFromWindow(View view) {
        this.zzud = -1;
        zzar();
        zzap();
        zzf(view);
    }

    private final void zza(Activity activity, int i) {
        if (this.zzua != null) {
            Window window = activity.getWindow();
            if (window != null) {
                View peekDecorView = window.peekDecorView();
                View view = (View) this.zzua.get();
                if (!(view == null || peekDecorView == null || view.getRootView() != peekDecorView.getRootView())) {
                    this.zzud = i;
                }
            }
        }
    }

    public final void onActivityCreated(Activity activity, Bundle bundle) {
        zza(activity, 0);
        zzar();
    }

    public final void onActivityStarted(Activity activity) {
        zza(activity, 0);
        zzar();
    }

    public final void onActivityResumed(Activity activity) {
        zza(activity, 0);
        zzar();
        zzap();
    }

    public final void onActivityPaused(Activity activity) {
        zza(activity, 4);
        zzar();
    }

    public final void onActivityStopped(Activity activity) {
        zzar();
    }

    public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        zzar();
    }

    public final void onActivityDestroyed(Activity activity) {
        zzar();
    }

    public final void onGlobalLayout() {
        zzar();
    }

    public final void onScrollChanged() {
        zzar();
    }

    public final long zzaq() {
        if (this.zzue == -2 && this.zzua.get() == null) {
            this.zzue = -3;
        }
        return this.zzue;
    }

    /* JADX WARNING: Removed duplicated region for block: B:27:0x0067  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x008a  */
    /* JADX WARNING: Missing block: B:22:0x0058, code skipped:
            if (r4 == false) goto L_0x005b;
     */
    private final void zzar() {
        /*
        r9 = this;
        r0 = r9.zzua;
        if (r0 != 0) goto L_0x0005;
    L_0x0004:
        return;
    L_0x0005:
        r0 = r9.zzua;
        r0 = r0.get();
        r0 = (android.view.View) r0;
        r1 = 0;
        if (r0 != 0) goto L_0x0017;
    L_0x0010:
        r2 = -3;
        r9.zzue = r2;
        r9.zzuc = r1;
        return;
    L_0x0017:
        r2 = new android.graphics.Rect;
        r2.<init>();
        r2 = r0.getGlobalVisibleRect(r2);
        r3 = new android.graphics.Rect;
        r3.<init>();
        r3 = r0.getLocalVisibleRect(r3);
        r4 = r9.zzqo;
        r4 = r4.zzaj();
        r5 = 1;
        if (r4 != 0) goto L_0x005d;
    L_0x0032:
        r4 = r9.zztx;
        r4 = r4.inKeyguardRestrictedInputMode();
        if (r4 == 0) goto L_0x005b;
    L_0x003a:
        r4 = com.google.android.gms.internal.ads.zzds.zzc(r0);
        if (r4 == 0) goto L_0x0057;
    L_0x0040:
        r4 = r4.getWindow();
        if (r4 != 0) goto L_0x0048;
    L_0x0046:
        r4 = 0;
        goto L_0x004c;
    L_0x0048:
        r4 = r4.getAttributes();
    L_0x004c:
        if (r4 == 0) goto L_0x0057;
    L_0x004e:
        r4 = r4.flags;
        r6 = 524288; // 0x80000 float:7.34684E-40 double:2.590327E-318;
        r4 = r4 & r6;
        if (r4 == 0) goto L_0x0057;
    L_0x0055:
        r4 = r5;
        goto L_0x0058;
    L_0x0057:
        r4 = r1;
    L_0x0058:
        if (r4 == 0) goto L_0x005b;
    L_0x005a:
        goto L_0x005d;
    L_0x005b:
        r4 = r1;
        goto L_0x005e;
    L_0x005d:
        r4 = r5;
    L_0x005e:
        r6 = r0.getWindowVisibility();
        r7 = r9.zzud;
        r8 = -1;
        if (r7 == r8) goto L_0x0069;
    L_0x0067:
        r6 = r9.zzud;
    L_0x0069:
        r7 = r0.getVisibility();
        if (r7 != 0) goto L_0x0086;
    L_0x006f:
        r0 = r0.isShown();
        if (r0 == 0) goto L_0x0086;
    L_0x0075:
        r0 = r9.zztw;
        r0 = r0.isScreenOn();
        if (r0 == 0) goto L_0x0086;
    L_0x007d:
        if (r4 == 0) goto L_0x0086;
    L_0x007f:
        if (r3 == 0) goto L_0x0086;
    L_0x0081:
        if (r2 == 0) goto L_0x0086;
    L_0x0083:
        if (r6 != 0) goto L_0x0086;
    L_0x0085:
        r1 = r5;
    L_0x0086:
        r0 = r9.zzuc;
        if (r0 == r1) goto L_0x0097;
    L_0x008a:
        if (r1 == 0) goto L_0x0091;
    L_0x008c:
        r2 = android.os.SystemClock.elapsedRealtime();
        goto L_0x0093;
    L_0x0091:
        r2 = -2;
    L_0x0093:
        r9.zzue = r2;
        r9.zzuc = r1;
    L_0x0097:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzdu.zzar():void");
    }

    private final void zze(View view) {
        ViewTreeObserver viewTreeObserver = view.getViewTreeObserver();
        if (viewTreeObserver.isAlive()) {
            this.zztz = new WeakReference(viewTreeObserver);
            viewTreeObserver.addOnScrollChangedListener(this);
            viewTreeObserver.addOnGlobalLayoutListener(this);
        }
        if (this.zzty == null) {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.intent.action.SCREEN_ON");
            intentFilter.addAction("android.intent.action.SCREEN_OFF");
            intentFilter.addAction("android.intent.action.USER_PRESENT");
            this.zzty = new zzdw(this);
            this.zztv.registerReceiver(this.zzty, intentFilter);
        }
        if (this.zzsg != null) {
            try {
                this.zzsg.registerActivityLifecycleCallbacks(this.zzub);
            } catch (Exception unused) {
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x0027 A:{Catch:{ Exception -> 0x002d }} */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0031 A:{SYNTHETIC, Splitter:B:17:0x0031} */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x003e A:{SYNTHETIC, Splitter:B:23:0x003e} */
    /* JADX WARNING: Missing exception handler attribute for start block: B:10:0x001d */
    /* JADX WARNING: Failed to process nested try/catch */
    private final void zzf(android.view.View r4) {
        /*
        r3 = this;
        r0 = 0;
        r1 = r3.zztz;	 Catch:{ Exception -> 0x001d }
        if (r1 == 0) goto L_0x001d;
    L_0x0005:
        r1 = r3.zztz;	 Catch:{ Exception -> 0x001d }
        r1 = r1.get();	 Catch:{ Exception -> 0x001d }
        r1 = (android.view.ViewTreeObserver) r1;	 Catch:{ Exception -> 0x001d }
        if (r1 == 0) goto L_0x001b;
    L_0x000f:
        r2 = r1.isAlive();	 Catch:{ Exception -> 0x001d }
        if (r2 == 0) goto L_0x001b;
    L_0x0015:
        r1.removeOnScrollChangedListener(r3);	 Catch:{ Exception -> 0x001d }
        r1.removeGlobalOnLayoutListener(r3);	 Catch:{ Exception -> 0x001d }
    L_0x001b:
        r3.zztz = r0;	 Catch:{ Exception -> 0x001d }
    L_0x001d:
        r4 = r4.getViewTreeObserver();	 Catch:{ Exception -> 0x002d }
        r1 = r4.isAlive();	 Catch:{ Exception -> 0x002d }
        if (r1 == 0) goto L_0x002d;
    L_0x0027:
        r4.removeOnScrollChangedListener(r3);	 Catch:{ Exception -> 0x002d }
        r4.removeGlobalOnLayoutListener(r3);	 Catch:{ Exception -> 0x002d }
    L_0x002d:
        r4 = r3.zzty;
        if (r4 == 0) goto L_0x003a;
    L_0x0031:
        r4 = r3.zztv;	 Catch:{ Exception -> 0x0038 }
        r1 = r3.zzty;	 Catch:{ Exception -> 0x0038 }
        r4.unregisterReceiver(r1);	 Catch:{ Exception -> 0x0038 }
    L_0x0038:
        r3.zzty = r0;
    L_0x003a:
        r4 = r3.zzsg;
        if (r4 == 0) goto L_0x0046;
    L_0x003e:
        r4 = r3.zzsg;	 Catch:{ Exception -> 0x0046 }
        r0 = r3.zzub;	 Catch:{ Exception -> 0x0046 }
        r4.unregisterActivityLifecycleCallbacks(r0);	 Catch:{ Exception -> 0x0046 }
        return;
    L_0x0046:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzdu.zzf(android.view.View):void");
    }
}
