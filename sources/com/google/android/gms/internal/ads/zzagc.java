package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.IObjectWrapper.Stub;

public abstract class zzagc extends zzex implements zzagb {
    public zzagc() {
        super("com.google.android.gms.ads.internal.instream.client.IInstreamAd");
    }

    /* Access modifiers changed, original: protected|final */
    public final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        switch (i) {
            case 3:
                IInterface videoController = getVideoController();
                parcel2.writeNoException();
                zzey.zza(parcel2, videoController);
                break;
            case 4:
                destroy();
                parcel2.writeNoException();
                break;
            case 5:
                zzagd zzagd;
                IObjectWrapper asInterface = Stub.asInterface(parcel.readStrongBinder());
                IBinder readStrongBinder = parcel.readStrongBinder();
                if (readStrongBinder == null) {
                    zzagd = null;
                } else {
                    IInterface queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.gms.ads.internal.instream.client.IInstreamAdCallback");
                    if (queryLocalInterface instanceof zzagd) {
                        zzagd = (zzagd) queryLocalInterface;
                    } else {
                        zzagd = new zzage(readStrongBinder);
                    }
                }
                zza(asInterface, zzagd);
                parcel2.writeNoException();
                break;
            default:
                return false;
        }
        return true;
    }
}
