package com.google.android.gms.internal.cast;

import android.annotation.TargetApi;
import android.hardware.display.VirtualDisplay;
import com.google.android.gms.cast.CastRemoteDisplay.CastRemoteDisplaySessionResult;
import com.google.android.gms.cast.CastRemoteDisplayApi;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;

@Deprecated
public final class zzeh implements CastRemoteDisplayApi {
    private static final zzdw zzbf = new zzdw("CastRemoteDisplayApiImpl");
    private Api<?> zzaax;
    private final zzex zzaay = new zzei(this);
    private VirtualDisplay zzbg;

    public zzeh(Api api) {
        this.zzaax = api;
    }

    public final PendingResult<CastRemoteDisplaySessionResult> startRemoteDisplay(GoogleApiClient googleApiClient, String str) {
        zzbf.d("startRemoteDisplay", new Object[0]);
        return googleApiClient.execute(new zzej(this, googleApiClient, str));
    }

    public final PendingResult<CastRemoteDisplaySessionResult> stopRemoteDisplay(GoogleApiClient googleApiClient) {
        zzbf.d("stopRemoteDisplay", new Object[0]);
        return googleApiClient.execute(new zzek(this, googleApiClient));
    }

    @TargetApi(19)
    private final void zzc() {
        if (this.zzbg != null) {
            if (this.zzbg.getDisplay() != null) {
                zzdw zzdw = zzbf;
                int displayId = this.zzbg.getDisplay().getDisplayId();
                StringBuilder stringBuilder = new StringBuilder(38);
                stringBuilder.append("releasing virtual display: ");
                stringBuilder.append(displayId);
                zzdw.d(stringBuilder.toString(), new Object[0]);
            }
            this.zzbg.release();
            this.zzbg = null;
        }
    }
}
