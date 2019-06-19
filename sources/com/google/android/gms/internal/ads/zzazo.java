package com.google.android.gms.internal.ads;

import java.util.ArrayList;
import java.util.List;

@zzark
public final class zzazo {
    private final String[] zzeml;
    private final double[] zzemm;
    private final double[] zzemn;
    private final int[] zzemo;
    private int zzemp;

    private zzazo(zzazr zzazr) {
        int size = zzazr.zzemu.size();
        this.zzeml = (String[]) zzazr.zzemt.toArray(new String[size]);
        this.zzemm = zzo(zzazr.zzemu);
        this.zzemn = zzo(zzazr.zzemv);
        this.zzemo = new int[size];
        this.zzemp = 0;
    }

    private static double[] zzo(List<Double> list) {
        double[] dArr = new double[list.size()];
        for (int i = 0; i < dArr.length; i++) {
            dArr[i] = ((Double) list.get(i)).doubleValue();
        }
        return dArr;
    }

    public final void zza(double d) {
        this.zzemp++;
        int i = 0;
        while (i < this.zzemn.length) {
            if (this.zzemn[i] <= d && d < this.zzemm[i]) {
                int[] iArr = this.zzemo;
                iArr[i] = iArr[i] + 1;
            }
            if (d >= this.zzemn[i]) {
                i++;
            } else {
                return;
            }
        }
    }

    public final List<zzazq> zzaai() {
        ArrayList arrayList = new ArrayList(this.zzeml.length);
        for (int i = 0; i < this.zzeml.length; i++) {
            arrayList.add(new zzazq(this.zzeml[i], this.zzemn[i], this.zzemm[i], ((double) this.zzemo[i]) / ((double) this.zzemp), this.zzemo[i]));
        }
        return arrayList;
    }

    /* synthetic */ zzazo(zzazr zzazr, zzazp zzazp) {
        this(zzazr);
    }
}
