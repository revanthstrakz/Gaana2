package com.facebook.ads.internal.view.g.c;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Build.VERSION;
import android.view.View;
import android.widget.Button;
import com.facebook.ads.internal.s.a.y;

public class m extends Button {
    private final Path a;
    private final Path b;
    private final Paint c;
    private final Path d;
    private boolean e;

    public m(Context context) {
        this(context, false);
    }

    public m(Context context, final boolean z) {
        super(context);
        this.e = false;
        this.a = new Path();
        this.b = new Path();
        this.d = new Path();
        this.c = new Paint() {
        };
        setClickable(true);
        y.a((View) this, 0);
    }

    /* Access modifiers changed, original: protected */
    public void onDraw(Canvas canvas) {
        Path path;
        if (canvas.isHardwareAccelerated() && VERSION.SDK_INT < 17) {
            setLayerType(1, null);
        }
        float max = ((float) Math.max(canvas.getWidth(), canvas.getHeight())) / 100.0f;
        float f;
        float f2;
        if (this.e) {
            this.d.rewind();
            f = 26.5f * max;
            f2 = 15.5f * max;
            this.d.moveTo(f, f2);
            this.d.lineTo(f, 84.5f * max);
            this.d.lineTo(90.0f * max, 50.0f * max);
            this.d.lineTo(f, f2);
            this.d.close();
            path = this.d;
        } else {
            this.a.rewind();
            f = 29.0f * max;
            f2 = 21.0f * max;
            this.a.moveTo(f, f2);
            float f3 = 79.0f * max;
            this.a.lineTo(f, f3);
            float f4 = 45.0f * max;
            this.a.lineTo(f4, f3);
            this.a.lineTo(f4, f2);
            this.a.lineTo(f, f2);
            this.a.close();
            this.b.rewind();
            f = 55.0f * max;
            this.b.moveTo(f, f2);
            this.b.lineTo(f, f3);
            f4 = 71.0f * max;
            this.b.lineTo(f4, f3);
            this.b.lineTo(f4, f2);
            this.b.lineTo(f, f2);
            this.b.close();
            canvas.drawPath(this.a, this.c);
            path = this.b;
        }
        canvas.drawPath(path, this.c);
        super.onDraw(canvas);
    }

    public void setChecked(boolean z) {
        this.e = z;
        refreshDrawableState();
        invalidate();
    }
}
