package com.google.android.gms.cast;

import com.google.android.gms.cast.RemoteMediaPlayer.MediaChannelResult;
import com.google.android.gms.common.api.Status;
import org.json.JSONObject;

final class zzbw implements MediaChannelResult {
    private final /* synthetic */ Status zzam;

    zzbw(zzb zzb, Status status) {
        this.zzam = status;
    }

    public final JSONObject getCustomData() {
        return null;
    }

    public final Status getStatus() {
        return this.zzam;
    }
}
