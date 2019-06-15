package com.google.android.gms.internal.ads;

import com.google.android.gms.common.util.MurmurHash3;
import java.io.UnsupportedEncodingException;

@zzark
public final class zzth {
    public static int zzba(String str) {
        byte[] bytes;
        try {
            bytes = str.getBytes("UTF-8");
        } catch (UnsupportedEncodingException unused) {
            bytes = str.getBytes();
        }
        return MurmurHash3.murmurhash3_x86_32(bytes, 0, bytes.length, 0);
    }

    /* JADX WARNING: Removed duplicated region for block: B:56:0x009c  */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x0086  */
    /* JADX WARNING: Missing block: B:48:0x007f, code skipped:
            if (r8 != 0) goto L_0x0081;
     */
    /* JADX WARNING: Missing block: B:67:0x00c4, code skipped:
            if (r4 == 0) goto L_0x00d6;
     */
    /* JADX WARNING: Missing block: B:70:0x00d4, code skipped:
            if (r4 == 0) goto L_0x00d6;
     */
    @android.support.annotation.Nullable
    public static java.lang.String[] zze(@android.support.annotation.Nullable java.lang.String r11, boolean r12) {
        /*
        if (r11 != 0) goto L_0x0004;
    L_0x0002:
        r11 = 0;
        return r11;
    L_0x0004:
        r0 = new java.util.ArrayList;
        r0.<init>();
        r1 = r11.toCharArray();
        r11 = r11.length();
        r2 = 0;
        r3 = r2;
        r4 = r3;
        r5 = r4;
    L_0x0015:
        if (r3 >= r11) goto L_0x00db;
    L_0x0017:
        r6 = java.lang.Character.codePointAt(r1, r3);
        r7 = java.lang.Character.charCount(r6);
        r8 = java.lang.Character.isLetter(r6);
        r9 = 1;
        if (r8 == 0) goto L_0x0083;
    L_0x0026:
        r8 = java.lang.Character.UnicodeBlock.of(r6);
        r10 = java.lang.Character.UnicodeBlock.BOPOMOFO;
        if (r8 == r10) goto L_0x0065;
    L_0x002e:
        r10 = java.lang.Character.UnicodeBlock.BOPOMOFO_EXTENDED;
        if (r8 == r10) goto L_0x0065;
    L_0x0032:
        r10 = java.lang.Character.UnicodeBlock.CJK_COMPATIBILITY;
        if (r8 == r10) goto L_0x0065;
    L_0x0036:
        r10 = java.lang.Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS;
        if (r8 == r10) goto L_0x0065;
    L_0x003a:
        r10 = java.lang.Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS_SUPPLEMENT;
        if (r8 == r10) goto L_0x0065;
    L_0x003e:
        r10 = java.lang.Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS;
        if (r8 == r10) goto L_0x0065;
    L_0x0042:
        r10 = java.lang.Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A;
        if (r8 == r10) goto L_0x0065;
    L_0x0046:
        r10 = java.lang.Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B;
        if (r8 == r10) goto L_0x0065;
    L_0x004a:
        r10 = java.lang.Character.UnicodeBlock.ENCLOSED_CJK_LETTERS_AND_MONTHS;
        if (r8 == r10) goto L_0x0065;
    L_0x004e:
        r10 = java.lang.Character.UnicodeBlock.HANGUL_JAMO;
        if (r8 == r10) goto L_0x0065;
    L_0x0052:
        r10 = java.lang.Character.UnicodeBlock.HANGUL_SYLLABLES;
        if (r8 == r10) goto L_0x0065;
    L_0x0056:
        r10 = java.lang.Character.UnicodeBlock.HIRAGANA;
        if (r8 == r10) goto L_0x0065;
    L_0x005a:
        r10 = java.lang.Character.UnicodeBlock.KATAKANA;
        if (r8 == r10) goto L_0x0065;
    L_0x005e:
        r10 = java.lang.Character.UnicodeBlock.KATAKANA_PHONETIC_EXTENSIONS;
        if (r8 != r10) goto L_0x0063;
    L_0x0062:
        goto L_0x0065;
    L_0x0063:
        r8 = r2;
        goto L_0x0066;
    L_0x0065:
        r8 = r9;
    L_0x0066:
        if (r8 != 0) goto L_0x0081;
    L_0x0068:
        r8 = 65382; // 0xff66 float:9.162E-41 double:3.2303E-319;
        if (r6 < r8) goto L_0x0072;
    L_0x006d:
        r8 = 65437; // 0xff9d float:9.1697E-41 double:3.233E-319;
        if (r6 <= r8) goto L_0x007c;
    L_0x0072:
        r8 = 65441; // 0xffa1 float:9.1702E-41 double:3.2332E-319;
        if (r6 < r8) goto L_0x007e;
    L_0x0077:
        r8 = 65500; // 0xffdc float:9.1785E-41 double:3.23613E-319;
        if (r6 > r8) goto L_0x007e;
    L_0x007c:
        r8 = r9;
        goto L_0x007f;
    L_0x007e:
        r8 = r2;
    L_0x007f:
        if (r8 == 0) goto L_0x0083;
    L_0x0081:
        r8 = r9;
        goto L_0x0084;
    L_0x0083:
        r8 = r2;
    L_0x0084:
        if (r8 == 0) goto L_0x009c;
    L_0x0086:
        if (r4 == 0) goto L_0x0092;
    L_0x0088:
        r4 = new java.lang.String;
        r6 = r3 - r5;
        r4.<init>(r1, r5, r6);
        r0.add(r4);
    L_0x0092:
        r4 = new java.lang.String;
        r4.<init>(r1, r3, r7);
        r0.add(r4);
    L_0x009a:
        r4 = r2;
        goto L_0x00d8;
    L_0x009c:
        r8 = java.lang.Character.isLetterOrDigit(r6);
        if (r8 != 0) goto L_0x00d4;
    L_0x00a2:
        r8 = java.lang.Character.getType(r6);
        r10 = 6;
        if (r8 == r10) goto L_0x00d4;
    L_0x00a9:
        r8 = java.lang.Character.getType(r6);
        r10 = 8;
        if (r8 != r10) goto L_0x00b2;
    L_0x00b1:
        goto L_0x00d4;
    L_0x00b2:
        if (r12 == 0) goto L_0x00c7;
    L_0x00b4:
        r8 = java.lang.Character.charCount(r6);
        if (r8 != r9) goto L_0x00c7;
    L_0x00ba:
        r6 = java.lang.Character.toChars(r6);
        r6 = r6[r2];
        r8 = 39;
        if (r6 != r8) goto L_0x00c7;
    L_0x00c4:
        if (r4 != 0) goto L_0x00d7;
    L_0x00c6:
        goto L_0x00d6;
    L_0x00c7:
        if (r4 == 0) goto L_0x00d8;
    L_0x00c9:
        r4 = new java.lang.String;
        r6 = r3 - r5;
        r4.<init>(r1, r5, r6);
        r0.add(r4);
        goto L_0x009a;
    L_0x00d4:
        if (r4 != 0) goto L_0x00d7;
    L_0x00d6:
        r5 = r3;
    L_0x00d7:
        r4 = r9;
    L_0x00d8:
        r3 = r3 + r7;
        goto L_0x0015;
    L_0x00db:
        if (r4 == 0) goto L_0x00e6;
    L_0x00dd:
        r11 = new java.lang.String;
        r3 = r3 - r5;
        r11.<init>(r1, r5, r3);
        r0.add(r11);
    L_0x00e6:
        r11 = r0.size();
        r11 = new java.lang.String[r11];
        r11 = r0.toArray(r11);
        r11 = (java.lang.String[]) r11;
        return r11;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzth.zze(java.lang.String, boolean):java.lang.String[]");
    }
}
