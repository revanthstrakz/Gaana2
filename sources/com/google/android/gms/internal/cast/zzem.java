package com.google.android.gms.internal.cast;

import android.os.RemoteException;
import com.google.android.gms.cast.CastRemoteDisplay.CastRemoteDisplaySessionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl;
import com.google.android.gms.common.util.VisibleForTesting;

@VisibleForTesting
class zzem extends ApiMethodImpl<CastRemoteDisplaySessionResult, zzer> {
    final /* synthetic */ zzeh zzaaz;

    public zzem(zzeh zzeh, GoogleApiClient googleApiClient) {
        this.zzaaz = zzeh;
        super(zzeh.zzaax, googleApiClient);
    }

    @VisibleForTesting
    /* renamed from: zza */
    public void doExecute(zzer zzer) throws RemoteException {
    }

    /* Access modifiers changed, original: protected|synthetic */
    public /* synthetic */ Result createFailedResult(Status status) {
        return new zzep(status);
    }
}
