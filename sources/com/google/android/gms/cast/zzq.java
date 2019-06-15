package com.google.android.gms.cast;

import android.app.PendingIntent;
import android.os.Bundle;
import android.os.RemoteException;
import android.view.Display;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.internal.cast.zzeq;
import com.google.android.gms.internal.cast.zzev;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzq extends TaskApiCall<zzeq, Display> {
    private final /* synthetic */ String zzag;
    private final /* synthetic */ int zzbh;
    private final /* synthetic */ PendingIntent zzbi;
    private final /* synthetic */ CastDevice zzbj;
    final /* synthetic */ CastRemoteDisplayClient zzbk;

    zzq(CastRemoteDisplayClient castRemoteDisplayClient, int i, PendingIntent pendingIntent, CastDevice castDevice, String str) {
        this.zzbk = castRemoteDisplayClient;
        this.zzbh = i;
        this.zzbi = pendingIntent;
        this.zzbj = castDevice;
        this.zzag = str;
    }

    /* Access modifiers changed, original: protected|final|synthetic */
    public final /* synthetic */ void doExecute(AnyClient anyClient, TaskCompletionSource taskCompletionSource) throws RemoteException {
        zzeq zzeq = (zzeq) anyClient;
        Bundle bundle = new Bundle();
        bundle.putInt("configuration", this.zzbh);
        ((zzev) zzeq.getService()).zza(new zzr(this, taskCompletionSource, zzeq), this.zzbi, this.zzbj.getDeviceId(), this.zzag, bundle);
    }
}
