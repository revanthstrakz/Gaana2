package com.google.android.gms.internal.ads;

import android.media.MediaCodec.CodecException;

public final class zzjz extends Exception {
    private final String mimeType;
    private final boolean zzave;
    private final String zzavf;
    private final String zzavg;

    public zzjz(zzfs zzfs, Throwable th, boolean z, int i) {
        String valueOf = String.valueOf(zzfs);
        StringBuilder stringBuilder = new StringBuilder(36 + String.valueOf(valueOf).length());
        stringBuilder.append("Decoder init failed: [");
        stringBuilder.append(i);
        stringBuilder.append("], ");
        stringBuilder.append(valueOf);
        super(stringBuilder.toString(), th);
        this.mimeType = zzfs.zzzj;
        this.zzave = false;
        this.zzavf = null;
        Object obj = i < 0 ? "neg_" : "";
        int abs = Math.abs(i);
        StringBuilder stringBuilder2 = new StringBuilder(64 + String.valueOf(obj).length());
        stringBuilder2.append("com.google.android.exoplayer.MediaCodecTrackRenderer_");
        stringBuilder2.append(obj);
        stringBuilder2.append(abs);
        this.zzavg = stringBuilder2.toString();
    }

    public zzjz(zzfs zzfs, Throwable th, boolean z, String str) {
        String valueOf = String.valueOf(zzfs);
        StringBuilder stringBuilder = new StringBuilder((23 + String.valueOf(str).length()) + String.valueOf(valueOf).length());
        stringBuilder.append("Decoder init failed: ");
        stringBuilder.append(str);
        stringBuilder.append(", ");
        stringBuilder.append(valueOf);
        super(stringBuilder.toString(), th);
        this.mimeType = zzfs.zzzj;
        this.zzave = false;
        this.zzavf = str;
        valueOf = null;
        if (zzqe.SDK_INT >= 21 && (th instanceof CodecException)) {
            valueOf = ((CodecException) th).getDiagnosticInfo();
        }
        this.zzavg = valueOf;
    }
}
