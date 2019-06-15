package com.google.android.gms.cast;

import android.os.RemoteException;
import com.google.android.gms.cast.Cast.CastApi.zza;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.BaseImplementation.ResultHolder;
import com.google.android.gms.internal.cast.zzdd;

final class zzh extends zza {
    private final /* synthetic */ String zzag;
    private final /* synthetic */ LaunchOptions zzah;

    zzh(zza zza, GoogleApiClient googleApiClient, String str, LaunchOptions launchOptions) {
        this.zzag = str;
        this.zzah = launchOptions;
        super(googleApiClient);
    }

    public final void zza(zzdd zzdd) throws RemoteException {
        try {
            zzdd.zza(this.zzag, this.zzah, (ResultHolder) this);
        } catch (IllegalStateException unused) {
            zzp(2001);
        }
    }

    public final /* synthetic */ void doExecute(AnyClient anyClient) throws RemoteException {
        doExecute((zzdd) anyClient);
    }
}
