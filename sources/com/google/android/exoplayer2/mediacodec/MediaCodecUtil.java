package com.google.android.exoplayer2.mediacodec;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.media.MediaCodecInfo;
import android.media.MediaCodecInfo.CodecCapabilities;
import android.media.MediaCodecInfo.CodecProfileLevel;
import android.media.MediaCodecList;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Pair;
import android.util.SparseIntArray;
import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.Util;
import com.google.api.client.googleapis.media.MediaHttpDownloader;
import com.integralads.avid.library.inmobi.session.internal.InternalAvidAdSessionContext;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressLint({"InlinedApi"})
@TargetApi(16)
public final class MediaCodecUtil {
    private static final SparseIntArray AVC_LEVEL_NUMBER_TO_CONST = new SparseIntArray();
    private static final SparseIntArray AVC_PROFILE_NUMBER_TO_CONST = new SparseIntArray();
    private static final String CODEC_ID_AVC1 = "avc1";
    private static final String CODEC_ID_AVC2 = "avc2";
    private static final String CODEC_ID_HEV1 = "hev1";
    private static final String CODEC_ID_HVC1 = "hvc1";
    private static final String CODEC_ID_MP4A = "mp4a";
    private static final Map<String, Integer> HEVC_CODEC_STRING_TO_PROFILE_LEVEL = new HashMap();
    private static final SparseIntArray MP4A_AUDIO_OBJECT_TYPE_TO_PROFILE = new SparseIntArray();
    private static final Pattern PROFILE_PATTERN = Pattern.compile("^\\D?(\\d+)$");
    private static final RawAudioCodecComparator RAW_AUDIO_CODEC_COMPARATOR = new RawAudioCodecComparator();
    private static final String TAG = "MediaCodecUtil";
    private static final HashMap<CodecKey, List<MediaCodecInfo>> decoderInfosCache = new HashMap();
    private static int maxH264DecodableFrameSize = -1;

    private static final class CodecKey {
        public final String mimeType;
        public final boolean secure;

        public CodecKey(String str, boolean z) {
            this.mimeType = str;
            this.secure = z;
        }

        public int hashCode() {
            return (31 * ((this.mimeType == null ? 0 : this.mimeType.hashCode()) + 31)) + (this.secure ? 1231 : 1237);
        }

        public boolean equals(@Nullable Object obj) {
            boolean z = true;
            if (this == obj) {
                return true;
            }
            if (obj == null || obj.getClass() != CodecKey.class) {
                return false;
            }
            CodecKey codecKey = (CodecKey) obj;
            if (!(TextUtils.equals(this.mimeType, codecKey.mimeType) && this.secure == codecKey.secure)) {
                z = false;
            }
            return z;
        }
    }

    public static class DecoderQueryException extends Exception {
        private DecoderQueryException(Throwable th) {
            super("Failed to query underlying media codecs", th);
        }
    }

    private interface MediaCodecListCompat {
        int getCodecCount();

        MediaCodecInfo getCodecInfoAt(int i);

        boolean isSecurePlaybackSupported(String str, CodecCapabilities codecCapabilities);

        boolean secureDecodersExplicit();
    }

    private static final class RawAudioCodecComparator implements Comparator<MediaCodecInfo> {
        private RawAudioCodecComparator() {
        }

        public int compare(MediaCodecInfo mediaCodecInfo, MediaCodecInfo mediaCodecInfo2) {
            return scoreMediaCodecInfo(mediaCodecInfo) - scoreMediaCodecInfo(mediaCodecInfo2);
        }

        private static int scoreMediaCodecInfo(MediaCodecInfo mediaCodecInfo) {
            String str = mediaCodecInfo.name;
            if (str.startsWith("OMX.google") || str.startsWith("c2.android")) {
                return -1;
            }
            return (Util.SDK_INT >= 26 || !str.equals("OMX.MTK.AUDIO.DECODER.RAW")) ? 0 : 1;
        }
    }

    private static final class MediaCodecListCompatV16 implements MediaCodecListCompat {
        public boolean secureDecodersExplicit() {
            return false;
        }

        private MediaCodecListCompatV16() {
        }

        public int getCodecCount() {
            return MediaCodecList.getCodecCount();
        }

        public MediaCodecInfo getCodecInfoAt(int i) {
            return MediaCodecList.getCodecInfoAt(i);
        }

        public boolean isSecurePlaybackSupported(String str, CodecCapabilities codecCapabilities) {
            return MimeTypes.VIDEO_H264.equals(str);
        }
    }

    @TargetApi(21)
    private static final class MediaCodecListCompatV21 implements MediaCodecListCompat {
        private final int codecKind;
        private MediaCodecInfo[] mediaCodecInfos;

        public boolean secureDecodersExplicit() {
            return true;
        }

        public MediaCodecListCompatV21(boolean z) {
            this.codecKind = z;
        }

        public int getCodecCount() {
            ensureMediaCodecInfosInitialized();
            return this.mediaCodecInfos.length;
        }

        public MediaCodecInfo getCodecInfoAt(int i) {
            ensureMediaCodecInfosInitialized();
            return this.mediaCodecInfos[i];
        }

        public boolean isSecurePlaybackSupported(String str, CodecCapabilities codecCapabilities) {
            return codecCapabilities.isFeatureSupported("secure-playback");
        }

        private void ensureMediaCodecInfosInitialized() {
            if (this.mediaCodecInfos == null) {
                this.mediaCodecInfos = new MediaCodecList(this.codecKind).getCodecInfos();
            }
        }
    }

    private static int avcLevelToMaxFrameSize(int i) {
        switch (i) {
            case 1:
            case 2:
                return 25344;
            case 8:
            case 16:
            case 32:
                return 101376;
            case 64:
                return 202752;
            case 128:
            case 256:
                return 414720;
            case 512:
                return 921600;
            case 1024:
                return 1310720;
            case 2048:
            case 4096:
                return 2097152;
            case 8192:
                return 2228224;
            case 16384:
                return 5652480;
            case 32768:
            case 65536:
                return 9437184;
            default:
                return -1;
        }
    }

    static {
        AVC_PROFILE_NUMBER_TO_CONST.put(66, 1);
        AVC_PROFILE_NUMBER_TO_CONST.put(77, 2);
        AVC_PROFILE_NUMBER_TO_CONST.put(88, 4);
        AVC_PROFILE_NUMBER_TO_CONST.put(100, 8);
        AVC_PROFILE_NUMBER_TO_CONST.put(110, 16);
        AVC_PROFILE_NUMBER_TO_CONST.put(122, 32);
        AVC_PROFILE_NUMBER_TO_CONST.put(244, 64);
        AVC_LEVEL_NUMBER_TO_CONST.put(10, 1);
        AVC_LEVEL_NUMBER_TO_CONST.put(11, 4);
        AVC_LEVEL_NUMBER_TO_CONST.put(12, 8);
        AVC_LEVEL_NUMBER_TO_CONST.put(13, 16);
        AVC_LEVEL_NUMBER_TO_CONST.put(20, 32);
        AVC_LEVEL_NUMBER_TO_CONST.put(21, 64);
        AVC_LEVEL_NUMBER_TO_CONST.put(22, 128);
        AVC_LEVEL_NUMBER_TO_CONST.put(30, 256);
        AVC_LEVEL_NUMBER_TO_CONST.put(31, 512);
        AVC_LEVEL_NUMBER_TO_CONST.put(32, 1024);
        AVC_LEVEL_NUMBER_TO_CONST.put(40, 2048);
        AVC_LEVEL_NUMBER_TO_CONST.put(41, 4096);
        AVC_LEVEL_NUMBER_TO_CONST.put(42, 8192);
        AVC_LEVEL_NUMBER_TO_CONST.put(50, 16384);
        AVC_LEVEL_NUMBER_TO_CONST.put(51, 32768);
        AVC_LEVEL_NUMBER_TO_CONST.put(52, 65536);
        HEVC_CODEC_STRING_TO_PROFILE_LEVEL.put("L30", Integer.valueOf(1));
        HEVC_CODEC_STRING_TO_PROFILE_LEVEL.put("L60", Integer.valueOf(4));
        HEVC_CODEC_STRING_TO_PROFILE_LEVEL.put("L63", Integer.valueOf(16));
        HEVC_CODEC_STRING_TO_PROFILE_LEVEL.put("L90", Integer.valueOf(64));
        HEVC_CODEC_STRING_TO_PROFILE_LEVEL.put("L93", Integer.valueOf(256));
        HEVC_CODEC_STRING_TO_PROFILE_LEVEL.put("L120", Integer.valueOf(1024));
        HEVC_CODEC_STRING_TO_PROFILE_LEVEL.put("L123", Integer.valueOf(4096));
        HEVC_CODEC_STRING_TO_PROFILE_LEVEL.put("L150", Integer.valueOf(16384));
        HEVC_CODEC_STRING_TO_PROFILE_LEVEL.put("L153", Integer.valueOf(65536));
        HEVC_CODEC_STRING_TO_PROFILE_LEVEL.put("L156", Integer.valueOf(262144));
        HEVC_CODEC_STRING_TO_PROFILE_LEVEL.put("L180", Integer.valueOf(1048576));
        HEVC_CODEC_STRING_TO_PROFILE_LEVEL.put("L183", Integer.valueOf(4194304));
        HEVC_CODEC_STRING_TO_PROFILE_LEVEL.put("L186", Integer.valueOf(16777216));
        HEVC_CODEC_STRING_TO_PROFILE_LEVEL.put("H30", Integer.valueOf(2));
        HEVC_CODEC_STRING_TO_PROFILE_LEVEL.put("H60", Integer.valueOf(8));
        HEVC_CODEC_STRING_TO_PROFILE_LEVEL.put("H63", Integer.valueOf(32));
        HEVC_CODEC_STRING_TO_PROFILE_LEVEL.put("H90", Integer.valueOf(128));
        HEVC_CODEC_STRING_TO_PROFILE_LEVEL.put("H93", Integer.valueOf(512));
        HEVC_CODEC_STRING_TO_PROFILE_LEVEL.put("H120", Integer.valueOf(2048));
        HEVC_CODEC_STRING_TO_PROFILE_LEVEL.put("H123", Integer.valueOf(8192));
        HEVC_CODEC_STRING_TO_PROFILE_LEVEL.put("H150", Integer.valueOf(32768));
        HEVC_CODEC_STRING_TO_PROFILE_LEVEL.put("H153", Integer.valueOf(131072));
        HEVC_CODEC_STRING_TO_PROFILE_LEVEL.put("H156", Integer.valueOf(524288));
        HEVC_CODEC_STRING_TO_PROFILE_LEVEL.put("H180", Integer.valueOf(2097152));
        HEVC_CODEC_STRING_TO_PROFILE_LEVEL.put("H183", Integer.valueOf(8388608));
        HEVC_CODEC_STRING_TO_PROFILE_LEVEL.put("H186", Integer.valueOf(MediaHttpDownloader.MAXIMUM_CHUNK_SIZE));
        MP4A_AUDIO_OBJECT_TYPE_TO_PROFILE.put(1, 1);
        MP4A_AUDIO_OBJECT_TYPE_TO_PROFILE.put(2, 2);
        MP4A_AUDIO_OBJECT_TYPE_TO_PROFILE.put(3, 3);
        MP4A_AUDIO_OBJECT_TYPE_TO_PROFILE.put(4, 4);
        MP4A_AUDIO_OBJECT_TYPE_TO_PROFILE.put(5, 5);
        MP4A_AUDIO_OBJECT_TYPE_TO_PROFILE.put(6, 6);
        MP4A_AUDIO_OBJECT_TYPE_TO_PROFILE.put(17, 17);
        MP4A_AUDIO_OBJECT_TYPE_TO_PROFILE.put(20, 20);
        MP4A_AUDIO_OBJECT_TYPE_TO_PROFILE.put(23, 23);
        MP4A_AUDIO_OBJECT_TYPE_TO_PROFILE.put(29, 29);
        MP4A_AUDIO_OBJECT_TYPE_TO_PROFILE.put(39, 39);
        MP4A_AUDIO_OBJECT_TYPE_TO_PROFILE.put(42, 42);
    }

    private MediaCodecUtil() {
    }

    public static void warmDecoderInfoCache(String str, boolean z) {
        try {
            getDecoderInfos(str, z);
        } catch (DecoderQueryException e) {
            Log.e(TAG, "Codec warming failed", e);
        }
    }

    @Nullable
    public static MediaCodecInfo getPassthroughDecoderInfo() throws DecoderQueryException {
        MediaCodecInfo decoderInfo = getDecoderInfo(MimeTypes.AUDIO_RAW, false);
        if (decoderInfo == null) {
            return null;
        }
        return MediaCodecInfo.newPassthroughInstance(decoderInfo.name);
    }

    @Nullable
    public static MediaCodecInfo getDecoderInfo(String str, boolean z) throws DecoderQueryException {
        List decoderInfos = getDecoderInfos(str, z);
        return decoderInfos.isEmpty() ? null : (MediaCodecInfo) decoderInfos.get(0);
    }

    public static synchronized List<MediaCodecInfo> getDecoderInfos(String str, boolean z) throws DecoderQueryException {
        synchronized (MediaCodecUtil.class) {
            CodecKey codecKey = new CodecKey(str, z);
            List list = (List) decoderInfosCache.get(codecKey);
            if (list != null) {
                return list;
            }
            MediaCodecListCompat mediaCodecListCompatV21 = Util.SDK_INT >= 21 ? new MediaCodecListCompatV21(z) : new MediaCodecListCompatV16();
            ArrayList decoderInfosInternal = getDecoderInfosInternal(codecKey, mediaCodecListCompatV21, str);
            if (z && decoderInfosInternal.isEmpty() && 21 <= Util.SDK_INT && Util.SDK_INT <= 23) {
                mediaCodecListCompatV21 = new MediaCodecListCompatV16();
                decoderInfosInternal = getDecoderInfosInternal(codecKey, mediaCodecListCompatV21, str);
                if (!decoderInfosInternal.isEmpty()) {
                    String str2 = TAG;
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("MediaCodecList API didn't list secure decoder for: ");
                    stringBuilder.append(str);
                    stringBuilder.append(". Assuming: ");
                    stringBuilder.append(((MediaCodecInfo) decoderInfosInternal.get(0)).name);
                    Log.w(str2, stringBuilder.toString());
                }
            }
            if (MimeTypes.AUDIO_E_AC3_JOC.equals(str)) {
                decoderInfosInternal.addAll(getDecoderInfosInternal(new CodecKey(MimeTypes.AUDIO_E_AC3, codecKey.secure), mediaCodecListCompatV21, str));
            }
            applyWorkarounds(str, decoderInfosInternal);
            List unmodifiableList = Collections.unmodifiableList(decoderInfosInternal);
            decoderInfosCache.put(codecKey, unmodifiableList);
            return unmodifiableList;
        }
    }

    public static int maxH264DecodableFrameSize() throws DecoderQueryException {
        if (maxH264DecodableFrameSize == -1) {
            int i = 0;
            MediaCodecInfo decoderInfo = getDecoderInfo(MimeTypes.VIDEO_H264, false);
            if (decoderInfo != null) {
                CodecProfileLevel[] profileLevels = decoderInfo.getProfileLevels();
                int length = profileLevels.length;
                int i2 = 0;
                while (i < length) {
                    i2 = Math.max(avcLevelToMaxFrameSize(profileLevels[i].level), i2);
                    i++;
                }
                i = Math.max(i2, Util.SDK_INT >= 21 ? 345600 : 172800);
            }
            maxH264DecodableFrameSize = i;
        }
        return maxH264DecodableFrameSize;
    }

    /* JADX WARNING: Missing block: B:12:0x0030, code skipped:
            if (r3.equals(CODEC_ID_HEV1) != false) goto L_0x0048;
     */
    @android.support.annotation.Nullable
    public static android.util.Pair<java.lang.Integer, java.lang.Integer> getCodecProfileAndLevel(java.lang.String r6) {
        /*
        r0 = 0;
        if (r6 != 0) goto L_0x0004;
    L_0x0003:
        return r0;
    L_0x0004:
        r1 = "\\.";
        r1 = r6.split(r1);
        r2 = 0;
        r3 = r1[r2];
        r4 = -1;
        r5 = r3.hashCode();
        switch(r5) {
            case 3006243: goto L_0x003d;
            case 3006244: goto L_0x0033;
            case 3199032: goto L_0x002a;
            case 3214780: goto L_0x0020;
            case 3356560: goto L_0x0016;
            default: goto L_0x0015;
        };
    L_0x0015:
        goto L_0x0047;
    L_0x0016:
        r2 = "mp4a";
        r2 = r3.equals(r2);
        if (r2 == 0) goto L_0x0047;
    L_0x001e:
        r2 = 4;
        goto L_0x0048;
    L_0x0020:
        r2 = "hvc1";
        r2 = r3.equals(r2);
        if (r2 == 0) goto L_0x0047;
    L_0x0028:
        r2 = 1;
        goto L_0x0048;
    L_0x002a:
        r5 = "hev1";
        r3 = r3.equals(r5);
        if (r3 == 0) goto L_0x0047;
    L_0x0032:
        goto L_0x0048;
    L_0x0033:
        r2 = "avc2";
        r2 = r3.equals(r2);
        if (r2 == 0) goto L_0x0047;
    L_0x003b:
        r2 = 3;
        goto L_0x0048;
    L_0x003d:
        r2 = "avc1";
        r2 = r3.equals(r2);
        if (r2 == 0) goto L_0x0047;
    L_0x0045:
        r2 = 2;
        goto L_0x0048;
    L_0x0047:
        r2 = r4;
    L_0x0048:
        switch(r2) {
            case 0: goto L_0x0056;
            case 1: goto L_0x0056;
            case 2: goto L_0x0051;
            case 3: goto L_0x0051;
            case 4: goto L_0x004c;
            default: goto L_0x004b;
        };
    L_0x004b:
        return r0;
    L_0x004c:
        r6 = getAacCodecProfileAndLevel(r6, r1);
        return r6;
    L_0x0051:
        r6 = getAvcProfileAndLevel(r6, r1);
        return r6;
    L_0x0056:
        r6 = getHevcProfileAndLevel(r6, r1);
        return r6;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.mediacodec.MediaCodecUtil.getCodecProfileAndLevel(java.lang.String):android.util.Pair");
    }

    /* JADX WARNING: Removed duplicated region for block: B:55:0x00b2 A:{SYNTHETIC, SKIP} */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x0090 A:{Catch:{ Exception -> 0x00ef }} */
    private static java.util.ArrayList<com.google.android.exoplayer2.mediacodec.MediaCodecInfo> getDecoderInfosInternal(com.google.android.exoplayer2.mediacodec.MediaCodecUtil.CodecKey r20, com.google.android.exoplayer2.mediacodec.MediaCodecUtil.MediaCodecListCompat r21, java.lang.String r22) throws com.google.android.exoplayer2.mediacodec.MediaCodecUtil.DecoderQueryException {
        /*
        r1 = r20;
        r2 = r21;
        r3 = new java.util.ArrayList;	 Catch:{ Exception -> 0x00ef }
        r3.<init>();	 Catch:{ Exception -> 0x00ef }
        r4 = r1.mimeType;	 Catch:{ Exception -> 0x00ef }
        r5 = r21.getCodecCount();	 Catch:{ Exception -> 0x00ef }
        r6 = r21.secureDecodersExplicit();	 Catch:{ Exception -> 0x00ef }
        r8 = 0;
    L_0x0014:
        if (r8 >= r5) goto L_0x00ee;
    L_0x0016:
        r9 = r2.getCodecInfoAt(r8);	 Catch:{ Exception -> 0x00ef }
        r10 = r9.getName();	 Catch:{ Exception -> 0x00ef }
        r11 = r22;
        r12 = isCodecUsableDecoder(r9, r10, r6, r11);	 Catch:{ Exception -> 0x00ef }
        if (r12 == 0) goto L_0x00e4;
    L_0x0026:
        r12 = r9.getSupportedTypes();	 Catch:{ Exception -> 0x00ef }
        r13 = r12.length;	 Catch:{ Exception -> 0x00ef }
        r14 = 0;
    L_0x002c:
        if (r14 >= r13) goto L_0x00e4;
    L_0x002e:
        r15 = r12[r14];	 Catch:{ Exception -> 0x00ef }
        r16 = r15.equalsIgnoreCase(r4);	 Catch:{ Exception -> 0x00ef }
        if (r16 == 0) goto L_0x00d6;
    L_0x0036:
        r7 = r9.getCapabilitiesForType(r15);	 Catch:{ Exception -> 0x0084 }
        r17 = r5;
        r5 = r2.isSecurePlaybackSupported(r4, r7);	 Catch:{ Exception -> 0x0082 }
        r2 = codecNeedsDisableAdaptationWorkaround(r10);	 Catch:{ Exception -> 0x0082 }
        if (r6 == 0) goto L_0x0051;
    L_0x0046:
        r18 = r9;
        r9 = r1.secure;	 Catch:{ Exception -> 0x004f }
        if (r9 == r5) goto L_0x004d;
    L_0x004c:
        goto L_0x0053;
    L_0x004d:
        r9 = 0;
        goto L_0x005a;
    L_0x004f:
        r0 = move-exception;
        goto L_0x0089;
    L_0x0051:
        r18 = r9;
    L_0x0053:
        if (r6 != 0) goto L_0x0063;
    L_0x0055:
        r9 = r1.secure;	 Catch:{ Exception -> 0x004f }
        if (r9 != 0) goto L_0x0063;
    L_0x0059:
        goto L_0x004d;
    L_0x005a:
        r2 = com.google.android.exoplayer2.mediacodec.MediaCodecInfo.newInstance(r10, r4, r7, r2, r9);	 Catch:{ Exception -> 0x004f }
        r3.add(r2);	 Catch:{ Exception -> 0x004f }
        goto L_0x00da;
    L_0x0063:
        r9 = 0;
        if (r6 != 0) goto L_0x00da;
    L_0x0066:
        if (r5 == 0) goto L_0x00da;
    L_0x0068:
        r5 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x004f }
        r5.<init>();	 Catch:{ Exception -> 0x004f }
        r5.append(r10);	 Catch:{ Exception -> 0x004f }
        r9 = ".secure";
        r5.append(r9);	 Catch:{ Exception -> 0x004f }
        r5 = r5.toString();	 Catch:{ Exception -> 0x004f }
        r9 = 1;
        r2 = com.google.android.exoplayer2.mediacodec.MediaCodecInfo.newInstance(r5, r4, r7, r2, r9);	 Catch:{ Exception -> 0x004f }
        r3.add(r2);	 Catch:{ Exception -> 0x004f }
        return r3;
    L_0x0082:
        r0 = move-exception;
        goto L_0x0087;
    L_0x0084:
        r0 = move-exception;
        r17 = r5;
    L_0x0087:
        r18 = r9;
    L_0x0089:
        r2 = r0;
        r5 = com.google.android.exoplayer2.util.Util.SDK_INT;	 Catch:{ Exception -> 0x00ef }
        r7 = 23;
        if (r5 > r7) goto L_0x00b2;
    L_0x0090:
        r5 = r3.isEmpty();	 Catch:{ Exception -> 0x00ef }
        if (r5 != 0) goto L_0x00b2;
    L_0x0096:
        r2 = "MediaCodecUtil";
        r5 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x00ef }
        r5.<init>();	 Catch:{ Exception -> 0x00ef }
        r7 = "Skipping codec ";
        r5.append(r7);	 Catch:{ Exception -> 0x00ef }
        r5.append(r10);	 Catch:{ Exception -> 0x00ef }
        r7 = " (failed to query capabilities)";
        r5.append(r7);	 Catch:{ Exception -> 0x00ef }
        r5 = r5.toString();	 Catch:{ Exception -> 0x00ef }
        com.google.android.exoplayer2.util.Log.e(r2, r5);	 Catch:{ Exception -> 0x00ef }
        goto L_0x00da;
    L_0x00b2:
        r1 = "MediaCodecUtil";
        r3 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x00ef }
        r3.<init>();	 Catch:{ Exception -> 0x00ef }
        r4 = "Failed to query codec ";
        r3.append(r4);	 Catch:{ Exception -> 0x00ef }
        r3.append(r10);	 Catch:{ Exception -> 0x00ef }
        r4 = " (";
        r3.append(r4);	 Catch:{ Exception -> 0x00ef }
        r3.append(r15);	 Catch:{ Exception -> 0x00ef }
        r4 = ")";
        r3.append(r4);	 Catch:{ Exception -> 0x00ef }
        r3 = r3.toString();	 Catch:{ Exception -> 0x00ef }
        com.google.android.exoplayer2.util.Log.e(r1, r3);	 Catch:{ Exception -> 0x00ef }
        throw r2;	 Catch:{ Exception -> 0x00ef }
    L_0x00d6:
        r17 = r5;
        r18 = r9;
    L_0x00da:
        r14 = r14 + 1;
        r5 = r17;
        r9 = r18;
        r2 = r21;
        goto L_0x002c;
    L_0x00e4:
        r17 = r5;
        r8 = r8 + 1;
        r5 = r17;
        r2 = r21;
        goto L_0x0014;
    L_0x00ee:
        return r3;
    L_0x00ef:
        r0 = move-exception;
        r1 = r0;
        r2 = new com.google.android.exoplayer2.mediacodec.MediaCodecUtil$DecoderQueryException;
        r3 = 0;
        r2.<init>(r1);
        throw r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.mediacodec.MediaCodecUtil.getDecoderInfosInternal(com.google.android.exoplayer2.mediacodec.MediaCodecUtil$CodecKey, com.google.android.exoplayer2.mediacodec.MediaCodecUtil$MediaCodecListCompat, java.lang.String):java.util.ArrayList");
    }

    private static boolean isCodecUsableDecoder(MediaCodecInfo mediaCodecInfo, String str, boolean z, String str2) {
        if (mediaCodecInfo.isEncoder() || (!z && str.endsWith(".secure"))) {
            return false;
        }
        if (Util.SDK_INT < 21 && ("CIPAACDecoder".equals(str) || "CIPMP3Decoder".equals(str) || "CIPVorbisDecoder".equals(str) || "CIPAMRNBDecoder".equals(str) || "AACDecoder".equals(str) || "MP3Decoder".equals(str))) {
            return false;
        }
        if (Util.SDK_INT < 18 && "OMX.SEC.MP3.Decoder".equals(str)) {
            return false;
        }
        if ("OMX.SEC.mp3.dec".equals(str) && (Util.MODEL.startsWith("GT-I9152") || Util.MODEL.startsWith("GT-I9515") || Util.MODEL.startsWith("GT-P5220") || Util.MODEL.startsWith("GT-S7580") || Util.MODEL.startsWith("SM-G350") || Util.MODEL.startsWith("SM-G386") || Util.MODEL.startsWith("SM-T231") || Util.MODEL.startsWith("SM-T530"))) {
            return false;
        }
        if ("OMX.brcm.audio.mp3.decoder".equals(str) && (Util.MODEL.startsWith("GT-I9152") || Util.MODEL.startsWith("GT-S7580") || Util.MODEL.startsWith("SM-G350"))) {
            return false;
        }
        if (Util.SDK_INT < 18 && "OMX.MTK.AUDIO.DECODER.AAC".equals(str) && ("a70".equals(Util.DEVICE) || ("Xiaomi".equals(Util.MANUFACTURER) && Util.DEVICE.startsWith("HM")))) {
            return false;
        }
        if (Util.SDK_INT == 16 && "OMX.qcom.audio.decoder.mp3".equals(str) && ("dlxu".equals(Util.DEVICE) || "protou".equals(Util.DEVICE) || "ville".equals(Util.DEVICE) || "villeplus".equals(Util.DEVICE) || "villec2".equals(Util.DEVICE) || Util.DEVICE.startsWith("gee") || "C6602".equals(Util.DEVICE) || "C6603".equals(Util.DEVICE) || "C6606".equals(Util.DEVICE) || "C6616".equals(Util.DEVICE) || "L36h".equals(Util.DEVICE) || "SO-02E".equals(Util.DEVICE))) {
            return false;
        }
        if (Util.SDK_INT == 16 && "OMX.qcom.audio.decoder.aac".equals(str) && ("C1504".equals(Util.DEVICE) || "C1505".equals(Util.DEVICE) || "C1604".equals(Util.DEVICE) || "C1605".equals(Util.DEVICE))) {
            return false;
        }
        if (Util.SDK_INT < 24 && (("OMX.SEC.aac.dec".equals(str) || "OMX.Exynos.AAC.Decoder".equals(str)) && "samsung".equals(Util.MANUFACTURER) && (Util.DEVICE.startsWith("zeroflte") || Util.DEVICE.startsWith("zerolte") || Util.DEVICE.startsWith("zenlte") || "SC-05G".equals(Util.DEVICE) || "marinelteatt".equals(Util.DEVICE) || "404SC".equals(Util.DEVICE) || "SC-04G".equals(Util.DEVICE) || "SCV31".equals(Util.DEVICE)))) {
            return false;
        }
        if (Util.SDK_INT <= 19 && "OMX.SEC.vp8.dec".equals(str) && "samsung".equals(Util.MANUFACTURER) && (Util.DEVICE.startsWith("d2") || Util.DEVICE.startsWith("serrano") || Util.DEVICE.startsWith("jflte") || Util.DEVICE.startsWith("santos") || Util.DEVICE.startsWith("t0"))) {
            return false;
        }
        if (Util.SDK_INT <= 19 && Util.DEVICE.startsWith("jflte") && "OMX.qcom.video.decoder.vp8".equals(str)) {
            return false;
        }
        if (MimeTypes.AUDIO_E_AC3_JOC.equals(str2) && "OMX.MTK.AUDIO.DECODER.DSPAC3".equals(str)) {
            return false;
        }
        return true;
    }

    private static void applyWorkarounds(String str, List<MediaCodecInfo> list) {
        if (MimeTypes.AUDIO_RAW.equals(str)) {
            Collections.sort(list, RAW_AUDIO_CODEC_COMPARATOR);
        }
    }

    private static boolean codecNeedsDisableAdaptationWorkaround(String str) {
        return Util.SDK_INT <= 22 && (("ODROID-XU3".equals(Util.MODEL) || "Nexus 10".equals(Util.MODEL)) && ("OMX.Exynos.AVC.Decoder".equals(str) || "OMX.Exynos.AVC.Decoder.secure".equals(str)));
    }

    private static Pair<Integer, Integer> getHevcProfileAndLevel(String str, String[] strArr) {
        String str2;
        StringBuilder stringBuilder;
        if (strArr.length < 4) {
            str2 = TAG;
            stringBuilder = new StringBuilder();
            stringBuilder.append("Ignoring malformed HEVC codec string: ");
            stringBuilder.append(str);
            Log.w(str2, stringBuilder.toString());
            return null;
        }
        Matcher matcher = PROFILE_PATTERN.matcher(strArr[1]);
        if (matcher.matches()) {
            int i;
            str = matcher.group(1);
            if ("1".equals(str)) {
                i = 1;
            } else if (InternalAvidAdSessionContext.AVID_API_LEVEL.equals(str)) {
                i = 2;
            } else {
                str2 = TAG;
                stringBuilder = new StringBuilder();
                stringBuilder.append("Unknown HEVC profile string: ");
                stringBuilder.append(str);
                Log.w(str2, stringBuilder.toString());
                return null;
            }
            Integer num = (Integer) HEVC_CODEC_STRING_TO_PROFILE_LEVEL.get(strArr[3]);
            if (num != null) {
                return new Pair(Integer.valueOf(i), num);
            }
            str = TAG;
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("Unknown HEVC level string: ");
            stringBuilder2.append(matcher.group(1));
            Log.w(str, stringBuilder2.toString());
            return null;
        }
        str2 = TAG;
        stringBuilder = new StringBuilder();
        stringBuilder.append("Ignoring malformed HEVC codec string: ");
        stringBuilder.append(str);
        Log.w(str2, stringBuilder.toString());
        return null;
    }

    private static Pair<Integer, Integer> getAvcProfileAndLevel(String str, String[] strArr) {
        String str2;
        StringBuilder stringBuilder;
        if (strArr.length < 2) {
            str2 = TAG;
            stringBuilder = new StringBuilder();
            stringBuilder.append("Ignoring malformed AVC codec string: ");
            stringBuilder.append(str);
            Log.w(str2, stringBuilder.toString());
            return null;
        }
        try {
            Integer valueOf;
            Integer valueOf2;
            if (strArr[1].length() == 6) {
                valueOf = Integer.valueOf(Integer.parseInt(strArr[1].substring(0, 2), 16));
                valueOf2 = Integer.valueOf(Integer.parseInt(strArr[1].substring(4), 16));
            } else if (strArr.length >= 3) {
                Integer valueOf3 = Integer.valueOf(Integer.parseInt(strArr[1]));
                valueOf2 = Integer.valueOf(Integer.parseInt(strArr[2]));
                valueOf = valueOf3;
            } else {
                str2 = TAG;
                stringBuilder = new StringBuilder();
                stringBuilder.append("Ignoring malformed AVC codec string: ");
                stringBuilder.append(str);
                Log.w(str2, stringBuilder.toString());
                return null;
            }
            int i = AVC_PROFILE_NUMBER_TO_CONST.get(valueOf.intValue(), -1);
            if (i == -1) {
                str = TAG;
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append("Unknown AVC profile: ");
                stringBuilder2.append(valueOf);
                Log.w(str, stringBuilder2.toString());
                return null;
            }
            int i2 = AVC_LEVEL_NUMBER_TO_CONST.get(valueOf2.intValue(), -1);
            if (i2 != -1) {
                return new Pair(Integer.valueOf(i), Integer.valueOf(i2));
            }
            str = TAG;
            stringBuilder = new StringBuilder();
            stringBuilder.append("Unknown AVC level: ");
            stringBuilder.append(valueOf2);
            Log.w(str, stringBuilder.toString());
            return null;
        } catch (NumberFormatException unused) {
            str2 = TAG;
            stringBuilder = new StringBuilder();
            stringBuilder.append("Ignoring malformed AVC codec string: ");
            stringBuilder.append(str);
            Log.w(str2, stringBuilder.toString());
            return null;
        }
    }

    @Nullable
    private static Pair<Integer, Integer> getAacCodecProfileAndLevel(String str, String[] strArr) {
        String str2;
        StringBuilder stringBuilder;
        if (strArr.length != 3) {
            str2 = TAG;
            stringBuilder = new StringBuilder();
            stringBuilder.append("Ignoring malformed MP4A codec string: ");
            stringBuilder.append(str);
            Log.w(str2, stringBuilder.toString());
            return null;
        }
        try {
            if (MimeTypes.AUDIO_AAC.equals(MimeTypes.getMimeTypeFromMp4ObjectType(Integer.parseInt(strArr[1], 16)))) {
                int i = MP4A_AUDIO_OBJECT_TYPE_TO_PROFILE.get(Integer.parseInt(strArr[2]), -1);
                if (i != -1) {
                    return new Pair(Integer.valueOf(i), Integer.valueOf(0));
                }
            }
        } catch (NumberFormatException unused) {
            str2 = TAG;
            stringBuilder = new StringBuilder();
            stringBuilder.append("Ignoring malformed MP4A codec string: ");
            stringBuilder.append(str);
            Log.w(str2, stringBuilder.toString());
        }
        return null;
    }
}
