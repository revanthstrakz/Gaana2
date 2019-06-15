package com.timespointssdk;

import android.content.Context;
import android.util.Log;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;
import com.timespointssdk.c.e;
import java.util.LinkedList;
import java.util.Queue;
import org.json.JSONObject;

class d {
    private static e a;
    private static Context b;
    private static Queue<JSONObject> c;
    private static Tracker d;

    public static synchronized Tracker a() {
        Tracker tracker;
        synchronized (d.class) {
            if (d == null) {
                d = GoogleAnalytics.getInstance(d()).newTracker(e.global_tracker);
            }
            tracker = d;
        }
        return tracker;
    }

    protected static Queue<JSONObject> b() {
        if (c == null) {
            String b = g.b("activityqueue");
            if (b == null && b.equals("")) {
                c = new LinkedList();
            } else {
                c = g.c(b);
            }
        }
        return c;
    }

    protected static e c() {
        if (a != null) {
            return a;
        }
        a = e.a();
        return a;
    }

    protected static Context d() {
        return b;
    }

    protected static void a(Context context) {
        b = context;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("--");
        stringBuilder.append(context);
        Log.e("setmContext", stringBuilder.toString());
    }
}
