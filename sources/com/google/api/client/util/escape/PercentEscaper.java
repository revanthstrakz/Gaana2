package com.google.api.client.util.escape;

import android.support.v4.internal.view.SupportMenu;

public class PercentEscaper extends UnicodeEscaper {
    public static final String SAFECHARS_URLENCODER = "-_.*";
    public static final String SAFEPATHCHARS_URLENCODER = "-_.!~*'()@:$&,;=";
    public static final String SAFEQUERYSTRINGCHARS_URLENCODER = "-_.!~*'()@:$,;/?:";
    public static final String SAFEUSERINFOCHARS_URLENCODER = "-_.!~*'():$&,;=";
    public static final String SAFE_PLUS_RESERVED_CHARS_URLENCODER = "-_.!~*'()@:$&,;=+/?";
    private static final char[] UPPER_HEX_DIGITS = "0123456789ABCDEF".toCharArray();
    private static final char[] URI_ESCAPED_SPACE = new char[]{'+'};
    private final boolean plusForSpace;
    private final boolean[] safeOctets;

    public PercentEscaper(String str, boolean z) {
        if (str.matches(".*[0-9A-Za-z].*")) {
            throw new IllegalArgumentException("Alphanumeric characters are always 'safe' and should not be explicitly specified");
        } else if (z && str.contains(" ")) {
            throw new IllegalArgumentException("plusForSpace cannot be specified when space is a 'safe' character");
        } else if (str.contains("%")) {
            throw new IllegalArgumentException("The '%' character cannot be specified as 'safe'");
        } else {
            this.plusForSpace = z;
            this.safeOctets = createSafeOctets(str);
        }
    }

    private static boolean[] createSafeOctets(String str) {
        char[] toCharArray = str.toCharArray();
        int i = 0;
        int i2 = 122;
        for (char max : toCharArray) {
            i2 = Math.max(max, i2);
        }
        boolean[] zArr = new boolean[(i2 + 1)];
        for (i2 = 48; i2 <= 57; i2++) {
            zArr[i2] = true;
        }
        for (i2 = 65; i2 <= 90; i2++) {
            zArr[i2] = true;
        }
        for (i2 = 97; i2 <= 122; i2++) {
            zArr[i2] = true;
        }
        int length = toCharArray.length;
        while (i < length) {
            zArr[toCharArray[i]] = true;
            i++;
        }
        return zArr;
    }

    /* Access modifiers changed, original: protected */
    public int nextEscapeIndex(CharSequence charSequence, int i, int i2) {
        while (i < i2) {
            char charAt = charSequence.charAt(i);
            if (charAt >= this.safeOctets.length || !this.safeOctets[charAt]) {
                break;
            }
            i++;
        }
        return i;
    }

    public String escape(String str) {
        int length = str.length();
        for (int i = 0; i < length; i++) {
            char charAt = str.charAt(i);
            if (charAt >= this.safeOctets.length || !this.safeOctets[charAt]) {
                return escapeSlow(str, i);
            }
        }
        return str;
    }

    /* Access modifiers changed, original: protected */
    public char[] escape(int i) {
        if (i < this.safeOctets.length && this.safeOctets[i]) {
            return null;
        }
        if (i == 32 && this.plusForSpace) {
            return URI_ESCAPED_SPACE;
        }
        char[] cArr;
        if (i <= 127) {
            return new char[]{'%', UPPER_HEX_DIGITS[i & 15], UPPER_HEX_DIGITS[i >>> 4]};
        } else if (i <= 2047) {
            cArr = new char[6];
            cArr[0] = '%';
            cArr[3] = '%';
            cArr[5] = UPPER_HEX_DIGITS[i & 15];
            i >>>= 4;
            cArr[4] = UPPER_HEX_DIGITS[(i & 3) | 8];
            i >>>= 2;
            cArr[2] = UPPER_HEX_DIGITS[i & 15];
            cArr[1] = UPPER_HEX_DIGITS[(i >>> 4) | 12];
            return cArr;
        } else if (i <= SupportMenu.USER_MASK) {
            cArr = new char[9];
            i >>>= 4;
            cArr[7] = UPPER_HEX_DIGITS[(i & 3) | 8];
            i >>>= 2;
            cArr[5] = UPPER_HEX_DIGITS[i & 15];
            i >>>= 4;
            cArr[4] = UPPER_HEX_DIGITS[(i & 3) | 8];
            cArr[2] = UPPER_HEX_DIGITS[i >>> 2];
            return cArr;
        } else if (i <= 1114111) {
            cArr = new char[12];
            i >>>= 4;
            cArr[10] = UPPER_HEX_DIGITS[(i & 3) | 8];
            i >>>= 2;
            cArr[8] = UPPER_HEX_DIGITS[i & 15];
            i >>>= 4;
            cArr[7] = UPPER_HEX_DIGITS[(i & 3) | 8];
            i >>>= 2;
            cArr[5] = UPPER_HEX_DIGITS[i & 15];
            i >>>= 4;
            cArr[4] = UPPER_HEX_DIGITS[(i & 3) | 8];
            cArr[2] = UPPER_HEX_DIGITS[(i >>> 2) & 7];
            return cArr;
        } else {
            StringBuilder stringBuilder = new StringBuilder(43);
            stringBuilder.append("Invalid unicode character value ");
            stringBuilder.append(i);
            throw new IllegalArgumentException(stringBuilder.toString());
        }
    }
}
