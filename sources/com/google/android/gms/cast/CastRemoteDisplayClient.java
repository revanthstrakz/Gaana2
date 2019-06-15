package com.google.android.gms.cast;

import android.annotation.TargetApi;
import android.app.PendingIntent;
import android.content.Context;
import android.hardware.display.VirtualDisplay;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Display;
import android.view.Surface;
import com.google.android.gms.cast.CastRemoteDisplay.Configuration;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.AbstractClientBuilder;
import com.google.android.gms.common.api.Api.ApiOptions.NoOptions;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.GoogleApi.Settings;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.dynamite.descriptors.com.google.android.gms.ads.dynamite.ModuleDescriptor;
import com.google.android.gms.internal.cast.zzdv;
import com.google.android.gms.internal.cast.zzdw;
import com.google.android.gms.internal.cast.zzeq;
import com.google.android.gms.internal.cast.zzeu;
import com.google.android.gms.tasks.Task;

@TargetApi(19)
public class CastRemoteDisplayClient extends GoogleApi<NoOptions> {
    private static final Api<NoOptions> API = new Api("CastRemoteDisplay.API", zzad, zzdv.zzzh);
    private static final AbstractClientBuilder<zzeq, NoOptions> zzad = new zzp();
    private final zzdw zzbf = new zzdw("CastRemoteDisplay");
    private VirtualDisplay zzbg;

    private static class zza extends zzeu {
        private zza() {
        }

        public void zza(int i, int i2, Surface surface) throws RemoteException {
            throw new UnsupportedOperationException();
        }

        public void onError(int i) throws RemoteException {
            throw new UnsupportedOperationException();
        }

        public void onDisconnected() throws RemoteException {
            throw new UnsupportedOperationException();
        }

        public void zzd() throws RemoteException {
            throw new UnsupportedOperationException();
        }

        /* synthetic */ zza(zzp zzp) {
            this();
        }
    }

    CastRemoteDisplayClient(@NonNull Context context) {
        super(context, API, null, Settings.DEFAULT_SETTINGS);
    }

    public Task<Display> startRemoteDisplay(@NonNull CastDevice castDevice, @NonNull String str, @Configuration int i, @Nullable PendingIntent pendingIntent) {
        return doWrite((TaskApiCall) new zzq(this, i, pendingIntent, castDevice, str));
    }

    private static int zza(int i, int i2) {
        return (Math.min(i, i2) * ModuleDescriptor.MODULE_VERSION) / 1080;
    }

    @TargetApi(19)
    private final void zzc() {
        if (this.zzbg != null) {
            if (this.zzbg.getDisplay() != null) {
                zzdw zzdw = this.zzbf;
                int displayId = this.zzbg.getDisplay().getDisplayId();
                StringBuilder stringBuilder = new StringBuilder(38);
                stringBuilder.append("releasing virtual display: ");
                stringBuilder.append(displayId);
                zzdw.d(stringBuilder.toString(), new Object[0]);
            }
            this.zzbg.release();
            this.zzbg = null;
        }
    }

    public Task<Void> stopRemoteDisplay() {
        return doWrite((TaskApiCall) new zzs(this));
    }
}
