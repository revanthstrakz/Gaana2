package com.google.android.gms.common.internal;

import android.os.Looper;
import android.util.Log;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
public final class Asserts {
    @KeepForSdk
    public static void checkNull(Object obj) {
        if (obj != null) {
            throw new IllegalArgumentException("non-null reference");
        }
    }

    @KeepForSdk
    public static void checkNotNull(Object obj) {
        if (obj == null) {
            throw new IllegalArgumentException("null reference");
        }
    }

    @KeepForSdk
    public static void checkNotNull(Object obj, Object obj2) {
        if (obj == null) {
            throw new IllegalArgumentException(String.valueOf(obj2));
        }
    }

    @KeepForSdk
    public static void checkState(boolean z) {
        if (!z) {
            throw new IllegalStateException();
        }
    }

    @KeepForSdk
    public static void checkState(boolean z, Object obj) {
        if (!z) {
            throw new IllegalStateException(String.valueOf(obj));
        }
    }

    @KeepForSdk
    public static void checkMainThread(String str) {
        if (Looper.getMainLooper().getThread() != Thread.currentThread()) {
            String valueOf = String.valueOf(Thread.currentThread());
            String valueOf2 = String.valueOf(Looper.getMainLooper().getThread());
            StringBuilder stringBuilder = new StringBuilder((57 + String.valueOf(valueOf).length()) + String.valueOf(valueOf2).length());
            stringBuilder.append("checkMainThread: current thread ");
            stringBuilder.append(valueOf);
            stringBuilder.append(" IS NOT the main thread ");
            stringBuilder.append(valueOf2);
            stringBuilder.append("!");
            Log.e("Asserts", stringBuilder.toString());
            throw new IllegalStateException(str);
        }
    }

    @KeepForSdk
    public static void checkNotMainThread(String str) {
        if (Looper.getMainLooper().getThread() == Thread.currentThread()) {
            String valueOf = String.valueOf(Thread.currentThread());
            String valueOf2 = String.valueOf(Looper.getMainLooper().getThread());
            StringBuilder stringBuilder = new StringBuilder((56 + String.valueOf(valueOf).length()) + String.valueOf(valueOf2).length());
            stringBuilder.append("checkNotMainThread: current thread ");
            stringBuilder.append(valueOf);
            stringBuilder.append(" IS the main thread ");
            stringBuilder.append(valueOf2);
            stringBuilder.append("!");
            Log.e("Asserts", stringBuilder.toString());
            throw new IllegalStateException(str);
        }
    }

    private Asserts() {
        throw new AssertionError("Uninstantiable");
    }
}
