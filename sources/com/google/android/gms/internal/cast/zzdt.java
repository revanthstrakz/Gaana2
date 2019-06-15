package com.google.android.gms.internal.cast;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.cast.zzbx;
import com.google.android.gms.common.api.internal.IStatusCallback;
import java.util.List;

public interface zzdt extends IInterface {
    void zza(IStatusCallback iStatusCallback, String[] strArr, String str, List<zzbx> list) throws RemoteException;
}
