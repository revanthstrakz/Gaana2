package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class zzxm extends zzex implements zzxl {
    public zzxm() {
        super("com.google.android.gms.ads.internal.client.IAdManager");
    }

    public static zzxl zzb(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.client.IAdManager");
        if (queryLocalInterface instanceof zzxl) {
            return (zzxl) queryLocalInterface;
        }
        return new zzxn(iBinder);
    }

    /* Access modifiers changed, original: protected|final */
    public final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        zzxa zzxa = null;
        IInterface zzie;
        boolean isReady;
        IBinder readStrongBinder;
        IInterface queryLocalInterface;
        String mediationAdapterClassName;
        switch (i) {
            case 1:
                zzie = zzie();
                parcel2.writeNoException();
                zzey.zza(parcel2, zzie);
                break;
            case 2:
                destroy();
                parcel2.writeNoException();
                break;
            case 3:
                isReady = isReady();
                parcel2.writeNoException();
                zzey.writeBoolean(parcel2, isReady);
                break;
            case 4:
                isReady = zzb((zzwb) zzey.zza(parcel, zzwb.CREATOR));
                parcel2.writeNoException();
                zzey.writeBoolean(parcel2, isReady);
                break;
            case 5:
                pause();
                parcel2.writeNoException();
                break;
            case 6:
                resume();
                parcel2.writeNoException();
                break;
            case 7:
                readStrongBinder = parcel.readStrongBinder();
                if (readStrongBinder != null) {
                    queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.gms.ads.internal.client.IAdListener");
                    if (queryLocalInterface instanceof zzxa) {
                        zzxa = (zzxa) queryLocalInterface;
                    } else {
                        zzxa = new zzxc(readStrongBinder);
                    }
                }
                zza(zzxa);
                parcel2.writeNoException();
                break;
            case 8:
                zzxt zzxt;
                readStrongBinder = parcel.readStrongBinder();
                if (readStrongBinder != null) {
                    queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.gms.ads.internal.client.IAppEventListener");
                    if (queryLocalInterface instanceof zzxt) {
                        zzxt = (zzxt) queryLocalInterface;
                    } else {
                        zzxt = new zzxv(readStrongBinder);
                    }
                }
                zza(zzxt);
                parcel2.writeNoException();
                break;
            case 9:
                showInterstitial();
                parcel2.writeNoException();
                break;
            case 10:
                stopLoading();
                parcel2.writeNoException();
                break;
            case 11:
                zzih();
                parcel2.writeNoException();
                break;
            case 12:
                zzwf zzif = zzif();
                parcel2.writeNoException();
                zzey.zzb(parcel2, zzif);
                break;
            case 13:
                zza((zzwf) zzey.zza(parcel, zzwf.CREATOR));
                parcel2.writeNoException();
                break;
            case 14:
                zza(zzaox.zzy(parcel.readStrongBinder()));
                parcel2.writeNoException();
                break;
            case 15:
                zza(zzapd.zzaa(parcel.readStrongBinder()), parcel.readString());
                parcel2.writeNoException();
                break;
            case 18:
                mediationAdapterClassName = getMediationAdapterClassName();
                parcel2.writeNoException();
                parcel2.writeString(mediationAdapterClassName);
                break;
            case 19:
                zza(zzabh.zzh(parcel.readStrongBinder()));
                parcel2.writeNoException();
                break;
            case 20:
                zzwx zzwx;
                readStrongBinder = parcel.readStrongBinder();
                if (readStrongBinder != null) {
                    queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.gms.ads.internal.client.IAdClickListener");
                    if (queryLocalInterface instanceof zzwx) {
                        zzwx = (zzwx) queryLocalInterface;
                    } else {
                        zzwx = new zzwz(readStrongBinder);
                    }
                }
                zza(zzwx);
                parcel2.writeNoException();
                break;
            case 21:
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
                zza(zzxz);
                parcel2.writeNoException();
                break;
            case 22:
                setManualImpressionsEnabled(zzey.zza(parcel));
                parcel2.writeNoException();
                break;
            case 23:
                isReady = isLoading();
                parcel2.writeNoException();
                zzey.writeBoolean(parcel2, isReady);
                break;
            case 24:
                zza(zzavc.zzac(parcel.readStrongBinder()));
                parcel2.writeNoException();
                break;
            case 25:
                setUserId(parcel.readString());
                parcel2.writeNoException();
                break;
            case 26:
                zzie = getVideoController();
                parcel2.writeNoException();
                zzey.zza(parcel2, zzie);
                break;
            case 29:
                zza((zzzw) zzey.zza(parcel, zzzw.CREATOR));
                parcel2.writeNoException();
                break;
            case 30:
                zza((zzyv) zzey.zza(parcel, zzyv.CREATOR));
                parcel2.writeNoException();
                break;
            case 31:
                mediationAdapterClassName = getAdUnitId();
                parcel2.writeNoException();
                parcel2.writeString(mediationAdapterClassName);
                break;
            case 32:
                zzie = zzir();
                parcel2.writeNoException();
                zzey.zza(parcel2, zzie);
                break;
            case 33:
                zzie = zzis();
                parcel2.writeNoException();
                zzey.zza(parcel2, zzie);
                break;
            case 34:
                setImmersiveMode(zzey.zza(parcel));
                parcel2.writeNoException();
                break;
            case 35:
                mediationAdapterClassName = zzje();
                parcel2.writeNoException();
                parcel2.writeString(mediationAdapterClassName);
                break;
            case 36:
                zzxq zzxq;
                readStrongBinder = parcel.readStrongBinder();
                if (readStrongBinder != null) {
                    queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.gms.ads.internal.client.IAdMetadataListener");
                    if (queryLocalInterface instanceof zzxq) {
                        zzxq = (zzxq) queryLocalInterface;
                    } else {
                        zzxq = new zzxs(readStrongBinder);
                    }
                }
                zza(zzxq);
                parcel2.writeNoException();
                break;
            case 37:
                Bundle adMetadata = getAdMetadata();
                parcel2.writeNoException();
                zzey.zzb(parcel2, adMetadata);
                break;
            case 38:
                zzap(parcel.readString());
                parcel2.writeNoException();
                break;
            default:
                return false;
        }
        return true;
    }
}
