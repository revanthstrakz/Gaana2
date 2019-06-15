package net.hockeyapp.android;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings.Secure;
import android.text.TextUtils;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;
import net.hockeyapp.android.d.d;

public class a {
    public static String a;
    public static String b;
    public static String c;
    public static String d;
    public static String e;
    public static String f;
    public static String g;
    public static String h;
    public static String i;
    public static String j;

    public static void a(Context context) {
        e = VERSION.RELEASE;
        f = Build.DISPLAY;
        g = Build.MODEL;
        h = Build.MANUFACTURER;
        b(context);
        c(context);
        d(context);
        e(context);
    }

    public static File a() {
        File externalStorageDirectory = Environment.getExternalStorageDirectory();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(externalStorageDirectory.getAbsolutePath());
        stringBuilder.append("/");
        stringBuilder.append("HockeyApp");
        File file = new File(stringBuilder.toString());
        Object obj = (file.exists() || file.mkdirs()) ? 1 : null;
        if (obj == null) {
            d.b("Couldn't create HockeyApp Storage dir");
        }
        return file;
    }

    private static void b(Context context) {
        if (context != null) {
            try {
                File filesDir = context.getFilesDir();
                if (filesDir != null) {
                    a = filesDir.getAbsolutePath();
                }
            } catch (Exception e) {
                d.c("Exception thrown when accessing the files dir:");
                ThrowableExtension.printStackTrace(e);
            }
        }
    }

    private static void c(Context context) {
        if (context != null) {
            try {
                PackageManager packageManager = context.getPackageManager();
                PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
                d = packageInfo.packageName;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("");
                stringBuilder.append(packageInfo.versionCode);
                b = stringBuilder.toString();
                c = packageInfo.versionName;
                int a = a(context, packageManager);
                if (a != 0 && a > packageInfo.versionCode) {
                    StringBuilder stringBuilder2 = new StringBuilder();
                    stringBuilder2.append("");
                    stringBuilder2.append(a);
                    b = stringBuilder2.toString();
                }
            } catch (NameNotFoundException e) {
                d.c("Exception thrown when accessing the package info:");
                ThrowableExtension.printStackTrace(e);
            }
        }
    }

    private static int a(Context context, PackageManager packageManager) {
        try {
            Bundle bundle = packageManager.getApplicationInfo(context.getPackageName(), 128).metaData;
            if (bundle != null) {
                return bundle.getInt("buildNumber", 0);
            }
        } catch (NameNotFoundException e) {
            d.c("Exception thrown when accessing the application info:");
            ThrowableExtension.printStackTrace(e);
        }
        return 0;
    }

    private static void d(Context context) {
        String string = Secure.getString(context.getContentResolver(), "android_id");
        if (!TextUtils.isEmpty(d) && !TextUtils.isEmpty(string)) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(d);
            stringBuilder.append(":");
            stringBuilder.append(string);
            stringBuilder.append(":");
            stringBuilder.append(f(context));
            String stringBuilder2 = stringBuilder.toString();
            try {
                MessageDigest instance = MessageDigest.getInstance("SHA-1");
                byte[] bytes = stringBuilder2.getBytes("UTF-8");
                instance.update(bytes, 0, bytes.length);
                i = a(instance.digest());
            } catch (Throwable th) {
                StringBuilder stringBuilder3 = new StringBuilder();
                stringBuilder3.append("Couldn't create CrashIdentifier with Exception:");
                stringBuilder3.append(th.toString());
                d.c(stringBuilder3.toString());
            }
        }
    }

    private static void e(Context context) {
        String string = Secure.getString(context.getContentResolver(), "android_id");
        if (string != null) {
            String a = a(context, string);
            if (a == null) {
                a = UUID.randomUUID().toString();
            }
            j = a;
        }
    }

    private static String a(Context context, String str) {
        String f = f(context);
        try {
            MessageDigest instance = MessageDigest.getInstance("SHA-256");
            instance.reset();
            instance.update(str.getBytes());
            instance.update(f.getBytes());
            return a(instance.digest());
        } catch (NoSuchAlgorithmException unused) {
            return null;
        }
    }

    @SuppressLint({"InlinedApi"})
    private static String f(Context context) {
        String str;
        if (VERSION.SDK_INT >= 21) {
            str = Build.SUPPORTED_ABIS[0];
        } else {
            str = Build.CPU_ABI;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("HA");
        stringBuilder.append(Build.BOARD.length() % 10);
        stringBuilder.append(Build.BRAND.length() % 10);
        stringBuilder.append(str.length() % 10);
        stringBuilder.append(Build.PRODUCT.length() % 10);
        str = stringBuilder.toString();
        String str2 = "";
        try {
            str2 = Build.class.getField("SERIAL").get(null).toString();
        } catch (Throwable unused) {
        }
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append(str);
        stringBuilder2.append(":");
        stringBuilder2.append(str2);
        return stringBuilder2.toString();
    }

    private static String a(byte[] bArr) {
        char[] toCharArray = "0123456789ABCDEF".toCharArray();
        char[] cArr = new char[(bArr.length * 2)];
        for (int i = 0; i < bArr.length; i++) {
            int i2 = bArr[i] & 255;
            int i3 = i * 2;
            cArr[i3] = toCharArray[i2 >>> 4];
            cArr[i3 + 1] = toCharArray[i2 & 15];
        }
        return new String(cArr).replaceAll("(\\w{8})(\\w{4})(\\w{4})(\\w{4})(\\w{12})", "$1-$2-$3-$4-$5");
    }
}
