package com.google.android.gms.tagmanager;

import android.text.TextUtils;
import com.google.android.gms.common.util.VisibleForTesting;

@VisibleForTesting
final class zzbw {
    private final long zzaax;
    private final long zzbcj;
    private final long zzbck;
    private String zzbcl;

    /* Access modifiers changed, original: final */
    public final long zzov() {
        return this.zzbcj;
    }

    /* Access modifiers changed, original: final */
    public final long zzow() {
        return this.zzbck;
    }

    zzbw(long j, long j2, long j3) {
        this.zzbcj = j;
        this.zzaax = j2;
        this.zzbck = j3;
    }

    /* Access modifiers changed, original: final */
    public final String zzox() {
        return this.zzbcl;
    }

    /* Access modifiers changed, original: final */
    public final void zzds(String str) {
        if (str != null && !TextUtils.isEmpty(str.trim())) {
            this.zzbcl = str;
        }
    }
}
