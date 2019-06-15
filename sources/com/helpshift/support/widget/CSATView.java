package com.helpshift.support.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.RelativeLayout;
import com.helpshift.e.f;
import com.helpshift.e.h;

public class CSATView extends RelativeLayout implements OnRatingBarChangeListener {
    private a a;
    private RatingBar b;
    private a c = null;

    public interface a {
        void a(int i, String str);
    }

    public CSATView(Context context) {
        super(context);
        a(context);
    }

    public CSATView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    public CSATView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context);
    }

    private void a(Context context) {
        View.inflate(context, h.hs__csat_view, this);
        this.a = new a(context);
    }

    /* Access modifiers changed, original: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
        this.b = (RatingBar) findViewById(f.ratingBar);
        com.helpshift.support.util.h.c(getContext(), this.b.getProgressDrawable());
        this.b.setOnRatingBarChangeListener(this);
    }

    public void onRatingChanged(RatingBar ratingBar, float f, boolean z) {
        if (z) {
            this.a.a(this);
        }
    }

    /* Access modifiers changed, original: protected */
    public RatingBar getRatingBar() {
        return this.b;
    }

    /* Access modifiers changed, original: protected */
    public void a() {
        setVisibility(8);
        this.a = null;
    }

    /* Access modifiers changed, original: protected */
    public void a(float f, String str) {
        if (this.c != null) {
            this.c.a(Math.round(f), str);
        }
    }

    public void setCSATListener(a aVar) {
        this.c = aVar;
    }
}
