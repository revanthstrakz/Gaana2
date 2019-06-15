package com.helpshift.support.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.helpshift.conversation.dto.c;
import com.helpshift.support.d;
import com.helpshift.util.h;
import com.helpshift.util.l;
import com.helpshift.util.o;
import com.helpshift.util.p;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

public final class a {
    public static Bitmap a(String str, int i) {
        if (!b(str)) {
            return null;
        }
        Options options = new Options();
        if (i > 0) {
            int i2 = (int) ((((float) options.outHeight) / ((float) options.outWidth)) * ((float) i));
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(str, options);
            options.inSampleSize = p.a(options, i, i2);
        } else {
            options.inSampleSize = 4;
        }
        if (options.inSampleSize < 4) {
            options.inSampleSize++;
        }
        options.inJustDecodeBounds = false;
        Bitmap decodeFile = BitmapFactory.decodeFile(str, options);
        if (decodeFile != null) {
            int c = c(str);
            if (c != 0) {
                Matrix matrix = new Matrix();
                matrix.preRotate((float) c);
                decodeFile = Bitmap.createBitmap(decodeFile, 0, 0, decodeFile.getWidth(), decodeFile.getHeight(), matrix, false);
            }
        }
        return decodeFile;
    }

    private static boolean b(String str) {
        boolean z = false;
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        File file = new File(str);
        if (file.exists() && file.canRead()) {
            z = true;
        }
        return z;
    }

    /* JADX WARNING: Removed duplicated region for block: B:34:0x0073  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x0078  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x0073  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x0078  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x0080  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x0085  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x0080  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x0085  */
    public static java.lang.String a(java.lang.String r7, java.lang.String r8) throws java.io.IOException {
        /*
        r0 = com.helpshift.util.o.b();
        r1 = new com.helpshift.support.d;
        r1.<init>(r0);
        r2 = com.helpshift.util.h.b(r7);
        r3 = 0;
        r8 = b(r8, r2);	 Catch:{ NullPointerException -> 0x0067, all -> 0x0064 }
        r2 = new java.io.File;	 Catch:{ NullPointerException -> 0x0067, all -> 0x0064 }
        r4 = r0.getFilesDir();	 Catch:{ NullPointerException -> 0x0067, all -> 0x0064 }
        r2.<init>(r4, r8);	 Catch:{ NullPointerException -> 0x0067, all -> 0x0064 }
        r4 = r2.getAbsolutePath();	 Catch:{ NullPointerException -> 0x0067, all -> 0x0064 }
        r2 = r2.exists();	 Catch:{ NullPointerException -> 0x0067, all -> 0x0064 }
        if (r2 != 0) goto L_0x0057;
    L_0x0025:
        r1.b(r8);	 Catch:{ NullPointerException -> 0x0067, all -> 0x0064 }
        r1 = new java.io.FileInputStream;	 Catch:{ NullPointerException -> 0x0067, all -> 0x0064 }
        r2 = new java.io.File;	 Catch:{ NullPointerException -> 0x0067, all -> 0x0064 }
        r2.<init>(r7);	 Catch:{ NullPointerException -> 0x0067, all -> 0x0064 }
        r1.<init>(r2);	 Catch:{ NullPointerException -> 0x0067, all -> 0x0064 }
        r7 = 0;
        r8 = r0.openFileOutput(r8, r7);	 Catch:{ NullPointerException -> 0x0054, all -> 0x0052 }
        r0 = 1024; // 0x400 float:1.435E-42 double:5.06E-321;
        r2 = new byte[r0];	 Catch:{ NullPointerException -> 0x0050 }
    L_0x003b:
        r5 = r1.read(r2);	 Catch:{ NullPointerException -> 0x0050 }
        r6 = -1;
        if (r5 == r6) goto L_0x0046;
    L_0x0042:
        r8.write(r2, r7, r5);	 Catch:{ NullPointerException -> 0x0050 }
        goto L_0x003b;
    L_0x0046:
        r7 = com.helpshift.util.p.b(r4);	 Catch:{ NullPointerException -> 0x0050 }
        if (r7 == 0) goto L_0x0059;
    L_0x004c:
        com.helpshift.util.p.c(r4, r0);	 Catch:{ NullPointerException -> 0x0050 }
        goto L_0x0059;
    L_0x0050:
        r7 = move-exception;
        goto L_0x006a;
    L_0x0052:
        r7 = move-exception;
        goto L_0x007e;
    L_0x0054:
        r7 = move-exception;
        r8 = r3;
        goto L_0x006a;
    L_0x0057:
        r8 = r3;
        r1 = r8;
    L_0x0059:
        if (r8 == 0) goto L_0x005e;
    L_0x005b:
        r8.close();
    L_0x005e:
        if (r1 == 0) goto L_0x0063;
    L_0x0060:
        r1.close();
    L_0x0063:
        return r4;
    L_0x0064:
        r7 = move-exception;
        r1 = r3;
        goto L_0x007e;
    L_0x0067:
        r7 = move-exception;
        r8 = r3;
        r1 = r8;
    L_0x006a:
        r0 = "Helpshift_AttachUtil";
        r2 = "NPE";
        com.helpshift.util.l.a(r0, r2, r7);	 Catch:{ all -> 0x007c }
        if (r8 == 0) goto L_0x0076;
    L_0x0073:
        r8.close();
    L_0x0076:
        if (r1 == 0) goto L_0x007b;
    L_0x0078:
        r1.close();
    L_0x007b:
        return r3;
    L_0x007c:
        r7 = move-exception;
        r3 = r8;
    L_0x007e:
        if (r3 == 0) goto L_0x0083;
    L_0x0080:
        r3.close();
    L_0x0083:
        if (r1 == 0) goto L_0x0088;
    L_0x0085:
        r1.close();
    L_0x0088:
        throw r7;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.helpshift.support.util.a.a(java.lang.String, java.lang.String):java.lang.String");
    }

    public static void a(@NonNull c cVar, @Nullable String str) throws IOException {
        Uri uri = (Uri) cVar.a;
        if (uri == null) {
            l.a("Helpshift_AttachUtil", "Can't proceed if uri is null");
            return;
        }
        FileOutputStream fileOutputStream;
        Context b = o.b();
        d dVar = new d(b);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(".");
        stringBuilder.append(h.a(b, uri));
        str = b(str, stringBuilder.toString());
        File file = new File(b.getFilesDir(), str);
        String absolutePath = file.getAbsolutePath();
        InputStream inputStream = null;
        if (file.exists()) {
            fileOutputStream = null;
        } else {
            dVar.b(str);
            inputStream = b.getContentResolver().openInputStream(uri);
            fileOutputStream = b.openFileOutput(str, 0);
            byte[] bArr = new byte[8192];
            while (true) {
                int read = inputStream.read(bArr);
                if (read == -1) {
                    break;
                }
                fileOutputStream.write(bArr, 0, read);
            }
            cVar.b = absolutePath;
            cVar.e = true;
            if (p.b(absolutePath)) {
                p.c(absolutePath, 1024);
            }
        }
        if (fileOutputStream != null) {
            fileOutputStream.close();
        }
        if (inputStream != null) {
            inputStream.close();
        }
    }

    public static String b(@Nullable String str, String str2) {
        if (str == null) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("localRscMessage_");
            stringBuilder.append(UUID.randomUUID().toString());
            str = stringBuilder.toString();
        }
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append(str);
        stringBuilder2.append("0-thumbnail");
        stringBuilder2.append(str2);
        return stringBuilder2.toString();
    }

    public static String a(String str) {
        return str != null ? new File(str).getName() : "";
    }

    private static int c(String str) {
        try {
            String a = h.a(str);
            if (a != null && a.contains("jpeg")) {
                int attributeInt = new ExifInterface(str).getAttributeInt("Orientation", 1);
                if (attributeInt == 6) {
                    return 90;
                }
                if (attributeInt == 3) {
                    return 180;
                }
                if (attributeInt == 8) {
                    return 270;
                }
                return 0;
            }
        } catch (Exception e) {
            l.c("Helpshift_AttachUtil", "Exception in getting exif rotation", e);
        }
        return 0;
    }
}
