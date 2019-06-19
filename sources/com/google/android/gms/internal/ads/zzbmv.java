package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzbrd.zze;

public final class zzbmv extends zzbrd<zzbmv, zza> implements zzbsn {
    private static volatile zzbsw<zzbmv> zzcas;
    private static final zzbmv zzfgn = new zzbmv();
    private String zzfgk = "";
    private zzbpu zzfgl = zzbpu.zzfli;
    private int zzfgm;

    public static final class zza extends com.google.android.gms.internal.ads.zzbrd.zza<zzbmv, zza> implements zzbsn {
        private zza() {
            super(zzbmv.zzfgn);
        }

        public final zza zzfl(String str) {
            zzamw();
            ((zzbmv) this.zzfpy).zzfk(str);
            return this;
        }

        public final zza zzai(zzbpu zzbpu) {
            zzamw();
            ((zzbmv) this.zzfpy).zzah(zzbpu);
            return this;
        }

        public final zza zzb(zzb zzb) {
            zzamw();
            ((zzbmv) this.zzfpy).zza(zzb);
            return this;
        }

        /* synthetic */ zza(zzbmw zzbmw) {
            this();
        }
    }

    public enum zzb implements zzbrg {
        UNKNOWN_KEYMATERIAL(0),
        SYMMETRIC(1),
        ASYMMETRIC_PRIVATE(2),
        ASYMMETRIC_PUBLIC(3),
        REMOTE(4),
        UNRECOGNIZED(-1);
        
        private static final zzbrh<zzb> zzcbx = null;
        private final int value;

        public final int zzom() {
            if (this != UNRECOGNIZED) {
                return this.value;
            }
            throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
        }

        public static zzb zzdv(int i) {
            switch (i) {
                case 0:
                    return UNKNOWN_KEYMATERIAL;
                case 1:
                    return SYMMETRIC;
                case 2:
                    return ASYMMETRIC_PRIVATE;
                case 3:
                    return ASYMMETRIC_PUBLIC;
                case 4:
                    return REMOTE;
                default:
                    return null;
            }
        }

        private zzb(int i) {
            this.value = i;
        }

        static {
            zzcbx = new zzbmx();
        }
    }

    private zzbmv() {
    }

    public final String zzaig() {
        return this.zzfgk;
    }

    private final void zzfk(String str) {
        if (str == null) {
            throw new NullPointerException();
        }
        this.zzfgk = str;
    }

    public final zzbpu zzaih() {
        return this.zzfgl;
    }

    private final void zzah(zzbpu zzbpu) {
        if (zzbpu == null) {
            throw new NullPointerException();
        }
        this.zzfgl = zzbpu;
    }

    public final zzb zzaii() {
        zzb zzdv = zzb.zzdv(this.zzfgm);
        return zzdv == null ? zzb.UNRECOGNIZED : zzdv;
    }

    private final void zza(zzb zzb) {
        if (zzb == null) {
            throw new NullPointerException();
        }
        this.zzfgm = zzb.zzom();
    }

    public static zza zzaij() {
        return (zza) ((com.google.android.gms.internal.ads.zzbrd.zza) zzfgn.zza(zze.zzfqf, null, null));
    }

    /* Access modifiers changed, original: protected|final */
    public final Object zza(int i, Object obj, Object obj2) {
        switch (zzbmw.zzcaq[i - 1]) {
            case 1:
                return new zzbmv();
            case 2:
                return new zza();
            case 3:
                Object[] objArr = new Object[]{"zzfgk", "zzfgl", "zzfgm"};
                return zzbrd.zza((zzbsl) zzfgn, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001Ȉ\u0002\n\u0003\f", objArr);
            case 4:
                return zzfgn;
            case 5:
                Object obj3 = zzcas;
                if (obj3 == null) {
                    synchronized (zzbmv.class) {
                        obj3 = zzcas;
                        if (obj3 == null) {
                            obj3 = new com.google.android.gms.internal.ads.zzbrd.zzb(zzfgn);
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

    public static zzbmv zzaik() {
        return zzfgn;
    }

    static {
        zzbrd.zza(zzbmv.class, zzfgn);
    }
}
