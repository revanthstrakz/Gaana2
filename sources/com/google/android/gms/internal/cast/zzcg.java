package com.google.android.gms.internal.cast;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

final class zzcg implements ResultCallback<Status> {
    private final /* synthetic */ zzcb zzwi;
    private final /* synthetic */ long zzwn;

    zzcg(zzcb zzcb, long j) {
        this.zzwi = zzcb;
        this.zzwn = j;
    }

    public final /* synthetic */ void onResult(Result result) {
        Status status = (Status) result;
        if (!status.isSuccess()) {
            this.zzwi.zza(this.zzwn, status.getStatusCode());
        }
    }
}
