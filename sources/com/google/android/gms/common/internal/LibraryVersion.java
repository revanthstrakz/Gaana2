package com.google.android.gms.common.internal;

import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.concurrent.ConcurrentHashMap;

@KeepForSdk
public class LibraryVersion {
    private static final GmsLogger zzel = new GmsLogger("LibraryVersion", "");
    private static LibraryVersion zzem = new LibraryVersion();
    private ConcurrentHashMap<String, String> zzen = new ConcurrentHashMap();

    @KeepForSdk
    public static LibraryVersion getInstance() {
        return zzem;
    }

    @VisibleForTesting
    protected LibraryVersion() {
    }

    /* JADX WARNING: Removed duplicated region for block: B:27:0x00af  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x00a4  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x009f  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x00af  */
    @com.google.android.gms.common.annotation.KeepForSdk
    public java.lang.String getVersion(@android.support.annotation.NonNull java.lang.String r9) {
        /*
        r8 = this;
        r0 = "Please provide a valid libraryName";
        com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r9, r0);
        r0 = r8.zzen;
        r0 = r0.containsKey(r9);
        if (r0 == 0) goto L_0x0016;
    L_0x000d:
        r0 = r8.zzen;
        r9 = r0.get(r9);
        r9 = (java.lang.String) r9;
        return r9;
    L_0x0016:
        r0 = new java.util.Properties;
        r0.<init>();
        r1 = 0;
        r2 = "/%s.properties";
        r3 = 1;
        r3 = new java.lang.Object[r3];	 Catch:{ IOException -> 0x008e }
        r4 = 0;
        r3[r4] = r9;	 Catch:{ IOException -> 0x008e }
        r2 = java.lang.String.format(r2, r3);	 Catch:{ IOException -> 0x008e }
        r3 = com.google.android.gms.common.internal.LibraryVersion.class;
        r2 = r3.getResourceAsStream(r2);	 Catch:{ IOException -> 0x008e }
        if (r2 == 0) goto L_0x006f;
    L_0x0030:
        r0.load(r2);	 Catch:{ IOException -> 0x008e }
        r2 = "version";
        r0 = r0.getProperty(r2, r1);	 Catch:{ IOException -> 0x008e }
        r1 = zzel;	 Catch:{ IOException -> 0x006a }
        r2 = "LibraryVersion";
        r3 = 12;
        r4 = java.lang.String.valueOf(r9);	 Catch:{ IOException -> 0x006a }
        r4 = r4.length();	 Catch:{ IOException -> 0x006a }
        r3 = r3 + r4;
        r4 = java.lang.String.valueOf(r0);	 Catch:{ IOException -> 0x006a }
        r4 = r4.length();	 Catch:{ IOException -> 0x006a }
        r3 = r3 + r4;
        r4 = new java.lang.StringBuilder;	 Catch:{ IOException -> 0x006a }
        r4.<init>(r3);	 Catch:{ IOException -> 0x006a }
        r4.append(r9);	 Catch:{ IOException -> 0x006a }
        r3 = " version is ";
        r4.append(r3);	 Catch:{ IOException -> 0x006a }
        r4.append(r0);	 Catch:{ IOException -> 0x006a }
        r3 = r4.toString();	 Catch:{ IOException -> 0x006a }
        r1.v(r2, r3);	 Catch:{ IOException -> 0x006a }
        r1 = r0;
        goto L_0x00ad;
    L_0x006a:
        r1 = move-exception;
        r7 = r1;
        r1 = r0;
        r0 = r7;
        goto L_0x008f;
    L_0x006f:
        r0 = zzel;	 Catch:{ IOException -> 0x008e }
        r2 = "LibraryVersion";
        r3 = "Failed to get app version for libraryName: ";
        r4 = java.lang.String.valueOf(r9);	 Catch:{ IOException -> 0x008e }
        r5 = r4.length();	 Catch:{ IOException -> 0x008e }
        if (r5 == 0) goto L_0x0084;
    L_0x007f:
        r3 = r3.concat(r4);	 Catch:{ IOException -> 0x008e }
        goto L_0x008a;
    L_0x0084:
        r4 = new java.lang.String;	 Catch:{ IOException -> 0x008e }
        r4.<init>(r3);	 Catch:{ IOException -> 0x008e }
        r3 = r4;
    L_0x008a:
        r0.e(r2, r3);	 Catch:{ IOException -> 0x008e }
        goto L_0x00ad;
    L_0x008e:
        r0 = move-exception;
    L_0x008f:
        r2 = zzel;
        r3 = "LibraryVersion";
        r4 = "Failed to get app version for libraryName: ";
        r5 = java.lang.String.valueOf(r9);
        r6 = r5.length();
        if (r6 == 0) goto L_0x00a4;
    L_0x009f:
        r4 = r4.concat(r5);
        goto L_0x00aa;
    L_0x00a4:
        r5 = new java.lang.String;
        r5.<init>(r4);
        r4 = r5;
    L_0x00aa:
        r2.e(r3, r4, r0);
    L_0x00ad:
        if (r1 != 0) goto L_0x00ba;
    L_0x00af:
        r1 = "UNKNOWN";
        r0 = zzel;
        r2 = "LibraryVersion";
        r3 = ".properties file is dropped during release process. Failure to read app version isexpected druing Google internal testing where locally-built libraries are used";
        r0.d(r2, r3);
    L_0x00ba:
        r0 = r8.zzen;
        r0.put(r9, r1);
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.internal.LibraryVersion.getVersion(java.lang.String):java.lang.String");
    }
}
