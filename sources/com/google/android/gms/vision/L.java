package com.google.android.gms.vision;

import android.support.annotation.Keep;
import android.util.Log;

@Keep
public class L {
    public static boolean isLoggable(int i) {
        return 4 <= i;
    }

    public static int zza(String str, Object... objArr) {
        return isLoggable(2) ? Log.v("Vision", String.format(str, objArr)) : 0;
    }

    public static int zzb(String str, Object... objArr) {
        return isLoggable(3) ? Log.d("Vision", String.format(str, objArr)) : 0;
    }

    public static int zzc(String str, Object... objArr) {
        return isLoggable(6) ? Log.e("Vision", String.format(str, objArr)) : 0;
    }

    public static int zza(Throwable th, String str, Object... objArr) {
        if (!isLoggable(6)) {
            return 0;
        }
        if (isLoggable(3)) {
            return Log.e("Vision", String.format(str, objArr), th);
        }
        str = String.format(str, objArr);
        String valueOf = String.valueOf(th);
        StringBuilder stringBuilder = new StringBuilder((2 + String.valueOf(str).length()) + String.valueOf(valueOf).length());
        stringBuilder.append(str);
        stringBuilder.append(": ");
        stringBuilder.append(valueOf);
        return Log.e("Vision", stringBuilder.toString());
    }
}
