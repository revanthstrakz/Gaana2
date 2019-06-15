package com.iabutils;

import com.google.ads.mediation.inmobi.InMobiNetworkValues;
import org.json.JSONException;
import org.json.JSONObject;

public class e {
    String a;
    String b;
    String c;
    String d;
    long e;
    String f;
    String g;
    String h;
    long i;
    String j;
    String k;
    String l;
    String m;

    public e(String str, String str2) throws JSONException {
        this.a = str;
        this.m = str2;
        JSONObject jSONObject = new JSONObject(this.m);
        this.b = jSONObject.optString("productId");
        this.c = jSONObject.optString("type");
        this.d = jSONObject.optString(InMobiNetworkValues.PRICE);
        this.e = jSONObject.optLong("price_amount_micros");
        this.f = jSONObject.optString("title");
        this.g = jSONObject.optString("description");
        this.h = jSONObject.optString("introductoryPrice");
        this.i = jSONObject.optLong("introductoryPriceAmountMicros");
        this.j = jSONObject.optString("introductoryPriceAmountMicros");
        this.k = jSONObject.optString("introductoryPricePeriod");
        this.l = jSONObject.optString("introductoryPriceCycles");
    }

    public String a() {
        return this.b;
    }

    public String b() {
        return this.d;
    }

    public String c() {
        return this.h;
    }

    public long d() {
        return this.e;
    }

    public long e() {
        return this.i;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SkuDetails:");
        stringBuilder.append(this.m);
        return stringBuilder.toString();
    }
}
