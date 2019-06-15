package com.google.android.gms.cast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.internal.cast.zzdd;
import com.google.android.gms.internal.cast.zzea;

final class zzaz extends zzb {
    private final /* synthetic */ RemoteMediaPlayer zzfl;
    private final /* synthetic */ TextTrackStyle zzfn;

    zzaz(RemoteMediaPlayer remoteMediaPlayer, GoogleApiClient googleApiClient, TextTrackStyle textTrackStyle) {
        this.zzfl = remoteMediaPlayer;
        this.zzfn = textTrackStyle;
        super(googleApiClient);
    }

    /* Access modifiers changed, original: protected|final */
    public final void zzb(zzdd zzdd) throws zzea {
        this.zzfl.zzff.zza(this.zzgm, this.zzfn);
    }
}
