package com.helpshift;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Build.VERSION;
import com.helpshift.a.a;
import com.helpshift.exceptions.InstallException;
import com.helpshift.executors.ActionExecutor;
import com.helpshift.j.d;
import com.helpshift.util.a.b;
import com.helpshift.util.l;
import com.helpshift.util.o;
import com.helpshift.util.t;
import com.helpshift.util.w;
import java.util.Map;

public class c {
    static a a;

    public static void a(a aVar) {
        a = aVar;
    }

    protected static void a() {
        if (a == null) {
            throw new ExceptionInInitializerError("com.helpshift.Core.init() method not called");
        }
    }

    public static void a(final String str, final String str2) {
        a();
        b.a().a(new Runnable() {
            public void run() {
                c.a.a(str, str2);
            }
        });
    }

    public static void a(Application application, String str, String str2, String str3, Map map) throws InstallException {
        a();
        final String trim = !w.a(str) ? str.trim() : str;
        final String trim2 = !w.a(str2) ? str2.trim() : str2;
        final String trim3 = !w.a(str3) ? str3.trim() : str3;
        t.a(trim, trim2, trim3);
        com.helpshift.util.a.a a = b.a();
        final Application application2 = application;
        final String str4 = trim;
        final String str5 = trim2;
        final String str6 = trim3;
        final Map map2 = map;
        a.b(new Runnable() {
            public void run() {
                c.a.a(application2, str4, str5, str6, map2);
            }
        });
        final Map map3 = map;
        str5 = str;
        str6 = str2;
        final String str7 = str3;
        a.a(new Runnable() {
            public void run() {
                c.a(application2.getApplicationContext(), map3);
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Helpshift install :\n Flavor : ");
                stringBuilder.append(c.a.getClass().getSimpleName());
                stringBuilder.append("\n Apikey : ");
                stringBuilder.append(str5);
                stringBuilder.append("\n Domain : ");
                stringBuilder.append(str6);
                stringBuilder.append("\n AppId : ");
                stringBuilder.append(str7);
                stringBuilder.append("\n Config : ");
                stringBuilder.append(map3.toString());
                stringBuilder.append("\n Package Id : ");
                stringBuilder.append(application2.getPackageName());
                stringBuilder.append("\n SDK version : ");
                stringBuilder.append("6.4.0");
                stringBuilder.append("\n OS version : ");
                stringBuilder.append(VERSION.SDK_INT);
                stringBuilder.append("\n Device : ");
                stringBuilder.append(Build.DEVICE);
                l.a("Helpshift_CoreInternal", stringBuilder.toString());
                c.a.b(application2, trim, trim2, trim3, map3);
            }
        });
    }

    public static void a(final Context context, final String str) {
        a();
        b.a().a(new Runnable() {
            public void run() {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Registering push token : ");
                stringBuilder.append(str);
                l.a("Helpshift_CoreInternal", stringBuilder.toString());
                c.a.a(context, str);
            }
        });
    }

    public static void a(final Context context, final Intent intent) {
        a();
        b.a().c(new Runnable() {
            public void run() {
                l.a("Helpshift_CoreInternal", "Handling push on main thread");
                c.a.a(context, intent);
            }
        });
    }

    public static ActionExecutor b() {
        return a.a();
    }

    static void a(Context context, Map map) {
        Object obj = map.get("enableLogging");
        int i = 0;
        boolean z = (obj instanceof Boolean) && ((Boolean) obj).booleanValue();
        Object obj2 = map.get("disableErrorLogging");
        if (obj2 == null) {
            obj2 = map.get("disableErrorReporting");
        }
        if ((obj2 instanceof Boolean) && ((Boolean) obj2).booleanValue()) {
            i = 1;
        }
        float a = o.c().q().a();
        l.a(d.a(context, "__hs_log_store"));
        com.helpshift.j.c.d.a(new com.helpshift.j.c.c());
        l.a(a);
        l.a(z, i ^ 1);
        com.helpshift.p.a.a(i ^ 1);
        if (i == 0) {
            com.helpshift.exceptions.a.a.a(context);
        }
        if (l.c() == 0) {
            l.b();
        }
    }
}
