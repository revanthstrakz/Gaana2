package com.inmobi.commons.a;

import android.content.Context;
import com.inmobi.commons.core.d.c;

public final class b {
    public static String a() {
        String str = "";
        for (char c : "7.2.1".toCharArray()) {
            if (c == '.') {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(str);
                stringBuilder.append("T");
                str = stringBuilder.toString();
            } else {
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append(str);
                stringBuilder2.append((char) ((c - 48) + 65));
                str = stringBuilder2.toString();
            }
        }
        StringBuilder stringBuilder3 = new StringBuilder("pr-SAND-");
        stringBuilder3.append(str);
        stringBuilder3.append("-20180920");
        return stringBuilder3.toString();
    }

    public static String a(Context context) {
        return c.a(context, "sdk_version_store").c("sdk_version");
    }

    public static void a(Context context, boolean z) {
        c.a(context, "sdk_version_store").a("db_deletion_failed", z);
    }
}
