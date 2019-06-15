package com.google.android.gms.cast;

import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.TaskUtil;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzt extends zza {
    private final /* synthetic */ TaskCompletionSource zzbl;
    private final /* synthetic */ zzs zzbo;

    zzt(zzs zzs, TaskCompletionSource taskCompletionSource) {
        this.zzbo = zzs;
        this.zzbl = taskCompletionSource;
        super();
    }

    public final void onDisconnected() throws RemoteException {
        this.zzbo.zzbk.zzbf.d("onDisconnected", new Object[0]);
        this.zzbo.zzbk.zzc();
        TaskUtil.setResultOrApiException(Status.RESULT_SUCCESS, this.zzbl);
    }

    public final void onError(int i) throws RemoteException {
        this.zzbo.zzbk.zzbf.d("onError: %d", Integer.valueOf(i));
        this.zzbo.zzbk.zzc();
        TaskUtil.setResultOrApiException(Status.RESULT_INTERNAL_ERROR, this.zzbl);
    }
}
