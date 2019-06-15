package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzbrd.zzb;

public final class zzblz extends zzbrd<zzblz, zza> implements zzbsn {
    private static volatile zzbsw<zzblz> zzcas;
    private static final zzblz zzffc = new zzblz();
    private zzbna zzffb;

    public static final class zza extends com.google.android.gms.internal.ads.zzbrd.zza<zzblz, zza> implements zzbsn {
        private zza() {
            super(zzblz.zzffc);
        }

        /* synthetic */ zza(zzbma zzbma) {
            this();
        }
    }

    private zzblz() {
    }

    public final zzbna zzagz() {
        return this.zzffb == null ? zzbna.zzaim() : this.zzffb;
    }

    /* Access modifiers changed, original: protected|final */
    public final Object zza(int i, Object obj, Object obj2) {
        switch (zzbma.zzcaq[i - 1]) {
            case 1:
                return new zzblz();
            case 2:
                return new zza();
            case 3:
                Object[] objArr = new Object[]{"zzffb"};
                return zzbrd.zza((zzbsl) zzffc, "\u0000\u0001\u0000\u0000\u0002\u0002\u0001\u0000\u0000\u0000\u0002\t", objArr);
            case 4:
                return zzffc;
            case 5:
                Object obj3 = zzcas;
                if (obj3 == null) {
                    synchronized (zzblz.class) {
                        obj3 = zzcas;
                        if (obj3 == null) {
                            obj3 = new zzb(zzffc);
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

    public static zzblz zzaha() {
        return zzffc;
    }

    static {
        zzbrd.zza(zzblz.class, zzffc);
    }
}
