package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzbrd.zzb;

public final class zzbnk extends zzbrd<zzbnk, zza> implements zzbsn {
    private static volatile zzbsw<zzbnk> zzcas;
    private static final zzbnk zzfhv = new zzbnk();
    private String zzfhu = "";

    public static final class zza extends com.google.android.gms.internal.ads.zzbrd.zza<zzbnk, zza> implements zzbsn {
        private zza() {
            super(zzbnk.zzfhv);
        }

        /* synthetic */ zza(zzbnl zzbnl) {
            this();
        }
    }

    private zzbnk() {
    }

    public final String zzajl() {
        return this.zzfhu;
    }

    public static zzbnk zzak(zzbpu zzbpu) throws zzbrl {
        return (zzbnk) zzbrd.zza(zzfhv, zzbpu);
    }

    /* Access modifiers changed, original: protected|final */
    public final Object zza(int i, Object obj, Object obj2) {
        switch (zzbnl.zzcaq[i - 1]) {
            case 1:
                return new zzbnk();
            case 2:
                return new zza();
            case 3:
                Object[] objArr = new Object[]{"zzfhu"};
                return zzbrd.zza((zzbsl) zzfhv, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0000\u0000\u0001Ȉ", objArr);
            case 4:
                return zzfhv;
            case 5:
                Object obj3 = zzcas;
                if (obj3 == null) {
                    synchronized (zzbnk.class) {
                        obj3 = zzcas;
                        if (obj3 == null) {
                            obj3 = new zzb(zzfhv);
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

    public static zzbnk zzajm() {
        return zzfhv;
    }

    static {
        zzbrd.zza(zzbnk.class, zzfhv);
    }
}
