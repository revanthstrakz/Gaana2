package com.google.api.client.repackaged.org.apache.commons.codec.binary;

import com.google.api.client.repackaged.org.apache.commons.codec.BinaryDecoder;
import com.google.api.client.repackaged.org.apache.commons.codec.BinaryEncoder;
import com.google.api.client.repackaged.org.apache.commons.codec.DecoderException;
import com.google.api.client.repackaged.org.apache.commons.codec.EncoderException;

public abstract class BaseNCodec implements BinaryDecoder, BinaryEncoder {
    private static final int DEFAULT_BUFFER_RESIZE_FACTOR = 2;
    private static final int DEFAULT_BUFFER_SIZE = 8192;
    protected static final int MASK_8BITS = 255;
    public static final int MIME_CHUNK_SIZE = 76;
    protected static final byte PAD_DEFAULT = (byte) 61;
    public static final int PEM_CHUNK_SIZE = 64;
    protected final byte PAD = PAD_DEFAULT;
    protected byte[] buffer;
    private final int chunkSeparatorLength;
    protected int currentLinePos;
    private final int encodedBlockSize;
    protected boolean eof;
    protected final int lineLength;
    protected int modulus;
    protected int pos;
    private int readPos;
    private final int unencodedBlockSize;

    protected static boolean isWhiteSpace(byte b) {
        if (!(b == (byte) 13 || b == (byte) 32)) {
            switch (b) {
                case (byte) 9:
                case (byte) 10:
                    break;
                default:
                    return false;
            }
        }
        return true;
    }

    public abstract void decode(byte[] bArr, int i, int i2);

    public abstract void encode(byte[] bArr, int i, int i2);

    /* Access modifiers changed, original: protected */
    public int getDefaultBufferSize() {
        return 8192;
    }

    public abstract boolean isInAlphabet(byte b);

    protected BaseNCodec(int i, int i2, int i3, int i4) {
        this.unencodedBlockSize = i;
        this.encodedBlockSize = i2;
        i3 = (i3 <= 0 || i4 <= 0) ? 0 : (i3 / i2) * i2;
        this.lineLength = i3;
        this.chunkSeparatorLength = i4;
    }

    /* Access modifiers changed, original: 0000 */
    public boolean hasData() {
        return this.buffer != null;
    }

    /* Access modifiers changed, original: 0000 */
    public int available() {
        return this.buffer != null ? this.pos - this.readPos : 0;
    }

    private void resizeBuffer() {
        if (this.buffer == null) {
            this.buffer = new byte[getDefaultBufferSize()];
            this.pos = 0;
            this.readPos = 0;
            return;
        }
        byte[] bArr = new byte[(this.buffer.length * 2)];
        System.arraycopy(this.buffer, 0, bArr, 0, this.buffer.length);
        this.buffer = bArr;
    }

    /* Access modifiers changed, original: protected */
    public void ensureBufferSize(int i) {
        if (this.buffer == null || this.buffer.length < this.pos + i) {
            resizeBuffer();
        }
    }

    /* Access modifiers changed, original: 0000 */
    public int readResults(byte[] bArr, int i, int i2) {
        if (this.buffer != null) {
            i2 = Math.min(available(), i2);
            System.arraycopy(this.buffer, this.readPos, bArr, i, i2);
            this.readPos += i2;
            if (this.readPos >= this.pos) {
                this.buffer = null;
            }
            return i2;
        }
        return this.eof ? -1 : 0;
    }

    private void reset() {
        this.buffer = null;
        this.pos = 0;
        this.readPos = 0;
        this.currentLinePos = 0;
        this.modulus = 0;
        this.eof = false;
    }

    public Object encode(Object obj) throws EncoderException {
        if (obj instanceof byte[]) {
            return encode((byte[]) obj);
        }
        throw new EncoderException("Parameter supplied to Base-N encode is not a byte[]");
    }

    public String encodeToString(byte[] bArr) {
        return StringUtils.newStringUtf8(encode(bArr));
    }

    public Object decode(Object obj) throws DecoderException {
        if (obj instanceof byte[]) {
            return decode((byte[]) obj);
        }
        if (obj instanceof String) {
            return decode((String) obj);
        }
        throw new DecoderException("Parameter supplied to Base-N decode is not a byte[] or a String");
    }

    public byte[] decode(String str) {
        return decode(StringUtils.getBytesUtf8(str));
    }

    public byte[] decode(byte[] bArr) {
        reset();
        if (bArr == null || bArr.length == 0) {
            return bArr;
        }
        decode(bArr, 0, bArr.length);
        decode(bArr, 0, -1);
        bArr = new byte[this.pos];
        readResults(bArr, 0, bArr.length);
        return bArr;
    }

    public byte[] encode(byte[] bArr) {
        reset();
        if (bArr == null || bArr.length == 0) {
            return bArr;
        }
        encode(bArr, 0, bArr.length);
        encode(bArr, 0, -1);
        bArr = new byte[(this.pos - this.readPos)];
        readResults(bArr, 0, bArr.length);
        return bArr;
    }

    public String encodeAsString(byte[] bArr) {
        return StringUtils.newStringUtf8(encode(bArr));
    }

    public boolean isInAlphabet(byte[] bArr, boolean z) {
        int i = 0;
        while (i < bArr.length) {
            if (!isInAlphabet(bArr[i]) && (!z || (bArr[i] != PAD_DEFAULT && !isWhiteSpace(bArr[i])))) {
                return false;
            }
            i++;
        }
        return true;
    }

    public boolean isInAlphabet(String str) {
        return isInAlphabet(StringUtils.getBytesUtf8(str), true);
    }

    /* Access modifiers changed, original: protected */
    public boolean containsAlphabetOrPad(byte[] bArr) {
        if (bArr == null) {
            return false;
        }
        for (byte b : bArr) {
            if (PAD_DEFAULT == b || isInAlphabet(b)) {
                return true;
            }
        }
        return false;
    }

    public long getEncodedLength(byte[] bArr) {
        long length = ((long) (((bArr.length + this.unencodedBlockSize) - 1) / this.unencodedBlockSize)) * ((long) this.encodedBlockSize);
        return this.lineLength > 0 ? length + ((((length + ((long) this.lineLength)) - 1) / ((long) this.lineLength)) * ((long) this.chunkSeparatorLength)) : length;
    }
}
