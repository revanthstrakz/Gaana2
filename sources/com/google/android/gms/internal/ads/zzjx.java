package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.graphics.Point;
import android.media.MediaCodecInfo.AudioCapabilities;
import android.media.MediaCodecInfo.CodecCapabilities;
import android.media.MediaCodecInfo.CodecProfileLevel;
import android.media.MediaCodecInfo.VideoCapabilities;
import android.util.Log;
import android.util.Pair;
import com.google.android.exoplayer2.mediacodec.MediaCodecInfo;
import com.integralads.avid.library.inmobi.utils.AvidJSONUtil;

@TargetApi(16)
public final class zzjx {
    private final String mimeType;
    public final String name;
    public final boolean zzadt;
    public final boolean zzatq;
    public final boolean zzatr;
    private final CodecCapabilities zzats;

    public static zzjx zzt(String str) {
        return new zzjx(str, null, null, false, false);
    }

    public static zzjx zza(String str, String str2, CodecCapabilities codecCapabilities, boolean z, boolean z2) {
        return new zzjx(str, str2, codecCapabilities, z, z2);
    }

    /* JADX WARNING: Removed duplicated region for block: B:25:0x0049  */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0031  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0049  */
    /* JADX WARNING: Missing block: B:32:0x005a, code skipped:
            if (r4 != false) goto L_0x005c;
     */
    private zzjx(java.lang.String r2, java.lang.String r3, android.media.MediaCodecInfo.CodecCapabilities r4, boolean r5, boolean r6) {
        /*
        r1 = this;
        r1.<init>();
        r2 = com.google.android.gms.internal.ads.zzpo.checkNotNull(r2);
        r2 = (java.lang.String) r2;
        r1.name = r2;
        r1.mimeType = r3;
        r1.zzats = r4;
        r2 = 0;
        r3 = 1;
        if (r5 != 0) goto L_0x002a;
    L_0x0013:
        if (r4 == 0) goto L_0x002a;
    L_0x0015:
        r5 = com.google.android.gms.internal.ads.zzqe.SDK_INT;
        r0 = 19;
        if (r5 < r0) goto L_0x0025;
    L_0x001b:
        r5 = "adaptive-playback";
        r5 = r4.isFeatureSupported(r5);
        if (r5 == 0) goto L_0x0025;
    L_0x0023:
        r5 = r3;
        goto L_0x0026;
    L_0x0025:
        r5 = r2;
    L_0x0026:
        if (r5 == 0) goto L_0x002a;
    L_0x0028:
        r5 = r3;
        goto L_0x002b;
    L_0x002a:
        r5 = r2;
    L_0x002b:
        r1.zzatq = r5;
        r5 = 21;
        if (r4 == 0) goto L_0x0044;
    L_0x0031:
        r0 = com.google.android.gms.internal.ads.zzqe.SDK_INT;
        if (r0 < r5) goto L_0x003f;
    L_0x0035:
        r0 = "tunneled-playback";
        r0 = r4.isFeatureSupported(r0);
        if (r0 == 0) goto L_0x003f;
    L_0x003d:
        r0 = r3;
        goto L_0x0040;
    L_0x003f:
        r0 = r2;
    L_0x0040:
        if (r0 == 0) goto L_0x0044;
    L_0x0042:
        r0 = r3;
        goto L_0x0045;
    L_0x0044:
        r0 = r2;
    L_0x0045:
        r1.zzadt = r0;
        if (r6 != 0) goto L_0x005c;
    L_0x0049:
        if (r4 == 0) goto L_0x005d;
    L_0x004b:
        r6 = com.google.android.gms.internal.ads.zzqe.SDK_INT;
        if (r6 < r5) goto L_0x0059;
    L_0x004f:
        r5 = "secure-playback";
        r4 = r4.isFeatureSupported(r5);
        if (r4 == 0) goto L_0x0059;
    L_0x0057:
        r4 = r3;
        goto L_0x005a;
    L_0x0059:
        r4 = r2;
    L_0x005a:
        if (r4 == 0) goto L_0x005d;
    L_0x005c:
        r2 = r3;
    L_0x005d:
        r1.zzatr = r2;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzjx.<init>(java.lang.String, java.lang.String, android.media.MediaCodecInfo$CodecCapabilities, boolean, boolean):void");
    }

    public final CodecProfileLevel[] zzej() {
        if (this.zzats == null || this.zzats.profileLevels == null) {
            return new CodecProfileLevel[0];
        }
        return this.zzats.profileLevels;
    }

    public final boolean zzu(String str) {
        if (str == null || this.mimeType == null) {
            return true;
        }
        String zzae = zzpt.zzae(str);
        if (zzae == null) {
            return true;
        }
        StringBuilder stringBuilder;
        if (this.mimeType.equals(zzae)) {
            Pair zzw = zzkc.zzw(str);
            if (zzw == null) {
                return true;
            }
            for (CodecProfileLevel codecProfileLevel : zzej()) {
                if (codecProfileLevel.profile == ((Integer) zzw.first).intValue() && codecProfileLevel.level >= ((Integer) zzw.second).intValue()) {
                    return true;
                }
            }
            stringBuilder = new StringBuilder((22 + String.valueOf(str).length()) + String.valueOf(zzae).length());
            stringBuilder.append("codec.profileLevel, ");
            stringBuilder.append(str);
            stringBuilder.append(", ");
            stringBuilder.append(zzae);
            zzv(stringBuilder.toString());
            return false;
        }
        stringBuilder = new StringBuilder((13 + String.valueOf(str).length()) + String.valueOf(zzae).length());
        stringBuilder.append("codec.mime ");
        stringBuilder.append(str);
        stringBuilder.append(", ");
        stringBuilder.append(zzae);
        zzv(stringBuilder.toString());
        return false;
    }

    @TargetApi(21)
    public final boolean zza(int i, int i2, double d) {
        if (this.zzats == null) {
            zzv("sizeAndRate.caps");
            return false;
        }
        VideoCapabilities videoCapabilities = this.zzats.getVideoCapabilities();
        if (videoCapabilities == null) {
            zzv("sizeAndRate.vCaps");
            return false;
        }
        if (!zza(videoCapabilities, i, i2, d)) {
            StringBuilder stringBuilder;
            if (i >= i2 || !zza(videoCapabilities, i2, i, d)) {
                stringBuilder = new StringBuilder(69);
                stringBuilder.append("sizeAndRate.support, ");
                stringBuilder.append(i);
                stringBuilder.append(AvidJSONUtil.KEY_X);
                stringBuilder.append(i2);
                stringBuilder.append(AvidJSONUtil.KEY_X);
                stringBuilder.append(d);
                zzv(stringBuilder.toString());
                return false;
            }
            stringBuilder = new StringBuilder(69);
            stringBuilder.append("sizeAndRate.rotated, ");
            stringBuilder.append(i);
            stringBuilder.append(AvidJSONUtil.KEY_X);
            stringBuilder.append(i2);
            stringBuilder.append(AvidJSONUtil.KEY_X);
            stringBuilder.append(d);
            String stringBuilder2 = stringBuilder.toString();
            String str = MediaCodecInfo.TAG;
            String str2 = this.name;
            String str3 = this.mimeType;
            String str4 = zzqe.zzbic;
            StringBuilder stringBuilder3 = new StringBuilder((((25 + String.valueOf(stringBuilder2).length()) + String.valueOf(str2).length()) + String.valueOf(str3).length()) + String.valueOf(str4).length());
            stringBuilder3.append("AssumedSupport [");
            stringBuilder3.append(stringBuilder2);
            stringBuilder3.append("] [");
            stringBuilder3.append(str2);
            stringBuilder3.append(", ");
            stringBuilder3.append(str3);
            stringBuilder3.append("] [");
            stringBuilder3.append(str4);
            stringBuilder3.append("]");
            Log.d(str, stringBuilder3.toString());
        }
        return true;
    }

    @TargetApi(21)
    public final Point zzc(int i, int i2) {
        if (this.zzats == null) {
            zzv("align.caps");
            return null;
        }
        VideoCapabilities videoCapabilities = this.zzats.getVideoCapabilities();
        if (videoCapabilities == null) {
            zzv("align.vCaps");
            return null;
        }
        int widthAlignment = videoCapabilities.getWidthAlignment();
        int heightAlignment = videoCapabilities.getHeightAlignment();
        return new Point(zzqe.zzf(i, widthAlignment) * widthAlignment, zzqe.zzf(i2, heightAlignment) * heightAlignment);
    }

    @TargetApi(21)
    public final boolean zzam(int i) {
        if (this.zzats == null) {
            zzv("sampleRate.caps");
            return false;
        }
        AudioCapabilities audioCapabilities = this.zzats.getAudioCapabilities();
        if (audioCapabilities == null) {
            zzv("sampleRate.aCaps");
            return false;
        } else if (audioCapabilities.isSampleRateSupported(i)) {
            return true;
        } else {
            StringBuilder stringBuilder = new StringBuilder(31);
            stringBuilder.append("sampleRate.support, ");
            stringBuilder.append(i);
            zzv(stringBuilder.toString());
            return false;
        }
    }

    @TargetApi(21)
    public final boolean zzan(int i) {
        if (this.zzats == null) {
            zzv("channelCount.caps");
            return false;
        }
        AudioCapabilities audioCapabilities = this.zzats.getAudioCapabilities();
        if (audioCapabilities == null) {
            zzv("channelCount.aCaps");
            return false;
        } else if (audioCapabilities.getMaxInputChannelCount() >= i) {
            return true;
        } else {
            StringBuilder stringBuilder = new StringBuilder(33);
            stringBuilder.append("channelCount.support, ");
            stringBuilder.append(i);
            zzv(stringBuilder.toString());
            return false;
        }
    }

    private final void zzv(String str) {
        String str2 = MediaCodecInfo.TAG;
        String str3 = this.name;
        String str4 = this.mimeType;
        String str5 = zzqe.zzbic;
        StringBuilder stringBuilder = new StringBuilder((((20 + String.valueOf(str).length()) + String.valueOf(str3).length()) + String.valueOf(str4).length()) + String.valueOf(str5).length());
        stringBuilder.append("NoSupport [");
        stringBuilder.append(str);
        stringBuilder.append("] [");
        stringBuilder.append(str3);
        stringBuilder.append(", ");
        stringBuilder.append(str4);
        stringBuilder.append("] [");
        stringBuilder.append(str5);
        stringBuilder.append("]");
        Log.d(str2, stringBuilder.toString());
    }

    @TargetApi(21)
    private static boolean zza(VideoCapabilities videoCapabilities, int i, int i2, double d) {
        if (d == -1.0d || d <= 0.0d) {
            return videoCapabilities.isSizeSupported(i, i2);
        }
        return videoCapabilities.areSizeAndRateSupported(i, i2, d);
    }
}
