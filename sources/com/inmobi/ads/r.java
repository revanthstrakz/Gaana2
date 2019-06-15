package com.inmobi.ads;

import android.support.annotation.Nullable;
import android.util.Base64;
import com.inmobi.commons.core.network.d;
import com.inmobi.commons.core.utilities.a.b;

public final class r {
    private static final String d = "r";
    public byte[] a = b.a(16);
    public final byte[] b = b.a();
    public final byte[] c = b.a(8);

    r() {
    }

    /* Access modifiers changed, original: final */
    @Nullable
    public final String a(String str, String str2) {
        try {
            byte[] a = b.a(this.b);
            return new String(Base64.encode(b.a(b.a(b.a(a, b.a(this.c)), b.a(this.a)), str2, str), 8));
        } catch (Exception unused) {
            return null;
        }
    }

    /* Access modifiers changed, original: final */
    @Nullable
    public final String a(String str) {
        if (str == null) {
            return null;
        }
        try {
            return d.a(b.a(Base64.decode(str, 0), this.b, this.a));
        } catch (Exception unused) {
            return null;
        }
    }
}
