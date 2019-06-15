package com.g.a;

import android.content.Context;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class a {
    static Context a = null;
    static boolean b = false;
    static boolean c = false;
    static int d = 0;
    static String e = null;
    public static int f = 0;
    public static int g = 1;
    public static int h = 2;
    public static int i = 3;
    public static int j = 4;

    public static void a(Context context) {
        try {
            e = Long.toHexString(Double.doubleToLongBits(Math.random()));
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Transaction ID generated : ");
            stringBuilder.append(e);
            j.a(stringBuilder.toString());
            new d(context).a("mf_transactionid", e);
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
        }
    }

    public static void a(Context context, String str) {
        try {
            j.a("MFilterIt : setApplicationData() ");
            j.a(str);
            new d(context).a("mf_app_data", str);
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            j.a("Exception in sdkInit String appData");
        }
    }

    public static void b(Context context) {
        d dVar = new d(context);
        long b = e.b(context) - e.a(context);
        try {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Time difference : ");
            stringBuilder.append(b);
            j.a(stringBuilder.toString());
            if (b <= 0) {
                dVar.a("mf_is_install", "true");
            } else {
                dVar.a("mf_is_install", InternalLogger.EVENT_PARAM_EXTRAS_FALSE);
            }
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            j.a("Exception while Checking whether update or Install");
        }
    }

    public static String a(Context context, String str, int i) {
        try {
            a = context;
            d dVar = new d(context);
            j.a("MFilterIt : sdkInit() ");
            String format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
            a(context);
            dVar.a("mf_vendor_id", str);
            dVar.a("mf_ex_datapoints", Integer.toString(i));
            dVar.b("mf_total_retrycount", Integer.toString(d));
            if (dVar.b("mf_conversiontime", "").equalsIgnoreCase("")) {
                dVar.a("mf_conversiontime", format);
            }
            b(context);
            String b = dVar.b("mf_transactionid", "");
            a();
            return b;
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            j.a("Exception in sdkInit");
            j.a("sdkInit() completed");
            return "";
        }
    }

    public static void a() {
        j.a("startMFilterItService() started");
        try {
            new Thread() {
                public void run() {
                    j.a("Begin Transaction");
                    new l(a.a).b();
                }
            }.start();
        } catch (Exception e) {
            j.a("Exception in startMFilterItService");
            ThrowableExtension.printStackTrace(e);
        }
    }
}
