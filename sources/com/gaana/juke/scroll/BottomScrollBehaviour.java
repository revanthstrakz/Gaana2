package com.gaana.juke.scroll;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.CoordinatorLayout.Behavior;
import android.util.AttributeSet;
import android.view.View;

public class BottomScrollBehaviour<T extends View> extends Behavior<T> {
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull T t, @NonNull View view, @NonNull View view2, int i, int i2) {
        return i == 2;
    }

    public BottomScrollBehaviour(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void onNestedPreScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull T t, @NonNull View view, int i, int i2, @NonNull int[] iArr, int i3) {
        super.onNestedPreScroll(coordinatorLayout, t, view, i, i2, iArr, i3);
        t.setTranslationY(Math.max(0.0f, Math.min((float) t.getHeight(), t.getTranslationY() + ((float) i2))));
    }
}
