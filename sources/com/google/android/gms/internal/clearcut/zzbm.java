package com.google.android.gms.internal.clearcut;

final class zzbm extends zzbk {
    private final byte[] buffer;
    private int limit;
    private int pos;
    private final boolean zzfu;
    private int zzfv;
    private int zzfw;
    private int zzfx;

    private zzbm(byte[] bArr, int i, int i2, boolean z) {
        super();
        this.zzfx = Integer.MAX_VALUE;
        this.buffer = bArr;
        this.limit = i2 + i;
        this.pos = i;
        this.zzfw = this.pos;
        this.zzfu = z;
    }

    public final int zzaf() {
        return this.pos - this.zzfw;
    }

    public final int zzl(int i) throws zzco {
        if (i < 0) {
            throw new zzco("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
        }
        i += zzaf();
        int i2 = this.zzfx;
        if (i > i2) {
            throw zzco.zzbl();
        }
        this.zzfx = i;
        this.limit += this.zzfv;
        i = this.limit - this.zzfw;
        if (i > this.zzfx) {
            this.zzfv = i - this.zzfx;
            this.limit -= this.zzfv;
            return i2;
        }
        this.zzfv = 0;
        return i2;
    }
}
