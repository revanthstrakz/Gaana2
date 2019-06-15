package com.facebook.ads.internal.view.component.a.a;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.RectF;
import android.os.Build.VERSION;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.facebook.ads.internal.s.a.y;
import com.facebook.ads.internal.view.g.b.h;
import com.facebook.ads.internal.view.g.b.i;
import com.facebook.ads.internal.view.g.b.j;
import com.facebook.ads.internal.view.g.b.k;
import com.facebook.ads.internal.view.g.b.l;
import com.facebook.ads.internal.view.g.b.m;
import com.facebook.ads.internal.view.g.b.v;
import com.facebook.ads.internal.view.g.b.w;
import com.facebook.ads.internal.view.s;
import java.lang.ref.WeakReference;
import java.util.Map;

public abstract class b extends com.facebook.ads.internal.view.component.a.b {
    private static final int c = ((int) (1.0f * y.b));
    private static final int d = ((int) (4.0f * y.b));
    private static final int e = ((int) (6.0f * y.b));
    private s f;
    private com.facebook.ads.internal.view.d.a.e g;
    private RelativeLayout h;
    private final String i;
    private final Paint j;
    private boolean k;
    private com.facebook.ads.internal.view.d.a.a l;
    private final Path m = new Path();
    private final RectF n = new RectF();
    private boolean o;
    private boolean p;
    private a q;
    private final w r = new w() {
        public void a(v vVar) {
            b.this.l.c().a(b.this.getVideoView().getVolume());
        }
    };
    private final com.facebook.ads.internal.view.g.b.c s = new com.facebook.ads.internal.view.g.b.c() {
        public void a(com.facebook.ads.internal.view.g.b.b bVar) {
            b.this.l.d().a(((Integer) b.this.getTag(-1593835536)).intValue());
        }
    };
    private final k t = new k() {
        public void a(j jVar) {
            b.this.l.e().a(b.this);
        }
    };
    private final i u = new i() {
        public void a(h hVar) {
            b.this.l.e().b(b.this);
        }
    };
    private final m v = new m() {
        public void a(l lVar) {
            b.this.p = true;
            b.this.k();
        }
    };

    public interface a {
        void a();
    }

    public interface c {
        void a(int i);
    }

    public interface d {
        void a(View view);

        void b(View view);
    }

    public interface e {
        float a();

        void a(float f);
    }

    private static class b implements com.facebook.ads.internal.view.c.e {
        final WeakReference<b> a;

        private b(b bVar) {
            this.a = new WeakReference(bVar);
        }

        /* synthetic */ b(b bVar, AnonymousClass1 anonymousClass1) {
            this(bVar);
        }

        public void a(boolean z) {
            b bVar = (b) this.a.get();
            if (bVar != null) {
                bVar.o = z;
                bVar.k();
            }
        }
    }

    b(com.facebook.ads.internal.view.component.a.d dVar, com.facebook.ads.internal.adapters.a.d dVar2, boolean z, String str, com.facebook.ads.internal.view.d.a.a aVar) {
        super(dVar, dVar2, z);
        this.l = aVar;
        this.i = str;
        setGravity(17);
        setPadding(c, 0, c, c);
        y.a((View) this, 0);
        setUpView(getContext());
        this.j = new Paint();
        this.j.setColor(ViewCompat.MEASURED_STATE_MASK);
        this.j.setStyle(Style.FILL);
        this.j.setAlpha(16);
        this.j.setAntiAlias(true);
        if (VERSION.SDK_INT < 18) {
            setLayerType(1, null);
        }
    }

    private void a(View view) {
        view.setLayoutParams(new LayoutParams(-1, -2));
        y.a(view);
    }

    private void k() {
        if (this.q != null) {
            if ((f() && this.p) || (!f() && this.o)) {
                this.q.a();
            }
        }
    }

    private void setUpView(Context context) {
        setUpImageView(context);
        setUpVideoView(context);
        setUpMediaContainer(context);
        this.h.addView(this.f);
        this.h.addView(this.g);
        a(context);
    }

    public abstract void a(Context context);

    public void a(com.facebook.ads.internal.adapters.a.e eVar, Map<String, String> map) {
        getCtaButton().a(eVar, this.i, map);
    }

    public void a(String str, String str2) {
        getTitleDescContainer().a(str, str2, true, false);
    }

    public void a(Map<String, String> map) {
        this.g.c();
        if (f()) {
            this.g.a(getAdEventManager(), this.i, map);
        }
    }

    public boolean a() {
        return false;
    }

    /* Access modifiers changed, original: protected */
    public boolean e() {
        return false;
    }

    public boolean f() {
        return this.k;
    }

    public boolean g() {
        return f() && this.g.b();
    }

    /* Access modifiers changed, original: protected|final */
    public final RelativeLayout getMediaContainer() {
        return this.h;
    }

    public final com.facebook.ads.internal.view.d.a.e getVideoView() {
        return this.g;
    }

    public void h() {
        if (f()) {
            j();
            this.g.a(com.facebook.ads.internal.view.g.a.a.AUTO_STARTED);
        }
    }

    public void i() {
        if (f()) {
            this.g.a();
        }
    }

    public void j() {
        float a = this.l.c().a();
        if (f() && a != this.g.getVolume()) {
            this.g.setVolume(a);
        }
    }

    /* Access modifiers changed, original: protected */
    public void onDraw(Canvas canvas) {
        this.m.reset();
        this.n.set(0.0f, 0.0f, (float) getWidth(), (float) getHeight());
        this.m.addRoundRect(this.n, (float) e, (float) e, Direction.CW);
        canvas.drawPath(this.m, this.j);
        this.n.set((float) c, 0.0f, (float) (getWidth() - c), (float) (getHeight() - c));
        this.m.addRoundRect(this.n, (float) d, (float) d, Direction.CW);
        canvas.clipPath(this.m);
        super.onDraw(canvas);
    }

    public void setImageUrl(String str) {
        this.f.setVisibility(0);
        this.g.setVisibility(8);
        new com.facebook.ads.internal.view.c.d(this.f).a().a(new b(this, null)).a(str);
    }

    public void setIsVideo(boolean z) {
        this.k = z;
    }

    public void setOnAssetsLoadedListener(a aVar) {
        this.q = aVar;
    }

    /* Access modifiers changed, original: protected */
    public void setUpImageView(Context context) {
        this.f = new s(context);
        a(this.f);
    }

    /* Access modifiers changed, original: protected */
    public void setUpMediaContainer(Context context) {
        this.h = new RelativeLayout(context);
        a(this.h);
    }

    /* Access modifiers changed, original: protected */
    public void setUpVideoView(Context context) {
        this.g = new com.facebook.ads.internal.view.d.a.e(context, getAdEventManager());
        a(this.g);
    }

    public void setVideoPlaceholderUrl(String str) {
        this.g.setPlaceholderUrl(str);
    }

    public void setVideoUrl(String str) {
        this.f.setVisibility(8);
        this.g.setVisibility(0);
        this.g.setVideoURI(str);
        this.g.a(this.r);
        this.g.a(this.s);
        this.g.a(this.t);
        this.g.a(this.u);
        this.g.a(this.v);
    }
}
