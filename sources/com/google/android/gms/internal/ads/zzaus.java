package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.RemoteException;

public abstract class zzaus extends zzex implements zzaur {
    public zzaus() {
        super("com.google.android.gms.ads.internal.reward.client.IRewardItem");
    }

    /* Access modifiers changed, original: protected|final */
    public final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        switch (i) {
            case 1:
                String type = getType();
                parcel2.writeNoException();
                parcel2.writeString(type);
                break;
            case 2:
                i = getAmount();
                parcel2.writeNoException();
                parcel2.writeInt(i);
                break;
            default:
                return false;
        }
        return true;
    }
}
