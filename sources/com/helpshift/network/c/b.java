package com.helpshift.network.c;

import com.helpshift.util.o;
import com.helpshift.util.r;
import java.util.HashMap;
import java.util.Map;

public class b {
    public static Map<String, String> a() {
        HashMap hashMap = new HashMap();
        hashMap.put("Accept-Language", String.format("%s;q=1.0", new Object[]{r.a()}));
        hashMap.put("Accept-Encoding", "gzip");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Helpshift-Android/");
        stringBuilder.append(o.c().d().b());
        hashMap.put("X-HS-V", stringBuilder.toString());
        return hashMap;
    }
}
