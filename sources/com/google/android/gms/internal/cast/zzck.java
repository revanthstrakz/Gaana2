package com.google.android.gms.internal.cast;

import com.google.android.gms.cast.games.GameManagerClient;
import com.google.android.gms.cast.games.GameManagerClient.GameManagerInstanceResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.util.VisibleForTesting;

@VisibleForTesting
public abstract class zzck extends zzcj<GameManagerInstanceResult> {
    final /* synthetic */ zzcb zzwi;
    private GameManagerClient zzwr;

    public zzck(zzcb zzcb, GameManagerClient gameManagerClient) {
        this.zzwi = zzcb;
        super(zzcb);
        this.zzwr = gameManagerClient;
        this.zzwq = new zzcl(this, zzcb);
    }

    public static GameManagerInstanceResult zzc(Status status) {
        return new zzcm(status, null);
    }

    public /* synthetic */ Result createFailedResult(Status status) {
        return zzc(status);
    }
}
