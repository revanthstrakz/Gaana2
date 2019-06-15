package com.google.android.gms.internal.cast;

import com.google.android.gms.cast.games.GameManagerClient.GameManagerResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.util.VisibleForTesting;

@VisibleForTesting
public abstract class zzch extends zzcj<GameManagerResult> {
    final /* synthetic */ zzcb zzwi;

    public zzch(zzcb zzcb) {
        this.zzwi = zzcb;
        super(zzcb);
        this.zzwq = new zzci(this, zzcb);
    }

    public static GameManagerResult zzb(Status status) {
        return new zzcn(status, null, -1, null);
    }

    public /* synthetic */ Result createFailedResult(Status status) {
        return zzb(status);
    }
}
