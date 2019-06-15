package com.google.android.gms.internal.ads;

import android.util.Base64OutputStream;
import com.google.android.gms.common.util.VisibleForTesting;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@VisibleForTesting
final class zztg {
    @VisibleForTesting
    private ByteArrayOutputStream zzbzc = new ByteArrayOutputStream(4096);
    @VisibleForTesting
    private Base64OutputStream zzbzd = new Base64OutputStream(this.zzbzc, 10);

    public final void write(byte[] bArr) throws IOException {
        this.zzbzd.write(bArr);
    }

    public final String toString() {
        try {
            this.zzbzd.close();
        } catch (IOException e) {
            zzbbd.zzb("HashManager: Unable to convert to Base64.", e);
        }
        String byteArrayOutputStream;
        try {
            this.zzbzc.close();
            byteArrayOutputStream = this.zzbzc.toString();
            return byteArrayOutputStream;
        } catch (IOException e2) {
            zzbbd.zzb("HashManager: Unable to convert to Base64.", e2);
            byteArrayOutputStream = "";
            return byteArrayOutputStream;
        } finally {
            this.zzbzc = null;
            this.zzbzd = null;
        }
    }
}
