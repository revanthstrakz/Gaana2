package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper.Stub;

public abstract class zzaux extends zzex implements zzauw {
    public zzaux() {
        super("com.google.android.gms.ads.internal.reward.client.IRewardedVideoAd");
    }

    public static zzauw zzab(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.reward.client.IRewardedVideoAd");
        if (queryLocalInterface instanceof zzauw) {
            return (zzauw) queryLocalInterface;
        }
        return new zzauy(iBinder);
    }

    /* Access modifiers changed, original: protected|final */
    public final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i != 34) {
            zzavb zzavb = null;
            IBinder readStrongBinder;
            IInterface queryLocalInterface;
            switch (i) {
                case 1:
                    zza((zzavh) zzey.zza(parcel, zzavh.CREATOR));
                    parcel2.writeNoException();
                    break;
                case 2:
                    show();
                    parcel2.writeNoException();
                    break;
                case 3:
                    readStrongBinder = parcel.readStrongBinder();
                    if (readStrongBinder != null) {
                        queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.gms.ads.internal.reward.client.IRewardedVideoAdListener");
                        if (queryLocalInterface instanceof zzavb) {
                            zzavb = (zzavb) queryLocalInterface;
                        } else {
                            zzavb = new zzavd(readStrongBinder);
                        }
                    }
                    zza(zzavb);
                    parcel2.writeNoException();
                    break;
                default:
                    switch (i) {
                        case 5:
                            boolean isLoaded = isLoaded();
                            parcel2.writeNoException();
                            zzey.writeBoolean(parcel2, isLoaded);
                            break;
                        case 6:
                            pause();
                            parcel2.writeNoException();
                            break;
                        case 7:
                            resume();
                            parcel2.writeNoException();
                            break;
                        case 8:
                            destroy();
                            parcel2.writeNoException();
                            break;
                        case 9:
                            zze(Stub.asInterface(parcel.readStrongBinder()));
                            parcel2.writeNoException();
                            break;
                        case 10:
                            zzf(Stub.asInterface(parcel.readStrongBinder()));
                            parcel2.writeNoException();
                            break;
                        case 11:
                            zzg(Stub.asInterface(parcel.readStrongBinder()));
                            parcel2.writeNoException();
                            break;
                        case 12:
                            String mediationAdapterClassName = getMediationAdapterClassName();
                            parcel2.writeNoException();
                            parcel2.writeString(mediationAdapterClassName);
                            break;
                        case 13:
                            setUserId(parcel.readString());
                            parcel2.writeNoException();
                            break;
                        case 14:
                            zza(zzxr.zzc(parcel.readStrongBinder()));
                            parcel2.writeNoException();
                            break;
                        case 15:
                            Bundle adMetadata = getAdMetadata();
                            parcel2.writeNoException();
                            zzey.zzb(parcel2, adMetadata);
                            break;
                        case 16:
                            zzauu zzauu;
                            readStrongBinder = parcel.readStrongBinder();
                            if (readStrongBinder != null) {
                                queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.gms.ads.internal.reward.client.IRewardedAdSkuListener");
                                if (queryLocalInterface instanceof zzauu) {
                                    zzauu = (zzauu) queryLocalInterface;
                                } else {
                                    zzauu = new zzauv(readStrongBinder);
                                }
                            }
                            zza(zzauu);
                            parcel2.writeNoException();
                            break;
                        case 17:
                            setAppPackageName(parcel.readString());
                            parcel2.writeNoException();
                            break;
                        case 18:
                            zzd(Stub.asInterface(parcel.readStrongBinder()));
                            parcel2.writeNoException();
                            break;
                        case 19:
                            setCustomData(parcel.readString());
                            parcel2.writeNoException();
                            break;
                        default:
                            return false;
                    }
            }
        }
        setImmersiveMode(zzey.zza(parcel));
        parcel2.writeNoException();
        return true;
    }
}
