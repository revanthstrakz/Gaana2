package com.facebook.ads.internal.view.g.c;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.view.View;
import com.facebook.ads.internal.view.g.a;
import com.facebook.ads.internal.view.g.a.b;
import com.facebook.ads.internal.view.g.b.c;
import com.facebook.ads.internal.view.g.b.o;

public class n extends View implements b {
    private final Paint a = new Paint();
    private final Rect b;
    private float c;
    private final o d = new o() {
        public void a(com.facebook.ads.internal.view.g.b.n nVar) {
            if (n.this.f != null) {
                int duration = n.this.f.getDuration();
                if (duration > 0) {
                    n.this.c = ((float) n.this.f.getCurrentPositionInMillis()) / ((float) duration);
                } else {
                    n.this.c = 0.0f;
                }
                n.this.postInvalidate();
            }
        }
    };
    private final c e = new c() {
        public void a(com.facebook.ads.internal.view.g.b.b bVar) {
            if (n.this.f != null) {
                n.this.c = 0.0f;
                n.this.postInvalidate();
            }
        }
    };
    @Nullable
    private a f;

    public n(Context context) {
        super(context);
        this.a.setStyle(Style.FILL);
        this.a.setColor(-9528840);
        this.b = new Rect();
    }

    public void a(a aVar) {
        this.f = aVar;
        aVar.getEventBus().a(this.d, this.e);
    }

    public void b(a aVar) {
        aVar.getEventBus().b(this.e, this.d);
        this.f = null;
    }

    public void draw(Canvas canvas) {
        this.b.set(0, 0, (int) (((float) getWidth()) * this.c), getHeight());
        canvas.drawRect(this.b, this.a);
        super.draw(canvas);
    }
}
