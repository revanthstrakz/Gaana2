package com.google.android.gms.internal.ads;

import android.support.v4.view.PointerIconCompat;
import com.google.android.exoplayer2.C;
import java.util.UUID;

public final class zzfe {
    public static final int CHANNEL_OUT_7POINT1_SURROUND = (zzqe.SDK_INT < 23 ? PointerIconCompat.TYPE_GRAB : 6396);
    public static final UUID zzwm = new UUID(0, 0);
    private static final UUID zzwn = new UUID(1186680826959645954L, -5988876978535335093L);
    private static final UUID zzwo = new UUID(-1301668207276963122L, -6645017420763422227L);
    public static final UUID zzwp = new UUID(-7348484286925749626L, -6083546864340672619L);

    public static long zzf(long j) {
        return j == C.TIME_UNSET ? C.TIME_UNSET : j / 1000;
    }

    public static long zzg(long j) {
        return j == C.TIME_UNSET ? C.TIME_UNSET : j * 1000;
    }
}
