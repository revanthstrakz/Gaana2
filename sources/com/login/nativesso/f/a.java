package com.login.nativesso.f;

import android.content.Context;
import com.android.volley.Request;
import com.android.volley.c;
import com.android.volley.h;
import com.android.volley.toolbox.o;

public class a {
    private static a c = new a();
    private h a;
    private Context b;

    public static a a() {
        return c;
    }

    private a() {
    }

    public synchronized void a(Context context) {
        this.b = context;
        if (this.a == null) {
            this.a = o.a(this.b);
        }
    }

    public void a(Request request) {
        b();
        request.setRetryPolicy(new c(10000, 0, 1.0f));
        this.a.a(request);
    }

    private void b() {
        if (this.a == null) {
            this.a = o.a(this.b);
        }
    }
}
