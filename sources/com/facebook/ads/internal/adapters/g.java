package com.facebook.ads.internal.adapters;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.widget.RelativeLayout.LayoutParams;
import com.facebook.ads.AdError;
import com.facebook.ads.AudienceNetworkActivity;
import com.facebook.ads.CacheFlag;
import com.facebook.ads.internal.l.f;
import com.facebook.ads.internal.o.c;
import com.facebook.ads.internal.s.a.r;
import com.facebook.ads.internal.s.a.u;
import com.facebook.ads.internal.view.g.a;
import com.facebook.ads.internal.view.g.b.b;
import com.facebook.ads.internal.view.g.b.d;
import com.facebook.ads.internal.view.g.b.l;
import com.facebook.ads.internal.view.g.c.i;
import com.facebook.ads.internal.view.g.c.k;
import com.google.ads.mediation.inmobi.InMobiNetworkValues;
import com.google.android.exoplayer2.util.MimeTypes;
import com.til.colombia.android.internal.e;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class g extends o implements r<Bundle> {
    static final /* synthetic */ boolean e = true;
    @Nullable
    protected c a;
    @Nullable
    protected a b;
    @Nullable
    protected JSONObject c;
    @Nullable
    protected Context d;
    private final f<b> f = new f<b>() {
        public Class<b> a() {
            return b.class;
        }

        public void a(b bVar) {
            if (g.this.j != null) {
                g.this.j.d(g.this);
            }
        }
    };
    private final f<l> g = new f<l>() {
        public Class<l> a() {
            return l.class;
        }

        public void a(l lVar) {
            g.this.l = true;
            if (g.this.j != null) {
                g.this.j.a(g.this);
            }
        }
    };
    private final f<d> h = new f<d>() {
        public Class<d> a() {
            return d.class;
        }

        public void a(d dVar) {
            if (g.this.j != null) {
                g.this.j.a(g.this, AdError.internalError(2003));
            }
        }
    };
    private final f<com.facebook.ads.internal.view.g.b.a> i = new f<com.facebook.ads.internal.view.g.b.a>() {
        public Class<com.facebook.ads.internal.view.g.b.a> a() {
            return com.facebook.ads.internal.view.g.b.a.class;
        }

        public void a(com.facebook.ads.internal.view.g.b.a aVar) {
            if (g.this.j != null) {
                g.this.j.b(g.this);
            }
        }
    };
    @Nullable
    private com.facebook.ads.a.a j;
    @Nullable
    private String k;
    private boolean l = false;
    @Nullable
    private com.facebook.ads.internal.view.g.c m;
    @Nullable
    private String n;
    private boolean o = false;
    private com.facebook.ads.internal.f.b p;

    private void a(Context context, com.facebook.ads.a.a aVar, JSONObject jSONObject, c cVar, @Nullable Bundle bundle, EnumSet<CacheFlag> enumSet, int i) {
        Context context2 = context;
        JSONObject jSONObject2 = jSONObject;
        Bundle bundle2 = bundle;
        this.d = context2;
        this.j = aVar;
        c cVar2 = cVar;
        this.a = cVar2;
        this.c = jSONObject2;
        this.l = false;
        JSONObject jSONObject3 = jSONObject2.getJSONObject("video");
        this.n = jSONObject2.optString(e.P);
        this.b = new a(context2);
        this.b.setVideoProgressReportIntervalMs(i);
        a();
        this.b.getEventBus().a(this.f, this.g, this.h, this.i);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new com.facebook.ads.internal.b.b(this, 1.0E-7d, -1.0d, 0.001d, false) {
            final /* synthetic */ g a;

            /* Access modifiers changed, original: protected */
            public void a(boolean z, boolean z2, com.facebook.ads.internal.b.c cVar) {
                this.a.f();
            }
        });
        if (bundle2 != null) {
            this.m = new com.facebook.ads.internal.view.g.b(context2, cVar2, this.b, arrayList, this.n, bundle2.getBundle("logger"), null);
        } else {
            this.m = new com.facebook.ads.internal.view.g.b(context2, cVar2, this.b, (List) arrayList, this.n);
        }
        this.j.a(this, this.b);
        String str = (u.a(context) == u.a.MOBILE_INTERNET && jSONObject3.has("videoHDURL") && !jSONObject3.isNull("videoHDURL")) ? "videoHDURL" : AudienceNetworkActivity.VIDEO_URL;
        this.k = jSONObject3.getString(str);
        if (enumSet.contains(CacheFlag.VIDEO)) {
            this.p = new com.facebook.ads.internal.f.b(context2);
            this.p.a(this.k);
            this.p.a(new com.facebook.ads.internal.f.a() {
                public void a() {
                    g.this.b.setVideoURI(g.this.h());
                }

                public void b() {
                    g.this.b.setVideoURI(g.this.h());
                }
            });
            return;
        }
        this.b.setVideoURI(h());
    }

    private String h() {
        CharSequence charSequence = "";
        if (!(this.p == null || this.k == null)) {
            charSequence = this.p.b(this.k);
        }
        return TextUtils.isEmpty(charSequence) ? this.k : charSequence;
    }

    /* Access modifiers changed, original: protected */
    public void a() {
        if (!e && this.d == null) {
            throw new AssertionError();
        } else if (e || this.c != null) {
            LayoutParams layoutParams;
            com.facebook.ads.internal.view.g.a.b eVar;
            JSONObject optJSONObject = this.c.optJSONObject(MimeTypes.BASE_TYPE_TEXT);
            if (optJSONObject == null) {
                optJSONObject = new JSONObject();
            }
            this.b.a(new k(this.d));
            com.facebook.ads.internal.view.g.a.b lVar = new com.facebook.ads.internal.view.g.c.l(this.d);
            this.b.a(lVar);
            this.b.a(new com.facebook.ads.internal.view.g.c.d(lVar, com.facebook.ads.internal.view.g.c.d.a.INVSIBLE));
            this.b.a(new com.facebook.ads.internal.view.g.c.b(this.d));
            String b = b();
            if (b != null) {
                com.facebook.ads.internal.view.g.a.b cVar = new com.facebook.ads.internal.view.g.c.c(this.d, b);
                layoutParams = new LayoutParams(-2, -2);
                layoutParams.addRule(12);
                layoutParams.addRule(9);
                cVar.setLayoutParams(layoutParams);
                cVar.setCountdownTextColor(-1);
                this.b.a(cVar);
            }
            if (this.c.has(InMobiNetworkValues.CTA) && !this.c.isNull(InMobiNetworkValues.CTA)) {
                JSONObject jSONObject = this.c.getJSONObject(InMobiNetworkValues.CTA);
                eVar = new com.facebook.ads.internal.view.g.c.e(this.d, jSONObject.getString("url"), this.a, this.n, jSONObject.getString(MimeTypes.BASE_TYPE_TEXT));
                layoutParams = new LayoutParams(-2, -2);
                layoutParams.addRule(10);
                layoutParams.addRule(11);
                eVar.setLayoutParams(layoutParams);
                this.b.a(eVar);
            }
            b = d();
            if (!TextUtils.isEmpty(b)) {
                this.b.a(new com.facebook.ads.internal.view.g.c.a(this.d, b, this.n, new float[]{0.0f, 0.0f, 8.0f, 0.0f}));
            }
            int c = c();
            if (c > 0) {
                eVar = new i(this.d, c, optJSONObject.optString("skipAdIn", "Skip Ad in"), optJSONObject.optString("skipAd", "Skip Ad"));
                LayoutParams layoutParams2 = new LayoutParams(-2, -2);
                layoutParams2.addRule(12);
                layoutParams2.addRule(11);
                eVar.setLayoutParams(layoutParams2);
                eVar.setPadding(0, 0, 0, 30);
                this.b.a(eVar);
            }
        } else {
            throw new AssertionError();
        }
    }

    public final void a(Context context, com.facebook.ads.a.a aVar, c cVar, Bundle bundle, EnumSet<CacheFlag> enumSet) {
        try {
            JSONObject jSONObject = new JSONObject(bundle.getString("ad_response"));
            a(context, aVar, jSONObject, cVar, bundle, enumSet, jSONObject.optInt("video_time_polling_interval", 200));
        } catch (JSONException unused) {
            aVar.a((o) this, AdError.INTERNAL_ERROR);
        }
    }

    public final void a(Context context, com.facebook.ads.a.a aVar, Map<String, Object> map, c cVar, EnumSet<CacheFlag> enumSet) {
        try {
            JSONObject jSONObject = (JSONObject) map.get("data");
            com.facebook.ads.internal.j.d dVar = (com.facebook.ads.internal.j.d) map.get("definition");
            a(context, aVar, jSONObject, cVar, null, enumSet, dVar == null ? 200 : dVar.k());
        } catch (JSONException unused) {
            aVar.a((o) this, AdError.INTERNAL_ERROR);
        }
    }

    /* Access modifiers changed, original: protected */
    public String b() {
        if (e || this.c != null) {
            try {
                JSONObject jSONObject = this.c.getJSONObject("capabilities");
                if (!jSONObject.has("countdown") || jSONObject.isNull("countdown")) {
                    return null;
                }
                jSONObject = jSONObject.getJSONObject("countdown");
                if (jSONObject.has("format")) {
                    return jSONObject.optString("format");
                }
                return null;
            } catch (Exception e) {
                Log.w(String.valueOf(g.class), "Invalid JSON", e);
                return null;
            }
        }
        throw new AssertionError();
    }

    /* Access modifiers changed, original: protected */
    public int c() {
        if (e || this.c != null) {
            try {
                JSONObject jSONObject = this.c.getJSONObject("capabilities");
                if (!jSONObject.has("skipButton") || jSONObject.isNull("skipButton")) {
                    return -1;
                }
                jSONObject = jSONObject.getJSONObject("skipButton");
                if (jSONObject.has("skippableSeconds")) {
                    return jSONObject.getInt("skippableSeconds");
                }
                return -1;
            } catch (Exception e) {
                Log.w(String.valueOf(g.class), "Invalid JSON", e);
                return -1;
            }
        }
        throw new AssertionError();
    }

    /* Access modifiers changed, original: protected */
    @Nullable
    public String d() {
        if (e || this.c != null) {
            try {
                JSONObject jSONObject = this.c.getJSONObject("capabilities");
                if (!jSONObject.has("adChoices") || jSONObject.isNull("adChoices")) {
                    return null;
                }
                jSONObject = jSONObject.getJSONObject("adChoices");
                if (jSONObject.has("url")) {
                    return jSONObject.getString("url");
                }
                return null;
            } catch (Exception e) {
                Log.w(String.valueOf(g.class), "Invalid JSON", e);
                return null;
            }
        }
        throw new AssertionError();
    }

    public boolean e() {
        if (!this.l || this.b == null) {
            return false;
        }
        if (this.m.j() > 0) {
            this.b.a(this.m.j());
        }
        this.b.a(com.facebook.ads.internal.view.g.a.a.AUTO_STARTED);
        return true;
    }

    /* Access modifiers changed, original: protected */
    public void f() {
        if (this.a != null && !this.o) {
            this.o = true;
            this.a.a(this.n, new HashMap());
            if (this.j != null) {
                this.j.c(this);
            }
        }
    }

    public Bundle g() {
        if (this.m == null || this.c == null || this.b == null || this.b.getState() == com.facebook.ads.internal.view.g.d.d.IDLE) {
            return null;
        }
        Bundle bundle = new Bundle();
        bundle.putBundle("logger", this.m.g());
        bundle.putString("ad_response", this.c.toString());
        return bundle;
    }

    public void onDestroy() {
        if (this.b != null) {
            this.b.g();
            this.b.l();
        }
        this.j = null;
        this.a = null;
        this.k = null;
        this.l = false;
        this.n = null;
        this.b = null;
        this.m = null;
        this.c = null;
        this.d = null;
        this.o = false;
    }
}
