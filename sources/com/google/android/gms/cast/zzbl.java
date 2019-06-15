package com.google.android.gms.cast;

import com.google.android.gms.cast.RemoteMediaPlayer.MediaChannelResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.cast.zzdd;
import com.google.android.gms.internal.cast.zzea;
import org.json.JSONObject;

final class zzbl extends zzb {
    private final /* synthetic */ RemoteMediaPlayer zzfl;
    private final /* synthetic */ long zzfr;
    private final /* synthetic */ JSONObject zzfs;
    private final /* synthetic */ int zzgc;

    zzbl(RemoteMediaPlayer remoteMediaPlayer, GoogleApiClient googleApiClient, int i, long j, JSONObject jSONObject) {
        this.zzfl = remoteMediaPlayer;
        this.zzgc = i;
        this.zzfr = j;
        this.zzfs = jSONObject;
        super(googleApiClient);
    }

    /* Access modifiers changed, original: protected|final */
    public final void zzb(zzdd zzdd) throws zzea {
        if (this.zzfl.zzf(this.zzgc) == -1) {
            setResult((MediaChannelResult) createFailedResult(new Status(0)));
        } else {
            this.zzfl.zzff.zza(this.zzgm, this.zzgc, this.zzfr, null, 0, null, this.zzfs);
        }
    }
}
