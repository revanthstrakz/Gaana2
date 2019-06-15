package com.facebook.ads.internal.e;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.text.TextUtils;
import com.facebook.ads.internal.g.e;
import com.facebook.ads.internal.settings.AdInternalSettings;
import com.j.a.a;
import java.net.URL;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import org.json.JSONObject;

public abstract class b {
    private static boolean a = false;
    private static double b = 1.0d;
    private static boolean c = false;
    private static final Set<String> d = new HashSet();
    private static final Set<String> e = new HashSet();

    private static int a(String str) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException unused) {
            return 0;
        }
    }

    @SuppressLint({"StringFormatUse"})
    private static URL a() {
        if (TextUtils.isEmpty(AdInternalSettings.getUrlPrefix())) {
            return new URL("https://mobile.facebook.com/sbx/cydonia/");
        }
        return new URL(String.format(Locale.US, "https://mobile.%s.facebook.com/sbx/cydonia/", new Object[]{AdInternalSettings.getUrlPrefix()}));
    }

    @SuppressLint({"CatchGeneralException"})
    public static void a(Context context) {
        if (!a && (context instanceof Application)) {
            try {
                a.a(a());
                a.a("843748");
                a.a(context);
                b(context);
            } catch (Exception e) {
                com.facebook.ads.internal.s.d.a.a(context, "bdet", com.facebook.ads.internal.s.d.b.E, e);
            }
        }
    }

    @SuppressLint({"CatchGeneralException"})
    public static boolean a(Context context, String str, String str2) {
        if (!a || Math.random() > b || !d.contains(str)) {
            return false;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            Object str3;
            if (e.contains(str3)) {
                str3 = "click";
            }
            if (c && !TextUtils.isEmpty(com.facebook.ads.internal.d.b.b)) {
                jSONObject.put("ui", com.facebook.ads.internal.d.b.b);
            }
            jSONObject.put("ti", str2);
            jSONObject.put("bt", "AN_ANDROID");
            jSONObject.put("sn", str3);
            a.a(jSONObject);
            return true;
        } catch (Exception e) {
            com.facebook.ads.internal.s.d.a.a(context, "bdet", com.facebook.ads.internal.s.d.b.F, e);
            return false;
        }
    }

    private static synchronized void b(Context context) {
        synchronized (b.class) {
            String r = com.facebook.ads.internal.n.a.r(context);
            String[] a = a.a();
            if (r == null) {
                r = "";
            }
            String[] split = r.split(",");
            int length = split.length;
            int i = 0;
            while (true) {
                boolean z = true;
                if (i >= length) {
                    break;
                }
                String[] split2 = split[i].split(":");
                if (split2.length == 2) {
                    int a2;
                    int i2;
                    if (e.a.equalsIgnoreCase(split2[0].trim())) {
                        a2 = a(split2[1].trim());
                        for (i2 = 0; i2 < a.length; i2++) {
                            if (((1 << i2) & a2) > 0) {
                                d.add(a[i2]);
                            }
                        }
                    }
                    if ("c".equalsIgnoreCase(split2[0].trim())) {
                        a2 = a(split2[1].trim());
                        for (i2 = 0; i2 < a.length; i2++) {
                            if (((1 << i2) & a2) > 0) {
                                e.add(a[i2]);
                            }
                        }
                    }
                    if ("s".equalsIgnoreCase(split2[0].trim())) {
                        b = ((double) a(split2[1].trim())) / 100.0d;
                    }
                    if ("a".equalsIgnoreCase(split2[0].trim())) {
                        if (a(split2[1].trim()) != 1) {
                            z = false;
                        }
                        c = z;
                    }
                }
                i++;
            }
            if (d.isEmpty()) {
                d.add(a.IMPRESSION.m);
                d.add(a.STORE.m);
                d.add(a.CLOSE.m);
                d.add(a.OFF_TARGET_CLICK.m);
                d.add(a.OPEN_LINK.m);
            }
            if (e.isEmpty()) {
                e.add(a.STORE.m);
                e.add(a.CLOSE.m);
                e.add(a.OFF_TARGET_CLICK.m);
                e.add(a.OPEN_LINK.m);
            }
            a = true;
        }
    }
}
