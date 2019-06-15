package com.appsflyer;

import android.support.annotation.NonNull;
import android.util.Log;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class AFLogger {
    private static long a = System.currentTimeMillis();

    public enum LogLevel {
        NONE(0),
        ERROR(1),
        WARNING(2),
        INFO(3),
        DEBUG(4),
        VERBOSE(5);
        
        /* renamed from: ˋ */
        private int f1;

        private LogLevel(int i) {
            this.f1 = i;
        }

        public final int getLevel() {
            return this.f1;
        }
    }

    public static void a(String str, boolean z) {
        if (LogLevel.INFO.getLevel() <= i.a().a("logLevel", LogLevel.NONE.getLevel())) {
            Log.i("AppsFlyer_4.8.13", b(str, false));
        }
        if (z) {
            ah.a().b("I", b(str, true));
        }
    }

    public static void a() {
        a = System.currentTimeMillis();
    }

    @NonNull
    private static String b(String str, boolean z) {
        if (!z && LogLevel.VERBOSE.getLevel() > i.a().a("logLevel", LogLevel.NONE.getLevel())) {
            return str;
        }
        StringBuilder stringBuilder = new StringBuilder("(");
        stringBuilder.append(a(System.currentTimeMillis() - a));
        stringBuilder.append(") [");
        stringBuilder.append(Thread.currentThread().getName());
        stringBuilder.append("] ");
        stringBuilder.append(str);
        return stringBuilder.toString();
    }

    private static void a(String str, Throwable th, boolean z) {
        if ((LogLevel.ERROR.getLevel() <= i.a().a("logLevel", LogLevel.NONE.getLevel())) && z) {
            Log.e("AppsFlyer_4.8.13", b(str, false), th);
        }
        ah.a().a(th);
    }

    public static void a(String str) {
        if (LogLevel.VERBOSE.getLevel() <= i.a().a("logLevel", LogLevel.NONE.getLevel())) {
            Log.v("AppsFlyer_4.8.13", b(str, false));
        }
        ah.a().b("V", b(str, true));
    }

    public static void d(String str) {
        a(str, true);
    }

    public static void a(String str, Throwable th) {
        a(str, th, false);
    }

    private static String a(long j) {
        long toMillis = j - TimeUnit.HOURS.toMillis(TimeUnit.MILLISECONDS.toHours(j));
        long toMillis2 = toMillis - TimeUnit.MINUTES.toMillis(TimeUnit.MILLISECONDS.toMinutes(toMillis));
        toMillis = TimeUnit.MILLISECONDS.toMillis(toMillis2 - TimeUnit.SECONDS.toMillis(TimeUnit.MILLISECONDS.toSeconds(toMillis2)));
        return String.format(Locale.getDefault(), "%02d:%02d:%02d:%03d", new Object[]{Long.valueOf(r0), Long.valueOf(j), Long.valueOf(r2), Long.valueOf(toMillis)});
    }

    static void b(String str) {
        if (!i.a().e()) {
            Log.d("AppsFlyer_4.8.13", b(str, false));
        }
        ah.a().b("F", str);
    }

    public static void c(String str) {
        if (LogLevel.DEBUG.getLevel() <= i.a().a("logLevel", LogLevel.NONE.getLevel())) {
            Log.d("AppsFlyer_4.8.13", b(str, false));
        }
        ah.a().b("D", b(str, true));
    }

    public static void e(String str) {
        if (LogLevel.WARNING.getLevel() <= i.a().a("logLevel", LogLevel.NONE.getLevel())) {
            Log.w("AppsFlyer_4.8.13", b(str, false));
        }
        ah.a().b("W", b(str, true));
    }
}
