package com.facebook.ads.internal.adapters;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import com.facebook.ads.AdError;
import com.facebook.ads.AudienceNetworkActivity;
import com.facebook.ads.CacheFlag;
import com.facebook.ads.InterstitialAdActivity;
import com.facebook.ads.internal.a.e;
import com.facebook.ads.internal.adapters.a.g;
import com.facebook.ads.internal.j.d;
import com.facebook.ads.internal.o.c;
import com.facebook.ads.internal.protocol.AdPlacementType;
import com.facebook.ads.internal.s.d.b;
import com.google.android.exoplayer2.C;
import java.util.EnumSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.json.JSONObject;

public class h implements AdAdapter {
    private static final ConcurrentMap<String, com.facebook.ads.internal.view.a> a = new ConcurrentHashMap();
    private final String b = UUID.randomUUID().toString();
    private String c;
    private long d;
    private Context e;
    private p f;
    private InterstitialAdapterListener g;
    private boolean h = false;
    private m i;
    private a j = a.UNSPECIFIED;
    private g k;
    private com.facebook.ads.internal.settings.a.a l;
    private boolean m;

    public enum a {
        UNSPECIFIED,
        VERTICAL,
        HORIZONTAL;

        public static a a(int i) {
            return i == 0 ? UNSPECIFIED : i == 2 ? HORIZONTAL : VERTICAL;
        }
    }

    public static com.facebook.ads.internal.view.a a(String str) {
        return (com.facebook.ads.internal.view.a) a.get(str);
    }

    public static void a(com.facebook.ads.internal.view.a aVar) {
        for (Entry entry : a.entrySet()) {
            if (entry.getValue() == aVar) {
                a.remove(entry.getKey());
            }
        }
    }

    private int b() {
        int rotation = ((WindowManager) this.e.getSystemService("window")).getDefaultDisplay().getRotation();
        if (this.j == a.UNSPECIFIED) {
            return -1;
        }
        if (this.j != a.HORIZONTAL) {
            return rotation != 2 ? 1 : 9;
        } else {
            switch (rotation) {
                case 2:
                case 3:
                    return 8;
                default:
                    return 0;
            }
        }
    }

    private static void b(String str, com.facebook.ads.internal.view.a aVar) {
        a.put(str, aVar);
    }

    public void a(final Context context, InterstitialAdapterListener interstitialAdapterListener, Map<String, Object> map, c cVar, final EnumSet<CacheFlag> enumSet) {
        this.e = context;
        this.g = interstitialAdapterListener;
        this.c = (String) map.get(AudienceNetworkActivity.PLACEMENT_ID);
        this.d = ((Long) map.get(AudienceNetworkActivity.REQUEST_TIME)).longValue();
        JSONObject jSONObject = (JSONObject) map.get("data");
        d dVar = (d) map.get("definition");
        if (jSONObject.has("markup")) {
            this.l = com.facebook.ads.internal.settings.a.a.INTERSTITIAL_WEB_VIEW;
            this.i = m.a(jSONObject);
            if (e.a(context, this.i, cVar)) {
                interstitialAdapterListener.onInterstitialError(this, AdError.internalError(2006));
                return;
            }
            this.f = new p(context, this.b, this, this.g);
            this.f.a();
            Map f = this.i.f();
            if (f.containsKey("orientation")) {
                this.j = a.a(Integer.parseInt((String) f.get("orientation")));
            }
            this.h = true;
            if (this.g != null) {
                this.g.onInterstitialAdLoaded(this);
            }
        } else if (jSONObject.has("video")) {
            this.l = com.facebook.ads.internal.settings.a.a.INTERSTITIAL_OLD_NATIVE_VIDEO;
            this.f = new p(context, this.b, this, this.g);
            this.f.a();
            final i iVar = new i();
            iVar.a(context, (com.facebook.ads.a.a) new com.facebook.ads.a.a() {
                public void a(o oVar) {
                    h.this.h = true;
                    if (h.this.g != null) {
                        h.this.g.onInterstitialAdLoaded(h.this);
                    }
                }

                public void a(o oVar, View view) {
                    h.this.j = iVar.i();
                    h.b(h.this.b, iVar);
                }

                public void a(o oVar, AdError adError) {
                    iVar.j();
                    h.this.g.onInterstitialError(h.this, adError);
                }

                public void b(o oVar) {
                    h.this.g.onInterstitialAdClicked(h.this, "", true);
                }

                public void c(o oVar) {
                    h.this.g.onInterstitialLoggingImpression(h.this);
                }

                public void d(o oVar) {
                }
            }, (Map) map, cVar, (EnumSet) enumSet);
        } else {
            this.k = g.a(jSONObject, context);
            if (dVar != null) {
                this.k.a(dVar.k());
            }
            if (this.k.d().size() == 0) {
                this.g.onInterstitialError(this, AdError.internalError(2006));
                com.facebook.ads.internal.s.d.a.a(context, "api", b.j, new Exception("Internal Error 2006 without a valid AdInfo."));
                return;
            }
            com.facebook.ads.internal.f.b bVar;
            com.facebook.ads.internal.f.a anonymousClass2;
            this.f = new p(context, this.b, this, this.g);
            this.f.a();
            com.facebook.ads.internal.adapters.a.b c;
            if (jSONObject.has("carousel")) {
                this.l = com.facebook.ads.internal.settings.a.a.INTERSTITIAL_NATIVE_CAROUSEL;
                bVar = new com.facebook.ads.internal.f.b(context);
                bVar.a(this.k.a().b(), -1, -1);
                List<com.facebook.ads.internal.adapters.a.h> d = this.k.d();
                boolean contains = enumSet.contains(CacheFlag.VIDEO);
                for (com.facebook.ads.internal.adapters.a.h hVar : d) {
                    bVar.a(hVar.c().g(), hVar.c().i(), hVar.c().h());
                    if (contains && !TextUtils.isEmpty(hVar.c().a())) {
                        bVar.a(hVar.c().g());
                    }
                }
                anonymousClass2 = new com.facebook.ads.internal.f.a() {
                    private void a(boolean z) {
                        int contains = enumSet.contains(CacheFlag.NONE) ^ 1;
                        if (z || !com.facebook.ads.internal.n.a.E(context)) {
                            h hVar = h.this;
                            z = z && contains != 0;
                            hVar.m = z;
                            h.this.h = true;
                            h.this.g.onInterstitialAdLoaded(h.this);
                            return;
                        }
                        h.this.g.onInterstitialError(h.this, AdError.CACHE_ERROR);
                    }

                    public void a() {
                        a(true);
                    }

                    public void b() {
                        if (com.facebook.ads.internal.n.a.D(context)) {
                            com.facebook.ads.internal.s.d.a.a(context, "cache", b.H, new Exception("Interstitial carousel cache failed"));
                        }
                        a(false);
                    }
                };
            } else if (jSONObject.has("video_url")) {
                this.l = com.facebook.ads.internal.settings.a.a.INTERSTITIAL_NATIVE_VIDEO;
                bVar = new com.facebook.ads.internal.f.b(context);
                c = ((com.facebook.ads.internal.adapters.a.h) this.k.d().get(0)).c();
                bVar.a(c.g(), c.i(), c.h());
                bVar.a(this.k.a().b(), -1, -1);
                if (enumSet.contains(CacheFlag.VIDEO)) {
                    bVar.a(c.a());
                }
                anonymousClass2 = new com.facebook.ads.internal.f.a() {
                    private void a(boolean z) {
                        if (z || !com.facebook.ads.internal.n.a.E(context)) {
                            h hVar = h.this;
                            z = z && enumSet.contains(CacheFlag.VIDEO);
                            hVar.m = z;
                            h.this.h = true;
                            h.this.g.onInterstitialAdLoaded(h.this);
                            return;
                        }
                        h.this.g.onInterstitialError(h.this, AdError.CACHE_ERROR);
                    }

                    public void a() {
                        a(true);
                    }

                    public void b() {
                        if (com.facebook.ads.internal.n.a.D(context)) {
                            com.facebook.ads.internal.s.d.a.a(context, "cache", b.J, new Exception("Interstitial video cache failed"));
                        }
                        a(false);
                    }
                };
            } else {
                this.l = com.facebook.ads.internal.settings.a.a.INTERSTITIAL_NATIVE_IMAGE;
                bVar = new com.facebook.ads.internal.f.b(context);
                c = ((com.facebook.ads.internal.adapters.a.h) this.k.d().get(0)).c();
                bVar.a(c.g(), c.i(), c.h());
                bVar.a(this.k.a().b(), -1, -1);
                anonymousClass2 = new com.facebook.ads.internal.f.a() {
                    private void a(boolean z) {
                        if (z || !com.facebook.ads.internal.n.a.E(context)) {
                            h.this.h = true;
                            h.this.g.onInterstitialAdLoaded(h.this);
                            return;
                        }
                        h.this.g.onInterstitialError(h.this, AdError.CACHE_ERROR);
                    }

                    public void a() {
                        a(true);
                    }

                    public void b() {
                        if (com.facebook.ads.internal.n.a.D(context)) {
                            com.facebook.ads.internal.s.d.a.a(context, "cache", b.I, new Exception("Interstitial image cache failed"));
                        }
                        a(false);
                    }
                };
            }
            bVar.a(anonymousClass2);
        }
    }

    public boolean a() {
        if (this.h) {
            Intent intent = new Intent(this.e, AudienceNetworkActivity.class);
            intent.putExtra(AudienceNetworkActivity.PREDEFINED_ORIENTATION_KEY, b());
            intent.putExtra(AudienceNetworkActivity.AUDIENCE_NETWORK_UNIQUE_ID_EXTRA, this.b);
            intent.putExtra(AudienceNetworkActivity.PLACEMENT_ID, this.c);
            intent.putExtra(AudienceNetworkActivity.REQUEST_TIME, this.d);
            intent.putExtra(AudienceNetworkActivity.VIEW_TYPE, this.l);
            intent.putExtra(AudienceNetworkActivity.USE_CACHE, this.m);
            if (this.k != null) {
                intent.putExtra("ad_data_bundle", this.k);
            } else if (this.i != null) {
                this.i.a(intent);
            }
            intent.addFlags(C.ENCODING_PCM_MU_LAW);
            try {
                this.e.startActivity(intent);
            } catch (ActivityNotFoundException unused) {
                intent.setClass(this.e, InterstitialAdActivity.class);
                this.e.startActivity(intent);
            }
            return true;
        }
        if (this.g != null) {
            this.g.onInterstitialError(this, AdError.SHOW_CALLED_BEFORE_LOAD_ERROR);
        }
        return false;
    }

    public final AdPlacementType getPlacementType() {
        return AdPlacementType.INTERSTITIAL;
    }

    public void onDestroy() {
        if (this.f != null) {
            this.f.b();
        }
    }
}
