package com.bumptech.glide.load.resource.bitmap;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import com.bumptech.glide.load.EncodeStrategy;
import com.bumptech.glide.load.e;
import com.bumptech.glide.load.f;
import com.bumptech.glide.load.h;

public class c implements h<Bitmap> {
    public static final e<Integer> a = e.a("com.bumptech.glide.load.resource.bitmap.BitmapEncoder.CompressionQuality", Integer.valueOf(90));
    public static final e<CompressFormat> b = e.a("com.bumptech.glide.load.resource.bitmap.BitmapEncoder.CompressionFormat");

    /* JADX WARNING: Unknown top exception splitter block from list: {B:34:0x00d2=Splitter:B:34:0x00d2, B:25:0x0078=Splitter:B:25:0x0078} */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0081 A:{Catch:{ all -> 0x00d3 }} */
    /* JADX WARNING: Missing exception handler attribute for start block: B:25:0x0078 */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0081 A:{Catch:{ all -> 0x00d3 }} */
    /* JADX WARNING: Missing exception handler attribute for start block: B:34:0x00d2 */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x006c A:{Catch:{ all -> 0x0060 }} */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0075 A:{SYNTHETIC, Splitter:B:23:0x0075} */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0081 A:{Catch:{ all -> 0x00d3 }} */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x00cf A:{SYNTHETIC, Splitter:B:32:0x00cf} */
    /* JADX WARNING: Can't wrap try/catch for region: R(15:0|1|2|3|4|5|6|7|8|(2:10|11)|25|26|(1:28)|29|30) */
    /* JADX WARNING: Can't wrap try/catch for region: R(4:16|(2:32|33)|34|35) */
    public boolean a(com.bumptech.glide.load.engine.q<android.graphics.Bitmap> r8, java.io.File r9, com.bumptech.glide.load.f r10) {
        /*
        r7 = this;
        r8 = r8.c();
        r8 = (android.graphics.Bitmap) r8;
        r0 = r7.a(r8, r10);
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "encode: [";
        r1.append(r2);
        r2 = r8.getWidth();
        r1.append(r2);
        r2 = "x";
        r1.append(r2);
        r2 = r8.getHeight();
        r1.append(r2);
        r2 = "] ";
        r1.append(r2);
        r1.append(r0);
        r1 = r1.toString();
        android.support.v4.os.TraceCompat.beginSection(r1);
        r1 = com.bumptech.glide.f.d.a();	 Catch:{ all -> 0x00d3 }
        r3 = a;	 Catch:{ all -> 0x00d3 }
        r3 = r10.a(r3);	 Catch:{ all -> 0x00d3 }
        r3 = (java.lang.Integer) r3;	 Catch:{ all -> 0x00d3 }
        r3 = r3.intValue();	 Catch:{ all -> 0x00d3 }
        r4 = 0;
        r5 = 0;
        r6 = new java.io.FileOutputStream;	 Catch:{ IOException -> 0x0062 }
        r6.<init>(r9);	 Catch:{ IOException -> 0x0062 }
        r8.compress(r0, r3, r6);	 Catch:{ IOException -> 0x005d, all -> 0x005a }
        r6.close();	 Catch:{ IOException -> 0x005d, all -> 0x005a }
        r4 = 1;
        if (r6 == 0) goto L_0x0078;
    L_0x0056:
        r6.close();	 Catch:{ IOException -> 0x0078 }
        goto L_0x0078;
    L_0x005a:
        r8 = move-exception;
        r5 = r6;
        goto L_0x00cd;
    L_0x005d:
        r9 = move-exception;
        r5 = r6;
        goto L_0x0063;
    L_0x0060:
        r8 = move-exception;
        goto L_0x00cd;
    L_0x0062:
        r9 = move-exception;
    L_0x0063:
        r3 = "BitmapEncoder";
        r6 = 3;
        r3 = android.util.Log.isLoggable(r3, r6);	 Catch:{ all -> 0x0060 }
        if (r3 == 0) goto L_0x0073;
    L_0x006c:
        r3 = "BitmapEncoder";
        r6 = "Failed to encode Bitmap";
        android.util.Log.d(r3, r6, r9);	 Catch:{ all -> 0x0060 }
    L_0x0073:
        if (r5 == 0) goto L_0x0078;
    L_0x0075:
        r5.close();	 Catch:{ IOException -> 0x0078 }
    L_0x0078:
        r9 = "BitmapEncoder";
        r3 = 2;
        r9 = android.util.Log.isLoggable(r9, r3);	 Catch:{ all -> 0x00d3 }
        if (r9 == 0) goto L_0x00c9;
    L_0x0081:
        r9 = "BitmapEncoder";
        r3 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00d3 }
        r3.<init>();	 Catch:{ all -> 0x00d3 }
        r5 = "Compressed with type: ";
        r3.append(r5);	 Catch:{ all -> 0x00d3 }
        r3.append(r0);	 Catch:{ all -> 0x00d3 }
        r0 = " of size ";
        r3.append(r0);	 Catch:{ all -> 0x00d3 }
        r0 = com.bumptech.glide.f.i.a(r8);	 Catch:{ all -> 0x00d3 }
        r3.append(r0);	 Catch:{ all -> 0x00d3 }
        r0 = " in ";
        r3.append(r0);	 Catch:{ all -> 0x00d3 }
        r0 = com.bumptech.glide.f.d.a(r1);	 Catch:{ all -> 0x00d3 }
        r3.append(r0);	 Catch:{ all -> 0x00d3 }
        r0 = ", options format: ";
        r3.append(r0);	 Catch:{ all -> 0x00d3 }
        r0 = b;	 Catch:{ all -> 0x00d3 }
        r10 = r10.a(r0);	 Catch:{ all -> 0x00d3 }
        r3.append(r10);	 Catch:{ all -> 0x00d3 }
        r10 = ", hasAlpha: ";
        r3.append(r10);	 Catch:{ all -> 0x00d3 }
        r8 = r8.hasAlpha();	 Catch:{ all -> 0x00d3 }
        r3.append(r8);	 Catch:{ all -> 0x00d3 }
        r8 = r3.toString();	 Catch:{ all -> 0x00d3 }
        android.util.Log.v(r9, r8);	 Catch:{ all -> 0x00d3 }
    L_0x00c9:
        android.support.v4.os.TraceCompat.endSection();
        return r4;
    L_0x00cd:
        if (r5 == 0) goto L_0x00d2;
    L_0x00cf:
        r5.close();	 Catch:{ IOException -> 0x00d2 }
    L_0x00d2:
        throw r8;	 Catch:{ all -> 0x00d3 }
    L_0x00d3:
        r8 = move-exception;
        android.support.v4.os.TraceCompat.endSection();
        throw r8;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.load.resource.bitmap.c.a(com.bumptech.glide.load.engine.q, java.io.File, com.bumptech.glide.load.f):boolean");
    }

    private CompressFormat a(Bitmap bitmap, f fVar) {
        CompressFormat compressFormat = (CompressFormat) fVar.a(b);
        if (compressFormat != null) {
            return compressFormat;
        }
        if (bitmap.hasAlpha()) {
            return CompressFormat.PNG;
        }
        return CompressFormat.JPEG;
    }

    public EncodeStrategy a(f fVar) {
        return EncodeStrategy.TRANSFORMED;
    }
}
