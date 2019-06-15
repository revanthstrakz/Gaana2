package com.google.android.gms.internal.ads;

import android.util.Pair;
import android.util.SparseIntArray;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.util.MimeTypes;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

final class zzms implements zzlm, zzlx<zzmj<zzmq>> {
    final int id;
    private final int zzawn;
    private final zzot zzawq;
    private zzln zzawz;
    private final zzkt zzbah;
    private final zzmr zzbax;
    private final long zzbay;
    private final zzpk zzbaz;
    private final zzmt[] zzbba;
    private zzmj<zzmq>[] zzbbb = new zzmj[0];
    private zzla zzbbc = new zzla(this.zzbbb);
    private zznj zzbbd;
    private List<zzni> zzbbe;
    private final zzma zzxk;
    private int zzyr;

    public zzms(int i, zznj zznj, int i2, zzmr zzmr, int i3, zzkt zzkt, long j, zzpk zzpk, zzot zzot) {
        int i4;
        int i5;
        int[] iArr;
        int i6;
        int i7;
        int i8;
        this.id = i;
        this.zzbbd = zznj;
        this.zzyr = i2;
        this.zzbax = zzmr;
        this.zzawn = i3;
        this.zzbah = zzkt;
        this.zzbay = j;
        this.zzbaz = zzpk;
        this.zzawq = zzot;
        int i9 = 0;
        this.zzbbe = zznj.zzba(i2).zzbbe;
        List list = this.zzbbe;
        int size = list.size();
        SparseIntArray sparseIntArray = new SparseIntArray(size);
        for (int i10 = 0; i10 < size; i10++) {
            sparseIntArray.put(((zzni) list.get(i10)).id, i10);
        }
        int[][] iArr2 = new int[size][];
        boolean[] zArr = new boolean[size];
        int i11 = 0;
        int i12 = i11;
        while (i11 < size) {
            if (!zArr[i11]) {
                zznm zznm;
                zArr[i11] = true;
                List list2 = ((zzni) list.get(i11)).zzbcp;
                for (i4 = 0; i4 < list2.size(); i4++) {
                    zznm zznm2 = (zznm) list2.get(i4);
                    if ("urn:mpeg:dash:adaptation-set-switching:2016".equals(zznm2.zzbdi)) {
                        zznm = zznm2;
                        break;
                    }
                }
                zznm = null;
                if (zznm == null) {
                    i5 = i12 + 1;
                    iArr2[i12] = new int[]{i11};
                } else {
                    String[] split = zznm.value.split(",");
                    iArr = new int[(split.length + 1)];
                    iArr[0] = i11;
                    i4 = 0;
                    while (i4 < split.length) {
                        i6 = sparseIntArray.get(Integer.parseInt(split[i4]));
                        zArr[i6] = true;
                        i4++;
                        iArr[i4] = i6;
                    }
                    i5 = i12 + 1;
                    iArr2[i12] = iArr;
                }
                i12 = i5;
            }
            i11++;
        }
        if (i12 < size) {
            iArr2 = (int[][]) Arrays.copyOf(iArr2, i12);
        }
        size = iArr2.length;
        boolean[] zArr2 = new boolean[size];
        zArr = new boolean[size];
        i12 = size;
        i11 = 0;
        while (i11 < size) {
            boolean z;
            boolean z2;
            iArr = iArr2[i11];
            i4 = iArr.length;
            for (i6 = i9; i6 < i4; i6++) {
                List list3 = ((zzni) list.get(iArr[i6])).zzbcn;
                for (i7 = i9; i7 < list3.size(); i7++) {
                    if (!((zznp) list3.get(i7)).zzbdo.isEmpty()) {
                        z = true;
                        break;
                    }
                }
            }
            z = i9;
            if (z) {
                zArr2[i11] = true;
                i12++;
            }
            int[] iArr3 = iArr2[i11];
            int length = iArr3.length;
            i4 = i9;
            while (i4 < length) {
                List list4 = ((zzni) list.get(iArr3[i4])).zzbco;
                for (i8 = i9; i8 < list4.size(); i8++) {
                    if ("urn:scte:dash:cc:cea-608:2015".equals(((zznm) list4.get(i8)).zzbdi)) {
                        z2 = true;
                        break;
                    }
                }
                i4++;
                i9 = 0;
            }
            z2 = false;
            if (z2) {
                zArr[i11] = true;
                i12++;
            }
            i11++;
            i9 = 0;
        }
        zzlz[] zzlzArr = new zzlz[i12];
        zzmt[] zzmtArr = new zzmt[i12];
        i12 = 0;
        i5 = 0;
        while (i12 < size) {
            int i13;
            int i14;
            iArr = iArr2[i12];
            ArrayList arrayList = new ArrayList();
            for (int i72 : iArr) {
                arrayList.addAll(((zzni) list.get(i72)).zzbcn);
            }
            zzfs[] zzfsArr = new zzfs[arrayList.size()];
            for (i8 = 0; i8 < zzfsArr.length; i8++) {
                zzfsArr[i8] = ((zznp) arrayList.get(i8)).zzaad;
            }
            zzni zzni = (zzni) list.get(iArr[0]);
            boolean z3 = zArr2[i12];
            boolean z4 = zArr[i12];
            zzlzArr[i5] = new zzlz(zzfsArr);
            int i15 = i5 + 1;
            List list5 = list;
            zzmtArr[i5] = new zzmt(zzni.type, iArr, i5, true, z3, z4);
            if (z3) {
                i13 = zzni.id;
                StringBuilder stringBuilder = new StringBuilder(16);
                stringBuilder.append(i13);
                stringBuilder.append(":emsg");
                i14 = size;
                zzlzArr[i15] = new zzlz(zzfs.zza(stringBuilder.toString(), MimeTypes.APPLICATION_EMSG, null, -1, null));
                i13 = i15 + 1;
                zzmtArr[i15] = new zzmt(4, iArr, i5, false, false, false);
                i15 = i13;
            } else {
                i14 = size;
            }
            if (z4) {
                i13 = zzni.id;
                StringBuilder stringBuilder2 = new StringBuilder(18);
                stringBuilder2.append(i13);
                stringBuilder2.append(":cea608");
                i4 = 1;
                zzlzArr[i15] = new zzlz(zzfs.zza(stringBuilder2.toString(), MimeTypes.APPLICATION_CEA608, null, -1, 0, null, null));
                i13 = i15 + 1;
                zzmtArr[i15] = new zzmt(3, iArr, i5, false, false, false);
                i5 = i13;
            } else {
                i4 = 1;
                i5 = i15;
            }
            i12++;
            i15 = i4;
            list = list5;
            size = i14;
        }
        Pair create = Pair.create(new zzma(zzlzArr), zzmtArr);
        this.zzxk = (zzma) create.first;
        this.zzbba = (zzmt[]) create.second;
    }

    public final long zzey() {
        return C.TIME_UNSET;
    }

    public final void zza(zznj zznj, int i) {
        this.zzbbd = zznj;
        this.zzyr = i;
        this.zzbbe = zznj.zzba(i).zzbbe;
        if (this.zzbbb != null) {
            for (zzmj zzfy : this.zzbbb) {
                ((zzmq) zzfy.zzfy()).zza(zznj, i);
            }
            this.zzawz.zza(this);
        }
    }

    public final void release() {
        for (zzmj release : this.zzbbb) {
            release.release();
        }
    }

    public final void zza(zzln zzln, long j) {
        this.zzawz = zzln;
        zzln.zza(this);
    }

    public final void zzew() throws IOException {
        this.zzbaz.zzev();
    }

    public final zzma zzex() {
        return this.zzxk;
    }

    public final long zza(zzom[] zzomArr, boolean[] zArr, zzlv[] zzlvArr, boolean[] zArr2, long j) {
        int i;
        long j2;
        zzms zzms = this;
        zzom[] zzomArr2 = zzomArr;
        long j3 = j;
        HashMap hashMap = new HashMap();
        int i2 = 0;
        while (i2 < zzomArr2.length) {
            int i3;
            if (zzlvArr[i2] instanceof zzmj) {
                zzmj zzmj = (zzmj) zzlvArr[i2];
                if (zzomArr2[i2] == null || !zArr[i2]) {
                    zzmj.release();
                    zzlvArr[i2] = null;
                } else {
                    hashMap.put(Integer.valueOf(zzms.zzxk.zza(zzomArr2[i2].zzgk())), zzmj);
                }
            }
            if (zzlvArr[i2] == null && zzomArr2[i2] != null) {
                int zza = zzms.zzxk.zza(zzomArr2[i2].zzgk());
                zzmt zzmt = zzms.zzbba[zza];
                if (zzmt.zzbbg) {
                    zzom zzom = zzomArr2[i2];
                    int[] iArr = new int[2];
                    boolean z = zzmt.zzbbi;
                    if (z) {
                        iArr[0] = 4;
                        i = 1;
                    } else {
                        i = 0;
                    }
                    boolean z2 = zzmt.zzbbj;
                    if (z2) {
                        int i4 = i + 1;
                        iArr[i] = 3;
                        i = i4;
                    }
                    if (i < 2) {
                        iArr = Arrays.copyOf(iArr, i);
                    }
                    int i5 = i2;
                    int i6 = zza;
                    zzmq zza2 = zzms.zzbax.zza(zzms.zzbaz, zzms.zzbbd, zzms.zzyr, zzmt.zzbbf, zzom, zzmt.zzwg, zzms.zzbay, z, z2);
                    int i7 = zzmt.zzwg;
                    zzot zzot = zzms.zzawq;
                    int i8 = zzms.zzawn;
                    zzkt zzkt = zzms.zzbah;
                    zzms zzms2 = zzms;
                    i3 = i5;
                    int i9 = i6;
                    zzmj zzmj2 = new zzmj(i7, iArr, zza2, zzms2, zzot, j3, i8, zzkt);
                    hashMap.put(Integer.valueOf(i9), zzmj2);
                    zzlvArr[i3] = zzmj2;
                    zArr2[i3] = true;
                    i2 = i3 + 1;
                    j3 = j;
                    zzms = this;
                    zzomArr2 = zzomArr;
                }
            }
            i3 = i2;
            i2 = i3 + 1;
            j3 = j;
            zzms = this;
            zzomArr2 = zzomArr;
        }
        zzom[] zzomArr3 = zzomArr2;
        i = 0;
        while (i < zzomArr3.length) {
            if (((zzlvArr[i] instanceof zzmk) || (zzlvArr[i] instanceof zzlb)) && (zzomArr3[i] == null || !zArr[i])) {
                zza(zzlvArr[i]);
                zzlvArr[i] = null;
            }
            if (zzomArr3[i] != null) {
                zzmt zzmt2 = this.zzbba[this.zzxk.zza(zzomArr3[i].zzgk())];
                if (!zzmt2.zzbbg) {
                    zzmj zzmj3 = (zzmj) hashMap.get(Integer.valueOf(zzmt2.zzbbh));
                    zzlv zzlv = zzlvArr[i];
                    boolean z3 = zzmj3 == null ? zzlv instanceof zzlb : (zzlv instanceof zzmk) && ((zzmk) zzlv).zzbap == zzmj3;
                    if (!z3) {
                        zzlb zzlb;
                        zza(zzlv);
                        if (zzmj3 == null) {
                            zzlb = new zzlb();
                            j2 = j;
                        } else {
                            zzlb = zzmj3.zza(j, zzmt2.zzwg);
                        }
                        zzlvArr[i] = zzlb;
                        zArr2[i] = true;
                        i++;
                    }
                }
            }
            j2 = j;
            i++;
        }
        j2 = j;
        this.zzbbb = new zzmj[hashMap.size()];
        hashMap.values().toArray(this.zzbbb);
        this.zzbbc = new zzla(this.zzbbb);
        return j2;
    }

    public final void zzaa(long j) {
        for (zzmj zzaf : this.zzbbb) {
            zzaf.zzaf(j);
        }
    }

    public final boolean zzy(long j) {
        return this.zzbbc.zzy(j);
    }

    public final long zzeu() {
        return this.zzbbc.zzeu();
    }

    public final long zzez() {
        long j = Long.MAX_VALUE;
        for (zzmj zzez : this.zzbbb) {
            long zzez2 = zzez.zzez();
            if (zzez2 != Long.MIN_VALUE) {
                j = Math.min(j, zzez2);
            }
        }
        if (j == Long.MAX_VALUE) {
            return Long.MIN_VALUE;
        }
        return j;
    }

    public final long zzab(long j) {
        for (zzmj zzag : this.zzbbb) {
            zzag.zzag(j);
        }
        return j;
    }

    private static void zza(zzlv zzlv) {
        if (zzlv instanceof zzmk) {
            ((zzmk) zzlv).release();
        }
    }
}
