package com.google.android.gms.internal.cast;

import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.IStatusCallback.Stub;
import com.google.android.gms.common.api.internal.TaskUtil;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzda extends Stub {
    private final /* synthetic */ TaskCompletionSource zzbl;

    zzda(zzcz zzcz, TaskCompletionSource taskCompletionSource) {
        this.zzbl = taskCompletionSource;
    }

    public final void onResult(Status status) throws RemoteException {
        TaskUtil.setResultOrApiException(status, this.zzbl);
    }
}
