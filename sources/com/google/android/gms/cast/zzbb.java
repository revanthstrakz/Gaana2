package com.google.android.gms.cast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.internal.cast.zzdd;
import com.google.android.gms.internal.cast.zzea;
import org.json.JSONObject;

final class zzbb extends zzb {
    private final /* synthetic */ RemoteMediaPlayer zzfl;
    private final /* synthetic */ JSONObject zzfs;
    private final /* synthetic */ MediaQueueItem[] zzft;
    private final /* synthetic */ int zzfu;

    zzbb(RemoteMediaPlayer remoteMediaPlayer, GoogleApiClient googleApiClient, MediaQueueItem[] mediaQueueItemArr, int i, JSONObject jSONObject) {
        this.zzfl = remoteMediaPlayer;
        this.zzft = mediaQueueItemArr;
        this.zzfu = i;
        this.zzfs = jSONObject;
        super(googleApiClient);
    }

    /* Access modifiers changed, original: protected|final */
    public final void zzb(zzdd zzdd) throws zzea {
        this.zzfl.zzff.zza(this.zzgm, this.zzft, this.zzfu, 0, -1, -1, this.zzfs);
    }
}
