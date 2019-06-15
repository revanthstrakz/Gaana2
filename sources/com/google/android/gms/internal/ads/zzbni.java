package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzbrd.zzb;
import com.google.android.gms.internal.ads.zzbrd.zze;

public final class zzbni extends zzbrd<zzbni, zza> implements zzbsn {
    private static volatile zzbsw<zzbni> zzcas;
    private static final zzbni zzfht = new zzbni();
    private int zzfea;
    private zzbnk zzfhs;

    public static final class zza extends com.google.android.gms.internal.ads.zzbrd.zza<zzbni, zza> implements zzbsn {
        private zza() {
            super(zzbni.zzfht);
        }

        public final zza zzed(int i) {
            zzamw();
            ((zzbni) this.zzfpy).setVersion(0);
            return this;
        }

        public final zza zzb(zzbnk zzbnk) {
            zzamw();
            ((zzbni) this.zzfpy).zza(zzbnk);
            return this;
        }

        /* synthetic */ zza(zzbnj zzbnj) {
            this();
        }
    }

    private zzbni() {
    }

    public final int getVersion() {
        return this.zzfea;
    }

    private final void setVersion(int i) {
        this.zzfea = i;
    }

    public final zzbnk zzaji() {
        return this.zzfhs == null ? zzbnk.zzajm() : this.zzfhs;
    }

    private final void zza(zzbnk zzbnk) {
        if (zzbnk == null) {
            throw new NullPointerException();
        }
        this.zzfhs = zzbnk;
    }

    public static zzbni zzaj(zzbpu zzbpu) throws zzbrl {
        return (zzbni) zzbrd.zza(zzfht, zzbpu);
    }

    public static zza zzajj() {
        return (zza) ((com.google.android.gms.internal.ads.zzbrd.zza) zzfht.zza(zze.zzfqf, null, null));
    }

    /* Access modifiers changed, original: protected|final */
    public final Object zza(int i, Object obj, Object obj2) {
        switch (zzbnj.zzcaq[i - 1]) {
            case 1:
                return new zzbni();
            case 2:
                return new zza();
            case 3:
                Object[] objArr = new Object[]{"zzfea", "zzfhs"};
                return zzbrd.zza((zzbsl) zzfht, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\u000b\u0002\t", objArr);
            case 4:
                return zzfht;
            case 5:
                Object obj3 = zzcas;
                if (obj3 == null) {
                    synchronized (zzbni.class) {
                        obj3 = zzcas;
                        if (obj3 == null) {
                            obj3 = new zzb(zzfht);
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
        zzbrd.zza(zzbni.class, zzfht);
    }
}
