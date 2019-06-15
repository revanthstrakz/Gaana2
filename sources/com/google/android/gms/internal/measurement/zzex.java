package com.google.android.gms.internal.measurement;

import android.app.Activity;
import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.dynamic.ObjectWrapper;

final class zzex extends zzb {
    private final /* synthetic */ Activity val$activity;
    private final /* synthetic */ Bundle zzaez;
    private final /* synthetic */ zze zzafa;

    zzex(zze zze, Activity activity, Bundle bundle) {
        this.zzafa = zze;
        this.val$activity = activity;
        this.zzaez = bundle;
        super(zzea.this);
    }

    /* Access modifiers changed, original: final */
    public final void zzgd() throws RemoteException {
        zzea.this.zzadr.onActivityCreated(ObjectWrapper.wrap(this.val$activity), this.zzaez, this.zzaev);
    }
}
