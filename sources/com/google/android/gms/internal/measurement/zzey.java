package com.google.android.gms.internal.measurement;

import android.app.Activity;
import android.os.RemoteException;
import com.google.android.gms.dynamic.ObjectWrapper;

final class zzey extends zzb {
    private final /* synthetic */ Activity val$activity;
    private final /* synthetic */ zze zzafa;

    zzey(zze zze, Activity activity) {
        this.zzafa = zze;
        this.val$activity = activity;
        super(zzea.this);
    }

    /* Access modifiers changed, original: final */
    public final void zzgd() throws RemoteException {
        zzea.this.zzadr.onActivityStarted(ObjectWrapper.wrap(this.val$activity), this.zzaev);
    }
}
