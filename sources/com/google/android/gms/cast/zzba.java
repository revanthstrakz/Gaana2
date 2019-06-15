package com.google.android.gms.cast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.internal.cast.zzdd;
import org.json.JSONObject;

final class zzba extends zzb {
    private final /* synthetic */ RemoteMediaPlayer zzfl;
    private final /* synthetic */ MediaQueueItem[] zzfo;
    private final /* synthetic */ int zzfp;
    private final /* synthetic */ int zzfq;
    private final /* synthetic */ long zzfr;
    private final /* synthetic */ JSONObject zzfs;

    zzba(RemoteMediaPlayer remoteMediaPlayer, GoogleApiClient googleApiClient, MediaQueueItem[] mediaQueueItemArr, int i, int i2, long j, JSONObject jSONObject) {
        this.zzfl = remoteMediaPlayer;
        this.zzfo = mediaQueueItemArr;
        this.zzfp = i;
        this.zzfq = i2;
        this.zzfr = j;
        this.zzfs = jSONObject;
        super(googleApiClient);
    }

    /* Access modifiers changed, original: protected|final */
    public final void zzb(zzdd zzdd) {
        this.zzfl.zzff.zza(this.zzgm, this.zzfo, null, new zzar().zzb(this.zzfp).zzc(this.zzfq).zzb(this.zzfr).zzg(this.zzfs).zzl());
    }
}
