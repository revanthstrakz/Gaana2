package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.IObjectWrapper.Stub;
import java.util.List;

public abstract class zzalk extends zzex implements zzalj {
    public zzalk() {
        super("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
    }

    /* Access modifiers changed, original: protected|final */
    public final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        zzalm zzalm = null;
        IObjectWrapper asInterface;
        zzwf zzwf;
        zzwb zzwb;
        String readString;
        IBinder readStrongBinder;
        IInterface queryLocalInterface;
        IInterface zzut;
        IInterface queryLocalInterface2;
        zzwb zzwb2;
        String readString2;
        boolean isInitialized;
        Bundle zzuw;
        switch (i) {
            case 1:
                asInterface = Stub.asInterface(parcel.readStrongBinder());
                zzwf = (zzwf) zzey.zza(parcel, zzwf.CREATOR);
                zzwb = (zzwb) zzey.zza(parcel, zzwb.CREATOR);
                readString = parcel.readString();
                readStrongBinder = parcel.readStrongBinder();
                if (readStrongBinder != null) {
                    queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.IMediationAdapterListener");
                    if (queryLocalInterface instanceof zzalm) {
                        zzalm = (zzalm) queryLocalInterface;
                    } else {
                        zzalm = new zzalo(readStrongBinder);
                    }
                }
                zza(asInterface, zzwf, zzwb, readString, zzalm);
                parcel2.writeNoException();
                break;
            case 2:
                zzut = zzut();
                parcel2.writeNoException();
                zzey.zza(parcel2, zzut);
                break;
            case 3:
                IObjectWrapper asInterface2 = Stub.asInterface(parcel.readStrongBinder());
                zzwb zzwb3 = (zzwb) zzey.zza(parcel, zzwb.CREATOR);
                String readString3 = parcel.readString();
                IBinder readStrongBinder2 = parcel.readStrongBinder();
                if (readStrongBinder2 != null) {
                    queryLocalInterface2 = readStrongBinder2.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.IMediationAdapterListener");
                    if (queryLocalInterface2 instanceof zzalm) {
                        zzalm = (zzalm) queryLocalInterface2;
                    } else {
                        zzalm = new zzalo(readStrongBinder2);
                    }
                }
                zza(asInterface2, zzwb3, readString3, zzalm);
                parcel2.writeNoException();
                break;
            case 4:
                showInterstitial();
                parcel2.writeNoException();
                break;
            case 5:
                destroy();
                parcel2.writeNoException();
                break;
            case 6:
                asInterface = Stub.asInterface(parcel.readStrongBinder());
                zzwf = (zzwf) zzey.zza(parcel, zzwf.CREATOR);
                zzwb = (zzwb) zzey.zza(parcel, zzwb.CREATOR);
                readString = parcel.readString();
                String readString4 = parcel.readString();
                readStrongBinder = parcel.readStrongBinder();
                if (readStrongBinder != null) {
                    queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.IMediationAdapterListener");
                    if (queryLocalInterface instanceof zzalm) {
                        zzalm = (zzalm) queryLocalInterface;
                    } else {
                        zzalm = new zzalo(readStrongBinder);
                    }
                }
                zza(asInterface, zzwf, zzwb, readString, readString4, zzalm);
                parcel2.writeNoException();
                break;
            case 7:
                asInterface = Stub.asInterface(parcel.readStrongBinder());
                zzwb2 = (zzwb) zzey.zza(parcel, zzwb.CREATOR);
                readString2 = parcel.readString();
                readString = parcel.readString();
                readStrongBinder = parcel.readStrongBinder();
                if (readStrongBinder != null) {
                    queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.IMediationAdapterListener");
                    if (queryLocalInterface instanceof zzalm) {
                        zzalm = (zzalm) queryLocalInterface;
                    } else {
                        zzalm = new zzalo(readStrongBinder);
                    }
                }
                zza(asInterface, zzwb2, readString2, readString, zzalm);
                parcel2.writeNoException();
                break;
            case 8:
                pause();
                parcel2.writeNoException();
                break;
            case 9:
                resume();
                parcel2.writeNoException();
                break;
            case 10:
                zza(Stub.asInterface(parcel.readStrongBinder()), (zzwb) zzey.zza(parcel, zzwb.CREATOR), parcel.readString(), zzawa.zzad(parcel.readStrongBinder()), parcel.readString());
                parcel2.writeNoException();
                break;
            case 11:
                zzc((zzwb) zzey.zza(parcel, zzwb.CREATOR), parcel.readString());
                parcel2.writeNoException();
                break;
            case 12:
                showVideo();
                parcel2.writeNoException();
                break;
            case 13:
                isInitialized = isInitialized();
                parcel2.writeNoException();
                zzey.writeBoolean(parcel2, isInitialized);
                break;
            case 14:
                asInterface = Stub.asInterface(parcel.readStrongBinder());
                zzwb2 = (zzwb) zzey.zza(parcel, zzwb.CREATOR);
                readString2 = parcel.readString();
                readString = parcel.readString();
                readStrongBinder = parcel.readStrongBinder();
                if (readStrongBinder != null) {
                    queryLocalInterface2 = readStrongBinder.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.IMediationAdapterListener");
                    if (queryLocalInterface2 instanceof zzalm) {
                        zzalm = (zzalm) queryLocalInterface2;
                    } else {
                        zzalm = new zzalo(readStrongBinder);
                    }
                }
                zza(asInterface, zzwb2, readString2, readString, zzalm, (zzacp) zzey.zza(parcel, zzacp.CREATOR), parcel.createStringArrayList());
                parcel2.writeNoException();
                break;
            case 15:
                zzut = zzuu();
                parcel2.writeNoException();
                zzey.zza(parcel2, zzut);
                break;
            case 16:
                zzut = zzuv();
                parcel2.writeNoException();
                zzey.zza(parcel2, zzut);
                break;
            case 17:
                zzuw = zzuw();
                parcel2.writeNoException();
                zzey.zzb(parcel2, zzuw);
                break;
            case 18:
                zzuw = getInterstitialAdapterInfo();
                parcel2.writeNoException();
                zzey.zzb(parcel2, zzuw);
                break;
            case 19:
                zzuw = zzux();
                parcel2.writeNoException();
                zzey.zzb(parcel2, zzuw);
                break;
            case 20:
                zza((zzwb) zzey.zza(parcel, zzwb.CREATOR), parcel.readString(), parcel.readString());
                parcel2.writeNoException();
                break;
            case 21:
                zzj(Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                break;
            case 22:
                isInitialized = zzuy();
                parcel2.writeNoException();
                zzey.writeBoolean(parcel2, isInitialized);
                break;
            case 23:
                zza(Stub.asInterface(parcel.readStrongBinder()), zzawa.zzad(parcel.readStrongBinder()), (List) parcel.createStringArrayList());
                parcel2.writeNoException();
                break;
            case 24:
                zzut = zzuz();
                parcel2.writeNoException();
                zzey.zza(parcel2, zzut);
                break;
            case 25:
                setImmersiveMode(zzey.zza(parcel));
                parcel2.writeNoException();
                break;
            case 26:
                zzut = getVideoController();
                parcel2.writeNoException();
                zzey.zza(parcel2, zzut);
                break;
            case 27:
                zzut = zzva();
                parcel2.writeNoException();
                zzey.zza(parcel2, zzut);
                break;
            default:
                return false;
        }
        return true;
    }
}
