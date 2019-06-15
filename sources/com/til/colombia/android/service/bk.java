package com.til.colombia.android.service;

import android.view.ViewTreeObserver.OnScrollChangedListener;

final class bk implements OnScrollChangedListener {
    final /* synthetic */ ContentView a;

    bk(ContentView contentView) {
        this.a = contentView;
    }

    public final void onScrollChanged() {
        if (this.a.cmItem != null && !((NativeItem) this.a.cmItem).isImpressed() && CmManager.getInstance().isVisible(this.a)) {
            bi.a();
            bi.a(this.a.cmItem);
        }
    }
}
