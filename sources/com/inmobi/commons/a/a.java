package com.inmobi.commons.a;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Binder;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.webkit.WebSettings;
import android.webkit.WebView;
import com.google.android.exoplayer2.C;
import com.inmobi.commons.core.utilities.c;
import java.io.File;
import java.util.concurrent.atomic.AtomicBoolean;

public final class a {
    private static final String a = "a";
    private static final boolean b = false;
    private static Context c = null;
    private static String d = "";
    private static String e = "";
    private static AtomicBoolean f = new AtomicBoolean();
    private static boolean g;

    static {
        "row".contains("staging");
    }

    public static void a(Context context, String str) {
        if (!a()) {
            c = context.getApplicationContext();
            e = str;
            f.set(true);
            if (VERSION.SDK_INT < 17) {
                try {
                    d = new WebView(context).getSettings().getUserAgentString();
                } catch (Exception e) {
                    c = null;
                    new StringBuilder("SDK encountered an unexpected error in SdkContext.fetchWebviewUserAgent().handler() method; ").append(e.getMessage());
                }
            }
            h();
        }
    }

    public static boolean a() {
        return c != null;
    }

    @Nullable
    public static Context b() {
        return c;
    }

    public static void c() {
        c = null;
    }

    public static void a(boolean z) {
        g = z;
    }

    public static boolean d() {
        return g;
    }

    public static String e() {
        return e;
    }

    public static String f() {
        if (TextUtils.isEmpty(d) && VERSION.SDK_INT >= 17) {
            d = c(c);
        }
        return d;
    }

    public static boolean g() {
        return f.get();
    }

    public static void b(boolean z) {
        f.set(z);
    }

    public static File a(Context context) {
        return new File(context.getFilesDir(), "im_cached_content");
    }

    public static void a(File file) {
        c.a(file);
    }

    public static boolean b(Context context, String str) {
        boolean z = false;
        if (context == null) {
            return false;
        }
        PackageManager packageManager = context.getPackageManager();
        if (VERSION.SDK_INT >= 23) {
            z = c(context, str);
        } else if (packageManager.checkPermission(str, packageManager.getNameForUid(Binder.getCallingUid())) == 0) {
            z = true;
        }
        return z;
    }

    private static boolean c(Context context, String str) {
        if (context == null || str == null) {
            return false;
        }
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 4096);
            if (packageInfo.requestedPermissions != null) {
                for (String equals : packageInfo.requestedPermissions) {
                    if (equals.equals(str)) {
                        return true;
                    }
                }
            }
        } catch (Exception e) {
            StringBuilder stringBuilder = new StringBuilder("Could not check manifest for permission:");
            stringBuilder.append(str);
            stringBuilder.append(" Error:");
            stringBuilder.append(e.getLocalizedMessage());
        }
        return false;
    }

    public static void a(Context context, Intent intent) {
        if (!(context instanceof Activity)) {
            intent.setFlags(C.ENCODING_PCM_MU_LAW);
        }
        context.startActivity(intent);
    }

    public static File a(String str) {
        h();
        File a = a(c);
        int length = str.length() / 2;
        String valueOf = String.valueOf(str.substring(0, length).hashCode() & Integer.MAX_VALUE);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(valueOf);
        stringBuilder.append(String.valueOf(str.substring(length).hashCode() & Integer.MAX_VALUE));
        return new File(a, stringBuilder.toString());
    }

    public static void b(@NonNull Context context) {
        try {
            File file = new File(context.getCacheDir(), "im_cached_content");
            if (file.exists()) {
                c.a(file);
            }
        } catch (Exception e) {
            new StringBuilder("SDK encountered unexpected error in clearOldMediaCacheDirectory; ").append(e.getMessage());
        }
    }

    private static void h() {
        File a = a(c);
        if (!a.mkdir()) {
            a.isDirectory();
        }
    }

    @TargetApi(17)
    private static String c(Context context) {
        String property;
        Exception e;
        String str = "";
        try {
            if (!b) {
                return WebSettings.getDefaultUserAgent(context.getApplicationContext());
            }
            throw new Exception("android.util.AndroidRuntimeException: android.content.pm.PackageManager$NameNotFoundException: com.google.android.webview");
        } catch (Throwable th) {
            new StringBuilder("SDK encountered an unexpected error in getting user agent information; ").append(th.getMessage());
            com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(th));
            try {
                property = System.getProperty("http.agent");
                if (property == null) {
                    try {
                        return "";
                    } catch (Exception e2) {
                        Exception exception = e2;
                        str = property;
                        e = exception;
                        new StringBuilder("SDK encountered an unexpected error in getting property of http.agent; ").append(e.getMessage());
                        com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
                        property = str;
                        return property;
                    }
                }
            } catch (Exception e3) {
                e = e3;
                new StringBuilder("SDK encountered an unexpected error in getting property of http.agent; ").append(e.getMessage());
                com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
                property = str;
                return property;
            }
            return property;
        }
    }
}
