package com.exoplayer2.upstream.cache;

import com.google.android.exoplayer2.util.Assertions;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class k {
    private final Map<String, Object> a = new HashMap();
    private final List<String> b = new ArrayList();

    public k a(String str, String str2) {
        return a(str, (Object) str2);
    }

    public k a(String str, long j) {
        return a(str, Long.valueOf(j));
    }

    public k a(String str) {
        this.b.add(str);
        this.a.remove(str);
        return this;
    }

    public List<String> a() {
        return Collections.unmodifiableList(new ArrayList(this.b));
    }

    public Map<String, Object> b() {
        HashMap hashMap = new HashMap(this.a);
        for (Entry entry : hashMap.entrySet()) {
            Object value = entry.getValue();
            if (value instanceof byte[]) {
                byte[] bArr = (byte[]) value;
                entry.setValue(Arrays.copyOf(bArr, bArr.length));
            }
        }
        return Collections.unmodifiableMap(hashMap);
    }

    private k a(String str, Object obj) {
        this.a.put(Assertions.checkNotNull(str), Assertions.checkNotNull(obj));
        this.b.remove(str);
        return this;
    }
}
