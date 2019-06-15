package com.b;

import android.content.Context;
import android.content.ContextWrapper;
import android.view.LayoutInflater;

public class b extends ContextWrapper {
    private d a;
    private final int b = a.a().h();

    public static ContextWrapper a(Context context) {
        return new b(context);
    }

    b(Context context) {
        super(context);
    }

    public Object getSystemService(String str) {
        if (!"layout_inflater".equals(str)) {
            return super.getSystemService(str);
        }
        if (this.a == null) {
            this.a = new d(LayoutInflater.from(getBaseContext()), this, this.b, false);
        }
        return this.a;
    }
}
