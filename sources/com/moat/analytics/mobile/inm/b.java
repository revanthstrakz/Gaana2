package com.moat.analytics.mobile.inm;

import android.app.Activity;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebView;
import com.facebook.internal.AnalyticsEvents;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

abstract class b {
    m a = null;
    WeakReference<WebView> b;
    j c;
    TrackerListener d;
    final String e;
    final boolean f;
    private WeakReference<View> g;
    private final z h;
    private final boolean i;
    private boolean j;
    private boolean k;

    b(@Nullable View view, boolean z, boolean z2) {
        String stringBuilder;
        p.a(3, "BaseTracker", (Object) this, "Initializing.");
        if (z) {
            StringBuilder stringBuilder2 = new StringBuilder("m");
            stringBuilder2.append(hashCode());
            stringBuilder = stringBuilder2.toString();
        } else {
            stringBuilder = "";
        }
        this.e = stringBuilder;
        this.g = new WeakReference(view);
        this.i = z;
        this.f = z2;
        this.j = false;
        this.k = false;
        this.h = new z();
    }

    private void i() {
        String str;
        String str2;
        p.a(3, "BaseTracker", (Object) this, "Attempting bridge installation.");
        if (this.b.get() != null) {
            this.c = new j((WebView) this.b.get(), a.WEBVIEW);
            str = "BaseTracker";
            str2 = "Bridge installed.";
        } else {
            this.c = null;
            str = "BaseTracker";
            str2 = "Bridge not installed, WebView is null.";
        }
        p.a(3, str, (Object) this, str2);
    }

    private void j() {
        if (this.j) {
            throw new m("Tracker already started");
        }
    }

    private void k() {
        if (this.k) {
            throw new m("Tracker already stopped");
        }
    }

    private boolean l() {
        return this.i || this.f;
    }

    public abstract String a();

    /* Access modifiers changed, original: 0000 */
    public void a(WebView webView) {
        if (webView != null) {
            this.b = new WeakReference(webView);
            if (this.c == null && !l()) {
                i();
            }
            if (this.c != null) {
                this.c.a(this);
            }
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void a(j jVar) {
        this.c = jVar;
    }

    /* Access modifiers changed, original: 0000 */
    public void a(String str, Exception exception) {
        try {
            m.a(exception);
            str = m.a(str, exception);
            if (this.d != null) {
                this.d.onTrackingFailedToStart(str);
            }
            p.a(3, "BaseTracker", (Object) this, str);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(a());
            stringBuilder.append(" ");
            stringBuilder.append(str);
            p.a("[ERROR] ", stringBuilder.toString());
        } catch (Exception unused) {
        }
    }

    /* Access modifiers changed, original: 0000 */
    @CallSuper
    public void a(List<String> list) {
        if (f() == null && !this.f) {
            list.add("Tracker's target view is null");
        }
        if (!list.isEmpty()) {
            throw new m(TextUtils.join(" and ", list));
        }
    }

    /* Access modifiers changed, original: 0000 */
    @CallSuper
    public void b() {
        p.a(3, "BaseTracker", (Object) this, "Attempting to start impression.");
        c();
        d();
        a(new ArrayList());
        if (this.c != null) {
            this.c.b(this);
            this.j = true;
            p.a(3, "BaseTracker", (Object) this, "Impression started.");
            return;
        }
        p.a(3, "BaseTracker", (Object) this, "Bridge is null, won't start tracking");
        throw new m("Bridge is null");
    }

    /* Access modifiers changed, original: 0000 */
    public void c() {
        if (this.a != null) {
            StringBuilder stringBuilder = new StringBuilder("Tracker initialization failed: ");
            stringBuilder.append(this.a.getMessage());
            throw new m(stringBuilder.toString());
        }
    }

    @CallSuper
    public void changeTargetView(View view) {
        StringBuilder stringBuilder = new StringBuilder("changing view to ");
        stringBuilder.append(p.a(view));
        p.a(3, "BaseTracker", (Object) this, stringBuilder.toString());
        this.g = new WeakReference(view);
    }

    /* Access modifiers changed, original: 0000 */
    public void d() {
        j();
        k();
    }

    /* Access modifiers changed, original: 0000 */
    public boolean e() {
        return this.j && !this.k;
    }

    /* Access modifiers changed, original: 0000 */
    public View f() {
        return (View) this.g.get();
    }

    /* Access modifiers changed, original: 0000 */
    public String g() {
        return p.a(f());
    }

    /* Access modifiers changed, original: 0000 */
    public String h() {
        this.h.a(this.e, f());
        return this.h.a;
    }

    public void removeListener() {
        this.d = null;
    }

    @Deprecated
    public void setActivity(Activity activity) {
    }

    public void setListener(TrackerListener trackerListener) {
        this.d = trackerListener;
    }

    public void startTracking() {
        try {
            p.a(3, "BaseTracker", (Object) this, "In startTracking method.");
            b();
            if (this.d != null) {
                TrackerListener trackerListener = this.d;
                StringBuilder stringBuilder = new StringBuilder("Tracking started on ");
                stringBuilder.append(g());
                trackerListener.onTrackingStarted(stringBuilder.toString());
            }
            StringBuilder stringBuilder2 = new StringBuilder("startTracking succeeded for ");
            stringBuilder2.append(g());
            String stringBuilder3 = stringBuilder2.toString();
            p.a(3, "BaseTracker", (Object) this, stringBuilder3);
            StringBuilder stringBuilder4 = new StringBuilder();
            stringBuilder4.append(a());
            stringBuilder4.append(" ");
            stringBuilder4.append(stringBuilder3);
            p.a("[SUCCESS] ", stringBuilder4.toString());
        } catch (Exception e) {
            a("startTracking", e);
        }
    }

    @CallSuper
    public void stopTracking() {
        boolean z = false;
        try {
            p.a(3, "BaseTracker", (Object) this, "In stopTracking method.");
            this.k = true;
            if (this.c != null) {
                this.c.c(this);
                z = true;
            }
        } catch (Exception e) {
            m.a(e);
        }
        String str = "BaseTracker";
        StringBuilder stringBuilder = new StringBuilder("Attempt to stop tracking ad impression was ");
        stringBuilder.append(z ? "" : "un");
        stringBuilder.append("successful.");
        p.a(3, str, (Object) this, stringBuilder.toString());
        String str2 = z ? "[SUCCESS] " : "[ERROR] ";
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append(a());
        stringBuilder2.append(" stopTracking ");
        stringBuilder2.append(z ? AnalyticsEvents.PARAMETER_SHARE_OUTCOME_SUCCEEDED : "failed");
        stringBuilder2.append(" for ");
        stringBuilder2.append(g());
        p.a(str2, stringBuilder2.toString());
        if (this.d != null) {
            this.d.onTrackingStopped("");
            this.d = null;
        }
    }
}
