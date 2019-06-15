package com.bumptech.glide.load.resource.b;

import android.content.Context;
import android.content.res.Resources.Theme;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.content.res.AppCompatResources;

public final class a {
    private static volatile boolean a = true;

    public static Drawable a(Context context, @DrawableRes int i) {
        return a(context, i, null);
    }

    public static Drawable a(Context context, @DrawableRes int i, @Nullable Theme theme) {
        try {
            if (a) {
                return b(context, i);
            }
        } catch (NoClassDefFoundError unused) {
            a = false;
        }
        if (theme == null) {
            theme = context.getTheme();
        }
        return b(context, i, theme);
    }

    private static Drawable b(Context context, @DrawableRes int i) {
        return AppCompatResources.getDrawable(context, i);
    }

    private static Drawable b(Context context, @DrawableRes int i, @Nullable Theme theme) {
        return ResourcesCompat.getDrawable(context.getResources(), i, theme);
    }
}
