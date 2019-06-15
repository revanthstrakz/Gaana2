package com.google.android.gms.common.api.internal;

import android.os.Looper;
import android.support.annotation.NonNull;
import android.util.Log;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Releasable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.ResultCallbacks;
import com.google.android.gms.common.api.ResultTransform;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.TransformedResult;
import com.google.android.gms.common.internal.Preconditions;
import java.lang.ref.WeakReference;

public final class zacm<R extends Result> extends TransformedResult<R> implements ResultCallback<R> {
    private final Object zadn = new Object();
    private final WeakReference<GoogleApiClient> zadp;
    private ResultTransform<? super R, ? extends Result> zakn = null;
    private zacm<? extends Result> zako = null;
    private volatile ResultCallbacks<? super R> zakp = null;
    private PendingResult<R> zakq = null;
    private Status zakr = null;
    private final zaco zaks;
    private boolean zakt = false;

    public zacm(WeakReference<GoogleApiClient> weakReference) {
        Preconditions.checkNotNull(weakReference, "GoogleApiClient reference must not be null");
        this.zadp = weakReference;
        GoogleApiClient googleApiClient = (GoogleApiClient) this.zadp.get();
        this.zaks = new zaco(this, googleApiClient != null ? googleApiClient.getLooper() : Looper.getMainLooper());
    }

    @NonNull
    public final <S extends Result> TransformedResult<S> then(@NonNull ResultTransform<? super R, ? extends S> resultTransform) {
        zacm zacm;
        synchronized (this.zadn) {
            boolean z = false;
            Preconditions.checkState(this.zakn == null, "Cannot call then() twice.");
            if (this.zakp == null) {
                z = true;
            }
            Preconditions.checkState(z, "Cannot call then() and andFinally() on the same TransformedResult.");
            this.zakn = resultTransform;
            zacm = new zacm(this.zadp);
            this.zako = zacm;
            zabu();
        }
        return zacm;
    }

    public final void andFinally(@NonNull ResultCallbacks<? super R> resultCallbacks) {
        synchronized (this.zadn) {
            boolean z = false;
            Preconditions.checkState(this.zakp == null, "Cannot call andFinally() twice.");
            if (this.zakn == null) {
                z = true;
            }
            Preconditions.checkState(z, "Cannot call then() and andFinally() on the same TransformedResult.");
            this.zakp = resultCallbacks;
            zabu();
        }
    }

    public final void onResult(R r) {
        synchronized (this.zadn) {
            if (!r.getStatus().isSuccess()) {
                zad(r.getStatus());
                zab(r);
            } else if (this.zakn != null) {
                zacc.zabb().submit(new zacn(this, r));
            } else if (zabw()) {
                this.zakp.onSuccess(r);
            }
        }
    }

    public final void zaa(PendingResult<?> pendingResult) {
        synchronized (this.zadn) {
            this.zakq = pendingResult;
            zabu();
        }
    }

    private final void zabu() {
        if (this.zakn != null || this.zakp != null) {
            GoogleApiClient googleApiClient = (GoogleApiClient) this.zadp.get();
            if (!(this.zakt || this.zakn == null || googleApiClient == null)) {
                googleApiClient.zaa(this);
                this.zakt = true;
            }
            if (this.zakr != null) {
                zae(this.zakr);
                return;
            }
            if (this.zakq != null) {
                this.zakq.setResultCallback(this);
            }
        }
    }

    private final void zad(Status status) {
        synchronized (this.zadn) {
            this.zakr = status;
            zae(this.zakr);
        }
    }

    private final void zae(Status status) {
        synchronized (this.zadn) {
            if (this.zakn != null) {
                status = this.zakn.onFailure(status);
                Preconditions.checkNotNull(status, "onFailure must not return null");
                this.zako.zad(status);
            } else if (zabw()) {
                this.zakp.onFailure(status);
            }
        }
    }

    /* Access modifiers changed, original: final */
    public final void zabv() {
        this.zakp = null;
    }

    private final boolean zabw() {
        return (this.zakp == null || ((GoogleApiClient) this.zadp.get()) == null) ? false : true;
    }

    private static void zab(Result result) {
        if (result instanceof Releasable) {
            try {
                ((Releasable) result).release();
            } catch (RuntimeException e) {
                String valueOf = String.valueOf(result);
                StringBuilder stringBuilder = new StringBuilder(18 + String.valueOf(valueOf).length());
                stringBuilder.append("Unable to release ");
                stringBuilder.append(valueOf);
                Log.w("TransformedResultImpl", stringBuilder.toString(), e);
            }
        }
    }
}
