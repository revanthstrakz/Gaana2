package com.google.android.gms.internal.ads;

import android.net.Uri;
import android.os.Handler;
import com.google.android.exoplayer2.C;
import java.io.IOException;

public final class zzlk implements zzlo, zzlp {
    private final Uri uri;
    private final int zzawn = -1;
    private final zzll zzawo;
    private zzlp zzawp;
    private final String zzawr;
    private final zzow zzaxv;
    private final zzic zzaxw;
    private final int zzaxx;
    private boolean zzaxy;
    private final Handler zzwx;
    private final zzge zzxb;
    private zzgc zzxi;

    public zzlk(Uri uri, zzow zzow, zzic zzic, int i, Handler handler, zzll zzll, String str, int i2) {
        this.uri = uri;
        this.zzaxv = zzow;
        this.zzaxw = zzic;
        this.zzwx = handler;
        this.zzawo = zzll;
        this.zzawr = null;
        this.zzaxx = i2;
        this.zzxb = new zzge();
    }

    public final void zzfg() throws IOException {
    }

    public final void zza(zzfg zzfg, boolean z, zzlp zzlp) {
        this.zzawp = zzlp;
        this.zzxi = new zzly(C.TIME_UNSET, false);
        zzlp.zzb(this.zzxi, null);
    }

    public final zzlm zza(int i, zzot zzot) {
        zzpo.checkArgument(i == 0);
        return new zzlc(this.uri, this.zzaxv.zzgs(), this.zzaxw.zzdz(), this.zzawn, this.zzwx, this.zzawo, this, zzot, null, this.zzaxx);
    }

    public final void zzb(zzlm zzlm) {
        ((zzlc) zzlm).release();
    }

    public final void zzfh() {
        this.zzawp = null;
    }

    public final void zzb(zzgc zzgc, Object obj) {
        boolean z = false;
        if (zzgc.zza(0, this.zzxb, false).zzaan != C.TIME_UNSET) {
            z = true;
        }
        if (!this.zzaxy || z) {
            this.zzxi = zzgc;
            this.zzaxy = z;
            this.zzawp.zzb(this.zzxi, null);
        }
    }
}
