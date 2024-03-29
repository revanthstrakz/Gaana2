package com.google.android.gms.internal.ads;

public final class zzx<T> {
    public final T result;
    public final zzc zzbg;
    public final zzae zzbh;
    public boolean zzbi;

    public static <T> zzx<T> zza(T t, zzc zzc) {
        return new zzx(t, zzc);
    }

    public static <T> zzx<T> zzc(zzae zzae) {
        return new zzx(zzae);
    }

    private zzx(T t, zzc zzc) {
        this.zzbi = false;
        this.result = t;
        this.zzbg = zzc;
        this.zzbh = null;
    }

    private zzx(zzae zzae) {
        this.zzbi = false;
        this.result = null;
        this.zzbg = null;
        this.zzbh = zzae;
    }
}
