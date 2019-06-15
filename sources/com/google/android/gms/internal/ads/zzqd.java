package com.google.android.gms.internal.ads;

import android.text.TextUtils;

public final class zzqd {
    public static String zzc(String str, String str2) {
        StringBuilder stringBuilder = new StringBuilder();
        if (str == null) {
            str = "";
        }
        if (str2 == null) {
            str2 = "";
        }
        int[] zzag = zzag(str2);
        if (zzag[0] != -1) {
            stringBuilder.append(str2);
            zza(stringBuilder, zzag[1], zzag[2]);
            return stringBuilder.toString();
        }
        int[] zzag2 = zzag(str);
        if (zzag[3] == 0) {
            stringBuilder.append(str, 0, zzag2[3]);
            stringBuilder.append(str2);
            return stringBuilder.toString();
        } else if (zzag[2] == 0) {
            stringBuilder.append(str, 0, zzag2[2]);
            stringBuilder.append(str2);
            return stringBuilder.toString();
        } else if (zzag[1] != 0) {
            int i = zzag2[0] + 1;
            stringBuilder.append(str, 0, i);
            stringBuilder.append(str2);
            return zza(stringBuilder, zzag[1] + i, i + zzag[2]);
        } else if (str2.charAt(zzag[1]) == '/') {
            stringBuilder.append(str, 0, zzag2[1]);
            stringBuilder.append(str2);
            return zza(stringBuilder, zzag2[1], zzag2[1] + zzag[2]);
        } else if (zzag2[0] + 2 >= zzag2[1] || zzag2[1] != zzag2[2]) {
            int lastIndexOf = str.lastIndexOf(47, zzag2[2] - 1);
            int i2 = lastIndexOf == -1 ? zzag2[1] : lastIndexOf + 1;
            stringBuilder.append(str, 0, i2);
            stringBuilder.append(str2);
            return zza(stringBuilder, zzag2[1], i2 + zzag[2]);
        } else {
            stringBuilder.append(str, 0, zzag2[1]);
            stringBuilder.append('/');
            stringBuilder.append(str2);
            return zza(stringBuilder, zzag2[1], (zzag2[1] + zzag[2]) + 1);
        }
    }

    private static String zza(StringBuilder stringBuilder, int i, int i2) {
        if (i >= i2) {
            return stringBuilder.toString();
        }
        if (stringBuilder.charAt(i) == '/') {
            i++;
        }
        int i3 = i;
        int i4 = i2;
        while (true) {
            i2 = i3;
            while (i2 <= i4) {
                int i5;
                if (i2 == i4) {
                    i5 = i2;
                } else if (stringBuilder.charAt(i2) == '/') {
                    i5 = i2 + 1;
                } else {
                    i2++;
                }
                int i6 = i3 + 1;
                if (i2 == i6 && stringBuilder.charAt(i3) == '.') {
                    stringBuilder.delete(i3, i5);
                    i4 -= i5 - i3;
                } else if (i2 == i3 + 2 && stringBuilder.charAt(i3) == '.' && stringBuilder.charAt(i6) == '.') {
                    i3 = stringBuilder.lastIndexOf("/", i3 - 2) + 1;
                    i2 = i3 > i ? i3 : i;
                    stringBuilder.delete(i2, i5);
                    i4 -= i5 - i2;
                } else {
                    i3 = i2 + 1;
                }
            }
            return stringBuilder.toString();
        }
    }

    private static int[] zzag(String str) {
        int[] iArr = new int[4];
        if (TextUtils.isEmpty(str)) {
            iArr[0] = -1;
            return iArr;
        }
        int indexOf;
        int length = str.length();
        int indexOf2 = str.indexOf(35);
        if (indexOf2 != -1) {
            length = indexOf2;
        }
        indexOf2 = str.indexOf(63);
        if (indexOf2 == -1 || indexOf2 > length) {
            indexOf2 = length;
        }
        int indexOf3 = str.indexOf(47);
        if (indexOf3 == -1 || indexOf3 > indexOf2) {
            indexOf3 = indexOf2;
        }
        int indexOf4 = str.indexOf(58);
        if (indexOf4 > indexOf3) {
            indexOf4 = -1;
        }
        indexOf3 = indexOf4 + 2;
        indexOf3 = (indexOf3 < indexOf2 && str.charAt(indexOf4 + 1) == '/' && str.charAt(indexOf3) == '/') ? 1 : 0;
        if (indexOf3 != 0) {
            indexOf = str.indexOf(47, indexOf4 + 3);
            if (indexOf == -1 || indexOf > indexOf2) {
                indexOf = indexOf2;
            }
        } else {
            indexOf = indexOf4 + 1;
        }
        iArr[0] = indexOf4;
        iArr[1] = indexOf;
        iArr[2] = indexOf2;
        iArr[3] = length;
        return iArr;
    }
}
