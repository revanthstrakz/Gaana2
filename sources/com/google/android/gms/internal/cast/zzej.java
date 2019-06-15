package com.google.android.gms.internal.cast;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;

final class zzej extends zzem {
    private final /* synthetic */ zzeh zzaaz;
    private final /* synthetic */ String zzaba;

    zzej(zzeh zzeh, GoogleApiClient googleApiClient, String str) {
        this.zzaaz = zzeh;
        this.zzaba = str;
        super(zzeh, googleApiClient);
    }

    public final void zza(zzer zzer) throws RemoteException {
        zzer.zza(new zzen(this, zzer), this.zzaaz.zzaay, this.zzaba);
    }

    public final /* synthetic */ void doExecute(AnyClient anyClient) throws RemoteException {
        doExecute((zzer) anyClient);
    }
}
