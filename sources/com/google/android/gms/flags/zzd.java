package com.google.android.gms.flags;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper.Stub;
import com.google.android.gms.internal.flags.zzb;
import com.google.android.gms.internal.flags.zzc;

public abstract class zzd extends zzb implements zzc {
    public zzd() {
        super("com.google.android.gms.flags.IFlagProvider");
    }

    public static zzc asInterface(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.flags.IFlagProvider");
        if (queryLocalInterface instanceof zzc) {
            return (zzc) queryLocalInterface;
        }
        return new zze(iBinder);
    }

    /* Access modifiers changed, original: protected|final */
    public final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        switch (i) {
            case 1:
                init(Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                break;
            case 2:
                boolean booleanFlagValue = getBooleanFlagValue(parcel.readString(), zzc.zza(parcel), parcel.readInt());
                parcel2.writeNoException();
                zzc.writeBoolean(parcel2, booleanFlagValue);
                break;
            case 3:
                i = getIntFlagValue(parcel.readString(), parcel.readInt(), parcel.readInt());
                parcel2.writeNoException();
                parcel2.writeInt(i);
                break;
            case 4:
                long longFlagValue = getLongFlagValue(parcel.readString(), parcel.readLong(), parcel.readInt());
                parcel2.writeNoException();
                parcel2.writeLong(longFlagValue);
                break;
            case 5:
                String stringFlagValue = getStringFlagValue(parcel.readString(), parcel.readString(), parcel.readInt());
                parcel2.writeNoException();
                parcel2.writeString(stringFlagValue);
                break;
            default:
                return false;
        }
        return true;
    }
}
