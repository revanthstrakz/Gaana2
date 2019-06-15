package com.til.colombia.android.service;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import com.til.colombia.android.commons.USER_ACTION;
import com.til.colombia.android.internal.Log;
import com.til.colombia.android.network.n;
import com.til.colombia.android.service.ColombiaAdManager.ITEM_TYPE;

final class bx implements OnCompletionListener {
    final /* synthetic */ InterstitialActivity a;

    bx(InterstitialActivity interstitialActivity) {
        this.a = interstitialActivity;
    }

    public final void onCompletion(MediaPlayer mediaPlayer) {
        Log.a(InterstitialActivity.d, "onCompletion");
        n.a(this.a.h.getVastHelper().getVastTrackingByType(6), 5, "video completion tracked.");
        this.a.a(ck.d, null);
        if (this.a.h.getItemType() == ITEM_TYPE.INTERSTITIAL_VIDEO) {
            this.a.a(USER_ACTION.AUTO_CLOSED);
        } else {
            InterstitialActivity.k(this.a);
        }
    }
}
