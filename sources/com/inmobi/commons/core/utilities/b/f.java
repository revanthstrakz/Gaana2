package com.inmobi.commons.core.utilities.b;

import com.google.android.gms.cast.HlsSegmentFormat;
import com.inmobi.commons.a.b;
import com.inmobi.commons.core.utilities.uid.c;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class f {
    private static final String a = "f";

    public static Map<String, String> a() {
        HashMap hashMap = new HashMap();
        try {
            hashMap.put("mk-version", b.a());
            c.a();
            Boolean g = c.g();
            if (g != null) {
                hashMap.put("u-id-adt", String.valueOf(g.booleanValue()));
            }
            hashMap.put(HlsSegmentFormat.TS, String.valueOf(Calendar.getInstance().getTimeInMillis()));
            Calendar instance = Calendar.getInstance();
            hashMap.put("tz", String.valueOf(instance.get(15) + instance.get(16)));
            h a = h.a();
            HashMap hashMap2 = new HashMap();
            if (a.d && a.a != null) {
                hashMap2.put("u-s-id", a.a);
            }
            hashMap.putAll(hashMap2);
        } catch (Exception e) {
            new StringBuilder("SDK encountered unexpected error in getting UID info; ").append(e.getMessage());
        }
        return hashMap;
    }
}
