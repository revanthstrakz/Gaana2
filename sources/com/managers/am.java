package com.managers;

import android.text.TextUtils;
import com.constants.Constants;
import com.services.d;

public class am {
    private static am a;
    private d b = d.a();

    private am() {
    }

    public static am a() {
        if (a == null) {
            a = new am();
        }
        return a;
    }

    public String b() {
        String c = this.b.c("PREFERENCE_KEY_GAANAAPP_VERSION", false);
        if (!TextUtils.isEmpty(c)) {
            return c;
        }
        c = String.valueOf(Constants.cA);
        this.b.a("PREFERENCE_KEY_GAANAAPP_VERSION", Constants.cz, false);
        return c;
    }

    public int c() {
        int b = this.b.b("PREFERENCE_KEY_GAANAAPP_VERSION_CODE", 0, false);
        if (b != 0) {
            return b;
        }
        b = Constants.cA;
        this.b.a("PREFERENCE_KEY_GAANAAPP_VERSION_CODE", Constants.cA, false);
        return b;
    }
}
