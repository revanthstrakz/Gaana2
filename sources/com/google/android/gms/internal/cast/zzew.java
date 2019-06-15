package com.google.android.gms.internal.cast;

import android.app.PendingIntent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;

public final class zzew extends zza implements zzev {
    zzew(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.cast.remote_display.ICastRemoteDisplayService");
    }

    public final void disconnect() throws RemoteException {
        zzc(3, zza());
    }

    public final void zza(zzet zzet, zzex zzex, String str, String str2, Bundle bundle) throws RemoteException {
        Parcel zza = zza();
        zzc.zza(zza, (IInterface) zzet);
        zzc.zza(zza, (IInterface) zzex);
        zza.writeString(str);
        zza.writeString(str2);
        zzc.zza(zza, (Parcelable) bundle);
        zzc(7, zza);
    }

    public final void zza(zzet zzet, PendingIntent pendingIntent, String str, String str2, Bundle bundle) throws RemoteException {
        Parcel zza = zza();
        zzc.zza(zza, (IInterface) zzet);
        zzc.zza(zza, (Parcelable) pendingIntent);
        zza.writeString(str);
        zza.writeString(str2);
        zzc.zza(zza, (Parcelable) bundle);
        zzc(8, zza);
    }

    public final void zza(zzet zzet, int i) throws RemoteException {
        Parcel zza = zza();
        zzc.zza(zza, (IInterface) zzet);
        zza.writeInt(i);
        zzc(5, zza);
    }

    public final void zza(zzet zzet) throws RemoteException {
        Parcel zza = zza();
        zzc.zza(zza, (IInterface) zzet);
        zzc(6, zza);
    }
}
