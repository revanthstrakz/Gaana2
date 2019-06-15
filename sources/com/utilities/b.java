package com.utilities;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.FontMetricsInt;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.text.style.ImageSpan;
import com.gaana.R;
import java.lang.ref.WeakReference;

public class b extends ImageSpan {
    private WeakReference<Drawable> a;
    private int b;
    private Context c;

    public b(Context context, int i, int i2) {
        super(context, i);
        this.b = i2;
        this.c = context;
    }

    public int getSize(Paint paint, CharSequence charSequence, int i, int i2, FontMetricsInt fontMetricsInt) {
        Rect bounds = a().getBounds();
        if (fontMetricsInt != null) {
            FontMetricsInt fontMetricsInt2 = paint.getFontMetricsInt();
            fontMetricsInt.ascent = fontMetricsInt2.ascent;
            fontMetricsInt.descent = fontMetricsInt2.descent;
            fontMetricsInt.top = fontMetricsInt2.top;
            fontMetricsInt.bottom = fontMetricsInt2.bottom;
        }
        return bounds.right;
    }

    public void draw(@NonNull Canvas canvas, CharSequence charSequence, int i, int i2, float f, int i3, int i4, int i5, @NonNull Paint paint) {
        if (this.b == -12) {
            Drawable a = a();
            canvas.save();
            canvas.translate(f, (float) this.c.getResources().getDimensionPixelOffset(R.dimen.dimen_25dp));
            a.draw(canvas);
            canvas.restore();
            return;
        }
        super.draw(canvas, charSequence, i, i2, f, i3, i4, i5, paint);
    }

    private Drawable a() {
        WeakReference weakReference = this.a;
        Drawable drawable = weakReference != null ? (Drawable) weakReference.get() : null;
        if (drawable != null) {
            return drawable;
        }
        drawable = getDrawable();
        this.a = new WeakReference(drawable);
        return drawable;
    }
}
