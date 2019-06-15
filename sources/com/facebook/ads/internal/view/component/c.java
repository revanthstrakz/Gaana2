package com.facebook.ads.internal.view.component;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.support.v4.graphics.ColorUtils;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.facebook.ads.internal.adapters.a.d;
import com.facebook.ads.internal.s.a.y;
import java.util.Locale;

public class c extends Button {
    public static final int a = ((int) (16.0f * y.b));
    private static final int b = ((int) (4.0f * y.b));
    private final Paint c = new Paint();
    private final RectF d;
    private final boolean e;

    public c(Context context, boolean z, boolean z2, d dVar) {
        super(context);
        this.e = z;
        y.a((TextView) this, false, 16);
        setGravity(17);
        setPadding(a, a, a, a);
        setTextColor(dVar.f(z2));
        int e = dVar.e(z2);
        int blendARGB = ColorUtils.blendARGB(e, ViewCompat.MEASURED_STATE_MASK, 0.1f);
        setButtonColor(e);
        this.d = new RectF();
        if (!z) {
            Drawable stateListDrawable = new StateListDrawable();
            stateListDrawable.addState(new int[]{16842919}, new ColorDrawable(blendARGB));
            stateListDrawable.addState(new int[0], new ColorDrawable(e));
            y.a((View) this, stateListDrawable);
        }
    }

    /* Access modifiers changed, original: protected */
    public void onDraw(Canvas canvas) {
        if (this.e) {
            this.d.set(0.0f, 0.0f, (float) getWidth(), (float) getHeight());
            canvas.drawRoundRect(this.d, (float) b, (float) b, this.c);
        }
        super.onDraw(canvas);
    }

    public void setButtonColor(int i) {
        this.c.setStyle(Style.FILL);
        this.c.setColor(i);
    }

    public void setText(String str) {
        super.setText(str.toUpperCase(Locale.US));
    }
}
