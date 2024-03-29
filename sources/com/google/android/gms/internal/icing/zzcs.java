package com.google.android.gms.internal.icing;

final class zzcs extends zzcq {
    private final byte[] buffer;
    private int limit;
    private int pos;
    private final boolean zzgj;
    private int zzgk;
    private int zzgl;
    private int zzgm;

    private zzcs(byte[] bArr, int i, int i2, boolean z) {
        super();
        this.zzgm = Integer.MAX_VALUE;
        this.buffer = bArr;
        this.limit = i2 + i;
        this.pos = i;
        this.zzgl = this.pos;
        this.zzgj = z;
    }

    public final int zzn(int i) throws zzdr {
        if (i < 0) {
            throw new zzdr("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
        }
        i += zzau();
        int i2 = this.zzgm;
        if (i > i2) {
            throw new zzdr("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
        }
        this.zzgm = i;
        this.limit += this.zzgk;
        i = this.limit - this.zzgl;
        if (i > this.zzgm) {
            this.zzgk = i - this.zzgm;
            this.limit -= this.zzgk;
        } else {
            this.zzgk = 0;
        }
        return i2;
    }

    public final int zzau() {
        return this.pos - this.zzgl;
    }
}
