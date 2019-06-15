package com.google.android.gms.internal.ads;

import com.google.android.exoplayer2.util.MimeTypes;

public final class zzpt {
    public static boolean zzab(String str) {
        return "audio".equals(zzaf(str));
    }

    public static boolean zzac(String str) {
        return "video".equals(zzaf(str));
    }

    public static boolean zzad(String str) {
        return MimeTypes.BASE_TYPE_TEXT.equals(zzaf(str));
    }

    public static String zzae(String str) {
        if (str == null) {
            return null;
        }
        str = str.trim();
        if (str.startsWith("avc1") || str.startsWith("avc3")) {
            return MimeTypes.VIDEO_H264;
        }
        if (str.startsWith("hev1") || str.startsWith("hvc1")) {
            return MimeTypes.VIDEO_H265;
        }
        if (str.startsWith("vp9")) {
            return MimeTypes.VIDEO_VP9;
        }
        if (str.startsWith("vp8")) {
            return MimeTypes.VIDEO_VP8;
        }
        if (str.startsWith("mp4a")) {
            return MimeTypes.AUDIO_AAC;
        }
        if (str.startsWith("ac-3") || str.startsWith("dac3")) {
            return MimeTypes.AUDIO_AC3;
        }
        if (str.startsWith("ec-3") || str.startsWith("dec3")) {
            return MimeTypes.AUDIO_E_AC3;
        }
        if (str.startsWith("dtsc") || str.startsWith("dtse")) {
            return MimeTypes.AUDIO_DTS;
        }
        if (str.startsWith("dtsh") || str.startsWith("dtsl")) {
            return MimeTypes.AUDIO_DTS_HD;
        }
        if (str.startsWith("opus")) {
            return MimeTypes.AUDIO_OPUS;
        }
        if (str.startsWith("vorbis")) {
            return MimeTypes.AUDIO_VORBIS;
        }
        return null;
    }

    private static String zzaf(String str) {
        if (str == null) {
            return null;
        }
        int indexOf = str.indexOf(47);
        if (indexOf != -1) {
            return str.substring(0, indexOf);
        }
        String str2 = "Invalid mime type: ";
        str = String.valueOf(str);
        throw new IllegalArgumentException(str.length() != 0 ? str2.concat(str) : new String(str2));
    }
}
