package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.IObjectWrapper.Stub;

public abstract class zzanh extends zzex implements zzang {
    public zzanh() {
        super("com.google.android.gms.ads.internal.mediation.client.rtb.IRtbAdapter");
    }

    public static zzang zzw(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.rtb.IRtbAdapter");
        if (queryLocalInterface instanceof zzang) {
            return (zzang) queryLocalInterface;
        }
        return new zzani(iBinder);
    }

    /* Access modifiers changed, original: protected|final */
    public final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        zzanj zzanj = null;
        String readString;
        Bundle bundle;
        IInterface queryLocalInterface;
        zzans zzvi;
        String readString2;
        IObjectWrapper asInterface;
        IBinder readStrongBinder;
        zzanj zzanj2;
        switch (i) {
            case 1:
                IObjectWrapper asInterface2 = Stub.asInterface(parcel.readStrongBinder());
                readString = parcel.readString();
                bundle = (Bundle) zzey.zza(parcel, Bundle.CREATOR);
                Bundle bundle2 = (Bundle) zzey.zza(parcel, Bundle.CREATOR);
                zzwf zzwf = (zzwf) zzey.zza(parcel, zzwf.CREATOR);
                IBinder readStrongBinder2 = parcel.readStrongBinder();
                if (readStrongBinder2 != null) {
                    queryLocalInterface = readStrongBinder2.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.rtb.ISignalsCallback");
                    if (queryLocalInterface instanceof zzanj) {
                        zzanj = (zzanj) queryLocalInterface;
                    } else {
                        zzanj = new zzank(readStrongBinder2);
                    }
                }
                zza(asInterface2, readString, bundle, bundle2, zzwf, zzanj);
                parcel2.writeNoException();
                break;
            case 2:
                zzvi = zzvi();
                parcel2.writeNoException();
                zzey.zzb(parcel2, zzvi);
                break;
            case 3:
                zzvi = zzvj();
                parcel2.writeNoException();
                zzey.zzb(parcel2, zzvi);
                break;
            case 4:
                readString2 = parcel.readString();
                readString = parcel.readString();
                bundle = (Bundle) zzey.zza(parcel, Bundle.CREATOR);
                asInterface = Stub.asInterface(parcel.readStrongBinder());
                readStrongBinder = parcel.readStrongBinder();
                if (readStrongBinder != null) {
                    queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.rtb.IBannerCallback");
                    if (queryLocalInterface instanceof zzamy) {
                        zzamy zzamy = (zzamy) queryLocalInterface;
                    } else {
                        zzanj = new zzamz(readStrongBinder);
                    }
                }
                zzanj2 = zzanj;
                zza(readString2, readString, bundle, asInterface, zzanj2, zzaln.zzv(parcel.readStrongBinder()), (zzwf) zzey.zza(parcel, zzwf.CREATOR));
                parcel2.writeNoException();
                break;
            case 5:
                IInterface videoController = getVideoController();
                parcel2.writeNoException();
                zzey.zza(parcel2, videoController);
                break;
            case 6:
                readString2 = parcel.readString();
                readString = parcel.readString();
                bundle = (Bundle) zzey.zza(parcel, Bundle.CREATOR);
                asInterface = Stub.asInterface(parcel.readStrongBinder());
                readStrongBinder = parcel.readStrongBinder();
                if (readStrongBinder != null) {
                    queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.rtb.IInterstitialCallback");
                    if (queryLocalInterface instanceof zzana) {
                        zzana zzana = (zzana) queryLocalInterface;
                    } else {
                        zzanj = new zzanb(readStrongBinder);
                    }
                }
                zzanj2 = zzanj;
                zza(readString2, readString, bundle, asInterface, (zzana) zzanj2, zzaln.zzv(parcel.readStrongBinder()));
                parcel2.writeNoException();
                break;
            case 7:
                showInterstitial();
                parcel2.writeNoException();
                break;
            case 8:
                readString2 = parcel.readString();
                readString = parcel.readString();
                bundle = (Bundle) zzey.zza(parcel, Bundle.CREATOR);
                asInterface = Stub.asInterface(parcel.readStrongBinder());
                readStrongBinder = parcel.readStrongBinder();
                if (readStrongBinder != null) {
                    queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.rtb.IRewardedCallback");
                    if (queryLocalInterface instanceof zzane) {
                        zzane zzane = (zzane) queryLocalInterface;
                    } else {
                        zzanj = new zzanf(readStrongBinder);
                    }
                }
                zzanj2 = zzanj;
                zza(readString2, readString, bundle, asInterface, (zzane) zzanj2, zzaln.zzv(parcel.readStrongBinder()));
                parcel2.writeNoException();
                break;
            case 9:
                zzvk();
                parcel2.writeNoException();
                break;
            case 10:
                zzn(Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                break;
            case 11:
                zza(parcel.createStringArray(), (Bundle[]) parcel.createTypedArray(Bundle.CREATOR));
                parcel2.writeNoException();
                break;
            case 12:
                readString2 = parcel.readString();
                readString = parcel.readString();
                bundle = (Bundle) zzey.zza(parcel, Bundle.CREATOR);
                asInterface = Stub.asInterface(parcel.readStrongBinder());
                readStrongBinder = parcel.readStrongBinder();
                if (readStrongBinder != null) {
                    queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.rtb.INativeCallback");
                    if (queryLocalInterface instanceof zzanc) {
                        zzanc zzanc = (zzanc) queryLocalInterface;
                    } else {
                        zzanj = new zzand(readStrongBinder);
                    }
                }
                zzanj2 = zzanj;
                zza(readString2, readString, bundle, asInterface, (zzanc) zzanj2, zzaln.zzv(parcel.readStrongBinder()));
                parcel2.writeNoException();
                break;
            default:
                return false;
        }
        return true;
    }
}
