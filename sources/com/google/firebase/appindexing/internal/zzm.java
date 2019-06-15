package com.google.firebase.appindexing.internal;

import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.appindexing.FirebaseAppIndex;
import com.google.firebase.appindexing.FirebaseAppIndexingException;

@VisibleForTesting
final class zzm extends TaskApiCall<zzd, Void> {
    final /* synthetic */ zzk zzfb;

    zzm(zzk zzk) {
        this.zzfb = zzk;
    }

    /* Access modifiers changed, original: protected|final|synthetic */
    public final /* synthetic */ void doExecute(AnyClient anyClient, TaskCompletionSource taskCompletionSource) throws RemoteException {
        int i;
        zzf zza = ((zzs) ((zzd) anyClient).getService()).zza(new zzn(this, taskCompletionSource), this.zzfb.zzex);
        if (zza == null) {
            i = 2;
        } else {
            i = zza.status;
        }
        boolean z = false;
        boolean z2 = true;
        zzk zzk = null;
        String str;
        if (i == 3) {
            str = "Queue was full. API call will be retried.";
            if (zzu.isLoggable(4)) {
                Log.i(FirebaseAppIndex.APP_INDEXING_API_TAG, str);
            }
            if (taskCompletionSource.trySetResult(null)) {
                synchronized (this.zzfb.zzez.zzev) {
                    if (this.zzfb.zzez.zzew == 0) {
                        zzk zzk2 = (zzk) this.zzfb.zzez.zzev.peek();
                        if (zzk2 == this.zzfb) {
                            z = true;
                        }
                        Preconditions.checkState(z);
                        zzk = zzk2;
                    } else {
                        this.zzfb.zzez.zzew = 2;
                    }
                }
            }
        } else {
            if (i != 1) {
                StringBuilder stringBuilder = new StringBuilder(41);
                stringBuilder.append("API call failed. Status code: ");
                stringBuilder.append(i);
                str = stringBuilder.toString();
                if (zzu.isLoggable(6)) {
                    Log.e(FirebaseAppIndex.APP_INDEXING_API_TAG, str);
                }
                if (taskCompletionSource.trySetResult(null)) {
                    this.zzfb.zzey.setException(new FirebaseAppIndexingException("Indexing error."));
                }
            }
            synchronized (this.zzfb.zzez.zzev) {
                if (((zzk) this.zzfb.zzez.zzev.poll()) != this.zzfb) {
                    z2 = false;
                }
                Preconditions.checkState(z2);
                zzk = (zzk) this.zzfb.zzez.zzev.peek();
                this.zzfb.zzez.zzew = 0;
            }
        }
        if (zzk != null) {
            zzk.execute();
        }
    }
}
