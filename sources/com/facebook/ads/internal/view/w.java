package com.facebook.ads.internal.view;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup.LayoutParams;

public class w extends View {
    private v a;

    public w(Context context, v vVar) {
        super(context);
        this.a = vVar;
        setLayoutParams(new LayoutParams(0, 0));
    }

    public void onWindowVisibilityChanged(int i) {
        if (this.a != null) {
            this.a.a(i);
        }
    }
}
