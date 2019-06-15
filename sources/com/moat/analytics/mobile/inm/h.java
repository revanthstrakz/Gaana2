package com.moat.analytics.mobile.inm;

import android.view.View;
import java.util.Map;
import org.json.JSONObject;

abstract class h extends c {
    int l = Integer.MIN_VALUE;
    private a m = a.UNINITIALIZED;
    private int n = Integer.MIN_VALUE;
    private double o = Double.NaN;
    private int p = Integer.MIN_VALUE;
    private int q = 0;

    enum a {
        UNINITIALIZED,
        PAUSED,
        PLAYING,
        STOPPED,
        COMPLETED
    }

    h(String str) {
        super(str);
    }

    private void t() {
        this.i.postDelayed(new Runnable() {
            public void run() {
                try {
                    if (!h.this.n() || h.this.m()) {
                        h.this.l();
                    } else if (Boolean.valueOf(h.this.s()).booleanValue()) {
                        h.this.i.postDelayed(this, 200);
                    } else {
                        h.this.l();
                    }
                } catch (Exception e) {
                    h.this.l();
                    m.a(e);
                }
            }
        }, 200);
    }

    /* Access modifiers changed, original: 0000 */
    public JSONObject a(MoatAdEvent moatAdEvent) {
        Integer o;
        if (moatAdEvent.b.equals(MoatAdEvent.a)) {
            try {
                o = o();
            } catch (Exception unused) {
                o = Integer.valueOf(this.n);
            }
            moatAdEvent.b = o;
        } else {
            o = moatAdEvent.b;
        }
        if (moatAdEvent.b.intValue() < 0 || (moatAdEvent.b.intValue() == 0 && moatAdEvent.d == MoatAdEventType.AD_EVT_COMPLETE && this.n > 0)) {
            o = Integer.valueOf(this.n);
            moatAdEvent.b = o;
        }
        if (moatAdEvent.d == MoatAdEventType.AD_EVT_COMPLETE) {
            if (o.intValue() == Integer.MIN_VALUE || this.l == Integer.MIN_VALUE || !a(o, Integer.valueOf(this.l))) {
                this.m = a.STOPPED;
                moatAdEvent.d = MoatAdEventType.AD_EVT_STOPPED;
            } else {
                this.m = a.COMPLETED;
            }
        }
        return super.a(moatAdEvent);
    }

    public boolean a(Map<String, String> map, View view) {
        boolean a;
        try {
            a = super.a((Map) map, view);
            if (a && p()) {
                t();
                return a;
            }
        } catch (Exception e) {
            p.a(3, "IntervalVideoTracker", (Object) this, "Problem with video loop");
            a("trackVideoAd", e);
            a = false;
        }
        return a;
    }

    public abstract boolean n();

    public abstract Integer o();

    /* Access modifiers changed, original: protected */
    public boolean p() {
        return true;
    }

    public abstract boolean q();

    public abstract Integer r();

    /* Access modifiers changed, original: 0000 */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x0095 A:{Catch:{ Exception -> 0x00ca }} */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x0093 A:{Catch:{ Exception -> 0x00ca }} */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x00b5 A:{Catch:{ Exception -> 0x00ca }} */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x0093 A:{Catch:{ Exception -> 0x00ca }} */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x0095 A:{Catch:{ Exception -> 0x00ca }} */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x00b5 A:{Catch:{ Exception -> 0x00ca }} */
    @android.support.annotation.CallSuper
    public boolean s() {
        /*
        r12 = this;
        r0 = r12.n();
        r1 = 0;
        if (r0 == 0) goto L_0x00d4;
    L_0x0007:
        r0 = r12.m();
        if (r0 == 0) goto L_0x000e;
    L_0x000d:
        return r1;
    L_0x000e:
        r0 = 1;
        r2 = r12.o();	 Catch:{ Exception -> 0x00ca }
        r2 = r2.intValue();	 Catch:{ Exception -> 0x00ca }
        r3 = r12.n;	 Catch:{ Exception -> 0x00ca }
        if (r3 < 0) goto L_0x001e;
    L_0x001b:
        if (r2 >= 0) goto L_0x001e;
    L_0x001d:
        return r1;
    L_0x001e:
        r12.n = r2;	 Catch:{ Exception -> 0x00ca }
        if (r2 != 0) goto L_0x0023;
    L_0x0022:
        return r0;
    L_0x0023:
        r3 = r12.r();	 Catch:{ Exception -> 0x00ca }
        r3 = r3.intValue();	 Catch:{ Exception -> 0x00ca }
        r4 = r12.q();	 Catch:{ Exception -> 0x00ca }
        r5 = (double) r3;	 Catch:{ Exception -> 0x00ca }
        r7 = 4616189618054758400; // 0x4010000000000000 float:0.0 double:4.0;
        r5 = r5 / r7;
        r7 = r12.j();	 Catch:{ Exception -> 0x00ca }
        r7 = r7.doubleValue();	 Catch:{ Exception -> 0x00ca }
        r9 = 0;
        r10 = r12.p;	 Catch:{ Exception -> 0x00ca }
        if (r2 <= r10) goto L_0x0042;
    L_0x0040:
        r12.p = r2;	 Catch:{ Exception -> 0x00ca }
    L_0x0042:
        r10 = r12.l;	 Catch:{ Exception -> 0x00ca }
        r11 = -2147483648; // 0xffffffff80000000 float:-0.0 double:NaN;
        if (r10 != r11) goto L_0x004a;
    L_0x0048:
        r12.l = r3;	 Catch:{ Exception -> 0x00ca }
    L_0x004a:
        if (r4 == 0) goto L_0x0086;
    L_0x004c:
        r3 = r12.m;	 Catch:{ Exception -> 0x00ca }
        r4 = com.moat.analytics.mobile.inm.h.a.UNINITIALIZED;	 Catch:{ Exception -> 0x00ca }
        if (r3 != r4) goto L_0x0059;
    L_0x0052:
        r9 = com.moat.analytics.mobile.inm.MoatAdEventType.AD_EVT_START;	 Catch:{ Exception -> 0x00ca }
    L_0x0054:
        r3 = com.moat.analytics.mobile.inm.h.a.PLAYING;	 Catch:{ Exception -> 0x00ca }
    L_0x0056:
        r12.m = r3;	 Catch:{ Exception -> 0x00ca }
        goto L_0x0091;
    L_0x0059:
        r3 = r12.m;	 Catch:{ Exception -> 0x00ca }
        r4 = com.moat.analytics.mobile.inm.h.a.PAUSED;	 Catch:{ Exception -> 0x00ca }
        if (r3 != r4) goto L_0x0062;
    L_0x005f:
        r9 = com.moat.analytics.mobile.inm.MoatAdEventType.AD_EVT_PLAYING;	 Catch:{ Exception -> 0x00ca }
        goto L_0x0054;
    L_0x0062:
        r3 = (double) r2;	 Catch:{ Exception -> 0x00ca }
        r3 = r3 / r5;
        r3 = java.lang.Math.floor(r3);	 Catch:{ Exception -> 0x00ca }
        r3 = (int) r3;	 Catch:{ Exception -> 0x00ca }
        r3 = r3 - r0;
        if (r3 < 0) goto L_0x0091;
    L_0x006c:
        r4 = 3;
        if (r3 >= r4) goto L_0x0091;
    L_0x006f:
        r4 = g;	 Catch:{ Exception -> 0x00ca }
        r3 = r4[r3];	 Catch:{ Exception -> 0x00ca }
        r4 = r12.h;	 Catch:{ Exception -> 0x00ca }
        r4 = r4.containsKey(r3);	 Catch:{ Exception -> 0x00ca }
        if (r4 != 0) goto L_0x0091;
    L_0x007b:
        r4 = r12.h;	 Catch:{ Exception -> 0x00ca }
        r5 = java.lang.Integer.valueOf(r0);	 Catch:{ Exception -> 0x00ca }
        r4.put(r3, r5);	 Catch:{ Exception -> 0x00ca }
        r9 = r3;
        goto L_0x0091;
    L_0x0086:
        r3 = r12.m;	 Catch:{ Exception -> 0x00ca }
        r4 = com.moat.analytics.mobile.inm.h.a.PAUSED;	 Catch:{ Exception -> 0x00ca }
        if (r3 == r4) goto L_0x0091;
    L_0x008c:
        r9 = com.moat.analytics.mobile.inm.MoatAdEventType.AD_EVT_PAUSED;	 Catch:{ Exception -> 0x00ca }
        r3 = com.moat.analytics.mobile.inm.h.a.PAUSED;	 Catch:{ Exception -> 0x00ca }
        goto L_0x0056;
    L_0x0091:
        if (r9 == 0) goto L_0x0095;
    L_0x0093:
        r3 = r0;
        goto L_0x0096;
    L_0x0095:
        r3 = r1;
    L_0x0096:
        if (r3 != 0) goto L_0x00b3;
    L_0x0098:
        r4 = r12.o;	 Catch:{ Exception -> 0x00ca }
        r4 = java.lang.Double.isNaN(r4);	 Catch:{ Exception -> 0x00ca }
        if (r4 != 0) goto L_0x00b3;
    L_0x00a0:
        r4 = r12.o;	 Catch:{ Exception -> 0x00ca }
        r4 = r4 - r7;
        r4 = java.lang.Math.abs(r4);	 Catch:{ Exception -> 0x00ca }
        r10 = 4587366580439587226; // 0x3fa999999999999a float:-1.5881868E-23 double:0.05;
        r6 = (r4 > r10 ? 1 : (r4 == r10 ? 0 : -1));
        if (r6 <= 0) goto L_0x00b3;
    L_0x00b0:
        r9 = com.moat.analytics.mobile.inm.MoatAdEventType.AD_EVT_VOLUME_CHANGE;	 Catch:{ Exception -> 0x00ca }
        r3 = r0;
    L_0x00b3:
        if (r3 == 0) goto L_0x00c5;
    L_0x00b5:
        r3 = new com.moat.analytics.mobile.inm.MoatAdEvent;	 Catch:{ Exception -> 0x00ca }
        r2 = java.lang.Integer.valueOf(r2);	 Catch:{ Exception -> 0x00ca }
        r4 = r12.k();	 Catch:{ Exception -> 0x00ca }
        r3.<init>(r9, r2, r4);	 Catch:{ Exception -> 0x00ca }
        r12.dispatchEvent(r3);	 Catch:{ Exception -> 0x00ca }
    L_0x00c5:
        r12.o = r7;	 Catch:{ Exception -> 0x00ca }
        r12.q = r1;	 Catch:{ Exception -> 0x00ca }
        return r0;
    L_0x00ca:
        r2 = r12.q;
        r3 = r2 + 1;
        r12.q = r3;
        r3 = 5;
        if (r2 >= r3) goto L_0x00d4;
    L_0x00d3:
        return r0;
    L_0x00d4:
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.moat.analytics.mobile.inm.h.s():boolean");
    }

    public void setPlayerVolume(Double d) {
        super.setPlayerVolume(d);
        this.o = j().doubleValue();
    }

    public void stopTracking() {
        try {
            dispatchEvent(new MoatAdEvent(MoatAdEventType.AD_EVT_COMPLETE));
            super.stopTracking();
        } catch (Exception e) {
            m.a(e);
        }
    }
}
