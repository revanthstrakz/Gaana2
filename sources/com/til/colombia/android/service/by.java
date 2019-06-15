package com.til.colombia.android.service;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnErrorListener;
import android.os.Bundle;
import com.til.colombia.android.internal.Log;

final class by implements OnErrorListener {
    final /* synthetic */ InterstitialActivity a;

    by(InterstitialActivity interstitialActivity) {
        this.a = interstitialActivity;
    }

    public final boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
        String a = InterstitialActivity.d;
        StringBuilder stringBuilder = new StringBuilder("onError ");
        stringBuilder.append(i);
        stringBuilder.append(" ");
        stringBuilder.append(i2);
        Log.a(a, stringBuilder.toString());
        if (this.a.o != null) {
            this.a.o.dismiss();
        }
        Bundle bundle = new Bundle();
        StringBuilder stringBuilder2 = new StringBuilder("onError ");
        stringBuilder2.append(i);
        stringBuilder2.append(" ");
        stringBuilder2.append(i2);
        bundle.putString("ERROR", stringBuilder2.toString());
        this.a.a(ck.e, bundle);
        this.a.finish();
        return false;
    }
}
