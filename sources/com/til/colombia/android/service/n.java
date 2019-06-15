package com.til.colombia.android.service;

import android.view.ViewTreeObserver.OnScrollChangedListener;

final class n implements OnScrollChangedListener {
    final /* synthetic */ AdView a;

    n(AdView adView) {
        this.a = adView;
    }

    public final void onScrollChanged() {
        if (this.a.item != null && !((NativeItem) this.a.item).isImpressed() && CmManager.getInstance().isVisible(this.a)) {
            bi.a();
            bi.a(this.a.item);
        }
    }
}
