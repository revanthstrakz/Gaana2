package com.til.colombia.dmp.android;

import android.annotation.TargetApi;
import android.content.Context;
import android.util.Log;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class AdvertisingIDUtil {
    private static final String cAdvertisingIdClientInfoName = "com.google.android.gms.ads.identifier.AdvertisingIdClient$Info";
    private static final String cAdvertisingIdClientName = "com.google.android.gms.ads.identifier.AdvertisingIdClient";

    @TargetApi(11)
    public static void retrieveAndSetAAID(Context context) {
        if (context != null) {
            try {
                Class cls = Class.forName(cAdvertisingIdClientInfoName);
                Method method = Class.forName(cAdvertisingIdClientName).getMethod("getAdvertisingIdInfo", new Class[]{Context.class});
                Method method2 = cls.getMethod("getId", new Class[0]);
                Method method3 = cls.getMethod("isLimitAdTrackingEnabled", new Class[0]);
                Object cast = cls.cast(method.invoke(null, new Object[]{context.getApplicationContext()}));
                Utils.setAAID((String) method2.invoke(cast, new Object[0]), (Boolean) method3.invoke(cast, new Object[0]));
            } catch (ClassNotFoundException e) {
                Log.e(Utils.LOG_TAG_VER, "", e);
            } catch (InvocationTargetException e2) {
                Log.e(Utils.LOG_TAG_VER, "", e2);
            } catch (NoSuchMethodException e3) {
                Log.e(Utils.LOG_TAG_VER, "", e3);
            } catch (IllegalAccessException e4) {
                Log.e(Utils.LOG_TAG_VER, "", e4);
            } catch (ClassCastException e5) {
                Log.e(Utils.LOG_TAG_VER, "", e5);
            } catch (NullPointerException e6) {
                Log.e(Utils.LOG_TAG_VER, "", e6);
            } catch (Exception e7) {
                Log.e(Utils.LOG_TAG_VER, "", e7);
                e7.getMessage().equalsIgnoreCase("Google play services is mandatory.");
            }
        }
    }
}
