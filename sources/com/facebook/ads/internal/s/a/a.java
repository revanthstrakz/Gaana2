package com.facebook.ads.internal.s.a;

import android.app.Activity;
import android.support.annotation.Nullable;
import java.lang.reflect.Field;
import java.util.Map;

public class a {
    @Nullable
    public static Activity a() {
        try {
            return b();
        } catch (Exception unused) {
            return null;
        }
    }

    private static Activity b() {
        Class cls = Class.forName("android.app.ActivityThread");
        Object invoke = cls.getMethod("currentActivityThread", new Class[0]).invoke(null, new Object[0]);
        Field declaredField = cls.getDeclaredField("mActivities");
        declaredField.setAccessible(true);
        Map map = (Map) declaredField.get(invoke);
        if (map == null) {
            return null;
        }
        for (Object invoke2 : map.values()) {
            Class cls2 = invoke2.getClass();
            Field declaredField2 = cls2.getDeclaredField(InAppConstants.INAPP_CAMPAIGN_STATUS_PAUSED);
            declaredField2.setAccessible(true);
            if (!declaredField2.getBoolean(invoke2)) {
                declaredField = cls2.getDeclaredField("activity");
                declaredField.setAccessible(true);
                return (Activity) declaredField.get(invoke2);
            }
        }
        return null;
    }
}
