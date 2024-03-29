package com.google.android.gms.internal.vision;

import java.io.IOException;
import java.util.List;

final class zzfc implements zzhv {
    private int tag;
    private int zzru;
    private final zzez zzsp;
    private int zzsq = 0;

    public static zzfc zza(zzez zzez) {
        if (zzez.zzsi != null) {
            return zzez.zzsi;
        }
        return new zzfc(zzez);
    }

    private zzfc(zzez zzez) {
        this.zzsp = (zzez) zzga.zza((Object) zzez, "input");
        this.zzsp.zzsi = this;
    }

    public final int zzcn() throws IOException {
        if (this.zzsq != 0) {
            this.tag = this.zzsq;
            this.zzsq = 0;
        } else {
            this.tag = this.zzsp.zzdq();
        }
        return (this.tag == 0 || this.tag == this.zzru) ? Integer.MAX_VALUE : this.tag >>> 3;
    }

    public final int getTag() {
        return this.tag;
    }

    public final boolean zzco() throws IOException {
        return (this.zzsp.zzcm() || this.tag == this.zzru) ? false : this.zzsp.zzal(this.tag);
    }

    private final void zzab(int i) throws IOException {
        if ((this.tag & 7) != i) {
            throw zzgf.zzfm();
        }
    }

    public final double readDouble() throws IOException {
        zzab(1);
        return this.zzsp.readDouble();
    }

    public final float readFloat() throws IOException {
        zzab(5);
        return this.zzsp.readFloat();
    }

    public final long zzcp() throws IOException {
        zzab(0);
        return this.zzsp.zzcp();
    }

    public final long zzcq() throws IOException {
        zzab(0);
        return this.zzsp.zzcq();
    }

    public final int zzcr() throws IOException {
        zzab(0);
        return this.zzsp.zzcr();
    }

    public final long zzcs() throws IOException {
        zzab(1);
        return this.zzsp.zzcs();
    }

    public final int zzct() throws IOException {
        zzab(5);
        return this.zzsp.zzct();
    }

    public final boolean zzcu() throws IOException {
        zzab(0);
        return this.zzsp.zzcu();
    }

    public final String readString() throws IOException {
        zzab(2);
        return this.zzsp.readString();
    }

    public final String zzcv() throws IOException {
        zzab(2);
        return this.zzsp.zzcv();
    }

    public final <T> T zza(Class<T> cls, zzfk zzfk) throws IOException {
        zzab(2);
        return zzb(zzhs.zzgl().zzf(cls), zzfk);
    }

    public final <T> T zza(zzhw<T> zzhw, zzfk zzfk) throws IOException {
        zzab(2);
        return zzb((zzhw) zzhw, zzfk);
    }

    public final <T> T zzb(Class<T> cls, zzfk zzfk) throws IOException {
        zzab(3);
        return zzd(zzhs.zzgl().zzf(cls), zzfk);
    }

    public final <T> T zzc(zzhw<T> zzhw, zzfk zzfk) throws IOException {
        zzab(3);
        return zzd(zzhw, zzfk);
    }

    private final <T> T zzb(zzhw<T> zzhw, zzfk zzfk) throws IOException {
        int zzcx = this.zzsp.zzcx();
        if (this.zzsp.zzsf >= this.zzsp.zzsg) {
            throw zzgf.zzfn();
        }
        zzcx = this.zzsp.zzan(zzcx);
        Object newInstance = zzhw.newInstance();
        zzez zzez = this.zzsp;
        zzez.zzsf++;
        zzhw.zza(newInstance, this, zzfk);
        zzhw.zze(newInstance);
        this.zzsp.zzak(0);
        zzez zzez2 = this.zzsp;
        zzez2.zzsf--;
        this.zzsp.zzao(zzcx);
        return newInstance;
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
        return this.zzsp.zzcw();
    }

    public final int zzcx() throws IOException {
        zzab(0);
        return this.zzsp.zzcx();
    }

    public final int zzcy() throws IOException {
        zzab(0);
        return this.zzsp.zzcy();
    }

    public final int zzcz() throws IOException {
        zzab(5);
        return this.zzsp.zzcz();
    }

    public final long zzda() throws IOException {
        zzab(1);
        return this.zzsp.zzda();
    }

    public final int zzdb() throws IOException {
        zzab(0);
        return this.zzsp.zzdb();
    }

    public final long zzdc() throws IOException {
        zzab(0);
        return this.zzsp.zzdc();
    }

    public final void zza(List<Double> list) throws IOException {
        int zzcx;
        int zzds;
        if (list instanceof zzfh) {
            zzfh zzfh = (zzfh) list;
            switch (this.tag & 7) {
                case 1:
                    break;
                case 2:
                    zzcx = this.zzsp.zzcx();
                    zzac(zzcx);
                    zzds = this.zzsp.zzds() + zzcx;
                    do {
                        zzfh.zzc(this.zzsp.readDouble());
                    } while (this.zzsp.zzds() < zzds);
                    return;
                default:
                    throw zzgf.zzfm();
            }
            do {
                zzfh.zzc(this.zzsp.readDouble());
                if (!this.zzsp.zzcm()) {
                    zzcx = this.zzsp.zzdq();
                } else {
                    return;
                }
            } while (zzcx == this.tag);
            this.zzsq = zzcx;
            return;
        }
        switch (this.tag & 7) {
            case 1:
                break;
            case 2:
                zzcx = this.zzsp.zzcx();
                zzac(zzcx);
                zzds = this.zzsp.zzds() + zzcx;
                do {
                    list.add(Double.valueOf(this.zzsp.readDouble()));
                } while (this.zzsp.zzds() < zzds);
                return;
            default:
                throw zzgf.zzfm();
        }
        do {
            list.add(Double.valueOf(this.zzsp.readDouble()));
            if (!this.zzsp.zzcm()) {
                zzcx = this.zzsp.zzdq();
            } else {
                return;
            }
        } while (zzcx == this.tag);
        this.zzsq = zzcx;
    }

    public final void zzb(List<Float> list) throws IOException {
        if (list instanceof zzfv) {
            zzfv zzfv = (zzfv) list;
            int i = this.tag & 7;
            if (i == 2) {
                i = this.zzsp.zzcx();
                zzad(i);
                int zzds = this.zzsp.zzds() + i;
                do {
                    zzfv.zzh(this.zzsp.readFloat());
                } while (this.zzsp.zzds() < zzds);
                return;
            } else if (i != 5) {
                throw zzgf.zzfm();
            } else {
                do {
                    zzfv.zzh(this.zzsp.readFloat());
                    if (!this.zzsp.zzcm()) {
                        i = this.zzsp.zzdq();
                    } else {
                        return;
                    }
                } while (i == this.tag);
                this.zzsq = i;
                return;
            }
        }
        int i2 = this.tag & 7;
        if (i2 == 2) {
            i2 = this.zzsp.zzcx();
            zzad(i2);
            int zzds2 = this.zzsp.zzds() + i2;
            do {
                list.add(Float.valueOf(this.zzsp.readFloat()));
            } while (this.zzsp.zzds() < zzds2);
        } else if (i2 != 5) {
            throw zzgf.zzfm();
        } else {
            do {
                list.add(Float.valueOf(this.zzsp.readFloat()));
                if (!this.zzsp.zzcm()) {
                    i2 = this.zzsp.zzdq();
                } else {
                    return;
                }
            } while (i2 == this.tag);
            this.zzsq = i2;
        }
    }

    public final void zzc(List<Long> list) throws IOException {
        int zzds;
        if (list instanceof zzgt) {
            zzgt zzgt = (zzgt) list;
            int i = this.tag & 7;
            if (i == 0) {
                do {
                    zzgt.zzp(this.zzsp.zzcp());
                    if (!this.zzsp.zzcm()) {
                        i = this.zzsp.zzdq();
                    } else {
                        return;
                    }
                } while (i == this.tag);
                this.zzsq = i;
                return;
            } else if (i != 2) {
                throw zzgf.zzfm();
            } else {
                zzds = this.zzsp.zzds() + this.zzsp.zzcx();
                do {
                    zzgt.zzp(this.zzsp.zzcp());
                } while (this.zzsp.zzds() < zzds);
                zzae(zzds);
                return;
            }
        }
        int i2 = this.tag & 7;
        if (i2 == 0) {
            do {
                list.add(Long.valueOf(this.zzsp.zzcp()));
                if (!this.zzsp.zzcm()) {
                    i2 = this.zzsp.zzdq();
                } else {
                    return;
                }
            } while (i2 == this.tag);
            this.zzsq = i2;
        } else if (i2 != 2) {
            throw zzgf.zzfm();
        } else {
            zzds = this.zzsp.zzds() + this.zzsp.zzcx();
            do {
                list.add(Long.valueOf(this.zzsp.zzcp()));
            } while (this.zzsp.zzds() < zzds);
            zzae(zzds);
        }
    }

    public final void zzd(List<Long> list) throws IOException {
        int zzds;
        if (list instanceof zzgt) {
            zzgt zzgt = (zzgt) list;
            int i = this.tag & 7;
            if (i == 0) {
                do {
                    zzgt.zzp(this.zzsp.zzcq());
                    if (!this.zzsp.zzcm()) {
                        i = this.zzsp.zzdq();
                    } else {
                        return;
                    }
                } while (i == this.tag);
                this.zzsq = i;
                return;
            } else if (i != 2) {
                throw zzgf.zzfm();
            } else {
                zzds = this.zzsp.zzds() + this.zzsp.zzcx();
                do {
                    zzgt.zzp(this.zzsp.zzcq());
                } while (this.zzsp.zzds() < zzds);
                zzae(zzds);
                return;
            }
        }
        int i2 = this.tag & 7;
        if (i2 == 0) {
            do {
                list.add(Long.valueOf(this.zzsp.zzcq()));
                if (!this.zzsp.zzcm()) {
                    i2 = this.zzsp.zzdq();
                } else {
                    return;
                }
            } while (i2 == this.tag);
            this.zzsq = i2;
        } else if (i2 != 2) {
            throw zzgf.zzfm();
        } else {
            zzds = this.zzsp.zzds() + this.zzsp.zzcx();
            do {
                list.add(Long.valueOf(this.zzsp.zzcq()));
            } while (this.zzsp.zzds() < zzds);
            zzae(zzds);
        }
    }

    public final void zze(List<Integer> list) throws IOException {
        int zzds;
        if (list instanceof zzfz) {
            zzfz zzfz = (zzfz) list;
            int i = this.tag & 7;
            if (i == 0) {
                do {
                    zzfz.zzbg(this.zzsp.zzcr());
                    if (!this.zzsp.zzcm()) {
                        i = this.zzsp.zzdq();
                    } else {
                        return;
                    }
                } while (i == this.tag);
                this.zzsq = i;
                return;
            } else if (i != 2) {
                throw zzgf.zzfm();
            } else {
                zzds = this.zzsp.zzds() + this.zzsp.zzcx();
                do {
                    zzfz.zzbg(this.zzsp.zzcr());
                } while (this.zzsp.zzds() < zzds);
                zzae(zzds);
                return;
            }
        }
        int i2 = this.tag & 7;
        if (i2 == 0) {
            do {
                list.add(Integer.valueOf(this.zzsp.zzcr()));
                if (!this.zzsp.zzcm()) {
                    i2 = this.zzsp.zzdq();
                } else {
                    return;
                }
            } while (i2 == this.tag);
            this.zzsq = i2;
        } else if (i2 != 2) {
            throw zzgf.zzfm();
        } else {
            zzds = this.zzsp.zzds() + this.zzsp.zzcx();
            do {
                list.add(Integer.valueOf(this.zzsp.zzcr()));
            } while (this.zzsp.zzds() < zzds);
            zzae(zzds);
        }
    }

    public final void zzf(List<Long> list) throws IOException {
        int zzcx;
        int zzds;
        if (list instanceof zzgt) {
            zzgt zzgt = (zzgt) list;
            switch (this.tag & 7) {
                case 1:
                    break;
                case 2:
                    zzcx = this.zzsp.zzcx();
                    zzac(zzcx);
                    zzds = this.zzsp.zzds() + zzcx;
                    do {
                        zzgt.zzp(this.zzsp.zzcs());
                    } while (this.zzsp.zzds() < zzds);
                    return;
                default:
                    throw zzgf.zzfm();
            }
            do {
                zzgt.zzp(this.zzsp.zzcs());
                if (!this.zzsp.zzcm()) {
                    zzcx = this.zzsp.zzdq();
                } else {
                    return;
                }
            } while (zzcx == this.tag);
            this.zzsq = zzcx;
            return;
        }
        switch (this.tag & 7) {
            case 1:
                break;
            case 2:
                zzcx = this.zzsp.zzcx();
                zzac(zzcx);
                zzds = this.zzsp.zzds() + zzcx;
                do {
                    list.add(Long.valueOf(this.zzsp.zzcs()));
                } while (this.zzsp.zzds() < zzds);
                return;
            default:
                throw zzgf.zzfm();
        }
        do {
            list.add(Long.valueOf(this.zzsp.zzcs()));
            if (!this.zzsp.zzcm()) {
                zzcx = this.zzsp.zzdq();
            } else {
                return;
            }
        } while (zzcx == this.tag);
        this.zzsq = zzcx;
    }

    public final void zzg(List<Integer> list) throws IOException {
        if (list instanceof zzfz) {
            zzfz zzfz = (zzfz) list;
            int i = this.tag & 7;
            if (i == 2) {
                i = this.zzsp.zzcx();
                zzad(i);
                int zzds = this.zzsp.zzds() + i;
                do {
                    zzfz.zzbg(this.zzsp.zzct());
                } while (this.zzsp.zzds() < zzds);
                return;
            } else if (i != 5) {
                throw zzgf.zzfm();
            } else {
                do {
                    zzfz.zzbg(this.zzsp.zzct());
                    if (!this.zzsp.zzcm()) {
                        i = this.zzsp.zzdq();
                    } else {
                        return;
                    }
                } while (i == this.tag);
                this.zzsq = i;
                return;
            }
        }
        int i2 = this.tag & 7;
        if (i2 == 2) {
            i2 = this.zzsp.zzcx();
            zzad(i2);
            int zzds2 = this.zzsp.zzds() + i2;
            do {
                list.add(Integer.valueOf(this.zzsp.zzct()));
            } while (this.zzsp.zzds() < zzds2);
        } else if (i2 != 5) {
            throw zzgf.zzfm();
        } else {
            do {
                list.add(Integer.valueOf(this.zzsp.zzct()));
                if (!this.zzsp.zzcm()) {
                    i2 = this.zzsp.zzdq();
                } else {
                    return;
                }
            } while (i2 == this.tag);
            this.zzsq = i2;
        }
    }

    public final void zzh(List<Boolean> list) throws IOException {
        int zzds;
        if (list instanceof zzem) {
            zzem zzem = (zzem) list;
            int i = this.tag & 7;
            if (i == 0) {
                do {
                    zzem.addBoolean(this.zzsp.zzcu());
                    if (!this.zzsp.zzcm()) {
                        i = this.zzsp.zzdq();
                    } else {
                        return;
                    }
                } while (i == this.tag);
                this.zzsq = i;
                return;
            } else if (i != 2) {
                throw zzgf.zzfm();
            } else {
                zzds = this.zzsp.zzds() + this.zzsp.zzcx();
                do {
                    zzem.addBoolean(this.zzsp.zzcu());
                } while (this.zzsp.zzds() < zzds);
                zzae(zzds);
                return;
            }
        }
        int i2 = this.tag & 7;
        if (i2 == 0) {
            do {
                list.add(Boolean.valueOf(this.zzsp.zzcu()));
                if (!this.zzsp.zzcm()) {
                    i2 = this.zzsp.zzdq();
                } else {
                    return;
                }
            } while (i2 == this.tag);
            this.zzsq = i2;
        } else if (i2 != 2) {
            throw zzgf.zzfm();
        } else {
            zzds = this.zzsp.zzds() + this.zzsp.zzcx();
            do {
                list.add(Boolean.valueOf(this.zzsp.zzcu()));
            } while (this.zzsp.zzds() < zzds);
            zzae(zzds);
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
            int zzdq;
            do {
                list.add(z ? zzcv() : readString());
                if (!this.zzsp.zzcm()) {
                    zzdq = this.zzsp.zzdq();
                } else {
                    return;
                }
            } while (zzdq == this.tag);
            this.zzsq = zzdq;
        } else {
            int zzdq2;
            zzgo zzgo = (zzgo) list;
            do {
                zzgo.zzc(zzcw());
                if (!this.zzsp.zzcm()) {
                    zzdq2 = this.zzsp.zzdq();
                } else {
                    return;
                }
            } while (zzdq2 == this.tag);
            this.zzsq = zzdq2;
        }
    }

    public final <T> void zza(List<T> list, zzhw<T> zzhw, zzfk zzfk) throws IOException {
        if ((this.tag & 7) != 2) {
            throw zzgf.zzfm();
        }
        int zzdq;
        int i = this.tag;
        do {
            list.add(zzb((zzhw) zzhw, zzfk));
            if (!this.zzsp.zzcm() && this.zzsq == 0) {
                zzdq = this.zzsp.zzdq();
            } else {
                return;
            }
        } while (zzdq == i);
        this.zzsq = zzdq;
    }

    public final <T> void zzb(List<T> list, zzhw<T> zzhw, zzfk zzfk) throws IOException {
        if ((this.tag & 7) != 3) {
            throw zzgf.zzfm();
        }
        int zzdq;
        int i = this.tag;
        do {
            list.add(zzd(zzhw, zzfk));
            if (!this.zzsp.zzcm() && this.zzsq == 0) {
                zzdq = this.zzsp.zzdq();
            } else {
                return;
            }
        } while (zzdq == i);
        this.zzsq = zzdq;
    }

    public final void zzj(List<zzeo> list) throws IOException {
        if ((this.tag & 7) != 2) {
            throw zzgf.zzfm();
        }
        int zzdq;
        do {
            list.add(zzcw());
            if (!this.zzsp.zzcm()) {
                zzdq = this.zzsp.zzdq();
            } else {
                return;
            }
        } while (zzdq == this.tag);
        this.zzsq = zzdq;
    }

    public final void zzk(List<Integer> list) throws IOException {
        int zzds;
        if (list instanceof zzfz) {
            zzfz zzfz = (zzfz) list;
            int i = this.tag & 7;
            if (i == 0) {
                do {
                    zzfz.zzbg(this.zzsp.zzcx());
                    if (!this.zzsp.zzcm()) {
                        i = this.zzsp.zzdq();
                    } else {
                        return;
                    }
                } while (i == this.tag);
                this.zzsq = i;
                return;
            } else if (i != 2) {
                throw zzgf.zzfm();
            } else {
                zzds = this.zzsp.zzds() + this.zzsp.zzcx();
                do {
                    zzfz.zzbg(this.zzsp.zzcx());
                } while (this.zzsp.zzds() < zzds);
                zzae(zzds);
                return;
            }
        }
        int i2 = this.tag & 7;
        if (i2 == 0) {
            do {
                list.add(Integer.valueOf(this.zzsp.zzcx()));
                if (!this.zzsp.zzcm()) {
                    i2 = this.zzsp.zzdq();
                } else {
                    return;
                }
            } while (i2 == this.tag);
            this.zzsq = i2;
        } else if (i2 != 2) {
            throw zzgf.zzfm();
        } else {
            zzds = this.zzsp.zzds() + this.zzsp.zzcx();
            do {
                list.add(Integer.valueOf(this.zzsp.zzcx()));
            } while (this.zzsp.zzds() < zzds);
            zzae(zzds);
        }
    }

    public final void zzl(List<Integer> list) throws IOException {
        int zzds;
        if (list instanceof zzfz) {
            zzfz zzfz = (zzfz) list;
            int i = this.tag & 7;
            if (i == 0) {
                do {
                    zzfz.zzbg(this.zzsp.zzcy());
                    if (!this.zzsp.zzcm()) {
                        i = this.zzsp.zzdq();
                    } else {
                        return;
                    }
                } while (i == this.tag);
                this.zzsq = i;
                return;
            } else if (i != 2) {
                throw zzgf.zzfm();
            } else {
                zzds = this.zzsp.zzds() + this.zzsp.zzcx();
                do {
                    zzfz.zzbg(this.zzsp.zzcy());
                } while (this.zzsp.zzds() < zzds);
                zzae(zzds);
                return;
            }
        }
        int i2 = this.tag & 7;
        if (i2 == 0) {
            do {
                list.add(Integer.valueOf(this.zzsp.zzcy()));
                if (!this.zzsp.zzcm()) {
                    i2 = this.zzsp.zzdq();
                } else {
                    return;
                }
            } while (i2 == this.tag);
            this.zzsq = i2;
        } else if (i2 != 2) {
            throw zzgf.zzfm();
        } else {
            zzds = this.zzsp.zzds() + this.zzsp.zzcx();
            do {
                list.add(Integer.valueOf(this.zzsp.zzcy()));
            } while (this.zzsp.zzds() < zzds);
            zzae(zzds);
        }
    }

    public final void zzm(List<Integer> list) throws IOException {
        if (list instanceof zzfz) {
            zzfz zzfz = (zzfz) list;
            int i = this.tag & 7;
            if (i == 2) {
                i = this.zzsp.zzcx();
                zzad(i);
                int zzds = this.zzsp.zzds() + i;
                do {
                    zzfz.zzbg(this.zzsp.zzcz());
                } while (this.zzsp.zzds() < zzds);
                return;
            } else if (i != 5) {
                throw zzgf.zzfm();
            } else {
                do {
                    zzfz.zzbg(this.zzsp.zzcz());
                    if (!this.zzsp.zzcm()) {
                        i = this.zzsp.zzdq();
                    } else {
                        return;
                    }
                } while (i == this.tag);
                this.zzsq = i;
                return;
            }
        }
        int i2 = this.tag & 7;
        if (i2 == 2) {
            i2 = this.zzsp.zzcx();
            zzad(i2);
            int zzds2 = this.zzsp.zzds() + i2;
            do {
                list.add(Integer.valueOf(this.zzsp.zzcz()));
            } while (this.zzsp.zzds() < zzds2);
        } else if (i2 != 5) {
            throw zzgf.zzfm();
        } else {
            do {
                list.add(Integer.valueOf(this.zzsp.zzcz()));
                if (!this.zzsp.zzcm()) {
                    i2 = this.zzsp.zzdq();
                } else {
                    return;
                }
            } while (i2 == this.tag);
            this.zzsq = i2;
        }
    }

    public final void zzn(List<Long> list) throws IOException {
        int zzcx;
        int zzds;
        if (list instanceof zzgt) {
            zzgt zzgt = (zzgt) list;
            switch (this.tag & 7) {
                case 1:
                    break;
                case 2:
                    zzcx = this.zzsp.zzcx();
                    zzac(zzcx);
                    zzds = this.zzsp.zzds() + zzcx;
                    do {
                        zzgt.zzp(this.zzsp.zzda());
                    } while (this.zzsp.zzds() < zzds);
                    return;
                default:
                    throw zzgf.zzfm();
            }
            do {
                zzgt.zzp(this.zzsp.zzda());
                if (!this.zzsp.zzcm()) {
                    zzcx = this.zzsp.zzdq();
                } else {
                    return;
                }
            } while (zzcx == this.tag);
            this.zzsq = zzcx;
            return;
        }
        switch (this.tag & 7) {
            case 1:
                break;
            case 2:
                zzcx = this.zzsp.zzcx();
                zzac(zzcx);
                zzds = this.zzsp.zzds() + zzcx;
                do {
                    list.add(Long.valueOf(this.zzsp.zzda()));
                } while (this.zzsp.zzds() < zzds);
                return;
            default:
                throw zzgf.zzfm();
        }
        do {
            list.add(Long.valueOf(this.zzsp.zzda()));
            if (!this.zzsp.zzcm()) {
                zzcx = this.zzsp.zzdq();
            } else {
                return;
            }
        } while (zzcx == this.tag);
        this.zzsq = zzcx;
    }

    public final void zzo(List<Integer> list) throws IOException {
        int zzds;
        if (list instanceof zzfz) {
            zzfz zzfz = (zzfz) list;
            int i = this.tag & 7;
            if (i == 0) {
                do {
                    zzfz.zzbg(this.zzsp.zzdb());
                    if (!this.zzsp.zzcm()) {
                        i = this.zzsp.zzdq();
                    } else {
                        return;
                    }
                } while (i == this.tag);
                this.zzsq = i;
                return;
            } else if (i != 2) {
                throw zzgf.zzfm();
            } else {
                zzds = this.zzsp.zzds() + this.zzsp.zzcx();
                do {
                    zzfz.zzbg(this.zzsp.zzdb());
                } while (this.zzsp.zzds() < zzds);
                zzae(zzds);
                return;
            }
        }
        int i2 = this.tag & 7;
        if (i2 == 0) {
            do {
                list.add(Integer.valueOf(this.zzsp.zzdb()));
                if (!this.zzsp.zzcm()) {
                    i2 = this.zzsp.zzdq();
                } else {
                    return;
                }
            } while (i2 == this.tag);
            this.zzsq = i2;
        } else if (i2 != 2) {
            throw zzgf.zzfm();
        } else {
            zzds = this.zzsp.zzds() + this.zzsp.zzcx();
            do {
                list.add(Integer.valueOf(this.zzsp.zzdb()));
            } while (this.zzsp.zzds() < zzds);
            zzae(zzds);
        }
    }

    public final void zzp(List<Long> list) throws IOException {
        int zzds;
        if (list instanceof zzgt) {
            zzgt zzgt = (zzgt) list;
            int i = this.tag & 7;
            if (i == 0) {
                do {
                    zzgt.zzp(this.zzsp.zzdc());
                    if (!this.zzsp.zzcm()) {
                        i = this.zzsp.zzdq();
                    } else {
                        return;
                    }
                } while (i == this.tag);
                this.zzsq = i;
                return;
            } else if (i != 2) {
                throw zzgf.zzfm();
            } else {
                zzds = this.zzsp.zzds() + this.zzsp.zzcx();
                do {
                    zzgt.zzp(this.zzsp.zzdc());
                } while (this.zzsp.zzds() < zzds);
                zzae(zzds);
                return;
            }
        }
        int i2 = this.tag & 7;
        if (i2 == 0) {
            do {
                list.add(Long.valueOf(this.zzsp.zzdc()));
                if (!this.zzsp.zzcm()) {
                    i2 = this.zzsp.zzdq();
                } else {
                    return;
                }
            } while (i2 == this.tag);
            this.zzsq = i2;
        } else if (i2 != 2) {
            throw zzgf.zzfm();
        } else {
            zzds = this.zzsp.zzds() + this.zzsp.zzcx();
            do {
                list.add(Long.valueOf(this.zzsp.zzdc()));
            } while (this.zzsp.zzds() < zzds);
            zzae(zzds);
        }
    }

    private static void zzac(int i) throws IOException {
        if ((i & 7) != 0) {
            throw zzgf.zzfo();
        }
    }

    /* JADX WARNING: Missing exception handler attribute for start block: B:14:0x004e */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing block: B:16:0x0052, code skipped:
            if (zzco() == false) goto L_0x0054;
     */
    /* JADX WARNING: Missing block: B:18:0x005b, code skipped:
            throw new com.google.android.gms.internal.vision.zzgf("Unable to parse map entry.");
     */
    public final <K, V> void zza(java.util.Map<K, V> r6, com.google.android.gms.internal.vision.zzgy<K, V> r7, com.google.android.gms.internal.vision.zzfk r8) throws java.io.IOException {
        /*
        r5 = this;
        r0 = 2;
        r5.zzab(r0);
        r0 = r5.zzsp;
        r0 = r0.zzcx();
        r1 = r5.zzsp;
        r0 = r1.zzan(r0);
        r1 = r7.zzyw;
        r2 = r7.zzgq;
    L_0x0014:
        r3 = r5.zzcn();	 Catch:{ all -> 0x0065 }
        r4 = 2147483647; // 0x7fffffff float:NaN double:1.060997895E-314;
        if (r3 == r4) goto L_0x005c;
    L_0x001d:
        r4 = r5.zzsp;	 Catch:{ all -> 0x0065 }
        r4 = r4.zzcm();	 Catch:{ all -> 0x0065 }
        if (r4 != 0) goto L_0x005c;
    L_0x0025:
        switch(r3) {
            case 1: goto L_0x003b;
            case 2: goto L_0x002d;
            default: goto L_0x0028;
        };
    L_0x0028:
        r3 = r5.zzco();	 Catch:{ zzgg -> 0x004e }
        goto L_0x0044;
    L_0x002d:
        r3 = r7.zzyx;	 Catch:{ zzgg -> 0x004e }
        r4 = r7.zzgq;	 Catch:{ zzgg -> 0x004e }
        r4 = r4.getClass();	 Catch:{ zzgg -> 0x004e }
        r3 = r5.zza(r3, r4, r8);	 Catch:{ zzgg -> 0x004e }
        r2 = r3;
        goto L_0x0014;
    L_0x003b:
        r3 = r7.zzyv;	 Catch:{ zzgg -> 0x004e }
        r4 = 0;
        r3 = r5.zza(r3, r4, r4);	 Catch:{ zzgg -> 0x004e }
        r1 = r3;
        goto L_0x0014;
    L_0x0044:
        if (r3 != 0) goto L_0x0014;
    L_0x0046:
        r3 = new com.google.android.gms.internal.vision.zzgf;	 Catch:{ zzgg -> 0x004e }
        r4 = "Unable to parse map entry.";
        r3.<init>(r4);	 Catch:{ zzgg -> 0x004e }
        throw r3;	 Catch:{ zzgg -> 0x004e }
    L_0x004e:
        r3 = r5.zzco();	 Catch:{ all -> 0x0065 }
        if (r3 != 0) goto L_0x0014;
    L_0x0054:
        r6 = new com.google.android.gms.internal.vision.zzgf;	 Catch:{ all -> 0x0065 }
        r7 = "Unable to parse map entry.";
        r6.<init>(r7);	 Catch:{ all -> 0x0065 }
        throw r6;	 Catch:{ all -> 0x0065 }
    L_0x005c:
        r6.put(r1, r2);	 Catch:{ all -> 0x0065 }
        r6 = r5.zzsp;
        r6.zzao(r0);
        return;
    L_0x0065:
        r6 = move-exception;
        r7 = r5.zzsp;
        r7.zzao(r0);
        throw r6;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.vision.zzfc.zza(java.util.Map, com.google.android.gms.internal.vision.zzgy, com.google.android.gms.internal.vision.zzfk):void");
    }

    private final Object zza(zzjd zzjd, Class<?> cls, zzfk zzfk) throws IOException {
        switch (zzfd.zzrr[zzjd.ordinal()]) {
            case 1:
                return Boolean.valueOf(zzcu());
            case 2:
                return zzcw();
            case 3:
                return Double.valueOf(readDouble());
            case 4:
                return Integer.valueOf(zzcy());
            case 5:
                return Integer.valueOf(zzct());
            case 6:
                return Long.valueOf(zzcs());
            case 7:
                return Float.valueOf(readFloat());
            case 8:
                return Integer.valueOf(zzcr());
            case 9:
                return Long.valueOf(zzcq());
            case 10:
                return zza((Class) cls, zzfk);
            case 11:
                return Integer.valueOf(zzcz());
            case 12:
                return Long.valueOf(zzda());
            case 13:
                return Integer.valueOf(zzdb());
            case 14:
                return Long.valueOf(zzdc());
            case 15:
                return zzcv();
            case 16:
                return Integer.valueOf(zzcx());
            case 17:
                return Long.valueOf(zzcp());
            default:
                throw new RuntimeException("unsupported field type.");
        }
    }

    private static void zzad(int i) throws IOException {
        if ((i & 3) != 0) {
            throw zzgf.zzfo();
        }
    }

    private final void zzae(int i) throws IOException {
        if (this.zzsp.zzds() != i) {
            throw zzgf.zzfh();
        }
    }
}
