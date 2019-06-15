package com.google.android.gms.cast;

import android.os.RemoteException;
import android.text.TextUtils;
import com.google.android.gms.cast.Cast.CastApi.zza;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation.ResultHolder;
import com.google.android.gms.internal.cast.zzdd;
import com.google.android.gms.internal.cast.zzdo;

final class zzl extends zzdo {
    private final /* synthetic */ String val$sessionId;

    zzl(zza zza, GoogleApiClient googleApiClient, String str) {
        this.val$sessionId = str;
        super(googleApiClient);
    }

    public final void zza(zzdd zzdd) throws RemoteException {
        if (TextUtils.isEmpty(this.val$sessionId)) {
            setResult(createFailedResult(new Status(2001, "IllegalArgument: sessionId cannot be null or empty", null)));
            return;
        }
        try {
            zzdd.zza(this.val$sessionId, (ResultHolder) this);
        } catch (IllegalStateException unused) {
            zzp(2001);
        }
    }

    public final /* synthetic */ void doExecute(AnyClient anyClient) throws RemoteException {
        doExecute((zzdd) anyClient);
    }
}
