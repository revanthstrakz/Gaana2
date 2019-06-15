package com.google.android.gms.internal.cast;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.util.VisibleForTesting;

@VisibleForTesting
public abstract class zzcj<R extends Result> extends zzcv<R> {
    protected zzec zzwq;

    public zzcj(zzcb zzcb) {
        super(zzcb.zzpb);
    }

    public abstract void execute();

    /* Access modifiers changed, original: protected|synthetic */
    public /* synthetic */ void doExecute(AnyClient anyClient) throws RemoteException {
        execute();
    }
}
