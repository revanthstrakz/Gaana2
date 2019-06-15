package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzbrd.zzb;

public final class zzbln extends zzbrd<zzbln, zza> implements zzbsn {
    private static volatile zzbsw<zzbln> zzcas;
    private static final zzbln zzfeq = new zzbln();
    private int zzfek;
    private zzblp zzfeo;

    public static final class zza extends com.google.android.gms.internal.ads.zzbrd.zza<zzbln, zza> implements zzbsn {
        private zza() {
            super(zzbln.zzfeq);
        }

        /* synthetic */ zza(zzblo zzblo) {
            this();
        }
    }

    private zzbln() {
    }

    public final zzblp zzago() {
        return this.zzfeo == null ? zzblp.zzags() : this.zzfeo;
    }

    public final int getKeySize() {
        return this.zzfek;
    }

    public static zzbln zzq(zzbpu zzbpu) throws zzbrl {
        return (zzbln) zzbrd.zza(zzfeq, zzbpu);
    }

    /* Access modifiers changed, original: protected|final */
    public final Object zza(int i, Object obj, Object obj2) {
        switch (zzblo.zzcaq[i - 1]) {
            case 1:
                return new zzbln();
            case 2:
                return new zza();
            case 3:
                Object[] objArr = new Object[]{"zzfeo", "zzfek"};
                return zzbrd.zza((zzbsl) zzfeq, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\t\u0002\u000b", objArr);
            case 4:
                return zzfeq;
            case 5:
                Object obj3 = zzcas;
                if (obj3 == null) {
                    synchronized (zzbln.class) {
                        obj3 = zzcas;
                        if (obj3 == null) {
                            obj3 = new zzb(zzfeq);
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
        zzbrd.zza(zzbln.class, zzfeq);
    }
}
