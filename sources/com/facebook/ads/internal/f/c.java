package com.facebook.ads.internal.f;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.Nullable;
import android.util.Log;
import com.facebook.ads.internal.protocol.AdErrorType;
import com.facebook.ads.internal.s.c.d;
import com.facebook.ads.internal.s.d.a;
import com.facebook.ads.internal.s.d.b;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class c {
    private static final String a = "c";
    private static c b;
    private final Context c;

    private c(Context context) {
        this.c = context;
    }

    private Bitmap a(String str) {
        byte[] d = d.a(this.c).a(str, null).d();
        return BitmapFactory.decodeByteArray(d, 0, d.length);
    }

    public static c a(Context context) {
        if (b == null) {
            context = context.getApplicationContext();
            synchronized (c.class) {
                if (b == null) {
                    b = new c(context);
                }
            }
        }
        return b;
    }

    private static void a(@Nullable Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException unused) {
            }
        }
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:60:0x00d8 in {3, 12, 17, 19, 21, 23, 25, 27, 28, 30, 31, 33, 34, 36, 37, 39, 41, 43, 44, 46, 48, 52, 54, 56, 59, 61, 63} preds:[]
        	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.computeDominators(BlockProcessor.java:242)
        	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.processBlocksTree(BlockProcessor.java:52)
        	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.visit(BlockProcessor.java:42)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
        	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
        	at java.util.ArrayList.forEach(Unknown Source)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
        	at jadx.core.ProcessClass.process(ProcessClass.java:32)
        	at jadx.core.ProcessClass.lambda$processDependencies$0(ProcessClass.java:51)
        	at java.lang.Iterable.forEach(Unknown Source)
        	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:51)
        	at jadx.core.ProcessClass.process(ProcessClass.java:37)
        	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:292)
        	at jadx.api.JavaClass.decompile(JavaClass.java:62)
        	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
        */
    private void a(java.lang.String r6, android.graphics.Bitmap r7) {
        /*
        r5 = this;
        r0 = 0;
        if (r7 != 0) goto L_0x0007;
        r5.a(r0);
        return;
        r1 = new java.io.File;
        r2 = r5.c;
        r2 = r2.getCacheDir();
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r4 = r6.hashCode();
        r3.append(r4);
        r4 = ".png";
        r3.append(r4);
        r3 = r3.toString();
        r1.<init>(r2, r3);
        r2 = new java.io.ByteArrayOutputStream;	 Catch:{ FileNotFoundException -> 0x00b3, IOException -> 0x0089, OutOfMemoryError -> 0x0076, all -> 0x0073 }
        r2.<init>();	 Catch:{ FileNotFoundException -> 0x00b3, IOException -> 0x0089, OutOfMemoryError -> 0x0076, all -> 0x0073 }
        r3 = android.graphics.Bitmap.CompressFormat.PNG;	 Catch:{ FileNotFoundException -> 0x006f, IOException -> 0x006b, OutOfMemoryError -> 0x0067, all -> 0x0062 }
        r4 = 100;	 Catch:{ FileNotFoundException -> 0x006f, IOException -> 0x006b, OutOfMemoryError -> 0x0067, all -> 0x0062 }
        r7.compress(r3, r4, r2);	 Catch:{ FileNotFoundException -> 0x006f, IOException -> 0x006b, OutOfMemoryError -> 0x0067, all -> 0x0062 }
        r7 = r2.size();	 Catch:{ FileNotFoundException -> 0x006f, IOException -> 0x006b, OutOfMemoryError -> 0x0067, all -> 0x0062 }
        r3 = 3145728; // 0x300000 float:4.408104E-39 double:1.554196E-317;	 Catch:{ FileNotFoundException -> 0x006f, IOException -> 0x006b, OutOfMemoryError -> 0x0067, all -> 0x0062 }
        if (r7 < r3) goto L_0x0049;	 Catch:{ FileNotFoundException -> 0x006f, IOException -> 0x006b, OutOfMemoryError -> 0x0067, all -> 0x0062 }
        r7 = a;	 Catch:{ FileNotFoundException -> 0x006f, IOException -> 0x006b, OutOfMemoryError -> 0x0067, all -> 0x0062 }
        r3 = "Bitmap size exceeds max size for storage";	 Catch:{ FileNotFoundException -> 0x006f, IOException -> 0x006b, OutOfMemoryError -> 0x0067, all -> 0x0062 }
        android.util.Log.d(r7, r3);	 Catch:{ FileNotFoundException -> 0x006f, IOException -> 0x006b, OutOfMemoryError -> 0x0067, all -> 0x0062 }
        a(r2);
        a(r0);
        return;
        r7 = new java.io.FileOutputStream;	 Catch:{ FileNotFoundException -> 0x006f, IOException -> 0x006b, OutOfMemoryError -> 0x0067, all -> 0x0062 }
        r7.<init>(r1);	 Catch:{ FileNotFoundException -> 0x006f, IOException -> 0x006b, OutOfMemoryError -> 0x0067, all -> 0x0062 }
        r2.writeTo(r7);	 Catch:{ FileNotFoundException -> 0x0060, IOException -> 0x005c, OutOfMemoryError -> 0x005a, all -> 0x0058 }
        r7.flush();	 Catch:{ FileNotFoundException -> 0x0060, IOException -> 0x005c, OutOfMemoryError -> 0x005a, all -> 0x0058 }
        a(r2);
        goto L_0x0085;
        r6 = move-exception;
        goto L_0x0064;
        r6 = move-exception;
        goto L_0x0069;
        r0 = move-exception;
        r1 = r7;
        r7 = r0;
        goto L_0x006d;
        r6 = move-exception;
        goto L_0x0071;
        r6 = move-exception;
        r7 = r0;
        r0 = r2;
        goto L_0x00da;
        r6 = move-exception;
        r7 = r0;
        r0 = r2;
        goto L_0x0078;
        r7 = move-exception;
        r1 = r0;
        r0 = r2;
        goto L_0x008b;
        r6 = move-exception;
        r7 = r0;
        r0 = r2;
        goto L_0x00b5;
        r6 = move-exception;
        r7 = r0;
        goto L_0x00da;
        r6 = move-exception;
        r7 = r0;
        r5.a(r6);	 Catch:{ all -> 0x00d9 }
        r1 = a;	 Catch:{ all -> 0x00d9 }
        r2 = "Unable to write bitmap to output stream";	 Catch:{ all -> 0x00d9 }
        android.util.Log.e(r1, r2, r6);	 Catch:{ all -> 0x00d9 }
        a(r0);
        a(r7);
        return;
        r7 = move-exception;
        r1 = r0;
        r5.a(r7);	 Catch:{ all -> 0x00b0 }
        r2 = a;	 Catch:{ all -> 0x00b0 }
        r3 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00b0 }
        r3.<init>();	 Catch:{ all -> 0x00b0 }
        r4 = "Unable to write bitmap to file (url=";	 Catch:{ all -> 0x00b0 }
        r3.append(r4);	 Catch:{ all -> 0x00b0 }
        r3.append(r6);	 Catch:{ all -> 0x00b0 }
        r6 = ").";	 Catch:{ all -> 0x00b0 }
        r3.append(r6);	 Catch:{ all -> 0x00b0 }
        r6 = r3.toString();	 Catch:{ all -> 0x00b0 }
        android.util.Log.e(r2, r6, r7);	 Catch:{ all -> 0x00b0 }
        a(r0);
        a(r1);
        return;
        r6 = move-exception;
        r7 = r1;
        goto L_0x00da;
        r6 = move-exception;
        r7 = r0;
        r2 = a;	 Catch:{ all -> 0x00d9 }
        r3 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00d9 }
        r3.<init>();	 Catch:{ all -> 0x00d9 }
        r4 = "Bad output destination (file=";	 Catch:{ all -> 0x00d9 }
        r3.append(r4);	 Catch:{ all -> 0x00d9 }
        r1 = r1.getAbsolutePath();	 Catch:{ all -> 0x00d9 }
        r3.append(r1);	 Catch:{ all -> 0x00d9 }
        r1 = ").";	 Catch:{ all -> 0x00d9 }
        r3.append(r1);	 Catch:{ all -> 0x00d9 }
        r1 = r3.toString();	 Catch:{ all -> 0x00d9 }
        android.util.Log.e(r2, r1, r6);	 Catch:{ all -> 0x00d9 }
        r5.a(r6);	 Catch:{ all -> 0x00d9 }
        goto L_0x0082;
        return;
        r6 = move-exception;
        a(r0);
        a(r7);
        throw r6;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.ads.internal.f.c.a(java.lang.String, android.graphics.Bitmap):void");
    }

    private void a(Throwable th) {
        a.a(this.c, TtmlNode.TAG_IMAGE, b.G, new com.facebook.ads.internal.protocol.b(AdErrorType.IMAGE_CACHE_ERROR, AdErrorType.IMAGE_CACHE_ERROR.getDefaultErrorMessage(), th));
    }

    private boolean a(int i, int i2) {
        return i > 0 && i2 > 0 && com.facebook.ads.internal.n.a.e(this.c);
    }

    @Nullable
    private Bitmap b(String str, int i, int i2) {
        try {
            Bitmap a = a(i, i2) ? com.facebook.ads.internal.s.b.c.a(str.substring("file://".length()), i, i2) : BitmapFactory.decodeStream(new FileInputStream(str.substring("file://".length())), null, null);
            a(str, a);
            return a;
        } catch (IOException e) {
            String str2 = a;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Failed to copy local image into cache (url=");
            stringBuilder.append(str);
            stringBuilder.append(").");
            Log.e(str2, stringBuilder.toString(), e);
            return null;
        }
    }

    /* JADX WARNING: Unknown top exception splitter block from list: {B:24:0x0045=Splitter:B:24:0x0045, B:17:0x003a=Splitter:B:17:0x003a} */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x004a  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x003f  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0052  */
    @android.support.annotation.Nullable
    private android.graphics.Bitmap c(java.lang.String r5, int r6, int r7) {
        /*
        r4 = this;
        r0 = "asset:///";
        r0 = r5.startsWith(r0);
        if (r0 == 0) goto L_0x0056;
    L_0x0008:
        r0 = 0;
        r1 = r4.c;	 Catch:{ IOException -> 0x0043, OutOfMemoryError -> 0x0038, all -> 0x0036 }
        r1 = r1.getAssets();	 Catch:{ IOException -> 0x0043, OutOfMemoryError -> 0x0038, all -> 0x0036 }
        r2 = 9;
        r3 = r5.length();	 Catch:{ IOException -> 0x0043, OutOfMemoryError -> 0x0038, all -> 0x0036 }
        r2 = r5.substring(r2, r3);	 Catch:{ IOException -> 0x0043, OutOfMemoryError -> 0x0038, all -> 0x0036 }
        r1 = r1.open(r2);	 Catch:{ IOException -> 0x0043, OutOfMemoryError -> 0x0038, all -> 0x0036 }
        r2 = r4.a(r6, r7);	 Catch:{ IOException -> 0x0034, OutOfMemoryError -> 0x0032 }
        if (r2 == 0) goto L_0x0028;
    L_0x0023:
        r6 = com.facebook.ads.internal.s.b.c.a(r1, r6, r7);	 Catch:{ IOException -> 0x0034, OutOfMemoryError -> 0x0032 }
        goto L_0x002c;
    L_0x0028:
        r6 = android.graphics.BitmapFactory.decodeStream(r1);	 Catch:{ IOException -> 0x0034, OutOfMemoryError -> 0x0032 }
    L_0x002c:
        if (r1 == 0) goto L_0x0069;
    L_0x002e:
        a(r1);
        goto L_0x0069;
    L_0x0032:
        r5 = move-exception;
        goto L_0x003a;
    L_0x0034:
        r5 = move-exception;
        goto L_0x0045;
    L_0x0036:
        r5 = move-exception;
        goto L_0x0050;
    L_0x0038:
        r5 = move-exception;
        r1 = r0;
    L_0x003a:
        r4.a(r5);	 Catch:{ all -> 0x004e }
        if (r1 == 0) goto L_0x0042;
    L_0x003f:
        a(r1);
    L_0x0042:
        return r0;
    L_0x0043:
        r5 = move-exception;
        r1 = r0;
    L_0x0045:
        r4.a(r5);	 Catch:{ all -> 0x004e }
        if (r1 == 0) goto L_0x004d;
    L_0x004a:
        a(r1);
    L_0x004d:
        return r0;
    L_0x004e:
        r5 = move-exception;
        r0 = r1;
    L_0x0050:
        if (r0 == 0) goto L_0x0055;
    L_0x0052:
        a(r0);
    L_0x0055:
        throw r5;
    L_0x0056:
        r0 = r4.a(r6, r7);
        if (r0 == 0) goto L_0x0065;
    L_0x005c:
        r6 = r4.d(r5, r6, r7);	 Catch:{ IOException -> 0x0061 }
        goto L_0x0069;
    L_0x0061:
        r6 = move-exception;
        r4.a(r6);
    L_0x0065:
        r6 = r4.a(r5);
    L_0x0069:
        r4.a(r5, r6);
        return r6;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.ads.internal.f.c.c(java.lang.String, int, int):android.graphics.Bitmap");
    }

    private Bitmap d(String str, int i, int i2) {
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
        httpURLConnection.setDoInput(true);
        httpURLConnection.connect();
        Closeable inputStream = httpURLConnection.getInputStream();
        Bitmap a = com.facebook.ads.internal.s.b.c.a((InputStream) inputStream, i, i2);
        a(inputStream);
        return a;
    }

    @Nullable
    public Bitmap a(String str, int i, int i2) {
        File cacheDir = this.c.getCacheDir();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str.hashCode());
        stringBuilder.append(".png");
        File file = new File(cacheDir, stringBuilder.toString());
        return !file.exists() ? str.startsWith("file://") ? b(str, i, i2) : c(str, i, i2) : a(i, i2) ? com.facebook.ads.internal.s.b.c.a(file.getAbsolutePath(), i, i2) : BitmapFactory.decodeFile(file.getAbsolutePath());
    }
}
