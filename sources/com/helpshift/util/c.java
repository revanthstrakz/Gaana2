package com.helpshift.util;

public class c {
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0029  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x002e  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0029  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x002e  */
    public static byte[] a(java.lang.Object r3) throws java.io.IOException {
        /*
        r0 = 0;
        r1 = new java.io.ByteArrayOutputStream;	 Catch:{ all -> 0x0025 }
        r1.<init>();	 Catch:{ all -> 0x0025 }
        r2 = new java.io.ObjectOutputStream;	 Catch:{ all -> 0x0023 }
        r2.<init>(r1);	 Catch:{ all -> 0x0023 }
        r2.writeObject(r3);	 Catch:{ all -> 0x0020 }
        r2.flush();	 Catch:{ all -> 0x0020 }
        r3 = r1.toByteArray();	 Catch:{ all -> 0x0020 }
        if (r2 == 0) goto L_0x001a;
    L_0x0017:
        r2.close();
    L_0x001a:
        if (r1 == 0) goto L_0x001f;
    L_0x001c:
        r1.close();
    L_0x001f:
        return r3;
    L_0x0020:
        r3 = move-exception;
        r0 = r2;
        goto L_0x0027;
    L_0x0023:
        r3 = move-exception;
        goto L_0x0027;
    L_0x0025:
        r3 = move-exception;
        r1 = r0;
    L_0x0027:
        if (r0 == 0) goto L_0x002c;
    L_0x0029:
        r0.close();
    L_0x002c:
        if (r1 == 0) goto L_0x0031;
    L_0x002e:
        r1.close();
    L_0x0031:
        throw r3;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.helpshift.util.c.a(java.lang.Object):byte[]");
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x0025  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x002a  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0025  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x002a  */
    public static java.lang.Object a(byte[] r3) throws java.io.IOException, java.lang.ClassNotFoundException {
        /*
        r0 = 0;
        r1 = new java.io.ByteArrayInputStream;	 Catch:{ all -> 0x0021 }
        r1.<init>(r3);	 Catch:{ all -> 0x0021 }
        r3 = new java.io.ObjectInputStream;	 Catch:{ all -> 0x001f }
        r3.<init>(r1);	 Catch:{ all -> 0x001f }
        r0 = r3.readObject();	 Catch:{ all -> 0x001a }
        if (r1 == 0) goto L_0x0014;
    L_0x0011:
        r1.close();
    L_0x0014:
        if (r3 == 0) goto L_0x0019;
    L_0x0016:
        r3.close();
    L_0x0019:
        return r0;
    L_0x001a:
        r0 = move-exception;
        r2 = r0;
        r0 = r3;
        r3 = r2;
        goto L_0x0023;
    L_0x001f:
        r3 = move-exception;
        goto L_0x0023;
    L_0x0021:
        r3 = move-exception;
        r1 = r0;
    L_0x0023:
        if (r1 == 0) goto L_0x0028;
    L_0x0025:
        r1.close();
    L_0x0028:
        if (r0 == 0) goto L_0x002d;
    L_0x002a:
        r0.close();
    L_0x002d:
        throw r3;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.helpshift.util.c.a(byte[]):java.lang.Object");
    }
}
