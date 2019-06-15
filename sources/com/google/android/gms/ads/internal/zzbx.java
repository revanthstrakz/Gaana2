package com.google.android.gms.ads.internal;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.Nullable;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.ViewTreeObserver.OnScrollChangedListener;
import android.widget.ViewSwitcher;
import com.google.android.gms.internal.ads.zzaxz;
import com.google.android.gms.internal.ads.zzazc;
import com.google.android.gms.internal.ads.zzbas;
import com.google.android.gms.internal.ads.zzbgg;
import java.util.ArrayList;

public final class zzbx extends ViewSwitcher {
    private final zzazc zzbue;
    @Nullable
    private final zzbas zzbuf;
    private boolean zzbug = true;

    public zzbx(Context context, String str, String str2, OnGlobalLayoutListener onGlobalLayoutListener, OnScrollChangedListener onScrollChangedListener) {
        super(context);
        this.zzbue = new zzazc(context);
        this.zzbue.setAdUnitId(str);
        this.zzbue.zzee(str2);
        if (context instanceof Activity) {
            this.zzbuf = new zzbas((Activity) context, this, onGlobalLayoutListener, onScrollChangedListener);
        } else {
            this.zzbuf = new zzbas(null, this, onGlobalLayoutListener, onScrollChangedListener);
        }
        this.zzbuf.zzaam();
    }

    public final zzazc zzmm() {
        return this.zzbue;
    }

    public final boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (this.zzbug) {
            this.zzbue.zze(motionEvent);
        }
        return false;
    }

    public final void removeAllViews() {
        int i;
        ArrayList arrayList = new ArrayList();
        int i2 = 0;
        for (i = 0; i < getChildCount(); i++) {
            View childAt = getChildAt(i);
            if (childAt != null && (childAt instanceof zzbgg)) {
                arrayList.add((zzbgg) childAt);
            }
        }
        super.removeAllViews();
        arrayList = arrayList;
        i = arrayList.size();
        while (i2 < i) {
            Object obj = arrayList.get(i2);
            i2++;
            ((zzbgg) obj).destroy();
        }
    }

    public final void zzmn() {
        zzaxz.v("Disable position monitoring on adFrame.");
        if (this.zzbuf != null) {
            this.zzbuf.zzaan();
        }
    }

    public final void zzmo() {
        zzaxz.v("Enable debug gesture detector on adFrame.");
        this.zzbug = true;
    }

    public final void zzmp() {
        zzaxz.v("Disable debug gesture detector on adFrame.");
        this.zzbug = false;
    }

    /* Access modifiers changed, original: protected|final */
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.zzbuf != null) {
            this.zzbuf.onAttachedToWindow();
        }
    }

    /* Access modifiers changed, original: protected|final */
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.zzbuf != null) {
            this.zzbuf.onDetachedFromWindow();
        }
    }
}
