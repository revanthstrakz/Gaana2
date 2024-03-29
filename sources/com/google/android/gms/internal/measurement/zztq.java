package com.google.android.gms.internal.measurement;

import java.io.IOException;

public abstract class zztq {
    int zzbty;
    int zzbtz;
    private int zzbua;
    zztt zzbub;
    private boolean zzbuc;

    public static zztq zzd(byte[] bArr, int i, int i2) {
        return zza(bArr, i, i2, false);
    }

    public abstract double readDouble() throws IOException;

    public abstract float readFloat() throws IOException;

    public abstract String readString() throws IOException;

    public abstract <T extends zzvv> T zza(zzwf<T> zzwf, zzub zzub) throws IOException;

    public abstract void zzap(int i) throws zzuv;

    public abstract boolean zzaq(int i) throws IOException;

    public abstract int zzas(int i) throws zzuv;

    public abstract void zzat(int i);

    public abstract void zzau(int i) throws IOException;

    public abstract int zzuj() throws IOException;

    public abstract long zzuk() throws IOException;

    public abstract long zzul() throws IOException;

    public abstract int zzum() throws IOException;

    public abstract long zzun() throws IOException;

    public abstract int zzuo() throws IOException;

    public abstract boolean zzup() throws IOException;

    public abstract String zzuq() throws IOException;

    public abstract zzte zzur() throws IOException;

    public abstract int zzus() throws IOException;

    public abstract int zzut() throws IOException;

    public abstract int zzuu() throws IOException;

    public abstract long zzuv() throws IOException;

    public abstract int zzuw() throws IOException;

    public abstract long zzux() throws IOException;

    public abstract long zzuy() throws IOException;

    public abstract boolean zzuz() throws IOException;

    public abstract int zzva();

    static zztq zza(byte[] bArr, int i, int i2, boolean z) {
        zzts zzts = new zzts(bArr, i, i2, false, null);
        try {
            zzts.zzas(i2);
            return zzts;
        } catch (zzuv e) {
            throw new IllegalArgumentException(e);
        }
    }

    private zztq() {
        this.zzbtz = 100;
        this.zzbua = Integer.MAX_VALUE;
        this.zzbuc = false;
    }

    public final int zzar(int i) {
        if (i < 0) {
            StringBuilder stringBuilder = new StringBuilder(47);
            stringBuilder.append("Recursion limit cannot be negative: ");
            stringBuilder.append(i);
            throw new IllegalArgumentException(stringBuilder.toString());
        }
        int i2 = this.zzbtz;
        this.zzbtz = i;
        return i2;
    }

    /* synthetic */ zztq(zztr zztr) {
        this();
    }
}
