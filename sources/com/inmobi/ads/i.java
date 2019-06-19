package com.inmobi.ads;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.UiThread;
import android.support.annotation.VisibleForTesting;
import android.support.v7.widget.RecyclerView;
import com.facebook.ads.AudienceNetworkActivity;
import com.facebook.share.internal.ShareConstants;
import com.google.android.gms.cast.HlsSegmentFormat;
import com.inmobi.a.o;
import com.inmobi.ads.AdContainer.RenderingProperties;
import com.inmobi.ads.AdContainer.RenderingProperties.PlacementType;
import com.inmobi.ads.InMobiAdRequest.MonetizationContext;
import com.inmobi.ads.InMobiAdRequestStatus.StatusCode;
import com.inmobi.commons.core.configs.g;
import com.inmobi.commons.core.configs.h;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.inmobi.rendering.RenderView;
import com.integralads.avid.library.inmobi.session.AbstractAvidAdSession;
import com.squareup.picasso.Picasso;
import com.til.colombia.android.internal.e;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@UiThread
public abstract class i implements com.inmobi.ads.bl.a, com.inmobi.ads.h.a, com.inmobi.commons.core.configs.b.c, com.inmobi.rendering.RenderView.a, com.inmobi.rendering.a {
    private static final String y = "i";
    private boolean A;
    @Nullable
    private h B;
    private long C;
    private long D;
    private WeakReference<b> E;
    private RenderView F;
    private bn G;
    private long H;
    private long I = 0;
    @NonNull
    private a J;
    private Runnable K;
    private Set<bq> L;
    private MonetizationContext M;
    private bl N;
    private boolean O;
    private com.inmobi.ads.d.a P;
    private boolean Q;
    @Nullable
    private com.inmobi.ads.b.a R;
    private com.inmobi.rendering.RenderView.a S = new com.inmobi.rendering.RenderView.a() {
        public final void A() {
        }

        public final void B() {
        }

        public final void G() {
        }

        public final void a(HashMap<Object, Object> hashMap) {
        }

        public final void b(RenderView renderView) {
        }

        public final void b(String str, Map<String, Object> map) {
        }

        public final void b(HashMap<Object, Object> hashMap) {
        }

        public final void c(RenderView renderView) {
        }

        public final void d(RenderView renderView) {
        }

        public final void w() {
            i.this.s.post(new Runnable() {
                public final void run() {
                    if (2 == i.this.a) {
                        i.this.O = true;
                        i.this.J();
                    }
                }
            });
        }

        public final void y() {
            i.this.s.post(new Runnable() {
                public final void run() {
                    if (i.this.k != null) {
                        i.this.i().a(i.this.k);
                    }
                    i.this.a(new InMobiAdRequestStatus(StatusCode.INTERNAL_ERROR), false);
                }
            });
        }

        public final void a(RenderView renderView) {
            i.this.s.post(new Runnable() {
                public final void run() {
                    if (2 == i.this.a) {
                        i.this.L();
                    }
                }
            });
        }
    };
    int a;
    final JSONObject b = new JSONObject();
    final boolean c = false;
    public long d;
    public String e;
    public Map<String, String> f;
    c g;
    String h;
    JSONObject i;
    bx j;
    String k;
    public String l;
    String m;
    public boolean n = false;
    ah o;
    ExecutorService p;
    public d q;
    int r;
    Handler s;
    boolean t;
    RenderView u;
    boolean v;
    boolean w = false;
    String x;
    private WeakReference<Context> z;

    public interface d {
        void a(@NonNull i iVar);

        void a(@NonNull i iVar, @NonNull InMobiAdRequestStatus inMobiAdRequestStatus);
    }

    public static abstract class b {
        public void a() {
        }

        public void a(InMobiAdRequestStatus inMobiAdRequestStatus) {
        }

        /* Access modifiers changed, original: 0000 */
        public void a(i iVar) {
        }

        /* Access modifiers changed, original: 0000 */
        public void a(@NonNull Map<Object, Object> map) {
        }

        public void a(boolean z) {
        }

        /* Access modifiers changed, original: 0000 */
        public void a(byte[] bArr) {
        }

        /* Access modifiers changed, original: 0000 */
        public void b() {
        }

        /* Access modifiers changed, original: 0000 */
        public void b(InMobiAdRequestStatus inMobiAdRequestStatus) {
        }

        /* Access modifiers changed, original: 0000 */
        public void b(@NonNull Map<Object, Object> map) {
        }

        /* Access modifiers changed, original: 0000 */
        public void b(boolean z) {
        }

        /* Access modifiers changed, original: 0000 */
        public void c() {
        }

        /* Access modifiers changed, original: 0000 */
        public void d() {
        }

        /* Access modifiers changed, original: 0000 */
        public void e() {
        }

        /* Access modifiers changed, original: 0000 */
        public void f() {
        }

        /* Access modifiers changed, original: 0000 */
        public void g() {
        }

        /* Access modifiers changed, original: 0000 */
        public void h() {
        }

        public boolean i() {
            return true;
        }

        /* Access modifiers changed, original: 0000 */
        public void j() {
        }
    }

    static final class a extends Handler {
        private WeakReference<i> a;

        a(i iVar) {
            super(Looper.getMainLooper());
            this.a = new WeakReference(iVar);
        }

        public final void handleMessage(Message message) {
            i iVar = this.a == null ? null : (i) this.a.get();
            if (iVar != null) {
                Bundle data = message.getData();
                long j = data.getLong(AudienceNetworkActivity.PLACEMENT_ID);
                int i = message.what;
                switch (i) {
                    case 1:
                        iVar.a(j, data.getBoolean("adAvailable"), (a) message.obj);
                        return;
                    case 2:
                        iVar.c(j, (a) message.obj);
                        return;
                    case 3:
                        return;
                    case 4:
                        iVar.b(j, data.getBoolean("assetAvailable"));
                        return;
                    default:
                        switch (i) {
                            case 11:
                                iVar.x();
                                return;
                            case 12:
                                iVar.z();
                                return;
                            case 13:
                                iVar.b((InMobiAdRequestStatus) message.obj);
                                return;
                            case 14:
                                iVar.H();
                                return;
                            default:
                                return;
                        }
                }
            }
        }
    }

    static class c {
        @NonNull
        static HashMap<String, String> a(@NonNull String str, @NonNull String str2, JSONArray jSONArray, JSONArray jSONArray2, JSONObject jSONObject) {
            int length;
            HashMap hashMap = new HashMap();
            int i = 0;
            if (jSONArray != null) {
                try {
                    length = jSONArray.length();
                    int i2 = 0;
                    while (i2 < length) {
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append(str);
                        int i3 = i2 + 1;
                        stringBuilder.append(i3);
                        hashMap.put(stringBuilder.toString(), jSONArray.getString(i2));
                        i2 = i3;
                    }
                } catch (Exception e) {
                    i.y;
                    new StringBuilder("Exception while parsing map details for Moat : ").append(e.getMessage());
                    com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
                }
            }
            if (jSONArray2 != null) {
                int length2 = jSONArray2.length();
                while (i < length2) {
                    StringBuilder stringBuilder2 = new StringBuilder();
                    stringBuilder2.append(str2);
                    length = i + 1;
                    stringBuilder2.append(length);
                    hashMap.put(stringBuilder2.toString(), jSONArray2.getString(i));
                    i = length;
                }
            }
            if (jSONObject != null) {
                Iterator keys = jSONObject.keys();
                while (keys.hasNext()) {
                    str2 = (String) keys.next();
                    hashMap.put(str2, jSONObject.optString(str2));
                }
            }
            return hashMap;
        }

        @Nullable
        static Map<String, Object> a(@NonNull JSONArray jSONArray) {
            try {
                JSONObject jSONObject;
                int length = jSONArray.length();
                for (int i = 0; i < length; i++) {
                    JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                    if (jSONObject2.has("moat")) {
                        jSONObject = jSONObject2.getJSONObject("moat");
                        break;
                    }
                }
                jSONObject = null;
                if (jSONObject == null) {
                    return null;
                }
                HashMap hashMap = new HashMap();
                hashMap.put("enabled", Boolean.valueOf(jSONObject.getBoolean("enabled")));
                hashMap.put("instrumentVideo", Boolean.valueOf(jSONObject.optBoolean("instrumentVideo", false)));
                hashMap.put("partnerCode", jSONObject.optString("partnerCode", null));
                hashMap.put("clientLevels", jSONObject.optJSONArray("clientLevels"));
                hashMap.put("clientSlicers", jSONObject.optJSONArray("clientSlicers"));
                hashMap.put("zMoatExtras", jSONObject.optJSONObject("zMoatExtras"));
                return hashMap;
            } catch (JSONException e) {
                i.y;
                new StringBuilder("Exception while parsing MoatParams from response : ").append(e.getMessage());
                com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
                return null;
            }
        }
    }

    public final void G() {
    }

    /* Access modifiers changed, original: 0000 */
    public void K() {
    }

    /* Access modifiers changed, original: 0000 */
    public void L() {
    }

    public abstract String b();

    public abstract void b(a aVar);

    /* Access modifiers changed, original: 0000 */
    public void b(b bVar) {
    }

    public abstract String c();

    /* Access modifiers changed, original: 0000 */
    public void c(b bVar) {
    }

    public abstract PlacementType d();

    /* Access modifiers changed, original: protected */
    public void x() {
    }

    public final void a(com.inmobi.commons.core.configs.a aVar) {
        this.g = (c) aVar;
        i().d = this.g.a(b());
        if (this.N != null) {
            this.N.b = this.g.a(b());
        }
        com.inmobi.commons.core.e.b.a().a("ads", this.g.l);
    }

    public i(Context context, long j, b bVar) {
        this.z = new WeakReference(context);
        this.d = j;
        this.E = new WeakReference(bVar);
        this.P = new com.inmobi.ads.d.b(com.inmobi.b.a.a());
        this.m = "unknown";
        this.g = new c();
        com.inmobi.commons.core.configs.b.a().a(new g(), null);
        com.inmobi.commons.core.configs.b.a().a(this.g, (com.inmobi.commons.core.configs.b.c) this);
        this.p = Executors.newSingleThreadExecutor();
        this.p.submit(new Runnable() {
            public final void run() {
                i.this.B = new h(i.this, i.this.g.a(i.this.b()), i.this.u());
            }
        });
        this.J = new a(this);
        this.G = new bn(this);
        this.L = new HashSet();
        this.r = -1;
        this.K = new Runnable() {
            public final void run() {
                int r = i.this.r();
                switch (r) {
                    case -2:
                    case -1:
                    case 0:
                    case 1:
                    case 2:
                        break;
                    default:
                        StringBuilder stringBuilder = new StringBuilder("Unknown return value (");
                        stringBuilder.append(r);
                        stringBuilder.append(") from #doAdLoadWork()");
                        break;
                }
                i.y;
            }
        };
        com.inmobi.commons.core.e.b.a().a("ads", this.g.l);
        this.s = new Handler(Looper.getMainLooper());
        this.t = false;
        this.x = "";
        this.i = this.b;
        this.A = false;
        this.a = 0;
    }

    @Nullable
    public final Context a() {
        return this.z == null ? null : (Context) this.z.get();
    }

    public void a(Context context) {
        this.z = new WeakReference(context);
    }

    /* Access modifiers changed, original: protected */
    @NonNull
    public Map<String, String> e() {
        HashMap hashMap = new HashMap();
        hashMap.put("preload-request", this.n ? "1" : "0");
        return hashMap;
    }

    /* Access modifiers changed, original: final */
    @Nullable
    public final b f() {
        b bVar = (b) this.E.get();
        if (bVar == null) {
            g();
        }
        return bVar;
    }

    /* Access modifiers changed, original: final */
    public final void g() {
        Logger.a(InternalLogLevel.DEBUG, "InMobi", "Listener was garbage collected. Unable to give callback");
        d("ListenerNotFound");
    }

    /* Access modifiers changed, original: final */
    public final void a(b bVar) {
        this.E = new WeakReference(bVar);
    }

    public final boolean h() {
        if (1 == this.a) {
            return false;
        }
        return this.D == -1 ? this.C != 0 && System.currentTimeMillis() - this.C > TimeUnit.SECONDS.toMillis(this.g.a(b()).d) : this.C != 0 && System.currentTimeMillis() > this.D;
    }

    @NonNull
    public final h i() {
        if (this.B == null) {
            this.B = new h(this, this.g.a(b()), u());
        }
        return this.B;
    }

    /* Access modifiers changed, original: protected */
    @NonNull
    public RenderView k() {
        if ((this.F == null || this.F.s.get()) && a() != null) {
            this.F = new RenderView(a(), new RenderingProperties(d()), this.L, this.k);
            this.F.a((com.inmobi.rendering.RenderView.a) this, this.g);
            this.F.setPlacementId(this.d);
            this.F.setCreativeId(this.x);
            this.F.setAllowAutoRedirection(this.A);
        }
        return this.F;
    }

    /* JADX WARNING: Removed duplicated region for block: B:75:0x0181 A:{Catch:{ JSONException -> 0x01c4, IllegalArgumentException -> 0x01c2 }} */
    /* JADX WARNING: Removed duplicated region for block: B:77:0x0187 A:{Catch:{ JSONException -> 0x01c4, IllegalArgumentException -> 0x01c2 }} */
    /* JADX WARNING: Removed duplicated region for block: B:76:0x0184 A:{Catch:{ JSONException -> 0x01c4, IllegalArgumentException -> 0x01c2 }} */
    /* JADX WARNING: Removed duplicated region for block: B:80:0x0194 A:{Catch:{ JSONException -> 0x01c4, IllegalArgumentException -> 0x01c2 }} */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x0181 A:{Catch:{ JSONException -> 0x01c4, IllegalArgumentException -> 0x01c2 }} */
    /* JADX WARNING: Removed duplicated region for block: B:77:0x0187 A:{Catch:{ JSONException -> 0x01c4, IllegalArgumentException -> 0x01c2 }} */
    /* JADX WARNING: Removed duplicated region for block: B:76:0x0184 A:{Catch:{ JSONException -> 0x01c4, IllegalArgumentException -> 0x01c2 }} */
    /* JADX WARNING: Removed duplicated region for block: B:80:0x0194 A:{Catch:{ JSONException -> 0x01c4, IllegalArgumentException -> 0x01c2 }} */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0053 A:{Catch:{ JSONException -> 0x01d7, IllegalArgumentException -> 0x01c6 }} */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0059 A:{Catch:{ JSONException -> 0x01d7, IllegalArgumentException -> 0x01c6 }} */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0056 A:{Catch:{ JSONException -> 0x01d7, IllegalArgumentException -> 0x01c6 }} */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0079 A:{Catch:{ JSONException -> 0x01d7, IllegalArgumentException -> 0x01c6 }} */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0078 A:{RETURN, Catch:{ JSONException -> 0x01d7, IllegalArgumentException -> 0x01c6 }} */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0053 A:{Catch:{ JSONException -> 0x01d7, IllegalArgumentException -> 0x01c6 }} */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0059 A:{Catch:{ JSONException -> 0x01d7, IllegalArgumentException -> 0x01c6 }} */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0056 A:{Catch:{ JSONException -> 0x01d7, IllegalArgumentException -> 0x01c6 }} */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0078 A:{RETURN, Catch:{ JSONException -> 0x01d7, IllegalArgumentException -> 0x01c6 }} */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0079 A:{Catch:{ JSONException -> 0x01d7, IllegalArgumentException -> 0x01c6 }} */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0079 A:{Catch:{ JSONException -> 0x01d7, IllegalArgumentException -> 0x01c6 }} */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0078 A:{RETURN, Catch:{ JSONException -> 0x01d7, IllegalArgumentException -> 0x01c6 }} */
    public boolean a(com.inmobi.ads.a r14) {
        /*
        r13 = this;
        r0 = 0;
        r1 = new org.json.JSONObject;	 Catch:{ JSONException -> 0x01d7, IllegalArgumentException -> 0x01c6 }
        r2 = r14.c;	 Catch:{ JSONException -> 0x01d7, IllegalArgumentException -> 0x01c6 }
        r1.<init>(r2);	 Catch:{ JSONException -> 0x01d7, IllegalArgumentException -> 0x01c6 }
        r2 = r14.e;	 Catch:{ JSONException -> 0x01d7, IllegalArgumentException -> 0x01c6 }
        r13.C = r2;	 Catch:{ JSONException -> 0x01d7, IllegalArgumentException -> 0x01c6 }
        r2 = r14.c();	 Catch:{ JSONException -> 0x01d7, IllegalArgumentException -> 0x01c6 }
        r13.D = r2;	 Catch:{ JSONException -> 0x01d7, IllegalArgumentException -> 0x01c6 }
        r2 = r14.g;	 Catch:{ JSONException -> 0x01d7, IllegalArgumentException -> 0x01c6 }
        r13.k = r2;	 Catch:{ JSONException -> 0x01d7, IllegalArgumentException -> 0x01c6 }
        r2 = r14.h;	 Catch:{ JSONException -> 0x01d7, IllegalArgumentException -> 0x01c6 }
        r13.l = r2;	 Catch:{ JSONException -> 0x01d7, IllegalArgumentException -> 0x01c6 }
        r2 = "markupType";
        r2 = r1.optString(r2);	 Catch:{ JSONException -> 0x01d7, IllegalArgumentException -> 0x01c6 }
        r3 = 2;
        r4 = -1;
        r5 = 1;
        if (r2 == 0) goto L_0x005c;
    L_0x0025:
        r6 = r2.length();	 Catch:{ JSONException -> 0x01d7, IllegalArgumentException -> 0x01c6 }
        if (r6 != 0) goto L_0x002c;
    L_0x002b:
        goto L_0x005c;
    L_0x002c:
        r6 = r2.hashCode();	 Catch:{ JSONException -> 0x01d7, IllegalArgumentException -> 0x01c6 }
        r7 = -1084172778; // 0xffffffffbf60d616 float:-0.8782667 double:NaN;
        if (r6 == r7) goto L_0x0045;
    L_0x0035:
        r7 = 3213227; // 0x3107ab float:4.50269E-39 double:1.587545E-317;
        if (r6 == r7) goto L_0x003b;
    L_0x003a:
        goto L_0x004f;
    L_0x003b:
        r6 = "html";
        r2 = r2.equals(r6);	 Catch:{ JSONException -> 0x01d7, IllegalArgumentException -> 0x01c6 }
        if (r2 == 0) goto L_0x004f;
    L_0x0043:
        r2 = r5;
        goto L_0x0050;
    L_0x0045:
        r6 = "inmobiJson";
        r2 = r2.equals(r6);	 Catch:{ JSONException -> 0x01d7, IllegalArgumentException -> 0x01c6 }
        if (r2 == 0) goto L_0x004f;
    L_0x004d:
        r2 = r3;
        goto L_0x0050;
    L_0x004f:
        r2 = r4;
    L_0x0050:
        switch(r2) {
            case 1: goto L_0x0059;
            case 2: goto L_0x0056;
            default: goto L_0x0053;
        };	 Catch:{ JSONException -> 0x01d7, IllegalArgumentException -> 0x01c6 }
    L_0x0053:
        r2 = "unknown";
        goto L_0x005e;
    L_0x0056:
        r2 = "inmobiJson";
        goto L_0x005e;
    L_0x0059:
        r2 = "html";
        goto L_0x005e;
    L_0x005c:
        r2 = "html";
    L_0x005e:
        r13.m = r2;	 Catch:{ JSONException -> 0x01d7, IllegalArgumentException -> 0x01c6 }
        r2 = "allowAutoRedirection";
        r2 = r1.optBoolean(r2, r0);	 Catch:{ JSONException -> 0x01d7, IllegalArgumentException -> 0x01c6 }
        r13.A = r2;	 Catch:{ JSONException -> 0x01d7, IllegalArgumentException -> 0x01c6 }
        r14 = r14.b();	 Catch:{ JSONException -> 0x01d7, IllegalArgumentException -> 0x01c6 }
        r13.i = r14;	 Catch:{ JSONException -> 0x01d7, IllegalArgumentException -> 0x01c6 }
        r14 = "unknown";
        r2 = r13.m;	 Catch:{ JSONException -> 0x01d7, IllegalArgumentException -> 0x01c6 }
        r14 = r14.equals(r2);	 Catch:{ JSONException -> 0x01d7, IllegalArgumentException -> 0x01c6 }
        if (r14 == 0) goto L_0x0079;
    L_0x0078:
        return r0;
    L_0x0079:
        r14 = "inmobiJson";
        r2 = r13.m;	 Catch:{ JSONException -> 0x01d7, IllegalArgumentException -> 0x01c6 }
        r14 = r14.equals(r2);	 Catch:{ JSONException -> 0x01d7, IllegalArgumentException -> 0x01c6 }
        if (r14 == 0) goto L_0x0090;
    L_0x0083:
        r14 = "pubContent";
        r14 = r1.getJSONObject(r14);	 Catch:{ JSONException -> 0x01d7, IllegalArgumentException -> 0x01c6 }
        r14 = r14.toString();	 Catch:{ JSONException -> 0x01d7, IllegalArgumentException -> 0x01c6 }
        r13.h = r14;	 Catch:{ JSONException -> 0x01d7, IllegalArgumentException -> 0x01c6 }
        goto L_0x009c;
    L_0x0090:
        r14 = "pubContent";
        r14 = r1.getString(r14);	 Catch:{ JSONException -> 0x01d7, IllegalArgumentException -> 0x01c6 }
        r14 = r14.trim();	 Catch:{ JSONException -> 0x01d7, IllegalArgumentException -> 0x01c6 }
        r13.h = r14;	 Catch:{ JSONException -> 0x01d7, IllegalArgumentException -> 0x01c6 }
    L_0x009c:
        r14 = r13.h;	 Catch:{ JSONException -> 0x01d7, IllegalArgumentException -> 0x01c6 }
        if (r14 == 0) goto L_0x00ca;
    L_0x00a0:
        r14 = r13.h;	 Catch:{ JSONException -> 0x01d7, IllegalArgumentException -> 0x01c6 }
        r14 = r14.length();	 Catch:{ JSONException -> 0x01d7, IllegalArgumentException -> 0x01c6 }
        if (r14 == 0) goto L_0x00ca;
    L_0x00a8:
        r14 = "unknown";
        r2 = r13.m;	 Catch:{ JSONException -> 0x01d7, IllegalArgumentException -> 0x01c6 }
        r14 = r14.equals(r2);	 Catch:{ JSONException -> 0x01d7, IllegalArgumentException -> 0x01c6 }
        if (r14 != 0) goto L_0x00ca;
    L_0x00b2:
        r14 = r13.h;	 Catch:{ JSONException -> 0x01d7, IllegalArgumentException -> 0x01c6 }
        r2 = "@__imm_aft@";
        r6 = java.lang.System.currentTimeMillis();	 Catch:{ JSONException -> 0x01d7, IllegalArgumentException -> 0x01c6 }
        r8 = r13.H;	 Catch:{ JSONException -> 0x01d7, IllegalArgumentException -> 0x01c6 }
        r10 = r6 - r8;
        r6 = java.lang.String.valueOf(r10);	 Catch:{ JSONException -> 0x01d7, IllegalArgumentException -> 0x01c6 }
        r14 = r14.replace(r2, r6);	 Catch:{ JSONException -> 0x01d7, IllegalArgumentException -> 0x01c6 }
        r13.h = r14;	 Catch:{ JSONException -> 0x01d7, IllegalArgumentException -> 0x01c6 }
        r14 = r5;
        goto L_0x00cb;
    L_0x00ca:
        r14 = r0;
    L_0x00cb:
        r2 = "creativeId";
        r6 = "";
        r2 = r1.optString(r2, r6);	 Catch:{ JSONException -> 0x01c4, IllegalArgumentException -> 0x01c2 }
        r13.x = r2;	 Catch:{ JSONException -> 0x01c4, IllegalArgumentException -> 0x01c2 }
        r2 = r13.L;	 Catch:{ JSONException -> 0x01c4, IllegalArgumentException -> 0x01c2 }
        r2 = r2.isEmpty();	 Catch:{ JSONException -> 0x01c4, IllegalArgumentException -> 0x01c2 }
        if (r2 == 0) goto L_0x01e7;
    L_0x00dd:
        r2 = "viewability";
        r2 = r1.has(r2);	 Catch:{ JSONException -> 0x01c4, IllegalArgumentException -> 0x01c2 }
        if (r2 == 0) goto L_0x0122;
    L_0x00e5:
        r2 = new com.inmobi.ads.bq;	 Catch:{ JSONException -> 0x01c4, IllegalArgumentException -> 0x01c2 }
        r2.<init>(r5);	 Catch:{ JSONException -> 0x01c4, IllegalArgumentException -> 0x01c2 }
        r6 = "viewability";
        r6 = r1.getJSONArray(r6);	 Catch:{ JSONException -> 0x01c4, IllegalArgumentException -> 0x01c2 }
        r6 = com.inmobi.ads.i.c.a(r6);	 Catch:{ JSONException -> 0x01c4, IllegalArgumentException -> 0x01c2 }
        r2.b = r6;	 Catch:{ JSONException -> 0x01c4, IllegalArgumentException -> 0x01c2 }
        r6 = r13.L;	 Catch:{ JSONException -> 0x01c4, IllegalArgumentException -> 0x01c2 }
        r6.add(r2);	 Catch:{ JSONException -> 0x01c4, IllegalArgumentException -> 0x01c2 }
        r6 = r13.a();	 Catch:{ JSONException -> 0x01c4, IllegalArgumentException -> 0x01c2 }
        if (r6 == 0) goto L_0x010e;
    L_0x0101:
        r7 = r6 instanceof android.app.Activity;	 Catch:{ JSONException -> 0x01c4, IllegalArgumentException -> 0x01c2 }
        if (r7 == 0) goto L_0x010e;
    L_0x0105:
        r6 = (android.app.Activity) r6;	 Catch:{ JSONException -> 0x01c4, IllegalArgumentException -> 0x01c2 }
        r6 = r6.getApplication();	 Catch:{ JSONException -> 0x01c4, IllegalArgumentException -> 0x01c2 }
        com.inmobi.ads.z.a(r6);	 Catch:{ JSONException -> 0x01c4, IllegalArgumentException -> 0x01c2 }
    L_0x010e:
        r6 = r2.b;	 Catch:{ JSONException -> 0x01c4, IllegalArgumentException -> 0x01c2 }
        if (r6 == 0) goto L_0x0122;
    L_0x0112:
        r6 = new java.lang.StringBuilder;	 Catch:{ JSONException -> 0x01c4, IllegalArgumentException -> 0x01c2 }
        r7 = "Read out Moat params: ";
        r6.<init>(r7);	 Catch:{ JSONException -> 0x01c4, IllegalArgumentException -> 0x01c2 }
        r2 = r2.b;	 Catch:{ JSONException -> 0x01c4, IllegalArgumentException -> 0x01c2 }
        r2 = r2.toString();	 Catch:{ JSONException -> 0x01c4, IllegalArgumentException -> 0x01c2 }
        r6.append(r2);	 Catch:{ JSONException -> 0x01c4, IllegalArgumentException -> 0x01c2 }
    L_0x0122:
        r2 = "metaInfo";
        r2 = r1.has(r2);	 Catch:{ JSONException -> 0x01c4, IllegalArgumentException -> 0x01c2 }
        if (r2 == 0) goto L_0x01a9;
    L_0x012a:
        r2 = "metaInfo";
        r2 = r1.getJSONObject(r2);	 Catch:{ JSONException -> 0x01c4, IllegalArgumentException -> 0x01c2 }
        r6 = "unknown";
        r7 = "creativeType";
        r7 = r2.has(r7);	 Catch:{ JSONException -> 0x01c4, IllegalArgumentException -> 0x01c2 }
        if (r7 == 0) goto L_0x0140;
    L_0x013a:
        r6 = "creativeType";
        r6 = r2.getString(r6);	 Catch:{ JSONException -> 0x01c4, IllegalArgumentException -> 0x01c2 }
    L_0x0140:
        r7 = "iasEnabled";
        r7 = r2.has(r7);	 Catch:{ JSONException -> 0x01c4, IllegalArgumentException -> 0x01c2 }
        if (r7 == 0) goto L_0x01a9;
    L_0x0148:
        r7 = "iasEnabled";
        r2 = r2.getBoolean(r7);	 Catch:{ JSONException -> 0x01c4, IllegalArgumentException -> 0x01c2 }
        if (r2 == 0) goto L_0x01a9;
    L_0x0150:
        r2 = new com.inmobi.ads.bq;	 Catch:{ JSONException -> 0x01c4, IllegalArgumentException -> 0x01c2 }
        r7 = 3;
        r2.<init>(r7);	 Catch:{ JSONException -> 0x01c4, IllegalArgumentException -> 0x01c2 }
        r7 = new java.util.HashMap;	 Catch:{ JSONException -> 0x01c4, IllegalArgumentException -> 0x01c2 }
        r7.<init>();	 Catch:{ JSONException -> 0x01c4, IllegalArgumentException -> 0x01c2 }
        r8 = r6.hashCode();	 Catch:{ JSONException -> 0x01c4, IllegalArgumentException -> 0x01c2 }
        r9 = 112202875; // 0x6b0147b float:6.6233935E-35 double:5.5435586E-316;
        if (r8 == r9) goto L_0x0174;
    L_0x0164:
        r3 = 1425678798; // 0x54fa21ce float:8.5944718E12 double:7.04378916E-315;
        if (r8 == r3) goto L_0x016a;
    L_0x0169:
        goto L_0x017d;
    L_0x016a:
        r3 = "nonvideo";
        r3 = r6.equals(r3);	 Catch:{ JSONException -> 0x01c4, IllegalArgumentException -> 0x01c2 }
        if (r3 == 0) goto L_0x017d;
    L_0x0172:
        r3 = r5;
        goto L_0x017e;
    L_0x0174:
        r5 = "video";
        r5 = r6.equals(r5);	 Catch:{ JSONException -> 0x01c4, IllegalArgumentException -> 0x01c2 }
        if (r5 == 0) goto L_0x017d;
    L_0x017c:
        goto L_0x017e;
    L_0x017d:
        r3 = r4;
    L_0x017e:
        switch(r3) {
            case 1: goto L_0x0187;
            case 2: goto L_0x0184;
            default: goto L_0x0181;
        };	 Catch:{ JSONException -> 0x01c4, IllegalArgumentException -> 0x01c2 }
    L_0x0181:
        r3 = "unknown";
        goto L_0x0189;
    L_0x0184:
        r3 = "video";
        goto L_0x0189;
    L_0x0187:
        r3 = "nonvideo";
    L_0x0189:
        r4 = "creativeType";
        r7.put(r4, r3);	 Catch:{ JSONException -> 0x01c4, IllegalArgumentException -> 0x01c2 }
        r2.b = r7;	 Catch:{ JSONException -> 0x01c4, IllegalArgumentException -> 0x01c2 }
        r3 = r2.b;	 Catch:{ JSONException -> 0x01c4, IllegalArgumentException -> 0x01c2 }
        if (r3 == 0) goto L_0x01a4;
    L_0x0194:
        r3 = new java.lang.StringBuilder;	 Catch:{ JSONException -> 0x01c4, IllegalArgumentException -> 0x01c2 }
        r4 = "Read out IAS params: ";
        r3.<init>(r4);	 Catch:{ JSONException -> 0x01c4, IllegalArgumentException -> 0x01c2 }
        r4 = r2.b;	 Catch:{ JSONException -> 0x01c4, IllegalArgumentException -> 0x01c2 }
        r4 = r4.toString();	 Catch:{ JSONException -> 0x01c4, IllegalArgumentException -> 0x01c2 }
        r3.append(r4);	 Catch:{ JSONException -> 0x01c4, IllegalArgumentException -> 0x01c2 }
    L_0x01a4:
        r3 = r13.L;	 Catch:{ JSONException -> 0x01c4, IllegalArgumentException -> 0x01c2 }
        r3.add(r2);	 Catch:{ JSONException -> 0x01c4, IllegalArgumentException -> 0x01c2 }
    L_0x01a9:
        r2 = "tracking";
        r2 = r1.has(r2);	 Catch:{ JSONException -> 0x01c4, IllegalArgumentException -> 0x01c2 }
        if (r2 == 0) goto L_0x01e7;
    L_0x01b1:
        r2 = "web";
        r3 = "tracking";
        r1 = r1.getString(r3);	 Catch:{ JSONException -> 0x01c4, IllegalArgumentException -> 0x01c2 }
        r1 = r2.equals(r1);	 Catch:{ JSONException -> 0x01c4, IllegalArgumentException -> 0x01c2 }
        if (r1 == 0) goto L_0x01e7;
    L_0x01bf:
        r13.r = r0;	 Catch:{ JSONException -> 0x01c4, IllegalArgumentException -> 0x01c2 }
        goto L_0x01e7;
    L_0x01c2:
        r0 = move-exception;
        goto L_0x01ca;
    L_0x01c4:
        r0 = move-exception;
        goto L_0x01db;
    L_0x01c6:
        r14 = move-exception;
        r12 = r0;
        r0 = r14;
        r14 = r12;
    L_0x01ca:
        r1 = com.inmobi.commons.core.a.a.a();
        r2 = new com.inmobi.commons.core.e.a;
        r2.<init>(r0);
        r1.a(r2);
        goto L_0x01e7;
    L_0x01d7:
        r14 = move-exception;
        r12 = r0;
        r0 = r14;
        r14 = r12;
    L_0x01db:
        r1 = com.inmobi.commons.core.a.a.a();
        r2 = new com.inmobi.commons.core.e.a;
        r2.<init>(r0);
        r1.a(r2);
    L_0x01e7:
        return r14;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.inmobi.ads.i.a(com.inmobi.ads.a):boolean");
    }

    /* Access modifiers changed, original: protected */
    public void b(long j, boolean z) {
        StringBuilder stringBuilder = new StringBuilder("Asset availability changed (");
        stringBuilder.append(z);
        stringBuilder.append(") for placement ID (");
        stringBuilder.append(j);
        stringBuilder.append(")");
    }

    /* Access modifiers changed, original: protected */
    @UiThread
    public void a(long j, boolean z, @NonNull a aVar) {
        if (j == this.d && 1 == this.a && z) {
            this.C = aVar.e;
            this.D = aVar.c();
        }
    }

    /* Access modifiers changed, original: protected */
    @UiThread
    public void c(long j, @NonNull a aVar) {
        if (j == this.d && this.a == 1) {
            if (a(aVar)) {
                a(f(), "ARF", "");
                this.I = SystemClock.elapsedRealtime();
                this.a = 2;
                return;
            }
            b("ParsingFailed");
            a(new InMobiAdRequestStatus(StatusCode.INTERNAL_ERROR), true);
        }
    }

    /* Access modifiers changed, original: final */
    public final void a(InMobiAdRequestStatus inMobiAdRequestStatus) {
        if (StatusCode.NO_FILL.equals(inMobiAdRequestStatus.getStatusCode())) {
            b("NoFill");
        } else if (StatusCode.SERVER_ERROR.equals(inMobiAdRequestStatus.getStatusCode())) {
            b("ServerError");
        } else if (StatusCode.NETWORK_UNREACHABLE.equals(inMobiAdRequestStatus.getStatusCode())) {
            b("NetworkUnreachable");
        } else if (StatusCode.AD_ACTIVE.equals(inMobiAdRequestStatus.getStatusCode())) {
            b("AdActive");
        } else if (StatusCode.REQUEST_PENDING.equals(inMobiAdRequestStatus.getStatusCode())) {
            b("RequestPending");
        } else if (StatusCode.REQUEST_INVALID.equals(inMobiAdRequestStatus.getStatusCode())) {
            b("RequestInvalid");
        } else if (StatusCode.REQUEST_TIMED_OUT.equals(inMobiAdRequestStatus.getStatusCode())) {
            b("RequestTimedOut");
        } else if (StatusCode.EARLY_REFRESH_REQUEST.equals(inMobiAdRequestStatus.getStatusCode())) {
            b("EarlyRefreshRequest");
        } else {
            if (StatusCode.INTERNAL_ERROR.equals(inMobiAdRequestStatus.getStatusCode())) {
                b("InternalError");
            }
        }
    }

    public void a(MonetizationContext monetizationContext) {
        this.M = monetizationContext;
    }

    public MonetizationContext l() {
        return this.M;
    }

    static boolean m() {
        try {
            RecyclerView.class.getName();
            Picasso.class.getName();
            return false;
        } catch (NoClassDefFoundError unused) {
            return true;
        }
    }

    @UiThread
    public void n() {
        d("AdLoadRequested");
        if (!com.inmobi.commons.core.utilities.d.a()) {
            a(new InMobiAdRequestStatus(StatusCode.NETWORK_UNREACHABLE), true);
        } else if (this.Q) {
            a(new InMobiAdRequestStatus(StatusCode.LOAD_CALLED_AFTER_GET_SIGNALS), false);
        } else {
            this.p.execute(this.K);
        }
    }

    /* Access modifiers changed, original: final */
    public final void o() {
        boolean z;
        final b f = f();
        final long currentTimeMillis = System.currentTimeMillis();
        if (com.inmobi.commons.core.utilities.d.a()) {
            switch (this.a) {
                case 1:
                case 2:
                case 4:
                    if (f != null) {
                        f.b(new InMobiAdRequestStatus(StatusCode.GET_SIGNALS_CALLED_WHILE_LOADING));
                    }
                    a(f, "ART", "LoadInProgress");
                    a("AdGetSignalsFailed", currentTimeMillis);
                    break;
                case 6:
                case 7:
                case 8:
                    if (f != null) {
                        f.b(new InMobiAdRequestStatus(StatusCode.AD_ACTIVE));
                    }
                    a(f, "ART", "ReloadNotPermitted");
                    a("AdGetSignalsFailed", currentTimeMillis);
                    break;
                case 10:
                    if (f != null) {
                        f.b(new InMobiAdRequestStatus(StatusCode.FETCHING_SIGNALS_STATE_ERROR));
                    }
                    a(f, "ART", "SignalsFetchInProgress");
                    a("AdGetSignalsFailed", currentTimeMillis);
                    break;
                default:
                    z = false;
                    break;
            }
        }
        if (f != null) {
            f.b(new InMobiAdRequestStatus(StatusCode.NETWORK_UNREACHABLE));
        }
        a(f, "ART", "NetworkNotAvailable");
        a("AdGetSignalsFailed", currentTimeMillis);
        z = true;
        if (!z) {
            this.Q = true;
            d("AdGetSignalsRequested");
            this.p.execute(new Runnable() {
                public final void run() {
                    i.this.a = 10;
                    String a = com.inmobi.ads.c.a.a(i.this.f);
                    if (i.this.R == null) {
                        i.this.R = new com.inmobi.ads.b.a(i.this, a);
                    } else {
                        i.this.R.b = a;
                    }
                    if (f != null) {
                        try {
                            byte[] a2 = i.this.R.a();
                            if (a2 == null) {
                                i.this.a = 3;
                                f.b(new InMobiAdRequestStatus(StatusCode.INTERNAL_ERROR));
                                i.this.a(i.this.f(), "ART", "RequestCreationFailed");
                                i.this.a("AdGetSignalsFailed", currentTimeMillis);
                                return;
                            }
                            f.a(a2);
                            i.this.a = 11;
                            i.this.a(i.this.f(), "VAR", "");
                            i.this.a("AdGetSignalsSucceeded", currentTimeMillis);
                        } catch (com.inmobi.ads.a.b unused) {
                            i.this.a = 3;
                            f.b(new InMobiAdRequestStatus(StatusCode.GDPR_COMPLIANCE_ENFORCED));
                        }
                    }
                }
            });
        }
    }

    private void a(@NonNull String str, long j) {
        Map hashMap = new HashMap();
        hashMap.put("latency", Long.valueOf(System.currentTimeMillis() - j));
        a(str, hashMap);
    }

    @UiThread
    public void a(final boolean z) {
        d("AdPrefetchRequested");
        this.a = 1;
        this.p.execute(new Runnable() {
            public final void run() {
                try {
                    if (com.inmobi.commons.core.utilities.d.a()) {
                        o.a().e();
                        i.N();
                        com.inmobi.commons.core.configs.a hVar = new h();
                        com.inmobi.commons.core.configs.b.a().a(hVar, null);
                        if (!hVar.g) {
                            i.this.H = System.currentTimeMillis();
                            try {
                                if (i.this.N == null) {
                                    i.this.N = new bl(i.this, i.this.g.a(i.this.b()));
                                }
                                i.this.l = i.this.N.a(i.this.u(), z, i.this.g.c);
                                return;
                            } catch (com.inmobi.ads.a.a e) {
                                i.y;
                                e.getMessage();
                                if (!i.this.N.a) {
                                    i.this.b(i.this.d, new InMobiAdRequestStatus(StatusCode.EARLY_REFRESH_REQUEST));
                                }
                            }
                        }
                        return;
                    }
                    i.this.b(i.this.d, new InMobiAdRequestStatus(StatusCode.NETWORK_UNREACHABLE));
                } catch (Exception e2) {
                    Logger.a(InternalLogLevel.ERROR, "InMobi", "Unable to Prefetch ad; SDK encountered an unexpected error");
                    i.y;
                    new StringBuilder("Prefetch failed with unexpected error: ").append(e2.getMessage());
                    com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e2));
                }
            }
        });
    }

    /* Access modifiers changed, original: 0000 */
    @UiThread
    public void q() {
        a(false);
    }

    private void a(@NonNull final String str, final WeakReference<b> weakReference) {
        this.s.post(new Runnable() {
            public final void run() {
                i.this.a = 3;
                i.this.b(str);
                if (i.this.w) {
                    i.y;
                    return;
                }
                b bVar = (b) weakReference.get();
                if (bVar == null) {
                    i.this.g();
                } else if ("int".equals(i.this.b())) {
                    i.this.a(bVar, "AVFB", "");
                    bVar.b();
                } else {
                    bVar.a(new InMobiAdRequestStatus(StatusCode.INTERNAL_ERROR));
                }
            }
        });
    }

    /* Access modifiers changed, original: final */
    public final void s() {
        AdContainer j = j();
        if (j != null) {
            j.a(2, null);
        }
    }

    public final f t() {
        f u = u();
        u.l = true;
        return u;
    }

    @NonNull
    public final f u() {
        String str = this.g.a;
        long j = this.d;
        com.inmobi.commons.core.utilities.uid.d dVar = new com.inmobi.commons.core.utilities.uid.d(this.g.p.a);
        com.inmobi.ads.cache.d.a();
        f fVar = new f(str, j, dVar, com.inmobi.ads.cache.d.c());
        fVar.f = this.e;
        fVar.g = this.f;
        fVar.e = b();
        fVar.b = "sdkJson";
        fVar.d = this.g.a(b()).b;
        fVar.h = e();
        fVar.c = c();
        fVar.r = this.g.e * 1000;
        fVar.s = this.g.e * 1000;
        fVar.j = this.M;
        fVar.y = O();
        return fVar;
    }

    @UiThread
    public void v() {
        if (!this.w) {
            this.w = true;
            this.k = null;
            this.C = 0;
            this.D = -1;
            this.L.clear();
            AdContainer j = j();
            if (j != null) {
                j.destroy();
            }
            this.a = 0;
            this.m = "unknown";
            this.O = false;
            this.u = null;
            this.t = false;
            this.v = false;
            this.x = "";
            this.i = this.b;
            this.A = false;
            this.Q = false;
        }
    }

    /* Access modifiers changed, original: protected */
    public void z() {
        b("RenderFailed");
    }

    /* Access modifiers changed, original: final */
    public final void C() {
        this.G.removeMessages(0);
    }

    /* Access modifiers changed, original: final */
    public final void D() {
        this.s.post(new Runnable() {
            public final void run() {
                i.this.E();
            }
        });
    }

    /* Access modifiers changed, original: protected */
    public void E() {
        b("RenderTimeOut");
        if (this.a == 2) {
            this.a = 3;
            if (f() != null) {
                f().a(new InMobiAdRequestStatus(StatusCode.INTERNAL_ERROR));
            }
        }
    }

    /* Access modifiers changed, original: final */
    public final void a(String str) {
        Map hashMap = new HashMap();
        hashMap.put("errorCode", str);
        c("AdLoadRejected", hashMap);
    }

    /* Access modifiers changed, original: final */
    public final void F() {
        Map hashMap = new HashMap();
        hashMap.put("latency", Long.valueOf(SystemClock.elapsedRealtime() - this.I));
        c("AdLoadSuccessful", hashMap);
    }

    /* Access modifiers changed, original: final */
    public final void b(String str) {
        Map hashMap = new HashMap();
        hashMap.put("errorCode", str);
        hashMap.put("latency", Long.valueOf(SystemClock.elapsedRealtime() - this.I));
        c("AdLoadFailed", hashMap);
    }

    /* Access modifiers changed, original: final */
    public final void c(String str) {
        Map hashMap = new HashMap();
        hashMap.put("errorCode", str);
        c("AdPrefetchRejected", hashMap);
    }

    /* Access modifiers changed, original: final */
    public final void a(b bVar, String str, String str2) {
        if (bVar != null && bVar.i()) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(str);
            stringBuilder.append(" ");
            stringBuilder.append(str2);
            c cVar = this.g;
            String b = b();
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append(b);
            stringBuilder2.append("Dict");
            com.inmobi.ads.c.a aVar = (com.inmobi.ads.c.a) cVar.g.get(stringBuilder2.toString());
            if (aVar == null) {
                aVar = cVar.f;
            }
            if (aVar.h) {
                String str3 = "";
                if (this.l != null) {
                    str3 = this.l;
                }
                String str4 = str;
                this.P.a(new com.inmobi.commons.core.f.b(UUID.randomUUID().toString(), this.m, str4, this.d, str3, str2, (String) com.inmobi.commons.core.utilities.b.b.a(O()).get("d-nettype-raw"), b(), System.currentTimeMillis()));
            }
        }
    }

    @VisibleForTesting
    private boolean O() {
        return this.g.i.m && com.inmobi.commons.a.a.d();
    }

    public final void b(String str, Map<String, Object> map) {
        c(str, (Map) map);
    }

    public final void a(String str, Map<String, Object> map) {
        c(str, (Map) map);
    }

    public final void d(String str) {
        c(str, new HashMap());
    }

    public final void c(String str, Map<String, Object> map) {
        Object obj;
        HashMap hashMap = new HashMap();
        hashMap.put("type", b());
        hashMap.put("plId", Long.valueOf(this.d));
        hashMap.put("impId", this.k);
        hashMap.put("isPreloaded", this.n ? "1" : "0");
        String str2 = "networkType";
        switch (com.inmobi.commons.core.utilities.b.b.a()) {
            case 0:
                obj = e.w;
                break;
            case 1:
                obj = e.ad;
                break;
            default:
                obj = "NIL";
                break;
        }
        hashMap.put(str2, obj);
        hashMap.put(HlsSegmentFormat.TS, Long.valueOf(System.currentTimeMillis()));
        if (map.get("clientRequestId") == null) {
            hashMap.put("clientRequestId", this.l);
        }
        for (Entry entry : map.entrySet()) {
            hashMap.put(entry.getKey(), entry.getValue());
        }
        try {
            com.inmobi.commons.core.e.b.a();
            com.inmobi.commons.core.e.b.a("ads", str, hashMap);
        } catch (Exception e) {
            StringBuilder stringBuilder = new StringBuilder("Error in submitting telemetry event : (");
            stringBuilder.append(e.getMessage());
            stringBuilder.append(")");
        }
    }

    /* Access modifiers changed, original: final */
    public final void c(@NonNull a aVar) {
        if (aVar instanceof bc) {
            bc bcVar = (bc) aVar;
            Context a = a();
            boolean z = this.g.k.j;
            for (bq bqVar : this.L) {
                if (z && 3 == bqVar.a && "video" == bqVar.b.get(com.til.colombia.android.vast.g.p)) {
                    try {
                        be beVar = (be) new ao(d(), new JSONObject(this.h), this.g, new bx(bcVar.l, bcVar.m, bcVar.n, bcVar.h(), bcVar.i(), this.g.m)).c(ShareConstants.VIDEO_URL).get(0);
                        if (a != null) {
                            Set hashSet = new HashSet();
                            for (NativeTracker nativeTracker : beVar.u) {
                                if (TrackerEventType.TRACKER_EVENT_TYPE_IAS == nativeTracker.b) {
                                    hashSet.add(d(nativeTracker.a, nativeTracker.c));
                                }
                            }
                            if (hashSet.size() != 0) {
                                bqVar.b.put("avidAdSession", w.a(a, hashSet));
                                bqVar.b.put("deferred", Boolean.valueOf(true));
                            }
                        }
                    } catch (Exception e) {
                        new StringBuilder("Setting up impression tracking for AVID encountered an unexpected error: ").append(e.getMessage());
                        com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
                    }
                }
            }
        }
    }

    @Nullable
    private static String d(String str, Map<String, String> map) {
        if (map == null || str == null) {
            return str;
        }
        for (String str2 : map.keySet()) {
            str = str.replace(str2, (CharSequence) map.get(str2));
        }
        return str;
    }

    public final void a(long j) {
        d("AdPrefetchSuccessful");
        if (!this.w && a() != null) {
            Message obtain = Message.obtain();
            obtain.what = 14;
            Bundle bundle = new Bundle();
            bundle.putLong(AudienceNetworkActivity.PLACEMENT_ID, j);
            obtain.setData(bundle);
            this.J.sendMessage(obtain);
        }
    }

    public final void b(long j, InMobiAdRequestStatus inMobiAdRequestStatus) {
        if (StatusCode.EARLY_REFRESH_REQUEST.equals(inMobiAdRequestStatus.getStatusCode())) {
            c("EarlyRefreshRequest");
        } else if (StatusCode.NETWORK_UNREACHABLE.equals(inMobiAdRequestStatus.getStatusCode())) {
            c("NetworkUnreachable");
        } else {
            d("AdPrefetchFailed");
        }
        if (!this.w && a() != null) {
            Message obtain = Message.obtain();
            obtain.what = 13;
            obtain.obj = inMobiAdRequestStatus;
            Bundle bundle = new Bundle();
            bundle.putLong(AudienceNetworkActivity.PLACEMENT_ID, j);
            obtain.setData(bundle);
            this.J.sendMessage(obtain);
        }
    }

    public final void e(final String str) {
        this.p.execute(new Runnable() {
            public final void run() {
                if (i.this.k == null || str == null) {
                    i.y;
                    return;
                }
                d.a();
                String f = i.this.k;
                String str = str;
                com.inmobi.commons.core.d.b a = com.inmobi.commons.core.d.b.a();
                a c = d.c(f);
                int i = 0;
                if (c != null) {
                    c.i = str;
                    i = a.b("ad", c.a(), "imp_id=?", new String[]{f});
                }
                i.y;
                StringBuilder stringBuilder = new StringBuilder("Updated ");
                stringBuilder.append(i);
                stringBuilder.append("for blob ");
                stringBuilder.append(str);
            }
        });
    }

    public final void a(final String str, final String str2, @NonNull final com.inmobi.rendering.b bVar) {
        this.p.execute(new Runnable() {
            public final void run() {
                try {
                    if (i.this.k != null) {
                        d.a();
                        a c = d.c(i.this.k);
                        if (c != null) {
                            bVar.a(str, str2, c.i);
                            i.y;
                            return;
                        }
                        i.y;
                        bVar.a(str, str2, "");
                        return;
                    }
                    i.y;
                    bVar.a(str, str2, "");
                } catch (Exception e) {
                    i.y;
                    com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
                }
            }
        });
    }

    /* Access modifiers changed, original: final */
    public final void I() {
        this.p.execute(new Runnable() {
            public final void run() {
                try {
                    final ao aoVar = new ao(i.this.d(), new JSONObject(i.this.h), i.this.g, null);
                    i.this.s.post(new Runnable() {
                        public final void run() {
                            try {
                                bf bfVar = aoVar.k;
                                if (bfVar != null) {
                                    i.this.u = new RenderView(i.this.a(), new RenderingProperties(i.this.d()), i.this.L, i.this.k);
                                    i.this.u.a(i.this.S, i.this.g);
                                    i.this.u.j = true;
                                    i.this.u.setBlobProvider(i.this);
                                    i.this.u.setIsPreload(true);
                                    i.this.u.setPlacementId(i.this.d);
                                    i.this.u.setCreativeId(i.this.x);
                                    i.this.u.setAllowAutoRedirection(i.this.A);
                                    if (i.this.r == 0) {
                                        i.this.a(true, i.this.u);
                                    }
                                    if ("URL".equals(bfVar.z)) {
                                        i.this.u.b((String) bfVar.e);
                                    } else {
                                        i.this.u.a((String) bfVar.e);
                                    }
                                }
                                i.e(i.this);
                            } catch (Exception e) {
                                i.y;
                                i.this.a = 3;
                                i.this.a(new InMobiAdRequestStatus(StatusCode.INTERNAL_ERROR), false);
                                com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
                            }
                        }
                    });
                } catch (Exception e) {
                    i.y;
                    i.this.s.post(new Runnable() {
                        public final void run() {
                            i.this.a = 3;
                            i.this.a(new InMobiAdRequestStatus(StatusCode.INTERNAL_ERROR), false);
                        }
                    });
                    com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
                }
            }
        });
    }

    /* Access modifiers changed, original: final */
    @UiThread
    public final void J() {
        if (this.t && this.v && this.O) {
            C();
            K();
        }
    }

    /* Access modifiers changed, original: final */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x002f A:{RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x003d  */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0030  */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x002f A:{RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x003d  */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0030  */
    @android.support.annotation.Nullable
    public final com.inmobi.ads.AdContainer j() {
        /*
        r6 = this;
        r0 = r6.a;
        r1 = r6.m;
        r2 = r1.hashCode();
        r3 = -1084172778; // 0xffffffffbf60d616 float:-0.8782667 double:NaN;
        r4 = 2;
        r5 = 1;
        if (r2 == r3) goto L_0x001f;
    L_0x000f:
        r3 = 3213227; // 0x3107ab float:4.50269E-39 double:1.587545E-317;
        if (r2 == r3) goto L_0x0015;
    L_0x0014:
        goto L_0x0029;
    L_0x0015:
        r2 = "html";
        r1 = r1.equals(r2);
        if (r1 == 0) goto L_0x0029;
    L_0x001d:
        r1 = r5;
        goto L_0x002a;
    L_0x001f:
        r2 = "inmobiJson";
        r1 = r1.equals(r2);
        if (r1 == 0) goto L_0x0029;
    L_0x0027:
        r1 = r4;
        goto L_0x002a;
    L_0x0029:
        r1 = -1;
    L_0x002a:
        r2 = 3;
        r3 = 0;
        switch(r1) {
            case 1: goto L_0x003d;
            case 2: goto L_0x0030;
            default: goto L_0x002f;
        };
    L_0x002f:
        return r3;
    L_0x0030:
        if (r0 == 0) goto L_0x003c;
    L_0x0032:
        if (r5 == r0) goto L_0x003c;
    L_0x0034:
        if (r2 == r0) goto L_0x003c;
    L_0x0036:
        if (r4 != r0) goto L_0x0039;
    L_0x0038:
        goto L_0x003c;
    L_0x0039:
        r0 = r6.o;
        return r0;
    L_0x003c:
        return r3;
    L_0x003d:
        if (r0 == 0) goto L_0x0049;
    L_0x003f:
        if (r5 == r0) goto L_0x0049;
    L_0x0041:
        if (r2 != r0) goto L_0x0044;
    L_0x0043:
        goto L_0x0049;
    L_0x0044:
        r0 = r6.k();
        return r0;
    L_0x0049:
        return r3;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.inmobi.ads.i.j():com.inmobi.ads.AdContainer");
    }

    public final void a(long j, boolean z) {
        if (!this.w && a() != null) {
            Message obtain = Message.obtain();
            obtain.what = 4;
            Bundle bundle = new Bundle();
            bundle.putLong(AudienceNetworkActivity.PLACEMENT_ID, j);
            bundle.putBoolean("assetAvailable", z);
            obtain.setData(bundle);
            this.J.sendMessage(obtain);
        }
    }

    public final void b(long j, a aVar) {
        if (!this.w && a() != null) {
            this.I = SystemClock.elapsedRealtime();
            Message obtain = Message.obtain();
            obtain.what = 1;
            obtain.obj = aVar;
            Bundle bundle = new Bundle();
            bundle.putLong(AudienceNetworkActivity.PLACEMENT_ID, j);
            bundle.putBoolean("adAvailable", true);
            obtain.setData(bundle);
            this.J.sendMessage(obtain);
        }
    }

    public final void a(long j, @NonNull a aVar) {
        if (!this.w && a() != null) {
            Message obtain = Message.obtain();
            obtain.what = 2;
            Bundle bundle = new Bundle();
            bundle.putLong(AudienceNetworkActivity.PLACEMENT_ID, j);
            obtain.setData(bundle);
            obtain.obj = aVar;
            this.J.sendMessage(obtain);
        }
    }

    public void a(final long j, final InMobiAdRequestStatus inMobiAdRequestStatus) {
        if (!this.w && a() != null) {
            this.s.post(new Runnable() {
                public final void run() {
                    try {
                        if (j == i.this.d) {
                            i.this.a(i.this.f(), "ARN", "");
                            StringBuilder stringBuilder = new StringBuilder("Failed to fetch ad for placement id: ");
                            stringBuilder.append(i.this.d);
                            stringBuilder.append(", reason phrase available in onAdLoadFailed callback.");
                            Logger.a(InternalLogLevel.DEBUG, "InMobi", stringBuilder.toString());
                            i.this.a(inMobiAdRequestStatus, true);
                        }
                    } catch (Exception e) {
                        Logger.a(InternalLogLevel.ERROR, "[InMobi]", "Unable to load Ad; SDK encountered an unexpected error");
                        i.y;
                        new StringBuilder("onAdFetchFailed with error: ").append(e.getMessage());
                        com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
                    }
                }
            });
        }
    }

    /* Access modifiers changed, original: 0000 */
    @UiThread
    public void a(InMobiAdRequestStatus inMobiAdRequestStatus, boolean z) {
        if (this.a == 1 && z) {
            this.a = 3;
        }
        b f = f();
        if (f != null) {
            f.a(inMobiAdRequestStatus);
        }
        a(inMobiAdRequestStatus);
    }

    /* Access modifiers changed, original: final */
    @UiThread
    public final void a(@Nullable final byte[] bArr) {
        boolean z;
        if (com.inmobi.commons.core.utilities.b.e.e()) {
            int i = this.a;
            if (i != 1) {
                switch (i) {
                    case 6:
                    case 7:
                    case 8:
                        this.s.post(new Runnable() {
                            public final void run() {
                                i.this.a(new InMobiAdRequestStatus(StatusCode.AD_ACTIVE), false);
                            }
                        });
                        break;
                    default:
                        switch (i) {
                            case 10:
                                this.s.post(new Runnable() {
                                    public final void run() {
                                        i.this.a(new InMobiAdRequestStatus(StatusCode.FETCHING_SIGNALS_STATE_ERROR), false);
                                    }
                                });
                                break;
                            case 11:
                                z = false;
                                break;
                            default:
                                this.s.post(new Runnable() {
                                    public final void run() {
                                        i.this.a(new InMobiAdRequestStatus(StatusCode.GET_SIGNALS_NOT_CALLED_FOR_LOAD_WITH_RESPONSE), false);
                                    }
                                });
                                break;
                        }
                }
            }
            this.s.post(new Runnable() {
                public final void run() {
                    i.this.a(new InMobiAdRequestStatus(StatusCode.LOAD_WITH_RESPONSE_CALLED_WHILE_LOADING), false);
                }
            });
        } else {
            v();
            this.s.post(new Runnable() {
                public final void run() {
                    i.this.a(new InMobiAdRequestStatus(StatusCode.GDPR_COMPLIANCE_ENFORCED), false);
                }
            });
        }
        z = true;
        if (!z) {
            if (bArr == null || bArr.length == 0) {
                a(new InMobiAdRequestStatus(StatusCode.INVALID_RESPONSE_IN_LOAD), true);
            } else if (this.R == null) {
                a(new InMobiAdRequestStatus(StatusCode.INTERNAL_ERROR), true);
            } else {
                this.p.execute(new Runnable() {
                    public final void run() {
                        i.a(i.this, bArr, i.this.R);
                    }
                });
            }
        }
    }

    /* Access modifiers changed, original: final */
    public final boolean p() {
        StringBuilder stringBuilder;
        if (1 == this.a) {
            b(this.d, new InMobiAdRequestStatus(StatusCode.REQUEST_PENDING));
            stringBuilder = new StringBuilder("An ad prefetch is already in progress. Please wait for the prefetch to complete before requesting for another ad for placement id: ");
            stringBuilder.append(this.d);
            Logger.a(InternalLogLevel.ERROR, "InMobi", stringBuilder.toString());
            return true;
        } else if (8 == this.a || 7 == this.a) {
            b(this.d, new InMobiAdRequestStatus(StatusCode.AD_ACTIVE));
            stringBuilder = new StringBuilder("An ad is currently being viewed by the user. Please wait for the user to close the ad before requesting for another ad for placement id: ");
            stringBuilder.append(this.d);
            Logger.a(InternalLogLevel.ERROR, "InMobi", stringBuilder.toString());
            return true;
        } else {
            if (2 == this.a) {
                if ("html".equals(this.m)) {
                    b(this.d, new InMobiAdRequestStatus(StatusCode.REQUEST_PENDING));
                    stringBuilder = new StringBuilder("An ad load is already in progress. Please wait for the load to complete before requesting prefetch for another ad for placement id: ");
                    stringBuilder.append(this.d);
                    Logger.a(InternalLogLevel.ERROR, "InMobi", stringBuilder.toString());
                    return true;
                } else if ("inmobiJson".equals(this.m)) {
                    a(this.d);
                    return true;
                }
            }
            if (5 != this.a && 9 != this.a) {
                return false;
            }
            a(this.d);
            return true;
        }
    }

    /* Access modifiers changed, original: protected */
    public int r() {
        int i = 1;
        try {
            this.a = 1;
            o.a().e();
            com.inmobi.commons.core.utilities.uid.c.a();
            com.inmobi.commons.core.utilities.uid.c.c();
            com.inmobi.commons.core.configs.a hVar = new h();
            com.inmobi.commons.core.configs.b.a().a(hVar, null);
            if (hVar.g) {
                d("LoadAfterMonetizationDisabled");
                Logger.a(InternalLogLevel.ERROR, y, "SDK will not perform this load operation as monetization has been disabled. Please contact InMobi for further info.");
                return -1;
            }
            f u = u();
            this.H = System.currentTimeMillis();
            h i2 = i();
            try {
                String a;
                int i3 = this.g.c;
                h.a();
                i2.c = u;
                if ("int".equals(i2.c.e)) {
                    h.c();
                    List c = i2.b.c(i2.c.a, i2.c.c, i2.c.j, com.inmobi.ads.c.a.a(i2.c.g));
                    if (c.size() == 0) {
                        if (SystemClock.elapsedRealtime() - i2.e >= ((long) (i3 * 1000))) {
                            i = 0;
                        }
                        if (i != 0) {
                            throw new com.inmobi.ads.a.a("Ignoring request to fetch an ad from the network sooner than the minimum request interval");
                        }
                        a = i2.a(i2.c, i2.c.c().equals("1"));
                    } else {
                        a = ((a) c.get(0)).h;
                        if ("INMOBIJSON".equalsIgnoreCase(((a) c.get(0)).e())) {
                            i2.a.b(i2.c.a, (a) c.get(0));
                            i2.a(c);
                        } else {
                            a = i2.b();
                        }
                    }
                } else {
                    a = i2.b();
                }
                Map hashMap = new HashMap();
                hashMap.put("im-accid", com.inmobi.commons.a.a.e());
                hashMap.put("isPreloaded", i2.c.c());
                i2.a.a("AdCacheAdRequested", hashMap);
                this.l = a;
                a(f(), "VAR", "");
                if (this.n) {
                    d("AdPreLoadRequested");
                }
            } catch (com.inmobi.ads.a.a e) {
                e.getMessage();
                this.s.post(new Runnable() {
                    public final void run() {
                        i.this.a(new InMobiAdRequestStatus(StatusCode.EARLY_REFRESH_REQUEST), true);
                    }
                });
            }
            return 0;
        } catch (Exception e2) {
            Logger.a(InternalLogLevel.ERROR, "InMobi", "Unable to load ad; SDK encountered an unexpected error");
            new StringBuilder("Load failed with unexpected error: ").append(e2.getMessage());
            com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e2));
            return -2;
        }
    }

    /* Access modifiers changed, original: final */
    public final void a(b bVar, @NonNull String str, @Nullable Runnable runnable, @Nullable Looper looper) {
        Runnable runnable2 = runnable;
        Looper looper2 = looper;
        if ("html".equals(this.m)) {
            final String str2 = str;
            this.s.post(new Runnable() {
                public final void run() {
                    i.this.k().a(str2);
                    i.e(i.this);
                }
            });
            return;
        }
        if ("inmobiJson".equals(this.m)) {
            final WeakReference weakReference = new WeakReference(bVar);
            try {
                this.I = SystemClock.elapsedRealtime();
                ao aoVar = new ao(d(), new JSONObject(this.h), this.g, this.j);
                if (!aoVar.c() || a() == null) {
                    a("DataModelValidationFailed", weakReference);
                    return;
                }
                ah a = b.a(a(), new RenderingProperties(d()), aoVar, this.k, this.l, this.L, this.g, this.d, this.A, this.x);
                a.a(new com.inmobi.ads.ah.c() {
                    public final void a() {
                        if (!i.this.w) {
                            b bVar = (b) weakReference.get();
                            if (bVar != null) {
                                i.this.a(bVar, "AVFB", "");
                                bVar.b();
                                return;
                            }
                            i.this.g();
                        }
                    }

                    public final void b() {
                        i.this.d("AdRendered");
                        if (!i.this.w) {
                            i.this.s.post(new Runnable() {
                                public final void run() {
                                    i.this.b((b) weakReference.get());
                                }
                            });
                        }
                    }

                    public final void c() {
                        if (!i.this.w) {
                            b bVar = (b) weakReference.get();
                            if (bVar != null) {
                                bVar.c();
                            } else {
                                i.this.g();
                            }
                        }
                    }

                    public final void d() {
                        StringBuilder stringBuilder = new StringBuilder("Successfully impressed ad for placement id: ");
                        stringBuilder.append(i.this.d);
                        Logger.a(InternalLogLevel.DEBUG, "InMobi", stringBuilder.toString());
                        if (!i.this.w) {
                            b bVar = (b) weakReference.get();
                            if (bVar != null) {
                                bVar.g();
                            } else {
                                i.this.g();
                            }
                        }
                    }

                    public final void e() {
                        StringBuilder stringBuilder = new StringBuilder("Ad interaction for placement id: ");
                        stringBuilder.append(i.this.d);
                        Logger.a(InternalLogLevel.DEBUG, "InMobi", stringBuilder.toString());
                        if (!i.this.w) {
                            b bVar = (b) weakReference.get();
                            if (bVar != null) {
                                bVar.a(new HashMap());
                            } else {
                                i.this.g();
                            }
                        }
                    }

                    public final void f() {
                        if (!i.this.w) {
                            StringBuilder stringBuilder = new StringBuilder("Ad dismissed for placement id: ");
                            stringBuilder.append(i.this.d);
                            Logger.a(InternalLogLevel.DEBUG, "InMobi", stringBuilder.toString());
                            i.this.s.post(new Runnable() {
                                public final void run() {
                                    i.this.c((b) weakReference.get());
                                }
                            });
                        }
                    }

                    public final void a(Map<String, String> map) {
                        if (!i.this.w) {
                            b bVar = (b) weakReference.get();
                            if (bVar != null) {
                                bVar.b(new HashMap(map));
                            } else {
                                i.this.g();
                            }
                        }
                    }

                    public final void g() {
                        if (!i.this.w) {
                            b bVar = (b) weakReference.get();
                            if (bVar != null) {
                                bVar.f();
                            } else {
                                i.this.g();
                            }
                        }
                    }

                    public final void h() {
                        if (!i.this.w) {
                            b bVar = (b) weakReference.get();
                            if (bVar != null) {
                                bVar.h();
                            } else {
                                i.this.g();
                            }
                        }
                    }

                    public final void a(String str, Map<String, Object> map) {
                        i.this.c(str, (Map) map);
                    }

                    public final void i() {
                        if (!i.this.w) {
                            b bVar = (b) weakReference.get();
                            if (bVar != null) {
                                bVar.j();
                            } else {
                                i.this.g();
                            }
                        }
                    }

                    public final void a(boolean z) {
                        if (!i.this.w) {
                            b bVar = (b) weakReference.get();
                            if (bVar != null) {
                                bVar.b(z);
                            } else {
                                i.this.g();
                            }
                        }
                    }
                });
                this.o = a;
                if (!(runnable2 == null || looper2 == null)) {
                    new Handler(looper2).post(runnable2);
                }
            } catch (JSONException e) {
                Throwable th = e;
                a("InternalError", weakReference);
                com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(th));
            } catch (Exception e2) {
                Exception exception = e2;
                new StringBuilder("Encountered unexpected error in loading ad markup into container: ").append(exception.getMessage());
                a("InternalError", weakReference);
                com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(exception));
            }
        }
    }

    public final void w() {
        if (!this.w && a() != null) {
            this.J.sendEmptyMessage(11);
        }
    }

    public final void y() {
        if (!this.w && a() != null) {
            this.J.sendEmptyMessage(12);
        }
    }

    public void a(RenderView renderView) {
        if (!this.w && a() != null) {
        }
    }

    public void b(RenderView renderView) {
        if (!this.w && a() != null) {
        }
    }

    /* JADX WARNING: Missing block: B:10:0x002c, code skipped:
            return;
     */
    public final void A() {
        /*
        r3 = this;
        r0 = r3.w;
        if (r0 != 0) goto L_0x002c;
    L_0x0004:
        r0 = r3.a();
        if (r0 != 0) goto L_0x000b;
    L_0x000a:
        goto L_0x002c;
    L_0x000b:
        r0 = 7;
        r1 = r3.a;
        if (r0 != r1) goto L_0x002b;
    L_0x0010:
        r0 = 3;
        r3.a = r0;
        r0 = r3.f();
        r1 = "AVFB";
        r2 = "";
        r3.a(r0, r1, r2);
        r0 = r3.f();
        if (r0 == 0) goto L_0x002b;
    L_0x0024:
        r0 = r3.f();
        r0.b();
    L_0x002b:
        return;
    L_0x002c:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.inmobi.ads.i.A():void");
    }

    public void c(RenderView renderView) {
        if (!this.w && a() != null) {
        }
    }

    public void d(RenderView renderView) {
        if (!this.w && a() != null) {
        }
    }

    public final void a(HashMap<Object, Object> hashMap) {
        if (!this.w && a() != null) {
            new StringBuilder("Ad reward action completed. Params:").append(hashMap.toString());
            if (f() != null) {
                f().b((Map) hashMap);
            }
        }
    }

    public final void b(HashMap<Object, Object> hashMap) {
        if (!this.w && a() != null) {
            new StringBuilder("Ad interaction. Params:").append(hashMap.toString());
            d("AdInteracted");
            if (f() != null) {
                f().a((Map) hashMap);
            }
        }
    }

    /* JADX WARNING: Missing block: B:8:0x0019, code skipped:
            return;
     */
    public final void B() {
        /*
        r1 = this;
        r0 = r1.w;
        if (r0 != 0) goto L_0x0019;
    L_0x0004:
        r0 = r1.a();
        if (r0 != 0) goto L_0x000b;
    L_0x000a:
        goto L_0x0019;
    L_0x000b:
        r0 = r1.f();
        if (r0 == 0) goto L_0x0018;
    L_0x0011:
        r0 = r1.f();
        r0.f();
    L_0x0018:
        return;
    L_0x0019:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.inmobi.ads.i.B():void");
    }

    /* Access modifiers changed, original: final */
    public final void a(boolean z, RenderView renderView) {
        boolean z2 = this.g.k.j;
        for (bq bqVar : this.L) {
            if (z2 && 3 == bqVar.a) {
                try {
                    AbstractAvidAdSession a = v.a(a(), z, (String) bqVar.b.get(com.til.colombia.android.vast.g.p), renderView);
                    if (a != null) {
                        bqVar.b.put("avidAdSession", a);
                        bqVar.b.put("deferred", Boolean.valueOf(z));
                    }
                } catch (Exception e) {
                    new StringBuilder("Setting up impression tracking for IAS encountered an unexpected error: ").append(e.getMessage());
                    com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
                }
            }
        }
    }

    public void H() {
        if (1 == this.a && this.q != null) {
            this.q.a(this);
        }
    }

    public void b(InMobiAdRequestStatus inMobiAdRequestStatus) {
        if (1 == this.a && this.q != null) {
            this.q.a(this, inMobiAdRequestStatus);
        }
    }

    static /* synthetic */ void N() {
        com.inmobi.commons.core.utilities.uid.c.a();
        com.inmobi.commons.core.utilities.uid.c.c();
    }
}
