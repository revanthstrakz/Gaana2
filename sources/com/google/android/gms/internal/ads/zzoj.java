package com.google.android.gms.internal.ads;

import android.util.SparseArray;
import android.util.SparseBooleanArray;
import java.util.Arrays;
import java.util.Map;

public abstract class zzoj extends zzop {
    private int zzaak = 0;
    private final SparseArray<Map<zzma, zzol>> zzbfa = new SparseArray();
    private final SparseBooleanArray zzbfb = new SparseBooleanArray();
    private zzok zzbfc;

    public abstract zzom[] zza(zzga[] zzgaArr, zzma[] zzmaArr, int[][][] iArr) throws zzff;

    public final void zzf(int i, boolean z) {
        if (this.zzbfb.get(i) != z) {
            this.zzbfb.put(i, z);
            invalidate();
        }
    }

    public final zzor zza(zzga[] zzgaArr, zzma zzma) throws zzff {
        int i;
        zzga[] zzgaArr2 = zzgaArr;
        zzma zzma2 = zzma;
        int[] iArr = new int[(zzgaArr2.length + 1)];
        zzlz[][] zzlzArr = new zzlz[(zzgaArr2.length + 1)][];
        int[][][] iArr2 = new int[(zzgaArr2.length + 1)][][];
        for (i = 0; i < zzlzArr.length; i++) {
            zzlzArr[i] = new zzlz[zzma2.length];
            iArr2[i] = new int[zzma2.length][];
        }
        int[] iArr3 = new int[zzgaArr2.length];
        for (i = 0; i < iArr3.length; i++) {
            iArr3[i] = zzgaArr2[i].zzbl();
        }
        for (i = 0; i < zzma2.length; i++) {
            int[] iArr4;
            zzlz zzau = zzma2.zzau(i);
            int length = zzgaArr2.length;
            int i2 = 0;
            int i3 = 0;
            while (i2 < zzgaArr2.length) {
                zzga zzga = zzgaArr2[i2];
                int i4 = length;
                for (length = 0; length < zzau.length; length++) {
                    int zzb = zzga.zzb(zzau.zzat(length)) & 3;
                    if (zzb > i3) {
                        if (zzb == 3) {
                            break;
                        }
                        i4 = i2;
                        i3 = zzb;
                    }
                }
                i2++;
                length = i4;
            }
            i2 = length;
            if (i2 == zzgaArr2.length) {
                iArr4 = new int[zzau.length];
            } else {
                zzga zzga2 = zzgaArr2[i2];
                int[] iArr5 = new int[zzau.length];
                for (i3 = 0; i3 < zzau.length; i3++) {
                    iArr5[i3] = zzga2.zzb(zzau.zzat(i3));
                }
                iArr4 = iArr5;
            }
            length = iArr[i2];
            zzlzArr[i2][length] = zzau;
            iArr2[i2][length] = iArr4;
            iArr[i2] = iArr[i2] + 1;
        }
        zzma[] zzmaArr = new zzma[zzgaArr2.length];
        int[] iArr6 = new int[zzgaArr2.length];
        for (int i5 = 0; i5 < zzgaArr2.length; i5++) {
            i = iArr[i5];
            zzmaArr[i5] = new zzma((zzlz[]) Arrays.copyOf(zzlzArr[i5], i));
            iArr2[i5] = (int[][]) Arrays.copyOf(iArr2[i5], i);
            iArr6[i5] = zzgaArr2[i5].getTrackType();
        }
        zzma zzma3 = new zzma((zzlz[]) Arrays.copyOf(zzlzArr[zzgaArr2.length], iArr[zzgaArr2.length]));
        zzom[] zza = zza(zzgaArr2, zzmaArr, iArr2);
        int i6 = 0;
        while (true) {
            zzol zzol = null;
            if (i6 < zzgaArr2.length) {
                if (this.zzbfb.get(i6)) {
                    zza[i6] = null;
                } else {
                    Object obj = zzmaArr[i6];
                    Map map = (Map) this.zzbfa.get(i6);
                    if (map != null) {
                        zzol = (zzol) map.get(obj);
                    }
                    if (zzol != null) {
                        throw new NoSuchMethodError();
                    }
                }
                i6++;
            } else {
                zzok zzok = new zzok(iArr6, zzmaArr, iArr3, iArr2, zzma3);
                zzgb[] zzgbArr = new zzgb[zzgaArr2.length];
                for (int i7 = 0; i7 < zzgaArr2.length; i7++) {
                    zzgbArr[i7] = zza[i7] != null ? zzgb.zzaaj : null;
                }
                return new zzor(zzma2, new zzoo(zza), zzok, zzgbArr);
            }
        }
    }

    public final void zzd(Object obj) {
        this.zzbfc = (zzok) obj;
    }
}
