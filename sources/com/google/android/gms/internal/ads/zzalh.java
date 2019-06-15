package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class zzalh extends zzex implements zzalg {
    public zzalh() {
        super("com.google.android.gms.ads.internal.mediation.client.IAdapterCreator");
    }

    public static zzalg zzu(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.IAdapterCreator");
        if (queryLocalInterface instanceof zzalg) {
            return (zzalg) queryLocalInterface;
        }
        return new zzali(iBinder);
    }

    /* Access modifiers changed, original: protected|final */
    public final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        IInterface zzcp;
        switch (i) {
            case 1:
                zzcp = zzcp(parcel.readString());
                parcel2.writeNoException();
                zzey.zza(parcel2, zzcp);
                break;
            case 2:
                boolean zzcq = zzcq(parcel.readString());
                parcel2.writeNoException();
                zzey.writeBoolean(parcel2, zzcq);
                break;
            case 3:
                zzcp = zzct(parcel.readString());
                parcel2.writeNoException();
                zzey.zza(parcel2, zzcp);
                break;
            default:
                return false;
        }
        return true;
    }
}
