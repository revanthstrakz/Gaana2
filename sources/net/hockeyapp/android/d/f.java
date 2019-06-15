package net.hockeyapp.android.d;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.net.Uri;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class f {
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0016  */
    public static int a(java.io.File r2) throws java.io.IOException {
        /*
        r0 = 0;
        r1 = new java.io.FileInputStream;	 Catch:{ all -> 0x0013 }
        r1.<init>(r2);	 Catch:{ all -> 0x0013 }
        r2 = a(r1);	 Catch:{ all -> 0x0010 }
        if (r1 == 0) goto L_0x000f;
    L_0x000c:
        r1.close();
    L_0x000f:
        return r2;
    L_0x0010:
        r2 = move-exception;
        r0 = r1;
        goto L_0x0014;
    L_0x0013:
        r2 = move-exception;
    L_0x0014:
        if (r0 == 0) goto L_0x0019;
    L_0x0016:
        r0.close();
    L_0x0019:
        throw r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: net.hockeyapp.android.d.f.a(java.io.File):int");
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0019  */
    public static int a(android.content.Context r1, android.net.Uri r2) throws java.io.IOException {
        /*
        r0 = 0;
        r1 = r1.getContentResolver();	 Catch:{ all -> 0x0016 }
        r1 = r1.openInputStream(r2);	 Catch:{ all -> 0x0016 }
        r2 = a(r1);	 Catch:{ all -> 0x0013 }
        if (r1 == 0) goto L_0x0012;
    L_0x000f:
        r1.close();
    L_0x0012:
        return r2;
    L_0x0013:
        r2 = move-exception;
        r0 = r1;
        goto L_0x0017;
    L_0x0016:
        r2 = move-exception;
    L_0x0017:
        if (r0 == 0) goto L_0x001c;
    L_0x0019:
        r0.close();
    L_0x001c:
        throw r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: net.hockeyapp.android.d.f.a(android.content.Context, android.net.Uri):int");
    }

    public static int a(InputStream inputStream) {
        Options options = new Options();
        int i = 1;
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(inputStream, null, options);
        if (options.outWidth == -1 || options.outHeight == -1) {
            return 0;
        }
        if (((float) options.outWidth) / ((float) options.outHeight) <= 1.0f) {
            i = 0;
        }
        return i;
    }

    public static Bitmap a(File file, int i, int i2) throws IOException {
        Options options = new Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(file.getAbsolutePath(), options);
        options.inSampleSize = a(options, i, i2);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(file.getAbsolutePath(), options);
    }

    public static Bitmap a(Context context, Uri uri, int i, int i2) throws IOException {
        Options options = new Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(context.getContentResolver().openInputStream(uri), null, options);
        options.inSampleSize = a(options, i, i2);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeStream(context.getContentResolver().openInputStream(uri), null, options);
    }

    private static int a(Options options, int i, int i2) {
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
}
