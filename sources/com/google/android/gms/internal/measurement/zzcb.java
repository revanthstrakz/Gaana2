package com.google.android.gms.internal.measurement;

import com.google.android.gms.common.util.VisibleForTesting;
import com.integralads.avid.library.inmobi.utils.AvidJSONUtil;

@VisibleForTesting
public final class zzcb extends zzau {
    @VisibleForTesting
    zzcb(zzaw zzaw) {
        super(zzaw);
    }

    /* Access modifiers changed, original: protected|final */
    public final void zzag() {
    }

    public final zzac zzek() {
        zzcl();
        return zzca().zzae();
    }

    public final String zzel() {
        zzcl();
        zzac zzek = zzek();
        int i = zzek.zzuh;
        int i2 = zzek.zzui;
        StringBuilder stringBuilder = new StringBuilder(23);
        stringBuilder.append(i);
        stringBuilder.append(AvidJSONUtil.KEY_X);
        stringBuilder.append(i2);
        return stringBuilder.toString();
    }
}
