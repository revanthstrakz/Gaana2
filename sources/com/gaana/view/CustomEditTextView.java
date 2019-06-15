package com.gaana.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.v7.content.res.AppCompatResources;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;
import com.gaana.R;

public class CustomEditTextView extends AppCompatEditText {
    public CustomEditTextView(Context context) {
        super(context);
    }

    public CustomEditTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initAttrs(context, attributeSet);
    }

    public CustomEditTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        initAttrs(context, attributeSet);
    }

    /* Access modifiers changed, original: 0000 */
    public void initAttrs(Context context, AttributeSet attributeSet) {
        if (attributeSet != null) {
            Drawable drawable;
            Drawable drawable2;
            Drawable drawable3;
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.CustomTextView);
            Drawable drawable4 = null;
            if (VERSION.SDK_INT >= 21) {
                Drawable drawable5 = obtainStyledAttributes.getDrawable(3);
                Drawable drawable6 = obtainStyledAttributes.getDrawable(4);
                drawable = obtainStyledAttributes.getDrawable(2);
                drawable4 = obtainStyledAttributes.getDrawable(5);
                drawable2 = drawable6;
                drawable3 = drawable;
                drawable = drawable5;
            } else {
                int resourceId = obtainStyledAttributes.getResourceId(3, -1);
                int resourceId2 = obtainStyledAttributes.getResourceId(4, -1);
                int resourceId3 = obtainStyledAttributes.getResourceId(2, -1);
                int resourceId4 = obtainStyledAttributes.getResourceId(5, -1);
                drawable = resourceId != -1 ? AppCompatResources.getDrawable(context, resourceId) : null;
                drawable2 = resourceId2 != -1 ? AppCompatResources.getDrawable(context, resourceId2) : null;
                drawable3 = resourceId3 != -1 ? AppCompatResources.getDrawable(context, resourceId3) : null;
                if (resourceId4 != -1) {
                    drawable4 = AppCompatResources.getDrawable(context, resourceId4);
                }
            }
            setCompoundDrawablesWithIntrinsicBounds(drawable, drawable4, drawable2, drawable3);
            obtainStyledAttributes.recycle();
        }
    }
}
