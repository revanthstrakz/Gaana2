package com.inmobi.commons.core.utilities;

import android.content.Context;
import com.inmobi.commons.core.e.b;
import java.util.HashMap;

public final class e {
    public static boolean a(Context context, String str, String str2) {
        try {
            return context.checkCallingOrSelfPermission(str2) == 0;
        } catch (Exception e) {
            try {
                HashMap hashMap = new HashMap();
                hashMap.put("type", "RuntimeException");
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(e.getMessage());
                hashMap.put("message", stringBuilder.toString());
                b.a();
                b.a(str, "ExceptionCaught", hashMap);
            } catch (Exception unused) {
                StringBuilder stringBuilder2 = new StringBuilder("Error in submitting telemetry event : (");
                stringBuilder2.append(e.getMessage());
                stringBuilder2.append(")");
            }
            return false;
        }
    }
}
