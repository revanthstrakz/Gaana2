package com.google.android.exoplayer2.mediacodec;

import android.annotation.TargetApi;
import android.graphics.Point;
import android.media.MediaCodecInfo.AudioCapabilities;
import android.media.MediaCodecInfo.CodecCapabilities;
import android.media.MediaCodecInfo.CodecProfileLevel;
import android.media.MediaCodecInfo.VideoCapabilities;
import android.support.annotation.Nullable;
import android.util.Pair;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.mediacodec.MediaCodecUtil.DecoderQueryException;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.Util;
import com.integralads.avid.library.inmobi.utils.AvidJSONUtil;

@TargetApi(16)
public final class MediaCodecInfo {
    public static final int MAX_SUPPORTED_INSTANCES_UNKNOWN = -1;
    public static final String TAG = "MediaCodecInfo";
    public final boolean adaptive;
    @Nullable
    public final CodecCapabilities capabilities;
    private final boolean isVideo;
    @Nullable
    public final String mimeType;
    public final String name;
    public final boolean passthrough;
    public final boolean secure;
    public final boolean tunneling;

    public static MediaCodecInfo newPassthroughInstance(String str) {
        return new MediaCodecInfo(str, null, null, true, false, false);
    }

    public static MediaCodecInfo newInstance(String str, String str2, CodecCapabilities codecCapabilities) {
        return new MediaCodecInfo(str, str2, codecCapabilities, false, false, false);
    }

    public static MediaCodecInfo newInstance(String str, String str2, CodecCapabilities codecCapabilities, boolean z, boolean z2) {
        return new MediaCodecInfo(str, str2, codecCapabilities, false, z, z2);
    }

    private MediaCodecInfo(String str, @Nullable String str2, @Nullable CodecCapabilities codecCapabilities, boolean z, boolean z2, boolean z3) {
        this.name = (String) Assertions.checkNotNull(str);
        this.mimeType = str2;
        this.capabilities = codecCapabilities;
        this.passthrough = z;
        boolean z4 = false;
        z2 = (z2 || codecCapabilities == null || !isAdaptive(codecCapabilities)) ? false : true;
        this.adaptive = z2;
        z2 = codecCapabilities != null && isTunneling(codecCapabilities);
        this.tunneling = z2;
        if (z3 || (codecCapabilities != null && isSecure(codecCapabilities))) {
            z4 = true;
        }
        this.secure = z4;
        this.isVideo = MimeTypes.isVideo(str2);
    }

    public String toString() {
        return this.name;
    }

    public CodecProfileLevel[] getProfileLevels() {
        return (this.capabilities == null || this.capabilities.profileLevels == null) ? new CodecProfileLevel[0] : this.capabilities.profileLevels;
    }

    public int getMaxSupportedInstances() {
        return (Util.SDK_INT < 23 || this.capabilities == null) ? -1 : getMaxSupportedInstancesV23(this.capabilities);
    }

    public boolean isFormatSupported(Format format) throws DecoderQueryException {
        boolean z = false;
        if (!isCodecSupported(format.codecs)) {
            return false;
        }
        if (!this.isVideo) {
            if (Util.SDK_INT < 21 || ((format.sampleRate == -1 || isAudioSampleRateSupportedV21(format.sampleRate)) && (format.channelCount == -1 || isAudioChannelCountSupportedV21(format.channelCount)))) {
                z = true;
            }
            return z;
        } else if (format.width <= 0 || format.height <= 0) {
            return true;
        } else {
            if (Util.SDK_INT >= 21) {
                return isVideoSizeAndRateSupportedV21(format.width, format.height, (double) format.frameRate);
            }
            if (format.width * format.height <= MediaCodecUtil.maxH264DecodableFrameSize()) {
                z = true;
            }
            if (!z) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("legacyFrameSize, ");
                stringBuilder.append(format.width);
                stringBuilder.append(AvidJSONUtil.KEY_X);
                stringBuilder.append(format.height);
                logNoSupport(stringBuilder.toString());
            }
            return z;
        }
    }

    public boolean isCodecSupported(String str) {
        if (str == null || this.mimeType == null) {
            return true;
        }
        String mediaMimeType = MimeTypes.getMediaMimeType(str);
        if (mediaMimeType == null) {
            return true;
        }
        StringBuilder stringBuilder;
        if (this.mimeType.equals(mediaMimeType)) {
            Pair codecProfileAndLevel = MediaCodecUtil.getCodecProfileAndLevel(str);
            if (codecProfileAndLevel == null) {
                return true;
            }
            int intValue = ((Integer) codecProfileAndLevel.first).intValue();
            int intValue2 = ((Integer) codecProfileAndLevel.second).intValue();
            if (!this.isVideo && intValue != 42) {
                return true;
            }
            for (CodecProfileLevel codecProfileLevel : getProfileLevels()) {
                if (codecProfileLevel.profile == intValue && codecProfileLevel.level >= intValue2) {
                    return true;
                }
            }
            stringBuilder = new StringBuilder();
            stringBuilder.append("codec.profileLevel, ");
            stringBuilder.append(str);
            stringBuilder.append(", ");
            stringBuilder.append(mediaMimeType);
            logNoSupport(stringBuilder.toString());
            return false;
        }
        stringBuilder = new StringBuilder();
        stringBuilder.append("codec.mime ");
        stringBuilder.append(str);
        stringBuilder.append(", ");
        stringBuilder.append(mediaMimeType);
        logNoSupport(stringBuilder.toString());
        return false;
    }

    public boolean isSeamlessAdaptationSupported(Format format) {
        if (this.isVideo) {
            return this.adaptive;
        }
        Pair codecProfileAndLevel = MediaCodecUtil.getCodecProfileAndLevel(format.codecs);
        boolean z = codecProfileAndLevel != null && ((Integer) codecProfileAndLevel.first).intValue() == 42;
        return z;
    }

    public boolean isSeamlessAdaptationSupported(Format format, Format format2, boolean z) {
        boolean z2 = true;
        if (this.isVideo) {
            if (!(format.sampleMimeType.equals(format2.sampleMimeType) && format.rotationDegrees == format2.rotationDegrees && ((this.adaptive || (format.width == format2.width && format.height == format2.height)) && ((!z && format2.colorInfo == null) || Util.areEqual(format.colorInfo, format2.colorInfo))))) {
                z2 = false;
            }
            return z2;
        } else if (!MimeTypes.AUDIO_AAC.equals(this.mimeType) || !format.sampleMimeType.equals(format2.sampleMimeType) || format.channelCount != format2.channelCount || format.sampleRate != format2.sampleRate) {
            return false;
        } else {
            Pair codecProfileAndLevel = MediaCodecUtil.getCodecProfileAndLevel(format.codecs);
            Pair codecProfileAndLevel2 = MediaCodecUtil.getCodecProfileAndLevel(format2.codecs);
            if (codecProfileAndLevel == null || codecProfileAndLevel2 == null) {
                return false;
            }
            int intValue = ((Integer) codecProfileAndLevel.first).intValue();
            int intValue2 = ((Integer) codecProfileAndLevel2.first).intValue();
            if (!(intValue == 42 && intValue2 == 42)) {
                z2 = false;
            }
            return z2;
        }
    }

    @TargetApi(21)
    public boolean isVideoSizeAndRateSupportedV21(int i, int i2, double d) {
        if (this.capabilities == null) {
            logNoSupport("sizeAndRate.caps");
            return false;
        }
        VideoCapabilities videoCapabilities = this.capabilities.getVideoCapabilities();
        if (videoCapabilities == null) {
            logNoSupport("sizeAndRate.vCaps");
            return false;
        }
        if (!areSizeAndRateSupportedV21(videoCapabilities, i, i2, d)) {
            StringBuilder stringBuilder;
            if (i >= i2 || !areSizeAndRateSupportedV21(videoCapabilities, i2, i, d)) {
                stringBuilder = new StringBuilder();
                stringBuilder.append("sizeAndRate.support, ");
                stringBuilder.append(i);
                stringBuilder.append(AvidJSONUtil.KEY_X);
                stringBuilder.append(i2);
                stringBuilder.append(AvidJSONUtil.KEY_X);
                stringBuilder.append(d);
                logNoSupport(stringBuilder.toString());
                return false;
            }
            stringBuilder = new StringBuilder();
            stringBuilder.append("sizeAndRate.rotated, ");
            stringBuilder.append(i);
            stringBuilder.append(AvidJSONUtil.KEY_X);
            stringBuilder.append(i2);
            stringBuilder.append(AvidJSONUtil.KEY_X);
            stringBuilder.append(d);
            logAssumedSupport(stringBuilder.toString());
        }
        return true;
    }

    @TargetApi(21)
    public Point alignVideoSizeV21(int i, int i2) {
        if (this.capabilities == null) {
            logNoSupport("align.caps");
            return null;
        }
        VideoCapabilities videoCapabilities = this.capabilities.getVideoCapabilities();
        if (videoCapabilities == null) {
            logNoSupport("align.vCaps");
            return null;
        }
        int widthAlignment = videoCapabilities.getWidthAlignment();
        int heightAlignment = videoCapabilities.getHeightAlignment();
        return new Point(Util.ceilDivide(i, widthAlignment) * widthAlignment, Util.ceilDivide(i2, heightAlignment) * heightAlignment);
    }

    @TargetApi(21)
    public boolean isAudioSampleRateSupportedV21(int i) {
        if (this.capabilities == null) {
            logNoSupport("sampleRate.caps");
            return false;
        }
        AudioCapabilities audioCapabilities = this.capabilities.getAudioCapabilities();
        if (audioCapabilities == null) {
            logNoSupport("sampleRate.aCaps");
            return false;
        } else if (audioCapabilities.isSampleRateSupported(i)) {
            return true;
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("sampleRate.support, ");
            stringBuilder.append(i);
            logNoSupport(stringBuilder.toString());
            return false;
        }
    }

    @TargetApi(21)
    public boolean isAudioChannelCountSupportedV21(int i) {
        if (this.capabilities == null) {
            logNoSupport("channelCount.caps");
            return false;
        }
        AudioCapabilities audioCapabilities = this.capabilities.getAudioCapabilities();
        if (audioCapabilities == null) {
            logNoSupport("channelCount.aCaps");
            return false;
        } else if (adjustMaxInputChannelCount(this.name, this.mimeType, audioCapabilities.getMaxInputChannelCount()) >= i) {
            return true;
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("channelCount.support, ");
            stringBuilder.append(i);
            logNoSupport(stringBuilder.toString());
            return false;
        }
    }

    private void logNoSupport(String str) {
        String str2 = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("NoSupport [");
        stringBuilder.append(str);
        stringBuilder.append("] [");
        stringBuilder.append(this.name);
        stringBuilder.append(", ");
        stringBuilder.append(this.mimeType);
        stringBuilder.append("] [");
        stringBuilder.append(Util.DEVICE_DEBUG_INFO);
        stringBuilder.append("]");
        Log.d(str2, stringBuilder.toString());
    }

    private void logAssumedSupport(String str) {
        String str2 = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("AssumedSupport [");
        stringBuilder.append(str);
        stringBuilder.append("] [");
        stringBuilder.append(this.name);
        stringBuilder.append(", ");
        stringBuilder.append(this.mimeType);
        stringBuilder.append("] [");
        stringBuilder.append(Util.DEVICE_DEBUG_INFO);
        stringBuilder.append("]");
        Log.d(str2, stringBuilder.toString());
    }

    /* JADX WARNING: Missing block: B:37:0x00aa, code skipped:
            return r5;
     */
    private static int adjustMaxInputChannelCount(java.lang.String r3, java.lang.String r4, int r5) {
        /*
        r0 = 1;
        if (r5 > r0) goto L_0x00aa;
    L_0x0003:
        r0 = com.google.android.exoplayer2.util.Util.SDK_INT;
        r1 = 26;
        if (r0 < r1) goto L_0x000d;
    L_0x0009:
        if (r5 <= 0) goto L_0x000d;
    L_0x000b:
        goto L_0x00aa;
    L_0x000d:
        r0 = "audio/mpeg";
        r0 = r0.equals(r4);
        if (r0 != 0) goto L_0x00a9;
    L_0x0015:
        r0 = "audio/3gpp";
        r0 = r0.equals(r4);
        if (r0 != 0) goto L_0x00a9;
    L_0x001d:
        r0 = "audio/amr-wb";
        r0 = r0.equals(r4);
        if (r0 != 0) goto L_0x00a9;
    L_0x0025:
        r0 = "audio/mp4a-latm";
        r0 = r0.equals(r4);
        if (r0 != 0) goto L_0x00a9;
    L_0x002d:
        r0 = "audio/vorbis";
        r0 = r0.equals(r4);
        if (r0 != 0) goto L_0x00a9;
    L_0x0035:
        r0 = "audio/opus";
        r0 = r0.equals(r4);
        if (r0 != 0) goto L_0x00a9;
    L_0x003d:
        r0 = "audio/raw";
        r0 = r0.equals(r4);
        if (r0 != 0) goto L_0x00a9;
    L_0x0045:
        r0 = "audio/flac";
        r0 = r0.equals(r4);
        if (r0 != 0) goto L_0x00a9;
    L_0x004d:
        r0 = "audio/g711-alaw";
        r0 = r0.equals(r4);
        if (r0 != 0) goto L_0x00a9;
    L_0x0055:
        r0 = "audio/g711-mlaw";
        r0 = r0.equals(r4);
        if (r0 != 0) goto L_0x00a9;
    L_0x005d:
        r0 = "audio/gsm";
        r0 = r0.equals(r4);
        if (r0 == 0) goto L_0x0066;
    L_0x0065:
        goto L_0x00a9;
    L_0x0066:
        r0 = "audio/ac3";
        r0 = r0.equals(r4);
        if (r0 == 0) goto L_0x0070;
    L_0x006e:
        r4 = 6;
        goto L_0x007d;
    L_0x0070:
        r0 = "audio/eac3";
        r4 = r0.equals(r4);
        if (r4 == 0) goto L_0x007b;
    L_0x0078:
        r4 = 16;
        goto L_0x007d;
    L_0x007b:
        r4 = 30;
    L_0x007d:
        r0 = "MediaCodecInfo";
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "AssumedMaxChannelAdjustment: ";
        r1.append(r2);
        r1.append(r3);
        r3 = ", [";
        r1.append(r3);
        r1.append(r5);
        r3 = " to ";
        r1.append(r3);
        r1.append(r4);
        r3 = "]";
        r1.append(r3);
        r3 = r1.toString();
        com.google.android.exoplayer2.util.Log.w(r0, r3);
        return r4;
    L_0x00a9:
        return r5;
    L_0x00aa:
        return r5;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.mediacodec.MediaCodecInfo.adjustMaxInputChannelCount(java.lang.String, java.lang.String, int):int");
    }

    private static boolean isAdaptive(CodecCapabilities codecCapabilities) {
        return Util.SDK_INT >= 19 && isAdaptiveV19(codecCapabilities);
    }

    @TargetApi(19)
    private static boolean isAdaptiveV19(CodecCapabilities codecCapabilities) {
        return codecCapabilities.isFeatureSupported("adaptive-playback");
    }

    private static boolean isTunneling(CodecCapabilities codecCapabilities) {
        return Util.SDK_INT >= 21 && isTunnelingV21(codecCapabilities);
    }

    @TargetApi(21)
    private static boolean isTunnelingV21(CodecCapabilities codecCapabilities) {
        return codecCapabilities.isFeatureSupported("tunneled-playback");
    }

    private static boolean isSecure(CodecCapabilities codecCapabilities) {
        return Util.SDK_INT >= 21 && isSecureV21(codecCapabilities);
    }

    @TargetApi(21)
    private static boolean isSecureV21(CodecCapabilities codecCapabilities) {
        return codecCapabilities.isFeatureSupported("secure-playback");
    }

    @TargetApi(21)
    private static boolean areSizeAndRateSupportedV21(VideoCapabilities videoCapabilities, int i, int i2, double d) {
        if (d == -1.0d || d <= 0.0d) {
            return videoCapabilities.isSizeSupported(i, i2);
        }
        return videoCapabilities.areSizeAndRateSupported(i, i2, d);
    }

    @TargetApi(23)
    private static int getMaxSupportedInstancesV23(CodecCapabilities codecCapabilities) {
        return codecCapabilities.getMaxSupportedInstances();
    }
}
