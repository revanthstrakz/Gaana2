package com.facebook.ads.internal;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Looper;
import android.security.NetworkSecurityPolicy;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import com.facebook.ads.AdError;
import com.facebook.ads.AdSettings;
import com.facebook.ads.AdSettings.TestAdType;
import com.facebook.ads.AudienceNetworkActivity;
import com.facebook.ads.CacheFlag;
import com.facebook.ads.NativeAdBase;
import com.facebook.ads.RewardData;
import com.facebook.ads.internal.adapters.AdAdapter;
import com.facebook.ads.internal.adapters.BannerAdapterListener;
import com.facebook.ads.internal.adapters.InterstitialAdapterListener;
import com.facebook.ads.internal.adapters.f;
import com.facebook.ads.internal.adapters.h;
import com.facebook.ads.internal.adapters.j;
import com.facebook.ads.internal.adapters.o;
import com.facebook.ads.internal.adapters.r;
import com.facebook.ads.internal.adapters.t;
import com.facebook.ads.internal.adapters.u;
import com.facebook.ads.internal.p.d;
import com.facebook.ads.internal.protocol.AdErrorType;
import com.facebook.ads.internal.protocol.AdPlacementType;
import com.facebook.ads.internal.protocol.e;
import com.facebook.ads.internal.protocol.g;
import com.facebook.ads.internal.s.a.m;
import com.facebook.ads.internal.s.a.q;
import com.facebook.ads.internal.s.a.z;
import com.google.android.exoplayer2.C;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DisplayAdController implements com.facebook.ads.internal.q.c.a {
    private static final String b = "DisplayAdController";
    private static final Handler h = new Handler(Looper.getMainLooper());
    private static boolean i;
    private final com.facebook.ads.internal.o.c A;
    private final EnumSet<CacheFlag> B;
    private String C;
    private d D;
    protected com.facebook.ads.internal.adapters.a a;
    private final Context c;
    private final String d;
    private final AdPlacementType e;
    private final com.facebook.ads.internal.q.c f;
    private final Handler g;
    private final Runnable j;
    private final Runnable k;
    private volatile boolean l;
    private boolean m;
    private volatile boolean n;
    private AdAdapter o;
    private AdAdapter p;
    private View q;
    private com.facebook.ads.internal.j.c r;
    private com.facebook.ads.internal.q.b s;
    private e t;
    private com.facebook.ads.internal.protocol.d u;
    private int v;
    private boolean w;
    private int x;
    private final c y;
    private boolean z;

    private static final class a extends z<DisplayAdController> {
        public a(DisplayAdController displayAdController) {
            super(displayAdController);
        }

        public void run() {
            DisplayAdController displayAdController = (DisplayAdController) a();
            if (displayAdController != null) {
                displayAdController.l = false;
                displayAdController.c(null);
            }
        }
    }

    private static final class b extends z<DisplayAdController> {
        public b(DisplayAdController displayAdController) {
            super(displayAdController);
        }

        public void run() {
            DisplayAdController displayAdController = (DisplayAdController) a();
            if (displayAdController != null) {
                displayAdController.n();
            }
        }
    }

    private class c extends BroadcastReceiver {
        private c() {
        }

        /* synthetic */ c(DisplayAdController displayAdController, AnonymousClass1 anonymousClass1) {
            this();
        }

        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if ("android.intent.action.SCREEN_OFF".equals(action)) {
                DisplayAdController.this.o();
                return;
            }
            if ("android.intent.action.SCREEN_ON".equals(action)) {
                DisplayAdController.this.n();
            }
        }
    }

    static {
        com.facebook.ads.internal.s.a.d.a();
    }

    public DisplayAdController(Context context, String str, e eVar, AdPlacementType adPlacementType, com.facebook.ads.internal.protocol.d dVar, int i, boolean z) {
        this(context, str, eVar, adPlacementType, dVar, i, z, EnumSet.of(CacheFlag.NONE));
    }

    public DisplayAdController(Context context, String str, e eVar, AdPlacementType adPlacementType, com.facebook.ads.internal.protocol.d dVar, int i, boolean z, EnumSet<CacheFlag> enumSet) {
        this.g = new Handler();
        this.w = false;
        this.x = -1;
        this.c = context.getApplicationContext();
        this.d = str;
        this.t = eVar;
        this.e = adPlacementType;
        this.u = dVar;
        this.v = i;
        this.y = new c(this, null);
        this.B = enumSet;
        this.f = new com.facebook.ads.internal.q.c(this.c);
        this.f.a((com.facebook.ads.internal.q.c.a) this);
        this.j = new a(this);
        this.k = new b(this);
        this.m = this.m;
        i();
        try {
            CookieManager.getInstance();
            if (VERSION.SDK_INT < 21) {
                CookieSyncManager.createInstance(this.c);
            }
        } catch (Exception e) {
            Log.w(b, "Failed to initialize CookieManager.", e);
        }
        com.facebook.ads.internal.k.a.a(this.c).a();
        this.A = com.facebook.ads.internal.o.d.a(this.c);
    }

    private Map<String, String> a(long j) {
        HashMap hashMap = new HashMap();
        hashMap.put("delay", String.valueOf(System.currentTimeMillis() - j));
        return hashMap;
    }

    private void a(AdAdapter adAdapter) {
        if (adAdapter != null) {
            adAdapter.onDestroy();
        }
    }

    private void a(final f fVar, com.facebook.ads.internal.j.c cVar, Map<String, Object> map) {
        final AnonymousClass8 anonymousClass8 = new Runnable() {
            public void run() {
                DisplayAdController.this.a(fVar);
                DisplayAdController.this.l();
            }
        };
        this.g.postDelayed(anonymousClass8, (long) cVar.a().j());
        fVar.a(this.c, this.A, this.u, new BannerAdapterListener() {
            public void onBannerAdClicked(f fVar) {
                DisplayAdController.this.a.a();
            }

            public void onBannerAdLoaded(f fVar, View view) {
                if (fVar == DisplayAdController.this.o) {
                    DisplayAdController.this.g.removeCallbacks(anonymousClass8);
                    AdAdapter f = DisplayAdController.this.p;
                    DisplayAdController.this.p = fVar;
                    DisplayAdController.this.q = view;
                    if (DisplayAdController.this.n) {
                        DisplayAdController.this.a.a(view);
                        DisplayAdController.this.a(f);
                        return;
                    }
                    DisplayAdController.this.a.a((AdAdapter) fVar);
                }
            }

            public void onBannerError(f fVar, AdError adError) {
                if (fVar == DisplayAdController.this.o) {
                    DisplayAdController.this.g.removeCallbacks(anonymousClass8);
                    DisplayAdController.this.a((AdAdapter) fVar);
                    DisplayAdController.this.l();
                }
            }

            public void onBannerLoggingImpression(f fVar) {
                DisplayAdController.this.a.b();
            }
        }, map);
    }

    private void a(final h hVar, com.facebook.ads.internal.j.c cVar, Map<String, Object> map) {
        final AnonymousClass10 anonymousClass10 = new Runnable() {
            public void run() {
                DisplayAdController.this.a(hVar);
                DisplayAdController.this.l();
            }
        };
        this.g.postDelayed(anonymousClass10, (long) cVar.a().j());
        hVar.a(this.c, new InterstitialAdapterListener() {
            public void onInterstitialActivityDestroyed() {
                DisplayAdController.this.a.f();
            }

            public void onInterstitialAdClicked(h hVar, String str, boolean z) {
                DisplayAdController.this.a.a();
                int isEmpty = TextUtils.isEmpty(str) ^ 1;
                if (z && isEmpty != 0) {
                    Intent intent = new Intent("android.intent.action.VIEW");
                    if (!(DisplayAdController.this.s.b instanceof Activity)) {
                        intent.addFlags(C.ENCODING_PCM_MU_LAW);
                    }
                    intent.setData(Uri.parse(str));
                    DisplayAdController.this.s.b.startActivity(intent);
                }
            }

            public void onInterstitialAdDismissed(h hVar) {
                DisplayAdController.this.a.e();
            }

            public void onInterstitialAdDisplayed(h hVar) {
                DisplayAdController.this.a.d();
            }

            public void onInterstitialAdLoaded(h hVar) {
                if (hVar == DisplayAdController.this.o) {
                    if (hVar == null) {
                        com.facebook.ads.internal.s.d.a.a(DisplayAdController.this.c, "api", com.facebook.ads.internal.s.d.b.b, new com.facebook.ads.internal.protocol.b(AdErrorType.NO_ADAPTER_ON_LOAD, "Adapter is null on loadInterstitialAd"));
                        onInterstitialError(hVar, AdError.internalError(2004));
                        return;
                    }
                    DisplayAdController.this.g.removeCallbacks(anonymousClass10);
                    DisplayAdController.this.p = hVar;
                    DisplayAdController.this.a.a((AdAdapter) hVar);
                    DisplayAdController.this.n();
                }
            }

            public void onInterstitialError(h hVar, AdError adError) {
                if (hVar == DisplayAdController.this.o) {
                    DisplayAdController.this.g.removeCallbacks(anonymousClass10);
                    DisplayAdController.this.a((AdAdapter) hVar);
                    DisplayAdController.this.l();
                    DisplayAdController.this.a.a(new com.facebook.ads.internal.protocol.a(adError.getErrorCode(), adError.getErrorMessage()));
                }
            }

            public void onInterstitialLoggingImpression(h hVar) {
                DisplayAdController.this.a.b();
            }
        }, map, this.A, this.B);
    }

    private void a(j jVar, com.facebook.ads.internal.j.c cVar, com.facebook.ads.internal.j.a aVar, Map<String, Object> map) {
        final j jVar2 = jVar;
        final long currentTimeMillis = System.currentTimeMillis();
        final com.facebook.ads.internal.j.a aVar2 = aVar;
        AnonymousClass12 anonymousClass12 = new Runnable() {
            public void run() {
                DisplayAdController.this.a(jVar2);
                Map a = DisplayAdController.this.a(currentTimeMillis);
                a.put("error", "-1");
                a.put("msg", "timeout");
                DisplayAdController.this.a(aVar2.a(com.facebook.ads.internal.j.e.REQUEST), a);
                DisplayAdController.this.l();
            }
        };
        this.g.postDelayed(anonymousClass12, (long) cVar.a().j());
        final AnonymousClass12 anonymousClass122 = anonymousClass12;
        jVar.a(this.c, new r() {
            boolean a = false;
            boolean b = false;
            boolean c = false;

            public void a(j jVar) {
                if (jVar == DisplayAdController.this.o) {
                    DisplayAdController.this.g.removeCallbacks(anonymousClass122);
                    DisplayAdController.this.p = jVar;
                    DisplayAdController.this.a.a((AdAdapter) jVar);
                    if (!this.a) {
                        this.a = true;
                        DisplayAdController.this.a(aVar2.a(com.facebook.ads.internal.j.e.REQUEST), DisplayAdController.this.a(currentTimeMillis));
                    }
                }
            }

            public void a(j jVar, com.facebook.ads.internal.protocol.a aVar) {
                if (jVar == DisplayAdController.this.o) {
                    DisplayAdController.this.g.removeCallbacks(anonymousClass122);
                    DisplayAdController.this.a((AdAdapter) jVar);
                    if (!this.a) {
                        this.a = true;
                        Map a = DisplayAdController.this.a(currentTimeMillis);
                        a.put("error", String.valueOf(aVar.a().getErrorCode()));
                        a.put("msg", String.valueOf(aVar.b()));
                        DisplayAdController.this.a(aVar2.a(com.facebook.ads.internal.j.e.REQUEST), a);
                    }
                    DisplayAdController.this.l();
                }
            }

            public void b(j jVar) {
                if (!this.b) {
                    this.b = true;
                    DisplayAdController.this.a(aVar2.a(com.facebook.ads.internal.j.e.IMPRESSION), null);
                }
            }

            public void c(j jVar) {
                if (!this.c) {
                    this.c = true;
                    DisplayAdController.this.a(aVar2.a(com.facebook.ads.internal.j.e.CLICK), null);
                }
                if (DisplayAdController.this.a != null) {
                    DisplayAdController.this.a.a();
                }
            }
        }, this.A, map, NativeAdBase.getViewTraversalPredicate());
    }

    private void a(o oVar, com.facebook.ads.internal.j.c cVar, Map<String, Object> map) {
        oVar.a(this.c, new com.facebook.ads.a.a() {
            public void a(o oVar) {
                DisplayAdController.this.p = oVar;
                DisplayAdController.this.n = false;
                DisplayAdController.this.a.a((AdAdapter) oVar);
            }

            public void a(o oVar, View view) {
                DisplayAdController.this.a.a(view);
            }

            public void a(o oVar, AdError adError) {
                DisplayAdController.this.a.a(new com.facebook.ads.internal.protocol.a(adError.getErrorCode(), adError.getErrorMessage()));
            }

            public void b(o oVar) {
                DisplayAdController.this.a.a();
            }

            public void c(o oVar) {
                DisplayAdController.this.a.b();
            }

            public void d(o oVar) {
                DisplayAdController.this.a.c();
            }
        }, map, this.A, this.B);
    }

    private void a(t tVar, com.facebook.ads.internal.j.c cVar, Map<String, Object> map) {
        tVar.a(this.c, new u() {
            public void a() {
                DisplayAdController.this.a.h();
            }

            public void a(t tVar) {
                DisplayAdController.this.p = tVar;
                DisplayAdController.this.a.a((AdAdapter) tVar);
            }

            public void a(t tVar, AdError adError) {
                DisplayAdController.this.a.a(new com.facebook.ads.internal.protocol.a(AdErrorType.INTERNAL_ERROR, null));
                DisplayAdController.this.a((AdAdapter) tVar);
                DisplayAdController.this.l();
            }

            public void b() {
                DisplayAdController.this.a.k();
            }

            public void b(t tVar) {
                DisplayAdController.this.a.a();
            }

            public void c(t tVar) {
                DisplayAdController.this.a.b();
            }

            public void d(t tVar) {
                DisplayAdController.this.a.g();
            }

            public void e(t tVar) {
                DisplayAdController.this.a.i();
            }

            public void f(t tVar) {
                DisplayAdController.this.a.j();
            }
        }, map, this.w);
    }

    private void a(List<String> list, Map<String, String> map) {
        if (list != null && !list.isEmpty()) {
            for (String str : list) {
                new com.facebook.ads.internal.s.c.e(this.c, map).execute(new String[]{str});
            }
        }
    }

    private void c(String str) {
        try {
            this.s = new com.facebook.ads.internal.q.b(this.c, new com.facebook.ads.internal.k.c(this.c, false), this.d, this.u != null ? new m(this.u.b(), this.u.a()) : null, this.t, AdSettings.getTestAdType() != TestAdType.DEFAULT ? AdSettings.getTestAdType().getAdTypeString() : null, this.v, AdSettings.isTestMode(this.c), AdSettings.isChildDirected(), new g(this.c, str, this.d, this.t), q.a(com.facebook.ads.internal.n.a.t(this.c)), this.C);
            this.f.a(this.s);
        } catch (com.facebook.ads.internal.protocol.b e) {
            a(com.facebook.ads.internal.protocol.a.a(e));
        }
    }

    private void i() {
        if (!this.m) {
            IntentFilter intentFilter = new IntentFilter("android.intent.action.SCREEN_ON");
            intentFilter.addAction("android.intent.action.SCREEN_OFF");
            this.c.registerReceiver(this.y, intentFilter);
            this.z = true;
        }
    }

    private void j() {
        if (this.z) {
            try {
                this.c.unregisterReceiver(this.y);
                this.z = false;
            } catch (Exception e) {
                com.facebook.ads.internal.l.b.a(com.facebook.ads.internal.l.a.a(e, "Error unregistering screen state receiever"));
            }
        }
    }

    private AdPlacementType k() {
        return this.e != null ? this.e : this.u == null ? AdPlacementType.NATIVE : this.u == com.facebook.ads.internal.protocol.d.INTERSTITIAL ? AdPlacementType.INTERSTITIAL : AdPlacementType.BANNER;
    }

    private synchronized void l() {
        h.post(new Runnable() {
            public void run() {
                try {
                    DisplayAdController.this.m();
                } catch (Exception e) {
                    ThrowableExtension.printStackTrace(e);
                }
            }
        });
    }

    private void m() {
        this.o = null;
        com.facebook.ads.internal.j.c cVar = this.r;
        com.facebook.ads.internal.j.a e = cVar.e();
        if (e == null) {
            this.a.a(com.facebook.ads.internal.protocol.a.a(AdErrorType.NO_FILL, ""));
            n();
            return;
        }
        String a = e.a();
        AdAdapter a2 = com.facebook.ads.internal.adapters.d.a(cVar.a().b());
        if (a2 == null) {
            String str = b;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Adapter does not exist: ");
            stringBuilder.append(a);
            Log.e(str, stringBuilder.toString());
            l();
        } else if (k() != a2.getPlacementType()) {
            this.a.a(com.facebook.ads.internal.protocol.a.a(AdErrorType.INTERNAL_ERROR, ""));
        } else {
            this.o = a2;
            Map hashMap = new HashMap();
            com.facebook.ads.internal.j.d a3 = cVar.a();
            hashMap.put("data", e.b());
            hashMap.put("definition", a3);
            hashMap.put(AudienceNetworkActivity.PLACEMENT_ID, this.d);
            hashMap.put(AudienceNetworkActivity.REQUEST_TIME, Long.valueOf(a3.a()));
            if (this.s == null) {
                this.a.a(com.facebook.ads.internal.protocol.a.a(AdErrorType.UNKNOWN_ERROR, "environment is empty"));
                return;
            }
            switch (a2.getPlacementType()) {
                case INTERSTITIAL:
                    a((h) a2, cVar, hashMap);
                    return;
                case BANNER:
                    a((f) a2, cVar, hashMap);
                    return;
                case NATIVE:
                case NATIVE_BANNER:
                    a((j) a2, cVar, e, hashMap);
                    return;
                case INSTREAM:
                    a((o) a2, cVar, hashMap);
                    return;
                case REWARDED_VIDEO:
                    a((t) a2, cVar, hashMap);
                    return;
                default:
                    Log.e(b, "attempt unexpected adapter type");
                    return;
            }
        }
    }

    private void n() {
        if (!this.m && !this.l && AnonymousClass4.a[k().ordinal()] == 1) {
            if (!com.facebook.ads.internal.s.e.a.a(this.c)) {
                this.g.postDelayed(this.k, 1000);
            }
            long c = this.r == null ? 30000 : this.r.a().c();
            if (c > 0) {
                this.g.postDelayed(this.j, c);
                this.l = true;
            }
        }
    }

    private void o() {
        if (this.l) {
            this.g.removeCallbacks(this.j);
            this.l = false;
        }
    }

    private Handler p() {
        return !q() ? this.g : h;
    }

    private static synchronized boolean q() {
        boolean z;
        synchronized (DisplayAdController.class) {
            z = i;
        }
        return z;
    }

    protected static synchronized void setMainThreadForced(boolean z) {
        synchronized (DisplayAdController.class) {
            String str = b;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("DisplayAdController changed main thread forced from ");
            stringBuilder.append(i);
            stringBuilder.append(" to ");
            stringBuilder.append(z);
            Log.d(str, stringBuilder.toString());
            i = z;
        }
    }

    public com.facebook.ads.internal.j.d a() {
        return this.r == null ? null : this.r.a();
    }

    public void a(int i) {
        this.x = i;
    }

    public void a(RewardData rewardData) {
        if (this.p == null) {
            throw new IllegalStateException("no adapter ready to set reward on");
        } else if (this.p.getPlacementType() != AdPlacementType.REWARDED_VIDEO) {
            throw new IllegalStateException("can only set on rewarded video ads");
        } else {
            ((t) this.p).a(rewardData);
        }
    }

    public void a(com.facebook.ads.internal.adapters.a aVar) {
        this.a = aVar;
    }

    public void a(@Nullable d dVar) {
        this.D = dVar;
    }

    public synchronized void a(final com.facebook.ads.internal.protocol.a aVar) {
        p().post(new Runnable() {
            public void run() {
                DisplayAdController.this.a.a(aVar);
            }
        });
    }

    public synchronized void a(final com.facebook.ads.internal.q.g gVar) {
        if (com.facebook.ads.internal.n.a.F(this.c)) {
            com.facebook.ads.internal.protocol.a e = e();
            if (e != null) {
                Log.e("FBAudienceNetwork", e.b());
                a(e);
                return;
            }
        }
        p().post(new Runnable() {
            public void run() {
                com.facebook.ads.internal.j.c a = gVar.a();
                if (a == null || a.a() == null) {
                    throw new IllegalStateException("invalid placement in response");
                }
                DisplayAdController.this.r = a;
                DisplayAdController.this.l();
            }
        });
    }

    public void a(String str) {
        this.C = str;
    }

    public void a(boolean z) {
        this.w = z;
    }

    public void b() {
        com.facebook.ads.internal.adapters.a aVar;
        AdErrorType adErrorType;
        AdErrorType adErrorType2;
        if (this.p == null) {
            com.facebook.ads.internal.s.d.a.a(this.c, "api", com.facebook.ads.internal.s.d.b.e, new com.facebook.ads.internal.protocol.b(AdErrorType.NO_ADAPTER_ON_START, "Adapter is null on startAd"));
            aVar = this.a;
            adErrorType = AdErrorType.INTERNAL_ERROR;
            adErrorType2 = AdErrorType.INTERNAL_ERROR;
        } else if (this.n) {
            com.facebook.ads.internal.s.d.a.a(this.c, "api", com.facebook.ads.internal.s.d.b.c, new com.facebook.ads.internal.protocol.b(AdErrorType.AD_ALREADY_STARTED, "ad already started"));
            aVar = this.a;
            adErrorType = AdErrorType.AD_ALREADY_STARTED;
            adErrorType2 = AdErrorType.AD_ALREADY_STARTED;
        } else {
            this.n = true;
            switch (this.p.getPlacementType()) {
                case INTERSTITIAL:
                    ((h) this.p).a();
                    break;
                case BANNER:
                    if (this.q != null) {
                        this.a.a(this.q);
                        return;
                    }
                    break;
                case NATIVE:
                case NATIVE_BANNER:
                    j jVar = (j) this.p;
                    if (jVar.K()) {
                        this.a.a(jVar);
                        return;
                    }
                    throw new IllegalStateException("ad is not ready or already displayed");
                case INSTREAM:
                    ((o) this.p).e();
                    return;
                case REWARDED_VIDEO:
                    t tVar = (t) this.p;
                    tVar.a(this.x);
                    tVar.b();
                    return;
                default:
                    Log.e(b, "start unexpected adapter type");
                    return;
            }
            return;
        }
        aVar.a(com.facebook.ads.internal.protocol.a.a(adErrorType, adErrorType2.getDefaultErrorMessage()));
    }

    public void b(String str) {
        c(str);
    }

    public void b(boolean z) {
        j();
        if (z || this.n) {
            o();
            a(this.p);
            this.f.a();
            this.q = null;
            this.n = false;
        }
    }

    public void c() {
        b(false);
    }

    public boolean d() {
        return this.r == null || this.r.f();
    }

    /* Access modifiers changed, original: 0000 */
    @Nullable
    public com.facebook.ads.internal.protocol.a e() {
        com.facebook.ads.internal.protocol.a aVar = null;
        if (this.D != null && this.D != d.NONE) {
            return f() ? null : new com.facebook.ads.internal.protocol.a(AdErrorType.CLEAR_TEXT_SUPPORT_NOT_ALLOWED, "");
        } else {
            if (this.w) {
                return f() ? null : new com.facebook.ads.internal.protocol.a(AdErrorType.CLEAR_TEXT_SUPPORT_NOT_ALLOWED, "");
            } else {
                if (!(this.B == null || this.B.contains(CacheFlag.NONE))) {
                    if (f()) {
                        return null;
                    }
                    aVar = new com.facebook.ads.internal.protocol.a(AdErrorType.CLEAR_TEXT_SUPPORT_NOT_ALLOWED, "");
                }
                return aVar;
            }
        }
    }

    /* Access modifiers changed, original: 0000 */
    public boolean f() {
        boolean z = VERSION.SDK_INT < 24 || NetworkSecurityPolicy.getInstance().isCleartextTrafficPermitted() || NetworkSecurityPolicy.getInstance().isCleartextTrafficPermitted("127.0.0.1");
        if (!z) {
            com.facebook.ads.internal.s.d.a.a(this.c, "cache", com.facebook.ads.internal.s.d.b.K, new Exception("Cleartext http is not allowed."));
        }
        return z;
    }

    public com.facebook.ads.internal.o.c g() {
        return this.A;
    }

    public AdAdapter h() {
        return this.p;
    }
}
