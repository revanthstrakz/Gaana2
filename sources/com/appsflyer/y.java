package com.appsflyer;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import com.comscore.utils.Constants;
import com.payu.custombrowser.util.CBConstant;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

final class y {
    private static final BitSet g = new BitSet(6);
    private static final Handler h = new Handler(Looper.getMainLooper());
    private static volatile y i;
    final Handler a;
    final Object b = new Object();
    boolean c;
    final Runnable d = new Runnable() {
        private static String b;
        private static String c;

        AnonymousClass3() {
        }

        public final void run() {
            synchronized (y.this.b) {
                y.this.b();
                y.this.a.postDelayed(y.this.e, Constants.SESSION_INACTIVE_PERIOD);
            }
        }

        static void a(String str) {
            b = str;
            StringBuilder stringBuilder = new StringBuilder();
            int i = 0;
            while (i < str.length()) {
                if (i == 0 || i == str.length() - 1) {
                    stringBuilder.append(str.charAt(i));
                } else {
                    stringBuilder.append(CBConstant.DEFAULT_PAYMENT_URLS);
                }
                i++;
            }
            c = stringBuilder.toString();
        }

        static void b(String str) {
            if (b == null) {
                AnonymousClass3.a(i.a().a("AppsFlyerKey"));
            }
            if (b != null && str.contains(b)) {
                AFLogger.d(str.replace(b, c));
            }
        }
    };
    final Runnable e = new Runnable() {
        public final void run() {
            synchronized (y.this.b) {
                y.this.a();
                y.this.a.postDelayed(y.this.d, 500);
                y.this.c = true;
            }
        }
    };
    final Runnable f = new Runnable() {
        public final void run() {
            synchronized (y.this.b) {
                if (y.this.c) {
                    y.this.a.removeCallbacks(y.this.e);
                    y.this.a.removeCallbacks(y.this.d);
                    y.this.b();
                    y.this.c = false;
                }
            }
        }
    };
    private final Map<x, x> j = new HashMap(g.size());
    private final Map<x, Map<String, Object>> k = new HashMap(g.size());
    private final SensorManager l;
    private boolean m;

    static {
        g.set(1);
        g.set(2);
        g.set(4);
    }

    private y(@NonNull SensorManager sensorManager, Handler handler) {
        this.l = sensorManager;
        this.a = handler;
    }

    static y a(Context context) {
        return a((SensorManager) context.getApplicationContext().getSystemService("sensor"), h);
    }

    private static y a(SensorManager sensorManager, Handler handler) {
        if (i == null) {
            synchronized (y.class) {
                if (i == null) {
                    i = new y(sensorManager, handler);
                }
            }
        }
        return i;
    }

    /* Access modifiers changed, original: final */
    public final void a() {
        try {
            for (Sensor sensor : this.l.getSensorList(-1)) {
                int type = sensor.getType();
                boolean z = type >= 0 && g.get(type);
                if (z) {
                    x a = x.a(sensor);
                    if (!this.j.containsKey(a)) {
                        this.j.put(a, a);
                    }
                    this.l.registerListener((SensorEventListener) this.j.get(a), sensor, 0);
                }
            }
        } catch (Throwable unused) {
        }
        this.m = true;
    }

    /* Access modifiers changed, original: final */
    public final void b() {
        try {
            if (!this.j.isEmpty()) {
                for (x xVar : this.j.values()) {
                    this.l.unregisterListener(xVar);
                    xVar.a(this.k);
                }
            }
        } catch (Throwable unused) {
        }
        this.m = false;
    }

    /* Access modifiers changed, original: final */
    @NonNull
    public final List<Map<String, Object>> c() {
        synchronized (this.b) {
            if (!this.j.isEmpty() && this.m) {
                for (x b : this.j.values()) {
                    b.b(this.k);
                }
            }
            if (this.k.isEmpty()) {
                List emptyList = Collections.emptyList();
                return emptyList;
            }
            ArrayList arrayList = new ArrayList(this.k.values());
            return arrayList;
        }
    }
}
