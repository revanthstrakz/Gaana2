package com.helpshift.views;

import android.content.Context;
import android.support.design.widget.TextInputLayout;
import android.util.AttributeSet;
import android.view.View;

public class HSTextInputLayout extends TextInputLayout {
    public HSTextInputLayout(Context context) {
        super(context);
        a();
    }

    public HSTextInputLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    public HSTextInputLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a();
    }

    private void a() {
        a.a((View) this);
    }
}
