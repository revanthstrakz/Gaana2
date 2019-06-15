package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzbrd.zze;

public final class zzbng extends zzbrd<zzbng, zza> implements zzbsn {
    private static volatile zzbsw<zzbng> zzcas;
    private static final zzbng zzfhq = new zzbng();
    private int zzccg;
    private int zzfhi;
    private zzbrk<zzb> zzfhp = zzbrd.zzams();

    public static final class zza extends com.google.android.gms.internal.ads.zzbrd.zza<zzbng, zza> implements zzbsn {
        private zza() {
            super(zzbng.zzfhq);
        }

        public final zza zzea(int i) {
            zzamw();
            ((zzbng) this.zzfpy).zzdz(i);
            return this;
        }

        public final zza zzb(zzb zzb) {
            zzamw();
            ((zzbng) this.zzfpy).zza(zzb);
            return this;
        }

        /* synthetic */ zza(zzbnh zzbnh) {
            this();
        }
    }

    public static final class zzb extends zzbrd<zzb, zza> implements zzbsn {
        private static volatile zzbsw<zzb> zzcas;
        private static final zzb zzfhr = new zzb();
        private String zzfgk = "";
        private int zzfhb;
        private int zzfhm;
        private int zzfhn;

        public static final class zza extends com.google.android.gms.internal.ads.zzbrd.zza<zzb, zza> implements zzbsn {
            private zza() {
                super(zzb.zzfhr);
            }

            public final zza zzfr(String str) {
                zzamw();
                ((zzb) this.zzfpy).zzfk(str);
                return this;
            }

            public final zza zzb(zzbmy zzbmy) {
                zzamw();
                ((zzb) this.zzfpy).zza(zzbmy);
                return this;
            }

            public final zza zzec(int i) {
                zzamw();
                ((zzb) this.zzfpy).zzeb(i);
                return this;
            }

            public final zza zzb(zzbnq zzbnq) {
                zzamw();
                ((zzb) this.zzfpy).zza(zzbnq);
                return this;
            }

            /* synthetic */ zza(zzbnh zzbnh) {
                this();
            }
        }

        private zzb() {
        }

        private final void zzfk(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.zzfgk = str;
        }

        private final void zza(zzbmy zzbmy) {
            if (zzbmy == null) {
                throw new NullPointerException();
            }
            this.zzfhm = zzbmy.zzom();
        }

        private final void zzeb(int i) {
            this.zzfhn = i;
        }

        private final void zza(zzbnq zzbnq) {
            if (zzbnq == null) {
                throw new NullPointerException();
            }
            this.zzfhb = zzbnq.zzom();
        }

        public static zza zzajg() {
            return (zza) ((com.google.android.gms.internal.ads.zzbrd.zza) zzfhr.zza(zze.zzfqf, null, null));
        }

        /* Access modifiers changed, original: protected|final */
        public final Object zza(int i, Object obj, Object obj2) {
            switch (zzbnh.zzcaq[i - 1]) {
                case 1:
                    return new zzb();
                case 2:
                    return new zza();
                case 3:
                    Object[] objArr = new Object[]{"zzfgk", "zzfhm", "zzfhn", "zzfhb"};
                    return zzbrd.zza((zzbsl) zzfhr, "\u0000\u0004\u0000\u0000\u0001\u0004\u0004\u0000\u0000\u0000\u0001Ȉ\u0002\f\u0003\u000b\u0004\f", objArr);
                case 4:
                    return zzfhr;
                case 5:
                    Object obj3 = zzcas;
                    if (obj3 == null) {
                        synchronized (zzb.class) {
                            obj3 = zzcas;
                            if (obj3 == null) {
                                obj3 = new com.google.android.gms.internal.ads.zzbrd.zzb(zzfhr);
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
            zzbrd.zza(zzb.class, zzfhr);
        }
    }

    private zzbng() {
    }

    private final void zzdz(int i) {
        this.zzfhi = i;
    }

    private final void zza(zzb zzb) {
        if (zzb == null) {
            throw new NullPointerException();
        }
        if (!this.zzfhp.zzaki()) {
            zzbrk zzbrk = this.zzfhp;
            int size = zzbrk.size();
            this.zzfhp = zzbrk.zzel(size == 0 ? 10 : size << 1);
        }
        this.zzfhp.add(zzb);
    }

    public static zza zzaje() {
        return (zza) ((com.google.android.gms.internal.ads.zzbrd.zza) zzfhq.zza(zze.zzfqf, null, null));
    }

    /* Access modifiers changed, original: protected|final */
    public final Object zza(int i, Object obj, Object obj2) {
        switch (zzbnh.zzcaq[i - 1]) {
            case 1:
                return new zzbng();
            case 2:
                return new zza();
            case 3:
                Object[] objArr = new Object[]{"zzccg", "zzfhi", "zzfhp", zzb.class};
                return zzbrd.zza((zzbsl) zzfhq, "\u0000\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0001\u0000\u0001\u000b\u0002\u001b", objArr);
            case 4:
                return zzfhq;
            case 5:
                Object obj3 = zzcas;
                if (obj3 == null) {
                    synchronized (zzbng.class) {
                        obj3 = zzcas;
                        if (obj3 == null) {
                            obj3 = new com.google.android.gms.internal.ads.zzbrd.zzb(zzfhq);
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
        zzbrd.zza(zzbng.class, zzfhq);
    }
}
