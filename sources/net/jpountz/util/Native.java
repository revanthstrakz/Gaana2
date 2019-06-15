package net.jpountz.util;

public enum Native {
    ;
    
    private static boolean loaded = false;

    private enum OS {
        private static final /* synthetic */ OS[] $VALUES = null;
        public static final OS LINUX = null;
        public static final OS MAC = null;
        public static final OS SOLARIS = null;
        public static final OS WINDOWS = null;
        public final String libExtension;
        public final String name;

        public static OS valueOf(String str) {
            return (OS) Enum.valueOf(OS.class, str);
        }

        public static OS[] values() {
            return (OS[]) $VALUES.clone();
        }

        static {
            WINDOWS = new OS("WINDOWS", 0, "win32", "so");
            LINUX = new OS("LINUX", 1, "linux", "so");
            MAC = new OS("MAC", 2, "darwin", "dylib");
            SOLARIS = new OS("SOLARIS", 3, "solaris", "so");
            $VALUES = new OS[]{WINDOWS, LINUX, MAC, SOLARIS};
        }

        private OS(String str, int i, String str2, String str3) {
            this.name = str2;
            this.libExtension = str3;
        }
    }

    private static String arch() {
        return System.getProperty("os.arch");
    }

    private static OS os() {
        String property = System.getProperty("os.name");
        if (property.contains("Linux")) {
            return OS.LINUX;
        }
        if (property.contains("Mac")) {
            return OS.MAC;
        }
        if (property.contains("Windows")) {
            return OS.WINDOWS;
        }
        if (property.contains("Solaris") || property.contains("SunOS")) {
            return OS.SOLARIS;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Unsupported operating system: ");
        stringBuilder.append(property);
        throw new UnsupportedOperationException(stringBuilder.toString());
    }

    private static String resourceName() {
        OS os = os();
        String replace = Native.class.getPackage().getName().replace('.', '/');
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("/");
        stringBuilder.append(replace);
        stringBuilder.append("/");
        stringBuilder.append(os.name);
        stringBuilder.append("/");
        stringBuilder.append(arch());
        stringBuilder.append("/liblz4-java.");
        stringBuilder.append(os.libExtension);
        return stringBuilder.toString();
    }

    public static synchronized boolean isLoaded() {
        boolean z;
        synchronized (Native.class) {
            z = loaded;
        }
        return z;
    }

    /* JADX WARNING: Missing exception handler attribute for start block: B:12:0x0013 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:62:0x00af */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0078 A:{SYNTHETIC, Splitter:B:31:0x0078} */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x007d A:{SYNTHETIC, Splitter:B:35:0x007d} */
    /* JADX WARNING: Missing exception handler attribute for start block: B:28:0x006d */
    /* JADX WARNING: Can't wrap try/catch for region: R(3:12|13|(2:15|16)(6:17|18|19|20|21|(3:22|23|(9:25|26|27|28|29|(2:31|32)|(3:35|36|(2:38|(1:40)(1:41)))|42|43)(4:44|45|46|47)))) */
    /* JADX WARNING: Can't wrap try/catch for region: R(9:25|26|27|28|29|(2:31|32)|(3:35|36|(2:38|(1:40)(1:41)))|42|43) */
    /* JADX WARNING: Missing block: B:13:?, code skipped:
            r2 = resourceName();
            r3 = net.jpountz.util.Native.class.getResourceAsStream(r2);
     */
    /* JADX WARNING: Missing block: B:14:0x001d, code skipped:
            if (r3 == null) goto L_0x001f;
     */
    /* JADX WARNING: Missing block: B:15:0x001f, code skipped:
            r3 = new java.lang.StringBuilder();
            r3.append("Unsupported OS/arch, cannot find ");
            r3.append(r2);
            r3.append(". Please try building from source.");
     */
    /* JADX WARNING: Missing block: B:16:0x003a, code skipped:
            throw new java.lang.UnsupportedOperationException(r3.toString());
     */
    /* JADX WARNING: Missing block: B:18:?, code skipped:
            r4 = new java.lang.StringBuilder();
            r4.append(".");
            r4.append(os().libExtension);
            r2 = java.io.File.createTempFile("liblz4-java", r4.toString());
            r4 = new java.io.FileOutputStream(r2);
     */
    /* JADX WARNING: Missing block: B:21:?, code skipped:
            r5 = new byte[4096];
     */
    /* JADX WARNING: Missing block: B:22:0x0061, code skipped:
            r6 = r3.read(r5);
     */
    /* JADX WARNING: Missing block: B:24:0x0066, code skipped:
            if (r6 == -1) goto L_0x0068;
     */
    /* JADX WARNING: Missing block: B:26:?, code skipped:
            r4.close();
     */
    /* JADX WARNING: Missing block: B:27:0x006b, code skipped:
            r4 = null;
     */
    /* JADX WARNING: Missing block: B:29:?, code skipped:
            java.lang.System.load(r2.getAbsolutePath());
            loaded = true;
     */
    /* JADX WARNING: Missing block: B:30:0x0076, code skipped:
            if (r4 != null) goto L_0x0078;
     */
    /* JADX WARNING: Missing block: B:32:?, code skipped:
            r4.close();
     */
    /* JADX WARNING: Missing block: B:33:0x007b, code skipped:
            if (r2 != null) goto L_0x007d;
     */
    /* JADX WARNING: Missing block: B:37:0x0081, code skipped:
            if (r2.exists() != false) goto L_0x0083;
     */
    /* JADX WARNING: Missing block: B:39:0x0085, code skipped:
            if (loaded == false) goto L_0x0087;
     */
    /* JADX WARNING: Missing block: B:40:0x0087, code skipped:
            r2.delete();
     */
    /* JADX WARNING: Missing block: B:41:0x008b, code skipped:
            r2.deleteOnExit();
     */
    /* JADX WARNING: Missing block: B:43:0x008f, code skipped:
            return;
     */
    /* JADX WARNING: Missing block: B:46:?, code skipped:
            r4.write(r5, 0, r6);
     */
    /* JADX WARNING: Missing block: B:49:0x0096, code skipped:
            if (r4 != null) goto L_0x0098;
     */
    /* JADX WARNING: Missing block: B:51:?, code skipped:
            r4.close();
     */
    /* JADX WARNING: Missing block: B:52:0x009b, code skipped:
            if (r2 != null) goto L_0x009d;
     */
    /* JADX WARNING: Missing block: B:56:0x00a1, code skipped:
            if (r2.exists() != false) goto L_0x00a3;
     */
    /* JADX WARNING: Missing block: B:58:0x00a5, code skipped:
            if (loaded == false) goto L_0x00a7;
     */
    /* JADX WARNING: Missing block: B:59:0x00a7, code skipped:
            r2.delete();
     */
    /* JADX WARNING: Missing block: B:60:0x00ab, code skipped:
            r2.deleteOnExit();
     */
    public static synchronized void load() {
        /*
        r0 = net.jpountz.util.Native.class;
        monitor-enter(r0);
        r1 = loaded;	 Catch:{ all -> 0x00b7 }
        if (r1 == 0) goto L_0x0009;
    L_0x0007:
        monitor-exit(r0);
        return;
    L_0x0009:
        r1 = 1;
        r2 = "lz4-java";
        java.lang.System.loadLibrary(r2);	 Catch:{ UnsatisfiedLinkError -> 0x0013 }
        loaded = r1;	 Catch:{ UnsatisfiedLinkError -> 0x0013 }
        monitor-exit(r0);
        return;
    L_0x0013:
        r2 = resourceName();	 Catch:{ all -> 0x00b7 }
        r3 = net.jpountz.util.Native.class;
        r3 = r3.getResourceAsStream(r2);	 Catch:{ all -> 0x00b7 }
        if (r3 != 0) goto L_0x003b;
    L_0x001f:
        r1 = new java.lang.UnsupportedOperationException;	 Catch:{ all -> 0x00b7 }
        r3 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00b7 }
        r3.<init>();	 Catch:{ all -> 0x00b7 }
        r4 = "Unsupported OS/arch, cannot find ";
        r3.append(r4);	 Catch:{ all -> 0x00b7 }
        r3.append(r2);	 Catch:{ all -> 0x00b7 }
        r2 = ". Please try building from source.";
        r3.append(r2);	 Catch:{ all -> 0x00b7 }
        r2 = r3.toString();	 Catch:{ all -> 0x00b7 }
        r1.<init>(r2);	 Catch:{ all -> 0x00b7 }
        throw r1;	 Catch:{ all -> 0x00b7 }
    L_0x003b:
        r2 = "liblz4-java";
        r4 = new java.lang.StringBuilder;	 Catch:{ IOException -> 0x00af }
        r4.<init>();	 Catch:{ IOException -> 0x00af }
        r5 = ".";
        r4.append(r5);	 Catch:{ IOException -> 0x00af }
        r5 = os();	 Catch:{ IOException -> 0x00af }
        r5 = r5.libExtension;	 Catch:{ IOException -> 0x00af }
        r4.append(r5);	 Catch:{ IOException -> 0x00af }
        r4 = r4.toString();	 Catch:{ IOException -> 0x00af }
        r2 = java.io.File.createTempFile(r2, r4);	 Catch:{ IOException -> 0x00af }
        r4 = new java.io.FileOutputStream;	 Catch:{ IOException -> 0x00af }
        r4.<init>(r2);	 Catch:{ IOException -> 0x00af }
        r5 = 4096; // 0x1000 float:5.74E-42 double:2.0237E-320;
        r5 = new byte[r5];	 Catch:{ all -> 0x0095 }
    L_0x0061:
        r6 = r3.read(r5);	 Catch:{ all -> 0x0095 }
        r7 = -1;
        if (r6 != r7) goto L_0x0090;
    L_0x0068:
        r4.close();	 Catch:{ IOException -> 0x006d }
        r3 = 0;
        r4 = r3;
    L_0x006d:
        r3 = r2.getAbsolutePath();	 Catch:{ all -> 0x0095 }
        java.lang.System.load(r3);	 Catch:{ all -> 0x0095 }
        loaded = r1;	 Catch:{ all -> 0x0095 }
        if (r4 == 0) goto L_0x007b;
    L_0x0078:
        r4.close();	 Catch:{ IOException -> 0x007b }
    L_0x007b:
        if (r2 == 0) goto L_0x008e;
    L_0x007d:
        r1 = r2.exists();	 Catch:{ IOException -> 0x00af }
        if (r1 == 0) goto L_0x008e;
    L_0x0083:
        r1 = loaded;	 Catch:{ IOException -> 0x00af }
        if (r1 != 0) goto L_0x008b;
    L_0x0087:
        r2.delete();	 Catch:{ IOException -> 0x00af }
        goto L_0x008e;
    L_0x008b:
        r2.deleteOnExit();	 Catch:{ IOException -> 0x00af }
    L_0x008e:
        monitor-exit(r0);
        return;
    L_0x0090:
        r7 = 0;
        r4.write(r5, r7, r6);	 Catch:{ all -> 0x0095 }
        goto L_0x0061;
    L_0x0095:
        r1 = move-exception;
        if (r4 == 0) goto L_0x009b;
    L_0x0098:
        r4.close();	 Catch:{ IOException -> 0x009b }
    L_0x009b:
        if (r2 == 0) goto L_0x00ae;
    L_0x009d:
        r3 = r2.exists();	 Catch:{ IOException -> 0x00af }
        if (r3 == 0) goto L_0x00ae;
    L_0x00a3:
        r3 = loaded;	 Catch:{ IOException -> 0x00af }
        if (r3 != 0) goto L_0x00ab;
    L_0x00a7:
        r2.delete();	 Catch:{ IOException -> 0x00af }
        goto L_0x00ae;
    L_0x00ab:
        r2.deleteOnExit();	 Catch:{ IOException -> 0x00af }
    L_0x00ae:
        throw r1;	 Catch:{ IOException -> 0x00af }
    L_0x00af:
        r1 = new java.lang.ExceptionInInitializerError;	 Catch:{ all -> 0x00b7 }
        r2 = "Cannot unpack liblz4-java";
        r1.<init>(r2);	 Catch:{ all -> 0x00b7 }
        throw r1;	 Catch:{ all -> 0x00b7 }
    L_0x00b7:
        r1 = move-exception;
        monitor-exit(r0);
        throw r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: net.jpountz.util.Native.load():void");
    }
}
