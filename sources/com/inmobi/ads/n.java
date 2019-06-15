package com.inmobi.ads;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.support.annotation.Nullable;
import android.view.View;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class n {
    private static final String c = "n";
    List<a> a = new ArrayList();
    boolean b = false;

    class a {
        Animator a;
        long b;
        boolean c;

        a(Animator animator) {
            this.a = animator;
        }
    }

    /* Access modifiers changed, original: final */
    public final List<a> a(final View view, ak akVar) {
        LinkedList linkedList = new LinkedList();
        try {
            Animator ofFloat;
            final com.inmobi.ads.NativeContainerLayout.a aVar;
            if (((float) NativeViewFactory.c(akVar.c.c.x)) != ((float) NativeViewFactory.c(akVar.c.d.x))) {
                ofFloat = ValueAnimator.ofFloat(new float[]{(float) ((int) ((float) NativeViewFactory.c(akVar.c.c.x))), (float) ((int) ((float) NativeViewFactory.c(akVar.c.d.x)))});
                aVar = (com.inmobi.ads.NativeContainerLayout.a) view.getLayoutParams();
                ofFloat.addUpdateListener(new AnimatorUpdateListener() {
                    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                        aVar.a = ((Float) valueAnimator.getAnimatedValue()).intValue();
                        view.setLayoutParams(aVar);
                        view.requestLayout();
                    }
                });
                linkedList.add(a(ofFloat, akVar));
            }
            if (((float) NativeViewFactory.c(akVar.c.c.y)) != ((float) NativeViewFactory.c(akVar.c.d.y))) {
                ofFloat = ValueAnimator.ofFloat(new float[]{(float) ((int) ((float) NativeViewFactory.c(akVar.c.c.y))), (float) ((int) ((float) NativeViewFactory.c(akVar.c.d.y)))});
                aVar = (com.inmobi.ads.NativeContainerLayout.a) view.getLayoutParams();
                ofFloat.addUpdateListener(new AnimatorUpdateListener() {
                    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                        aVar.b = ((Float) valueAnimator.getAnimatedValue()).intValue();
                        view.setLayoutParams(aVar);
                        view.requestLayout();
                    }
                });
                linkedList.add(a(ofFloat, akVar));
            }
            float c = (float) NativeViewFactory.c(akVar.c.a.x);
            float c2 = (float) NativeViewFactory.c(akVar.c.b.x);
            if (c != c2) {
                linkedList.add(a(a(view, "scaleX", c, c2), akVar));
            }
            c = (float) NativeViewFactory.c(akVar.c.a.y);
            c2 = (float) NativeViewFactory.c(akVar.c.b.y);
            if (c != c2) {
                linkedList.add(a(a(view, "scaleY", c, c2), akVar));
            }
        } catch (Exception unused) {
        }
        return linkedList.isEmpty() ? null : linkedList;
    }

    private static Animator a(View view, String str, float f, float f2) {
        f2 /= f;
        view.setPivotX(0.0f);
        view.setPivotY(0.0f);
        return ObjectAnimator.ofFloat(view, str, new float[]{f2});
    }

    /* Access modifiers changed, original: final */
    public final void a(@Nullable List<a> list) {
        if (list != null) {
            for (a aVar : list) {
                if (!aVar.c) {
                    ValueAnimator valueAnimator = (ValueAnimator) aVar.a;
                    valueAnimator.setCurrentPlayTime(aVar.b);
                    valueAnimator.start();
                }
                if (!this.a.contains(aVar)) {
                    this.a.add(aVar);
                }
            }
        }
    }

    private a a(Animator animator, ak akVar) {
        animator.setDuration(0);
        animator.setStartDelay(0);
        ba g = akVar.c.g();
        if (g != null) {
            com.inmobi.ads.ba.a aVar = g.a;
            com.inmobi.ads.ba.a aVar2 = g.b;
            if (aVar2 != null) {
                animator.setDuration(aVar2.a() * 1000);
            }
            if (aVar != null) {
                animator.setStartDelay(aVar.a() * 1000);
            }
        }
        return new a(animator);
    }
}
