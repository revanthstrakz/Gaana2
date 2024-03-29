package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.DeadObjectException;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import com.google.android.gms.ads.internal.zzac;
import com.google.android.gms.common.internal.BaseGmsClient.BaseConnectionCallbacks;
import com.google.android.gms.common.internal.BaseGmsClient.BaseOnConnectionFailedListener;
import com.google.android.gms.common.util.VisibleForTesting;
import com.moe.pushlibrary.MoEWorker;

@zzark
public final class zztx extends zzac<zzub> {
    /* Access modifiers changed, original: protected|final */
    @VisibleForTesting
    public final String getServiceDescriptor() {
        return "com.google.android.gms.ads.internal.cache.ICacheService";
    }

    /* Access modifiers changed, original: protected|final */
    @VisibleForTesting
    public final String getStartServiceAction() {
        return "com.google.android.gms.ads.service.CACHE";
    }

    zztx(Context context, Looper looper, BaseConnectionCallbacks baseConnectionCallbacks, BaseOnConnectionFailedListener baseOnConnectionFailedListener) {
        super(zzaum.zzu(context), looper, MoEWorker.REQ_CODE_SEND_DATA, baseConnectionCallbacks, baseOnConnectionFailedListener, null);
    }

    public final zzub zzoh() throws DeadObjectException {
        return (zzub) super.getService();
    }

    /* Access modifiers changed, original: protected|final|synthetic */
    @VisibleForTesting
    public final /* synthetic */ IInterface createServiceInterface(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.cache.ICacheService");
        if (queryLocalInterface instanceof zzub) {
            return (zzub) queryLocalInterface;
        }
        return new zzuc(iBinder);
    }
}
