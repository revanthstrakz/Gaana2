package com.facebook.ads;

import android.content.Context;
import android.content.res.Configuration;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.widget.RelativeLayout;
import com.facebook.ads.internal.DisplayAdController;
import com.facebook.ads.internal.adapters.AdAdapter;
import com.facebook.ads.internal.adapters.a;
import com.facebook.ads.internal.protocol.AdPlacementType;
import com.facebook.ads.internal.protocol.d;
import com.facebook.ads.internal.protocol.f;
import com.facebook.ads.internal.view.c.c;

public class AdView extends RelativeLayout implements Ad {
    private final DisplayMetrics a;
    private final d b;
    private final String c;
    private DisplayAdController d;
    private AdListener e;
    private View f;
    private c g;
    private String h;

    public AdView(Context context, final String str, AdSize adSize) {
        super(context);
        if (adSize == null || adSize == AdSize.INTERSTITIAL) {
            throw new IllegalArgumentException("adSize");
        }
        this.a = getContext().getResources().getDisplayMetrics();
        this.b = adSize.toInternalAdSize();
        this.c = str;
        this.d = new DisplayAdController(context, str, f.a(this.b), AdPlacementType.BANNER, adSize.toInternalAdSize(), 1, true);
        this.d.a(this.h);
        this.d.a(new a() {
            public void a() {
                if (AdView.this.e != null) {
                    AdView.this.e.onAdClicked(AdView.this);
                }
            }

            public void a(View view) {
                if (view == null) {
                    throw new IllegalStateException("Cannot present null view");
                }
                AdView.this.f = view;
                AdView.this.removeAllViews();
                AdView.this.addView(AdView.this.f);
                if (AdView.this.f instanceof com.facebook.ads.internal.view.c.a) {
                    f.a(AdView.this.a, AdView.this.f, AdView.this.b);
                }
                if (AdView.this.e != null) {
                    AdView.this.e.onAdLoaded(AdView.this);
                }
                if (com.facebook.ads.internal.n.a.b(AdView.this.getContext())) {
                    AdView.this.g = new c();
                    AdView.this.g.a(str);
                    AdView.this.g.b(AdView.this.getContext().getPackageName());
                    if (AdView.this.d.a() != null) {
                        AdView.this.g.a(AdView.this.d.a().a());
                    }
                    if (AdView.this.f instanceof com.facebook.ads.internal.view.c.a) {
                        AdView.this.g.a(((com.facebook.ads.internal.view.c.a) AdView.this.f).getViewabilityChecker());
                    }
                    AdView.this.f.setOnLongClickListener(new OnLongClickListener() {
                        public boolean onLongClick(View view) {
                            AdView.this.g.setBounds(0, 0, AdView.this.f.getWidth(), AdView.this.f.getHeight());
                            AdView.this.g.a(AdView.this.g.a() ^ 1);
                            return true;
                        }
                    });
                    AdView.this.f.getOverlay().add(AdView.this.g);
                }
            }

            public void a(AdAdapter adAdapter) {
                if (AdView.this.d != null) {
                    AdView.this.d.b();
                }
            }

            public void a(com.facebook.ads.internal.protocol.a aVar) {
                if (AdView.this.e != null) {
                    AdView.this.e.onError(AdView.this, AdError.getAdErrorFromWrapper(aVar));
                }
            }

            public void b() {
                if (AdView.this.e != null) {
                    AdView.this.e.onLoggingImpression(AdView.this);
                }
            }
        });
    }

    private void a(String str) {
        this.d.b(str);
    }

    public void destroy() {
        if (this.d != null) {
            this.d.b(true);
            this.d = null;
        }
        if (this.g != null && com.facebook.ads.internal.n.a.b(getContext())) {
            this.g.b();
            this.f.getOverlay().remove(this.g);
        }
        removeAllViews();
        this.f = null;
        this.e = null;
    }

    @Deprecated
    public void disableAutoRefresh() {
    }

    public String getPlacementId() {
        return this.c;
    }

    public boolean isAdInvalidated() {
        return this.d == null || this.d.d();
    }

    public void loadAd() {
        a(null);
    }

    public void loadAdFromBid(String str) {
        a(str);
    }

    /* Access modifiers changed, original: protected */
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (this.f != null) {
            f.a(this.a, this.f, this.b);
        }
    }

    public void setAdListener(AdListener adListener) {
        this.e = adListener;
    }

    public void setExtraHints(ExtraHints extraHints) {
        this.h = extraHints.getHints();
    }
}
