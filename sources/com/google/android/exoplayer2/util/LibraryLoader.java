package com.google.android.exoplayer2.util;

public final class LibraryLoader {
    private boolean isAvailable;
    private boolean loadAttempted;
    private String[] nativeLibraries;

    public LibraryLoader(String... strArr) {
        this.nativeLibraries = strArr;
    }

    public synchronized void setLibraries(String... strArr) {
        Assertions.checkState(this.loadAttempted ^ 1, "Cannot set libraries after loading");
        this.nativeLibraries = strArr;
    }

    /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x001c */
    /* JADX WARNING: Can't wrap try/catch for region: R(12:7|8|9|10|11|(1:13)|22|14|15|16|17|18) */
    public synchronized boolean isAvailable() {
        /*
        r5 = this;
        monitor-enter(r5);
        r0 = r5.loadAttempted;	 Catch:{ all -> 0x0020 }
        if (r0 == 0) goto L_0x0009;
    L_0x0005:
        r0 = r5.isAvailable;	 Catch:{ all -> 0x0020 }
        monitor-exit(r5);
        return r0;
    L_0x0009:
        r0 = 1;
        r5.loadAttempted = r0;	 Catch:{ all -> 0x0020 }
        r1 = r5.nativeLibraries;	 Catch:{ UnsatisfiedLinkError -> 0x001c }
        r2 = r1.length;	 Catch:{ UnsatisfiedLinkError -> 0x001c }
        r3 = 0;
    L_0x0010:
        if (r3 >= r2) goto L_0x001a;
    L_0x0012:
        r4 = r1[r3];	 Catch:{ UnsatisfiedLinkError -> 0x001c }
        java.lang.System.loadLibrary(r4);	 Catch:{ UnsatisfiedLinkError -> 0x001c }
        r3 = r3 + 1;
        goto L_0x0010;
    L_0x001a:
        r5.isAvailable = r0;	 Catch:{ UnsatisfiedLinkError -> 0x001c }
    L_0x001c:
        r0 = r5.isAvailable;	 Catch:{ all -> 0x0020 }
        monitor-exit(r5);
        return r0;
    L_0x0020:
        r0 = move-exception;
        monitor-exit(r5);
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.util.LibraryLoader.isAvailable():boolean");
    }
}
