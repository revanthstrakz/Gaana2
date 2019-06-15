package com.google.android.gms.cast;

import android.os.RemoteException;
import com.google.android.gms.cast.Cast.CastApi.zza;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.BaseImplementation.ResultHolder;
import com.google.android.gms.internal.cast.zzdd;
import com.google.android.gms.internal.cast.zzdo;

final class zzf extends zzdo {
    private final /* synthetic */ String zzae;
    private final /* synthetic */ String zzaf;

    zzf(zza zza, GoogleApiClient googleApiClient, String str, String str2) {
        this.zzae = str;
        this.zzaf = str2;
        super(googleApiClient);
    }

    public final void zza(zzdd zzdd) throws RemoteException {
        try {
            zzdd.zza(this.zzae, this.zzaf, (ResultHolder) this);
        } catch (IllegalArgumentException | IllegalStateException unused) {
            zzp(2001);
        }
    }

    public final /* synthetic */ void doExecute(AnyClient anyClient) throws RemoteException {
        doExecute((zzdd) anyClient);
    }
}
