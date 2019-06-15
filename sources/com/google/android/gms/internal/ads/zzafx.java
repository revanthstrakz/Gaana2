package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.DeadObjectException;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.BaseGmsClient.BaseConnectionCallbacks;

final class zzafx implements BaseConnectionCallbacks {
    private final /* synthetic */ zzbcl zzcaf;
    private final /* synthetic */ zzaft zzdgo;

    zzafx(zzaft zzaft, zzbcl zzbcl) {
        this.zzdgo = zzaft;
        this.zzcaf = zzbcl;
    }

    public final void onConnected(@Nullable Bundle bundle) {
        try {
            this.zzcaf.set(this.zzdgo.zzdgm.zzte());
        } catch (DeadObjectException e) {
            this.zzcaf.setException(e);
        }
    }

    public final void onConnectionSuspended(int i) {
        zzbcl zzbcl = this.zzcaf;
        StringBuilder stringBuilder = new StringBuilder(34);
        stringBuilder.append("onConnectionSuspended: ");
        stringBuilder.append(i);
        zzbcl.setException(new RuntimeException(stringBuilder.toString()));
    }
}
