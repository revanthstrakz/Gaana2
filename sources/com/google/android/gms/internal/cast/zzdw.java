package com.google.android.gms.internal.cast;

import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.internal.Preconditions;
import java.util.Locale;

public final class zzdw {
    private static boolean zzzn;
    private final String mTag;
    private final boolean zzzo;
    private boolean zzzp;
    private boolean zzzq;
    private String zzzr;

    private zzdw(String str, boolean z) {
        Preconditions.checkNotEmpty(str, "The log tag cannot be null or empty.");
        this.mTag = str;
        this.zzzo = str.length() <= 23;
        this.zzzp = false;
        this.zzzq = false;
    }

    public zzdw(String str) {
        this(str, false);
    }

    public final void zzu(String str) {
        if (TextUtils.isEmpty(str)) {
            str = null;
        } else {
            str = String.format("[%s] ", new Object[]{str});
        }
        this.zzzr = str;
    }

    private final boolean zzfb() {
        return this.zzzp || (this.zzzo && Log.isLoggable(this.mTag, 3));
    }

    public final void zzl(boolean z) {
        this.zzzp = true;
    }

    public final void d(String str, Object... objArr) {
        if (zzfb()) {
            Log.d(this.mTag, zza(str, objArr));
        }
    }

    public final void zza(Throwable th, String str, Object... objArr) {
        if (zzfb()) {
            Log.d(this.mTag, zza(str, objArr), th);
        }
    }

    public final void i(String str, Object... objArr) {
        Log.i(this.mTag, zza(str, objArr));
    }

    public final void w(String str, Object... objArr) {
        Log.w(this.mTag, zza(str, objArr));
    }

    public final void zzb(Throwable th, String str, Object... objArr) {
        Log.w(this.mTag, zza(str, objArr), th);
    }

    public final void e(String str, Object... objArr) {
        Log.e(this.mTag, zza(str, objArr));
    }

    public final void zzc(Throwable th, String str, Object... objArr) {
        Log.e(this.mTag, zza(str, objArr), th);
    }

    private final String zza(String str, Object... objArr) {
        if (objArr.length != 0) {
            str = String.format(Locale.ROOT, str, objArr);
        }
        if (TextUtils.isEmpty(this.zzzr)) {
            return str;
        }
        String valueOf = String.valueOf(this.zzzr);
        str = String.valueOf(str);
        return str.length() != 0 ? valueOf.concat(str) : new String(valueOf);
    }
}
