package com.facebook.ads.internal.h;

import com.facebook.ads.internal.s.a.o;
import com.payu.custombrowser.util.CBConstant;
import java.util.HashMap;
import java.util.Map;

public class a extends d {
    public a(String str, String str2) {
        super(o.b(), o.c(), a(str, str2));
    }

    private static Map<String, String> a(String str, String str2) {
        HashMap hashMap = new HashMap();
        hashMap.put(CBConstant.KEY, str);
        hashMap.put("value", str2);
        return hashMap;
    }

    public String a() {
        return "client_response";
    }
}
