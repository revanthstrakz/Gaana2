package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzbrd.zzb;
import com.google.android.gms.internal.ads.zzbrd.zze;

public final class zzbmh extends zzbrd<zzbmh, zza> implements zzbsn {
    private static volatile zzbsw<zzbmh> zzcas;
    private static final zzbmh zzffn = new zzbmh();
    private int zzfea;
    private zzbmd zzffd;
    private zzbpu zzffl = zzbpu.zzfli;
    private zzbpu zzffm = zzbpu.zzfli;

    public static final class zza extends com.google.android.gms.internal.ads.zzbrd.zza<zzbmh, zza> implements zzbsn {
        private zza() {
            super(zzbmh.zzffn);
        }

        public final zza zzdr(int i) {
            zzamw();
            ((zzbmh) this.zzfpy).setVersion(0);
            return this;
        }

        public final zza zzc(zzbmd zzbmd) {
            zzamw();
            ((zzbmh) this.zzfpy).zzb(zzbmd);
            return this;
        }

        public final zza zzac(zzbpu zzbpu) {
            zzamw();
            ((zzbmh) this.zzfpy).zzz(zzbpu);
            return this;
        }

        public final zza zzad(zzbpu zzbpu) {
            zzamw();
            ((zzbmh) this.zzfpy).zzaa(zzbpu);
            return this;
        }

        /* synthetic */ zza(zzbmi zzbmi) {
            this();
        }
    }

    private zzbmh() {
    }

    public final int getVersion() {
        return this.zzfea;
    }

    private final void setVersion(int i) {
        this.zzfea = i;
    }

    public final zzbmd zzahc() {
        return this.zzffd == null ? zzbmd.zzahh() : this.zzffd;
    }

    private final void zzb(zzbmd zzbmd) {
        if (zzbmd == null) {
            throw new NullPointerException();
        }
        this.zzffd = zzbmd;
    }

    public final zzbpu zzahm() {
        return this.zzffl;
    }

    private final void zzz(zzbpu zzbpu) {
        if (zzbpu == null) {
            throw new NullPointerException();
        }
        this.zzffl = zzbpu;
    }

    public final zzbpu zzahn() {
        return this.zzffm;
    }

    private final void zzaa(zzbpu zzbpu) {
        if (zzbpu == null) {
            throw new NullPointerException();
        }
        this.zzffm = zzbpu;
    }

    public static zzbmh zzab(zzbpu zzbpu) throws zzbrl {
        return (zzbmh) zzbrd.zza(zzffn, zzbpu);
    }

    public static zza zzaho() {
        return (zza) ((com.google.android.gms.internal.ads.zzbrd.zza) zzffn.zza(zze.zzfqf, null, null));
    }

    /* Access modifiers changed, original: protected|final */
    public final Object zza(int i, Object obj, Object obj2) {
        switch (zzbmi.zzcaq[i - 1]) {
            case 1:
                return new zzbmh();
            case 2:
                return new zza();
            case 3:
                Object[] objArr = new Object[]{"zzfea", "zzffd", "zzffl", "zzffm"};
                return zzbrd.zza((zzbsl) zzffn, "\u0000\u0004\u0000\u0000\u0001\u0004\u0004\u0000\u0000\u0000\u0001\u000b\u0002\t\u0003\n\u0004\n", objArr);
            case 4:
                return zzffn;
            case 5:
                Object obj3 = zzcas;
                if (obj3 == null) {
                    synchronized (zzbmh.class) {
                        obj3 = zzcas;
                        if (obj3 == null) {
                            obj3 = new zzb(zzffn);
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

    public static zzbmh zzahp() {
        return zzffn;
    }

    static {
        zzbrd.zza(zzbmh.class, zzffn);
    }
}
