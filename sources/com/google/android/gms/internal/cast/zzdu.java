package com.google.android.gms.internal.cast;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.cast.zzbx;
import com.google.android.gms.common.api.internal.IStatusCallback;
import java.util.List;

public final class zzdu extends zza implements zzdt {
    zzdu(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.cast.internal.ICastService");
    }

    public final void zza(IStatusCallback iStatusCallback, String[] strArr, String str, List<zzbx> list) throws RemoteException {
        Parcel zza = zza();
        zzc.zza(zza, (IInterface) iStatusCallback);
        zza.writeStringArray(strArr);
        zza.writeString(str);
        zza.writeTypedList(list);
        zzc(2, zza);
    }
}
