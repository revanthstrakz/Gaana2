package com.google.android.gms.internal.measurement;

import com.google.android.gms.internal.measurement.zzuo.zze;

public final class zzc {

    public static final class zza extends zzuo<zza, zza> implements zzvx {
        private static final zza zznv = new zza();
        private static volatile zzwf<zza> zznw;
        private int zznr;
        private int zzns = 1;
        private int zznt;
        private int zznu;

        public enum zzb implements zzur {
            NO_CACHE(1),
            PRIVATE(2),
            PUBLIC(3);
            
            private static final zzus<zzb> zzoa = null;
            private final int value;

            public final int zzc() {
                return this.value;
            }

            public static zzb zza(int i) {
                switch (i) {
                    case 1:
                        return NO_CACHE;
                    case 2:
                        return PRIVATE;
                    case 3:
                        return PUBLIC;
                    default:
                        return null;
                }
            }

            public static zzut zzd() {
                return zzf.zzoc;
            }

            private zzb(int i) {
                this.value = i;
            }

            static {
                zzoa = new zze();
            }
        }

        public static final class zza extends com.google.android.gms.internal.measurement.zzuo.zza<zza, zza> implements zzvx {
            private zza() {
                super(zza.zznv);
            }

            /* synthetic */ zza(zzd zzd) {
                this();
            }
        }

        private zza() {
        }

        /* Access modifiers changed, original: protected|final */
        public final Object zza(int i, Object obj, Object obj2) {
            switch (zzd.zznq[i - 1]) {
                case 1:
                    return new zza();
                case 2:
                    return new zza();
                case 3:
                    Object[] objArr = new Object[]{"zznr", "zzns", zzb.zzd(), "zznt", "zznu"};
                    return zzuo.zza((zzvv) zznv, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001\f\u0000\u0002\u0004\u0001\u0003\u0004\u0002", objArr);
                case 4:
                    return zznv;
                case 5:
                    Object obj3 = zznw;
                    if (obj3 == null) {
                        synchronized (zza.class) {
                            obj3 = zznw;
                            if (obj3 == null) {
                                obj3 = new com.google.android.gms.internal.measurement.zzuo.zzb(zznv);
                                zznw = obj3;
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

        public static zzwf<zza> zza() {
            return (zzwf) zznv.zza(zze.zzbyq, null, null);
        }

        static {
            zzuo.zza(zza.class, zznv);
        }
    }
}
