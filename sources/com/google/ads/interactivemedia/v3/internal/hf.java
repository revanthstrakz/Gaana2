package com.google.ads.interactivemedia.v3.internal;

import java.io.IOException;
import java.io.Writer;

public final class hf {

    private static final class a extends Writer {
        private final Appendable a;
        private final a b = new a();

        static class a implements CharSequence {
            char[] a;

            a() {
            }

            public int length() {
                return this.a.length;
            }

            public char charAt(int i) {
                return this.a[i];
            }

            public CharSequence subSequence(int i, int i2) {
                return new String(this.a, i, i2 - i);
            }
        }

        a(Appendable appendable) {
            this.a = appendable;
        }

        public void close() {
        }

        public void flush() {
        }

        public void write(char[] cArr, int i, int i2) throws IOException {
            this.b.a = cArr;
            this.a.append(this.b, i, i2 + i);
        }

        public void write(int i) throws IOException {
            this.a.append((char) i);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x002b  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0028  */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x001d A:{Splitter:B:0:0x0000, ExcHandler: ia (r2_6 'e' java.lang.Throwable)} */
    /* JADX WARNING: Removed duplicated region for block: B:10:0x0016 A:{Splitter:B:0:0x0000, ExcHandler: IOException (r2_5 'e' java.lang.Throwable)} */
    /* JADX WARNING: Removed duplicated region for block: B:7:0x000f A:{Splitter:B:0:0x0000, ExcHandler: NumberFormatException (r2_4 'e' java.lang.Throwable)} */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing block: B:7:0x000f, code skipped:
            r2 = move-exception;
     */
    /* JADX WARNING: Missing block: B:9:0x0015, code skipped:
            throw new com.google.ads.interactivemedia.v3.internal.gn(r2);
     */
    /* JADX WARNING: Missing block: B:10:0x0016, code skipped:
            r2 = move-exception;
     */
    /* JADX WARNING: Missing block: B:12:0x001c, code skipped:
            throw new com.google.ads.interactivemedia.v3.internal.gg(r2);
     */
    /* JADX WARNING: Missing block: B:13:0x001d, code skipped:
            r2 = move-exception;
     */
    /* JADX WARNING: Missing block: B:15:0x0023, code skipped:
            throw new com.google.ads.interactivemedia.v3.internal.gn(r2);
     */
    /* JADX WARNING: Missing block: B:16:0x0024, code skipped:
            r2 = e;
     */
    /* JADX WARNING: Missing block: B:17:0x0025, code skipped:
            r0 = 1;
     */
    /* JADX WARNING: Missing block: B:20:0x002a, code skipped:
            return com.google.ads.interactivemedia.v3.internal.gh.a;
     */
    /* JADX WARNING: Missing block: B:22:0x0030, code skipped:
            throw new com.google.ads.interactivemedia.v3.internal.gn(r2);
     */
    public static com.google.ads.interactivemedia.v3.internal.gf a(com.google.ads.interactivemedia.v3.internal.hx r2) throws com.google.ads.interactivemedia.v3.internal.gj {
        /*
        r2.f();	 Catch:{ EOFException -> 0x0024, ia -> 0x001d, IOException -> 0x0016, NumberFormatException -> 0x000f }
        r0 = 0;
        r1 = com.google.ads.interactivemedia.v3.internal.hu.X;	 Catch:{ EOFException -> 0x000d, ia -> 0x001d, IOException -> 0x0016, NumberFormatException -> 0x000f }
        r2 = r1.read(r2);	 Catch:{ EOFException -> 0x000d, ia -> 0x001d, IOException -> 0x0016, NumberFormatException -> 0x000f }
        r2 = (com.google.ads.interactivemedia.v3.internal.gf) r2;	 Catch:{ EOFException -> 0x000d, ia -> 0x001d, IOException -> 0x0016, NumberFormatException -> 0x000f }
        return r2;
    L_0x000d:
        r2 = move-exception;
        goto L_0x0026;
    L_0x000f:
        r2 = move-exception;
        r0 = new com.google.ads.interactivemedia.v3.internal.gn;
        r0.<init>(r2);
        throw r0;
    L_0x0016:
        r2 = move-exception;
        r0 = new com.google.ads.interactivemedia.v3.internal.gg;
        r0.<init>(r2);
        throw r0;
    L_0x001d:
        r2 = move-exception;
        r0 = new com.google.ads.interactivemedia.v3.internal.gn;
        r0.<init>(r2);
        throw r0;
    L_0x0024:
        r2 = move-exception;
        r0 = 1;
    L_0x0026:
        if (r0 == 0) goto L_0x002b;
    L_0x0028:
        r2 = com.google.ads.interactivemedia.v3.internal.gh.a;
        return r2;
    L_0x002b:
        r0 = new com.google.ads.interactivemedia.v3.internal.gn;
        r0.<init>(r2);
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.ads.interactivemedia.v3.internal.hf.a(com.google.ads.interactivemedia.v3.internal.hx):com.google.ads.interactivemedia.v3.internal.gf");
    }

    public static void a(gf gfVar, hz hzVar) throws IOException {
        hu.X.write(hzVar, gfVar);
    }

    public static Writer a(Appendable appendable) {
        return appendable instanceof Writer ? (Writer) appendable : new a(appendable);
    }
}
