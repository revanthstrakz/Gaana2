package com.google.android.gms.internal.cast;

import android.view.Display;
import com.google.android.gms.cast.CastRemoteDisplay.CastRemoteDisplaySessionResult;
import com.google.android.gms.common.api.Status;

final class zzep implements CastRemoteDisplaySessionResult {
    private final Display zzbz;
    private final Status zzgq;

    public zzep(Display display) {
        this.zzgq = Status.RESULT_SUCCESS;
        this.zzbz = display;
    }

    public zzep(Status status) {
        this.zzgq = status;
        this.zzbz = null;
    }

    public final Status getStatus() {
        return this.zzgq;
    }

    public final Display getPresentationDisplay() {
        return this.zzbz;
    }
}
