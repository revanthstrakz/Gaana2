package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class zzyq extends zzex implements zzyp {
    public zzyq() {
        super("com.google.android.gms.ads.internal.client.IVideoController");
    }

    public static zzyp zzg(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.client.IVideoController");
        if (queryLocalInterface instanceof zzyp) {
            return (zzyp) queryLocalInterface;
        }
        return new zzyr(iBinder);
    }

    /* Access modifiers changed, original: protected|final */
    public final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        boolean isMuted;
        float zzqf;
        switch (i) {
            case 1:
                play();
                parcel2.writeNoException();
                break;
            case 2:
                pause();
                parcel2.writeNoException();
                break;
            case 3:
                mute(zzey.zza(parcel));
                parcel2.writeNoException();
                break;
            case 4:
                isMuted = isMuted();
                parcel2.writeNoException();
                zzey.writeBoolean(parcel2, isMuted);
                break;
            case 5:
                i = getPlaybackState();
                parcel2.writeNoException();
                parcel2.writeInt(i);
                break;
            case 6:
                zzqf = zzqf();
                parcel2.writeNoException();
                parcel2.writeFloat(zzqf);
                break;
            case 7:
                zzqf = zzqg();
                parcel2.writeNoException();
                parcel2.writeFloat(zzqf);
                break;
            case 8:
                zzys zzys;
                IBinder readStrongBinder = parcel.readStrongBinder();
                if (readStrongBinder == null) {
                    zzys = null;
                } else {
                    IInterface queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.gms.ads.internal.client.IVideoLifecycleCallbacks");
                    if (queryLocalInterface instanceof zzys) {
                        zzys = (zzys) queryLocalInterface;
                    } else {
                        zzys = new zzyu(readStrongBinder);
                    }
                }
                zza(zzys);
                parcel2.writeNoException();
                break;
            case 9:
                zzqf = getAspectRatio();
                parcel2.writeNoException();
                parcel2.writeFloat(zzqf);
                break;
            case 10:
                isMuted = isCustomControlsEnabled();
                parcel2.writeNoException();
                zzey.writeBoolean(parcel2, isMuted);
                break;
            case 11:
                IInterface zzqh = zzqh();
                parcel2.writeNoException();
                zzey.zza(parcel2, zzqh);
                break;
            case 12:
                isMuted = isClickToExpandEnabled();
                parcel2.writeNoException();
                zzey.writeBoolean(parcel2, isMuted);
                break;
            default:
                return false;
        }
        return true;
    }
}
