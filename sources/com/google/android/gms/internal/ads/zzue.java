package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Binder;
import android.support.annotation.Nullable;
import com.google.android.gms.ads.internal.zzbv;
import java.io.InputStream;
import java.util.concurrent.Future;

@zzark
public final class zzue {
    private final Context mContext;
    private final Object mLock = new Object();
    @Nullable
    private zztx zzbzr;
    private boolean zzcac;

    zzue(Context context) {
        this.mContext = context;
    }

    /* Access modifiers changed, original: final */
    public final Future<InputStream> zzb(zzty zzty) {
        zzuf zzuf = new zzuf(this);
        zzug zzug = new zzug(this, zzty, zzuf);
        zzuk zzuk = new zzuk(this, zzuf);
        synchronized (this.mLock) {
            this.zzbzr = new zztx(this.mContext, zzbv.zzlv().zzaak(), zzug, zzuk);
            this.zzbzr.checkAvailabilityAndConnect();
        }
        return zzuf;
    }

    private final void disconnect() {
        synchronized (this.mLock) {
            if (this.zzbzr == null) {
                return;
            }
            this.zzbzr.disconnect();
            this.zzbzr = null;
            Binder.flushPendingCommands();
        }
    }
}
