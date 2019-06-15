package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzbrd.zzb;

public final class zzbmb extends zzbrd<zzbmb, zza> implements zzbsn {
    private static volatile zzbsw<zzbmb> zzcas;
    private static final zzbmb zzffe = new zzbmb();
    private zzbmd zzffd;

    public static final class zza extends com.google.android.gms.internal.ads.zzbrd.zza<zzbmb, zza> implements zzbsn {
        private zza() {
            super(zzbmb.zzffe);
        }

        /* synthetic */ zza(zzbmc zzbmc) {
            this();
        }
    }

    private zzbmb() {
    }

    public final zzbmd zzahc() {
        return this.zzffd == null ? zzbmd.zzahh() : this.zzffd;
    }

    public static zzbmb zzw(zzbpu zzbpu) throws zzbrl {
        return (zzbmb) zzbrd.zza(zzffe, zzbpu);
    }

    /* Access modifiers changed, original: protected|final */
    public final Object zza(int i, Object obj, Object obj2) {
        switch (zzbmc.zzcaq[i - 1]) {
            case 1:
                return new zzbmb();
            case 2:
                return new zza();
            case 3:
                Object[] objArr = new Object[]{"zzffd"};
                return zzbrd.zza((zzbsl) zzffe, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0000\u0000\u0001\t", objArr);
            case 4:
                return zzffe;
            case 5:
                Object obj3 = zzcas;
                if (obj3 == null) {
                    synchronized (zzbmb.class) {
                        obj3 = zzcas;
                        if (obj3 == null) {
                            obj3 = new zzb(zzffe);
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
        zzbrd.zza(zzbmb.class, zzffe);
    }
}
