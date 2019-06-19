package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.util.List;

final class zzbqi implements zzbtb {
    private int tag;
    private final zzbqf zzfma;
    private int zzfmb;
    private int zzfmc = 0;

    public static zzbqi zza(zzbqf zzbqf) {
        if (zzbqf.zzflt != null) {
            return zzbqf.zzflt;
        }
        return new zzbqi(zzbqf);
    }

    private zzbqi(zzbqf zzbqf) {
        this.zzfma = (zzbqf) zzbrf.zza(zzbqf, "input");
        this.zzfma.zzflt = this;
    }

    public final int zzals() throws IOException {
        if (this.zzfmc != 0) {
            this.tag = this.zzfmc;
            this.zzfmc = 0;
        } else {
            this.tag = this.zzfma.zzaku();
        }
        return (this.tag == 0 || this.tag == this.zzfmb) ? Integer.MAX_VALUE : this.tag >>> 3;
    }

    public final int getTag() {
        return this.tag;
    }

    public final boolean zzalt() throws IOException {
        return (this.zzfma.zzalk() || this.tag == this.zzfmb) ? false : this.zzfma.zzep(this.tag);
    }

    private final void zzev(int i) throws IOException {
        if ((this.tag & 7) != i) {
            throw zzbrl.zzanh();
        }
    }

    public final double readDouble() throws IOException {
        zzev(1);
        return this.zzfma.readDouble();
    }

    public final float readFloat() throws IOException {
        zzev(5);
        return this.zzfma.readFloat();
    }

    public final long zzakv() throws IOException {
        zzev(0);
        return this.zzfma.zzakv();
    }

    public final long zzakw() throws IOException {
        zzev(0);
        return this.zzfma.zzakw();
    }

    public final int zzakx() throws IOException {
        zzev(0);
        return this.zzfma.zzakx();
    }

    public final long zzaky() throws IOException {
        zzev(1);
        return this.zzfma.zzaky();
    }

    public final int zzakz() throws IOException {
        zzev(5);
        return this.zzfma.zzakz();
    }

    public final boolean zzala() throws IOException {
        zzev(0);
        return this.zzfma.zzala();
    }

    public final String readString() throws IOException {
        zzev(2);
        return this.zzfma.readString();
    }

    public final String zzalb() throws IOException {
        zzev(2);
        return this.zzfma.zzalb();
    }

    public final <T> T zza(zzbtc<T> zzbtc, zzbqq zzbqq) throws IOException {
        zzev(2);
        return zzc(zzbtc, zzbqq);
    }

    public final <T> T zzb(zzbtc<T> zzbtc, zzbqq zzbqq) throws IOException {
        zzev(3);
        return zzd(zzbtc, zzbqq);
    }

    private final <T> T zzc(zzbtc<T> zzbtc, zzbqq zzbqq) throws IOException {
        int zzald = this.zzfma.zzald();
        if (this.zzfma.zzflq >= this.zzfma.zzflr) {
            throw zzbrl.zzani();
        }
        zzald = this.zzfma.zzer(zzald);
        Object newInstance = zzbtc.newInstance();
        zzbqf zzbqf = this.zzfma;
        zzbqf.zzflq++;
        zzbtc.zza(newInstance, this, zzbqq);
        zzbtc.zzs(newInstance);
        this.zzfma.zzeo(0);
        zzbqf zzbqf2 = this.zzfma;
        zzbqf2.zzflq--;
        this.zzfma.zzes(zzald);
        return newInstance;
    }

    private final <T> T zzd(zzbtc<T> zzbtc, zzbqq zzbqq) throws IOException {
        int i = this.zzfmb;
        T t = ((this.tag >>> 3) << 3) | 4;
        this.zzfmb = t;
        try {
            t = zzbtc.newInstance();
            zzbtc.zza(t, this, zzbqq);
            zzbtc.zzs(t);
            if (this.tag == this.zzfmb) {
                return t;
            }
            throw zzbrl.zzanj();
        } finally {
            this.zzfmb = i;
        }
    }

    public final zzbpu zzalc() throws IOException {
        zzev(2);
        return this.zzfma.zzalc();
    }

    public final int zzald() throws IOException {
        zzev(0);
        return this.zzfma.zzald();
    }

    public final int zzale() throws IOException {
        zzev(0);
        return this.zzfma.zzale();
    }

    public final int zzalf() throws IOException {
        zzev(5);
        return this.zzfma.zzalf();
    }

    public final long zzalg() throws IOException {
        zzev(1);
        return this.zzfma.zzalg();
    }

    public final int zzalh() throws IOException {
        zzev(0);
        return this.zzfma.zzalh();
    }

    public final long zzali() throws IOException {
        zzev(0);
        return this.zzfma.zzali();
    }

    public final void zzp(List<Double> list) throws IOException {
        int zzald;
        int zzall;
        if (list instanceof zzbqn) {
            zzbqn zzbqn = (zzbqn) list;
            switch (this.tag & 7) {
                case 1:
                    break;
                case 2:
                    zzald = this.zzfma.zzald();
                    zzew(zzald);
                    zzall = this.zzfma.zzall() + zzald;
                    do {
                        zzbqn.zzd(this.zzfma.readDouble());
                    } while (this.zzfma.zzall() < zzall);
                    return;
                default:
                    throw zzbrl.zzanh();
            }
            do {
                zzbqn.zzd(this.zzfma.readDouble());
                if (!this.zzfma.zzalk()) {
                    zzald = this.zzfma.zzaku();
                } else {
                    return;
                }
            } while (zzald == this.tag);
            this.zzfmc = zzald;
            return;
        }
        switch (this.tag & 7) {
            case 1:
                break;
            case 2:
                zzald = this.zzfma.zzald();
                zzew(zzald);
                zzall = this.zzfma.zzall() + zzald;
                do {
                    list.add(Double.valueOf(this.zzfma.readDouble()));
                } while (this.zzfma.zzall() < zzall);
                return;
            default:
                throw zzbrl.zzanh();
        }
        do {
            list.add(Double.valueOf(this.zzfma.readDouble()));
            if (!this.zzfma.zzalk()) {
                zzald = this.zzfma.zzaku();
            } else {
                return;
            }
        } while (zzald == this.tag);
        this.zzfmc = zzald;
    }

    public final void zzq(List<Float> list) throws IOException {
        if (list instanceof zzbra) {
            zzbra zzbra = (zzbra) list;
            int i = this.tag & 7;
            if (i == 2) {
                i = this.zzfma.zzald();
                zzex(i);
                int zzall = this.zzfma.zzall() + i;
                do {
                    zzbra.zzh(this.zzfma.readFloat());
                } while (this.zzfma.zzall() < zzall);
                return;
            } else if (i != 5) {
                throw zzbrl.zzanh();
            } else {
                do {
                    zzbra.zzh(this.zzfma.readFloat());
                    if (!this.zzfma.zzalk()) {
                        i = this.zzfma.zzaku();
                    } else {
                        return;
                    }
                } while (i == this.tag);
                this.zzfmc = i;
                return;
            }
        }
        int i2 = this.tag & 7;
        if (i2 == 2) {
            i2 = this.zzfma.zzald();
            zzex(i2);
            int zzall2 = this.zzfma.zzall() + i2;
            do {
                list.add(Float.valueOf(this.zzfma.readFloat()));
            } while (this.zzfma.zzall() < zzall2);
        } else if (i2 != 5) {
            throw zzbrl.zzanh();
        } else {
            do {
                list.add(Float.valueOf(this.zzfma.readFloat()));
                if (!this.zzfma.zzalk()) {
                    i2 = this.zzfma.zzaku();
                } else {
                    return;
                }
            } while (i2 == this.tag);
            this.zzfmc = i2;
        }
    }

    public final void zzr(List<Long> list) throws IOException {
        int zzall;
        if (list instanceof zzbrz) {
            zzbrz zzbrz = (zzbrz) list;
            int i = this.tag & 7;
            if (i == 0) {
                do {
                    zzbrz.zzbj(this.zzfma.zzakv());
                    if (!this.zzfma.zzalk()) {
                        i = this.zzfma.zzaku();
                    } else {
                        return;
                    }
                } while (i == this.tag);
                this.zzfmc = i;
                return;
            } else if (i != 2) {
                throw zzbrl.zzanh();
            } else {
                zzall = this.zzfma.zzall() + this.zzfma.zzald();
                do {
                    zzbrz.zzbj(this.zzfma.zzakv());
                } while (this.zzfma.zzall() < zzall);
                zzey(zzall);
                return;
            }
        }
        int i2 = this.tag & 7;
        if (i2 == 0) {
            do {
                list.add(Long.valueOf(this.zzfma.zzakv()));
                if (!this.zzfma.zzalk()) {
                    i2 = this.zzfma.zzaku();
                } else {
                    return;
                }
            } while (i2 == this.tag);
            this.zzfmc = i2;
        } else if (i2 != 2) {
            throw zzbrl.zzanh();
        } else {
            zzall = this.zzfma.zzall() + this.zzfma.zzald();
            do {
                list.add(Long.valueOf(this.zzfma.zzakv()));
            } while (this.zzfma.zzall() < zzall);
            zzey(zzall);
        }
    }

    public final void zzs(List<Long> list) throws IOException {
        int zzall;
        if (list instanceof zzbrz) {
            zzbrz zzbrz = (zzbrz) list;
            int i = this.tag & 7;
            if (i == 0) {
                do {
                    zzbrz.zzbj(this.zzfma.zzakw());
                    if (!this.zzfma.zzalk()) {
                        i = this.zzfma.zzaku();
                    } else {
                        return;
                    }
                } while (i == this.tag);
                this.zzfmc = i;
                return;
            } else if (i != 2) {
                throw zzbrl.zzanh();
            } else {
                zzall = this.zzfma.zzall() + this.zzfma.zzald();
                do {
                    zzbrz.zzbj(this.zzfma.zzakw());
                } while (this.zzfma.zzall() < zzall);
                zzey(zzall);
                return;
            }
        }
        int i2 = this.tag & 7;
        if (i2 == 0) {
            do {
                list.add(Long.valueOf(this.zzfma.zzakw()));
                if (!this.zzfma.zzalk()) {
                    i2 = this.zzfma.zzaku();
                } else {
                    return;
                }
            } while (i2 == this.tag);
            this.zzfmc = i2;
        } else if (i2 != 2) {
            throw zzbrl.zzanh();
        } else {
            zzall = this.zzfma.zzall() + this.zzfma.zzald();
            do {
                list.add(Long.valueOf(this.zzfma.zzakw()));
            } while (this.zzfma.zzall() < zzall);
            zzey(zzall);
        }
    }

    public final void zzt(List<Integer> list) throws IOException {
        int zzall;
        if (list instanceof zzbre) {
            zzbre zzbre = (zzbre) list;
            int i = this.tag & 7;
            if (i == 0) {
                do {
                    zzbre.zzfo(this.zzfma.zzakx());
                    if (!this.zzfma.zzalk()) {
                        i = this.zzfma.zzaku();
                    } else {
                        return;
                    }
                } while (i == this.tag);
                this.zzfmc = i;
                return;
            } else if (i != 2) {
                throw zzbrl.zzanh();
            } else {
                zzall = this.zzfma.zzall() + this.zzfma.zzald();
                do {
                    zzbre.zzfo(this.zzfma.zzakx());
                } while (this.zzfma.zzall() < zzall);
                zzey(zzall);
                return;
            }
        }
        int i2 = this.tag & 7;
        if (i2 == 0) {
            do {
                list.add(Integer.valueOf(this.zzfma.zzakx()));
                if (!this.zzfma.zzalk()) {
                    i2 = this.zzfma.zzaku();
                } else {
                    return;
                }
            } while (i2 == this.tag);
            this.zzfmc = i2;
        } else if (i2 != 2) {
            throw zzbrl.zzanh();
        } else {
            zzall = this.zzfma.zzall() + this.zzfma.zzald();
            do {
                list.add(Integer.valueOf(this.zzfma.zzakx()));
            } while (this.zzfma.zzall() < zzall);
            zzey(zzall);
        }
    }

    public final void zzu(List<Long> list) throws IOException {
        int zzald;
        int zzall;
        if (list instanceof zzbrz) {
            zzbrz zzbrz = (zzbrz) list;
            switch (this.tag & 7) {
                case 1:
                    break;
                case 2:
                    zzald = this.zzfma.zzald();
                    zzew(zzald);
                    zzall = this.zzfma.zzall() + zzald;
                    do {
                        zzbrz.zzbj(this.zzfma.zzaky());
                    } while (this.zzfma.zzall() < zzall);
                    return;
                default:
                    throw zzbrl.zzanh();
            }
            do {
                zzbrz.zzbj(this.zzfma.zzaky());
                if (!this.zzfma.zzalk()) {
                    zzald = this.zzfma.zzaku();
                } else {
                    return;
                }
            } while (zzald == this.tag);
            this.zzfmc = zzald;
            return;
        }
        switch (this.tag & 7) {
            case 1:
                break;
            case 2:
                zzald = this.zzfma.zzald();
                zzew(zzald);
                zzall = this.zzfma.zzall() + zzald;
                do {
                    list.add(Long.valueOf(this.zzfma.zzaky()));
                } while (this.zzfma.zzall() < zzall);
                return;
            default:
                throw zzbrl.zzanh();
        }
        do {
            list.add(Long.valueOf(this.zzfma.zzaky()));
            if (!this.zzfma.zzalk()) {
                zzald = this.zzfma.zzaku();
            } else {
                return;
            }
        } while (zzald == this.tag);
        this.zzfmc = zzald;
    }

    public final void zzv(List<Integer> list) throws IOException {
        if (list instanceof zzbre) {
            zzbre zzbre = (zzbre) list;
            int i = this.tag & 7;
            if (i == 2) {
                i = this.zzfma.zzald();
                zzex(i);
                int zzall = this.zzfma.zzall() + i;
                do {
                    zzbre.zzfo(this.zzfma.zzakz());
                } while (this.zzfma.zzall() < zzall);
                return;
            } else if (i != 5) {
                throw zzbrl.zzanh();
            } else {
                do {
                    zzbre.zzfo(this.zzfma.zzakz());
                    if (!this.zzfma.zzalk()) {
                        i = this.zzfma.zzaku();
                    } else {
                        return;
                    }
                } while (i == this.tag);
                this.zzfmc = i;
                return;
            }
        }
        int i2 = this.tag & 7;
        if (i2 == 2) {
            i2 = this.zzfma.zzald();
            zzex(i2);
            int zzall2 = this.zzfma.zzall() + i2;
            do {
                list.add(Integer.valueOf(this.zzfma.zzakz()));
            } while (this.zzfma.zzall() < zzall2);
        } else if (i2 != 5) {
            throw zzbrl.zzanh();
        } else {
            do {
                list.add(Integer.valueOf(this.zzfma.zzakz()));
                if (!this.zzfma.zzalk()) {
                    i2 = this.zzfma.zzaku();
                } else {
                    return;
                }
            } while (i2 == this.tag);
            this.zzfmc = i2;
        }
    }

    public final void zzw(List<Boolean> list) throws IOException {
        int zzall;
        if (list instanceof zzbps) {
            zzbps zzbps = (zzbps) list;
            int i = this.tag & 7;
            if (i == 0) {
                do {
                    zzbps.addBoolean(this.zzfma.zzala());
                    if (!this.zzfma.zzalk()) {
                        i = this.zzfma.zzaku();
                    } else {
                        return;
                    }
                } while (i == this.tag);
                this.zzfmc = i;
                return;
            } else if (i != 2) {
                throw zzbrl.zzanh();
            } else {
                zzall = this.zzfma.zzall() + this.zzfma.zzald();
                do {
                    zzbps.addBoolean(this.zzfma.zzala());
                } while (this.zzfma.zzall() < zzall);
                zzey(zzall);
                return;
            }
        }
        int i2 = this.tag & 7;
        if (i2 == 0) {
            do {
                list.add(Boolean.valueOf(this.zzfma.zzala()));
                if (!this.zzfma.zzalk()) {
                    i2 = this.zzfma.zzaku();
                } else {
                    return;
                }
            } while (i2 == this.tag);
            this.zzfmc = i2;
        } else if (i2 != 2) {
            throw zzbrl.zzanh();
        } else {
            zzall = this.zzfma.zzall() + this.zzfma.zzald();
            do {
                list.add(Boolean.valueOf(this.zzfma.zzala()));
            } while (this.zzfma.zzall() < zzall);
            zzey(zzall);
        }
    }

    public final void readStringList(List<String> list) throws IOException {
        zzb((List) list, false);
    }

    public final void zzx(List<String> list) throws IOException {
        zzb((List) list, true);
    }

    private final void zzb(List<String> list, boolean z) throws IOException {
        if ((this.tag & 7) != 2) {
            throw zzbrl.zzanh();
        } else if (!(list instanceof zzbru) || z) {
            int zzaku;
            do {
                list.add(z ? zzalb() : readString());
                if (!this.zzfma.zzalk()) {
                    zzaku = this.zzfma.zzaku();
                } else {
                    return;
                }
            } while (zzaku == this.tag);
            this.zzfmc = zzaku;
        } else {
            int zzaku2;
            zzbru zzbru = (zzbru) list;
            do {
                zzbru.zzap(zzalc());
                if (!this.zzfma.zzalk()) {
                    zzaku2 = this.zzfma.zzaku();
                } else {
                    return;
                }
            } while (zzaku2 == this.tag);
            this.zzfmc = zzaku2;
        }
    }

    public final <T> void zza(List<T> list, zzbtc<T> zzbtc, zzbqq zzbqq) throws IOException {
        if ((this.tag & 7) != 2) {
            throw zzbrl.zzanh();
        }
        int zzaku;
        int i = this.tag;
        do {
            list.add(zzc(zzbtc, zzbqq));
            if (!this.zzfma.zzalk() && this.zzfmc == 0) {
                zzaku = this.zzfma.zzaku();
            } else {
                return;
            }
        } while (zzaku == i);
        this.zzfmc = zzaku;
    }

    public final <T> void zzb(List<T> list, zzbtc<T> zzbtc, zzbqq zzbqq) throws IOException {
        if ((this.tag & 7) != 3) {
            throw zzbrl.zzanh();
        }
        int zzaku;
        int i = this.tag;
        do {
            list.add(zzd(zzbtc, zzbqq));
            if (!this.zzfma.zzalk() && this.zzfmc == 0) {
                zzaku = this.zzfma.zzaku();
            } else {
                return;
            }
        } while (zzaku == i);
        this.zzfmc = zzaku;
    }

    public final void zzy(List<zzbpu> list) throws IOException {
        if ((this.tag & 7) != 2) {
            throw zzbrl.zzanh();
        }
        int zzaku;
        do {
            list.add(zzalc());
            if (!this.zzfma.zzalk()) {
                zzaku = this.zzfma.zzaku();
            } else {
                return;
            }
        } while (zzaku == this.tag);
        this.zzfmc = zzaku;
    }

    public final void zzz(List<Integer> list) throws IOException {
        int zzall;
        if (list instanceof zzbre) {
            zzbre zzbre = (zzbre) list;
            int i = this.tag & 7;
            if (i == 0) {
                do {
                    zzbre.zzfo(this.zzfma.zzald());
                    if (!this.zzfma.zzalk()) {
                        i = this.zzfma.zzaku();
                    } else {
                        return;
                    }
                } while (i == this.tag);
                this.zzfmc = i;
                return;
            } else if (i != 2) {
                throw zzbrl.zzanh();
            } else {
                zzall = this.zzfma.zzall() + this.zzfma.zzald();
                do {
                    zzbre.zzfo(this.zzfma.zzald());
                } while (this.zzfma.zzall() < zzall);
                zzey(zzall);
                return;
            }
        }
        int i2 = this.tag & 7;
        if (i2 == 0) {
            do {
                list.add(Integer.valueOf(this.zzfma.zzald()));
                if (!this.zzfma.zzalk()) {
                    i2 = this.zzfma.zzaku();
                } else {
                    return;
                }
            } while (i2 == this.tag);
            this.zzfmc = i2;
        } else if (i2 != 2) {
            throw zzbrl.zzanh();
        } else {
            zzall = this.zzfma.zzall() + this.zzfma.zzald();
            do {
                list.add(Integer.valueOf(this.zzfma.zzald()));
            } while (this.zzfma.zzall() < zzall);
            zzey(zzall);
        }
    }

    public final void zzaa(List<Integer> list) throws IOException {
        int zzall;
        if (list instanceof zzbre) {
            zzbre zzbre = (zzbre) list;
            int i = this.tag & 7;
            if (i == 0) {
                do {
                    zzbre.zzfo(this.zzfma.zzale());
                    if (!this.zzfma.zzalk()) {
                        i = this.zzfma.zzaku();
                    } else {
                        return;
                    }
                } while (i == this.tag);
                this.zzfmc = i;
                return;
            } else if (i != 2) {
                throw zzbrl.zzanh();
            } else {
                zzall = this.zzfma.zzall() + this.zzfma.zzald();
                do {
                    zzbre.zzfo(this.zzfma.zzale());
                } while (this.zzfma.zzall() < zzall);
                zzey(zzall);
                return;
            }
        }
        int i2 = this.tag & 7;
        if (i2 == 0) {
            do {
                list.add(Integer.valueOf(this.zzfma.zzale()));
                if (!this.zzfma.zzalk()) {
                    i2 = this.zzfma.zzaku();
                } else {
                    return;
                }
            } while (i2 == this.tag);
            this.zzfmc = i2;
        } else if (i2 != 2) {
            throw zzbrl.zzanh();
        } else {
            zzall = this.zzfma.zzall() + this.zzfma.zzald();
            do {
                list.add(Integer.valueOf(this.zzfma.zzale()));
            } while (this.zzfma.zzall() < zzall);
            zzey(zzall);
        }
    }

    public final void zzab(List<Integer> list) throws IOException {
        if (list instanceof zzbre) {
            zzbre zzbre = (zzbre) list;
            int i = this.tag & 7;
            if (i == 2) {
                i = this.zzfma.zzald();
                zzex(i);
                int zzall = this.zzfma.zzall() + i;
                do {
                    zzbre.zzfo(this.zzfma.zzalf());
                } while (this.zzfma.zzall() < zzall);
                return;
            } else if (i != 5) {
                throw zzbrl.zzanh();
            } else {
                do {
                    zzbre.zzfo(this.zzfma.zzalf());
                    if (!this.zzfma.zzalk()) {
                        i = this.zzfma.zzaku();
                    } else {
                        return;
                    }
                } while (i == this.tag);
                this.zzfmc = i;
                return;
            }
        }
        int i2 = this.tag & 7;
        if (i2 == 2) {
            i2 = this.zzfma.zzald();
            zzex(i2);
            int zzall2 = this.zzfma.zzall() + i2;
            do {
                list.add(Integer.valueOf(this.zzfma.zzalf()));
            } while (this.zzfma.zzall() < zzall2);
        } else if (i2 != 5) {
            throw zzbrl.zzanh();
        } else {
            do {
                list.add(Integer.valueOf(this.zzfma.zzalf()));
                if (!this.zzfma.zzalk()) {
                    i2 = this.zzfma.zzaku();
                } else {
                    return;
                }
            } while (i2 == this.tag);
            this.zzfmc = i2;
        }
    }

    public final void zzac(List<Long> list) throws IOException {
        int zzald;
        int zzall;
        if (list instanceof zzbrz) {
            zzbrz zzbrz = (zzbrz) list;
            switch (this.tag & 7) {
                case 1:
                    break;
                case 2:
                    zzald = this.zzfma.zzald();
                    zzew(zzald);
                    zzall = this.zzfma.zzall() + zzald;
                    do {
                        zzbrz.zzbj(this.zzfma.zzalg());
                    } while (this.zzfma.zzall() < zzall);
                    return;
                default:
                    throw zzbrl.zzanh();
            }
            do {
                zzbrz.zzbj(this.zzfma.zzalg());
                if (!this.zzfma.zzalk()) {
                    zzald = this.zzfma.zzaku();
                } else {
                    return;
                }
            } while (zzald == this.tag);
            this.zzfmc = zzald;
            return;
        }
        switch (this.tag & 7) {
            case 1:
                break;
            case 2:
                zzald = this.zzfma.zzald();
                zzew(zzald);
                zzall = this.zzfma.zzall() + zzald;
                do {
                    list.add(Long.valueOf(this.zzfma.zzalg()));
                } while (this.zzfma.zzall() < zzall);
                return;
            default:
                throw zzbrl.zzanh();
        }
        do {
            list.add(Long.valueOf(this.zzfma.zzalg()));
            if (!this.zzfma.zzalk()) {
                zzald = this.zzfma.zzaku();
            } else {
                return;
            }
        } while (zzald == this.tag);
        this.zzfmc = zzald;
    }

    public final void zzad(List<Integer> list) throws IOException {
        int zzall;
        if (list instanceof zzbre) {
            zzbre zzbre = (zzbre) list;
            int i = this.tag & 7;
            if (i == 0) {
                do {
                    zzbre.zzfo(this.zzfma.zzalh());
                    if (!this.zzfma.zzalk()) {
                        i = this.zzfma.zzaku();
                    } else {
                        return;
                    }
                } while (i == this.tag);
                this.zzfmc = i;
                return;
            } else if (i != 2) {
                throw zzbrl.zzanh();
            } else {
                zzall = this.zzfma.zzall() + this.zzfma.zzald();
                do {
                    zzbre.zzfo(this.zzfma.zzalh());
                } while (this.zzfma.zzall() < zzall);
                zzey(zzall);
                return;
            }
        }
        int i2 = this.tag & 7;
        if (i2 == 0) {
            do {
                list.add(Integer.valueOf(this.zzfma.zzalh()));
                if (!this.zzfma.zzalk()) {
                    i2 = this.zzfma.zzaku();
                } else {
                    return;
                }
            } while (i2 == this.tag);
            this.zzfmc = i2;
        } else if (i2 != 2) {
            throw zzbrl.zzanh();
        } else {
            zzall = this.zzfma.zzall() + this.zzfma.zzald();
            do {
                list.add(Integer.valueOf(this.zzfma.zzalh()));
            } while (this.zzfma.zzall() < zzall);
            zzey(zzall);
        }
    }

    public final void zzae(List<Long> list) throws IOException {
        int zzall;
        if (list instanceof zzbrz) {
            zzbrz zzbrz = (zzbrz) list;
            int i = this.tag & 7;
            if (i == 0) {
                do {
                    zzbrz.zzbj(this.zzfma.zzali());
                    if (!this.zzfma.zzalk()) {
                        i = this.zzfma.zzaku();
                    } else {
                        return;
                    }
                } while (i == this.tag);
                this.zzfmc = i;
                return;
            } else if (i != 2) {
                throw zzbrl.zzanh();
            } else {
                zzall = this.zzfma.zzall() + this.zzfma.zzald();
                do {
                    zzbrz.zzbj(this.zzfma.zzali());
                } while (this.zzfma.zzall() < zzall);
                zzey(zzall);
                return;
            }
        }
        int i2 = this.tag & 7;
        if (i2 == 0) {
            do {
                list.add(Long.valueOf(this.zzfma.zzali()));
                if (!this.zzfma.zzalk()) {
                    i2 = this.zzfma.zzaku();
                } else {
                    return;
                }
            } while (i2 == this.tag);
            this.zzfmc = i2;
        } else if (i2 != 2) {
            throw zzbrl.zzanh();
        } else {
            zzall = this.zzfma.zzall() + this.zzfma.zzald();
            do {
                list.add(Long.valueOf(this.zzfma.zzali()));
            } while (this.zzfma.zzall() < zzall);
            zzey(zzall);
        }
    }

    private static void zzew(int i) throws IOException {
        if ((i & 7) != 0) {
            throw zzbrl.zzanj();
        }
    }

    /* JADX WARNING: Missing exception handler attribute for start block: B:14:0x004e */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing block: B:16:0x0052, code skipped:
            if (zzalt() == false) goto L_0x0054;
     */
    /* JADX WARNING: Missing block: B:18:0x005b, code skipped:
            throw new com.google.android.gms.internal.ads.zzbrl("Unable to parse map entry.");
     */
    public final <K, V> void zza(java.util.Map<K, V> r6, com.google.android.gms.internal.ads.zzbse<K, V> r7, com.google.android.gms.internal.ads.zzbqq r8) throws java.io.IOException {
        /*
        r5 = this;
        r0 = 2;
        r5.zzev(r0);
        r0 = r5.zzfma;
        r0 = r0.zzald();
        r1 = r5.zzfma;
        r0 = r1.zzer(r0);
        r1 = r7.zzfsa;
        r2 = r7.zzfsc;
    L_0x0014:
        r3 = r5.zzals();	 Catch:{ all -> 0x0065 }
        r4 = 2147483647; // 0x7fffffff float:NaN double:1.060997895E-314;
        if (r3 == r4) goto L_0x005c;
    L_0x001d:
        r4 = r5.zzfma;	 Catch:{ all -> 0x0065 }
        r4 = r4.zzalk();	 Catch:{ all -> 0x0065 }
        if (r4 != 0) goto L_0x005c;
    L_0x0025:
        switch(r3) {
            case 1: goto L_0x003b;
            case 2: goto L_0x002d;
            default: goto L_0x0028;
        };
    L_0x0028:
        r3 = r5.zzalt();	 Catch:{ zzbrm -> 0x004e }
        goto L_0x0044;
    L_0x002d:
        r3 = r7.zzfsb;	 Catch:{ zzbrm -> 0x004e }
        r4 = r7.zzfsc;	 Catch:{ zzbrm -> 0x004e }
        r4 = r4.getClass();	 Catch:{ zzbrm -> 0x004e }
        r3 = r5.zza(r3, r4, r8);	 Catch:{ zzbrm -> 0x004e }
        r2 = r3;
        goto L_0x0014;
    L_0x003b:
        r3 = r7.zzfrz;	 Catch:{ zzbrm -> 0x004e }
        r4 = 0;
        r3 = r5.zza(r3, r4, r4);	 Catch:{ zzbrm -> 0x004e }
        r1 = r3;
        goto L_0x0014;
    L_0x0044:
        if (r3 != 0) goto L_0x0014;
    L_0x0046:
        r3 = new com.google.android.gms.internal.ads.zzbrl;	 Catch:{ zzbrm -> 0x004e }
        r4 = "Unable to parse map entry.";
        r3.<init>(r4);	 Catch:{ zzbrm -> 0x004e }
        throw r3;	 Catch:{ zzbrm -> 0x004e }
    L_0x004e:
        r3 = r5.zzalt();	 Catch:{ all -> 0x0065 }
        if (r3 != 0) goto L_0x0014;
    L_0x0054:
        r6 = new com.google.android.gms.internal.ads.zzbrl;	 Catch:{ all -> 0x0065 }
        r7 = "Unable to parse map entry.";
        r6.<init>(r7);	 Catch:{ all -> 0x0065 }
        throw r6;	 Catch:{ all -> 0x0065 }
    L_0x005c:
        r6.put(r1, r2);	 Catch:{ all -> 0x0065 }
        r6 = r5.zzfma;
        r6.zzes(r0);
        return;
    L_0x0065:
        r6 = move-exception;
        r7 = r5.zzfma;
        r7.zzes(r0);
        throw r6;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzbqi.zza(java.util.Map, com.google.android.gms.internal.ads.zzbse, com.google.android.gms.internal.ads.zzbqq):void");
    }

    private final Object zza(zzbuj zzbuj, Class<?> cls, zzbqq zzbqq) throws IOException {
        switch (zzbqj.zzfmd[zzbuj.ordinal()]) {
            case 1:
                return Boolean.valueOf(zzala());
            case 2:
                return zzalc();
            case 3:
                return Double.valueOf(readDouble());
            case 4:
                return Integer.valueOf(zzale());
            case 5:
                return Integer.valueOf(zzakz());
            case 6:
                return Long.valueOf(zzaky());
            case 7:
                return Float.valueOf(readFloat());
            case 8:
                return Integer.valueOf(zzakx());
            case 9:
                return Long.valueOf(zzakw());
            case 10:
                zzev(2);
                return zzc(zzbsy.zzaog().zzf(cls), zzbqq);
            case 11:
                return Integer.valueOf(zzalf());
            case 12:
                return Long.valueOf(zzalg());
            case 13:
                return Integer.valueOf(zzalh());
            case 14:
                return Long.valueOf(zzali());
            case 15:
                return zzalb();
            case 16:
                return Integer.valueOf(zzald());
            case 17:
                return Long.valueOf(zzakv());
            default:
                throw new RuntimeException("unsupported field type.");
        }
    }

    private static void zzex(int i) throws IOException {
        if ((i & 3) != 0) {
            throw zzbrl.zzanj();
        }
    }

    private final void zzey(int i) throws IOException {
        if (this.zzfma.zzall() != i) {
            throw zzbrl.zzanc();
        }
    }
}
