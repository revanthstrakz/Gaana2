package net.jpountz.lz4;

import java.nio.ByteBuffer;
import java.util.Arrays;
import net.jpountz.util.ByteBufferUtils;
import net.jpountz.util.SafeUtils;

final class LZ4HCJavaSafeCompressor extends LZ4Compressor {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final LZ4Compressor INSTANCE = new LZ4HCJavaSafeCompressor();
    final int compressionLevel;
    private final int maxAttempts;

    private class HashTable {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        static final int MASK = 65535;
        private final int base;
        private final short[] chainTable;
        private final int[] hashTable = new int[32768];
        int nextToUpdate;

        static {
            Class cls = LZ4HCJavaSafeCompressor.class;
        }

        HashTable(int i) {
            this.base = i;
            this.nextToUpdate = i;
            Arrays.fill(this.hashTable, -1);
            this.chainTable = new short[65536];
        }

        private int hashPointer(byte[] bArr, int i) {
            return hashPointer(SafeUtils.readInt(bArr, i));
        }

        private int hashPointer(ByteBuffer byteBuffer, int i) {
            return hashPointer(ByteBufferUtils.readInt(byteBuffer, i));
        }

        private int hashPointer(int i) {
            return this.hashTable[LZ4Utils.hashHC(i)];
        }

        private int next(int i) {
            return i - (this.chainTable[i & 65535] & 65535);
        }

        private void addHash(byte[] bArr, int i) {
            addHash(SafeUtils.readInt(bArr, i), i);
        }

        private void addHash(ByteBuffer byteBuffer, int i) {
            addHash(ByteBufferUtils.readInt(byteBuffer, i), i);
        }

        private void addHash(int i, int i2) {
            i = LZ4Utils.hashHC(i);
            int i3 = i2 - this.hashTable[i];
            if (i3 >= 65536) {
                i3 = 65535;
            }
            this.chainTable[65535 & i2] = (short) i3;
            this.hashTable[i] = i2;
        }

        /* Access modifiers changed, original: 0000 */
        public void insert(int i, byte[] bArr) {
            while (this.nextToUpdate < i) {
                addHash(bArr, this.nextToUpdate);
                this.nextToUpdate++;
            }
        }

        /* Access modifiers changed, original: 0000 */
        public void insert(int i, ByteBuffer byteBuffer) {
            while (this.nextToUpdate < i) {
                addHash(byteBuffer, this.nextToUpdate);
                this.nextToUpdate++;
            }
        }

        /* Access modifiers changed, original: 0000 */
        public boolean insertAndFindBestMatch(byte[] bArr, int i, int i2, Match match) {
            int i3;
            int i4;
            match.start = i;
            match.len = 0;
            insert(i, bArr);
            int hashPointer = hashPointer(bArr, i);
            if (hashPointer < i - 4 || hashPointer > i || hashPointer < this.base) {
                i3 = 0;
                i4 = i3;
            } else {
                if (LZ4SafeUtils.readIntEquals(bArr, hashPointer, i)) {
                    i3 = i - hashPointer;
                    i4 = LZ4SafeUtils.commonBytes(bArr, hashPointer + 4, i + 4, i2) + 4;
                    match.len = i4;
                    match.ref = hashPointer;
                } else {
                    i3 = 0;
                    i4 = i3;
                }
                hashPointer = next(hashPointer);
            }
            int i5 = hashPointer;
            for (hashPointer = 0; hashPointer < LZ4HCJavaSafeCompressor.this.maxAttempts && i5 >= Math.max(this.base, (i - 65536) + 1) && i5 <= i; hashPointer++) {
                if (LZ4SafeUtils.readIntEquals(bArr, i5, i)) {
                    int commonBytes = LZ4SafeUtils.commonBytes(bArr, i5 + 4, i + 4, i2) + 4;
                    if (commonBytes > match.len) {
                        match.ref = i5;
                        match.len = commonBytes;
                    }
                }
                i5 = next(i5);
            }
            if (i4 != 0) {
                i4 = (i4 + i) - 3;
                while (i < i4 - i3) {
                    this.chainTable[65535 & i] = (short) i3;
                    i++;
                }
                do {
                    this.chainTable[i & 65535] = (short) i3;
                    this.hashTable[LZ4Utils.hashHC(SafeUtils.readInt(bArr, i))] = i;
                    i++;
                } while (i < i4);
                this.nextToUpdate = i4;
            }
            if (match.len != 0) {
                return true;
            }
            return false;
        }

        /* Access modifiers changed, original: 0000 */
        public boolean insertAndFindWiderMatch(byte[] bArr, int i, int i2, int i3, int i4, Match match) {
            match.len = i4;
            insert(i, bArr);
            int hashPointer = hashPointer(bArr, i);
            for (int i5 = 0; i5 < LZ4HCJavaSafeCompressor.this.maxAttempts && hashPointer >= Math.max(this.base, (i - 65536) + 1) && hashPointer <= i; i5++) {
                if (LZ4SafeUtils.readIntEquals(bArr, hashPointer, i)) {
                    int commonBytes = 4 + LZ4SafeUtils.commonBytes(bArr, hashPointer + 4, i + 4, i3);
                    int commonBytesBackward = LZ4SafeUtils.commonBytesBackward(bArr, hashPointer, i, this.base, i2);
                    commonBytes += commonBytesBackward;
                    if (commonBytes > match.len) {
                        match.len = commonBytes;
                        match.ref = hashPointer - commonBytesBackward;
                        match.start = i - commonBytesBackward;
                    }
                }
                hashPointer = next(hashPointer);
            }
            if (match.len > i4) {
                return true;
            }
            return false;
        }

        /* Access modifiers changed, original: 0000 */
        public boolean insertAndFindBestMatch(ByteBuffer byteBuffer, int i, int i2, Match match) {
            int i3;
            int i4;
            match.start = i;
            match.len = 0;
            insert(i, byteBuffer);
            int hashPointer = hashPointer(byteBuffer, i);
            if (hashPointer < i - 4 || hashPointer > i || hashPointer < this.base) {
                i3 = 0;
                i4 = i3;
            } else {
                if (LZ4ByteBufferUtils.readIntEquals(byteBuffer, hashPointer, i)) {
                    i3 = i - hashPointer;
                    i4 = LZ4ByteBufferUtils.commonBytes(byteBuffer, hashPointer + 4, i + 4, i2) + 4;
                    match.len = i4;
                    match.ref = hashPointer;
                } else {
                    i3 = 0;
                    i4 = i3;
                }
                hashPointer = next(hashPointer);
            }
            int i5 = hashPointer;
            for (hashPointer = 0; hashPointer < LZ4HCJavaSafeCompressor.this.maxAttempts && i5 >= Math.max(this.base, (i - 65536) + 1) && i5 <= i; hashPointer++) {
                if (LZ4ByteBufferUtils.readIntEquals(byteBuffer, i5, i)) {
                    int commonBytes = LZ4ByteBufferUtils.commonBytes(byteBuffer, i5 + 4, i + 4, i2) + 4;
                    if (commonBytes > match.len) {
                        match.ref = i5;
                        match.len = commonBytes;
                    }
                }
                i5 = next(i5);
            }
            if (i4 != 0) {
                i4 = (i4 + i) - 3;
                while (i < i4 - i3) {
                    this.chainTable[65535 & i] = (short) i3;
                    i++;
                }
                do {
                    this.chainTable[i & 65535] = (short) i3;
                    this.hashTable[LZ4Utils.hashHC(ByteBufferUtils.readInt(byteBuffer, i))] = i;
                    i++;
                } while (i < i4);
                this.nextToUpdate = i4;
            }
            if (match.len != 0) {
                return true;
            }
            return false;
        }

        /* Access modifiers changed, original: 0000 */
        public boolean insertAndFindWiderMatch(ByteBuffer byteBuffer, int i, int i2, int i3, int i4, Match match) {
            match.len = i4;
            insert(i, byteBuffer);
            int hashPointer = hashPointer(byteBuffer, i);
            for (int i5 = 0; i5 < LZ4HCJavaSafeCompressor.this.maxAttempts && hashPointer >= Math.max(this.base, (i - 65536) + 1) && hashPointer <= i; i5++) {
                if (LZ4ByteBufferUtils.readIntEquals(byteBuffer, hashPointer, i)) {
                    int commonBytes = 4 + LZ4ByteBufferUtils.commonBytes(byteBuffer, hashPointer + 4, i + 4, i3);
                    int commonBytesBackward = LZ4ByteBufferUtils.commonBytesBackward(byteBuffer, hashPointer, i, this.base, i2);
                    commonBytes += commonBytesBackward;
                    if (commonBytes > match.len) {
                        match.len = commonBytes;
                        match.ref = hashPointer - commonBytesBackward;
                        match.start = i - commonBytesBackward;
                    }
                }
                hashPointer = next(hashPointer);
            }
            if (match.len > i4) {
                return true;
            }
            return false;
        }
    }

    LZ4HCJavaSafeCompressor() {
        this(9);
    }

    LZ4HCJavaSafeCompressor(int i) {
        this.maxAttempts = 1 << (i - 1);
        this.compressionLevel = i;
    }

    public int compress(byte[] bArr, int i, int i2, byte[] bArr2, int i3, int i4) {
        int i5 = i;
        SafeUtils.checkRange(bArr, i, i2);
        SafeUtils.checkRange(bArr2, i3, i4);
        int i6 = i5 + i2;
        int i7 = i3 + i4;
        int i8 = i6 - 12;
        int i9 = i6 - 5;
        int i10 = i5 + 1;
        HashTable hashTable = new HashTable(i5);
        Match match = new Match();
        Match match2 = new Match();
        Match match3 = new Match();
        Match match4 = new Match();
        int i11 = i5;
        i5 = i3;
        while (i10 < i8) {
            byte[] bArr3 = bArr;
            if (hashTable.insertAndFindBestMatch(bArr3, i10, i9, match2)) {
                Match match5;
                int i12;
                int i13;
                Match match6;
                Match match7;
                Match match8;
                LZ4Utils.copyTo(match2, match);
                int i14 = i5;
                i5 = i11;
                while (match2.end() < i8) {
                    Match match9 = match4;
                    Match match10 = match3;
                    match5 = match2;
                    if (!hashTable.insertAndFindWiderMatch(bArr3, match2.end() - 2, match2.start + 1, i9, match2.len, match10)) {
                        i12 = i8;
                        i13 = i9;
                        match6 = match9;
                        match7 = match10;
                        break;
                    }
                    if (match.start < match5.start) {
                        match2 = match10;
                        if (match2.start < match5.start + match.len) {
                            LZ4Utils.copyTo(match, match5);
                        }
                    } else {
                        match2 = match10;
                    }
                    Object obj = 3;
                    if (match2.start - match5.start < 3) {
                        LZ4Utils.copyTo(match2, match5);
                        bArr3 = bArr;
                        match3 = match2;
                        match2 = match5;
                        match4 = match9;
                    } else {
                        int i15;
                        while (true) {
                            if (match2.start - match5.start < 18) {
                                i10 = match5.len;
                                if (i10 > 18) {
                                    i10 = 18;
                                }
                                if (match5.start + i10 > match2.end() - 4) {
                                    i10 = ((match2.start - match5.start) + match2.len) - 4;
                                }
                                i10 -= match2.start - match5.start;
                                if (i10 > 0) {
                                    match2.fix(i10);
                                }
                            }
                            if (match2.start + match2.len >= i8) {
                                i12 = i8;
                                i13 = i9;
                                match6 = match9;
                                match7 = match2;
                                match8 = match;
                                break;
                            }
                            int end = match2.end() - 3;
                            i11 = match2.start;
                            Match match11 = match;
                            i15 = 18;
                            i15 = 4;
                            Object obj2 = obj;
                            match = match2;
                            if (!hashTable.insertAndFindWiderMatch(bArr, end, i11, i9, match2.len, match9)) {
                                i12 = i8;
                                i13 = i9;
                                match6 = match9;
                                match8 = match11;
                                match7 = match;
                                break;
                            }
                            match2 = match9;
                            Match match12;
                            if (match2.start >= match5.end() + 3) {
                                i12 = i8;
                                i13 = i9;
                                match8 = match11;
                                match7 = match;
                                match = match2;
                                if (match7.start < match5.end()) {
                                    if (match7.start - match5.start < 15) {
                                        if (match5.len > 18) {
                                            match5.len = 18;
                                        }
                                        if (match5.end() > match7.end() - 4) {
                                            match5.len = (match7.end() - match5.start) - 4;
                                        }
                                        match7.fix(match5.end() - match7.start);
                                    } else {
                                        match5.len = match7.start - match5.start;
                                    }
                                }
                                i11 = i5;
                                match12 = match;
                                i14 = LZ4SafeUtils.encodeSequence(bArr, i11, match5.start, match5.ref, match5.len, bArr2, i14, i7);
                                i10 = match5.end();
                                LZ4Utils.copyTo(match7, match5);
                                LZ4Utils.copyTo(match12, match7);
                                match9 = match12;
                                i5 = i10;
                            } else if (match2.start >= match5.end()) {
                                if (match.start < match5.end()) {
                                    match.fix(match5.end() - match.start);
                                    if (match.len < 4) {
                                        LZ4Utils.copyTo(match2, match);
                                    }
                                }
                                i11 = i5;
                                match12 = match2;
                                i12 = i8;
                                i13 = i9;
                                match8 = match11;
                                match7 = match;
                                i14 = LZ4SafeUtils.encodeSequence(bArr, i11, match5.start, match5.ref, match5.len, bArr2, i14, i7);
                                i10 = match5.end();
                                LZ4Utils.copyTo(match12, match5);
                                LZ4Utils.copyTo(match7, match8);
                                bArr3 = bArr;
                                match4 = match12;
                                i5 = i10;
                                match = match8;
                                match3 = match7;
                                match2 = match5;
                                i8 = i12;
                                i9 = i13;
                            } else {
                                i12 = i8;
                                i13 = i9;
                                match8 = match11;
                                match7 = match;
                                match = match2;
                                LZ4Utils.copyTo(match, match7);
                                match9 = match;
                            }
                            match = match8;
                            match2 = match7;
                            obj = obj2;
                            i8 = i12;
                            i9 = i13;
                        }
                        if (match7.start < match5.end()) {
                            match5.len = match7.start - match5.start;
                        }
                        byte[] bArr4 = bArr;
                        byte[] bArr5 = bArr2;
                        i15 = i7;
                        i5 = LZ4SafeUtils.encodeSequence(bArr4, match5.end(), match7.start, match7.ref, match7.len, bArr5, LZ4SafeUtils.encodeSequence(bArr4, i5, match5.start, match5.ref, match5.len, bArr5, i14, i15), i15);
                        i11 = match7.end();
                        i10 = i11;
                        match = match8;
                        match3 = match7;
                        match2 = match5;
                        match4 = match6;
                        i8 = i12;
                        i9 = i13;
                    }
                }
                match6 = match4;
                match5 = match2;
                i12 = i8;
                i13 = i9;
                match7 = match3;
                match8 = match;
                i5 = LZ4SafeUtils.encodeSequence(bArr, i5, match5.start, match5.ref, match5.len, bArr2, i14, i7);
                i11 = match5.end();
                i10 = i11;
                match = match8;
                match3 = match7;
                match2 = match5;
                match4 = match6;
                i8 = i12;
                i9 = i13;
            } else {
                i10++;
            }
        }
        return LZ4SafeUtils.lastLiterals(bArr, i11, i6 - i11, bArr2, i5, i7) - i3;
    }

    public int compress(ByteBuffer byteBuffer, int i, int i2, ByteBuffer byteBuffer2, int i3, int i4) {
        int i5 = i;
        int i6 = i2;
        int i7 = i3;
        int i8 = i4;
        if (byteBuffer.hasArray() && byteBuffer2.hasArray()) {
            return compress(byteBuffer.array(), byteBuffer.arrayOffset() + i5, i6, byteBuffer2.array(), i7 + byteBuffer2.arrayOffset(), i8);
        }
        ByteBuffer inNativeByteOrder = ByteBufferUtils.inNativeByteOrder(byteBuffer);
        ByteBuffer inNativeByteOrder2 = ByteBufferUtils.inNativeByteOrder(byteBuffer2);
        ByteBufferUtils.checkRange(inNativeByteOrder, i5, i6);
        ByteBufferUtils.checkRange(inNativeByteOrder2, i7, i8);
        int i9 = i5 + i6;
        int i10 = i7 + i8;
        int i11 = i9 - 12;
        int i12 = i9 - 5;
        int i13 = i5 + 1;
        HashTable hashTable = new HashTable(i5);
        Match match = new Match();
        Match match2 = new Match();
        Match match3 = new Match();
        Match match4 = new Match();
        i6 = i5;
        i5 = i7;
        while (i13 < i11) {
            if (hashTable.insertAndFindBestMatch(inNativeByteOrder, i13, i12, match2)) {
                Match match5;
                int i14;
                Match match6;
                HashTable hashTable2;
                int i15;
                Match match7;
                Match match8;
                LZ4Utils.copyTo(match2, match);
                int i16 = i5;
                i5 = i6;
                while (match2.end() < i11) {
                    Match match9 = match4;
                    Match match10 = match3;
                    match5 = match2;
                    i14 = i9;
                    match6 = match;
                    if (!hashTable.insertAndFindWiderMatch(inNativeByteOrder, match2.end() - 2, match2.start + 1, i12, match2.len, match10)) {
                        hashTable2 = hashTable;
                        i15 = i11;
                        match7 = match9;
                        match8 = match10;
                        break;
                    }
                    Match match11;
                    if (match6.start < match5.start) {
                        match11 = match10;
                        if (match11.start < match5.start + match6.len) {
                            LZ4Utils.copyTo(match6, match5);
                        }
                    } else {
                        match11 = match10;
                    }
                    Object obj = 3;
                    if (match11.start - match5.start < 3) {
                        LZ4Utils.copyTo(match11, match5);
                        match2 = match5;
                        match3 = match11;
                        match = match6;
                        match4 = match9;
                        i9 = i14;
                        i7 = i3;
                    } else {
                        int i17;
                        while (true) {
                            if (match11.start - match5.start < 18) {
                                i13 = match5.len;
                                if (i13 > 18) {
                                    i13 = 18;
                                }
                                if (match5.start + i13 > match11.end() - 4) {
                                    i13 = ((match11.start - match5.start) + match11.len) - 4;
                                }
                                i13 -= match11.start - match5.start;
                                if (i13 > 0) {
                                    match11.fix(i13);
                                }
                            }
                            if (match11.start + match11.len >= i11) {
                                hashTable2 = hashTable;
                                i15 = i11;
                                match7 = match9;
                                match8 = match11;
                                break;
                            }
                            int end = match11.end() - 3;
                            i6 = match11.start;
                            HashTable hashTable3 = hashTable;
                            i17 = 18;
                            i17 = 4;
                            Object obj2 = obj;
                            Match match12 = match11;
                            if (!hashTable.insertAndFindWiderMatch(inNativeByteOrder, end, i6, i12, match11.len, match9)) {
                                i15 = i11;
                                match7 = match9;
                                hashTable2 = hashTable3;
                                match8 = match12;
                                break;
                            }
                            match11 = match9;
                            Match match13;
                            if (match11.start >= match5.end() + 3) {
                                i15 = i11;
                                hashTable2 = hashTable3;
                                match8 = match12;
                                match12 = match11;
                                if (match8.start < match5.end()) {
                                    if (match8.start - match5.start < 15) {
                                        if (match5.len > 18) {
                                            match5.len = 18;
                                        }
                                        if (match5.end() > match8.end() - 4) {
                                            match5.len = (match8.end() - match5.start) - 4;
                                        }
                                        match8.fix(match5.end() - match8.start);
                                    } else {
                                        match5.len = match8.start - match5.start;
                                    }
                                }
                                i6 = i5;
                                match13 = match12;
                                i16 = LZ4ByteBufferUtils.encodeSequence(inNativeByteOrder, i6, match5.start, match5.ref, match5.len, inNativeByteOrder2, i16, i10);
                                i13 = match5.end();
                                LZ4Utils.copyTo(match8, match5);
                                LZ4Utils.copyTo(match13, match8);
                                match9 = match13;
                                i5 = i13;
                            } else if (match11.start >= match5.end()) {
                                if (match12.start < match5.end()) {
                                    match12.fix(match5.end() - match12.start);
                                    if (match12.len < 4) {
                                        LZ4Utils.copyTo(match11, match12);
                                    }
                                }
                                i6 = i5;
                                match13 = match11;
                                i15 = i11;
                                hashTable2 = hashTable3;
                                match8 = match12;
                                i16 = LZ4ByteBufferUtils.encodeSequence(inNativeByteOrder, i6, match5.start, match5.ref, match5.len, inNativeByteOrder2, i16, i10);
                                i13 = match5.end();
                                LZ4Utils.copyTo(match13, match5);
                                LZ4Utils.copyTo(match8, match6);
                                match4 = match13;
                                match2 = match5;
                                i5 = i13;
                                match = match6;
                                match3 = match8;
                                hashTable = hashTable2;
                                i9 = i14;
                                i11 = i15;
                                i7 = i3;
                            } else {
                                i15 = i11;
                                hashTable2 = hashTable3;
                                match8 = match12;
                                match12 = match11;
                                LZ4Utils.copyTo(match12, match8);
                                match9 = match12;
                            }
                            match11 = match8;
                            hashTable = hashTable2;
                            obj = obj2;
                            i11 = i15;
                        }
                        if (match8.start < match5.end()) {
                            match5.len = match8.start - match5.start;
                        }
                        ByteBuffer byteBuffer3 = inNativeByteOrder;
                        ByteBuffer byteBuffer4 = inNativeByteOrder2;
                        i17 = i10;
                        i5 = LZ4ByteBufferUtils.encodeSequence(byteBuffer3, match5.end(), match8.start, match8.ref, match8.len, byteBuffer4, LZ4ByteBufferUtils.encodeSequence(byteBuffer3, i5, match5.start, match5.ref, match5.len, byteBuffer4, i16, i17), i17);
                        i6 = match8.end();
                        match2 = match5;
                        i13 = i6;
                        match = match6;
                        match3 = match8;
                        hashTable = hashTable2;
                        match4 = match7;
                        i9 = i14;
                        i11 = i15;
                        i7 = i3;
                    }
                }
                match7 = match4;
                match5 = match2;
                hashTable2 = hashTable;
                i14 = i9;
                i15 = i11;
                match8 = match3;
                match6 = match;
                i5 = LZ4ByteBufferUtils.encodeSequence(inNativeByteOrder, i5, match5.start, match5.ref, match5.len, inNativeByteOrder2, i16, i10);
                i6 = match5.end();
                match2 = match5;
                i13 = i6;
                match = match6;
                match3 = match8;
                hashTable = hashTable2;
                match4 = match7;
                i9 = i14;
                i11 = i15;
                i7 = i3;
            } else {
                i13++;
            }
        }
        return LZ4ByteBufferUtils.lastLiterals(inNativeByteOrder, i6, i9 - i6, inNativeByteOrder2, i5, i10) - i3;
    }
}
