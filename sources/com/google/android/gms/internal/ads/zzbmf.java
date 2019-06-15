package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzbrd.zzb;
import com.google.android.gms.internal.ads.zzbrd.zze;

public final class zzbmf extends zzbrd<zzbmf, zza> implements zzbsn {
    private static volatile zzbsw<zzbmf> zzcas;
    private static final zzbmf zzffk = new zzbmf();
    private int zzfea;
    private zzbpu zzfei = zzbpu.zzfli;
    private zzbmh zzffj;

    public static final class zza extends com.google.android.gms.internal.ads.zzbrd.zza<zzbmf, zza> implements zzbsn {
        private zza() {
            super(zzbmf.zzffk);
        }

        public final zza zzdq(int i) {
            zzamw();
            ((zzbmf) this.zzfpy).setVersion(0);
            return this;
        }

        public final zza zzb(zzbmh zzbmh) {
            zzamw();
            ((zzbmf) this.zzfpy).zza(zzbmh);
            return this;
        }

        public final zza zzy(zzbpu zzbpu) {
            zzamw();
            ((zzbmf) this.zzfpy).zzk(zzbpu);
            return this;
        }

        /* synthetic */ zza(zzbmg zzbmg) {
            this();
        }
    }

    private zzbmf() {
    }

    public final int getVersion() {
        return this.zzfea;
    }

    private final void setVersion(int i) {
        this.zzfea = i;
    }

    public final zzbmh zzahj() {
        return this.zzffj == null ? zzbmh.zzahp() : this.zzffj;
    }

    private final void zza(zzbmh zzbmh) {
        if (zzbmh == null) {
            throw new NullPointerException();
        }
        this.zzffj = zzbmh;
    }

    public final zzbpu zzagf() {
        return this.zzfei;
    }

    private final void zzk(zzbpu zzbpu) {
        if (zzbpu == null) {
            throw new NullPointerException();
        }
        this.zzfei = zzbpu;
    }

    public static zzbmf zzx(zzbpu zzbpu) throws zzbrl {
        return (zzbmf) zzbrd.zza(zzffk, zzbpu);
    }

    public static zza zzahk() {
        return (zza) ((com.google.android.gms.internal.ads.zzbrd.zza) zzffk.zza(zze.zzfqf, null, null));
    }

    /* Access modifiers changed, original: protected|final */
    public final Object zza(int i, Object obj, Object obj2) {
        switch (zzbmg.zzcaq[i - 1]) {
            case 1:
                return new zzbmf();
            case 2:
                return new zza();
            case 3:
                Object[] objArr = new Object[]{"zzfea", "zzffj", "zzfei"};
                return zzbrd.zza((zzbsl) zzffk, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001\u000b\u0002\t\u0003\n", objArr);
            case 4:
                return zzffk;
            case 5:
                Object obj3 = zzcas;
                if (obj3 == null) {
                    synchronized (zzbmf.class) {
                        obj3 = zzcas;
                        if (obj3 == null) {
                            obj3 = new zzb(zzffk);
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
        zzbrd.zza(zzbmf.class, zzffk);
    }
}
