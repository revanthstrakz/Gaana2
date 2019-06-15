package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzbrd.zzb;
import com.google.android.gms.internal.ads.zzbrd.zze;

public final class zzbll extends zzbrd<zzbll, zza> implements zzbsn {
    private static volatile zzbsw<zzbll> zzcas;
    private static final zzbll zzfep = new zzbll();
    private int zzfea;
    private zzbpu zzfei = zzbpu.zzfli;
    private zzblp zzfeo;

    public static final class zza extends com.google.android.gms.internal.ads.zzbrd.zza<zzbll, zza> implements zzbsn {
        private zza() {
            super(zzbll.zzfep);
        }

        public final zza zzdm(int i) {
            zzamw();
            ((zzbll) this.zzfpy).setVersion(0);
            return this;
        }

        public final zza zzb(zzblp zzblp) {
            zzamw();
            ((zzbll) this.zzfpy).zza(zzblp);
            return this;
        }

        public final zza zzp(zzbpu zzbpu) {
            zzamw();
            ((zzbll) this.zzfpy).zzk(zzbpu);
            return this;
        }

        /* synthetic */ zza(zzblm zzblm) {
            this();
        }
    }

    private zzbll() {
    }

    public final int getVersion() {
        return this.zzfea;
    }

    private final void setVersion(int i) {
        this.zzfea = i;
    }

    public final zzblp zzago() {
        return this.zzfeo == null ? zzblp.zzags() : this.zzfeo;
    }

    private final void zza(zzblp zzblp) {
        if (zzblp == null) {
            throw new NullPointerException();
        }
        this.zzfeo = zzblp;
    }

    public final zzbpu zzagf() {
        return this.zzfei;
    }

    private final void zzk(zzbpu zzbpu) {
        if (zzbpu == null) {
            throw new NullPointerException();
        }
        this.zzfei = zzbpu;
    }

    public static zzbll zzo(zzbpu zzbpu) throws zzbrl {
        return (zzbll) zzbrd.zza(zzfep, zzbpu);
    }

    public static zza zzagp() {
        return (zza) ((com.google.android.gms.internal.ads.zzbrd.zza) zzfep.zza(zze.zzfqf, null, null));
    }

    /* Access modifiers changed, original: protected|final */
    public final Object zza(int i, Object obj, Object obj2) {
        switch (zzblm.zzcaq[i - 1]) {
            case 1:
                return new zzbll();
            case 2:
                return new zza();
            case 3:
                Object[] objArr = new Object[]{"zzfea", "zzfeo", "zzfei"};
                return zzbrd.zza((zzbsl) zzfep, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001\u000b\u0002\t\u0003\n", objArr);
            case 4:
                return zzfep;
            case 5:
                Object obj3 = zzcas;
                if (obj3 == null) {
                    synchronized (zzbll.class) {
                        obj3 = zzcas;
                        if (obj3 == null) {
                            obj3 = new zzb(zzfep);
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
        zzbrd.zza(zzbll.class, zzfep);
    }
}
