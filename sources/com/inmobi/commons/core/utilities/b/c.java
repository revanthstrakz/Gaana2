package com.inmobi.commons.core.utilities.b;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.widget.TextView;
import com.inmobi.commons.a.a;
import com.integralads.avid.library.inmobi.utils.AvidJSONUtil;
import java.util.HashMap;
import java.util.Map;

public class c {
    private static final String a = "c";

    public static int a(int i) {
        return Math.round(((float) i) * a().c);
    }

    public static d a() {
        Context b = a.b();
        if (b == null) {
            return new d(0, 0, 2.0f);
        }
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) b.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        float f = displayMetrics.density;
        return new d(Math.round(((float) displayMetrics.widthPixels) / f), Math.round(((float) displayMetrics.heightPixels) / f), f);
    }

    public static int b(int i) {
        return Math.round(((float) i) / a().c);
    }

    public static int b() {
        Context b = a.b();
        if (b == null) {
            return 1;
        }
        int rotation = ((WindowManager) b.getSystemService("window")).getDefaultDisplay().getRotation();
        switch (b.getResources().getConfiguration().orientation) {
            case 1:
                if (rotation == 1 || rotation == 2) {
                    return 2;
                }
                return 1;
            case 2:
                return (rotation == 0 || rotation == 1) ? 3 : 4;
            default:
                return 1;
        }
    }

    public static Map<String, String> c() {
        HashMap hashMap = new HashMap();
        try {
            Object obj;
            hashMap.put("d-device-screen-density", String.valueOf(a().c));
            d a = a();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(a.a);
            stringBuilder.append("X");
            stringBuilder.append(a.b);
            hashMap.put("d-device-screen-size", stringBuilder.toString());
            String str = "d-density-dependent-screen-size";
            Context b = a.b();
            if (b == null) {
                obj = "0x0";
            } else {
                DisplayMetrics displayMetrics = new DisplayMetrics();
                ((WindowManager) b.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
                int i = displayMetrics.widthPixels;
                int i2 = displayMetrics.heightPixels;
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append(i);
                stringBuilder2.append(AvidJSONUtil.KEY_X);
                stringBuilder2.append(i2);
                obj = stringBuilder2.toString();
            }
            hashMap.put(str, obj);
            hashMap.put("d-orientation", String.valueOf(b()));
            hashMap.put("d-textsize", String.valueOf(new TextView(a.b()).getTextSize()));
        } catch (Exception e) {
            new StringBuilder("SDK encountered unexpected error in getting display info; ").append(e.getMessage());
        }
        return hashMap;
    }
}
