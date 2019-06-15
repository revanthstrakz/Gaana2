package com.google.android.gms.internal.icing;

public final class zzak {

    public static final class zza extends zzdj<zza, zza> implements zzes {
        private static final zza zzbb = new zza();
        private static volatile zzfa<zza> zzbc;
        private zzdq<zzb> zzba = zzdj.zzbt();

        public static final class zza extends com.google.android.gms.internal.icing.zzdj.zza<zza, zza> implements zzes {
            private zza() {
                super(zza.zzbb);
            }

            public final zza zzb(Iterable<? extends zzb> iterable) {
                zzbw();
                ((zza) this.zzjx).zza(iterable);
                return this;
            }

            /* synthetic */ zza(zzal zzal) {
                this();
            }
        }

        public static final class zzb extends zzdj<zzb, zza> implements zzes {
            private static volatile zzfa<zzb> zzbc;
            private static final zzb zzbh = new zzb();
            private int zzbd;
            private String zzbe = "";
            private String zzbf = "";
            private int zzbg;

            public static final class zza extends com.google.android.gms.internal.icing.zzdj.zza<zzb, zza> implements zzes {
                private zza() {
                    super(zzb.zzbh);
                }

                public final zza zzg(String str) {
                    zzbw();
                    ((zzb) this.zzjx).zze(str);
                    return this;
                }

                public final zza zzh(String str) {
                    zzbw();
                    ((zzb) this.zzjx).zzf(str);
                    return this;
                }

                public final zza zze(int i) {
                    zzbw();
                    ((zzb) this.zzjx).zzd(i);
                    return this;
                }

                /* synthetic */ zza(zzal zzal) {
                    this();
                }
            }

            private zzb() {
            }

            private final void zze(String str) {
                if (str == null) {
                    throw new NullPointerException();
                }
                this.zzbd |= 1;
                this.zzbe = str;
            }

            private final void zzf(String str) {
                if (str == null) {
                    throw new NullPointerException();
                }
                this.zzbd |= 2;
                this.zzbf = str;
            }

            private final void zzd(int i) {
                this.zzbd |= 4;
                this.zzbg = i;
            }

            public static zza zzh() {
                return (zza) ((com.google.android.gms.internal.icing.zzdj.zza) zzbh.zza(com.google.android.gms.internal.icing.zzdj.zzd.zzke, null, null));
            }

            /* Access modifiers changed, original: protected|final */
            public final Object zza(int i, Object obj, Object obj2) {
                switch (zzal.zzaz[i - 1]) {
                    case 1:
                        return new zzb();
                    case 2:
                        return new zza();
                    case 3:
                        Object[] objArr = new Object[]{"zzbd", "zzbe", "zzbf", "zzbg"};
                        return zzdj.zza((zzeq) zzbh, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001\b\u0000\u0002\b\u0001\u0003\u0004\u0002", objArr);
                    case 4:
                        return zzbh;
                    case 5:
                        Object obj3 = zzbc;
                        if (obj3 == null) {
                            synchronized (zzb.class) {
                                obj3 = zzbc;
                                if (obj3 == null) {
                                    obj3 = new com.google.android.gms.internal.icing.zzdj.zzb(zzbh);
                                    zzbc = obj3;
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
                zzdj.zza(zzb.class, zzbh);
            }
        }

        private zza() {
        }

        private final void zza(Iterable<? extends zzb> iterable) {
            if (!this.zzba.zzai()) {
                zzdq zzdq = this.zzba;
                int size = zzdq.size();
                this.zzba = zzdq.zzj(size == 0 ? 10 : size << 1);
            }
            zzbx.zza(iterable, this.zzba);
        }

        public static zza zzf() {
            return (zza) ((com.google.android.gms.internal.icing.zzdj.zza) zzbb.zza(com.google.android.gms.internal.icing.zzdj.zzd.zzke, null, null));
        }

        /* Access modifiers changed, original: protected|final */
        public final Object zza(int i, Object obj, Object obj2) {
            switch (zzal.zzaz[i - 1]) {
                case 1:
                    return new zza();
                case 2:
                    return new zza();
                case 3:
                    Object[] objArr = new Object[]{"zzba", zzb.class};
                    return zzdj.zza((zzeq) zzbb, "\u0001\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0000\u0001\u001b", objArr);
                case 4:
                    return zzbb;
                case 5:
                    Object obj3 = zzbc;
                    if (obj3 == null) {
                        synchronized (zza.class) {
                            obj3 = zzbc;
                            if (obj3 == null) {
                                obj3 = new com.google.android.gms.internal.icing.zzdj.zzb(zzbb);
                                zzbc = obj3;
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
            zzdj.zza(zza.class, zzbb);
        }
    }

    public static final class zzb extends zzdj<zzb, zza> implements zzes {
        private static volatile zzfa<zzb> zzbc;
        private static final zzb zzbk = new zzb();
        private int zzbd;
        private String zzbi = "";
        private zzd zzbj;

        public static final class zza extends com.google.android.gms.internal.icing.zzdj.zza<zzb, zza> implements zzes {
            private zza() {
                super(zzb.zzbk);
            }

            public final zza zzi(String str) {
                zzbw();
                ((zzb) this.zzjx).setName(str);
                return this;
            }

            public final zza zzb(zzd zzd) {
                zzbw();
                ((zzb) this.zzjx).zza(zzd);
                return this;
            }

            /* synthetic */ zza(zzal zzal) {
                this();
            }
        }

        private zzb() {
        }

        private final void setName(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.zzbd |= 1;
            this.zzbi = str;
        }

        private final void zza(zzd zzd) {
            if (zzd == null) {
                throw new NullPointerException();
            }
            this.zzbj = zzd;
            this.zzbd |= 2;
        }

        public static zza zzj() {
            return (zza) ((com.google.android.gms.internal.icing.zzdj.zza) zzbk.zza(com.google.android.gms.internal.icing.zzdj.zzd.zzke, null, null));
        }

        /* Access modifiers changed, original: protected|final */
        public final Object zza(int i, Object obj, Object obj2) {
            switch (zzal.zzaz[i - 1]) {
                case 1:
                    return new zzb();
                case 2:
                    return new zza();
                case 3:
                    Object[] objArr = new Object[]{"zzbd", "zzbi", "zzbj"};
                    return zzdj.zza((zzeq) zzbk, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001\b\u0000\u0002\t\u0001", objArr);
                case 4:
                    return zzbk;
                case 5:
                    Object obj3 = zzbc;
                    if (obj3 == null) {
                        synchronized (zzb.class) {
                            obj3 = zzbc;
                            if (obj3 == null) {
                                obj3 = new com.google.android.gms.internal.icing.zzdj.zzb(zzbk);
                                zzbc = obj3;
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
            zzdj.zza(zzb.class, zzbk);
        }
    }

    public static final class zzc extends zzdj<zzc, zza> implements zzes {
        private static volatile zzfa<zzc> zzbc;
        private static final zzc zzbn = new zzc();
        private int zzbd;
        private String zzbl = "";
        private zzdq<zzb> zzbm = zzdj.zzbt();

        public static final class zza extends com.google.android.gms.internal.icing.zzdj.zza<zzc, zza> implements zzes {
            private zza() {
                super(zzc.zzbn);
            }

            public final zza zzk(String str) {
                zzbw();
                ((zzc) this.zzjx).zzj(str);
                return this;
            }

            public final zza zzb(zzb zzb) {
                zzbw();
                ((zzc) this.zzjx).zza(zzb);
                return this;
            }

            /* synthetic */ zza(zzal zzal) {
                this();
            }
        }

        private zzc() {
        }

        private final void zzj(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.zzbd |= 1;
            this.zzbl = str;
        }

        private final void zza(zzb zzb) {
            if (zzb == null) {
                throw new NullPointerException();
            }
            if (!this.zzbm.zzai()) {
                zzdq zzdq = this.zzbm;
                int size = zzdq.size();
                this.zzbm = zzdq.zzj(size == 0 ? 10 : size << 1);
            }
            this.zzbm.add(zzb);
        }

        public static zza zzl() {
            return (zza) ((com.google.android.gms.internal.icing.zzdj.zza) zzbn.zza(com.google.android.gms.internal.icing.zzdj.zzd.zzke, null, null));
        }

        /* Access modifiers changed, original: protected|final */
        public final Object zza(int i, Object obj, Object obj2) {
            switch (zzal.zzaz[i - 1]) {
                case 1:
                    return new zzc();
                case 2:
                    return new zza();
                case 3:
                    Object[] objArr = new Object[]{"zzbd", "zzbl", "zzbm", zzb.class};
                    return zzdj.zza((zzeq) zzbn, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0001\u0000\u0001\b\u0000\u0002\u001b", objArr);
                case 4:
                    return zzbn;
                case 5:
                    Object obj3 = zzbc;
                    if (obj3 == null) {
                        synchronized (zzc.class) {
                            obj3 = zzbc;
                            if (obj3 == null) {
                                obj3 = new com.google.android.gms.internal.icing.zzdj.zzb(zzbn);
                                zzbc = obj3;
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
            zzdj.zza(zzc.class, zzbn);
        }
    }

    public static final class zzd extends zzdj<zzd, zza> implements zzes {
        private static volatile zzfa<zzd> zzbc;
        private static final zzd zzbt = new zzd();
        private int zzbd;
        private boolean zzbo;
        private String zzbp = "";
        private long zzbq;
        private double zzbr;
        private zzc zzbs;

        public static final class zza extends com.google.android.gms.internal.icing.zzdj.zza<zzd, zza> implements zzes {
            private zza() {
                super(zzd.zzbt);
            }

            public final zza zzf(boolean z) {
                zzbw();
                ((zzd) this.zzjx).zze(z);
                return this;
            }

            public final zza zzm(String str) {
                zzbw();
                ((zzd) this.zzjx).zzl(str);
                return this;
            }

            public final zza zzb(zzc zzc) {
                zzbw();
                ((zzd) this.zzjx).zza(zzc);
                return this;
            }

            /* synthetic */ zza(zzal zzal) {
                this();
            }
        }

        private zzd() {
        }

        private final void zze(boolean z) {
            this.zzbd |= 1;
            this.zzbo = z;
        }

        private final void zzl(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.zzbd |= 2;
            this.zzbp = str;
        }

        private final void zza(zzc zzc) {
            if (zzc == null) {
                throw new NullPointerException();
            }
            this.zzbs = zzc;
            this.zzbd |= 16;
        }

        public static zza zzn() {
            return (zza) ((com.google.android.gms.internal.icing.zzdj.zza) zzbt.zza(com.google.android.gms.internal.icing.zzdj.zzd.zzke, null, null));
        }

        /* Access modifiers changed, original: protected|final */
        public final Object zza(int i, Object obj, Object obj2) {
            switch (zzal.zzaz[i - 1]) {
                case 1:
                    return new zzd();
                case 2:
                    return new zza();
                case 3:
                    Object[] objArr = new Object[]{"zzbd", "zzbo", "zzbp", "zzbq", "zzbr", "zzbs"};
                    return zzdj.zza((zzeq) zzbt, "\u0001\u0005\u0000\u0001\u0001\u0005\u0005\u0000\u0000\u0000\u0001\u0007\u0000\u0002\b\u0001\u0003\u0002\u0002\u0004\u0000\u0003\u0005\t\u0004", objArr);
                case 4:
                    return zzbt;
                case 5:
                    Object obj3 = zzbc;
                    if (obj3 == null) {
                        synchronized (zzd.class) {
                            obj3 = zzbc;
                            if (obj3 == null) {
                                obj3 = new com.google.android.gms.internal.icing.zzdj.zzb(zzbt);
                                zzbc = obj3;
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
            zzdj.zza(zzd.class, zzbt);
        }
    }
}
