package com.inmobi.commons.core.f;

import android.content.ContentValues;
import com.google.android.gms.cast.HlsSegmentFormat;
import com.integralads.avid.library.inmobi.utils.AvidJSONUtil;

public final class b {
    public int a;
    public String b;
    public String c;
    public String d;
    public long e;
    public String f;
    public String g;
    public String h;
    public long i;
    public String j;
    long k = System.currentTimeMillis();

    public b(String str, String str2, String str3, long j, String str4, String str5, String str6, String str7, long j2) {
        this.b = str;
        this.c = str2;
        this.d = str3;
        this.e = j;
        this.f = str4;
        this.g = str5;
        this.h = str6;
        this.i = j2;
        this.j = str7;
    }

    static b a(ContentValues contentValues) {
        String asString = contentValues.getAsString("eventId");
        String asString2 = contentValues.getAsString("adMarkup");
        String asString3 = contentValues.getAsString("eventName");
        long longValue = contentValues.getAsLong("imPlid").longValue();
        String asString4 = contentValues.getAsString("requestId");
        String asString5 = contentValues.getAsString("eventType");
        String asString6 = contentValues.getAsString("dNettypeRaw");
        long longValue2 = contentValues.getAsLong(HlsSegmentFormat.TS).longValue();
        String asString7 = contentValues.getAsString("adtype");
        long longValue3 = contentValues.getAsLong(AvidJSONUtil.KEY_TIMESTAMP).longValue();
        b bVar = new b(asString, asString2, asString3, longValue, asString4, asString5, asString6, asString7, longValue2);
        bVar.k = longValue3;
        bVar.a = contentValues.getAsInteger("id").intValue();
        return bVar;
    }
}
