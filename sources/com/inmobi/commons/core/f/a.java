package com.inmobi.commons.core.f;

import android.content.ContentValues;
import com.google.android.gms.cast.HlsSegmentFormat;
import com.inmobi.commons.core.b.b;
import com.inmobi.commons.core.d.c;
import com.integralads.avid.library.inmobi.utils.AvidJSONUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class a extends b {
    private static final String a = "a";

    public a() {
        com.inmobi.commons.core.d.b a = com.inmobi.commons.core.d.b.a();
        a.a("trc", "(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, eventId TEXT NOT NULL, adMarkup TEXT NOT NULL, eventName TEXT NOT NULL, imPlid INTEGER NOT NULL, requestId TEXT NOT NULL, eventType TEXT NOT NULL, dNettypeRaw TEXT NOT NULL, ts TEXT NOT NULL, adtype TEXT NOT NULL, timestamp TEXT NOT NULL)");
        a.b();
    }

    public static void a(b bVar) {
        com.inmobi.commons.core.d.b a = com.inmobi.commons.core.d.b.a();
        ContentValues contentValues = new ContentValues();
        contentValues.put("eventId", bVar.b);
        contentValues.put("adMarkup", bVar.c);
        contentValues.put("eventName", bVar.d);
        contentValues.put("imPlid", Long.valueOf(bVar.e));
        contentValues.put("requestId", bVar.f);
        contentValues.put("eventType", bVar.g);
        contentValues.put("dNettypeRaw", bVar.h);
        contentValues.put(HlsSegmentFormat.TS, String.valueOf(bVar.i));
        contentValues.put("adtype", bVar.j);
        contentValues.put(AvidJSONUtil.KEY_TIMESTAMP, String.valueOf(bVar.k));
        a.a("trc", contentValues);
        a.b();
    }

    public static List<b> a(int i, String str) {
        com.inmobi.commons.core.d.b a = com.inmobi.commons.core.d.b.a();
        com.inmobi.commons.core.d.b bVar = a;
        List<ContentValues> a2 = bVar.a("trc", null, "adtype=?", new String[]{str}, null, null, "timestamp ASC", String.valueOf(i));
        a.b();
        ArrayList arrayList = new ArrayList();
        for (ContentValues a3 : a2) {
            arrayList.add(b.a(a3));
        }
        return arrayList;
    }

    public final boolean a(long j, String str) {
        List a = a(1, str);
        if (a.size() <= 0 || TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis() - ((b) a.get(0)).i) <= j) {
            return false;
        }
        return true;
    }

    public static boolean c(String str) {
        com.inmobi.commons.core.d.b a = com.inmobi.commons.core.d.b.a();
        int b = a.b("trc", "adtype=?", new String[]{str});
        a.b();
        if (b > 0) {
            return true;
        }
        return false;
    }

    public static void d(String str) {
        com.inmobi.commons.core.d.b a = com.inmobi.commons.core.d.b.a();
        com.inmobi.commons.core.d.b bVar = a;
        List a2 = bVar.a("trc", null, "adtype=?", new String[]{str}, null, null, "timestamp ASC", "1");
        if (!a2.isEmpty()) {
            str = ((ContentValues) a2.get(0)).getAsString("id");
            StringBuilder stringBuilder = new StringBuilder("id IN (");
            stringBuilder.append(str);
            stringBuilder.append(")");
            a.a("trc", stringBuilder.toString(), null);
        }
        a.b();
    }

    public final void a(List<Integer> list) {
        if (!list.isEmpty()) {
            com.inmobi.commons.core.d.b a = com.inmobi.commons.core.d.b.a();
            StringBuffer stringBuffer = new StringBuffer("");
            for (int i = 0; i < list.size() - 1; i++) {
                stringBuffer.append(list.get(i));
                stringBuffer.append(",");
            }
            stringBuffer.append(String.valueOf(list.get(list.size() - 1)));
            StringBuilder stringBuilder = new StringBuilder("id IN (");
            stringBuilder.append(stringBuffer);
            stringBuilder.append(")");
            a.a("trc", stringBuilder.toString(), null);
            a.b();
        }
    }

    public final int a(String str) {
        com.inmobi.commons.core.d.b a = com.inmobi.commons.core.d.b.a();
        int b = a.b("trc", "adtype=?", new String[]{str});
        a.b();
        return b;
    }

    public final int b(long j, String str) {
        com.inmobi.commons.core.d.b a = com.inmobi.commons.core.d.b.a();
        long currentTimeMillis = System.currentTimeMillis() - (j * 1000);
        int a2 = a.a("trc", "ts<? and adtype=?", new String[]{String.valueOf(currentTimeMillis), str});
        StringBuilder stringBuilder = new StringBuilder("Deleted ");
        stringBuilder.append(a2);
        stringBuilder.append(" expired events from trc DB");
        a.b();
        return a2;
    }

    public final String b(int i) {
        com.inmobi.commons.core.d.b a = com.inmobi.commons.core.d.b.a();
        List a2 = a.a("trc", null, "id=?", new String[]{String.valueOf(i)}, null, null, "timestamp ASC", "1");
        a.b();
        return !a2.isEmpty() ? b.a((ContentValues) a2.get(0)).j : null;
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0036  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x003c  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0039  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0036  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x003c  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0039  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0036  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x003c  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0039  */
    private static java.lang.String e(java.lang.String r2) {
        /*
        r0 = r2.hashCode();
        r1 = -1396342996; // 0xffffffffacc57f2c float:-5.6131957E-12 double:NaN;
        if (r0 == r1) goto L_0x0028;
    L_0x0009:
        r1 = -1052618729; // 0xffffffffc1425017 float:-12.144553 double:NaN;
        if (r0 == r1) goto L_0x001e;
    L_0x000e:
        r1 = 104431; // 0x197ef float:1.46339E-40 double:5.1596E-319;
        if (r0 == r1) goto L_0x0014;
    L_0x0013:
        goto L_0x0032;
    L_0x0014:
        r0 = "int";
        r2 = r2.equals(r0);
        if (r2 == 0) goto L_0x0032;
    L_0x001c:
        r2 = 1;
        goto L_0x0033;
    L_0x001e:
        r0 = "native";
        r2 = r2.equals(r0);
        if (r2 == 0) goto L_0x0032;
    L_0x0026:
        r2 = 2;
        goto L_0x0033;
    L_0x0028:
        r0 = "banner";
        r2 = r2.equals(r0);
        if (r2 == 0) goto L_0x0032;
    L_0x0030:
        r2 = 0;
        goto L_0x0033;
    L_0x0032:
        r2 = -1;
    L_0x0033:
        switch(r2) {
            case 0: goto L_0x003c;
            case 1: goto L_0x0039;
            default: goto L_0x0036;
        };
    L_0x0036:
        r2 = "trc_last_native_batch_process";
        goto L_0x003e;
    L_0x0039:
        r2 = "trc_last_int_batch_process";
        goto L_0x003e;
    L_0x003c:
        r2 = "trc_last_banner_batch_process";
    L_0x003e:
        return r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.inmobi.commons.core.f.a.e(java.lang.String):java.lang.String");
    }

    public final void c(long j, String str) {
        str = e(str);
        if (com.inmobi.commons.a.a.a()) {
            c.b("batch_processing_info").a(str, j);
        }
    }

    public final long b(String str) {
        str = e(str);
        if (com.inmobi.commons.a.a.a()) {
            return c.b("batch_processing_info").b(str, -1);
        }
        return -1;
    }
}
