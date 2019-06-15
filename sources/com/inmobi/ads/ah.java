package com.inmobi.ads;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application.ActivityLifecycleCallbacks;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.UiThread;
import android.view.View;
import android.view.ViewGroup;
import com.facebook.ads.AudienceNetworkActivity;
import com.facebook.internal.AnalyticsEvents;
import com.facebook.share.internal.ShareConstants;
import com.google.api.client.http.HttpStatusCodes;
import com.inmobi.ads.AdContainer.RenderingProperties;
import com.inmobi.ads.AdContainer.RenderingProperties.PlacementType;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.inmobi.commons.core.utilities.d;
import com.inmobi.rendering.InMobiAdActivity;
import com.inmobi.rendering.RenderView;
import com.integralads.avid.library.inmobi.session.AbstractAvidAdSession;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.json.JSONException;
import org.json.JSONObject;

public class ah implements ActivityLifecycleCallbacks, AdContainer {
    private static final String B = "ah";
    com.inmobi.ads.ai.a A = new com.inmobi.ads.ai.a() {
        public final void a(View view, boolean z) {
            ah.this.a(z);
        }
    };
    @NonNull
    private Set<Integer> C = new HashSet();
    @NonNull
    private List<ak> D = new ArrayList();
    private au E;
    private int F = -1;
    private o G;
    private ah H;
    private ak I = null;
    private String J = null;
    private ah K;
    private com.inmobi.rendering.RenderView.a L;
    private final com.inmobi.ads.AdContainer.a M = new com.inmobi.ads.AdContainer.a() {
        public final void a() {
            ah.B;
            c e = ah.this.e();
            if (e != null) {
                e.a();
            }
        }

        public final void a(Object obj) {
            if (ah.this.l() != null) {
                c e = ah.this.e();
                if (e != null) {
                    e.b();
                }
            }
        }

        public final void b(Object obj) {
            c e = ah.this.e();
            if (e != null) {
                e.f();
            }
        }
    };
    private ExecutorService N;
    private Runnable O = new Runnable() {
        public final void run() {
            if (!ah.this.l && PlacementType.PLACEMENT_TYPE_INLINE == ah.this.b.a && ah.this.a.c) {
                ah.B;
                ah.a(ah.this);
            }
        }
    };
    protected ao a;
    @NonNull
    public RenderingProperties b;
    @NonNull
    c c;
    @NonNull
    final String d;
    @NonNull
    final long e;
    final String f;
    @NonNull
    final boolean g;
    @NonNull
    final String h;
    @Nullable
    protected Set<bq> i;
    protected ca j;
    protected boolean k;
    public boolean l = false;
    protected boolean m;
    @NonNull
    ah n;
    @Nullable
    protected c o;
    @NonNull
    protected WeakReference<Context> p = new WeakReference(null);
    @Nullable
    WeakReference<Activity> q;
    boolean r = false;
    int s = 0;
    boolean t = true;
    boolean u = false;
    Intent v = null;
    RenderView w;
    RenderView x;
    int y;
    @Nullable
    public List<RenderView> z;

    final class a extends Thread {
        private WeakReference<ah> b;

        a(ah ahVar) {
            this.b = new WeakReference(ahVar);
        }

        public final void run() {
            if (ah.this.l() == null) {
                ah.B;
                return;
            }
            ah ahVar = (ah) this.b.get();
            if (ahVar != null && !ahVar.l) {
                try {
                    ao h = ahVar.h();
                    if (ah.this.l() != null) {
                        if (h.e.length() != 0) {
                            ah.B;
                            JSONObject a = h.a();
                            if (a != null) {
                                ao aoVar = new ao(ah.this.b.a, a, h, ah.this.b.a == PlacementType.PLACEMENT_TYPE_INLINE, ah.this.c, null);
                                if (aoVar.c()) {
                                    ah a2 = b.a(ah.this.l(), new RenderingProperties(PlacementType.PLACEMENT_TYPE_INLINE), aoVar, ah.this.d, ah.this.h, null, ah.this.c, ah.this.e, ah.this.g, ah.this.f);
                                    ah.B;
                                    a2.a((AdContainer) ahVar);
                                    a2.w = ahVar.w;
                                    ahVar.K = a2;
                                    return;
                                }
                                ah.B;
                                return;
                            }
                            return;
                        }
                    }
                    ah.B;
                } catch (Exception e) {
                    Exception exception = e;
                    ah.B;
                    new StringBuilder("Encountered unexpected error in EndCardBuilder: ").append(exception.getMessage());
                    com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(exception));
                }
            }
        }
    }

    static final class b {
        static ah a(@NonNull Context context, @NonNull RenderingProperties renderingProperties, @NonNull ao aoVar, @NonNull String str, @NonNull String str2, @Nullable Set<bq> set, @NonNull c cVar, long j, boolean z, String str3) {
            ao aoVar2 = aoVar;
            if (new ArrayList(aoVar2.h.keySet()).contains(ShareConstants.VIDEO_URL)) {
                return new bd(context, renderingProperties, aoVar2, str, str2, set, cVar, j, z, str3);
            }
            return new ah(context, renderingProperties, aoVar2, str, str2, set, cVar, j, z, str3);
        }
    }

    public interface c {
        void a();

        void a(String str, Map<String, Object> map);

        void a(Map<String, String> map);

        void a(boolean z);

        void b();

        void c();

        void d();

        void e();

        void f();

        void g();

        void h();

        void i();
    }

    public final void a() {
    }

    public String getMarkupType() {
        return "inmobiJson";
    }

    @Nullable
    public View getVideoContainerView() {
        return null;
    }

    /* Access modifiers changed, original: 0000 */
    public boolean n() {
        return false;
    }

    public void onActivityCreated(Activity activity, Bundle bundle) {
    }

    public void onActivityPaused(Activity activity) {
    }

    public void onActivityResumed(Activity activity) {
    }

    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    ah(@NonNull Context context, @NonNull RenderingProperties renderingProperties, @NonNull ao aoVar, @NonNull String str, @NonNull String str2, @Nullable Set<bq> set, @NonNull c cVar, long j, boolean z, String str3) {
        this.b = renderingProperties;
        this.a = aoVar;
        this.d = str;
        this.e = j;
        this.g = z;
        this.f = str3;
        this.h = str2;
        a((AdContainer) this);
        this.k = false;
        this.l = false;
        this.c = cVar;
        this.G = new o();
        if (set != null) {
            this.i = new HashSet(set);
        }
        this.a.d.z = System.currentTimeMillis();
        a(context);
        this.y = -1;
        this.N = Executors.newSingleThreadExecutor();
        this.N.submit(this.O);
    }

    public void setRequestedScreenOrientation() {
        Activity l = l();
        if (!(l == null || this.l)) {
            switch (this.a.a) {
                case 1:
                    l.setRequestedOrientation(1);
                    return;
                case 2:
                    l.setRequestedOrientation(0);
                    return;
                default:
                    l.setRequestedOrientation(l.getRequestedOrientation());
                    break;
            }
        }
    }

    public RenderingProperties getRenderingProperties() {
        return this.b;
    }

    @Nullable
    public com.inmobi.ads.AdContainer.a getFullScreenEventsListener() {
        return this.M;
    }

    /* Access modifiers changed, original: final */
    public final void a(Context context) {
        B();
        this.p = new WeakReference(context);
        if (context instanceof Activity) {
            ((Activity) context).getApplication().registerActivityLifecycleCallbacks(this);
        }
    }

    @Nullable
    public final Context d() {
        return (Context) this.p.get();
    }

    public final void a(@NonNull AdContainer adContainer) {
        if (adContainer instanceof ah) {
            this.n = (ah) adContainer;
        }
    }

    @Nullable
    public final c e() {
        return this.o;
    }

    public final void a(@NonNull c cVar) {
        this.o = cVar;
    }

    @Nullable
    public final View f() {
        return this.j == null ? null : this.j.b();
    }

    /* Access modifiers changed, original: final */
    public final void g() {
        Map a = a(this.a.d);
        a(1, a);
        a(2, a);
    }

    @SuppressLint({"SwitchIntDef"})
    public ca getViewableAd() {
        Context j = j();
        if (this.j == null && j != null) {
            g();
            this.j = new aa(j, this, new cc(this, this.w));
            if (this.i != null) {
                if (j instanceof Activity) {
                    try {
                        Activity activity = (Activity) j;
                        for (bq bqVar : this.i) {
                            int i = bqVar.a;
                            if (i != 1) {
                                if (i == 3) {
                                    if (this.y == 0) {
                                        AbstractAvidAdSession abstractAvidAdSession = (AbstractAvidAdSession) bqVar.b.get("avidAdSession");
                                        boolean z = bqVar.b.containsKey("deferred") && ((Boolean) bqVar.b.get("deferred")).booleanValue();
                                        if (abstractAvidAdSession != null) {
                                            this.j = new v(this, activity, this.j, abstractAvidAdSession, z);
                                        }
                                    }
                                }
                            } else if (this.y == 0) {
                                this.j = new ad(this, activity, this.j, bqVar.b);
                            } else {
                                bqVar.b.put("zMoatIID", UUID.randomUUID().toString());
                                this.j = new ae(activity, this.j, bqVar.b);
                            }
                        }
                    } catch (Exception e) {
                        new StringBuilder("Exception occurred while creating the Display viewable ad : ").append(e.getMessage());
                        com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
                    }
                } else {
                    HashMap hashMap = new HashMap();
                    hashMap.put("type", AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_NATIVE);
                    hashMap.put("impId", this.d);
                    com.inmobi.commons.core.e.b.a();
                    com.inmobi.commons.core.e.b.a("ads", "TrackersForService", hashMap);
                }
            }
        }
        return this.j;
    }

    @NonNull
    public final ao h() {
        return this.a;
    }

    public final boolean c() {
        return this.l;
    }

    public void destroy() {
        if (!this.l) {
            this.l = true;
            this.F = -1;
            if (this.H != null) {
                this.H.b();
            }
            this.l = true;
            this.o = null;
            au A = A();
            if (A != null) {
                n nVar = A.c;
                for (a aVar : nVar.a) {
                    aVar.a.cancel();
                }
                nVar.a.clear();
                A.b();
            }
            this.D.clear();
            if (this.j != null) {
                this.j.d();
                this.j.e();
            }
            B();
            this.p.clear();
            if (this.q != null) {
                this.q.clear();
            }
            if (this.z != null) {
                this.z.clear();
            }
            this.a = null;
            this.w = null;
            if (this.K != null) {
                this.K.destroy();
                this.K = null;
            }
        }
    }

    /* Access modifiers changed, original: 0000 */
    public boolean i() {
        return PlacementType.PLACEMENT_TYPE_INLINE == this.b.a && l() != null;
    }

    /* Access modifiers changed, original: protected|final */
    @Nullable
    public final Context j() {
        return (PlacementType.PLACEMENT_TYPE_FULLSCREEN == this.b.a || i()) ? l() : (Context) this.p.get();
    }

    /* Access modifiers changed, original: protected|final */
    public final boolean k() {
        return this.k;
    }

    private ak a(@NonNull ak akVar, @NonNull ao aoVar, @NonNull String str) {
        if (com.inmobi.commons.core.utilities.b.a((Context) this.p.get(), str)) {
            return akVar;
        }
        String[] split = str.split("\\|");
        ak b = aoVar.b(split[0]);
        if (b == null) {
            return b(aoVar.f, akVar);
        }
        if (b.equals(akVar)) {
            return null;
        }
        if (1 == split.length || 2 == split.length) {
            b.m = 1;
            return b;
        }
        if (split.length > 2) {
            b.m = ao.a(split[2]);
        }
        return b;
    }

    /* JADX WARNING: Removed duplicated region for block: B:36:0x007e  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x0084 A:{RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x0083 A:{RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x0082 A:{RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x0081 A:{RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x0080 A:{RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x007e  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x0084 A:{RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x0083 A:{RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x0082 A:{RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x0081 A:{RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x0080 A:{RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x007e  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x0084 A:{RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x0083 A:{RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x0082 A:{RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x0081 A:{RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x0080 A:{RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x007e  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x0084 A:{RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x0083 A:{RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x0082 A:{RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x0081 A:{RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x0080 A:{RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x007e  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x0084 A:{RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x0083 A:{RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x0082 A:{RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x0081 A:{RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x0080 A:{RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x007e  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x0084 A:{RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x0083 A:{RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x0082 A:{RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x0081 A:{RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x0080 A:{RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x007e  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x0084 A:{RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x0083 A:{RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x0082 A:{RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x0081 A:{RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x0080 A:{RETURN} */
    private static int a(java.lang.String r7) {
        /*
        r0 = java.util.Locale.US;
        r7 = r7.toLowerCase(r0);
        r7 = r7.trim();
        r0 = r7.hashCode();
        r1 = -934641255; // 0xffffffffc84a8199 float:-207366.39 double:NaN;
        r2 = 3;
        r3 = 4;
        r4 = 1;
        r5 = 5;
        r6 = 2;
        if (r0 == r1) goto L_0x0070;
    L_0x0018:
        r1 = -934524953; // 0xffffffffc84c47e7 float:-209183.61 double:NaN;
        if (r0 == r1) goto L_0x0066;
    L_0x001d:
        if (r0 == 0) goto L_0x005c;
    L_0x001f:
        r1 = 3127582; // 0x2fb91e float:4.382676E-39 double:1.545231E-317;
        if (r0 == r1) goto L_0x0052;
    L_0x0024:
        r1 = 3443508; // 0x348b34 float:4.825382E-39 double:1.701319E-317;
        if (r0 == r1) goto L_0x0048;
    L_0x0029:
        r1 = 3532159; // 0x35e57f float:4.949609E-39 double:1.7451184E-317;
        if (r0 == r1) goto L_0x003e;
    L_0x002e:
        r1 = 110066619; // 0x68f7bbb float:5.3972427E-35 double:5.4380135E-316;
        if (r0 == r1) goto L_0x0034;
    L_0x0033:
        goto L_0x007a;
    L_0x0034:
        r0 = "fullscreen";
        r7 = r7.equals(r0);
        if (r7 == 0) goto L_0x007a;
    L_0x003c:
        r7 = 6;
        goto L_0x007b;
    L_0x003e:
        r0 = "skip";
        r7 = r7.equals(r0);
        if (r7 == 0) goto L_0x007a;
    L_0x0046:
        r7 = r6;
        goto L_0x007b;
    L_0x0048:
        r0 = "play";
        r7 = r7.equals(r0);
        if (r7 == 0) goto L_0x007a;
    L_0x0050:
        r7 = 7;
        goto L_0x007b;
    L_0x0052:
        r0 = "exit";
        r7 = r7.equals(r0);
        if (r7 == 0) goto L_0x007a;
    L_0x005a:
        r7 = r5;
        goto L_0x007b;
    L_0x005c:
        r0 = "";
        r7 = r7.equals(r0);
        if (r7 == 0) goto L_0x007a;
    L_0x0064:
        r7 = r4;
        goto L_0x007b;
    L_0x0066:
        r0 = "replay";
        r7 = r7.equals(r0);
        if (r7 == 0) goto L_0x007a;
    L_0x006e:
        r7 = r3;
        goto L_0x007b;
    L_0x0070:
        r0 = "reload";
        r7 = r7.equals(r0);
        if (r7 == 0) goto L_0x007a;
    L_0x0078:
        r7 = r2;
        goto L_0x007b;
    L_0x007a:
        r7 = -1;
    L_0x007b:
        switch(r7) {
            case 2: goto L_0x0084;
            case 3: goto L_0x0083;
            case 4: goto L_0x0083;
            case 5: goto L_0x0082;
            case 6: goto L_0x0081;
            case 7: goto L_0x0080;
            default: goto L_0x007e;
        };
    L_0x007e:
        r7 = 0;
        return r7;
    L_0x0080:
        return r5;
    L_0x0081:
        return r3;
    L_0x0082:
        return r4;
    L_0x0083:
        return r2;
    L_0x0084:
        return r6;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.inmobi.ads.ah.a(java.lang.String):int");
    }

    /* Access modifiers changed, original: final */
    public final void a(@NonNull ak akVar, boolean z) {
        if (this.a.j && !this.l) {
            ak b = b(this.a, akVar);
            if (b != null) {
                Map a = a(b);
                b.i = akVar.i;
                if (ShareConstants.VIDEO_URL.equals(b.b) || b.h) {
                    if (this.j != null) {
                        this.j.a(4);
                    }
                    int i = b.i;
                    if (i != 0) {
                        String str = b.r;
                        if (!(this.t && 4 == i)) {
                            if (2 == b.m) {
                                bt f = ((be) b).b().f();
                                if (!(f == null || f.e == null || f.e.trim().isEmpty())) {
                                    str = f.e;
                                }
                            }
                            if (!com.inmobi.commons.core.utilities.b.a(C(), str)) {
                                StringBuilder stringBuilder = new StringBuilder("Invalid url:");
                                stringBuilder.append(str);
                                stringBuilder.append(" will use fallback");
                                a("DeeplinkFailed", str);
                                str = b.s;
                                if (!com.inmobi.commons.core.utilities.b.a(C(), str)) {
                                    a("DeeplinkFallbackFailed", str);
                                    return;
                                }
                            }
                            String a2 = d.a(str, a);
                            if (!this.u || z) {
                                a(b, i, a2);
                            } else {
                                ah f2 = f(this);
                                if (f2 != null) {
                                    c cVar = f2.o;
                                    if (cVar != null) {
                                        if (1 == i && com.inmobi.commons.core.utilities.b.a(a2)) {
                                            cVar.c();
                                        } else {
                                            cVar.g();
                                        }
                                    }
                                    this.I = b;
                                    this.J = a2;
                                    return;
                                }
                            }
                        }
                    }
                }
                return;
            }
            Map hashMap = new HashMap();
            hashMap.put("url", akVar.r);
            a("DeeplinkFailed", hashMap);
            hashMap = new HashMap();
            hashMap.put("url", akVar.s);
            a("DeeplinkFallbackFailed", hashMap);
        }
    }

    /* Access modifiers changed, original: final */
    public final void a(int i, ak akVar) {
        if (!this.C.contains(Integer.valueOf(i)) && !this.l) {
            w();
            a(i, (am) akVar);
        }
    }

    public void setFullScreenActivityContext(Activity activity) {
        this.q = new WeakReference(activity);
    }

    @Nullable
    public final Activity l() {
        return this.q == null ? null : (Activity) this.q.get();
    }

    private void w() {
        am a = this.a.a(0);
        if (!this.C.contains(Integer.valueOf(0)) && a != null) {
            a(0, a);
        }
    }

    private void a(@NonNull ak akVar, @Nullable Map<String, String> map) {
        a("ReportClick", new HashMap());
        if (2 == akVar.m) {
            bt f = ((be) akVar).b().f();
            if (f == null || (f.e == null && akVar.r != null)) {
                akVar.a(TrackerEventType.TRACKER_EVENT_TYPE_CLICK, (Map) map);
            } else if (f.d.size() > 0) {
                for (NativeTracker a : f.a(TrackerEventType.TRACKER_EVENT_TYPE_CLICK)) {
                    ak.a(a, (Map) map);
                }
                return;
            }
            return;
        }
        akVar.a(TrackerEventType.TRACKER_EVENT_TYPE_CLICK, (Map) map);
    }

    /* Access modifiers changed, original: final */
    public final Map<String, String> a(@NonNull ak akVar) {
        HashMap hashMap = new HashMap(3);
        if (this.l || this.a == null) {
            return hashMap;
        }
        hashMap.put("$LTS", String.valueOf(this.a.d.z));
        am a = ao.a(akVar);
        long currentTimeMillis = System.currentTimeMillis();
        if (!(a == null || 0 == a.z)) {
            currentTimeMillis = a.z;
        }
        hashMap.put("$STS", String.valueOf(currentTimeMillis));
        hashMap.put("$TS", String.valueOf(System.currentTimeMillis()));
        return hashMap;
    }

    private void b(@Nullable ak akVar, @Nullable Map<String, String> map) {
        if (akVar != null) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("id", akVar.g);
                jSONObject.put("asset", akVar.f);
            } catch (JSONException e) {
                com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
            }
            HashMap hashMap = new HashMap();
            hashMap.put("type", AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_NATIVE);
            hashMap.put("impId", this.d);
            hashMap.put("pageJson", jSONObject);
            com.inmobi.commons.core.e.b.a();
            com.inmobi.commons.core.e.b.a("ads", "PageRendered", hashMap);
            akVar.a(TrackerEventType.TRACKER_EVENT_TYPE_PAGE_VIEW, (Map) map);
        }
    }

    /* Access modifiers changed, original: final */
    public final void a(boolean z) {
        if (z) {
            x();
        } else {
            y();
        }
    }

    static NativeTimerView b(View view) {
        return view != null ? (NativeTimerView) view.findViewWithTag("timerView") : null;
    }

    protected static void c(View view) {
        NativeTimerView b = b(view);
        if (b != null && b.c != null && b.c.isRunning()) {
            b.b = b.c.getCurrentPlayTime();
            b.c.cancel();
        }
    }

    protected static void d(View view) {
        NativeTimerView b = b(view);
        if (b != null && b.c != null && !b.c.isRunning()) {
            b.c.setCurrentPlayTime(b.b);
            b.c.start();
        }
    }

    private void x() {
        au A = A();
        if (A != null) {
            n nVar = A.c;
            if (!nVar.b) {
                nVar.b = true;
                nVar.a(nVar.a);
            }
        }
    }

    private void y() {
        au A = A();
        if (A != null) {
            n nVar = A.c;
            if (nVar.b) {
                nVar.b = false;
                for (a aVar : nVar.a) {
                    ValueAnimator valueAnimator = (ValueAnimator) aVar.a;
                    aVar.b = valueAnimator.getCurrentPlayTime();
                    if (((double) valueAnimator.getAnimatedFraction()) == 1.0d) {
                        aVar.c = true;
                    }
                    valueAnimator.cancel();
                }
            }
        }
    }

    /* Access modifiers changed, original: final */
    public final void m() {
        ah f = f(this);
        if (f != null) {
            c cVar = f.o;
            if (cVar != null) {
                cVar.c();
            }
            this.N.submit(new Runnable() {
                public final void run() {
                    if (ah.this.H == null) {
                        ah.a(ah.this);
                    }
                    int a = InMobiAdActivity.a(ah.this.H);
                    Intent intent = new Intent((Context) ah.this.p.get(), InMobiAdActivity.class);
                    intent.putExtra("com.inmobi.rendering.InMobiAdActivity.EXTRA_AD_CONTAINER_INDEX", a);
                    intent.putExtra("com.inmobi.rendering.InMobiAdActivity.EXTRA_AD_ACTIVITY_TYPE", 102);
                    intent.putExtra("com.inmobi.rendering.InMobiAdActivity.EXTRA_AD_ACTIVITY_IS_FULL_SCREEN", true);
                    intent.putExtra("com.inmobi.rendering.InMobiAdActivity.EXTRA_AD_CONTAINER_TYPE", HttpStatusCodes.STATUS_CODE_CREATED);
                    if (ah.this.u) {
                        ah.this.v = intent;
                    } else {
                        com.inmobi.commons.a.a.a((Context) ah.this.p.get(), intent);
                    }
                }
            });
        }
    }

    /* Access modifiers changed, original: final */
    public final void o() {
        if (n()) {
            this.r = true;
            c cVar = this.o;
            if (cVar != null && this.a.g != null) {
                cVar.a(this.a.g);
            }
        }
    }

    private void a(be beVar, ah ahVar) {
        bt f = beVar.b().f();
        if (f != null && f.g) {
            for (NativeTracker a : f.a(TrackerEventType.TRACKER_EVENT_TYPE_END_CARD_CLOSE)) {
                ak.a(a, a((ak) beVar));
            }
            f.g = false;
            ahVar.a("EndCardClosed", ahVar.z());
        }
    }

    private Map<String, Object> z() {
        List c = this.K.a.c("WEBVIEW");
        bf bfVar = c.size() > 0 ? (bf) c.get(0) : null;
        Object obj = bfVar == null ? "Static" : "Rich";
        HashMap hashMap = new HashMap();
        hashMap.put("type", obj);
        hashMap.put("dataType", bfVar == null ? "URL" : bfVar.z);
        return hashMap;
    }

    /* Access modifiers changed, original: final */
    public final void a(String str, Map<String, Object> map) {
        ah f = f(this);
        StringBuilder stringBuilder;
        if (f != null) {
            c cVar = f.o;
            if (cVar != null) {
                cVar.a(str, map);
                return;
            }
            stringBuilder = new StringBuilder("InteractionCallback is null. Discarding telemetry event : [");
            stringBuilder.append(str);
            stringBuilder.append(" ]");
            return;
        }
        stringBuilder = new StringBuilder("Target container is null. Discarding telemetry event : [");
        stringBuilder.append(str);
        stringBuilder.append(" ]");
    }

    private static ah f(@Nullable ah ahVar) {
        while (ahVar != null) {
            if (ahVar.l() != null || ahVar == ahVar.n) {
                return ahVar;
            }
            ahVar = ahVar.n;
        }
        return null;
    }

    @Nullable
    private au A() {
        at atVar;
        if (this.j == null) {
            atVar = null;
        } else {
            atVar = (at) this.j.f();
        }
        if (atVar != null) {
            this.E = atVar.a;
        }
        return this.E;
    }

    public o getApkDownloader() {
        return this.G;
    }

    public final void a(@NonNull RenderView renderView) {
        if (this.z == null) {
            this.z = new LinkedList();
        }
        if (!this.z.contains(renderView)) {
            this.z.add(renderView);
        }
    }

    private void a(String str, String str2) {
        Map hashMap = new HashMap();
        hashMap.put("url", str2);
        a(str, hashMap);
    }

    private void a(@NonNull ak akVar, int i, String str) {
        if (1 != i) {
            a(str, akVar.s, akVar);
        } else if (com.inmobi.commons.core.utilities.b.a(str)) {
            Context context = (Context) this.p.get();
            if (context != null) {
                if (l() == null) {
                    c cVar = this.o;
                    if (cVar != null) {
                        cVar.c();
                    }
                }
                InMobiAdActivity.a(null);
                InMobiAdActivity.a(u());
                Intent intent = new Intent(context, InMobiAdActivity.class);
                intent.putExtra("com.inmobi.rendering.InMobiAdActivity.EXTRA_AD_ACTIVITY_TYPE", 100);
                intent.putExtra("com.inmobi.rendering.InMobiAdActivity.IN_APP_BROWSER_URL", str);
                intent.putExtra(AudienceNetworkActivity.PLACEMENT_ID, this.e);
                intent.putExtra("creativeId", this.f);
                intent.putExtra("impressionId", this.d);
                intent.putExtra("allowAutoRedirection", this.g);
                com.inmobi.commons.a.a.a(context, intent);
            }
        } else {
            a(str, null, akVar);
        }
    }

    private void a(@NonNull String str, @Nullable String str2, @NonNull ak akVar) {
        if (this.p.get() != null) {
            str = com.inmobi.commons.core.utilities.b.a((Context) this.p.get(), str, str2);
            if (str != null) {
                ah f = f(this);
                if (f != null) {
                    c cVar = f.o;
                    if (!(cVar == null || this.u)) {
                        cVar.g();
                    }
                    if (str.equals(str2)) {
                        akVar.a(TrackerEventType.TRACKER_EVENT_TYPE_FALLBACK_URL, a(akVar));
                    }
                }
            }
        }
    }

    private void B() {
        Context context = (Context) this.p.get();
        if (context instanceof Activity) {
            ((Activity) context).getApplication().unregisterActivityLifecycleCallbacks(this);
        }
    }

    public void onActivityStarted(Activity activity) {
        Context C = C();
        if (C != null && C.equals(activity)) {
            p();
        }
    }

    /* Access modifiers changed, original: final */
    public final void p() {
        this.m = false;
        d(f());
        x();
        if (this.j != null) {
            this.j.a(C(), 0);
        }
    }

    public void onActivityStopped(Activity activity) {
        Context C = C();
        if (C != null && C.equals(activity)) {
            q();
        }
    }

    private Context C() {
        Context l = l();
        return l == null ? (Context) this.p.get() : l;
    }

    /* Access modifiers changed, original: 0000 */
    public void q() {
        this.m = true;
        c(f());
        y();
        if (this.j != null) {
            this.j.a(C(), 1);
        }
    }

    public void onActivityDestroyed(Activity activity) {
        if (this.j != null) {
            this.j.a(activity, 2);
        }
    }

    /* Access modifiers changed, original: final */
    public final void r() {
        if (this.I == null || this.J == null) {
            if (!(this.v == null || this.p.get() == null)) {
                com.inmobi.commons.a.a.a((Context) this.p.get(), this.v);
            }
            return;
        }
        a(this.I, this.I.i, this.J);
    }

    /* Access modifiers changed, original: final */
    @Nullable
    public final RenderView s() {
        if (this.w == null) {
            return this.x;
        }
        return this.w;
    }

    /* Access modifiers changed, original: final */
    public final void t() {
        new a(this).start();
    }

    /* Access modifiers changed, original: final */
    @NonNull
    public final com.inmobi.rendering.RenderView.a u() {
        if (this.L == null) {
            this.L = new com.inmobi.rendering.RenderView.a() {
                public final void a(RenderView renderView) {
                }

                public final void a(HashMap<Object, Object> hashMap) {
                }

                public final void b(RenderView renderView) {
                }

                public final void w() {
                }

                public final void y() {
                }

                public final void A() {
                    c e = ah.this.e();
                    if (e != null) {
                        e.a();
                    }
                }

                public final void c(RenderView renderView) {
                    c e = ah.this.e();
                    if (e != null) {
                        e.b();
                    }
                }

                public final void d(RenderView renderView) {
                    c e = ah.this.e();
                    if (e != null) {
                        e.f();
                    }
                }

                public final void b(HashMap<Object, Object> hashMap) {
                    c e = ah.this.e();
                    if (e != null) {
                        e.e();
                    }
                }

                public final void B() {
                    c e = ah.this.e();
                    if (e != null) {
                        e.g();
                    }
                }

                public final void b(String str, Map<String, Object> map) {
                    ah.this.a(str, (Map) map);
                }

                public final void G() {
                    c e = ah.this.e();
                    if (e != null && PlacementType.PLACEMENT_TYPE_INLINE == ah.this.b.a) {
                        e.c();
                    }
                }
            };
        }
        return this.L;
    }

    public final void a(int i, Map<String, String> map) {
        if (!this.l) {
            switch (i) {
                case 1:
                    this.a.d.a(TrackerEventType.TRACKER_EVENT_TYPE_LOAD, (Map) map);
                    return;
                case 2:
                    this.a.d.a(TrackerEventType.TRACKER_EVENT_TYPE_CLIENT_FILL, (Map) map);
                    return;
                case 3:
                    return;
                default:
                    return;
            }
        }
    }

    private void a(int i, @NonNull am amVar) {
        if (!this.l) {
            this.C.add(Integer.valueOf(i));
            amVar.z = System.currentTimeMillis();
            if (this.k) {
                b((ak) amVar, a((ak) amVar));
            } else {
                this.D.add(amVar);
            }
        }
    }

    @Nullable
    private ak b(@Nullable ao aoVar, @NonNull ak akVar) {
        ak akVar2 = null;
        if (aoVar == null) {
            return null;
        }
        String str = akVar.r;
        String str2 = akVar.s;
        if (str != null) {
            akVar2 = a(akVar, aoVar, str);
        }
        if (akVar2 == null && str2 != null) {
            akVar2 = a(akVar, aoVar, str2);
        }
        if (akVar2 != null) {
            StringBuilder stringBuilder = new StringBuilder("Referenced asset (");
            stringBuilder.append(akVar2.d);
            stringBuilder.append(")");
        }
        return akVar2;
    }

    @Nullable
    protected static ak a(@Nullable ao aoVar, @NonNull ak akVar) {
        while (aoVar != null) {
            String str = akVar.j;
            if (str == null || str.length() == 0) {
                akVar.l = 0;
                return akVar;
            }
            String[] split = str.split("\\|");
            if (1 == split.length) {
                akVar.l = a(split[0]);
                return akVar;
            }
            ak b = aoVar.b(split[0]);
            if (b == null) {
                aoVar = aoVar.f;
            } else if (b.equals(akVar)) {
                return null;
            } else {
                b.l = a(split[1]);
                StringBuilder stringBuilder = new StringBuilder("Referenced asset (");
                stringBuilder.append(b.d);
                stringBuilder.append(")");
                return b;
            }
        }
        return null;
    }

    /* Access modifiers changed, original: final */
    public final void a(@Nullable View view, @NonNull ak akVar) {
        if (!this.l) {
            w();
            ak b = b(this.a, akVar);
            if (b != null) {
                Map a = a(b);
                a(b, a);
                if (!b.equals(akVar)) {
                    a(akVar, a);
                }
            } else {
                a(akVar, a(akVar));
            }
            ah f = f(this);
            if (f != null) {
                if (!akVar.r.trim().isEmpty()) {
                    c cVar = f.o;
                    if (cVar != null) {
                        cVar.e();
                    }
                }
                b = a(this.a, akVar);
                if (b != null) {
                    if (view != null && ShareConstants.VIDEO_URL.equals(b.b) && 5 == b.l) {
                        view.setVisibility(4);
                        akVar.x = 4;
                    }
                    b(b);
                }
            }
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void a(View view) {
        if (!this.k && !this.l) {
            this.k = true;
            this.a.d.a(TrackerEventType.TRACKER_EVENT_TYPE_RENDER, a(this.a.d));
            HashMap hashMap = new HashMap();
            hashMap.put("type", PlacementType.PLACEMENT_TYPE_FULLSCREEN == getRenderingProperties().a ? "int" : AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_NATIVE);
            hashMap.put("clientRequestId", this.h);
            hashMap.put("impId", this.d);
            com.inmobi.commons.core.e.b.a();
            com.inmobi.commons.core.e.b.a("ads", "AdRendered", hashMap);
            com.inmobi.commons.core.e.b.a();
            com.inmobi.commons.core.e.b.a("ads", "ViewableBeaconFired", hashMap);
            w();
            for (ak akVar : this.D) {
                b(akVar, a(akVar));
            }
            this.D.clear();
            ah f = f(this);
            if (f != null) {
                c cVar = f.o;
                if (cVar != null) {
                    cVar.d();
                }
            }
        }
    }

    /* Access modifiers changed, original: protected */
    @TargetApi(15)
    public void b(@NonNull ak akVar) {
        switch (akVar.l) {
            case 0:
                return;
            case 1:
                try {
                    if (this.w != null) {
                        this.w.d("window.imraid.broadcastEvent('close');");
                    }
                    b();
                    return;
                } catch (Exception e) {
                    new StringBuilder("Encountered unexpected error in handling exit action on video: ").append(e.getMessage());
                    Logger.a(InternalLogLevel.DEBUG, "InMobi", "SDK encountered unexpected error in exiting video");
                    com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
                    return;
                }
            case 3:
                try {
                    if (this.w != null) {
                        this.w.d("window.imraid.broadcastEvent('replay');");
                    }
                    if (f() != null) {
                        View f = f();
                        ViewGroup viewGroup = (ViewGroup) f.getParent();
                        if (viewGroup != null) {
                            viewGroup.removeView(f);
                        }
                    }
                    ah ahVar = this.n;
                    NativeTimerView b = b(ahVar.f());
                    if (!(b == null || b.c == null || !b.c.isRunning())) {
                        b.c.setCurrentPlayTime(b.a * 1000);
                        b.a(1.0f);
                    }
                    if (ShareConstants.VIDEO_URL.equals(akVar.b)) {
                        if (ahVar instanceof bd) {
                            NativeVideoWrapper nativeVideoWrapper = (NativeVideoWrapper) ahVar.getVideoContainerView();
                            if (nativeVideoWrapper != null) {
                                NativeVideoView videoView = nativeVideoWrapper.getVideoView();
                                be beVar = (be) videoView.getTag();
                                if (beVar != null) {
                                    if (beVar.a()) {
                                        videoView.e();
                                    } else {
                                        videoView.d();
                                    }
                                } else if (PlacementType.PLACEMENT_TYPE_FULLSCREEN == this.b.a) {
                                    videoView.e();
                                } else {
                                    videoView.d();
                                }
                                a(beVar, ahVar);
                                videoView.start();
                            }
                        }
                        return;
                    }
                    new StringBuilder("Action 3 not valid for asset of type: ").append(akVar.b);
                    return;
                } catch (Exception e2) {
                    new StringBuilder("Encountered unexpected error in handling replay action on video: ").append(e2.getMessage());
                    Logger.a(InternalLogLevel.DEBUG, "InMobi", "SDK encountered unexpected error in replaying video");
                    com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e2));
                    return;
                }
            case 4:
                try {
                    if (PlacementType.PLACEMENT_TYPE_INLINE == this.b.a) {
                        m();
                    }
                    return;
                } catch (Exception e22) {
                    new StringBuilder("Encountered unexpected error in handling fullscreen action ").append(e22.getMessage());
                    Logger.a(InternalLogLevel.DEBUG, "InMobi", "SDK encountered unexpected error in launching fullscreen ad");
                    com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e22));
                    return;
                }
            case 5:
                return;
            default:
                this.r = true;
                if (!(this.w == null || this.w == null)) {
                    this.w.d("window.imraid.broadcastEvent('skip');");
                }
                c(f());
                c(akVar);
                return;
        }
    }

    public final void b() {
        try {
            if (!this.l) {
                Object f = f(this);
                if (f != null) {
                    Activity activity;
                    f.o();
                    InMobiAdActivity.a(f);
                    if (f instanceof bd) {
                        ah ahVar = (bd) f;
                        NativeVideoWrapper nativeVideoWrapper = (NativeVideoWrapper) ahVar.getVideoContainerView();
                        if (nativeVideoWrapper != null) {
                            NativeVideoView videoView = nativeVideoWrapper.getVideoView();
                            be beVar = (be) videoView.getTag();
                            beVar.v.put("seekPosition", Integer.valueOf(videoView.getCurrentPosition()));
                            beVar.v.put("lastMediaVolume", Integer.valueOf(videoView.getVolume()));
                            if (beVar.y != null) {
                                ((be) beVar.y).a(beVar);
                            }
                            a(beVar, ahVar);
                        }
                    }
                    if (f.q == null) {
                        activity = null;
                    } else {
                        activity = (Activity) f.q.get();
                    }
                    if (activity != null && (activity instanceof InMobiAdActivity)) {
                        ((InMobiAdActivity) activity).a = true;
                        activity.finish();
                        if (this.F != -1) {
                            activity.overridePendingTransition(0, this.F);
                        }
                    }
                    this.n.H = null;
                    this.n.N.submit(this.O);
                }
            }
        } catch (Exception e) {
            new StringBuilder("Encountered unexpected error in handling exit action on video: ").append(e.getMessage());
            Logger.a(InternalLogLevel.DEBUG, "InMobi", "SDK encountered unexpected error in exiting video");
            com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
        }
    }

    @UiThread
    public final void c(@Nullable ak akVar) {
        ah ahVar = this.K;
        if (ahVar == null || f() == null) {
            Logger.a(InternalLogLevel.DEBUG, "InMobi", "Failed to show end card");
            b();
        } else {
            try {
                a("EndCardRequested", z());
                ViewGroup viewGroup = (ViewGroup) f();
                View a = ahVar.getViewableAd().a(null, viewGroup, false);
                if (a != null) {
                    viewGroup.addView(a);
                    a.setClickable(true);
                    ahVar.x();
                    a("EndCardDisplayed", z());
                    if (akVar instanceof be) {
                        bt f = ((be) akVar).b().f();
                        if (f != null) {
                            f.g = true;
                        }
                        return;
                    }
                }
                b();
            } catch (Exception e) {
                b();
                com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
            }
        }
    }

    @NonNull
    public /* bridge */ /* synthetic */ Object getDataModel() {
        return this.a;
    }
}
