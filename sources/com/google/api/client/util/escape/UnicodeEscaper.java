package com.google.api.client.util.escape;

public abstract class UnicodeEscaper extends Escaper {
    private static final int DEST_PAD = 32;

    public abstract String escape(String str);

    public abstract char[] escape(int i);

    public abstract int nextEscapeIndex(CharSequence charSequence, int i, int i2);

    /* Access modifiers changed, original: protected|final */
    public final String escapeSlow(String str, int i) {
        int length = str.length();
        char[] charBufferFromThreadLocal = Platform.charBufferFromThreadLocal();
        int i2 = 0;
        int i3 = i2;
        while (i < length) {
            int codePointAt = codePointAt(str, i, length);
            if (codePointAt < 0) {
                throw new IllegalArgumentException("Trailing high surrogate at end of input");
            }
            char[] escape = escape(codePointAt);
            codePointAt = (Character.isSupplementaryCodePoint(codePointAt) ? 2 : 1) + i;
            if (escape != null) {
                int i4 = i - i2;
                int i5 = i3 + i4;
                int length2 = escape.length + i5;
                if (charBufferFromThreadLocal.length < length2) {
                    charBufferFromThreadLocal = growBuffer(charBufferFromThreadLocal, i3, ((length2 + length) - i) + 32);
                }
                if (i4 > 0) {
                    str.getChars(i2, i, charBufferFromThreadLocal, i3);
                    i3 = i5;
                }
                if (escape.length > 0) {
                    System.arraycopy(escape, 0, charBufferFromThreadLocal, i3, escape.length);
                    i3 += escape.length;
                }
                i2 = codePointAt;
            }
            i = nextEscapeIndex(str, codePointAt, length);
        }
        i = length - i2;
        if (i > 0) {
            i += i3;
            if (charBufferFromThreadLocal.length < i) {
                charBufferFromThreadLocal = growBuffer(charBufferFromThreadLocal, i3, i);
            }
            str.getChars(i2, length, charBufferFromThreadLocal, i3);
        } else {
            i = i3;
        }
        return new String(charBufferFromThreadLocal, 0, i);
    }

    protected static int codePointAt(CharSequence charSequence, int i, int i2) {
        if (i < i2) {
            int i3 = i + 1;
            char charAt = charSequence.charAt(i);
            if (charAt < 55296 || charAt > 57343) {
                return charAt;
            }
            StringBuilder stringBuilder;
            if (charAt > 56319) {
                i3--;
                stringBuilder = new StringBuilder(82);
                stringBuilder.append("Unexpected low surrogate character '");
                stringBuilder.append(charAt);
                stringBuilder.append("' with value ");
                stringBuilder.append(charAt);
                stringBuilder.append(" at index ");
                stringBuilder.append(i3);
                throw new IllegalArgumentException(stringBuilder.toString());
            } else if (i3 == i2) {
                return -charAt;
            } else {
                char charAt2 = charSequence.charAt(i3);
                if (Character.isLowSurrogate(charAt2)) {
                    return Character.toCodePoint(charAt, charAt2);
                }
                stringBuilder = new StringBuilder(83);
                stringBuilder.append("Expected low surrogate but got char '");
                stringBuilder.append(charAt2);
                stringBuilder.append("' with value ");
                stringBuilder.append(charAt2);
                stringBuilder.append(" at index ");
                stringBuilder.append(i3);
                throw new IllegalArgumentException(stringBuilder.toString());
            }
        }
        throw new IndexOutOfBoundsException("Index exceeds specified range");
    }

    private static char[] growBuffer(char[] cArr, int i, int i2) {
        char[] cArr2 = new char[i2];
        if (i > 0) {
            System.arraycopy(cArr, 0, cArr2, 0, i);
        }
        return cArr2;
    }
}
