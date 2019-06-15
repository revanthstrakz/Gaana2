package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzbrd.zzb;
import com.google.android.gms.internal.ads.zzbrd.zze;

public final class zzbnm extends zzbrd<zzbnm, zza> implements zzbsn {
    private static volatile zzbsw<zzbnm> zzcas;
    private static final zzbnm zzfhx = new zzbnm();
    private int zzfea;
    private zzbno zzfhw;

    public static final class zza extends com.google.android.gms.internal.ads.zzbrd.zza<zzbnm, zza> implements zzbsn {
        private zza() {
            super(zzbnm.zzfhx);
        }

        public final zza zzee(int i) {
            zzamw();
            ((zzbnm) this.zzfpy).setVersion(0);
            return this;
        }

        public final zza zzb(zzbno zzbno) {
            zzamw();
            ((zzbnm) this.zzfpy).zza(zzbno);
            return this;
        }

        /* synthetic */ zza(zzbnn zzbnn) {
            this();
        }
    }

    private zzbnm() {
    }

    public final int getVersion() {
        return this.zzfea;
    }

    private final void setVersion(int i) {
        this.zzfea = i;
    }

    public final zzbno zzajo() {
        return this.zzfhw == null ? zzbno.zzajt() : this.zzfhw;
    }

    private final void zza(zzbno zzbno) {
        if (zzbno == null) {
            throw new NullPointerException();
        }
        this.zzfhw = zzbno;
    }

    public static zzbnm zzal(zzbpu zzbpu) throws zzbrl {
        return (zzbnm) zzbrd.zza(zzfhx, zzbpu);
    }

    public static zza zzajp() {
        return (zza) ((com.google.android.gms.internal.ads.zzbrd.zza) zzfhx.zza(zze.zzfqf, null, null));
    }

    /* Access modifiers changed, original: protected|final */
    public final Object zza(int i, Object obj, Object obj2) {
        switch (zzbnn.zzcaq[i - 1]) {
            case 1:
                return new zzbnm();
            case 2:
                return new zza();
            case 3:
                Object[] objArr = new Object[]{"zzfea", "zzfhw"};
                return zzbrd.zza((zzbsl) zzfhx, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\u000b\u0002\t", objArr);
            case 4:
                return zzfhx;
            case 5:
                Object obj3 = zzcas;
                if (obj3 == null) {
                    synchronized (zzbnm.class) {
                        obj3 = zzcas;
                        if (obj3 == null) {
                            obj3 = new zzb(zzfhx);
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
        zzbrd.zza(zzbnm.class, zzfhx);
    }
}
