package com.exoplayer2.upstream.cache;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.io.File;

public class d implements Comparable<d> {
    public final String a;
    public final long b;
    public final long c;
    public final boolean d;
    @Nullable
    public final File e;
    public final long f;

    public d(String str, long j, long j2, long j3, @Nullable File file) {
        this.a = str;
        this.b = j;
        this.c = j2;
        this.d = file != null;
        this.e = file;
        this.f = j3;
    }

    public boolean a() {
        return this.c == -1;
    }

    public boolean b() {
        return this.d ^ 1;
    }

    /* renamed from: a */
    public int compareTo(@NonNull d dVar) {
        if (!this.a.equals(dVar.a)) {
            return this.a.compareTo(dVar.a);
        }
        long j = this.b - dVar.b;
        int i = j == 0 ? 0 : j < 0 ? -1 : 1;
        return i;
    }
}
