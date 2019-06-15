package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzbrd.zzb;
import com.google.android.gms.internal.ads.zzbrd.zze;

public final class zzbmp extends zzbrd<zzbmp, zza> implements zzbsn {
    private static volatile zzbsw<zzbmp> zzcas;
    private static final zzbmp zzfgf = new zzbmp();
    private int zzfea;
    private zzbpu zzfei = zzbpu.zzfli;
    private zzbmt zzfge;

    public static final class zza extends com.google.android.gms.internal.ads.zzbrd.zza<zzbmp, zza> implements zzbsn {
        private zza() {
            super(zzbmp.zzfgf);
        }

        public final zza zzdu(int i) {
            zzamw();
            ((zzbmp) this.zzfpy).setVersion(0);
            return this;
        }

        public final zza zzc(zzbmt zzbmt) {
            zzamw();
            ((zzbmp) this.zzfpy).zzb(zzbmt);
            return this;
        }

        public final zza zzaf(zzbpu zzbpu) {
            zzamw();
            ((zzbmp) this.zzfpy).zzk(zzbpu);
            return this;
        }

        /* synthetic */ zza(zzbmq zzbmq) {
            this();
        }
    }

    private zzbmp() {
    }

    public final int getVersion() {
        return this.zzfea;
    }

    private final void setVersion(int i) {
        this.zzfea = i;
    }

    public final zzbmt zzahw() {
        return this.zzfge == null ? zzbmt.zzaie() : this.zzfge;
    }

    private final void zzb(zzbmt zzbmt) {
        if (zzbmt == null) {
            throw new NullPointerException();
        }
        this.zzfge = zzbmt;
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

    public static zzbmp zzae(zzbpu zzbpu) throws zzbrl {
        return (zzbmp) zzbrd.zza(zzfgf, zzbpu);
    }

    public static zza zzahx() {
        return (zza) ((com.google.android.gms.internal.ads.zzbrd.zza) zzfgf.zza(zze.zzfqf, null, null));
    }

    /* Access modifiers changed, original: protected|final */
    public final Object zza(int i, Object obj, Object obj2) {
        switch (zzbmq.zzcaq[i - 1]) {
            case 1:
                return new zzbmp();
            case 2:
                return new zza();
            case 3:
                Object[] objArr = new Object[]{"zzfea", "zzfge", "zzfei"};
                return zzbrd.zza((zzbsl) zzfgf, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001\u000b\u0002\t\u0003\n", objArr);
            case 4:
                return zzfgf;
            case 5:
                Object obj3 = zzcas;
                if (obj3 == null) {
                    synchronized (zzbmp.class) {
                        obj3 = zzcas;
                        if (obj3 == null) {
                            obj3 = new zzb(zzfgf);
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

    public static zzbmp zzahy() {
        return zzfgf;
    }

    static {
        zzbrd.zza(zzbmp.class, zzfgf);
    }
}
