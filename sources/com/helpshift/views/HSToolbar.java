package com.helpshift.views;

import android.content.Context;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.View;

public class HSToolbar extends Toolbar {
    public HSToolbar(Context context) {
        super(context);
        a();
    }

    public HSToolbar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    public HSToolbar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a();
    }

    private void a() {
        a.a((View) this);
    }
}
