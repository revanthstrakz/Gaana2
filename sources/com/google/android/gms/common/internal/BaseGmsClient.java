package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.support.annotation.BinderThread;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import android.text.TextUtils;
import android.util.Log;
import com.gaana.cardoption.AssetsHelper.CARD;
import com.google.android.exoplayer2.extractor.ts.TsExtractor;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.util.VisibleForTesting;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

@KeepForSdk
public abstract class BaseGmsClient<T extends IInterface> {
    @KeepForSdk
    public static final int CONNECT_STATE_CONNECTED = 4;
    @KeepForSdk
    public static final int CONNECT_STATE_DISCONNECTED = 1;
    @KeepForSdk
    public static final int CONNECT_STATE_DISCONNECTING = 5;
    @KeepForSdk
    public static final String DEFAULT_ACCOUNT = "<<default account>>";
    @KeepForSdk
    public static final String[] GOOGLE_PLUS_REQUIRED_FEATURES = new String[]{"service_esmobile", "service_googleme"};
    @KeepForSdk
    public static final String KEY_PENDING_INTENT = "pendingIntent";
    private static final Feature[] zzbt = new Feature[0];
    private final Context mContext;
    final Handler mHandler;
    private final Object mLock;
    private int zzbu;
    private long zzbv;
    private long zzbw;
    private int zzbx;
    private long zzby;
    @VisibleForTesting
    private zzh zzbz;
    private final Looper zzca;
    private final GmsClientSupervisor zzcb;
    private final GoogleApiAvailabilityLight zzcc;
    private final Object zzcd;
    private IGmsServiceBroker zzce;
    @VisibleForTesting
    protected ConnectionProgressReportCallbacks zzcf;
    private T zzcg;
    private final ArrayList<zzc<?>> zzch;
    private zze zzci;
    private int zzcj;
    private final BaseConnectionCallbacks zzck;
    private final BaseOnConnectionFailedListener zzcl;
    private final int zzcm;
    private final String zzcn;
    private ConnectionResult zzco;
    private boolean zzcp;
    private volatile zzb zzcq;
    @VisibleForTesting
    protected AtomicInteger zzcr;

    @KeepForSdk
    public interface BaseConnectionCallbacks {
        @KeepForSdk
        void onConnected(@Nullable Bundle bundle);

        @KeepForSdk
        void onConnectionSuspended(int i);
    }

    @KeepForSdk
    public interface BaseOnConnectionFailedListener {
        void onConnectionFailed(@NonNull ConnectionResult connectionResult);
    }

    @KeepForSdk
    public interface ConnectionProgressReportCallbacks {
        @KeepForSdk
        void onReportServiceBinding(@NonNull ConnectionResult connectionResult);
    }

    @KeepForSdk
    public interface SignOutCallbacks {
        @KeepForSdk
        void onSignOutComplete();
    }

    protected abstract class zzc<TListener> {
        private TListener zzcu;
        private boolean zzcv = false;

        public zzc(TListener tListener) {
            this.zzcu = tListener;
        }

        public abstract void zza(TListener tListener);

        public abstract void zzn();

        public final void zzo() {
            Object obj;
            synchronized (this) {
                obj = this.zzcu;
                if (this.zzcv) {
                    String valueOf = String.valueOf(this);
                    StringBuilder stringBuilder = new StringBuilder(47 + String.valueOf(valueOf).length());
                    stringBuilder.append("Callback proxy ");
                    stringBuilder.append(valueOf);
                    stringBuilder.append(" being reused. This is not safe.");
                    Log.w("GmsClient", stringBuilder.toString());
                }
            }
            if (obj != null) {
                try {
                    zza(obj);
                } catch (RuntimeException e) {
                    zzn();
                    throw e;
                }
            }
            zzn();
            synchronized (this) {
                this.zzcv = true;
            }
            unregister();
        }

        public final void unregister() {
            removeListener();
            synchronized (BaseGmsClient.this.zzch) {
                BaseGmsClient.this.zzch.remove(this);
            }
        }

        public final void removeListener() {
            synchronized (this) {
                this.zzcu = null;
            }
        }
    }

    @VisibleForTesting
    public final class zze implements ServiceConnection {
        private final int zzcx;

        public zze(int i) {
            this.zzcx = i;
        }

        public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            if (iBinder == null) {
                BaseGmsClient.this.zzb(16);
                return;
            }
            synchronized (BaseGmsClient.this.zzcd) {
                IGmsServiceBroker iGmsServiceBroker;
                BaseGmsClient baseGmsClient = BaseGmsClient.this;
                if (iBinder == null) {
                    iGmsServiceBroker = null;
                } else {
                    IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
                    if (queryLocalInterface == null || !(queryLocalInterface instanceof IGmsServiceBroker)) {
                        iGmsServiceBroker = new zza(iBinder);
                    } else {
                        iGmsServiceBroker = (IGmsServiceBroker) queryLocalInterface;
                    }
                }
                baseGmsClient.zzce = iGmsServiceBroker;
            }
            BaseGmsClient.this.zza(0, null, this.zzcx);
        }

        public final void onServiceDisconnected(ComponentName componentName) {
            synchronized (BaseGmsClient.this.zzcd) {
                BaseGmsClient.this.zzce = null;
            }
            BaseGmsClient.this.mHandler.sendMessage(BaseGmsClient.this.mHandler.obtainMessage(6, this.zzcx, 1));
        }
    }

    protected class LegacyClientCallbackAdapter implements ConnectionProgressReportCallbacks {
        public void onReportServiceBinding(@NonNull ConnectionResult connectionResult) {
            if (connectionResult.isSuccess()) {
                BaseGmsClient.this.getRemoteService(null, BaseGmsClient.this.getScopes());
                return;
            }
            if (BaseGmsClient.this.zzcl != null) {
                BaseGmsClient.this.zzcl.onConnectionFailed(connectionResult);
            }
        }
    }

    private abstract class zza extends zzc<Boolean> {
        private final int statusCode;
        private final Bundle zzcs;

        @BinderThread
        protected zza(int i, Bundle bundle) {
            super(Boolean.valueOf(true));
            this.statusCode = i;
            this.zzcs = bundle;
        }

        public abstract void zza(ConnectionResult connectionResult);

        public abstract boolean zzm();

        /* Access modifiers changed, original: protected|final */
        public final void zzn() {
        }
    }

    final class zzb extends com.google.android.gms.internal.common.zze {
        public zzb(Looper looper) {
            super(looper);
        }

        public final void handleMessage(Message message) {
            if (BaseGmsClient.this.zzcr.get() != message.arg1) {
                if (zzb(message)) {
                    zza(message);
                }
            } else if ((message.what == 1 || message.what == 7 || message.what == 4 || message.what == 5) && !BaseGmsClient.this.isConnecting()) {
                zza(message);
            } else {
                PendingIntent pendingIntent = null;
                ConnectionResult zzd;
                if (message.what == 4) {
                    BaseGmsClient.this.zzco = new ConnectionResult(message.arg2);
                    if (!BaseGmsClient.this.zzl() || BaseGmsClient.this.zzcp) {
                        if (BaseGmsClient.this.zzco != null) {
                            zzd = BaseGmsClient.this.zzco;
                        } else {
                            zzd = new ConnectionResult(8);
                        }
                        BaseGmsClient.this.zzcf.onReportServiceBinding(zzd);
                        BaseGmsClient.this.onConnectionFailed(zzd);
                        return;
                    }
                    BaseGmsClient.this.zza(3, null);
                } else if (message.what == 5) {
                    if (BaseGmsClient.this.zzco != null) {
                        zzd = BaseGmsClient.this.zzco;
                    } else {
                        zzd = new ConnectionResult(8);
                    }
                    BaseGmsClient.this.zzcf.onReportServiceBinding(zzd);
                    BaseGmsClient.this.onConnectionFailed(zzd);
                } else if (message.what == 3) {
                    if (message.obj instanceof PendingIntent) {
                        pendingIntent = (PendingIntent) message.obj;
                    }
                    ConnectionResult connectionResult = new ConnectionResult(message.arg2, pendingIntent);
                    BaseGmsClient.this.zzcf.onReportServiceBinding(connectionResult);
                    BaseGmsClient.this.onConnectionFailed(connectionResult);
                } else if (message.what == 6) {
                    BaseGmsClient.this.zza(5, null);
                    if (BaseGmsClient.this.zzck != null) {
                        BaseGmsClient.this.zzck.onConnectionSuspended(message.arg2);
                    }
                    BaseGmsClient.this.onConnectionSuspended(message.arg2);
                    BaseGmsClient.this.zza(5, 1, null);
                } else if (message.what == 2 && !BaseGmsClient.this.isConnected()) {
                    zza(message);
                } else if (zzb(message)) {
                    ((zzc) message.obj).zzo();
                } else {
                    int i = message.what;
                    StringBuilder stringBuilder = new StringBuilder(45);
                    stringBuilder.append("Don't know how to handle message: ");
                    stringBuilder.append(i);
                    Log.wtf("GmsClient", stringBuilder.toString(), new Exception());
                }
            }
        }

        private static void zza(Message message) {
            zzc zzc = (zzc) message.obj;
            zzc.zzn();
            zzc.unregister();
        }

        private static boolean zzb(Message message) {
            return message.what == 2 || message.what == 1 || message.what == 7;
        }
    }

    @VisibleForTesting
    public static final class zzd extends com.google.android.gms.common.internal.IGmsCallbacks.zza {
        private BaseGmsClient zzcw;
        private final int zzcx;

        public zzd(@NonNull BaseGmsClient baseGmsClient, int i) {
            this.zzcw = baseGmsClient;
            this.zzcx = i;
        }

        @BinderThread
        public final void zza(int i, @Nullable Bundle bundle) {
            Log.wtf("GmsClient", "received deprecated onAccountValidationComplete callback, ignoring", new Exception());
        }

        @BinderThread
        public final void onPostInitComplete(int i, @NonNull IBinder iBinder, @Nullable Bundle bundle) {
            Preconditions.checkNotNull(this.zzcw, "onPostInitComplete can be called only once per call to getRemoteService");
            this.zzcw.onPostInitHandler(i, iBinder, bundle, this.zzcx);
            this.zzcw = null;
        }

        @BinderThread
        public final void zza(int i, @NonNull IBinder iBinder, @NonNull zzb zzb) {
            Preconditions.checkNotNull(this.zzcw, "onPostInitCompleteWithConnectionInfo can be called only once per call togetRemoteService");
            Preconditions.checkNotNull(zzb);
            this.zzcw.zza(zzb);
            onPostInitComplete(i, iBinder, zzb.zzda);
        }
    }

    protected final class zzf extends zza {
        private final IBinder zzcy;

        @BinderThread
        public zzf(int i, IBinder iBinder, Bundle bundle) {
            super(i, bundle);
            this.zzcy = iBinder;
        }

        /* Access modifiers changed, original: protected|final */
        public final void zza(ConnectionResult connectionResult) {
            if (BaseGmsClient.this.zzcl != null) {
                BaseGmsClient.this.zzcl.onConnectionFailed(connectionResult);
            }
            BaseGmsClient.this.onConnectionFailed(connectionResult);
        }

        /* Access modifiers changed, original: protected|final */
        public final boolean zzm() {
            try {
                String interfaceDescriptor = this.zzcy.getInterfaceDescriptor();
                if (BaseGmsClient.this.getServiceDescriptor().equals(interfaceDescriptor)) {
                    IInterface createServiceInterface = BaseGmsClient.this.createServiceInterface(this.zzcy);
                    if (createServiceInterface == null || (!BaseGmsClient.this.zza(2, 4, createServiceInterface) && !BaseGmsClient.this.zza(3, 4, createServiceInterface))) {
                        return false;
                    }
                    BaseGmsClient.this.zzco = null;
                    Bundle connectionHint = BaseGmsClient.this.getConnectionHint();
                    if (BaseGmsClient.this.zzck != null) {
                        BaseGmsClient.this.zzck.onConnected(connectionHint);
                    }
                    return true;
                }
                String serviceDescriptor = BaseGmsClient.this.getServiceDescriptor();
                StringBuilder stringBuilder = new StringBuilder((34 + String.valueOf(serviceDescriptor).length()) + String.valueOf(interfaceDescriptor).length());
                stringBuilder.append("service descriptor mismatch: ");
                stringBuilder.append(serviceDescriptor);
                stringBuilder.append(" vs. ");
                stringBuilder.append(interfaceDescriptor);
                Log.e("GmsClient", stringBuilder.toString());
                return false;
            } catch (RemoteException unused) {
                Log.w("GmsClient", "service probably died");
                return false;
            }
        }
    }

    protected final class zzg extends zza {
        @BinderThread
        public zzg(int i, Bundle bundle) {
            super(i, null);
        }

        /* Access modifiers changed, original: protected|final */
        public final void zza(ConnectionResult connectionResult) {
            BaseGmsClient.this.zzcf.onReportServiceBinding(connectionResult);
            BaseGmsClient.this.onConnectionFailed(connectionResult);
        }

        /* Access modifiers changed, original: protected|final */
        public final boolean zzm() {
            BaseGmsClient.this.zzcf.onReportServiceBinding(ConnectionResult.RESULT_SUCCESS);
            return true;
        }
    }

    @Nullable
    @KeepForSdk
    public abstract T createServiceInterface(IBinder iBinder);

    @KeepForSdk
    public Account getAccount() {
        return null;
    }

    @KeepForSdk
    public Bundle getConnectionHint() {
        return null;
    }

    /* Access modifiers changed, original: protected */
    @Nullable
    @KeepForSdk
    public String getLocalStartServiceAction() {
        return null;
    }

    @KeepForSdk
    @NonNull
    public abstract String getServiceDescriptor();

    @KeepForSdk
    @NonNull
    public abstract String getStartServiceAction();

    /* Access modifiers changed, original: protected */
    @KeepForSdk
    public String getStartServicePackage() {
        return "com.google.android.gms";
    }

    /* Access modifiers changed, original: 0000 */
    @KeepForSdk
    public void onSetConnectState(int i, T t) {
    }

    @KeepForSdk
    public boolean providesSignIn() {
        return false;
    }

    @KeepForSdk
    public boolean requiresAccount() {
        return false;
    }

    @KeepForSdk
    public boolean requiresGooglePlayServices() {
        return true;
    }

    @KeepForSdk
    public boolean requiresSignIn() {
        return false;
    }

    @KeepForSdk
    protected BaseGmsClient(Context context, Looper looper, int i, BaseConnectionCallbacks baseConnectionCallbacks, BaseOnConnectionFailedListener baseOnConnectionFailedListener, String str) {
        this(context, looper, GmsClientSupervisor.getInstance(context), GoogleApiAvailabilityLight.getInstance(), i, (BaseConnectionCallbacks) Preconditions.checkNotNull(baseConnectionCallbacks), (BaseOnConnectionFailedListener) Preconditions.checkNotNull(baseOnConnectionFailedListener), str);
    }

    @KeepForSdk
    @VisibleForTesting
    protected BaseGmsClient(Context context, Looper looper, GmsClientSupervisor gmsClientSupervisor, GoogleApiAvailabilityLight googleApiAvailabilityLight, int i, BaseConnectionCallbacks baseConnectionCallbacks, BaseOnConnectionFailedListener baseOnConnectionFailedListener, String str) {
        this.mLock = new Object();
        this.zzcd = new Object();
        this.zzch = new ArrayList();
        this.zzcj = 1;
        this.zzco = null;
        this.zzcp = false;
        this.zzcq = null;
        this.zzcr = new AtomicInteger(0);
        this.mContext = (Context) Preconditions.checkNotNull(context, "Context must not be null");
        this.zzca = (Looper) Preconditions.checkNotNull(looper, "Looper must not be null");
        this.zzcb = (GmsClientSupervisor) Preconditions.checkNotNull(gmsClientSupervisor, "Supervisor must not be null");
        this.zzcc = (GoogleApiAvailabilityLight) Preconditions.checkNotNull(googleApiAvailabilityLight, "API availability must not be null");
        this.mHandler = new zzb(looper);
        this.zzcm = i;
        this.zzck = baseConnectionCallbacks;
        this.zzcl = baseOnConnectionFailedListener;
        this.zzcn = str;
    }

    @KeepForSdk
    @VisibleForTesting
    protected BaseGmsClient(Context context, Handler handler, GmsClientSupervisor gmsClientSupervisor, GoogleApiAvailabilityLight googleApiAvailabilityLight, int i, BaseConnectionCallbacks baseConnectionCallbacks, BaseOnConnectionFailedListener baseOnConnectionFailedListener) {
        this.mLock = new Object();
        this.zzcd = new Object();
        this.zzch = new ArrayList();
        this.zzcj = 1;
        this.zzco = null;
        this.zzcp = false;
        this.zzcq = null;
        this.zzcr = new AtomicInteger(0);
        this.mContext = (Context) Preconditions.checkNotNull(context, "Context must not be null");
        this.mHandler = (Handler) Preconditions.checkNotNull(handler, "Handler must not be null");
        this.zzca = handler.getLooper();
        this.zzcb = (GmsClientSupervisor) Preconditions.checkNotNull(gmsClientSupervisor, "Supervisor must not be null");
        this.zzcc = (GoogleApiAvailabilityLight) Preconditions.checkNotNull(googleApiAvailabilityLight, "API availability must not be null");
        this.zzcm = i;
        this.zzck = baseConnectionCallbacks;
        this.zzcl = baseOnConnectionFailedListener;
        this.zzcn = null;
    }

    @Nullable
    private final String zzj() {
        return this.zzcn == null ? this.mContext.getClass().getName() : this.zzcn;
    }

    private final void zza(zzb zzb) {
        this.zzcq = zzb;
    }

    @Nullable
    @KeepForSdk
    public final Feature[] getAvailableFeatures() {
        zzb zzb = this.zzcq;
        if (zzb == null) {
            return null;
        }
        return zzb.zzdb;
    }

    /* Access modifiers changed, original: protected */
    @KeepForSdk
    @CallSuper
    public void onConnectedLocked(@NonNull T t) {
        this.zzbw = System.currentTimeMillis();
    }

    /* Access modifiers changed, original: protected */
    @KeepForSdk
    @CallSuper
    public void onConnectionSuspended(int i) {
        this.zzbu = i;
        this.zzbv = System.currentTimeMillis();
    }

    /* Access modifiers changed, original: protected */
    @KeepForSdk
    @CallSuper
    public void onConnectionFailed(ConnectionResult connectionResult) {
        this.zzbx = connectionResult.getErrorCode();
        this.zzby = System.currentTimeMillis();
    }

    private final void zza(int i, T t) {
        Preconditions.checkArgument((i == 4) == (t != null));
        synchronized (this.mLock) {
            this.zzcj = i;
            this.zzcg = t;
            onSetConnectState(i, t);
            switch (i) {
                case 1:
                    if (this.zzci != null) {
                        this.zzcb.zza(this.zzbz.zzu(), this.zzbz.getPackageName(), this.zzbz.zzq(), this.zzci, zzj());
                        this.zzci = null;
                        break;
                    }
                    break;
                case 2:
                case 3:
                    String zzu;
                    zzh zzh;
                    if (!(this.zzci == null || this.zzbz == null)) {
                        zzu = this.zzbz.zzu();
                        String packageName = this.zzbz.getPackageName();
                        StringBuilder stringBuilder = new StringBuilder((70 + String.valueOf(zzu).length()) + String.valueOf(packageName).length());
                        stringBuilder.append("Calling connect() while still connected, missing disconnect() for ");
                        stringBuilder.append(zzu);
                        stringBuilder.append(" on ");
                        stringBuilder.append(packageName);
                        Log.e("GmsClient", stringBuilder.toString());
                        this.zzcb.zza(this.zzbz.zzu(), this.zzbz.getPackageName(), this.zzbz.zzq(), this.zzci, zzj());
                        this.zzcr.incrementAndGet();
                    }
                    this.zzci = new zze(this.zzcr.get());
                    if (this.zzcj != 3 || getLocalStartServiceAction() == null) {
                        zzh = new zzh(getStartServicePackage(), getStartServiceAction(), false, TsExtractor.TS_STREAM_TYPE_AC3);
                    } else {
                        zzh = new zzh(getContext().getPackageName(), getLocalStartServiceAction(), true, TsExtractor.TS_STREAM_TYPE_AC3);
                    }
                    this.zzbz = zzh;
                    if (!this.zzcb.zza(new zza(this.zzbz.zzu(), this.zzbz.getPackageName(), this.zzbz.zzq()), this.zzci, zzj())) {
                        zzu = this.zzbz.zzu();
                        String packageName2 = this.zzbz.getPackageName();
                        StringBuilder stringBuilder2 = new StringBuilder((34 + String.valueOf(zzu).length()) + String.valueOf(packageName2).length());
                        stringBuilder2.append("unable to connect to service: ");
                        stringBuilder2.append(zzu);
                        stringBuilder2.append(" on ");
                        stringBuilder2.append(packageName2);
                        Log.e("GmsClient", stringBuilder2.toString());
                        zza(16, null, this.zzcr.get());
                        break;
                    }
                    break;
                case 4:
                    onConnectedLocked(t);
                    break;
                default:
                    break;
            }
        }
    }

    private final boolean zza(int i, int i2, T t) {
        synchronized (this.mLock) {
            if (this.zzcj != i) {
                return false;
            }
            zza(i2, (IInterface) t);
            return true;
        }
    }

    @KeepForSdk
    public void checkAvailabilityAndConnect() {
        int isGooglePlayServicesAvailable = this.zzcc.isGooglePlayServicesAvailable(this.mContext, getMinApkVersion());
        if (isGooglePlayServicesAvailable != 0) {
            zza(1, null);
            triggerNotAvailable(new LegacyClientCallbackAdapter(), isGooglePlayServicesAvailable, null);
            return;
        }
        connect(new LegacyClientCallbackAdapter());
    }

    @KeepForSdk
    public void connect(@NonNull ConnectionProgressReportCallbacks connectionProgressReportCallbacks) {
        this.zzcf = (ConnectionProgressReportCallbacks) Preconditions.checkNotNull(connectionProgressReportCallbacks, "Connection progress callbacks cannot be null.");
        zza(2, null);
    }

    @KeepForSdk
    public boolean isConnected() {
        boolean z;
        synchronized (this.mLock) {
            z = this.zzcj == 4;
        }
        return z;
    }

    @KeepForSdk
    public boolean isConnecting() {
        boolean z;
        synchronized (this.mLock) {
            if (this.zzcj != 2) {
                if (this.zzcj != 3) {
                    z = false;
                }
            }
            z = true;
        }
        return z;
    }

    private final boolean zzk() {
        boolean z;
        synchronized (this.mLock) {
            z = this.zzcj == 3;
        }
        return z;
    }

    @KeepForSdk
    public void disconnect() {
        this.zzcr.incrementAndGet();
        synchronized (this.zzch) {
            int size = this.zzch.size();
            for (int i = 0; i < size; i++) {
                ((zzc) this.zzch.get(i)).removeListener();
            }
            this.zzch.clear();
        }
        synchronized (this.zzcd) {
            this.zzce = null;
        }
        zza(1, null);
    }

    @KeepForSdk
    public void triggerConnectionSuspended(int i) {
        this.mHandler.sendMessage(this.mHandler.obtainMessage(6, this.zzcr.get(), i));
    }

    private final void zzb(int i) {
        if (zzk()) {
            i = 5;
            this.zzcp = true;
        } else {
            i = 4;
        }
        this.mHandler.sendMessage(this.mHandler.obtainMessage(i, this.zzcr.get(), 16));
    }

    /* Access modifiers changed, original: protected */
    @KeepForSdk
    @VisibleForTesting
    public void triggerNotAvailable(@NonNull ConnectionProgressReportCallbacks connectionProgressReportCallbacks, int i, @Nullable PendingIntent pendingIntent) {
        this.zzcf = (ConnectionProgressReportCallbacks) Preconditions.checkNotNull(connectionProgressReportCallbacks, "Connection progress callbacks cannot be null.");
        this.mHandler.sendMessage(this.mHandler.obtainMessage(3, this.zzcr.get(), i, pendingIntent));
    }

    @KeepForSdk
    public final Context getContext() {
        return this.mContext;
    }

    @KeepForSdk
    public final Looper getLooper() {
        return this.zzca;
    }

    @KeepForSdk
    public Feature[] getApiFeatures() {
        return zzbt;
    }

    /* Access modifiers changed, original: protected */
    @KeepForSdk
    public Bundle getGetServiceRequestExtraArgs() {
        return new Bundle();
    }

    /* Access modifiers changed, original: protected */
    @KeepForSdk
    public void onPostInitHandler(int i, IBinder iBinder, Bundle bundle, int i2) {
        this.mHandler.sendMessage(this.mHandler.obtainMessage(1, i2, -1, new zzf(i, iBinder, bundle)));
    }

    /* Access modifiers changed, original: protected|final */
    public final void zza(int i, @Nullable Bundle bundle, int i2) {
        this.mHandler.sendMessage(this.mHandler.obtainMessage(7, i2, -1, new zzg(i, null)));
    }

    /* Access modifiers changed, original: protected|final */
    @KeepForSdk
    public final void checkConnected() {
        if (!isConnected()) {
            throw new IllegalStateException("Not connected. Call connect() and wait for onConnected() to be called.");
        }
    }

    @KeepForSdk
    public final T getService() throws DeadObjectException {
        IInterface iInterface;
        synchronized (this.mLock) {
            if (this.zzcj == 5) {
                throw new DeadObjectException();
            }
            checkConnected();
            Preconditions.checkState(this.zzcg != null, "Client is connected but service is null");
            iInterface = this.zzcg;
        }
        return iInterface;
    }

    @WorkerThread
    @KeepForSdk
    public void getRemoteService(IAccountAccessor iAccountAccessor, Set<Scope> set) {
        Bundle getServiceRequestExtraArgs = getGetServiceRequestExtraArgs();
        GetServiceRequest getServiceRequest = new GetServiceRequest(this.zzcm);
        getServiceRequest.zzy = this.mContext.getPackageName();
        getServiceRequest.zzdk = getServiceRequestExtraArgs;
        if (set != null) {
            getServiceRequest.zzdj = (Scope[]) set.toArray(new Scope[set.size()]);
        }
        if (requiresSignIn()) {
            getServiceRequest.zzdl = getAccount() != null ? getAccount() : new Account("<<default account>>", "com.google");
            if (iAccountAccessor != null) {
                getServiceRequest.zzdi = iAccountAccessor.asBinder();
            }
        } else if (requiresAccount()) {
            getServiceRequest.zzdl = getAccount();
        }
        getServiceRequest.zzdm = zzbt;
        getServiceRequest.zzdn = getApiFeatures();
        try {
            synchronized (this.zzcd) {
                if (this.zzce != null) {
                    this.zzce.getService(new zzd(this, this.zzcr.get()), getServiceRequest);
                } else {
                    Log.w("GmsClient", "mServiceBroker is null, client disconnected");
                }
            }
        } catch (DeadObjectException e) {
            Log.w("GmsClient", "IGmsServiceBroker.getService failed", e);
            triggerConnectionSuspended(1);
        } catch (SecurityException e2) {
            throw e2;
        } catch (RemoteException | RuntimeException e3) {
            Log.w("GmsClient", "IGmsServiceBroker.getService failed", e3);
            onPostInitHandler(8, null, null, this.zzcr.get());
        }
    }

    @KeepForSdk
    public void onUserSignOut(@NonNull SignOutCallbacks signOutCallbacks) {
        signOutCallbacks.onSignOutComplete();
    }

    @KeepForSdk
    public Intent getSignInIntent() {
        throw new UnsupportedOperationException("Not a sign in API");
    }

    /* Access modifiers changed, original: protected */
    @KeepForSdk
    public Set<Scope> getScopes() {
        return Collections.EMPTY_SET;
    }

    @KeepForSdk
    public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        int i;
        IInterface iInterface;
        IGmsServiceBroker iGmsServiceBroker;
        PrintWriter append;
        long j;
        String format;
        StringBuilder stringBuilder;
        synchronized (this.mLock) {
            i = this.zzcj;
            iInterface = this.zzcg;
        }
        synchronized (this.zzcd) {
            iGmsServiceBroker = this.zzce;
        }
        printWriter.append(str).append("mConnectState=");
        switch (i) {
            case 1:
                printWriter.print("DISCONNECTED");
                break;
            case 2:
                printWriter.print("REMOTE_CONNECTING");
                break;
            case 3:
                printWriter.print("LOCAL_CONNECTING");
                break;
            case 4:
                printWriter.print("CONNECTED");
                break;
            case 5:
                printWriter.print("DISCONNECTING");
                break;
            default:
                printWriter.print(CARD.UNKNOWN);
                break;
        }
        printWriter.append(" mService=");
        if (iInterface == null) {
            printWriter.append("null");
        } else {
            printWriter.append(getServiceDescriptor()).append("@").append(Integer.toHexString(System.identityHashCode(iInterface.asBinder())));
        }
        printWriter.append(" mServiceBroker=");
        if (iGmsServiceBroker == null) {
            printWriter.println("null");
        } else {
            printWriter.append("IGmsServiceBroker@").println(Integer.toHexString(System.identityHashCode(iGmsServiceBroker.asBinder())));
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.US);
        if (this.zzbw > 0) {
            append = printWriter.append(str).append("lastConnectedTime=");
            j = this.zzbw;
            format = simpleDateFormat.format(new Date(this.zzbw));
            stringBuilder = new StringBuilder(String.valueOf(format).length() + 21);
            stringBuilder.append(j);
            stringBuilder.append(" ");
            stringBuilder.append(format);
            append.println(stringBuilder.toString());
        }
        if (this.zzbv > 0) {
            printWriter.append(str).append("lastSuspendedCause=");
            switch (this.zzbu) {
                case 1:
                    printWriter.append("CAUSE_SERVICE_DISCONNECTED");
                    break;
                case 2:
                    printWriter.append("CAUSE_NETWORK_LOST");
                    break;
                default:
                    printWriter.append(String.valueOf(this.zzbu));
                    break;
            }
            append = printWriter.append(" lastSuspendedTime=");
            j = this.zzbv;
            format = simpleDateFormat.format(new Date(this.zzbv));
            stringBuilder = new StringBuilder(String.valueOf(format).length() + 21);
            stringBuilder.append(j);
            stringBuilder.append(" ");
            stringBuilder.append(format);
            append.println(stringBuilder.toString());
        }
        if (this.zzby > 0) {
            printWriter.append(str).append("lastFailedStatus=").append(CommonStatusCodes.getStatusCodeString(this.zzbx));
            PrintWriter append2 = printWriter.append(" lastFailedTime=");
            long j2 = this.zzby;
            String format2 = simpleDateFormat.format(new Date(this.zzby));
            StringBuilder stringBuilder2 = new StringBuilder(21 + String.valueOf(format2).length());
            stringBuilder2.append(j2);
            stringBuilder2.append(" ");
            stringBuilder2.append(format2);
            append2.println(stringBuilder2.toString());
        }
    }

    @Nullable
    @KeepForSdk
    public IBinder getServiceBrokerBinder() {
        synchronized (this.zzcd) {
            if (this.zzce == null) {
                return null;
            }
            IBinder asBinder = this.zzce.asBinder();
            return asBinder;
        }
    }

    private final boolean zzl() {
        if (this.zzcp || TextUtils.isEmpty(getServiceDescriptor()) || TextUtils.isEmpty(getLocalStartServiceAction())) {
            return false;
        }
        try {
            Class.forName(getServiceDescriptor());
            return true;
        } catch (ClassNotFoundException unused) {
            return false;
        }
    }

    @KeepForSdk
    public String getEndpointPackageName() {
        if (isConnected() && this.zzbz != null) {
            return this.zzbz.getPackageName();
        }
        throw new RuntimeException("Failed to connect when checking package");
    }

    @KeepForSdk
    public int getMinApkVersion() {
        return GoogleApiAvailabilityLight.GOOGLE_PLAY_SERVICES_VERSION_CODE;
    }
}
