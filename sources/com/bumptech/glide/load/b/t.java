package com.bumptech.glide.load.b;

import com.bumptech.glide.load.a;
import com.bumptech.glide.load.engine.a.b;
import java.io.InputStream;

public class t implements a<InputStream> {
    private final b a;

    public t(b bVar) {
        this.a = bVar;
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x003a A:{Catch:{ all -> 0x002e }} */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0043 A:{SYNTHETIC, Splitter:B:23:0x0043} */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0050 A:{SYNTHETIC, Splitter:B:29:0x0050} */
    public boolean a(java.io.InputStream r4, java.io.File r5, com.bumptech.glide.load.f r6) {
        /*
        r3 = this;
        r6 = r3.a;
        r0 = byte[].class;
        r1 = 65536; // 0x10000 float:9.18355E-41 double:3.2379E-319;
        r6 = r6.a(r1, r0);
        r6 = (byte[]) r6;
        r0 = 0;
        r1 = 0;
        r2 = new java.io.FileOutputStream;	 Catch:{ IOException -> 0x0030 }
        r2.<init>(r5);	 Catch:{ IOException -> 0x0030 }
    L_0x0013:
        r5 = r4.read(r6);	 Catch:{ IOException -> 0x002b, all -> 0x0028 }
        r1 = -1;
        if (r5 == r1) goto L_0x001e;
    L_0x001a:
        r2.write(r6, r0, r5);	 Catch:{ IOException -> 0x002b, all -> 0x0028 }
        goto L_0x0013;
    L_0x001e:
        r2.close();	 Catch:{ IOException -> 0x002b, all -> 0x0028 }
        r0 = 1;
        if (r2 == 0) goto L_0x0046;
    L_0x0024:
        r2.close();	 Catch:{ IOException -> 0x0046 }
        goto L_0x0046;
    L_0x0028:
        r4 = move-exception;
        r1 = r2;
        goto L_0x004e;
    L_0x002b:
        r4 = move-exception;
        r1 = r2;
        goto L_0x0031;
    L_0x002e:
        r4 = move-exception;
        goto L_0x004e;
    L_0x0030:
        r4 = move-exception;
    L_0x0031:
        r5 = "StreamEncoder";
        r2 = 3;
        r5 = android.util.Log.isLoggable(r5, r2);	 Catch:{ all -> 0x002e }
        if (r5 == 0) goto L_0x0041;
    L_0x003a:
        r5 = "StreamEncoder";
        r2 = "Failed to encode data onto the OutputStream";
        android.util.Log.d(r5, r2, r4);	 Catch:{ all -> 0x002e }
    L_0x0041:
        if (r1 == 0) goto L_0x0046;
    L_0x0043:
        r1.close();	 Catch:{ IOException -> 0x0046 }
    L_0x0046:
        r4 = r3.a;
        r5 = byte[].class;
        r4.a(r6, r5);
        return r0;
    L_0x004e:
        if (r1 == 0) goto L_0x0053;
    L_0x0050:
        r1.close();	 Catch:{ IOException -> 0x0053 }
    L_0x0053:
        r5 = r3.a;
        r0 = byte[].class;
        r5.a(r6, r0);
        throw r4;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.load.b.t.a(java.io.InputStream, java.io.File, com.bumptech.glide.load.f):boolean");
    }
}
