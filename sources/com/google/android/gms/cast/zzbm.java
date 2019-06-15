package com.google.android.gms.cast;

import com.google.android.gms.cast.RemoteMediaPlayer.MediaChannelResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.cast.zzdd;
import com.google.android.gms.internal.cast.zzea;
import java.util.Locale;
import org.json.JSONObject;

final class zzbm extends zzb {
    private final /* synthetic */ RemoteMediaPlayer zzfl;
    private final /* synthetic */ JSONObject zzfs;
    private final /* synthetic */ int zzgc;
    private final /* synthetic */ int zzgd;

    zzbm(RemoteMediaPlayer remoteMediaPlayer, GoogleApiClient googleApiClient, int i, int i2, JSONObject jSONObject) {
        this.zzfl = remoteMediaPlayer;
        this.zzgc = i;
        this.zzgd = i2;
        this.zzfs = jSONObject;
        super(googleApiClient);
    }

    /* Access modifiers changed, original: protected|final */
    public final void zzb(zzdd zzdd) throws zzea {
        int zza = this.zzfl.zzf(this.zzgc);
        if (zza == -1) {
            setResult((MediaChannelResult) createFailedResult(new Status(0)));
        } else if (this.zzgd < 0) {
            setResult((MediaChannelResult) createFailedResult(new Status(2001, String.format(Locale.ROOT, "Invalid request: Invalid newIndex %d.", new Object[]{Integer.valueOf(this.zzgd)}))));
        } else if (zza == this.zzgd) {
            setResult((MediaChannelResult) createFailedResult(new Status(0)));
        } else {
            MediaQueueItem queueItem = this.zzfl.getMediaStatus().getQueueItem(this.zzgd > zza ? this.zzgd + 1 : this.zzgd);
            this.zzfl.zzff.zza(this.zzgm, new int[]{this.zzgc}, queueItem != null ? queueItem.getItemId() : 0, this.zzfs);
        }
    }
}
