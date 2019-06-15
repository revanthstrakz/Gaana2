package net.jpountz.lz4;

import java.nio.ByteBuffer;
import net.jpountz.util.ByteBufferUtils;
import net.jpountz.util.SafeUtils;

final class LZ4JNISafeDecompressor extends LZ4SafeDecompressor {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final LZ4JNISafeDecompressor INSTANCE = new LZ4JNISafeDecompressor();
    private static LZ4SafeDecompressor SAFE_INSTANCE;

    LZ4JNISafeDecompressor() {
    }

    public final int decompress(byte[] bArr, int i, int i2, byte[] bArr2, int i3, int i4) {
        SafeUtils.checkRange(bArr, i, i2);
        SafeUtils.checkRange(bArr2, i3, i4);
        int LZ4_decompress_safe = LZ4JNI.LZ4_decompress_safe(bArr, null, i, i2, bArr2, null, i3, i4);
        if (LZ4_decompress_safe >= 0) {
            return LZ4_decompress_safe;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Error decoding offset ");
        stringBuilder.append(i - LZ4_decompress_safe);
        stringBuilder.append(" of input buffer");
        throw new LZ4Exception(stringBuilder.toString());
    }

    public int decompress(ByteBuffer byteBuffer, int i, int i2, ByteBuffer byteBuffer2, int i3, int i4) {
        ByteBufferUtils.checkNotReadOnly(byteBuffer2);
        ByteBufferUtils.checkRange(byteBuffer, i, i2);
        ByteBufferUtils.checkRange(byteBuffer2, i3, i4);
        if ((byteBuffer.hasArray() || byteBuffer.isDirect()) && (byteBuffer2.hasArray() || byteBuffer2.isDirect())) {
            int arrayOffset;
            byte[] array;
            ByteBuffer byteBuffer3;
            byte[] array2;
            ByteBuffer byteBuffer4;
            int arrayOffset2;
            if (byteBuffer.hasArray()) {
                arrayOffset = byteBuffer.arrayOffset() + i;
                array = byteBuffer.array();
                byteBuffer3 = null;
            } else {
                byteBuffer3 = byteBuffer;
                arrayOffset = i;
                array = null;
            }
            if (byteBuffer2.hasArray()) {
                array2 = byteBuffer2.array();
                byteBuffer4 = null;
                arrayOffset2 = i3 + byteBuffer2.arrayOffset();
            } else {
                byteBuffer4 = byteBuffer2;
                arrayOffset2 = i3;
                array2 = null;
            }
            int LZ4_decompress_safe = LZ4JNI.LZ4_decompress_safe(array, byteBuffer3, arrayOffset, i2, array2, byteBuffer4, arrayOffset2, i4);
            if (LZ4_decompress_safe >= 0) {
                return LZ4_decompress_safe;
            }
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Error decoding offset ");
            stringBuilder.append(arrayOffset - LZ4_decompress_safe);
            stringBuilder.append(" of input buffer");
            throw new LZ4Exception(stringBuilder.toString());
        }
        LZ4SafeDecompressor lZ4SafeDecompressor = SAFE_INSTANCE;
        if (lZ4SafeDecompressor == null) {
            lZ4SafeDecompressor = LZ4Factory.safeInstance().safeDecompressor();
            SAFE_INSTANCE = lZ4SafeDecompressor;
        }
        return lZ4SafeDecompressor.decompress(byteBuffer, i, i2, byteBuffer2, i3, i4);
    }
}
