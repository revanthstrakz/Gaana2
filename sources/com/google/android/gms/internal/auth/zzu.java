package com.google.android.gms.internal.auth;

import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import com.google.android.gms.auth.api.accounttransfer.zzn;
import com.google.android.gms.common.GooglePlayServicesUtilLight;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.GmsClient;

public final class zzu extends GmsClient<zzz> {
    private final Bundle zzbv;

    public zzu(Context context, Looper looper, ClientSettings clientSettings, zzn zzn, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
        super(context, looper, 128, clientSettings, connectionCallbacks, onConnectionFailedListener);
        if (zzn == null) {
            this.zzbv = new Bundle();
            return;
        }
        throw new NoSuchMethodError();
    }

    public final int getMinApkVersion() {
        return GooglePlayServicesUtilLight.GOOGLE_PLAY_SERVICES_VERSION_CODE;
    }

    /* Access modifiers changed, original: protected|final */
    public final String getServiceDescriptor() {
        return "com.google.android.gms.auth.api.accounttransfer.internal.IAccountTransferService";
    }

    /* Access modifiers changed, original: protected|final */
    public final String getStartServiceAction() {
        return "com.google.android.gms.auth.api.accounttransfer.service.START";
    }

    /* Access modifiers changed, original: protected|final */
    public final Bundle getGetServiceRequestExtraArgs() {
        return this.zzbv;
    }

    /* Access modifiers changed, original: protected|final|synthetic */
    public final /* synthetic */ IInterface createServiceInterface(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.auth.api.accounttransfer.internal.IAccountTransferService");
        if (queryLocalInterface instanceof zzz) {
            return (zzz) queryLocalInterface;
        }
        return new zzaa(iBinder);
    }
}
