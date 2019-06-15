package com.helpshift.views;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatButton;
import android.text.SpannableString;
import android.util.AttributeSet;
import android.widget.TextView;

public class HSButton extends AppCompatButton {
    public HSButton(Context context) {
        super(context);
        a();
    }

    public HSButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    public HSButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a();
    }

    private void a() {
        a.a((TextView) this);
    }

    public void setError(CharSequence charSequence, Drawable drawable) {
        if (charSequence == null) {
            super.setError(charSequence, drawable);
            return;
        }
        f a = a.a();
        if (a == null) {
            super.setError(charSequence, drawable);
            return;
        }
        SpannableString spannableString = new SpannableString(charSequence);
        spannableString.setSpan(a, 0, charSequence.length(), 17);
        super.setError(spannableString, drawable);
    }
}
