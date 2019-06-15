package com.bumptech.glide.load.b;

import android.support.annotation.VisibleForTesting;
import android.text.TextUtils;
import com.til.colombia.android.internal.e;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public final class j implements h {
    private final Map<String, List<i>> c;
    private volatile Map<String, String> d;

    public static final class a {
        private static final String a = b();
        private static final Map<String, List<i>> b;
        private boolean c = true;
        private Map<String, List<i>> d = b;
        private boolean e = true;

        static {
            HashMap hashMap = new HashMap(2);
            if (!TextUtils.isEmpty(a)) {
                hashMap.put(e.c, Collections.singletonList(new b(a)));
            }
            b = Collections.unmodifiableMap(hashMap);
        }

        public j a() {
            this.c = true;
            return new j(this.d);
        }

        @VisibleForTesting
        static String b() {
            String property = System.getProperty("http.agent");
            if (TextUtils.isEmpty(property)) {
                return property;
            }
            int length = property.length();
            StringBuilder stringBuilder = new StringBuilder(property.length());
            for (int i = 0; i < length; i++) {
                char charAt = property.charAt(i);
                if ((charAt > 31 || charAt == 9) && charAt < 127) {
                    stringBuilder.append(charAt);
                } else {
                    stringBuilder.append('?');
                }
            }
            return stringBuilder.toString();
        }
    }

    static final class b implements i {
        private final String a;

        b(String str) {
            this.a = str;
        }

        public String a() {
            return this.a;
        }

        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("StringHeaderFactory{value='");
            stringBuilder.append(this.a);
            stringBuilder.append('\'');
            stringBuilder.append('}');
            return stringBuilder.toString();
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof b)) {
                return false;
            }
            return this.a.equals(((b) obj).a);
        }

        public int hashCode() {
            return this.a.hashCode();
        }
    }

    j(Map<String, List<i>> map) {
        this.c = Collections.unmodifiableMap(map);
    }

    public Map<String, String> a() {
        if (this.d == null) {
            synchronized (this) {
                if (this.d == null) {
                    this.d = Collections.unmodifiableMap(b());
                }
            }
        }
        return this.d;
    }

    private Map<String, String> b() {
        HashMap hashMap = new HashMap();
        for (Entry entry : this.c.entrySet()) {
            StringBuilder stringBuilder = new StringBuilder();
            List list = (List) entry.getValue();
            int size = list.size();
            for (int i = 0; i < size; i++) {
                String a = ((i) list.get(i)).a();
                if (!TextUtils.isEmpty(a)) {
                    stringBuilder.append(a);
                    if (i != list.size() - 1) {
                        stringBuilder.append(',');
                    }
                }
            }
            if (!TextUtils.isEmpty(stringBuilder.toString())) {
                hashMap.put(entry.getKey(), stringBuilder.toString());
            }
        }
        return hashMap;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("LazyHeaders{headers=");
        stringBuilder.append(this.c);
        stringBuilder.append('}');
        return stringBuilder.toString();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof j)) {
            return false;
        }
        return this.c.equals(((j) obj).c);
    }

    public int hashCode() {
        return this.c.hashCode();
    }
}
