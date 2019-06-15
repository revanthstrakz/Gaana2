package com.google.firebase.appindexing.internal;

import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;

@VisibleForTesting
final class zzk {
    private final zzx zzex;
    private final TaskCompletionSource<Void> zzey = new TaskCompletionSource();
    final /* synthetic */ zzj zzez;

    public zzk(zzj zzj, zzx zzx) {
        this.zzez = zzj;
        this.zzex = zzx;
    }

    public final Task<Void> getTask() {
        return this.zzey.getTask();
    }

    public final void execute() {
        synchronized (this.zzez.zzev) {
            Preconditions.checkState(this.zzez.zzew == 0);
            this.zzez.zzew = 1;
        }
        this.zzez.zzet.doWrite(new zzm(this)).addOnFailureListener(this.zzez, new zzl(this));
    }
}
