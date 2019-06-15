package com.fasterxml.jackson.core.util;

import com.gaana.login.sso.SsoErrorCodes;

public class BufferRecycler {
    protected final byte[][] a = new byte[ByteBufferType.values().length][];
    protected final char[][] b = new char[CharBufferType.values().length][];

    public enum ByteBufferType {
        READ_IO_BUFFER(SsoErrorCodes.SDK_NOT_INITIALIZED),
        WRITE_ENCODING_BUFFER(SsoErrorCodes.SDK_NOT_INITIALIZED),
        WRITE_CONCAT_BUFFER(2000),
        BASE64_CODEC_BUFFER(2000);
        
        protected final int size;

        private ByteBufferType(int i) {
            this.size = i;
        }
    }

    public enum CharBufferType {
        TOKEN_BUFFER(2000),
        CONCAT_BUFFER(2000),
        TEXT_BUFFER(200),
        NAME_COPY_BUFFER(200);
        
        protected final int size;

        private CharBufferType(int i) {
            this.size = i;
        }
    }

    public final byte[] a(ByteBufferType byteBufferType) {
        int ordinal = byteBufferType.ordinal();
        byte[] bArr = this.a[ordinal];
        if (bArr == null) {
            return a(byteBufferType.size);
        }
        this.a[ordinal] = null;
        return bArr;
    }

    public final void a(ByteBufferType byteBufferType, byte[] bArr) {
        this.a[byteBufferType.ordinal()] = bArr;
    }

    public final char[] a(CharBufferType charBufferType) {
        return a(charBufferType, 0);
    }

    public final char[] a(CharBufferType charBufferType, int i) {
        if (charBufferType.size > i) {
            i = charBufferType.size;
        }
        int ordinal = charBufferType.ordinal();
        char[] cArr = this.b[ordinal];
        if (cArr == null || cArr.length < i) {
            return b(i);
        }
        this.b[ordinal] = null;
        return cArr;
    }

    public final void a(CharBufferType charBufferType, char[] cArr) {
        this.b[charBufferType.ordinal()] = cArr;
    }

    private byte[] a(int i) {
        return new byte[i];
    }

    private char[] b(int i) {
        return new char[i];
    }
}
