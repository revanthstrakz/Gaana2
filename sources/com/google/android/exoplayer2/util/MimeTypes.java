package com.google.android.exoplayer2.util;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import java.util.ArrayList;

public final class MimeTypes {
    public static final String APPLICATION_CAMERA_MOTION = "application/x-camera-motion";
    public static final String APPLICATION_CEA608 = "application/cea-608";
    public static final String APPLICATION_CEA708 = "application/cea-708";
    public static final String APPLICATION_DVBSUBS = "application/dvbsubs";
    public static final String APPLICATION_EMSG = "application/x-emsg";
    public static final String APPLICATION_EXIF = "application/x-exif";
    public static final String APPLICATION_ID3 = "application/id3";
    public static final String APPLICATION_M3U8 = "application/x-mpegURL";
    public static final String APPLICATION_MP4 = "application/mp4";
    public static final String APPLICATION_MP4CEA608 = "application/x-mp4-cea-608";
    public static final String APPLICATION_MP4VTT = "application/x-mp4-vtt";
    public static final String APPLICATION_MPD = "application/dash+xml";
    public static final String APPLICATION_PGS = "application/pgs";
    public static final String APPLICATION_RAWCC = "application/x-rawcc";
    public static final String APPLICATION_SCTE35 = "application/x-scte35";
    public static final String APPLICATION_SS = "application/vnd.ms-sstr+xml";
    public static final String APPLICATION_SUBRIP = "application/x-subrip";
    public static final String APPLICATION_TTML = "application/ttml+xml";
    public static final String APPLICATION_TX3G = "application/x-quicktime-tx3g";
    public static final String APPLICATION_VOBSUB = "application/vobsub";
    public static final String APPLICATION_WEBM = "application/webm";
    public static final String AUDIO_AAC = "audio/mp4a-latm";
    public static final String AUDIO_AC3 = "audio/ac3";
    public static final String AUDIO_ALAC = "audio/alac";
    public static final String AUDIO_ALAW = "audio/g711-alaw";
    public static final String AUDIO_AMR_NB = "audio/3gpp";
    public static final String AUDIO_AMR_WB = "audio/amr-wb";
    public static final String AUDIO_DTS = "audio/vnd.dts";
    public static final String AUDIO_DTS_EXPRESS = "audio/vnd.dts.hd;profile=lbr";
    public static final String AUDIO_DTS_HD = "audio/vnd.dts.hd";
    public static final String AUDIO_E_AC3 = "audio/eac3";
    public static final String AUDIO_E_AC3_JOC = "audio/eac3-joc";
    public static final String AUDIO_FLAC = "audio/flac";
    public static final String AUDIO_MLAW = "audio/g711-mlaw";
    public static final String AUDIO_MP4 = "audio/mp4";
    public static final String AUDIO_MPEG = "audio/mpeg";
    public static final String AUDIO_MPEG_L1 = "audio/mpeg-L1";
    public static final String AUDIO_MPEG_L2 = "audio/mpeg-L2";
    public static final String AUDIO_MSGSM = "audio/gsm";
    public static final String AUDIO_OPUS = "audio/opus";
    public static final String AUDIO_RAW = "audio/raw";
    public static final String AUDIO_TRUEHD = "audio/true-hd";
    public static final String AUDIO_UNKNOWN = "audio/x-unknown";
    public static final String AUDIO_VORBIS = "audio/vorbis";
    public static final String AUDIO_WEBM = "audio/webm";
    public static final String BASE_TYPE_APPLICATION = "application";
    public static final String BASE_TYPE_AUDIO = "audio";
    public static final String BASE_TYPE_TEXT = "text";
    public static final String BASE_TYPE_VIDEO = "video";
    public static final String TEXT_SSA = "text/x-ssa";
    public static final String TEXT_VTT = "text/vtt";
    public static final String VIDEO_H263 = "video/3gpp";
    public static final String VIDEO_H264 = "video/avc";
    public static final String VIDEO_H265 = "video/hevc";
    public static final String VIDEO_MP4 = "video/mp4";
    public static final String VIDEO_MP4V = "video/mp4v-es";
    public static final String VIDEO_MPEG = "video/mpeg";
    public static final String VIDEO_MPEG2 = "video/mpeg2";
    public static final String VIDEO_UNKNOWN = "video/x-unknown";
    public static final String VIDEO_VC1 = "video/wvc1";
    public static final String VIDEO_VP8 = "video/x-vnd.on2.vp8";
    public static final String VIDEO_VP9 = "video/x-vnd.on2.vp9";
    public static final String VIDEO_WEBM = "video/webm";
    private static final ArrayList<CustomMimeType> customMimeTypes = new ArrayList();

    private static final class CustomMimeType {
        public final String codecPrefix;
        public final String mimeType;
        public final int trackType;

        public CustomMimeType(String str, String str2, int i) {
            this.mimeType = str;
            this.codecPrefix = str2;
            this.trackType = i;
        }
    }

    @Nullable
    public static String getMimeTypeFromMp4ObjectType(int i) {
        if (i == 35) {
            return VIDEO_H265;
        }
        if (i != 64) {
            if (i == 163) {
                return VIDEO_VC1;
            }
            if (i == 177) {
                return VIDEO_VP9;
            }
            switch (i) {
                case 32:
                    return VIDEO_MP4V;
                case 33:
                    return VIDEO_H264;
                default:
                    switch (i) {
                        case 96:
                        case 97:
                        case 98:
                        case 99:
                        case 100:
                        case 101:
                            return VIDEO_MPEG2;
                        case 102:
                        case 103:
                        case 104:
                            break;
                        case 105:
                        case 107:
                            return AUDIO_MPEG;
                        case 106:
                            return VIDEO_MPEG;
                        default:
                            switch (i) {
                                case 165:
                                    return AUDIO_AC3;
                                case 166:
                                    return AUDIO_E_AC3;
                                default:
                                    switch (i) {
                                        case 169:
                                        case 172:
                                            return AUDIO_DTS;
                                        case 170:
                                        case 171:
                                            return AUDIO_DTS_HD;
                                        case 173:
                                            return AUDIO_OPUS;
                                        default:
                                            return null;
                                    }
                            }
                    }
            }
        }
        return AUDIO_AAC;
    }

    public static void registerCustomMimeType(String str, String str2, int i) {
        CustomMimeType customMimeType = new CustomMimeType(str, str2, i);
        int size = customMimeTypes.size();
        for (i = 0; i < size; i++) {
            if (str.equals(((CustomMimeType) customMimeTypes.get(i)).mimeType)) {
                customMimeTypes.remove(i);
                break;
            }
        }
        customMimeTypes.add(customMimeType);
    }

    public static boolean isAudio(@Nullable String str) {
        return "audio".equals(getTopLevelType(str));
    }

    public static boolean isVideo(@Nullable String str) {
        return "video".equals(getTopLevelType(str));
    }

    public static boolean isText(@Nullable String str) {
        return BASE_TYPE_TEXT.equals(getTopLevelType(str));
    }

    public static boolean isApplication(@Nullable String str) {
        return BASE_TYPE_APPLICATION.equals(getTopLevelType(str));
    }

    @Nullable
    public static String getVideoMediaMimeType(@Nullable String str) {
        if (str == null) {
            return null;
        }
        for (String mediaMimeType : Util.splitCodecs(str)) {
            String mediaMimeType2 = getMediaMimeType(mediaMimeType2);
            if (mediaMimeType2 != null && isVideo(mediaMimeType2)) {
                return mediaMimeType2;
            }
        }
        return null;
    }

    @Nullable
    public static String getAudioMediaMimeType(@Nullable String str) {
        if (str == null) {
            return null;
        }
        for (String mediaMimeType : Util.splitCodecs(str)) {
            String mediaMimeType2 = getMediaMimeType(mediaMimeType2);
            if (mediaMimeType2 != null && isAudio(mediaMimeType2)) {
                return mediaMimeType2;
            }
        }
        return null;
    }

    /* JADX WARNING: Removed duplicated region for block: B:31:0x0087  */
    @android.support.annotation.Nullable
    public static java.lang.String getMediaMimeType(@android.support.annotation.Nullable java.lang.String r3) {
        /*
        r0 = 0;
        if (r3 != 0) goto L_0x0004;
    L_0x0003:
        return r0;
    L_0x0004:
        r3 = r3.trim();
        r3 = com.google.android.exoplayer2.util.Util.toLowerInvariant(r3);
        r1 = "avc1";
        r1 = r3.startsWith(r1);
        if (r1 != 0) goto L_0x0115;
    L_0x0014:
        r1 = "avc3";
        r1 = r3.startsWith(r1);
        if (r1 == 0) goto L_0x001e;
    L_0x001c:
        goto L_0x0115;
    L_0x001e:
        r1 = "hev1";
        r1 = r3.startsWith(r1);
        if (r1 != 0) goto L_0x0112;
    L_0x0026:
        r1 = "hvc1";
        r1 = r3.startsWith(r1);
        if (r1 == 0) goto L_0x0030;
    L_0x002e:
        goto L_0x0112;
    L_0x0030:
        r1 = "vp9";
        r1 = r3.startsWith(r1);
        if (r1 != 0) goto L_0x010f;
    L_0x0038:
        r1 = "vp09";
        r1 = r3.startsWith(r1);
        if (r1 == 0) goto L_0x0042;
    L_0x0040:
        goto L_0x010f;
    L_0x0042:
        r1 = "vp8";
        r1 = r3.startsWith(r1);
        if (r1 != 0) goto L_0x010c;
    L_0x004a:
        r1 = "vp08";
        r1 = r3.startsWith(r1);
        if (r1 == 0) goto L_0x0054;
    L_0x0052:
        goto L_0x010c;
    L_0x0054:
        r1 = "mp4a";
        r1 = r3.startsWith(r1);
        if (r1 == 0) goto L_0x008a;
    L_0x005c:
        r1 = "mp4a.";
        r1 = r3.startsWith(r1);
        if (r1 == 0) goto L_0x0084;
    L_0x0064:
        r1 = 5;
        r3 = r3.substring(r1);
        r1 = r3.length();
        r2 = 2;
        if (r1 < r2) goto L_0x0084;
    L_0x0070:
        r1 = 0;
        r3 = r3.substring(r1, r2);	 Catch:{ NumberFormatException -> 0x0084 }
        r3 = com.google.android.exoplayer2.util.Util.toUpperInvariant(r3);	 Catch:{ NumberFormatException -> 0x0084 }
        r1 = 16;
        r3 = java.lang.Integer.parseInt(r3, r1);	 Catch:{ NumberFormatException -> 0x0084 }
        r3 = getMimeTypeFromMp4ObjectType(r3);	 Catch:{ NumberFormatException -> 0x0084 }
        goto L_0x0085;
    L_0x0084:
        r3 = r0;
    L_0x0085:
        if (r3 != 0) goto L_0x0089;
    L_0x0087:
        r3 = "audio/mp4a-latm";
    L_0x0089:
        return r3;
    L_0x008a:
        r0 = "ac-3";
        r0 = r3.startsWith(r0);
        if (r0 != 0) goto L_0x0109;
    L_0x0092:
        r0 = "dac3";
        r0 = r3.startsWith(r0);
        if (r0 == 0) goto L_0x009c;
    L_0x009a:
        goto L_0x0109;
    L_0x009c:
        r0 = "ec-3";
        r0 = r3.startsWith(r0);
        if (r0 != 0) goto L_0x0106;
    L_0x00a4:
        r0 = "dec3";
        r0 = r3.startsWith(r0);
        if (r0 == 0) goto L_0x00ad;
    L_0x00ac:
        goto L_0x0106;
    L_0x00ad:
        r0 = "ec+3";
        r0 = r3.startsWith(r0);
        if (r0 == 0) goto L_0x00b8;
    L_0x00b5:
        r3 = "audio/eac3-joc";
        return r3;
    L_0x00b8:
        r0 = "dtsc";
        r0 = r3.startsWith(r0);
        if (r0 != 0) goto L_0x0103;
    L_0x00c0:
        r0 = "dtse";
        r0 = r3.startsWith(r0);
        if (r0 == 0) goto L_0x00c9;
    L_0x00c8:
        goto L_0x0103;
    L_0x00c9:
        r0 = "dtsh";
        r0 = r3.startsWith(r0);
        if (r0 != 0) goto L_0x0100;
    L_0x00d1:
        r0 = "dtsl";
        r0 = r3.startsWith(r0);
        if (r0 == 0) goto L_0x00da;
    L_0x00d9:
        goto L_0x0100;
    L_0x00da:
        r0 = "opus";
        r0 = r3.startsWith(r0);
        if (r0 == 0) goto L_0x00e5;
    L_0x00e2:
        r3 = "audio/opus";
        return r3;
    L_0x00e5:
        r0 = "vorbis";
        r0 = r3.startsWith(r0);
        if (r0 == 0) goto L_0x00f0;
    L_0x00ed:
        r3 = "audio/vorbis";
        return r3;
    L_0x00f0:
        r0 = "flac";
        r0 = r3.startsWith(r0);
        if (r0 == 0) goto L_0x00fb;
    L_0x00f8:
        r3 = "audio/flac";
        return r3;
    L_0x00fb:
        r3 = getCustomMimeTypeForCodec(r3);
        return r3;
    L_0x0100:
        r3 = "audio/vnd.dts.hd";
        return r3;
    L_0x0103:
        r3 = "audio/vnd.dts";
        return r3;
    L_0x0106:
        r3 = "audio/eac3";
        return r3;
    L_0x0109:
        r3 = "audio/ac3";
        return r3;
    L_0x010c:
        r3 = "video/x-vnd.on2.vp8";
        return r3;
    L_0x010f:
        r3 = "video/x-vnd.on2.vp9";
        return r3;
    L_0x0112:
        r3 = "video/hevc";
        return r3;
    L_0x0115:
        r3 = "video/avc";
        return r3;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.util.MimeTypes.getMediaMimeType(java.lang.String):java.lang.String");
    }

    public static int getTrackType(@Nullable String str) {
        if (TextUtils.isEmpty(str)) {
            return -1;
        }
        if (isAudio(str)) {
            return 1;
        }
        if (isVideo(str)) {
            return 2;
        }
        if (isText(str) || APPLICATION_CEA608.equals(str) || APPLICATION_CEA708.equals(str) || APPLICATION_MP4CEA608.equals(str) || APPLICATION_SUBRIP.equals(str) || APPLICATION_TTML.equals(str) || APPLICATION_TX3G.equals(str) || APPLICATION_MP4VTT.equals(str) || APPLICATION_RAWCC.equals(str) || APPLICATION_VOBSUB.equals(str) || APPLICATION_PGS.equals(str) || APPLICATION_DVBSUBS.equals(str)) {
            return 3;
        }
        if (APPLICATION_ID3.equals(str) || APPLICATION_EMSG.equals(str) || APPLICATION_SCTE35.equals(str)) {
            return 4;
        }
        if (APPLICATION_CAMERA_MOTION.equals(str)) {
            return 5;
        }
        return getTrackTypeForCustomMimeType(str);
    }

    public static int getEncoding(java.lang.String r3) {
        /*
        r0 = r3.hashCode();
        r1 = 0;
        r2 = 5;
        switch(r0) {
            case -2123537834: goto L_0x003c;
            case -1095064472: goto L_0x0032;
            case 187078296: goto L_0x0028;
            case 1504578661: goto L_0x001e;
            case 1505942594: goto L_0x0014;
            case 1556697186: goto L_0x000a;
            default: goto L_0x0009;
        };
    L_0x0009:
        goto L_0x0046;
    L_0x000a:
        r0 = "audio/true-hd";
        r3 = r3.equals(r0);
        if (r3 == 0) goto L_0x0046;
    L_0x0012:
        r3 = r2;
        goto L_0x0047;
    L_0x0014:
        r0 = "audio/vnd.dts.hd";
        r3 = r3.equals(r0);
        if (r3 == 0) goto L_0x0046;
    L_0x001c:
        r3 = 4;
        goto L_0x0047;
    L_0x001e:
        r0 = "audio/eac3";
        r3 = r3.equals(r0);
        if (r3 == 0) goto L_0x0046;
    L_0x0026:
        r3 = 1;
        goto L_0x0047;
    L_0x0028:
        r0 = "audio/ac3";
        r3 = r3.equals(r0);
        if (r3 == 0) goto L_0x0046;
    L_0x0030:
        r3 = r1;
        goto L_0x0047;
    L_0x0032:
        r0 = "audio/vnd.dts";
        r3 = r3.equals(r0);
        if (r3 == 0) goto L_0x0046;
    L_0x003a:
        r3 = 3;
        goto L_0x0047;
    L_0x003c:
        r0 = "audio/eac3-joc";
        r3 = r3.equals(r0);
        if (r3 == 0) goto L_0x0046;
    L_0x0044:
        r3 = 2;
        goto L_0x0047;
    L_0x0046:
        r3 = -1;
    L_0x0047:
        switch(r3) {
            case 0: goto L_0x0055;
            case 1: goto L_0x0053;
            case 2: goto L_0x0053;
            case 3: goto L_0x0051;
            case 4: goto L_0x004e;
            case 5: goto L_0x004b;
            default: goto L_0x004a;
        };
    L_0x004a:
        return r1;
    L_0x004b:
        r3 = 14;
        return r3;
    L_0x004e:
        r3 = 8;
        return r3;
    L_0x0051:
        r3 = 7;
        return r3;
    L_0x0053:
        r3 = 6;
        return r3;
    L_0x0055:
        return r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.util.MimeTypes.getEncoding(java.lang.String):int");
    }

    public static int getTrackTypeOfCodec(String str) {
        return getTrackType(getMediaMimeType(str));
    }

    @Nullable
    private static String getTopLevelType(@Nullable String str) {
        if (str == null) {
            return null;
        }
        int indexOf = str.indexOf(47);
        if (indexOf != -1) {
            return str.substring(0, indexOf);
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Invalid mime type: ");
        stringBuilder.append(str);
        throw new IllegalArgumentException(stringBuilder.toString());
    }

    @Nullable
    private static String getCustomMimeTypeForCodec(String str) {
        int size = customMimeTypes.size();
        for (int i = 0; i < size; i++) {
            CustomMimeType customMimeType = (CustomMimeType) customMimeTypes.get(i);
            if (str.startsWith(customMimeType.codecPrefix)) {
                return customMimeType.mimeType;
            }
        }
        return null;
    }

    private static int getTrackTypeForCustomMimeType(String str) {
        int size = customMimeTypes.size();
        for (int i = 0; i < size; i++) {
            CustomMimeType customMimeType = (CustomMimeType) customMimeTypes.get(i);
            if (str.equals(customMimeType.mimeType)) {
                return customMimeType.trackType;
            }
        }
        return -1;
    }

    private MimeTypes() {
    }
}
