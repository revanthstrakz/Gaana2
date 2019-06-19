package com.google.android.gms.common.api.internal;

import android.app.PendingIntent;
import android.content.Context;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import android.support.v4.util.ArrayMap;
import android.support.v4.util.ArraySet;
import android.util.Log;
import com.comscore.streaming.Constants;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.Api.ApiOptions;
import com.google.android.gms.common.api.Api.Client;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.UnsupportedApiCallException;
import com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl;
import com.google.android.gms.common.api.internal.ListenerHolder.ListenerKey;
import com.google.android.gms.common.internal.BaseGmsClient.ConnectionProgressReportCallbacks;
import com.google.android.gms.common.internal.GoogleApiAvailabilityCache;
import com.google.android.gms.common.internal.IAccountAccessor;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.SimpleClientAdapter;
import com.google.android.gms.common.util.ArrayUtils;
import com.google.android.gms.internal.base.zal;
import com.google.android.gms.signin.zad;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.payu.custombrowser.util.CBConstant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@KeepForSdk
public class GoogleApiManager implements Callback {
    private static final Object lock = new Object();
    public static final Status zahw = new Status(4, "Sign-out occurred while this API call was in progress.");
    private static final Status zahx = new Status(4, "The user must be signed in to make this API call.");
    private static GoogleApiManager zaib;
    private final Handler handler;
    private long zahy = DefaultRenderersFactory.DEFAULT_ALLOWED_VIDEO_JOINING_TIME_MS;
    private long zahz = 120000;
    private long zaia = Constants.HEARTBEAT_STAGE_ONE_INTERVAL;
    private final Context zaic;
    private final GoogleApiAvailability zaid;
    private final GoogleApiAvailabilityCache zaie;
    private final AtomicInteger zaif = new AtomicInteger(1);
    private final AtomicInteger zaig = new AtomicInteger(0);
    private final Map<zai<?>, zaa<?>> zaih = new ConcurrentHashMap(5, 0.75f, 1);
    private zaae zaii = null;
    private final Set<zai<?>> zaij = new ArraySet();
    private final Set<zai<?>> zaik = new ArraySet();

    public class zaa<O extends ApiOptions> implements ConnectionCallbacks, OnConnectionFailedListener, zar {
        private final zai<O> zafp;
        private final Queue<zab> zaim = new LinkedList();
        private final Client zain;
        private final AnyClient zaio;
        private final zaab zaip;
        private final Set<zak> zaiq = new HashSet();
        private final Map<ListenerKey<?>, zabw> zair = new HashMap();
        private final int zais;
        private final zace zait;
        private boolean zaiu;
        private final List<zab> zaiv = new ArrayList();
        private ConnectionResult zaiw = null;

        @WorkerThread
        public zaa(GoogleApi<O> googleApi) {
            this.zain = googleApi.zaa(GoogleApiManager.this.handler.getLooper(), this);
            if (this.zain instanceof SimpleClientAdapter) {
                this.zaio = ((SimpleClientAdapter) this.zain).getClient();
            } else {
                this.zaio = this.zain;
            }
            this.zafp = googleApi.zak();
            this.zaip = new zaab();
            this.zais = googleApi.getInstanceId();
            if (this.zain.requiresSignIn()) {
                this.zait = googleApi.zaa(GoogleApiManager.this.zaic, GoogleApiManager.this.handler);
            } else {
                this.zait = null;
            }
        }

        public final void onConnected(@Nullable Bundle bundle) {
            if (Looper.myLooper() == GoogleApiManager.this.handler.getLooper()) {
                zabg();
            } else {
                GoogleApiManager.this.handler.post(new zabj(this));
            }
        }

        @WorkerThread
        private final void zabg() {
            zabl();
            zai(ConnectionResult.RESULT_SUCCESS);
            zabn();
            Iterator it = this.zair.values().iterator();
            while (it.hasNext()) {
                zabw zabw = (zabw) it.next();
                if (zaa(zabw.zajw.getRequiredFeatures()) != null) {
                    it.remove();
                } else {
                    try {
                        zabw.zajw.registerListener(this.zaio, new TaskCompletionSource());
                    } catch (DeadObjectException unused) {
                        onConnectionSuspended(1);
                        this.zain.disconnect();
                    } catch (RemoteException unused2) {
                        it.remove();
                    }
                }
            }
            zabi();
            zabo();
        }

        public final void onConnectionSuspended(int i) {
            if (Looper.myLooper() == GoogleApiManager.this.handler.getLooper()) {
                zabh();
            } else {
                GoogleApiManager.this.handler.post(new zabk(this));
            }
        }

        @WorkerThread
        private final void zabh() {
            zabl();
            this.zaiu = true;
            this.zaip.zaai();
            GoogleApiManager.this.handler.sendMessageDelayed(Message.obtain(GoogleApiManager.this.handler, 9, this.zafp), GoogleApiManager.this.zahy);
            GoogleApiManager.this.handler.sendMessageDelayed(Message.obtain(GoogleApiManager.this.handler, 11, this.zafp), GoogleApiManager.this.zahz);
            GoogleApiManager.this.zaie.flush();
        }

        @WorkerThread
        public final void zag(@NonNull ConnectionResult connectionResult) {
            Preconditions.checkHandlerThread(GoogleApiManager.this.handler);
            this.zain.disconnect();
            onConnectionFailed(connectionResult);
        }

        @WorkerThread
        private final boolean zah(@NonNull ConnectionResult connectionResult) {
            synchronized (GoogleApiManager.lock) {
                if (GoogleApiManager.this.zaii == null || !GoogleApiManager.this.zaij.contains(this.zafp)) {
                    return false;
                }
                GoogleApiManager.this.zaii.zab(connectionResult, this.zais);
                return true;
            }
        }

        public final void zaa(ConnectionResult connectionResult, Api<?> api, boolean z) {
            if (Looper.myLooper() == GoogleApiManager.this.handler.getLooper()) {
                onConnectionFailed(connectionResult);
            } else {
                GoogleApiManager.this.handler.post(new zabl(this, connectionResult));
            }
        }

        @WorkerThread
        public final void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
            Preconditions.checkHandlerThread(GoogleApiManager.this.handler);
            if (this.zait != null) {
                this.zait.zabs();
            }
            zabl();
            GoogleApiManager.this.zaie.flush();
            zai(connectionResult);
            if (connectionResult.getErrorCode() == 4) {
                zac(GoogleApiManager.zahx);
            } else if (this.zaim.isEmpty()) {
                this.zaiw = connectionResult;
            } else {
                if (!(zah(connectionResult) || GoogleApiManager.this.zac(connectionResult, this.zais))) {
                    if (connectionResult.getErrorCode() == 18) {
                        this.zaiu = true;
                    }
                    if (this.zaiu) {
                        GoogleApiManager.this.handler.sendMessageDelayed(Message.obtain(GoogleApiManager.this.handler, 9, this.zafp), GoogleApiManager.this.zahy);
                        return;
                    }
                    String zan = this.zafp.zan();
                    StringBuilder stringBuilder = new StringBuilder(38 + String.valueOf(zan).length());
                    stringBuilder.append("API: ");
                    stringBuilder.append(zan);
                    stringBuilder.append(" is not available on this device.");
                    zac(new Status(17, stringBuilder.toString()));
                }
            }
        }

        @WorkerThread
        private final void zabi() {
            ArrayList arrayList = new ArrayList(this.zaim);
            int size = arrayList.size();
            int i = 0;
            while (i < size) {
                Object obj = arrayList.get(i);
                i++;
                zab zab = (zab) obj;
                if (!this.zain.isConnected()) {
                    return;
                }
                if (zab(zab)) {
                    this.zaim.remove(zab);
                }
            }
        }

        @WorkerThread
        public final void zaa(zab zab) {
            Preconditions.checkHandlerThread(GoogleApiManager.this.handler);
            if (!this.zain.isConnected()) {
                this.zaim.add(zab);
                if (this.zaiw == null || !this.zaiw.hasResolution()) {
                    connect();
                } else {
                    onConnectionFailed(this.zaiw);
                }
            } else if (zab(zab)) {
                zabo();
            } else {
                this.zaim.add(zab);
            }
        }

        @WorkerThread
        public final void zabj() {
            Preconditions.checkHandlerThread(GoogleApiManager.this.handler);
            zac(GoogleApiManager.zahw);
            this.zaip.zaah();
            for (ListenerKey zah : (ListenerKey[]) this.zair.keySet().toArray(new ListenerKey[this.zair.size()])) {
                zaa(new zah(zah, new TaskCompletionSource()));
            }
            zai(new ConnectionResult(4));
            if (this.zain.isConnected()) {
                this.zain.onUserSignOut(new zabm(this));
            }
        }

        public final Client zaab() {
            return this.zain;
        }

        public final Map<ListenerKey<?>, zabw> zabk() {
            return this.zair;
        }

        @WorkerThread
        public final void zabl() {
            Preconditions.checkHandlerThread(GoogleApiManager.this.handler);
            this.zaiw = null;
        }

        @WorkerThread
        public final ConnectionResult zabm() {
            Preconditions.checkHandlerThread(GoogleApiManager.this.handler);
            return this.zaiw;
        }

        @WorkerThread
        private final boolean zab(zab zab) {
            if (zab instanceof zac) {
                zac zac = (zac) zab;
                Feature zaa = zaa(zac.zab(this));
                if (zaa == null) {
                    zac(zab);
                    return true;
                }
                if (zac.zac(this)) {
                    zab zab2 = new zab(this.zafp, zaa, null);
                    int indexOf = this.zaiv.indexOf(zab2);
                    if (indexOf >= 0) {
                        zab2 = (zab) this.zaiv.get(indexOf);
                        GoogleApiManager.this.handler.removeMessages(15, zab2);
                        GoogleApiManager.this.handler.sendMessageDelayed(Message.obtain(GoogleApiManager.this.handler, 15, zab2), GoogleApiManager.this.zahy);
                    } else {
                        this.zaiv.add(zab2);
                        GoogleApiManager.this.handler.sendMessageDelayed(Message.obtain(GoogleApiManager.this.handler, 15, zab2), GoogleApiManager.this.zahy);
                        GoogleApiManager.this.handler.sendMessageDelayed(Message.obtain(GoogleApiManager.this.handler, 16, zab2), GoogleApiManager.this.zahz);
                        ConnectionResult connectionResult = new ConnectionResult(2, null);
                        if (!zah(connectionResult)) {
                            GoogleApiManager.this.zac(connectionResult, this.zais);
                        }
                    }
                } else {
                    zac.zaa(new UnsupportedApiCallException(zaa));
                }
                return false;
            }
            zac(zab);
            return true;
        }

        @WorkerThread
        private final void zac(zab zab) {
            zab.zaa(this.zaip, requiresSignIn());
            try {
                zab.zaa(this);
            } catch (DeadObjectException unused) {
                onConnectionSuspended(1);
                this.zain.disconnect();
            }
        }

        @WorkerThread
        public final void zac(Status status) {
            Preconditions.checkHandlerThread(GoogleApiManager.this.handler);
            for (zab zaa : this.zaim) {
                zaa.zaa(status);
            }
            this.zaim.clear();
        }

        @WorkerThread
        public final void resume() {
            Preconditions.checkHandlerThread(GoogleApiManager.this.handler);
            if (this.zaiu) {
                connect();
            }
        }

        @WorkerThread
        private final void zabn() {
            if (this.zaiu) {
                GoogleApiManager.this.handler.removeMessages(11, this.zafp);
                GoogleApiManager.this.handler.removeMessages(9, this.zafp);
                this.zaiu = false;
            }
        }

        @WorkerThread
        public final void zaav() {
            Preconditions.checkHandlerThread(GoogleApiManager.this.handler);
            if (this.zaiu) {
                Status status;
                zabn();
                if (GoogleApiManager.this.zaid.isGooglePlayServicesAvailable(GoogleApiManager.this.zaic) == 18) {
                    status = new Status(8, "Connection timed out while waiting for Google Play services update to complete.");
                } else {
                    status = new Status(8, "API failed to connect while resuming due to an unknown error.");
                }
                zac(status);
                this.zain.disconnect();
            }
        }

        private final void zabo() {
            GoogleApiManager.this.handler.removeMessages(12, this.zafp);
            GoogleApiManager.this.handler.sendMessageDelayed(GoogleApiManager.this.handler.obtainMessage(12, this.zafp), GoogleApiManager.this.zaia);
        }

        @WorkerThread
        public final boolean zabp() {
            return zac(true);
        }

        @WorkerThread
        private final boolean zac(boolean z) {
            Preconditions.checkHandlerThread(GoogleApiManager.this.handler);
            if (!this.zain.isConnected() || this.zair.size() != 0) {
                return false;
            }
            if (this.zaip.zaag()) {
                if (z) {
                    zabo();
                }
                return false;
            }
            this.zain.disconnect();
            return true;
        }

        @WorkerThread
        public final void connect() {
            Preconditions.checkHandlerThread(GoogleApiManager.this.handler);
            if (!this.zain.isConnected() && !this.zain.isConnecting()) {
                int clientAvailability = GoogleApiManager.this.zaie.getClientAvailability(GoogleApiManager.this.zaic, this.zain);
                if (clientAvailability != 0) {
                    onConnectionFailed(new ConnectionResult(clientAvailability, null));
                    return;
                }
                zach zac = new zac(this.zain, this.zafp);
                if (this.zain.requiresSignIn()) {
                    this.zait.zaa(zac);
                }
                this.zain.connect(zac);
            }
        }

        @WorkerThread
        public final void zaa(zak zak) {
            Preconditions.checkHandlerThread(GoogleApiManager.this.handler);
            this.zaiq.add(zak);
        }

        @WorkerThread
        private final void zai(ConnectionResult connectionResult) {
            for (zak zak : this.zaiq) {
                String str = null;
                if (Objects.equal(connectionResult, ConnectionResult.RESULT_SUCCESS)) {
                    str = this.zain.getEndpointPackageName();
                }
                zak.zaa(this.zafp, connectionResult, str);
            }
            this.zaiq.clear();
        }

        /* Access modifiers changed, original: final */
        public final boolean isConnected() {
            return this.zain.isConnected();
        }

        public final boolean requiresSignIn() {
            return this.zain.requiresSignIn();
        }

        public final int getInstanceId() {
            return this.zais;
        }

        /* Access modifiers changed, original: final */
        public final zad zabq() {
            return this.zait == null ? null : this.zait.zabq();
        }

        @Nullable
        @WorkerThread
        private final Feature zaa(@Nullable Feature[] featureArr) {
            if (featureArr == null || featureArr.length == 0) {
                return null;
            }
            Feature[] availableFeatures = this.zain.getAvailableFeatures();
            int i = 0;
            if (availableFeatures == null) {
                availableFeatures = new Feature[0];
            }
            ArrayMap arrayMap = new ArrayMap(availableFeatures.length);
            for (Feature feature : availableFeatures) {
                arrayMap.put(feature.getName(), Long.valueOf(feature.getVersion()));
            }
            int length = featureArr.length;
            while (i < length) {
                Feature feature2 = featureArr[i];
                if (!arrayMap.containsKey(feature2.getName()) || ((Long) arrayMap.get(feature2.getName())).longValue() < feature2.getVersion()) {
                    return feature2;
                }
                i++;
            }
            return null;
        }

        @WorkerThread
        private final void zaa(zab zab) {
            if (this.zaiv.contains(zab) && !this.zaiu) {
                if (this.zain.isConnected()) {
                    zabi();
                } else {
                    connect();
                }
            }
        }

        @WorkerThread
        private final void zab(zab zab) {
            if (this.zaiv.remove(zab)) {
                GoogleApiManager.this.handler.removeMessages(15, zab);
                GoogleApiManager.this.handler.removeMessages(16, zab);
                Object zad = zab.zajb;
                ArrayList arrayList = new ArrayList(this.zaim.size());
                for (zab zab2 : this.zaim) {
                    if (zab2 instanceof zac) {
                        Object[] zab3 = ((zac) zab2).zab(this);
                        if (zab3 != null && ArrayUtils.contains(zab3, zad)) {
                            arrayList.add(zab2);
                        }
                    }
                }
                arrayList = arrayList;
                int size = arrayList.size();
                int i = 0;
                while (i < size) {
                    Object obj = arrayList.get(i);
                    i++;
                    zab zab4 = (zab) obj;
                    this.zaim.remove(zab4);
                    zab4.zaa(new UnsupportedApiCallException(zad));
                }
            }
        }
    }

    private static class zab {
        private final zai<?> zaja;
        private final Feature zajb;

        private zab(zai<?> zai, Feature feature) {
            this.zaja = zai;
            this.zajb = feature;
        }

        public final boolean equals(Object obj) {
            if (obj == null || !(obj instanceof zab)) {
                return false;
            }
            zab zab = (zab) obj;
            if (Objects.equal(this.zaja, zab.zaja) && Objects.equal(this.zajb, zab.zajb)) {
                return true;
            }
            return false;
        }

        public final int hashCode() {
            return Objects.hashCode(this.zaja, this.zajb);
        }

        public final String toString() {
            return Objects.toStringHelper(this).add(CBConstant.KEY, this.zaja).add("feature", this.zajb).toString();
        }

        /* synthetic */ zab(zai zai, Feature feature, zabi zabi) {
            this(zai, feature);
        }
    }

    private class zac implements zach, ConnectionProgressReportCallbacks {
        private final zai<?> zafp;
        private final Client zain;
        private IAccountAccessor zajc = null;
        private Set<Scope> zajd = null;
        private boolean zaje = false;

        public zac(Client client, zai<?> zai) {
            this.zain = client;
            this.zafp = zai;
        }

        public final void onReportServiceBinding(@NonNull ConnectionResult connectionResult) {
            GoogleApiManager.this.handler.post(new zabo(this, connectionResult));
        }

        @WorkerThread
        public final void zag(ConnectionResult connectionResult) {
            ((zaa) GoogleApiManager.this.zaih.get(this.zafp)).zag(connectionResult);
        }

        @WorkerThread
        public final void zaa(IAccountAccessor iAccountAccessor, Set<Scope> set) {
            if (iAccountAccessor == null || set == null) {
                Log.wtf("GoogleApiManager", "Received null response from onSignInSuccess", new Exception());
                zag(new ConnectionResult(4));
                return;
            }
            this.zajc = iAccountAccessor;
            this.zajd = set;
            zabr();
        }

        @WorkerThread
        private final void zabr() {
            if (this.zaje && this.zajc != null) {
                this.zain.getRemoteService(this.zajc, this.zajd);
            }
        }
    }

    public static GoogleApiManager zab(Context context) {
        GoogleApiManager googleApiManager;
        synchronized (lock) {
            if (zaib == null) {
                HandlerThread handlerThread = new HandlerThread("GoogleApiHandler", 9);
                handlerThread.start();
                zaib = new GoogleApiManager(context.getApplicationContext(), handlerThread.getLooper(), GoogleApiAvailability.getInstance());
            }
            googleApiManager = zaib;
        }
        return googleApiManager;
    }

    public static GoogleApiManager zabc() {
        GoogleApiManager googleApiManager;
        synchronized (lock) {
            Preconditions.checkNotNull(zaib, "Must guarantee manager is non-null before using getInstance");
            googleApiManager = zaib;
        }
        return googleApiManager;
    }

    @KeepForSdk
    public static void reportSignOut() {
        synchronized (lock) {
            if (zaib != null) {
                GoogleApiManager googleApiManager = zaib;
                googleApiManager.zaig.incrementAndGet();
                googleApiManager.handler.sendMessageAtFrontOfQueue(googleApiManager.handler.obtainMessage(10));
            }
        }
    }

    @KeepForSdk
    private GoogleApiManager(Context context, Looper looper, GoogleApiAvailability googleApiAvailability) {
        this.zaic = context;
        this.handler = new zal(looper, this);
        this.zaid = googleApiAvailability;
        this.zaie = new GoogleApiAvailabilityCache(googleApiAvailability);
        this.handler.sendMessage(this.handler.obtainMessage(6));
    }

    public final int zabd() {
        return this.zaif.getAndIncrement();
    }

    public final void zaa(GoogleApi<?> googleApi) {
        this.handler.sendMessage(this.handler.obtainMessage(7, googleApi));
    }

    @WorkerThread
    private final void zab(GoogleApi<?> googleApi) {
        zai zak = googleApi.zak();
        zaa zaa = (zaa) this.zaih.get(zak);
        if (zaa == null) {
            zaa = new zaa(googleApi);
            this.zaih.put(zak, zaa);
        }
        if (zaa.requiresSignIn()) {
            this.zaik.add(zak);
        }
        zaa.connect();
    }

    public final void zaa(@NonNull zaae zaae) {
        synchronized (lock) {
            if (this.zaii != zaae) {
                this.zaii = zaae;
                this.zaij.clear();
            }
            this.zaij.addAll(zaae.zaaj());
        }
    }

    /* Access modifiers changed, original: final */
    public final void zab(@NonNull zaae zaae) {
        synchronized (lock) {
            if (this.zaii == zaae) {
                this.zaii = null;
                this.zaij.clear();
            }
        }
    }

    public final Task<Map<zai<?>, String>> zaa(Iterable<? extends GoogleApi<?>> iterable) {
        zak zak = new zak(iterable);
        this.handler.sendMessage(this.handler.obtainMessage(2, zak));
        return zak.getTask();
    }

    public final void zao() {
        this.handler.sendMessage(this.handler.obtainMessage(3));
    }

    /* Access modifiers changed, original: final */
    public final void maybeSignOut() {
        this.zaig.incrementAndGet();
        this.handler.sendMessage(this.handler.obtainMessage(10));
    }

    public final Task<Boolean> zac(GoogleApi<?> googleApi) {
        zaaf zaaf = new zaaf(googleApi.zak());
        this.handler.sendMessage(this.handler.obtainMessage(14, zaaf));
        return zaaf.zaal().getTask();
    }

    public final <O extends ApiOptions> void zaa(GoogleApi<O> googleApi, int i, ApiMethodImpl<? extends Result, AnyClient> apiMethodImpl) {
        this.handler.sendMessage(this.handler.obtainMessage(4, new zabv(new zae(i, apiMethodImpl), this.zaig.get(), googleApi)));
    }

    public final <O extends ApiOptions, ResultT> void zaa(GoogleApi<O> googleApi, int i, TaskApiCall<AnyClient, ResultT> taskApiCall, TaskCompletionSource<ResultT> taskCompletionSource, StatusExceptionMapper statusExceptionMapper) {
        this.handler.sendMessage(this.handler.obtainMessage(4, new zabv(new zag(i, taskApiCall, taskCompletionSource, statusExceptionMapper), this.zaig.get(), googleApi)));
    }

    public final <O extends ApiOptions> Task<Void> zaa(@NonNull GoogleApi<O> googleApi, @NonNull RegisterListenerMethod<AnyClient, ?> registerListenerMethod, @NonNull UnregisterListenerMethod<AnyClient, ?> unregisterListenerMethod) {
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        this.handler.sendMessage(this.handler.obtainMessage(8, new zabv(new zaf(new zabw(registerListenerMethod, unregisterListenerMethod), taskCompletionSource), this.zaig.get(), googleApi)));
        return taskCompletionSource.getTask();
    }

    public final <O extends ApiOptions> Task<Boolean> zaa(@NonNull GoogleApi<O> googleApi, @NonNull ListenerKey<?> listenerKey) {
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        this.handler.sendMessage(this.handler.obtainMessage(13, new zabv(new zah(listenerKey, taskCompletionSource), this.zaig.get(), googleApi)));
        return taskCompletionSource.getTask();
    }

    /* JADX WARNING: Removed duplicated region for block: B:44:0x01b9  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x0172  */
    @android.support.annotation.WorkerThread
    public boolean handleMessage(android.os.Message r8) {
        /*
        r7 = this;
        r0 = r8.what;
        r1 = 300000; // 0x493e0 float:4.2039E-40 double:1.482197E-318;
        r3 = 1;
        r4 = 0;
        r5 = 0;
        switch(r0) {
            case 1: goto L_0x029a;
            case 2: goto L_0x0244;
            case 3: goto L_0x0227;
            case 4: goto L_0x01dd;
            case 5: goto L_0x014c;
            case 6: goto L_0x0117;
            case 7: goto L_0x010e;
            case 8: goto L_0x01dd;
            case 9: goto L_0x00f5;
            case 10: goto L_0x00d0;
            case 11: goto L_0x00b7;
            case 12: goto L_0x009e;
            case 13: goto L_0x01dd;
            case 14: goto L_0x0068;
            case 15: goto L_0x0047;
            case 16: goto L_0x0026;
            default: goto L_0x000b;
        };
    L_0x000b:
        r0 = "GoogleApiManager";
        r8 = r8.what;
        r1 = 31;
        r2 = new java.lang.StringBuilder;
        r2.<init>(r1);
        r1 = "Unknown message id: ";
        r2.append(r1);
        r2.append(r8);
        r8 = r2.toString();
        android.util.Log.w(r0, r8);
        return r5;
    L_0x0026:
        r8 = r8.obj;
        r8 = (com.google.android.gms.common.api.internal.GoogleApiManager.zab) r8;
        r0 = r7.zaih;
        r1 = r8.zaja;
        r0 = r0.containsKey(r1);
        if (r0 == 0) goto L_0x02d3;
    L_0x0036:
        r0 = r7.zaih;
        r1 = r8.zaja;
        r0 = r0.get(r1);
        r0 = (com.google.android.gms.common.api.internal.GoogleApiManager.zaa) r0;
        r0.zab(r8);
        goto L_0x02d3;
    L_0x0047:
        r8 = r8.obj;
        r8 = (com.google.android.gms.common.api.internal.GoogleApiManager.zab) r8;
        r0 = r7.zaih;
        r1 = r8.zaja;
        r0 = r0.containsKey(r1);
        if (r0 == 0) goto L_0x02d3;
    L_0x0057:
        r0 = r7.zaih;
        r1 = r8.zaja;
        r0 = r0.get(r1);
        r0 = (com.google.android.gms.common.api.internal.GoogleApiManager.zaa) r0;
        r0.zaa(r8);
        goto L_0x02d3;
    L_0x0068:
        r8 = r8.obj;
        r8 = (com.google.android.gms.common.api.internal.zaaf) r8;
        r0 = r8.zak();
        r1 = r7.zaih;
        r1 = r1.containsKey(r0);
        if (r1 != 0) goto L_0x0085;
    L_0x0078:
        r8 = r8.zaal();
        r0 = java.lang.Boolean.valueOf(r5);
        r8.setResult(r0);
        goto L_0x02d3;
    L_0x0085:
        r1 = r7.zaih;
        r0 = r1.get(r0);
        r0 = (com.google.android.gms.common.api.internal.GoogleApiManager.zaa) r0;
        r0 = r0.zac(false);
        r8 = r8.zaal();
        r0 = java.lang.Boolean.valueOf(r0);
        r8.setResult(r0);
        goto L_0x02d3;
    L_0x009e:
        r0 = r7.zaih;
        r1 = r8.obj;
        r0 = r0.containsKey(r1);
        if (r0 == 0) goto L_0x02d3;
    L_0x00a8:
        r0 = r7.zaih;
        r8 = r8.obj;
        r8 = r0.get(r8);
        r8 = (com.google.android.gms.common.api.internal.GoogleApiManager.zaa) r8;
        r8.zabp();
        goto L_0x02d3;
    L_0x00b7:
        r0 = r7.zaih;
        r1 = r8.obj;
        r0 = r0.containsKey(r1);
        if (r0 == 0) goto L_0x02d3;
    L_0x00c1:
        r0 = r7.zaih;
        r8 = r8.obj;
        r8 = r0.get(r8);
        r8 = (com.google.android.gms.common.api.internal.GoogleApiManager.zaa) r8;
        r8.zaav();
        goto L_0x02d3;
    L_0x00d0:
        r8 = r7.zaik;
        r8 = r8.iterator();
    L_0x00d6:
        r0 = r8.hasNext();
        if (r0 == 0) goto L_0x00ee;
    L_0x00dc:
        r0 = r8.next();
        r0 = (com.google.android.gms.common.api.internal.zai) r0;
        r1 = r7.zaih;
        r0 = r1.remove(r0);
        r0 = (com.google.android.gms.common.api.internal.GoogleApiManager.zaa) r0;
        r0.zabj();
        goto L_0x00d6;
    L_0x00ee:
        r8 = r7.zaik;
        r8.clear();
        goto L_0x02d3;
    L_0x00f5:
        r0 = r7.zaih;
        r1 = r8.obj;
        r0 = r0.containsKey(r1);
        if (r0 == 0) goto L_0x02d3;
    L_0x00ff:
        r0 = r7.zaih;
        r8 = r8.obj;
        r8 = r0.get(r8);
        r8 = (com.google.android.gms.common.api.internal.GoogleApiManager.zaa) r8;
        r8.resume();
        goto L_0x02d3;
    L_0x010e:
        r8 = r8.obj;
        r8 = (com.google.android.gms.common.api.GoogleApi) r8;
        r7.zab(r8);
        goto L_0x02d3;
    L_0x0117:
        r8 = com.google.android.gms.common.util.PlatformVersion.isAtLeastIceCreamSandwich();
        if (r8 == 0) goto L_0x02d3;
    L_0x011d:
        r8 = r7.zaic;
        r8 = r8.getApplicationContext();
        r8 = r8 instanceof android.app.Application;
        if (r8 == 0) goto L_0x02d3;
    L_0x0127:
        r8 = r7.zaic;
        r8 = r8.getApplicationContext();
        r8 = (android.app.Application) r8;
        com.google.android.gms.common.api.internal.BackgroundDetector.initialize(r8);
        r8 = com.google.android.gms.common.api.internal.BackgroundDetector.getInstance();
        r0 = new com.google.android.gms.common.api.internal.zabi;
        r0.<init>(r7);
        r8.addListener(r0);
        r8 = com.google.android.gms.common.api.internal.BackgroundDetector.getInstance();
        r8 = r8.readCurrentStateIfPossible(r3);
        if (r8 != 0) goto L_0x02d3;
    L_0x0148:
        r7.zaia = r1;
        goto L_0x02d3;
    L_0x014c:
        r0 = r8.arg1;
        r8 = r8.obj;
        r8 = (com.google.android.gms.common.ConnectionResult) r8;
        r1 = r7.zaih;
        r1 = r1.values();
        r1 = r1.iterator();
    L_0x015c:
        r2 = r1.hasNext();
        if (r2 == 0) goto L_0x016f;
    L_0x0162:
        r2 = r1.next();
        r2 = (com.google.android.gms.common.api.internal.GoogleApiManager.zaa) r2;
        r5 = r2.getInstanceId();
        if (r5 != r0) goto L_0x015c;
    L_0x016e:
        goto L_0x0170;
    L_0x016f:
        r2 = r4;
    L_0x0170:
        if (r2 == 0) goto L_0x01b9;
    L_0x0172:
        r0 = new com.google.android.gms.common.api.Status;
        r1 = 17;
        r4 = r7.zaid;
        r5 = r8.getErrorCode();
        r4 = r4.getErrorString(r5);
        r8 = r8.getErrorMessage();
        r5 = 69;
        r6 = java.lang.String.valueOf(r4);
        r6 = r6.length();
        r5 = r5 + r6;
        r6 = java.lang.String.valueOf(r8);
        r6 = r6.length();
        r5 = r5 + r6;
        r6 = new java.lang.StringBuilder;
        r6.<init>(r5);
        r5 = "Error resolution was canceled by the user, original error message: ";
        r6.append(r5);
        r6.append(r4);
        r4 = ": ";
        r6.append(r4);
        r6.append(r8);
        r8 = r6.toString();
        r0.<init>(r1, r8);
        r2.zac(r0);
        goto L_0x02d3;
    L_0x01b9:
        r8 = "GoogleApiManager";
        r1 = 76;
        r2 = new java.lang.StringBuilder;
        r2.<init>(r1);
        r1 = "Could not find API instance ";
        r2.append(r1);
        r2.append(r0);
        r0 = " while trying to fail enqueued calls.";
        r2.append(r0);
        r0 = r2.toString();
        r1 = new java.lang.Exception;
        r1.<init>();
        android.util.Log.wtf(r8, r0, r1);
        goto L_0x02d3;
    L_0x01dd:
        r8 = r8.obj;
        r8 = (com.google.android.gms.common.api.internal.zabv) r8;
        r0 = r7.zaih;
        r1 = r8.zajs;
        r1 = r1.zak();
        r0 = r0.get(r1);
        r0 = (com.google.android.gms.common.api.internal.GoogleApiManager.zaa) r0;
        if (r0 != 0) goto L_0x0204;
    L_0x01f1:
        r0 = r8.zajs;
        r7.zab(r0);
        r0 = r7.zaih;
        r1 = r8.zajs;
        r1 = r1.zak();
        r0 = r0.get(r1);
        r0 = (com.google.android.gms.common.api.internal.GoogleApiManager.zaa) r0;
    L_0x0204:
        r1 = r0.requiresSignIn();
        if (r1 == 0) goto L_0x0220;
    L_0x020a:
        r1 = r7.zaig;
        r1 = r1.get();
        r2 = r8.zajr;
        if (r1 == r2) goto L_0x0220;
    L_0x0214:
        r8 = r8.zajq;
        r1 = zahw;
        r8.zaa(r1);
        r0.zabj();
        goto L_0x02d3;
    L_0x0220:
        r8 = r8.zajq;
        r0.zaa(r8);
        goto L_0x02d3;
    L_0x0227:
        r8 = r7.zaih;
        r8 = r8.values();
        r8 = r8.iterator();
    L_0x0231:
        r0 = r8.hasNext();
        if (r0 == 0) goto L_0x02d3;
    L_0x0237:
        r0 = r8.next();
        r0 = (com.google.android.gms.common.api.internal.GoogleApiManager.zaa) r0;
        r0.zabl();
        r0.connect();
        goto L_0x0231;
    L_0x0244:
        r8 = r8.obj;
        r8 = (com.google.android.gms.common.api.internal.zak) r8;
        r0 = r8.zap();
        r0 = r0.iterator();
    L_0x0250:
        r1 = r0.hasNext();
        if (r1 == 0) goto L_0x02d3;
    L_0x0256:
        r1 = r0.next();
        r1 = (com.google.android.gms.common.api.internal.zai) r1;
        r2 = r7.zaih;
        r2 = r2.get(r1);
        r2 = (com.google.android.gms.common.api.internal.GoogleApiManager.zaa) r2;
        if (r2 != 0) goto L_0x0271;
    L_0x0266:
        r0 = new com.google.android.gms.common.ConnectionResult;
        r2 = 13;
        r0.<init>(r2);
        r8.zaa(r1, r0, r4);
        goto L_0x02d3;
    L_0x0271:
        r5 = r2.isConnected();
        if (r5 == 0) goto L_0x0285;
    L_0x0277:
        r5 = com.google.android.gms.common.ConnectionResult.RESULT_SUCCESS;
        r2 = r2.zaab();
        r2 = r2.getEndpointPackageName();
        r8.zaa(r1, r5, r2);
        goto L_0x0250;
    L_0x0285:
        r5 = r2.zabm();
        if (r5 == 0) goto L_0x0293;
    L_0x028b:
        r2 = r2.zabm();
        r8.zaa(r1, r2, r4);
        goto L_0x0250;
    L_0x0293:
        r2.zaa(r8);
        r2.connect();
        goto L_0x0250;
    L_0x029a:
        r8 = r8.obj;
        r8 = (java.lang.Boolean) r8;
        r8 = r8.booleanValue();
        if (r8 == 0) goto L_0x02a6;
    L_0x02a4:
        r1 = 10000; // 0x2710 float:1.4013E-41 double:4.9407E-320;
    L_0x02a6:
        r7.zaia = r1;
        r8 = r7.handler;
        r0 = 12;
        r8.removeMessages(r0);
        r8 = r7.zaih;
        r8 = r8.keySet();
        r8 = r8.iterator();
    L_0x02b9:
        r1 = r8.hasNext();
        if (r1 == 0) goto L_0x02d3;
    L_0x02bf:
        r1 = r8.next();
        r1 = (com.google.android.gms.common.api.internal.zai) r1;
        r2 = r7.handler;
        r4 = r7.handler;
        r1 = r4.obtainMessage(r0, r1);
        r4 = r7.zaia;
        r2.sendMessageDelayed(r1, r4);
        goto L_0x02b9;
    L_0x02d3:
        return r3;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.api.internal.GoogleApiManager.handleMessage(android.os.Message):boolean");
    }

    /* Access modifiers changed, original: final */
    public final PendingIntent zaa(zai<?> zai, int i) {
        zaa zaa = (zaa) this.zaih.get(zai);
        if (zaa == null) {
            return null;
        }
        zad zabq = zaa.zabq();
        if (zabq == null) {
            return null;
        }
        return PendingIntent.getActivity(this.zaic, i, zabq.getSignInIntent(), 134217728);
    }

    /* Access modifiers changed, original: final */
    public final boolean zac(ConnectionResult connectionResult, int i) {
        return this.zaid.zaa(this.zaic, connectionResult, i);
    }

    public final void zaa(ConnectionResult connectionResult, int i) {
        if (!zac(connectionResult, i)) {
            this.handler.sendMessage(this.handler.obtainMessage(5, i, 0, connectionResult));
        }
    }
}
