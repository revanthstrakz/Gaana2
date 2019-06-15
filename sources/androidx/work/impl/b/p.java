package androidx.work.impl.b;

import androidx.work.BackoffPolicy;
import androidx.work.NetworkType;
import androidx.work.WorkInfo.State;

public class p {
    public static int a(State state) {
        switch (state) {
            case ENQUEUED:
                return 0;
            case RUNNING:
                return 1;
            case SUCCEEDED:
                return 2;
            case FAILED:
                return 3;
            case BLOCKED:
                return 4;
            case CANCELLED:
                return 5;
            default:
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Could not convert ");
                stringBuilder.append(state);
                stringBuilder.append(" to int");
                throw new IllegalArgumentException(stringBuilder.toString());
        }
    }

    public static State a(int i) {
        switch (i) {
            case 0:
                return State.ENQUEUED;
            case 1:
                return State.RUNNING;
            case 2:
                return State.SUCCEEDED;
            case 3:
                return State.FAILED;
            case 4:
                return State.BLOCKED;
            case 5:
                return State.CANCELLED;
            default:
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Could not convert ");
                stringBuilder.append(i);
                stringBuilder.append(" to State");
                throw new IllegalArgumentException(stringBuilder.toString());
        }
    }

    public static int a(BackoffPolicy backoffPolicy) {
        switch (backoffPolicy) {
            case EXPONENTIAL:
                return 0;
            case LINEAR:
                return 1;
            default:
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Could not convert ");
                stringBuilder.append(backoffPolicy);
                stringBuilder.append(" to int");
                throw new IllegalArgumentException(stringBuilder.toString());
        }
    }

    public static BackoffPolicy b(int i) {
        switch (i) {
            case 0:
                return BackoffPolicy.EXPONENTIAL;
            case 1:
                return BackoffPolicy.LINEAR;
            default:
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Could not convert ");
                stringBuilder.append(i);
                stringBuilder.append(" to BackoffPolicy");
                throw new IllegalArgumentException(stringBuilder.toString());
        }
    }

    public static int a(NetworkType networkType) {
        switch (networkType) {
            case NOT_REQUIRED:
                return 0;
            case CONNECTED:
                return 1;
            case UNMETERED:
                return 2;
            case NOT_ROAMING:
                return 3;
            case METERED:
                return 4;
            default:
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Could not convert ");
                stringBuilder.append(networkType);
                stringBuilder.append(" to int");
                throw new IllegalArgumentException(stringBuilder.toString());
        }
    }

    public static NetworkType c(int i) {
        switch (i) {
            case 0:
                return NetworkType.NOT_REQUIRED;
            case 1:
                return NetworkType.CONNECTED;
            case 2:
                return NetworkType.UNMETERED;
            case 3:
                return NetworkType.NOT_ROAMING;
            case 4:
                return NetworkType.METERED;
            default:
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Could not convert ");
                stringBuilder.append(i);
                stringBuilder.append(" to NetworkType");
                throw new IllegalArgumentException(stringBuilder.toString());
        }
    }

    /* JADX WARNING: Unknown top exception splitter block from list: {B:32:0x0064=Splitter:B:32:0x0064, B:17:0x004a=Splitter:B:17:0x004a} */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x005c A:{SYNTHETIC, Splitter:B:28:0x005c} */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x0073 A:{SYNTHETIC, Splitter:B:39:0x0073} */
    public static byte[] a(androidx.work.c r4) {
        /*
        r0 = r4.b();
        r1 = 0;
        if (r0 != 0) goto L_0x0008;
    L_0x0007:
        return r1;
    L_0x0008:
        r0 = new java.io.ByteArrayOutputStream;
        r0.<init>();
        r2 = new java.io.ObjectOutputStream;	 Catch:{ IOException -> 0x0056 }
        r2.<init>(r0);	 Catch:{ IOException -> 0x0056 }
        r1 = r4.b();	 Catch:{ IOException -> 0x0050, all -> 0x004e }
        r2.writeInt(r1);	 Catch:{ IOException -> 0x0050, all -> 0x004e }
        r4 = r4.a();	 Catch:{ IOException -> 0x0050, all -> 0x004e }
        r4 = r4.iterator();	 Catch:{ IOException -> 0x0050, all -> 0x004e }
    L_0x0021:
        r1 = r4.hasNext();	 Catch:{ IOException -> 0x0050, all -> 0x004e }
        if (r1 == 0) goto L_0x0040;
    L_0x0027:
        r1 = r4.next();	 Catch:{ IOException -> 0x0050, all -> 0x004e }
        r1 = (androidx.work.c.a) r1;	 Catch:{ IOException -> 0x0050, all -> 0x004e }
        r3 = r1.a();	 Catch:{ IOException -> 0x0050, all -> 0x004e }
        r3 = r3.toString();	 Catch:{ IOException -> 0x0050, all -> 0x004e }
        r2.writeUTF(r3);	 Catch:{ IOException -> 0x0050, all -> 0x004e }
        r1 = r1.b();	 Catch:{ IOException -> 0x0050, all -> 0x004e }
        r2.writeBoolean(r1);	 Catch:{ IOException -> 0x0050, all -> 0x004e }
        goto L_0x0021;
    L_0x0040:
        if (r2 == 0) goto L_0x004a;
    L_0x0042:
        r2.close();	 Catch:{ IOException -> 0x0046 }
        goto L_0x004a;
    L_0x0046:
        r4 = move-exception;
        com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r4);
    L_0x004a:
        r0.close();	 Catch:{ IOException -> 0x0068 }
        goto L_0x006c;
    L_0x004e:
        r4 = move-exception;
        goto L_0x0071;
    L_0x0050:
        r4 = move-exception;
        r1 = r2;
        goto L_0x0057;
    L_0x0053:
        r4 = move-exception;
        r2 = r1;
        goto L_0x0071;
    L_0x0056:
        r4 = move-exception;
    L_0x0057:
        com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r4);	 Catch:{ all -> 0x0053 }
        if (r1 == 0) goto L_0x0064;
    L_0x005c:
        r1.close();	 Catch:{ IOException -> 0x0060 }
        goto L_0x0064;
    L_0x0060:
        r4 = move-exception;
        com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r4);
    L_0x0064:
        r0.close();	 Catch:{ IOException -> 0x0068 }
        goto L_0x006c;
    L_0x0068:
        r4 = move-exception;
        com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r4);
    L_0x006c:
        r4 = r0.toByteArray();
        return r4;
    L_0x0071:
        if (r2 == 0) goto L_0x007b;
    L_0x0073:
        r2.close();	 Catch:{ IOException -> 0x0077 }
        goto L_0x007b;
    L_0x0077:
        r1 = move-exception;
        com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r1);
    L_0x007b:
        r0.close();	 Catch:{ IOException -> 0x007f }
        goto L_0x0083;
    L_0x007f:
        r0 = move-exception;
        com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r0);
    L_0x0083:
        throw r4;
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.work.impl.b.p.a(androidx.work.c):byte[]");
    }

    /* JADX WARNING: Unknown top exception splitter block from list: {B:30:0x0050=Splitter:B:30:0x0050, B:16:0x0035=Splitter:B:16:0x0035} */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0048 A:{SYNTHETIC, Splitter:B:26:0x0048} */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x005c A:{SYNTHETIC, Splitter:B:37:0x005c} */
    public static androidx.work.c a(byte[] r6) {
        /*
        r0 = new androidx.work.c;
        r0.<init>();
        if (r6 != 0) goto L_0x0008;
    L_0x0007:
        return r0;
    L_0x0008:
        r1 = new java.io.ByteArrayInputStream;
        r1.<init>(r6);
        r6 = 0;
        r2 = new java.io.ObjectInputStream;	 Catch:{ IOException -> 0x003f, all -> 0x003b }
        r2.<init>(r1);	 Catch:{ IOException -> 0x003f, all -> 0x003b }
        r6 = r2.readInt();	 Catch:{ IOException -> 0x0039 }
    L_0x0017:
        if (r6 <= 0) goto L_0x002b;
    L_0x0019:
        r3 = r2.readUTF();	 Catch:{ IOException -> 0x0039 }
        r3 = android.net.Uri.parse(r3);	 Catch:{ IOException -> 0x0039 }
        r4 = r2.readBoolean();	 Catch:{ IOException -> 0x0039 }
        r0.a(r3, r4);	 Catch:{ IOException -> 0x0039 }
        r6 = r6 + -1;
        goto L_0x0017;
    L_0x002b:
        if (r2 == 0) goto L_0x0035;
    L_0x002d:
        r2.close();	 Catch:{ IOException -> 0x0031 }
        goto L_0x0035;
    L_0x0031:
        r6 = move-exception;
        com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r6);
    L_0x0035:
        r1.close();	 Catch:{ IOException -> 0x0054 }
        goto L_0x0058;
    L_0x0039:
        r6 = move-exception;
        goto L_0x0043;
    L_0x003b:
        r0 = move-exception;
        r2 = r6;
        r6 = r0;
        goto L_0x005a;
    L_0x003f:
        r2 = move-exception;
        r5 = r2;
        r2 = r6;
        r6 = r5;
    L_0x0043:
        com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r6);	 Catch:{ all -> 0x0059 }
        if (r2 == 0) goto L_0x0050;
    L_0x0048:
        r2.close();	 Catch:{ IOException -> 0x004c }
        goto L_0x0050;
    L_0x004c:
        r6 = move-exception;
        com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r6);
    L_0x0050:
        r1.close();	 Catch:{ IOException -> 0x0054 }
        goto L_0x0058;
    L_0x0054:
        r6 = move-exception;
        com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r6);
    L_0x0058:
        return r0;
    L_0x0059:
        r6 = move-exception;
    L_0x005a:
        if (r2 == 0) goto L_0x0064;
    L_0x005c:
        r2.close();	 Catch:{ IOException -> 0x0060 }
        goto L_0x0064;
    L_0x0060:
        r0 = move-exception;
        com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r0);
    L_0x0064:
        r1.close();	 Catch:{ IOException -> 0x0068 }
        goto L_0x006c;
    L_0x0068:
        r0 = move-exception;
        com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r0);
    L_0x006c:
        throw r6;
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.work.impl.b.p.a(byte[]):androidx.work.c");
    }
}
