package com.google.android.gms.internal.cast;

import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IBinder.DeathRecipient;
import android.os.IInterface;
import android.os.Looper;
import android.os.RemoteException;
import com.google.android.gms.cast.CastDevice;
import com.google.android.gms.cast.CastRemoteDisplay.CastRemoteDisplaySessionCallbacks;
import com.google.android.gms.common.GooglePlayServicesUtilLight;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.GmsClient;

@Deprecated
public final class zzer extends GmsClient<zzev> implements DeathRecipient {
    private static final zzdw zzbf = new zzdw("CastRemoteDisplayClientImpl");
    private CastRemoteDisplaySessionCallbacks zzabd;
    private Bundle zzabe;
    private CastDevice zzaj;

    public zzer(Context context, Looper looper, ClientSettings clientSettings, CastDevice castDevice, Bundle bundle, CastRemoteDisplaySessionCallbacks castRemoteDisplaySessionCallbacks, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
        super(context, looper, 83, clientSettings, connectionCallbacks, onConnectionFailedListener);
        zzbf.d("instance created", new Object[0]);
        this.zzabd = castRemoteDisplaySessionCallbacks;
        this.zzaj = castDevice;
        this.zzabe = bundle;
    }

    public final void binderDied() {
    }

    public final int getMinApkVersion() {
        return GooglePlayServicesUtilLight.GOOGLE_PLAY_SERVICES_VERSION_CODE;
    }

    /* Access modifiers changed, original: protected|final */
    public final String getServiceDescriptor() {
        return "com.google.android.gms.cast.remote_display.ICastRemoteDisplayService";
    }

    /* Access modifiers changed, original: protected|final */
    public final String getStartServiceAction() {
        return "com.google.android.gms.cast.remote_display.service.START";
    }

    public final void disconnect() {
        zzbf.d("disconnect", new Object[0]);
        this.zzabd = null;
        this.zzaj = null;
        try {
            ((zzev) getService()).disconnect();
        } catch (RemoteException | IllegalStateException unused) {
        } finally {
            super.disconnect();
        }
    }

    public final void zza(zzet zzet, zzex zzex, String str) throws RemoteException {
        zzbf.d("startRemoteDisplay", new Object[0]);
        zzet zzet2 = zzet;
        ((zzev) getService()).zza(zzet2, new zzes(this, zzex), this.zzaj.getDeviceId(), str, this.zzabe);
    }

    public final void zza(zzet zzet) throws RemoteException {
        zzbf.d("stopRemoteDisplay", new Object[0]);
        ((zzev) getService()).zza(zzet);
    }

    public final /* synthetic */ IInterface createServiceInterface(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.cast.remote_display.ICastRemoteDisplayService");
        if (queryLocalInterface instanceof zzev) {
            return (zzev) queryLocalInterface;
        }
        return new zzew(iBinder);
    }
}
