package com.exoplayer2.upstream.cache;

import android.support.annotation.Nullable;
import com.exoplayer2.upstream.cache.Cache.CacheException;
import com.google.android.exoplayer2.util.Assertions;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.TreeSet;

final class g {
    public final int a;
    public final String b;
    private final TreeSet<p> c = new TreeSet();
    private l d = l.a;
    private boolean e;

    public static g a(int i, DataInputStream dataInputStream) throws IOException {
        g gVar = new g(dataInputStream.readInt(), dataInputStream.readUTF());
        if (i < 2) {
            long readLong = dataInputStream.readLong();
            k kVar = new k();
            j.a(kVar, readLong);
            gVar.a(kVar);
        } else {
            gVar.d = l.a(dataInputStream);
        }
        return gVar;
    }

    public g(int i, String str) {
        this.a = i;
        this.b = str;
    }

    public void a(DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.writeInt(this.a);
        dataOutputStream.writeUTF(this.b);
        this.d.a(dataOutputStream);
    }

    public i a() {
        return this.d;
    }

    public boolean a(k kVar) {
        l lVar = this.d;
        this.d = this.d.a(kVar);
        return this.d.equals(lVar) ^ 1;
    }

    public boolean b() {
        return this.e;
    }

    public void a(boolean z) {
        this.e = z;
    }

    public void a(p pVar) {
        this.c.add(pVar);
    }

    public TreeSet<p> c() {
        return this.c;
    }

    public p a(long j) {
        p a = p.a(this.b, j);
        p pVar = (p) this.c.floor(a);
        if (pVar != null && pVar.b + pVar.c > j) {
            return pVar;
        }
        p b;
        a = (p) this.c.ceiling(a);
        if (a == null) {
            b = p.b(this.b, j);
        } else {
            b = p.a(this.b, j, a.b - j);
        }
        return b;
    }

    public p b(p pVar) throws CacheException {
        p a = pVar.a(this.a);
        if (pVar.e.renameTo(a.e)) {
            Assertions.checkState(this.c.remove(pVar));
            this.c.add(a);
            return a;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Renaming of ");
        stringBuilder.append(pVar.e);
        stringBuilder.append(" to ");
        stringBuilder.append(a.e);
        stringBuilder.append(" failed.");
        throw new CacheException(stringBuilder.toString());
    }

    public boolean d() {
        return this.c.isEmpty();
    }

    public boolean a(d dVar) {
        if (!this.c.remove(dVar)) {
            return false;
        }
        dVar.e.delete();
        return true;
    }

    public int a(int i) {
        int hashCode = (this.a * 31) + this.b.hashCode();
        if (i >= 2) {
            return (31 * hashCode) + this.d.hashCode();
        }
        long a = j.a(this.d);
        return (31 * hashCode) + ((int) (a ^ (a >>> 32)));
    }

    public int hashCode() {
        return (31 * a(Integer.MAX_VALUE)) + this.c.hashCode();
    }

    public boolean equals(@Nullable Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        g gVar = (g) obj;
        if (!(this.a == gVar.a && this.b.equals(gVar.b) && this.c.equals(gVar.c) && this.d.equals(gVar.d))) {
            z = false;
        }
        return z;
    }
}
