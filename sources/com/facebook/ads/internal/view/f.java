package com.facebook.ads.internal.view;

import android.animation.LayoutTransition;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.net.Uri;
import android.os.Build.VERSION;
import android.support.annotation.Nullable;
import android.support.v4.graphics.ColorUtils;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.PopupMenu;
import android.widget.PopupMenu.OnDismissListener;
import android.widget.PopupMenu.OnMenuItemClickListener;
import com.facebook.ads.internal.adapters.a.d;
import com.facebook.ads.internal.adapters.a.i;
import com.facebook.ads.internal.s.a.y;
import com.facebook.ads.internal.s.c.g;
import com.facebook.ads.internal.view.component.CircularProgressView;
import com.facebook.ads.internal.view.g.b.c;
import com.facebook.ads.internal.view.g.b.n;
import com.facebook.ads.internal.view.g.b.o;

public class f extends LinearLayout implements com.facebook.ads.internal.view.g.a.b {
    private static final float c = Resources.getSystem().getDisplayMetrics().density;
    private static final int d = ((int) (40.0f * c));
    private static final int e = ((int) (44.0f * c));
    private static final int f = ((int) (10.0f * c));
    private static final int g = ((int) (16.0f * c));
    private static final int h = (g - f);
    private static final int i = ((2 * g) - f);
    private final o a = new o() {
        public void a(n nVar) {
            if (f.this.s != null && f.this.t != 0 && f.this.n.isShown()) {
                float currentPositionInMillis = ((float) f.this.s.getCurrentPositionInMillis()) / Math.min(((float) f.this.t) * 1000.0f, (float) f.this.s.getDuration());
                f.this.setProgress(100.0f * currentPositionInMillis);
                if (currentPositionInMillis >= 1.0f) {
                    f.this.a(true);
                    f.this.s.getEventBus().b(f.this.a, f.this.b);
                }
            }
        }
    };
    private final c b = new c() {
        public void a(com.facebook.ads.internal.view.g.b.b bVar) {
            if (!(f.this.s == null || f.this.t == 0 || !f.this.n.isShown() || f.this.v)) {
                f.this.a(true);
                f.this.s.getEventBus().b(f.this.a, f.this.b);
            }
        }
    };
    private final com.facebook.ads.internal.view.a.a j;
    private final ImageView k;
    private final FrameLayout l;
    private final ImageView m;
    private final CircularProgressView n;
    private final com.facebook.ads.internal.view.d.c o;
    private final PopupMenu p;
    @Nullable
    private ImageView q;
    @Nullable
    private b r;
    @Nullable
    private com.facebook.ads.internal.view.g.a s;
    private int t = 0;
    private boolean u = false;
    private boolean v = false;
    private OnDismissListener w;

    public interface b {
        void a();
    }

    public enum a {
        CROSS,
        ARROWS
    }

    public f(Context context, com.facebook.ads.internal.view.a.a aVar, a aVar2) {
        super(context);
        this.j = aVar;
        setGravity(16);
        if (VERSION.SDK_INT >= 14) {
            this.w = new OnDismissListener() {
                public void onDismiss(PopupMenu popupMenu) {
                    f.this.u = false;
                }
            };
        }
        this.m = new ImageView(context);
        this.m.setPadding(f, f, f, f);
        this.m.setScaleType(ScaleType.FIT_CENTER);
        this.m.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (f.this.r != null && f.this.v) {
                    f.this.r.a();
                }
            }
        });
        setCloseButtonStyle(aVar2);
        this.n = new CircularProgressView(context);
        this.n.setPadding(f, f, f, f);
        this.n.setProgress(0.0f);
        LayoutParams layoutParams = new LayoutParams(-2, -2);
        layoutParams.setMargins(h, h, i, h);
        LayoutParams layoutParams2 = new LayoutParams(e, e);
        this.l = new FrameLayout(context);
        this.l.setLayoutTransition(new LayoutTransition());
        this.l.addView(this.m, layoutParams2);
        this.l.addView(this.n, layoutParams2);
        addView(this.l, layoutParams);
        this.o = new com.facebook.ads.internal.view.d.c(context);
        layoutParams = new LayoutParams(0, -2);
        layoutParams.gravity = 17;
        layoutParams.weight = 1.0f;
        addView(this.o, layoutParams);
        this.k = new ImageView(context);
        this.k.setPadding(f, f, f, f);
        this.k.setScaleType(ScaleType.FIT_CENTER);
        this.k.setImageBitmap(com.facebook.ads.internal.s.b.c.a(com.facebook.ads.internal.s.b.b.INTERSTITIAL_AD_CHOICES));
        this.k.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                f.this.p.show();
                f.this.u = true;
            }
        });
        this.p = new PopupMenu(context, this.k);
        this.p.getMenu().add("Ad Choices");
        LayoutParams layoutParams3 = new LayoutParams(d, d);
        layoutParams3.setMargins(0, g / 2, g / 2, g / 2);
        addView(this.k, layoutParams3);
    }

    public void a(d dVar, boolean z) {
        int a = dVar.a(z);
        this.o.a(dVar.g(z), a);
        this.k.setColorFilter(a);
        if (this.q != null) {
            this.q.setColorFilter(a);
        }
        this.m.setColorFilter(a);
        this.n.a(ColorUtils.setAlphaComponent(a, 77), a);
        if (z) {
            Drawable gradientDrawable = new GradientDrawable(Orientation.TOP_BOTTOM, new int[]{-1778384896, 0});
            gradientDrawable.setCornerRadius(0.0f);
            y.a((View) this, gradientDrawable);
            return;
        }
        y.a((View) this, 0);
    }

    public void a(final i iVar, final String str) {
        this.q = new ImageView(getContext());
        this.q.setPadding(f, f, f, f);
        this.q.setScaleType(ScaleType.FIT_CENTER);
        this.q.setImageBitmap(com.facebook.ads.internal.s.b.c.a(com.facebook.ads.internal.s.b.b.INFO_ICON));
        this.q.setColorFilter(-1);
        addView(this.q, getChildCount() - 1, new LayoutParams(d, d));
        this.q.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                f.this.j.b(str);
            }
        });
        this.k.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                CharSequence n = !TextUtils.isEmpty(com.facebook.ads.internal.c.a.n(f.this.getContext())) ? com.facebook.ads.internal.c.a.n(f.this.getContext()) : iVar.c();
                if (!TextUtils.isEmpty(n)) {
                    g.a(new g(), f.this.getContext(), Uri.parse(n), str);
                }
            }
        });
    }

    public void a(final i iVar, final String str, int i) {
        this.t = i;
        this.o.setPageDetails(iVar);
        this.p.setOnMenuItemClickListener(new OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem menuItem) {
                f.this.u = false;
                if (!TextUtils.isEmpty(iVar.c())) {
                    g.a(new g(), f.this.getContext(), Uri.parse(iVar.c()), str);
                }
                return true;
            }
        });
        if (VERSION.SDK_INT >= 14) {
            this.p.setOnDismissListener(this.w);
        }
        a(i <= 0);
    }

    public void a(com.facebook.ads.internal.view.g.a aVar) {
        this.s = aVar;
        this.s.getEventBus().a(this.a, this.b);
    }

    public void a(boolean z) {
        this.v = z;
        int i = 0;
        this.l.setVisibility(0);
        this.n.setVisibility(z ? 4 : 0);
        ImageView imageView = this.m;
        if (!z) {
            i = 4;
        }
        imageView.setVisibility(i);
    }

    public boolean a() {
        return this.v;
    }

    public void b() {
        this.v = false;
        this.l.setVisibility(4);
        this.n.setVisibility(4);
        this.m.setVisibility(4);
    }

    public void b(com.facebook.ads.internal.view.g.a aVar) {
        if (this.s != null) {
            this.s.getEventBus().b(this.a, this.b);
            this.s = null;
        }
    }

    public void c() {
        this.o.setVisibility(4);
    }

    public void d() {
        if (VERSION.SDK_INT >= 14) {
            this.p.setOnDismissListener(null);
        }
        this.p.dismiss();
        if (VERSION.SDK_INT >= 14) {
            this.p.setOnDismissListener(this.w);
        }
    }

    public void e() {
        if (this.u && VERSION.SDK_INT >= 14) {
            this.p.show();
        }
    }

    public void setCloseButtonStyle(a aVar) {
        if (this.m != null) {
            this.m.setImageBitmap(com.facebook.ads.internal.s.b.c.a(aVar == a.ARROWS ? com.facebook.ads.internal.s.b.b.SKIP_ARROW : com.facebook.ads.internal.s.b.b.INTERSTITIAL_CLOSE));
        }
    }

    public void setProgress(float f) {
        this.n.setProgressWithAnimation(f);
    }

    public void setShowPageDetails(boolean z) {
        this.o.setVisibility(z ? 0 : 4);
    }

    public void setToolbarListener(b bVar) {
        this.r = bVar;
    }
}
