package com.helpshift.views;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.TextView;
import com.helpshift.k.b;
import com.helpshift.util.l;
import java.lang.ref.WeakReference;

public final class a {
    private static Typeface a = null;
    private static f b = null;
    private static boolean c = false;

    private static class a implements OnGlobalLayoutListener {
        private final WeakReference<View> a;

        public a(View view) {
            this.a = new WeakReference(view);
        }

        public void onGlobalLayout() {
            View view = (View) this.a.get();
            if (view != null) {
                a.b(view);
            }
        }
    }

    public static void a(TextView textView) {
        a(textView.getContext());
        if (a != null) {
            textView.setTypeface(a);
        }
    }

    public static void a(Dialog dialog) {
        a(dialog.findViewById(16908290));
    }

    public static void a(View view) {
        a(view.getContext());
        if (a != null) {
            view.getViewTreeObserver().addOnGlobalLayoutListener(new a(view));
        }
    }

    static void b(View view) {
        if (view instanceof TextView) {
            a((TextView) view);
        } else if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                b(viewGroup.getChildAt(i));
            }
        }
    }

    @Nullable
    public static f a() {
        if (a != null && b == null) {
            b = new f(a);
        }
        return b;
    }

    @Nullable
    public static String b() {
        return b.a().a.a();
    }

    private static void a(Context context) {
        String b = b();
        if (b != null && a == null && !c) {
            try {
                a = Typeface.createFromAsset(context.getAssets(), b);
            } catch (Exception e) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Typeface initialisation failed. Using default typeface. ");
                stringBuilder.append(e.getMessage());
                l.c("HS_FontApplier", stringBuilder.toString());
            } catch (Throwable th) {
                c = true;
            }
            c = true;
        }
    }
}
