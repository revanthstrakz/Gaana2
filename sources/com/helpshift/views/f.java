package com.helpshift.views;

import android.graphics.Typeface;
import android.text.TextPaint;
import android.text.style.MetricAffectingSpan;

public class f extends MetricAffectingSpan {
    private final Typeface a;

    f(Typeface typeface) {
        this.a = typeface;
    }

    public void updateMeasureState(TextPaint textPaint) {
        textPaint.setTypeface(this.a);
    }

    public void updateDrawState(TextPaint textPaint) {
        textPaint.setTypeface(this.a);
    }
}
