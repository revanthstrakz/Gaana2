package com.google.android.gms.internal.icing;

final class zzcx {
    private static final Class<?> zzgr = zzba();

    private static Class<?> zzba() {
        try {
            return Class.forName("com.google.protobuf.ExtensionRegistry");
        } catch (ClassNotFoundException unused) {
            return null;
        }
    }

    public static zzcy zzbb() {
        if (zzgr != null) {
            try {
                return (zzcy) zzgr.getDeclaredMethod("getEmptyRegistry", new Class[0]).invoke(null, new Object[0]);
            } catch (Exception unused) {
            }
        }
        return zzcy.zzgu;
    }
}
