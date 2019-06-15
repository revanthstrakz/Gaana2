package com.google.android.gms.internal.ads;

import android.net.Uri;
import java.io.IOException;

@zzark
final class zzbfa implements zzov {
    private Uri uri;
    private final zzov zzewe;
    private final long zzewf;
    private final zzov zzewg;
    private long zzewh;

    zzbfa(zzov zzov, int i, zzov zzov2) {
        this.zzewe = zzov;
        this.zzewf = (long) i;
        this.zzewg = zzov2;
    }

    public final long zza(zzoz zzoz) throws IOException {
        zzoz zzoz2;
        zzoz zzoz3 = zzoz;
        this.uri = zzoz3.uri;
        zzoz zzoz4 = null;
        if (zzoz3.zzaha >= this.zzewf) {
            zzoz2 = null;
        } else {
            long min;
            long j = zzoz3.zzaha;
            if (zzoz3.zzcc != -1) {
                min = Math.min(zzoz3.zzcc, this.zzewf - j);
            } else {
                min = this.zzewf - j;
            }
            zzoz zzoz5 = new zzoz(zzoz3.uri, j, min, null);
        }
        if (zzoz3.zzcc == -1 || zzoz3.zzaha + zzoz3.zzcc > this.zzewf) {
            zzoz zzoz6 = new zzoz(zzoz3.uri, Math.max(this.zzewf, zzoz3.zzaha), zzoz3.zzcc != -1 ? Math.min(zzoz3.zzcc, (zzoz3.zzaha + zzoz3.zzcc) - this.zzewf) : -1, null);
        }
        long j2 = 0;
        long zza = zzoz2 != null ? this.zzewe.zza(zzoz2) : 0;
        if (zzoz4 != null) {
            j2 = this.zzewg.zza(zzoz4);
        }
        this.zzewh = zzoz3.zzaha;
        if (zza == -1 || j2 == -1) {
            return -1;
        }
        return zza + j2;
    }

    public final int read(byte[] bArr, int i, int i2) throws IOException {
        int read;
        if (this.zzewh < this.zzewf) {
            read = this.zzewe.read(bArr, i, (int) Math.min((long) i2, this.zzewf - this.zzewh));
            this.zzewh += (long) read;
        } else {
            read = 0;
        }
        if (this.zzewh < this.zzewf) {
            return read;
        }
        int read2 = this.zzewg.read(bArr, i + read, i2 - read);
        read += read2;
        this.zzewh += (long) read2;
        return read;
    }

    public final Uri getUri() {
        return this.uri;
    }

    public final void close() throws IOException {
        this.zzewe.close();
        this.zzewg.close();
    }
}
