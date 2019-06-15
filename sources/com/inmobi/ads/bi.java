package com.inmobi.ads;

import android.content.ContentValues;
import com.inmobi.ads.InMobiAdRequest.MonetizationContext;
import com.inmobi.ads.c.a;
import java.util.Map;

public final class bi {
    public long a;
    public String b;
    public Map<String, String> c;
    public String d;
    String e;
    public MonetizationContext f = MonetizationContext.MONETIZATION_CONTEXT_ACTIVITY;

    public static bi a(long j, Map<String, String> map, String str, String str2) {
        bi biVar = new bi(j, a.a((Map) map), str);
        biVar.d = str2;
        biVar.c = map;
        return biVar;
    }

    private bi(long j, String str, String str2) {
        this.a = j;
        this.b = str;
        this.e = str2;
        if (this.b == null) {
            this.b = "";
        }
    }

    public bi(ContentValues contentValues) {
        this.a = contentValues.getAsLong("placement_id").longValue();
        this.b = contentValues.getAsString("tp_key");
        this.e = contentValues.getAsString("ad_type");
        this.f = MonetizationContext.a(contentValues.getAsString("m10_context"));
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        bi biVar = (bi) obj;
        return this.a == biVar.a && this.f == biVar.f && this.b.equals(biVar.b) && this.e.equals(biVar.e);
    }

    public final int hashCode() {
        return (30 * ((31 * ((int) (this.a ^ (this.a >>> 32)))) + this.e.hashCode())) + this.f.hashCode();
    }
}
