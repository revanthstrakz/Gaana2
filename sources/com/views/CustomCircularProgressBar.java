package com.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import com.views.CircularProgressBar.a;

public class CustomCircularProgressBar extends FrameLayout implements a {
    private View a;
    private CircularProgressBar b;

    public void a(int i, int i2, float f) {
    }

    public CustomCircularProgressBar(Context context, View view) {
        super(context);
        a(context);
        this.a = view;
    }

    public CustomCircularProgressBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    private void a(Context context) {
        this.b = new CircularProgressBar(getContext());
        addView(this.b);
        this.b.setOnProgressChangeListener(this);
    }

    public void setExtraView(View view) {
        this.a = view;
        LayoutParams layoutParams = new LayoutParams(-2, -2);
        layoutParams.gravity = 17;
        this.b.setLayoutParams(layoutParams);
        if (this.a != null) {
            addView(this.a);
            this.a.setLayoutParams(layoutParams);
        }
    }

    public void setMax(int i) {
        this.b.setMax(i);
    }

    public void setProgress(int i) {
        this.b.setProgress(i);
    }

    public CircularProgressBar getCircularProgressBar() {
        return this.b;
    }

    public View getExtraView() {
        return this.a;
    }
}
