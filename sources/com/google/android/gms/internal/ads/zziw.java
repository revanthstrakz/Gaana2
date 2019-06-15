package com.google.android.gms.internal.ads;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

final class zziw extends zziv {
    public final long zzaop;
    public final List<zzix> zzaoq = new ArrayList();
    public final List<zziw> zzaor = new ArrayList();

    public zziw(int i, long j) {
        super(i);
        this.zzaop = j;
    }

    public final void zza(zzix zzix) {
        this.zzaoq.add(zzix);
    }

    public final void zza(zziw zziw) {
        this.zzaor.add(zziw);
    }

    public final zzix zzai(int i) {
        int size = this.zzaoq.size();
        for (int i2 = 0; i2 < size; i2++) {
            zzix zzix = (zzix) this.zzaoq.get(i2);
            if (zzix.type == i) {
                return zzix;
            }
        }
        return null;
    }

    public final zziw zzaj(int i) {
        int size = this.zzaor.size();
        for (int i2 = 0; i2 < size; i2++) {
            zziw zziw = (zziw) this.zzaor.get(i2);
            if (zziw.type == i) {
                return zziw;
            }
        }
        return null;
    }

    public final String toString() {
        String zzah = zziv.zzah(this.type);
        String arrays = Arrays.toString(this.zzaoq.toArray());
        String arrays2 = Arrays.toString(this.zzaor.toArray());
        StringBuilder stringBuilder = new StringBuilder(((22 + String.valueOf(zzah).length()) + String.valueOf(arrays).length()) + String.valueOf(arrays2).length());
        stringBuilder.append(zzah);
        stringBuilder.append(" leaves: ");
        stringBuilder.append(arrays);
        stringBuilder.append(" containers: ");
        stringBuilder.append(arrays2);
        return stringBuilder.toString();
    }
}
