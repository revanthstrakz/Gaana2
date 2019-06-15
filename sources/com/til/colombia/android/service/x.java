package com.til.colombia.android.service;

import android.view.ViewTreeObserver.OnScrollChangedListener;

final class x implements OnScrollChangedListener {
    final /* synthetic */ v a;

    x(v vVar) {
        this.a = vVar;
    }

    public final void onScrollChanged() {
        if (this.a.q) {
            this.a.q();
        }
    }
}
