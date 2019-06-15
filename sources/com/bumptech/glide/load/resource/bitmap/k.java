package com.bumptech.glide.load.resource.bitmap;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory.Options;
import android.os.Build.VERSION;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.util.Log;
import com.bumptech.glide.f.d;
import com.bumptech.glide.f.h;
import com.bumptech.glide.f.i;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.ImageHeaderParser;
import com.bumptech.glide.load.ImageHeaderParser.ImageType;
import com.bumptech.glide.load.e;
import com.bumptech.glide.load.engine.a.b;
import com.bumptech.glide.load.engine.q;
import com.bumptech.glide.load.f;
import com.bumptech.glide.load.resource.bitmap.DownsampleStrategy.SampleSizeRounding;
import com.integralads.avid.library.inmobi.utils.AvidJSONUtil;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public final class k {
    public static final e<DecodeFormat> a = e.a("com.bumptech.glide.load.resource.bitmap.Downsampler.DecodeFormat", DecodeFormat.DEFAULT);
    public static final e<DownsampleStrategy> b = e.a("com.bumptech.glide.load.resource.bitmap.Downsampler.DownsampleStrategy", DownsampleStrategy.g);
    public static final e<Boolean> c = e.a("com.bumptech.glide.load.resource.bitmap.Downsampler.FixBitmapSize", Boolean.valueOf(false));
    public static final e<Boolean> d = e.a("com.bumtpech.glide.load.resource.bitmap.Downsampler.AllowHardwareDecode", null);
    private static final Set<String> e = Collections.unmodifiableSet(new HashSet(Arrays.asList(new String[]{"image/vnd.wap.wbmp", "image/x-ico"})));
    private static final a f = new a() {
        public void a() {
        }

        public void a(com.bumptech.glide.load.engine.a.e eVar, Bitmap bitmap) throws IOException {
        }
    };
    private static final Set<ImageType> g = Collections.unmodifiableSet(EnumSet.of(ImageType.JPEG, ImageType.PNG_A, ImageType.PNG));
    private static final Queue<Options> h = i.a(0);
    private final com.bumptech.glide.load.engine.a.e i;
    private final DisplayMetrics j;
    private final b k;
    private final List<ImageHeaderParser> l;
    private final o m = o.a();

    public interface a {
        void a();

        void a(com.bumptech.glide.load.engine.a.e eVar, Bitmap bitmap) throws IOException;
    }

    private static int c(double d) {
        return (int) (d + 0.5d);
    }

    public boolean a(InputStream inputStream) {
        return true;
    }

    public boolean a(ByteBuffer byteBuffer) {
        return true;
    }

    public k(List<ImageHeaderParser> list, DisplayMetrics displayMetrics, com.bumptech.glide.load.engine.a.e eVar, b bVar) {
        this.l = list;
        this.j = (DisplayMetrics) h.a((Object) displayMetrics);
        this.i = (com.bumptech.glide.load.engine.a.e) h.a((Object) eVar);
        this.k = (b) h.a((Object) bVar);
    }

    public q<Bitmap> a(InputStream inputStream, int i, int i2, f fVar) throws IOException {
        return a(inputStream, i, i2, fVar, f);
    }

    public q<Bitmap> a(InputStream inputStream, int i, int i2, f fVar, a aVar) throws IOException {
        f fVar2 = fVar;
        h.a(inputStream.markSupported(), "You must provide an InputStream that supports mark()");
        Object obj = (byte[]) this.k.a(65536, byte[].class);
        Options a = a();
        a.inTempStorage = obj;
        DecodeFormat decodeFormat = (DecodeFormat) fVar2.a(a);
        DownsampleStrategy downsampleStrategy = (DownsampleStrategy) fVar2.a(b);
        boolean booleanValue = ((Boolean) fVar2.a(c)).booleanValue();
        boolean z = fVar2.a(d) != null && ((Boolean) fVar2.a(d)).booleanValue();
        try {
            d a2 = d.a(a(inputStream, a, downsampleStrategy, decodeFormat, decodeFormat == DecodeFormat.PREFER_ARGB_8888_DISALLOW_HARDWARE ? false : z, i, i2, booleanValue, aVar), this.i);
            c(a);
            this.k.a(obj, byte[].class);
            return a2;
        } catch (Throwable th) {
            Throwable th2 = th;
            c(a);
            this.k.a(obj, byte[].class);
        }
    }

    private Bitmap a(InputStream inputStream, Options options, DownsampleStrategy downsampleStrategy, DecodeFormat decodeFormat, boolean z, int i, int i2, boolean z2, a aVar) throws IOException {
        int i3;
        int i4;
        int i5;
        InputStream inputStream2 = inputStream;
        Options options2 = options;
        a aVar2 = aVar;
        long a = d.a();
        int[] a2 = a(inputStream2, options2, aVar2, this.i);
        int i6 = 0;
        int i7 = a2[0];
        int i8 = a2[1];
        String str = options2.outMimeType;
        boolean z3 = (i7 == -1 || i8 == -1) ? false : z;
        int b = com.bumptech.glide.load.b.b(this.l, inputStream2, this.k);
        int a3 = t.a(b);
        boolean b2 = t.b(b);
        int i9 = i;
        if (i9 == Integer.MIN_VALUE) {
            i3 = i2;
            i4 = i7;
        } else {
            i3 = i2;
            i4 = i9;
        }
        int i10 = i3 == Integer.MIN_VALUE ? i8 : i3;
        ImageType a4 = com.bumptech.glide.load.b.a(this.l, inputStream2, this.k);
        ImageType imageType = a4;
        a(a4, inputStream2, aVar2, this.i, downsampleStrategy, a3, i7, i8, i4, i10, options2);
        i9 = b;
        String str2 = str;
        int i11 = i8;
        int i12 = 1;
        int i13 = i7;
        a aVar3 = aVar2;
        Options options3 = options2;
        a(inputStream2, decodeFormat, z3, b2, options2, i4, i10);
        if (VERSION.SDK_INT >= 19) {
            i6 = i12;
        }
        if ((options3.inSampleSize == i12 || i6 != 0) && a(imageType)) {
            int ceil;
            if (i13 < 0 || i11 < 0 || !z2 || i6 == 0) {
                float f = a(options) ? ((float) options3.inTargetDensity) / ((float) options3.inDensity) : 1.0f;
                i8 = options3.inSampleSize;
                float f2 = (float) i8;
                ceil = (int) Math.ceil((double) (((float) i13) / f2));
                i7 = (int) Math.ceil((double) (((float) i11) / f2));
                ceil = Math.round(((float) ceil) * f);
                i7 = Math.round(((float) i7) * f);
                if (Log.isLoggable("Downsampler", 2)) {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("Calculated target [");
                    stringBuilder.append(ceil);
                    stringBuilder.append(AvidJSONUtil.KEY_X);
                    stringBuilder.append(i7);
                    stringBuilder.append("] for source [");
                    stringBuilder.append(i13);
                    stringBuilder.append(AvidJSONUtil.KEY_X);
                    stringBuilder.append(i11);
                    stringBuilder.append("], sampleSize: ");
                    stringBuilder.append(i8);
                    stringBuilder.append(", targetDensity: ");
                    stringBuilder.append(options3.inTargetDensity);
                    stringBuilder.append(", density: ");
                    stringBuilder.append(options3.inDensity);
                    stringBuilder.append(", density multiplier: ");
                    stringBuilder.append(f);
                    Log.v("Downsampler", stringBuilder.toString());
                }
            } else {
                ceil = i4;
                i7 = i10;
            }
            if (ceil > 0 && i7 > 0) {
                a(options3, this.i, ceil, i7);
            }
        }
        Bitmap b3 = b(inputStream, options3, aVar3, this.i);
        aVar3.a(this.i, b3);
        if (Log.isLoggable("Downsampler", 2)) {
            i5 = i9;
            a(i13, i11, str2, options3, b3, i, i2, a);
        } else {
            i5 = i9;
        }
        Bitmap bitmap = null;
        if (b3 != null) {
            b3.setDensity(this.j.densityDpi);
            bitmap = t.a(this.i, b3, i5);
            if (!b3.equals(bitmap)) {
                this.i.a(b3);
            }
        }
        return bitmap;
    }

    static void a(ImageType imageType, InputStream inputStream, a aVar, com.bumptech.glide.load.engine.a.e eVar, DownsampleStrategy downsampleStrategy, int i, int i2, int i3, int i4, int i5, Options options) throws IOException {
        ImageType imageType2 = imageType;
        DownsampleStrategy downsampleStrategy2 = downsampleStrategy;
        int i6 = i;
        int i7 = i2;
        int i8 = i3;
        int i9 = i4;
        int i10 = i5;
        Options options2 = options;
        if (i7 <= 0 || i8 <= 0) {
            if (Log.isLoggable("Downsampler", 3)) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Unable to determine dimensions for: ");
                stringBuilder.append(imageType2);
                stringBuilder.append(" with target [");
                stringBuilder.append(i9);
                stringBuilder.append(AvidJSONUtil.KEY_X);
                stringBuilder.append(i10);
                stringBuilder.append("]");
                Log.d("Downsampler", stringBuilder.toString());
            }
            return;
        }
        float a;
        if (i6 == 90 || i6 == 270) {
            a = downsampleStrategy2.a(i8, i7, i9, i10);
        } else {
            a = downsampleStrategy2.a(i7, i8, i9, i10);
        }
        if (a <= 0.0f) {
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("Cannot scale with factor: ");
            stringBuilder2.append(a);
            stringBuilder2.append(" from: ");
            stringBuilder2.append(downsampleStrategy2);
            stringBuilder2.append(", source: [");
            stringBuilder2.append(i7);
            stringBuilder2.append(AvidJSONUtil.KEY_X);
            stringBuilder2.append(i8);
            stringBuilder2.append("], target: [");
            stringBuilder2.append(i9);
            stringBuilder2.append(AvidJSONUtil.KEY_X);
            stringBuilder2.append(i10);
            stringBuilder2.append("]");
            throw new IllegalArgumentException(stringBuilder2.toString());
        }
        SampleSizeRounding b = downsampleStrategy2.b(i7, i8, i9, i10);
        if (b == null) {
            throw new IllegalArgumentException("Cannot round with null rounding");
        }
        int i11;
        int ceil;
        int ceil2;
        float f = (float) i7;
        float f2 = (float) i8;
        int c = i7 / c((double) (a * f));
        int c2 = i8 / c((double) (a * f2));
        if (b == SampleSizeRounding.MEMORY) {
            c = Math.max(c, c2);
        } else {
            c = Math.min(c, c2);
        }
        if (VERSION.SDK_INT > 23 || !e.contains(options2.outMimeType)) {
            c = Math.max(1, Integer.highestOneBit(c));
            i11 = (b != SampleSizeRounding.MEMORY || ((float) c) >= 1.0f / a) ? c : c << 1;
        } else {
            i11 = 1;
        }
        options2.inSampleSize = i11;
        float min;
        if (imageType2 == ImageType.JPEG) {
            min = (float) Math.min(i11, 8);
            ceil = (int) Math.ceil((double) (f / min));
            ceil2 = (int) Math.ceil((double) (f2 / min));
            c = i11 / 8;
            if (c > 0) {
                ceil /= c;
                ceil2 /= c;
            }
        } else if (imageType2 == ImageType.PNG || imageType2 == ImageType.PNG_A) {
            min = (float) i11;
            ceil = (int) Math.floor((double) (f / min));
            ceil2 = (int) Math.floor((double) (f2 / min));
        } else if (imageType2 == ImageType.WEBP || imageType2 == ImageType.WEBP_A) {
            if (VERSION.SDK_INT >= 24) {
                min = (float) i11;
                ceil = Math.round(f / min);
                ceil2 = Math.round(f2 / min);
            } else {
                min = (float) i11;
                ceil = (int) Math.floor((double) (f / min));
                ceil2 = (int) Math.floor((double) (f2 / min));
            }
        } else if (i7 % i11 == 0 && i8 % i11 == 0) {
            ceil = i7 / i11;
            ceil2 = i8 / i11;
        } else {
            int[] a2 = a(inputStream, options2, aVar, eVar);
            ceil = a2[0];
            ceil2 = a2[1];
        }
        double a3 = (double) downsampleStrategy2.a(ceil, ceil2, i9, i10);
        if (VERSION.SDK_INT >= 19) {
            options2.inTargetDensity = a(a3);
            options2.inDensity = b(a3);
        }
        if (a(options)) {
            options2.inScaled = true;
        } else {
            options2.inTargetDensity = 0;
            options2.inDensity = 0;
        }
        if (Log.isLoggable("Downsampler", 2)) {
            StringBuilder stringBuilder3 = new StringBuilder();
            stringBuilder3.append("Calculate scaling, source: [");
            stringBuilder3.append(i7);
            stringBuilder3.append(AvidJSONUtil.KEY_X);
            stringBuilder3.append(i8);
            stringBuilder3.append("], target: [");
            stringBuilder3.append(i9);
            stringBuilder3.append(AvidJSONUtil.KEY_X);
            stringBuilder3.append(i10);
            stringBuilder3.append("], power of two scaled: [");
            stringBuilder3.append(ceil);
            stringBuilder3.append(AvidJSONUtil.KEY_X);
            stringBuilder3.append(ceil2);
            stringBuilder3.append("], exact scale factor: ");
            stringBuilder3.append(a);
            stringBuilder3.append(", power of 2 sample size: ");
            stringBuilder3.append(i11);
            stringBuilder3.append(", adjusted scale factor: ");
            stringBuilder3.append(a3);
            stringBuilder3.append(", target density: ");
            stringBuilder3.append(options2.inTargetDensity);
            stringBuilder3.append(", density: ");
            stringBuilder3.append(options2.inDensity);
            Log.v("Downsampler", stringBuilder3.toString());
        }
    }

    private static int a(double d) {
        int b = b(d);
        int c = c(((double) b) * d);
        return c((d / ((double) (((float) c) / ((float) b)))) * ((double) c));
    }

    private static int b(double d) {
        if (d > 1.0d) {
            d = 1.0d / d;
        }
        return (int) Math.round(2.147483647E9d * d);
    }

    private boolean a(ImageType imageType) throws IOException {
        if (VERSION.SDK_INT >= 19) {
            return true;
        }
        return g.contains(imageType);
    }

    private void a(InputStream inputStream, DecodeFormat decodeFormat, boolean z, boolean z2, Options options, int i, int i2) throws IOException {
        if (!this.m.a(i, i2, options, decodeFormat, z, z2)) {
            if (decodeFormat == DecodeFormat.PREFER_ARGB_8888 || decodeFormat == DecodeFormat.PREFER_ARGB_8888_DISALLOW_HARDWARE || VERSION.SDK_INT == 16) {
                options.inPreferredConfig = Config.ARGB_8888;
                return;
            }
            boolean hasAlpha;
            try {
                hasAlpha = com.bumptech.glide.load.b.a(this.l, inputStream, this.k).hasAlpha();
            } catch (IOException e) {
                if (Log.isLoggable("Downsampler", 3)) {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("Cannot determine whether the image has alpha or not from header, format ");
                    stringBuilder.append(decodeFormat);
                    Log.d("Downsampler", stringBuilder.toString(), e);
                }
                hasAlpha = false;
            }
            options.inPreferredConfig = hasAlpha ? Config.ARGB_8888 : Config.RGB_565;
            if (options.inPreferredConfig == Config.RGB_565 || options.inPreferredConfig == Config.ARGB_4444 || options.inPreferredConfig == Config.ALPHA_8) {
                options.inDither = true;
            }
        }
    }

    private static int[] a(InputStream inputStream, Options options, a aVar, com.bumptech.glide.load.engine.a.e eVar) throws IOException {
        options.inJustDecodeBounds = true;
        b(inputStream, options, aVar, eVar);
        options.inJustDecodeBounds = false;
        return new int[]{options.outWidth, options.outHeight};
    }

    /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x005f */
    /* JADX WARNING: Can't wrap try/catch for region: R(4:18|19|21|22) */
    private static android.graphics.Bitmap b(java.io.InputStream r5, android.graphics.BitmapFactory.Options r6, com.bumptech.glide.load.resource.bitmap.k.a r7, com.bumptech.glide.load.engine.a.e r8) throws java.io.IOException {
        /*
        r0 = r6.inJustDecodeBounds;
        if (r0 == 0) goto L_0x000a;
    L_0x0004:
        r0 = 10485760; // 0xa00000 float:1.469368E-38 double:5.180654E-317;
        r5.mark(r0);
        goto L_0x000d;
    L_0x000a:
        r7.a();
    L_0x000d:
        r0 = r6.outWidth;
        r1 = r6.outHeight;
        r2 = r6.outMimeType;
        r3 = com.bumptech.glide.load.resource.bitmap.t.a();
        r3.lock();
        r3 = 0;
        r4 = android.graphics.BitmapFactory.decodeStream(r5, r3, r6);	 Catch:{ IllegalArgumentException -> 0x0030 }
        r7 = com.bumptech.glide.load.resource.bitmap.t.a();
        r7.unlock();
        r6 = r6.inJustDecodeBounds;
        if (r6 == 0) goto L_0x002d;
    L_0x002a:
        r5.reset();
    L_0x002d:
        return r4;
    L_0x002e:
        r5 = move-exception;
        goto L_0x0061;
    L_0x0030:
        r4 = move-exception;
        r0 = a(r4, r0, r1, r2, r6);	 Catch:{ all -> 0x002e }
        r1 = "Downsampler";
        r2 = 3;
        r1 = android.util.Log.isLoggable(r1, r2);	 Catch:{ all -> 0x002e }
        if (r1 == 0) goto L_0x0045;
    L_0x003e:
        r1 = "Downsampler";
        r2 = "Failed to decode with inBitmap, trying again without Bitmap re-use";
        android.util.Log.d(r1, r2, r0);	 Catch:{ all -> 0x002e }
    L_0x0045:
        r1 = r6.inBitmap;	 Catch:{ all -> 0x002e }
        if (r1 == 0) goto L_0x0060;
    L_0x0049:
        r5.reset();	 Catch:{ IOException -> 0x005f }
        r1 = r6.inBitmap;	 Catch:{ IOException -> 0x005f }
        r8.a(r1);	 Catch:{ IOException -> 0x005f }
        r6.inBitmap = r3;	 Catch:{ IOException -> 0x005f }
        r5 = b(r5, r6, r7, r8);	 Catch:{ IOException -> 0x005f }
        r6 = com.bumptech.glide.load.resource.bitmap.t.a();
        r6.unlock();
        return r5;
    L_0x005f:
        throw r0;	 Catch:{ all -> 0x002e }
    L_0x0060:
        throw r0;	 Catch:{ all -> 0x002e }
    L_0x0061:
        r6 = com.bumptech.glide.load.resource.bitmap.t.a();
        r6.unlock();
        throw r5;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.load.resource.bitmap.k.b(java.io.InputStream, android.graphics.BitmapFactory$Options, com.bumptech.glide.load.resource.bitmap.k$a, com.bumptech.glide.load.engine.a.e):android.graphics.Bitmap");
    }

    private static boolean a(Options options) {
        return options.inTargetDensity > 0 && options.inDensity > 0 && options.inTargetDensity != options.inDensity;
    }

    private static void a(int i, int i2, String str, Options options, Bitmap bitmap, int i3, int i4, long j) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Decoded ");
        stringBuilder.append(a(bitmap));
        stringBuilder.append(" from [");
        stringBuilder.append(i);
        stringBuilder.append(AvidJSONUtil.KEY_X);
        stringBuilder.append(i2);
        stringBuilder.append("] ");
        stringBuilder.append(str);
        stringBuilder.append(" with inBitmap ");
        stringBuilder.append(b(options));
        stringBuilder.append(" for [");
        stringBuilder.append(i3);
        stringBuilder.append(AvidJSONUtil.KEY_X);
        stringBuilder.append(i4);
        stringBuilder.append("], sample size: ");
        stringBuilder.append(options.inSampleSize);
        stringBuilder.append(", density: ");
        stringBuilder.append(options.inDensity);
        stringBuilder.append(", target density: ");
        stringBuilder.append(options.inTargetDensity);
        stringBuilder.append(", thread: ");
        stringBuilder.append(Thread.currentThread().getName());
        stringBuilder.append(", duration: ");
        stringBuilder.append(d.a(j));
        Log.v("Downsampler", stringBuilder.toString());
    }

    private static String b(Options options) {
        return a(options.inBitmap);
    }

    @Nullable
    @TargetApi(19)
    private static String a(Bitmap bitmap) {
        if (bitmap == null) {
            return null;
        }
        String stringBuilder;
        if (VERSION.SDK_INT >= 19) {
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append(" (");
            stringBuilder2.append(bitmap.getAllocationByteCount());
            stringBuilder2.append(")");
            stringBuilder = stringBuilder2.toString();
        } else {
            stringBuilder = "";
        }
        StringBuilder stringBuilder3 = new StringBuilder();
        stringBuilder3.append("[");
        stringBuilder3.append(bitmap.getWidth());
        stringBuilder3.append(AvidJSONUtil.KEY_X);
        stringBuilder3.append(bitmap.getHeight());
        stringBuilder3.append("] ");
        stringBuilder3.append(bitmap.getConfig());
        stringBuilder3.append(stringBuilder);
        return stringBuilder3.toString();
    }

    private static IOException a(IllegalArgumentException illegalArgumentException, int i, int i2, String str, Options options) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Exception decoding bitmap, outWidth: ");
        stringBuilder.append(i);
        stringBuilder.append(", outHeight: ");
        stringBuilder.append(i2);
        stringBuilder.append(", outMimeType: ");
        stringBuilder.append(str);
        stringBuilder.append(", inBitmap: ");
        stringBuilder.append(b(options));
        return new IOException(stringBuilder.toString(), illegalArgumentException);
    }

    @TargetApi(26)
    private static void a(Options options, com.bumptech.glide.load.engine.a.e eVar, int i, int i2) {
        if (VERSION.SDK_INT < 26 || options.inPreferredConfig != Config.HARDWARE) {
            options.inBitmap = eVar.b(i, i2, options.inPreferredConfig);
        }
    }

    private static synchronized Options a() {
        Options options;
        synchronized (k.class) {
            synchronized (h) {
                options = (Options) h.poll();
            }
            if (options == null) {
                options = new Options();
                d(options);
            }
        }
        return options;
    }

    private static void c(Options options) {
        d(options);
        synchronized (h) {
            h.offer(options);
        }
    }

    private static void d(Options options) {
        options.inTempStorage = null;
        options.inDither = false;
        options.inScaled = false;
        options.inSampleSize = 1;
        options.inPreferredConfig = null;
        options.inJustDecodeBounds = false;
        options.inDensity = 0;
        options.inTargetDensity = 0;
        options.outWidth = 0;
        options.outHeight = 0;
        options.outMimeType = null;
        options.inBitmap = null;
        options.inMutable = true;
    }
}
