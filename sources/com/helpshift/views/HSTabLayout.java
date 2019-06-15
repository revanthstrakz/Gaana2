package com.helpshift.views;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.util.AttributeSet;
import android.view.View;

public class HSTabLayout extends TabLayout {
    public HSTabLayout(Context context) {
        super(context);
        a();
    }

    public HSTabLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    public HSTabLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a();
    }

    private void a() {
        a.a((View) this);
    }
}
