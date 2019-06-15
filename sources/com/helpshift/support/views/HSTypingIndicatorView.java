package com.helpshift.support.views;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.animation.LinearInterpolator;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import com.helpshift.e.l;
import java.util.Iterator;

public class HSTypingIndicatorView extends LinearLayout {
    Animator[] a;
    AnimatorSet b;
    private final long c;
    private final long d;
    private float e;
    private DotView[] f;
    private int g;
    private float h;

    public HSTypingIndicatorView(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public HSTypingIndicatorView(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.c = 900;
        this.d = 450;
        this.a = new Animator[3];
        a(context, attributeSet);
        c();
    }

    private void a(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, l.HSTypingIndicatorView, 0, 0);
        int color = obtainStyledAttributes.getColor(l.HSTypingIndicatorView_hs__dotColor, 0);
        this.g = Color.argb(76, Color.red(color), Color.green(color), Color.blue(color));
        this.h = obtainStyledAttributes.getDimension(l.HSTypingIndicatorView_hs__interDotPadding, 0.0f);
        this.e = obtainStyledAttributes.getDimension(l.HSTypingIndicatorView_hs__dotDiameter, 0.0f);
        obtainStyledAttributes.recycle();
    }

    /* Access modifiers changed, original: protected */
    public void onWindowVisibilityChanged(int i) {
        super.onWindowVisibilityChanged(i);
        if (i == 0) {
            a();
        } else {
            b();
        }
    }

    private void a() {
        if (this.b == null) {
            this.b = new AnimatorSet();
            this.b.playTogether(this.a);
            this.b.addListener(new AnimatorListener() {
                public void onAnimationCancel(Animator animator) {
                }

                public void onAnimationRepeat(Animator animator) {
                }

                public void onAnimationStart(Animator animator) {
                }

                public void onAnimationEnd(Animator animator) {
                    animator.setStartDelay(450);
                    animator.start();
                }
            });
            this.b.start();
        }
    }

    private void b() {
        if (this.b != null) {
            Iterator it = this.b.getChildAnimations().iterator();
            while (it.hasNext()) {
                ((Animator) it.next()).cancel();
            }
            this.b.cancel();
            this.b = null;
            for (DotView dotColor : this.f) {
                dotColor.setDotColor(this.g);
            }
        }
    }

    private void c() {
        this.f = new DotView[3];
        for (int i = 0; i < 3; i++) {
            this.f[i] = new DotView(getContext(), this.g);
            float f = this.h / 2.0f;
            float f2 = this.h / 2.0f;
            long j = 0;
            switch (i) {
                case 0:
                    f = 0.0f;
                    break;
                case 1:
                    j = 225;
                    break;
                case 2:
                    j = 450;
                    f2 = 0.0f;
                    break;
                default:
                    break;
            }
            LayoutParams layoutParams = new LayoutParams((int) this.e, (int) this.e);
            layoutParams.setMargins((int) f, 0, (int) f2, 0);
            addView(this.f[i], layoutParams);
            this.a[i] = a(j, this.f[i]);
        }
    }

    public ValueAnimator a(long j, AnimatorUpdateListener animatorUpdateListener) {
        ValueAnimator ofInt = ValueAnimator.ofInt(new int[]{76, 179, 76});
        ofInt.setStartDelay(j);
        ofInt.setDuration(900);
        ofInt.setInterpolator(new LinearInterpolator());
        ofInt.addUpdateListener(animatorUpdateListener);
        return ofInt;
    }
}
