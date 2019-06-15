package com.google.android.gms.internal.measurement;

import android.content.Context;
import com.google.android.gms.common.internal.Preconditions;

public final class zzay {
    private final Context zzwq;
    private final Context zzwr;

    public zzay(Context context) {
        Preconditions.checkNotNull(context);
        context = context.getApplicationContext();
        Preconditions.checkNotNull(context, "Application context can't be null");
        this.zzwq = context;
        this.zzwr = context;
    }

    public final Context getApplicationContext() {
        return this.zzwq;
    }

    public final Context zzcm() {
        return this.zzwr;
    }
}
