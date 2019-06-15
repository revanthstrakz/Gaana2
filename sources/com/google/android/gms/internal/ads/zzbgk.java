package com.google.android.gms.internal.ads;

import android.view.View;
import android.view.View.OnAttachStateChangeListener;

final class zzbgk implements OnAttachStateChangeListener {
    private final /* synthetic */ zzawr zzeyn;
    private final /* synthetic */ zzbgh zzeyp;

    zzbgk(zzbgh zzbgh, zzawr zzawr) {
        this.zzeyp = zzbgh;
        this.zzeyn = zzawr;
    }

    public final void onViewDetachedFromWindow(View view) {
    }

    public final void onViewAttachedToWindow(View view) {
        this.zzeyp.zza(view, this.zzeyn, 10);
    }
}
