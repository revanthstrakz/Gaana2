package com.google.android.gms.internal.cast;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.cast.ApplicationMetadata;

public abstract class zzds extends zzb implements zzdr {
    public zzds() {
        super("com.google.android.gms.cast.internal.ICastDeviceControllerListener");
    }

    /* Access modifiers changed, original: protected|final */
    public final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        switch (i) {
            case 1:
                zzs(parcel.readInt());
                break;
            case 2:
                zza((ApplicationMetadata) zzc.zza(parcel, ApplicationMetadata.CREATOR), parcel.readString(), parcel.readString(), zzc.zza(parcel));
                break;
            case 3:
                zzi(parcel.readInt());
                break;
            case 4:
                zza(parcel.readString(), parcel.readDouble(), zzc.zza(parcel));
                break;
            case 5:
                zzb(parcel.readString(), parcel.readString());
                break;
            case 6:
                zza(parcel.readString(), parcel.createByteArray());
                break;
            case 7:
                zzu(parcel.readInt());
                break;
            case 8:
                zzt(parcel.readInt());
                break;
            case 9:
                onApplicationDisconnected(parcel.readInt());
                break;
            case 10:
                zza(parcel.readString(), parcel.readLong(), parcel.readInt());
                break;
            case 11:
                zza(parcel.readString(), parcel.readLong());
                break;
            case 12:
                zzb((zzct) zzc.zza(parcel, zzct.CREATOR));
                break;
            case 13:
                zzb((zzdl) zzc.zza(parcel, zzdl.CREATOR));
                break;
            default:
                return false;
        }
        return true;
    }
}
