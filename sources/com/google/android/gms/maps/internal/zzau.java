package com.google.android.gms.maps.internal;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.maps.zzb;
import com.google.android.gms.internal.maps.zzu;

public abstract class zzau extends zzb implements zzat {
    public zzau() {
        super("com.google.android.gms.maps.internal.IOnMarkerDragListener");
    }

    /* Access modifiers changed, original: protected|final */
    public final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        switch (i) {
            case 1:
                zzb(zzu.zzg(parcel.readStrongBinder()));
                break;
            case 2:
                zzd(zzu.zzg(parcel.readStrongBinder()));
                break;
            case 3:
                zzc(zzu.zzg(parcel.readStrongBinder()));
                break;
            default:
                return false;
        }
        parcel2.writeNoException();
        return true;
    }
}
