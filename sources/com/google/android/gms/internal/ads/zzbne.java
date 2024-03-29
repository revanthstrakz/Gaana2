package com.google.android.gms.internal.ads;

import java.util.List;

public final class zzbne extends zzbrd<zzbne, zza> implements zzbsn {
    private static volatile zzbsw<zzbne> zzcas;
    private static final zzbne zzfhk = new zzbne();
    private int zzccg;
    private int zzfhi;
    private zzbrk<zzb> zzfhj = zzbrd.zzams();

    public static final class zza extends com.google.android.gms.internal.ads.zzbrd.zza<zzbne, zza> implements zzbsn {
        private zza() {
            super(zzbne.zzfhk);
        }

        /* synthetic */ zza(zzbnf zzbnf) {
            this();
        }
    }

    public static final class zzb extends zzbrd<zzb, zza> implements zzbsn {
        private static volatile zzbsw<zzb> zzcas;
        private static final zzb zzfho = new zzb();
        private int zzfhb;
        private zzbmv zzfhl;
        private int zzfhm;
        private int zzfhn;

        public static final class zza extends com.google.android.gms.internal.ads.zzbrd.zza<zzb, zza> implements zzbsn {
            private zza() {
                super(zzb.zzfho);
            }

            /* synthetic */ zza(zzbnf zzbnf) {
                this();
            }
        }

        private zzb() {
        }

        public final boolean zzaiy() {
            return this.zzfhl != null;
        }

        public final zzbmv zzaiz() {
            return this.zzfhl == null ? zzbmv.zzaik() : this.zzfhl;
        }

        public final zzbmy zzaja() {
            zzbmy zzdw = zzbmy.zzdw(this.zzfhm);
            return zzdw == null ? zzbmy.UNRECOGNIZED : zzdw;
        }

        public final int zzajb() {
            return this.zzfhn;
        }

        public final zzbnq zzajc() {
            zzbnq zzef = zzbnq.zzef(this.zzfhb);
            return zzef == null ? zzbnq.UNRECOGNIZED : zzef;
        }

        /* Access modifiers changed, original: protected|final */
        public final Object zza(int i, Object obj, Object obj2) {
            switch (zzbnf.zzcaq[i - 1]) {
                case 1:
                    return new zzb();
                case 2:
                    return new zza();
                case 3:
                    Object[] objArr = new Object[]{"zzfhl", "zzfhm", "zzfhn", "zzfhb"};
                    return zzbrd.zza((zzbsl) zzfho, "\u0000\u0004\u0000\u0000\u0001\u0004\u0004\u0000\u0000\u0000\u0001\t\u0002\f\u0003\u000b\u0004\f", objArr);
                case 4:
                    return zzfho;
                case 5:
                    Object obj3 = zzcas;
                    if (obj3 == null) {
                        synchronized (zzb.class) {
                            obj3 = zzcas;
                            if (obj3 == null) {
                                obj3 = new com.google.android.gms.internal.ads.zzbrd.zzb(zzfho);
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
            zzbrd.zza(zzb.class, zzfho);
        }
    }

    private zzbne() {
    }

    public final int zzaiu() {
        return this.zzfhi;
    }

    public final List<zzb> zzaiv() {
        return this.zzfhj;
    }

    public final int zzaiw() {
        return this.zzfhj.size();
    }

    public static zzbne zzm(byte[] bArr) throws zzbrl {
        return (zzbne) zzbrd.zzb(zzfhk, bArr);
    }

    /* Access modifiers changed, original: protected|final */
    public final Object zza(int i, Object obj, Object obj2) {
        switch (zzbnf.zzcaq[i - 1]) {
            case 1:
                return new zzbne();
            case 2:
                return new zza();
            case 3:
                Object[] objArr = new Object[]{"zzccg", "zzfhi", "zzfhj", zzb.class};
                return zzbrd.zza((zzbsl) zzfhk, "\u0000\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0001\u0000\u0001\u000b\u0002\u001b", objArr);
            case 4:
                return zzfhk;
            case 5:
                Object obj3 = zzcas;
                if (obj3 == null) {
                    synchronized (zzbne.class) {
                        obj3 = zzcas;
                        if (obj3 == null) {
                            obj3 = new com.google.android.gms.internal.ads.zzbrd.zzb(zzfhk);
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
        zzbrd.zza(zzbne.class, zzfhk);
    }
}
