package com.google.android.gms.internal.ads;

import com.google.android.exoplayer2.extractor.ts.PsExtractor;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.gms.dynamite.descriptors.com.google.android.gms.ads.dynamite.ModuleDescriptor;
import com.moe.pushlibrary.utils.MoEHelperUtils;
import java.nio.ByteBuffer;

public final class zzgg {
    private static final int[] zzaay = new int[]{1, 2, 3, 6};
    private static final int[] zzaaz = new int[]{48000, 44100, 32000};
    private static final int[] zzaba = new int[]{24000, 22050, 16000};
    private static final int[] zzabb = new int[]{2, 1, 2, 3, 3, 4, 4, 5};
    private static final int[] zzabc = new int[]{32, 40, 48, 56, 64, 80, 96, 112, 128, MoEHelperUtils.BASELINE_SCREEN_DPI, PsExtractor.AUDIO_STREAM, 224, 256, ModuleDescriptor.MODULE_VERSION, 384, 448, 512, 576, 640};
    private static final int[] zzabd = new int[]{69, 87, 104, 121, 139, 174, 208, 243, 278, 348, 417, 487, 557, 696, 835, 975, 1114, 1253, 1393};

    public static zzfs zza(zzpx zzpx, String str, String str2, zzhp zzhp) {
        int i = zzaaz[(zzpx.readUnsignedByte() & PsExtractor.AUDIO_STREAM) >> 6];
        int readUnsignedByte = zzpx.readUnsignedByte();
        int i2 = zzabb[(readUnsignedByte & 56) >> 3];
        if ((readUnsignedByte & 4) != 0) {
            i2++;
        }
        return zzfs.zza(str, MimeTypes.AUDIO_AC3, null, -1, -1, i2, i, null, zzhp, 0, str2);
    }

    public static int zzcn() {
        return 1536;
    }

    public static zzfs zzb(zzpx zzpx, String str, String str2, zzhp zzhp) {
        zzpx zzpx2 = zzpx;
        zzpx2.zzbl(2);
        int i = zzaaz[(zzpx2.readUnsignedByte() & PsExtractor.AUDIO_STREAM) >> 6];
        int readUnsignedByte = zzpx2.readUnsignedByte();
        int i2 = zzabb[(readUnsignedByte & 14) >> 1];
        if ((readUnsignedByte & 1) != 0) {
            i2++;
        }
        return zzfs.zza(str, MimeTypes.AUDIO_E_AC3, null, -1, -1, i2, i, null, zzhp, 0, str2);
    }

    public static int zzh(ByteBuffer byteBuffer) {
        int i = 6;
        if (((byteBuffer.get(byteBuffer.position() + 4) & PsExtractor.AUDIO_STREAM) >> 6) != 3) {
            i = zzaay[(byteBuffer.get(byteBuffer.position() + 4) & 48) >> 4];
        }
        return 256 * i;
    }
}
