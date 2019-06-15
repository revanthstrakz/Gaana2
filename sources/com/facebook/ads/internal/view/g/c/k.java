package com.facebook.ads.internal.view.g.c;

import android.content.Context;
import android.graphics.PorterDuff.Mode;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.ProgressBar;
import android.widget.RelativeLayout.LayoutParams;
import com.facebook.ads.internal.l.f;
import com.facebook.ads.internal.view.g.a.c;
import com.facebook.ads.internal.view.g.b.l;

public class k extends c {
    private final f<l> a;

    public k(Context context) {
        this(context, null);
    }

    public k(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public k(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.a = new f<l>() {
            public Class<l> a() {
                return l.class;
            }

            public void a(l lVar) {
                k.this.setVisibility(8);
            }
        };
        int applyDimension = (int) TypedValue.applyDimension(1, 40.0f, getResources().getDisplayMetrics());
        ProgressBar progressBar = new ProgressBar(getContext());
        progressBar.setIndeterminate(true);
        progressBar.getIndeterminateDrawable().setColorFilter(-1, Mode.SRC_IN);
        LayoutParams layoutParams = new LayoutParams(applyDimension, applyDimension);
        layoutParams.addRule(13);
        addView(progressBar, layoutParams);
    }

    /* Access modifiers changed, original: protected */
    public void a() {
        super.a();
        setVisibility(0);
        if (getVideoView() != null) {
            getVideoView().getEventBus().a(this.a);
        }
    }

    /* Access modifiers changed, original: protected */
    public void b() {
        if (getVideoView() != null) {
            getVideoView().getEventBus().b(this.a);
        }
        setVisibility(8);
        super.b();
    }
}
