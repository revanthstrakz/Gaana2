package com.inmobi.commons.core.utilities.b;

import android.location.Location;
import com.inmobi.commons.a.a;
import com.inmobi.commons.core.d.c;
import java.util.HashMap;
import java.util.Locale;

public final class g {
    private static int a = Integer.MIN_VALUE;
    private static String b = null;
    private static String c = null;
    private static String d = null;
    private static String e = null;
    private static String f = null;
    private static String g = null;
    private static int h = Integer.MIN_VALUE;
    private static String i;
    private static String j;
    private static String k;
    private static String l;
    private static Location m;

    public static String a() {
        return c.a("user_info_store");
    }

    public static void b() {
        a(a);
        a(b);
        b(c);
        c(d);
        d(e);
        e(f);
        f(g);
        b(h);
        g(i);
        h(j);
        i(k);
        j(l);
        a(m);
    }

    public static void a(int i) {
        if (!a.a() || i == Integer.MIN_VALUE) {
            a = i;
        } else {
            c.b("user_info_store").a("user_age", i);
        }
    }

    public static void a(String str) {
        if (!a.a() || str == null) {
            b = str;
        } else {
            c.b("user_info_store").a("user_age_group", str);
        }
    }

    public static void b(String str) {
        if (!a.a() || str == null) {
            c = str;
        } else {
            c.b("user_info_store").a("user_area_code", str);
        }
    }

    public static void c(String str) {
        if (!a.a() || str == null) {
            d = str;
        } else {
            c.b("user_info_store").a("user_post_code", str);
        }
    }

    public static void d(String str) {
        if (!a.a() || str == null) {
            e = str;
        } else {
            c.b("user_info_store").a("user_city_code", str);
        }
    }

    public static void e(String str) {
        if (!a.a() || str == null) {
            f = str;
        } else {
            c.b("user_info_store").a("user_state_code", str);
        }
    }

    public static void f(String str) {
        if (!a.a() || str == null) {
            g = str;
        } else {
            c.b("user_info_store").a("user_country_code", str);
        }
    }

    public static void b(int i) {
        if (!a.a() || i == Integer.MIN_VALUE) {
            h = i;
        } else {
            c.b("user_info_store").a("user_yob", i);
        }
    }

    public static void g(String str) {
        if (!a.a() || str == null) {
            i = str;
        } else {
            c.b("user_info_store").a("user_gender", str);
        }
    }

    public static void h(String str) {
        if (!a.a() || str == null) {
            j = str;
        } else {
            c.b("user_info_store").a("user_education", str);
        }
    }

    public static void i(String str) {
        if (!a.a() || str == null) {
            k = str;
        } else {
            c.b("user_info_store").a("user_language", str);
        }
    }

    public static void j(String str) {
        if (!a.a() || str == null) {
            l = str;
        } else {
            c.b("user_info_store").a("user_interest", str);
        }
    }

    public static Location c() {
        if (m != null) {
            return m;
        }
        String c = c.b("user_info_store").c("user_location");
        Location location = null;
        if (c == null) {
            return null;
        }
        Location location2 = new Location("");
        try {
            String[] split = c.split(",");
            location2.setLatitude(Double.parseDouble(split[0]));
            location2.setLongitude(Double.parseDouble(split[1]));
            location2.setAccuracy(Float.parseFloat(split[2]));
            location2.setTime(Long.parseLong(split[3]));
            location = location2;
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException unused) {
        }
        return location;
    }

    public static void a(Location location) {
        if (!a.a() || location == null) {
            m = location;
            return;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(location.getLatitude());
        stringBuilder.append(",");
        stringBuilder.append(location.getLongitude());
        stringBuilder.append(",");
        stringBuilder.append((int) location.getAccuracy());
        stringBuilder.append(",");
        stringBuilder.append(location.getTime());
        c.b("user_info_store").a("user_location", stringBuilder.toString());
    }

    public static HashMap<String, String> d() {
        int i;
        String str;
        String str2;
        String str3;
        StringBuilder stringBuilder;
        Object obj;
        HashMap hashMap = new HashMap();
        if (a != Integer.MIN_VALUE) {
            i = a;
        } else {
            i = c.b("user_info_store").d("user_age");
        }
        if (i != Integer.MIN_VALUE && i > 0) {
            hashMap.put("u-age", String.valueOf(i));
        }
        if (h != Integer.MIN_VALUE) {
            i = h;
        } else {
            i = c.b("user_info_store").d("user_yob");
        }
        if (i != Integer.MIN_VALUE && i > 0) {
            hashMap.put("u-yearofbirth", String.valueOf(i));
        }
        if (e != null) {
            str = e;
        } else {
            str = c.b("user_info_store").c("user_city_code");
        }
        if (f != null) {
            str2 = f;
        } else {
            str2 = c.b("user_info_store").c("user_state_code");
        }
        if (g != null) {
            str3 = g;
        } else {
            str3 = c.b("user_info_store").c("user_country_code");
        }
        String str4 = "";
        if (!(str == null || str.trim().length() == 0)) {
            str4 = str.trim();
        }
        if (!(str2 == null || str2.trim().length() == 0)) {
            stringBuilder = new StringBuilder();
            stringBuilder.append(str4);
            stringBuilder.append("-");
            stringBuilder.append(str2.trim());
            str4 = stringBuilder.toString();
        }
        if (!(str3 == null || str3.trim().length() == 0)) {
            stringBuilder = new StringBuilder();
            stringBuilder.append(str4);
            stringBuilder.append("-");
            stringBuilder.append(str3.trim());
            str4 = stringBuilder.toString();
        }
        if (!(str4 == null || str4.trim().length() == 0)) {
            hashMap.put("u-location", str4);
        }
        if (b != null) {
            str = b;
        } else {
            str = c.b("user_info_store").c("user_age_group");
        }
        if (str != null) {
            hashMap.put("u-agegroup", str.toLowerCase(Locale.ENGLISH));
        }
        if (c != null) {
            obj = c;
        } else {
            obj = c.b("user_info_store").c("user_area_code");
        }
        if (obj != null) {
            hashMap.put("u-areacode", obj);
        }
        if (d != null) {
            obj = d;
        } else {
            obj = c.b("user_info_store").c("user_post_code");
        }
        if (obj != null) {
            hashMap.put("u-postalcode", obj);
        }
        if (i != null) {
            obj = i;
        } else {
            obj = c.b("user_info_store").c("user_gender");
        }
        if (obj != null) {
            hashMap.put("u-gender", obj);
        }
        if (j != null) {
            obj = j;
        } else {
            obj = c.b("user_info_store").c("user_education");
        }
        if (obj != null) {
            hashMap.put("u-education", obj);
        }
        if (k != null) {
            obj = k;
        } else {
            obj = c.b("user_info_store").c("user_language");
        }
        if (obj != null) {
            hashMap.put("u-language", obj);
        }
        if (l != null) {
            obj = l;
        } else {
            obj = c.b("user_info_store").c("user_interest");
        }
        if (obj != null) {
            hashMap.put("u-interests", obj);
        }
        return hashMap;
    }
}
