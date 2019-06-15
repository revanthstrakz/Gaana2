package com.utilities;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.FontMetricsInt;
import android.graphics.Rect;
import android.text.style.LineBackgroundSpan;
import android.text.style.ReplacementSpan;

public class c extends ReplacementSpan implements LineBackgroundSpan {
    int a = 0;
    int b = 0;

    public void drawBackground(Canvas canvas, Paint paint, int i, int i2, int i3, int i4, int i5, CharSequence charSequence, int i6, int i7, int i8) {
        Rect rect = new Rect();
        canvas.getClipBounds(rect);
        this.a = rect.left;
        this.b = rect.right;
    }

    public int getSize(Paint paint, CharSequence charSequence, int i, int i2, FontMetricsInt fontMetricsInt) {
        return this.b - this.a;
    }

    public void draw(Canvas canvas, CharSequence charSequence, int i, int i2, float f, int i3, int i4, int i5, Paint paint) {
        CharSequence charSequence2 = charSequence;
        int i6 = i;
        int i7 = i4;
        int i8 = i2;
        Paint paint2 = paint;
        if (f + ((float) ((int) Math.ceil((double) paint2.measureText(charSequence2, i6, i8)))) < ((float) this.b)) {
            canvas.drawText(charSequence2, i6, i8, f, (float) i7, paint2);
            return;
        }
        CharSequence charSequence3 = charSequence2;
        int i9 = i6;
        int breakText = i6 + paint2.breakText(charSequence3, i9, i8, true, (((float) this.b) - f) - paint2.measureText("…"), null);
        float f2 = (float) i7;
        canvas.drawText(charSequence3, i9, breakText, f, f2, paint2);
        canvas.drawText("…", f + paint2.measureText(charSequence2, i6, breakText), f2, paint2);
    }
}
