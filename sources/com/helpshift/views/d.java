package com.helpshift.views;

import android.content.Context;
import android.content.res.Resources.NotFoundException;
import android.widget.Toast;

public class d {
    public static Toast a(Context context, CharSequence charSequence, int i) {
        Toast makeText = Toast.makeText(context, charSequence, i);
        a.a(makeText.getView());
        return makeText;
    }

    public static Toast a(Context context, int i, int i2) throws NotFoundException {
        return a(context, context.getResources().getText(i), i2);
    }
}
