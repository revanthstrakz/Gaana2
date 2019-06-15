package com.google.android.gms.internal.measurement;

import com.google.android.gms.internal.measurement.zzg.zza;
import com.google.android.gms.tagmanager.zzdi;
import com.google.android.gms.tagmanager.zzgj;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public final class zzrg {
    public static zzrk zza(zzl zzl) throws zzro {
        int i;
        zzp[] zzpArr = new zzp[zzl.zzou.length];
        for (int i2 = 0; i2 < zzl.zzou.length; i2++) {
            zza(i2, zzl, zzpArr, new HashSet(0));
        }
        zzrl zztb = zzrk.zztb();
        ArrayList arrayList = new ArrayList();
        for (int i3 = 0; i3 < zzl.zzox.length; i3++) {
            arrayList.add(zza(zzl.zzox[i3], zzl, zzpArr, i3));
        }
        ArrayList arrayList2 = new ArrayList();
        for (int i4 = 0; i4 < zzl.zzoy.length; i4++) {
            arrayList2.add(zza(zzl.zzoy[i4], zzl, zzpArr, i4));
        }
        ArrayList arrayList3 = new ArrayList();
        for (i = 0; i < zzl.zzow.length; i++) {
            zzri zza = zza(zzl.zzow[i], zzl, zzpArr, i);
            zztb.zzc(zza);
            arrayList3.add(zza);
        }
        for (zzm zzm : zzl.zzoz) {
            zzrn zzrn = new zzrn();
            for (int valueOf : zzm.zzpj) {
                zzrn.zzd((zzri) arrayList2.get(Integer.valueOf(valueOf).intValue()));
            }
            for (int valueOf2 : zzm.zzpk) {
                zzrn.zze((zzri) arrayList2.get(Integer.valueOf(valueOf2).intValue()));
            }
            for (int valueOf22 : zzm.zzpl) {
                zzrn.zzf((zzri) arrayList.get(Integer.valueOf(valueOf22).intValue()));
            }
            for (int valueOf222 : zzm.zzpn) {
                zzrn.zzfj(zzl.zzou[Integer.valueOf(valueOf222).intValue()].string);
            }
            for (int valueOf2222 : zzm.zzpm) {
                zzrn.zzg((zzri) arrayList.get(Integer.valueOf(valueOf2222).intValue()));
            }
            for (int valueOf22222 : zzm.zzpo) {
                zzrn.zzfk(zzl.zzou[Integer.valueOf(valueOf22222).intValue()].string);
            }
            for (int valueOf222222 : zzm.zzpp) {
                zzrn.zzh((zzri) arrayList3.get(Integer.valueOf(valueOf222222).intValue()));
            }
            for (int valueOf2222222 : zzm.zzpr) {
                zzrn.zzfl(zzl.zzou[Integer.valueOf(valueOf2222222).intValue()].string);
            }
            for (int valueOf22222222 : zzm.zzpq) {
                zzrn.zzi((zzri) arrayList3.get(Integer.valueOf(valueOf22222222).intValue()));
            }
            for (int valueOf3 : zzm.zzps) {
                zzrn.zzfm(zzl.zzou[Integer.valueOf(valueOf3).intValue()].string);
            }
            zztb.zzb(zzrn.zztg());
        }
        zztb.zzfi(zzl.version);
        zztb.zzah(zzl.zzph);
        return zztb.zztd();
    }

    public static zzp zzk(zzp zzp) {
        zzp zzp2 = new zzp();
        zzp2.type = zzp.type;
        zzp2.zzqr = (int[]) zzp.zzqr.clone();
        if (zzp.zzqs) {
            zzp2.zzqs = zzp.zzqs;
        }
        return zzp2;
    }

    private static zzp zza(int i, zzl zzl, zzp[] zzpArr, Set<Integer> set) throws zzro {
        StringBuilder stringBuilder;
        if (set.contains(Integer.valueOf(i))) {
            String valueOf = String.valueOf(set);
            stringBuilder = new StringBuilder(90 + String.valueOf(valueOf).length());
            stringBuilder.append("Value cycle detected.  Current value reference: ");
            stringBuilder.append(i);
            stringBuilder.append(".  Previous value references: ");
            stringBuilder.append(valueOf);
            stringBuilder.append(".");
            zzev(stringBuilder.toString());
        }
        zzp zzp = (zzp) zza(zzl.zzou, i, "values");
        if (zzpArr[i] != null) {
            return zzpArr[i];
        }
        zzp zzp2 = null;
        set.add(Integer.valueOf(i));
        int i2 = 0;
        int length;
        int i3;
        int i4;
        zza zzl2;
        int[] iArr;
        switch (zzp.type) {
            case 1:
            case 5:
            case 6:
            case 8:
                zzp2 = zzp;
                break;
            case 2:
                zza zzl3 = zzl(zzp);
                zzp zzk = zzk(zzp);
                zzk.zzqj = new zzp[zzl3.zzpv.length];
                int[] iArr2 = zzl3.zzpv;
                length = iArr2.length;
                i3 = 0;
                while (i2 < length) {
                    i4 = i3 + 1;
                    zzk.zzqj[i3] = zza(iArr2[i2], zzl, zzpArr, (Set) set);
                    i2++;
                    i3 = i4;
                }
                zzp2 = zzk;
                break;
            case 3:
                zzp2 = zzk(zzp);
                zzl2 = zzl(zzp);
                if (zzl2.zzpw.length != zzl2.zzpx.length) {
                    length = zzl2.zzpw.length;
                    i3 = zzl2.zzpx.length;
                    StringBuilder stringBuilder2 = new StringBuilder(58);
                    stringBuilder2.append("Uneven map keys (");
                    stringBuilder2.append(length);
                    stringBuilder2.append(") and map values (");
                    stringBuilder2.append(i3);
                    stringBuilder2.append(")");
                    zzev(stringBuilder2.toString());
                }
                zzp2.zzqk = new zzp[zzl2.zzpw.length];
                zzp2.zzql = new zzp[zzl2.zzpw.length];
                int[] iArr3 = zzl2.zzpw;
                i3 = iArr3.length;
                int i5 = 0;
                int i6 = i5;
                while (i5 < i3) {
                    int i7 = i6 + 1;
                    zzp2.zzqk[i6] = zza(iArr3[i5], zzl, zzpArr, (Set) set);
                    i5++;
                    i6 = i7;
                }
                iArr = zzl2.zzpx;
                length = iArr.length;
                i3 = 0;
                while (i2 < length) {
                    i4 = i3 + 1;
                    zzp2.zzql[i3] = zza(iArr[i2], zzl, zzpArr, (Set) set);
                    i2++;
                    i3 = i4;
                }
                break;
            case 4:
                zzp2 = zzk(zzp);
                zzp2.zzqm = zzgj.zzc(zza(zzl(zzp).zzqa, zzl, zzpArr, (Set) set));
                break;
            case 7:
                zzp2 = zzk(zzp);
                zzl2 = zzl(zzp);
                zzp2.zzqq = new zzp[zzl2.zzpz.length];
                iArr = zzl2.zzpz;
                length = iArr.length;
                i3 = 0;
                while (i2 < length) {
                    i4 = i3 + 1;
                    zzp2.zzqq[i3] = zza(iArr[i2], zzl, zzpArr, (Set) set);
                    i2++;
                    i3 = i4;
                }
                break;
        }
        if (zzp2 == null) {
            String valueOf2 = String.valueOf(zzp);
            stringBuilder = new StringBuilder(15 + String.valueOf(valueOf2).length());
            stringBuilder.append("Invalid value: ");
            stringBuilder.append(valueOf2);
            zzev(stringBuilder.toString());
        }
        zzpArr[i] = zzp2;
        set.remove(Integer.valueOf(i));
        return zzp2;
    }

    private static zza zzl(zzp zzp) throws zzro {
        if (((zza) zzp.zza(zza.zzpt)) == null) {
            String valueOf = String.valueOf(zzp);
            StringBuilder stringBuilder = new StringBuilder(54 + String.valueOf(valueOf).length());
            stringBuilder.append("Expected a ServingValue and didn't get one. Value is: ");
            stringBuilder.append(valueOf);
            zzev(stringBuilder.toString());
        }
        return (zza) zzp.zza(zza.zzpt);
    }

    private static void zzev(String str) throws zzro {
        zzdi.e(str);
        throw new zzro(str);
    }

    private static <T> T zza(T[] tArr, int i, String str) throws zzro {
        if (i < 0 || i >= tArr.length) {
            StringBuilder stringBuilder = new StringBuilder(45 + String.valueOf(str).length());
            stringBuilder.append("Index out of bounds detected: ");
            stringBuilder.append(i);
            stringBuilder.append(" in ");
            stringBuilder.append(str);
            zzev(stringBuilder.toString());
        }
        return tArr[i];
    }

    private static zzri zza(zzh zzh, zzl zzl, zzp[] zzpArr, int i) throws zzro {
        zzrj zzsz = zzri.zzsz();
        for (int valueOf : zzh.zzoe) {
            zzk zzk = (zzk) zza(zzl.zzov, Integer.valueOf(valueOf).intValue(), InAppConstants.INAPP_BACKGROUND_PROPERTIES);
            String str = (String) zza(zzl.zzot, zzk.key, "keys");
            zzp zzp = (zzp) zza(zzpArr, zzk.value, "values");
            if (zzb.PUSH_AFTER_EVALUATE.toString().equals(str)) {
                zzsz.zzm(zzp);
            } else {
                zzsz.zzb(str, zzp);
            }
        }
        return zzsz.zzta();
    }

    public static void zza(InputStream inputStream, OutputStream outputStream) throws IOException {
        byte[] bArr = new byte[1024];
        while (true) {
            int read = inputStream.read(bArr);
            if (read != -1) {
                outputStream.write(bArr, 0, read);
            } else {
                return;
            }
        }
    }
}
