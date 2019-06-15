package com.facebook.ads.internal.s.a;

import android.annotation.TargetApi;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.v4.view.ViewCompat;
import android.transition.AutoTransition;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.TextView;
import java.util.concurrent.atomic.AtomicInteger;

public class y {
    public static final DisplayMetrics a = Resources.getSystem().getDisplayMetrics();
    public static final float b = a.density;
    private static final AtomicInteger c = new AtomicInteger(1);

    public static int a() {
        int i;
        int i2;
        do {
            i = c.get();
            i2 = i + 1;
            if (i2 > ViewCompat.MEASURED_SIZE_MASK) {
                i2 = 1;
            }
        } while (!c.compareAndSet(i, i2));
        return i;
    }

    public static int a(int i) {
        return (int) TypedValue.applyDimension(2, (float) i, a);
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:6:0x0013 in {2, 4, 5} preds:[]
        	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.computeDominators(BlockProcessor.java:242)
        	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.processBlocksTree(BlockProcessor.java:52)
        	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.visit(BlockProcessor.java:42)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
        	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
        	at java.util.ArrayList.forEach(Unknown Source)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
        	at jadx.core.ProcessClass.process(ProcessClass.java:32)
        	at jadx.core.ProcessClass.lambda$processDependencies$0(ProcessClass.java:51)
        	at java.lang.Iterable.forEach(Unknown Source)
        	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:51)
        	at jadx.core.ProcessClass.process(ProcessClass.java:37)
        	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:292)
        	at jadx.api.JavaClass.decompile(JavaClass.java:62)
        	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
        */
    public static void a(android.view.View r2) {
        /*
        r0 = android.os.Build.VERSION.SDK_INT;
        r1 = 17;
        if (r0 < r1) goto L_0x000e;
        r0 = android.view.View.generateViewId();
        r2.setId(r0);
        return;
        r0 = a();
        goto L_0x000a;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.ads.internal.s.a.y.a(android.view.View):void");
    }

    public static void a(View view, int i) {
        if (VERSION.SDK_INT >= 16) {
            view.setBackground(new ColorDrawable(i));
        } else {
            view.setBackgroundDrawable(new ColorDrawable(i));
        }
    }

    public static void a(View view, Drawable drawable) {
        if (VERSION.SDK_INT >= 16) {
            view.setBackground(drawable);
        } else {
            view.setBackgroundDrawable(drawable);
        }
    }

    public static void a(ViewGroup viewGroup) {
        if (VERSION.SDK_INT > 19) {
            a(viewGroup, 200);
        }
    }

    public static void a(ViewGroup viewGroup, int i) {
        if (VERSION.SDK_INT > 19) {
            a(viewGroup, new AutoTransition(), i);
        }
    }

    public static void a(ViewGroup viewGroup, Transition transition) {
        if (VERSION.SDK_INT > 19) {
            a(viewGroup, transition, 200);
        }
    }

    @TargetApi(19)
    private static void a(ViewGroup viewGroup, Transition transition, int i) {
        transition.setDuration((long) i);
        transition.setInterpolator(new AccelerateDecelerateInterpolator());
        TransitionManager.beginDelayedTransition(viewGroup, transition);
    }

    public static void a(TextView textView, boolean z, int i) {
        Typeface typeface;
        int i2 = 0;
        if (!z) {
            typeface = Typeface.SANS_SERIF;
        } else if (VERSION.SDK_INT >= 21) {
            typeface = Typeface.create("sans-serif-medium", 0);
            textView.setTypeface(typeface);
            textView.setTextSize(2, (float) i);
        } else {
            typeface = Typeface.SANS_SERIF;
            i2 = 1;
        }
        typeface = Typeface.create(typeface, i2);
        textView.setTypeface(typeface);
        textView.setTextSize(2, (float) i);
    }

    public static void a(View... viewArr) {
        for (View b : viewArr) {
            b(b);
        }
    }

    public static void b(View view) {
        if (view != null) {
            ViewGroup viewGroup = (ViewGroup) view.getParent();
            if (viewGroup != null) {
                viewGroup.removeView(view);
            }
        }
    }

    public static void c(View view) {
        ViewParent parent = view.getParent();
        if (parent != null && (parent instanceof ViewGroup)) {
            a((ViewGroup) parent);
        }
    }
}
