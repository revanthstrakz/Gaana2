package com.google.flatbuffers;

import java.nio.ByteBuffer;

public class ByteBufferUtil {
    public static int getSizePrefix(ByteBuffer byteBuffer) {
        return byteBuffer.getInt(byteBuffer.position());
    }

    public static ByteBuffer removeSizePrefix(ByteBuffer byteBuffer) {
        byteBuffer = byteBuffer.duplicate();
        byteBuffer.position(byteBuffer.position() + 4);
        return byteBuffer;
    }
}
