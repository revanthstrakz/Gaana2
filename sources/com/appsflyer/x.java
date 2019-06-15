package com.appsflyer;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

final class x implements SensorEventListener {
    private final int a;
    @NonNull
    private final String b;
    @NonNull
    private final String c;
    private final float[][] d = new float[2][];
    private final long[] e = new long[2];
    private final int f;
    private double g;
    private long h;

    public final void onAccuracyChanged(Sensor sensor, int i) {
    }

    private x(int i, @Nullable String str, @Nullable String str2) {
        this.a = i;
        if (str == null) {
            str = "";
        }
        this.b = str;
        if (str2 == null) {
            str2 = "";
        }
        this.c = str2;
        this.f = ((((i + 31) * 31) + this.b.hashCode()) * 31) + this.c.hashCode();
    }

    static x a(Sensor sensor) {
        return new x(sensor.getType(), sensor.getName(), sensor.getVendor());
    }

    private static double a(@NonNull float[] fArr, @NonNull float[] fArr2) {
        double d = 0.0d;
        for (int i = 0; i < Math.min(fArr.length, fArr2.length); i++) {
            d += StrictMath.pow((double) (fArr[i] - fArr2[i]), 2.0d);
        }
        return Math.sqrt(d);
    }

    @NonNull
    private static List<Float> a(@NonNull float[] fArr) {
        ArrayList arrayList = new ArrayList(fArr.length);
        for (float valueOf : fArr) {
            arrayList.add(Float.valueOf(valueOf));
        }
        return arrayList;
    }

    public final void onSensorChanged(SensorEvent sensorEvent) {
        if (!(sensorEvent == null || sensorEvent.values == null)) {
            Sensor sensor = sensorEvent.sensor;
            int i = (sensor == null || sensor.getName() == null || sensor.getVendor() == null) ? 0 : 1;
            if (i != 0) {
                i = sensorEvent.sensor.getType();
                String name = sensorEvent.sensor.getName();
                String vendor = sensorEvent.sensor.getVendor();
                long j = sensorEvent.timestamp;
                float[] fArr = sensorEvent.values;
                if (a(i, name, vendor)) {
                    long currentTimeMillis = System.currentTimeMillis();
                    float[] fArr2 = this.d[0];
                    if (fArr2 == null) {
                        this.d[0] = Arrays.copyOf(fArr, fArr.length);
                        this.e[0] = currentTimeMillis;
                        return;
                    }
                    float[] fArr3 = this.d[1];
                    if (fArr3 == null) {
                        fArr = Arrays.copyOf(fArr, fArr.length);
                        this.d[1] = fArr;
                        this.e[1] = currentTimeMillis;
                        this.g = a(fArr2, fArr);
                    } else if (50000000 <= j - this.h) {
                        this.h = j;
                        if (Arrays.equals(fArr3, fArr)) {
                            this.e[1] = currentTimeMillis;
                            return;
                        }
                        double a = a(fArr2, fArr);
                        if (a > this.g) {
                            this.d[1] = Arrays.copyOf(fArr, fArr.length);
                            this.e[1] = currentTimeMillis;
                            this.g = a;
                        }
                    }
                }
            }
        }
    }

    /* Access modifiers changed, original: final */
    public final void a(@NonNull Map<x, Map<String, Object>> map) {
        a((Map) map, true);
    }

    public final void b(Map<x, Map<String, Object>> map) {
        a((Map) map, false);
    }

    private boolean a(int i, @NonNull String str, @NonNull String str2) {
        return this.a == i && this.b.equals(str) && this.c.equals(str2);
    }

    @NonNull
    private Map<String, Object> a() {
        HashMap hashMap = new HashMap(7);
        hashMap.put("sT", Integer.valueOf(this.a));
        hashMap.put("sN", this.b);
        hashMap.put("sV", this.c);
        float[] fArr = this.d[0];
        if (fArr != null) {
            hashMap.put("sVS", a(fArr));
        }
        fArr = this.d[1];
        if (fArr != null) {
            hashMap.put("sVE", a(fArr));
        }
        return hashMap;
    }

    private void b() {
        int i = 0;
        for (int i2 = 0; i2 < 2; i2++) {
            this.d[i2] = null;
        }
        while (i < 2) {
            this.e[i] = 0;
            i++;
        }
        this.g = 0.0d;
        this.h = 0;
    }

    public final int hashCode() {
        return this.f;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof x)) {
            return false;
        }
        x xVar = (x) obj;
        return a(xVar.a, xVar.b, xVar.c);
    }

    private void a(@NonNull Map<x, Map<String, Object>> map, boolean z) {
        int i = 0;
        if (this.d[0] != null) {
            i = 1;
        }
        if (i != 0) {
            map.put(this, a());
            if (z) {
                b();
            }
        } else if (!map.containsKey(this)) {
            map.put(this, a());
        }
    }
}
