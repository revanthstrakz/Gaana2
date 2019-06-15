package com.til.colombia.android.service;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import com.til.colombia.android.internal.Log;
import com.til.colombia.android.internal.i;
import com.til.colombia.android.utils.a.b;

final class au implements b {
    final /* synthetic */ at a;

    au(at atVar) {
        this.a = atVar;
    }

    public final void a(Bitmap bitmap) {
        new Handler(Looper.getMainLooper()).post(new av(this, bitmap));
    }

    public final void a() {
        String str = i.f;
        StringBuilder stringBuilder = new StringBuilder("AB:Image downloading failed for url ");
        stringBuilder.append(this.a.a.nativeAd.getImageUrl());
        Log.a(str, stringBuilder.toString());
    }
}
