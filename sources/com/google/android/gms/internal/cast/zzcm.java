package com.google.android.gms.internal.cast;

import com.google.android.gms.cast.games.GameManagerClient;
import com.google.android.gms.cast.games.GameManagerClient.GameManagerInstanceResult;
import com.google.android.gms.common.api.Status;

final class zzcm implements GameManagerInstanceResult {
    private final Status zzgq;
    private final GameManagerClient zzwr;

    zzcm(Status status, GameManagerClient gameManagerClient) {
        this.zzgq = status;
        this.zzwr = gameManagerClient;
    }

    public final Status getStatus() {
        return this.zzgq;
    }

    public final GameManagerClient getGameManagerClient() {
        return this.zzwr;
    }
}
