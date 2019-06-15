package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzbrd.zzb;

public final class zzbmj extends zzbrd<zzbmj, zza> implements zzbsn {
    private static volatile zzbsw<zzbmj> zzcas;
    private static final zzbmj zzffr = new zzbmj();
    private int zzffo;
    private int zzffp;
    private zzbpu zzffq = zzbpu.zzfli;

    public static final class zza extends com.google.android.gms.internal.ads.zzbrd.zza<zzbmj, zza> implements zzbsn {
        private zza() {
            super(zzbmj.zzffr);
        }

        /* synthetic */ zza(zzbmk zzbmk) {
            this();
        }
    }

    private zzbmj() {
    }

    public final zzbml zzahr() {
        zzbml zzds = zzbml.zzds(this.zzffo);
        return zzds == null ? zzbml.UNRECOGNIZED : zzds;
    }

    public final zzbmn zzahs() {
        zzbmn zzdt = zzbmn.zzdt(this.zzffp);
        return zzdt == null ? zzbmn.UNRECOGNIZED : zzdt;
    }

    public final zzbpu zzaht() {
        return this.zzffq;
    }

    /* Access modifiers changed, original: protected|final */
    public final Object zza(int i, Object obj, Object obj2) {
        switch (zzbmk.zzcaq[i - 1]) {
            case 1:
                return new zzbmj();
            case 2:
                return new zza();
            case 3:
                Object[] objArr = new Object[]{"zzffo", "zzffp", "zzffq"};
                return zzbrd.zza((zzbsl) zzffr, "\u0000\u0003\u0000\u0000\u0001\u000b\u0003\u0000\u0000\u0000\u0001\f\u0002\f\u000b\n", objArr);
            case 4:
                return zzffr;
            case 5:
                Object obj3 = zzcas;
                if (obj3 == null) {
                    synchronized (zzbmj.class) {
                        obj3 = zzcas;
                        if (obj3 == null) {
                            obj3 = new zzb(zzffr);
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

    public static zzbmj zzahu() {
        return zzffr;
    }

    static {
        zzbrd.zza(zzbmj.class, zzffr);
    }
}
