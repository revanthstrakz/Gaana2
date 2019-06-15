package com.bumptech.glide.load.resource.bitmap;

import android.support.v4.internal.view.SupportMenu;
import android.support.v4.view.InputDeviceCompat;
import android.support.v4.view.MotionEventCompat;
import android.util.Log;
import com.bumptech.glide.f.h;
import com.bumptech.glide.load.ImageHeaderParser;
import com.bumptech.glide.load.ImageHeaderParser.ImageType;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;

public final class j implements ImageHeaderParser {
    static final byte[] a = "Exif\u0000\u0000".getBytes(Charset.forName("UTF-8"));
    private static final int[] b = new int[]{0, 1, 1, 2, 4, 8, 1, 1, 2, 4, 8, 4, 8};

    private static final class b {
        private final ByteBuffer a;

        b(byte[] bArr, int i) {
            this.a = (ByteBuffer) ByteBuffer.wrap(bArr).order(ByteOrder.BIG_ENDIAN).limit(i);
        }

        /* Access modifiers changed, original: 0000 */
        public void a(ByteOrder byteOrder) {
            this.a.order(byteOrder);
        }

        /* Access modifiers changed, original: 0000 */
        public int a() {
            return this.a.remaining();
        }

        /* Access modifiers changed, original: 0000 */
        public int a(int i) {
            return a(i, 4) ? this.a.getInt(i) : -1;
        }

        /* Access modifiers changed, original: 0000 */
        public short b(int i) {
            return a(i, 2) ? this.a.getShort(i) : (short) -1;
        }

        private boolean a(int i, int i2) {
            return this.a.remaining() - i >= i2;
        }
    }

    private interface c {
        int a() throws IOException;

        int a(byte[] bArr, int i) throws IOException;

        long a(long j) throws IOException;

        short b() throws IOException;

        int c() throws IOException;
    }

    private static final class a implements c {
        private final ByteBuffer a;

        a(ByteBuffer byteBuffer) {
            this.a = byteBuffer;
            byteBuffer.order(ByteOrder.BIG_ENDIAN);
        }

        public int a() throws IOException {
            return ((c() << 8) & MotionEventCompat.ACTION_POINTER_INDEX_MASK) | (c() & 255);
        }

        public short b() throws IOException {
            return (short) (c() & 255);
        }

        public long a(long j) throws IOException {
            int min = (int) Math.min((long) this.a.remaining(), j);
            this.a.position(this.a.position() + min);
            return (long) min;
        }

        public int a(byte[] bArr, int i) throws IOException {
            i = Math.min(i, this.a.remaining());
            if (i == 0) {
                return -1;
            }
            this.a.get(bArr, 0, i);
            return i;
        }

        public int c() throws IOException {
            if (this.a.remaining() < 1) {
                return -1;
            }
            return this.a.get();
        }
    }

    private static final class d implements c {
        private final InputStream a;

        d(InputStream inputStream) {
            this.a = inputStream;
        }

        public int a() throws IOException {
            return ((this.a.read() << 8) & MotionEventCompat.ACTION_POINTER_INDEX_MASK) | (this.a.read() & 255);
        }

        public short b() throws IOException {
            return (short) (this.a.read() & 255);
        }

        public long a(long j) throws IOException {
            if (j < 0) {
                return 0;
            }
            long j2 = j;
            while (j2 > 0) {
                long skip = this.a.skip(j2);
                if (skip <= 0) {
                    if (this.a.read() == -1) {
                        break;
                    }
                    skip = 1;
                }
                j2 -= skip;
            }
            return j - j2;
        }

        public int a(byte[] bArr, int i) throws IOException {
            int i2 = i;
            while (i2 > 0) {
                int read = this.a.read(bArr, i - i2, i2);
                if (read == -1) {
                    break;
                }
                i2 -= read;
            }
            return i - i2;
        }

        public int c() throws IOException {
            return this.a.read();
        }
    }

    private static int a(int i, int i2) {
        return (i + 2) + (12 * i2);
    }

    private static boolean a(int i) {
        return (i & 65496) == 65496 || i == 19789 || i == 18761;
    }

    public ImageType a(InputStream inputStream) throws IOException {
        return a(new d((InputStream) h.a((Object) inputStream)));
    }

    public ImageType a(ByteBuffer byteBuffer) throws IOException {
        return a(new a((ByteBuffer) h.a((Object) byteBuffer)));
    }

    public int a(InputStream inputStream, com.bumptech.glide.load.engine.a.b bVar) throws IOException {
        return a(new d((InputStream) h.a((Object) inputStream)), (com.bumptech.glide.load.engine.a.b) h.a((Object) bVar));
    }

    private ImageType a(c cVar) throws IOException {
        int a = cVar.a();
        if (a == 65496) {
            return ImageType.JPEG;
        }
        a = ((a << 16) & SupportMenu.CATEGORY_MASK) | (cVar.a() & SupportMenu.USER_MASK);
        if (a == -1991225785) {
            cVar.a(21);
            return cVar.c() >= 3 ? ImageType.PNG_A : ImageType.PNG;
        } else if ((a >> 8) == 4671814) {
            return ImageType.GIF;
        } else {
            if (a != 1380533830) {
                return ImageType.UNKNOWN;
            }
            cVar.a(4);
            if ((((cVar.a() << 16) & SupportMenu.CATEGORY_MASK) | (cVar.a() & SupportMenu.USER_MASK)) != 1464156752) {
                return ImageType.UNKNOWN;
            }
            a = ((cVar.a() << 16) & SupportMenu.CATEGORY_MASK) | (cVar.a() & SupportMenu.USER_MASK);
            if ((a & InputDeviceCompat.SOURCE_ANY) != 1448097792) {
                return ImageType.UNKNOWN;
            }
            a &= 255;
            if (a == 88) {
                cVar.a(4);
                return (cVar.c() & 16) != 0 ? ImageType.WEBP_A : ImageType.WEBP;
            } else if (a != 76) {
                return ImageType.WEBP;
            } else {
                cVar.a(4);
                return (cVar.c() & 8) != 0 ? ImageType.WEBP_A : ImageType.WEBP;
            }
        }
    }

    private int a(c cVar, com.bumptech.glide.load.engine.a.b bVar) throws IOException {
        int a = cVar.a();
        if (a(a)) {
            a = b(cVar);
            if (a == -1) {
                if (Log.isLoggable("DfltImageHeaderParser", 3)) {
                    Log.d("DfltImageHeaderParser", "Failed to parse exif segment length, or exif segment not found");
                }
                return -1;
            }
            Object obj = (byte[]) bVar.a(a, byte[].class);
            try {
                int a2 = a(cVar, obj, a);
                return a2;
            } finally {
                bVar.a(obj, byte[].class);
            }
        } else {
            if (Log.isLoggable("DfltImageHeaderParser", 3)) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Parser doesn't handle magic number: ");
                stringBuilder.append(a);
                Log.d("DfltImageHeaderParser", stringBuilder.toString());
            }
            return -1;
        }
    }

    private int a(c cVar, byte[] bArr, int i) throws IOException {
        int a = cVar.a(bArr, i);
        if (a != i) {
            if (Log.isLoggable("DfltImageHeaderParser", 3)) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Unable to read exif segment data, length: ");
                stringBuilder.append(i);
                stringBuilder.append(", actually read: ");
                stringBuilder.append(a);
                Log.d("DfltImageHeaderParser", stringBuilder.toString());
            }
            return -1;
        } else if (a(bArr, i)) {
            return a(new b(bArr, i));
        } else {
            if (Log.isLoggable("DfltImageHeaderParser", 3)) {
                Log.d("DfltImageHeaderParser", "Missing jpeg exif preamble");
            }
            return -1;
        }
    }

    private boolean a(byte[] bArr, int i) {
        boolean z = bArr != null && i > a.length;
        if (!z) {
            return z;
        }
        for (int i2 = 0; i2 < a.length; i2++) {
            if (bArr[i2] != a[i2]) {
                return false;
            }
        }
        return z;
    }

    private int b(c cVar) throws IOException {
        long a;
        short b;
        int a2;
        long j;
        do {
            b = cVar.b();
            if (b != (short) 255) {
                if (Log.isLoggable("DfltImageHeaderParser", 3)) {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("Unknown segmentId=");
                    stringBuilder.append(b);
                    Log.d("DfltImageHeaderParser", stringBuilder.toString());
                }
                return -1;
            }
            b = cVar.b();
            if (b == (short) 218) {
                return -1;
            }
            if (b == (short) 217) {
                if (Log.isLoggable("DfltImageHeaderParser", 3)) {
                    Log.d("DfltImageHeaderParser", "Found MARKER_EOI in exif segment");
                }
                return -1;
            }
            a2 = cVar.a() - 2;
            if (b == (short) 225) {
                return a2;
            }
            j = (long) a2;
            a = cVar.a(j);
        } while (a == j);
        if (Log.isLoggable("DfltImageHeaderParser", 3)) {
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("Unable to skip enough data, type: ");
            stringBuilder2.append(b);
            stringBuilder2.append(", wanted to skip: ");
            stringBuilder2.append(a2);
            stringBuilder2.append(", but actually skipped: ");
            stringBuilder2.append(a);
            Log.d("DfltImageHeaderParser", stringBuilder2.toString());
        }
        return -1;
    }

    private static int a(b bVar) {
        ByteOrder byteOrder;
        int length = "Exif\u0000\u0000".length();
        short b = bVar.b(length);
        if (b == (short) 19789) {
            byteOrder = ByteOrder.BIG_ENDIAN;
        } else if (b == (short) 18761) {
            byteOrder = ByteOrder.LITTLE_ENDIAN;
        } else {
            if (Log.isLoggable("DfltImageHeaderParser", 3)) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Unknown endianness = ");
                stringBuilder.append(b);
                Log.d("DfltImageHeaderParser", stringBuilder.toString());
            }
            byteOrder = ByteOrder.BIG_ENDIAN;
        }
        bVar.a(byteOrder);
        int a = bVar.a(length + 4) + length;
        short b2 = bVar.b(a);
        for (int i = 0; i < b2; i++) {
            int a2 = a(a, i);
            short b3 = bVar.b(a2);
            if (b3 == (short) 274) {
                short b4 = bVar.b(a2 + 2);
                StringBuilder stringBuilder2;
                if (b4 >= (short) 1 && b4 <= (short) 12) {
                    int a3 = bVar.a(a2 + 4);
                    if (a3 >= 0) {
                        if (Log.isLoggable("DfltImageHeaderParser", 3)) {
                            StringBuilder stringBuilder3 = new StringBuilder();
                            stringBuilder3.append("Got tagIndex=");
                            stringBuilder3.append(i);
                            stringBuilder3.append(" tagType=");
                            stringBuilder3.append(b3);
                            stringBuilder3.append(" formatCode=");
                            stringBuilder3.append(b4);
                            stringBuilder3.append(" componentCount=");
                            stringBuilder3.append(a3);
                            Log.d("DfltImageHeaderParser", stringBuilder3.toString());
                        }
                        a3 += b[b4];
                        if (a3 <= 4) {
                            a2 += 8;
                            if (a2 < 0 || a2 > bVar.a()) {
                                if (Log.isLoggable("DfltImageHeaderParser", 3)) {
                                    StringBuilder stringBuilder4 = new StringBuilder();
                                    stringBuilder4.append("Illegal tagValueOffset=");
                                    stringBuilder4.append(a2);
                                    stringBuilder4.append(" tagType=");
                                    stringBuilder4.append(b3);
                                    Log.d("DfltImageHeaderParser", stringBuilder4.toString());
                                }
                            } else if (a3 >= 0 && a3 + a2 <= bVar.a()) {
                                return bVar.b(a2);
                            } else {
                                if (Log.isLoggable("DfltImageHeaderParser", 3)) {
                                    StringBuilder stringBuilder5 = new StringBuilder();
                                    stringBuilder5.append("Illegal number of bytes for TI tag data tagType=");
                                    stringBuilder5.append(b3);
                                    Log.d("DfltImageHeaderParser", stringBuilder5.toString());
                                }
                            }
                        } else if (Log.isLoggable("DfltImageHeaderParser", 3)) {
                            stringBuilder2 = new StringBuilder();
                            stringBuilder2.append("Got byte count > 4, not orientation, continuing, formatCode=");
                            stringBuilder2.append(b4);
                            Log.d("DfltImageHeaderParser", stringBuilder2.toString());
                        }
                    } else if (Log.isLoggable("DfltImageHeaderParser", 3)) {
                        Log.d("DfltImageHeaderParser", "Negative tiff component count");
                    }
                } else if (Log.isLoggable("DfltImageHeaderParser", 3)) {
                    stringBuilder2 = new StringBuilder();
                    stringBuilder2.append("Got invalid format code = ");
                    stringBuilder2.append(b4);
                    Log.d("DfltImageHeaderParser", stringBuilder2.toString());
                }
            }
        }
        return -1;
    }
}
