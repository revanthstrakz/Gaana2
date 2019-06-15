package com.google.ads.interactivemedia.v3.internal;

import android.util.Pair;
import android.util.SparseArray;
import com.google.ads.interactivemedia.v3.internal.bu.c;
import com.google.android.exoplayer2.extractor.ts.TsExtractor;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.firebase.FirebaseError;
import com.moe.pushlibrary.utils.MoEHelperUtils;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

public final class en implements cc {
    private static final byte[] a = new byte[]{(byte) 49, (byte) 10, (byte) 48, (byte) 48, (byte) 58, (byte) 48, (byte) 48, (byte) 58, (byte) 48, (byte) 48, (byte) 44, (byte) 48, (byte) 48, (byte) 48, (byte) 32, (byte) 45, (byte) 45, (byte) 62, (byte) 32, (byte) 48, (byte) 48, (byte) 58, (byte) 48, (byte) 48, (byte) 58, (byte) 48, (byte) 48, (byte) 44, (byte) 48, (byte) 48, (byte) 48, (byte) 10};
    private static final byte[] b = new byte[]{(byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32};
    private static final UUID c = new UUID(72057594037932032L, -9223371306706625679L);
    private long A;
    private boolean B;
    private long C;
    private long D;
    private long E;
    private fk F;
    private fk G;
    private boolean H;
    private int I;
    private long J;
    private long K;
    private int L;
    private int M;
    private int[] N;
    private int O;
    private int P;
    private int Q;
    private int R;
    private boolean S;
    private boolean T;
    private boolean U;
    private boolean V;
    private byte W;
    private int X;
    private int Y;
    private int Z;
    private boolean aa;
    private boolean ab;
    private ce ac;
    private final ej d;
    private final em e;
    private final SparseArray<b> f;
    private final boolean g;
    private final fp h;
    private final fp i;
    private final fp j;
    private final fp k;
    private final fp l;
    private final fp m;
    private final fp n;
    private final fp o;
    private final fp p;
    private ByteBuffer q;
    private long r;
    private long s;
    private long t;
    private long u;
    private long v;
    private b w;
    private boolean x;
    private boolean y;
    private int z;

    private static final class b {
        public float A;
        public float B;
        public float C;
        public float D;
        public float E;
        public int F;
        public int G;
        public int H;
        public long I;
        public long J;
        public ck K;
        public int L;
        private String M;
        public String a;
        public int b;
        public int c;
        public int d;
        public boolean e;
        public byte[] f;
        public byte[] g;
        public byte[] h;
        public int i;
        public int j;
        public int k;
        public int l;
        public int m;
        public byte[] n;
        public int o;
        public boolean p;
        public int q;
        public int r;
        public int s;
        public int t;
        public int u;
        public float v;
        public float w;
        public float x;
        public float y;
        public float z;

        private b() {
            this.i = -1;
            this.j = -1;
            this.k = -1;
            this.l = -1;
            this.m = 0;
            this.n = null;
            this.o = -1;
            this.p = false;
            this.q = -1;
            this.r = -1;
            this.s = -1;
            this.t = 1000;
            this.u = 200;
            this.v = -1.0f;
            this.w = -1.0f;
            this.x = -1.0f;
            this.y = -1.0f;
            this.z = -1.0f;
            this.A = -1.0f;
            this.B = -1.0f;
            this.C = -1.0f;
            this.D = -1.0f;
            this.E = -1.0f;
            this.F = 1;
            this.G = -1;
            this.H = 8000;
            this.I = 0;
            this.J = 0;
            this.M = "eng";
        }

        /* JADX WARNING: Missing block: B:89:0x016f, code skipped:
            r8 = r1;
            r17 = r3;
            r10 = -1;
     */
        /* JADX WARNING: Missing block: B:109:0x0243, code skipped:
            r8 = r1;
     */
        /* JADX WARNING: Missing block: B:112:0x027c, code skipped:
            r8 = r1;
            r2 = r3;
     */
        /* JADX WARNING: Missing block: B:113:0x027e, code skipped:
            r10 = -1;
            r17 = r10;
     */
        /* JADX WARNING: Missing block: B:121:0x0299, code skipped:
            r8 = r1;
            r10 = -1;
            r17 = r10;
     */
        /* JADX WARNING: Missing block: B:122:0x029d, code skipped:
            r2 = null;
     */
        /* JADX WARNING: Missing block: B:124:0x02a2, code skipped:
            if (com.google.ads.interactivemedia.v3.internal.fl.a(r8) == false) goto L_0x02ba;
     */
        /* JADX WARNING: Missing block: B:125:0x02a4, code skipped:
            r1 = com.google.ads.interactivemedia.v3.internal.bj.a(java.lang.Integer.toString(r23), r8, -1, r10, r24, r0.F, r0.H, r2, r0.M, r17);
     */
        /* JADX WARNING: Missing block: B:127:0x02be, code skipped:
            if (com.google.ads.interactivemedia.v3.internal.fl.b(r8) == false) goto L_0x0323;
     */
        /* JADX WARNING: Missing block: B:129:0x02c2, code skipped:
            if (r0.m != 0) goto L_0x02da;
     */
        /* JADX WARNING: Missing block: B:131:0x02c6, code skipped:
            if (r0.k != -1) goto L_0x02cb;
     */
        /* JADX WARNING: Missing block: B:132:0x02c8, code skipped:
            r1 = r0.i;
     */
        /* JADX WARNING: Missing block: B:133:0x02cb, code skipped:
            r1 = r0.k;
     */
        /* JADX WARNING: Missing block: B:134:0x02cd, code skipped:
            r0.k = r1;
     */
        /* JADX WARNING: Missing block: B:135:0x02d1, code skipped:
            if (r0.l != -1) goto L_0x02d6;
     */
        /* JADX WARNING: Missing block: B:136:0x02d3, code skipped:
            r1 = r0.j;
     */
        /* JADX WARNING: Missing block: B:137:0x02d6, code skipped:
            r1 = r0.l;
     */
        /* JADX WARNING: Missing block: B:138:0x02d8, code skipped:
            r0.l = r1;
     */
        /* JADX WARNING: Missing block: B:139:0x02da, code skipped:
            r1 = -1.0f;
     */
        /* JADX WARNING: Missing block: B:140:0x02de, code skipped:
            if (r0.k == -1) goto L_0x02f1;
     */
        /* JADX WARNING: Missing block: B:142:0x02e2, code skipped:
            if (r0.l == -1) goto L_0x02f1;
     */
        /* JADX WARNING: Missing block: B:143:0x02e4, code skipped:
            r1 = ((float) (r0.j * r0.k)) / ((float) (r0.i * r0.l));
     */
        /* JADX WARNING: Missing block: B:144:0x02f1, code skipped:
            r17 = r1;
     */
        /* JADX WARNING: Missing block: B:145:0x02f5, code skipped:
            if (r0.p == false) goto L_0x0306;
     */
        /* JADX WARNING: Missing block: B:146:0x02f7, code skipped:
            r6 = new com.google.ads.interactivemedia.v3.internal.aw(r0.q, r0.s, r0.r, a());
     */
        /* JADX WARNING: Missing block: B:147:0x0306, code skipped:
            r20 = r6;
            r1 = com.google.ads.interactivemedia.v3.internal.bj.a(java.lang.Integer.toString(r23), r8, -1, r10, r24, r0.i, r0.j, r2, -1, r17, r0.n, r0.o, r20);
     */
        /* JADX WARNING: Missing block: B:149:0x0329, code skipped:
            if (com.google.android.exoplayer2.util.MimeTypes.APPLICATION_SUBRIP.equals(r8) == false) goto L_0x0339;
     */
        /* JADX WARNING: Missing block: B:150:0x032b, code skipped:
            r1 = com.google.ads.interactivemedia.v3.internal.bj.a(java.lang.Integer.toString(r23), r8, -1, r24, r0.M);
     */
        /* JADX WARNING: Missing block: B:152:0x033f, code skipped:
            if (com.google.android.exoplayer2.util.MimeTypes.APPLICATION_VOBSUB.equals(r8) != false) goto L_0x0352;
     */
        /* JADX WARNING: Missing block: B:154:0x0347, code skipped:
            if (com.google.android.exoplayer2.util.MimeTypes.APPLICATION_PGS.equals(r8) == false) goto L_0x034a;
     */
        /* JADX WARNING: Missing block: B:156:0x0351, code skipped:
            throw new com.google.ads.interactivemedia.v3.internal.bl("Unexpected MIME type.");
     */
        /* JADX WARNING: Missing block: B:157:0x0352, code skipped:
            r1 = com.google.ads.interactivemedia.v3.internal.bj.a(java.lang.Integer.toString(r23), r8, -1, r24, r2, r0.M);
     */
        /* JADX WARNING: Missing block: B:158:0x0360, code skipped:
            r0.K = r22.d(r0.b);
            r0.K.a(r1);
     */
        /* JADX WARNING: Missing block: B:159:0x036f, code skipped:
            return;
     */
        public void a(com.google.ads.interactivemedia.v3.internal.ce r22, int r23, long r24) throws com.google.ads.interactivemedia.v3.internal.bl {
            /*
            r21 = this;
            r0 = r21;
            r1 = r0.a;
            r2 = r1.hashCode();
            r3 = 3;
            r4 = 8;
            r5 = -1;
            switch(r2) {
                case -2095576542: goto L_0x0121;
                case -2095575984: goto L_0x0117;
                case -1985379776: goto L_0x010c;
                case -1784763192: goto L_0x0101;
                case -1730367663: goto L_0x00f6;
                case -1482641357: goto L_0x00eb;
                case -1373388978: goto L_0x00e1;
                case -538363189: goto L_0x00d7;
                case -538363109: goto L_0x00cd;
                case -425012669: goto L_0x00c1;
                case -356037306: goto L_0x00b5;
                case 62923557: goto L_0x00a9;
                case 62923603: goto L_0x009d;
                case 62927045: goto L_0x0091;
                case 82338133: goto L_0x0086;
                case 82338134: goto L_0x007b;
                case 99146302: goto L_0x006f;
                case 542569478: goto L_0x0063;
                case 725957860: goto L_0x0057;
                case 855502857: goto L_0x004c;
                case 1422270023: goto L_0x0040;
                case 1809237540: goto L_0x0035;
                case 1950749482: goto L_0x0029;
                case 1950789798: goto L_0x001d;
                case 1951062397: goto L_0x0011;
                default: goto L_0x000f;
            };
        L_0x000f:
            goto L_0x012b;
        L_0x0011:
            r2 = "A_OPUS";
            r1 = r1.equals(r2);
            if (r1 == 0) goto L_0x012b;
        L_0x0019:
            r1 = 10;
            goto L_0x012c;
        L_0x001d:
            r2 = "A_FLAC";
            r1 = r1.equals(r2);
            if (r1 == 0) goto L_0x012b;
        L_0x0025:
            r1 = 19;
            goto L_0x012c;
        L_0x0029:
            r2 = "A_EAC3";
            r1 = r1.equals(r2);
            if (r1 == 0) goto L_0x012b;
        L_0x0031:
            r1 = 14;
            goto L_0x012c;
        L_0x0035:
            r2 = "V_MPEG2";
            r1 = r1.equals(r2);
            if (r1 == 0) goto L_0x012b;
        L_0x003d:
            r1 = 2;
            goto L_0x012c;
        L_0x0040:
            r2 = "S_TEXT/UTF8";
            r1 = r1.equals(r2);
            if (r1 == 0) goto L_0x012b;
        L_0x0048:
            r1 = 22;
            goto L_0x012c;
        L_0x004c:
            r2 = "V_MPEGH/ISO/HEVC";
            r1 = r1.equals(r2);
            if (r1 == 0) goto L_0x012b;
        L_0x0054:
            r1 = 7;
            goto L_0x012c;
        L_0x0057:
            r2 = "A_PCM/INT/LIT";
            r1 = r1.equals(r2);
            if (r1 == 0) goto L_0x012b;
        L_0x005f:
            r1 = 21;
            goto L_0x012c;
        L_0x0063:
            r2 = "A_DTS/EXPRESS";
            r1 = r1.equals(r2);
            if (r1 == 0) goto L_0x012b;
        L_0x006b:
            r1 = 17;
            goto L_0x012c;
        L_0x006f:
            r2 = "S_HDMV/PGS";
            r1 = r1.equals(r2);
            if (r1 == 0) goto L_0x012b;
        L_0x0077:
            r1 = 24;
            goto L_0x012c;
        L_0x007b:
            r2 = "V_VP9";
            r1 = r1.equals(r2);
            if (r1 == 0) goto L_0x012b;
        L_0x0083:
            r1 = 1;
            goto L_0x012c;
        L_0x0086:
            r2 = "V_VP8";
            r1 = r1.equals(r2);
            if (r1 == 0) goto L_0x012b;
        L_0x008e:
            r1 = 0;
            goto L_0x012c;
        L_0x0091:
            r2 = "A_DTS";
            r1 = r1.equals(r2);
            if (r1 == 0) goto L_0x012b;
        L_0x0099:
            r1 = 16;
            goto L_0x012c;
        L_0x009d:
            r2 = "A_AC3";
            r1 = r1.equals(r2);
            if (r1 == 0) goto L_0x012b;
        L_0x00a5:
            r1 = 13;
            goto L_0x012c;
        L_0x00a9:
            r2 = "A_AAC";
            r1 = r1.equals(r2);
            if (r1 == 0) goto L_0x012b;
        L_0x00b1:
            r1 = 11;
            goto L_0x012c;
        L_0x00b5:
            r2 = "A_DTS/LOSSLESS";
            r1 = r1.equals(r2);
            if (r1 == 0) goto L_0x012b;
        L_0x00bd:
            r1 = 18;
            goto L_0x012c;
        L_0x00c1:
            r2 = "S_VOBSUB";
            r1 = r1.equals(r2);
            if (r1 == 0) goto L_0x012b;
        L_0x00c9:
            r1 = 23;
            goto L_0x012c;
        L_0x00cd:
            r2 = "V_MPEG4/ISO/AVC";
            r1 = r1.equals(r2);
            if (r1 == 0) goto L_0x012b;
        L_0x00d5:
            r1 = 6;
            goto L_0x012c;
        L_0x00d7:
            r2 = "V_MPEG4/ISO/ASP";
            r1 = r1.equals(r2);
            if (r1 == 0) goto L_0x012b;
        L_0x00df:
            r1 = 4;
            goto L_0x012c;
        L_0x00e1:
            r2 = "V_MS/VFW/FOURCC";
            r1 = r1.equals(r2);
            if (r1 == 0) goto L_0x012b;
        L_0x00e9:
            r1 = r4;
            goto L_0x012c;
        L_0x00eb:
            r2 = "A_MPEG/L3";
            r1 = r1.equals(r2);
            if (r1 == 0) goto L_0x012b;
        L_0x00f3:
            r1 = 12;
            goto L_0x012c;
        L_0x00f6:
            r2 = "A_VORBIS";
            r1 = r1.equals(r2);
            if (r1 == 0) goto L_0x012b;
        L_0x00fe:
            r1 = 9;
            goto L_0x012c;
        L_0x0101:
            r2 = "A_TRUEHD";
            r1 = r1.equals(r2);
            if (r1 == 0) goto L_0x012b;
        L_0x0109:
            r1 = 15;
            goto L_0x012c;
        L_0x010c:
            r2 = "A_MS/ACM";
            r1 = r1.equals(r2);
            if (r1 == 0) goto L_0x012b;
        L_0x0114:
            r1 = 20;
            goto L_0x012c;
        L_0x0117:
            r2 = "V_MPEG4/ISO/SP";
            r1 = r1.equals(r2);
            if (r1 == 0) goto L_0x012b;
        L_0x011f:
            r1 = r3;
            goto L_0x012c;
        L_0x0121:
            r2 = "V_MPEG4/ISO/AP";
            r1 = r1.equals(r2);
            if (r1 == 0) goto L_0x012b;
        L_0x0129:
            r1 = 5;
            goto L_0x012c;
        L_0x012b:
            r1 = r5;
        L_0x012c:
            r2 = 38;
            r6 = 0;
            switch(r1) {
                case 0: goto L_0x0297;
                case 1: goto L_0x0294;
                case 2: goto L_0x0291;
                case 3: goto L_0x0282;
                case 4: goto L_0x0282;
                case 5: goto L_0x0282;
                case 6: goto L_0x0261;
                case 7: goto L_0x0245;
                case 8: goto L_0x0236;
                case 9: goto L_0x0225;
                case 10: goto L_0x01de;
                case 11: goto L_0x01d5;
                case 12: goto L_0x01cb;
                case 13: goto L_0x01c7;
                case 14: goto L_0x01c3;
                case 15: goto L_0x01bf;
                case 16: goto L_0x01bb;
                case 17: goto L_0x01bb;
                case 18: goto L_0x01b7;
                case 19: goto L_0x01ad;
                case 20: goto L_0x0175;
                case 21: goto L_0x014c;
                case 22: goto L_0x0148;
                case 23: goto L_0x013e;
                case 24: goto L_0x013a;
                default: goto L_0x0132;
            };
        L_0x0132:
            r1 = new com.google.ads.interactivemedia.v3.internal.bl;
            r2 = "Unrecognized codec identifier.";
            r1.<init>(r2);
            throw r1;
        L_0x013a:
            r1 = "application/pgs";
            goto L_0x0299;
        L_0x013e:
            r1 = "application/vobsub";
            r2 = r0.h;
            r2 = java.util.Collections.singletonList(r2);
            goto L_0x0243;
        L_0x0148:
            r1 = "application/x-subrip";
            goto L_0x0299;
        L_0x014c:
            r1 = "audio/raw";
            r3 = r0.G;
            r3 = com.google.ads.interactivemedia.v3.internal.ft.a(r3);
            if (r3 != 0) goto L_0x016f;
        L_0x0156:
            r1 = new com.google.ads.interactivemedia.v3.internal.bl;
            r3 = r0.G;
            r4 = new java.lang.StringBuilder;
            r4.<init>(r2);
            r2 = "Unsupported PCM bit depth: ";
            r4.append(r2);
            r4.append(r3);
            r2 = r4.toString();
            r1.<init>(r2);
            throw r1;
        L_0x016f:
            r8 = r1;
            r17 = r3;
            r10 = r5;
            goto L_0x029d;
        L_0x0175:
            r1 = "audio/raw";
            r3 = new com.google.ads.interactivemedia.v3.internal.fp;
            r4 = r0.h;
            r3.<init>(r4);
            r3 = d(r3);
            if (r3 != 0) goto L_0x018c;
        L_0x0184:
            r1 = new com.google.ads.interactivemedia.v3.internal.bl;
            r2 = "Non-PCM MS/ACM is unsupported";
            r1.<init>(r2);
            throw r1;
        L_0x018c:
            r3 = r0.G;
            r3 = com.google.ads.interactivemedia.v3.internal.ft.a(r3);
            if (r3 != 0) goto L_0x016f;
        L_0x0194:
            r1 = new com.google.ads.interactivemedia.v3.internal.bl;
            r3 = r0.G;
            r4 = new java.lang.StringBuilder;
            r4.<init>(r2);
            r2 = "Unsupported PCM bit depth: ";
            r4.append(r2);
            r4.append(r3);
            r2 = r4.toString();
            r1.<init>(r2);
            throw r1;
        L_0x01ad:
            r1 = "audio/x-flac";
            r2 = r0.h;
            r2 = java.util.Collections.singletonList(r2);
            goto L_0x0243;
        L_0x01b7:
            r1 = "audio/vnd.dts.hd";
            goto L_0x0299;
        L_0x01bb:
            r1 = "audio/vnd.dts";
            goto L_0x0299;
        L_0x01bf:
            r1 = "audio/true-hd";
            goto L_0x0299;
        L_0x01c3:
            r1 = "audio/eac3";
            goto L_0x0299;
        L_0x01c7:
            r1 = "audio/ac3";
            goto L_0x0299;
        L_0x01cb:
            r1 = "audio/mpeg";
            r2 = 4096; // 0x1000 float:5.74E-42 double:2.0237E-320;
            r8 = r1;
            r10 = r2;
            r17 = r5;
            goto L_0x029d;
        L_0x01d5:
            r1 = "audio/mp4a-latm";
            r2 = r0.h;
            r2 = java.util.Collections.singletonList(r2);
            goto L_0x0243;
        L_0x01de:
            r1 = "audio/opus";
            r2 = 5760; // 0x1680 float:8.071E-42 double:2.846E-320;
            r7 = new java.util.ArrayList;
            r7.<init>(r3);
            r3 = r0.h;
            r7.add(r3);
            r3 = java.nio.ByteBuffer.allocate(r4);
            r8 = java.nio.ByteOrder.nativeOrder();
            r3 = r3.order(r8);
            r8 = r0.I;
            r3 = r3.putLong(r8);
            r3 = r3.array();
            r7.add(r3);
            r3 = java.nio.ByteBuffer.allocate(r4);
            r4 = java.nio.ByteOrder.nativeOrder();
            r3 = r3.order(r4);
            r8 = r0.J;
            r3 = r3.putLong(r8);
            r3 = r3.array();
            r7.add(r3);
            r8 = r1;
            r10 = r2;
            r17 = r5;
            r2 = r7;
            goto L_0x029e;
        L_0x0225:
            r1 = "audio/vorbis";
            r2 = 8192; // 0x2000 float:1.14794E-41 double:4.0474E-320;
            r3 = r0.h;
            r3 = a(r3);
            r8 = r1;
            r10 = r2;
            r2 = r3;
            r17 = r5;
            goto L_0x029e;
        L_0x0236:
            r1 = "video/wvc1";
            r2 = new com.google.ads.interactivemedia.v3.internal.fp;
            r3 = r0.h;
            r2.<init>(r3);
            r2 = a(r2);
        L_0x0243:
            r8 = r1;
            goto L_0x027e;
        L_0x0245:
            r1 = "video/hevc";
            r2 = new com.google.ads.interactivemedia.v3.internal.fp;
            r3 = r0.h;
            r2.<init>(r3);
            r2 = c(r2);
            r3 = r2.first;
            r3 = (java.util.List) r3;
            r2 = r2.second;
            r2 = (java.lang.Integer) r2;
            r2 = r2.intValue();
            r0.L = r2;
            goto L_0x027c;
        L_0x0261:
            r1 = "video/avc";
            r2 = new com.google.ads.interactivemedia.v3.internal.fp;
            r3 = r0.h;
            r2.<init>(r3);
            r2 = b(r2);
            r3 = r2.first;
            r3 = (java.util.List) r3;
            r2 = r2.second;
            r2 = (java.lang.Integer) r2;
            r2 = r2.intValue();
            r0.L = r2;
        L_0x027c:
            r8 = r1;
            r2 = r3;
        L_0x027e:
            r10 = r5;
            r17 = r10;
            goto L_0x029e;
        L_0x0282:
            r1 = "video/mp4v-es";
            r2 = r0.h;
            if (r2 != 0) goto L_0x028a;
        L_0x0288:
            r2 = r6;
            goto L_0x0243;
        L_0x028a:
            r2 = r0.h;
            r2 = java.util.Collections.singletonList(r2);
            goto L_0x0243;
        L_0x0291:
            r1 = "video/mpeg2";
            goto L_0x0299;
        L_0x0294:
            r1 = "video/x-vnd.on2.vp9";
            goto L_0x0299;
        L_0x0297:
            r1 = "video/x-vnd.on2.vp8";
        L_0x0299:
            r8 = r1;
            r10 = r5;
            r17 = r10;
        L_0x029d:
            r2 = r6;
        L_0x029e:
            r1 = com.google.ads.interactivemedia.v3.internal.fl.a(r8);
            if (r1 == 0) goto L_0x02ba;
        L_0x02a4:
            r7 = java.lang.Integer.toString(r23);
            r9 = -1;
            r13 = r0.F;
            r14 = r0.H;
            r1 = r0.M;
            r11 = r24;
            r15 = r2;
            r16 = r1;
            r1 = com.google.ads.interactivemedia.v3.internal.bj.a(r7, r8, r9, r10, r11, r13, r14, r15, r16, r17);
            goto L_0x0360;
        L_0x02ba:
            r1 = com.google.ads.interactivemedia.v3.internal.fl.b(r8);
            if (r1 == 0) goto L_0x0323;
        L_0x02c0:
            r1 = r0.m;
            if (r1 != 0) goto L_0x02da;
        L_0x02c4:
            r1 = r0.k;
            if (r1 != r5) goto L_0x02cb;
        L_0x02c8:
            r1 = r0.i;
            goto L_0x02cd;
        L_0x02cb:
            r1 = r0.k;
        L_0x02cd:
            r0.k = r1;
            r1 = r0.l;
            if (r1 != r5) goto L_0x02d6;
        L_0x02d3:
            r1 = r0.j;
            goto L_0x02d8;
        L_0x02d6:
            r1 = r0.l;
        L_0x02d8:
            r0.l = r1;
        L_0x02da:
            r1 = -1082130432; // 0xffffffffbf800000 float:-1.0 double:NaN;
            r3 = r0.k;
            if (r3 == r5) goto L_0x02f1;
        L_0x02e0:
            r3 = r0.l;
            if (r3 == r5) goto L_0x02f1;
        L_0x02e4:
            r1 = r0.j;
            r3 = r0.k;
            r1 = r1 * r3;
            r1 = (float) r1;
            r3 = r0.i;
            r4 = r0.l;
            r3 = r3 * r4;
            r3 = (float) r3;
            r1 = r1 / r3;
        L_0x02f1:
            r17 = r1;
            r1 = r0.p;
            if (r1 == 0) goto L_0x0306;
        L_0x02f7:
            r1 = r21.a();
            r6 = new com.google.ads.interactivemedia.v3.internal.aw;
            r3 = r0.q;
            r4 = r0.s;
            r5 = r0.r;
            r6.<init>(r3, r4, r5, r1);
        L_0x0306:
            r20 = r6;
            r7 = java.lang.Integer.toString(r23);
            r9 = -1;
            r13 = r0.i;
            r14 = r0.j;
            r16 = -1;
            r1 = r0.n;
            r3 = r0.o;
            r11 = r24;
            r15 = r2;
            r18 = r1;
            r19 = r3;
            r1 = com.google.ads.interactivemedia.v3.internal.bj.a(r7, r8, r9, r10, r11, r13, r14, r15, r16, r17, r18, r19, r20);
            goto L_0x0360;
        L_0x0323:
            r1 = "application/x-subrip";
            r1 = r1.equals(r8);
            if (r1 == 0) goto L_0x0339;
        L_0x032b:
            r7 = java.lang.Integer.toString(r23);
            r9 = -1;
            r12 = r0.M;
            r10 = r24;
            r1 = com.google.ads.interactivemedia.v3.internal.bj.a(r7, r8, r9, r10, r12);
            goto L_0x0360;
        L_0x0339:
            r1 = "application/vobsub";
            r1 = r1.equals(r8);
            if (r1 != 0) goto L_0x0352;
        L_0x0341:
            r1 = "application/pgs";
            r1 = r1.equals(r8);
            if (r1 == 0) goto L_0x034a;
        L_0x0349:
            goto L_0x0352;
        L_0x034a:
            r1 = new com.google.ads.interactivemedia.v3.internal.bl;
            r2 = "Unexpected MIME type.";
            r1.<init>(r2);
            throw r1;
        L_0x0352:
            r7 = java.lang.Integer.toString(r23);
            r9 = -1;
            r13 = r0.M;
            r10 = r24;
            r12 = r2;
            r1 = com.google.ads.interactivemedia.v3.internal.bj.a(r7, r8, r9, r10, r12, r13);
        L_0x0360:
            r2 = r0.b;
            r3 = r22;
            r2 = r3.d(r2);
            r0.K = r2;
            r2 = r0.K;
            r2.a(r1);
            return;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.ads.interactivemedia.v3.internal.en$b.a(com.google.ads.interactivemedia.v3.internal.ce, int, long):void");
        }

        private byte[] a() {
            if (this.v == -1.0f || this.w == -1.0f || this.x == -1.0f || this.y == -1.0f || this.z == -1.0f || this.A == -1.0f || this.B == -1.0f || this.C == -1.0f || this.D == -1.0f || this.E == -1.0f) {
                return null;
            }
            byte[] bArr = new byte[25];
            ByteBuffer wrap = ByteBuffer.wrap(bArr);
            wrap.put((byte) 0);
            wrap.putShort((short) ((int) ((this.v * 50000.0f) + 0.5f)));
            wrap.putShort((short) ((int) ((this.w * 50000.0f) + 0.5f)));
            wrap.putShort((short) ((int) ((this.x * 50000.0f) + 0.5f)));
            wrap.putShort((short) ((int) ((this.y * 50000.0f) + 0.5f)));
            wrap.putShort((short) ((int) ((this.z * 50000.0f) + 0.5f)));
            wrap.putShort((short) ((int) ((this.A * 50000.0f) + 0.5f)));
            wrap.putShort((short) ((int) ((this.B * 50000.0f) + 0.5f)));
            wrap.putShort((short) ((int) ((this.C * 50000.0f) + 0.5f)));
            wrap.putShort((short) ((int) (this.D + 0.5f)));
            wrap.putShort((short) ((int) (this.E + 0.5f)));
            wrap.putShort((short) this.t);
            wrap.putShort((short) this.u);
            return bArr;
        }

        private static List<byte[]> a(fp fpVar) throws bl {
            try {
                fpVar.d(16);
                long l = fpVar.l();
                if (l != 826496599) {
                    StringBuilder stringBuilder = new StringBuilder(57);
                    stringBuilder.append("Unsupported FourCC compression type: ");
                    stringBuilder.append(l);
                    throw new bl(stringBuilder.toString());
                }
                int d = fpVar.d() + 20;
                byte[] bArr = fpVar.a;
                while (d < bArr.length - 4) {
                    if (bArr[d] == (byte) 0 && bArr[d + 1] == (byte) 0 && bArr[d + 2] == (byte) 1 && bArr[d + 3] == (byte) 15) {
                        return Collections.singletonList(Arrays.copyOfRange(bArr, d, bArr.length));
                    }
                    d++;
                }
                throw new bl("Failed to find FourCC VC1 initialization data");
            } catch (ArrayIndexOutOfBoundsException unused) {
                throw new bl("Error parsing FourCC VC1 codec private");
            }
        }

        private static Pair<List<byte[]>, Integer> b(fp fpVar) throws bl {
            try {
                fpVar.c(4);
                int f = (fpVar.f() & 3) + 1;
                if (f == 3) {
                    throw new bl();
                }
                ArrayList arrayList = new ArrayList();
                int f2 = fpVar.f() & 31;
                int i = 0;
                for (int i2 = 0; i2 < f2; i2++) {
                    arrayList.add(fn.a(fpVar));
                }
                f2 = fpVar.f();
                while (i < f2) {
                    arrayList.add(fn.a(fpVar));
                    i++;
                }
                return Pair.create(arrayList, Integer.valueOf(f));
            } catch (ArrayIndexOutOfBoundsException unused) {
                throw new bl("Error parsing AVC codec private");
            }
        }

        private static Pair<List<byte[]>, Integer> c(fp fpVar) throws bl {
            try {
                int i;
                int g;
                Object obj;
                fpVar.c(21);
                int f = fpVar.f() & 3;
                int f2 = fpVar.f();
                int d = fpVar.d();
                int i2 = 0;
                int i3 = i2;
                while (i2 < f2) {
                    fpVar.d(1);
                    int g2 = fpVar.g();
                    i = i3;
                    for (i3 = 0; i3 < g2; i3++) {
                        g = fpVar.g();
                        i += 4 + g;
                        fpVar.d(g);
                    }
                    i2++;
                    i3 = i;
                }
                fpVar.c(d);
                byte[] bArr = new byte[i3];
                i2 = 0;
                i = i2;
                while (i2 < f2) {
                    fpVar.d(1);
                    g = fpVar.g();
                    int i4 = i;
                    for (i = 0; i < g; i++) {
                        int g3 = fpVar.g();
                        System.arraycopy(fn.a, 0, bArr, i4, fn.a.length);
                        i4 += fn.a.length;
                        System.arraycopy(fpVar.a, fpVar.d(), bArr, i4, g3);
                        i4 += g3;
                        fpVar.d(g3);
                    }
                    i2++;
                    i = i4;
                }
                if (i3 == 0) {
                    obj = null;
                } else {
                    obj = Collections.singletonList(bArr);
                }
                return Pair.create(obj, Integer.valueOf(f + 1));
            } catch (ArrayIndexOutOfBoundsException unused) {
                throw new bl("Error parsing HEVC codec private");
            }
        }

        private static List<byte[]> a(byte[] bArr) throws bl {
            try {
                if (bArr[0] != (byte) 2) {
                    throw new bl("Error parsing vorbis codec private");
                }
                int i = 0;
                int i2 = 1;
                while (bArr[i2] == (byte) -1) {
                    i += 255;
                    i2++;
                }
                int i3 = i2 + 1;
                i += bArr[i2];
                i2 = 0;
                while (bArr[i3] == (byte) -1) {
                    i2 += 255;
                    i3++;
                }
                int i4 = i3 + 1;
                i2 += bArr[i3];
                if (bArr[i4] != (byte) 1) {
                    throw new bl("Error parsing vorbis codec private");
                }
                byte[] bArr2 = new byte[i];
                System.arraycopy(bArr, i4, bArr2, 0, i);
                i4 += i;
                if (bArr[i4] != (byte) 3) {
                    throw new bl("Error parsing vorbis codec private");
                }
                i4 += i2;
                if (bArr[i4] != (byte) 5) {
                    throw new bl("Error parsing vorbis codec private");
                }
                byte[] bArr3 = new byte[(bArr.length - i4)];
                System.arraycopy(bArr, i4, bArr3, 0, bArr.length - i4);
                ArrayList arrayList = new ArrayList(2);
                arrayList.add(bArr2);
                arrayList.add(bArr3);
                return arrayList;
            } catch (ArrayIndexOutOfBoundsException unused) {
                throw new bl("Error parsing vorbis codec private");
            }
        }

        private static boolean d(fp fpVar) throws bl {
            try {
                int h = fpVar.h();
                boolean z = true;
                if (h == 1) {
                    return true;
                }
                if (h != 65534) {
                    return false;
                }
                fpVar.c(24);
                if (!(fpVar.o() == en.c.getMostSignificantBits() && fpVar.o() == en.c.getLeastSignificantBits())) {
                    z = false;
                }
                return z;
            } catch (ArrayIndexOutOfBoundsException unused) {
                throw new bl("Error parsing MS/ACM codec private");
            }
        }
    }

    private final class a implements ek {
        private a() {
        }

        public int a(int i) {
            return en.this.a(i);
        }

        public boolean b(int i) {
            return en.this.b(i);
        }

        public void a(int i, long j, long j2) throws bl {
            en.this.a(i, j, j2);
        }

        public void c(int i) throws bl {
            en.this.c(i);
        }

        public void a(int i, long j) throws bl {
            en.this.a(i, j);
        }

        public void a(int i, double d) throws bl {
            en.this.a(i, d);
        }

        public void a(int i, String str) throws bl {
            en.this.a(i, str);
        }

        public void a(int i, int i2, cd cdVar) throws IOException, InterruptedException {
            en.this.a(i, i2, cdVar);
        }
    }

    public en() {
        this(new ei(), 0);
    }

    /* Access modifiers changed, original: 0000 */
    public int a(int i) {
        switch (i) {
            case 131:
            case 155:
            case 159:
            case 176:
            case 179:
            case 186:
            case 215:
            case 231:
            case 241:
            case 251:
            case 16980:
            case 17029:
            case 17143:
            case 18401:
            case 18408:
            case 20529:
            case 20530:
            case 21420:
            case 21432:
            case 21680:
            case 21682:
            case 21690:
            case 21945:
            case 21946:
            case 21947:
            case 21948:
            case 21949:
            case 22186:
            case 22203:
            case 25188:
            case 2352003:
            case 2807729:
                return 2;
            case TsExtractor.TS_STREAM_TYPE_SPLICE_INFO /*134*/:
            case FirebaseError.ERROR_WEAK_PASSWORD /*17026*/:
            case 2274716:
                return 3;
            case MoEHelperUtils.BASELINE_SCREEN_DPI /*160*/:
            case 174:
            case 183:
            case 187:
            case 224:
            case 225:
            case 18407:
            case 19899:
            case 20532:
            case 20533:
            case 21936:
            case 21968:
            case 25152:
            case 28032:
            case 30320:
            case 290298740:
            case 357149030:
            case 374648427:
            case 408125543:
            case 440786851:
            case 475249515:
            case 524531317:
                return 1;
            case 161:
            case 163:
            case 16981:
            case 18402:
            case 21419:
            case 25506:
            case 30322:
                return 4;
            case 181:
            case 17545:
            case 21969:
            case 21970:
            case 21971:
            case 21972:
            case 21973:
            case 21974:
            case 21975:
            case 21976:
            case 21977:
            case 21978:
                return 5;
            default:
                return 0;
        }
    }

    /* Access modifiers changed, original: 0000 */
    public boolean b(int i) {
        return i == 357149030 || i == 524531317 || i == 475249515 || i == 374648427;
    }

    public void c() {
    }

    en(ej ejVar, int i) {
        this.r = -1;
        this.s = -1;
        this.t = -1;
        this.u = -1;
        this.v = -1;
        this.C = -1;
        this.D = -1;
        this.E = -1;
        this.d = ejVar;
        this.d.a(new a());
        boolean z = true;
        if ((i & 1) != 0) {
            z = false;
        }
        this.g = z;
        this.e = new em();
        this.f = new SparseArray();
        this.j = new fp(4);
        this.k = new fp(ByteBuffer.allocate(4).putInt(-1).array());
        this.l = new fp(4);
        this.h = new fp(fn.a);
        this.i = new fp(4);
        this.m = new fp();
        this.n = new fp();
        this.o = new fp(8);
        this.p = new fp();
    }

    public boolean a(cd cdVar) throws IOException, InterruptedException {
        return new el().a(cdVar);
    }

    public void a(ce ceVar) {
        this.ac = ceVar;
    }

    public void b() {
        this.E = -1;
        this.I = 0;
        this.d.a();
        this.e.a();
        d();
    }

    public int a(cd cdVar, ch chVar) throws IOException, InterruptedException {
        int i = 0;
        this.aa = false;
        boolean z = true;
        while (z && !this.aa) {
            z = this.d.a(cdVar);
            if (z && a(chVar, cdVar.c())) {
                return 1;
            }
        }
        if (!z) {
            i = -1;
        }
        return i;
    }

    /* Access modifiers changed, original: 0000 */
    public void a(int i, long j, long j2) throws bl {
        if (i == MoEHelperUtils.BASELINE_SCREEN_DPI) {
            this.ab = false;
        } else if (i == 174) {
            this.w = new b();
        } else if (i == 187) {
            this.H = false;
        } else if (i == 19899) {
            this.z = -1;
            this.A = -1;
        } else if (i == 20533) {
            this.w.e = true;
        } else if (i == 21968) {
            this.w.p = true;
        } else if (i == 25152) {
        } else {
            if (i != 408125543) {
                if (i != 475249515) {
                    if (i == 524531317 && !this.y) {
                        if (!this.g || this.C == -1) {
                            this.ac.a(cj.f);
                            this.y = true;
                        } else {
                            this.B = true;
                        }
                    }
                    return;
                }
                this.F = new fk();
                this.G = new fk();
            } else if (this.r == -1 || this.r == j) {
                this.r = j;
                this.s = j2;
            } else {
                throw new bl("Multiple Segment elements not supported");
            }
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void c(int i) throws bl {
        if (i != MoEHelperUtils.BASELINE_SCREEN_DPI) {
            if (i == 174) {
                if (a(this.w.a)) {
                    this.w.a(this.ac, this.w.b, this.v);
                    this.f.put(this.w.b, this.w);
                }
                this.w = null;
            } else if (i != 19899) {
                if (i == 25152) {
                    if (this.w.e) {
                        if (this.w.g == null) {
                            throw new bl("Encrypted Track found but ContentEncKeyID was not found");
                        } else if (!this.x) {
                            this.ac.a(new c(new com.google.ads.interactivemedia.v3.internal.bu.b(MimeTypes.VIDEO_WEBM, this.w.g)));
                            this.x = true;
                        }
                    }
                } else if (i != 28032) {
                    if (i == 357149030) {
                        if (this.t == -1) {
                            this.t = 1000000;
                        }
                        if (this.u != -1) {
                            this.v = a(this.u);
                        }
                    } else if (i != 374648427) {
                        if (i == 475249515 && !this.y) {
                            this.ac.a(e());
                            this.y = true;
                        }
                    } else if (this.f.size() == 0) {
                        throw new bl("No valid tracks were found");
                    } else {
                        this.ac.f();
                    }
                } else if (this.w.e && this.w.f != null) {
                    throw new bl("Combining encryption and compression is not supported");
                }
            } else if (this.z == -1 || this.A == -1) {
                throw new bl("Mandatory element SeekID or SeekPosition not found");
            } else {
                if (this.z == 475249515) {
                    this.C = this.A;
                }
            }
        } else if (this.I == 2) {
            if (!this.ab) {
                this.Q |= 1;
            }
            a((b) this.f.get(this.O), this.J);
            this.I = 0;
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void a(int i, long j) throws bl {
        StringBuilder stringBuilder;
        StringBuilder stringBuilder2;
        switch (i) {
            case 131:
                this.w.c = (int) j;
                return;
            case 155:
                this.K = a(j);
                return;
            case 159:
                this.w.F = (int) j;
                return;
            case 176:
                this.w.i = (int) j;
                return;
            case 179:
                this.F.a(a(j));
                return;
            case 186:
                this.w.j = (int) j;
                return;
            case 215:
                this.w.b = (int) j;
                return;
            case 231:
                this.E = a(j);
                return;
            case 241:
                if (!this.H) {
                    this.G.a(j);
                    this.H = true;
                }
                return;
            case 251:
                this.ab = true;
                return;
            case 16980:
                if (j != 3) {
                    stringBuilder = new StringBuilder(50);
                    stringBuilder.append("ContentCompAlgo ");
                    stringBuilder.append(j);
                    stringBuilder.append(" not supported");
                    throw new bl(stringBuilder.toString());
                }
                return;
            case 17029:
                if (j < 1 || j > 2) {
                    stringBuilder = new StringBuilder(53);
                    stringBuilder.append("DocTypeReadVersion ");
                    stringBuilder.append(j);
                    stringBuilder.append(" not supported");
                    throw new bl(stringBuilder.toString());
                }
                return;
            case 17143:
                if (j != 1) {
                    stringBuilder = new StringBuilder(50);
                    stringBuilder.append("EBMLReadVersion ");
                    stringBuilder.append(j);
                    stringBuilder.append(" not supported");
                    throw new bl(stringBuilder.toString());
                }
                return;
            case 18401:
                if (j != 5) {
                    stringBuilder = new StringBuilder(49);
                    stringBuilder.append("ContentEncAlgo ");
                    stringBuilder.append(j);
                    stringBuilder.append(" not supported");
                    throw new bl(stringBuilder.toString());
                }
                return;
            case 18408:
                if (j != 1) {
                    stringBuilder = new StringBuilder(56);
                    stringBuilder.append("AESSettingsCipherMode ");
                    stringBuilder.append(j);
                    stringBuilder.append(" not supported");
                    throw new bl(stringBuilder.toString());
                }
                return;
            case 20529:
                if (j != 0) {
                    stringBuilder2 = new StringBuilder(55);
                    stringBuilder2.append("ContentEncodingOrder ");
                    stringBuilder2.append(j);
                    stringBuilder2.append(" not supported");
                    throw new bl(stringBuilder2.toString());
                }
                return;
            case 20530:
                if (j != 1) {
                    stringBuilder2 = new StringBuilder(55);
                    stringBuilder2.append("ContentEncodingScope ");
                    stringBuilder2.append(j);
                    stringBuilder2.append(" not supported");
                    throw new bl(stringBuilder2.toString());
                }
                return;
            case 21420:
                this.A = j + this.r;
                return;
            case 21432:
                i = (int) j;
                if (i == 3) {
                    this.w.o = 1;
                } else if (i != 15) {
                    switch (i) {
                        case 0:
                            this.w.o = 0;
                            break;
                        case 1:
                            this.w.o = 2;
                            break;
                    }
                } else {
                    this.w.o = 3;
                }
                return;
            case 21680:
                this.w.k = (int) j;
                return;
            case 21682:
                this.w.m = (int) j;
                return;
            case 21690:
                this.w.l = (int) j;
                return;
            case 21945:
                switch ((int) j) {
                    case 1:
                        this.w.s = 2;
                        break;
                    case 2:
                        this.w.s = 1;
                        break;
                }
                break;
            case 21946:
                i = (int) j;
                if (i != 1) {
                    if (i != 16) {
                        if (i == 18) {
                            this.w.r = 7;
                            break;
                        } else {
                            switch (i) {
                                case 6:
                                case 7:
                                    break;
                            }
                        }
                    }
                    this.w.r = 6;
                    break;
                }
                this.w.r = 3;
                break;
            case 21947:
                this.w.p = true;
                i = (int) j;
                if (i != 1) {
                    if (i == 9) {
                        this.w.q = 6;
                        break;
                    }
                    switch (i) {
                        case 4:
                        case 5:
                        case 6:
                        case 7:
                            this.w.q = 2;
                            break;
                    }
                }
                this.w.q = 1;
                break;
                break;
            case 21948:
                this.w.t = (int) j;
                break;
            case 21949:
                this.w.u = (int) j;
                break;
            case 22186:
                this.w.I = j;
                return;
            case 22203:
                this.w.J = j;
                return;
            case 25188:
                this.w.G = (int) j;
                return;
            case 2352003:
                this.w.d = (int) j;
                return;
            case 2807729:
                this.t = j;
                return;
            default:
                return;
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void a(int i, double d) {
        if (i == 181) {
            this.w.H = (int) d;
        } else if (i != 17545) {
            switch (i) {
                case 21969:
                    this.w.v = (float) d;
                    break;
                case 21970:
                    this.w.w = (float) d;
                    break;
                case 21971:
                    this.w.x = (float) d;
                    break;
                case 21972:
                    this.w.y = (float) d;
                    break;
                case 21973:
                    this.w.z = (float) d;
                    break;
                case 21974:
                    this.w.A = (float) d;
                    break;
                case 21975:
                    this.w.B = (float) d;
                    break;
                case 21976:
                    this.w.C = (float) d;
                    break;
                case 21977:
                    this.w.D = (float) d;
                    break;
                case 21978:
                    this.w.E = (float) d;
                    break;
                default:
                    return;
            }
        } else {
            this.u = (long) d;
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void a(int i, String str) throws bl {
        if (i == TsExtractor.TS_STREAM_TYPE_SPLICE_INFO) {
            this.w.a = str;
        } else if (i != FirebaseError.ERROR_WEAK_PASSWORD) {
            if (i == 2274716) {
                this.w.M = str;
            }
        } else if (!"webm".equals(str) && !"matroska".equals(str)) {
            StringBuilder stringBuilder = new StringBuilder(22 + String.valueOf(str).length());
            stringBuilder.append("DocType ");
            stringBuilder.append(str);
            stringBuilder.append(" not supported");
            throw new bl(stringBuilder.toString());
        }
    }

    /* Access modifiers changed, original: 0000 */
    /* JADX WARNING: Removed duplicated region for block: B:111:0x020b A:{SYNTHETIC, SKIP} */
    /* JADX WARNING: Removed duplicated region for block: B:74:0x01e8  */
    public void a(int r25, int r26, com.google.ads.interactivemedia.v3.internal.cd r27) throws java.io.IOException, java.lang.InterruptedException {
        /*
        r24 = this;
        r0 = r24;
        r1 = r25;
        r2 = r26;
        r3 = r27;
        r4 = 161; // 0xa1 float:2.26E-43 double:7.95E-322;
        r5 = 163; // 0xa3 float:2.28E-43 double:8.05E-322;
        r6 = 4;
        r7 = 0;
        if (r1 == r4) goto L_0x0095;
    L_0x0010:
        if (r1 == r5) goto L_0x0095;
    L_0x0012:
        r4 = 16981; // 0x4255 float:2.3795E-41 double:8.3897E-320;
        if (r1 == r4) goto L_0x0087;
    L_0x0016:
        r4 = 18402; // 0x47e2 float:2.5787E-41 double:9.092E-320;
        if (r1 == r4) goto L_0x0079;
    L_0x001a:
        r4 = 21419; // 0x53ab float:3.0014E-41 double:1.05824E-319;
        if (r1 == r4) goto L_0x005b;
    L_0x001e:
        r4 = 25506; // 0x63a2 float:3.5742E-41 double:1.26016E-319;
        if (r1 == r4) goto L_0x004d;
    L_0x0022:
        r4 = 30322; // 0x7672 float:4.249E-41 double:1.4981E-319;
        if (r1 == r4) goto L_0x003f;
    L_0x0026:
        r2 = new com.google.ads.interactivemedia.v3.internal.bl;
        r3 = 26;
        r4 = new java.lang.StringBuilder;
        r4.<init>(r3);
        r3 = "Unexpected id: ";
        r4.append(r3);
        r4.append(r1);
        r1 = r4.toString();
        r2.<init>(r1);
        throw r2;
    L_0x003f:
        r1 = r0.w;
        r4 = new byte[r2];
        r1.n = r4;
        r1 = r0.w;
        r1 = r1.n;
        r3.b(r1, r7, r2);
        return;
    L_0x004d:
        r1 = r0.w;
        r4 = new byte[r2];
        r1.h = r4;
        r1 = r0.w;
        r1 = r1.h;
        r3.b(r1, r7, r2);
        return;
    L_0x005b:
        r1 = r0.l;
        r1 = r1.a;
        java.util.Arrays.fill(r1, r7);
        r1 = r0.l;
        r1 = r1.a;
        r6 = r6 - r2;
        r3.b(r1, r6, r2);
        r1 = r0.l;
        r1.c(r7);
        r1 = r0.l;
        r1 = r1.k();
        r1 = (int) r1;
        r0.z = r1;
        return;
    L_0x0079:
        r1 = r0.w;
        r4 = new byte[r2];
        r1.g = r4;
        r1 = r0.w;
        r1 = r1.g;
        r3.b(r1, r7, r2);
        return;
    L_0x0087:
        r1 = r0.w;
        r4 = new byte[r2];
        r1.f = r4;
        r1 = r0.w;
        r1 = r1.f;
        r3.b(r1, r7, r2);
        return;
    L_0x0095:
        r4 = r0.I;
        r8 = 8;
        r9 = 1;
        if (r4 != 0) goto L_0x00b8;
    L_0x009c:
        r4 = r0.e;
        r10 = r4.a(r3, r7, r9, r8);
        r4 = (int) r10;
        r0.O = r4;
        r4 = r0.e;
        r4 = r4.b();
        r0.P = r4;
        r10 = -1;
        r0.K = r10;
        r0.I = r9;
        r4 = r0.j;
        r4.a();
    L_0x00b8:
        r4 = r0.f;
        r10 = r0.O;
        r4 = r4.get(r10);
        r4 = (com.google.ads.interactivemedia.v3.internal.en.b) r4;
        if (r4 != 0) goto L_0x00ce;
    L_0x00c4:
        r1 = r0.P;
        r1 = r2 - r1;
        r3.b(r1);
        r0.I = r7;
        return;
    L_0x00ce:
        r10 = r0.I;
        if (r10 != r9) goto L_0x028c;
    L_0x00d2:
        r10 = 3;
        r0.a(r3, r10);
        r11 = r0.j;
        r11 = r11.a;
        r12 = 2;
        r11 = r11[r12];
        r13 = 6;
        r11 = r11 & r13;
        r11 = r11 >> r9;
        r14 = 255; // 0xff float:3.57E-43 double:1.26E-321;
        if (r11 != 0) goto L_0x00f8;
    L_0x00e4:
        r0.M = r9;
        r6 = r0.N;
        r6 = a(r6, r9);
        r0.N = r6;
        r6 = r0.N;
        r11 = r0.P;
        r2 = r2 - r11;
        r2 = r2 - r10;
        r6[r7] = r2;
        goto L_0x0223;
    L_0x00f8:
        if (r1 == r5) goto L_0x0102;
    L_0x00fa:
        r1 = new com.google.ads.interactivemedia.v3.internal.bl;
        r2 = "Lacing only supported in SimpleBlocks.";
        r1.<init>(r2);
        throw r1;
    L_0x0102:
        r0.a(r3, r6);
        r15 = r0.j;
        r15 = r15.a;
        r15 = r15[r10];
        r15 = r15 & r14;
        r15 = r15 + r9;
        r0.M = r15;
        r15 = r0.N;
        r5 = r0.M;
        r5 = a(r15, r5);
        r0.N = r5;
        if (r11 != r12) goto L_0x012b;
    L_0x011b:
        r5 = r0.P;
        r2 = r2 - r5;
        r2 = r2 - r6;
        r5 = r0.M;
        r2 = r2 / r5;
        r5 = r0.N;
        r6 = r0.M;
        java.util.Arrays.fill(r5, r7, r6, r2);
        goto L_0x0223;
    L_0x012b:
        if (r11 != r9) goto L_0x0164;
    L_0x012d:
        r5 = r7;
        r10 = r5;
    L_0x012f:
        r11 = r0.M;
        r11 = r11 - r9;
        if (r5 >= r11) goto L_0x0156;
    L_0x0134:
        r11 = r0.N;
        r11[r5] = r7;
    L_0x0138:
        r6 = r6 + r9;
        r0.a(r3, r6);
        r11 = r0.j;
        r11 = r11.a;
        r13 = r6 + -1;
        r11 = r11[r13];
        r11 = r11 & r14;
        r13 = r0.N;
        r15 = r13[r5];
        r15 = r15 + r11;
        r13[r5] = r15;
        if (r11 == r14) goto L_0x0138;
    L_0x014e:
        r11 = r0.N;
        r11 = r11[r5];
        r10 = r10 + r11;
        r5 = r5 + 1;
        goto L_0x012f;
    L_0x0156:
        r5 = r0.N;
        r11 = r0.M;
        r11 = r11 - r9;
        r13 = r0.P;
        r2 = r2 - r13;
        r2 = r2 - r6;
        r2 = r2 - r10;
        r5[r11] = r2;
        goto L_0x0223;
    L_0x0164:
        if (r11 != r10) goto L_0x0273;
    L_0x0166:
        r5 = r7;
        r10 = r5;
    L_0x0168:
        r11 = r0.M;
        r11 = r11 - r9;
        if (r5 >= r11) goto L_0x0213;
    L_0x016d:
        r11 = r0.N;
        r11[r5] = r7;
        r6 = r6 + 1;
        r0.a(r3, r6);
        r11 = r0.j;
        r11 = r11.a;
        r15 = r6 + -1;
        r11 = r11[r15];
        if (r11 != 0) goto L_0x0188;
    L_0x0180:
        r1 = new com.google.ads.interactivemedia.v3.internal.bl;
        r2 = "No valid varint length mask found";
        r1.<init>(r2);
        throw r1;
    L_0x0188:
        r16 = 0;
        r11 = r7;
    L_0x018b:
        if (r11 >= r8) goto L_0x01dd;
    L_0x018d:
        r18 = 7 - r11;
        r18 = r9 << r18;
        r12 = r0.j;
        r12 = r12.a;
        r12 = r12[r15];
        r12 = r12 & r18;
        if (r12 == 0) goto L_0x01d6;
    L_0x019b:
        r6 = r6 + r11;
        r0.a(r3, r6);
        r12 = r0.j;
        r12 = r12.a;
        r16 = r15 + 1;
        r12 = r12[r15];
        r12 = r12 & r14;
        r15 = r18 ^ -1;
        r12 = r12 & r15;
        r19 = r10;
        r9 = (long) r12;
        r22 = r9;
        r9 = r16;
        r16 = r22;
    L_0x01b4:
        if (r9 >= r6) goto L_0x01c8;
    L_0x01b6:
        r16 = r16 << r8;
        r10 = r0.j;
        r10 = r10.a;
        r12 = r9 + 1;
        r9 = r10[r9];
        r9 = r9 & r14;
        r9 = (long) r9;
        r20 = r16 | r9;
        r9 = r12;
        r16 = r20;
        goto L_0x01b4;
    L_0x01c8:
        if (r5 <= 0) goto L_0x01df;
    L_0x01ca:
        r11 = r11 * 7;
        r11 = r11 + r13;
        r9 = 1;
        r11 = r9 << r11;
        r20 = r11 - r9;
        r9 = r16 - r20;
        goto L_0x01e1;
    L_0x01d6:
        r19 = r10;
        r11 = r11 + 1;
        r9 = 1;
        r12 = 2;
        goto L_0x018b;
    L_0x01dd:
        r19 = r10;
    L_0x01df:
        r9 = r16;
    L_0x01e1:
        r11 = -2147483648; // 0xffffffff80000000 float:-0.0 double:NaN;
        r15 = (r9 > r11 ? 1 : (r9 == r11 ? 0 : -1));
        if (r15 < 0) goto L_0x020b;
    L_0x01e8:
        r11 = 2147483647; // 0x7fffffff float:NaN double:1.060997895E-314;
        r15 = (r9 > r11 ? 1 : (r9 == r11 ? 0 : -1));
        if (r15 <= 0) goto L_0x01f0;
    L_0x01ef:
        goto L_0x020b;
    L_0x01f0:
        r9 = (int) r9;
        r10 = r0.N;
        if (r5 != 0) goto L_0x01f6;
    L_0x01f5:
        goto L_0x01fd;
    L_0x01f6:
        r11 = r0.N;
        r12 = r5 + -1;
        r11 = r11[r12];
        r9 = r9 + r11;
    L_0x01fd:
        r10[r5] = r9;
        r9 = r0.N;
        r9 = r9[r5];
        r10 = r19 + r9;
        r5 = r5 + 1;
        r9 = 1;
        r12 = 2;
        goto L_0x0168;
    L_0x020b:
        r1 = new com.google.ads.interactivemedia.v3.internal.bl;
        r2 = "EBML lacing sample size out of range.";
        r1.<init>(r2);
        throw r1;
    L_0x0213:
        r19 = r10;
        r5 = r0.N;
        r9 = r0.M;
        r10 = 1;
        r9 = r9 - r10;
        r10 = r0.P;
        r2 = r2 - r10;
        r2 = r2 - r6;
        r2 = r2 - r19;
        r5[r9] = r2;
    L_0x0223:
        r2 = r0.j;
        r2 = r2.a;
        r2 = r2[r7];
        r2 = r2 << r8;
        r5 = r0.j;
        r5 = r5.a;
        r6 = 1;
        r5 = r5[r6];
        r5 = r5 & r14;
        r2 = r2 | r5;
        r5 = r0.E;
        r9 = (long) r2;
        r9 = r0.a(r9);
        r11 = r5 + r9;
        r0.J = r11;
        r2 = r0.j;
        r2 = r2.a;
        r5 = 2;
        r2 = r2[r5];
        r2 = r2 & r8;
        if (r2 != r8) goto L_0x024a;
    L_0x0248:
        r2 = 1;
        goto L_0x024b;
    L_0x024a:
        r2 = r7;
    L_0x024b:
        r6 = r4.c;
        if (r6 == r5) goto L_0x0261;
    L_0x024f:
        r6 = 163; // 0xa3 float:2.28E-43 double:8.05E-322;
        if (r1 != r6) goto L_0x025f;
    L_0x0253:
        r6 = r0.j;
        r6 = r6.a;
        r6 = r6[r5];
        r5 = 128; // 0x80 float:1.794E-43 double:6.32E-322;
        r6 = r6 & r5;
        if (r6 != r5) goto L_0x025f;
    L_0x025e:
        goto L_0x0261;
    L_0x025f:
        r5 = r7;
        goto L_0x0262;
    L_0x0261:
        r5 = 1;
    L_0x0262:
        if (r2 == 0) goto L_0x0267;
    L_0x0264:
        r2 = 134217728; // 0x8000000 float:3.85186E-34 double:6.63123685E-316;
        goto L_0x0268;
    L_0x0267:
        r2 = r7;
    L_0x0268:
        r2 = r2 | r5;
        r0.Q = r2;
        r2 = 2;
        r0.I = r2;
        r0.L = r7;
        r2 = 163; // 0xa3 float:2.28E-43 double:8.05E-322;
        goto L_0x028d;
    L_0x0273:
        r1 = new com.google.ads.interactivemedia.v3.internal.bl;
        r2 = 36;
        r3 = new java.lang.StringBuilder;
        r3.<init>(r2);
        r2 = "Unexpected lacing value: ";
        r3.append(r2);
        r3.append(r11);
        r2 = r3.toString();
        r1.<init>(r2);
        throw r1;
    L_0x028c:
        r2 = r5;
    L_0x028d:
        if (r1 != r2) goto L_0x02b7;
    L_0x028f:
        r1 = r0.L;
        r2 = r0.M;
        if (r1 >= r2) goto L_0x02b4;
    L_0x0295:
        r1 = r0.N;
        r2 = r0.L;
        r1 = r1[r2];
        r0.a(r3, r4, r1);
        r1 = r0.J;
        r5 = r0.L;
        r6 = r4.d;
        r5 = r5 * r6;
        r5 = r5 / 1000;
        r5 = (long) r5;
        r8 = r1 + r5;
        r0.a(r4, r8);
        r1 = r0.L;
        r2 = 1;
        r1 = r1 + r2;
        r0.L = r1;
        goto L_0x028f;
    L_0x02b4:
        r0.I = r7;
        goto L_0x02be;
    L_0x02b7:
        r1 = r0.N;
        r1 = r1[r7];
        r0.a(r3, r4, r1);
    L_0x02be:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.ads.interactivemedia.v3.internal.en.a(int, int, com.google.ads.interactivemedia.v3.internal.cd):void");
    }

    private void a(b bVar, long j) {
        if ("S_TEXT/UTF8".equals(bVar.a)) {
            a(bVar);
        }
        bVar.K.a(j, this.Q, this.Z, 0, bVar.g);
        this.aa = true;
        d();
    }

    private void d() {
        this.R = 0;
        this.Z = 0;
        this.Y = 0;
        this.S = false;
        this.T = false;
        this.V = false;
        this.X = 0;
        this.W = (byte) 0;
        this.U = false;
        this.m.a();
    }

    private void a(cd cdVar, int i) throws IOException, InterruptedException {
        if (this.j.c() < i) {
            if (this.j.e() < i) {
                this.j.a(Arrays.copyOf(this.j.a, Math.max(this.j.a.length * 2, i)), this.j.c());
            }
            cdVar.b(this.j.a, this.j.c(), i - this.j.c());
            this.j.b(i);
        }
    }

    private void a(cd cdVar, b bVar, int i) throws IOException, InterruptedException {
        if ("S_TEXT/UTF8".equals(bVar.a)) {
            int length = a.length + i;
            if (this.n.e() < length) {
                this.n.a = Arrays.copyOf(a, length + i);
            }
            cdVar.b(this.n.a, a.length, i);
            this.n.c(0);
            this.n.b(length);
            return;
        }
        ck ckVar = bVar.K;
        if (!this.S) {
            if (bVar.e) {
                this.Q &= -3;
                int i2 = 128;
                if (!this.T) {
                    cdVar.b(this.j.a, 0, 1);
                    this.R++;
                    if ((this.j.a[0] & 128) == 128) {
                        throw new bl("Extension bit is set in signal byte");
                    }
                    this.W = this.j.a[0];
                    this.T = true;
                }
                if (((this.W & 1) == 1 ? 1 : 0) != 0) {
                    int i3 = (this.W & 2) == 2 ? 1 : 0;
                    this.Q |= 2;
                    if (!this.U) {
                        cdVar.b(this.o.a, 0, 8);
                        this.R += 8;
                        this.U = true;
                        byte[] bArr = this.j.a;
                        if (i3 == 0) {
                            i2 = 0;
                        }
                        bArr[0] = (byte) (i2 | 8);
                        this.j.c(0);
                        ckVar.a(this.j, 1);
                        this.Z++;
                        this.o.c(0);
                        ckVar.a(this.o, 8);
                        this.Z += 8;
                    }
                    if (i3 != 0) {
                        if (!this.V) {
                            cdVar.b(this.j.a, 0, 1);
                            this.R++;
                            this.j.c(0);
                            this.X = this.j.f();
                            this.V = true;
                        }
                        i3 = this.X * 4;
                        if (this.j.c() < i3) {
                            this.j.a(new byte[i3], i3);
                        }
                        cdVar.b(this.j.a, 0, i3);
                        this.R += i3;
                        this.j.c(0);
                        this.j.b(i3);
                        short s = (short) ((this.X / 2) + 1);
                        i2 = (6 * s) + 2;
                        if (this.q == null || this.q.capacity() < i2) {
                            this.q = ByteBuffer.allocate(i2);
                        }
                        this.q.position(0);
                        this.q.putShort(s);
                        i3 = 0;
                        int i4 = i3;
                        while (i3 < this.X) {
                            int s2 = this.j.s();
                            if (i3 % 2 == 0) {
                                this.q.putShort((short) (s2 - i4));
                            } else {
                                this.q.putInt(s2 - i4);
                            }
                            i3++;
                            i4 = s2;
                        }
                        i3 = (i - this.R) - i4;
                        if (this.X % 2 == 1) {
                            this.q.putInt(i3);
                        } else {
                            this.q.putShort((short) i3);
                            this.q.putInt(0);
                        }
                        this.p.a(this.q.array(), i2);
                        ckVar.a(this.p, i2);
                        this.Z += i2;
                    }
                }
            } else if (bVar.f != null) {
                this.m.a(bVar.f, bVar.f.length);
            }
            this.S = true;
        }
        i += this.m.c();
        if ("V_MPEG4/ISO/AVC".equals(bVar.a) || "V_MPEGH/ISO/HEVC".equals(bVar.a)) {
            byte[] bArr2 = this.i.a;
            bArr2[0] = (byte) 0;
            bArr2[1] = (byte) 0;
            bArr2[2] = (byte) 0;
            int i5 = bVar.L;
            int i6 = 4 - bVar.L;
            while (this.R < i) {
                if (this.Y == 0) {
                    a(cdVar, bArr2, i6, i5);
                    this.i.c(0);
                    this.Y = this.i.s();
                    this.h.c(0);
                    ckVar.a(this.h, 4);
                    this.Z += 4;
                } else {
                    this.Y -= a(cdVar, ckVar, this.Y);
                }
            }
        } else {
            while (this.R < i) {
                a(cdVar, ckVar, i - this.R);
            }
        }
        if ("A_VORBIS".equals(bVar.a)) {
            this.k.c(0);
            ckVar.a(this.k, 4);
            this.Z += 4;
        }
    }

    private void a(b bVar) {
        a(this.n.a, this.K);
        bVar.K.a(this.n, this.n.c());
        this.Z += this.n.c();
    }

    private static void a(byte[] bArr, long j) {
        Object obj;
        if (j == -1) {
            obj = b;
        } else {
            long j2 = j - (((long) ((int) (j / 3600000000L))) * 3600000000L);
            long j3 = j2 - ((long) (60000000 * ((int) (j2 / 60000000))));
            int i = (int) ((j3 - ((long) (1000000 * ((int) (j3 / 1000000))))) / 1000);
            obj = String.format(Locale.US, "%02d:%02d:%02d,%03d", new Object[]{Integer.valueOf((int) (j / 3600000000L)), Integer.valueOf((int) (j2 / 60000000)), Integer.valueOf((int) (j3 / 1000000)), Integer.valueOf(i)}).getBytes();
        }
        System.arraycopy(obj, 0, bArr, 19, 12);
    }

    private void a(cd cdVar, byte[] bArr, int i, int i2) throws IOException, InterruptedException {
        int min = Math.min(i2, this.m.b());
        cdVar.b(bArr, i + min, i2 - min);
        if (min > 0) {
            this.m.a(bArr, i, min);
        }
        this.R += i2;
    }

    private int a(cd cdVar, ck ckVar, int i) throws IOException, InterruptedException {
        int min;
        int b = this.m.b();
        if (b > 0) {
            min = Math.min(i, b);
            ckVar.a(this.m, min);
        } else {
            min = ckVar.a(cdVar, i, false);
        }
        this.R += min;
        this.Z += min;
        return min;
    }

    private cj e() {
        if (this.r == -1 || this.v == -1 || this.F == null || this.F.a() == 0 || this.G == null || this.G.a() != this.F.a()) {
            this.F = null;
            this.G = null;
            return cj.f;
        }
        int i;
        int a = this.F.a();
        int[] iArr = new int[a];
        long[] jArr = new long[a];
        long[] jArr2 = new long[a];
        long[] jArr3 = new long[a];
        int i2 = 0;
        for (i = 0; i < a; i++) {
            jArr3[i] = this.F.a(i);
            jArr[i] = this.r + this.G.a(i);
        }
        while (true) {
            i = a - 1;
            if (i2 < i) {
                i = i2 + 1;
                iArr[i2] = (int) (jArr[i] - jArr[i2]);
                jArr2[i2] = jArr3[i] - jArr3[i2];
                i2 = i;
            } else {
                iArr[i] = (int) ((this.r + this.s) - jArr[i]);
                jArr2[i] = this.v - jArr3[i];
                this.F = null;
                this.G = null;
                return new by(iArr, jArr, jArr2, jArr3);
            }
        }
    }

    private boolean a(ch chVar, long j) {
        if (this.B) {
            this.D = j;
            chVar.a = this.C;
            this.B = false;
            return true;
        } else if (!this.y || this.D == -1) {
            return false;
        } else {
            chVar.a = this.D;
            this.D = -1;
            return true;
        }
    }

    private long a(long j) throws bl {
        if (this.t == -1) {
            throw new bl("Can't scale timecode prior to timecodeScale being set.");
        }
        return ft.a(j, this.t, 1000);
    }

    private static boolean a(String str) {
        return "V_VP8".equals(str) || "V_VP9".equals(str) || "V_MPEG2".equals(str) || "V_MPEG4/ISO/SP".equals(str) || "V_MPEG4/ISO/ASP".equals(str) || "V_MPEG4/ISO/AP".equals(str) || "V_MPEG4/ISO/AVC".equals(str) || "V_MPEGH/ISO/HEVC".equals(str) || "V_MS/VFW/FOURCC".equals(str) || "A_OPUS".equals(str) || "A_VORBIS".equals(str) || "A_AAC".equals(str) || "A_MPEG/L3".equals(str) || "A_AC3".equals(str) || "A_EAC3".equals(str) || "A_TRUEHD".equals(str) || "A_DTS".equals(str) || "A_DTS/EXPRESS".equals(str) || "A_DTS/LOSSLESS".equals(str) || "A_FLAC".equals(str) || "A_MS/ACM".equals(str) || "A_PCM/INT/LIT".equals(str) || "S_TEXT/UTF8".equals(str) || "S_VOBSUB".equals(str) || "S_HDMV/PGS".equals(str);
    }

    private static int[] a(int[] iArr, int i) {
        if (iArr == null) {
            return new int[i];
        }
        if (iArr.length >= i) {
            return iArr;
        }
        return new int[Math.max(iArr.length * 2, i)];
    }
}
