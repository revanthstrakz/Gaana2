package com.google.android.gms.internal.cast;

import android.support.annotation.NonNull;
import android.text.TextUtils;

public class zzdc {
    protected final zzdw zzxw;
    private final String zzxx;
    private zzeb zzxy;

    protected zzdc(String str, String str2, String str3) {
        zzdk.zzp(str);
        this.zzxx = str;
        this.zzxw = new zzdw(str2);
        setSessionLabel(str3);
    }

    public void zza(long j, int i) {
    }

    public void zzeq() {
    }

    public void zzo(@NonNull String str) {
    }

    public final void setSessionLabel(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.zzxw.zzu(str);
        }
    }

    public final String getNamespace() {
        return this.zzxx;
    }

    public final void zza(zzeb zzeb) {
        this.zzxy = zzeb;
        if (this.zzxy == null) {
            zzeq();
        }
    }

    /* Access modifiers changed, original: protected|final */
    public final void zza(String str, long j, String str2) throws IllegalStateException {
        Object[] objArr = new Object[]{str, null};
        this.zzxy.zza(this.zzxx, str, j, null);
    }

    /* Access modifiers changed, original: protected|final */
    public final long zzes() {
        return this.zzxy.zzr();
    }
}
