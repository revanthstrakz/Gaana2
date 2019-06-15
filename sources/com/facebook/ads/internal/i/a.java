package com.facebook.ads.internal.i;

import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.StatFs;
import com.integralads.avid.library.inmobi.utils.AvidJSONUtil;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class a {
    private static SensorManager a;
    private static Sensor b;
    private static Sensor c;
    private static volatile float[] d;
    private static volatile float[] e;
    private static Map<String, String> f = new ConcurrentHashMap();
    private static String[] g = new String[]{AvidJSONUtil.KEY_X, AvidJSONUtil.KEY_Y, "z"};
    private static SensorEventListener h;
    private static SensorEventListener i;

    private static class a implements SensorEventListener {
        private a() {
        }

        public void onAccuracyChanged(Sensor sensor, int i) {
        }

        public void onSensorChanged(SensorEvent sensorEvent) {
            a.d = sensorEvent.values;
            a.d();
        }
    }

    private static class b implements SensorEventListener {
        private b() {
        }

        public void onAccuracyChanged(Sensor sensor, int i) {
        }

        public void onSensorChanged(SensorEvent sensorEvent) {
            a.e = sensorEvent.values;
            a.e();
        }
    }

    public static Map<String, String> a() {
        Map hashMap = new HashMap();
        hashMap.putAll(f);
        a(hashMap);
        return hashMap;
    }

    /* JADX WARNING: Missing block: B:27:0x006d, code skipped:
            return;
     */
    public static synchronized void a(android.content.Context r5) {
        /*
        r0 = com.facebook.ads.internal.i.a.class;
        monitor-enter(r0);
        b(r5);	 Catch:{ all -> 0x006e }
        c(r5);	 Catch:{ all -> 0x006e }
        d(r5);	 Catch:{ all -> 0x006e }
        r1 = a;	 Catch:{ all -> 0x006e }
        if (r1 != 0) goto L_0x0020;
    L_0x0010:
        r1 = "sensor";
        r5 = r5.getSystemService(r1);	 Catch:{ all -> 0x006e }
        r5 = (android.hardware.SensorManager) r5;	 Catch:{ all -> 0x006e }
        a = r5;	 Catch:{ all -> 0x006e }
        r5 = a;	 Catch:{ all -> 0x006e }
        if (r5 != 0) goto L_0x0020;
    L_0x001e:
        monitor-exit(r0);
        return;
    L_0x0020:
        r5 = b;	 Catch:{ all -> 0x006e }
        if (r5 != 0) goto L_0x002d;
    L_0x0024:
        r5 = a;	 Catch:{ all -> 0x006e }
        r1 = 1;
        r5 = r5.getDefaultSensor(r1);	 Catch:{ all -> 0x006e }
        b = r5;	 Catch:{ all -> 0x006e }
    L_0x002d:
        r5 = c;	 Catch:{ all -> 0x006e }
        if (r5 != 0) goto L_0x003a;
    L_0x0031:
        r5 = a;	 Catch:{ all -> 0x006e }
        r1 = 4;
        r5 = r5.getDefaultSensor(r1);	 Catch:{ all -> 0x006e }
        c = r5;	 Catch:{ all -> 0x006e }
    L_0x003a:
        r5 = h;	 Catch:{ all -> 0x006e }
        r1 = 3;
        r2 = 0;
        if (r5 != 0) goto L_0x0054;
    L_0x0040:
        r5 = new com.facebook.ads.internal.i.a$a;	 Catch:{ all -> 0x006e }
        r5.<init>();	 Catch:{ all -> 0x006e }
        h = r5;	 Catch:{ all -> 0x006e }
        r5 = b;	 Catch:{ all -> 0x006e }
        if (r5 == 0) goto L_0x0054;
    L_0x004b:
        r5 = a;	 Catch:{ all -> 0x006e }
        r3 = h;	 Catch:{ all -> 0x006e }
        r4 = b;	 Catch:{ all -> 0x006e }
        r5.registerListener(r3, r4, r1);	 Catch:{ all -> 0x006e }
    L_0x0054:
        r5 = i;	 Catch:{ all -> 0x006e }
        if (r5 != 0) goto L_0x006c;
    L_0x0058:
        r5 = new com.facebook.ads.internal.i.a$b;	 Catch:{ all -> 0x006e }
        r5.<init>();	 Catch:{ all -> 0x006e }
        i = r5;	 Catch:{ all -> 0x006e }
        r5 = c;	 Catch:{ all -> 0x006e }
        if (r5 == 0) goto L_0x006c;
    L_0x0063:
        r5 = a;	 Catch:{ all -> 0x006e }
        r2 = i;	 Catch:{ all -> 0x006e }
        r3 = c;	 Catch:{ all -> 0x006e }
        r5.registerListener(r2, r3, r1);	 Catch:{ all -> 0x006e }
    L_0x006c:
        monitor-exit(r0);
        return;
    L_0x006e:
        r5 = move-exception;
        monitor-exit(r0);
        throw r5;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.ads.internal.i.a.a(android.content.Context):void");
    }

    private static void a(Map<String, String> map) {
        float[] fArr = d;
        float[] fArr2 = e;
        int i = 0;
        if (fArr != null) {
            int min = Math.min(g.length, fArr.length);
            for (int i2 = 0; i2 < min; i2++) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("accelerometer_");
                stringBuilder.append(g[i2]);
                map.put(stringBuilder.toString(), String.valueOf(fArr[i2]));
            }
        }
        if (fArr2 != null) {
            int min2 = Math.min(g.length, fArr2.length);
            while (i < min2) {
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append("rotation_");
                stringBuilder2.append(g[i]);
                map.put(stringBuilder2.toString(), String.valueOf(fArr2[i]));
                i++;
            }
        }
    }

    private static void b(Context context) {
        MemoryInfo memoryInfo = new MemoryInfo();
        ((ActivityManager) context.getSystemService("activity")).getMemoryInfo(memoryInfo);
        f.put("available_memory", String.valueOf(memoryInfo.availMem));
        if (VERSION.SDK_INT >= 16) {
            f.put("total_memory", String.valueOf(memoryInfo.totalMem));
        }
    }

    private static void c(Context context) {
        StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
        long availableBlocks = (long) statFs.getAvailableBlocks();
        f.put("free_space", String.valueOf(availableBlocks * ((long) statFs.getBlockSize())));
    }

    private static synchronized void d() {
        synchronized (a.class) {
            if (a != null) {
                a.unregisterListener(h);
            }
            h = null;
        }
    }

    private static void d(Context context) {
        Intent registerReceiver = context.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        if (registerReceiver != null) {
            int intExtra = registerReceiver.getIntExtra("level", -1);
            int intExtra2 = registerReceiver.getIntExtra("scale", -1);
            int intExtra3 = registerReceiver.getIntExtra("status", -1);
            Object obj = (intExtra3 == 2 || intExtra3 == 5) ? 1 : null;
            float f = 0.0f;
            if (intExtra2 > 0) {
                f = 100.0f * (((float) intExtra) / ((float) intExtra2));
            }
            f.put("battery", String.valueOf(f));
            f.put("charging", obj != null ? "1" : "0");
        }
    }

    private static synchronized void e() {
        synchronized (a.class) {
            if (a != null) {
                a.unregisterListener(i);
            }
            i = null;
        }
    }
}
