package com.google.android.gms.tagmanager;

import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.VisibleForTesting;

@VisibleForTesting
final class zzdg implements zzej {
    private final long zzabb = 900000;
    private final int zzabc = 5;
    private double zzabd = ((double) Math.min(1, 5));
    private long zzabe;
    private final Object zzabf = new Object();
    private final long zzbdl = DefaultRenderersFactory.DEFAULT_ALLOWED_VIDEO_JOINING_TIME_MS;
    private final Clock zzrz;
    private final String zzul;

    public zzdg(int i, int i2, long j, long j2, String str, Clock clock) {
        this.zzul = str;
        this.zzrz = clock;
    }

    public final boolean zzew() {
        synchronized (this.zzabf) {
            long currentTimeMillis = this.zzrz.currentTimeMillis();
            String str;
            StringBuilder stringBuilder;
            if (currentTimeMillis - this.zzabe < this.zzbdl) {
                str = this.zzul;
                stringBuilder = new StringBuilder(34 + String.valueOf(str).length());
                stringBuilder.append("Excessive ");
                stringBuilder.append(str);
                stringBuilder.append(" detected; call ignored.");
                zzdi.zzab(stringBuilder.toString());
                return false;
            }
            if (this.zzabd < ((double) this.zzabc)) {
                double d = ((double) (currentTimeMillis - this.zzabe)) / ((double) this.zzabb);
                if (d > 0.0d) {
                    this.zzabd = Math.min((double) this.zzabc, this.zzabd + d);
                }
            }
            this.zzabe = currentTimeMillis;
            if (this.zzabd >= 1.0d) {
                this.zzabd -= 1.0d;
                return true;
            }
            str = this.zzul;
            stringBuilder = new StringBuilder(34 + String.valueOf(str).length());
            stringBuilder.append("Excessive ");
            stringBuilder.append(str);
            stringBuilder.append(" detected; call ignored.");
            zzdi.zzab(stringBuilder.toString());
            return false;
        }
    }
}
