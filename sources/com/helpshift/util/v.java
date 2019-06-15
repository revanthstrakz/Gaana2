package com.helpshift.util;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewCompat;
import com.helpshift.e.b;

public class v {
    public static int a(Context context, int i) {
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(new int[]{i});
        i = obtainStyledAttributes.getColor(0, -1);
        obtainStyledAttributes.recycle();
        return i;
    }

    public static String b(Context context, int i) {
        return a(a(context, i));
    }

    public static String a(int i) {
        return String.format("#%06X", new Object[]{Integer.valueOf(i & ViewCompat.MEASURED_SIZE_MASK)});
    }

    public static void a(Context context, Drawable drawable) {
        a(context, drawable, b.hs__actionButtonIconColor);
    }

    public static void a(Context context, Drawable drawable, int i) {
        if (drawable != null) {
            drawable.setColorFilter(a(context, i), Mode.SRC_ATOP);
        }
    }

    public static void a(Drawable drawable, int i) {
        if (drawable != null) {
            drawable.setColorFilter(i, Mode.SRC_ATOP);
        }
    }

    public static float a(Context context, float f) {
        return f * context.getResources().getDisplayMetrics().density;
    }
}
