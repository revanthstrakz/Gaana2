package com.google.android.gms.internal.cast;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.cast.LaunchOptions;
import com.google.android.gms.cast.zzag;

public final class zzdq extends zza implements zzdp {
    zzdq(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.cast.internal.ICastDeviceController");
    }

    public final void disconnect() throws RemoteException {
        zzc(1, zza());
    }

    public final void zzfa() throws RemoteException {
        zzc(4, zza());
    }

    public final void zzj(String str) throws RemoteException {
        Parcel zza = zza();
        zza.writeString(str);
        zzc(5, zza);
    }

    public final void requestStatus() throws RemoteException {
        zzc(6, zza());
    }

    public final void zza(double d, double d2, boolean z) throws RemoteException {
        Parcel zza = zza();
        zza.writeDouble(d);
        zza.writeDouble(d2);
        zzc.writeBoolean(zza, z);
        zzc(7, zza);
    }

    public final void zza(boolean z, double d, boolean z2) throws RemoteException {
        Parcel zza = zza();
        zzc.writeBoolean(zza, z);
        zza.writeDouble(d);
        zzc.writeBoolean(zza, z2);
        zzc(8, zza);
    }

    public final void zza(String str, String str2, long j) throws RemoteException {
        Parcel zza = zza();
        zza.writeString(str);
        zza.writeString(str2);
        zza.writeLong(j);
        zzc(9, zza);
    }

    public final void zzs(String str) throws RemoteException {
        Parcel zza = zza();
        zza.writeString(str);
        zzc(11, zza);
    }

    public final void zzt(String str) throws RemoteException {
        Parcel zza = zza();
        zza.writeString(str);
        zzc(12, zza);
    }

    public final void zzb(String str, LaunchOptions launchOptions) throws RemoteException {
        Parcel zza = zza();
        zza.writeString(str);
        zzc.zza(zza, (Parcelable) launchOptions);
        zzc(13, zza);
    }

    public final void zza(String str, String str2, zzag zzag) throws RemoteException {
        Parcel zza = zza();
        zza.writeString(str);
        zza.writeString(str2);
        zzc.zza(zza, (Parcelable) zzag);
        zzc(14, zza);
    }
}
