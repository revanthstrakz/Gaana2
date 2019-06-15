package com.facebook.ads.internal.h;

import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.support.annotation.WorkerThread;
import android.util.Log;
import com.facebook.ads.internal.s.a.o;
import com.facebook.ads.internal.s.a.s;
import com.facebook.ads.internal.s.a.v;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import org.json.JSONArray;
import org.json.JSONObject;

public class e {
    private static final String a = "com.facebook.ads.internal.h.e";
    private static final Object b = new Object();
    private static final Set<String> c = Collections.synchronizedSet(new HashSet());
    private static final Map<String, Integer> d = Collections.synchronizedMap(new HashMap());

    public static d a(Exception exception, Context context, Map<String, String> map) {
        d dVar;
        try {
            dVar = new d(o.b(), o.c(), new b(s.a((Throwable) exception), map, true).a());
            try {
                a(dVar, context);
                return dVar;
            } catch (Exception unused) {
                return dVar;
            }
        } catch (Exception unused2) {
            dVar = null;
            return dVar;
        }
    }

    @WorkerThread
    public static JSONArray a(Context context) {
        return a(context, -1);
    }

    /* JADX WARNING: Removed duplicated region for block: B:57:0x00be A:{SYNTHETIC, Splitter:B:57:0x00be} */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x00c6 A:{Catch:{ IOException -> 0x00c2 }} */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x00cb A:{Catch:{ IOException -> 0x00c2 }} */
    /* JADX WARNING: Removed duplicated region for block: B:72:0x00da A:{SYNTHETIC, Splitter:B:72:0x00da} */
    /* JADX WARNING: Removed duplicated region for block: B:79:0x00e4 A:{Catch:{ IOException -> 0x00e0 }} */
    /* JADX WARNING: Removed duplicated region for block: B:81:0x00e9 A:{Catch:{ IOException -> 0x00e0 }} */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x00be A:{SYNTHETIC, Splitter:B:57:0x00be} */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x00c6 A:{Catch:{ IOException -> 0x00c2 }} */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x00cb A:{Catch:{ IOException -> 0x00c2 }} */
    /* JADX WARNING: Removed duplicated region for block: B:72:0x00da A:{SYNTHETIC, Splitter:B:72:0x00da} */
    /* JADX WARNING: Removed duplicated region for block: B:79:0x00e4 A:{Catch:{ IOException -> 0x00e0 }} */
    /* JADX WARNING: Removed duplicated region for block: B:81:0x00e9 A:{Catch:{ IOException -> 0x00e0 }} */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x00be A:{SYNTHETIC, Splitter:B:57:0x00be} */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x00c6 A:{Catch:{ IOException -> 0x00c2 }} */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x00cb A:{Catch:{ IOException -> 0x00c2 }} */
    /* JADX WARNING: Removed duplicated region for block: B:72:0x00da A:{SYNTHETIC, Splitter:B:72:0x00da} */
    /* JADX WARNING: Removed duplicated region for block: B:79:0x00e4 A:{Catch:{ IOException -> 0x00e0 }} */
    /* JADX WARNING: Removed duplicated region for block: B:81:0x00e9 A:{Catch:{ IOException -> 0x00e0 }} */
    @android.support.annotation.WorkerThread
    public static org.json.JSONArray a(android.content.Context r8, int r9) {
        /*
        r0 = new org.json.JSONArray;
        r0.<init>();
        r1 = b;
        monitor-enter(r1);
        r2 = 0;
        r3 = new java.io.File;	 Catch:{ IOException | JSONException -> 0x00b2, IOException | JSONException -> 0x00b2, all -> 0x00ad }
        r4 = r8.getFilesDir();	 Catch:{ IOException | JSONException -> 0x00b2, IOException | JSONException -> 0x00b2, all -> 0x00ad }
        r5 = "debuglogs";
        r3.<init>(r4, r5);	 Catch:{ IOException | JSONException -> 0x00b2, IOException | JSONException -> 0x00b2, all -> 0x00ad }
        r3 = r3.exists();	 Catch:{ IOException | JSONException -> 0x00b2, IOException | JSONException -> 0x00b2, all -> 0x00ad }
        if (r3 == 0) goto L_0x0090;
    L_0x001a:
        r3 = "debuglogs";
        r8 = r8.openFileInput(r3);	 Catch:{ IOException | JSONException -> 0x00b2, IOException | JSONException -> 0x00b2, all -> 0x00ad }
        r3 = new java.io.InputStreamReader;	 Catch:{ IOException | JSONException -> 0x008d, IOException | JSONException -> 0x008d, all -> 0x008a }
        r3.<init>(r8);	 Catch:{ IOException | JSONException -> 0x008d, IOException | JSONException -> 0x008d, all -> 0x008a }
        r4 = new java.io.BufferedReader;	 Catch:{ IOException | JSONException -> 0x0088, IOException | JSONException -> 0x0088 }
        r4.<init>(r3);	 Catch:{ IOException | JSONException -> 0x0088, IOException | JSONException -> 0x0088 }
    L_0x002a:
        r2 = r4.readLine();	 Catch:{ IOException | JSONException -> 0x0085, IOException | JSONException -> 0x0085, all -> 0x0082 }
        if (r2 == 0) goto L_0x0080;
    L_0x0030:
        if (r9 == 0) goto L_0x0080;
    L_0x0032:
        r5 = new org.json.JSONObject;	 Catch:{ IOException | JSONException -> 0x0085, IOException | JSONException -> 0x0085, all -> 0x0082 }
        r5.<init>(r2);	 Catch:{ IOException | JSONException -> 0x0085, IOException | JSONException -> 0x0085, all -> 0x0082 }
        r2 = "attempt";
        r2 = r5.has(r2);	 Catch:{ IOException | JSONException -> 0x0085, IOException | JSONException -> 0x0085, all -> 0x0082 }
        if (r2 != 0) goto L_0x0049;
    L_0x003f:
        r2 = "attempt";
        r6 = 0;
        r6 = java.lang.String.valueOf(r6);	 Catch:{ IOException | JSONException -> 0x0085, IOException | JSONException -> 0x0085, all -> 0x0082 }
        r5.put(r2, r6);	 Catch:{ IOException | JSONException -> 0x0085, IOException | JSONException -> 0x0085, all -> 0x0082 }
    L_0x0049:
        r2 = "id";
        r2 = r5.getString(r2);	 Catch:{ IOException | JSONException -> 0x0085, IOException | JSONException -> 0x0085, all -> 0x0082 }
        r6 = c;	 Catch:{ IOException | JSONException -> 0x0085, IOException | JSONException -> 0x0085, all -> 0x0082 }
        r6 = r6.contains(r2);	 Catch:{ IOException | JSONException -> 0x0085, IOException | JSONException -> 0x0085, all -> 0x0082 }
        if (r6 != 0) goto L_0x002a;
    L_0x0057:
        r6 = "attempt";
        r6 = r5.getInt(r6);	 Catch:{ IOException | JSONException -> 0x0085, IOException | JSONException -> 0x0085, all -> 0x0082 }
        r7 = d;	 Catch:{ IOException | JSONException -> 0x0085, IOException | JSONException -> 0x0085, all -> 0x0082 }
        r7 = r7.containsKey(r2);	 Catch:{ IOException | JSONException -> 0x0085, IOException | JSONException -> 0x0085, all -> 0x0082 }
        if (r7 == 0) goto L_0x0075;
    L_0x0065:
        r6 = "attempt";
        r7 = d;	 Catch:{ IOException | JSONException -> 0x0085, IOException | JSONException -> 0x0085, all -> 0x0082 }
        r2 = r7.get(r2);	 Catch:{ IOException | JSONException -> 0x0085, IOException | JSONException -> 0x0085, all -> 0x0082 }
        r2 = java.lang.String.valueOf(r2);	 Catch:{ IOException | JSONException -> 0x0085, IOException | JSONException -> 0x0085, all -> 0x0082 }
        r5.put(r6, r2);	 Catch:{ IOException | JSONException -> 0x0085, IOException | JSONException -> 0x0085, all -> 0x0082 }
        goto L_0x0078;
    L_0x0075:
        a(r2, r6);	 Catch:{ IOException | JSONException -> 0x0085, IOException | JSONException -> 0x0085, all -> 0x0082 }
    L_0x0078:
        r0.put(r5);	 Catch:{ IOException | JSONException -> 0x0085, IOException | JSONException -> 0x0085, all -> 0x0082 }
        if (r9 <= 0) goto L_0x002a;
    L_0x007d:
        r9 = r9 + -1;
        goto L_0x002a;
    L_0x0080:
        r2 = r4;
        goto L_0x0092;
    L_0x0082:
        r9 = move-exception;
        goto L_0x00d8;
    L_0x0085:
        r9 = move-exception;
        r2 = r4;
        goto L_0x00b5;
    L_0x0088:
        r9 = move-exception;
        goto L_0x00b5;
    L_0x008a:
        r9 = move-exception;
        r3 = r2;
        goto L_0x00b0;
    L_0x008d:
        r9 = move-exception;
        r3 = r2;
        goto L_0x00b5;
    L_0x0090:
        r8 = r2;
        r3 = r8;
    L_0x0092:
        if (r2 == 0) goto L_0x009a;
    L_0x0094:
        r2.close();	 Catch:{ IOException -> 0x0098 }
        goto L_0x009a;
    L_0x0098:
        r8 = move-exception;
        goto L_0x00a5;
    L_0x009a:
        if (r3 == 0) goto L_0x009f;
    L_0x009c:
        r3.close();	 Catch:{ IOException -> 0x0098 }
    L_0x009f:
        if (r8 == 0) goto L_0x00d4;
    L_0x00a1:
        r8.close();	 Catch:{ IOException -> 0x0098 }
        goto L_0x00d4;
    L_0x00a5:
        r9 = a;	 Catch:{ all -> 0x00de }
        r2 = "Failed to close buffers";
    L_0x00a9:
        android.util.Log.e(r9, r2, r8);	 Catch:{ all -> 0x00de }
        goto L_0x00d4;
    L_0x00ad:
        r9 = move-exception;
        r8 = r2;
        r3 = r8;
    L_0x00b0:
        r4 = r3;
        goto L_0x00d8;
    L_0x00b2:
        r9 = move-exception;
        r8 = r2;
        r3 = r8;
    L_0x00b5:
        r4 = a;	 Catch:{ all -> 0x00d6 }
        r5 = "Failed to read crashes";
        android.util.Log.e(r4, r5, r9);	 Catch:{ all -> 0x00d6 }
        if (r2 == 0) goto L_0x00c4;
    L_0x00be:
        r2.close();	 Catch:{ IOException -> 0x00c2 }
        goto L_0x00c4;
    L_0x00c2:
        r8 = move-exception;
        goto L_0x00cf;
    L_0x00c4:
        if (r3 == 0) goto L_0x00c9;
    L_0x00c6:
        r3.close();	 Catch:{ IOException -> 0x00c2 }
    L_0x00c9:
        if (r8 == 0) goto L_0x00d4;
    L_0x00cb:
        r8.close();	 Catch:{ IOException -> 0x00c2 }
        goto L_0x00d4;
    L_0x00cf:
        r9 = a;	 Catch:{ all -> 0x00de }
        r2 = "Failed to close buffers";
        goto L_0x00a9;
    L_0x00d4:
        monitor-exit(r1);	 Catch:{ all -> 0x00de }
        return r0;
    L_0x00d6:
        r9 = move-exception;
        r4 = r2;
    L_0x00d8:
        if (r4 == 0) goto L_0x00e2;
    L_0x00da:
        r4.close();	 Catch:{ IOException -> 0x00e0 }
        goto L_0x00e2;
    L_0x00de:
        r8 = move-exception;
        goto L_0x00f5;
    L_0x00e0:
        r8 = move-exception;
        goto L_0x00ed;
    L_0x00e2:
        if (r3 == 0) goto L_0x00e7;
    L_0x00e4:
        r3.close();	 Catch:{ IOException -> 0x00e0 }
    L_0x00e7:
        if (r8 == 0) goto L_0x00f4;
    L_0x00e9:
        r8.close();	 Catch:{ IOException -> 0x00e0 }
        goto L_0x00f4;
    L_0x00ed:
        r0 = a;	 Catch:{ all -> 0x00de }
        r2 = "Failed to close buffers";
        android.util.Log.e(r0, r2, r8);	 Catch:{ all -> 0x00de }
    L_0x00f4:
        throw r9;	 Catch:{ all -> 0x00de }
    L_0x00f5:
        monitor-exit(r1);	 Catch:{ all -> 0x00de }
        throw r8;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.ads.internal.h.e.a(android.content.Context, int):org.json.JSONArray");
    }

    private static JSONObject a(d dVar) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("id", UUID.randomUUID().toString());
        jSONObject.put("type", dVar.a());
        jSONObject.put("time", v.a(dVar.b()));
        jSONObject.put("session_time", v.a(dVar.c()));
        jSONObject.put("session_id", dVar.d());
        jSONObject.put("data", dVar.e() != null ? new JSONObject(dVar.e()) : new JSONObject());
        jSONObject.put("attempt", String.valueOf(0));
        return jSONObject;
    }

    public static void a(d dVar, Context context) {
        if (dVar != null && context != null) {
            synchronized (b) {
                try {
                    JSONObject a = a(dVar);
                    FileOutputStream openFileOutput = context.openFileOutput("debuglogs", 32768);
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(a.toString());
                    stringBuilder.append("\n");
                    openFileOutput.write(stringBuilder.toString().getBytes());
                    openFileOutput.close();
                    e(context);
                } catch (Exception e) {
                    Log.e(a, "Failed to store crash", e);
                }
            }
        }
    }

    public static void a(String str) {
        Integer num = (Integer) d.get(str);
        if (num == null) {
            num = Integer.valueOf(0);
        } else {
            d.remove(str);
        }
        d.put(str, Integer.valueOf(num.intValue() + 1));
    }

    private static void a(String str, int i) {
        if (c.contains(str)) {
            throw new RuntimeException("finished event should not be updated to OngoingEvent.");
        }
        if (d.containsKey(str)) {
            d.remove(str);
        }
        d.put(str, Integer.valueOf(i));
    }

    @WorkerThread
    public static void b(Context context) {
        synchronized (b) {
            File file = new File(context.getFilesDir(), "debuglogs");
            if (file.exists()) {
                file.delete();
            }
            b(context, 0);
            c.clear();
            d.clear();
        }
    }

    private static void b(Context context, int i) {
        Editor edit = context.getApplicationContext().getSharedPreferences("DEBUG_PREF", 0).edit();
        String str = "EventCount";
        if (i < 0) {
            i = 0;
        }
        edit.putInt(str, i).apply();
    }

    public static void b(String str) {
        if (d.containsKey(str)) {
            d.remove(str);
        }
        c.add(str);
    }

    public static int c(Context context) {
        return context.getApplicationContext().getSharedPreferences("DEBUG_PREF", 0).getInt("EventCount", 0) - c.size();
    }

    public static boolean c(String str) {
        return c.contains(str) || d.containsKey(str);
    }

    /* JADX WARNING: Removed duplicated region for block: B:70:0x00f6 A:{SYNTHETIC, Splitter:B:70:0x00f6} */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x00fe A:{Catch:{ IOException -> 0x00fa }} */
    /* JADX WARNING: Removed duplicated region for block: B:77:0x0103 A:{Catch:{ IOException -> 0x00fa }} */
    /* JADX WARNING: Removed duplicated region for block: B:79:0x0108 A:{Catch:{ IOException -> 0x00fa }} */
    /* JADX WARNING: Removed duplicated region for block: B:89:0x0124 A:{SYNTHETIC, Splitter:B:89:0x0124} */
    /* JADX WARNING: Removed duplicated region for block: B:96:0x012e A:{Catch:{ IOException -> 0x012a }} */
    /* JADX WARNING: Removed duplicated region for block: B:98:0x0133 A:{Catch:{ IOException -> 0x012a }} */
    /* JADX WARNING: Removed duplicated region for block: B:100:0x0138 A:{Catch:{ IOException -> 0x012a }} */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x00f6 A:{SYNTHETIC, Splitter:B:70:0x00f6} */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x00fe A:{Catch:{ IOException -> 0x00fa }} */
    /* JADX WARNING: Removed duplicated region for block: B:77:0x0103 A:{Catch:{ IOException -> 0x00fa }} */
    /* JADX WARNING: Removed duplicated region for block: B:79:0x0108 A:{Catch:{ IOException -> 0x00fa }} */
    /* JADX WARNING: Removed duplicated region for block: B:89:0x0124 A:{SYNTHETIC, Splitter:B:89:0x0124} */
    /* JADX WARNING: Removed duplicated region for block: B:96:0x012e A:{Catch:{ IOException -> 0x012a }} */
    /* JADX WARNING: Removed duplicated region for block: B:98:0x0133 A:{Catch:{ IOException -> 0x012a }} */
    /* JADX WARNING: Removed duplicated region for block: B:100:0x0138 A:{Catch:{ IOException -> 0x012a }} */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x00f6 A:{SYNTHETIC, Splitter:B:70:0x00f6} */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x00fe A:{Catch:{ IOException -> 0x00fa }} */
    /* JADX WARNING: Removed duplicated region for block: B:77:0x0103 A:{Catch:{ IOException -> 0x00fa }} */
    /* JADX WARNING: Removed duplicated region for block: B:79:0x0108 A:{Catch:{ IOException -> 0x00fa }} */
    /* JADX WARNING: Removed duplicated region for block: B:89:0x0124 A:{SYNTHETIC, Splitter:B:89:0x0124} */
    /* JADX WARNING: Removed duplicated region for block: B:96:0x012e A:{Catch:{ IOException -> 0x012a }} */
    /* JADX WARNING: Removed duplicated region for block: B:98:0x0133 A:{Catch:{ IOException -> 0x012a }} */
    /* JADX WARNING: Removed duplicated region for block: B:100:0x0138 A:{Catch:{ IOException -> 0x012a }} */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x00f6 A:{SYNTHETIC, Splitter:B:70:0x00f6} */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x00fe A:{Catch:{ IOException -> 0x00fa }} */
    /* JADX WARNING: Removed duplicated region for block: B:77:0x0103 A:{Catch:{ IOException -> 0x00fa }} */
    /* JADX WARNING: Removed duplicated region for block: B:79:0x0108 A:{Catch:{ IOException -> 0x00fa }} */
    /* JADX WARNING: Removed duplicated region for block: B:89:0x0124 A:{SYNTHETIC, Splitter:B:89:0x0124} */
    /* JADX WARNING: Removed duplicated region for block: B:96:0x012e A:{Catch:{ IOException -> 0x012a }} */
    /* JADX WARNING: Removed duplicated region for block: B:98:0x0133 A:{Catch:{ IOException -> 0x012a }} */
    /* JADX WARNING: Removed duplicated region for block: B:100:0x0138 A:{Catch:{ IOException -> 0x012a }} */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x00f6 A:{SYNTHETIC, Splitter:B:70:0x00f6} */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x00fe A:{Catch:{ IOException -> 0x00fa }} */
    /* JADX WARNING: Removed duplicated region for block: B:77:0x0103 A:{Catch:{ IOException -> 0x00fa }} */
    /* JADX WARNING: Removed duplicated region for block: B:79:0x0108 A:{Catch:{ IOException -> 0x00fa }} */
    /* JADX WARNING: Removed duplicated region for block: B:89:0x0124 A:{SYNTHETIC, Splitter:B:89:0x0124} */
    /* JADX WARNING: Removed duplicated region for block: B:96:0x012e A:{Catch:{ IOException -> 0x012a }} */
    /* JADX WARNING: Removed duplicated region for block: B:98:0x0133 A:{Catch:{ IOException -> 0x012a }} */
    /* JADX WARNING: Removed duplicated region for block: B:100:0x0138 A:{Catch:{ IOException -> 0x012a }} */
    @android.support.annotation.WorkerThread
    public static boolean d(android.content.Context r11) {
        /*
        r0 = new org.json.JSONArray;
        r0.<init>();
        r1 = b;
        monitor-enter(r1);
        r2 = 0;
        r3 = 0;
        r4 = new java.io.File;	 Catch:{ IOException | JSONException -> 0x00e9, IOException | JSONException -> 0x00e9, all -> 0x00e4 }
        r5 = r11.getFilesDir();	 Catch:{ IOException | JSONException -> 0x00e9, IOException | JSONException -> 0x00e9, all -> 0x00e4 }
        r6 = "debuglogs";
        r4.<init>(r5, r6);	 Catch:{ IOException | JSONException -> 0x00e9, IOException | JSONException -> 0x00e9, all -> 0x00e4 }
        r4 = r4.exists();	 Catch:{ IOException | JSONException -> 0x00e9, IOException | JSONException -> 0x00e9, all -> 0x00e4 }
        if (r4 == 0) goto L_0x00ac;
    L_0x001b:
        r4 = "debuglogs";
        r4 = r11.openFileInput(r4);	 Catch:{ IOException | JSONException -> 0x00e9, IOException | JSONException -> 0x00e9, all -> 0x00e4 }
        r5 = new java.io.InputStreamReader;	 Catch:{ IOException | JSONException -> 0x00a8, IOException | JSONException -> 0x00a8, all -> 0x00a5 }
        r5.<init>(r4);	 Catch:{ IOException | JSONException -> 0x00a8, IOException | JSONException -> 0x00a8, all -> 0x00a5 }
        r6 = new java.io.BufferedReader;	 Catch:{ IOException | JSONException -> 0x00a2, IOException | JSONException -> 0x00a2, all -> 0x009e }
        r6.<init>(r5);	 Catch:{ IOException | JSONException -> 0x00a2, IOException | JSONException -> 0x00a2, all -> 0x009e }
    L_0x002b:
        r7 = r6.readLine();	 Catch:{ IOException | JSONException -> 0x0099, IOException | JSONException -> 0x0099, all -> 0x0096 }
        if (r7 == 0) goto L_0x005f;
    L_0x0031:
        r8 = new org.json.JSONObject;	 Catch:{ IOException | JSONException -> 0x0099, IOException | JSONException -> 0x0099, all -> 0x0096 }
        r8.<init>(r7);	 Catch:{ IOException | JSONException -> 0x0099, IOException | JSONException -> 0x0099, all -> 0x0096 }
        r7 = "id";
        r7 = r8.getString(r7);	 Catch:{ IOException | JSONException -> 0x0099, IOException | JSONException -> 0x0099, all -> 0x0096 }
        r9 = c;	 Catch:{ IOException | JSONException -> 0x0099, IOException | JSONException -> 0x0099, all -> 0x0096 }
        r9 = r9.contains(r7);	 Catch:{ IOException | JSONException -> 0x0099, IOException | JSONException -> 0x0099, all -> 0x0096 }
        if (r9 != 0) goto L_0x002b;
    L_0x0044:
        r9 = d;	 Catch:{ IOException | JSONException -> 0x0099, IOException | JSONException -> 0x0099, all -> 0x0096 }
        r9 = r9.containsKey(r7);	 Catch:{ IOException | JSONException -> 0x0099, IOException | JSONException -> 0x0099, all -> 0x0096 }
        if (r9 == 0) goto L_0x005b;
    L_0x004c:
        r9 = "attempt";
        r10 = d;	 Catch:{ IOException | JSONException -> 0x0099, IOException | JSONException -> 0x0099, all -> 0x0096 }
        r7 = r10.get(r7);	 Catch:{ IOException | JSONException -> 0x0099, IOException | JSONException -> 0x0099, all -> 0x0096 }
        r7 = java.lang.String.valueOf(r7);	 Catch:{ IOException | JSONException -> 0x0099, IOException | JSONException -> 0x0099, all -> 0x0096 }
        r8.put(r9, r7);	 Catch:{ IOException | JSONException -> 0x0099, IOException | JSONException -> 0x0099, all -> 0x0096 }
    L_0x005b:
        r0.put(r8);	 Catch:{ IOException | JSONException -> 0x0099, IOException | JSONException -> 0x0099, all -> 0x0096 }
        goto L_0x002b;
    L_0x005f:
        r7 = new java.lang.StringBuilder;	 Catch:{ IOException | JSONException -> 0x0099, IOException | JSONException -> 0x0099, all -> 0x0096 }
        r7.<init>();	 Catch:{ IOException | JSONException -> 0x0099, IOException | JSONException -> 0x0099, all -> 0x0096 }
        r8 = r0.length();	 Catch:{ IOException | JSONException -> 0x0099, IOException | JSONException -> 0x0099, all -> 0x0096 }
        r9 = r2;
    L_0x0069:
        if (r9 >= r8) goto L_0x007e;
    L_0x006b:
        r10 = r0.getJSONObject(r9);	 Catch:{ IOException | JSONException -> 0x0099, IOException | JSONException -> 0x0099, all -> 0x0096 }
        r10 = r10.toString();	 Catch:{ IOException | JSONException -> 0x0099, IOException | JSONException -> 0x0099, all -> 0x0096 }
        r7.append(r10);	 Catch:{ IOException | JSONException -> 0x0099, IOException | JSONException -> 0x0099, all -> 0x0096 }
        r10 = 10;
        r7.append(r10);	 Catch:{ IOException | JSONException -> 0x0099, IOException | JSONException -> 0x0099, all -> 0x0096 }
        r9 = r9 + 1;
        goto L_0x0069;
    L_0x007e:
        r0 = "debuglogs";
        r0 = r11.openFileOutput(r0, r2);	 Catch:{ IOException | JSONException -> 0x0099, IOException | JSONException -> 0x0099, all -> 0x0096 }
        r3 = r7.toString();	 Catch:{ IOException | JSONException -> 0x0094, IOException | JSONException -> 0x0094, all -> 0x0091 }
        r3 = r3.getBytes();	 Catch:{ IOException | JSONException -> 0x0094, IOException | JSONException -> 0x0094, all -> 0x0091 }
        r0.write(r3);	 Catch:{ IOException | JSONException -> 0x0094, IOException | JSONException -> 0x0094, all -> 0x0091 }
        r3 = r6;
        goto L_0x00af;
    L_0x0091:
        r11 = move-exception;
        goto L_0x0121;
    L_0x0094:
        r11 = move-exception;
        goto L_0x009b;
    L_0x0096:
        r11 = move-exception;
        goto L_0x0122;
    L_0x0099:
        r11 = move-exception;
        r0 = r3;
    L_0x009b:
        r3 = r6;
        goto L_0x00ed;
    L_0x009e:
        r11 = move-exception;
        r6 = r3;
        goto L_0x0122;
    L_0x00a2:
        r11 = move-exception;
        r0 = r3;
        goto L_0x00ed;
    L_0x00a5:
        r11 = move-exception;
        r5 = r3;
        goto L_0x00e7;
    L_0x00a8:
        r11 = move-exception;
        r0 = r3;
        r5 = r0;
        goto L_0x00ed;
    L_0x00ac:
        r0 = r3;
        r4 = r0;
        r5 = r4;
    L_0x00af:
        r6 = c(r11);	 Catch:{ IOException | JSONException -> 0x00e2, IOException | JSONException -> 0x00e2 }
        b(r11, r6);	 Catch:{ IOException | JSONException -> 0x00e2, IOException | JSONException -> 0x00e2 }
        if (r3 == 0) goto L_0x00be;
    L_0x00b8:
        r3.close();	 Catch:{ IOException -> 0x00bc }
        goto L_0x00be;
    L_0x00bc:
        r11 = move-exception;
        goto L_0x00ce;
    L_0x00be:
        if (r5 == 0) goto L_0x00c3;
    L_0x00c0:
        r5.close();	 Catch:{ IOException -> 0x00bc }
    L_0x00c3:
        if (r4 == 0) goto L_0x00c8;
    L_0x00c5:
        r4.close();	 Catch:{ IOException -> 0x00bc }
    L_0x00c8:
        if (r0 == 0) goto L_0x00d5;
    L_0x00ca:
        r0.close();	 Catch:{ IOException -> 0x00bc }
        goto L_0x00d5;
    L_0x00ce:
        r0 = a;	 Catch:{ all -> 0x0128 }
        r2 = "Failed to close buffers";
        android.util.Log.e(r0, r2, r11);	 Catch:{ all -> 0x0128 }
    L_0x00d5:
        r11 = c;	 Catch:{ all -> 0x0128 }
        r11.clear();	 Catch:{ all -> 0x0128 }
        r11 = d;	 Catch:{ all -> 0x0128 }
        r11.clear();	 Catch:{ all -> 0x0128 }
        monitor-exit(r1);	 Catch:{ all -> 0x0128 }
        r11 = 1;
        return r11;
    L_0x00e2:
        r11 = move-exception;
        goto L_0x00ed;
    L_0x00e4:
        r11 = move-exception;
        r4 = r3;
        r5 = r4;
    L_0x00e7:
        r6 = r5;
        goto L_0x0122;
    L_0x00e9:
        r11 = move-exception;
        r0 = r3;
        r4 = r0;
        r5 = r4;
    L_0x00ed:
        r6 = a;	 Catch:{ all -> 0x011f }
        r7 = "Failed to rewrite File.";
        android.util.Log.e(r6, r7, r11);	 Catch:{ all -> 0x011f }
        if (r3 == 0) goto L_0x00fc;
    L_0x00f6:
        r3.close();	 Catch:{ IOException -> 0x00fa }
        goto L_0x00fc;
    L_0x00fa:
        r11 = move-exception;
        goto L_0x010c;
    L_0x00fc:
        if (r5 == 0) goto L_0x0101;
    L_0x00fe:
        r5.close();	 Catch:{ IOException -> 0x00fa }
    L_0x0101:
        if (r4 == 0) goto L_0x0106;
    L_0x0103:
        r4.close();	 Catch:{ IOException -> 0x00fa }
    L_0x0106:
        if (r0 == 0) goto L_0x0113;
    L_0x0108:
        r0.close();	 Catch:{ IOException -> 0x00fa }
        goto L_0x0113;
    L_0x010c:
        r0 = a;	 Catch:{ all -> 0x0128 }
        r3 = "Failed to close buffers";
        android.util.Log.e(r0, r3, r11);	 Catch:{ all -> 0x0128 }
    L_0x0113:
        r11 = c;	 Catch:{ all -> 0x0128 }
        r11.clear();	 Catch:{ all -> 0x0128 }
        r11 = d;	 Catch:{ all -> 0x0128 }
        r11.clear();	 Catch:{ all -> 0x0128 }
        monitor-exit(r1);	 Catch:{ all -> 0x0128 }
        return r2;
    L_0x011f:
        r11 = move-exception;
        r6 = r3;
    L_0x0121:
        r3 = r0;
    L_0x0122:
        if (r6 == 0) goto L_0x012c;
    L_0x0124:
        r6.close();	 Catch:{ IOException -> 0x012a }
        goto L_0x012c;
    L_0x0128:
        r11 = move-exception;
        goto L_0x014e;
    L_0x012a:
        r0 = move-exception;
        goto L_0x013c;
    L_0x012c:
        if (r5 == 0) goto L_0x0131;
    L_0x012e:
        r5.close();	 Catch:{ IOException -> 0x012a }
    L_0x0131:
        if (r4 == 0) goto L_0x0136;
    L_0x0133:
        r4.close();	 Catch:{ IOException -> 0x012a }
    L_0x0136:
        if (r3 == 0) goto L_0x0143;
    L_0x0138:
        r3.close();	 Catch:{ IOException -> 0x012a }
        goto L_0x0143;
    L_0x013c:
        r2 = a;	 Catch:{ all -> 0x0128 }
        r3 = "Failed to close buffers";
        android.util.Log.e(r2, r3, r0);	 Catch:{ all -> 0x0128 }
    L_0x0143:
        r0 = c;	 Catch:{ all -> 0x0128 }
        r0.clear();	 Catch:{ all -> 0x0128 }
        r0 = d;	 Catch:{ all -> 0x0128 }
        r0.clear();	 Catch:{ all -> 0x0128 }
        throw r11;	 Catch:{ all -> 0x0128 }
    L_0x014e:
        monitor-exit(r1);	 Catch:{ all -> 0x0128 }
        throw r11;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.ads.internal.h.e.d(android.content.Context):boolean");
    }

    private static void e(Context context) {
        b(context, context.getApplicationContext().getSharedPreferences("DEBUG_PREF", 0).getInt("EventCount", 0) + 1);
    }
}
