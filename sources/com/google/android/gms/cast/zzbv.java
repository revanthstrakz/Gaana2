package com.google.android.gms.cast;

import android.util.Log;
import com.google.android.gms.cast.RemoteMediaPlayer.MediaChannelResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.cast.zzec;
import org.json.JSONObject;

final class zzbv implements zzec {
    private final /* synthetic */ RemoteMediaPlayer zzgo;
    private final /* synthetic */ zzb zzgp;

    zzbv(zzb zzb, RemoteMediaPlayer remoteMediaPlayer) {
        this.zzgp = zzb;
        this.zzgo = remoteMediaPlayer;
    }

    public final void zzd(long j) {
        try {
            this.zzgp.setResult((MediaChannelResult) this.zzgp.createFailedResult(new Status(2103)));
        } catch (IllegalStateException e) {
            Log.e("RemoteMediaPlayer", "Result already set when calling onRequestReplaced", e);
        }
    }

    public final void zza(long j, int i, Object obj) {
        try {
            this.zzgp.setResult(new zzc(new Status(i), obj instanceof JSONObject ? (JSONObject) obj : null));
        } catch (IllegalStateException e) {
            Log.e("RemoteMediaPlayer", "Result already set when calling onRequestCompleted", e);
        }
    }
}
