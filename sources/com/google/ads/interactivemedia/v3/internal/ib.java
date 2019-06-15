package com.google.ads.interactivemedia.v3.internal;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.app.Application.ActivityLifecycleCallbacks;
import android.content.Context;
import android.graphics.Rect;
import android.media.AudioManager;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.util.DisplayMetrics;
import android.view.View;
import com.google.ads.interactivemedia.v3.impl.data.m;
import com.integralads.avid.library.inmobi.AvidBridge;

public class ib implements com.google.ads.interactivemedia.v3.internal.jd.a {
    private final jd a;
    private String b;
    private View c;
    private b d;
    private a e;
    private Activity f;
    private boolean g;

    @TargetApi(14)
    protected class a implements ActivityLifecycleCallbacks {
        protected a() {
        }

        public void onActivityCreated(Activity activity, Bundle bundle) {
        }

        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        }

        public void onActivityStarted(Activity activity) {
        }

        public void onActivityStopped(Activity activity) {
        }

        public void onActivityResumed(Activity activity) {
            if (ib.this.f == activity) {
                ib.this.a.b(new jc(com.google.ads.interactivemedia.v3.internal.jc.a.activityMonitor, com.google.ads.interactivemedia.v3.internal.jc.b.appStateChanged, ib.this.b, ib.this.a("", "", "", "active")));
            }
        }

        public void onActivityPaused(Activity activity) {
            if (ib.this.f == null || ib.this.f == activity) {
                ib.this.f = activity;
                ib.this.a.b(new jc(com.google.ads.interactivemedia.v3.internal.jc.a.activityMonitor, com.google.ads.interactivemedia.v3.internal.jc.b.appStateChanged, ib.this.b, ib.this.a("", "", "", AvidBridge.APP_STATE_INACTIVE)));
            }
        }

        public void onActivityDestroyed(Activity activity) {
            if (ib.this.f == activity) {
                ib.this.f = null;
                Application d = ib.this.j();
                if (d != null) {
                    d.unregisterActivityLifecycleCallbacks(ib.this.e);
                }
            }
        }
    }

    public interface b {
        long a();
    }

    private static class c implements b {
        private c() {
        }

        public long a() {
            return System.currentTimeMillis();
        }
    }

    private static int a(int i, float f) {
        return (int) Math.ceil((double) (((float) i) / f));
    }

    private static m a(m mVar, float f) {
        return m.builder().left(a(mVar.left(), f)).top(a(mVar.top(), f)).height(a(mVar.height(), f)).width(a(mVar.width(), f)).build();
    }

    private DisplayMetrics i() {
        return this.c.getContext().getResources().getDisplayMetrics();
    }

    public ib(String str, jd jdVar, View view) {
        this(str, jdVar, view, new c());
    }

    protected ib(String str, jd jdVar, View view, b bVar) {
        this.b = str;
        this.a = jdVar;
        this.c = view;
        this.d = bVar;
        this.f = null;
        this.e = null;
        this.g = false;
    }

    /* Access modifiers changed, original: protected */
    public void a(boolean z) {
        this.g = z;
    }

    private Application j() {
        Context applicationContext = this.c.getContext().getApplicationContext();
        return applicationContext instanceof Application ? (Application) applicationContext : null;
    }

    public void a() {
        this.a.a((com.google.ads.interactivemedia.v3.internal.jd.a) this, this.b);
    }

    public void b() {
        this.a.a(this.b);
    }

    @TargetApi(14)
    public void c() {
        if (VERSION.SDK_INT >= 14 && this.g) {
            Application j = j();
            if (j != null) {
                this.e = new a();
                j.registerActivityLifecycleCallbacks(this.e);
            }
        }
    }

    @TargetApi(14)
    public void d() {
        if (VERSION.SDK_INT >= 14) {
            Application j = j();
            if (j != null && this.e != null) {
                j.unregisterActivityLifecycleCallbacks(this.e);
            }
        }
    }

    public double e() {
        AudioManager audioManager = (AudioManager) this.c.getContext().getSystemService("audio");
        if (audioManager == null) {
            return 0.0d;
        }
        return ((double) audioManager.getStreamVolume(3)) / ((double) audioManager.getStreamMaxVolume(3));
    }

    public boolean f() {
        return (this.c.getGlobalVisibleRect(new Rect()) && this.c.isShown()) ? false : true;
    }

    public m g() {
        return a(m.builder().locationOnScreenOfView(this.c).build(), i().density);
    }

    public m h() {
        Rect rect = new Rect();
        boolean globalVisibleRect = this.c.getGlobalVisibleRect(rect);
        int i = this.c.getWindowToken() != null ? 1 : 0;
        if (!(globalVisibleRect && i != 0 && this.c.isShown())) {
            rect.set(0, 0, 0, 0);
        }
        return a(m.builder().left(rect.left).top(rect.top).height(rect.height()).width(rect.width()).build(), i().density);
    }

    public com.google.ads.interactivemedia.v3.impl.data.a a(String str, String str2, String str3, String str4) {
        m g = g();
        m h = h();
        boolean isAttachedToWindow = ViewCompat.isAttachedToWindow(this.c);
        return com.google.ads.interactivemedia.v3.impl.data.a.builder().queryId(str).eventId(str2).vastEvent(str3).appState(str4).nativeTime(this.d.a()).nativeVolume(e()).nativeViewAttached(isAttachedToWindow).nativeViewHidden(f()).nativeViewBounds(g).nativeViewVisibleBounds(h).build();
    }

    public void a(String str, String str2) {
        this.a.b(new jc(com.google.ads.interactivemedia.v3.internal.jc.a.activityMonitor, com.google.ads.interactivemedia.v3.internal.jc.b.viewability, this.b, a(str, str2, "", "")));
    }

    public void a(String str, String str2, String str3) {
        this.a.b(new jc(com.google.ads.interactivemedia.v3.internal.jc.a.activityMonitor, com.google.ads.interactivemedia.v3.internal.jc.b.viewability, this.b, a(str, str2, str3, "")));
    }
}
