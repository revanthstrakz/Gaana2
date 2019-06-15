package com.google.android.gms.internal.ads;

import android.support.v4.media.session.PlaybackStateCompat;
import java.io.IOException;

final class zzit {
    private final zzpx zzahz = new zzpx(8);
    private int zzaky;

    public final boolean zza(zzia zzia) throws IOException, InterruptedException {
        long zzhd;
        zzia zzia2 = zzia;
        long length = zzia.getLength();
        int i = (length > -1 ? 1 : (length == -1 ? 0 : -1));
        long j = PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
        if (i != 0 && length <= PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID) {
            j = length;
        }
        i = (int) j;
        zzia2.zza(this.zzahz.data, 0, 4);
        this.zzaky = 4;
        for (zzhd = this.zzahz.zzhd(); zzhd != 440786851; zzhd = ((zzhd << 8) & -256) | ((long) (this.zzahz.data[0] & 255))) {
            int i2 = this.zzaky + 1;
            this.zzaky = i2;
            if (i2 == i) {
                return false;
            }
            zzia2.zza(this.zzahz.data, 0, 1);
        }
        zzhd = zzc(zzia);
        long j2 = (long) this.zzaky;
        long j3 = Long.MIN_VALUE;
        if (zzhd == Long.MIN_VALUE || (length != -1 && j2 + zzhd >= length)) {
            return false;
        }
        while (true) {
            long j4 = j2 + zzhd;
            if (((long) this.zzaky) < j4) {
                if (zzc(zzia) == j3) {
                    return false;
                }
                length = zzc(zzia);
                if (length < 0 || length > 2147483647L) {
                    return false;
                }
                if (length != 0) {
                    zzia2.zzx((int) length);
                    this.zzaky = (int) (((long) this.zzaky) + length);
                }
                j3 = Long.MIN_VALUE;
            } else if (((long) this.zzaky) == j4) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    private final long zzc(zzia zzia) throws IOException, InterruptedException {
        int i = 0;
        zzia.zza(this.zzahz.data, 0, 1);
        int i2 = this.zzahz.data[0] & 255;
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
        zzia.zza(this.zzahz.data, 1, i4);
        while (i < i4) {
            i++;
            i2 = (this.zzahz.data[i] & 255) + (i2 << 8);
        }
        this.zzaky += i4 + 1;
        return (long) i2;
    }
}
