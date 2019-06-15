package com.fasterxml.jackson.core.io;

import com.google.android.exoplayer2.C;

public final class f {
    static final String a = String.valueOf(Long.MIN_VALUE).substring(1);
    static final String b = String.valueOf(Long.MAX_VALUE);

    public static int a(char[] cArr, int i, int i2) {
        int i3 = cArr[i] - 48;
        i2 += i;
        i++;
        if (i >= i2) {
            return i3;
        }
        i3 = (i3 * 10) + (cArr[i] - 48);
        i++;
        if (i >= i2) {
            return i3;
        }
        i3 = (i3 * 10) + (cArr[i] - 48);
        i++;
        if (i >= i2) {
            return i3;
        }
        i3 = (i3 * 10) + (cArr[i] - 48);
        i++;
        if (i >= i2) {
            return i3;
        }
        i3 = (i3 * 10) + (cArr[i] - 48);
        i++;
        if (i >= i2) {
            return i3;
        }
        i3 = (i3 * 10) + (cArr[i] - 48);
        i++;
        if (i >= i2) {
            return i3;
        }
        i3 = (i3 * 10) + (cArr[i] - 48);
        i++;
        if (i >= i2) {
            return i3;
        }
        i3 = (i3 * 10) + (cArr[i] - 48);
        i++;
        return i < i2 ? (i3 * 10) + (cArr[i] - 48) : i3;
    }

    public static long b(char[] cArr, int i, int i2) {
        i2 -= 9;
        return (((long) a(cArr, i, i2)) * C.NANOS_PER_SECOND) + ((long) a(cArr, i + i2, 9));
    }

    public static boolean a(char[] cArr, int i, int i2, boolean z) {
        String str = z ? a : b;
        int length = str.length();
        boolean z2 = true;
        if (i2 < length) {
            return true;
        }
        if (i2 > length) {
            return false;
        }
        for (i2 = 0; i2 < length; i2++) {
            int charAt = cArr[i + i2] - str.charAt(i2);
            if (charAt != 0) {
                if (charAt >= 0) {
                    z2 = false;
                }
                return z2;
            }
        }
        return true;
    }

    public static double a(String str) throws NumberFormatException {
        if ("2.2250738585072012e-308".equals(str)) {
            return Double.MIN_VALUE;
        }
        return Double.parseDouble(str);
    }
}
