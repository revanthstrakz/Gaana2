package com.google.android.gms.internal.cast;

import android.app.PendingIntent;
import android.os.Bundle;
import android.os.IInterface;
import android.os.RemoteException;

public interface zzev extends IInterface {
    void disconnect() throws RemoteException;

    void zza(zzet zzet) throws RemoteException;

    void zza(zzet zzet, int i) throws RemoteException;

    void zza(zzet zzet, PendingIntent pendingIntent, String str, String str2, Bundle bundle) throws RemoteException;

    void zza(zzet zzet, zzex zzex, String str, String str2, Bundle bundle) throws RemoteException;
}
