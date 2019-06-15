package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzbrd.zzb;
import com.google.android.gms.internal.ads.zzbrd.zze;

public final class zzblf extends zzbrd<zzblf, zza> implements zzbsn {
    private static volatile zzbsw<zzblf> zzcas;
    private static final zzblf zzfej = new zzblf();
    private int zzfea;
    private zzblj zzfeh;
    private zzbpu zzfei = zzbpu.zzfli;

    public static final class zza extends com.google.android.gms.internal.ads.zzbrd.zza<zzblf, zza> implements zzbsn {
        private zza() {
            super(zzblf.zzfej);
        }

        public final zza zzdl(int i) {
            zzamw();
            ((zzblf) this.zzfpy).setVersion(0);
            return this;
        }

        public final zza zzc(zzblj zzblj) {
            zzamw();
            ((zzblf) this.zzfpy).zzb(zzblj);
            return this;
        }

        public final zza zzm(zzbpu zzbpu) {
            zzamw();
            ((zzblf) this.zzfpy).zzk(zzbpu);
            return this;
        }

        /* synthetic */ zza(zzblg zzblg) {
            this();
        }
    }

    private zzblf() {
    }

    public final int getVersion() {
        return this.zzfea;
    }

    private final void setVersion(int i) {
        this.zzfea = i;
    }

    public final zzblj zzage() {
        return this.zzfeh == null ? zzblj.zzagm() : this.zzfeh;
    }

    private final void zzb(zzblj zzblj) {
        if (zzblj == null) {
            throw new NullPointerException();
        }
        this.zzfeh = zzblj;
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

    public static zzblf zzl(zzbpu zzbpu) throws zzbrl {
        return (zzblf) zzbrd.zza(zzfej, zzbpu);
    }

    public static zza zzagg() {
        return (zza) ((com.google.android.gms.internal.ads.zzbrd.zza) zzfej.zza(zze.zzfqf, null, null));
    }

    /* Access modifiers changed, original: protected|final */
    public final Object zza(int i, Object obj, Object obj2) {
        switch (zzblg.zzcaq[i - 1]) {
            case 1:
                return new zzblf();
            case 2:
                return new zza();
            case 3:
                Object[] objArr = new Object[]{"zzfea", "zzfeh", "zzfei"};
                return zzbrd.zza((zzbsl) zzfej, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001\u000b\u0002\t\u0003\n", objArr);
            case 4:
                return zzfej;
            case 5:
                Object obj3 = zzcas;
                if (obj3 == null) {
                    synchronized (zzblf.class) {
                        obj3 = zzcas;
                        if (obj3 == null) {
                            obj3 = new zzb(zzfej);
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

    public static zzblf zzagh() {
        return zzfej;
    }

    static {
        zzbrd.zza(zzblf.class, zzfej);
    }
}
