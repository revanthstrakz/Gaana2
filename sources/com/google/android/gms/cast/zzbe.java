package com.google.android.gms.cast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.internal.cast.zzdd;
import com.google.android.gms.internal.cast.zzea;
import org.json.JSONObject;

final class zzbe extends zzb {
    private final /* synthetic */ RemoteMediaPlayer zzfl;
    private final /* synthetic */ JSONObject zzfs;
    private final /* synthetic */ int[] zzfx;

    zzbe(RemoteMediaPlayer remoteMediaPlayer, GoogleApiClient googleApiClient, int[] iArr, JSONObject jSONObject) {
        this.zzfl = remoteMediaPlayer;
        this.zzfx = iArr;
        this.zzfs = jSONObject;
        super(googleApiClient);
    }

    /* Access modifiers changed, original: protected|final */
    public final void zzb(zzdd zzdd) throws zzea {
        this.zzfl.zzff.zza(this.zzgm, this.zzfx, this.zzfs);
    }
}
