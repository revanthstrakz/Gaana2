package com.helpshift.support;

import java.util.Comparator;
import java.util.HashMap;

class k implements Comparator<String> {
    HashMap<String, Double> a;

    public k(HashMap<String, Double> hashMap) {
        this.a = hashMap;
    }

    /* renamed from: a */
    public int compare(String str, String str2) {
        return ((Double) this.a.get(str)).doubleValue() >= ((Double) this.a.get(str2)).doubleValue() ? -1 : 1;
    }
}
