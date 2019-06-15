package com.google.android.gms.internal.cast;

import android.os.Handler;
import com.google.android.gms.cast.ApplicationMetadata;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.concurrent.atomic.AtomicReference;

@VisibleForTesting
final class zzdf extends zzds {
    private final Handler handler;
    private final AtomicReference<zzdd> zzyx;

    public zzdf(zzdd zzdd) {
        this.zzyx = new AtomicReference(zzdd);
        this.handler = new zzez(zzdd.getLooper());
    }

    public final zzdd zzex() {
        zzdd zzdd = (zzdd) this.zzyx.getAndSet(null);
        if (zzdd == null) {
            return null;
        }
        zzdd.zzet();
        return zzdd;
    }

    public final boolean isDisposed() {
        return this.zzyx.get() == null;
    }

    public final void zzs(int i) {
        zzdd zzex = zzex();
        if (zzex != null) {
            zzdd.zzbf.d("ICastDeviceControllerListener.onDisconnected: %d", Integer.valueOf(i));
            if (i != 0) {
                zzex.triggerConnectionSuspended(2);
            }
        }
    }

    public final void zza(ApplicationMetadata applicationMetadata, String str, String str2, boolean z) {
        zzdd zzdd = (zzdd) this.zzyx.get();
        if (zzdd != null) {
            zzdd.zzxz = applicationMetadata;
            zzdd.zzyl = applicationMetadata.getApplicationId();
            zzdd.zzym = str2;
            zzdd.zzyd = str;
            synchronized (zzdd.zzyr) {
                if (zzdd.zzyp != null) {
                    zzdd.zzyp.setResult(new zzde(new Status(0), applicationMetadata, str, str2, z));
                    zzdd.zzyp = null;
                }
            }
        }
    }

    public final void zzi(int i) {
        zzdd zzdd = (zzdd) this.zzyx.get();
        if (zzdd != null) {
            zzdd.zzq(i);
        }
    }

    public final void zzt(int i) {
        zzdd zzdd = (zzdd) this.zzyx.get();
        if (zzdd != null) {
            zzdd.zzr(i);
        }
    }

    public final void zzu(int i) {
        zzdd zzdd = (zzdd) this.zzyx.get();
        if (zzdd != null) {
            zzdd.zzr(i);
        }
    }

    public final void onApplicationDisconnected(int i) {
        zzdd zzdd = (zzdd) this.zzyx.get();
        if (zzdd != null) {
            zzdd.zzyl = null;
            zzdd.zzym = null;
            zzdd.zzr(i);
            if (zzdd.zzak != null) {
                this.handler.post(new zzdg(this, zzdd, i));
            }
        }
    }

    public final void zza(String str, double d, boolean z) {
        zzdd.zzbf.d("Deprecated callback: \"onStatusreceived\"", new Object[0]);
    }

    public final void zzb(zzdl zzdl) {
        zzdd zzdd = (zzdd) this.zzyx.get();
        if (zzdd != null) {
            zzdd.zzbf.d("onDeviceStatusChanged", new Object[0]);
            this.handler.post(new zzdh(this, zzdd, zzdl));
        }
    }

    public final void zzb(zzct zzct) {
        zzdd zzdd = (zzdd) this.zzyx.get();
        if (zzdd != null) {
            zzdd.zzbf.d("onApplicationStatusChanged", new Object[0]);
            this.handler.post(new zzdi(this, zzdd, zzct));
        }
    }

    public final void zzb(String str, String str2) {
        zzdd zzdd = (zzdd) this.zzyx.get();
        if (zzdd != null) {
            zzdd.zzbf.d("Receive (type=text, ns=%s) %s", str, str2);
            this.handler.post(new zzdj(this, zzdd, str, str2));
        }
    }

    public final void zza(String str, byte[] bArr) {
        if (((zzdd) this.zzyx.get()) != null) {
            zzdd.zzbf.d("IGNORING: Receive (type=binary, ns=%s) <%d bytes>", str, Integer.valueOf(bArr.length));
        }
    }

    public final void zza(String str, long j, int i) {
        zzdd zzdd = (zzdd) this.zzyx.get();
        if (zzdd != null) {
            zzdd.zzb(j, i);
        }
    }

    public final void zza(String str, long j) {
        zzdd zzdd = (zzdd) this.zzyx.get();
        if (zzdd != null) {
            zzdd.zzb(j, 0);
        }
    }
}
