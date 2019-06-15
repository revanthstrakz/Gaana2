package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzbrd.zzb;
import com.google.android.gms.internal.ads.zzbrd.zze;

public final class zzblr extends zzbrd<zzblr, zza> implements zzbsn {
    private static volatile zzbsw<zzblr> zzcas;
    private static final zzblr zzfes = new zzblr();
    private int zzfea;
    private zzbpu zzfei = zzbpu.zzfli;

    public static final class zza extends com.google.android.gms.internal.ads.zzbrd.zza<zzblr, zza> implements zzbsn {
        private zza() {
            super(zzblr.zzfes);
        }

        public final zza zzdn(int i) {
            zzamw();
            ((zzblr) this.zzfpy).setVersion(0);
            return this;
        }

        public final zza zzs(zzbpu zzbpu) {
            zzamw();
            ((zzblr) this.zzfpy).zzk(zzbpu);
            return this;
        }

        /* synthetic */ zza(zzbls zzbls) {
            this();
        }
    }

    private zzblr() {
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

    public static zzblr zzr(zzbpu zzbpu) throws zzbrl {
        return (zzblr) zzbrd.zza(zzfes, zzbpu);
    }

    public static zza zzagu() {
        return (zza) ((com.google.android.gms.internal.ads.zzbrd.zza) zzfes.zza(zze.zzfqf, null, null));
    }

    /* Access modifiers changed, original: protected|final */
    public final Object zza(int i, Object obj, Object obj2) {
        switch (zzbls.zzcaq[i - 1]) {
            case 1:
                return new zzblr();
            case 2:
                return new zza();
            case 3:
                Object[] objArr = new Object[]{"zzfea", "zzfei"};
                return zzbrd.zza((zzbsl) zzfes, "\u0000\u0002\u0000\u0000\u0001\u0003\u0002\u0000\u0000\u0000\u0001\u000b\u0003\n", objArr);
            case 4:
                return zzfes;
            case 5:
                Object obj3 = zzcas;
                if (obj3 == null) {
                    synchronized (zzblr.class) {
                        obj3 = zzcas;
                        if (obj3 == null) {
                            obj3 = new zzb(zzfes);
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
        zzbrd.zza(zzblr.class, zzfes);
    }
}
