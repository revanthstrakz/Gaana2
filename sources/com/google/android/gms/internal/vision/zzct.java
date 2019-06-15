package com.google.android.gms.internal.vision;

import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;

public final class zzct {
    private static final zzcu zzlq;
    private static final int zzlr;

    static final class zza extends zzcu {
        zza() {
        }

        public final void zza(Throwable th) {
            ThrowableExtension.printStackTrace(th);
        }
    }

    public static void zza(Throwable th) {
        zzlq.zza(th);
    }

    private static Integer zzbq() {
        try {
            return (Integer) Class.forName("android.os.Build$VERSION").getField("SDK_INT").get(null);
        } catch (Exception e) {
            System.err.println("Failed to retrieve value from android.os.Build$VERSION.SDK_INT due to the following exception.");
            ThrowableExtension.printStackTrace(e, System.err);
            return null;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x0069  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0069  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0069  */
    static {
        /*
        r0 = 1;
        r1 = zzbq();	 Catch:{ Throwable -> 0x002c }
        if (r1 == 0) goto L_0x0017;
    L_0x0007:
        r2 = r1.intValue();	 Catch:{ Throwable -> 0x0015 }
        r3 = 19;
        if (r2 < r3) goto L_0x0017;
    L_0x000f:
        r2 = new com.google.android.gms.internal.vision.zzcy;	 Catch:{ Throwable -> 0x0015 }
        r2.<init>();	 Catch:{ Throwable -> 0x0015 }
        goto L_0x0064;
    L_0x0015:
        r2 = move-exception;
        goto L_0x002e;
    L_0x0017:
        r2 = "com.google.devtools.build.android.desugar.runtime.twr_disable_mimic";
        r2 = java.lang.Boolean.getBoolean(r2);	 Catch:{ Throwable -> 0x0015 }
        r2 = r2 ^ r0;
        if (r2 == 0) goto L_0x0026;
    L_0x0020:
        r2 = new com.google.android.gms.internal.vision.zzcx;	 Catch:{ Throwable -> 0x0015 }
        r2.<init>();	 Catch:{ Throwable -> 0x0015 }
        goto L_0x0064;
    L_0x0026:
        r2 = new com.google.android.gms.internal.vision.zzct$zza;	 Catch:{ Throwable -> 0x0015 }
        r2.<init>();	 Catch:{ Throwable -> 0x0015 }
        goto L_0x0064;
    L_0x002c:
        r2 = move-exception;
        r1 = 0;
    L_0x002e:
        r3 = java.lang.System.err;
        r4 = com.google.android.gms.internal.vision.zzct.zza.class;
        r4 = r4.getName();
        r5 = 132; // 0x84 float:1.85E-43 double:6.5E-322;
        r6 = java.lang.String.valueOf(r4);
        r6 = r6.length();
        r5 = r5 + r6;
        r6 = new java.lang.StringBuilder;
        r6.<init>(r5);
        r5 = "An error has occured when initializing the try-with-resources desuguring strategy. The default strategy ";
        r6.append(r5);
        r6.append(r4);
        r4 = "will be used. The error is: ";
        r6.append(r4);
        r4 = r6.toString();
        r3.println(r4);
        r3 = java.lang.System.err;
        com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r2, r3);
        r2 = new com.google.android.gms.internal.vision.zzct$zza;
        r2.<init>();
    L_0x0064:
        zzlq = r2;
        if (r1 != 0) goto L_0x0069;
    L_0x0068:
        goto L_0x006d;
    L_0x0069:
        r0 = r1.intValue();
    L_0x006d:
        zzlr = r0;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.vision.zzct.<clinit>():void");
    }
}
