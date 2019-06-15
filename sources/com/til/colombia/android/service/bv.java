package com.til.colombia.android.service;

import com.til.colombia.android.commons.USER_ACTION;
import com.til.colombia.android.internal.views.CloseableLayout.a;

final class bv implements a {
    final /* synthetic */ InterstitialActivity a;

    bv(InterstitialActivity interstitialActivity) {
        this.a = interstitialActivity;
    }

    public final void a() {
        this.a.a(USER_ACTION.USER_CLOSED);
    }
}
