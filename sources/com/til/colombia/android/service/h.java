package com.til.colombia.android.service;

import android.graphics.Bitmap;
import com.til.colombia.android.utils.a.b;

final class h implements b {
    final /* synthetic */ NativeItem a;
    final /* synthetic */ AdRequestResponse b;

    h(AdRequestResponse adRequestResponse, NativeItem nativeItem) {
        this.b = adRequestResponse;
        this.a = nativeItem;
    }

    public final void a(Bitmap bitmap) {
        this.a.setImage(bitmap);
        this.a.setAdImgWidth(bitmap.getWidth());
        this.a.setAdImgHeight(bitmap.getHeight());
        b.a(this.b.colombiaAdRequest, this.b.adListener, this.b.response);
    }

    public final void a() {
        this.b.onItemRequestFailedOnMainThread(new Exception("Error : failed to download media files"));
    }
}
