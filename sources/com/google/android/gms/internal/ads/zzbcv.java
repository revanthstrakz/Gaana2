package com.google.android.gms.internal.ads;

import android.view.View;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnScrollChangedListener;
import java.lang.ref.WeakReference;

@zzark
final class zzbcv extends zzbcw implements OnScrollChangedListener {
    private final WeakReference<OnScrollChangedListener> zzeqc;

    public zzbcv(View view, OnScrollChangedListener onScrollChangedListener) {
        super(view);
        this.zzeqc = new WeakReference(onScrollChangedListener);
    }

    public final void onScrollChanged() {
        OnScrollChangedListener onScrollChangedListener = (OnScrollChangedListener) this.zzeqc.get();
        if (onScrollChangedListener != null) {
            onScrollChangedListener.onScrollChanged();
        } else {
            detach();
        }
    }

    /* Access modifiers changed, original: protected|final */
    public final void zza(ViewTreeObserver viewTreeObserver) {
        viewTreeObserver.addOnScrollChangedListener(this);
    }

    /* Access modifiers changed, original: protected|final */
    public final void zzb(ViewTreeObserver viewTreeObserver) {
        viewTreeObserver.removeOnScrollChangedListener(this);
    }
}
