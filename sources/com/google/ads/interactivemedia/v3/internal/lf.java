package com.google.ads.interactivemedia.v3.internal;

import java.util.Map;
import java.util.Map.Entry;

public final class lf {

    /* renamed from: com.google.ads.interactivemedia.v3.internal.lf$1 */
    class AnonymousClass1 extends lm<Entry<K, V>, K> {
        /* Access modifiers changed, original: 0000 */
        public K a(Entry<K, V> entry) {
            return entry.getKey();
        }
    }

    private enum a implements kn<Entry<?, ?>, Object> {
        KEY {
            public Object a(Entry<?, ?> entry) {
                return entry.getKey();
            }
        },
        VALUE {
            public Object a(Entry<?, ?> entry) {
                return entry.getValue();
            }
        }
    }

    static <V> kn<Entry<?, V>, V> a() {
        return a.VALUE;
    }

    static boolean a(Map<?, ?> map, Object obj) {
        if (map == obj) {
            return true;
        }
        if (!(obj instanceof Map)) {
            return false;
        }
        return map.entrySet().equals(((Map) obj).entrySet());
    }

    static String a(Map<?, ?> map) {
        StringBuilder a = kw.a(map.size());
        a.append('{');
        Object obj = 1;
        for (Entry entry : map.entrySet()) {
            if (obj == null) {
                a.append(", ");
            }
            obj = null;
            a.append(entry.getKey());
            a.append('=');
            a.append(entry.getValue());
        }
        a.append('}');
        return a.toString();
    }
}
