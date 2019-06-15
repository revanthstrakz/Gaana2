package com.facebook.ads.internal.s.e;

import android.app.Activity;
import android.app.KeyguardManager;
import android.content.Context;
import android.util.Log;
import android.view.Window;
import com.facebook.ads.internal.s.a.aa;
import com.facebook.ads.internal.s.d.a;
import java.util.HashMap;
import java.util.Map;

public class b {
    private static final String a = "b";

    public static Map<String, String> a(Context context) {
        HashMap hashMap = new HashMap();
        if (context == null) {
            Log.v(a, "Null context in window interactive check.");
            return hashMap;
        }
        try {
            String str;
            String str2;
            hashMap.put("kgr", String.valueOf(c(context)));
            if (context instanceof Activity) {
                Window window = ((Activity) context).getWindow();
                if (window != null) {
                    int i = window.getAttributes().flags;
                    hashMap.put("wt", Integer.toString(window.getAttributes().type));
                    hashMap.put("wfdkg", (4194304 & i) > 0 ? "1" : "0");
                    hashMap.put("wfswl", (524288 & i) > 0 ? "1" : "0");
                    return hashMap;
                }
                str = a;
                str2 = "Invalid window in window interactive check, assuming interactive.";
            } else {
                str = a;
                str2 = "Invalid Activity context in window interactive check, assuming interactive.";
            }
            Log.v(str, str2);
            return hashMap;
        } catch (Exception e) {
            Log.e(a, "Exception in window info check", e);
            a.a(context, "risky", com.facebook.ads.internal.s.d.b.v, e);
            return hashMap;
        }
    }

    public static boolean b(Context context) {
        return aa.b(a(context)) ^ 1;
    }

    public static boolean c(Context context) {
        KeyguardManager keyguardManager = (KeyguardManager) context.getSystemService("keyguard");
        return keyguardManager != null && keyguardManager.inKeyguardRestrictedInputMode();
    }
}
