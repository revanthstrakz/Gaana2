package com.helpshift.views;

import android.content.Context;
import android.graphics.Rect;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.widget.Toast;

public class e {
    private Context a;
    private View b;
    private String c;

    public e(View view, String str) {
        this.b = view;
        this.a = view.getContext();
        this.c = str;
    }

    public void a() {
        int[] iArr = new int[2];
        Rect rect = new Rect();
        this.b.getLocationOnScreen(iArr);
        this.b.getWindowVisibleDisplayFrame(rect);
        int width = this.b.getWidth();
        int height = this.b.getHeight();
        int i = iArr[0] + (width / 2);
        int i2 = iArr[1] + (height / 2);
        if (ViewCompat.getLayoutDirection(this.b) == 0) {
            i = this.a.getResources().getDisplayMetrics().widthPixels - i;
        }
        Toast a = d.a(this.a, this.c, 0);
        if (i2 < rect.height()) {
            a.setGravity(8388661, i, i2);
        } else {
            a.setGravity(81, 0, height);
        }
        a.show();
    }
}
