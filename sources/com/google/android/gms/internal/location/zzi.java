package com.google.android.gms.internal.location;

import android.app.PendingIntent;
import android.os.RemoteException;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.BaseImplementation.ResultHolder;

final class zzi extends zzj {
    private final /* synthetic */ PendingIntent zzbz;

    zzi(zze zze, GoogleApiClient googleApiClient, PendingIntent pendingIntent) {
        this.zzbz = pendingIntent;
        super(googleApiClient);
    }

    /* Access modifiers changed, original: protected|final|synthetic */
    public final /* synthetic */ void doExecute(AnyClient anyClient) throws RemoteException {
        ((zzaz) anyClient).zza(this.zzbz, (ResultHolder) this);
    }
}
