package com.google.android.gms.cast;

import android.hardware.display.DisplayManager;
import android.os.RemoteException;
import android.view.Display;
import android.view.Surface;
import com.facebook.internal.ServerProtocol;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.TaskUtil;
import com.google.android.gms.internal.cast.zzeq;
import com.google.android.gms.internal.cast.zzev;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzr extends zza {
    private final /* synthetic */ TaskCompletionSource zzbl;
    private final /* synthetic */ zzeq zzbm;
    private final /* synthetic */ zzq zzbn;

    zzr(zzq zzq, TaskCompletionSource taskCompletionSource, zzeq zzeq) {
        this.zzbn = zzq;
        this.zzbl = taskCompletionSource;
        this.zzbm = zzeq;
        super();
    }

    public final void zza(int i, int i2, Surface surface) throws RemoteException {
        this.zzbn.zzbk.zzbf.d("onConnected", new Object[0]);
        DisplayManager displayManager = (DisplayManager) this.zzbn.zzbk.getApplicationContext().getSystemService(ServerProtocol.DIALOG_PARAM_DISPLAY);
        if (displayManager == null) {
            this.zzbn.zzbk.zzbf.e("Unable to get the display manager", new Object[0]);
            TaskUtil.setResultOrApiException(Status.RESULT_INTERNAL_ERROR, null, this.zzbl);
            return;
        }
        this.zzbn.zzbk.zzc();
        this.zzbn.zzbk.zzbg = displayManager.createVirtualDisplay("private_display", i, i2, CastRemoteDisplayClient.zza(i, i2), surface, 2);
        if (this.zzbn.zzbk.zzbg == null) {
            this.zzbn.zzbk.zzbf.e("Unable to create virtual display", new Object[0]);
            TaskUtil.setResultOrApiException(Status.RESULT_INTERNAL_ERROR, null, this.zzbl);
            return;
        }
        Display display = this.zzbn.zzbk.zzbg.getDisplay();
        if (display == null) {
            this.zzbn.zzbk.zzbf.e("Virtual display does not have a display", new Object[0]);
            TaskUtil.setResultOrApiException(Status.RESULT_INTERNAL_ERROR, null, this.zzbl);
            return;
        }
        try {
            ((zzev) this.zzbm.getService()).zza(this, display.getDisplayId());
        } catch (RemoteException | IllegalStateException unused) {
            this.zzbn.zzbk.zzbf.e("Unable to provision the route's new virtual Display", new Object[0]);
            TaskUtil.setResultOrApiException(Status.RESULT_INTERNAL_ERROR, null, this.zzbl);
        }
    }

    public final void zzd() {
        this.zzbn.zzbk.zzbf.d("onConnectedWithDisplay", new Object[0]);
        if (this.zzbn.zzbk.zzbg == null) {
            this.zzbn.zzbk.zzbf.e("There is no virtual display", new Object[0]);
            TaskUtil.setResultOrApiException(Status.RESULT_INTERNAL_ERROR, null, this.zzbl);
            return;
        }
        Display display = this.zzbn.zzbk.zzbg.getDisplay();
        if (display != null) {
            TaskUtil.setResultOrApiException(Status.RESULT_SUCCESS, display, this.zzbl);
            return;
        }
        this.zzbn.zzbk.zzbf.e("Virtual display no longer has a display", new Object[0]);
        TaskUtil.setResultOrApiException(Status.RESULT_INTERNAL_ERROR, null, this.zzbl);
    }

    public final void onError(int i) throws RemoteException {
        this.zzbn.zzbk.zzbf.d("onError: %d", Integer.valueOf(i));
        this.zzbn.zzbk.zzc();
        TaskUtil.setResultOrApiException(Status.RESULT_INTERNAL_ERROR, null, this.zzbl);
    }
}
