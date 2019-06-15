package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzbrd.zzb;
import com.google.android.gms.internal.ads.zzbrd.zze;

public final class zzblv extends zzbrd<zzblv, zza> implements zzbsn {
    private static volatile zzbsw<zzblv> zzcas;
    private static final zzblv zzfeu = new zzblv();
    private int zzfea;
    private zzbpu zzfei = zzbpu.zzfli;

    public static final class zza extends com.google.android.gms.internal.ads.zzbrd.zza<zzblv, zza> implements zzbsn {
        private zza() {
            super(zzblv.zzfeu);
        }

        public final zza zzdo(int i) {
            zzamw();
            ((zzblv) this.zzfpy).setVersion(0);
            return this;
        }

        public final zza zzv(zzbpu zzbpu) {
            zzamw();
            ((zzblv) this.zzfpy).zzk(zzbpu);
            return this;
        }

        /* synthetic */ zza(zzblw zzblw) {
            this();
        }
    }

    private zzblv() {
    }

    public final int getVersion() {
        return this.zzfea;
    }

    private final void setVersion(int i) {
        this.zzfea = i;
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

    public static zzblv zzu(zzbpu zzbpu) throws zzbrl {
        return (zzblv) zzbrd.zza(zzfeu, zzbpu);
    }

    public static zza zzagx() {
        return (zza) ((com.google.android.gms.internal.ads.zzbrd.zza) zzfeu.zza(zze.zzfqf, null, null));
    }

    /* Access modifiers changed, original: protected|final */
    public final Object zza(int i, Object obj, Object obj2) {
        switch (zzblw.zzcaq[i - 1]) {
            case 1:
                return new zzblv();
            case 2:
                return new zza();
            case 3:
                Object[] objArr = new Object[]{"zzfea", "zzfei"};
                return zzbrd.zza((zzbsl) zzfeu, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\u000b\u0002\n", objArr);
            case 4:
                return zzfeu;
            case 5:
                Object obj3 = zzcas;
                if (obj3 == null) {
                    synchronized (zzblv.class) {
                        obj3 = zzcas;
                        if (obj3 == null) {
                            obj3 = new zzb(zzfeu);
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
        zzbrd.zza(zzblv.class, zzfeu);
    }
}
