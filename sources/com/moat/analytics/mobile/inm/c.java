package com.moat.analytics.mobile.inm;

import android.os.Handler;
import android.support.annotation.CallSuper;
import android.text.TextUtils;
import android.view.View;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import org.json.JSONObject;

abstract class c extends b {
    static final MoatAdEventType[] g = new MoatAdEventType[]{MoatAdEventType.AD_EVT_FIRST_QUARTILE, MoatAdEventType.AD_EVT_MID_POINT, MoatAdEventType.AD_EVT_THIRD_QUARTILE};
    final Map<MoatAdEventType, Integer> h;
    final Handler i;
    Map<String, String> j;
    WeakReference<View> k;
    private final Set<MoatAdEventType> l;
    private VideoTrackerListener m;
    private boolean n;
    private Double o;
    private final g p = new g(a.a(), a.VIDEO);
    private final String q;

    c(String str) {
        super(null, false, true);
        p.a(3, "BaseVideoTracker", (Object) this, "Initializing.");
        this.q = str;
        super.a(this.p.b);
        try {
            super.a(this.p.a);
        } catch (m e) {
            this.a = e;
        }
        this.h = new HashMap();
        this.l = new HashSet();
        this.i = new Handler();
        this.n = false;
        this.o = Double.valueOf(1.0d);
    }

    private static boolean a(MoatAdEventType moatAdEventType) {
        return moatAdEventType == MoatAdEventType.AD_EVT_COMPLETE || moatAdEventType == MoatAdEventType.AD_EVT_STOPPED || moatAdEventType == MoatAdEventType.AD_EVT_SKIPPED;
    }

    private void b(MoatAdEvent moatAdEvent) {
        p.a(3, "BaseVideoTracker", (Object) this, String.format("Received event: %s", new Object[]{a(moatAdEvent).toString()}));
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(a());
        stringBuilder.append(String.format(" Received event: %s", new Object[]{r0.toString()}));
        p.a("[SUCCESS] ", stringBuilder.toString());
        if (e() && this.c != null) {
            this.c.a(this.p.c, r0);
            if (!this.l.contains(moatAdEvent.d)) {
                this.l.add(moatAdEvent.d);
                if (this.m != null) {
                    this.m.onVideoEventReported(moatAdEvent.d);
                }
            }
        }
        MoatAdEventType moatAdEventType = moatAdEvent.d;
        if (a(moatAdEventType)) {
            this.h.put(moatAdEventType, Integer.valueOf(1));
            if (this.c != null) {
                this.c.c((b) this);
            }
            l();
        }
    }

    /* Access modifiers changed, original: 0000 */
    public JSONObject a(MoatAdEvent moatAdEvent) {
        if (Double.isNaN(moatAdEvent.c.doubleValue())) {
            moatAdEvent.c = this.o;
        }
        return new JSONObject(moatAdEvent.a());
    }

    /* Access modifiers changed, original: 0000 */
    public void a(List<String> list) {
        if (this.j == null) {
            list.add("Null adIds object");
        }
        if (list.isEmpty()) {
            super.a((List) list);
            return;
        }
        throw new m(TextUtils.join(" and ", list));
    }

    /* Access modifiers changed, original: 0000 */
    public boolean a(Integer num, Integer num2) {
        return ((double) Math.abs(num2.intValue() - num.intValue())) <= Math.min(750.0d, ((double) num2.intValue()) * 0.05d);
    }

    @CallSuper
    public boolean a(Map<String, String> map, View view) {
        try {
            c();
            d();
            if (view == null) {
                p.a(3, "BaseVideoTracker", (Object) this, "trackVideoAd received null video view instance");
            }
            this.j = map;
            this.k = new WeakReference(view);
            b();
            String format = String.format("trackVideoAd tracking ids: %s | view: %s", new Object[]{new JSONObject(map).toString(), p.a(view)});
            p.a(3, "BaseVideoTracker", (Object) this, format);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(a());
            stringBuilder.append(" ");
            stringBuilder.append(format);
            p.a("[SUCCESS] ", stringBuilder.toString());
            if (this.d != null) {
                this.d.onTrackingStarted(g());
            }
            return true;
        } catch (Exception e) {
            a("trackVideoAd", e);
            return false;
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void b() {
        super.changeTargetView((View) this.k.get());
        super.b();
        Map i = i();
        Integer num = (Integer) i.get("height");
        p.a(3, "BaseVideoTracker", (Object) this, String.format(Locale.ROOT, "Player metadata: height = %d, width = %d, duration = %d", new Object[]{num, (Integer) i.get("width"), (Integer) i.get("duration")}));
        this.p.a(this.q, this.j, r5, num, r7);
    }

    public void changeTargetView(View view) {
        StringBuilder stringBuilder = new StringBuilder("changing view to ");
        stringBuilder.append(p.a(view));
        p.a(3, "BaseVideoTracker", (Object) this, stringBuilder.toString());
        this.k = new WeakReference(view);
        try {
            super.changeTargetView(view);
        } catch (Exception e) {
            m.a(e);
        }
    }

    public void dispatchEvent(MoatAdEvent moatAdEvent) {
        try {
            b(moatAdEvent);
        } catch (Exception e) {
            m.a(e);
        }
    }

    public abstract Map<String, Object> i();

    /* Access modifiers changed, original: 0000 */
    public Double j() {
        return Double.valueOf(k().doubleValue() * s.a());
    }

    /* Access modifiers changed, original: 0000 */
    public Double k() {
        return this.o;
    }

    /* Access modifiers changed, original: 0000 */
    public void l() {
        if (!this.n) {
            this.n = true;
            this.i.postDelayed(new Runnable() {
                public void run() {
                    try {
                        p.a(3, "BaseVideoTracker", (Object) this, "Shutting down.");
                        c.this.p.a();
                        c.this.m = null;
                    } catch (Exception e) {
                        m.a(e);
                    }
                }
            }, 500);
        }
    }

    /* Access modifiers changed, original: 0000 */
    public boolean m() {
        return this.h.containsKey(MoatAdEventType.AD_EVT_COMPLETE) || this.h.containsKey(MoatAdEventType.AD_EVT_STOPPED) || this.h.containsKey(MoatAdEventType.AD_EVT_SKIPPED);
    }

    public void removeVideoListener() {
        this.m = null;
    }

    public void setPlayerVolume(Double d) {
        Double j = j();
        if (!d.equals(this.o)) {
            p.a(3, "BaseVideoTracker", (Object) this, String.format(Locale.ROOT, "player volume changed to %f ", new Object[]{d}));
            this.o = d;
            if (!j.equals(j())) {
                dispatchEvent(new MoatAdEvent(MoatAdEventType.AD_EVT_VOLUME_CHANGE, MoatAdEvent.a, this.o));
            }
        }
    }

    public void setVideoListener(VideoTrackerListener videoTrackerListener) {
        this.m = videoTrackerListener;
    }

    public void stopTracking() {
        try {
            super.stopTracking();
            l();
            if (this.m != null) {
                this.m = null;
            }
        } catch (Exception e) {
            m.a(e);
        }
    }
}
