package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzbrd.zzb;
import com.google.android.gms.internal.ads.zzbrd.zze;

public final class zzbnc extends zzbrd<zzbnc, zza> implements zzbsn {
    private static volatile zzbsw<zzbnc> zzcas;
    private static final zzbnc zzfhh = new zzbnc();
    private String zzfgk = "";
    private String zzfhd = "";
    private int zzfhe;
    private boolean zzfhf;
    private String zzfhg = "";

    public static final class zza extends com.google.android.gms.internal.ads.zzbrd.zza<zzbnc, zza> implements zzbsn {
        private zza() {
            super(zzbnc.zzfhh);
        }

        public final zza zzfo(String str) {
            zzamw();
            ((zzbnc) this.zzfpy).zzfm(str);
            return this;
        }

        public final zza zzfp(String str) {
            zzamw();
            ((zzbnc) this.zzfpy).zzfk(str);
            return this;
        }

        public final zza zzdy(int i) {
            zzamw();
            ((zzbnc) this.zzfpy).zzdx(0);
            return this;
        }

        public final zza zzbc(boolean z) {
            zzamw();
            ((zzbnc) this.zzfpy).zzbb(true);
            return this;
        }

        public final zza zzfq(String str) {
            zzamw();
            ((zzbnc) this.zzfpy).zzfn(str);
            return this;
        }

        /* synthetic */ zza(zzbnd zzbnd) {
            this();
        }
    }

    private zzbnc() {
    }

    public final String zzaio() {
        return this.zzfhd;
    }

    private final void zzfm(String str) {
        if (str == null) {
            throw new NullPointerException();
        }
        this.zzfhd = str;
    }

    public final String zzaig() {
        return this.zzfgk;
    }

    private final void zzfk(String str) {
        if (str == null) {
            throw new NullPointerException();
        }
        this.zzfgk = str;
    }

    public final int zzaip() {
        return this.zzfhe;
    }

    private final void zzdx(int i) {
        this.zzfhe = i;
    }

    public final boolean zzaiq() {
        return this.zzfhf;
    }

    private final void zzbb(boolean z) {
        this.zzfhf = z;
    }

    public final String zzair() {
        return this.zzfhg;
    }

    private final void zzfn(String str) {
        if (str == null) {
            throw new NullPointerException();
        }
        this.zzfhg = str;
    }

    public static zza zzais() {
        return (zza) ((com.google.android.gms.internal.ads.zzbrd.zza) zzfhh.zza(zze.zzfqf, null, null));
    }

    /* Access modifiers changed, original: protected|final */
    public final Object zza(int i, Object obj, Object obj2) {
        switch (zzbnd.zzcaq[i - 1]) {
            case 1:
                return new zzbnc();
            case 2:
                return new zza();
            case 3:
                Object[] objArr = new Object[]{"zzfhd", "zzfgk", "zzfhe", "zzfhf", "zzfhg"};
                return zzbrd.zza((zzbsl) zzfhh, "\u0000\u0005\u0000\u0000\u0001\u0005\u0005\u0000\u0000\u0000\u0001Ȉ\u0002Ȉ\u0003\u000b\u0004\u0007\u0005Ȉ", objArr);
            case 4:
                return zzfhh;
            case 5:
                Object obj3 = zzcas;
                if (obj3 == null) {
                    synchronized (zzbnc.class) {
                        obj3 = zzcas;
                        if (obj3 == null) {
                            obj3 = new zzb(zzfhh);
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
        zzbrd.zza(zzbnc.class, zzfhh);
    }
}
