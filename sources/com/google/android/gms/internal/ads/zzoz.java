package com.google.android.gms.internal.ads;

import android.net.Uri;
import java.util.Arrays;

public final class zzoz {
    public final int flags;
    public final Uri uri;
    public final long zzaha;
    public final byte[] zzbft;
    public final long zzbfu;
    public final String zzcb;
    public final long zzcc;

    public zzoz(Uri uri) {
        this(uri, 0);
    }

    public zzoz(Uri uri, int i) {
        this(uri, 0, -1, null, i);
    }

    public zzoz(Uri uri, long j, long j2, String str) {
        this(uri, j, j, j2, str, 0);
    }

    public zzoz(Uri uri, long j, long j2, String str, int i) {
        this(uri, j, j, j2, str, i);
    }

    private zzoz(Uri uri, long j, long j2, long j3, String str, int i) {
        this(uri, null, j, j2, j3, str, i);
    }

    public zzoz(Uri uri, byte[] bArr, long j, long j2, long j3, String str, int i) {
        boolean z = false;
        zzpo.checkArgument(j >= 0);
        zzpo.checkArgument(j2 >= 0);
        if (j3 > 0 || j3 == -1) {
            z = true;
        }
        zzpo.checkArgument(z);
        this.uri = uri;
        this.zzbft = bArr;
        this.zzbfu = j;
        this.zzaha = j2;
        this.zzcc = j3;
        this.zzcb = str;
        this.flags = i;
    }

    public final boolean zzbg(int i) {
        return (this.flags & 1) == 1;
    }

    public final String toString() {
        String valueOf = String.valueOf(this.uri);
        String arrays = Arrays.toString(this.zzbft);
        long j = this.zzbfu;
        long j2 = this.zzaha;
        long j3 = this.zzcc;
        String str = this.zzcb;
        int i = this.flags;
        StringBuilder stringBuilder = new StringBuilder(((93 + String.valueOf(valueOf).length()) + String.valueOf(arrays).length()) + String.valueOf(str).length());
        stringBuilder.append("DataSpec[");
        stringBuilder.append(valueOf);
        stringBuilder.append(", ");
        stringBuilder.append(arrays);
        stringBuilder.append(", ");
        stringBuilder.append(j);
        stringBuilder.append(", ");
        stringBuilder.append(j2);
        stringBuilder.append(", ");
        stringBuilder.append(j3);
        stringBuilder.append(", ");
        stringBuilder.append(str);
        stringBuilder.append(", ");
        stringBuilder.append(i);
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}
