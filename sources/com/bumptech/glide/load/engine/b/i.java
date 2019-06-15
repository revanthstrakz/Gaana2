package com.bumptech.glide.load.engine.b;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Build.VERSION;
import android.text.format.Formatter;
import android.util.DisplayMetrics;
import android.util.Log;

public final class i {
    private final int a;
    private final int b;
    private final Context c;
    private final int d;

    public static final class a {
        static final int a = (VERSION.SDK_INT < 26 ? 4 : 1);
        private final Context b;
        private ActivityManager c;
        private c d;
        private float e = 2.0f;
        private float f = ((float) a);
        private float g = 0.4f;
        private float h = 0.33f;
        private int i = 4194304;

        public a(Context context) {
            this.b = context;
            this.c = (ActivityManager) context.getSystemService("activity");
            this.d = new b(context.getResources().getDisplayMetrics());
            if (VERSION.SDK_INT >= 26 && i.b(this.c)) {
                this.f = 0.0f;
            }
        }

        public i a() {
            return new i(this);
        }
    }

    interface c {
        int a();

        int b();
    }

    private static final class b implements c {
        private final DisplayMetrics a;

        public b(DisplayMetrics displayMetrics) {
            this.a = displayMetrics;
        }

        public int a() {
            return this.a.widthPixels;
        }

        public int b() {
            return this.a.heightPixels;
        }
    }

    i(a aVar) {
        int c;
        this.c = aVar.b;
        if (b(aVar.c)) {
            c = aVar.i / 2;
        } else {
            c = aVar.i;
        }
        this.d = c;
        c = a(aVar.c, aVar.g, aVar.h);
        float a = (float) ((aVar.d.a() * aVar.d.b()) * 4);
        int round = Math.round(aVar.f * a);
        int round2 = Math.round(a * aVar.e);
        int i = c - this.d;
        int i2 = round2 + round;
        if (i2 <= i) {
            this.b = round2;
            this.a = round;
        } else {
            a = ((float) i) / (aVar.f + aVar.e);
            this.b = Math.round(aVar.e * a);
            this.a = Math.round(a * aVar.f);
        }
        if (Log.isLoggable("MemorySizeCalculator", 3)) {
            String str = "MemorySizeCalculator";
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Calculation complete, Calculated memory cache size: ");
            stringBuilder.append(a(this.b));
            stringBuilder.append(", pool size: ");
            stringBuilder.append(a(this.a));
            stringBuilder.append(", byte array size: ");
            stringBuilder.append(a(this.d));
            stringBuilder.append(", memory class limited? ");
            stringBuilder.append(i2 > c);
            stringBuilder.append(", max size: ");
            stringBuilder.append(a(c));
            stringBuilder.append(", memoryClass: ");
            stringBuilder.append(aVar.c.getMemoryClass());
            stringBuilder.append(", isLowMemoryDevice: ");
            stringBuilder.append(b(aVar.c));
            Log.d(str, stringBuilder.toString());
        }
    }

    public int a() {
        return this.b;
    }

    public int b() {
        return this.a;
    }

    public int c() {
        return this.d;
    }

    private static int a(ActivityManager activityManager, float f, float f2) {
        float memoryClass = (float) ((activityManager.getMemoryClass() * 1024) * 1024);
        if (b(activityManager)) {
            f = f2;
        }
        return Math.round(memoryClass * f);
    }

    private String a(int i) {
        return Formatter.formatFileSize(this.c, (long) i);
    }

    private static boolean b(ActivityManager activityManager) {
        return VERSION.SDK_INT >= 19 ? activityManager.isLowRamDevice() : false;
    }
}
