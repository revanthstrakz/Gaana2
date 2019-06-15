package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

public abstract class zzaex extends zzex implements zzaew {
    public zzaex() {
        super("com.google.android.gms.ads.internal.formats.client.IUnifiedNativeAd");
    }

    /* Access modifiers changed, original: protected|final */
    public final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        String headline;
        List images;
        IInterface zzsb;
        boolean recordImpression;
        switch (i) {
            case 2:
                headline = getHeadline();
                parcel2.writeNoException();
                parcel2.writeString(headline);
                break;
            case 3:
                images = getImages();
                parcel2.writeNoException();
                parcel2.writeList(images);
                break;
            case 4:
                headline = getBody();
                parcel2.writeNoException();
                parcel2.writeString(headline);
                break;
            case 5:
                zzsb = zzsb();
                parcel2.writeNoException();
                zzey.zza(parcel2, zzsb);
                break;
            case 6:
                headline = getCallToAction();
                parcel2.writeNoException();
                parcel2.writeString(headline);
                break;
            case 7:
                headline = getAdvertiser();
                parcel2.writeNoException();
                parcel2.writeString(headline);
                break;
            case 8:
                double starRating = getStarRating();
                parcel2.writeNoException();
                parcel2.writeDouble(starRating);
                break;
            case 9:
                headline = getStore();
                parcel2.writeNoException();
                parcel2.writeString(headline);
                break;
            case 10:
                headline = getPrice();
                parcel2.writeNoException();
                parcel2.writeString(headline);
                break;
            case 11:
                zzsb = getVideoController();
                parcel2.writeNoException();
                zzey.zza(parcel2, zzsb);
                break;
            case 12:
                headline = getMediationAdapterClassName();
                parcel2.writeNoException();
                parcel2.writeString(headline);
                break;
            case 13:
                destroy();
                parcel2.writeNoException();
                break;
            case 14:
                zzsb = zzse();
                parcel2.writeNoException();
                zzey.zza(parcel2, zzsb);
                break;
            case 15:
                performClick((Bundle) zzey.zza(parcel, Bundle.CREATOR));
                parcel2.writeNoException();
                break;
            case 16:
                recordImpression = recordImpression((Bundle) zzey.zza(parcel, Bundle.CREATOR));
                parcel2.writeNoException();
                zzey.writeBoolean(parcel2, recordImpression);
                break;
            case 17:
                reportTouchEvent((Bundle) zzey.zza(parcel, Bundle.CREATOR));
                parcel2.writeNoException();
                break;
            case 18:
                zzsb = zzsc();
                parcel2.writeNoException();
                zzey.zza(parcel2, zzsb);
                break;
            case 19:
                zzsb = zzsd();
                parcel2.writeNoException();
                zzey.zza(parcel2, zzsb);
                break;
            case 20:
                Bundle extras = getExtras();
                parcel2.writeNoException();
                zzey.zzb(parcel2, extras);
                break;
            case 21:
                zzaet zzaet;
                IBinder readStrongBinder = parcel.readStrongBinder();
                if (readStrongBinder == null) {
                    zzaet = null;
                } else {
                    IInterface queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.gms.ads.internal.formats.client.IUnconfirmedClickListener");
                    if (queryLocalInterface instanceof zzaet) {
                        zzaet = (zzaet) queryLocalInterface;
                    } else {
                        zzaet = new zzaev(readStrongBinder);
                    }
                }
                zza(zzaet);
                parcel2.writeNoException();
                break;
            case 22:
                cancelUnconfirmedClick();
                parcel2.writeNoException();
                break;
            case 23:
                images = getMuteThisAdReasons();
                parcel2.writeNoException();
                parcel2.writeList(images);
                break;
            case 24:
                recordImpression = isCustomMuteThisAdEnabled();
                parcel2.writeNoException();
                zzey.writeBoolean(parcel2, recordImpression);
                break;
            case 25:
                zza(zzym.zzf(parcel.readStrongBinder()));
                parcel2.writeNoException();
                break;
            case 26:
                zza(zzyi.zze(parcel.readStrongBinder()));
                parcel2.writeNoException();
                break;
            case 27:
                zzsi();
                parcel2.writeNoException();
                break;
            case 28:
                recordCustomClickGesture();
                parcel2.writeNoException();
                break;
            default:
                return false;
        }
        return true;
    }
}
