package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzbrd.zzb;

public final class zzblp extends zzbrd<zzblp, zza> implements zzbsn {
    private static volatile zzbsw<zzblp> zzcas;
    private static final zzblp zzfer = new zzblp();
    private int zzfem;

    public static final class zza extends com.google.android.gms.internal.ads.zzbrd.zza<zzblp, zza> implements zzbsn {
        private zza() {
            super(zzblp.zzfer);
        }

        /* synthetic */ zza(zzblq zzblq) {
            this();
        }
    }

    private zzblp() {
    }

    public final int zzagl() {
        return this.zzfem;
    }

    /* Access modifiers changed, original: protected|final */
    public final Object zza(int i, Object obj, Object obj2) {
        switch (zzblq.zzcaq[i - 1]) {
            case 1:
                return new zzblp();
            case 2:
                return new zza();
            case 3:
                Object[] objArr = new Object[]{"zzfem"};
                return zzbrd.zza((zzbsl) zzfer, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0000\u0000\u0001\u000b", objArr);
            case 4:
                return zzfer;
            case 5:
                Object obj3 = zzcas;
                if (obj3 == null) {
                    synchronized (zzblp.class) {
                        obj3 = zzcas;
                        if (obj3 == null) {
                            obj3 = new zzb(zzfer);
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

    public static zzblp zzags() {
        return zzfer;
    }

    static {
        zzbrd.zza(zzblp.class, zzfer);
    }
}
