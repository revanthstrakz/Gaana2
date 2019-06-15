package com.google.android.exoplayer2.util;

import android.support.v4.view.MotionEventCompat;

public final class ParsableBitArray {
    private int bitOffset;
    private int byteLimit;
    private int byteOffset;
    public byte[] data;

    public ParsableBitArray() {
        this.data = Util.EMPTY_BYTE_ARRAY;
    }

    public ParsableBitArray(byte[] bArr) {
        this(bArr, bArr.length);
    }

    public ParsableBitArray(byte[] bArr, int i) {
        this.data = bArr;
        this.byteLimit = i;
    }

    public void reset(byte[] bArr) {
        reset(bArr, bArr.length);
    }

    public void reset(ParsableByteArray parsableByteArray) {
        reset(parsableByteArray.data, parsableByteArray.limit());
        setPosition(parsableByteArray.getPosition() * 8);
    }

    public void reset(byte[] bArr, int i) {
        this.data = bArr;
        this.byteOffset = 0;
        this.bitOffset = 0;
        this.byteLimit = i;
    }

    public int bitsLeft() {
        return ((this.byteLimit - this.byteOffset) * 8) - this.bitOffset;
    }

    public int getPosition() {
        return (this.byteOffset * 8) + this.bitOffset;
    }

    public int getBytePosition() {
        Assertions.checkState(this.bitOffset == 0);
        return this.byteOffset;
    }

    public void setPosition(int i) {
        this.byteOffset = i / 8;
        this.bitOffset = i - (this.byteOffset * 8);
        assertValidOffset();
    }

    public void skipBit() {
        int i = this.bitOffset + 1;
        this.bitOffset = i;
        if (i == 8) {
            this.bitOffset = 0;
            this.byteOffset++;
        }
        assertValidOffset();
    }

    public void skipBits(int i) {
        int i2 = i / 8;
        this.byteOffset += i2;
        this.bitOffset += i - (i2 * 8);
        if (this.bitOffset > 7) {
            this.byteOffset++;
            this.bitOffset -= 8;
        }
        assertValidOffset();
    }

    public boolean readBit() {
        boolean z = (this.data[this.byteOffset] & (128 >> this.bitOffset)) != 0;
        skipBit();
        return z;
    }

    public int readBits(int i) {
        if (i == 0) {
            return 0;
        }
        this.bitOffset += i;
        int i2 = 0;
        while (this.bitOffset > 8) {
            this.bitOffset -= 8;
            byte[] bArr = this.data;
            int i3 = this.byteOffset;
            this.byteOffset = i3 + 1;
            i2 |= (bArr[i3] & 255) << this.bitOffset;
        }
        i = (-1 >>> (32 - i)) & (i2 | ((this.data[this.byteOffset] & 255) >> (8 - this.bitOffset)));
        if (this.bitOffset == 8) {
            this.bitOffset = 0;
            this.byteOffset++;
        }
        assertValidOffset();
        return i;
    }

    public void readBits(byte[] bArr, int i, int i2) {
        byte[] bArr2;
        int i3;
        int i4 = (i2 >> 3) + i;
        while (i < i4) {
            bArr2 = this.data;
            i3 = this.byteOffset;
            this.byteOffset = i3 + 1;
            bArr[i] = (byte) (bArr2[i3] << this.bitOffset);
            bArr[i] = (byte) (((255 & this.data[this.byteOffset]) >> (8 - this.bitOffset)) | bArr[i]);
            i++;
        }
        i = i2 & 7;
        if (i != 0) {
            bArr[i4] = (byte) (bArr[i4] & (255 >> i));
            if (this.bitOffset + i > 8) {
                byte b = bArr[i4];
                bArr2 = this.data;
                i3 = this.byteOffset;
                this.byteOffset = i3 + 1;
                bArr[i4] = (byte) (b | ((bArr2[i3] & 255) << this.bitOffset));
                this.bitOffset -= 8;
            }
            this.bitOffset += i;
            bArr[i4] = (byte) (((byte) (((this.data[this.byteOffset] & 255) >> (8 - this.bitOffset)) << (8 - i))) | bArr[i4]);
            if (this.bitOffset == 8) {
                this.bitOffset = 0;
                this.byteOffset++;
            }
            assertValidOffset();
        }
    }

    public void byteAlign() {
        if (this.bitOffset != 0) {
            this.bitOffset = 0;
            this.byteOffset++;
            assertValidOffset();
        }
    }

    public void readBytes(byte[] bArr, int i, int i2) {
        Assertions.checkState(this.bitOffset == 0);
        System.arraycopy(this.data, this.byteOffset, bArr, i, i2);
        this.byteOffset += i2;
        assertValidOffset();
    }

    public void skipBytes(int i) {
        Assertions.checkState(this.bitOffset == 0);
        this.byteOffset += i;
        assertValidOffset();
    }

    public void putInt(int i, int i2) {
        if (i2 < 32) {
            i &= (1 << i2) - 1;
        }
        int min = Math.min(8 - this.bitOffset, i2);
        int i3 = (8 - this.bitOffset) - min;
        this.data[this.byteOffset] = (byte) (((MotionEventCompat.ACTION_POINTER_INDEX_MASK >> this.bitOffset) | ((1 << i3) - 1)) & this.data[this.byteOffset]);
        min = i2 - min;
        this.data[this.byteOffset] = (byte) (((i >>> min) << i3) | this.data[this.byteOffset]);
        i3 = this.byteOffset + 1;
        while (min > 8) {
            int i4 = i3 + 1;
            this.data[i3] = (byte) (i >>> (min - 8));
            min -= 8;
            i3 = i4;
        }
        int i5 = 8 - min;
        this.data[i3] = (byte) (this.data[i3] & ((1 << i5) - 1));
        this.data[i3] = (byte) (((i & ((1 << min) - 1)) << i5) | this.data[i3]);
        skipBits(i2);
        assertValidOffset();
    }

    private void assertValidOffset() {
        boolean z = this.byteOffset >= 0 && (this.byteOffset < this.byteLimit || (this.byteOffset == this.byteLimit && this.bitOffset == 0));
        Assertions.checkState(z);
    }
}
