package net.jpountz.lz4;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Locale;
import net.jpountz.a.c;
import net.jpountz.a.e;
import net.jpountz.lz4.LZ4FrameOutputStream.BD;
import net.jpountz.lz4.LZ4FrameOutputStream.FLG;
import net.jpountz.lz4.LZ4FrameOutputStream.FLG.Bits;

public class LZ4FrameInputStream extends FilterInputStream {
    static final String BLOCK_HASH_MISMATCH = "Block checksum mismatch";
    static final String DESCRIPTOR_HASH_MISMATCH = "Stream frame descriptor corrupted";
    static final int MAGIC_SKIPPABLE_BASE = 407710288;
    static final String NOT_SUPPORTED = "Stream unsupported";
    static final String PREMATURE_EOS = "Stream ended prematurely";
    private ByteBuffer buffer;
    private final c checksum;
    private byte[] compressedBuffer;
    private final LZ4SafeDecompressor decompressor;
    private long expectedContentSize;
    private FrameInfo frameInfo;
    private final byte[] headerArray;
    private final ByteBuffer headerBuffer;
    private int maxBlockSize;
    private byte[] rawBuffer;
    private final ByteBuffer readNumberBuff;
    private long totalContentSize;

    public boolean markSupported() {
        return false;
    }

    public LZ4FrameInputStream(InputStream inputStream) throws IOException {
        this(inputStream, LZ4Factory.fastestInstance().safeDecompressor(), e.e().f());
    }

    public LZ4FrameInputStream(InputStream inputStream, LZ4SafeDecompressor lZ4SafeDecompressor, c cVar) throws IOException {
        super(inputStream);
        this.headerArray = new byte[15];
        this.headerBuffer = ByteBuffer.wrap(this.headerArray).order(ByteOrder.LITTLE_ENDIAN);
        this.buffer = null;
        this.rawBuffer = null;
        this.maxBlockSize = -1;
        this.expectedContentSize = -1;
        this.totalContentSize = 0;
        this.frameInfo = null;
        this.readNumberBuff = ByteBuffer.allocate(8).order(ByteOrder.LITTLE_ENDIAN);
        this.decompressor = lZ4SafeDecompressor;
        this.checksum = cVar;
        nextFrameInfo();
    }

    private boolean nextFrameInfo() throws IOException {
        while (true) {
            int i = 0;
            do {
                int read = this.in.read(this.readNumberBuff.array(), i, 4 - i);
                if (read < 0) {
                    return false;
                }
                i += read;
            } while (i < 4);
            int i2 = this.readNumberBuff.getInt(0);
            if (i2 == 407708164) {
                readHeader();
                return true;
            } else if ((i2 >>> 4) == 25481893) {
                skippableFrame();
            } else {
                throw new IOException(NOT_SUPPORTED);
            }
        }
    }

    private void skippableFrame() throws IOException {
        int readInt = readInt(this.in);
        byte[] bArr = new byte[1024];
        while (readInt > 0) {
            int read = this.in.read(bArr, 0, Math.min(readInt, bArr.length));
            if (read < 0) {
                throw new IOException(PREMATURE_EOS);
            }
            readInt -= read;
        }
    }

    private void readHeader() throws IOException {
        this.headerBuffer.rewind();
        int read = this.in.read();
        if (read < 0) {
            throw new IOException(PREMATURE_EOS);
        }
        int read2 = this.in.read();
        if (read2 < 0) {
            throw new IOException(PREMATURE_EOS);
        }
        byte b = (byte) (read & 255);
        FLG fromByte = FLG.fromByte(b);
        this.headerBuffer.put(b);
        b = (byte) (read2 & 255);
        BD fromByte2 = BD.fromByte(b);
        this.headerBuffer.put(b);
        this.frameInfo = new FrameInfo(fromByte, fromByte2);
        if (fromByte.isEnabled(Bits.CONTENT_SIZE)) {
            this.expectedContentSize = readLong(this.in);
            this.headerBuffer.putLong(this.expectedContentSize);
        }
        this.totalContentSize = 0;
        b = (byte) ((this.checksum.a(this.headerArray, 0, this.headerBuffer.position(), 0) >> 8) & 255);
        read2 = this.in.read();
        if (read2 < 0) {
            throw new IOException(PREMATURE_EOS);
        } else if (b != ((byte) (read2 & 255))) {
            throw new IOException(DESCRIPTOR_HASH_MISMATCH);
        } else {
            this.maxBlockSize = this.frameInfo.getBD().getBlockMaximumSize();
            this.compressedBuffer = new byte[this.maxBlockSize];
            this.rawBuffer = new byte[this.maxBlockSize];
            this.buffer = ByteBuffer.wrap(this.rawBuffer);
            this.buffer.limit(0);
        }
    }

    private long readLong(InputStream inputStream) throws IOException {
        int i = 0;
        do {
            int read = inputStream.read(this.readNumberBuff.array(), i, 8 - i);
            if (read < 0) {
                throw new IOException(PREMATURE_EOS);
            }
            i += read;
        } while (i < 8);
        return this.readNumberBuff.getLong(0);
    }

    private int readInt(InputStream inputStream) throws IOException {
        int i = 0;
        do {
            int read = inputStream.read(this.readNumberBuff.array(), i, 4 - i);
            if (read < 0) {
                throw new IOException(PREMATURE_EOS);
            }
            i += read;
        } while (i < 4);
        return this.readNumberBuff.getInt(0);
    }

    private void readBlock() throws IOException {
        int readInt = readInt(this.in);
        int i = (Integer.MIN_VALUE & readInt) == 0 ? 1 : 0;
        int i2 = readInt & Integer.MAX_VALUE;
        if (i2 != 0) {
            byte[] bArr;
            if (i != 0) {
                bArr = this.compressedBuffer;
            } else {
                bArr = this.rawBuffer;
            }
            byte[] bArr2 = bArr;
            if (i2 > this.maxBlockSize) {
                throw new IOException(String.format(Locale.ROOT, "Block size %s exceeded max: %s", new Object[]{Integer.valueOf(i2), Integer.valueOf(this.maxBlockSize)}));
            }
            readInt = 0;
            while (readInt < i2) {
                int read = this.in.read(bArr2, readInt, i2 - readInt);
                if (read < 0) {
                    throw new IOException(PREMATURE_EOS);
                }
                readInt += read;
            }
            if (!this.frameInfo.isEnabled(Bits.BLOCK_CHECKSUM) || readInt(this.in) == this.checksum.a(bArr2, 0, i2, 0)) {
                if (i != 0) {
                    try {
                        i2 = this.decompressor.decompress(bArr2, 0, i2, this.rawBuffer, 0, this.rawBuffer.length);
                    } catch (LZ4Exception e) {
                        throw new IOException(e);
                    }
                }
                if (this.frameInfo.isEnabled(Bits.CONTENT_CHECKSUM)) {
                    this.frameInfo.updateStreamHash(this.rawBuffer, 0, i2);
                }
                this.totalContentSize += (long) i2;
                this.buffer.limit(i2);
                this.buffer.rewind();
                return;
            }
            throw new IOException(BLOCK_HASH_MISMATCH);
        } else if (this.frameInfo.isEnabled(Bits.CONTENT_CHECKSUM) && readInt(this.in) != this.frameInfo.currentStreamHash()) {
            throw new IOException("Content checksum mismatch");
        } else if (!this.frameInfo.isEnabled(Bits.CONTENT_SIZE) || this.expectedContentSize == this.totalContentSize) {
            this.frameInfo.finish();
        } else {
            throw new IOException("Size check mismatch");
        }
    }

    public int read() throws IOException {
        while (this.buffer.remaining() == 0) {
            if (this.frameInfo.isFinished() && !nextFrameInfo()) {
                return -1;
            }
            readBlock();
        }
        return this.buffer.get();
    }

    public int read(byte[] bArr, int i, int i2) throws IOException {
        if (i < 0 || i2 < 0 || i + i2 > bArr.length) {
            throw new IndexOutOfBoundsException();
        }
        while (this.buffer.remaining() == 0) {
            if (this.frameInfo.isFinished() && !nextFrameInfo()) {
                return -1;
            }
            readBlock();
        }
        i2 = Math.min(i2, this.buffer.remaining());
        this.buffer.get(bArr, i, i2);
        return i2;
    }

    public long skip(long j) throws IOException {
        while (this.buffer.remaining() == 0) {
            if (this.frameInfo.isFinished() && !nextFrameInfo()) {
                return 0;
            }
            readBlock();
        }
        j = Math.min(j, (long) this.buffer.remaining());
        this.buffer.position(this.buffer.position() + ((int) j));
        return j;
    }

    public int available() throws IOException {
        return this.buffer.remaining();
    }

    public void close() throws IOException {
        super.close();
    }

    public synchronized void mark(int i) {
        throw new UnsupportedOperationException("mark not supported");
    }

    public synchronized void reset() throws IOException {
        throw new UnsupportedOperationException("reset not supported");
    }
}
