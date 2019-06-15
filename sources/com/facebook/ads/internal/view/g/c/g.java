package com.facebook.ads.internal.view.g.c;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout.LayoutParams;
import com.facebook.ads.internal.l.f;
import com.facebook.ads.internal.s.a.y;
import com.facebook.ads.internal.view.c.d;
import com.facebook.ads.internal.view.c.e;
import com.facebook.ads.internal.view.g.a.c;
import com.facebook.ads.internal.view.g.b.b;
import com.facebook.ads.internal.view.g.b.j;

public class g extends c {
    private final ImageView a;
    private final f<j> b = new f<j>() {
        public Class<j> a() {
            return j.class;
        }

        public void a(j jVar) {
            g.this.setVisibility(8);
        }
    };
    private final f<b> c = new f<b>() {
        public Class<b> a() {
            return b.class;
        }

        public void a(b bVar) {
            g.this.setVisibility(0);
        }
    };

    public g(Context context) {
        super(context);
        this.a = new ImageView(context);
        this.a.setScaleType(ScaleType.FIT_CENTER);
        y.a(this.a, (int) ViewCompat.MEASURED_STATE_MASK);
        this.a.setLayoutParams(new LayoutParams(-1, -1));
        addView(this.a);
    }

    /* Access modifiers changed, original: protected */
    public void a() {
        super.a();
        if (getVideoView() != null) {
            getVideoView().getEventBus().a(this.b, this.c);
        }
    }

    public void a(@Nullable String str, @Nullable e eVar) {
        if (str == null) {
            setVisibility(8);
            return;
        }
        setVisibility(0);
        d a = new d(this.a).a();
        if (eVar != null) {
            a.a(eVar);
        }
        a.a(str);
    }

    /* Access modifiers changed, original: protected */
    public void b() {
        if (getVideoView() != null) {
            getVideoView().getEventBus().b(this.c, this.b);
        }
        super.b();
    }

    /* Access modifiers changed, original: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        this.a.layout(0, 0, i3 - i, i4 - i2);
    }

    public void setImage(@Nullable String str) {
        a(str, null);
    }
}
