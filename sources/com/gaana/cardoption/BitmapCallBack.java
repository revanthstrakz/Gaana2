package com.gaana.cardoption;

import android.graphics.Bitmap;

public interface BitmapCallBack {
    void onBitmapFailed(Bitmap bitmap);

    void onBitmapReceived(Bitmap bitmap);
}
