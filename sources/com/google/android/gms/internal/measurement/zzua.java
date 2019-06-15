package com.google.android.gms.internal.measurement;

final class zzua {
    private static final Class<?> zzbuz = zzvn();

    private static Class<?> zzvn() {
        try {
            return Class.forName("com.google.protobuf.ExtensionRegistry");
        } catch (ClassNotFoundException unused) {
            return null;
        }
    }

    public static zzub zzvo() {
        if (zzbuz != null) {
            try {
                return zzge("getEmptyRegistry");
            } catch (Exception unused) {
            }
        }
        return zzub.zzbvd;
    }

    /* JADX WARNING: Removed duplicated region for block: B:7:0x000e  */
    /* JADX WARNING: Removed duplicated region for block: B:11:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x0014  */
    static com.google.android.gms.internal.measurement.zzub zzvp() {
        /*
        r0 = zzbuz;
        if (r0 == 0) goto L_0x000b;
    L_0x0004:
        r0 = "loadGeneratedRegistry";
        r0 = zzge(r0);	 Catch:{ Exception -> 0x000b }
        goto L_0x000c;
    L_0x000b:
        r0 = 0;
    L_0x000c:
        if (r0 != 0) goto L_0x0012;
    L_0x000e:
        r0 = com.google.android.gms.internal.measurement.zzub.zzvp();
    L_0x0012:
        if (r0 != 0) goto L_0x0018;
    L_0x0014:
        r0 = zzvo();
    L_0x0018:
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzua.zzvp():com.google.android.gms.internal.measurement.zzub");
    }

    private static final zzub zzge(String str) throws Exception {
        return (zzub) zzbuz.getDeclaredMethod(str, new Class[0]).invoke(null, new Object[0]);
    }
}
