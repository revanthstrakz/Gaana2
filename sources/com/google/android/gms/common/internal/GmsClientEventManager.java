package com.google.android.gms.common.internal;

import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.base.zal;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public final class GmsClientEventManager implements Callback {
    private final Handler mHandler;
    private final Object mLock = new Object();
    private final GmsClientEventState zaok;
    private final ArrayList<ConnectionCallbacks> zaol = new ArrayList();
    @VisibleForTesting
    private final ArrayList<ConnectionCallbacks> zaom = new ArrayList();
    private final ArrayList<OnConnectionFailedListener> zaon = new ArrayList();
    private volatile boolean zaoo = false;
    private final AtomicInteger zaop = new AtomicInteger(0);
    private boolean zaoq = false;

    @VisibleForTesting
    public interface GmsClientEventState {
        Bundle getConnectionHint();

        boolean isConnected();
    }

    public GmsClientEventManager(Looper looper, GmsClientEventState gmsClientEventState) {
        this.zaok = gmsClientEventState;
        this.mHandler = new zal(looper, this);
    }

    public final void disableCallbacks() {
        this.zaoo = false;
        this.zaop.incrementAndGet();
    }

    public final void enableCallbacks() {
        this.zaoo = true;
    }

    /* Access modifiers changed, original: protected|final */
    @VisibleForTesting
    public final void onConnectionSuccess() {
        synchronized (this.mLock) {
            onConnectionSuccess(this.zaok.getConnectionHint());
        }
    }

    @VisibleForTesting
    public final void onConnectionSuccess(Bundle bundle) {
        boolean z = true;
        Preconditions.checkState(Looper.myLooper() == this.mHandler.getLooper(), "onConnectionSuccess must only be called on the Handler thread");
        synchronized (this.mLock) {
            Preconditions.checkState(this.zaoq ^ 1);
            this.mHandler.removeMessages(1);
            this.zaoq = true;
            if (this.zaom.size() != 0) {
                z = false;
            }
            Preconditions.checkState(z);
            ArrayList arrayList = new ArrayList(this.zaol);
            int i = this.zaop.get();
            arrayList = arrayList;
            int size = arrayList.size();
            int i2 = 0;
            while (i2 < size) {
                Object obj = arrayList.get(i2);
                i2++;
                ConnectionCallbacks connectionCallbacks = (ConnectionCallbacks) obj;
                if (!this.zaoo || !this.zaok.isConnected() || this.zaop.get() != i) {
                    break;
                } else if (!this.zaom.contains(connectionCallbacks)) {
                    connectionCallbacks.onConnected(bundle);
                }
            }
            this.zaom.clear();
            this.zaoq = false;
        }
    }

    @VisibleForTesting
    public final void onUnintentionalDisconnection(int i) {
        Preconditions.checkState(Looper.myLooper() == this.mHandler.getLooper(), "onUnintentionalDisconnection must only be called on the Handler thread");
        this.mHandler.removeMessages(1);
        synchronized (this.mLock) {
            this.zaoq = true;
            ArrayList arrayList = new ArrayList(this.zaol);
            int i2 = this.zaop.get();
            arrayList = arrayList;
            int size = arrayList.size();
            int i3 = 0;
            while (i3 < size) {
                Object obj = arrayList.get(i3);
                i3++;
                ConnectionCallbacks connectionCallbacks = (ConnectionCallbacks) obj;
                if (!this.zaoo || this.zaop.get() != i2) {
                    break;
                } else if (this.zaol.contains(connectionCallbacks)) {
                    connectionCallbacks.onConnectionSuspended(i);
                }
            }
            this.zaom.clear();
            this.zaoq = false;
        }
    }

    /* JADX WARNING: Missing block: B:18:0x0055, code skipped:
            return;
     */
    @com.google.android.gms.common.util.VisibleForTesting
    public final void onConnectionFailure(com.google.android.gms.common.ConnectionResult r8) {
        /*
        r7 = this;
        r0 = android.os.Looper.myLooper();
        r1 = r7.mHandler;
        r1 = r1.getLooper();
        r2 = 0;
        r3 = 1;
        if (r0 != r1) goto L_0x0010;
    L_0x000e:
        r0 = r3;
        goto L_0x0011;
    L_0x0010:
        r0 = r2;
    L_0x0011:
        r1 = "onConnectionFailure must only be called on the Handler thread";
        com.google.android.gms.common.internal.Preconditions.checkState(r0, r1);
        r0 = r7.mHandler;
        r0.removeMessages(r3);
        r0 = r7.mLock;
        monitor-enter(r0);
        r1 = new java.util.ArrayList;	 Catch:{ all -> 0x0058 }
        r3 = r7.zaon;	 Catch:{ all -> 0x0058 }
        r1.<init>(r3);	 Catch:{ all -> 0x0058 }
        r3 = r7.zaop;	 Catch:{ all -> 0x0058 }
        r3 = r3.get();	 Catch:{ all -> 0x0058 }
        r1 = (java.util.ArrayList) r1;	 Catch:{ all -> 0x0058 }
        r4 = r1.size();	 Catch:{ all -> 0x0058 }
    L_0x0031:
        if (r2 >= r4) goto L_0x0056;
    L_0x0033:
        r5 = r1.get(r2);	 Catch:{ all -> 0x0058 }
        r2 = r2 + 1;
        r5 = (com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener) r5;	 Catch:{ all -> 0x0058 }
        r6 = r7.zaoo;	 Catch:{ all -> 0x0058 }
        if (r6 == 0) goto L_0x0054;
    L_0x003f:
        r6 = r7.zaop;	 Catch:{ all -> 0x0058 }
        r6 = r6.get();	 Catch:{ all -> 0x0058 }
        if (r6 == r3) goto L_0x0048;
    L_0x0047:
        goto L_0x0054;
    L_0x0048:
        r6 = r7.zaon;	 Catch:{ all -> 0x0058 }
        r6 = r6.contains(r5);	 Catch:{ all -> 0x0058 }
        if (r6 == 0) goto L_0x0031;
    L_0x0050:
        r5.onConnectionFailed(r8);	 Catch:{ all -> 0x0058 }
        goto L_0x0031;
    L_0x0054:
        monitor-exit(r0);	 Catch:{ all -> 0x0058 }
        return;
    L_0x0056:
        monitor-exit(r0);	 Catch:{ all -> 0x0058 }
        return;
    L_0x0058:
        r8 = move-exception;
        monitor-exit(r0);	 Catch:{ all -> 0x0058 }
        throw r8;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.internal.GmsClientEventManager.onConnectionFailure(com.google.android.gms.common.ConnectionResult):void");
    }

    public final void registerConnectionCallbacks(ConnectionCallbacks connectionCallbacks) {
        Preconditions.checkNotNull(connectionCallbacks);
        synchronized (this.mLock) {
            if (this.zaol.contains(connectionCallbacks)) {
                String valueOf = String.valueOf(connectionCallbacks);
                StringBuilder stringBuilder = new StringBuilder(62 + String.valueOf(valueOf).length());
                stringBuilder.append("registerConnectionCallbacks(): listener ");
                stringBuilder.append(valueOf);
                stringBuilder.append(" is already registered");
                Log.w("GmsClientEvents", stringBuilder.toString());
            } else {
                this.zaol.add(connectionCallbacks);
            }
        }
        if (this.zaok.isConnected()) {
            this.mHandler.sendMessage(this.mHandler.obtainMessage(1, connectionCallbacks));
        }
    }

    public final boolean isConnectionCallbacksRegistered(ConnectionCallbacks connectionCallbacks) {
        boolean contains;
        Preconditions.checkNotNull(connectionCallbacks);
        synchronized (this.mLock) {
            contains = this.zaol.contains(connectionCallbacks);
        }
        return contains;
    }

    public final void unregisterConnectionCallbacks(ConnectionCallbacks connectionCallbacks) {
        Preconditions.checkNotNull(connectionCallbacks);
        synchronized (this.mLock) {
            if (!this.zaol.remove(connectionCallbacks)) {
                String valueOf = String.valueOf(connectionCallbacks);
                StringBuilder stringBuilder = new StringBuilder(52 + String.valueOf(valueOf).length());
                stringBuilder.append("unregisterConnectionCallbacks(): listener ");
                stringBuilder.append(valueOf);
                stringBuilder.append(" not found");
                Log.w("GmsClientEvents", stringBuilder.toString());
            } else if (this.zaoq) {
                this.zaom.add(connectionCallbacks);
            }
        }
    }

    public final void registerConnectionFailedListener(OnConnectionFailedListener onConnectionFailedListener) {
        Preconditions.checkNotNull(onConnectionFailedListener);
        synchronized (this.mLock) {
            if (this.zaon.contains(onConnectionFailedListener)) {
                String valueOf = String.valueOf(onConnectionFailedListener);
                StringBuilder stringBuilder = new StringBuilder(67 + String.valueOf(valueOf).length());
                stringBuilder.append("registerConnectionFailedListener(): listener ");
                stringBuilder.append(valueOf);
                stringBuilder.append(" is already registered");
                Log.w("GmsClientEvents", stringBuilder.toString());
            } else {
                this.zaon.add(onConnectionFailedListener);
            }
        }
    }

    public final boolean isConnectionFailedListenerRegistered(OnConnectionFailedListener onConnectionFailedListener) {
        boolean contains;
        Preconditions.checkNotNull(onConnectionFailedListener);
        synchronized (this.mLock) {
            contains = this.zaon.contains(onConnectionFailedListener);
        }
        return contains;
    }

    public final void unregisterConnectionFailedListener(OnConnectionFailedListener onConnectionFailedListener) {
        Preconditions.checkNotNull(onConnectionFailedListener);
        synchronized (this.mLock) {
            if (!this.zaon.remove(onConnectionFailedListener)) {
                String valueOf = String.valueOf(onConnectionFailedListener);
                StringBuilder stringBuilder = new StringBuilder(57 + String.valueOf(valueOf).length());
                stringBuilder.append("unregisterConnectionFailedListener(): listener ");
                stringBuilder.append(valueOf);
                stringBuilder.append(" not found");
                Log.w("GmsClientEvents", stringBuilder.toString());
            }
        }
    }

    public final boolean handleMessage(Message message) {
        if (message.what == 1) {
            ConnectionCallbacks connectionCallbacks = (ConnectionCallbacks) message.obj;
            synchronized (this.mLock) {
                if (this.zaoo && this.zaok.isConnected() && this.zaol.contains(connectionCallbacks)) {
                    connectionCallbacks.onConnected(this.zaok.getConnectionHint());
                }
            }
            return true;
        }
        int i = message.what;
        StringBuilder stringBuilder = new StringBuilder(45);
        stringBuilder.append("Don't know how to handle message: ");
        stringBuilder.append(i);
        Log.wtf("GmsClientEvents", stringBuilder.toString(), new Exception());
        return false;
    }

    public final boolean areCallbacksEnabled() {
        return this.zaoo;
    }
}
