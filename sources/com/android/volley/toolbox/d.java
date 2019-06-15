package com.android.volley.toolbox;

import com.android.volley.a.a;
import com.android.volley.g;
import java.util.Map;
import org.apache.http.entity.mime.MIME;
import org.apache.http.impl.cookie.DateParseException;
import org.apache.http.impl.cookie.DateUtils;

public class d {
    public static a a(g gVar) {
        int i;
        long j;
        long j2;
        int i2;
        g gVar2 = gVar;
        long currentTimeMillis = System.currentTimeMillis();
        Map map = gVar2.c;
        String str = (String) map.get("Date");
        long a = str != null ? a(str) : 0;
        str = (String) map.get("Cache-Control");
        int i3 = 0;
        if (str != null) {
            String[] split = str.split(",");
            i = 0;
            j = 0;
            j2 = 0;
            while (i3 < split.length) {
                String trim = split[i3].trim();
                if (trim.equals("no-cache") || trim.equals("no-store")) {
                    return null;
                }
                if (trim.startsWith("max-age=")) {
                    try {
                        j = Long.parseLong(trim.substring(8));
                    } catch (Exception unused) {
                    }
                } else if (trim.startsWith("stale-while-revalidate=")) {
                    j2 = Long.parseLong(trim.substring(23));
                } else if (trim.equals("must-revalidate") || trim.equals("proxy-revalidate")) {
                    i = 1;
                }
                i3++;
            }
            i2 = 1;
        } else {
            i = 0;
            i2 = i;
            j = 0;
            j2 = 0;
        }
        str = (String) map.get("Expires");
        long a2 = str != null ? a(str) : 0;
        str = (String) map.get("Last-Modified");
        long a3 = str != null ? a(str) : 0;
        str = (String) map.get("ETag");
        if (i2 != 0) {
            long j3 = currentTimeMillis + (j * 1000);
            currentTimeMillis = i != 0 ? j3 : j3 + (j2 * 1000);
            a2 = j3;
        } else if (a <= 0 || a2 < a) {
            currentTimeMillis = 0;
            a2 = currentTimeMillis;
        } else {
            a2 = currentTimeMillis + (a2 - a);
            currentTimeMillis = a2;
        }
        a aVar = new a();
        aVar.a = gVar2.b;
        aVar.b = str;
        aVar.f = a2;
        aVar.e = currentTimeMillis;
        aVar.c = a;
        aVar.d = a3;
        aVar.g = map;
        return aVar;
    }

    public static long a(String str) {
        try {
            return DateUtils.parseDate(str).getTime();
        } catch (DateParseException unused) {
            return 0;
        }
    }

    public static String a(Map<String, String> map, String str) {
        String str2 = (String) map.get(MIME.CONTENT_TYPE);
        if (str2 != null) {
            String[] split = str2.split(";");
            for (int i = 1; i < split.length; i++) {
                String[] split2 = split[i].trim().split("=");
                if (split2.length == 2 && split2[0].equals("charset")) {
                    return split2[1];
                }
            }
        }
        return str;
    }

    public static String a(Map<String, String> map) {
        return a(map, "ISO-8859-1");
    }
}
