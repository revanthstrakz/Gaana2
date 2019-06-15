package com.google.ads.interactivemedia.v3.internal;

import android.support.v4.media.session.PlaybackStateCompat;
import java.io.IOException;

final class dc {
    private static final int[] a = new int[]{ft.c("isom"), ft.c("iso2"), ft.c("iso3"), ft.c("iso4"), ft.c("iso5"), ft.c("iso6"), ft.c("avc1"), ft.c("hvc1"), ft.c("hev1"), ft.c("mp41"), ft.c("mp42"), ft.c("3g2a"), ft.c("3g2b"), ft.c("3gr6"), ft.c("3gs6"), ft.c("3ge6"), ft.c("3gg6"), ft.c("M4V "), ft.c("M4A "), ft.c("f4v "), ft.c("kddi"), ft.c("M4VP"), ft.c("qt  "), ft.c("MSNV")};

    public static boolean a(cd cdVar) throws IOException, InterruptedException {
        return a(cdVar, true);
    }

    public static boolean b(cd cdVar) throws IOException, InterruptedException {
        return a(cdVar, false);
    }

    private static boolean a(cd cdVar, boolean z) throws IOException, InterruptedException {
        boolean z2;
        boolean z3;
        cd cdVar2 = cdVar;
        long d = cdVar.d();
        if (d == -1 || d > PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM) {
            d = PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM;
        }
        int i = (int) d;
        fp fpVar = new fp(64);
        boolean z4 = false;
        int i2 = 0;
        int i3 = i2;
        while (i2 < i) {
            int i4;
            cdVar2.c(fpVar.a, 0, 8);
            fpVar.c(0);
            long k = fpVar.k();
            int m = fpVar.m();
            if (k == 1) {
                cdVar2.c(fpVar.a, 8, 8);
                i4 = 16;
                k = fpVar.u();
            } else {
                i4 = 8;
            }
            long j = (long) i4;
            if (k < j) {
                return false;
            }
            i2 += i4;
            if (m != cv.B) {
                if (m == cv.K || m == cv.M) {
                    z2 = true;
                    z3 = true;
                    break;
                }
                int i5 = m;
                if ((((long) i2) + k) - j >= ((long) i)) {
                    break;
                }
                int i6 = (int) (k - j);
                i2 += i6;
                if (i5 == cv.a) {
                    if (i6 < 8) {
                        return false;
                    }
                    if (fpVar.e() < i6) {
                        fpVar.a(new byte[i6], i6);
                    }
                    cdVar2.c(fpVar.a, 0, i6);
                    i6 /= 4;
                    for (m = 0; m < i6; m++) {
                        if (m == 1) {
                            fpVar.d(4);
                        } else if (a(fpVar.m())) {
                            i3 = 1;
                            break;
                        }
                    }
                    if (i3 == 0) {
                        return false;
                    }
                } else if (i6 != 0) {
                    cdVar2.c(i6);
                }
            }
        }
        z2 = true;
        z3 = false;
        if (i3 != 0 && z == z3) {
            z4 = z2;
        }
        return z4;
    }

    private static boolean a(int i) {
        if ((i >>> 8) == ft.c("3gp")) {
            return true;
        }
        for (int i2 : a) {
            if (i2 == i) {
                return true;
            }
        }
        return false;
    }
}
