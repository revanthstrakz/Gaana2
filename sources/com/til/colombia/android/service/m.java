package com.til.colombia.android.service;

import com.til.colombia.android.internal.Log;
import com.til.colombia.android.internal.a.h;
import com.til.colombia.android.internal.i;
import org.json.JSONArray;
import org.json.JSONException;

final class m {
    boolean a;
    Exception b;
    boolean c;
    JSONArray d;

    public m(String str) {
        this.a = false;
        this.b = null;
        this.c = false;
        if (h.a(str)) {
            this.c = false;
            return;
        }
        try {
            this.d = new JSONArray(str);
            this.c = true;
        } catch (JSONException e) {
            Log.a(i.f, "Error in parsing item Json", e);
        }
    }

    public m(boolean z) {
        this.a = false;
        this.b = null;
        this.c = false;
        this.a = true;
    }

    public m(boolean z, Exception exception) {
        this.a = false;
        this.b = null;
        this.c = false;
        this.a = true;
        this.b = exception;
    }

    public final JSONArray a() {
        return this.d;
    }

    public final Exception b() {
        return this.b;
    }

    public final boolean c() {
        return this.a;
    }

    public final boolean d() {
        return this.c;
    }

    public final void a(boolean z) {
        this.c = false;
    }
}
