package com.helpshift.util;

import android.content.Context;
import android.os.Build.VERSION;
import android.os.LocaleList;
import android.text.TextUtils;
import com.helpshift.k.b;
import java.util.Locale;

public class r {
    public static String a() {
        String g = b.a().b.g();
        return TextUtils.isEmpty(g) ? Locale.getDefault().toString() : g;
    }

    public static String a(Context context) {
        Locale locale;
        String str = "";
        if (VERSION.SDK_INT >= 24) {
            LocaleList locales = context.getResources().getConfiguration().getLocales();
            locale = locales.size() > 0 ? locales.get(0) : null;
        } else {
            locale = context.getResources().getConfiguration().locale;
        }
        return locale != null ? locale.getCountry() : str;
    }
}
