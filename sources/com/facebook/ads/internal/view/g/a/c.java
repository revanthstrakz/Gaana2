package com.facebook.ads.internal.view.g.a;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.facebook.ads.internal.view.g.a;

public abstract class c extends RelativeLayout implements b {
    @Nullable
    private a a;

    public c(Context context) {
        super(context);
    }

    public c(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        setLayoutParams(new LayoutParams(-1, -1));
    }

    /* Access modifiers changed, original: protected */
    public void a() {
    }

    public void a(a aVar) {
        this.a = aVar;
        a();
    }

    /* Access modifiers changed, original: protected */
    public void b() {
    }

    public void b(a aVar) {
        b();
        this.a = null;
    }

    /* Access modifiers changed, original: protected */
    @Nullable
    public a getVideoView() {
        return this.a;
    }
}
