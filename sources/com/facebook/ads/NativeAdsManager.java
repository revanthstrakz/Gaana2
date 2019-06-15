package com.facebook.ads;

import android.content.Context;
import android.os.Build.VERSION;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import com.facebook.ads.NativeAdBase.MediaCacheFlag;
import com.facebook.ads.internal.a;
import com.facebook.ads.internal.adapters.j;
import com.facebook.ads.internal.f.b;
import com.facebook.ads.internal.protocol.e;
import java.util.ArrayList;
import java.util.List;

public class NativeAdsManager {
    private static final String a = "NativeAdsManager";
    private final Context b;
    private final String c;
    private final int d;
    private final List<NativeAd> e;
    private int f;
    private Listener g;
    private String h;
    private a i;
    private boolean j;
    private boolean k;

    public interface Listener {
        void onAdError(AdError adError);

        void onAdsLoaded();
    }

    public NativeAdsManager(Context context, String str, int i) {
        if (context == null) {
            throw new IllegalArgumentException("context can not be null");
        }
        this.b = context;
        this.c = str;
        this.d = Math.max(i, 0);
        this.e = new ArrayList(i);
        this.f = -1;
        this.k = false;
        this.j = false;
        try {
            CookieManager.getInstance();
            if (VERSION.SDK_INT < 21) {
                CookieSyncManager.createInstance(context);
            }
        } catch (Exception e) {
            Log.w(a, "Failed to initialize CookieManager.", e);
        }
    }

    public void disableAutoRefresh() {
        this.j = true;
        if (this.i != null) {
            this.i.c();
        }
    }

    public int getUniqueNativeAdCount() {
        return this.e.size();
    }

    public boolean isLoaded() {
        return this.k;
    }

    public void loadAds() {
        loadAds(MediaCacheFlag.ALL);
    }

    public void loadAds(final MediaCacheFlag mediaCacheFlag) {
        e eVar = e.NATIVE_UNKNOWN;
        int i = this.d;
        if (this.i != null) {
            this.i.b();
        }
        this.i = new a(this.b, this.c, eVar, null, i);
        if (this.j) {
            this.i.c();
        }
        this.i.a(this.h);
        this.i.a(new a.a() {
            public void a(com.facebook.ads.internal.protocol.a aVar) {
                if (NativeAdsManager.this.g != null) {
                    NativeAdsManager.this.g.onAdError(AdError.getAdErrorFromWrapper(aVar));
                }
            }

            public void a(final List<j> list) {
                b bVar = new b(NativeAdsManager.this.b);
                for (j jVar : list) {
                    if (mediaCacheFlag.equals(MediaCacheFlag.ALL)) {
                        if (jVar.m() != null) {
                            bVar.a(jVar.m().a(), jVar.m().c(), jVar.m().b());
                        }
                        if (jVar.n() != null) {
                            bVar.a(jVar.n().a(), jVar.n().c(), jVar.n().b());
                        }
                        if (!TextUtils.isEmpty(jVar.D())) {
                            bVar.a(jVar.D());
                        }
                    }
                }
                bVar.a(new com.facebook.ads.internal.f.a() {
                    private void c() {
                        NativeAdsManager.this.k = true;
                        NativeAdsManager.this.e.clear();
                        NativeAdsManager.this.f = 0;
                        for (j nativeAd : list) {
                            NativeAdsManager.this.e.add(new NativeAd(NativeAdsManager.this.b, nativeAd, null));
                        }
                        if (NativeAdsManager.this.g != null) {
                            NativeAdsManager.this.g.onAdsLoaded();
                        }
                    }

                    public void a() {
                        c();
                    }

                    public void b() {
                        c();
                    }
                });
            }
        });
        this.i.a();
    }

    public NativeAd nextNativeAd() {
        if (this.e.size() == 0) {
            return null;
        }
        int i = this.f;
        this.f = i + 1;
        NativeAdBase nativeAdBase = (NativeAd) this.e.get(i % this.e.size());
        return i >= this.e.size() ? new NativeAd(nativeAdBase) : nativeAdBase;
    }

    public void setExtraHints(String str) {
        this.h = str;
    }

    public void setListener(Listener listener) {
        this.g = listener;
    }
}
