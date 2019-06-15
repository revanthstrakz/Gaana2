package com.voice;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.gaana.R;
import java.util.ArrayList;
import java.util.Iterator;

public class RippleBackground extends RelativeLayout {
    private int a;
    private float b;
    private float c;
    private int d;
    private int e;
    private int f;
    private float g;
    private int h;
    private int i;
    private Paint j;
    private boolean k = false;
    private AnimatorSet l;
    private ArrayList<Animator> m;
    private LayoutParams n;
    private ArrayList<a> o = new ArrayList();

    private class a extends View {
        public a(Context context) {
            super(context);
            setVisibility(4);
        }

        /* Access modifiers changed, original: protected */
        public void onDraw(Canvas canvas) {
            float min = (float) (Math.min(getWidth(), getHeight()) / 2);
            canvas.drawCircle(min, min, min - RippleBackground.this.b, RippleBackground.this.j);
        }
    }

    public RippleBackground(Context context) {
        super(context);
    }

    public RippleBackground(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context, attributeSet);
    }

    public RippleBackground(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context, attributeSet);
    }

    private void a(Context context, AttributeSet attributeSet) {
        if (!isInEditMode()) {
            if (attributeSet == null) {
                throw new IllegalArgumentException("Attributes should be provided to this view,");
            }
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.RippleBackground);
            this.a = obtainStyledAttributes.getColor(0, getResources().getColor(R.color.new_gaana_red));
            this.b = obtainStyledAttributes.getDimension(6, getResources().getDimension(R.dimen.rippleStrokeWidth));
            this.c = obtainStyledAttributes.getDimension(2, getResources().getDimension(R.dimen.rippleRadius));
            this.d = obtainStyledAttributes.getInt(1, 3000);
            this.e = obtainStyledAttributes.getInt(3, 6);
            this.g = obtainStyledAttributes.getFloat(5, 6.0f);
            this.h = obtainStyledAttributes.getInt(7, 0);
            this.i = obtainStyledAttributes.getInt(4, 1);
            obtainStyledAttributes.recycle();
            this.j = new Paint();
            this.j.setAntiAlias(true);
            if (this.h == 0) {
                this.b = 0.0f;
                this.j.setStyle(Style.FILL);
            } else {
                this.j.setStyle(Style.STROKE);
            }
            this.j.setColor(this.a);
            this.n = new LayoutParams((int) ((this.c + this.b) * 2.0f), (int) (2.0f * (this.c + this.b)));
            this.n.addRule(12, -1);
            this.n.addRule(14, -1);
            if (this.i == 1) {
                this.f = this.d / this.e;
                this.l = new AnimatorSet();
                this.l.setInterpolator(new AccelerateDecelerateInterpolator());
                this.m = new ArrayList();
                for (int i = 0; i < this.e; i++) {
                    a aVar = new a(getContext());
                    addView(aVar, this.n);
                    this.o.add(aVar);
                    ObjectAnimator ofFloat = ObjectAnimator.ofFloat(aVar, "ScaleX", new float[]{1.0f, this.g});
                    ofFloat.setRepeatCount(-1);
                    ofFloat.setRepeatMode(1);
                    ofFloat.setStartDelay((long) (this.f * i));
                    ofFloat.setDuration((long) this.d);
                    this.m.add(ofFloat);
                    ofFloat = ObjectAnimator.ofFloat(aVar, "ScaleY", new float[]{1.0f, this.g});
                    ofFloat.setRepeatCount(-1);
                    ofFloat.setRepeatMode(1);
                    ofFloat.setStartDelay((long) (this.f * i));
                    ofFloat.setDuration((long) this.d);
                    this.m.add(ofFloat);
                    ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(aVar, "Alpha", new float[]{1.0f, 0.0f});
                    ofFloat2.setRepeatCount(-1);
                    ofFloat2.setRepeatMode(1);
                    ofFloat2.setStartDelay((long) (this.f * i));
                    ofFloat2.setDuration((long) this.d);
                    this.m.add(ofFloat2);
                }
                this.l.playTogether(this.m);
            } else {
                a aVar2 = new a(getContext());
                addView(aVar2, this.n);
                aVar2.setVisibility(0);
            }
        }
    }

    public void a() {
        if (!b()) {
            Iterator it = this.o.iterator();
            while (it.hasNext()) {
                ((a) it.next()).setVisibility(0);
            }
            this.l.start();
            this.k = true;
        }
    }

    public boolean b() {
        return this.k;
    }
}
