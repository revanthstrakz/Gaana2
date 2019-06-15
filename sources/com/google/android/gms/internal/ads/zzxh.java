package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.ads.formats.PublisherAdViewOptions;

public abstract class zzxh extends zzex implements zzxg {
    public zzxh() {
        super("com.google.android.gms.ads.internal.client.IAdLoaderBuilder");
    }

    /* Access modifiers changed, original: protected|final */
    public final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        zzxa zzxa = null;
        IBinder readStrongBinder;
        IInterface queryLocalInterface;
        switch (i) {
            case 1:
                IInterface zzkd = zzkd();
                parcel2.writeNoException();
                zzey.zza(parcel2, zzkd);
                break;
            case 2:
                readStrongBinder = parcel.readStrongBinder();
                if (readStrongBinder != null) {
                    queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.gms.ads.internal.client.IAdListener");
                    if (queryLocalInterface instanceof zzxa) {
                        zzxa = (zzxa) queryLocalInterface;
                    } else {
                        zzxa = new zzxc(readStrongBinder);
                    }
                }
                zzb(zzxa);
                parcel2.writeNoException();
                break;
            case 3:
                zza(zzaec.zzn(parcel.readStrongBinder()));
                parcel2.writeNoException();
                break;
            case 4:
                zza(zzaef.zzo(parcel.readStrongBinder()));
                parcel2.writeNoException();
                break;
            case 5:
                zza(parcel.readString(), zzael.zzq(parcel.readStrongBinder()), zzaei.zzp(parcel.readStrongBinder()));
                parcel2.writeNoException();
                break;
            case 6:
                zza((zzacp) zzey.zza(parcel, zzacp.CREATOR));
                parcel2.writeNoException();
                break;
            case 7:
                zzxz zzxz;
                readStrongBinder = parcel.readStrongBinder();
                if (readStrongBinder != null) {
                    queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.gms.ads.internal.client.ICorrelationIdProvider");
                    if (queryLocalInterface instanceof zzxz) {
                        zzxz = (zzxz) queryLocalInterface;
                    } else {
                        zzxz = new zzyb(readStrongBinder);
                    }
                }
                zzb(zzxz);
                parcel2.writeNoException();
                break;
            case 8:
                zza(zzaeo.zzr(parcel.readStrongBinder()), (zzwf) zzey.zza(parcel, zzwf.CREATOR));
                parcel2.writeNoException();
                break;
            case 9:
                zza((PublisherAdViewOptions) zzey.zza(parcel, PublisherAdViewOptions.CREATOR));
                parcel2.writeNoException();
                break;
            case 10:
                zza(zzaer.zzs(parcel.readStrongBinder()));
                parcel2.writeNoException();
                break;
            case 13:
                zza((zzafz) zzey.zza(parcel, zzafz.CREATOR));
                parcel2.writeNoException();
                break;
            case 14:
                zza(zzagg.zzt(parcel.readStrongBinder()));
                parcel2.writeNoException();
                break;
            default:
                return false;
        }
        return true;
    }
}
