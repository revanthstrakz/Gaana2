package com.helpshift.views;

import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.view.View;

public class c {
    @NonNull
    public static Snackbar a(View view, CharSequence charSequence, int i) {
        Snackbar make = Snackbar.make(view, charSequence, i);
        a.a(make.getView());
        return make;
    }

    @NonNull
    public static Snackbar a(View view, int i, int i2) {
        return a(view, view.getResources().getText(i), i2);
    }
}
