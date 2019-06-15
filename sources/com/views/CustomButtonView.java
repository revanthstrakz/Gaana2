package com.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.v7.content.res.AppCompatResources;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import com.gaana.R;

public class CustomButtonView extends AppCompatButton {
    public CustomButtonView(Context context) {
        super(context);
    }

    public CustomButtonView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context, attributeSet);
    }

    public CustomButtonView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context, attributeSet);
    }

    /* Access modifiers changed, original: 0000 */
    public void a(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.CustomTextView);
        if (VERSION.SDK_INT >= 21 || attributeSet == null) {
            setCompoundDrawablesWithIntrinsicBounds(obtainStyledAttributes.getDrawable(3), obtainStyledAttributes.getDrawable(5), obtainStyledAttributes.getDrawable(4), obtainStyledAttributes.getDrawable(2));
            obtainStyledAttributes.recycle();
            return;
        }
        int resourceId = obtainStyledAttributes.getResourceId(3, -1);
        int resourceId2 = obtainStyledAttributes.getResourceId(4, -1);
        int resourceId3 = obtainStyledAttributes.getResourceId(2, -1);
        int resourceId4 = obtainStyledAttributes.getResourceId(5, -1);
        Drawable drawable = null;
        Drawable drawable2 = resourceId != -1 ? AppCompatResources.getDrawable(context, resourceId) : null;
        Drawable drawable3 = resourceId2 != -1 ? AppCompatResources.getDrawable(context, resourceId2) : null;
        Drawable drawable4 = resourceId3 != -1 ? AppCompatResources.getDrawable(context, resourceId3) : null;
        if (resourceId4 != -1) {
            drawable = AppCompatResources.getDrawable(context, resourceId4);
        }
        if (!(resourceId == -1 && resourceId2 == -1 && resourceId4 == -1 && resourceId3 == -1)) {
            setCompoundDrawablesWithIntrinsicBounds(drawable2, drawable, drawable3, drawable4);
        }
        obtainStyledAttributes.recycle();
    }
}
