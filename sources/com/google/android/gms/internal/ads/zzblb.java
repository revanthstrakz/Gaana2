package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzbrd.zzb;
import com.google.android.gms.internal.ads.zzbrd.zze;

public final class zzblb extends zzbrd<zzblb, zza> implements zzbsn {
    private static volatile zzbsw<zzblb> zzcas;
    private static final zzblb zzfed = new zzblb();
    private int zzfea;
    private zzblf zzfeb;
    private zzbmp zzfec;

    public static final class zza extends com.google.android.gms.internal.ads.zzbrd.zza<zzblb, zza> implements zzbsn {
        private zza() {
            super(zzblb.zzfed);
        }

        public final zza zzdk(int i) {
            zzamw();
            ((zzblb) this.zzfpy).setVersion(i);
            return this;
        }

        public final zza zzb(zzblf zzblf) {
            zzamw();
            ((zzblb) this.zzfpy).zza(zzblf);
            return this;
        }

        public final zza zzb(zzbmp zzbmp) {
            zzamw();
            ((zzblb) this.zzfpy).zza(zzbmp);
            return this;
        }

        /* synthetic */ zza(zzblc zzblc) {
            this();
        }
    }

    private zzblb() {
    }

    public final int getVersion() {
        return this.zzfea;
    }

    private final void setVersion(int i) {
        this.zzfea = i;
    }

    public final zzblf zzafx() {
        return this.zzfeb == null ? zzblf.zzagh() : this.zzfeb;
    }

    private final void zza(zzblf zzblf) {
        if (zzblf == null) {
            throw new NullPointerException();
        }
        this.zzfeb = zzblf;
    }

    public final zzbmp zzafy() {
        return this.zzfec == null ? zzbmp.zzahy() : this.zzfec;
    }

    private final void zza(zzbmp zzbmp) {
        if (zzbmp == null) {
            throw new NullPointerException();
        }
        this.zzfec = zzbmp;
    }

    public static zzblb zzi(zzbpu zzbpu) throws zzbrl {
        return (zzblb) zzbrd.zza(zzfed, zzbpu);
    }

    public static zza zzafz() {
        return (zza) ((com.google.android.gms.internal.ads.zzbrd.zza) zzfed.zza(zze.zzfqf, null, null));
    }

    /* Access modifiers changed, original: protected|final */
    public final Object zza(int i, Object obj, Object obj2) {
        switch (zzblc.zzcaq[i - 1]) {
            case 1:
                return new zzblb();
            case 2:
                return new zza();
            case 3:
                Object[] objArr = new Object[]{"zzfea", "zzfeb", "zzfec"};
                return zzbrd.zza((zzbsl) zzfed, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001\u000b\u0002\t\u0003\t", objArr);
            case 4:
                return zzfed;
            case 5:
                Object obj3 = zzcas;
                if (obj3 == null) {
                    synchronized (zzblb.class) {
                        obj3 = zzcas;
                        if (obj3 == null) {
                            obj3 = new zzb(zzfed);
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
        zzbrd.zza(zzblb.class, zzfed);
    }
}
