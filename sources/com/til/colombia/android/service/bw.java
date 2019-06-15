package com.til.colombia.android.service;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import com.til.colombia.android.network.n;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

final class bw implements OnPreparedListener {
    final /* synthetic */ InterstitialActivity a;

    bw(InterstitialActivity interstitialActivity) {
        this.a = interstitialActivity;
    }

    public final void onPrepared(MediaPlayer mediaPlayer) {
        this.a.n.seekTo(this.a.a);
        if (this.a.o != null) {
            this.a.o.dismiss();
        }
        if (this.a.a == 0) {
            this.a.z = Executors.newScheduledThreadPool(1);
            this.a.A = new b(this.a, (byte) 0);
            this.a.z.scheduleWithFixedDelay(new bq(this.a), 1000, 1000, TimeUnit.MILLISECONDS);
            ArrayList arrayList = new ArrayList();
            List vastTrackingByType = this.a.h.getVastHelper().getVastTrackingByType(1);
            if (vastTrackingByType != null) {
                arrayList.addAll(vastTrackingByType);
            }
            vastTrackingByType = this.a.h.getVastHelper().getImpressionTrackerUrl();
            if (vastTrackingByType != null) {
                arrayList.addAll(vastTrackingByType);
            }
            n.a(arrayList, 5, "video creative view tracked.");
        }
        this.a.n.start();
        int duration = this.a.n.getDuration();
        if (duration > 0) {
            this.a.h.setDuration(duration);
        }
    }
}
