package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper.Stub;

public abstract class zzxx extends zzex implements zzxw {
    public zzxx() {
        super("com.google.android.gms.ads.internal.client.IClientApi");
    }

    public static zzxw asInterface(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.client.IClientApi");
        if (queryLocalInterface instanceof zzxw) {
            return (zzxw) queryLocalInterface;
        }
        return new zzxy(iBinder);
    }

    /* Access modifiers changed, original: protected|final */
    public final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        IInterface createBannerAdManager;
        switch (i) {
            case 1:
                createBannerAdManager = createBannerAdManager(Stub.asInterface(parcel.readStrongBinder()), (zzwf) zzey.zza(parcel, zzwf.CREATOR), parcel.readString(), zzalh.zzu(parcel.readStrongBinder()), parcel.readInt());
                parcel2.writeNoException();
                zzey.zza(parcel2, createBannerAdManager);
                break;
            case 2:
                createBannerAdManager = createInterstitialAdManager(Stub.asInterface(parcel.readStrongBinder()), (zzwf) zzey.zza(parcel, zzwf.CREATOR), parcel.readString(), zzalh.zzu(parcel.readStrongBinder()), parcel.readInt());
                parcel2.writeNoException();
                zzey.zza(parcel2, createBannerAdManager);
                break;
            case 3:
                createBannerAdManager = createAdLoaderBuilder(Stub.asInterface(parcel.readStrongBinder()), parcel.readString(), zzalh.zzu(parcel.readStrongBinder()), parcel.readInt());
                parcel2.writeNoException();
                zzey.zza(parcel2, createBannerAdManager);
                break;
            case 4:
                createBannerAdManager = getMobileAdsSettingsManager(Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                zzey.zza(parcel2, createBannerAdManager);
                break;
            case 5:
                createBannerAdManager = createNativeAdViewDelegate(Stub.asInterface(parcel.readStrongBinder()), Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                zzey.zza(parcel2, createBannerAdManager);
                break;
            case 6:
                createBannerAdManager = createRewardedVideoAd(Stub.asInterface(parcel.readStrongBinder()), zzalh.zzu(parcel.readStrongBinder()), parcel.readInt());
                parcel2.writeNoException();
                zzey.zza(parcel2, createBannerAdManager);
                break;
            case 7:
                createBannerAdManager = createInAppPurchaseManager(Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                zzey.zza(parcel2, createBannerAdManager);
                break;
            case 8:
                createBannerAdManager = createAdOverlay(Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                zzey.zza(parcel2, createBannerAdManager);
                break;
            case 9:
                createBannerAdManager = getMobileAdsSettingsManagerWithClientJarVersion(Stub.asInterface(parcel.readStrongBinder()), parcel.readInt());
                parcel2.writeNoException();
                zzey.zza(parcel2, createBannerAdManager);
                break;
            case 10:
                createBannerAdManager = createSearchAdManager(Stub.asInterface(parcel.readStrongBinder()), (zzwf) zzey.zza(parcel, zzwf.CREATOR), parcel.readString(), parcel.readInt());
                parcel2.writeNoException();
                zzey.zza(parcel2, createBannerAdManager);
                break;
            case 11:
                createBannerAdManager = createNativeAdViewHolderDelegate(Stub.asInterface(parcel.readStrongBinder()), Stub.asInterface(parcel.readStrongBinder()), Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                zzey.zza(parcel2, createBannerAdManager);
                break;
            case 12:
                createBannerAdManager = createRewardedVideoAdSku(Stub.asInterface(parcel.readStrongBinder()), parcel.readInt());
                parcel2.writeNoException();
                zzey.zza(parcel2, createBannerAdManager);
                break;
            default:
                return false;
        }
        return true;
    }
}
