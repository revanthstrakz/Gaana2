package com.inmobi.ads;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import org.json.JSONObject;

public final class am extends ak implements Iterable<ak> {
    int A;
    ak[] B;
    int C;
    long z;

    private class a implements Iterator<ak> {
        private int b = 0;

        public final boolean hasNext() {
            return this.b < am.this.C;
        }

        public final void remove() {
            throw new UnsupportedOperationException();
        }

        public final /* synthetic */ Object next() {
            ak[] b = am.this.B;
            int i = this.b;
            this.b = i + 1;
            return b[i];
        }
    }

    public final Iterator<ak> iterator() {
        return new a();
    }

    public am(String str, String str2, al alVar, int i, JSONObject jSONObject, int i2) {
        this(str, str2, alVar, new LinkedList(), i, jSONObject, i2);
    }

    public am(String str, String str2, al alVar, List<NativeTracker> list, int i, JSONObject jSONObject, int i2) {
        super(str, str2, "CONTAINER", alVar, list);
        this.z = 0;
        this.f = jSONObject;
        this.B = new ak[1];
        this.i = i;
        this.C = 0;
        this.A = i2;
    }

    public final ak a(int i) {
        return (i < 0 || i >= this.C) ? null : this.B[i];
    }
}
