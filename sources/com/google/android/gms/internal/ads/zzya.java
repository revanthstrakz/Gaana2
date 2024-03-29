package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.RemoteException;

public abstract class zzya extends zzex implements zzxz {
    public zzya() {
        super("com.google.android.gms.ads.internal.client.ICorrelationIdProvider");
    }

    /* Access modifiers changed, original: protected|final */
    public final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i != 1) {
            return false;
        }
        long value = getValue();
        parcel2.writeNoException();
        parcel2.writeLong(value);
        return true;
    }
}
