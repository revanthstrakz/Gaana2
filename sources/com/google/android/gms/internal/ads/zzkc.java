package com.google.android.gms.internal.ads;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.media.MediaCodecInfo.CodecProfileLevel;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import android.util.SparseIntArray;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.api.client.googleapis.media.MediaHttpDownloader;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

@SuppressLint({"InlinedApi"})
@TargetApi(16)
public final class zzkc {
    private static final zzjx zzavi = zzjx.zzt("OMX.google.raw.decoder");
    private static final Pattern zzavj = Pattern.compile("^\\D?(\\d+)$");
    private static final HashMap<zza, List<zzjx>> zzavk = new HashMap();
    private static final SparseIntArray zzavl;
    private static final SparseIntArray zzavm;
    private static final Map<String, Integer> zzavn;
    private static int zzavo = -1;

    static final class zza {
        public final String mimeType;
        public final boolean zzatr;

        public zza(String str, boolean z) {
            this.mimeType = str;
            this.zzatr = z;
        }

        public final int hashCode() {
            return (((this.mimeType == null ? 0 : this.mimeType.hashCode()) + 31) * 31) + (this.zzatr ? 1231 : 1237);
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || obj.getClass() != zza.class) {
                return false;
            }
            zza zza = (zza) obj;
            return TextUtils.equals(this.mimeType, zza.mimeType) && this.zzatr == zza.zzatr;
        }
    }

    public static zzjx zzeq() {
        return zzavi;
    }

    public static zzjx zzb(String str, boolean z) throws zzke {
        List zzc = zzc(str, z);
        return zzc.isEmpty() ? null : (zzjx) zzc.get(0);
    }

    private static synchronized List<zzjx> zzc(String str, boolean z) throws zzke {
        synchronized (zzkc.class) {
            zza zza = new zza(str, z);
            List list = (List) zzavk.get(zza);
            if (list != null) {
                return list;
            }
            list = zza(zza, zzqe.SDK_INT >= 21 ? new zzkh(z) : new zzkg());
            if (z && list.isEmpty() && 21 <= zzqe.SDK_INT && zzqe.SDK_INT <= 23) {
                list = zza(zza, new zzkg());
                if (!list.isEmpty()) {
                    String str2 = ((zzjx) list.get(0)).name;
                    StringBuilder stringBuilder = new StringBuilder((63 + String.valueOf(str).length()) + String.valueOf(str2).length());
                    stringBuilder.append("MediaCodecList API didn't list secure decoder for: ");
                    stringBuilder.append(str);
                    stringBuilder.append(". Assuming: ");
                    stringBuilder.append(str2);
                    Log.w("MediaCodecUtil", stringBuilder.toString());
                }
            }
            List unmodifiableList = Collections.unmodifiableList(list);
            zzavk.put(zza, unmodifiableList);
            return unmodifiableList;
        }
    }

    public static int zzer() throws zzke {
        if (zzavo == -1) {
            int i = 0;
            zzjx zzb = zzb(MimeTypes.VIDEO_H264, false);
            if (zzb != null) {
                CodecProfileLevel[] zzej = zzb.zzej();
                int length = zzej.length;
                int i2 = 0;
                while (i < length) {
                    int i3;
                    switch (zzej[i].level) {
                        case 1:
                        case 2:
                            i3 = 25344;
                            break;
                        case 8:
                        case 16:
                        case 32:
                            i3 = 101376;
                            break;
                        case 64:
                            i3 = 202752;
                            break;
                        case 128:
                        case 256:
                            i3 = 414720;
                            break;
                        case 512:
                            i3 = 921600;
                            break;
                        case 1024:
                            i3 = 1310720;
                            break;
                        case 2048:
                        case 4096:
                            i3 = 2097152;
                            break;
                        case 8192:
                            i3 = 2228224;
                            break;
                        case 16384:
                            i3 = 5652480;
                            break;
                        case 32768:
                        case 65536:
                            i3 = 9437184;
                            break;
                        default:
                            i3 = -1;
                            break;
                    }
                    i2 = Math.max(i3, i2);
                    i++;
                }
                i = Math.max(i2, zzqe.SDK_INT >= 21 ? 345600 : 172800);
            }
            zzavo = i;
        }
        return zzavo;
    }

    /* JADX WARNING: Missing block: B:9:0x0029, code skipped:
            if (r3.equals("hev1") != false) goto L_0x0041;
     */
    public static android.util.Pair<java.lang.Integer, java.lang.Integer> zzw(java.lang.String r9) {
        /*
        r0 = 0;
        if (r9 != 0) goto L_0x0004;
    L_0x0003:
        return r0;
    L_0x0004:
        r1 = "\\.";
        r1 = r9.split(r1);
        r2 = 0;
        r3 = r1[r2];
        r4 = -1;
        r5 = r3.hashCode();
        r6 = 2;
        r7 = 3;
        r8 = 1;
        switch(r5) {
            case 3006243: goto L_0x0036;
            case 3006244: goto L_0x002c;
            case 3199032: goto L_0x0023;
            case 3214780: goto L_0x0019;
            default: goto L_0x0018;
        };
    L_0x0018:
        goto L_0x0040;
    L_0x0019:
        r2 = "hvc1";
        r2 = r3.equals(r2);
        if (r2 == 0) goto L_0x0040;
    L_0x0021:
        r2 = r8;
        goto L_0x0041;
    L_0x0023:
        r5 = "hev1";
        r3 = r3.equals(r5);
        if (r3 == 0) goto L_0x0040;
    L_0x002b:
        goto L_0x0041;
    L_0x002c:
        r2 = "avc2";
        r2 = r3.equals(r2);
        if (r2 == 0) goto L_0x0040;
    L_0x0034:
        r2 = r7;
        goto L_0x0041;
    L_0x0036:
        r2 = "avc1";
        r2 = r3.equals(r2);
        if (r2 == 0) goto L_0x0040;
    L_0x003e:
        r2 = r6;
        goto L_0x0041;
    L_0x0040:
        r2 = r4;
    L_0x0041:
        switch(r2) {
            case 0: goto L_0x004a;
            case 1: goto L_0x004a;
            case 2: goto L_0x0045;
            case 3: goto L_0x0045;
            default: goto L_0x0044;
        };
    L_0x0044:
        return r0;
    L_0x0045:
        r9 = zza(r9, r1);
        return r9;
    L_0x004a:
        r2 = r1.length;
        r3 = 4;
        if (r2 >= r3) goto L_0x006a;
    L_0x004e:
        r1 = "MediaCodecUtil";
        r2 = "Ignoring malformed HEVC codec string: ";
        r9 = java.lang.String.valueOf(r9);
        r3 = r9.length();
        if (r3 == 0) goto L_0x0061;
    L_0x005c:
        r9 = r2.concat(r9);
        goto L_0x0066;
    L_0x0061:
        r9 = new java.lang.String;
        r9.<init>(r2);
    L_0x0066:
        android.util.Log.w(r1, r9);
        return r0;
    L_0x006a:
        r2 = zzavj;
        r3 = r1[r8];
        r2 = r2.matcher(r3);
        r3 = r2.matches();
        if (r3 != 0) goto L_0x0094;
    L_0x0078:
        r1 = "MediaCodecUtil";
        r2 = "Ignoring malformed HEVC codec string: ";
        r9 = java.lang.String.valueOf(r9);
        r3 = r9.length();
        if (r3 == 0) goto L_0x008b;
    L_0x0086:
        r9 = r2.concat(r9);
        goto L_0x0090;
    L_0x008b:
        r9 = new java.lang.String;
        r9.<init>(r2);
    L_0x0090:
        android.util.Log.w(r1, r9);
        return r0;
    L_0x0094:
        r9 = r2.group(r8);
        r3 = "1";
        r3 = r3.equals(r9);
        if (r3 == 0) goto L_0x00a2;
    L_0x00a0:
        r6 = r8;
        goto L_0x00aa;
    L_0x00a2:
        r3 = "2";
        r3 = r3.equals(r9);
        if (r3 == 0) goto L_0x00e1;
    L_0x00aa:
        r9 = zzavn;
        r1 = r1[r7];
        r9 = r9.get(r1);
        r9 = (java.lang.Integer) r9;
        if (r9 != 0) goto L_0x00d7;
    L_0x00b6:
        r9 = "MediaCodecUtil";
        r1 = "Unknown HEVC level string: ";
        r2 = r2.group(r8);
        r2 = java.lang.String.valueOf(r2);
        r3 = r2.length();
        if (r3 == 0) goto L_0x00cd;
    L_0x00c8:
        r1 = r1.concat(r2);
        goto L_0x00d3;
    L_0x00cd:
        r2 = new java.lang.String;
        r2.<init>(r1);
        r1 = r2;
    L_0x00d3:
        android.util.Log.w(r9, r1);
        return r0;
    L_0x00d7:
        r0 = new android.util.Pair;
        r1 = java.lang.Integer.valueOf(r6);
        r0.<init>(r1, r9);
        return r0;
    L_0x00e1:
        r1 = "MediaCodecUtil";
        r2 = "Unknown HEVC profile string: ";
        r9 = java.lang.String.valueOf(r9);
        r3 = r9.length();
        if (r3 == 0) goto L_0x00f4;
    L_0x00ef:
        r9 = r2.concat(r9);
        goto L_0x00f9;
    L_0x00f4:
        r9 = new java.lang.String;
        r9.<init>(r2);
    L_0x00f9:
        android.util.Log.w(r1, r9);
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzkc.zzw(java.lang.String):android.util.Pair");
    }

    /* JADX WARNING: Removed duplicated region for block: B:128:0x0201 A:{Catch:{ Exception -> 0x01fa }} */
    /* JADX WARNING: Removed duplicated region for block: B:172:0x02af A:{SYNTHETIC} */
    /* JADX WARNING: Removed duplicated region for block: B:106:0x01b4 A:{Catch:{ Exception -> 0x02ba }} */
    /* JADX WARNING: Removed duplicated region for block: B:170:0x026c A:{SYNTHETIC, SKIP} */
    /* JADX WARNING: Removed duplicated region for block: B:155:0x023f A:{Catch:{ Exception -> 0x02ba }} */
    private static java.util.List<com.google.android.gms.internal.ads.zzjx> zza(com.google.android.gms.internal.ads.zzkc.zza r19, com.google.android.gms.internal.ads.zzkf r20) throws com.google.android.gms.internal.ads.zzke {
        /*
        r1 = r19;
        r2 = r20;
        r3 = new java.util.ArrayList;	 Catch:{ Exception -> 0x02ba }
        r3.<init>();	 Catch:{ Exception -> 0x02ba }
        r4 = r1.mimeType;	 Catch:{ Exception -> 0x02ba }
        r5 = r20.getCodecCount();	 Catch:{ Exception -> 0x02ba }
        r6 = r20.zzes();	 Catch:{ Exception -> 0x02ba }
        r8 = 0;
    L_0x0014:
        if (r8 >= r5) goto L_0x02b9;
    L_0x0016:
        r9 = r2.getCodecInfoAt(r8);	 Catch:{ Exception -> 0x02ba }
        r10 = r9.getName();	 Catch:{ Exception -> 0x02ba }
        r11 = r9.isEncoder();	 Catch:{ Exception -> 0x02ba }
        if (r11 != 0) goto L_0x01b1;
    L_0x0024:
        if (r6 != 0) goto L_0x0030;
    L_0x0026:
        r11 = ".secure";
        r11 = r10.endsWith(r11);	 Catch:{ Exception -> 0x02ba }
        if (r11 == 0) goto L_0x0030;
    L_0x002e:
        goto L_0x01b1;
    L_0x0030:
        r11 = com.google.android.gms.internal.ads.zzqe.SDK_INT;	 Catch:{ Exception -> 0x02ba }
        r13 = 21;
        if (r11 >= r13) goto L_0x0068;
    L_0x0036:
        r11 = "CIPAACDecoder";
        r11 = r11.equals(r10);	 Catch:{ Exception -> 0x02ba }
        if (r11 != 0) goto L_0x01b1;
    L_0x003e:
        r11 = "CIPMP3Decoder";
        r11 = r11.equals(r10);	 Catch:{ Exception -> 0x02ba }
        if (r11 != 0) goto L_0x01b1;
    L_0x0046:
        r11 = "CIPVorbisDecoder";
        r11 = r11.equals(r10);	 Catch:{ Exception -> 0x02ba }
        if (r11 != 0) goto L_0x01b1;
    L_0x004e:
        r11 = "CIPAMRNBDecoder";
        r11 = r11.equals(r10);	 Catch:{ Exception -> 0x02ba }
        if (r11 != 0) goto L_0x01b1;
    L_0x0056:
        r11 = "AACDecoder";
        r11 = r11.equals(r10);	 Catch:{ Exception -> 0x02ba }
        if (r11 != 0) goto L_0x01b1;
    L_0x005e:
        r11 = "MP3Decoder";
        r11 = r11.equals(r10);	 Catch:{ Exception -> 0x02ba }
        if (r11 == 0) goto L_0x0068;
    L_0x0066:
        goto L_0x01b1;
    L_0x0068:
        r11 = com.google.android.gms.internal.ads.zzqe.SDK_INT;	 Catch:{ Exception -> 0x02ba }
        r13 = 18;
        if (r11 >= r13) goto L_0x0078;
    L_0x006e:
        r11 = "OMX.SEC.MP3.Decoder";
        r11 = r11.equals(r10);	 Catch:{ Exception -> 0x02ba }
        if (r11 == 0) goto L_0x0078;
    L_0x0076:
        goto L_0x01b1;
    L_0x0078:
        r11 = com.google.android.gms.internal.ads.zzqe.SDK_INT;	 Catch:{ Exception -> 0x02ba }
        if (r11 >= r13) goto L_0x0090;
    L_0x007c:
        r11 = "OMX.MTK.AUDIO.DECODER.AAC";
        r11 = r11.equals(r10);	 Catch:{ Exception -> 0x02ba }
        if (r11 == 0) goto L_0x0090;
    L_0x0084:
        r11 = "a70";
        r13 = com.google.android.gms.internal.ads.zzqe.DEVICE;	 Catch:{ Exception -> 0x02ba }
        r11 = r11.equals(r13);	 Catch:{ Exception -> 0x02ba }
        if (r11 == 0) goto L_0x0090;
    L_0x008e:
        goto L_0x01b1;
    L_0x0090:
        r11 = com.google.android.gms.internal.ads.zzqe.SDK_INT;	 Catch:{ Exception -> 0x02ba }
        r13 = 16;
        if (r11 != r13) goto L_0x0118;
    L_0x0096:
        r11 = "OMX.qcom.audio.decoder.mp3";
        r11 = r11.equals(r10);	 Catch:{ Exception -> 0x02ba }
        if (r11 == 0) goto L_0x0118;
    L_0x009e:
        r11 = "dlxu";
        r14 = com.google.android.gms.internal.ads.zzqe.DEVICE;	 Catch:{ Exception -> 0x02ba }
        r11 = r11.equals(r14);	 Catch:{ Exception -> 0x02ba }
        if (r11 != 0) goto L_0x01b1;
    L_0x00a8:
        r11 = "protou";
        r14 = com.google.android.gms.internal.ads.zzqe.DEVICE;	 Catch:{ Exception -> 0x02ba }
        r11 = r11.equals(r14);	 Catch:{ Exception -> 0x02ba }
        if (r11 != 0) goto L_0x01b1;
    L_0x00b2:
        r11 = "ville";
        r14 = com.google.android.gms.internal.ads.zzqe.DEVICE;	 Catch:{ Exception -> 0x02ba }
        r11 = r11.equals(r14);	 Catch:{ Exception -> 0x02ba }
        if (r11 != 0) goto L_0x01b1;
    L_0x00bc:
        r11 = "villeplus";
        r14 = com.google.android.gms.internal.ads.zzqe.DEVICE;	 Catch:{ Exception -> 0x02ba }
        r11 = r11.equals(r14);	 Catch:{ Exception -> 0x02ba }
        if (r11 != 0) goto L_0x01b1;
    L_0x00c6:
        r11 = "villec2";
        r14 = com.google.android.gms.internal.ads.zzqe.DEVICE;	 Catch:{ Exception -> 0x02ba }
        r11 = r11.equals(r14);	 Catch:{ Exception -> 0x02ba }
        if (r11 != 0) goto L_0x01b1;
    L_0x00d0:
        r11 = com.google.android.gms.internal.ads.zzqe.DEVICE;	 Catch:{ Exception -> 0x02ba }
        r14 = "gee";
        r11 = r11.startsWith(r14);	 Catch:{ Exception -> 0x02ba }
        if (r11 != 0) goto L_0x01b1;
    L_0x00da:
        r11 = "C6602";
        r14 = com.google.android.gms.internal.ads.zzqe.DEVICE;	 Catch:{ Exception -> 0x02ba }
        r11 = r11.equals(r14);	 Catch:{ Exception -> 0x02ba }
        if (r11 != 0) goto L_0x01b1;
    L_0x00e4:
        r11 = "C6603";
        r14 = com.google.android.gms.internal.ads.zzqe.DEVICE;	 Catch:{ Exception -> 0x02ba }
        r11 = r11.equals(r14);	 Catch:{ Exception -> 0x02ba }
        if (r11 != 0) goto L_0x01b1;
    L_0x00ee:
        r11 = "C6606";
        r14 = com.google.android.gms.internal.ads.zzqe.DEVICE;	 Catch:{ Exception -> 0x02ba }
        r11 = r11.equals(r14);	 Catch:{ Exception -> 0x02ba }
        if (r11 != 0) goto L_0x01b1;
    L_0x00f8:
        r11 = "C6616";
        r14 = com.google.android.gms.internal.ads.zzqe.DEVICE;	 Catch:{ Exception -> 0x02ba }
        r11 = r11.equals(r14);	 Catch:{ Exception -> 0x02ba }
        if (r11 != 0) goto L_0x01b1;
    L_0x0102:
        r11 = "L36h";
        r14 = com.google.android.gms.internal.ads.zzqe.DEVICE;	 Catch:{ Exception -> 0x02ba }
        r11 = r11.equals(r14);	 Catch:{ Exception -> 0x02ba }
        if (r11 != 0) goto L_0x01b1;
    L_0x010c:
        r11 = "SO-02E";
        r14 = com.google.android.gms.internal.ads.zzqe.DEVICE;	 Catch:{ Exception -> 0x02ba }
        r11 = r11.equals(r14);	 Catch:{ Exception -> 0x02ba }
        if (r11 == 0) goto L_0x0118;
    L_0x0116:
        goto L_0x01b1;
    L_0x0118:
        r11 = com.google.android.gms.internal.ads.zzqe.SDK_INT;	 Catch:{ Exception -> 0x02ba }
        if (r11 != r13) goto L_0x014d;
    L_0x011c:
        r11 = "OMX.qcom.audio.decoder.aac";
        r11 = r11.equals(r10);	 Catch:{ Exception -> 0x02ba }
        if (r11 == 0) goto L_0x014d;
    L_0x0124:
        r11 = "C1504";
        r13 = com.google.android.gms.internal.ads.zzqe.DEVICE;	 Catch:{ Exception -> 0x02ba }
        r11 = r11.equals(r13);	 Catch:{ Exception -> 0x02ba }
        if (r11 != 0) goto L_0x01b1;
    L_0x012e:
        r11 = "C1505";
        r13 = com.google.android.gms.internal.ads.zzqe.DEVICE;	 Catch:{ Exception -> 0x02ba }
        r11 = r11.equals(r13);	 Catch:{ Exception -> 0x02ba }
        if (r11 != 0) goto L_0x01b1;
    L_0x0138:
        r11 = "C1604";
        r13 = com.google.android.gms.internal.ads.zzqe.DEVICE;	 Catch:{ Exception -> 0x02ba }
        r11 = r11.equals(r13);	 Catch:{ Exception -> 0x02ba }
        if (r11 != 0) goto L_0x01b1;
    L_0x0142:
        r11 = "C1605";
        r13 = com.google.android.gms.internal.ads.zzqe.DEVICE;	 Catch:{ Exception -> 0x02ba }
        r11 = r11.equals(r13);	 Catch:{ Exception -> 0x02ba }
        if (r11 == 0) goto L_0x014d;
    L_0x014c:
        goto L_0x01b1;
    L_0x014d:
        r11 = com.google.android.gms.internal.ads.zzqe.SDK_INT;	 Catch:{ Exception -> 0x02ba }
        r13 = 19;
        if (r11 > r13) goto L_0x0198;
    L_0x0153:
        r11 = "OMX.SEC.vp8.dec";
        r11 = r11.equals(r10);	 Catch:{ Exception -> 0x02ba }
        if (r11 == 0) goto L_0x0198;
    L_0x015b:
        r11 = "samsung";
        r14 = com.google.android.gms.internal.ads.zzqe.MANUFACTURER;	 Catch:{ Exception -> 0x02ba }
        r11 = r11.equals(r14);	 Catch:{ Exception -> 0x02ba }
        if (r11 == 0) goto L_0x0198;
    L_0x0165:
        r11 = com.google.android.gms.internal.ads.zzqe.DEVICE;	 Catch:{ Exception -> 0x02ba }
        r14 = "d2";
        r11 = r11.startsWith(r14);	 Catch:{ Exception -> 0x02ba }
        if (r11 != 0) goto L_0x01b1;
    L_0x016f:
        r11 = com.google.android.gms.internal.ads.zzqe.DEVICE;	 Catch:{ Exception -> 0x02ba }
        r14 = "serrano";
        r11 = r11.startsWith(r14);	 Catch:{ Exception -> 0x02ba }
        if (r11 != 0) goto L_0x01b1;
    L_0x0179:
        r11 = com.google.android.gms.internal.ads.zzqe.DEVICE;	 Catch:{ Exception -> 0x02ba }
        r14 = "jflte";
        r11 = r11.startsWith(r14);	 Catch:{ Exception -> 0x02ba }
        if (r11 != 0) goto L_0x01b1;
    L_0x0183:
        r11 = com.google.android.gms.internal.ads.zzqe.DEVICE;	 Catch:{ Exception -> 0x02ba }
        r14 = "santos";
        r11 = r11.startsWith(r14);	 Catch:{ Exception -> 0x02ba }
        if (r11 != 0) goto L_0x01b1;
    L_0x018d:
        r11 = com.google.android.gms.internal.ads.zzqe.DEVICE;	 Catch:{ Exception -> 0x02ba }
        r14 = "t0";
        r11 = r11.startsWith(r14);	 Catch:{ Exception -> 0x02ba }
        if (r11 == 0) goto L_0x0198;
    L_0x0197:
        goto L_0x01b1;
    L_0x0198:
        r11 = com.google.android.gms.internal.ads.zzqe.SDK_INT;	 Catch:{ Exception -> 0x02ba }
        if (r11 > r13) goto L_0x01af;
    L_0x019c:
        r11 = com.google.android.gms.internal.ads.zzqe.DEVICE;	 Catch:{ Exception -> 0x02ba }
        r13 = "jflte";
        r11 = r11.startsWith(r13);	 Catch:{ Exception -> 0x02ba }
        if (r11 == 0) goto L_0x01af;
    L_0x01a6:
        r11 = "OMX.qcom.video.decoder.vp8";
        r11 = r11.equals(r10);	 Catch:{ Exception -> 0x02ba }
        if (r11 == 0) goto L_0x01af;
    L_0x01ae:
        goto L_0x01b1;
    L_0x01af:
        r11 = 1;
        goto L_0x01b2;
    L_0x01b1:
        r11 = 0;
    L_0x01b2:
        if (r11 == 0) goto L_0x02af;
    L_0x01b4:
        r11 = r9.getSupportedTypes();	 Catch:{ Exception -> 0x02ba }
        r13 = r11.length;	 Catch:{ Exception -> 0x02ba }
        r14 = 0;
    L_0x01ba:
        if (r14 >= r13) goto L_0x02af;
    L_0x01bc:
        r15 = r11[r14];	 Catch:{ Exception -> 0x02ba }
        r16 = r15.equalsIgnoreCase(r4);	 Catch:{ Exception -> 0x02ba }
        if (r16 == 0) goto L_0x02a4;
    L_0x01c4:
        r12 = r9.getCapabilitiesForType(r15);	 Catch:{ Exception -> 0x0234 }
        r7 = r2.zza(r4, r12);	 Catch:{ Exception -> 0x0234 }
        r2 = com.google.android.gms.internal.ads.zzqe.SDK_INT;	 Catch:{ Exception -> 0x0234 }
        r17 = r5;
        r5 = 22;
        if (r2 > r5) goto L_0x01fe;
    L_0x01d4:
        r2 = com.google.android.gms.internal.ads.zzqe.MODEL;	 Catch:{ Exception -> 0x01fa }
        r5 = "ODROID-XU3";
        r2 = r2.equals(r5);	 Catch:{ Exception -> 0x01fa }
        if (r2 != 0) goto L_0x01e8;
    L_0x01de:
        r2 = com.google.android.gms.internal.ads.zzqe.MODEL;	 Catch:{ Exception -> 0x01fa }
        r5 = "Nexus 10";
        r2 = r2.equals(r5);	 Catch:{ Exception -> 0x01fa }
        if (r2 == 0) goto L_0x01fe;
    L_0x01e8:
        r2 = "OMX.Exynos.AVC.Decoder";
        r2 = r2.equals(r10);	 Catch:{ Exception -> 0x01fa }
        if (r2 != 0) goto L_0x01f8;
    L_0x01f0:
        r2 = "OMX.Exynos.AVC.Decoder.secure";
        r2 = r2.equals(r10);	 Catch:{ Exception -> 0x01fa }
        if (r2 == 0) goto L_0x01fe;
    L_0x01f8:
        r2 = 1;
        goto L_0x01ff;
    L_0x01fa:
        r0 = move-exception;
        r2 = r0;
        r7 = 1;
        goto L_0x0239;
    L_0x01fe:
        r2 = 0;
    L_0x01ff:
        if (r6 == 0) goto L_0x0208;
    L_0x0201:
        r5 = r1.zzatr;	 Catch:{ Exception -> 0x01fa }
        if (r5 == r7) goto L_0x0206;
    L_0x0205:
        goto L_0x0208;
    L_0x0206:
        r5 = 0;
        goto L_0x020f;
    L_0x0208:
        if (r6 != 0) goto L_0x0218;
    L_0x020a:
        r5 = r1.zzatr;	 Catch:{ Exception -> 0x01fa }
        if (r5 != 0) goto L_0x0218;
    L_0x020e:
        goto L_0x0206;
    L_0x020f:
        r2 = com.google.android.gms.internal.ads.zzjx.zza(r10, r4, r12, r2, r5);	 Catch:{ Exception -> 0x01fa }
        r3.add(r2);	 Catch:{ Exception -> 0x01fa }
        goto L_0x02a6;
    L_0x0218:
        r5 = 0;
        if (r6 != 0) goto L_0x02a6;
    L_0x021b:
        if (r7 == 0) goto L_0x02a6;
    L_0x021d:
        r7 = java.lang.String.valueOf(r10);	 Catch:{ Exception -> 0x0232 }
        r5 = ".secure";
        r5 = r7.concat(r5);	 Catch:{ Exception -> 0x0232 }
        r7 = 1;
        r2 = com.google.android.gms.internal.ads.zzjx.zza(r5, r4, r12, r2, r7);	 Catch:{ Exception -> 0x0230 }
        r3.add(r2);	 Catch:{ Exception -> 0x0230 }
        return r3;
    L_0x0230:
        r0 = move-exception;
        goto L_0x0238;
    L_0x0232:
        r0 = move-exception;
        goto L_0x0237;
    L_0x0234:
        r0 = move-exception;
        r17 = r5;
    L_0x0237:
        r7 = 1;
    L_0x0238:
        r2 = r0;
    L_0x0239:
        r5 = com.google.android.gms.internal.ads.zzqe.SDK_INT;	 Catch:{ Exception -> 0x02ba }
        r12 = 23;
        if (r5 > r12) goto L_0x026c;
    L_0x023f:
        r5 = r3.isEmpty();	 Catch:{ Exception -> 0x02ba }
        if (r5 != 0) goto L_0x026c;
    L_0x0245:
        r2 = "MediaCodecUtil";
        r5 = 46;
        r12 = java.lang.String.valueOf(r10);	 Catch:{ Exception -> 0x02ba }
        r12 = r12.length();	 Catch:{ Exception -> 0x02ba }
        r5 = r5 + r12;
        r12 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x02ba }
        r12.<init>(r5);	 Catch:{ Exception -> 0x02ba }
        r5 = "Skipping codec ";
        r12.append(r5);	 Catch:{ Exception -> 0x02ba }
        r12.append(r10);	 Catch:{ Exception -> 0x02ba }
        r5 = " (failed to query capabilities)";
        r12.append(r5);	 Catch:{ Exception -> 0x02ba }
        r5 = r12.toString();	 Catch:{ Exception -> 0x02ba }
        android.util.Log.e(r2, r5);	 Catch:{ Exception -> 0x02ba }
        goto L_0x02a7;
    L_0x026c:
        r1 = "MediaCodecUtil";
        r3 = 25;
        r4 = java.lang.String.valueOf(r10);	 Catch:{ Exception -> 0x02ba }
        r4 = r4.length();	 Catch:{ Exception -> 0x02ba }
        r3 = r3 + r4;
        r4 = java.lang.String.valueOf(r15);	 Catch:{ Exception -> 0x02ba }
        r4 = r4.length();	 Catch:{ Exception -> 0x02ba }
        r3 = r3 + r4;
        r4 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x02ba }
        r4.<init>(r3);	 Catch:{ Exception -> 0x02ba }
        r3 = "Failed to query codec ";
        r4.append(r3);	 Catch:{ Exception -> 0x02ba }
        r4.append(r10);	 Catch:{ Exception -> 0x02ba }
        r3 = " (";
        r4.append(r3);	 Catch:{ Exception -> 0x02ba }
        r4.append(r15);	 Catch:{ Exception -> 0x02ba }
        r3 = ")";
        r4.append(r3);	 Catch:{ Exception -> 0x02ba }
        r3 = r4.toString();	 Catch:{ Exception -> 0x02ba }
        android.util.Log.e(r1, r3);	 Catch:{ Exception -> 0x02ba }
        throw r2;	 Catch:{ Exception -> 0x02ba }
    L_0x02a4:
        r17 = r5;
    L_0x02a6:
        r7 = 1;
    L_0x02a7:
        r14 = r14 + 1;
        r5 = r17;
        r2 = r20;
        goto L_0x01ba;
    L_0x02af:
        r17 = r5;
        r8 = r8 + 1;
        r5 = r17;
        r2 = r20;
        goto L_0x0014;
    L_0x02b9:
        return r3;
    L_0x02ba:
        r0 = move-exception;
        r1 = r0;
        r2 = new com.google.android.gms.internal.ads.zzke;
        r3 = 0;
        r2.<init>(r1);
        throw r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzkc.zza(com.google.android.gms.internal.ads.zzkc$zza, com.google.android.gms.internal.ads.zzkf):java.util.List");
    }

    private static Pair<Integer, Integer> zza(String str, String[] strArr) {
        String str2;
        String str3;
        if (strArr.length < 2) {
            str2 = "MediaCodecUtil";
            str3 = "Ignoring malformed AVC codec string: ";
            str = String.valueOf(str);
            Log.w(str2, str.length() != 0 ? str3.concat(str) : new String(str3));
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
                str2 = "MediaCodecUtil";
                str3 = "Ignoring malformed AVC codec string: ";
                String valueOf4 = String.valueOf(str);
                Log.w(str2, valueOf4.length() != 0 ? str3.concat(valueOf4) : new String(str3));
                return null;
            }
            Integer valueOf5 = Integer.valueOf(zzavl.get(valueOf.intValue()));
            StringBuilder stringBuilder;
            if (valueOf5 == null) {
                str2 = String.valueOf(valueOf);
                stringBuilder = new StringBuilder(21 + String.valueOf(str2).length());
                stringBuilder.append("Unknown AVC profile: ");
                stringBuilder.append(str2);
                Log.w("MediaCodecUtil", stringBuilder.toString());
                return null;
            }
            valueOf = Integer.valueOf(zzavm.get(valueOf2.intValue()));
            if (valueOf != null) {
                return new Pair(valueOf5, valueOf);
            }
            str2 = String.valueOf(valueOf2);
            stringBuilder = new StringBuilder(19 + String.valueOf(str2).length());
            stringBuilder.append("Unknown AVC level: ");
            stringBuilder.append(str2);
            Log.w("MediaCodecUtil", stringBuilder.toString());
            return null;
        } catch (NumberFormatException unused) {
            str2 = "MediaCodecUtil";
            str3 = "Ignoring malformed AVC codec string: ";
            str = String.valueOf(str);
            Log.w(str2, str.length() != 0 ? str3.concat(str) : new String(str3));
            return null;
        }
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        zzavl = sparseIntArray;
        sparseIntArray.put(66, 1);
        zzavl.put(77, 2);
        zzavl.put(88, 4);
        zzavl.put(100, 8);
        sparseIntArray = new SparseIntArray();
        zzavm = sparseIntArray;
        sparseIntArray.put(10, 1);
        zzavm.put(11, 4);
        zzavm.put(12, 8);
        zzavm.put(13, 16);
        zzavm.put(20, 32);
        zzavm.put(21, 64);
        zzavm.put(22, 128);
        zzavm.put(30, 256);
        zzavm.put(31, 512);
        zzavm.put(32, 1024);
        zzavm.put(40, 2048);
        zzavm.put(41, 4096);
        zzavm.put(42, 8192);
        zzavm.put(50, 16384);
        zzavm.put(51, 32768);
        zzavm.put(52, 65536);
        HashMap hashMap = new HashMap();
        zzavn = hashMap;
        hashMap.put("L30", Integer.valueOf(1));
        zzavn.put("L60", Integer.valueOf(4));
        zzavn.put("L63", Integer.valueOf(16));
        zzavn.put("L90", Integer.valueOf(64));
        zzavn.put("L93", Integer.valueOf(256));
        zzavn.put("L120", Integer.valueOf(1024));
        zzavn.put("L123", Integer.valueOf(4096));
        zzavn.put("L150", Integer.valueOf(16384));
        zzavn.put("L153", Integer.valueOf(65536));
        zzavn.put("L156", Integer.valueOf(262144));
        zzavn.put("L180", Integer.valueOf(1048576));
        zzavn.put("L183", Integer.valueOf(4194304));
        zzavn.put("L186", Integer.valueOf(16777216));
        zzavn.put("H30", Integer.valueOf(2));
        zzavn.put("H60", Integer.valueOf(8));
        zzavn.put("H63", Integer.valueOf(32));
        zzavn.put("H90", Integer.valueOf(128));
        zzavn.put("H93", Integer.valueOf(512));
        zzavn.put("H120", Integer.valueOf(2048));
        zzavn.put("H123", Integer.valueOf(8192));
        zzavn.put("H150", Integer.valueOf(32768));
        zzavn.put("H153", Integer.valueOf(131072));
        zzavn.put("H156", Integer.valueOf(524288));
        zzavn.put("H180", Integer.valueOf(2097152));
        zzavn.put("H183", Integer.valueOf(8388608));
        zzavn.put("H186", Integer.valueOf(MediaHttpDownloader.MAXIMUM_CHUNK_SIZE));
    }
}
