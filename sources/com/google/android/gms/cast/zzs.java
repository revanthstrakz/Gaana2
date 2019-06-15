package com.google.android.gms.cast;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.internal.cast.zzeq;
import com.google.android.gms.internal.cast.zzev;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzs extends TaskApiCall<zzeq, Void> {
    final /* synthetic */ CastRemoteDisplayClient zzbk;

    zzs(CastRemoteDisplayClient castRemoteDisplayClient) {
        this.zzbk = castRemoteDisplayClient;
    }

    /* Access modifiers changed, original: protected|final|synthetic */
    public final /* synthetic */ void doExecute(AnyClient anyClient, TaskCompletionSource taskCompletionSource) throws RemoteException {
        zzeq zzeq = (zzeq) anyClient;
        ((zzev) zzeq.getService()).zza(new zzt(this, taskCompletionSource));
    }
}
