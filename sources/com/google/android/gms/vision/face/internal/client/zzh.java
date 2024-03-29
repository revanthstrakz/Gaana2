package com.google.android.gms.vision.face.internal.client;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper.Stub;
import com.google.android.gms.internal.vision.zzb;
import com.google.android.gms.internal.vision.zzc;
import com.google.android.gms.internal.vision.zzn;

public abstract class zzh extends zzb implements zzg {
    public zzh() {
        super("com.google.android.gms.vision.face.internal.client.INativeFaceDetector");
    }

    /* Access modifiers changed, original: protected|final */
    public final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        switch (i) {
            case 1:
                FaceParcel[] zzc = zzc(Stub.asInterface(parcel.readStrongBinder()), (zzn) zzc.zza(parcel, zzn.CREATOR));
                parcel2.writeNoException();
                parcel2.writeTypedArray(zzc, 1);
                break;
            case 2:
                boolean zzd = zzd(parcel.readInt());
                parcel2.writeNoException();
                zzc.writeBoolean(parcel2, zzd);
                break;
            case 3:
                zzn();
                parcel2.writeNoException();
                break;
            default:
                return false;
        }
        return true;
    }
}
