package net.jpountz.lz4;

import java.nio.ByteBuffer;
import net.jpountz.util.ByteBufferUtils;
import net.jpountz.util.SafeUtils;

final class LZ4JNIFastDecompressor extends LZ4FastDecompressor {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final LZ4JNIFastDecompressor INSTANCE = new LZ4JNIFastDecompressor();
    private static LZ4FastDecompressor SAFE_INSTANCE;

    LZ4JNIFastDecompressor() {
    }

    public final int decompress(byte[] bArr, int i, byte[] bArr2, int i2, int i3) {
        SafeUtils.checkRange(bArr, i);
        SafeUtils.checkRange(bArr2, i2, i3);
        int LZ4_decompress_fast = LZ4JNI.LZ4_decompress_fast(bArr, null, i, bArr2, null, i2, i3);
        if (LZ4_decompress_fast >= 0) {
            return LZ4_decompress_fast;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Error decoding offset ");
        stringBuilder.append(i - LZ4_decompress_fast);
        stringBuilder.append(" of input buffer");
        throw new LZ4Exception(stringBuilder.toString());
    }

    public int decompress(ByteBuffer byteBuffer, int i, ByteBuffer byteBuffer2, int i2, int i3) {
        ByteBufferUtils.checkNotReadOnly(byteBuffer2);
        ByteBufferUtils.checkRange(byteBuffer, i);
        ByteBufferUtils.checkRange(byteBuffer2, i2, i3);
        if ((byteBuffer.hasArray() || byteBuffer.isDirect()) && (byteBuffer2.hasArray() || byteBuffer2.isDirect())) {
            byte[] array;
            ByteBuffer byteBuffer3;
            byte[] array2;
            int arrayOffset;
            ByteBuffer byteBuffer4;
            if (byteBuffer.hasArray()) {
                i += byteBuffer.arrayOffset();
                array = byteBuffer.array();
                byteBuffer3 = null;
            } else {
                byteBuffer3 = byteBuffer;
                array = null;
            }
            if (byteBuffer2.hasArray()) {
                array2 = byteBuffer2.array();
                arrayOffset = i2 + byteBuffer2.arrayOffset();
                byteBuffer4 = null;
            } else {
                byteBuffer4 = byteBuffer2;
                arrayOffset = i2;
                array2 = null;
            }
            int LZ4_decompress_fast = LZ4JNI.LZ4_decompress_fast(array, byteBuffer3, i, array2, byteBuffer4, arrayOffset, i3);
            if (LZ4_decompress_fast >= 0) {
                return LZ4_decompress_fast;
            }
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Error decoding offset ");
            stringBuilder.append(i - LZ4_decompress_fast);
            stringBuilder.append(" of input buffer");
            throw new LZ4Exception(stringBuilder.toString());
        }
        LZ4FastDecompressor lZ4FastDecompressor = SAFE_INSTANCE;
        if (lZ4FastDecompressor == null) {
            lZ4FastDecompressor = LZ4Factory.safeInstance().fastDecompressor();
            SAFE_INSTANCE = lZ4FastDecompressor;
        }
        return lZ4FastDecompressor.decompress(byteBuffer, i, byteBuffer2, i2, i3);
    }
}
