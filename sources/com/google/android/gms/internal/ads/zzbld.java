package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzbrd.zzb;

public final class zzbld extends zzbrd<zzbld, zza> implements zzbsn {
    private static volatile zzbsw<zzbld> zzcas;
    private static final zzbld zzfeg = new zzbld();
    private zzblh zzfee;
    private zzbmr zzfef;

    public static final class zza extends com.google.android.gms.internal.ads.zzbrd.zza<zzbld, zza> implements zzbsn {
        private zza() {
            super(zzbld.zzfeg);
        }

        /* synthetic */ zza(zzble zzble) {
            this();
        }
    }

    private zzbld() {
    }

    public final zzblh zzagb() {
        return this.zzfee == null ? zzblh.zzagj() : this.zzfee;
    }

    public final zzbmr zzagc() {
        return this.zzfef == null ? zzbmr.zzaia() : this.zzfef;
    }

    public static zzbld zzj(zzbpu zzbpu) throws zzbrl {
        return (zzbld) zzbrd.zza(zzfeg, zzbpu);
    }

    /* Access modifiers changed, original: protected|final */
    public final Object zza(int i, Object obj, Object obj2) {
        switch (zzble.zzcaq[i - 1]) {
            case 1:
                return new zzbld();
            case 2:
                return new zza();
            case 3:
                Object[] objArr = new Object[]{"zzfee", "zzfef"};
                return zzbrd.zza((zzbsl) zzfeg, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\t\u0002\t", objArr);
            case 4:
                return zzfeg;
            case 5:
                Object obj3 = zzcas;
                if (obj3 == null) {
                    synchronized (zzbld.class) {
                        obj3 = zzcas;
                        if (obj3 == null) {
                            obj3 = new zzb(zzfeg);
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

    static {
        zzbrd.zza(zzbld.class, zzfeg);
    }
}
