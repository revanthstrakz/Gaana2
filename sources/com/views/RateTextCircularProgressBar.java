package com.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.TextView;
import com.constants.Constants;
import com.gaana.R;
import com.views.CircularProgressBar.a;

public class RateTextCircularProgressBar extends FrameLayout implements a {
    private CircularProgressBar a;
    private TextView b;

    public RateTextCircularProgressBar(Context context) {
        super(context);
        a(context);
    }

    public RateTextCircularProgressBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    private void a(Context context) {
        this.a = new CircularProgressBar(getContext());
        addView(this.a);
        LayoutParams layoutParams = new LayoutParams(-2, -2);
        layoutParams.gravity = 17;
        this.a.setLayoutParams(layoutParams);
        this.b = new TextView(getContext());
        addView(this.b);
        this.b.setLayoutParams(layoutParams);
        this.b.setGravity(17);
        if (Constants.l) {
            this.b.setTextColor(context.getResources().getColor(R.color.black));
        } else {
            this.b.setTextColor(context.getResources().getColor(R.color.white));
        }
        this.b.setTextSize(12.0f);
        this.a.setOnProgressChangeListener(this);
    }

    public void setMax(int i) {
        this.a.setMax(i);
    }

    public void setProgress(int i) {
        this.a.setProgress(i);
    }

    public CircularProgressBar getCircularProgressBar() {
        return this.a;
    }

    public void setTextSize(float f) {
        this.b.setTextSize(f);
    }

    public void setTextColor(int i) {
        this.b.setTextColor(i);
    }

    public void a(int i, int i2, float f) {
        this.b.setText(String.valueOf((int) (f * 100.0f)));
    }
}
