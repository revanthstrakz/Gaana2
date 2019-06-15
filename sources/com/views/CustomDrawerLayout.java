package com.views;

import android.content.Context;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.DrawerLayout.LayoutParams;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;

public class CustomDrawerLayout extends DrawerLayout {
    private boolean a = false;

    public CustomDrawerLayout(Context context) {
        super(context);
    }

    public CustomDrawerLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public CustomDrawerLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void requestDisallowInterceptTouchEvent(boolean z) {
        this.a = z;
        super.requestDisallowInterceptTouchEvent(z);
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getPointerCount() <= 1 || !this.a) {
            return super.dispatchTouchEvent(motionEvent);
        }
        requestDisallowInterceptTouchEvent(false);
        boolean dispatchTouchEvent = super.dispatchTouchEvent(motionEvent);
        requestDisallowInterceptTouchEvent(true);
        return dispatchTouchEvent;
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        try {
            return super.onInterceptTouchEvent(motionEvent);
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            return false;
        }
    }

    /* Access modifiers changed, original: protected */
    public void onMeasure(int i, int i2) {
        int mode = MeasureSpec.getMode(i);
        int mode2 = MeasureSpec.getMode(i2);
        int size = MeasureSpec.getSize(i);
        int size2 = MeasureSpec.getSize(i2);
        if (mode == 1073741824 && mode2 == 1073741824) {
            setMeasuredDimension(size, size2);
            mode = getChildCount();
            for (int i3 = 0; i3 < mode; i3++) {
                View childAt = getChildAt(i3);
                if (childAt.getVisibility() != 8) {
                    LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                    StringBuilder stringBuilder;
                    if (isContentView(childAt)) {
                        childAt.measure(MeasureSpec.makeMeasureSpec((size - layoutParams.leftMargin) - layoutParams.rightMargin, 1073741824), MeasureSpec.makeMeasureSpec((size2 - layoutParams.topMargin) - layoutParams.bottomMargin, 1073741824));
                    } else if (isDrawerView(childAt)) {
                        int a = a(childAt) & 7;
                        if ((0 & a) != 0) {
                            stringBuilder = new StringBuilder();
                            stringBuilder.append("Child drawer has absolute gravity ");
                            stringBuilder.append(a(a));
                            stringBuilder.append(" but this already has a ");
                            stringBuilder.append("drawer view along that edge");
                            throw new IllegalStateException(stringBuilder.toString());
                        }
                        childAt.measure(getChildMeasureSpec(i, (layoutParams.leftMargin + 0) + layoutParams.rightMargin, layoutParams.width), getChildMeasureSpec(i2, layoutParams.topMargin + layoutParams.bottomMargin, layoutParams.height));
                    } else {
                        stringBuilder = new StringBuilder();
                        stringBuilder.append("Child ");
                        stringBuilder.append(childAt);
                        stringBuilder.append(" at index ");
                        stringBuilder.append(i3);
                        stringBuilder.append(" does not have a valid layout_gravity - must be Gravity.LEFT, ");
                        stringBuilder.append("Gravity.RIGHT or Gravity.NO_GRAVITY");
                        throw new IllegalStateException(stringBuilder.toString());
                    }
                }
            }
            return;
        }
        throw new IllegalArgumentException("DrawerLayout must be measured with MeasureSpec.EXACTLY.");
    }

    /* Access modifiers changed, original: 0000 */
    public boolean isContentView(View view) {
        return ((LayoutParams) view.getLayoutParams()).gravity == 0;
    }

    /* Access modifiers changed, original: 0000 */
    public boolean isDrawerView(View view) {
        return (GravityCompat.getAbsoluteGravity(((LayoutParams) view.getLayoutParams()).gravity, ViewCompat.getLayoutDirection(view)) & 7) != 0;
    }

    /* Access modifiers changed, original: 0000 */
    public int a(View view) {
        return GravityCompat.getAbsoluteGravity(((LayoutParams) view.getLayoutParams()).gravity, ViewCompat.getLayoutDirection(view));
    }

    static String a(int i) {
        if ((i & 3) == 3) {
            return "LEFT";
        }
        return (i & 5) == 5 ? "RIGHT" : Integer.toHexString(i);
    }
}
