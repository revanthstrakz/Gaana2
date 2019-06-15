package com.helpshift.support;

import java.util.HashMap;
import java.util.Map;

public class h {
    private Map<String, Object> a;
    private String[] b;

    public h(Map<String, Object> map) {
        this(map, null);
    }

    public h(Map<String, Object> map, String[] strArr) {
        if (map != null) {
            this.a = map;
        }
        if (strArr != null && strArr.length > 0) {
            this.b = strArr;
        }
    }

    public Map<String, Object> a() {
        HashMap hashMap = new HashMap();
        if (this.a != null) {
            hashMap.putAll(this.a);
        }
        if (this.b != null) {
            hashMap.put("hs-tags", this.b);
        }
        return hashMap;
    }
}
