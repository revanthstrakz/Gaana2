package net.hockeyapp.android.d;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.Notification.Builder;
import android.app.PendingIntent;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build.VERSION;
import android.text.TextUtils;
import android.util.Patterns;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.hockeyapp.android.i.d;

public class i {
    private static final Pattern a = Pattern.compile("[0-9a-f]+", 2);
    private static final char[] b = "0123456789ABCDEF".toCharArray();
    private static final ThreadLocal<DateFormat> c = new ThreadLocal<DateFormat>() {
        /* Access modifiers changed, original: protected */
        /* renamed from: a */
        public DateFormat initialValue() {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US);
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            return simpleDateFormat;
        }
    };

    public static String a(String str) {
        try {
            return URLEncoder.encode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            ThrowableExtension.printStackTrace(e);
            return "";
        }
    }

    public static final boolean b(String str) {
        return !TextUtils.isEmpty(str) && Patterns.EMAIL_ADDRESS.matcher(str).matches();
    }

    public static String c(String str) throws IllegalArgumentException {
        if (str == null) {
            throw new IllegalArgumentException("App ID must not be null.");
        }
        str = str.trim();
        Matcher matcher = a.matcher(str);
        if (str.length() != 32) {
            throw new IllegalArgumentException("App ID length must be 32 characters.");
        } else if (matcher.matches()) {
            return str;
        } else {
            throw new IllegalArgumentException("App ID must match regex pattern /[0-9a-f]+/i");
        }
    }

    public static boolean d(String str) {
        boolean z = false;
        try {
            if (Class.forName(str) != null) {
                z = true;
            }
            return z;
        } catch (ClassNotFoundException unused) {
            return false;
        }
    }

    public static boolean a() {
        return VERSION.SDK_INT >= 11 && d("android.app.Notification.Builder");
    }

    public static Notification a(Context context, PendingIntent pendingIntent, String str, String str2, int i) {
        if (a()) {
            return c(context, pendingIntent, str, str2, i);
        }
        return b(context, pendingIntent, str, str2, i);
    }

    private static Notification b(Context context, PendingIntent pendingIntent, String str, String str2, int i) {
        Notification notification = new Notification(i, "", System.currentTimeMillis());
        try {
            notification.getClass().getMethod("setLatestEventInfo", new Class[]{Context.class, CharSequence.class, CharSequence.class, PendingIntent.class}).invoke(notification, new Object[]{context, str, str2, pendingIntent});
        } catch (Exception unused) {
        }
        return notification;
    }

    @TargetApi(11)
    private static Notification c(Context context, PendingIntent pendingIntent, String str, String str2, int i) {
        Builder smallIcon = new Builder(context).setContentTitle(str).setContentText(str2).setContentIntent(pendingIntent).setSmallIcon(i);
        if (VERSION.SDK_INT < 16) {
            return smallIcon.getNotification();
        }
        return smallIcon.build();
    }

    public static boolean a(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        boolean z = false;
        if (connectivityManager == null) {
            return false;
        }
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
            z = true;
        }
        return z;
    }

    public static String b(Context context) {
        if (context == null) {
            return "";
        }
        String str;
        PackageManager packageManager = context.getPackageManager();
        ApplicationInfo applicationInfo = null;
        try {
            applicationInfo = packageManager.getApplicationInfo(context.getApplicationInfo().packageName, 0);
        } catch (NameNotFoundException unused) {
        }
        if (applicationInfo != null) {
            str = (String) packageManager.getApplicationLabel(applicationInfo);
        } else {
            str = context.getString(d.hockeyapp_crash_dialog_app_name_fallback);
        }
        return str;
    }
}
