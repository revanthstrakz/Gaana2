package com.helpshift.util;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.net.Uri;
import android.text.TextUtils;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class p {
    private static final Set<String> a = new HashSet(Arrays.asList(new String[]{"image/jpeg", "image/png", "image/x-png", "image/x-citrix-pjpeg", "image/pjpeg"}));
    private static Set<String> b = new HashSet(Arrays.asList(new String[]{"image/jpeg", "image/png", "image/bmp"}));

    public static boolean a(String str) {
        return b.contains(h.a(str));
    }

    public static boolean b(String str) {
        return a.contains(h.a(str));
    }

    public static boolean a(Uri uri, Context context) {
        return a.contains(context.getContentResolver().getType(uri));
    }

    public static Bitmap a(String str, int i) {
        if (!TextUtils.isEmpty(str)) {
            File file = new File(str);
            if (file.canRead()) {
                int i2 = 0;
                int i3 = 1;
                do {
                    try {
                        Options options = new Options();
                        options.inSampleSize = i3;
                        options.inJustDecodeBounds = false;
                        return BitmapFactory.decodeFile(file.getAbsolutePath(), options);
                    } catch (OutOfMemoryError unused) {
                        i3 = i > 0 ? i : i3 * 2;
                        i2++;
                        if (i2 == 3) {
                        }
                    }
                } while (i2 == 3);
            }
        }
        return null;
    }

    public static Bitmap a(Resources resources, int i, int i2) {
        int i3 = 0;
        int i4 = 1;
        do {
            try {
                Options options = new Options();
                options.inSampleSize = i4;
                options.inJustDecodeBounds = false;
                return BitmapFactory.decodeResource(resources, i, options);
            } catch (OutOfMemoryError unused) {
                i4 = i2 > 0 ? i2 : i4 * 2;
                i3++;
                if (i3 == 3) {
                    return null;
                }
            }
        } while (i3 == 3);
        return null;
    }

    private static Bitmap a(Bitmap bitmap, int i) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        float max = ((float) i) / ((float) Math.max(height, width));
        if (max >= 1.0f) {
            return bitmap;
        }
        return Bitmap.createScaledBitmap(bitmap, (int) (((float) width) * max), (int) (((float) height) * max), true);
    }

    public static void b(String str, int i) {
        if (!TextUtils.isEmpty(str) && i > 0) {
            File file = new File(str);
            Options options = new Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(file.getAbsolutePath(), options);
            float f = (float) options.outWidth;
            float f2 = (float) options.outHeight;
            int sqrt = 100 * ((int) Math.sqrt((double) (((float) (25 * i)) * (f / f2))));
            options.inSampleSize = a(options, sqrt, (int) (((float) sqrt) * (f2 / f)));
            if (options.inSampleSize > 1) {
                options.inJustDecodeBounds = false;
                Bitmap decodeFile = BitmapFactory.decodeFile(str, options);
                if (decodeFile != null) {
                    a(decodeFile, str, h.a(str));
                }
            }
        }
    }

    public static void c(String str, int i) {
        if (!TextUtils.isEmpty(str)) {
            File file = new File(str);
            Options options = new Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(file.getAbsolutePath(), options);
            int i2 = options.outHeight;
            int i3 = options.outWidth;
            float max = ((float) i) / ((float) Math.max(i2, i3));
            if (max < 1.0f) {
                options.inSampleSize = a(options, (int) (((float) i3) * max), (int) (((float) i2) * max));
                options.inJustDecodeBounds = false;
                Bitmap decodeFile = BitmapFactory.decodeFile(str, options);
                if (decodeFile != null) {
                    a(a(decodeFile, i), str, h.a(str));
                }
            }
        }
    }

    public static int a(Options options, int i, int i2) {
        int i3 = options.outHeight;
        int i4 = options.outWidth;
        int i5 = 1;
        if (i3 > i2 || i4 > i) {
            i3 /= 2;
            i4 /= 2;
            while (i3 / i5 > i2 && i4 / i5 > i) {
                i5 *= 2;
            }
        }
        return i5;
    }

    private static void a(Bitmap bitmap, String str, String str2) {
        CompressFormat compressFormat;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        if (str2.contains("png")) {
            compressFormat = CompressFormat.PNG;
        } else {
            compressFormat = CompressFormat.JPEG;
        }
        if (bitmap.compress(compressFormat, 70, byteArrayOutputStream)) {
            byte[] toByteArray = byteArrayOutputStream.toByteArray();
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(str, false);
                fileOutputStream.write(toByteArray);
                fileOutputStream.flush();
                fileOutputStream.close();
                return;
            } catch (IOException e) {
                l.a("Helpshift_ImageUtil", "saveBitmapToFile : ", e);
                return;
            }
        }
        l.c("Helpshift_ImageUtil", "saveBitmapToFile : Compression Failed");
    }
}
