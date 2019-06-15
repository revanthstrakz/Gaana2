package com.google.android.gms.internal.ads;

import java.io.Closeable;
import java.io.IOException;

public class zzba extends zzbvy implements Closeable {
    private static zzbwg zzco = zzbwg.zzk(zzba.class);

    public zzba(zzbwa zzbwa, zzaz zzaz) throws IOException {
        zza(zzbwa, zzbwa.size(), zzaz);
    }

    public void close() throws IOException {
        this.zzgcl.close();
    }

    public String toString() {
        String obj = this.zzgcl.toString();
        StringBuilder stringBuilder = new StringBuilder(7 + String.valueOf(obj).length());
        stringBuilder.append("model(");
        stringBuilder.append(obj);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
