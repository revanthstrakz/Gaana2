package com.google.android.gms.internal.measurement;

import android.app.Activity;
import android.os.RemoteException;
import com.google.android.gms.dynamic.ObjectWrapper;

final class zzfb extends zzb {
    private final /* synthetic */ Activity val$activity;
    private final /* synthetic */ zze zzafa;

    zzfb(zze zze, Activity activity) {
        this.zzafa = zze;
        this.val$activity = activity;
        super(zzea.this);
    }

    /* Access modifiers changed, original: final */
    public final void zzgd() throws RemoteException {
        zzea.this.zzadr.onActivityStopped(ObjectWrapper.wrap(this.val$activity), this.zzaev);
    }
}
