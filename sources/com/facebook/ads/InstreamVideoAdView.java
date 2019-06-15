package com.facebook.ads;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.facebook.ads.a.a;
import com.facebook.ads.internal.DisplayAdController;
import com.facebook.ads.internal.adapters.AdAdapter;
import com.facebook.ads.internal.adapters.g;
import com.facebook.ads.internal.adapters.o;
import com.facebook.ads.internal.protocol.AdPlacementType;
import com.facebook.ads.internal.protocol.e;
import com.facebook.ads.internal.view.c.c;
import java.util.EnumSet;

public class InstreamVideoAdView extends RelativeLayout implements Ad {
    private final Context a;
    private final String b;
    private final AdSize c;
    private DisplayAdController d;
    @Nullable
    private g e;
    private boolean f;
    @Nullable
    private InstreamVideoAdListener g;
    @Nullable
    private View h;
    @Nullable
    private Bundle i;
    @Nullable
    private c j;

    public InstreamVideoAdView(Context context, Bundle bundle) {
        this(context, bundle.getString("placementID"), (AdSize) bundle.get("adSize"));
        this.i = bundle;
    }

    public InstreamVideoAdView(Context context, String str, AdSize adSize) {
        super(context);
        this.f = false;
        this.a = context;
        this.b = str;
        this.c = adSize;
        this.d = getController();
    }

    private final void a() {
        if (this.d != null) {
            this.d.b(true);
            this.d = null;
            this.d = getController();
            this.e = null;
            this.f = false;
            removeAllViews();
        }
    }

    private void a(String str) {
        if (this.i != null) {
            this.e = new g();
            this.e.a(getContext(), new a() {
                public void a(o oVar) {
                    InstreamVideoAdView.this.f = true;
                    if (InstreamVideoAdView.this.g != null) {
                        InstreamVideoAdView.this.g.onAdLoaded(InstreamVideoAdView.this);
                    }
                }

                public void a(o oVar, View view) {
                    if (view == null) {
                        throw new IllegalStateException("Cannot present null view");
                    }
                    InstreamVideoAdView.this.h = view;
                    InstreamVideoAdView.this.removeAllViews();
                    InstreamVideoAdView.this.h.setLayoutParams(new LayoutParams(-1, -1));
                    InstreamVideoAdView.this.addView(InstreamVideoAdView.this.h);
                }

                public void a(o oVar, AdError adError) {
                    if (InstreamVideoAdView.this.g != null) {
                        InstreamVideoAdView.this.g.onError(InstreamVideoAdView.this, adError);
                    }
                }

                public void b(o oVar) {
                    if (InstreamVideoAdView.this.g != null) {
                        InstreamVideoAdView.this.g.onAdClicked(InstreamVideoAdView.this);
                    }
                }

                public void c(o oVar) {
                }

                public void d(o oVar) {
                    if (InstreamVideoAdView.this.g != null) {
                        InstreamVideoAdView.this.g.onAdVideoComplete(InstreamVideoAdView.this);
                    }
                }
            }, this.d.g(), this.i.getBundle("adapter"), EnumSet.of(CacheFlag.NONE));
            return;
        }
        this.d.b(str);
    }

    private DisplayAdController getController() {
        this.d = new DisplayAdController(getContext(), this.b, e.INSTREAM_VIDEO, AdPlacementType.INSTREAM, this.c.toInternalAdSize(), 1, true);
        this.d.a(new com.facebook.ads.internal.adapters.a() {
            public void a() {
                if (InstreamVideoAdView.this.g != null) {
                    InstreamVideoAdView.this.g.onAdClicked(InstreamVideoAdView.this);
                }
            }

            public void a(View view) {
                if (view == null) {
                    throw new IllegalStateException("Cannot present null view");
                }
                InstreamVideoAdView.this.h = view;
                InstreamVideoAdView.this.removeAllViews();
                InstreamVideoAdView.this.h.setLayoutParams(new LayoutParams(-1, -1));
                InstreamVideoAdView.this.addView(InstreamVideoAdView.this.h);
                if (com.facebook.ads.internal.n.a.b(InstreamVideoAdView.this.a)) {
                    InstreamVideoAdView.this.j = new c();
                    InstreamVideoAdView.this.j.a(InstreamVideoAdView.this.b);
                    InstreamVideoAdView.this.j.b(InstreamVideoAdView.this.a.getPackageName());
                    if (InstreamVideoAdView.this.d.a() != null) {
                        InstreamVideoAdView.this.j.a(InstreamVideoAdView.this.d.a().a());
                    }
                    InstreamVideoAdView.this.h.getOverlay().add(InstreamVideoAdView.this.j);
                    InstreamVideoAdView.this.h.setOnLongClickListener(new OnLongClickListener() {
                        public boolean onLongClick(View view) {
                            if (InstreamVideoAdView.this.h == null || InstreamVideoAdView.this.j == null) {
                                return false;
                            }
                            InstreamVideoAdView.this.j.setBounds(0, 0, InstreamVideoAdView.this.h.getWidth(), InstreamVideoAdView.this.h.getHeight());
                            InstreamVideoAdView.this.j.a(InstreamVideoAdView.this.j.a() ^ 1);
                            return true;
                        }
                    });
                }
            }

            public void a(AdAdapter adAdapter) {
                if (InstreamVideoAdView.this.d != null) {
                    InstreamVideoAdView.this.f = true;
                    if (InstreamVideoAdView.this.g != null) {
                        InstreamVideoAdView.this.g.onAdLoaded(InstreamVideoAdView.this);
                    }
                }
            }

            public void a(com.facebook.ads.internal.protocol.a aVar) {
                if (InstreamVideoAdView.this.g != null) {
                    InstreamVideoAdView.this.g.onError(InstreamVideoAdView.this, AdError.getAdErrorFromWrapper(aVar));
                }
            }

            public void b() {
                if (InstreamVideoAdView.this.g != null) {
                    InstreamVideoAdView.this.g.onLoggingImpression(InstreamVideoAdView.this);
                }
            }

            public void c() {
                if (InstreamVideoAdView.this.g != null) {
                    InstreamVideoAdView.this.g.onAdVideoComplete(InstreamVideoAdView.this);
                }
            }
        });
        return this.d;
    }

    public void destroy() {
        if (this.j != null && com.facebook.ads.internal.n.a.b(this.a)) {
            this.j.b();
            if (this.h != null) {
                this.h.getOverlay().remove(this.j);
            }
        }
        a();
    }

    public String getPlacementId() {
        return this.b;
    }

    public Bundle getSaveInstanceState() {
        o oVar = this.e != null ? this.e : (o) this.d.h();
        if (oVar == null) {
            return null;
        }
        Bundle g = oVar.g();
        if (g == null) {
            return null;
        }
        Bundle bundle = new Bundle();
        bundle.putBundle("adapter", g);
        bundle.putString("placementID", this.b);
        bundle.putSerializable("adSize", this.c);
        return bundle;
    }

    public boolean isAdInvalidated() {
        return this.d == null || this.d.d();
    }

    public boolean isAdLoaded() {
        return this.f;
    }

    public void loadAd() {
        a(null);
    }

    public void loadAdFromBid(String str) {
        a(str);
    }

    public void setAdListener(InstreamVideoAdListener instreamVideoAdListener) {
        this.g = instreamVideoAdListener;
    }

    public boolean show() {
        if (!this.f || (this.d == null && this.e == null)) {
            if (this.g != null) {
                this.g.onError(this, AdError.SHOW_CALLED_BEFORE_LOAD_ERROR);
            }
            return false;
        }
        if (this.e != null) {
            this.e.e();
        } else {
            this.d.b();
        }
        this.f = false;
        return true;
    }
}
