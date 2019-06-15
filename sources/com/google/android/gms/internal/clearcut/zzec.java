package com.google.android.gms.internal.clearcut;

import com.google.android.gms.internal.clearcut.zzcg.zzg;

final class zzec implements zzdm {
    private final String info;
    private final zzdo zzmn;
    private final zzed zzng;

    zzec(zzdo zzdo, String str, Object[] objArr) {
        this.zzmn = zzdo;
        this.info = str;
        this.zzng = new zzed(zzdo.getClass(), str, objArr);
    }

    public final int getFieldCount() {
        return this.zzng.zznj;
    }

    public final int zzcf() {
        return (this.zzng.flags & 1) == 1 ? zzg.zzkl : zzg.zzkm;
    }

    public final boolean zzcg() {
        return (this.zzng.flags & 2) == 2;
    }

    public final zzdo zzch() {
        return this.zzmn;
    }

    /* Access modifiers changed, original: final */
    public final zzed zzco() {
        return this.zzng;
    }

    public final int zzcp() {
        return this.zzng.zzmk;
    }

    public final int zzcq() {
        return this.zzng.zzml;
    }

    public final int zzcr() {
        return this.zzng.zznm;
    }

    public final int zzcs() {
        return this.zzng.zzno;
    }

    /* Access modifiers changed, original: final */
    public final int[] zzct() {
        return this.zzng.zzms;
    }

    public final int zzcu() {
        return this.zzng.zznn;
    }

    public final int zzcv() {
        return this.zzng.zzmm;
    }
}
