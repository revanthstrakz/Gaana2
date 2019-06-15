package com.google.android.gms.internal.measurement;

import java.io.IOException;
import java.util.List;

final class zztt implements zzwk {
    private int tag;
    private final zztq zzbui;
    private int zzbuj;
    private int zzbuk = 0;

    public static zztt zza(zztq zztq) {
        if (zztq.zzbub != null) {
            return zztq.zzbub;
        }
        return new zztt(zztq);
    }

    private zztt(zztq zztq) {
        this.zzbui = (zztq) zzuq.zza(zztq, "input");
        this.zzbui.zzbub = this;
    }

    public final int zzvh() throws IOException {
        if (this.zzbuk != 0) {
            this.tag = this.zzbuk;
            this.zzbuk = 0;
        } else {
            this.tag = this.zzbui.zzuj();
        }
        return (this.tag == 0 || this.tag == this.zzbuj) ? Integer.MAX_VALUE : this.tag >>> 3;
    }

    public final int getTag() {
        return this.tag;
    }

    public final boolean zzvi() throws IOException {
        return (this.zzbui.zzuz() || this.tag == this.zzbuj) ? false : this.zzbui.zzaq(this.tag);
    }

    private final void zzav(int i) throws IOException {
        if ((this.tag & 7) != i) {
            throw zzuv.zzwu();
        }
    }

    public final double readDouble() throws IOException {
        zzav(1);
        return this.zzbui.readDouble();
    }

    public final float readFloat() throws IOException {
        zzav(5);
        return this.zzbui.readFloat();
    }

    public final long zzuk() throws IOException {
        zzav(0);
        return this.zzbui.zzuk();
    }

    public final long zzul() throws IOException {
        zzav(0);
        return this.zzbui.zzul();
    }

    public final int zzum() throws IOException {
        zzav(0);
        return this.zzbui.zzum();
    }

    public final long zzun() throws IOException {
        zzav(1);
        return this.zzbui.zzun();
    }

    public final int zzuo() throws IOException {
        zzav(5);
        return this.zzbui.zzuo();
    }

    public final boolean zzup() throws IOException {
        zzav(0);
        return this.zzbui.zzup();
    }

    public final String readString() throws IOException {
        zzav(2);
        return this.zzbui.readString();
    }

    public final String zzuq() throws IOException {
        zzav(2);
        return this.zzbui.zzuq();
    }

    public final <T> T zza(zzwl<T> zzwl, zzub zzub) throws IOException {
        zzav(2);
        return zzc(zzwl, zzub);
    }

    public final <T> T zzb(zzwl<T> zzwl, zzub zzub) throws IOException {
        zzav(3);
        return zzd(zzwl, zzub);
    }

    private final <T> T zzc(zzwl<T> zzwl, zzub zzub) throws IOException {
        int zzus = this.zzbui.zzus();
        if (this.zzbui.zzbty >= this.zzbui.zzbtz) {
            throw zzuv.zzwv();
        }
        zzus = this.zzbui.zzas(zzus);
        Object newInstance = zzwl.newInstance();
        zztq zztq = this.zzbui;
        zztq.zzbty++;
        zzwl.zza(newInstance, this, zzub);
        zzwl.zzy(newInstance);
        this.zzbui.zzap(0);
        zztq zztq2 = this.zzbui;
        zztq2.zzbty--;
        this.zzbui.zzat(zzus);
        return newInstance;
    }

    private final <T> T zzd(zzwl<T> zzwl, zzub zzub) throws IOException {
        int i = this.zzbuj;
        T t = ((this.tag >>> 3) << 3) | 4;
        this.zzbuj = t;
        try {
            t = zzwl.newInstance();
            zzwl.zza(t, this, zzub);
            zzwl.zzy(t);
            if (this.tag == this.zzbuj) {
                return t;
            }
            throw zzuv.zzww();
        } finally {
            this.zzbuj = i;
        }
    }

    public final zzte zzur() throws IOException {
        zzav(2);
        return this.zzbui.zzur();
    }

    public final int zzus() throws IOException {
        zzav(0);
        return this.zzbui.zzus();
    }

    public final int zzut() throws IOException {
        zzav(0);
        return this.zzbui.zzut();
    }

    public final int zzuu() throws IOException {
        zzav(5);
        return this.zzbui.zzuu();
    }

    public final long zzuv() throws IOException {
        zzav(1);
        return this.zzbui.zzuv();
    }

    public final int zzuw() throws IOException {
        zzav(0);
        return this.zzbui.zzuw();
    }

    public final long zzux() throws IOException {
        zzav(0);
        return this.zzbui.zzux();
    }

    public final void zzi(List<Double> list) throws IOException {
        int zzus;
        int zzva;
        if (list instanceof zzty) {
            zzty zzty = (zzty) list;
            switch (this.tag & 7) {
                case 1:
                    break;
                case 2:
                    zzus = this.zzbui.zzus();
                    zzaw(zzus);
                    zzva = this.zzbui.zzva() + zzus;
                    do {
                        zzty.zzd(this.zzbui.readDouble());
                    } while (this.zzbui.zzva() < zzva);
                    return;
                default:
                    throw zzuv.zzwu();
            }
            do {
                zzty.zzd(this.zzbui.readDouble());
                if (!this.zzbui.zzuz()) {
                    zzus = this.zzbui.zzuj();
                } else {
                    return;
                }
            } while (zzus == this.tag);
            this.zzbuk = zzus;
            return;
        }
        switch (this.tag & 7) {
            case 1:
                break;
            case 2:
                zzus = this.zzbui.zzus();
                zzaw(zzus);
                zzva = this.zzbui.zzva() + zzus;
                do {
                    list.add(Double.valueOf(this.zzbui.readDouble()));
                } while (this.zzbui.zzva() < zzva);
                return;
            default:
                throw zzuv.zzwu();
        }
        do {
            list.add(Double.valueOf(this.zzbui.readDouble()));
            if (!this.zzbui.zzuz()) {
                zzus = this.zzbui.zzuj();
            } else {
                return;
            }
        } while (zzus == this.tag);
        this.zzbuk = zzus;
    }

    public final void zzj(List<Float> list) throws IOException {
        if (list instanceof zzul) {
            zzul zzul = (zzul) list;
            int i = this.tag & 7;
            if (i == 2) {
                i = this.zzbui.zzus();
                zzax(i);
                int zzva = this.zzbui.zzva() + i;
                do {
                    zzul.zzc(this.zzbui.readFloat());
                } while (this.zzbui.zzva() < zzva);
                return;
            } else if (i != 5) {
                throw zzuv.zzwu();
            } else {
                do {
                    zzul.zzc(this.zzbui.readFloat());
                    if (!this.zzbui.zzuz()) {
                        i = this.zzbui.zzuj();
                    } else {
                        return;
                    }
                } while (i == this.tag);
                this.zzbuk = i;
                return;
            }
        }
        int i2 = this.tag & 7;
        if (i2 == 2) {
            i2 = this.zzbui.zzus();
            zzax(i2);
            int zzva2 = this.zzbui.zzva() + i2;
            do {
                list.add(Float.valueOf(this.zzbui.readFloat()));
            } while (this.zzbui.zzva() < zzva2);
        } else if (i2 != 5) {
            throw zzuv.zzwu();
        } else {
            do {
                list.add(Float.valueOf(this.zzbui.readFloat()));
                if (!this.zzbui.zzuz()) {
                    i2 = this.zzbui.zzuj();
                } else {
                    return;
                }
            } while (i2 == this.tag);
            this.zzbuk = i2;
        }
    }

    public final void zzk(List<Long> list) throws IOException {
        int zzva;
        if (list instanceof zzvj) {
            zzvj zzvj = (zzvj) list;
            int i = this.tag & 7;
            if (i == 0) {
                do {
                    zzvj.zzbe(this.zzbui.zzuk());
                    if (!this.zzbui.zzuz()) {
                        i = this.zzbui.zzuj();
                    } else {
                        return;
                    }
                } while (i == this.tag);
                this.zzbuk = i;
                return;
            } else if (i != 2) {
                throw zzuv.zzwu();
            } else {
                zzva = this.zzbui.zzva() + this.zzbui.zzus();
                do {
                    zzvj.zzbe(this.zzbui.zzuk());
                } while (this.zzbui.zzva() < zzva);
                zzay(zzva);
                return;
            }
        }
        int i2 = this.tag & 7;
        if (i2 == 0) {
            do {
                list.add(Long.valueOf(this.zzbui.zzuk()));
                if (!this.zzbui.zzuz()) {
                    i2 = this.zzbui.zzuj();
                } else {
                    return;
                }
            } while (i2 == this.tag);
            this.zzbuk = i2;
        } else if (i2 != 2) {
            throw zzuv.zzwu();
        } else {
            zzva = this.zzbui.zzva() + this.zzbui.zzus();
            do {
                list.add(Long.valueOf(this.zzbui.zzuk()));
            } while (this.zzbui.zzva() < zzva);
            zzay(zzva);
        }
    }

    public final void zzl(List<Long> list) throws IOException {
        int zzva;
        if (list instanceof zzvj) {
            zzvj zzvj = (zzvj) list;
            int i = this.tag & 7;
            if (i == 0) {
                do {
                    zzvj.zzbe(this.zzbui.zzul());
                    if (!this.zzbui.zzuz()) {
                        i = this.zzbui.zzuj();
                    } else {
                        return;
                    }
                } while (i == this.tag);
                this.zzbuk = i;
                return;
            } else if (i != 2) {
                throw zzuv.zzwu();
            } else {
                zzva = this.zzbui.zzva() + this.zzbui.zzus();
                do {
                    zzvj.zzbe(this.zzbui.zzul());
                } while (this.zzbui.zzva() < zzva);
                zzay(zzva);
                return;
            }
        }
        int i2 = this.tag & 7;
        if (i2 == 0) {
            do {
                list.add(Long.valueOf(this.zzbui.zzul()));
                if (!this.zzbui.zzuz()) {
                    i2 = this.zzbui.zzuj();
                } else {
                    return;
                }
            } while (i2 == this.tag);
            this.zzbuk = i2;
        } else if (i2 != 2) {
            throw zzuv.zzwu();
        } else {
            zzva = this.zzbui.zzva() + this.zzbui.zzus();
            do {
                list.add(Long.valueOf(this.zzbui.zzul()));
            } while (this.zzbui.zzva() < zzva);
            zzay(zzva);
        }
    }

    public final void zzm(List<Integer> list) throws IOException {
        int zzva;
        if (list instanceof zzup) {
            zzup zzup = (zzup) list;
            int i = this.tag & 7;
            if (i == 0) {
                do {
                    zzup.zzbo(this.zzbui.zzum());
                    if (!this.zzbui.zzuz()) {
                        i = this.zzbui.zzuj();
                    } else {
                        return;
                    }
                } while (i == this.tag);
                this.zzbuk = i;
                return;
            } else if (i != 2) {
                throw zzuv.zzwu();
            } else {
                zzva = this.zzbui.zzva() + this.zzbui.zzus();
                do {
                    zzup.zzbo(this.zzbui.zzum());
                } while (this.zzbui.zzva() < zzva);
                zzay(zzva);
                return;
            }
        }
        int i2 = this.tag & 7;
        if (i2 == 0) {
            do {
                list.add(Integer.valueOf(this.zzbui.zzum()));
                if (!this.zzbui.zzuz()) {
                    i2 = this.zzbui.zzuj();
                } else {
                    return;
                }
            } while (i2 == this.tag);
            this.zzbuk = i2;
        } else if (i2 != 2) {
            throw zzuv.zzwu();
        } else {
            zzva = this.zzbui.zzva() + this.zzbui.zzus();
            do {
                list.add(Integer.valueOf(this.zzbui.zzum()));
            } while (this.zzbui.zzva() < zzva);
            zzay(zzva);
        }
    }

    public final void zzn(List<Long> list) throws IOException {
        int zzus;
        int zzva;
        if (list instanceof zzvj) {
            zzvj zzvj = (zzvj) list;
            switch (this.tag & 7) {
                case 1:
                    break;
                case 2:
                    zzus = this.zzbui.zzus();
                    zzaw(zzus);
                    zzva = this.zzbui.zzva() + zzus;
                    do {
                        zzvj.zzbe(this.zzbui.zzun());
                    } while (this.zzbui.zzva() < zzva);
                    return;
                default:
                    throw zzuv.zzwu();
            }
            do {
                zzvj.zzbe(this.zzbui.zzun());
                if (!this.zzbui.zzuz()) {
                    zzus = this.zzbui.zzuj();
                } else {
                    return;
                }
            } while (zzus == this.tag);
            this.zzbuk = zzus;
            return;
        }
        switch (this.tag & 7) {
            case 1:
                break;
            case 2:
                zzus = this.zzbui.zzus();
                zzaw(zzus);
                zzva = this.zzbui.zzva() + zzus;
                do {
                    list.add(Long.valueOf(this.zzbui.zzun()));
                } while (this.zzbui.zzva() < zzva);
                return;
            default:
                throw zzuv.zzwu();
        }
        do {
            list.add(Long.valueOf(this.zzbui.zzun()));
            if (!this.zzbui.zzuz()) {
                zzus = this.zzbui.zzuj();
            } else {
                return;
            }
        } while (zzus == this.tag);
        this.zzbuk = zzus;
    }

    public final void zzo(List<Integer> list) throws IOException {
        if (list instanceof zzup) {
            zzup zzup = (zzup) list;
            int i = this.tag & 7;
            if (i == 2) {
                i = this.zzbui.zzus();
                zzax(i);
                int zzva = this.zzbui.zzva() + i;
                do {
                    zzup.zzbo(this.zzbui.zzuo());
                } while (this.zzbui.zzva() < zzva);
                return;
            } else if (i != 5) {
                throw zzuv.zzwu();
            } else {
                do {
                    zzup.zzbo(this.zzbui.zzuo());
                    if (!this.zzbui.zzuz()) {
                        i = this.zzbui.zzuj();
                    } else {
                        return;
                    }
                } while (i == this.tag);
                this.zzbuk = i;
                return;
            }
        }
        int i2 = this.tag & 7;
        if (i2 == 2) {
            i2 = this.zzbui.zzus();
            zzax(i2);
            int zzva2 = this.zzbui.zzva() + i2;
            do {
                list.add(Integer.valueOf(this.zzbui.zzuo()));
            } while (this.zzbui.zzva() < zzva2);
        } else if (i2 != 5) {
            throw zzuv.zzwu();
        } else {
            do {
                list.add(Integer.valueOf(this.zzbui.zzuo()));
                if (!this.zzbui.zzuz()) {
                    i2 = this.zzbui.zzuj();
                } else {
                    return;
                }
            } while (i2 == this.tag);
            this.zzbuk = i2;
        }
    }

    public final void zzp(List<Boolean> list) throws IOException {
        int zzva;
        if (list instanceof zztc) {
            zztc zztc = (zztc) list;
            int i = this.tag & 7;
            if (i == 0) {
                do {
                    zztc.addBoolean(this.zzbui.zzup());
                    if (!this.zzbui.zzuz()) {
                        i = this.zzbui.zzuj();
                    } else {
                        return;
                    }
                } while (i == this.tag);
                this.zzbuk = i;
                return;
            } else if (i != 2) {
                throw zzuv.zzwu();
            } else {
                zzva = this.zzbui.zzva() + this.zzbui.zzus();
                do {
                    zztc.addBoolean(this.zzbui.zzup());
                } while (this.zzbui.zzva() < zzva);
                zzay(zzva);
                return;
            }
        }
        int i2 = this.tag & 7;
        if (i2 == 0) {
            do {
                list.add(Boolean.valueOf(this.zzbui.zzup()));
                if (!this.zzbui.zzuz()) {
                    i2 = this.zzbui.zzuj();
                } else {
                    return;
                }
            } while (i2 == this.tag);
            this.zzbuk = i2;
        } else if (i2 != 2) {
            throw zzuv.zzwu();
        } else {
            zzva = this.zzbui.zzva() + this.zzbui.zzus();
            do {
                list.add(Boolean.valueOf(this.zzbui.zzup()));
            } while (this.zzbui.zzva() < zzva);
            zzay(zzva);
        }
    }

    public final void readStringList(List<String> list) throws IOException {
        zza((List) list, false);
    }

    public final void zzq(List<String> list) throws IOException {
        zza((List) list, true);
    }

    private final void zza(List<String> list, boolean z) throws IOException {
        if ((this.tag & 7) != 2) {
            throw zzuv.zzwu();
        } else if (!(list instanceof zzve) || z) {
            int zzuj;
            do {
                list.add(z ? zzuq() : readString());
                if (!this.zzbui.zzuz()) {
                    zzuj = this.zzbui.zzuj();
                } else {
                    return;
                }
            } while (zzuj == this.tag);
            this.zzbuk = zzuj;
        } else {
            int zzuj2;
            zzve zzve = (zzve) list;
            do {
                zzve.zzc(zzur());
                if (!this.zzbui.zzuz()) {
                    zzuj2 = this.zzbui.zzuj();
                } else {
                    return;
                }
            } while (zzuj2 == this.tag);
            this.zzbuk = zzuj2;
        }
    }

    public final <T> void zza(List<T> list, zzwl<T> zzwl, zzub zzub) throws IOException {
        if ((this.tag & 7) != 2) {
            throw zzuv.zzwu();
        }
        int zzuj;
        int i = this.tag;
        do {
            list.add(zzc(zzwl, zzub));
            if (!this.zzbui.zzuz() && this.zzbuk == 0) {
                zzuj = this.zzbui.zzuj();
            } else {
                return;
            }
        } while (zzuj == i);
        this.zzbuk = zzuj;
    }

    public final <T> void zzb(List<T> list, zzwl<T> zzwl, zzub zzub) throws IOException {
        if ((this.tag & 7) != 3) {
            throw zzuv.zzwu();
        }
        int zzuj;
        int i = this.tag;
        do {
            list.add(zzd(zzwl, zzub));
            if (!this.zzbui.zzuz() && this.zzbuk == 0) {
                zzuj = this.zzbui.zzuj();
            } else {
                return;
            }
        } while (zzuj == i);
        this.zzbuk = zzuj;
    }

    public final void zzr(List<zzte> list) throws IOException {
        if ((this.tag & 7) != 2) {
            throw zzuv.zzwu();
        }
        int zzuj;
        do {
            list.add(zzur());
            if (!this.zzbui.zzuz()) {
                zzuj = this.zzbui.zzuj();
            } else {
                return;
            }
        } while (zzuj == this.tag);
        this.zzbuk = zzuj;
    }

    public final void zzs(List<Integer> list) throws IOException {
        int zzva;
        if (list instanceof zzup) {
            zzup zzup = (zzup) list;
            int i = this.tag & 7;
            if (i == 0) {
                do {
                    zzup.zzbo(this.zzbui.zzus());
                    if (!this.zzbui.zzuz()) {
                        i = this.zzbui.zzuj();
                    } else {
                        return;
                    }
                } while (i == this.tag);
                this.zzbuk = i;
                return;
            } else if (i != 2) {
                throw zzuv.zzwu();
            } else {
                zzva = this.zzbui.zzva() + this.zzbui.zzus();
                do {
                    zzup.zzbo(this.zzbui.zzus());
                } while (this.zzbui.zzva() < zzva);
                zzay(zzva);
                return;
            }
        }
        int i2 = this.tag & 7;
        if (i2 == 0) {
            do {
                list.add(Integer.valueOf(this.zzbui.zzus()));
                if (!this.zzbui.zzuz()) {
                    i2 = this.zzbui.zzuj();
                } else {
                    return;
                }
            } while (i2 == this.tag);
            this.zzbuk = i2;
        } else if (i2 != 2) {
            throw zzuv.zzwu();
        } else {
            zzva = this.zzbui.zzva() + this.zzbui.zzus();
            do {
                list.add(Integer.valueOf(this.zzbui.zzus()));
            } while (this.zzbui.zzva() < zzva);
            zzay(zzva);
        }
    }

    public final void zzt(List<Integer> list) throws IOException {
        int zzva;
        if (list instanceof zzup) {
            zzup zzup = (zzup) list;
            int i = this.tag & 7;
            if (i == 0) {
                do {
                    zzup.zzbo(this.zzbui.zzut());
                    if (!this.zzbui.zzuz()) {
                        i = this.zzbui.zzuj();
                    } else {
                        return;
                    }
                } while (i == this.tag);
                this.zzbuk = i;
                return;
            } else if (i != 2) {
                throw zzuv.zzwu();
            } else {
                zzva = this.zzbui.zzva() + this.zzbui.zzus();
                do {
                    zzup.zzbo(this.zzbui.zzut());
                } while (this.zzbui.zzva() < zzva);
                zzay(zzva);
                return;
            }
        }
        int i2 = this.tag & 7;
        if (i2 == 0) {
            do {
                list.add(Integer.valueOf(this.zzbui.zzut()));
                if (!this.zzbui.zzuz()) {
                    i2 = this.zzbui.zzuj();
                } else {
                    return;
                }
            } while (i2 == this.tag);
            this.zzbuk = i2;
        } else if (i2 != 2) {
            throw zzuv.zzwu();
        } else {
            zzva = this.zzbui.zzva() + this.zzbui.zzus();
            do {
                list.add(Integer.valueOf(this.zzbui.zzut()));
            } while (this.zzbui.zzva() < zzva);
            zzay(zzva);
        }
    }

    public final void zzu(List<Integer> list) throws IOException {
        if (list instanceof zzup) {
            zzup zzup = (zzup) list;
            int i = this.tag & 7;
            if (i == 2) {
                i = this.zzbui.zzus();
                zzax(i);
                int zzva = this.zzbui.zzva() + i;
                do {
                    zzup.zzbo(this.zzbui.zzuu());
                } while (this.zzbui.zzva() < zzva);
                return;
            } else if (i != 5) {
                throw zzuv.zzwu();
            } else {
                do {
                    zzup.zzbo(this.zzbui.zzuu());
                    if (!this.zzbui.zzuz()) {
                        i = this.zzbui.zzuj();
                    } else {
                        return;
                    }
                } while (i == this.tag);
                this.zzbuk = i;
                return;
            }
        }
        int i2 = this.tag & 7;
        if (i2 == 2) {
            i2 = this.zzbui.zzus();
            zzax(i2);
            int zzva2 = this.zzbui.zzva() + i2;
            do {
                list.add(Integer.valueOf(this.zzbui.zzuu()));
            } while (this.zzbui.zzva() < zzva2);
        } else if (i2 != 5) {
            throw zzuv.zzwu();
        } else {
            do {
                list.add(Integer.valueOf(this.zzbui.zzuu()));
                if (!this.zzbui.zzuz()) {
                    i2 = this.zzbui.zzuj();
                } else {
                    return;
                }
            } while (i2 == this.tag);
            this.zzbuk = i2;
        }
    }

    public final void zzv(List<Long> list) throws IOException {
        int zzus;
        int zzva;
        if (list instanceof zzvj) {
            zzvj zzvj = (zzvj) list;
            switch (this.tag & 7) {
                case 1:
                    break;
                case 2:
                    zzus = this.zzbui.zzus();
                    zzaw(zzus);
                    zzva = this.zzbui.zzva() + zzus;
                    do {
                        zzvj.zzbe(this.zzbui.zzuv());
                    } while (this.zzbui.zzva() < zzva);
                    return;
                default:
                    throw zzuv.zzwu();
            }
            do {
                zzvj.zzbe(this.zzbui.zzuv());
                if (!this.zzbui.zzuz()) {
                    zzus = this.zzbui.zzuj();
                } else {
                    return;
                }
            } while (zzus == this.tag);
            this.zzbuk = zzus;
            return;
        }
        switch (this.tag & 7) {
            case 1:
                break;
            case 2:
                zzus = this.zzbui.zzus();
                zzaw(zzus);
                zzva = this.zzbui.zzva() + zzus;
                do {
                    list.add(Long.valueOf(this.zzbui.zzuv()));
                } while (this.zzbui.zzva() < zzva);
                return;
            default:
                throw zzuv.zzwu();
        }
        do {
            list.add(Long.valueOf(this.zzbui.zzuv()));
            if (!this.zzbui.zzuz()) {
                zzus = this.zzbui.zzuj();
            } else {
                return;
            }
        } while (zzus == this.tag);
        this.zzbuk = zzus;
    }

    public final void zzw(List<Integer> list) throws IOException {
        int zzva;
        if (list instanceof zzup) {
            zzup zzup = (zzup) list;
            int i = this.tag & 7;
            if (i == 0) {
                do {
                    zzup.zzbo(this.zzbui.zzuw());
                    if (!this.zzbui.zzuz()) {
                        i = this.zzbui.zzuj();
                    } else {
                        return;
                    }
                } while (i == this.tag);
                this.zzbuk = i;
                return;
            } else if (i != 2) {
                throw zzuv.zzwu();
            } else {
                zzva = this.zzbui.zzva() + this.zzbui.zzus();
                do {
                    zzup.zzbo(this.zzbui.zzuw());
                } while (this.zzbui.zzva() < zzva);
                zzay(zzva);
                return;
            }
        }
        int i2 = this.tag & 7;
        if (i2 == 0) {
            do {
                list.add(Integer.valueOf(this.zzbui.zzuw()));
                if (!this.zzbui.zzuz()) {
                    i2 = this.zzbui.zzuj();
                } else {
                    return;
                }
            } while (i2 == this.tag);
            this.zzbuk = i2;
        } else if (i2 != 2) {
            throw zzuv.zzwu();
        } else {
            zzva = this.zzbui.zzva() + this.zzbui.zzus();
            do {
                list.add(Integer.valueOf(this.zzbui.zzuw()));
            } while (this.zzbui.zzva() < zzva);
            zzay(zzva);
        }
    }

    public final void zzx(List<Long> list) throws IOException {
        int zzva;
        if (list instanceof zzvj) {
            zzvj zzvj = (zzvj) list;
            int i = this.tag & 7;
            if (i == 0) {
                do {
                    zzvj.zzbe(this.zzbui.zzux());
                    if (!this.zzbui.zzuz()) {
                        i = this.zzbui.zzuj();
                    } else {
                        return;
                    }
                } while (i == this.tag);
                this.zzbuk = i;
                return;
            } else if (i != 2) {
                throw zzuv.zzwu();
            } else {
                zzva = this.zzbui.zzva() + this.zzbui.zzus();
                do {
                    zzvj.zzbe(this.zzbui.zzux());
                } while (this.zzbui.zzva() < zzva);
                zzay(zzva);
                return;
            }
        }
        int i2 = this.tag & 7;
        if (i2 == 0) {
            do {
                list.add(Long.valueOf(this.zzbui.zzux()));
                if (!this.zzbui.zzuz()) {
                    i2 = this.zzbui.zzuj();
                } else {
                    return;
                }
            } while (i2 == this.tag);
            this.zzbuk = i2;
        } else if (i2 != 2) {
            throw zzuv.zzwu();
        } else {
            zzva = this.zzbui.zzva() + this.zzbui.zzus();
            do {
                list.add(Long.valueOf(this.zzbui.zzux()));
            } while (this.zzbui.zzva() < zzva);
            zzay(zzva);
        }
    }

    private static void zzaw(int i) throws IOException {
        if ((i & 7) != 0) {
            throw zzuv.zzww();
        }
    }

    /* JADX WARNING: Missing exception handler attribute for start block: B:14:0x004e */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing block: B:16:0x0052, code skipped:
            if (zzvi() == false) goto L_0x0054;
     */
    /* JADX WARNING: Missing block: B:18:0x005b, code skipped:
            throw new com.google.android.gms.internal.measurement.zzuv("Unable to parse map entry.");
     */
    public final <K, V> void zza(java.util.Map<K, V> r6, com.google.android.gms.internal.measurement.zzvo<K, V> r7, com.google.android.gms.internal.measurement.zzub r8) throws java.io.IOException {
        /*
        r5 = this;
        r0 = 2;
        r5.zzav(r0);
        r0 = r5.zzbui;
        r0 = r0.zzus();
        r1 = r5.zzbui;
        r0 = r1.zzas(r0);
        r1 = r7.zzcaj;
        r2 = r7.zzbrp;
    L_0x0014:
        r3 = r5.zzvh();	 Catch:{ all -> 0x0065 }
        r4 = 2147483647; // 0x7fffffff float:NaN double:1.060997895E-314;
        if (r3 == r4) goto L_0x005c;
    L_0x001d:
        r4 = r5.zzbui;	 Catch:{ all -> 0x0065 }
        r4 = r4.zzuz();	 Catch:{ all -> 0x0065 }
        if (r4 != 0) goto L_0x005c;
    L_0x0025:
        switch(r3) {
            case 1: goto L_0x003b;
            case 2: goto L_0x002d;
            default: goto L_0x0028;
        };
    L_0x0028:
        r3 = r5.zzvi();	 Catch:{ zzuw -> 0x004e }
        goto L_0x0044;
    L_0x002d:
        r3 = r7.zzcak;	 Catch:{ zzuw -> 0x004e }
        r4 = r7.zzbrp;	 Catch:{ zzuw -> 0x004e }
        r4 = r4.getClass();	 Catch:{ zzuw -> 0x004e }
        r3 = r5.zza(r3, r4, r8);	 Catch:{ zzuw -> 0x004e }
        r2 = r3;
        goto L_0x0014;
    L_0x003b:
        r3 = r7.zzcai;	 Catch:{ zzuw -> 0x004e }
        r4 = 0;
        r3 = r5.zza(r3, r4, r4);	 Catch:{ zzuw -> 0x004e }
        r1 = r3;
        goto L_0x0014;
    L_0x0044:
        if (r3 != 0) goto L_0x0014;
    L_0x0046:
        r3 = new com.google.android.gms.internal.measurement.zzuv;	 Catch:{ zzuw -> 0x004e }
        r4 = "Unable to parse map entry.";
        r3.<init>(r4);	 Catch:{ zzuw -> 0x004e }
        throw r3;	 Catch:{ zzuw -> 0x004e }
    L_0x004e:
        r3 = r5.zzvi();	 Catch:{ all -> 0x0065 }
        if (r3 != 0) goto L_0x0014;
    L_0x0054:
        r6 = new com.google.android.gms.internal.measurement.zzuv;	 Catch:{ all -> 0x0065 }
        r7 = "Unable to parse map entry.";
        r6.<init>(r7);	 Catch:{ all -> 0x0065 }
        throw r6;	 Catch:{ all -> 0x0065 }
    L_0x005c:
        r6.put(r1, r2);	 Catch:{ all -> 0x0065 }
        r6 = r5.zzbui;
        r6.zzat(r0);
        return;
    L_0x0065:
        r6 = move-exception;
        r7 = r5.zzbui;
        r7.zzat(r0);
        throw r6;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zztt.zza(java.util.Map, com.google.android.gms.internal.measurement.zzvo, com.google.android.gms.internal.measurement.zzub):void");
    }

    private final Object zza(zzxs zzxs, Class<?> cls, zzub zzub) throws IOException {
        switch (zzxs) {
            case BOOL:
                return Boolean.valueOf(zzup());
            case BYTES:
                return zzur();
            case DOUBLE:
                return Double.valueOf(readDouble());
            case ENUM:
                return Integer.valueOf(zzut());
            case FIXED32:
                return Integer.valueOf(zzuo());
            case FIXED64:
                return Long.valueOf(zzun());
            case FLOAT:
                return Float.valueOf(readFloat());
            case INT32:
                return Integer.valueOf(zzum());
            case INT64:
                return Long.valueOf(zzul());
            case MESSAGE:
                zzav(2);
                return zzc(zzwh.zzxt().zzi(cls), zzub);
            case SFIXED32:
                return Integer.valueOf(zzuu());
            case SFIXED64:
                return Long.valueOf(zzuv());
            case SINT32:
                return Integer.valueOf(zzuw());
            case SINT64:
                return Long.valueOf(zzux());
            case STRING:
                return zzuq();
            case UINT32:
                return Integer.valueOf(zzus());
            case UINT64:
                return Long.valueOf(zzuk());
            default:
                throw new RuntimeException("unsupported field type.");
        }
    }

    private static void zzax(int i) throws IOException {
        if ((i & 3) != 0) {
            throw zzuv.zzww();
        }
    }

    private final void zzay(int i) throws IOException {
        if (this.zzbui.zzva() != i) {
            throw zzuv.zzwq();
        }
    }
}
