package com.helpshift.views;

import android.content.Context;
import android.support.design.widget.TextInputEditText;
import android.util.AttributeSet;
import android.widget.TextView;

public class HSTextInputEditText extends TextInputEditText {
    public HSTextInputEditText(Context context) {
        super(context);
        a();
    }

    public HSTextInputEditText(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    public HSTextInputEditText(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a();
    }

    private void a() {
        setGravity(51);
        a.a((TextView) this);
    }
}
