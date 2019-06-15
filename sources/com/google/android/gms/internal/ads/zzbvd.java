package com.google.android.gms.internal.ads;

public final class zzbvd {

    public static final class zza extends zzbrd<zza, zza> implements zzbsn {
        private static volatile zzbsw<zza> zzcas;
        private static final zza zzfxm = new zza();
        private int zzccg;
        private int zzfxf;
        private zzb zzfxg;
        private zzbpu zzfxh = zzbpu.zzfli;
        private zzbpu zzfxi = zzbpu.zzfli;
        private boolean zzfxj;
        private boolean zzfxk;
        private byte zzfxl = (byte) 2;

        public enum zzc implements zzbrg {
            SAFE(0),
            DANGEROUS(1),
            UNKNOWN(2),
            POTENTIALLY_UNWANTED(3),
            DANGEROUS_HOST(4);
            
            private static final zzbrh<zzc> zzcbx = null;
            private final int value;

            public final int zzom() {
                return this.value;
            }

            public static zzc zzgi(int i) {
                switch (i) {
                    case 0:
                        return SAFE;
                    case 1:
                        return DANGEROUS;
                    case 2:
                        return UNKNOWN;
                    case 3:
                        return POTENTIALLY_UNWANTED;
                    case 4:
                        return DANGEROUS_HOST;
                    default:
                        return null;
                }
            }

            public static zzbri zzop() {
                return zzbvg.zzccw;
            }

            private zzc(int i) {
                this.value = i;
            }

            static {
                zzcbx = new zzbvf();
            }
        }

        public static final class zza extends com.google.android.gms.internal.ads.zzbrd.zza<zza, zza> implements zzbsn {
            private zza() {
                super(zza.zzfxm);
            }

            /* synthetic */ zza(zzbve zzbve) {
                this();
            }
        }

        public static final class zzb extends zzbrd<zzb, zza> implements zzbsn {
            private static volatile zzbsw<zzb> zzcas;
            private static final zzb zzfxr = new zzb();
            private int zzccg;
            private String zzfxn = "";
            private String zzfxo = "";
            private String zzfxp = "";
            private int zzfxq;

            public static final class zza extends com.google.android.gms.internal.ads.zzbrd.zza<zzb, zza> implements zzbsn {
                private zza() {
                    super(zzb.zzfxr);
                }

                /* synthetic */ zza(zzbve zzbve) {
                    this();
                }
            }

            private zzb() {
            }

            /* Access modifiers changed, original: protected|final */
            public final Object zza(int i, Object obj, Object obj2) {
                switch (zzbve.zzcaq[i - 1]) {
                    case 1:
                        return new zzb();
                    case 2:
                        return new zza();
                    case 3:
                        Object[] objArr = new Object[]{"zzccg", "zzfxn", "zzfxo", "zzfxp", "zzfxq"};
                        return zzbrd.zza((zzbsl) zzfxr, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001\b\u0000\u0002\b\u0001\u0003\b\u0002\u0004\u0004\u0003", objArr);
                    case 4:
                        return zzfxr;
                    case 5:
                        Object obj3 = zzcas;
                        if (obj3 == null) {
                            synchronized (zzb.class) {
                                obj3 = zzcas;
                                if (obj3 == null) {
                                    obj3 = new com.google.android.gms.internal.ads.zzbrd.zzb(zzfxr);
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
                zzbrd.zza(zzb.class, zzfxr);
            }
        }

        private zza() {
        }

        /* Access modifiers changed, original: protected|final */
        public final Object zza(int i, Object obj, Object obj2) {
            int i2 = 1;
            switch (zzbve.zzcaq[i - 1]) {
                case 1:
                    return new zza();
                case 2:
                    return new zza();
                case 3:
                    Object[] objArr = new Object[]{"zzccg", "zzfxf", zzc.zzop(), "zzfxg", "zzfxh", "zzfxi", "zzfxj", "zzfxk"};
                    return zzbrd.zza((zzbsl) zzfxm, "\u0001\u0006\u0000\u0001\u0001\u0006\u0006\u0000\u0000\u0001\u0001Ԍ\u0000\u0002\t\u0001\u0003\n\u0002\u0004\n\u0003\u0005\u0007\u0004\u0006\u0007\u0005", objArr);
                case 4:
                    return zzfxm;
                case 5:
                    Object obj3 = zzcas;
                    if (obj3 == null) {
                        synchronized (zza.class) {
                            obj3 = zzcas;
                            if (obj3 == null) {
                                obj3 = new com.google.android.gms.internal.ads.zzbrd.zzb(zzfxm);
                                zzcas = obj3;
                            }
                        }
                    }
                    return obj3;
                case 6:
                    return Byte.valueOf(this.zzfxl);
                case 7:
                    if (obj == null) {
                        i2 = 0;
                    }
                    this.zzfxl = (byte) i2;
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        static {
            zzbrd.zza(zza.class, zzfxm);
        }
    }

    public static final class zzb extends zzbrd<zzb, zza> implements zzbsn {
        private static volatile zzbsw<zzb> zzcas;
        private static final zzb zzfyn = new zzb();
        private int zzccg;
        private int zzcch;
        private zzbpu zzfxh = zzbpu.zzfli;
        private byte zzfxl = (byte) 2;
        private String zzfxo = "";
        private int zzfxy;
        private String zzfxz = "";
        private String zzfya = "";
        private zzb zzfyb;
        private zzbrk<zzh> zzfyc = zzbrd.zzams();
        private String zzfyd = "";
        private zzf zzfye;
        private boolean zzfyf;
        private zzbrk<String> zzfyg = zzbrd.zzams();
        private String zzfyh = "";
        private boolean zzfyi;
        private boolean zzfyj;
        private zzi zzfyk;
        private zzbrk<String> zzfyl = zzbrd.zzams();
        private zzbrk<String> zzfym = zzbrd.zzams();

        public enum zzg implements zzbrg {
            UNKNOWN(0),
            URL_PHISHING(1),
            URL_MALWARE(2),
            URL_UNWANTED(3),
            CLIENT_SIDE_PHISHING_URL(4),
            CLIENT_SIDE_MALWARE_URL(5),
            DANGEROUS_DOWNLOAD_RECOVERY(6),
            DANGEROUS_DOWNLOAD_WARNING(7),
            OCTAGON_AD(8),
            OCTAGON_AD_SB_MATCH(9);
            
            private static final zzbrh<zzg> zzcbx = null;
            private final int value;

            public final int zzom() {
                return this.value;
            }

            public static zzg zzgk(int i) {
                switch (i) {
                    case 0:
                        return UNKNOWN;
                    case 1:
                        return URL_PHISHING;
                    case 2:
                        return URL_MALWARE;
                    case 3:
                        return URL_UNWANTED;
                    case 4:
                        return CLIENT_SIDE_PHISHING_URL;
                    case 5:
                        return CLIENT_SIDE_MALWARE_URL;
                    case 6:
                        return DANGEROUS_DOWNLOAD_RECOVERY;
                    case 7:
                        return DANGEROUS_DOWNLOAD_WARNING;
                    case 8:
                        return OCTAGON_AD;
                    case 9:
                        return OCTAGON_AD_SB_MATCH;
                    default:
                        return null;
                }
            }

            public static zzbri zzop() {
                return zzbvk.zzccw;
            }

            private zzg(int i) {
                this.value = i;
            }

            static {
                zzcbx = new zzbvj();
            }
        }

        public static final class zza extends com.google.android.gms.internal.ads.zzbrd.zza<zzb, zza> implements zzbsn {
            private zza() {
                super(zzb.zzfyn);
            }

            /* synthetic */ zza(zzbve zzbve) {
                this();
            }
        }

        public static final class zzb extends zzbrd<zzb, zza> implements zzbsn {
            private static volatile zzbsw<zzb> zzcas;
            private static final zzb zzfyp = new zzb();
            private int zzccg;
            private String zzfyo = "";

            public static final class zza extends com.google.android.gms.internal.ads.zzbrd.zza<zzb, zza> implements zzbsn {
                private zza() {
                    super(zzb.zzfyp);
                }

                /* synthetic */ zza(zzbve zzbve) {
                    this();
                }
            }

            private zzb() {
            }

            /* Access modifiers changed, original: protected|final */
            public final Object zza(int i, Object obj, Object obj2) {
                switch (zzbve.zzcaq[i - 1]) {
                    case 1:
                        return new zzb();
                    case 2:
                        return new zza();
                    case 3:
                        Object[] objArr = new Object[]{"zzccg", "zzfyo"};
                        return zzbrd.zza((zzbsl) zzfyp, "\u0001\u0001\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u0000\u0001\b\u0000", objArr);
                    case 4:
                        return zzfyp;
                    case 5:
                        Object obj3 = zzcas;
                        if (obj3 == null) {
                            synchronized (zzb.class) {
                                obj3 = zzcas;
                                if (obj3 == null) {
                                    obj3 = new com.google.android.gms.internal.ads.zzbrd.zzb(zzfyp);
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
                zzbrd.zza(zzb.class, zzfyp);
            }
        }

        public static final class zzc extends zzbrd<zzc, zza> implements zzbsn {
            private static volatile zzbsw<zzc> zzcas;
            private static final zzc zzfyr = new zzc();
            private int zzccg;
            private zzbpu zzfgl = zzbpu.zzfli;
            private byte zzfxl = (byte) 2;
            private zzbpu zzfyq = zzbpu.zzfli;

            public static final class zza extends com.google.android.gms.internal.ads.zzbrd.zza<zzc, zza> implements zzbsn {
                private zza() {
                    super(zzc.zzfyr);
                }

                /* synthetic */ zza(zzbve zzbve) {
                    this();
                }
            }

            private zzc() {
            }

            /* Access modifiers changed, original: protected|final */
            public final Object zza(int i, Object obj, Object obj2) {
                int i2 = 1;
                switch (zzbve.zzcaq[i - 1]) {
                    case 1:
                        return new zzc();
                    case 2:
                        return new zza();
                    case 3:
                        Object[] objArr = new Object[]{"zzccg", "zzfyq", "zzfgl"};
                        return zzbrd.zza((zzbsl) zzfyr, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0001\u0001Ԋ\u0000\u0002\n\u0001", objArr);
                    case 4:
                        return zzfyr;
                    case 5:
                        Object obj3 = zzcas;
                        if (obj3 == null) {
                            synchronized (zzc.class) {
                                obj3 = zzcas;
                                if (obj3 == null) {
                                    obj3 = new com.google.android.gms.internal.ads.zzbrd.zzb(zzfyr);
                                    zzcas = obj3;
                                }
                            }
                        }
                        return obj3;
                    case 6:
                        return Byte.valueOf(this.zzfxl);
                    case 7:
                        if (obj == null) {
                            i2 = 0;
                        }
                        this.zzfxl = (byte) i2;
                        return null;
                    default:
                        throw new UnsupportedOperationException();
                }
            }

            static {
                zzbrd.zza(zzc.class, zzfyr);
            }
        }

        public static final class zzd extends zzbrd<zzd, zza> implements zzbsn {
            private static volatile zzbsw<zzd> zzcas;
            private static final zzd zzfyx = new zzd();
            private int zzccg;
            private byte zzfxl = (byte) 2;
            private zzb zzfys;
            private zzbrk<zzc> zzfyt = zzbrd.zzams();
            private zzbpu zzfyu = zzbpu.zzfli;
            private zzbpu zzfyv = zzbpu.zzfli;
            private int zzfyw;

            public static final class zza extends com.google.android.gms.internal.ads.zzbrd.zza<zzd, zza> implements zzbsn {
                private zza() {
                    super(zzd.zzfyx);
                }

                /* synthetic */ zza(zzbve zzbve) {
                    this();
                }
            }

            public static final class zzb extends zzbrd<zzb, zza> implements zzbsn {
                private static volatile zzbsw<zzb> zzcas;
                private static final zzb zzfzb = new zzb();
                private int zzccg;
                private zzbpu zzfyy = zzbpu.zzfli;
                private zzbpu zzfyz = zzbpu.zzfli;
                private zzbpu zzfza = zzbpu.zzfli;

                public static final class zza extends com.google.android.gms.internal.ads.zzbrd.zza<zzb, zza> implements zzbsn {
                    private zza() {
                        super(zzb.zzfzb);
                    }

                    /* synthetic */ zza(zzbve zzbve) {
                        this();
                    }
                }

                private zzb() {
                }

                /* Access modifiers changed, original: protected|final */
                public final Object zza(int i, Object obj, Object obj2) {
                    switch (zzbve.zzcaq[i - 1]) {
                        case 1:
                            return new zzb();
                        case 2:
                            return new zza();
                        case 3:
                            Object[] objArr = new Object[]{"zzccg", "zzfyy", "zzfyz", "zzfza"};
                            return zzbrd.zza((zzbsl) zzfzb, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001\n\u0000\u0002\n\u0001\u0003\n\u0002", objArr);
                        case 4:
                            return zzfzb;
                        case 5:
                            Object obj3 = zzcas;
                            if (obj3 == null) {
                                synchronized (zzb.class) {
                                    obj3 = zzcas;
                                    if (obj3 == null) {
                                        obj3 = new com.google.android.gms.internal.ads.zzbrd.zzb(zzfzb);
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

                public static zzbsw<zzb> zzon() {
                    return (zzbsw) zzfzb.zza(com.google.android.gms.internal.ads.zzbrd.zze.zzfqh, null, null);
                }

                static {
                    zzbrd.zza(zzb.class, zzfzb);
                }
            }

            private zzd() {
            }

            /* Access modifiers changed, original: protected|final */
            public final Object zza(int i, Object obj, Object obj2) {
                int i2 = 1;
                switch (zzbve.zzcaq[i - 1]) {
                    case 1:
                        return new zzd();
                    case 2:
                        return new zza();
                    case 3:
                        Object[] objArr = new Object[]{"zzccg", "zzfys", "zzfyt", zzc.class, "zzfyu", "zzfyv", "zzfyw"};
                        return zzbrd.zza((zzbsl) zzfyx, "\u0001\u0005\u0000\u0001\u0001\u0005\u0005\u0000\u0001\u0001\u0001\t\u0000\u0002Л\u0003\n\u0001\u0004\n\u0002\u0005\u0004\u0003", objArr);
                    case 4:
                        return zzfyx;
                    case 5:
                        Object obj3 = zzcas;
                        if (obj3 == null) {
                            synchronized (zzd.class) {
                                obj3 = zzcas;
                                if (obj3 == null) {
                                    obj3 = new com.google.android.gms.internal.ads.zzbrd.zzb(zzfyx);
                                    zzcas = obj3;
                                }
                            }
                        }
                        return obj3;
                    case 6:
                        return Byte.valueOf(this.zzfxl);
                    case 7:
                        if (obj == null) {
                            i2 = 0;
                        }
                        this.zzfxl = (byte) i2;
                        return null;
                    default:
                        throw new UnsupportedOperationException();
                }
            }

            static {
                zzbrd.zza(zzd.class, zzfyx);
            }
        }

        public static final class zze extends zzbrd<zze, zza> implements zzbsn {
            private static volatile zzbsw<zze> zzcas;
            private static final zze zzfze = new zze();
            private int zzccg;
            private byte zzfxl = (byte) 2;
            private zzbrk<zzc> zzfyt = zzbrd.zzams();
            private zzbpu zzfyu = zzbpu.zzfli;
            private zzbpu zzfyv = zzbpu.zzfli;
            private int zzfyw;
            private zzb zzfzc;
            private zzbpu zzfzd = zzbpu.zzfli;

            public static final class zza extends com.google.android.gms.internal.ads.zzbrd.zza<zze, zza> implements zzbsn {
                private zza() {
                    super(zze.zzfze);
                }

                /* synthetic */ zza(zzbve zzbve) {
                    this();
                }
            }

            public static final class zzb extends zzbrd<zzb, zza> implements zzbsn {
                private static volatile zzbsw<zzb> zzcas;
                private static final zzb zzfzh = new zzb();
                private int zzccg;
                private zzbpu zzfza = zzbpu.zzfli;
                private int zzfzf;
                private zzbpu zzfzg = zzbpu.zzfli;

                public static final class zza extends com.google.android.gms.internal.ads.zzbrd.zza<zzb, zza> implements zzbsn {
                    private zza() {
                        super(zzb.zzfzh);
                    }

                    /* synthetic */ zza(zzbve zzbve) {
                        this();
                    }
                }

                private zzb() {
                }

                /* Access modifiers changed, original: protected|final */
                public final Object zza(int i, Object obj, Object obj2) {
                    switch (zzbve.zzcaq[i - 1]) {
                        case 1:
                            return new zzb();
                        case 2:
                            return new zza();
                        case 3:
                            Object[] objArr = new Object[]{"zzccg", "zzfzf", "zzfzg", "zzfza"};
                            return zzbrd.zza((zzbsl) zzfzh, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001\u0004\u0000\u0002\n\u0001\u0003\n\u0002", objArr);
                        case 4:
                            return zzfzh;
                        case 5:
                            Object obj3 = zzcas;
                            if (obj3 == null) {
                                synchronized (zzb.class) {
                                    obj3 = zzcas;
                                    if (obj3 == null) {
                                        obj3 = new com.google.android.gms.internal.ads.zzbrd.zzb(zzfzh);
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

                public static zzbsw<zzb> zzon() {
                    return (zzbsw) zzfzh.zza(com.google.android.gms.internal.ads.zzbrd.zze.zzfqh, null, null);
                }

                static {
                    zzbrd.zza(zzb.class, zzfzh);
                }
            }

            private zze() {
            }

            /* Access modifiers changed, original: protected|final */
            public final Object zza(int i, Object obj, Object obj2) {
                int i2 = 1;
                switch (zzbve.zzcaq[i - 1]) {
                    case 1:
                        return new zze();
                    case 2:
                        return new zza();
                    case 3:
                        Object[] objArr = new Object[]{"zzccg", "zzfzc", "zzfyt", zzc.class, "zzfyu", "zzfyv", "zzfyw", "zzfzd"};
                        return zzbrd.zza((zzbsl) zzfze, "\u0001\u0006\u0000\u0001\u0001\u0006\u0006\u0000\u0001\u0001\u0001\t\u0000\u0002Л\u0003\n\u0001\u0004\n\u0002\u0005\u0004\u0003\u0006\n\u0004", objArr);
                    case 4:
                        return zzfze;
                    case 5:
                        Object obj3 = zzcas;
                        if (obj3 == null) {
                            synchronized (zze.class) {
                                obj3 = zzcas;
                                if (obj3 == null) {
                                    obj3 = new com.google.android.gms.internal.ads.zzbrd.zzb(zzfze);
                                    zzcas = obj3;
                                }
                            }
                        }
                        return obj3;
                    case 6:
                        return Byte.valueOf(this.zzfxl);
                    case 7:
                        if (obj == null) {
                            i2 = 0;
                        }
                        this.zzfxl = (byte) i2;
                        return null;
                    default:
                        throw new UnsupportedOperationException();
                }
            }

            static {
                zzbrd.zza(zze.class, zzfze);
            }
        }

        public static final class zzf extends zzbrd<zzf, zza> implements zzbsn {
            private static volatile zzbsw<zzf> zzcas;
            private static final zzf zzfzk = new zzf();
            private int zzccg;
            private int zzcch;
            private String zzfzi = "";
            private zzbpu zzfzj = zzbpu.zzfli;

            public enum zzb implements zzbrg {
                TYPE_UNKNOWN(0),
                TYPE_CREATIVE(1);
                
                private static final zzbrh<zzb> zzcbx = null;
                private final int value;

                public final int zzom() {
                    return this.value;
                }

                public static zzb zzgj(int i) {
                    switch (i) {
                        case 0:
                            return TYPE_UNKNOWN;
                        case 1:
                            return TYPE_CREATIVE;
                        default:
                            return null;
                    }
                }

                public static zzbri zzop() {
                    return zzbvi.zzccw;
                }

                private zzb(int i) {
                    this.value = i;
                }

                static {
                    zzcbx = new zzbvh();
                }
            }

            public static final class zza extends com.google.android.gms.internal.ads.zzbrd.zza<zzf, zza> implements zzbsn {
                private zza() {
                    super(zzf.zzfzk);
                }

                /* synthetic */ zza(zzbve zzbve) {
                    this();
                }
            }

            private zzf() {
            }

            /* Access modifiers changed, original: protected|final */
            public final Object zza(int i, Object obj, Object obj2) {
                switch (zzbve.zzcaq[i - 1]) {
                    case 1:
                        return new zzf();
                    case 2:
                        return new zza();
                    case 3:
                        Object[] objArr = new Object[]{"zzccg", "zzcch", zzb.zzop(), "zzfzi", "zzfzj"};
                        return zzbrd.zza((zzbsl) zzfzk, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001\f\u0000\u0002\b\u0001\u0003\n\u0002", objArr);
                    case 4:
                        return zzfzk;
                    case 5:
                        Object obj3 = zzcas;
                        if (obj3 == null) {
                            synchronized (zzf.class) {
                                obj3 = zzcas;
                                if (obj3 == null) {
                                    obj3 = new com.google.android.gms.internal.ads.zzbrd.zzb(zzfzk);
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
                zzbrd.zza(zzf.class, zzfzk);
            }
        }

        public static final class zzh extends zzbrd<zzh, zzb> implements zzbsn {
            private static volatile zzbsw<zzh> zzcas;
            private static final zzh zzgah = new zzh();
            private int zzccg;
            private byte zzfxl = (byte) 2;
            private String zzfxo = "";
            private int zzfzz;
            private zzd zzgaa;
            private zze zzgab;
            private int zzgac;
            private zzbrj zzgad = zzbrd.zzamr();
            private String zzgae = "";
            private int zzgaf;
            private zzbrk<String> zzgag = zzbrd.zzams();

            public enum zza implements zzbrg {
                AD_RESOURCE_UNKNOWN(0),
                AD_RESOURCE_CREATIVE(1),
                AD_RESOURCE_POST_CLICK(2),
                AD_RESOURCE_AUTO_CLICK_DESTINATION(3);
                
                private static final zzbrh<zza> zzcbx = null;
                private final int value;

                public final int zzom() {
                    return this.value;
                }

                public static zza zzgl(int i) {
                    switch (i) {
                        case 0:
                            return AD_RESOURCE_UNKNOWN;
                        case 1:
                            return AD_RESOURCE_CREATIVE;
                        case 2:
                            return AD_RESOURCE_POST_CLICK;
                        case 3:
                            return AD_RESOURCE_AUTO_CLICK_DESTINATION;
                        default:
                            return null;
                    }
                }

                public static zzbri zzop() {
                    return zzbvm.zzccw;
                }

                private zza(int i) {
                    this.value = i;
                }

                static {
                    zzcbx = new zzbvl();
                }
            }

            public static final class zzb extends com.google.android.gms.internal.ads.zzbrd.zza<zzh, zzb> implements zzbsn {
                private zzb() {
                    super(zzh.zzgah);
                }

                /* synthetic */ zzb(zzbve zzbve) {
                    this();
                }
            }

            private zzh() {
            }

            /* Access modifiers changed, original: protected|final */
            public final Object zza(int i, Object obj, Object obj2) {
                int i2 = 1;
                switch (zzbve.zzcaq[i - 1]) {
                    case 1:
                        return new zzh();
                    case 2:
                        return new zzb();
                    case 3:
                        Object[] objArr = new Object[]{"zzccg", "zzfzz", "zzfxo", "zzgaa", "zzgab", "zzgac", "zzgad", "zzgae", "zzgaf", zza.zzop(), "zzgag"};
                        return zzbrd.zza((zzbsl) zzgah, "\u0001\t\u0000\u0001\u0001\t\t\u0000\u0002\u0003\u0001Ԅ\u0000\u0002\b\u0001\u0003Љ\u0002\u0004Љ\u0003\u0005\u0004\u0004\u0006\u0016\u0007\b\u0005\b\f\u0006\t\u001a", objArr);
                    case 4:
                        return zzgah;
                    case 5:
                        Object obj3 = zzcas;
                        if (obj3 == null) {
                            synchronized (zzh.class) {
                                obj3 = zzcas;
                                if (obj3 == null) {
                                    obj3 = new com.google.android.gms.internal.ads.zzbrd.zzb(zzgah);
                                    zzcas = obj3;
                                }
                            }
                        }
                        return obj3;
                    case 6:
                        return Byte.valueOf(this.zzfxl);
                    case 7:
                        if (obj == null) {
                            i2 = 0;
                        }
                        this.zzfxl = (byte) i2;
                        return null;
                    default:
                        throw new UnsupportedOperationException();
                }
            }

            static {
                zzbrd.zza(zzh.class, zzgah);
            }
        }

        public static final class zzi extends zzbrd<zzi, zza> implements zzbsn {
            private static volatile zzbsw<zzi> zzcas;
            private static final zzi zzgaq = new zzi();
            private int zzccg;
            private String zzgan = "";
            private long zzgao;
            private boolean zzgap;

            public static final class zza extends com.google.android.gms.internal.ads.zzbrd.zza<zzi, zza> implements zzbsn {
                private zza() {
                    super(zzi.zzgaq);
                }

                /* synthetic */ zza(zzbve zzbve) {
                    this();
                }
            }

            private zzi() {
            }

            /* Access modifiers changed, original: protected|final */
            public final Object zza(int i, Object obj, Object obj2) {
                switch (zzbve.zzcaq[i - 1]) {
                    case 1:
                        return new zzi();
                    case 2:
                        return new zza();
                    case 3:
                        Object[] objArr = new Object[]{"zzccg", "zzgan", "zzgao", "zzgap"};
                        return zzbrd.zza((zzbsl) zzgaq, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001\b\u0000\u0002\u0002\u0001\u0003\u0007\u0002", objArr);
                    case 4:
                        return zzgaq;
                    case 5:
                        Object obj3 = zzcas;
                        if (obj3 == null) {
                            synchronized (zzi.class) {
                                obj3 = zzcas;
                                if (obj3 == null) {
                                    obj3 = new com.google.android.gms.internal.ads.zzbrd.zzb(zzgaq);
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
                zzbrd.zza(zzi.class, zzgaq);
            }
        }

        private zzb() {
        }

        /* Access modifiers changed, original: protected|final */
        public final Object zza(int i, Object obj, Object obj2) {
            int i2 = 1;
            switch (zzbve.zzcaq[i - 1]) {
                case 1:
                    return new zzb();
                case 2:
                    return new zza();
                case 3:
                    Object[] objArr = new Object[]{"zzccg", "zzfxo", "zzfxz", "zzfya", "zzfyc", zzh.class, "zzfyf", "zzfyg", "zzfyh", "zzfyi", "zzfyj", "zzcch", zzg.zzop(), "zzfxy", zzc.zzop(), "zzfyb", "zzfyd", "zzfye", "zzfxh", "zzfyk", "zzfyl", "zzfym"};
                    return zzbrd.zza((zzbsl) zzfyn, "\u0001\u0012\u0000\u0001\u0001\u0015\u0012\u0000\u0004\u0001\u0001\b\u0002\u0002\b\u0003\u0003\b\u0004\u0004Л\u0005\u0007\b\u0006\u001a\u0007\b\t\b\u0007\n\t\u0007\u000b\n\f\u0000\u000b\f\u0001\f\t\u0005\r\b\u0006\u000e\t\u0007\u000f\n\f\u0011\t\r\u0014\u001a\u0015\u001a", objArr);
                case 4:
                    return zzfyn;
                case 5:
                    Object obj3 = zzcas;
                    if (obj3 == null) {
                        synchronized (zzb.class) {
                            obj3 = zzcas;
                            if (obj3 == null) {
                                obj3 = new com.google.android.gms.internal.ads.zzbrd.zzb(zzfyn);
                                zzcas = obj3;
                            }
                        }
                    }
                    return obj3;
                case 6:
                    return Byte.valueOf(this.zzfxl);
                case 7:
                    if (obj == null) {
                        i2 = 0;
                    }
                    this.zzfxl = (byte) i2;
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        static {
            zzbrd.zza(zzb.class, zzfyn);
        }
    }
}
