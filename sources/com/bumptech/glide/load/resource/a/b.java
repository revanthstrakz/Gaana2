package com.bumptech.glide.load.resource.a;

import com.bumptech.glide.f.h;
import com.bumptech.glide.load.engine.q;

public class b implements q<byte[]> {
    private final byte[] a;

    public void e() {
    }

    public b(byte[] bArr) {
        this.a = (byte[]) h.a((Object) bArr);
    }

    public Class<byte[]> b() {
        return byte[].class;
    }

    /* renamed from: a */
    public byte[] c() {
        return this.a;
    }

    public int d() {
        return this.a.length;
    }
}
