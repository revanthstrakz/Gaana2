package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzbrd.zzb;

public final class zzblh extends zzbrd<zzblh, zza> implements zzbsn {
    private static volatile zzbsw<zzblh> zzcas;
    private static final zzblh zzfel = new zzblh();
    private zzblj zzfeh;
    private int zzfek;

    public static final class zza extends com.google.android.gms.internal.ads.zzbrd.zza<zzblh, zza> implements zzbsn {
        private zza() {
            super(zzblh.zzfel);
        }

        /* synthetic */ zza(zzbli zzbli) {
            this();
        }
    }

    private zzblh() {
    }

    public final zzblj zzage() {
        return this.zzfeh == null ? zzblj.zzagm() : this.zzfeh;
    }

    public final int getKeySize() {
        return this.zzfek;
    }

    public static zzblh zzn(zzbpu zzbpu) throws zzbrl {
        return (zzblh) zzbrd.zza(zzfel, zzbpu);
    }

    /* Access modifiers changed, original: protected|final */
    public final Object zza(int i, Object obj, Object obj2) {
        switch (zzbli.zzcaq[i - 1]) {
            case 1:
                return new zzblh();
            case 2:
                return new zza();
            case 3:
                Object[] objArr = new Object[]{"zzfeh", "zzfek"};
                return zzbrd.zza((zzbsl) zzfel, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\t\u0002\u000b", objArr);
            case 4:
                return zzfel;
            case 5:
                Object obj3 = zzcas;
                if (obj3 == null) {
                    synchronized (zzblh.class) {
                        obj3 = zzcas;
                        if (obj3 == null) {
                            obj3 = new zzb(zzfel);
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

    public static zzblh zzagj() {
        return zzfel;
    }

    static {
        zzbrd.zza(zzblh.class, zzfel);
    }
}
