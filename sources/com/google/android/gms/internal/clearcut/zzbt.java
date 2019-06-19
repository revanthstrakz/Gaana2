package com.google.android.gms.internal.clearcut;

import com.google.android.gms.internal.clearcut.zzcg.zzf;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class zzbt {
    private static volatile boolean zzgm;
    private static final Class<?> zzgn = zzam();
    static final zzbt zzgo = new zzbt(true);
    private final Map<Object, zzf<?, ?>> zzgp;

    zzbt() {
        this.zzgp = new HashMap();
    }

    private zzbt(boolean z) {
        this.zzgp = Collections.emptyMap();
    }

    private static Class<?> zzam() {
        try {
            return Class.forName("com.google.protobuf.Extension");
        } catch (ClassNotFoundException unused) {
            return null;
        }
    }

    public static zzbt zzan() {
        return zzbs.zzal();
    }
}
