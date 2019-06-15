package com.inmobi.commons.core.e;

import android.content.ContentValues;
import com.google.android.gms.cast.HlsSegmentFormat;
import com.inmobi.commons.a.a;
import com.inmobi.commons.core.b.b;
import com.inmobi.commons.core.d.c;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class e extends b {
    private static final String a = "e";

    public e() {
        com.inmobi.commons.core.d.b a = com.inmobi.commons.core.d.b.a();
        a.a("telemetry", "(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, componentType TEXT NOT NULL, eventId TEXT NOT NULL, eventType TEXT NOT NULL, payload TEXT NOT NULL, ts TEXT NOT NULL)");
        a.b();
    }

    public static void a(f fVar) {
        com.inmobi.commons.core.d.b a = com.inmobi.commons.core.d.b.a();
        ContentValues contentValues = new ContentValues();
        contentValues.put("eventId", fVar.b);
        contentValues.put("componentType", fVar.d);
        contentValues.put("eventType", fVar.c);
        contentValues.put("payload", fVar.a());
        contentValues.put(HlsSegmentFormat.TS, String.valueOf(fVar.e));
        a.a("telemetry", contentValues);
        a.b();
    }

    public static List<f> a(int i) {
        com.inmobi.commons.core.d.b a = com.inmobi.commons.core.d.b.a();
        List<ContentValues> a2 = a.a("telemetry", null, null, null, null, null, "ts ASC", String.valueOf(i));
        ArrayList arrayList = new ArrayList();
        a.b();
        for (ContentValues a3 : a2) {
            arrayList.add(f.a(a3));
        }
        return arrayList;
    }

    public final boolean a(long j, String str) {
        List a = a(1);
        if (a.size() <= 0 || TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis() - ((f) a.get(0)).e) <= j) {
            return false;
        }
        return true;
    }

    public final int a(String str) {
        com.inmobi.commons.core.d.b a = com.inmobi.commons.core.d.b.a();
        int a2 = a.a("telemetry");
        a.b();
        return a2;
    }

    public final int b(long j, String str) {
        com.inmobi.commons.core.d.b a = com.inmobi.commons.core.d.b.a();
        long currentTimeMillis = System.currentTimeMillis() - (j * 1000);
        int a2 = a.a("telemetry", "ts<?", new String[]{String.valueOf(currentTimeMillis)});
        StringBuilder stringBuilder = new StringBuilder("Deleted ");
        stringBuilder.append(a2);
        stringBuilder.append(" expired events from telemetry DB");
        a.b();
        return a2;
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
            a.a("telemetry", stringBuilder.toString(), null);
            a.b();
        }
    }

    public static void a() {
        com.inmobi.commons.core.d.b a = com.inmobi.commons.core.d.b.a();
        List a2 = a.a("telemetry", null, null, null, null, null, "ts ASC", "1");
        if (!a2.isEmpty()) {
            String asString = ((ContentValues) a2.get(0)).getAsString("id");
            StringBuilder stringBuilder = new StringBuilder("id IN (");
            stringBuilder.append(asString);
            stringBuilder.append(")");
            a.a("telemetry", stringBuilder.toString(), null);
        }
        a.b();
    }

    public final void c(long j, String str) {
        if (a.a()) {
            c.b("batch_processing_info").a("telemetry_last_batch_process", j);
        }
    }

    public final long b(String str) {
        if (a.a()) {
            return c.b("batch_processing_info").b("telemetry_last_batch_process", -1);
        }
        return -1;
    }
}
