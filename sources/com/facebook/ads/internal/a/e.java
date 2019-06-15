package com.facebook.ads.internal.a;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.text.TextUtils;
import com.facebook.ads.internal.o.c;
import com.facebook.ads.internal.s.d.b;
import java.util.Collection;
import java.util.HashSet;
import org.json.JSONArray;

public class e {

    public interface a {
        d a();

        Collection<String> b();

        String c();
    }

    public static Collection<String> a(JSONArray jSONArray) {
        if (jSONArray == null || jSONArray.length() == 0) {
            return null;
        }
        HashSet hashSet = new HashSet();
        for (int i = 0; i < jSONArray.length(); i++) {
            hashSet.add(jSONArray.optString(i));
        }
        return hashSet;
    }

    public static boolean a(Context context, a aVar, c cVar) {
        d a = aVar.a();
        if (a == null || a == d.NONE) {
            return false;
        }
        Collection<String> b = aVar.b();
        if (b == null || b.isEmpty()) {
            return false;
        }
        boolean z;
        for (String a2 : b) {
            if (a(context, a2)) {
                z = true;
                break;
            }
        }
        z = false;
        if (z == (a == d.INSTALLED)) {
            String c = aVar.c();
            if (TextUtils.isEmpty(c)) {
                com.facebook.ads.internal.s.d.a.a(context, "api", b.j, new Exception("Ad is invalidated without token."));
                return true;
            }
            cVar.b(c, null);
            return true;
        }
        return false;
    }

    public static boolean a(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            context.getPackageManager().getPackageInfo(str, 1);
            return true;
        } catch (NameNotFoundException | RuntimeException unused) {
            return false;
        }
    }
}
