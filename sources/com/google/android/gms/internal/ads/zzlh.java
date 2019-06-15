package com.google.android.gms.internal.ads;

import android.net.Uri;
import java.io.IOException;

final class zzlh implements zzpi {
    private final Uri uri;
    private final zzov zzagy;
    private final zzli zzawu;
    private final zzpq zzawv;
    private final /* synthetic */ zzlc zzaxn;
    private final zzif zzaxp = new zzif();
    private volatile boolean zzaxq;
    private boolean zzaxr = true;
    private long zzaxs;
    private long zzcc = -1;

    public zzlh(zzlc zzlc, Uri uri, zzov zzov, zzli zzli, zzpq zzpq) {
        this.zzaxn = zzlc;
        this.uri = (Uri) zzpo.checkNotNull(uri);
        this.zzagy = (zzov) zzpo.checkNotNull(zzov);
        this.zzawu = (zzli) zzpo.checkNotNull(zzli);
        this.zzawv = zzpq;
    }

    public final void zze(long j, long j2) {
        this.zzaxp.zzaha = j;
        this.zzaxs = j2;
        this.zzaxr = true;
    }

    public final void cancelLoad() {
        this.zzaxq = true;
    }

    public final boolean zzfe() {
        return this.zzaxq;
    }

    public final void zzff() throws IOException, InterruptedException {
        Throwable th;
        zzia zzia;
        boolean z = false;
        while (!z && !this.zzaxq) {
            try {
                long j = this.zzaxp.zzaha;
                this.zzcc = this.zzagy.zza(new zzoz(this.uri, j, -1, this.zzaxn.zzawr));
                if (this.zzcc != -1) {
                    this.zzcc += j;
                }
                zzia zzhx = new zzhx(this.zzagy, j, this.zzcc);
                try {
                    zzhz zza = this.zzawu.zza(zzhx, this.zzagy.getUri());
                    if (this.zzaxr) {
                        zza.zzc(j, this.zzaxs);
                        this.zzaxr = false;
                    }
                    while (!z && !this.zzaxq) {
                        this.zzawv.block();
                        boolean zza2 = zza.zza(zzhx, this.zzaxp);
                        try {
                            if (zzhx.getPosition() > j + this.zzaxn.zzaws) {
                                j = zzhx.getPosition();
                                this.zzawv.zzha();
                                this.zzaxn.handler.post(this.zzaxn.zzawx);
                            }
                            z = zza2;
                        } catch (Throwable th2) {
                            th = th2;
                            z = zza2;
                            if (!(z || zzia == null)) {
                                this.zzaxp.zzaha = zzia.getPosition();
                            }
                            zzqe.zza(this.zzagy);
                            throw th;
                        }
                    }
                    if (z) {
                        z = false;
                    } else {
                        this.zzaxp.zzaha = zzhx.getPosition();
                    }
                    zzqe.zza(this.zzagy);
                } catch (Throwable th3) {
                    th = th3;
                    this.zzaxp.zzaha = zzia.getPosition();
                    zzqe.zza(this.zzagy);
                    throw th;
                }
            } catch (Throwable th4) {
                th = th4;
                zzia = null;
                this.zzaxp.zzaha = zzia.getPosition();
                zzqe.zza(this.zzagy);
                throw th;
            }
        }
    }
}
