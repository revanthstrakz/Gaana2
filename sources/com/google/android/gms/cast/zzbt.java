package com.google.android.gms.cast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.internal.cast.zzdd;

final class zzbt extends zzb {
    private final /* synthetic */ RemoteMediaPlayer zzfl;

    zzbt(RemoteMediaPlayer remoteMediaPlayer, GoogleApiClient googleApiClient) {
        this.zzfl = remoteMediaPlayer;
        super(googleApiClient);
    }

    /* Access modifiers changed, original: protected|final */
    public final void zzb(zzdd zzdd) {
        this.zzfl.zzff.zzb(this.zzgm);
    }
}
