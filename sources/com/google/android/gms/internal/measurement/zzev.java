package com.google.android.gms.internal.measurement;

import android.os.RemoteException;
import com.google.android.gms.dynamic.ObjectWrapper;

final class zzev extends zzb {
    private final /* synthetic */ String val$name;
    private final /* synthetic */ String zzads;
    private final /* synthetic */ zzea zzadv;
    private final /* synthetic */ boolean zzaeq = true;
    private final /* synthetic */ Object zzaes;

    zzev(zzea zzea, String str, String str2, Object obj, boolean z) {
        this.zzadv = zzea;
        this.zzads = str;
        this.val$name = str2;
        this.zzaes = obj;
        super(zzea);
    }

    /* Access modifiers changed, original: final */
    public final void zzgd() throws RemoteException {
        this.zzadv.zzadr.setUserProperty(this.zzads, this.val$name, ObjectWrapper.wrap(this.zzaes), this.zzaeq, this.timestamp);
    }
}
