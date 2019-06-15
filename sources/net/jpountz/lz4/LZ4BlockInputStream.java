package net.jpountz.lz4;

import com.google.android.exoplayer2.extractor.ts.PsExtractor;
import java.io.EOFException;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.Checksum;
import net.jpountz.a.e;
import net.jpountz.util.SafeUtils;

public final class LZ4BlockInputStream extends FilterInputStream {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private byte[] buffer;
    private final Checksum checksum;
    private byte[] compressedBuffer;
    private final LZ4FastDecompressor decompressor;
    private boolean finished;
    private int o;
    private int originalLen;
    private final boolean stopOnEmptyBlock;

    public void mark(int i) {
    }

    public boolean markSupported() {
        return false;
    }

    public LZ4BlockInputStream(InputStream inputStream, LZ4FastDecompressor lZ4FastDecompressor, Checksum checksum, boolean z) {
        super(inputStream);
        this.decompressor = lZ4FastDecompressor;
        this.checksum = checksum;
        this.stopOnEmptyBlock = z;
        this.buffer = new byte[0];
        this.compressedBuffer = new byte[LZ4BlockOutputStream.HEADER_LENGTH];
        this.originalLen = 0;
        this.o = 0;
        this.finished = false;
    }

    public LZ4BlockInputStream(InputStream inputStream, LZ4FastDecompressor lZ4FastDecompressor, Checksum checksum) {
        this(inputStream, lZ4FastDecompressor, checksum, true);
    }

    public LZ4BlockInputStream(InputStream inputStream, LZ4FastDecompressor lZ4FastDecompressor) {
        this(inputStream, lZ4FastDecompressor, e.e().a(-1756908916).c(), true);
    }

    public LZ4BlockInputStream(InputStream inputStream, boolean z) {
        this(inputStream, LZ4Factory.fastestInstance().fastDecompressor(), e.e().a(-1756908916).c(), z);
    }

    public LZ4BlockInputStream(InputStream inputStream) {
        this(inputStream, LZ4Factory.fastestInstance().fastDecompressor());
    }

    public int available() throws IOException {
        return this.originalLen - this.o;
    }

    public int read() throws IOException {
        if (this.finished) {
            return -1;
        }
        if (this.o == this.originalLen) {
            refill();
        }
        if (this.finished) {
            return -1;
        }
        byte[] bArr = this.buffer;
        int i = this.o;
        this.o = i + 1;
        return bArr[i] & 255;
    }

    public int read(byte[] bArr, int i, int i2) throws IOException {
        SafeUtils.checkRange(bArr, i, i2);
        if (this.finished) {
            return -1;
        }
        if (this.o == this.originalLen) {
            refill();
        }
        if (this.finished) {
            return -1;
        }
        i2 = Math.min(i2, this.originalLen - this.o);
        System.arraycopy(this.buffer, this.o, bArr, i, i2);
        this.o += i2;
        return i2;
    }

    public int read(byte[] bArr) throws IOException {
        return read(bArr, 0, bArr.length);
    }

    public long skip(long j) throws IOException {
        if (this.finished) {
            return -1;
        }
        if (this.o == this.originalLen) {
            refill();
        }
        if (this.finished) {
            return -1;
        }
        int min = (int) Math.min(j, (long) (this.originalLen - this.o));
        this.o += min;
        return (long) min;
    }

    private void refill() throws IOException {
        try {
            int i;
            readFully(this.compressedBuffer, LZ4BlockOutputStream.HEADER_LENGTH);
            for (i = 0; i < LZ4BlockOutputStream.MAGIC_LENGTH; i++) {
                if (this.compressedBuffer[i] != LZ4BlockOutputStream.MAGIC[i]) {
                    throw new IOException("Stream is corrupted");
                }
            }
            i = this.compressedBuffer[LZ4BlockOutputStream.MAGIC_LENGTH] & 255;
            int i2 = i & PsExtractor.VIDEO_STREAM_MASK;
            int i3 = 10 + (i & 15);
            if (i2 == 16 || i2 == 32) {
                int readIntLE = SafeUtils.readIntLE(this.compressedBuffer, LZ4BlockOutputStream.MAGIC_LENGTH + 1);
                this.originalLen = SafeUtils.readIntLE(this.compressedBuffer, LZ4BlockOutputStream.MAGIC_LENGTH + 5);
                int readIntLE2 = SafeUtils.readIntLE(this.compressedBuffer, LZ4BlockOutputStream.MAGIC_LENGTH + 9);
                if (this.originalLen > (1 << i3) || this.originalLen < 0 || readIntLE < 0 || ((this.originalLen == 0 && readIntLE != 0) || ((this.originalLen != 0 && readIntLE == 0) || (i2 == 16 && this.originalLen != readIntLE)))) {
                    throw new IOException("Stream is corrupted");
                } else if (this.originalLen != 0 || readIntLE != 0) {
                    if (this.buffer.length < this.originalLen) {
                        this.buffer = new byte[Math.max(this.originalLen, (this.buffer.length * 3) / 2)];
                    }
                    if (i2 == 16) {
                        readFully(this.buffer, this.originalLen);
                    } else if (i2 != 32) {
                        throw new AssertionError();
                    } else {
                        if (this.compressedBuffer.length < readIntLE) {
                            this.compressedBuffer = new byte[Math.max(readIntLE, (this.compressedBuffer.length * 3) / 2)];
                        }
                        readFully(this.compressedBuffer, readIntLE);
                        try {
                            if (readIntLE != this.decompressor.decompress(this.compressedBuffer, 0, this.buffer, 0, this.originalLen)) {
                                throw new IOException("Stream is corrupted");
                            }
                        } catch (LZ4Exception e) {
                            throw new IOException("Stream is corrupted", e);
                        }
                    }
                    this.checksum.reset();
                    this.checksum.update(this.buffer, 0, this.originalLen);
                    if (((int) this.checksum.getValue()) != readIntLE2) {
                        throw new IOException("Stream is corrupted");
                    }
                    this.o = 0;
                    return;
                } else if (readIntLE2 != 0) {
                    throw new IOException("Stream is corrupted");
                } else {
                    if (this.stopOnEmptyBlock) {
                        this.finished = true;
                    } else {
                        refill();
                    }
                    return;
                }
            }
            throw new IOException("Stream is corrupted");
        } catch (EOFException e2) {
            if (this.stopOnEmptyBlock) {
                throw e2;
            }
            this.finished = true;
        }
    }

    private void readFully(byte[] bArr, int i) throws IOException {
        int i2 = 0;
        while (i2 < i) {
            int read = this.in.read(bArr, i2, i - i2);
            if (read < 0) {
                throw new EOFException("Stream ended prematurely");
            }
            i2 += read;
        }
    }

    public void reset() throws IOException {
        throw new IOException("mark/reset not supported");
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getClass().getSimpleName());
        stringBuilder.append("(in=");
        stringBuilder.append(this.in);
        stringBuilder.append(", decompressor=");
        stringBuilder.append(this.decompressor);
        stringBuilder.append(", checksum=");
        stringBuilder.append(this.checksum);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
