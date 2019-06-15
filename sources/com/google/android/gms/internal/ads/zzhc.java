package com.google.android.gms.internal.ads;

import com.google.android.exoplayer2.extractor.ts.PsExtractor;
import java.nio.ByteBuffer;

public final class zzhc {
    private static final int[] zzaer = new int[]{1, 2, 2, 2, 2, 3, 3, 4, 4, 5, 6, 6, 6, 7, 8, 8};
    private static final int[] zzaes = new int[]{-1, 8000, 16000, 32000, -1, -1, 11025, 22050, 44100, -1, -1, 12000, 24000, 48000, -1, -1};
    private static final int[] zzaet = new int[]{64, 112, 128, PsExtractor.AUDIO_STREAM, 224, 256, 384, 448, 512, 640, 768, MediaRouterJellybean.DEVICE_OUT_BLUETOOTH, 1024, 1152, 1280, 1536, 1920, 2048, 2304, 2560, 2688, 2816, 2823, 2944, 3072, 3840, 4096, 6144, 7680};

    public static int zzj(ByteBuffer byteBuffer) {
        int position = byteBuffer.position();
        return ((((byteBuffer.get(position + 5) & 252) >> 2) | ((byteBuffer.get(position + 4) & 1) << 6)) + 1) << 5;
    }
}
