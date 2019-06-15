package com.fasterxml.jackson.core.util;

import java.util.LinkedHashMap;
import java.util.Map.Entry;

public final class InternCache extends LinkedHashMap<String, String> {
    public static final InternCache a = new InternCache();

    private InternCache() {
        super(100, 0.8f, true);
    }

    /* Access modifiers changed, original: protected */
    public boolean removeEldestEntry(Entry<String, String> entry) {
        return size() > 100;
    }

    public synchronized String a(String str) {
        String str2;
        str2 = (String) get(str);
        if (str2 == null) {
            str2 = str.intern();
            put(str2, str2);
        }
        return str2;
    }
}
