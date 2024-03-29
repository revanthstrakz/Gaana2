package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.RemoteException;

public abstract class zzyt extends zzex implements zzys {
    public zzyt() {
        super("com.google.android.gms.ads.internal.client.IVideoLifecycleCallbacks");
    }

    /* Access modifiers changed, original: protected|final */
    public final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        switch (i) {
            case 1:
                onVideoStart();
                break;
            case 2:
                onVideoPlay();
                break;
            case 3:
                onVideoPause();
                break;
            case 4:
                onVideoEnd();
                break;
            case 5:
                onVideoMute(zzey.zza(parcel));
                break;
            default:
                return false;
        }
        parcel2.writeNoException();
        return true;
    }
}
