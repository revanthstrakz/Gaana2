package com.google.android.gms.internal.ads;

import android.net.Uri;
import java.io.EOFException;
import java.io.IOException;

final class zzli {
    private final zzib zzajq;
    private final zzhz[] zzaxt;
    private zzhz zzaxu;

    public zzli(zzhz[] zzhzArr, zzib zzib) {
        this.zzaxt = zzhzArr;
        this.zzajq = zzib;
    }

    public final zzhz zza(zzia zzia, Uri uri) throws IOException, InterruptedException {
        if (this.zzaxu != null) {
            return this.zzaxu;
        }
        zzhz[] zzhzArr = this.zzaxt;
        int length = zzhzArr.length;
        int i = 0;
        loop0:
        while (i < length) {
            zzhz zzhz = zzhzArr[i];
            try {
                if (zzhz.zza(zzia)) {
                    this.zzaxu = zzhz;
                    zzia.zzdx();
                    break loop0;
                }
                i++;
            } catch (EOFException unused) {
                i++;
            } finally {
                zzia.zzdx();
            }
        }
        if (this.zzaxu == null) {
            String zza = zzqe.zza(this.zzaxt);
            StringBuilder stringBuilder = new StringBuilder(58 + String.valueOf(zza).length());
            stringBuilder.append("None of the available extractors (");
            stringBuilder.append(zza);
            stringBuilder.append(") could read the stream.");
            throw new zzmb(stringBuilder.toString(), uri);
        }
        this.zzaxu.zza(this.zzajq);
        return this.zzaxu;
    }

    public final void release() {
        if (this.zzaxu != null) {
            this.zzaxu.release();
            this.zzaxu = null;
        }
    }
}
