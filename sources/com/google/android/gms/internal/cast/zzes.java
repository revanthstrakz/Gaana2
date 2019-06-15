package com.google.android.gms.internal.cast;

import android.os.RemoteException;
import com.google.android.gms.common.api.Status;

final class zzes extends zzey {
    private final /* synthetic */ zzex zzabf;
    private final /* synthetic */ zzer zzabg;

    zzes(zzer zzer, zzex zzex) {
        this.zzabg = zzer;
        this.zzabf = zzex;
    }

    public final void zzw(int i) throws RemoteException {
        zzer.zzbf.d("onRemoteDisplayEnded", new Object[0]);
        if (this.zzabf != null) {
            this.zzabf.zzw(i);
        }
        if (this.zzabg.zzabd != null) {
            this.zzabg.zzabd.onRemoteDisplayEnded(new Status(i));
        }
    }
}
