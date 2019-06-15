package com.google.android.gms.cast;

import com.google.android.gms.cast.MediaLoadOptions.Builder;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.internal.cast.zzdd;
import org.json.JSONObject;

final class zzbi extends zzb {
    private final /* synthetic */ RemoteMediaPlayer zzfl;
    private final /* synthetic */ long zzfr;
    private final /* synthetic */ JSONObject zzfs;
    private final /* synthetic */ boolean zzfz;
    private final /* synthetic */ long[] zzga;
    private final /* synthetic */ MediaInfo zzgb;

    zzbi(RemoteMediaPlayer remoteMediaPlayer, GoogleApiClient googleApiClient, boolean z, long j, long[] jArr, JSONObject jSONObject, MediaInfo mediaInfo) {
        this.zzfl = remoteMediaPlayer;
        this.zzfz = z;
        this.zzfr = j;
        this.zzga = jArr;
        this.zzfs = jSONObject;
        this.zzgb = mediaInfo;
        super(googleApiClient);
    }

    /* Access modifiers changed, original: protected|final */
    public final void zzb(zzdd zzdd) {
        synchronized (this.zzfl.lock) {
            this.zzfl.zzff.zza(this.zzgm, this.zzgb, null, new Builder().setAutoplay(this.zzfz).setPlayPosition(this.zzfr).setActiveTrackIds(this.zzga).setCustomData(this.zzfs).build());
        }
    }
}
