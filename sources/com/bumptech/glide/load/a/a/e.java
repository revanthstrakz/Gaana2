package com.bumptech.glide.load.a.a;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;
import com.bumptech.glide.load.ImageHeaderParser;
import com.bumptech.glide.load.engine.a.b;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

class e {
    private static final a a = new a();
    private final a b;
    private final d c;
    private final b d;
    private final ContentResolver e;
    private final List<ImageHeaderParser> f;

    public e(List<ImageHeaderParser> list, d dVar, b bVar, ContentResolver contentResolver) {
        this(list, a, dVar, bVar, contentResolver);
    }

    public e(List<ImageHeaderParser> list, a aVar, d dVar, b bVar, ContentResolver contentResolver) {
        this.b = aVar;
        this.c = dVar;
        this.d = bVar;
        this.e = contentResolver;
        this.f = list;
    }

    /* JADX WARNING: Removed duplicated region for block: B:27:0x0047 A:{SYNTHETIC, Splitter:B:27:0x0047} */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0027 A:{Catch:{ all -> 0x0044 }} */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x003f A:{SYNTHETIC, Splitter:B:20:0x003f} */
    public int a(android.net.Uri r7) {
        /*
        r6 = this;
        r0 = 0;
        r1 = r6.e;	 Catch:{ IOException | NullPointerException -> 0x001a, IOException | NullPointerException -> 0x001a, all -> 0x0017 }
        r1 = r1.openInputStream(r7);	 Catch:{ IOException | NullPointerException -> 0x001a, IOException | NullPointerException -> 0x001a, all -> 0x0017 }
        r0 = r6.f;	 Catch:{ IOException | NullPointerException -> 0x0015, IOException | NullPointerException -> 0x0015 }
        r2 = r6.d;	 Catch:{ IOException | NullPointerException -> 0x0015, IOException | NullPointerException -> 0x0015 }
        r0 = com.bumptech.glide.load.b.b(r0, r1, r2);	 Catch:{ IOException | NullPointerException -> 0x0015, IOException | NullPointerException -> 0x0015 }
        if (r1 == 0) goto L_0x0014;
    L_0x0011:
        r1.close();	 Catch:{ IOException -> 0x0014 }
    L_0x0014:
        return r0;
    L_0x0015:
        r0 = move-exception;
        goto L_0x001e;
    L_0x0017:
        r7 = move-exception;
        r1 = r0;
        goto L_0x0045;
    L_0x001a:
        r1 = move-exception;
        r5 = r1;
        r1 = r0;
        r0 = r5;
    L_0x001e:
        r2 = "ThumbStreamOpener";
        r3 = 3;
        r2 = android.util.Log.isLoggable(r2, r3);	 Catch:{ all -> 0x0044 }
        if (r2 == 0) goto L_0x003d;
    L_0x0027:
        r2 = "ThumbStreamOpener";
        r3 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0044 }
        r3.<init>();	 Catch:{ all -> 0x0044 }
        r4 = "Failed to open uri: ";
        r3.append(r4);	 Catch:{ all -> 0x0044 }
        r3.append(r7);	 Catch:{ all -> 0x0044 }
        r7 = r3.toString();	 Catch:{ all -> 0x0044 }
        android.util.Log.d(r2, r7, r0);	 Catch:{ all -> 0x0044 }
    L_0x003d:
        if (r1 == 0) goto L_0x0042;
    L_0x003f:
        r1.close();	 Catch:{ IOException -> 0x0042 }
    L_0x0042:
        r7 = -1;
        return r7;
    L_0x0044:
        r7 = move-exception;
    L_0x0045:
        if (r1 == 0) goto L_0x004a;
    L_0x0047:
        r1.close();	 Catch:{ IOException -> 0x004a }
    L_0x004a:
        throw r7;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.load.a.a.e.a(android.net.Uri):int");
    }

    public InputStream b(Uri uri) throws FileNotFoundException {
        Cursor a = this.c.a(uri);
        InputStream inputStream = null;
        if (a != null) {
            try {
                if (a.moveToFirst()) {
                    String string = a.getString(0);
                    if (TextUtils.isEmpty(string)) {
                        return null;
                    }
                    File a2 = this.b.a(string);
                    Uri fromFile = (!this.b.a(a2) || this.b.b(a2) <= 0) ? null : Uri.fromFile(a2);
                    if (a != null) {
                        a.close();
                    }
                    if (fromFile != null) {
                        try {
                            inputStream = this.e.openInputStream(fromFile);
                        } catch (NullPointerException e) {
                            StringBuilder stringBuilder = new StringBuilder();
                            stringBuilder.append("NPE opening uri: ");
                            stringBuilder.append(fromFile);
                            throw ((FileNotFoundException) new FileNotFoundException(stringBuilder.toString()).initCause(e));
                        }
                    }
                    return inputStream;
                }
            } finally {
                if (a != null) {
                    a.close();
                }
            }
        }
        if (a != null) {
            a.close();
        }
        return null;
    }
}
