package net.jpountz.lz4;

final class LZ4JavaSafeCompressor extends LZ4Compressor {
    public static final LZ4Compressor INSTANCE = new LZ4JavaSafeCompressor();

    LZ4JavaSafeCompressor() {
    }

    /* JADX WARNING: Missing block: B:9:0x0047, code skipped:
            r11 = net.jpountz.lz4.LZ4SafeUtils.commonBytesBackward(r0, r15, r8, r1, r9);
            r8 = r8 - r11;
            r15 = r15 - r11;
            r11 = r8 - r9;
            r12 = r10 + 1;
     */
    /* JADX WARNING: Missing block: B:10:0x0058, code skipped:
            if ((((r12 + r11) + 8) + (r11 >>> 8)) <= r5) goto L_0x0062;
     */
    /* JADX WARNING: Missing block: B:12:0x0061, code skipped:
            throw new net.jpountz.lz4.LZ4Exception("maxDestLen is too small");
     */
    /* JADX WARNING: Missing block: B:14:0x0064, code skipped:
            if (r11 < 15) goto L_0x0072;
     */
    /* JADX WARNING: Missing block: B:15:0x0066, code skipped:
            net.jpountz.util.SafeUtils.writeByte(r3, r10, com.google.android.exoplayer2.extractor.ts.PsExtractor.VIDEO_STREAM_MASK);
            r12 = net.jpountz.lz4.LZ4SafeUtils.writeLen(r11 - 15, r3, r12);
     */
    /* JADX WARNING: Missing block: B:16:0x0072, code skipped:
            net.jpountz.util.SafeUtils.writeByte(r3, r10, r11 << 4);
     */
    /* JADX WARNING: Missing block: B:17:0x0077, code skipped:
            net.jpountz.lz4.LZ4SafeUtils.wildArraycopy(r0, r9, r3, r12, r11);
            r12 = r12 + r11;
     */
    /* JADX WARNING: Missing block: B:18:0x007b, code skipped:
            net.jpountz.util.SafeUtils.writeShortLE(r3, r12, (short) (r8 - r15));
            r12 = r12 + 2;
            r8 = r8 + 4;
            r9 = net.jpountz.lz4.LZ4SafeUtils.commonBytes(r0, r15 + 4, r8, r6);
     */
    /* JADX WARNING: Missing block: B:19:0x0090, code skipped:
            if (((r12 + 6) + (r9 >>> 8)) <= r5) goto L_0x009a;
     */
    /* JADX WARNING: Missing block: B:21:0x0099, code skipped:
            throw new net.jpountz.lz4.LZ4Exception("maxDestLen is too small");
     */
    /* JADX WARNING: Missing block: B:22:0x009a, code skipped:
            r8 = r8 + r9;
     */
    /* JADX WARNING: Missing block: B:23:0x009b, code skipped:
            if (r9 < 15) goto L_0x00ad;
     */
    /* JADX WARNING: Missing block: B:24:0x009d, code skipped:
            net.jpountz.util.SafeUtils.writeByte(r3, r10, net.jpountz.util.SafeUtils.readByte(r3, r10) | 15);
            r10 = net.jpountz.lz4.LZ4SafeUtils.writeLen(r9 - 15, r3, r12);
     */
    /* JADX WARNING: Missing block: B:25:0x00ad, code skipped:
            net.jpountz.util.SafeUtils.writeByte(r3, r10, r9 | net.jpountz.util.SafeUtils.readByte(r3, r10));
            r10 = r12;
     */
    /* JADX WARNING: Missing block: B:26:0x00b6, code skipped:
            if (r8 <= r7) goto L_0x00ba;
     */
    /* JADX WARNING: Missing block: B:27:0x00b8, code skipped:
            r1 = r8;
     */
    /* JADX WARNING: Missing block: B:28:0x00ba, code skipped:
            r9 = r8 - 2;
            net.jpountz.util.SafeUtils.writeShort(r2, net.jpountz.lz4.LZ4Utils.hash64k(net.jpountz.util.SafeUtils.readInt(r0, r9)), r9 - r1);
            r9 = net.jpountz.lz4.LZ4Utils.hash64k(net.jpountz.util.SafeUtils.readInt(r0, r8));
            r15 = r1 + net.jpountz.util.SafeUtils.readShort(r2, r9);
            net.jpountz.util.SafeUtils.writeShort(r2, r9, r8 - r1);
     */
    /* JADX WARNING: Missing block: B:29:0x00df, code skipped:
            if (net.jpountz.lz4.LZ4SafeUtils.readIntEquals(r0, r8, r15) != false) goto L_0x00ea;
     */
    /* JADX WARNING: Missing block: B:31:0x00ea, code skipped:
            r12 = r10 + 1;
            net.jpountz.util.SafeUtils.writeByte(r3, r10, 0);
     */
    static int compress64k(byte[] r18, int r19, int r20, byte[] r21, int r22, int r23) {
        /*
        r0 = r18;
        r1 = r19;
        r2 = r20;
        r3 = r21;
        r5 = r23;
        r4 = r1 + r2;
        r6 = r4 + -5;
        r7 = r4 + -12;
        r8 = 13;
        if (r2 < r8) goto L_0x00f7;
    L_0x0014:
        r2 = 8192; // 0x2000 float:1.14794E-41 double:4.0474E-320;
        r2 = new short[r2];
        r8 = r1 + 1;
        r10 = r22;
        r9 = r1;
    L_0x001d:
        r11 = net.jpountz.lz4.LZ4Constants.SKIP_STRENGTH;
        r12 = 1;
        r11 = r12 << r11;
    L_0x0022:
        r12 = r12 + r8;
        r13 = r11 + 1;
        r14 = net.jpountz.lz4.LZ4Constants.SKIP_STRENGTH;
        r11 = r11 >>> r14;
        if (r12 <= r7) goto L_0x002d;
    L_0x002a:
        r1 = r9;
        goto L_0x00f9;
    L_0x002d:
        r14 = net.jpountz.util.SafeUtils.readInt(r0, r8);
        r14 = net.jpountz.lz4.LZ4Utils.hash64k(r14);
        r15 = net.jpountz.util.SafeUtils.readShort(r2, r14);
        r15 = r15 + r1;
        r16 = r11;
        r11 = r8 - r1;
        net.jpountz.util.SafeUtils.writeShort(r2, r14, r11);
        r11 = net.jpountz.lz4.LZ4SafeUtils.readIntEquals(r0, r15, r8);
        if (r11 == 0) goto L_0x00f1;
    L_0x0047:
        r11 = net.jpountz.lz4.LZ4SafeUtils.commonBytesBackward(r0, r15, r8, r1, r9);
        r8 = r8 - r11;
        r15 = r15 - r11;
        r11 = r8 - r9;
        r12 = r10 + 1;
        r13 = r12 + r11;
        r13 = r13 + 8;
        r14 = r11 >>> 8;
        r13 = r13 + r14;
        if (r13 <= r5) goto L_0x0062;
    L_0x005a:
        r0 = new net.jpountz.lz4.LZ4Exception;
        r1 = "maxDestLen is too small";
        r0.<init>(r1);
        throw r0;
    L_0x0062:
        r14 = 15;
        if (r11 < r14) goto L_0x0072;
    L_0x0066:
        r13 = 240; // 0xf0 float:3.36E-43 double:1.186E-321;
        net.jpountz.util.SafeUtils.writeByte(r3, r10, r13);
        r13 = r11 + -15;
        r12 = net.jpountz.lz4.LZ4SafeUtils.writeLen(r13, r3, r12);
        goto L_0x0077;
    L_0x0072:
        r13 = r11 << 4;
        net.jpountz.util.SafeUtils.writeByte(r3, r10, r13);
    L_0x0077:
        net.jpountz.lz4.LZ4SafeUtils.wildArraycopy(r0, r9, r3, r12, r11);
        r12 = r12 + r11;
    L_0x007b:
        r9 = r8 - r15;
        r9 = (short) r9;
        net.jpountz.util.SafeUtils.writeShortLE(r3, r12, r9);
        r12 = r12 + 2;
        r8 = r8 + 4;
        r15 = r15 + 4;
        r9 = net.jpountz.lz4.LZ4SafeUtils.commonBytes(r0, r15, r8, r6);
        r11 = r12 + 6;
        r13 = r9 >>> 8;
        r11 = r11 + r13;
        if (r11 <= r5) goto L_0x009a;
    L_0x0092:
        r0 = new net.jpountz.lz4.LZ4Exception;
        r1 = "maxDestLen is too small";
        r0.<init>(r1);
        throw r0;
    L_0x009a:
        r8 = r8 + r9;
        if (r9 < r14) goto L_0x00ad;
    L_0x009d:
        r11 = net.jpountz.util.SafeUtils.readByte(r3, r10);
        r11 = r11 | r14;
        net.jpountz.util.SafeUtils.writeByte(r3, r10, r11);
        r9 = r9 + -15;
        r9 = net.jpountz.lz4.LZ4SafeUtils.writeLen(r9, r3, r12);
        r10 = r9;
        goto L_0x00b6;
    L_0x00ad:
        r11 = net.jpountz.util.SafeUtils.readByte(r3, r10);
        r9 = r9 | r11;
        net.jpountz.util.SafeUtils.writeByte(r3, r10, r9);
        r10 = r12;
    L_0x00b6:
        if (r8 <= r7) goto L_0x00ba;
    L_0x00b8:
        r1 = r8;
        goto L_0x00f9;
    L_0x00ba:
        r9 = r8 + -2;
        r11 = net.jpountz.util.SafeUtils.readInt(r0, r9);
        r11 = net.jpountz.lz4.LZ4Utils.hash64k(r11);
        r9 = r9 - r1;
        net.jpountz.util.SafeUtils.writeShort(r2, r11, r9);
        r9 = net.jpountz.util.SafeUtils.readInt(r0, r8);
        r9 = net.jpountz.lz4.LZ4Utils.hash64k(r9);
        r11 = net.jpountz.util.SafeUtils.readShort(r2, r9);
        r15 = r1 + r11;
        r11 = r8 - r1;
        net.jpountz.util.SafeUtils.writeShort(r2, r9, r11);
        r9 = net.jpountz.lz4.LZ4SafeUtils.readIntEquals(r0, r8, r15);
        if (r9 != 0) goto L_0x00ea;
    L_0x00e1:
        r9 = r8 + 1;
        r17 = r9;
        r9 = r8;
        r8 = r17;
        goto L_0x001d;
    L_0x00ea:
        r12 = r10 + 1;
        r9 = 0;
        net.jpountz.util.SafeUtils.writeByte(r3, r10, r9);
        goto L_0x007b;
    L_0x00f1:
        r8 = r12;
        r11 = r13;
        r12 = r16;
        goto L_0x0022;
    L_0x00f7:
        r10 = r22;
    L_0x00f9:
        r2 = r4 - r1;
        r4 = r10;
        r0 = net.jpountz.lz4.LZ4SafeUtils.lastLiterals(r0, r1, r2, r3, r4, r5);
        r0 = r0 - r22;
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: net.jpountz.lz4.LZ4JavaSafeCompressor.compress64k(byte[], int, int, byte[], int, int):int");
    }

    /* JADX WARNING: Missing block: B:14:0x005c, code skipped:
            r13 = net.jpountz.lz4.LZ4SafeUtils.commonBytesBackward(r0, r11, r7, r1, r9);
            r7 = r7 - r13;
            r11 = r11 - r13;
            r13 = r7 - r9;
            r14 = r10 + 1;
     */
    /* JADX WARNING: Missing block: B:15:0x006e, code skipped:
            if ((((r14 + r13) + 8) + (r13 >>> 8)) <= r5) goto L_0x0078;
     */
    /* JADX WARNING: Missing block: B:17:0x0077, code skipped:
            throw new net.jpountz.lz4.LZ4Exception("maxDestLen is too small");
     */
    /* JADX WARNING: Missing block: B:19:0x007a, code skipped:
            if (r13 < 15) goto L_0x0088;
     */
    /* JADX WARNING: Missing block: B:20:0x007c, code skipped:
            net.jpountz.util.SafeUtils.writeByte(r3, r10, com.google.android.exoplayer2.extractor.ts.PsExtractor.VIDEO_STREAM_MASK);
            r14 = net.jpountz.lz4.LZ4SafeUtils.writeLen(r13 - 15, r3, r14);
     */
    /* JADX WARNING: Missing block: B:21:0x0088, code skipped:
            net.jpountz.util.SafeUtils.writeByte(r3, r10, r13 << 4);
     */
    /* JADX WARNING: Missing block: B:22:0x008d, code skipped:
            net.jpountz.lz4.LZ4SafeUtils.wildArraycopy(r0, r9, r3, r14, r13);
            r14 = r14 + r13;
     */
    /* JADX WARNING: Missing block: B:23:0x0091, code skipped:
            net.jpountz.util.SafeUtils.writeShortLE(r3, r14, r12);
            r14 = r14 + 2;
            r7 = r7 + 4;
            r1 = net.jpountz.lz4.LZ4SafeUtils.commonBytes(r0, r11 + 4, r7, r4);
     */
    /* JADX WARNING: Missing block: B:24:0x00a3, code skipped:
            if (((r14 + 6) + (r1 >>> 8)) <= r5) goto L_0x00ad;
     */
    /* JADX WARNING: Missing block: B:26:0x00ac, code skipped:
            throw new net.jpountz.lz4.LZ4Exception("maxDestLen is too small");
     */
    /* JADX WARNING: Missing block: B:27:0x00ad, code skipped:
            r7 = r7 + r1;
     */
    /* JADX WARNING: Missing block: B:28:0x00b0, code skipped:
            if (r1 < 15) goto L_0x00c2;
     */
    /* JADX WARNING: Missing block: B:29:0x00b2, code skipped:
            net.jpountz.util.SafeUtils.writeByte(r3, r10, net.jpountz.util.SafeUtils.readByte(r3, r10) | 15);
            r10 = net.jpountz.lz4.LZ4SafeUtils.writeLen(r1 - 15, r3, r14);
     */
    /* JADX WARNING: Missing block: B:30:0x00c2, code skipped:
            net.jpountz.util.SafeUtils.writeByte(r3, r10, r1 | net.jpountz.util.SafeUtils.readByte(r3, r10));
            r10 = r14;
     */
    /* JADX WARNING: Missing block: B:31:0x00cb, code skipped:
            if (r7 <= r6) goto L_0x00d8;
     */
    /* JADX WARNING: Missing block: B:32:0x00cd, code skipped:
            r1 = r7;
     */
    /* JADX WARNING: Missing block: B:35:0x00d8, code skipped:
            r1 = r7 - 2;
            net.jpountz.util.SafeUtils.writeInt(r8, net.jpountz.lz4.LZ4Utils.hash(net.jpountz.util.SafeUtils.readInt(r0, r1)), r1);
            r1 = net.jpountz.lz4.LZ4Utils.hash(net.jpountz.util.SafeUtils.readInt(r0, r7));
            r11 = net.jpountz.util.SafeUtils.readInt(r8, r1);
            net.jpountz.util.SafeUtils.writeInt(r8, r1, r7);
            r12 = r7 - r11;
     */
    /* JADX WARNING: Missing block: B:36:0x00f8, code skipped:
            if (r12 >= 65536) goto L_0x0108;
     */
    /* JADX WARNING: Missing block: B:38:0x00fe, code skipped:
            if (net.jpountz.lz4.LZ4SafeUtils.readIntEquals(r0, r11, r7) != false) goto L_0x0101;
     */
    /* JADX WARNING: Missing block: B:39:0x0101, code skipped:
            r14 = r10 + 1;
            net.jpountz.util.SafeUtils.writeByte(r3, r10, 0);
     */
    public int compress(byte[] r20, int r21, int r22, byte[] r23, int r24, int r25) {
        /*
        r19 = this;
        r0 = r20;
        r1 = r21;
        r2 = r22;
        r3 = r23;
        net.jpountz.util.SafeUtils.checkRange(r20, r21, r22);
        net.jpountz.util.SafeUtils.checkRange(r23, r24, r25);
        r5 = r24 + r25;
        r4 = 65547; // 0x1000b float:9.1851E-41 double:3.23845E-319;
        if (r2 >= r4) goto L_0x001c;
    L_0x0015:
        r4 = r24;
        r0 = compress64k(r0, r1, r2, r3, r4, r5);
        return r0;
    L_0x001c:
        r2 = r2 + r1;
        r4 = r2 + -5;
        r6 = r2 + -12;
        r7 = r1 + 1;
        r8 = 4096; // 0x1000 float:5.74E-42 double:2.0237E-320;
        r8 = new int[r8];
        java.util.Arrays.fill(r8, r1);
        r10 = r24;
        r9 = r1;
    L_0x002d:
        r11 = net.jpountz.lz4.LZ4Constants.SKIP_STRENGTH;
        r12 = 1;
        r11 = r12 << r11;
    L_0x0032:
        r12 = r12 + r7;
        r13 = r11 + 1;
        r14 = net.jpountz.lz4.LZ4Constants.SKIP_STRENGTH;
        r11 = r11 >>> r14;
        if (r12 <= r6) goto L_0x003e;
    L_0x003a:
        r1 = r9;
    L_0x003b:
        r4 = r10;
        goto L_0x00d0;
    L_0x003e:
        r14 = net.jpountz.util.SafeUtils.readInt(r0, r7);
        r14 = net.jpountz.lz4.LZ4Utils.hash(r14);
        r15 = r11;
        r11 = net.jpountz.util.SafeUtils.readInt(r8, r14);
        r16 = r12;
        r12 = r7 - r11;
        net.jpountz.util.SafeUtils.writeInt(r8, r14, r7);
        r14 = 65536; // 0x10000 float:9.18355E-41 double:3.2379E-319;
        if (r12 >= r14) goto L_0x0110;
    L_0x0056:
        r17 = net.jpountz.lz4.LZ4SafeUtils.readIntEquals(r0, r11, r7);
        if (r17 == 0) goto L_0x0110;
    L_0x005c:
        r13 = net.jpountz.lz4.LZ4SafeUtils.commonBytesBackward(r0, r11, r7, r1, r9);
        r7 = r7 - r13;
        r11 = r11 - r13;
        r13 = r7 - r9;
        r14 = r10 + 1;
        r15 = r14 + r13;
        r15 = r15 + 8;
        r16 = r13 >>> 8;
        r1 = r15 + r16;
        if (r1 <= r5) goto L_0x0078;
    L_0x0070:
        r0 = new net.jpountz.lz4.LZ4Exception;
        r1 = "maxDestLen is too small";
        r0.<init>(r1);
        throw r0;
    L_0x0078:
        r1 = 15;
        if (r13 < r1) goto L_0x0088;
    L_0x007c:
        r1 = 240; // 0xf0 float:3.36E-43 double:1.186E-321;
        net.jpountz.util.SafeUtils.writeByte(r3, r10, r1);
        r1 = r13 + -15;
        r14 = net.jpountz.lz4.LZ4SafeUtils.writeLen(r1, r3, r14);
        goto L_0x008d;
    L_0x0088:
        r1 = r13 << 4;
        net.jpountz.util.SafeUtils.writeByte(r3, r10, r1);
    L_0x008d:
        net.jpountz.lz4.LZ4SafeUtils.wildArraycopy(r0, r9, r3, r14, r13);
        r14 = r14 + r13;
    L_0x0091:
        net.jpountz.util.SafeUtils.writeShortLE(r3, r14, r12);
        r14 = r14 + 2;
        r7 = r7 + 4;
        r11 = r11 + 4;
        r1 = net.jpountz.lz4.LZ4SafeUtils.commonBytes(r0, r11, r7, r4);
        r9 = r14 + 6;
        r11 = r1 >>> 8;
        r9 = r9 + r11;
        if (r9 <= r5) goto L_0x00ad;
    L_0x00a5:
        r0 = new net.jpountz.lz4.LZ4Exception;
        r1 = "maxDestLen is too small";
        r0.<init>(r1);
        throw r0;
    L_0x00ad:
        r7 = r7 + r1;
        r9 = 15;
        if (r1 < r9) goto L_0x00c2;
    L_0x00b2:
        r11 = net.jpountz.util.SafeUtils.readByte(r3, r10);
        r11 = r11 | r9;
        net.jpountz.util.SafeUtils.writeByte(r3, r10, r11);
        r1 = r1 + -15;
        r1 = net.jpountz.lz4.LZ4SafeUtils.writeLen(r1, r3, r14);
        r10 = r1;
        goto L_0x00cb;
    L_0x00c2:
        r11 = net.jpountz.util.SafeUtils.readByte(r3, r10);
        r1 = r1 | r11;
        net.jpountz.util.SafeUtils.writeByte(r3, r10, r1);
        r10 = r14;
    L_0x00cb:
        if (r7 <= r6) goto L_0x00d8;
    L_0x00cd:
        r1 = r7;
        goto L_0x003b;
    L_0x00d0:
        r2 = r2 - r1;
        r0 = net.jpountz.lz4.LZ4SafeUtils.lastLiterals(r0, r1, r2, r3, r4, r5);
        r0 = r0 - r24;
        return r0;
    L_0x00d8:
        r1 = r7 + -2;
        r11 = net.jpountz.util.SafeUtils.readInt(r0, r1);
        r11 = net.jpountz.lz4.LZ4Utils.hash(r11);
        net.jpountz.util.SafeUtils.writeInt(r8, r11, r1);
        r1 = net.jpountz.util.SafeUtils.readInt(r0, r7);
        r1 = net.jpountz.lz4.LZ4Utils.hash(r1);
        r11 = net.jpountz.util.SafeUtils.readInt(r8, r1);
        net.jpountz.util.SafeUtils.writeInt(r8, r1, r7);
        r12 = r7 - r11;
        r1 = 65536; // 0x10000 float:9.18355E-41 double:3.2379E-319;
        if (r12 >= r1) goto L_0x0108;
    L_0x00fa:
        r13 = net.jpountz.lz4.LZ4SafeUtils.readIntEquals(r0, r11, r7);
        if (r13 != 0) goto L_0x0101;
    L_0x0100:
        goto L_0x0108;
    L_0x0101:
        r14 = r10 + 1;
        r13 = 0;
        net.jpountz.util.SafeUtils.writeByte(r3, r10, r13);
        goto L_0x0091;
    L_0x0108:
        r1 = r7 + 1;
        r9 = r7;
        r7 = r1;
        r1 = r21;
        goto L_0x002d;
    L_0x0110:
        r11 = r13;
        r12 = r15;
        r7 = r16;
        r1 = r21;
        goto L_0x0032;
        */
        throw new UnsupportedOperationException("Method not decompiled: net.jpountz.lz4.LZ4JavaSafeCompressor.compress(byte[], int, int, byte[], int, int):int");
    }

    /* JADX WARNING: Missing block: B:9:0x0047, code skipped:
            r11 = net.jpountz.lz4.LZ4ByteBufferUtils.commonBytesBackward(r0, r15, r8, r1, r9);
            r8 = r8 - r11;
            r15 = r15 - r11;
            r11 = r8 - r9;
            r12 = r10 + 1;
     */
    /* JADX WARNING: Missing block: B:10:0x0058, code skipped:
            if ((((r12 + r11) + 8) + (r11 >>> 8)) <= r5) goto L_0x0062;
     */
    /* JADX WARNING: Missing block: B:12:0x0061, code skipped:
            throw new net.jpountz.lz4.LZ4Exception("maxDestLen is too small");
     */
    /* JADX WARNING: Missing block: B:14:0x0064, code skipped:
            if (r11 < 15) goto L_0x0072;
     */
    /* JADX WARNING: Missing block: B:15:0x0066, code skipped:
            net.jpountz.util.ByteBufferUtils.writeByte(r3, r10, com.google.android.exoplayer2.extractor.ts.PsExtractor.VIDEO_STREAM_MASK);
            r12 = net.jpountz.lz4.LZ4ByteBufferUtils.writeLen(r11 - 15, r3, r12);
     */
    /* JADX WARNING: Missing block: B:16:0x0072, code skipped:
            net.jpountz.util.ByteBufferUtils.writeByte(r3, r10, r11 << 4);
     */
    /* JADX WARNING: Missing block: B:17:0x0077, code skipped:
            net.jpountz.lz4.LZ4ByteBufferUtils.wildArraycopy(r0, r9, r3, r12, r11);
            r12 = r12 + r11;
     */
    /* JADX WARNING: Missing block: B:18:0x007b, code skipped:
            net.jpountz.util.ByteBufferUtils.writeShortLE(r3, r12, (short) (r8 - r15));
            r12 = r12 + 2;
            r8 = r8 + 4;
            r9 = net.jpountz.lz4.LZ4ByteBufferUtils.commonBytes(r0, r15 + 4, r8, r6);
     */
    /* JADX WARNING: Missing block: B:19:0x0090, code skipped:
            if (((r12 + 6) + (r9 >>> 8)) <= r5) goto L_0x009a;
     */
    /* JADX WARNING: Missing block: B:21:0x0099, code skipped:
            throw new net.jpountz.lz4.LZ4Exception("maxDestLen is too small");
     */
    /* JADX WARNING: Missing block: B:22:0x009a, code skipped:
            r8 = r8 + r9;
     */
    /* JADX WARNING: Missing block: B:23:0x009b, code skipped:
            if (r9 < 15) goto L_0x00ad;
     */
    /* JADX WARNING: Missing block: B:24:0x009d, code skipped:
            net.jpountz.util.ByteBufferUtils.writeByte(r3, r10, net.jpountz.util.ByteBufferUtils.readByte(r3, r10) | 15);
            r10 = net.jpountz.lz4.LZ4ByteBufferUtils.writeLen(r9 - 15, r3, r12);
     */
    /* JADX WARNING: Missing block: B:25:0x00ad, code skipped:
            net.jpountz.util.ByteBufferUtils.writeByte(r3, r10, r9 | net.jpountz.util.ByteBufferUtils.readByte(r3, r10));
            r10 = r12;
     */
    /* JADX WARNING: Missing block: B:26:0x00b6, code skipped:
            if (r8 <= r7) goto L_0x00ba;
     */
    /* JADX WARNING: Missing block: B:27:0x00b8, code skipped:
            r1 = r8;
     */
    /* JADX WARNING: Missing block: B:28:0x00ba, code skipped:
            r9 = r8 - 2;
            net.jpountz.util.SafeUtils.writeShort(r2, net.jpountz.lz4.LZ4Utils.hash64k(net.jpountz.util.ByteBufferUtils.readInt(r0, r9)), r9 - r1);
            r9 = net.jpountz.lz4.LZ4Utils.hash64k(net.jpountz.util.ByteBufferUtils.readInt(r0, r8));
            r15 = r1 + net.jpountz.util.SafeUtils.readShort(r2, r9);
            net.jpountz.util.SafeUtils.writeShort(r2, r9, r8 - r1);
     */
    /* JADX WARNING: Missing block: B:29:0x00df, code skipped:
            if (net.jpountz.lz4.LZ4ByteBufferUtils.readIntEquals(r0, r8, r15) != false) goto L_0x00ea;
     */
    /* JADX WARNING: Missing block: B:31:0x00ea, code skipped:
            r12 = r10 + 1;
            net.jpountz.util.ByteBufferUtils.writeByte(r3, r10, 0);
     */
    static int compress64k(java.nio.ByteBuffer r18, int r19, int r20, java.nio.ByteBuffer r21, int r22, int r23) {
        /*
        r0 = r18;
        r1 = r19;
        r2 = r20;
        r3 = r21;
        r5 = r23;
        r4 = r1 + r2;
        r6 = r4 + -5;
        r7 = r4 + -12;
        r8 = 13;
        if (r2 < r8) goto L_0x00f7;
    L_0x0014:
        r2 = 8192; // 0x2000 float:1.14794E-41 double:4.0474E-320;
        r2 = new short[r2];
        r8 = r1 + 1;
        r10 = r22;
        r9 = r1;
    L_0x001d:
        r11 = net.jpountz.lz4.LZ4Constants.SKIP_STRENGTH;
        r12 = 1;
        r11 = r12 << r11;
    L_0x0022:
        r12 = r12 + r8;
        r13 = r11 + 1;
        r14 = net.jpountz.lz4.LZ4Constants.SKIP_STRENGTH;
        r11 = r11 >>> r14;
        if (r12 <= r7) goto L_0x002d;
    L_0x002a:
        r1 = r9;
        goto L_0x00f9;
    L_0x002d:
        r14 = net.jpountz.util.ByteBufferUtils.readInt(r0, r8);
        r14 = net.jpountz.lz4.LZ4Utils.hash64k(r14);
        r15 = net.jpountz.util.SafeUtils.readShort(r2, r14);
        r15 = r15 + r1;
        r16 = r11;
        r11 = r8 - r1;
        net.jpountz.util.SafeUtils.writeShort(r2, r14, r11);
        r11 = net.jpountz.lz4.LZ4ByteBufferUtils.readIntEquals(r0, r15, r8);
        if (r11 == 0) goto L_0x00f1;
    L_0x0047:
        r11 = net.jpountz.lz4.LZ4ByteBufferUtils.commonBytesBackward(r0, r15, r8, r1, r9);
        r8 = r8 - r11;
        r15 = r15 - r11;
        r11 = r8 - r9;
        r12 = r10 + 1;
        r13 = r12 + r11;
        r13 = r13 + 8;
        r14 = r11 >>> 8;
        r13 = r13 + r14;
        if (r13 <= r5) goto L_0x0062;
    L_0x005a:
        r0 = new net.jpountz.lz4.LZ4Exception;
        r1 = "maxDestLen is too small";
        r0.<init>(r1);
        throw r0;
    L_0x0062:
        r14 = 15;
        if (r11 < r14) goto L_0x0072;
    L_0x0066:
        r13 = 240; // 0xf0 float:3.36E-43 double:1.186E-321;
        net.jpountz.util.ByteBufferUtils.writeByte(r3, r10, r13);
        r13 = r11 + -15;
        r12 = net.jpountz.lz4.LZ4ByteBufferUtils.writeLen(r13, r3, r12);
        goto L_0x0077;
    L_0x0072:
        r13 = r11 << 4;
        net.jpountz.util.ByteBufferUtils.writeByte(r3, r10, r13);
    L_0x0077:
        net.jpountz.lz4.LZ4ByteBufferUtils.wildArraycopy(r0, r9, r3, r12, r11);
        r12 = r12 + r11;
    L_0x007b:
        r9 = r8 - r15;
        r9 = (short) r9;
        net.jpountz.util.ByteBufferUtils.writeShortLE(r3, r12, r9);
        r12 = r12 + 2;
        r8 = r8 + 4;
        r15 = r15 + 4;
        r9 = net.jpountz.lz4.LZ4ByteBufferUtils.commonBytes(r0, r15, r8, r6);
        r11 = r12 + 6;
        r13 = r9 >>> 8;
        r11 = r11 + r13;
        if (r11 <= r5) goto L_0x009a;
    L_0x0092:
        r0 = new net.jpountz.lz4.LZ4Exception;
        r1 = "maxDestLen is too small";
        r0.<init>(r1);
        throw r0;
    L_0x009a:
        r8 = r8 + r9;
        if (r9 < r14) goto L_0x00ad;
    L_0x009d:
        r11 = net.jpountz.util.ByteBufferUtils.readByte(r3, r10);
        r11 = r11 | r14;
        net.jpountz.util.ByteBufferUtils.writeByte(r3, r10, r11);
        r9 = r9 + -15;
        r9 = net.jpountz.lz4.LZ4ByteBufferUtils.writeLen(r9, r3, r12);
        r10 = r9;
        goto L_0x00b6;
    L_0x00ad:
        r11 = net.jpountz.util.ByteBufferUtils.readByte(r3, r10);
        r9 = r9 | r11;
        net.jpountz.util.ByteBufferUtils.writeByte(r3, r10, r9);
        r10 = r12;
    L_0x00b6:
        if (r8 <= r7) goto L_0x00ba;
    L_0x00b8:
        r1 = r8;
        goto L_0x00f9;
    L_0x00ba:
        r9 = r8 + -2;
        r11 = net.jpountz.util.ByteBufferUtils.readInt(r0, r9);
        r11 = net.jpountz.lz4.LZ4Utils.hash64k(r11);
        r9 = r9 - r1;
        net.jpountz.util.SafeUtils.writeShort(r2, r11, r9);
        r9 = net.jpountz.util.ByteBufferUtils.readInt(r0, r8);
        r9 = net.jpountz.lz4.LZ4Utils.hash64k(r9);
        r11 = net.jpountz.util.SafeUtils.readShort(r2, r9);
        r15 = r1 + r11;
        r11 = r8 - r1;
        net.jpountz.util.SafeUtils.writeShort(r2, r9, r11);
        r9 = net.jpountz.lz4.LZ4ByteBufferUtils.readIntEquals(r0, r8, r15);
        if (r9 != 0) goto L_0x00ea;
    L_0x00e1:
        r9 = r8 + 1;
        r17 = r9;
        r9 = r8;
        r8 = r17;
        goto L_0x001d;
    L_0x00ea:
        r12 = r10 + 1;
        r9 = 0;
        net.jpountz.util.ByteBufferUtils.writeByte(r3, r10, r9);
        goto L_0x007b;
    L_0x00f1:
        r8 = r12;
        r11 = r13;
        r12 = r16;
        goto L_0x0022;
    L_0x00f7:
        r10 = r22;
    L_0x00f9:
        r2 = r4 - r1;
        r4 = r10;
        r0 = net.jpountz.lz4.LZ4ByteBufferUtils.lastLiterals(r0, r1, r2, r3, r4, r5);
        r0 = r0 - r22;
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: net.jpountz.lz4.LZ4JavaSafeCompressor.compress64k(java.nio.ByteBuffer, int, int, java.nio.ByteBuffer, int, int):int");
    }

    /* JADX WARNING: Missing block: B:20:0x0092, code skipped:
            r14 = net.jpountz.lz4.LZ4ByteBufferUtils.commonBytesBackward(r5, r12, r6, r1, r9);
            r6 = r6 - r14;
            r12 = r12 - r14;
            r14 = r6 - r9;
            r15 = r11 + 1;
     */
    /* JADX WARNING: Missing block: B:21:0x00a4, code skipped:
            if ((((r15 + r14) + 8) + (r14 >>> 8)) <= r10) goto L_0x00ae;
     */
    /* JADX WARNING: Missing block: B:23:0x00ad, code skipped:
            throw new net.jpountz.lz4.LZ4Exception("maxDestLen is too small");
     */
    /* JADX WARNING: Missing block: B:25:0x00b0, code skipped:
            if (r14 < 15) goto L_0x00be;
     */
    /* JADX WARNING: Missing block: B:26:0x00b2, code skipped:
            net.jpountz.util.ByteBufferUtils.writeByte(r8, r11, com.google.android.exoplayer2.extractor.ts.PsExtractor.VIDEO_STREAM_MASK);
            r15 = net.jpountz.lz4.LZ4ByteBufferUtils.writeLen(r14 - 15, r8, r15);
     */
    /* JADX WARNING: Missing block: B:27:0x00be, code skipped:
            net.jpountz.util.ByteBufferUtils.writeByte(r8, r11, r14 << 4);
     */
    /* JADX WARNING: Missing block: B:28:0x00c3, code skipped:
            net.jpountz.lz4.LZ4ByteBufferUtils.wildArraycopy(r5, r9, r8, r15, r14);
            r15 = r15 + r14;
     */
    /* JADX WARNING: Missing block: B:29:0x00c7, code skipped:
            net.jpountz.util.ByteBufferUtils.writeShortLE(r8, r15, r13);
            r15 = r15 + 2;
            r6 = r6 + 4;
            r1 = net.jpountz.lz4.LZ4ByteBufferUtils.commonBytes(r5, r12 + 4, r6, r2);
     */
    /* JADX WARNING: Missing block: B:30:0x00d9, code skipped:
            if (((r15 + 6) + (r1 >>> 8)) <= r10) goto L_0x00e3;
     */
    /* JADX WARNING: Missing block: B:32:0x00e2, code skipped:
            throw new net.jpountz.lz4.LZ4Exception("maxDestLen is too small");
     */
    /* JADX WARNING: Missing block: B:33:0x00e3, code skipped:
            r6 = r6 + r1;
     */
    /* JADX WARNING: Missing block: B:34:0x00e6, code skipped:
            if (r1 < 15) goto L_0x00f8;
     */
    /* JADX WARNING: Missing block: B:35:0x00e8, code skipped:
            net.jpountz.util.ByteBufferUtils.writeByte(r8, r11, net.jpountz.util.ByteBufferUtils.readByte(r8, r11) | 15);
            r11 = net.jpountz.lz4.LZ4ByteBufferUtils.writeLen(r1 - 15, r8, r15);
     */
    /* JADX WARNING: Missing block: B:36:0x00f8, code skipped:
            net.jpountz.util.ByteBufferUtils.writeByte(r8, r11, r1 | net.jpountz.util.ByteBufferUtils.readByte(r8, r11));
            r11 = r15;
     */
    /* JADX WARNING: Missing block: B:37:0x0101, code skipped:
            if (r6 <= r3) goto L_0x010d;
     */
    /* JADX WARNING: Missing block: B:40:0x010d, code skipped:
            r1 = r6 - 2;
            net.jpountz.util.SafeUtils.writeInt(r7, net.jpountz.lz4.LZ4Utils.hash(net.jpountz.util.ByteBufferUtils.readInt(r5, r1)), r1);
            r1 = net.jpountz.lz4.LZ4Utils.hash(net.jpountz.util.ByteBufferUtils.readInt(r5, r6));
            r12 = net.jpountz.util.SafeUtils.readInt(r7, r1);
            net.jpountz.util.SafeUtils.writeInt(r7, r1, r6);
            r13 = r6 - r12;
     */
    /* JADX WARNING: Missing block: B:41:0x012d, code skipped:
            if (r13 >= 65536) goto L_0x013d;
     */
    /* JADX WARNING: Missing block: B:43:0x0133, code skipped:
            if (net.jpountz.lz4.LZ4ByteBufferUtils.readIntEquals(r5, r12, r6) != false) goto L_0x0136;
     */
    /* JADX WARNING: Missing block: B:44:0x0136, code skipped:
            r15 = r11 + 1;
            net.jpountz.util.ByteBufferUtils.writeByte(r8, r11, 0);
     */
    public int compress(java.nio.ByteBuffer r21, int r22, int r23, java.nio.ByteBuffer r24, int r25, int r26) {
        /*
        r20 = this;
        r1 = r22;
        r3 = r23;
        r4 = r25;
        r6 = r26;
        r0 = r21.hasArray();
        if (r0 == 0) goto L_0x0033;
    L_0x000e:
        r0 = r24.hasArray();
        if (r0 == 0) goto L_0x0033;
    L_0x0014:
        r2 = r21.array();
        r0 = r21.arrayOffset();
        r5 = r1 + r0;
        r7 = r24.array();
        r0 = r24.arrayOffset();
        r8 = r4 + r0;
        r0 = r20;
        r1 = r2;
        r2 = r5;
        r4 = r7;
        r5 = r8;
        r0 = r0.compress(r1, r2, r3, r4, r5, r6);
        return r0;
    L_0x0033:
        r5 = net.jpountz.util.ByteBufferUtils.inNativeByteOrder(r21);
        r8 = net.jpountz.util.ByteBufferUtils.inNativeByteOrder(r24);
        net.jpountz.util.ByteBufferUtils.checkRange(r5, r1, r3);
        net.jpountz.util.ByteBufferUtils.checkRange(r8, r4, r6);
        r10 = r4 + r6;
        r0 = 65547; // 0x1000b float:9.1851E-41 double:3.23845E-319;
        if (r3 >= r0) goto L_0x0051;
    L_0x0048:
        r0 = r5;
        r2 = r3;
        r3 = r8;
        r5 = r10;
        r0 = compress64k(r0, r1, r2, r3, r4, r5);
        return r0;
    L_0x0051:
        r0 = r1 + r3;
        r2 = r0 + -5;
        r3 = r0 + -12;
        r6 = r1 + 1;
        r7 = 4096; // 0x1000 float:5.74E-42 double:2.0237E-320;
        r7 = new int[r7];
        java.util.Arrays.fill(r7, r1);
        r9 = r1;
        r11 = r4;
    L_0x0062:
        r12 = net.jpountz.lz4.LZ4Constants.SKIP_STRENGTH;
        r13 = 1;
        r12 = r13 << r12;
    L_0x0067:
        r13 = r13 + r6;
        r14 = r12 + 1;
        r15 = net.jpountz.lz4.LZ4Constants.SKIP_STRENGTH;
        r12 = r12 >>> r15;
        if (r13 <= r3) goto L_0x0073;
    L_0x006f:
        r6 = r9;
    L_0x0070:
        r9 = r11;
        goto L_0x0105;
    L_0x0073:
        r15 = net.jpountz.util.ByteBufferUtils.readInt(r5, r6);
        r15 = net.jpountz.lz4.LZ4Utils.hash(r15);
        r16 = r12;
        r12 = net.jpountz.util.SafeUtils.readInt(r7, r15);
        r17 = r13;
        r13 = r6 - r12;
        net.jpountz.util.SafeUtils.writeInt(r7, r15, r6);
        r15 = 65536; // 0x10000 float:9.18355E-41 double:3.2379E-319;
        if (r13 >= r15) goto L_0x0145;
    L_0x008c:
        r18 = net.jpountz.lz4.LZ4ByteBufferUtils.readIntEquals(r5, r12, r6);
        if (r18 == 0) goto L_0x0145;
    L_0x0092:
        r14 = net.jpountz.lz4.LZ4ByteBufferUtils.commonBytesBackward(r5, r12, r6, r1, r9);
        r6 = r6 - r14;
        r12 = r12 - r14;
        r14 = r6 - r9;
        r15 = r11 + 1;
        r16 = r15 + r14;
        r16 = r16 + 8;
        r17 = r14 >>> 8;
        r1 = r16 + r17;
        if (r1 <= r10) goto L_0x00ae;
    L_0x00a6:
        r0 = new net.jpountz.lz4.LZ4Exception;
        r1 = "maxDestLen is too small";
        r0.<init>(r1);
        throw r0;
    L_0x00ae:
        r1 = 15;
        if (r14 < r1) goto L_0x00be;
    L_0x00b2:
        r1 = 240; // 0xf0 float:3.36E-43 double:1.186E-321;
        net.jpountz.util.ByteBufferUtils.writeByte(r8, r11, r1);
        r1 = r14 + -15;
        r15 = net.jpountz.lz4.LZ4ByteBufferUtils.writeLen(r1, r8, r15);
        goto L_0x00c3;
    L_0x00be:
        r1 = r14 << 4;
        net.jpountz.util.ByteBufferUtils.writeByte(r8, r11, r1);
    L_0x00c3:
        net.jpountz.lz4.LZ4ByteBufferUtils.wildArraycopy(r5, r9, r8, r15, r14);
        r15 = r15 + r14;
    L_0x00c7:
        net.jpountz.util.ByteBufferUtils.writeShortLE(r8, r15, r13);
        r15 = r15 + 2;
        r6 = r6 + 4;
        r12 = r12 + 4;
        r1 = net.jpountz.lz4.LZ4ByteBufferUtils.commonBytes(r5, r12, r6, r2);
        r9 = r15 + 6;
        r12 = r1 >>> 8;
        r9 = r9 + r12;
        if (r9 <= r10) goto L_0x00e3;
    L_0x00db:
        r0 = new net.jpountz.lz4.LZ4Exception;
        r1 = "maxDestLen is too small";
        r0.<init>(r1);
        throw r0;
    L_0x00e3:
        r6 = r6 + r1;
        r9 = 15;
        if (r1 < r9) goto L_0x00f8;
    L_0x00e8:
        r12 = net.jpountz.util.ByteBufferUtils.readByte(r8, r11);
        r12 = r12 | r9;
        net.jpountz.util.ByteBufferUtils.writeByte(r8, r11, r12);
        r1 = r1 + -15;
        r1 = net.jpountz.lz4.LZ4ByteBufferUtils.writeLen(r1, r8, r15);
        r11 = r1;
        goto L_0x0101;
    L_0x00f8:
        r12 = net.jpountz.util.ByteBufferUtils.readByte(r8, r11);
        r1 = r1 | r12;
        net.jpountz.util.ByteBufferUtils.writeByte(r8, r11, r1);
        r11 = r15;
    L_0x0101:
        if (r6 <= r3) goto L_0x010d;
    L_0x0103:
        goto L_0x0070;
    L_0x0105:
        r7 = r0 - r6;
        r0 = net.jpountz.lz4.LZ4ByteBufferUtils.lastLiterals(r5, r6, r7, r8, r9, r10);
        r0 = r0 - r4;
        return r0;
    L_0x010d:
        r1 = r6 + -2;
        r12 = net.jpountz.util.ByteBufferUtils.readInt(r5, r1);
        r12 = net.jpountz.lz4.LZ4Utils.hash(r12);
        net.jpountz.util.SafeUtils.writeInt(r7, r12, r1);
        r1 = net.jpountz.util.ByteBufferUtils.readInt(r5, r6);
        r1 = net.jpountz.lz4.LZ4Utils.hash(r1);
        r12 = net.jpountz.util.SafeUtils.readInt(r7, r1);
        net.jpountz.util.SafeUtils.writeInt(r7, r1, r6);
        r13 = r6 - r12;
        r1 = 65536; // 0x10000 float:9.18355E-41 double:3.2379E-319;
        if (r13 >= r1) goto L_0x013d;
    L_0x012f:
        r14 = net.jpountz.lz4.LZ4ByteBufferUtils.readIntEquals(r5, r12, r6);
        if (r14 != 0) goto L_0x0136;
    L_0x0135:
        goto L_0x013d;
    L_0x0136:
        r15 = r11 + 1;
        r14 = 0;
        net.jpountz.util.ByteBufferUtils.writeByte(r8, r11, r14);
        goto L_0x00c7;
    L_0x013d:
        r1 = r6 + 1;
        r9 = r6;
        r6 = r1;
        r1 = r22;
        goto L_0x0062;
    L_0x0145:
        r12 = r14;
        r13 = r16;
        r6 = r17;
        r1 = r22;
        goto L_0x0067;
        */
        throw new UnsupportedOperationException("Method not decompiled: net.jpountz.lz4.LZ4JavaSafeCompressor.compress(java.nio.ByteBuffer, int, int, java.nio.ByteBuffer, int, int):int");
    }
}
