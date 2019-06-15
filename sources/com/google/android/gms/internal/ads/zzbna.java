package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzbrd.zzb;

public final class zzbna extends zzbrd<zzbna, zza> implements zzbsn {
    private static volatile zzbsw<zzbna> zzcas;
    private static final zzbna zzfhc = new zzbna();
    private String zzfgk = "";
    private zzbpu zzfgl = zzbpu.zzfli;
    private int zzfhb;

    public static final class zza extends com.google.android.gms.internal.ads.zzbrd.zza<zzbna, zza> implements zzbsn {
        private zza() {
            super(zzbna.zzfhc);
        }

        /* synthetic */ zza(zzbnb zzbnb) {
            this();
        }
    }

    private zzbna() {
    }

    public final String zzaig() {
        return this.zzfgk;
    }

    public final zzbpu zzaih() {
        return this.zzfgl;
    }

    /* Access modifiers changed, original: protected|final */
    public final Object zza(int i, Object obj, Object obj2) {
        switch (zzbnb.zzcaq[i - 1]) {
            case 1:
                return new zzbna();
            case 2:
                return new zza();
            case 3:
                Object[] objArr = new Object[]{"zzfgk", "zzfgl", "zzfhb"};
                return zzbrd.zza((zzbsl) zzfhc, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001Ȉ\u0002\n\u0003\f", objArr);
            case 4:
                return zzfhc;
            case 5:
                Object obj3 = zzcas;
                if (obj3 == null) {
                    synchronized (zzbna.class) {
                        obj3 = zzcas;
                        if (obj3 == null) {
                            obj3 = new zzb(zzfhc);
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

    public static zzbna zzaim() {
        return zzfhc;
    }

    static {
        zzbrd.zza(zzbna.class, zzfhc);
    }
}
