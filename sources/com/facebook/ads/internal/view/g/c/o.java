package com.facebook.ads.internal.view.g.c;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ScaleDrawable;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v7.widget.helper.ItemTouchHelper.Callback;
import android.view.animation.LinearInterpolator;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.facebook.ads.internal.l.f;
import com.facebook.ads.internal.s.a.y;
import com.facebook.ads.internal.view.g.a;
import com.facebook.ads.internal.view.g.a.b;
import com.facebook.ads.internal.view.g.b.c;
import com.facebook.ads.internal.view.g.b.h;
import com.facebook.ads.internal.view.g.b.i;
import com.facebook.ads.internal.view.g.b.j;
import com.facebook.ads.internal.view.g.b.k;
import com.facebook.ads.internal.view.g.b.n;
import java.util.concurrent.atomic.AtomicInteger;

public class o extends RelativeLayout implements b {
    private static final int a = ((int) (6.0f * y.b));
    private ObjectAnimator b;
    private AtomicInteger c;
    private ProgressBar d;
    @Nullable
    private a e;
    private f f;
    private f g;
    private f h;
    private f i;

    public o(Context context) {
        this(context, a, -12549889);
    }

    public o(Context context, int i, int i2) {
        super(context);
        this.f = new com.facebook.ads.internal.view.g.b.o() {
            public void a(n nVar) {
                if (o.this.e != null) {
                    o.this.a(o.this.e.getDuration(), o.this.e.getCurrentPositionInMillis());
                }
            }
        };
        this.g = new i() {
            public void a(h hVar) {
                o.this.b();
            }
        };
        this.h = new k() {
            public void a(j jVar) {
                if (o.this.e != null) {
                    o.this.a(o.this.e.getDuration(), o.this.e.getCurrentPositionInMillis());
                }
            }
        };
        this.i = new c() {
            public void a(com.facebook.ads.internal.view.g.b.b bVar) {
                if (o.this.e != null) {
                    o.this.c();
                }
            }
        };
        this.c = new AtomicInteger(-1);
        this.d = new ProgressBar(context, null, 16842872);
        this.d.setLayoutParams(new LayoutParams(-1, i));
        setProgressBarColor(i2);
        this.d.setMax(10000);
        addView(this.d);
    }

    private void a(int i, int i2) {
        b();
        if (this.c.get() < i2 && i > i2) {
            int i3 = (i2 * 10000) / i;
            int min = (Math.min(i2 + Callback.DEFAULT_SWIPE_ANIMATION_DURATION, i) * 10000) / i;
            this.b = ObjectAnimator.ofInt(this.d, NotificationCompat.CATEGORY_PROGRESS, new int[]{i3, min});
            this.b.setDuration((long) Math.min(Callback.DEFAULT_SWIPE_ANIMATION_DURATION, i - i2));
            this.b.setInterpolator(new LinearInterpolator());
            this.b.start();
            this.c.set(i2);
        }
    }

    private void b() {
        if (this.b != null) {
            this.b.cancel();
            this.b.setTarget(null);
            this.b = null;
            this.d.clearAnimation();
        }
    }

    private void c() {
        b();
        this.b = ObjectAnimator.ofInt(this.d, NotificationCompat.CATEGORY_PROGRESS, new int[]{0, 0});
        this.b.setDuration(0);
        this.b.setInterpolator(new LinearInterpolator());
        this.b.start();
        this.c.set(0);
    }

    public void a() {
        b();
        this.d = null;
        this.e = null;
    }

    public void a(a aVar) {
        this.e = aVar;
        aVar.getEventBus().a(this.g, this.h, this.f, this.i);
    }

    public void b(a aVar) {
        aVar.getEventBus().b(this.f, this.h, this.g, this.i);
        this.e = null;
    }

    public void setProgressBarColor(int i) {
        ColorDrawable colorDrawable = new ColorDrawable(0);
        ColorDrawable colorDrawable2 = new ColorDrawable(0);
        ScaleDrawable scaleDrawable = new ScaleDrawable(new ColorDrawable(i), 8388611, 1.0f, -1.0f);
        LayerDrawable layerDrawable = new LayerDrawable(new Drawable[]{colorDrawable, colorDrawable2, scaleDrawable});
        layerDrawable.setId(0, 16908288);
        layerDrawable.setId(1, 16908303);
        layerDrawable.setId(2, 16908301);
        this.d.setProgressDrawable(layerDrawable);
    }
}
