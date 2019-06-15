package com.google.android.gms.internal.cast;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;

final class zzek extends zzem {
    zzek(zzeh zzeh, GoogleApiClient googleApiClient) {
        super(zzeh, googleApiClient);
    }

    public final void zza(zzer zzer) throws RemoteException {
        zzer.zza(new zzeo(this));
    }

    public final /* synthetic */ void doExecute(AnyClient anyClient) throws RemoteException {
        doExecute((zzer) anyClient);
    }
}
