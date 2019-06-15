package com.til.colombia.android.service;

import android.view.View;
import android.view.View.OnClickListener;
import com.til.colombia.android.commons.USER_ACTION;

final class bp implements OnClickListener {
    final /* synthetic */ InterstitialActivity a;

    bp(InterstitialActivity interstitialActivity) {
        this.a = interstitialActivity;
    }

    public final void onClick(View view) {
        this.a.a(ck.b, null);
        this.a.n.pause();
        this.a.n.suspend();
        this.a.a(USER_ACTION.AUTO_CLOSED);
        bi.a();
        bi.a(this.a.h, false);
    }
}
