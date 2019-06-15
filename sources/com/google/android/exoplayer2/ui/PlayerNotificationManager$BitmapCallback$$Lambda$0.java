package com.google.android.exoplayer2.ui;

import android.graphics.Bitmap;
import com.google.android.exoplayer2.ui.PlayerNotificationManager.BitmapCallback;

final /* synthetic */ class PlayerNotificationManager$BitmapCallback$$Lambda$0 implements Runnable {
    private final BitmapCallback arg$1;
    private final Bitmap arg$2;

    PlayerNotificationManager$BitmapCallback$$Lambda$0(BitmapCallback bitmapCallback, Bitmap bitmap) {
        this.arg$1 = bitmapCallback;
        this.arg$2 = bitmap;
    }

    public void run() {
        this.arg$1.lambda$onBitmap$0$PlayerNotificationManager$BitmapCallback(this.arg$2);
    }
}
