package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzbrd.zzb;
import com.google.android.gms.internal.ads.zzbrd.zze;
import java.util.List;

public final class zzbns extends zzbrd<zzbns, zza> implements zzbsn {
    private static volatile zzbsw<zzbns> zzcas;
    private static final zzbns zzfik = new zzbns();
    private int zzccg;
    private String zzfii = "";
    private zzbrk<zzbnc> zzfij = zzbrd.zzams();

    public static final class zza extends com.google.android.gms.internal.ads.zzbrd.zza<zzbns, zza> implements zzbsn {
        private zza() {
            super(zzbns.zzfik);
        }

        public final zza zzft(String str) {
            zzamw();
            ((zzbns) this.zzfpy).zzfs(str);
            return this;
        }

        public final zza zzb(zzbnc zzbnc) {
            zzamw();
            ((zzbns) this.zzfpy).zza(zzbnc);
            return this;
        }

        /* synthetic */ zza(zzbnt zzbnt) {
            this();
        }
    }

    private zzbns() {
    }

    private final void zzfs(String str) {
        if (str == null) {
            throw new NullPointerException();
        }
        this.zzfii = str;
    }

    public final List<zzbnc> zzajv() {
        return this.zzfij;
    }

    private final void zza(zzbnc zzbnc) {
        if (zzbnc == null) {
            throw new NullPointerException();
        }
        if (!this.zzfij.zzaki()) {
            zzbrk zzbrk = this.zzfij;
            int size = zzbrk.size();
            this.zzfij = zzbrk.zzel(size == 0 ? 10 : size << 1);
        }
        this.zzfij.add(zzbnc);
    }

    public static zza zzajw() {
        return (zza) ((com.google.android.gms.internal.ads.zzbrd.zza) zzfik.zza(zze.zzfqf, null, null));
    }

    /* Access modifiers changed, original: protected|final */
    public final Object zza(int i, Object obj, Object obj2) {
        switch (zzbnt.zzcaq[i - 1]) {
            case 1:
                return new zzbns();
            case 2:
                return new zza();
            case 3:
                Object[] objArr = new Object[]{"zzccg", "zzfii", "zzfij", zzbnc.class};
                return zzbrd.zza((zzbsl) zzfik, "\u0000\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0001\u0000\u0001Ȉ\u0002\u001b", objArr);
            case 4:
                return zzfik;
            case 5:
                Object obj3 = zzcas;
                if (obj3 == null) {
                    synchronized (zzbns.class) {
                        obj3 = zzcas;
                        if (obj3 == null) {
                            obj3 = new zzb(zzfik);
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
        zzbrd.zza(zzbns.class, zzfik);
    }
}
