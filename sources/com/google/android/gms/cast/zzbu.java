package com.google.android.gms.cast;

import android.support.annotation.NonNull;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

final class zzbu implements ResultCallback<Status> {
    private final long zzgk;
    private final /* synthetic */ zza zzgl;

    zzbu(zza zza, long j) {
        this.zzgl = zza;
        this.zzgk = j;
    }

    public final /* synthetic */ void onResult(@NonNull Result result) {
        Status status = (Status) result;
        if (!status.isSuccess()) {
            RemoteMediaPlayer.this.zzff.zza(this.zzgk, status.getStatusCode());
        }
    }
}
