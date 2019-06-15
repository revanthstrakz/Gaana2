package com.helpshift.support.util;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public class e {
    public static void a(Context context, View view) {
        ((InputMethodManager) context.getSystemService("input_method")).hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static void b(Context context, View view) {
        ((InputMethodManager) context.getSystemService("input_method")).showSoftInput(view, 1);
    }
}
