package com.services;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Point;
import android.os.Build.VERSION;
import android.view.Display;
import android.view.WindowManager;
import com.gaana.application.GaanaApplication;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.managers.ap;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class d {
    private static d a;
    private static final Map<String, Object> e = new HashMap();
    private Context b = GaanaApplication.getContext();
    private SharedPreferences c = null;
    private Editor d = null;

    static class a {
        String a;
        Object b;
        int c;

        a(String str, Object obj, int i) {
            this.a = str;
            this.b = obj;
            this.c = i;
        }
    }

    public static class b {
        private static final Context a = GaanaApplication.getContext();
        private static final Executor b = Executors.newSingleThreadExecutor();

        public static void a(a aVar) {
            b.execute(new e(aVar));
        }

        static final /* synthetic */ void b(a aVar) {
            Editor edit = a.getSharedPreferences(aVar.a, d.e()).edit();
            switch (aVar.c) {
                case 0:
                    edit.putString(aVar.a, (String) aVar.b);
                    break;
                case 1:
                    edit.putInt(aVar.a, ((Integer) aVar.b).intValue());
                    break;
                case 2:
                    edit.putLong(aVar.a, ((Long) aVar.b).longValue());
                    break;
                case 3:
                    edit.putLong(aVar.a, Double.doubleToRawLongBits(((Double) aVar.b).doubleValue()));
                    break;
                case 5:
                    edit.putBoolean(aVar.a, ((Boolean) aVar.b).booleanValue());
                    break;
            }
            edit.apply();
        }
    }

    private d() {
    }

    public static d a() {
        if (a == null) {
            a = new d();
        }
        return a;
    }

    public int b() {
        Display defaultDisplay = ((WindowManager) this.b.getSystemService("window")).getDefaultDisplay();
        if (!com.utilities.d.c()) {
            return defaultDisplay.getWidth();
        }
        Point point = new Point();
        defaultDisplay.getSize(point);
        return point.x;
    }

    public int c() {
        Display defaultDisplay = ((WindowManager) this.b.getSystemService("window")).getDefaultDisplay();
        if (!com.utilities.d.c()) {
            return defaultDisplay.getHeight();
        }
        Point point = new Point();
        defaultDisplay.getSize(point);
        return point.y;
    }

    public float d() {
        return this.b.getResources().getDisplayMetrics().density;
    }

    public int a(int i) {
        return Math.round(((float) i) * d());
    }

    public void a(String str, String str2, boolean z) {
        if (z) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(ap.a().b());
            stringBuilder.append(str);
            str = stringBuilder.toString();
        }
        b.a(new a(str, str2, 0));
        e.put(str, str2);
    }

    public void a(String str, int i, boolean z) {
        if (z) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(ap.a().b());
            stringBuilder.append(str);
            str = stringBuilder.toString();
        }
        b.a(new a(str, Integer.valueOf(i), 1));
        e.put(str, Integer.valueOf(i));
    }

    public void a(String str, boolean z, boolean z2) {
        if (z2) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(ap.a().b());
            stringBuilder.append(str);
            str = stringBuilder.toString();
        }
        b.a(new a(str, Boolean.valueOf(z), 5));
        e.put(str, Boolean.valueOf(z));
    }

    public void a(long j, String str, boolean z) {
        if (z) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(ap.a().b());
            stringBuilder.append(str);
            str = stringBuilder.toString();
        }
        b.a(new a(str, Long.valueOf(j), 2));
        e.put(str, Long.valueOf(j));
    }

    public void a(double d, String str, boolean z) {
        if (z) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(ap.a().b());
            stringBuilder.append(str);
            str = stringBuilder.toString();
        }
        b.a(new a(str, Double.valueOf(d), 3));
        e.put(str, Long.valueOf(Double.doubleToRawLongBits(d)));
    }

    public double b(double d, String str, boolean z) {
        Object str2;
        if (z) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(ap.a().b());
            stringBuilder.append(str2);
            str2 = stringBuilder.toString();
        }
        if (e.containsKey(str2)) {
            return Double.longBitsToDouble(((Long) e.get(str2)).longValue());
        }
        this.c = this.b.getSharedPreferences(str2, e());
        long j = this.c.getLong(str2, Double.doubleToRawLongBits(d));
        double longBitsToDouble = Double.longBitsToDouble(j);
        e.put(str2, Long.valueOf(j));
        return longBitsToDouble;
    }

    public void a(int i, String str, boolean z) {
        if (z) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(ap.a().b());
            stringBuilder.append(str);
            str = stringBuilder.toString();
        }
        b.a(new a(str, Integer.valueOf(i), 1));
        e.put(str, Integer.valueOf(i));
    }

    public void a(HashMap<String, Long> hashMap, String str, boolean z) {
        if (z) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(ap.a().b());
            stringBuilder.append(str);
            str = stringBuilder.toString();
        }
        String toJson = new Gson().toJson((Object) hashMap);
        this.c = this.b.getSharedPreferences(str, e());
        this.d = this.c.edit();
        this.d.putString(str, toJson);
        this.d.apply();
    }

    public HashMap<String, Long> a(String str, boolean z) {
        if (z) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(ap.a().b());
            stringBuilder.append(str);
            str = stringBuilder.toString();
        }
        this.c = this.b.getSharedPreferences(str, e());
        return (HashMap) new Gson().fromJson(this.c.getString(str, null), new TypeToken<HashMap<String, Long>>() {
        }.getType());
    }

    public void b(String str, boolean z) {
        if (z) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(ap.a().b());
            stringBuilder.append(str);
            str = stringBuilder.toString();
        }
        this.c = this.b.getSharedPreferences(str, e());
        this.d = this.c.edit();
        this.d.remove(str);
        this.d.apply();
        e.remove(str);
    }

    public String c(String str, boolean z) {
        Object str2;
        if (z) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(ap.a().b());
            stringBuilder.append(str2);
            str2 = stringBuilder.toString();
        }
        if (e.containsKey(str2)) {
            return (String) e.get(str2);
        }
        this.c = this.b.getSharedPreferences(str2, e());
        String string = this.c.getString(str2, null);
        e.put(str2, string);
        return string;
    }

    public String b(String str, String str2, boolean z) {
        Object str3;
        if (z) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(ap.a().b());
            stringBuilder.append(str3);
            str3 = stringBuilder.toString();
        }
        if (e.containsKey(str3)) {
            return (String) e.get(str3);
        }
        this.c = this.b.getSharedPreferences(str3, e());
        str2 = this.c.getString(str3, str2);
        e.put(str3, str2);
        return str2;
    }

    public int b(String str, int i, boolean z) {
        Object str2;
        if (z) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(ap.a().b());
            stringBuilder.append(str2);
            str2 = stringBuilder.toString();
        }
        if (e.containsKey(str2)) {
            return ((Integer) e.get(str2)).intValue();
        }
        this.c = this.b.getSharedPreferences(str2, e());
        i = this.c.getInt(str2, i);
        e.put(str2, Integer.valueOf(i));
        return i;
    }

    public boolean b(String str, boolean z, boolean z2) {
        Object str2;
        if (z2) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(ap.a().b());
            stringBuilder.append(str2);
            str2 = stringBuilder.toString();
        }
        if (e.containsKey(str2)) {
            return ((Boolean) e.get(str2)).booleanValue();
        }
        this.c = this.b.getSharedPreferences(str2, e());
        z = this.c.getBoolean(str2, z);
        e.put(str2, Boolean.valueOf(z));
        return z;
    }

    public long b(long j, String str, boolean z) {
        Object str2;
        if (z) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(ap.a().b());
            stringBuilder.append(str2);
            str2 = stringBuilder.toString();
        }
        if (e.containsKey(str2)) {
            return ((Long) e.get(str2)).longValue();
        }
        this.c = this.b.getSharedPreferences(str2, e());
        j = this.c.getLong(str2, j);
        e.put(str2, Long.valueOf(j));
        return j;
    }

    public boolean d(String str, boolean z) {
        if (z) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(ap.a().b());
            stringBuilder.append(str);
            str = stringBuilder.toString();
        }
        this.c = this.b.getSharedPreferences(str, e());
        return this.c.contains(str);
    }

    public static int e() {
        return VERSION.SDK_INT >= 11 ? 4 : 0;
    }
}
