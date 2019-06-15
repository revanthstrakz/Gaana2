package com.google.android.gms.internal.cast;

import android.os.Parcel;
import android.os.RemoteException;
import android.view.Surface;

public abstract class zzeu extends zzb implements zzet {
    public zzeu() {
        super("com.google.android.gms.cast.remote_display.ICastRemoteDisplayCallbacks");
    }

    /* Access modifiers changed, original: protected|final */
    public final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        switch (i) {
            case 1:
                zza(parcel.readInt(), parcel.readInt(), (Surface) zzc.zza(parcel, Surface.CREATOR));
                break;
            case 2:
                onError(parcel.readInt());
                break;
            case 3:
                onDisconnected();
                break;
            case 4:
                zzd();
                break;
            default:
                return false;
        }
        parcel2.writeNoException();
        return true;
    }
}
