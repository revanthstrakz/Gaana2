package com.google.android.gms.internal.phenotype;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build.VERSION;

public final class zzh<T> {
    private static final Object zzak = new Object();
    @SuppressLint({"StaticFieldLeak"})
    private static Context zzal;
    private static boolean zzam;
    private static volatile Boolean zzan;
    private static volatile Boolean zzbq;

    public static void init(Context context) {
        synchronized (zzak) {
            if (VERSION.SDK_INT < 24 || !context.isDeviceProtectedStorage()) {
                Context applicationContext = context.getApplicationContext();
                if (applicationContext != null) {
                    context = applicationContext;
                }
            }
            if (zzal != context) {
                zzan = null;
            }
            zzal = context;
        }
        zzam = false;
    }

    public static void maybeInit(Context context) {
        if (zzal == null) {
            init(context);
        }
    }
}
