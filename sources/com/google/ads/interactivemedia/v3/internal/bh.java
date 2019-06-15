package com.google.ads.interactivemedia.v3.internal;

import android.annotation.TargetApi;
import android.media.MediaCodecInfo;
import android.media.MediaCodecInfo.CodecCapabilities;
import android.media.MediaCodecList;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.exoplayer2.util.MimeTypes;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@TargetApi(16)
public final class bh {
    private static final ay a = new ay("OMX.google.raw.decoder", null);
    private static final Map<a, List<ay>> b = new HashMap();
    private static int c = -1;

    private static final class a {
        public final String a;
        public final boolean b;

        public a(String str, boolean z) {
            this.a = str;
            this.b = z;
        }

        public int hashCode() {
            return (31 * ((this.a == null ? 0 : this.a.hashCode()) + 31)) + (this.b ? 1231 : 1237);
        }

        public boolean equals(Object obj) {
            boolean z = true;
            if (this == obj) {
                return true;
            }
            if (obj == null || obj.getClass() != a.class) {
                return false;
            }
            a aVar = (a) obj;
            if (!(TextUtils.equals(this.a, aVar.a) && this.b == aVar.b)) {
                z = false;
            }
            return z;
        }
    }

    public static class b extends IOException {
        private b(Throwable th) {
            super("Failed to query underlying media codecs", th);
        }
    }

    private interface c {
        int a();

        MediaCodecInfo a(int i);

        boolean a(String str, CodecCapabilities codecCapabilities);

        boolean b();
    }

    private static final class d implements c {
        private d() {
        }

        public boolean b() {
            return false;
        }

        public int a() {
            return MediaCodecList.getCodecCount();
        }

        public MediaCodecInfo a(int i) {
            return MediaCodecList.getCodecInfoAt(i);
        }

        public boolean a(String str, CodecCapabilities codecCapabilities) {
            return MimeTypes.VIDEO_H264.equals(str);
        }
    }

    @TargetApi(21)
    private static final class e implements c {
        private final int a;
        private MediaCodecInfo[] b;

        public e(boolean z) {
            this.a = z;
        }

        public boolean b() {
            return true;
        }

        public int a() {
            c();
            return this.b.length;
        }

        public MediaCodecInfo a(int i) {
            c();
            return this.b[i];
        }

        public boolean a(String str, CodecCapabilities codecCapabilities) {
            return codecCapabilities.isFeatureSupported("secure-playback");
        }

        private void c() {
            if (this.b == null) {
                this.b = new MediaCodecList(this.a).getCodecInfos();
            }
        }
    }

    public static ay a() {
        return a;
    }

    public static ay a(String str, boolean z) throws b {
        List b = b(str, z);
        return b.isEmpty() ? null : (ay) b.get(0);
    }

    public static synchronized List<ay> b(String str, boolean z) throws b {
        synchronized (bh.class) {
            a aVar = new a(str, z);
            List list = (List) b.get(aVar);
            if (list != null) {
                return list;
            }
            list = a(aVar, ft.a >= 21 ? new e(z) : new d());
            if (z && list.isEmpty() && 21 <= ft.a && ft.a <= 23) {
                list = a(aVar, new d());
                if (!list.isEmpty()) {
                    String str2 = ((ay) list.get(0)).a;
                    StringBuilder stringBuilder = new StringBuilder((63 + String.valueOf(str).length()) + String.valueOf(str2).length());
                    stringBuilder.append("MediaCodecList API didn't list secure decoder for: ");
                    stringBuilder.append(str);
                    stringBuilder.append(". Assuming: ");
                    stringBuilder.append(str2);
                    Log.w("MediaCodecUtil", stringBuilder.toString());
                }
            }
            List unmodifiableList = Collections.unmodifiableList(list);
            b.put(aVar, unmodifiableList);
            return unmodifiableList;
        }
    }

    private static List<ay> a(a aVar, c cVar) throws b {
        a aVar2 = aVar;
        c cVar2 = cVar;
        String name;
        String str;
        try {
            ArrayList arrayList = new ArrayList();
            String str2 = aVar2.a;
            int a = cVar.a();
            boolean b = cVar.b();
            int i = 0;
            while (i < a) {
                MediaCodecInfo a2 = cVar2.a(i);
                name = a2.getName();
                if (a(a2, name, b)) {
                    String[] supportedTypes = a2.getSupportedTypes();
                    int length = supportedTypes.length;
                    int i2 = 0;
                    while (i2 < length) {
                        str = supportedTypes[i2];
                        if (str.equalsIgnoreCase(str2)) {
                            CodecCapabilities capabilitiesForType = a2.getCapabilitiesForType(str);
                            boolean a3 = cVar2.a(str2, capabilitiesForType);
                            if ((b && r1.b == a3) || (!b && !r1.b)) {
                                arrayList.add(new ay(name, capabilitiesForType));
                            } else if (!b && a3) {
                                arrayList.add(new ay(String.valueOf(name).concat(".secure"), capabilitiesForType));
                                return arrayList;
                            }
                        }
                        i2++;
                        aVar2 = aVar;
                        cVar2 = cVar;
                    }
                    continue;
                }
                i++;
                aVar2 = aVar;
                cVar2 = cVar;
            }
            return arrayList;
        } catch (Exception e) {
            Exception exception = e;
            if (ft.a > 23 || arrayList.isEmpty()) {
                StringBuilder stringBuilder = new StringBuilder((25 + String.valueOf(name).length()) + String.valueOf(str).length());
                stringBuilder.append("Failed to query codec ");
                stringBuilder.append(name);
                stringBuilder.append(" (");
                stringBuilder.append(str);
                stringBuilder.append(")");
                Log.e("MediaCodecUtil", stringBuilder.toString());
                throw exception;
            }
            StringBuilder stringBuilder2 = new StringBuilder(46 + String.valueOf(name).length());
            stringBuilder2.append("Skipping codec ");
            stringBuilder2.append(name);
            stringBuilder2.append(" (failed to query capabilities)");
            Log.e("MediaCodecUtil", stringBuilder2.toString());
        } catch (Exception e2) {
            throw new b(e2);
        }
    }

    private static boolean a(MediaCodecInfo mediaCodecInfo, String str, boolean z) {
        if (mediaCodecInfo.isEncoder() || (!z && str.endsWith(".secure"))) {
            return false;
        }
        if (ft.a < 21 && ("CIPAACDecoder".equals(str) || "CIPMP3Decoder".equals(str) || "CIPVorbisDecoder".equals(str) || "CIPAMRNBDecoder".equals(str) || "AACDecoder".equals(str) || "MP3Decoder".equals(str))) {
            return false;
        }
        if (ft.a < 18 && "OMX.SEC.MP3.Decoder".equals(str)) {
            return false;
        }
        if (ft.a < 18 && "OMX.MTK.AUDIO.DECODER.AAC".equals(str) && "a70".equals(ft.b)) {
            return false;
        }
        if (ft.a == 16 && ft.b != null && "OMX.qcom.audio.decoder.mp3".equals(str) && ("dlxu".equals(ft.b) || "protou".equals(ft.b) || "ville".equals(ft.b) || "villeplus".equals(ft.b) || "villec2".equals(ft.b) || ft.b.startsWith("gee") || "C6602".equals(ft.b) || "C6603".equals(ft.b) || "C6606".equals(ft.b) || "C6616".equals(ft.b) || "L36h".equals(ft.b) || "SO-02E".equals(ft.b))) {
            return false;
        }
        if (ft.a == 16 && "OMX.qcom.audio.decoder.aac".equals(str) && ("C1504".equals(ft.b) || "C1505".equals(ft.b) || "C1604".equals(ft.b) || "C1605".equals(ft.b))) {
            return false;
        }
        if (ft.a <= 19 && ft.b != null && ((ft.b.startsWith("d2") || ft.b.startsWith("serrano") || ft.b.startsWith("jflte") || ft.b.startsWith("santos")) && "samsung".equals(ft.c) && str.equals("OMX.SEC.vp8.dec"))) {
            return false;
        }
        if (ft.a > 19 || ft.b == null || !ft.b.startsWith("jflte") || !"OMX.qcom.video.decoder.vp8".equals(str)) {
            return true;
        }
        return false;
    }
}
