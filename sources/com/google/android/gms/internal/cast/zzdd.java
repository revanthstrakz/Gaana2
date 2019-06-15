package com.google.android.gms.internal.cast;

import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import com.google.android.gms.cast.ApplicationMetadata;
import com.google.android.gms.cast.Cast;
import com.google.android.gms.cast.Cast.ApplicationConnectionResult;
import com.google.android.gms.cast.Cast.Listener;
import com.google.android.gms.cast.Cast.MessageReceivedCallback;
import com.google.android.gms.cast.CastDevice;
import com.google.android.gms.cast.CastStatusCodes;
import com.google.android.gms.cast.LaunchOptions;
import com.google.android.gms.cast.zzad;
import com.google.android.gms.cast.zzag;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation.ResultHolder;
import com.google.android.gms.common.internal.BinderWrapper;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.GmsClient;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs.CastExtraArgs;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public final class zzdd extends GmsClient<zzdp> {
    private static final zzdw zzbf = new zzdw("CastClientImpl");
    private static final Object zzyr = new Object();
    private static final Object zzys = new Object();
    private final Bundle extras;
    private final Listener zzak;
    private double zzet;
    private boolean zzeu;
    private final CastDevice zzie;
    private ApplicationMetadata zzxz;
    private final Map<String, MessageReceivedCallback> zzya = new HashMap();
    private final long zzyb;
    private zzdf zzyc;
    private String zzyd;
    private boolean zzye;
    private boolean zzyf;
    private boolean zzyg;
    private zzad zzyh;
    private int zzyi;
    private int zzyj;
    private final AtomicLong zzyk = new AtomicLong(0);
    private String zzyl;
    private String zzym;
    private Bundle zzyn;
    private final Map<Long, ResultHolder<Status>> zzyo = new HashMap();
    private ResultHolder<ApplicationConnectionResult> zzyp;
    private ResultHolder<Status> zzyq;

    public zzdd(Context context, Looper looper, ClientSettings clientSettings, CastDevice castDevice, long j, Listener listener, Bundle bundle, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
        super(context, looper, 10, clientSettings, connectionCallbacks, onConnectionFailedListener);
        this.zzie = castDevice;
        this.zzak = listener;
        this.zzyb = j;
        this.extras = bundle;
        zzet();
    }

    public final int getMinApkVersion() {
        return 12800000;
    }

    /* Access modifiers changed, original: protected|final */
    @NonNull
    public final String getServiceDescriptor() {
        return "com.google.android.gms.cast.internal.ICastDeviceController";
    }

    /* Access modifiers changed, original: protected|final */
    @NonNull
    public final String getStartServiceAction() {
        return "com.google.android.gms.cast.service.BIND_CAST_DEVICE_CONTROLLER_SERVICE";
    }

    private final void zzet() {
        this.zzyg = false;
        this.zzyi = -1;
        this.zzyj = -1;
        this.zzxz = null;
        this.zzyd = null;
        this.zzet = 0.0d;
        this.zzeu = false;
        this.zzyh = null;
    }

    /* Access modifiers changed, original: protected|final */
    public final void onPostInitHandler(int i, IBinder iBinder, Bundle bundle, int i2) {
        zzbf.d("in onPostInitHandler; statusCode=%d", Integer.valueOf(i));
        if (i == 0 || i == 1001) {
            this.zzyg = true;
            this.zzye = true;
            this.zzyf = true;
        } else {
            this.zzyg = false;
        }
        if (i == 1001) {
            this.zzyn = new Bundle();
            this.zzyn.putBoolean(Cast.EXTRA_APP_NO_LONGER_RUNNING, true);
            i = 0;
        }
        super.onPostInitHandler(i, iBinder, bundle, i2);
    }

    public final void disconnect() {
        zzbf.d("disconnect(); ServiceListener=%s, isConnected=%b", this.zzyc, Boolean.valueOf(isConnected()));
        zzdf zzdf = this.zzyc;
        this.zzyc = null;
        if (zzdf == null || zzdf.zzex() == null) {
            zzbf.d("already disposed, so short-circuiting", new Object[0]);
            return;
        }
        zzeu();
        try {
            ((zzdp) getService()).disconnect();
        } catch (RemoteException | IllegalStateException e) {
            zzbf.zza(e, "Error while disconnecting the controller interface: %s", e.getMessage());
        } finally {
            super.disconnect();
        }
    }

    public final Bundle getConnectionHint() {
        if (this.zzyn == null) {
            return super.getConnectionHint();
        }
        Bundle bundle = this.zzyn;
        this.zzyn = null;
        return bundle;
    }

    /* Access modifiers changed, original: protected|final */
    public final Bundle getGetServiceRequestExtraArgs() {
        Bundle bundle = new Bundle();
        zzbf.d("getRemoteService(): mLastApplicationId=%s, mLastSessionId=%s", this.zzyl, this.zzym);
        this.zzie.putInBundle(bundle);
        bundle.putLong("com.google.android.gms.cast.EXTRA_CAST_FLAGS", this.zzyb);
        if (this.extras != null) {
            bundle.putAll(this.extras);
        }
        this.zzyc = new zzdf(this);
        bundle.putParcelable(CastExtraArgs.LISTENER, new BinderWrapper(this.zzyc.asBinder()));
        if (this.zzyl != null) {
            bundle.putString("last_application_id", this.zzyl);
            if (this.zzym != null) {
                bundle.putString("last_session_id", this.zzym);
            }
        }
        return bundle;
    }

    public final void zza(String str, String str2, ResultHolder<Status> resultHolder) throws IllegalArgumentException, IllegalStateException, RemoteException {
        if (TextUtils.isEmpty(str2)) {
            throw new IllegalArgumentException("The message payload cannot be null or empty");
        } else if (str2.length() > 524288) {
            zzbf.w("Message send failed. Message exceeds maximum size", new Object[0]);
            throw new IllegalArgumentException("Message exceeds maximum size");
        } else {
            zzdk.zzp(str);
            long incrementAndGet = this.zzyk.incrementAndGet();
            try {
                this.zzyo.put(Long.valueOf(incrementAndGet), resultHolder);
                zzdp zzdp = (zzdp) getService();
                if (zzev()) {
                    zzdp.zza(str, str2, incrementAndGet);
                } else {
                    zzb(incrementAndGet, (int) CastStatusCodes.DEVICE_CONNECTION_SUSPENDED);
                }
            } catch (Throwable th) {
                this.zzyo.remove(Long.valueOf(incrementAndGet));
            }
        }
    }

    public final void zza(String str, LaunchOptions launchOptions, ResultHolder<ApplicationConnectionResult> resultHolder) throws IllegalStateException, RemoteException {
        zza((ResultHolder) resultHolder);
        zzdp zzdp = (zzdp) getService();
        if (zzev()) {
            zzdp.zzb(str, launchOptions);
        } else {
            zzq(CastStatusCodes.DEVICE_CONNECTION_SUSPENDED);
        }
    }

    public final void zza(String str, String str2, zzag zzag, ResultHolder<ApplicationConnectionResult> resultHolder) throws IllegalStateException, RemoteException {
        zza((ResultHolder) resultHolder);
        if (zzag == null) {
            zzag = new zzag();
        }
        zzdp zzdp = (zzdp) getService();
        if (zzev()) {
            zzdp.zza(str, str2, zzag);
        } else {
            zzq(CastStatusCodes.DEVICE_CONNECTION_SUSPENDED);
        }
    }

    private final void zza(ResultHolder<ApplicationConnectionResult> resultHolder) {
        synchronized (zzyr) {
            if (this.zzyp != null) {
                this.zzyp.setResult(new zzde(new Status(2002)));
            }
            this.zzyp = resultHolder;
        }
    }

    public final void zzb(ResultHolder<Status> resultHolder) throws IllegalStateException, RemoteException {
        zzc((ResultHolder) resultHolder);
        zzdp zzdp = (zzdp) getService();
        if (zzev()) {
            zzdp.zzfa();
        } else {
            zzr(CastStatusCodes.DEVICE_CONNECTION_SUSPENDED);
        }
    }

    public final void zza(String str, ResultHolder<Status> resultHolder) throws IllegalStateException, RemoteException {
        zzc((ResultHolder) resultHolder);
        zzdp zzdp = (zzdp) getService();
        if (zzev()) {
            zzdp.zzj(str);
        } else {
            zzr(CastStatusCodes.DEVICE_CONNECTION_SUSPENDED);
        }
    }

    private final void zzc(ResultHolder<Status> resultHolder) {
        synchronized (zzys) {
            if (this.zzyq != null) {
                resultHolder.setResult(new Status(2001));
                return;
            }
            this.zzyq = resultHolder;
        }
    }

    public final void requestStatus() throws IllegalStateException, RemoteException {
        zzdp zzdp = (zzdp) getService();
        if (zzev()) {
            zzdp.requestStatus();
        }
    }

    public final void setVolume(double d) throws IllegalArgumentException, IllegalStateException, RemoteException {
        if (Double.isInfinite(d) || Double.isNaN(d)) {
            StringBuilder stringBuilder = new StringBuilder(41);
            stringBuilder.append("Volume cannot be ");
            stringBuilder.append(d);
            throw new IllegalArgumentException(stringBuilder.toString());
        }
        zzdp zzdp = (zzdp) getService();
        if (zzev()) {
            zzdp.zza(d, this.zzet, this.zzeu);
        }
    }

    public final void setMute(boolean z) throws IllegalStateException, RemoteException {
        zzdp zzdp = (zzdp) getService();
        if (zzev()) {
            zzdp.zza(z, this.zzet, this.zzeu);
        }
    }

    public final double getVolume() throws IllegalStateException {
        checkConnected();
        return this.zzet;
    }

    public final boolean isMute() throws IllegalStateException {
        checkConnected();
        return this.zzeu;
    }

    public final int getActiveInputState() throws IllegalStateException {
        checkConnected();
        return this.zzyi;
    }

    public final int getStandbyState() throws IllegalStateException {
        checkConnected();
        return this.zzyj;
    }

    public final void setMessageReceivedCallbacks(String str, MessageReceivedCallback messageReceivedCallback) throws IllegalArgumentException, IllegalStateException, RemoteException {
        zzdk.zzp(str);
        removeMessageReceivedCallbacks(str);
        if (messageReceivedCallback != null) {
            synchronized (this.zzya) {
                this.zzya.put(str, messageReceivedCallback);
            }
            zzdp zzdp = (zzdp) getService();
            if (zzev()) {
                zzdp.zzs(str);
            }
        }
    }

    public final void removeMessageReceivedCallbacks(String str) throws IllegalArgumentException, RemoteException {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("Channel namespace cannot be null or empty");
        }
        MessageReceivedCallback messageReceivedCallback;
        synchronized (this.zzya) {
            messageReceivedCallback = (MessageReceivedCallback) this.zzya.remove(str);
        }
        if (messageReceivedCallback != null) {
            try {
                ((zzdp) getService()).zzt(str);
            } catch (IllegalStateException e) {
                zzbf.zza(e, "Error unregistering namespace (%s): %s", str, e.getMessage());
            }
        }
    }

    public final ApplicationMetadata getApplicationMetadata() throws IllegalStateException {
        checkConnected();
        return this.zzxz;
    }

    public final String getApplicationStatus() throws IllegalStateException {
        checkConnected();
        return this.zzyd;
    }

    public final void onConnectionFailed(ConnectionResult connectionResult) {
        super.onConnectionFailed(connectionResult);
        zzeu();
    }

    private final void zzeu() {
        zzbf.d("removing all MessageReceivedCallbacks", new Object[0]);
        synchronized (this.zzya) {
            this.zzya.clear();
        }
    }

    private final void zza(zzdl zzdl) {
        boolean z;
        ApplicationMetadata applicationMetadata = zzdl.getApplicationMetadata();
        if (!zzdk.zza(applicationMetadata, this.zzxz)) {
            this.zzxz = applicationMetadata;
            this.zzak.onApplicationMetadataChanged(this.zzxz);
        }
        double volume = zzdl.getVolume();
        if (Double.isNaN(volume) || Math.abs(volume - this.zzet) <= 1.0E-7d) {
            z = false;
        } else {
            this.zzet = volume;
            z = true;
        }
        boolean zzey = zzdl.zzey();
        if (zzey != this.zzeu) {
            this.zzeu = zzey;
            z = true;
        }
        zzbf.d("hasVolumeChanged=%b, mFirstDeviceStatusUpdate=%b", Boolean.valueOf(z), Boolean.valueOf(this.zzyf));
        if (this.zzak != null && (z || this.zzyf)) {
            this.zzak.onVolumeChanged();
        }
        int activeInputState = zzdl.getActiveInputState();
        if (activeInputState != this.zzyi) {
            this.zzyi = activeInputState;
            z = true;
        } else {
            z = false;
        }
        zzbf.d("hasActiveInputChanged=%b, mFirstDeviceStatusUpdate=%b", Boolean.valueOf(z), Boolean.valueOf(this.zzyf));
        if (this.zzak != null && (z || this.zzyf)) {
            this.zzak.onActiveInputStateChanged(this.zzyi);
        }
        activeInputState = zzdl.getStandbyState();
        if (activeInputState != this.zzyj) {
            this.zzyj = activeInputState;
            z = true;
        } else {
            z = false;
        }
        zzbf.d("hasStandbyStateChanged=%b, mFirstDeviceStatusUpdate=%b", Boolean.valueOf(z), Boolean.valueOf(this.zzyf));
        if (this.zzak != null && (z || this.zzyf)) {
            this.zzak.onStandbyStateChanged(this.zzyj);
        }
        if (!zzdk.zza(this.zzyh, zzdl.zzez())) {
            this.zzyh = zzdl.zzez();
        }
        this.zzyf = false;
    }

    private final void zza(zzct zzct) {
        boolean z;
        String zzep = zzct.zzep();
        if (zzdk.zza(zzep, this.zzyd)) {
            z = false;
        } else {
            this.zzyd = zzep;
            z = true;
        }
        zzbf.d("hasChanged=%b, mFirstApplicationStatusUpdate=%b", Boolean.valueOf(z), Boolean.valueOf(this.zzye));
        if (this.zzak != null && (z || this.zzye)) {
            this.zzak.onApplicationStatusChanged();
        }
        this.zzye = false;
    }

    @VisibleForTesting
    private final boolean zzev() {
        return (!this.zzyg || this.zzyc == null || this.zzyc.isDisposed()) ? false : true;
    }

    private final void zzb(long j, int i) {
        ResultHolder resultHolder;
        synchronized (this.zzyo) {
            resultHolder = (ResultHolder) this.zzyo.remove(Long.valueOf(j));
        }
        if (resultHolder != null) {
            resultHolder.setResult(new Status(i));
        }
    }

    public final void zzq(int i) {
        synchronized (zzyr) {
            if (this.zzyp != null) {
                this.zzyp.setResult(new zzde(new Status(i)));
                this.zzyp = null;
            }
        }
    }

    private final void zzr(int i) {
        synchronized (zzys) {
            if (this.zzyq != null) {
                this.zzyq.setResult(new Status(i));
                this.zzyq = null;
            }
        }
    }

    /* Access modifiers changed, original: protected|final|synthetic */
    public final /* synthetic */ IInterface createServiceInterface(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.cast.internal.ICastDeviceController");
        if (queryLocalInterface instanceof zzdp) {
            return (zzdp) queryLocalInterface;
        }
        return new zzdq(iBinder);
    }
}
