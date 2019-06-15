package com.helpshift.util;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build.VERSION;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import com.moe.pushlibrary.utils.MoEHelperConstants;
import java.util.Locale;

public final class b {
    public static String a(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (Exception e) {
            l.a("Helpshift_AppUtil", "Error getting app version", e);
            return null;
        }
    }

    public static int b(Context context) {
        try {
            return context.getApplicationInfo().targetSdkVersion;
        } catch (Exception e) {
            l.a("Helpshift_AppUtil", "Target SDK version not found", e);
            return 0;
        }
    }

    public static String c(Context context) {
        String charSequence;
        try {
            charSequence = context.getPackageManager().getApplicationLabel(context.getApplicationInfo()).toString();
        } catch (Exception e) {
            l.a("Helpshift_AppUtil", "Error getting application name", e);
            charSequence = null;
        }
        return charSequence == null ? "Support" : charSequence;
    }

    public static boolean a(Context context, String str) {
        try {
            return ContextCompat.checkSelfPermission(context, str) == 0;
        } catch (Exception e) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Error checking for permission : ");
            stringBuilder.append(str);
            l.a("Helpshift_AppUtil", stringBuilder.toString(), e);
            return false;
        }
    }

    public static void a(Context context, String str, Notification notification) {
        if (notification != null) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Showing notification : Tag : ");
            stringBuilder.append(str);
            l.a("Helpshift_AppUtil", stringBuilder.toString());
            NotificationManager d = d(context);
            if (d != null) {
                d.notify(str, 1, notification);
            }
        }
    }

    public static void a(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Cancelling notification : Tag : ");
        stringBuilder.append(str);
        l.a("Helpshift_AppUtil", stringBuilder.toString());
        a(o.b(), str, 1);
    }

    public static void a(Context context, String str, int i) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Cancelling notification : Tag : ");
        stringBuilder.append(str);
        stringBuilder.append(", id : ");
        stringBuilder.append(i);
        l.a("Helpshift_AppUtil", stringBuilder.toString());
        NotificationManager d = d(context);
        if (d != null) {
            d.cancel(str, i);
        }
    }

    @Nullable
    public static NotificationManager d(Context context) {
        try {
            return (NotificationManager) context.getSystemService(MoEHelperConstants.NAVIGATION_SOURCE_NOTIFICATION);
        } catch (Exception e) {
            l.c("Helpshift_AppUtil", "Unable to get notification manager from System service", e);
            return null;
        }
    }

    public static Context e(Context context) {
        if (VERSION.SDK_INT < 17) {
            return context;
        }
        Locale d = o.d().t().d();
        if (d != null) {
            Configuration configuration = new Configuration(context.getResources().getConfiguration());
            configuration.setLocale(d);
            context = context.createConfigurationContext(configuration);
        }
        return context;
    }

    public static Context f(Context context) {
        Locale d = o.d().t().d();
        if (d != null) {
            o.d().t().a();
            Resources resources = context.getResources();
            Configuration configuration = new Configuration(resources.getConfiguration());
            configuration.locale = d;
            resources.updateConfiguration(configuration, resources.getDisplayMetrics());
        }
        return context;
    }

    public static void a() {
        o.d().t().b();
    }

    @Nullable
    public static String g(Context context) {
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            if (applicationInfo.metaData != null) {
                return applicationInfo.metaData.getString("android.support.VERSION");
            }
        } catch (Exception e) {
            l.a("Helpshift_AppUtil", "Error getting SupportLib version : ", e);
        }
        return null;
    }

    public static boolean a(Context context, int i) {
        boolean z = false;
        try {
            String g = g(context);
            if (g != null) {
                if (Integer.parseInt(g.split("\\.")[0]) >= i) {
                    z = true;
                }
                return z;
            }
        } catch (Exception e) {
            l.a("Helpshift_AppUtil", "Error in doing comparison check for supportLib version : ", e);
        }
        return false;
    }
}
