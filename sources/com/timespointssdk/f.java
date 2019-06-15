package com.timespointssdk;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.payu.custombrowser.util.CBConstant;
import in.til.core.integrations.c;
import java.util.Calendar;
import java.util.Queue;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class f {
    private static e a;
    private static Handler b = new Handler(Looper.getMainLooper());
    private static Runnable c = new Runnable() {
        public void run() {
            Long valueOf;
            if (a.a.booleanValue()) {
                Log.d("TimesPointsConnect", "Handler runnable");
            }
            g.a("logout");
            g.a("logoutinonecall");
            g.a("activitypaused");
            g.c();
            if (f.a == null || f.a.g() == null) {
                valueOf = Long.valueOf(((long) a.c.intValue()) * 1000);
            } else {
                valueOf = Long.valueOf(((long) f.a.g().intValue()) * 1000);
            }
            f.b.postDelayed(this, valueOf.longValue());
        }
    };

    public interface a extends c {
        void a();

        void a(Exception exception);
    }

    public static void a(Context context, String str, String str2, String str3, String str4, String str5, a aVar) {
        try {
            if (a.a.booleanValue()) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Request for init received with userID = ");
                stringBuilder.append(str3);
                stringBuilder.append(" pCode = ");
                stringBuilder.append(str);
                stringBuilder.append(" deviceID = ");
                stringBuilder.append(str4);
                Log.d("TimesPointsConnect", stringBuilder.toString());
            }
            if (!(str == null || context == null || str2 == null || str4 == null || str.isEmpty() || str2.isEmpty())) {
                if (!str4.isEmpty()) {
                    if (a == null) {
                        a = d.c();
                    }
                    d.a(context);
                    if (g.b() && !g.b("userid").equalsIgnoreCase("")) {
                        g.a(false);
                    }
                    if (str3 == null || str3.equals("")) {
                        g.a("userid");
                        g.a(str, str2, "", str4);
                    } else {
                        if (g.b("userid").equalsIgnoreCase(str3)) {
                            g.a(str, str2, str3, str4);
                        } else if (d.b().isEmpty()) {
                            g.a(str, str2, str3, str4);
                        } else {
                            a.a(str3);
                            a.c(str);
                            a.b(str2);
                            a.d(str4);
                            g.a("yes", "logout");
                            g.a("logoutinonecall");
                            g.c();
                        }
                        if (!(str5 == null || str5.equals(""))) {
                            g.a(str5, "ticketID");
                        }
                    }
                    a(context);
                    g.m();
                    if (aVar != null) {
                        aVar.a();
                        return;
                    }
                    return;
                }
            }
            throw new Exception("One or More of mandatory parameters are missing.");
        } catch (Exception e) {
            if (aVar != null) {
                aVar.a(e);
            }
        }
    }

    public static void a(Context context, String str, a aVar) {
        try {
            Intent intent = new Intent(context, ProfileViewActivity.class);
            intent.putExtra("ticketId", str);
            context.startActivity(intent);
            if (aVar != null) {
                aVar.a();
            }
        } catch (Exception e) {
            if (aVar != null) {
                aVar.a(e);
            }
        }
    }

    public static void a(a aVar) {
        try {
            if (a.a.booleanValue()) {
                Log.d("TimesPointsConnect", "TPC flushAll");
            }
            b.removeCallbacks(c);
            g.a("yes", "activitypaused");
            g.a("logoutinonecall");
            g.a("logout");
            g.c();
            if (aVar != null) {
                aVar.a();
            }
        } catch (Exception e) {
            if (aVar != null) {
                aVar.a(e);
            }
        }
    }

    public static void a(Context context, a aVar) throws Exception {
        if (context == null) {
            try {
                throw new Exception("Context is missing");
            } catch (Exception e) {
                if (aVar != null) {
                    aVar.a(e);
                    return;
                }
                return;
            }
        }
        d.a(context);
        if (aVar != null) {
            aVar.a();
        }
    }

    public static void b(a aVar) {
        try {
            if (a == null) {
                a = d.c();
            }
            b.removeCallbacks(c);
            String b = g.b("userid");
            if (!(b == null || b.equals(""))) {
                g.a("yes", "logout");
                g.a("yes", "logoutinonecall");
                g.a();
                g.a("activitypaused");
                g.c();
                a = null;
                g.a("ticketID");
            }
            if (aVar != null) {
                aVar.a();
            }
        } catch (Exception e) {
            if (aVar != null) {
                aVar.a(e);
            }
        }
    }

    public static void a(String str, String str2, String str3, a aVar) {
        try {
            if (a.a.booleanValue()) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Request to credit points received for activityCode = ");
                stringBuilder.append(str);
                stringBuilder.append(" , transactionID : ");
                stringBuilder.append(str2);
                stringBuilder.append(" , txnHash : ");
                stringBuilder.append(str3);
                Log.d("TimesPointsConnect", stringBuilder.toString());
            }
            if (!(str == null || str.isEmpty() || str2 == null || str2.isEmpty() || str3 == null)) {
                if (!str3.isEmpty()) {
                    if (g.b() && !g.b("userid").equalsIgnoreCase("")) {
                        g.a(true);
                    }
                    long timeInMillis = Calendar.getInstance().getTimeInMillis();
                    try {
                        if (timeInMillis < Long.parseLong(g.b("servertime"))) {
                            return;
                        }
                    } catch (Exception e) {
                        ThrowableExtension.printStackTrace(e);
                        g.j();
                    }
                    if (a == null) {
                        a = d.c();
                    }
                    Queue b = d.b();
                    if (b.size() == 0) {
                        a(d.d());
                    }
                    StringBuilder stringBuilder2 = new StringBuilder();
                    stringBuilder2.append(timeInMillis);
                    stringBuilder2.append("");
                    a(str, stringBuilder2.toString(), str2, str3);
                    StringBuilder stringBuilder3;
                    if (a(str)) {
                        if (a.a.booleanValue()) {
                            stringBuilder3 = new StringBuilder();
                            stringBuilder3.append("Priority Act ");
                            stringBuilder3.append(b.size());
                            Log.d("TimesPointsConnect", stringBuilder3.toString());
                        }
                        g.a("logout");
                        g.a("logoutinonecall");
                        g.a("activitypaused");
                        g.c();
                    } else if (b != null && b.size() > a.f().intValue() - 1) {
                        if (a.a.booleanValue()) {
                            stringBuilder3 = new StringBuilder();
                            stringBuilder3.append("listOfActivities.length() = ");
                            stringBuilder3.append(b.size());
                            stringBuilder3.append(" threshhold ");
                            stringBuilder3.append(a.f());
                            Log.d("TimesPointsConnect", stringBuilder3.toString());
                        }
                        g.a("logout");
                        g.a("logoutinonecall");
                        g.a("activitypaused");
                        g.c();
                    }
                    if (aVar != null) {
                        aVar.a();
                    }
                    return;
                }
            }
            throw new Exception("One or More of mandatory parameters are missing.");
        } catch (Exception e2) {
            if (aVar != null) {
                aVar.a(e2);
            }
        }
    }

    private static boolean a(String str) throws NullPointerException {
        if (a == null) {
            a = d.c();
        }
        try {
            JSONArray h = a.h();
            if (h == null) {
                return false;
            }
            for (int i = 0; i < h.length(); i++) {
                if (str.equals(h.getString(i))) {
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
        }
    }

    private static void a(String str, String str2, String str3, String str4) throws NullPointerException {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("aname", str);
            jSONObject.put("createTimeStamp", str2);
            jSONObject.put(CBConstant.TXNID, str3);
            jSONObject.put("txnHash", str4);
            d.b().add(jSONObject);
        } catch (JSONException e) {
            ThrowableExtension.printStackTrace(e);
        } catch (Exception e2) {
            ThrowableExtension.printStackTrace(e2);
        }
    }

    private static void a(Context context) throws NullPointerException {
        try {
            Object valueOf;
            if (a == null) {
                a = d.c();
            }
            if (context != null) {
                d.a(context);
            }
            if (a == null || a.g() == null) {
                valueOf = Long.valueOf(((long) a.c.intValue()) * 1000);
            } else {
                valueOf = Long.valueOf(((long) a.g().intValue()) * 1000);
            }
            if (a.a.booleanValue()) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("foreground sheduling timer to time ");
                stringBuilder.append(valueOf);
                Log.d("TimesPointsConnect", stringBuilder.toString());
            }
            b.removeCallbacks(c);
            b.postDelayed(c, valueOf.longValue());
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
        }
    }
}
