package com.google.android.gms.internal.vision;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.List;

final class zzel extends zzej {
    private final byte[] buffer;
    private int limit;
    private int pos;
    private int tag;
    private final boolean zzrs = true;
    private final int zzrt;
    private int zzru;

    public zzel(ByteBuffer byteBuffer, boolean z) {
        super();
        this.buffer = byteBuffer.array();
        int arrayOffset = byteBuffer.arrayOffset() + byteBuffer.position();
        this.pos = arrayOffset;
        this.zzrt = arrayOffset;
        this.limit = byteBuffer.arrayOffset() + byteBuffer.limit();
    }

    private final boolean zzcm() {
        return this.pos == this.limit;
    }

    public final int zzcn() throws IOException {
        if (zzcm()) {
            return Integer.MAX_VALUE;
        }
        this.tag = zzdd();
        if (this.tag == this.zzru) {
            return Integer.MAX_VALUE;
        }
        return this.tag >>> 3;
    }

    public final int getTag() {
        return this.tag;
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x0045  */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0040  */
    public final boolean zzco() throws java.io.IOException {
        /*
        r7 = this;
        r0 = r7.zzcm();
        r1 = 0;
        if (r0 != 0) goto L_0x0089;
    L_0x0007:
        r0 = r7.tag;
        r2 = r7.zzru;
        if (r0 != r2) goto L_0x000f;
    L_0x000d:
        goto L_0x0089;
    L_0x000f:
        r0 = r7.tag;
        r0 = r0 & 7;
        r2 = 5;
        r3 = 4;
        r4 = 1;
        if (r0 == r2) goto L_0x0085;
    L_0x0018:
        switch(r0) {
            case 0: goto L_0x0056;
            case 1: goto L_0x0050;
            case 2: goto L_0x0048;
            case 3: goto L_0x0020;
            default: goto L_0x001b;
        };
    L_0x001b:
        r0 = com.google.android.gms.internal.vision.zzgf.zzfm();
        throw r0;
    L_0x0020:
        r0 = r7.zzru;
        r1 = r7.tag;
        r1 = r1 >>> 3;
        r1 = r1 << 3;
        r1 = r1 | r3;
        r7.zzru = r1;
    L_0x002b:
        r1 = r7.zzcn();
        r2 = 2147483647; // 0x7fffffff float:NaN double:1.060997895E-314;
        if (r1 == r2) goto L_0x003a;
    L_0x0034:
        r1 = r7.zzco();
        if (r1 != 0) goto L_0x002b;
    L_0x003a:
        r1 = r7.tag;
        r2 = r7.zzru;
        if (r1 == r2) goto L_0x0045;
    L_0x0040:
        r0 = com.google.android.gms.internal.vision.zzgf.zzfo();
        throw r0;
    L_0x0045:
        r7.zzru = r0;
        return r4;
    L_0x0048:
        r0 = r7.zzdd();
        r7.zzz(r0);
        return r4;
    L_0x0050:
        r0 = 8;
        r7.zzz(r0);
        return r4;
    L_0x0056:
        r0 = r7.limit;
        r2 = r7.pos;
        r0 = r0 - r2;
        r2 = 10;
        if (r0 < r2) goto L_0x0074;
    L_0x005f:
        r0 = r7.buffer;
        r3 = r7.pos;
        r5 = r3;
        r3 = r1;
    L_0x0065:
        if (r3 >= r2) goto L_0x0074;
    L_0x0067:
        r6 = r5 + 1;
        r5 = r0[r5];
        if (r5 < 0) goto L_0x0070;
    L_0x006d:
        r7.pos = r6;
        goto L_0x007f;
    L_0x0070:
        r3 = r3 + 1;
        r5 = r6;
        goto L_0x0065;
    L_0x0074:
        if (r1 >= r2) goto L_0x0080;
    L_0x0076:
        r0 = r7.readByte();
        if (r0 >= 0) goto L_0x007f;
    L_0x007c:
        r1 = r1 + 1;
        goto L_0x0074;
    L_0x007f:
        return r4;
    L_0x0080:
        r0 = com.google.android.gms.internal.vision.zzgf.zzfj();
        throw r0;
    L_0x0085:
        r7.zzz(r3);
        return r4;
    L_0x0089:
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.vision.zzel.zzco():boolean");
    }

    public final double readDouble() throws IOException {
        zzab(1);
        return Double.longBitsToDouble(zzdh());
    }

    public final float readFloat() throws IOException {
        zzab(5);
        return Float.intBitsToFloat(zzdg());
    }

    public final long zzcp() throws IOException {
        zzab(0);
        return zzde();
    }

    public final long zzcq() throws IOException {
        zzab(0);
        return zzde();
    }

    public final int zzcr() throws IOException {
        zzab(0);
        return zzdd();
    }

    public final long zzcs() throws IOException {
        zzab(1);
        return zzdh();
    }

    public final int zzct() throws IOException {
        zzab(5);
        return zzdg();
    }

    public final boolean zzcu() throws IOException {
        zzab(0);
        if (zzdd() != 0) {
            return true;
        }
        return false;
    }

    public final String readString() throws IOException {
        return zzg(false);
    }

    public final String zzcv() throws IOException {
        return zzg(true);
    }

    private final String zzg(boolean z) throws IOException {
        zzab(2);
        int zzdd = zzdd();
        if (zzdd == 0) {
            return "";
        }
        zzaa(zzdd);
        if (!z || zziw.zzg(this.buffer, this.pos, this.pos + zzdd)) {
            String str = new String(this.buffer, this.pos, zzdd, zzga.UTF_8);
            this.pos += zzdd;
            return str;
        }
        throw zzgf.zzfp();
    }

    public final <T> T zza(Class<T> cls, zzfk zzfk) throws IOException {
        zzab(2);
        return zzb(zzhs.zzgl().zzf(cls), zzfk);
    }

    public final <T> T zza(zzhw<T> zzhw, zzfk zzfk) throws IOException {
        zzab(2);
        return zzb((zzhw) zzhw, zzfk);
    }

    private final <T> T zzb(zzhw<T> zzhw, zzfk zzfk) throws IOException {
        T zzdd = zzdd();
        zzaa(zzdd);
        int i = this.limit;
        int i2 = this.pos + zzdd;
        this.limit = i2;
        try {
            zzdd = zzhw.newInstance();
            zzhw.zza(zzdd, this, zzfk);
            zzhw.zze(zzdd);
            if (this.pos == i2) {
                return zzdd;
            }
            throw zzgf.zzfo();
        } finally {
            this.limit = i;
        }
    }

    public final <T> T zzb(Class<T> cls, zzfk zzfk) throws IOException {
        zzab(3);
        return zzd(zzhs.zzgl().zzf(cls), zzfk);
    }

    public final <T> T zzc(zzhw<T> zzhw, zzfk zzfk) throws IOException {
        zzab(3);
        return zzd(zzhw, zzfk);
    }

    private final <T> T zzd(zzhw<T> zzhw, zzfk zzfk) throws IOException {
        int i = this.zzru;
        T t = ((this.tag >>> 3) << 3) | 4;
        this.zzru = t;
        try {
            t = zzhw.newInstance();
            zzhw.zza(t, this, zzfk);
            zzhw.zze(t);
            if (this.tag == this.zzru) {
                return t;
            }
            throw zzgf.zzfo();
        } finally {
            this.zzru = i;
        }
    }

    public final zzeo zzcw() throws IOException {
        zzab(2);
        int zzdd = zzdd();
        if (zzdd == 0) {
            return zzeo.zzrx;
        }
        zzeo zzc;
        zzaa(zzdd);
        if (this.zzrs) {
            zzc = zzeo.zzc(this.buffer, this.pos, zzdd);
        } else {
            zzc = zzeo.zzb(this.buffer, this.pos, zzdd);
        }
        this.pos += zzdd;
        return zzc;
    }

    public final int zzcx() throws IOException {
        zzab(0);
        return zzdd();
    }

    public final int zzcy() throws IOException {
        zzab(0);
        return zzdd();
    }

    public final int zzcz() throws IOException {
        zzab(5);
        return zzdg();
    }

    public final long zzda() throws IOException {
        zzab(1);
        return zzdh();
    }

    public final int zzdb() throws IOException {
        zzab(0);
        return zzez.zzaq(zzdd());
    }

    public final long zzdc() throws IOException {
        zzab(0);
        return zzez.zzd(zzde());
    }

    public final void zza(List<Double> list) throws IOException {
        int zzdd;
        int i;
        if (list instanceof zzfh) {
            zzfh zzfh = (zzfh) list;
            switch (this.tag & 7) {
                case 1:
                    break;
                case 2:
                    zzdd = zzdd();
                    zzac(zzdd);
                    i = this.pos + zzdd;
                    while (this.pos < i) {
                        zzfh.zzc(Double.longBitsToDouble(zzdj()));
                    }
                    return;
                default:
                    throw zzgf.zzfm();
            }
            do {
                zzfh.zzc(readDouble());
                if (!zzcm()) {
                    zzdd = this.pos;
                } else {
                    return;
                }
            } while (zzdd() == this.tag);
            this.pos = zzdd;
            return;
        }
        switch (this.tag & 7) {
            case 1:
                break;
            case 2:
                zzdd = zzdd();
                zzac(zzdd);
                i = this.pos + zzdd;
                while (this.pos < i) {
                    list.add(Double.valueOf(Double.longBitsToDouble(zzdj())));
                }
                return;
            default:
                throw zzgf.zzfm();
        }
        do {
            list.add(Double.valueOf(readDouble()));
            if (!zzcm()) {
                zzdd = this.pos;
            } else {
                return;
            }
        } while (zzdd() == this.tag);
        this.pos = zzdd;
    }

    public final void zzb(List<Float> list) throws IOException {
        int i;
        int i2;
        if (list instanceof zzfv) {
            zzfv zzfv = (zzfv) list;
            i = this.tag & 7;
            if (i == 2) {
                i = zzdd();
                zzad(i);
                i2 = this.pos + i;
                while (this.pos < i2) {
                    zzfv.zzh(Float.intBitsToFloat(zzdi()));
                }
                return;
            } else if (i != 5) {
                throw zzgf.zzfm();
            } else {
                do {
                    zzfv.zzh(readFloat());
                    if (!zzcm()) {
                        i = this.pos;
                    } else {
                        return;
                    }
                } while (zzdd() == this.tag);
                this.pos = i;
                return;
            }
        }
        i = this.tag & 7;
        if (i == 2) {
            i = zzdd();
            zzad(i);
            i2 = this.pos + i;
            while (this.pos < i2) {
                list.add(Float.valueOf(Float.intBitsToFloat(zzdi())));
            }
        } else if (i != 5) {
            throw zzgf.zzfm();
        } else {
            do {
                list.add(Float.valueOf(readFloat()));
                if (!zzcm()) {
                    i = this.pos;
                } else {
                    return;
                }
            } while (zzdd() == this.tag);
            this.pos = i;
        }
    }

    public final void zzc(List<Long> list) throws IOException {
        int zzdd;
        if (list instanceof zzgt) {
            zzgt zzgt = (zzgt) list;
            int i = this.tag & 7;
            if (i == 0) {
                do {
                    zzgt.zzp(zzcp());
                    if (!zzcm()) {
                        i = this.pos;
                    } else {
                        return;
                    }
                } while (zzdd() == this.tag);
                this.pos = i;
                return;
            } else if (i != 2) {
                throw zzgf.zzfm();
            } else {
                zzdd = this.pos + zzdd();
                while (this.pos < zzdd) {
                    zzgt.zzp(zzde());
                }
                zzae(zzdd);
                return;
            }
        }
        int i2 = this.tag & 7;
        if (i2 == 0) {
            do {
                list.add(Long.valueOf(zzcp()));
                if (!zzcm()) {
                    i2 = this.pos;
                } else {
                    return;
                }
            } while (zzdd() == this.tag);
            this.pos = i2;
        } else if (i2 != 2) {
            throw zzgf.zzfm();
        } else {
            zzdd = this.pos + zzdd();
            while (this.pos < zzdd) {
                list.add(Long.valueOf(zzde()));
            }
            zzae(zzdd);
        }
    }

    public final void zzd(List<Long> list) throws IOException {
        int zzdd;
        if (list instanceof zzgt) {
            zzgt zzgt = (zzgt) list;
            int i = this.tag & 7;
            if (i == 0) {
                do {
                    zzgt.zzp(zzcq());
                    if (!zzcm()) {
                        i = this.pos;
                    } else {
                        return;
                    }
                } while (zzdd() == this.tag);
                this.pos = i;
                return;
            } else if (i != 2) {
                throw zzgf.zzfm();
            } else {
                zzdd = this.pos + zzdd();
                while (this.pos < zzdd) {
                    zzgt.zzp(zzde());
                }
                zzae(zzdd);
                return;
            }
        }
        int i2 = this.tag & 7;
        if (i2 == 0) {
            do {
                list.add(Long.valueOf(zzcq()));
                if (!zzcm()) {
                    i2 = this.pos;
                } else {
                    return;
                }
            } while (zzdd() == this.tag);
            this.pos = i2;
        } else if (i2 != 2) {
            throw zzgf.zzfm();
        } else {
            zzdd = this.pos + zzdd();
            while (this.pos < zzdd) {
                list.add(Long.valueOf(zzde()));
            }
            zzae(zzdd);
        }
    }

    public final void zze(List<Integer> list) throws IOException {
        int zzdd;
        if (list instanceof zzfz) {
            zzfz zzfz = (zzfz) list;
            int i = this.tag & 7;
            if (i == 0) {
                do {
                    zzfz.zzbg(zzcr());
                    if (!zzcm()) {
                        i = this.pos;
                    } else {
                        return;
                    }
                } while (zzdd() == this.tag);
                this.pos = i;
                return;
            } else if (i != 2) {
                throw zzgf.zzfm();
            } else {
                zzdd = this.pos + zzdd();
                while (this.pos < zzdd) {
                    zzfz.zzbg(zzdd());
                }
                zzae(zzdd);
                return;
            }
        }
        int i2 = this.tag & 7;
        if (i2 == 0) {
            do {
                list.add(Integer.valueOf(zzcr()));
                if (!zzcm()) {
                    i2 = this.pos;
                } else {
                    return;
                }
            } while (zzdd() == this.tag);
            this.pos = i2;
        } else if (i2 != 2) {
            throw zzgf.zzfm();
        } else {
            zzdd = this.pos + zzdd();
            while (this.pos < zzdd) {
                list.add(Integer.valueOf(zzdd()));
            }
            zzae(zzdd);
        }
    }

    public final void zzf(List<Long> list) throws IOException {
        int zzdd;
        int i;
        if (list instanceof zzgt) {
            zzgt zzgt = (zzgt) list;
            switch (this.tag & 7) {
                case 1:
                    break;
                case 2:
                    zzdd = zzdd();
                    zzac(zzdd);
                    i = this.pos + zzdd;
                    while (this.pos < i) {
                        zzgt.zzp(zzdj());
                    }
                    return;
                default:
                    throw zzgf.zzfm();
            }
            do {
                zzgt.zzp(zzcs());
                if (!zzcm()) {
                    zzdd = this.pos;
                } else {
                    return;
                }
            } while (zzdd() == this.tag);
            this.pos = zzdd;
            return;
        }
        switch (this.tag & 7) {
            case 1:
                break;
            case 2:
                zzdd = zzdd();
                zzac(zzdd);
                i = this.pos + zzdd;
                while (this.pos < i) {
                    list.add(Long.valueOf(zzdj()));
                }
                return;
            default:
                throw zzgf.zzfm();
        }
        do {
            list.add(Long.valueOf(zzcs()));
            if (!zzcm()) {
                zzdd = this.pos;
            } else {
                return;
            }
        } while (zzdd() == this.tag);
        this.pos = zzdd;
    }

    public final void zzg(List<Integer> list) throws IOException {
        int i;
        int i2;
        if (list instanceof zzfz) {
            zzfz zzfz = (zzfz) list;
            i = this.tag & 7;
            if (i == 2) {
                i = zzdd();
                zzad(i);
                i2 = this.pos + i;
                while (this.pos < i2) {
                    zzfz.zzbg(zzdi());
                }
                return;
            } else if (i != 5) {
                throw zzgf.zzfm();
            } else {
                do {
                    zzfz.zzbg(zzct());
                    if (!zzcm()) {
                        i = this.pos;
                    } else {
                        return;
                    }
                } while (zzdd() == this.tag);
                this.pos = i;
                return;
            }
        }
        i = this.tag & 7;
        if (i == 2) {
            i = zzdd();
            zzad(i);
            i2 = this.pos + i;
            while (this.pos < i2) {
                list.add(Integer.valueOf(zzdi()));
            }
        } else if (i != 5) {
            throw zzgf.zzfm();
        } else {
            do {
                list.add(Integer.valueOf(zzct()));
                if (!zzcm()) {
                    i = this.pos;
                } else {
                    return;
                }
            } while (zzdd() == this.tag);
            this.pos = i;
        }
    }

    public final void zzh(List<Boolean> list) throws IOException {
        int zzdd;
        if (list instanceof zzem) {
            zzem zzem = (zzem) list;
            int i = this.tag & 7;
            if (i == 0) {
                do {
                    zzem.addBoolean(zzcu());
                    if (!zzcm()) {
                        i = this.pos;
                    } else {
                        return;
                    }
                } while (zzdd() == this.tag);
                this.pos = i;
                return;
            } else if (i != 2) {
                throw zzgf.zzfm();
            } else {
                zzdd = this.pos + zzdd();
                while (this.pos < zzdd) {
                    zzem.addBoolean(zzdd() != 0);
                }
                zzae(zzdd);
                return;
            }
        }
        int i2 = this.tag & 7;
        if (i2 == 0) {
            do {
                list.add(Boolean.valueOf(zzcu()));
                if (!zzcm()) {
                    i2 = this.pos;
                } else {
                    return;
                }
            } while (zzdd() == this.tag);
            this.pos = i2;
        } else if (i2 != 2) {
            throw zzgf.zzfm();
        } else {
            zzdd = this.pos + zzdd();
            while (this.pos < zzdd) {
                list.add(Boolean.valueOf(zzdd() != 0));
            }
            zzae(zzdd);
        }
    }

    public final void readStringList(List<String> list) throws IOException {
        zza((List) list, false);
    }

    public final void zzi(List<String> list) throws IOException {
        zza((List) list, true);
    }

    private final void zza(List<String> list, boolean z) throws IOException {
        if ((this.tag & 7) != 2) {
            throw zzgf.zzfm();
        } else if (!(list instanceof zzgo) || z) {
            int i;
            do {
                list.add(zzg(z));
                if (!zzcm()) {
                    i = this.pos;
                } else {
                    return;
                }
            } while (zzdd() == this.tag);
            this.pos = i;
        } else {
            int i2;
            zzgo zzgo = (zzgo) list;
            do {
                zzgo.zzc(zzcw());
                if (!zzcm()) {
                    i2 = this.pos;
                } else {
                    return;
                }
            } while (zzdd() == this.tag);
            this.pos = i2;
        }
    }

    public final <T> void zza(List<T> list, zzhw<T> zzhw, zzfk zzfk) throws IOException {
        if ((this.tag & 7) != 2) {
            throw zzgf.zzfm();
        }
        int i;
        int i2 = this.tag;
        do {
            list.add(zzb((zzhw) zzhw, zzfk));
            if (!zzcm()) {
                i = this.pos;
            } else {
                return;
            }
        } while (zzdd() == i2);
        this.pos = i;
    }

    public final <T> void zzb(List<T> list, zzhw<T> zzhw, zzfk zzfk) throws IOException {
        if ((this.tag & 7) != 3) {
            throw zzgf.zzfm();
        }
        int i;
        int i2 = this.tag;
        do {
            list.add(zzd(zzhw, zzfk));
            if (!zzcm()) {
                i = this.pos;
            } else {
                return;
            }
        } while (zzdd() == i2);
        this.pos = i;
    }

    public final void zzj(List<zzeo> list) throws IOException {
        if ((this.tag & 7) != 2) {
            throw zzgf.zzfm();
        }
        int i;
        do {
            list.add(zzcw());
            if (!zzcm()) {
                i = this.pos;
            } else {
                return;
            }
        } while (zzdd() == this.tag);
        this.pos = i;
    }

    public final void zzk(List<Integer> list) throws IOException {
        int zzdd;
        if (list instanceof zzfz) {
            zzfz zzfz = (zzfz) list;
            int i = this.tag & 7;
            if (i == 0) {
                do {
                    zzfz.zzbg(zzcx());
                    if (!zzcm()) {
                        i = this.pos;
                    } else {
                        return;
                    }
                } while (zzdd() == this.tag);
                this.pos = i;
                return;
            } else if (i != 2) {
                throw zzgf.zzfm();
            } else {
                zzdd = this.pos + zzdd();
                while (this.pos < zzdd) {
                    zzfz.zzbg(zzdd());
                }
                return;
            }
        }
        int i2 = this.tag & 7;
        if (i2 == 0) {
            do {
                list.add(Integer.valueOf(zzcx()));
                if (!zzcm()) {
                    i2 = this.pos;
                } else {
                    return;
                }
            } while (zzdd() == this.tag);
            this.pos = i2;
        } else if (i2 != 2) {
            throw zzgf.zzfm();
        } else {
            zzdd = this.pos + zzdd();
            while (this.pos < zzdd) {
                list.add(Integer.valueOf(zzdd()));
            }
        }
    }

    public final void zzl(List<Integer> list) throws IOException {
        int zzdd;
        if (list instanceof zzfz) {
            zzfz zzfz = (zzfz) list;
            int i = this.tag & 7;
            if (i == 0) {
                do {
                    zzfz.zzbg(zzcy());
                    if (!zzcm()) {
                        i = this.pos;
                    } else {
                        return;
                    }
                } while (zzdd() == this.tag);
                this.pos = i;
                return;
            } else if (i != 2) {
                throw zzgf.zzfm();
            } else {
                zzdd = this.pos + zzdd();
                while (this.pos < zzdd) {
                    zzfz.zzbg(zzdd());
                }
                return;
            }
        }
        int i2 = this.tag & 7;
        if (i2 == 0) {
            do {
                list.add(Integer.valueOf(zzcy()));
                if (!zzcm()) {
                    i2 = this.pos;
                } else {
                    return;
                }
            } while (zzdd() == this.tag);
            this.pos = i2;
        } else if (i2 != 2) {
            throw zzgf.zzfm();
        } else {
            zzdd = this.pos + zzdd();
            while (this.pos < zzdd) {
                list.add(Integer.valueOf(zzdd()));
            }
        }
    }

    public final void zzm(List<Integer> list) throws IOException {
        int i;
        int i2;
        if (list instanceof zzfz) {
            zzfz zzfz = (zzfz) list;
            i = this.tag & 7;
            if (i == 2) {
                i = zzdd();
                zzad(i);
                i2 = this.pos + i;
                while (this.pos < i2) {
                    zzfz.zzbg(zzdi());
                }
                return;
            } else if (i != 5) {
                throw zzgf.zzfm();
            } else {
                do {
                    zzfz.zzbg(zzcz());
                    if (!zzcm()) {
                        i = this.pos;
                    } else {
                        return;
                    }
                } while (zzdd() == this.tag);
                this.pos = i;
                return;
            }
        }
        i = this.tag & 7;
        if (i == 2) {
            i = zzdd();
            zzad(i);
            i2 = this.pos + i;
            while (this.pos < i2) {
                list.add(Integer.valueOf(zzdi()));
            }
        } else if (i != 5) {
            throw zzgf.zzfm();
        } else {
            do {
                list.add(Integer.valueOf(zzcz()));
                if (!zzcm()) {
                    i = this.pos;
                } else {
                    return;
                }
            } while (zzdd() == this.tag);
            this.pos = i;
        }
    }

    public final void zzn(List<Long> list) throws IOException {
        int zzdd;
        int i;
        if (list instanceof zzgt) {
            zzgt zzgt = (zzgt) list;
            switch (this.tag & 7) {
                case 1:
                    break;
                case 2:
                    zzdd = zzdd();
                    zzac(zzdd);
                    i = this.pos + zzdd;
                    while (this.pos < i) {
                        zzgt.zzp(zzdj());
                    }
                    return;
                default:
                    throw zzgf.zzfm();
            }
            do {
                zzgt.zzp(zzda());
                if (!zzcm()) {
                    zzdd = this.pos;
                } else {
                    return;
                }
            } while (zzdd() == this.tag);
            this.pos = zzdd;
            return;
        }
        switch (this.tag & 7) {
            case 1:
                break;
            case 2:
                zzdd = zzdd();
                zzac(zzdd);
                i = this.pos + zzdd;
                while (this.pos < i) {
                    list.add(Long.valueOf(zzdj()));
                }
                return;
            default:
                throw zzgf.zzfm();
        }
        do {
            list.add(Long.valueOf(zzda()));
            if (!zzcm()) {
                zzdd = this.pos;
            } else {
                return;
            }
        } while (zzdd() == this.tag);
        this.pos = zzdd;
    }

    public final void zzo(List<Integer> list) throws IOException {
        int zzdd;
        if (list instanceof zzfz) {
            zzfz zzfz = (zzfz) list;
            int i = this.tag & 7;
            if (i == 0) {
                do {
                    zzfz.zzbg(zzdb());
                    if (!zzcm()) {
                        i = this.pos;
                    } else {
                        return;
                    }
                } while (zzdd() == this.tag);
                this.pos = i;
                return;
            } else if (i != 2) {
                throw zzgf.zzfm();
            } else {
                zzdd = this.pos + zzdd();
                while (this.pos < zzdd) {
                    zzfz.zzbg(zzez.zzaq(zzdd()));
                }
                return;
            }
        }
        int i2 = this.tag & 7;
        if (i2 == 0) {
            do {
                list.add(Integer.valueOf(zzdb()));
                if (!zzcm()) {
                    i2 = this.pos;
                } else {
                    return;
                }
            } while (zzdd() == this.tag);
            this.pos = i2;
        } else if (i2 != 2) {
            throw zzgf.zzfm();
        } else {
            zzdd = this.pos + zzdd();
            while (this.pos < zzdd) {
                list.add(Integer.valueOf(zzez.zzaq(zzdd())));
            }
        }
    }

    public final void zzp(List<Long> list) throws IOException {
        int zzdd;
        if (list instanceof zzgt) {
            zzgt zzgt = (zzgt) list;
            int i = this.tag & 7;
            if (i == 0) {
                do {
                    zzgt.zzp(zzdc());
                    if (!zzcm()) {
                        i = this.pos;
                    } else {
                        return;
                    }
                } while (zzdd() == this.tag);
                this.pos = i;
                return;
            } else if (i != 2) {
                throw zzgf.zzfm();
            } else {
                zzdd = this.pos + zzdd();
                while (this.pos < zzdd) {
                    zzgt.zzp(zzez.zzd(zzde()));
                }
                return;
            }
        }
        int i2 = this.tag & 7;
        if (i2 == 0) {
            do {
                list.add(Long.valueOf(zzdc()));
                if (!zzcm()) {
                    i2 = this.pos;
                } else {
                    return;
                }
            } while (zzdd() == this.tag);
            this.pos = i2;
        } else if (i2 != 2) {
            throw zzgf.zzfm();
        } else {
            zzdd = this.pos + zzdd();
            while (this.pos < zzdd) {
                list.add(Long.valueOf(zzez.zzd(zzde())));
            }
        }
    }

    /* JADX WARNING: Missing exception handler attribute for start block: B:14:0x0048 */
    /* JADX WARNING: Can't wrap try/catch for region: R(3:14|15|(3:24|17|18)(1:30)) */
    /* JADX WARNING: Missing block: B:16:0x004c, code skipped:
            if (zzco() == false) goto L_0x004e;
     */
    /* JADX WARNING: Missing block: B:18:0x0055, code skipped:
            throw new com.google.android.gms.internal.vision.zzgf("Unable to parse map entry.");
     */
    public final <K, V> void zza(java.util.Map<K, V> r6, com.google.android.gms.internal.vision.zzgy<K, V> r7, com.google.android.gms.internal.vision.zzfk r8) throws java.io.IOException {
        /*
        r5 = this;
        r0 = 2;
        r5.zzab(r0);
        r0 = r5.zzdd();
        r5.zzaa(r0);
        r1 = r5.limit;
        r2 = r5.pos;
        r2 = r2 + r0;
        r5.limit = r2;
        r0 = r7.zzyw;	 Catch:{ all -> 0x005c }
        r2 = r7.zzgq;	 Catch:{ all -> 0x005c }
    L_0x0016:
        r3 = r5.zzcn();	 Catch:{ all -> 0x005c }
        r4 = 2147483647; // 0x7fffffff float:NaN double:1.060997895E-314;
        if (r3 == r4) goto L_0x0056;
    L_0x001f:
        switch(r3) {
            case 1: goto L_0x0035;
            case 2: goto L_0x0027;
            default: goto L_0x0022;
        };
    L_0x0022:
        r3 = r5.zzco();	 Catch:{ zzgg -> 0x0048 }
        goto L_0x003e;
    L_0x0027:
        r3 = r7.zzyx;	 Catch:{ zzgg -> 0x0048 }
        r4 = r7.zzgq;	 Catch:{ zzgg -> 0x0048 }
        r4 = r4.getClass();	 Catch:{ zzgg -> 0x0048 }
        r3 = r5.zza(r3, r4, r8);	 Catch:{ zzgg -> 0x0048 }
        r2 = r3;
        goto L_0x0016;
    L_0x0035:
        r3 = r7.zzyv;	 Catch:{ zzgg -> 0x0048 }
        r4 = 0;
        r3 = r5.zza(r3, r4, r4);	 Catch:{ zzgg -> 0x0048 }
        r0 = r3;
        goto L_0x0016;
    L_0x003e:
        if (r3 != 0) goto L_0x0016;
    L_0x0040:
        r3 = new com.google.android.gms.internal.vision.zzgf;	 Catch:{ zzgg -> 0x0048 }
        r4 = "Unable to parse map entry.";
        r3.<init>(r4);	 Catch:{ zzgg -> 0x0048 }
        throw r3;	 Catch:{ zzgg -> 0x0048 }
    L_0x0048:
        r3 = r5.zzco();	 Catch:{ all -> 0x005c }
        if (r3 != 0) goto L_0x0016;
    L_0x004e:
        r6 = new com.google.android.gms.internal.vision.zzgf;	 Catch:{ all -> 0x005c }
        r7 = "Unable to parse map entry.";
        r6.<init>(r7);	 Catch:{ all -> 0x005c }
        throw r6;	 Catch:{ all -> 0x005c }
    L_0x0056:
        r6.put(r0, r2);	 Catch:{ all -> 0x005c }
        r5.limit = r1;
        return;
    L_0x005c:
        r6 = move-exception;
        r5.limit = r1;
        throw r6;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.vision.zzel.zza(java.util.Map, com.google.android.gms.internal.vision.zzgy, com.google.android.gms.internal.vision.zzfk):void");
    }

    private final Object zza(zzjd zzjd, Class<?> cls, zzfk zzfk) throws IOException {
        switch (zzjd) {
            case BOOL:
                return Boolean.valueOf(zzcu());
            case BYTES:
                return zzcw();
            case DOUBLE:
                return Double.valueOf(readDouble());
            case ENUM:
                return Integer.valueOf(zzcy());
            case FIXED32:
                return Integer.valueOf(zzct());
            case FIXED64:
                return Long.valueOf(zzcs());
            case FLOAT:
                return Float.valueOf(readFloat());
            case INT32:
                return Integer.valueOf(zzcr());
            case INT64:
                return Long.valueOf(zzcq());
            case MESSAGE:
                return zza((Class) cls, zzfk);
            case SFIXED32:
                return Integer.valueOf(zzcz());
            case SFIXED64:
                return Long.valueOf(zzda());
            case SINT32:
                return Integer.valueOf(zzdb());
            case SINT64:
                return Long.valueOf(zzdc());
            case STRING:
                return zzg(true);
            case UINT32:
                return Integer.valueOf(zzcx());
            case UINT64:
                return Long.valueOf(zzcp());
            default:
                throw new RuntimeException("unsupported field type.");
        }
    }

    private final int zzdd() throws IOException {
        int i = this.pos;
        if (this.limit == this.pos) {
            throw zzgf.zzfh();
        }
        int i2 = i + 1;
        byte b = this.buffer[i];
        if (b >= (byte) 0) {
            this.pos = i2;
            return b;
        } else if (this.limit - i2 < 9) {
            return (int) zzdf();
        } else {
            int i3 = i2 + 1;
            i = b ^ (this.buffer[i2] << 7);
            if (i < 0) {
                i ^= -128;
            } else {
                i2 = i3 + 1;
                i ^= this.buffer[i3] << 14;
                if (i >= 0) {
                    i ^= 16256;
                } else {
                    i3 = i2 + 1;
                    i ^= this.buffer[i2] << 21;
                    if (i < 0) {
                        i ^= -2080896;
                    } else {
                        i2 = i3 + 1;
                        byte b2 = this.buffer[i3];
                        i = (i ^ (b2 << 28)) ^ 266354560;
                        if (b2 < (byte) 0) {
                            i3 = i2 + 1;
                            if (this.buffer[i2] < (byte) 0) {
                                i2 = i3 + 1;
                                if (this.buffer[i3] < (byte) 0) {
                                    i3 = i2 + 1;
                                    if (this.buffer[i2] < (byte) 0) {
                                        i2 = i3 + 1;
                                        if (this.buffer[i3] < (byte) 0) {
                                            i3 = i2 + 1;
                                            if (this.buffer[i2] < (byte) 0) {
                                                throw zzgf.zzfj();
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                i3 = i2;
            }
            this.pos = i3;
            return i;
        }
    }

    private final long zzde() throws IOException {
        int i = this.pos;
        if (this.limit == i) {
            throw zzgf.zzfh();
        }
        byte[] bArr = this.buffer;
        int i2 = i + 1;
        byte b = bArr[i];
        if (b >= (byte) 0) {
            this.pos = i2;
            return (long) b;
        } else if (this.limit - i2 < 9) {
            return zzdf();
        } else {
            long j;
            long j2;
            long j3;
            int i3 = i2 + 1;
            i = b ^ (bArr[i2] << 7);
            if (i < 0) {
                j = (long) (i ^ -128);
            } else {
                i2 = i3 + 1;
                i ^= bArr[i3] << 14;
                if (i >= 0) {
                    j2 = (long) (i ^ 16256);
                    i = i2;
                    j3 = j2;
                    this.pos = i;
                    return j3;
                }
                i3 = i2 + 1;
                i ^= bArr[i2] << 21;
                if (i < 0) {
                    j = (long) (i ^ -2080896);
                } else {
                    long j4;
                    long j5 = (long) i;
                    i = i3 + 1;
                    long j6 = j5 ^ (((long) bArr[i3]) << 28);
                    if (j6 >= 0) {
                        j4 = j6 ^ 266354560;
                    } else {
                        int i4 = i + 1;
                        long j7 = j6 ^ (((long) bArr[i]) << 35);
                        if (j7 < 0) {
                            j3 = j7 ^ -34093383808L;
                        } else {
                            i = i4 + 1;
                            j6 = j7 ^ (((long) bArr[i4]) << 42);
                            if (j6 >= 0) {
                                j4 = j6 ^ 4363953127296L;
                            } else {
                                i4 = i + 1;
                                j7 = j6 ^ (((long) bArr[i]) << 49);
                                if (j7 < 0) {
                                    j3 = j7 ^ -558586000294016L;
                                } else {
                                    i = i4 + 1;
                                    long j8 = (j7 ^ (((long) bArr[i4]) << 56)) ^ 71499008037633920L;
                                    if (j8 < 0) {
                                        i4 = i + 1;
                                        if (((long) bArr[i]) < 0) {
                                            throw zzgf.zzfj();
                                        }
                                        i = i4;
                                    }
                                    j3 = j8;
                                    this.pos = i;
                                    return j3;
                                }
                            }
                        }
                        i = i4;
                        this.pos = i;
                        return j3;
                    }
                    j3 = j4;
                    this.pos = i;
                    return j3;
                }
            }
            j2 = j;
            i = i3;
            j3 = j2;
            this.pos = i;
            return j3;
        }
    }

    private final long zzdf() throws IOException {
        long j = 0;
        int i = 0;
        while (i < 64) {
            byte readByte = readByte();
            long j2 = j | (((long) (readByte & 127)) << i);
            if ((readByte & 128) == 0) {
                return j2;
            }
            i += 7;
            j = j2;
        }
        throw zzgf.zzfj();
    }

    private final byte readByte() throws IOException {
        if (this.pos == this.limit) {
            throw zzgf.zzfh();
        }
        byte[] bArr = this.buffer;
        int i = this.pos;
        this.pos = i + 1;
        return bArr[i];
    }

    private final int zzdg() throws IOException {
        zzaa(4);
        return zzdi();
    }

    private final long zzdh() throws IOException {
        zzaa(8);
        return zzdj();
    }

    private final int zzdi() {
        int i = this.pos;
        byte[] bArr = this.buffer;
        this.pos = i + 4;
        return ((bArr[i + 3] & 255) << 24) | (((bArr[i] & 255) | ((bArr[i + 1] & 255) << 8)) | ((bArr[i + 2] & 255) << 16));
    }

    private final long zzdj() {
        int i = this.pos;
        byte[] bArr = this.buffer;
        this.pos = i + 8;
        return (((((((((long) bArr[i]) & 255) | ((((long) bArr[i + 1]) & 255) << 8)) | ((((long) bArr[i + 2]) & 255) << 16)) | ((((long) bArr[i + 3]) & 255) << 24)) | ((((long) bArr[i + 4]) & 255) << 32)) | ((((long) bArr[i + 5]) & 255) << 40)) | ((((long) bArr[i + 6]) & 255) << 48)) | ((((long) bArr[i + 7]) & 255) << 56);
    }

    private final void zzz(int i) throws IOException {
        zzaa(i);
        this.pos += i;
    }

    private final void zzaa(int i) throws IOException {
        if (i < 0 || i > this.limit - this.pos) {
            throw zzgf.zzfh();
        }
    }

    private final void zzab(int i) throws IOException {
        if ((this.tag & 7) != i) {
            throw zzgf.zzfm();
        }
    }

    private final void zzac(int i) throws IOException {
        zzaa(i);
        if ((i & 7) != 0) {
            throw zzgf.zzfo();
        }
    }

    private final void zzad(int i) throws IOException {
        zzaa(i);
        if ((i & 3) != 0) {
            throw zzgf.zzfo();
        }
    }

    private final void zzae(int i) throws IOException {
        if (this.pos != i) {
            throw zzgf.zzfh();
        }
    }
}
