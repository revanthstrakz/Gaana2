package com.google.android.gms.internal.cast;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.List;

final class zzcz extends TaskApiCall<zzdb, Void> {
    private final /* synthetic */ List zzpp;
    private final /* synthetic */ String[] zzxu;
    private final /* synthetic */ String zzxv;

    zzcz(zzcx zzcx, String[] strArr, String str, List list) {
        this.zzxu = strArr;
        this.zzxv = str;
        this.zzpp = list;
    }

    /* Access modifiers changed, original: protected|final|synthetic */
    public final /* synthetic */ void doExecute(AnyClient anyClient, TaskCompletionSource taskCompletionSource) throws RemoteException {
        zzdb zzdb = (zzdb) anyClient;
        ((zzdt) zzdb.getService()).zza(new zzda(this, taskCompletionSource), this.zzxu, this.zzxv, this.zzpp);
    }
}
