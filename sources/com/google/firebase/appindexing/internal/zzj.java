package com.google.firebase.appindexing.internal;

import android.os.Handler;
import android.support.annotation.NonNull;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.libs.punchclock.threads.TracingHandler;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.Executor;

@VisibleForTesting
final class zzj implements OnCompleteListener<Void>, Executor {
    @NonNull
    private final Handler handler;
    @NonNull
    private final GoogleApi<?> zzet;
    private final Queue<zzk> zzev = new ArrayDeque();
    private int zzew = 0;

    public zzj(@NonNull GoogleApi<?> googleApi) {
        this.zzet = googleApi;
        this.handler = new TracingHandler(googleApi.getLooper());
    }

    public final Task<Void> zzb(zzx zzx) {
        boolean isEmpty;
        zzk zzk = new zzk(this, zzx);
        Task task = zzk.getTask();
        task.addOnCompleteListener((Executor) this, (OnCompleteListener) this);
        synchronized (this.zzev) {
            isEmpty = this.zzev.isEmpty();
            this.zzev.add(zzk);
        }
        if (isEmpty) {
            zzk.execute();
        }
        return task;
    }

    public final void onComplete(@NonNull Task<Void> task) {
        zzk zzk;
        synchronized (this.zzev) {
            if (this.zzew == 2) {
                zzk = (zzk) this.zzev.peek();
                Preconditions.checkState(zzk != null);
            } else {
                zzk = null;
            }
            this.zzew = 0;
        }
        if (zzk != null) {
            zzk.execute();
        }
    }

    public final void execute(Runnable runnable) {
        this.handler.post(runnable);
    }
}
