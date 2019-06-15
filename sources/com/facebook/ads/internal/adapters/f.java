package com.facebook.ads.internal.adapters;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.Log;
import com.facebook.ads.AdError;
import com.facebook.ads.AudienceNetworkActivity;
import com.facebook.ads.internal.a.e;
import com.facebook.ads.internal.j.d;
import com.facebook.ads.internal.o.c;
import com.facebook.ads.internal.protocol.AdPlacementType;
import com.facebook.ads.internal.view.c.a;
import com.facebook.ads.internal.view.c.a.b;
import java.lang.ref.WeakReference;
import java.util.Map;
import org.json.JSONObject;

public class f implements AdAdapter {
    private static final String a = "f";
    @Nullable
    private b b;
    @Nullable
    private a c;
    private n d;
    private BannerAdapterListener e;
    private Map<String, Object> f;
    @Nullable
    private c g;
    private Context h;
    private long i;
    private com.facebook.ads.internal.l.a.a j;

    private void a(d dVar) {
        this.i = 0;
        this.j = null;
        final m a = m.a((JSONObject) this.f.get("data"));
        if (e.a(this.h, a, this.g)) {
            this.e.onBannerError(this, AdError.internalError(2006));
            return;
        }
        this.b = new a.c() {
            public void a() {
                f.this.d.b();
            }

            public void a(int i) {
                if (i == 0 && f.this.i > 0 && f.this.j != null) {
                    com.facebook.ads.internal.l.b.a(com.facebook.ads.internal.l.a.a(f.this.i, f.this.j, a.g()));
                    f.this.i = 0;
                    f.this.j = null;
                }
            }

            public void a(String str, Map<String, String> map) {
                Uri parse = Uri.parse(str);
                if ("fbad".equals(parse.getScheme()) && com.facebook.ads.internal.a.c.a(parse.getAuthority()) && f.this.e != null) {
                    f.this.e.onBannerAdClicked(f.this);
                }
                com.facebook.ads.internal.a.b a = com.facebook.ads.internal.a.c.a(f.this.h, f.this.g, a.c(), parse, map);
                if (a != null) {
                    try {
                        f.this.j = a.a();
                        f.this.i = System.currentTimeMillis();
                        a.b();
                    } catch (Exception e) {
                        Log.e(f.a, "Error executing action", e);
                    }
                }
            }

            public void b() {
                if (f.this.d != null) {
                    f.this.d.a();
                }
            }
        };
        this.c = new a(this.h, new WeakReference(this.b), dVar.f());
        this.c.a(dVar.h(), dVar.i());
        this.d = new n(this.h, this.g, this.c, this.c.getViewabilityChecker(), new c() {
            public void a() {
                if (f.this.e != null) {
                    f.this.e.onBannerLoggingImpression(f.this);
                }
            }
        });
        this.d.a(a);
        this.c.loadDataWithBaseURL(com.facebook.ads.internal.s.c.b.a(), a.d(), "text/html", AudienceNetworkActivity.WEBVIEW_ENCODING, null);
        if (this.e != null) {
            this.e.onBannerAdLoaded(this, this.c);
        }
    }

    public void a(Context context, c cVar, com.facebook.ads.internal.protocol.d dVar, BannerAdapterListener bannerAdapterListener, Map<String, Object> map) {
        this.h = context;
        this.g = cVar;
        this.e = bannerAdapterListener;
        this.f = map;
        a((d) this.f.get("definition"));
    }

    public final AdPlacementType getPlacementType() {
        return AdPlacementType.BANNER;
    }

    public void onDestroy() {
        if (this.c != null) {
            this.c.destroy();
            this.c = null;
            this.b = null;
        }
    }
}
