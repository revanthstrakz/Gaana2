package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class zzdr extends zzr implements zzdq {
    public zzdr() {
        super("com.google.android.gms.measurement.api.internal.IBundleReceiver");
    }

    /* Access modifiers changed, original: protected|final */
    public final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i != 1) {
            return false;
        }
        zzb((Bundle) zzs.zza(parcel, Bundle.CREATOR));
        parcel2.writeNoException();
        return true;
    }
}
