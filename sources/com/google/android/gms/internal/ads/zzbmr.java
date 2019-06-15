package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzbrd.zzb;

public final class zzbmr extends zzbrd<zzbmr, zza> implements zzbsn {
    private static volatile zzbsw<zzbmr> zzcas;
    private static final zzbmr zzfgg = new zzbmr();
    private int zzfek;
    private zzbmt zzfge;

    public static final class zza extends com.google.android.gms.internal.ads.zzbrd.zza<zzbmr, zza> implements zzbsn {
        private zza() {
            super(zzbmr.zzfgg);
        }

        /* synthetic */ zza(zzbms zzbms) {
            this();
        }
    }

    private zzbmr() {
    }

    public final zzbmt zzahw() {
        return this.zzfge == null ? zzbmt.zzaie() : this.zzfge;
    }

    public final int getKeySize() {
        return this.zzfek;
    }

    public static zzbmr zzag(zzbpu zzbpu) throws zzbrl {
        return (zzbmr) zzbrd.zza(zzfgg, zzbpu);
    }

    /* Access modifiers changed, original: protected|final */
    public final Object zza(int i, Object obj, Object obj2) {
        switch (zzbms.zzcaq[i - 1]) {
            case 1:
                return new zzbmr();
            case 2:
                return new zza();
            case 3:
                Object[] objArr = new Object[]{"zzfge", "zzfek"};
                return zzbrd.zza((zzbsl) zzfgg, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\t\u0002\u000b", objArr);
            case 4:
                return zzfgg;
            case 5:
                Object obj3 = zzcas;
                if (obj3 == null) {
                    synchronized (zzbmr.class) {
                        obj3 = zzcas;
                        if (obj3 == null) {
                            obj3 = new zzb(zzfgg);
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

    public static zzbmr zzaia() {
        return zzfgg;
    }

    static {
        zzbrd.zza(zzbmr.class, zzfgg);
    }
}
