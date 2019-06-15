package net.jpountz.lz4;

import com.google.android.exoplayer2.extractor.ts.PsExtractor;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Locale;
import net.jpountz.a.a;
import net.jpountz.a.c;
import net.jpountz.a.e;

public class LZ4FrameOutputStream extends FilterOutputStream {
    static final String CLOSED_STREAM = "The stream is already closed";
    static final Bits[] DEFAULT_FEATURES = new Bits[]{Bits.BLOCK_INDEPENDENCE};
    static final int INTEGER_BYTES = 4;
    static final int LONG_BYTES = 8;
    static final int LZ4_FRAME_INCOMPRESSIBLE_MASK = Integer.MIN_VALUE;
    static final int LZ4_MAX_HEADER_LENGTH = 15;
    static final int MAGIC = 407708164;
    private final ByteBuffer buffer;
    private final c checksum;
    private final byte[] compressedBuffer;
    private final LZ4Compressor compressor;
    private FrameInfo frameInfo;
    private final ByteBuffer intLEBuffer;
    private final long knownSize;
    private final int maxBlockSize;

    public static class BD {
        private static final int RESERVED_MASK = 143;
        private final BLOCKSIZE blockSizeValue;

        private BD(BLOCKSIZE blocksize) {
            this.blockSizeValue = blocksize;
        }

        public static BD fromByte(byte b) {
            int i = (b >>> 4) & 7;
            if ((b & RESERVED_MASK) <= 0) {
                return new BD(BLOCKSIZE.valueOf(i));
            }
            throw new RuntimeException("Reserved fields must be 0");
        }

        public int getBlockMaximumSize() {
            return 1 << ((2 * this.blockSizeValue.getIndicator()) + 8);
        }

        public byte toByte() {
            return (byte) ((this.blockSizeValue.getIndicator() & 7) << 4);
        }
    }

    public enum BLOCKSIZE {
        SIZE_64KB(4),
        SIZE_256KB(5),
        SIZE_1MB(6),
        SIZE_4MB(7);
        
        private final int indicator;

        private BLOCKSIZE(int i) {
            this.indicator = i;
        }

        public int getIndicator() {
            return this.indicator;
        }

        public static BLOCKSIZE valueOf(int i) {
            switch (i) {
                case 4:
                    return SIZE_64KB;
                case 5:
                    return SIZE_256KB;
                case 6:
                    return SIZE_1MB;
                case 7:
                    return SIZE_4MB;
                default:
                    throw new IllegalArgumentException(String.format(Locale.ROOT, "Block size must be 4-7. Cannot use value of [%d]", new Object[]{Integer.valueOf(i)}));
            }
        }
    }

    public static class FLG {
        private static final int DEFAULT_VERSION = 1;
        private final BitSet bitSet;
        private final int version;

        public enum Bits {
            RESERVED_0(0),
            RESERVED_1(1),
            CONTENT_CHECKSUM(2),
            CONTENT_SIZE(3),
            BLOCK_CHECKSUM(4),
            BLOCK_INDEPENDENCE(5);
            
            private final int position;

            private Bits(int i) {
                this.position = i;
            }
        }

        public FLG(int i, Bits... bitsArr) {
            this.bitSet = new BitSet(8);
            this.version = i;
            if (bitsArr != null) {
                for (Bits access$100 : bitsArr) {
                    this.bitSet.set(access$100.position);
                }
            }
            validate();
        }

        private FLG(int i, byte b) {
            this.bitSet = BitSet.valueOf(new byte[]{b});
            this.version = i;
            validate();
        }

        public static FLG fromByte(byte b) {
            byte b2 = (byte) (b & PsExtractor.AUDIO_STREAM);
            return new FLG(b2 >>> 6, (byte) (b ^ b2));
        }

        public byte toByte() {
            return (byte) (this.bitSet.toByteArray()[0] | ((this.version & 3) << 6));
        }

        private void validate() {
            if (this.bitSet.get(Bits.RESERVED_0.position)) {
                throw new RuntimeException("Reserved0 field must be 0");
            } else if (this.bitSet.get(Bits.RESERVED_1.position)) {
                throw new RuntimeException("Reserved1 field must be 0");
            } else if (!this.bitSet.get(Bits.BLOCK_INDEPENDENCE.position)) {
                throw new RuntimeException("Dependent block stream is unsupported (BLOCK_INDEPENDENCE must be set)");
            } else if (this.version != 1) {
                throw new RuntimeException(String.format(Locale.ROOT, "Version %d is unsupported", new Object[]{Integer.valueOf(this.version)}));
            }
        }

        public boolean isEnabled(Bits bits) {
            return this.bitSet.get(bits.position);
        }

        public int getVersion() {
            return this.version;
        }
    }

    static class FrameInfo {
        private final BD bd;
        private boolean finished = false;
        private final FLG flg;
        private final a streamHash;

        public FrameInfo(FLG flg, BD bd) {
            this.flg = flg;
            this.bd = bd;
            this.streamHash = flg.isEnabled(Bits.CONTENT_CHECKSUM) ? e.e().a(0) : null;
        }

        public boolean isEnabled(Bits bits) {
            return this.flg.isEnabled(bits);
        }

        public FLG getFLG() {
            return this.flg;
        }

        public BD getBD() {
            return this.bd;
        }

        public void updateStreamHash(byte[] bArr, int i, int i2) {
            this.streamHash.a(bArr, i, i2);
        }

        public int currentStreamHash() {
            return this.streamHash.a();
        }

        public void finish() {
            this.finished = true;
        }

        public boolean isFinished() {
            return this.finished;
        }
    }

    public LZ4FrameOutputStream(OutputStream outputStream, BLOCKSIZE blocksize, Bits... bitsArr) throws IOException {
        this(outputStream, blocksize, -1, bitsArr);
    }

    public LZ4FrameOutputStream(OutputStream outputStream, BLOCKSIZE blocksize, long j, Bits... bitsArr) throws IOException {
        super(outputStream);
        this.intLEBuffer = ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN);
        this.frameInfo = null;
        this.compressor = LZ4Factory.fastestInstance().fastCompressor();
        this.checksum = e.e().f();
        this.frameInfo = new FrameInfo(new FLG(1, bitsArr), new BD(blocksize));
        this.maxBlockSize = this.frameInfo.getBD().getBlockMaximumSize();
        this.buffer = ByteBuffer.allocate(this.maxBlockSize).order(ByteOrder.LITTLE_ENDIAN);
        this.compressedBuffer = new byte[this.compressor.maxCompressedLength(this.maxBlockSize)];
        if (!this.frameInfo.getFLG().isEnabled(Bits.CONTENT_SIZE) || j >= 0) {
            this.knownSize = j;
            writeHeader();
            return;
        }
        throw new IllegalArgumentException("Known size must be greater than zero in order to use the known size feature");
    }

    public LZ4FrameOutputStream(OutputStream outputStream, BLOCKSIZE blocksize) throws IOException {
        this(outputStream, blocksize, DEFAULT_FEATURES);
    }

    public LZ4FrameOutputStream(OutputStream outputStream) throws IOException {
        this(outputStream, BLOCKSIZE.SIZE_4MB);
    }

    private void writeHeader() throws IOException {
        ByteBuffer order = ByteBuffer.allocate(15).order(ByteOrder.LITTLE_ENDIAN);
        order.putInt(MAGIC);
        order.put(this.frameInfo.getFLG().toByte());
        order.put(this.frameInfo.getBD().toByte());
        if (this.frameInfo.isEnabled(Bits.CONTENT_SIZE)) {
            order.putLong(this.knownSize);
        }
        order.put((byte) ((this.checksum.a(order.array(), 4, order.position() - 4, 0) >> 8) & 255));
        this.out.write(order.array(), 0, order.position());
    }

    private void writeBlock() throws IOException {
        if (this.buffer.position() != 0) {
            byte[] copyOf;
            int i;
            Arrays.fill(this.compressedBuffer, (byte) 0);
            int compress = this.compressor.compress(this.buffer.array(), 0, this.buffer.position(), this.compressedBuffer, 0);
            if (compress >= this.buffer.position()) {
                compress = this.buffer.position();
                copyOf = Arrays.copyOf(this.buffer.array(), compress);
                i = Integer.MIN_VALUE;
            } else {
                copyOf = this.compressedBuffer;
                i = 0;
            }
            this.intLEBuffer.putInt(0, i | compress);
            this.out.write(this.intLEBuffer.array());
            this.out.write(copyOf, 0, compress);
            if (this.frameInfo.isEnabled(Bits.BLOCK_CHECKSUM)) {
                this.intLEBuffer.putInt(0, this.checksum.a(copyOf, 0, compress, 0));
                this.out.write(this.intLEBuffer.array());
            }
            this.buffer.rewind();
        }
    }

    private void writeEndMark() throws IOException {
        this.intLEBuffer.putInt(0, 0);
        this.out.write(this.intLEBuffer.array());
        if (this.frameInfo.isEnabled(Bits.CONTENT_CHECKSUM)) {
            this.intLEBuffer.putInt(0, this.frameInfo.currentStreamHash());
            this.out.write(this.intLEBuffer.array());
        }
        this.frameInfo.finish();
    }

    public void write(int i) throws IOException {
        ensureNotFinished();
        if (this.buffer.position() == this.maxBlockSize) {
            writeBlock();
        }
        this.buffer.put((byte) i);
        if (this.frameInfo.isEnabled(Bits.CONTENT_CHECKSUM)) {
            this.frameInfo.updateStreamHash(new byte[]{r5}, 0, 1);
        }
    }

    public void write(byte[] bArr, int i, int i2) throws IOException {
        if (i < 0 || i2 < 0 || i + i2 > bArr.length) {
            throw new IndexOutOfBoundsException();
        }
        ensureNotFinished();
        while (i2 > this.buffer.remaining()) {
            int remaining = this.buffer.remaining();
            this.buffer.put(bArr, i, remaining);
            if (this.frameInfo.isEnabled(Bits.CONTENT_CHECKSUM)) {
                this.frameInfo.updateStreamHash(bArr, i, remaining);
            }
            writeBlock();
            i += remaining;
            i2 -= remaining;
        }
        this.buffer.put(bArr, i, i2);
        if (this.frameInfo.isEnabled(Bits.CONTENT_CHECKSUM)) {
            this.frameInfo.updateStreamHash(bArr, i, i2);
        }
    }

    public void flush() throws IOException {
        if (!this.frameInfo.isFinished()) {
            writeBlock();
        }
        super.flush();
    }

    private void ensureNotFinished() {
        if (this.frameInfo.isFinished()) {
            throw new IllegalStateException(CLOSED_STREAM);
        }
    }

    public void close() throws IOException {
        if (!this.frameInfo.isFinished()) {
            flush();
            writeEndMark();
        }
        super.close();
    }
}
