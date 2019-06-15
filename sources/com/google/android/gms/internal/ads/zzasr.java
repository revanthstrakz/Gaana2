package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class zzasr extends zzex implements zzasq {
    public zzasr() {
        super("com.google.android.gms.ads.internal.request.IAdRequestService");
    }

    /* Access modifiers changed, original: protected|final */
    public final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        zzast zzast = null;
        IBinder readStrongBinder;
        IInterface queryLocalInterface;
        zzatb zzatb;
        zzasw zzasw;
        switch (i) {
            case 1:
                zzasm zzb = zzb((zzasi) zzey.zza(parcel, zzasi.CREATOR));
                parcel2.writeNoException();
                zzey.zzb(parcel2, zzb);
                break;
            case 2:
                zzasi zzasi = (zzasi) zzey.zza(parcel, zzasi.CREATOR);
                readStrongBinder = parcel.readStrongBinder();
                if (readStrongBinder != null) {
                    queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.gms.ads.internal.request.IAdResponseListener");
                    if (queryLocalInterface instanceof zzast) {
                        zzast = (zzast) queryLocalInterface;
                    } else {
                        zzast = new zzasv(readStrongBinder);
                    }
                }
                zza(zzasi, zzast);
                parcel2.writeNoException();
                break;
            case 4:
                zzatb = (zzatb) zzey.zza(parcel, zzatb.CREATOR);
                readStrongBinder = parcel.readStrongBinder();
                if (readStrongBinder != null) {
                    queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.gms.ads.internal.request.INonagonStreamingResponseListener");
                    if (queryLocalInterface instanceof zzasw) {
                        zzasw = (zzasw) queryLocalInterface;
                    } else {
                        zzasw = new zzasx(readStrongBinder);
                    }
                }
                zza(zzatb, zzasw);
                parcel2.writeNoException();
                break;
            case 5:
                zzatb = (zzatb) zzey.zza(parcel, zzatb.CREATOR);
                readStrongBinder = parcel.readStrongBinder();
                if (readStrongBinder != null) {
                    queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.gms.ads.internal.request.INonagonStreamingResponseListener");
                    if (queryLocalInterface instanceof zzasw) {
                        zzasw = (zzasw) queryLocalInterface;
                    } else {
                        zzasw = new zzasx(readStrongBinder);
                    }
                }
                zzb(zzatb, zzasw);
                parcel2.writeNoException();
                break;
            default:
                return false;
        }
        return true;
    }
}
