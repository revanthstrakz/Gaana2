package com.bumptech.glide.load.engine.a;

public final class g implements a<byte[]> {
    public String a() {
        return "ByteArrayPool";
    }

    public int b() {
        return 1;
    }

    public int a(byte[] bArr) {
        return bArr.length;
    }

    /* renamed from: b */
    public byte[] a(int i) {
        return new byte[i];
    }
}
