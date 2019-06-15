package com.google.android.gms.cast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.internal.cast.zzdd;
import com.google.android.gms.internal.cast.zzea;
import org.json.JSONObject;

final class zzbc extends zzb {
    private final /* synthetic */ RemoteMediaPlayer zzfl;
    private final /* synthetic */ long zzfr;
    private final /* synthetic */ JSONObject zzfs;
    private final /* synthetic */ int zzfu;
    private final /* synthetic */ MediaQueueItem zzfv;

    zzbc(RemoteMediaPlayer remoteMediaPlayer, GoogleApiClient googleApiClient, MediaQueueItem mediaQueueItem, int i, long j, JSONObject jSONObject) {
        this.zzfl = remoteMediaPlayer;
        this.zzfv = mediaQueueItem;
        this.zzfu = i;
        this.zzfr = j;
        this.zzfs = jSONObject;
        super(googleApiClient);
    }

    /* Access modifiers changed, original: protected|final */
    public final void zzb(zzdd zzdd) throws zzea {
        this.zzfl.zzff.zza(this.zzgm, new MediaQueueItem[]{this.zzfv}, this.zzfu, 0, 0, this.zzfr, this.zzfs);
    }
}
