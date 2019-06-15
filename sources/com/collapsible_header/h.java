package com.collapsible_header;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;
import com.collapsible_header.SlidingTabLayout.d;

class h extends LinearLayout {
    private final int a;
    private final Paint b;
    private final int c;
    private final Paint d;
    private final int e;
    private int f;
    private float g;
    private d h;
    private final a i;

    private static class a implements d {
        private int[] a;

        private a() {
        }

        public final int a(int i) {
            return this.a[i % this.a.length];
        }

        /* Access modifiers changed, original: varargs */
        public void a(int... iArr) {
            this.a = iArr;
        }
    }

    h(Context context) {
        this(context, null);
    }

    h(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setWillNotDraw(false);
        float f = getResources().getDisplayMetrics().density;
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(16842800, typedValue, true);
        this.e = a(typedValue.data, (byte) 38);
        this.i = new a();
        this.i.a(-13388315);
        this.a = (int) (0.0f * f);
        this.b = new Paint();
        this.b.setColor(this.e);
        this.c = (int) (3.0f * f);
        this.d = new Paint();
    }

    /* Access modifiers changed, original: 0000 */
    public void a(d dVar) {
        this.h = dVar;
        invalidate();
    }

    /* Access modifiers changed, original: varargs */
    public void a(int... iArr) {
        this.h = null;
        this.i.a(iArr);
        invalidate();
    }

    /* Access modifiers changed, original: 0000 */
    public void a(int i, float f) {
        this.f = i;
        this.g = f;
        invalidate();
    }

    /* Access modifiers changed, original: protected */
    public void onDraw(Canvas canvas) {
        int height = getHeight();
        int childCount = getChildCount();
        d dVar = this.h != null ? this.h : this.i;
        if (childCount > 0) {
            View childAt = getChildAt(this.f);
            int left = childAt.getLeft();
            childCount = childAt.getRight();
            int a = dVar.a(this.f);
            if (this.g > 0.0f && this.f < getChildCount() - 1) {
                int a2 = dVar.a(this.f + 1);
                if (a != a2) {
                    a = a(a2, a, this.g);
                }
                View childAt2 = getChildAt(this.f + 1);
                left = (int) ((this.g * ((float) childAt2.getLeft())) + ((1.0f - this.g) * ((float) left)));
                childCount = (int) ((this.g * ((float) childAt2.getRight())) + ((1.0f - this.g) * ((float) childCount)));
            }
            this.d.setColor(a);
            canvas.drawRect((float) left, (float) (height - this.c), (float) childCount, (float) height, this.d);
        }
        canvas.drawRect(0.0f, (float) (height - this.a), (float) getWidth(), (float) height, this.b);
    }

    private static int a(int i, byte b) {
        return Color.argb(b, Color.red(i), Color.green(i), Color.blue(i));
    }

    private static int a(int i, int i2, float f) {
        float f2 = 1.0f - f;
        return Color.rgb((int) ((((float) Color.red(i)) * f) + (((float) Color.red(i2)) * f2)), (int) ((((float) Color.green(i)) * f) + (((float) Color.green(i2)) * f2)), (int) ((((float) Color.blue(i)) * f) + (((float) Color.blue(i2)) * f2)));
    }
}
