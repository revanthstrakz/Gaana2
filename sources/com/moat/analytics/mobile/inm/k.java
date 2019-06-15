package com.moat.analytics.mobile.inm;

import android.app.Application;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.annotation.UiThread;
import java.lang.ref.WeakReference;

class k extends MoatAnalytics implements b {
    boolean a = false;
    boolean b = false;
    boolean c = false;
    @Nullable
    g d;
    WeakReference<Context> e;
    private boolean f = false;
    private String g;
    private MoatOptions h;

    k() {
    }

    private void a(MoatOptions moatOptions, Application application) {
        if (this.f) {
            p.a(3, "Analytics", (Object) this, "Moat SDK has already been started.");
            return;
        }
        this.h = moatOptions;
        w.a().b();
        this.c = moatOptions.disableLocationServices;
        if (application == null) {
            throw new m("Moat Analytics SDK didn't start, application was null");
        }
        if (moatOptions.loggingEnabled && s.b(application.getApplicationContext())) {
            this.a = true;
        }
        this.e = new WeakReference(application.getApplicationContext());
        this.f = true;
        this.b = moatOptions.autoTrackGMAInterstitials;
        a.a(application);
        w.a().a((b) this);
        if (!moatOptions.disableAdIdCollection) {
            s.a((Context) application);
        }
        p.a("[SUCCESS] ", "Moat Analytics SDK Version 2.5.0 started");
    }

    @UiThread
    private void e() {
        if (this.d == null) {
            this.d = new g(a.a(), a.DISPLAY);
            this.d.a(this.g);
            StringBuilder stringBuilder = new StringBuilder("Preparing native display tracking with partner code ");
            stringBuilder.append(this.g);
            p.a(3, "Analytics", (Object) this, stringBuilder.toString());
            StringBuilder stringBuilder2 = new StringBuilder("Prepared for native display tracking with partner code ");
            stringBuilder2.append(this.g);
            p.a("[SUCCESS] ", stringBuilder2.toString());
        }
    }

    /* Access modifiers changed, original: 0000 */
    public boolean a() {
        return this.f;
    }

    /* Access modifiers changed, original: 0000 */
    public boolean b() {
        return this.h != null && this.h.disableLocationServices;
    }

    public void c() {
        m.a();
        o.a();
        if (this.g != null) {
            try {
                e();
            } catch (Exception e) {
                m.a(e);
            }
        }
    }

    public void d() {
    }

    @UiThread
    public void prepareNativeDisplayTracking(String str) {
        this.g = str;
        if (w.a().a != d.OFF) {
            try {
                e();
            } catch (Exception e) {
                m.a(e);
            }
        }
    }

    public void start(Application application) {
        start(new MoatOptions(), application);
    }

    public void start(MoatOptions moatOptions, Application application) {
        try {
            a(moatOptions, application);
        } catch (Exception e) {
            m.a(e);
        }
    }
}
