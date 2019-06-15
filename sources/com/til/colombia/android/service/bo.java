package com.til.colombia.android.service;

import android.view.View;
import android.view.View.OnClickListener;
import com.til.colombia.android.commons.USER_ACTION;
import com.til.colombia.android.network.n;

final class bo implements OnClickListener {
    final /* synthetic */ InterstitialActivity a;

    bo(InterstitialActivity interstitialActivity) {
        this.a = interstitialActivity;
    }

    public final void onClick(View view) {
        n.a(this.a.h.getVastHelper().getVastTrackingByType(8), 5, "video skip tracked.");
        this.a.n.pause();
        this.a.n.suspend();
        this.a.a(USER_ACTION.SKIPPED);
    }
}
