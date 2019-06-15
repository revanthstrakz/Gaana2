package com.google.android.youtube.player.internal;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.view.View.MeasureSpec;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ProgressBar;
import android.widget.TextView;

public final class n extends FrameLayout {
    private final ProgressBar a;
    private final TextView b;

    public n(Context context) {
        super(context, null, z.c(context));
        m mVar = new m(context);
        setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
        this.a = new ProgressBar(context);
        this.a.setVisibility(8);
        addView(this.a, new LayoutParams(-2, -2, 17));
        int i = (int) ((10.0f * context.getResources().getDisplayMetrics().density) + 0.5f);
        this.b = new TextView(context);
        this.b.setTextAppearance(context, 16973894);
        this.b.setTextColor(-1);
        this.b.setVisibility(8);
        this.b.setPadding(i, i, i, i);
        this.b.setGravity(17);
        this.b.setText(mVar.a);
        addView(this.b, new LayoutParams(-2, -2, 17));
    }

    public final void a() {
        this.a.setVisibility(8);
        this.b.setVisibility(8);
    }

    public final void b() {
        this.a.setVisibility(0);
        this.b.setVisibility(8);
    }

    public final void c() {
        this.a.setVisibility(8);
        this.b.setVisibility(0);
    }

    /* Access modifiers changed, original: protected|final */
    public final void onMeasure(int i, int i2) {
        int mode = MeasureSpec.getMode(i);
        int mode2 = MeasureSpec.getMode(i2);
        int size = MeasureSpec.getSize(i);
        int size2 = MeasureSpec.getSize(i2);
        if (!(mode == 1073741824 && mode2 == 1073741824)) {
            if (mode == 1073741824 || (mode == Integer.MIN_VALUE && mode2 == 0)) {
                size2 = (int) (((float) size) / 1.777f);
            } else {
                float f;
                if (mode2 == 1073741824 || (mode2 == Integer.MIN_VALUE && mode == 0)) {
                    f = (float) size2;
                } else if (mode == Integer.MIN_VALUE && mode2 == Integer.MIN_VALUE) {
                    f = (float) size2;
                    float f2 = ((float) size) / 1.777f;
                    if (f >= f2) {
                        size2 = (int) f2;
                    }
                } else {
                    size = 0;
                    size2 = size;
                }
                size = (int) (f * 1.777f);
            }
        }
        super.onMeasure(MeasureSpec.makeMeasureSpec(resolveSize(size, i), 1073741824), MeasureSpec.makeMeasureSpec(resolveSize(size2, i2), 1073741824));
    }
}
