package com.google.android.exoplayer2.decoder;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.nio.ByteBuffer;

public class DecoderInputBuffer extends Buffer {
    public static final int BUFFER_REPLACEMENT_MODE_DIRECT = 2;
    public static final int BUFFER_REPLACEMENT_MODE_DISABLED = 0;
    public static final int BUFFER_REPLACEMENT_MODE_NORMAL = 1;
    private final int bufferReplacementMode;
    public final CryptoInfo cryptoInfo = new CryptoInfo();
    public ByteBuffer data;
    public long timeUs;

    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface BufferReplacementMode {
    }

    public static DecoderInputBuffer newFlagsOnlyInstance() {
        return new DecoderInputBuffer(0);
    }

    public DecoderInputBuffer(int i) {
        this.bufferReplacementMode = i;
    }

    public void ensureSpaceForWrite(int i) {
        if (this.data == null) {
            this.data = createReplacementByteBuffer(i);
            return;
        }
        int capacity = this.data.capacity();
        int position = this.data.position();
        i += position;
        if (capacity < i) {
            ByteBuffer createReplacementByteBuffer = createReplacementByteBuffer(i);
            if (position > 0) {
                this.data.position(0);
                this.data.limit(position);
                createReplacementByteBuffer.put(this.data);
            }
            this.data = createReplacementByteBuffer;
        }
    }

    public final boolean isFlagsOnly() {
        return this.data == null && this.bufferReplacementMode == 0;
    }

    public final boolean isEncrypted() {
        return getFlag(1073741824);
    }

    public final void flip() {
        this.data.flip();
    }

    public void clear() {
        super.clear();
        if (this.data != null) {
            this.data.clear();
        }
    }

    private ByteBuffer createReplacementByteBuffer(int i) {
        if (this.bufferReplacementMode == 1) {
            return ByteBuffer.allocate(i);
        }
        if (this.bufferReplacementMode == 2) {
            return ByteBuffer.allocateDirect(i);
        }
        int capacity = this.data == null ? 0 : this.data.capacity();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Buffer too small (");
        stringBuilder.append(capacity);
        stringBuilder.append(" < ");
        stringBuilder.append(i);
        stringBuilder.append(")");
        throw new IllegalStateException(stringBuilder.toString());
    }
}
