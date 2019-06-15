package com.fasterxml.jackson.core.io;

import com.gaana.login.sso.SsoErrorCodes;
import com.integralads.avid.library.inmobi.session.internal.InternalAvidAdSessionContext;

public final class g {
    static final String a = String.valueOf(Long.MIN_VALUE);
    static final char[] b = new char[SsoErrorCodes.SDK_NOT_INITIALIZED];
    static final char[] c = new char[SsoErrorCodes.SDK_NOT_INITIALIZED];
    static final byte[] d = new byte[SsoErrorCodes.SDK_NOT_INITIALIZED];
    static final String[] e = new String[]{"0", "1", InternalAvidAdSessionContext.AVID_API_LEVEL, "3", "4", "5", "6", "7", "8", "9", "10"};
    static final String[] f = new String[]{"-1", "-2", "-3", "-4", "-5", "-6", "-7", "-8", "-9", "-10"};
    private static int g = 1000000;
    private static int h = 1000000000;
    private static long i = 10000000000L;
    private static long j = 1000;
    private static long k = -2147483648L;
    private static long l = 2147483647L;

    static {
        int i = 0;
        int i2 = i;
        while (i < 10) {
            char c = (char) (48 + i);
            char c2 = i == 0 ? 0 : c;
            int i3 = i2;
            i2 = 0;
            while (i2 < 10) {
                char c3 = (char) (48 + i2);
                char c4 = (i == 0 && i2 == 0) ? 0 : c3;
                int i4 = i3;
                for (i3 = 0; i3 < 10; i3++) {
                    char c5 = (char) (48 + i3);
                    b[i4] = c2;
                    int i5 = i4 + 1;
                    b[i5] = c4;
                    int i6 = i4 + 2;
                    b[i6] = c5;
                    c[i4] = c;
                    c[i5] = c3;
                    c[i6] = c5;
                    i4 += 4;
                }
                i2++;
                i3 = i4;
            }
            i++;
            i2 = i3;
        }
        for (i = 0; i < SsoErrorCodes.SDK_NOT_INITIALIZED; i++) {
            d[i] = (byte) c[i];
        }
    }

    public static int a(int i, char[] cArr, int i2) {
        int i3;
        if (i < 0) {
            if (i == Integer.MIN_VALUE) {
                return a((long) i, cArr, i2);
            }
            i3 = i2 + 1;
            cArr[i2] = '-';
            i = -i;
            i2 = i3;
        }
        if (i < g) {
            if (i >= 1000) {
                i3 = i / 1000;
                i3 = c(i - (i3 * 1000), cArr, b(i3, cArr, i2));
            } else if (i < 10) {
                i3 = i2 + 1;
                cArr[i2] = (char) (48 + i);
            } else {
                i3 = b(i, cArr, i2);
            }
            return i3;
        }
        int i4;
        Object obj = i >= h ? 1 : null;
        if (obj != null) {
            i -= h;
            if (i >= h) {
                i -= h;
                i4 = i2 + 1;
                cArr[i2] = '2';
            } else {
                i4 = i2 + 1;
                cArr[i2] = '1';
            }
        } else {
            i4 = i2;
        }
        i2 = i / 1000;
        i -= i2 * 1000;
        int i5 = i2 / 1000;
        i2 -= i5 * 1000;
        if (obj != null) {
            i3 = c(i5, cArr, i4);
        } else {
            i3 = b(i5, cArr, i4);
        }
        return c(i, cArr, c(i2, cArr, i3));
    }

    public static int a(int i, byte[] bArr, int i2) {
        int i3;
        if (i < 0) {
            if (i == Integer.MIN_VALUE) {
                return a((long) i, bArr, i2);
            }
            i3 = i2 + 1;
            bArr[i2] = (byte) 45;
            i = -i;
            i2 = i3;
        }
        if (i < g) {
            if (i >= 1000) {
                i3 = i / 1000;
                i3 = c(i - (i3 * 1000), bArr, b(i3, bArr, i2));
            } else if (i < 10) {
                i3 = i2 + 1;
                bArr[i2] = (byte) (48 + i);
            } else {
                i3 = b(i, bArr, i2);
            }
            return i3;
        }
        int i4;
        Object obj = i >= h ? 1 : null;
        if (obj != null) {
            i -= h;
            if (i >= h) {
                i -= h;
                i4 = i2 + 1;
                bArr[i2] = (byte) 50;
            } else {
                i4 = i2 + 1;
                bArr[i2] = (byte) 49;
            }
        } else {
            i4 = i2;
        }
        i2 = i / 1000;
        i -= i2 * 1000;
        int i5 = i2 / 1000;
        i2 -= i5 * 1000;
        if (obj != null) {
            i3 = c(i5, bArr, i4);
        } else {
            i3 = b(i5, bArr, i4);
        }
        return c(i, bArr, c(i2, bArr, i3));
    }

    public static int a(long j, char[] cArr, int i) {
        int length;
        int i2;
        if (j < 0) {
            if (j > k) {
                return a((int) j, cArr, i);
            }
            if (j == Long.MIN_VALUE) {
                length = a.length();
                a.getChars(0, length, cArr, i);
                return i + length;
            }
            i2 = i + 1;
            cArr[i] = '-';
            j = -j;
            i = i2;
        } else if (j <= l) {
            return a((int) j, cArr, i);
        }
        i2 = a(j) + i;
        int i3 = i2;
        while (j > l) {
            i3 -= 3;
            long j2 = j / j;
            c((int) (j - (j * j2)), cArr, i3);
            j = j2;
        }
        length = (int) j;
        while (length >= 1000) {
            i3 -= 3;
            int i4 = length / 1000;
            c(length - (i4 * 1000), cArr, i3);
            length = i4;
        }
        b(length, cArr, i);
        return i2;
    }

    public static int a(long j, byte[] bArr, int i) {
        int length;
        int i2;
        int i3;
        if (j < 0) {
            if (j > k) {
                return a((int) j, bArr, i);
            }
            if (j == Long.MIN_VALUE) {
                length = a.length();
                i2 = 0;
                while (i2 < length) {
                    i3 = i + 1;
                    bArr[i] = (byte) a.charAt(i2);
                    i2++;
                    i = i3;
                }
                return i;
            }
            i3 = i + 1;
            bArr[i] = (byte) 45;
            j = -j;
            i = i3;
        } else if (j <= l) {
            return a((int) j, bArr, i);
        }
        i3 = a(j) + i;
        int i4 = i3;
        while (j > l) {
            i4 -= 3;
            long j2 = j / j;
            c((int) (j - (j * j2)), bArr, i4);
            j = j2;
        }
        length = (int) j;
        while (length >= 1000) {
            i4 -= 3;
            i2 = length / 1000;
            c(length - (i2 * 1000), bArr, i4);
            length = i2;
        }
        b(length, bArr, i);
        return i3;
    }

    private static int b(int i, char[] cArr, int i2) {
        int i3;
        i <<= 2;
        int i4 = i + 1;
        char c = b[i];
        if (c != 0) {
            i3 = i2 + 1;
            cArr[i2] = c;
            i2 = i3;
        }
        i3 = i4 + 1;
        c = b[i4];
        if (c != 0) {
            i4 = i2 + 1;
            cArr[i2] = c;
            i2 = i4;
        }
        i = i2 + 1;
        cArr[i2] = b[i3];
        return i;
    }

    private static int b(int i, byte[] bArr, int i2) {
        int i3;
        i <<= 2;
        int i4 = i + 1;
        char c = b[i];
        if (c != 0) {
            i3 = i2 + 1;
            bArr[i2] = (byte) c;
            i2 = i3;
        }
        i3 = i4 + 1;
        c = b[i4];
        if (c != 0) {
            i4 = i2 + 1;
            bArr[i2] = (byte) c;
            i2 = i4;
        }
        i = i2 + 1;
        bArr[i2] = (byte) b[i3];
        return i;
    }

    private static int c(int i, char[] cArr, int i2) {
        i <<= 2;
        int i3 = i2 + 1;
        int i4 = i + 1;
        cArr[i2] = c[i];
        i = i3 + 1;
        int i5 = i4 + 1;
        cArr[i3] = c[i4];
        i2 = i + 1;
        cArr[i] = c[i5];
        return i2;
    }

    private static int c(int i, byte[] bArr, int i2) {
        i <<= 2;
        int i3 = i2 + 1;
        int i4 = i + 1;
        bArr[i2] = d[i];
        i = i3 + 1;
        int i5 = i4 + 1;
        bArr[i3] = d[i4];
        i2 = i + 1;
        bArr[i] = d[i5];
        return i2;
    }

    private static int a(long j) {
        int i = 10;
        for (long j2 = i; j >= j2 && i != 19; j2 = (j2 << 3) + (j2 << 1)) {
            i++;
        }
        return i;
    }
}
