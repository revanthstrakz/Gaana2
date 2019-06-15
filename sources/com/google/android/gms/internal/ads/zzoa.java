package com.google.android.gms.internal.ads;

import java.util.Locale;

public final class zzoa {
    private final String[] zzbef;
    private final int[] zzbeg;
    private final String[] zzbeh;
    private final int zzbei;

    public static zzoa zzaa(String str) {
        String[] strArr = new String[5];
        int[] iArr = new int[4];
        String[] strArr2 = new String[4];
        strArr[0] = "";
        int i = 0;
        int i2 = i;
        while (i < str.length()) {
            int indexOf = str.indexOf("$", i);
            int i3 = -1;
            String valueOf;
            if (indexOf == -1) {
                String valueOf2 = String.valueOf(strArr[i2]);
                valueOf = String.valueOf(str.substring(i));
                strArr[i2] = valueOf.length() != 0 ? valueOf2.concat(valueOf) : new String(valueOf2);
                i = str.length();
            } else if (indexOf != i) {
                String valueOf3 = String.valueOf(strArr[i2]);
                valueOf = String.valueOf(str.substring(i, indexOf));
                strArr[i2] = valueOf.length() != 0 ? valueOf3.concat(valueOf) : new String(valueOf3);
                i = indexOf;
            } else if (str.startsWith("$$", i)) {
                strArr[i2] = String.valueOf(strArr[i2]).concat("$");
                i += 2;
            } else {
                i++;
                indexOf = str.indexOf("$", i);
                valueOf = str.substring(i, indexOf);
                if (valueOf.equals("RepresentationID")) {
                    iArr[i2] = 1;
                } else {
                    int indexOf2 = valueOf.indexOf("%0");
                    String str2 = "%01d";
                    if (indexOf2 != -1) {
                        str2 = valueOf.substring(indexOf2);
                        if (!str2.endsWith("d")) {
                            str2 = String.valueOf(str2).concat("d");
                        }
                        valueOf = valueOf.substring(0, indexOf2);
                    }
                    indexOf2 = valueOf.hashCode();
                    if (indexOf2 != -1950496919) {
                        if (indexOf2 != 2606829) {
                            if (indexOf2 == 38199441 && valueOf.equals("Bandwidth")) {
                                i3 = 1;
                            }
                        } else if (valueOf.equals("Time")) {
                            i3 = 2;
                        }
                    } else if (valueOf.equals("Number")) {
                        i3 = 0;
                    }
                    switch (i3) {
                        case 0:
                            iArr[i2] = 2;
                            break;
                        case 1:
                            iArr[i2] = 3;
                            break;
                        case 2:
                            iArr[i2] = 4;
                            break;
                        default:
                            String str3 = "Invalid template: ";
                            str = String.valueOf(str);
                            throw new IllegalArgumentException(str.length() != 0 ? str3.concat(str) : new String(str3));
                    }
                    strArr2[i2] = str2;
                }
                i2++;
                strArr[i2] = "";
                i = indexOf + 1;
            }
        }
        return new zzoa(strArr, iArr, strArr2, i2);
    }

    private zzoa(String[] strArr, int[] iArr, String[] strArr2, int i) {
        this.zzbef = strArr;
        this.zzbeg = iArr;
        this.zzbeh = strArr2;
        this.zzbei = i;
    }

    public final String zza(String str, int i, int i2, long j) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i3 = 0; i3 < this.zzbei; i3++) {
            stringBuilder.append(this.zzbef[i3]);
            if (this.zzbeg[i3] == 1) {
                stringBuilder.append(str);
            } else if (this.zzbeg[i3] == 2) {
                stringBuilder.append(String.format(Locale.US, this.zzbeh[i3], new Object[]{Integer.valueOf(i)}));
            } else if (this.zzbeg[i3] == 3) {
                stringBuilder.append(String.format(Locale.US, this.zzbeh[i3], new Object[]{Integer.valueOf(i2)}));
            } else if (this.zzbeg[i3] == 4) {
                stringBuilder.append(String.format(Locale.US, this.zzbeh[i3], new Object[]{Long.valueOf(j)}));
            }
        }
        stringBuilder.append(this.zzbef[this.zzbei]);
        return stringBuilder.toString();
    }
}
