package com.bumptech.glide.load.engine;

import com.bumptech.glide.f.e;
import com.bumptech.glide.load.c;
import com.bumptech.glide.load.f;
import com.bumptech.glide.load.i;
import java.nio.ByteBuffer;
import java.security.MessageDigest;

final class s implements c {
    private static final e<Class<?>, byte[]> b = new e(50);
    private final c c;
    private final c d;
    private final int e;
    private final int f;
    private final Class<?> g;
    private final f h;
    private final i<?> i;

    public s(c cVar, c cVar2, int i, int i2, i<?> iVar, Class<?> cls, f fVar) {
        this.c = cVar;
        this.d = cVar2;
        this.e = i;
        this.f = i2;
        this.i = iVar;
        this.g = cls;
        this.h = fVar;
    }

    public boolean equals(Object obj) {
        boolean z = false;
        if (!(obj instanceof s)) {
            return false;
        }
        s sVar = (s) obj;
        if (this.f == sVar.f && this.e == sVar.e && com.bumptech.glide.f.i.a(this.i, sVar.i) && this.g.equals(sVar.g) && this.c.equals(sVar.c) && this.d.equals(sVar.d) && this.h.equals(sVar.h)) {
            z = true;
        }
        return z;
    }

    public int hashCode() {
        int hashCode = (((((this.c.hashCode() * 31) + this.d.hashCode()) * 31) + this.e) * 31) + this.f;
        if (this.i != null) {
            hashCode = (hashCode * 31) + this.i.hashCode();
        }
        return (31 * ((hashCode * 31) + this.g.hashCode())) + this.h.hashCode();
    }

    public void updateDiskCacheKey(MessageDigest messageDigest) {
        byte[] array = ByteBuffer.allocate(8).putInt(this.e).putInt(this.f).array();
        this.d.updateDiskCacheKey(messageDigest);
        this.c.updateDiskCacheKey(messageDigest);
        messageDigest.update(array);
        if (this.i != null) {
            this.i.updateDiskCacheKey(messageDigest);
        }
        this.h.updateDiskCacheKey(messageDigest);
        messageDigest.update(a());
    }

    private byte[] a() {
        byte[] bArr = (byte[]) b.b(this.g);
        if (bArr != null) {
            return bArr;
        }
        bArr = this.g.getName().getBytes(a);
        b.b(this.g, bArr);
        return bArr;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("ResourceCacheKey{sourceKey=");
        stringBuilder.append(this.c);
        stringBuilder.append(", signature=");
        stringBuilder.append(this.d);
        stringBuilder.append(", width=");
        stringBuilder.append(this.e);
        stringBuilder.append(", height=");
        stringBuilder.append(this.f);
        stringBuilder.append(", decodedResourceClass=");
        stringBuilder.append(this.g);
        stringBuilder.append(", transformation='");
        stringBuilder.append(this.i);
        stringBuilder.append('\'');
        stringBuilder.append(", options=");
        stringBuilder.append(this.h);
        stringBuilder.append('}');
        return stringBuilder.toString();
    }
}
