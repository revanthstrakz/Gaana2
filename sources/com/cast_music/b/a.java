package com.cast_music.b;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build.VERSION;

public abstract class a extends AsyncTask<Uri, Void, Bitmap> {
    private final int a;
    private final int b;
    private final boolean c;

    public a(int i, int i2, boolean z) {
        this.a = i;
        this.b = i2;
        this.c = z;
    }

    public a(int i, int i2) {
        this(i, i2, false);
    }

    public a() {
        this(0, 0);
    }

    /* Access modifiers changed, original: protected|varargs */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x009b  */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x00a0 A:{SYNTHETIC, Splitter:B:63:0x00a0} */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x008e  */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x0093 A:{SYNTHETIC, Splitter:B:54:0x0093} */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x009b  */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x00a0 A:{SYNTHETIC, Splitter:B:63:0x00a0} */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x008e  */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x0093 A:{SYNTHETIC, Splitter:B:54:0x0093} */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x009b  */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x00a0 A:{SYNTHETIC, Splitter:B:63:0x00a0} */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0073 A:{ExcHandler: all (th java.lang.Throwable), Splitter:B:21:0x005c} */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing block: B:32:0x0073, code skipped:
            r8 = th;
     */
    /* JADX WARNING: Missing block: B:33:0x0074, code skipped:
            r1 = r0;
     */
    /* renamed from: a */
    public android.graphics.Bitmap doInBackground(android.net.Uri... r8) {
        /*
        r7 = this;
        r0 = 1;
        r1 = 0;
        r2 = r8.length;
        if (r2 != r0) goto L_0x00a5;
    L_0x0005:
        r2 = 0;
        r3 = r8[r2];
        if (r3 != 0) goto L_0x000c;
    L_0x000a:
        goto L_0x00a5;
    L_0x000c:
        r3 = new java.net.URL;	 Catch:{ MalformedURLException -> 0x00a4 }
        r8 = r8[r2];	 Catch:{ MalformedURLException -> 0x00a4 }
        r8 = r8.toString();	 Catch:{ MalformedURLException -> 0x00a4 }
        r3.<init>(r8);	 Catch:{ MalformedURLException -> 0x00a4 }
        r8 = new android.graphics.BitmapFactory$Options;
        r8.<init>();
        r8.inJustDecodeBounds = r2;
        r8.inSampleSize = r0;
        r2 = r7.a;
        if (r2 <= 0) goto L_0x0042;
    L_0x0024:
        r2 = r7.b;
        if (r2 <= 0) goto L_0x0042;
    L_0x0028:
        r2 = r7.a(r3);
        r4 = r2.x;
        if (r4 <= 0) goto L_0x0042;
    L_0x0030:
        r4 = r2.y;
        if (r4 <= 0) goto L_0x0042;
    L_0x0034:
        r4 = r2.x;
        r2 = r2.y;
        r5 = r7.a;
        r6 = r7.b;
        r2 = r7.a(r4, r2, r5, r6);
        r8.inSampleSize = r2;
    L_0x0042:
        r2 = r3.openConnection();	 Catch:{ IOException -> 0x0097, all -> 0x008a }
        r2 = (java.net.HttpURLConnection) r2;	 Catch:{ IOException -> 0x0097, all -> 0x008a }
        r2.setDoInput(r0);	 Catch:{ IOException -> 0x0088, all -> 0x0086 }
        r0 = r2.getResponseCode();	 Catch:{ IOException -> 0x0088, all -> 0x0086 }
        r3 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        if (r0 != r3) goto L_0x0079;
    L_0x0053:
        r0 = new java.io.BufferedInputStream;	 Catch:{ IOException -> 0x0088, all -> 0x0086 }
        r3 = r2.getInputStream();	 Catch:{ IOException -> 0x0088, all -> 0x0086 }
        r0.<init>(r3);	 Catch:{ IOException -> 0x0088, all -> 0x0086 }
        r8 = android.graphics.BitmapFactory.decodeStream(r0, r1, r8);	 Catch:{ IOException -> 0x0076, all -> 0x0073 }
        r1 = r7.a;	 Catch:{ IOException -> 0x0077, all -> 0x0073 }
        if (r1 <= 0) goto L_0x0071;
    L_0x0064:
        r1 = r7.b;	 Catch:{ IOException -> 0x0077, all -> 0x0073 }
        if (r1 <= 0) goto L_0x0071;
    L_0x0068:
        r1 = r7.c;	 Catch:{ IOException -> 0x0077, all -> 0x0073 }
        if (r1 == 0) goto L_0x0071;
    L_0x006c:
        r1 = r7.a(r8);	 Catch:{ IOException -> 0x0077, all -> 0x0073 }
        goto L_0x007a;
    L_0x0071:
        r1 = r8;
        goto L_0x007a;
    L_0x0073:
        r8 = move-exception;
        r1 = r0;
        goto L_0x008c;
    L_0x0076:
        r8 = r1;
    L_0x0077:
        r1 = r0;
        goto L_0x0099;
    L_0x0079:
        r0 = r1;
    L_0x007a:
        if (r2 == 0) goto L_0x007f;
    L_0x007c:
        r2.disconnect();
    L_0x007f:
        if (r0 == 0) goto L_0x0084;
    L_0x0081:
        r0.close();	 Catch:{ IOException -> 0x0084 }
    L_0x0084:
        r8 = r1;
        goto L_0x00a3;
    L_0x0086:
        r8 = move-exception;
        goto L_0x008c;
    L_0x0088:
        r8 = r1;
        goto L_0x0099;
    L_0x008a:
        r8 = move-exception;
        r2 = r1;
    L_0x008c:
        if (r2 == 0) goto L_0x0091;
    L_0x008e:
        r2.disconnect();
    L_0x0091:
        if (r1 == 0) goto L_0x0096;
    L_0x0093:
        r1.close();	 Catch:{ IOException -> 0x0096 }
    L_0x0096:
        throw r8;
    L_0x0097:
        r8 = r1;
        r2 = r8;
    L_0x0099:
        if (r2 == 0) goto L_0x009e;
    L_0x009b:
        r2.disconnect();
    L_0x009e:
        if (r1 == 0) goto L_0x00a3;
    L_0x00a0:
        r1.close();	 Catch:{ IOException -> 0x00a3 }
    L_0x00a3:
        return r8;
    L_0x00a4:
        return r1;
    L_0x00a5:
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cast_music.b.a.doInBackground(android.net.Uri[]):android.graphics.Bitmap");
    }

    @TargetApi(11)
    public void a(Uri uri) {
        if (VERSION.SDK_INT >= 11) {
            executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Uri[]{uri});
            return;
        }
        execute(new Uri[]{uri});
    }

    private Bitmap a(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int i = height - this.b;
        if (width - this.a == 0 && i == 0) {
            return bitmap;
        }
        float f = (float) width;
        float f2 = (float) height;
        float min = Math.min(((float) this.b) / f2, ((float) this.a) / f);
        return Bitmap.createScaledBitmap(bitmap, (int) ((f * min) + 0.5f), (int) ((f2 * min) + 0.5f), false);
    }

    /* JADX WARNING: Removed duplicated region for block: B:32:0x0046  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x004b A:{SYNTHETIC, Splitter:B:34:0x004b} */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0039  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x003e A:{SYNTHETIC, Splitter:B:24:0x003e} */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0046  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x004b A:{SYNTHETIC, Splitter:B:34:0x004b} */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0039  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x003e A:{SYNTHETIC, Splitter:B:24:0x003e} */
    private android.graphics.Point a(java.net.URL r6) {
        /*
        r5 = this;
        r0 = new android.graphics.BitmapFactory$Options;
        r0.<init>();
        r1 = 1;
        r0.inJustDecodeBounds = r1;
        r1 = 0;
        r0.inSampleSize = r1;
        r2 = 0;
        r6 = r6.openConnection();	 Catch:{ IOException -> 0x0042, all -> 0x0034 }
        r6 = (java.net.HttpURLConnection) r6;	 Catch:{ IOException -> 0x0042, all -> 0x0034 }
        r3 = r6.getInputStream();	 Catch:{ IOException -> 0x0032, all -> 0x002f }
        android.graphics.BitmapFactory.decodeStream(r3, r2, r0);	 Catch:{ IOException -> 0x0044, all -> 0x002d }
        r2 = new android.graphics.Point;	 Catch:{ IOException -> 0x0044, all -> 0x002d }
        r4 = r0.outWidth;	 Catch:{ IOException -> 0x0044, all -> 0x002d }
        r0 = r0.outHeight;	 Catch:{ IOException -> 0x0044, all -> 0x002d }
        r2.<init>(r4, r0);	 Catch:{ IOException -> 0x0044, all -> 0x002d }
        if (r6 == 0) goto L_0x0027;
    L_0x0024:
        r6.disconnect();
    L_0x0027:
        if (r3 == 0) goto L_0x002c;
    L_0x0029:
        r3.close();	 Catch:{ IOException -> 0x002c }
    L_0x002c:
        return r2;
    L_0x002d:
        r0 = move-exception;
        goto L_0x0037;
    L_0x002f:
        r0 = move-exception;
        r3 = r2;
        goto L_0x0037;
    L_0x0032:
        r3 = r2;
        goto L_0x0044;
    L_0x0034:
        r0 = move-exception;
        r6 = r2;
        r3 = r6;
    L_0x0037:
        if (r6 == 0) goto L_0x003c;
    L_0x0039:
        r6.disconnect();
    L_0x003c:
        if (r3 == 0) goto L_0x0041;
    L_0x003e:
        r3.close();	 Catch:{ IOException -> 0x0041 }
    L_0x0041:
        throw r0;
    L_0x0042:
        r6 = r2;
        r3 = r6;
    L_0x0044:
        if (r6 == 0) goto L_0x0049;
    L_0x0046:
        r6.disconnect();
    L_0x0049:
        if (r3 == 0) goto L_0x004e;
    L_0x004b:
        r3.close();	 Catch:{ IOException -> 0x004e }
    L_0x004e:
        r6 = new android.graphics.Point;
        r6.<init>(r1, r1);
        return r6;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cast_music.b.a.a(java.net.URL):android.graphics.Point");
    }

    private int a(int i, int i2, int i3, int i4) {
        int i5 = 1;
        if (i2 > i4 || i > i3) {
            i2 /= 2;
            i /= 2;
            while (i2 / i5 > i4 && i / i5 > i3) {
                i5 *= 2;
            }
        }
        return i5;
    }
}
