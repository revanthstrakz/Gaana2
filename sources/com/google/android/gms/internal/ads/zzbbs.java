package com.google.android.gms.internal.ads;

import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;

final /* synthetic */ class zzbbs implements Runnable {
    private final zzbcl zzcak;
    private final zzbbm zzepb;
    private final zzbcb zzepc;

    zzbbs(zzbcl zzbcl, zzbbm zzbbm, zzbcb zzbcb) {
        this.zzcak = zzbcl;
        this.zzepb = zzbbm;
        this.zzepc = zzbcb;
    }

    public final void run() {
        zzbcl zzbcl = this.zzcak;
        try {
            zzbcl.set(this.zzepb.apply(this.zzepc.get()));
        } catch (CancellationException unused) {
            zzbcl.cancel(true);
        } catch (ExecutionException e) {
            Throwable e2 = e;
            Throwable cause = e2.getCause();
            if (cause != null) {
                e2 = cause;
            }
            zzbcl.setException(e2);
        } catch (InterruptedException e3) {
            Thread.currentThread().interrupt();
            zzbcl.setException(e3);
        } catch (Exception e4) {
            zzbcl.setException(e4);
        }
    }
}
