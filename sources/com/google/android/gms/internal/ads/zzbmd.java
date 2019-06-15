package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzbrd.zzb;

public final class zzbmd extends zzbrd<zzbmd, zza> implements zzbsn {
    private static volatile zzbsw<zzbmd> zzcas;
    private static final zzbmd zzffi = new zzbmd();
    private zzbmj zzfff;
    private zzblz zzffg;
    private int zzffh;

    public static final class zza extends com.google.android.gms.internal.ads.zzbrd.zza<zzbmd, zza> implements zzbsn {
        private zza() {
            super(zzbmd.zzffi);
        }

        /* synthetic */ zza(zzbme zzbme) {
            this();
        }
    }

    private zzbmd() {
    }

    public final zzbmj zzahe() {
        return this.zzfff == null ? zzbmj.zzahu() : this.zzfff;
    }

    public final zzblz zzahf() {
        return this.zzffg == null ? zzblz.zzaha() : this.zzffg;
    }

    public final zzblx zzahg() {
        zzblx zzdp = zzblx.zzdp(this.zzffh);
        return zzdp == null ? zzblx.UNRECOGNIZED : zzdp;
    }

    /* Access modifiers changed, original: protected|final */
    public final Object zza(int i, Object obj, Object obj2) {
        switch (zzbme.zzcaq[i - 1]) {
            case 1:
                return new zzbmd();
            case 2:
                return new zza();
            case 3:
                Object[] objArr = new Object[]{"zzfff", "zzffg", "zzffh"};
                return zzbrd.zza((zzbsl) zzffi, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001\t\u0002\t\u0003\f", objArr);
            case 4:
                return zzffi;
            case 5:
                Object obj3 = zzcas;
                if (obj3 == null) {
                    synchronized (zzbmd.class) {
                        obj3 = zzcas;
                        if (obj3 == null) {
                            obj3 = new zzb(zzffi);
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

    public static zzbmd zzahh() {
        return zzffi;
    }

    static {
        zzbrd.zza(zzbmd.class, zzffi);
    }
}
