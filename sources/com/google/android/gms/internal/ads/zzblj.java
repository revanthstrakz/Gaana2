package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzbrd.zzb;

public final class zzblj extends zzbrd<zzblj, zza> implements zzbsn {
    private static volatile zzbsw<zzblj> zzcas;
    private static final zzblj zzfen = new zzblj();
    private int zzfem;

    public static final class zza extends com.google.android.gms.internal.ads.zzbrd.zza<zzblj, zza> implements zzbsn {
        private zza() {
            super(zzblj.zzfen);
        }

        /* synthetic */ zza(zzblk zzblk) {
            this();
        }
    }

    private zzblj() {
    }

    public final int zzagl() {
        return this.zzfem;
    }

    /* Access modifiers changed, original: protected|final */
    public final Object zza(int i, Object obj, Object obj2) {
        switch (zzblk.zzcaq[i - 1]) {
            case 1:
                return new zzblj();
            case 2:
                return new zza();
            case 3:
                Object[] objArr = new Object[]{"zzfem"};
                return zzbrd.zza((zzbsl) zzfen, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0000\u0000\u0001\u000b", objArr);
            case 4:
                return zzfen;
            case 5:
                Object obj3 = zzcas;
                if (obj3 == null) {
                    synchronized (zzblj.class) {
                        obj3 = zzcas;
                        if (obj3 == null) {
                            obj3 = new zzb(zzfen);
                            zzcas = obj3;
                        }
                    }
                }
                return obj3;
            case 6:
                return Byte.valueOf((byte) 1);
            case 7:
                return null;
            default:
                throw new UnsupportedOperationException();
        }
    }

    public static zzblj zzagm() {
        return zzfen;
    }

    static {
        zzbrd.zza(zzblj.class, zzfen);
    }
}
