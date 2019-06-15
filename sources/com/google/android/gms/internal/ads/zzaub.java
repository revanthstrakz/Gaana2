package com.google.android.gms.internal.ads;

import android.content.Context;
import java.util.WeakHashMap;
import java.util.concurrent.Future;

@zzark
public final class zzaub {
    private WeakHashMap<Context, zzaud> zzedp = new WeakHashMap();

    public final Future<zzatz> zzt(Context context) {
        return zzayf.zza(new zzauc(this, context));
    }
}
