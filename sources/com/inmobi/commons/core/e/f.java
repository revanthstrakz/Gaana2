package com.inmobi.commons.core.e;

import android.content.ContentValues;
import com.google.android.gms.cast.HlsSegmentFormat;
import java.util.UUID;

public class f {
    private static final String g = "f";
    int a;
    String b;
    String c;
    String d;
    long e;
    String f;

    public f(String str, String str2) {
        this.b = UUID.randomUUID().toString();
        this.d = str;
        this.c = str2;
        this.f = null;
        this.e = System.currentTimeMillis();
    }

    private f(String str, String str2, String str3, String str4) {
        this.b = str;
        this.d = str2;
        this.c = str3;
        this.f = str4;
        this.e = System.currentTimeMillis();
    }

    public final String a() {
        return this.f == null ? "" : this.f;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.c);
        stringBuilder.append("@");
        stringBuilder.append(this.d);
        stringBuilder.append(" ");
        return stringBuilder.toString();
    }

    public static f a(ContentValues contentValues) {
        String asString = contentValues.getAsString("eventId");
        String asString2 = contentValues.getAsString("eventType");
        String asString3 = contentValues.getAsString("componentType");
        String asString4 = contentValues.getAsString("payload");
        long longValue = Long.valueOf(contentValues.getAsString(HlsSegmentFormat.TS)).longValue();
        f fVar = new f(asString, asString3, asString2, asString4);
        fVar.e = longValue;
        fVar.a = contentValues.getAsInteger("id").intValue();
        return fVar;
    }
}
