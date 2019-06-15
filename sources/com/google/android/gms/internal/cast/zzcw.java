package com.google.android.gms.internal.cast;

import com.google.android.gms.common.util.VisibleForTesting;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@VisibleForTesting
public class zzcw extends zzdc {
    private final List<zzed> zzxt = Collections.synchronizedList(new ArrayList());

    public zzcw(String str, String str2, String str3) {
        super(str, str2, null);
    }

    public void zzeq() {
        synchronized (this.zzxt) {
            for (zzed zzv : this.zzxt) {
                zzv.zzv(2002);
            }
        }
    }

    /* Access modifiers changed, original: protected|final */
    public final List<zzed> zzer() {
        return this.zzxt;
    }

    /* Access modifiers changed, original: protected|final */
    public final void zza(zzed zzed) {
        this.zzxt.add(zzed);
    }
}
