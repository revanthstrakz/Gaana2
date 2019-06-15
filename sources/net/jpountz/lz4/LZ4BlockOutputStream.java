package net.jpountz.lz4;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.Checksum;
import net.jpountz.a.e;
import net.jpountz.util.SafeUtils;

public final class LZ4BlockOutputStream extends FilterOutputStream {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static final int COMPRESSION_LEVEL_BASE = 10;
    static final int COMPRESSION_METHOD_LZ4 = 32;
    static final int COMPRESSION_METHOD_RAW = 16;
    static final int DEFAULT_SEED = -1756908916;
    static final int HEADER_LENGTH = ((((MAGIC_LENGTH + 1) + 4) + 4) + 4);
    static final byte[] MAGIC = new byte[]{(byte) 76, (byte) 90, (byte) 52, (byte) 66, (byte) 108, (byte) 111, (byte) 99, (byte) 107};
    static final int MAGIC_LENGTH = MAGIC.length;
    static final int MAX_BLOCK_SIZE = 33554432;
    static final int MIN_BLOCK_SIZE = 64;
    private final int blockSize;
    private final byte[] buffer;
    private final Checksum checksum;
    private final byte[] compressedBuffer;
    private final int compressionLevel;
    private final LZ4Compressor compressor;
    private boolean finished;
    private int o;
    private final boolean syncFlush;

    private static int compressionLevel(int i) {
        StringBuilder stringBuilder;
        if (i < 64) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("blockSize must be >= 64, got ");
            stringBuilder.append(i);
            throw new IllegalArgumentException(stringBuilder.toString());
        } else if (i <= 33554432) {
            return Math.max(0, (32 - Integer.numberOfLeadingZeros(i - 1)) - 10);
        } else {
            stringBuilder = new StringBuilder();
            stringBuilder.append("blockSize must be <= 33554432, got ");
            stringBuilder.append(i);
            throw new IllegalArgumentException(stringBuilder.toString());
        }
    }

    public LZ4BlockOutputStream(OutputStream outputStream, int i, LZ4Compressor lZ4Compressor, Checksum checksum, boolean z) {
        super(outputStream);
        this.blockSize = i;
        this.compressor = lZ4Compressor;
        this.checksum = checksum;
        this.compressionLevel = compressionLevel(i);
        this.buffer = new byte[i];
        this.compressedBuffer = new byte[(HEADER_LENGTH + lZ4Compressor.maxCompressedLength(i))];
        this.syncFlush = z;
        this.o = 0;
        this.finished = false;
        System.arraycopy(MAGIC, 0, this.compressedBuffer, 0, MAGIC_LENGTH);
    }

    public LZ4BlockOutputStream(OutputStream outputStream, int i, LZ4Compressor lZ4Compressor) {
        this(outputStream, i, lZ4Compressor, e.e().a((int) DEFAULT_SEED).c(), false);
    }

    public LZ4BlockOutputStream(OutputStream outputStream, int i) {
        this(outputStream, i, LZ4Factory.fastestInstance().fastCompressor());
    }

    public LZ4BlockOutputStream(OutputStream outputStream) {
        this(outputStream, 65536);
    }

    private void ensureNotFinished() {
        if (this.finished) {
            throw new IllegalStateException("This stream is already closed");
        }
    }

    public void write(int i) throws IOException {
        ensureNotFinished();
        if (this.o == this.blockSize) {
            flushBufferedData();
        }
        byte[] bArr = this.buffer;
        int i2 = this.o;
        this.o = i2 + 1;
        bArr[i2] = (byte) i;
    }

    public void write(byte[] bArr, int i, int i2) throws IOException {
        SafeUtils.checkRange(bArr, i, i2);
        ensureNotFinished();
        while (this.o + i2 > this.blockSize) {
            int i3 = this.blockSize - this.o;
            System.arraycopy(bArr, i, this.buffer, this.o, this.blockSize - this.o);
            this.o = this.blockSize;
            flushBufferedData();
            i += i3;
            i2 -= i3;
        }
        System.arraycopy(bArr, i, this.buffer, this.o, i2);
        this.o += i2;
    }

    public void write(byte[] bArr) throws IOException {
        ensureNotFinished();
        write(bArr, 0, bArr.length);
    }

    public void close() throws IOException {
        if (!this.finished) {
            finish();
        }
        if (this.out != null) {
            this.out.close();
            this.out = null;
        }
    }

    private void flushBufferedData() throws IOException {
        if (this.o != 0) {
            int i;
            this.checksum.reset();
            this.checksum.update(this.buffer, 0, this.o);
            int value = (int) this.checksum.getValue();
            int compress = this.compressor.compress(this.buffer, 0, this.o, this.compressedBuffer, HEADER_LENGTH);
            if (compress >= this.o) {
                compress = 16;
                i = this.o;
                System.arraycopy(this.buffer, 0, this.compressedBuffer, HEADER_LENGTH, this.o);
            } else {
                i = compress;
                compress = 32;
            }
            this.compressedBuffer[MAGIC_LENGTH] = (byte) (compress | this.compressionLevel);
            writeIntLE(i, this.compressedBuffer, MAGIC_LENGTH + 1);
            writeIntLE(this.o, this.compressedBuffer, MAGIC_LENGTH + 5);
            writeIntLE(value, this.compressedBuffer, MAGIC_LENGTH + 9);
            this.out.write(this.compressedBuffer, 0, HEADER_LENGTH + i);
            this.o = 0;
        }
    }

    public void flush() throws IOException {
        if (this.out != null) {
            if (this.syncFlush) {
                flushBufferedData();
            }
            this.out.flush();
        }
    }

    public void finish() throws IOException {
        ensureNotFinished();
        flushBufferedData();
        this.compressedBuffer[MAGIC_LENGTH] = (byte) (this.compressionLevel | 16);
        writeIntLE(0, this.compressedBuffer, MAGIC_LENGTH + 1);
        writeIntLE(0, this.compressedBuffer, MAGIC_LENGTH + 5);
        writeIntLE(0, this.compressedBuffer, MAGIC_LENGTH + 9);
        this.out.write(this.compressedBuffer, 0, HEADER_LENGTH);
        this.finished = true;
        this.out.flush();
    }

    private static void writeIntLE(int i, byte[] bArr, int i2) {
        int i3 = i2 + 1;
        bArr[i2] = (byte) i;
        i2 = i3 + 1;
        bArr[i3] = (byte) (i >>> 8);
        i3 = i2 + 1;
        bArr[i2] = (byte) (i >>> 16);
        bArr[i3] = (byte) (i >>> 24);
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getClass().getSimpleName());
        stringBuilder.append("(out=");
        stringBuilder.append(this.out);
        stringBuilder.append(", blockSize=");
        stringBuilder.append(this.blockSize);
        stringBuilder.append(", compressor=");
        stringBuilder.append(this.compressor);
        stringBuilder.append(", checksum=");
        stringBuilder.append(this.checksum);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
