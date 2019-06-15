package com.google.ads.interactivemedia.v3.internal;

import android.support.v4.media.session.PlaybackStateCompat;
import java.io.IOException;

final class el {
    private final fp a = new fp(8);
    private int b;

    public boolean a(cd cdVar) throws IOException, InterruptedException {
        long k;
        cd cdVar2 = cdVar;
        long d = cdVar.d();
        int i = (d > -1 ? 1 : (d == -1 ? 0 : -1));
        long j = PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
        if (i != 0 && d <= PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID) {
            j = d;
        }
        i = (int) j;
        cdVar2.c(this.a.a, 0, 4);
        this.b = 4;
        for (k = this.a.k(); k != 440786851; k = ((k << 8) & -256) | ((long) (this.a.a[0] & 255))) {
            int i2 = this.b + 1;
            this.b = i2;
            if (i2 == i) {
                return false;
            }
            cdVar2.c(this.a.a, 0, 1);
        }
        k = b(cdVar);
        long j2 = (long) this.b;
        if (k == Long.MIN_VALUE || (d != -1 && j2 + k >= d)) {
            return false;
        }
        while (true) {
            long j3 = j2 + k;
            if (((long) this.b) >= j3) {
                return ((long) this.b) == j3;
            } else if (b(cdVar) == Long.MIN_VALUE) {
                return false;
            } else {
                d = b(cdVar);
                if (d < 0 || d > 2147483647L) {
                    return false;
                }
                if (d != 0) {
                    cdVar2.c((int) d);
                    this.b = (int) (((long) this.b) + d);
                }
            }
        }
        return false;
    }

    private long b(cd cdVar) throws IOException, InterruptedException {
        int i = 0;
        cdVar.c(this.a.a, 0, 1);
        int i2 = this.a.a[0] & 255;
        if (i2 == 0) {
            return Long.MIN_VALUE;
        }
        int i3 = 128;
        int i4 = 0;
        while ((i2 & i3) == 0) {
            i3 >>= 1;
            i4++;
        }
        i2 &= i3 ^ -1;
        cdVar.c(this.a.a, 1, i4);
        while (i < i4) {
            i++;
            i2 = (this.a.a[i] & 255) + (i2 << 8);
        }
        this.b += i4 + 1;
        return (long) i2;
    }
}
