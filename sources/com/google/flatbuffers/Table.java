package com.google.flatbuffers;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CoderResult;
import java.util.Arrays;
import java.util.Comparator;

public class Table {
    private static final ThreadLocal<CharBuffer> CHAR_BUFFER = new ThreadLocal();
    public static final ThreadLocal<Charset> UTF8_CHARSET = new ThreadLocal<Charset>() {
        /* Access modifiers changed, original: protected */
        public Charset initialValue() {
            return Charset.forName("UTF-8");
        }
    };
    private static final ThreadLocal<CharsetDecoder> UTF8_DECODER = new ThreadLocal<CharsetDecoder>() {
        /* Access modifiers changed, original: protected */
        public CharsetDecoder initialValue() {
            return Charset.forName("UTF-8").newDecoder();
        }
    };
    protected ByteBuffer bb;
    protected int bb_pos;

    /* Access modifiers changed, original: protected */
    public int keysCompare(Integer num, Integer num2, ByteBuffer byteBuffer) {
        return 0;
    }

    public ByteBuffer getByteBuffer() {
        return this.bb;
    }

    /* Access modifiers changed, original: protected */
    public int __offset(int i) {
        int i2 = this.bb_pos - this.bb.getInt(this.bb_pos);
        return i < this.bb.getShort(i2) ? this.bb.getShort(i2 + i) : 0;
    }

    protected static int __offset(int i, int i2, ByteBuffer byteBuffer) {
        int capacity = byteBuffer.capacity() - i2;
        return byteBuffer.getShort((i + capacity) - byteBuffer.getInt(capacity)) + capacity;
    }

    /* Access modifiers changed, original: protected */
    public int __indirect(int i) {
        return i + this.bb.getInt(i);
    }

    protected static int __indirect(int i, ByteBuffer byteBuffer) {
        return i + byteBuffer.getInt(i);
    }

    /* Access modifiers changed, original: protected */
    public String __string(int i) {
        CharsetDecoder charsetDecoder = (CharsetDecoder) UTF8_DECODER.get();
        charsetDecoder.reset();
        i += this.bb.getInt(i);
        ByteBuffer order = this.bb.duplicate().order(ByteOrder.LITTLE_ENDIAN);
        int i2 = order.getInt(i);
        i += 4;
        order.position(i);
        order.limit(i + i2);
        i = (int) (((float) i2) * charsetDecoder.maxCharsPerByte());
        CharBuffer charBuffer = (CharBuffer) CHAR_BUFFER.get();
        if (charBuffer == null || charBuffer.capacity() < i) {
            charBuffer = CharBuffer.allocate(i);
            CHAR_BUFFER.set(charBuffer);
        }
        charBuffer.clear();
        try {
            CoderResult decode = charsetDecoder.decode(order, charBuffer, true);
            if (!decode.isUnderflow()) {
                decode.throwException();
            }
            return charBuffer.flip().toString();
        } catch (CharacterCodingException e) {
            throw new RuntimeException(e);
        }
    }

    /* Access modifiers changed, original: protected */
    public int __vector_len(int i) {
        i += this.bb_pos;
        return this.bb.getInt(i + this.bb.getInt(i));
    }

    /* Access modifiers changed, original: protected */
    public int __vector(int i) {
        i += this.bb_pos;
        return (i + this.bb.getInt(i)) + 4;
    }

    /* Access modifiers changed, original: protected */
    public ByteBuffer __vector_as_bytebuffer(int i, int i2) {
        i = __offset(i);
        if (i == 0) {
            return null;
        }
        ByteBuffer order = this.bb.duplicate().order(ByteOrder.LITTLE_ENDIAN);
        int __vector = __vector(i);
        order.position(__vector);
        order.limit(__vector + (__vector_len(i) * i2));
        return order;
    }

    /* Access modifiers changed, original: protected */
    public ByteBuffer __vector_in_bytebuffer(ByteBuffer byteBuffer, int i, int i2) {
        i = __offset(i);
        if (i == 0) {
            return null;
        }
        int __vector = __vector(i);
        byteBuffer.rewind();
        byteBuffer.limit((__vector_len(i) * i2) + __vector);
        byteBuffer.position(__vector);
        return byteBuffer;
    }

    /* Access modifiers changed, original: protected */
    public Table __union(Table table, int i) {
        i += this.bb_pos;
        table.bb_pos = i + this.bb.getInt(i);
        table.bb = this.bb;
        return table;
    }

    protected static boolean __has_identifier(ByteBuffer byteBuffer, String str) {
        if (str.length() != 4) {
            throw new AssertionError("FlatBuffers: file identifier must be length 4");
        }
        for (int i = 0; i < 4; i++) {
            if (str.charAt(i) != ((char) byteBuffer.get((byteBuffer.position() + 4) + i))) {
                return false;
            }
        }
        return true;
    }

    /* Access modifiers changed, original: protected */
    public void sortTables(int[] iArr, final ByteBuffer byteBuffer) {
        int i = 0;
        Integer[] numArr = new Integer[iArr.length];
        for (int i2 = 0; i2 < iArr.length; i2++) {
            numArr[i2] = Integer.valueOf(iArr[i2]);
        }
        Arrays.sort(numArr, new Comparator<Integer>() {
            public int compare(Integer num, Integer num2) {
                return Table.this.keysCompare(num, num2, byteBuffer);
            }
        });
        while (i < iArr.length) {
            iArr[i] = numArr[i].intValue();
            i++;
        }
    }

    protected static int compareStrings(int i, int i2, ByteBuffer byteBuffer) {
        i += byteBuffer.getInt(i);
        i2 += byteBuffer.getInt(i2);
        int i3 = byteBuffer.getInt(i);
        int i4 = byteBuffer.getInt(i2);
        i += 4;
        i2 += 4;
        int min = Math.min(i3, i4);
        for (int i5 = 0; i5 < min; i5++) {
            int i6 = i5 + i;
            int i7 = i5 + i2;
            if (byteBuffer.get(i6) != byteBuffer.get(i7)) {
                return byteBuffer.get(i6) - byteBuffer.get(i7);
            }
        }
        return i3 - i4;
    }

    protected static int compareStrings(int i, byte[] bArr, ByteBuffer byteBuffer) {
        i += byteBuffer.getInt(i);
        int i2 = byteBuffer.getInt(i);
        int length = bArr.length;
        i += 4;
        int min = Math.min(i2, length);
        for (int i3 = 0; i3 < min; i3++) {
            int i4 = i3 + i;
            if (byteBuffer.get(i4) != bArr[i3]) {
                return byteBuffer.get(i4) - bArr[i3];
            }
        }
        return i2 - length;
    }
}
