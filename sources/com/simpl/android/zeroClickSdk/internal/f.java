package com.simpl.android.zeroClickSdk.internal;

import android.content.Context;
import android.os.Bundle;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.lang.reflect.InvocationTargetException;

public final class f {
    private static Class<?> a;
    private static Class<?> b;
    private static Class<?> c;

    static int a(Bundle bundle) {
        try {
            Object cast = c.cast(bundle.get((String) a.getDeclaredField("EXTRA_STATUS").get(null)));
            return ((Integer) cast.getClass().getDeclaredMethod("getStatusCode", new Class[0]).invoke(cast, new Object[0])).intValue();
        } catch (IllegalAccessException | NoSuchFieldException | NoSuchMethodException | InvocationTargetException e) {
            ThrowableExtension.printStackTrace(e);
            return -2;
        }
    }

    static void a(Context context) {
        try {
            Object b = b(context);
            b.getClass().getMethod("startSmsRetriever", new Class[0]).invoke(b, new Object[0]);
        } catch (NoSuchMethodException e) {
            ThrowableExtension.printStackTrace(e);
        } catch (IllegalAccessException e2) {
            ThrowableExtension.printStackTrace(e2);
        } catch (InvocationTargetException e3) {
            ThrowableExtension.printStackTrace(e3);
        }
    }

    public static boolean a() {
        try {
            a = Class.forName("com.google.android.gms.auth.api.phone.SmsRetriever");
            b = Class.forName("com.google.android.gms.common.api.CommonStatusCodes");
            c = Class.forName("com.google.android.gms.common.api.Status");
            Class.forName("com.google.android.gms.auth.api.phone.SmsRetrieverClient");
            return true;
        } catch (ClassNotFoundException unused) {
            return false;
        }
    }

    private static Object b(Context context) {
        try {
            return a.getMethod("getClient", new Class[]{Context.class}).invoke(null, new Object[]{context});
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            ThrowableExtension.printStackTrace(e);
            return null;
        }
    }

    static String b() {
        try {
            return (String) a.getDeclaredField("SMS_RETRIEVED_ACTION").get(null);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            ThrowableExtension.printStackTrace(e);
            return "";
        }
    }

    static String b(Bundle bundle) {
        try {
            return (String) bundle.get((String) a.getDeclaredField("EXTRA_SMS_MESSAGE").get(null));
        } catch (IllegalAccessException | NoSuchFieldException e) {
            ThrowableExtension.printStackTrace(e);
            return "";
        }
    }

    static int c() {
        try {
            return b.getDeclaredField("SUCCESS").getInt(null);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            ThrowableExtension.printStackTrace(e);
            return -1;
        }
    }

    static int d() {
        try {
            return b.getDeclaredField("TIMEOUT").getInt(null);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            ThrowableExtension.printStackTrace(e);
            return -1;
        }
    }
}
