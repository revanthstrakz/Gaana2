package com.til.colombia.android.internal.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.StateListDrawable;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.widget.FrameLayout;
import com.til.colombia.android.R;
import com.til.colombia.android.commons.CommonUtil;

public class CloseableLayout extends FrameLayout {
    static final float a = 27.0f;
    static final float b = 50.0f;
    static final float c = 5.0f;
    public a d;
    private final int e;
    private final StateListDrawable f = new StateListDrawable();
    private ClosePosition g = ClosePosition.TOP_RIGHT;
    private final int h;
    private final int i;
    private final int j;
    private boolean k;
    private final Rect l = new Rect();
    private final Rect m = new Rect();
    private final Rect n = new Rect();
    private final Rect o = new Rect();
    private b p;

    public enum ClosePosition {
        TOP_LEFT(51),
        TOP_CENTER(49),
        TOP_RIGHT(53),
        CENTER(17),
        BOTTOM_LEFT(83),
        BOTTOM_CENTER(81),
        BOTTOM_RIGHT(85);
        
        private final int mGravity;

        private ClosePosition(int i) {
            this.mGravity = i;
        }

        /* Access modifiers changed, original: final */
        public final int getGravity() {
            return this.mGravity;
        }
    }

    public interface a {
        void a();
    }

    private final class b implements Runnable {
        private b() {
        }

        /* synthetic */ b(CloseableLayout closeableLayout, byte b) {
            this();
        }

        public final void run() {
            CloseableLayout.this.b(false);
        }
    }

    public CloseableLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        if (VERSION.SDK_INT >= 21) {
            this.f.addState(SELECTED_STATE_SET, context.getDrawable(R.drawable.close_pressed));
            this.f.addState(EMPTY_STATE_SET, context.getDrawable(R.drawable.close_normal));
        } else {
            this.f.addState(SELECTED_STATE_SET, context.getResources().getDrawable(R.drawable.close_pressed));
            this.f.addState(EMPTY_STATE_SET, context.getResources().getDrawable(R.drawable.close_normal));
        }
        this.f.setState(EMPTY_STATE_SET);
        this.f.setCallback(this);
        this.e = ViewConfiguration.get(context).getScaledTouchSlop();
        this.h = CommonUtil.a((float) b, context);
        this.i = CommonUtil.a((float) a, context);
        this.j = CommonUtil.a((float) c, context);
        setWillNotDraw(false);
    }

    public final void a(a aVar) {
        this.d = aVar;
    }

    private void a(ClosePosition closePosition) {
        this.g = closePosition;
        this.k = true;
        invalidate();
    }

    public final void a(boolean z) {
        if (this.f.setVisible(z, false)) {
            invalidate(this.m);
        }
    }

    /* Access modifiers changed, original: protected */
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.k = true;
    }

    public void draw(@NonNull Canvas canvas) {
        super.draw(canvas);
        if (this.k) {
            this.k = false;
            this.l.set(0, 0, getWidth(), getHeight());
            a(this.g, this.h, this.l, this.m);
            this.o.set(this.m);
            this.o.inset(this.j, this.j);
            a(this.g, this.i, this.o, this.n);
            this.f.setBounds(this.n);
        }
        if (this.f.isVisible()) {
            this.f.draw(canvas);
        }
    }

    private void a(ClosePosition closePosition, Rect rect, Rect rect2) {
        a(closePosition, this.h, rect, rect2);
    }

    private void b(ClosePosition closePosition, Rect rect, Rect rect2) {
        a(closePosition, this.i, rect, rect2);
    }

    private static void a(ClosePosition closePosition, int i, Rect rect, Rect rect2) {
        Gravity.apply(closePosition.getGravity(), i, i, rect, rect2);
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() != 0) {
            return false;
        }
        return a((int) motionEvent.getX(), (int) motionEvent.getY(), 0);
    }

    private void b(boolean z) {
        if (z != a()) {
            this.f.setState(z ? SELECTED_STATE_SET : EMPTY_STATE_SET);
            invalidate(this.m);
        }
    }

    private boolean a() {
        return this.f.getState() == SELECTED_STATE_SET;
    }

    private boolean a(int i, int i2, int i3) {
        return i >= this.m.left - i3 && i2 >= this.m.top - i3 && i < this.m.right + i3 && i2 < this.m.bottom + i3;
    }

    private void b() {
        playSoundEffect(0);
        if (this.d != null) {
            this.d.a();
        }
    }

    private void a(Rect rect) {
        this.m.set(rect);
    }

    private Rect c() {
        return this.m;
    }

    private void c(boolean z) {
        this.k = z;
    }

    private boolean d() {
        return this.f.isVisible();
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!this.f.isVisible()) {
            return true;
        }
        if (a((int) motionEvent.getX(), (int) motionEvent.getY(), this.e)) {
            int action = motionEvent.getAction();
            if (action != 3) {
                switch (action) {
                    case 0:
                        b(true);
                        break;
                    case 1:
                        if (a()) {
                            if (this.p == null) {
                                this.p = new b(this, (byte) 0);
                            }
                            postDelayed(this.p, (long) ViewConfiguration.getPressedStateDuration());
                            playSoundEffect(0);
                            if (this.d != null) {
                                this.d.a();
                                break;
                            }
                        }
                        break;
                }
            }
            b(false);
            return true;
        }
        b(false);
        super.onTouchEvent(motionEvent);
        return false;
    }
}
