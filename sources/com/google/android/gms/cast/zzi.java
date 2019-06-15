package com.google.android.gms.cast;

import android.os.RemoteException;
import com.google.android.gms.cast.Cast.CastApi.zza;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.internal.cast.zzdd;

final class zzi extends zza {
    private final /* synthetic */ String val$sessionId;
    private final /* synthetic */ String zzag;
    private final /* synthetic */ zzag zzai = null;

    zzi(zza zza, GoogleApiClient googleApiClient, String str, String str2, zzag zzag) {
        this.zzag = str;
        this.val$sessionId = str2;
        super(googleApiClient);
    }

    public final void zza(zzdd zzdd) throws RemoteException {
        try {
            zzdd.zza(this.zzag, this.val$sessionId, this.zzai, this);
        } catch (IllegalStateException unused) {
            zzp(2001);
        }
    }

    public final /* synthetic */ void doExecute(AnyClient anyClient) throws RemoteException {
        doExecute((zzdd) anyClient);
    }
}
