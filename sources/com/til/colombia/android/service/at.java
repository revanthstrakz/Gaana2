package com.til.colombia.android.service;

import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.ImageView;
import com.til.colombia.android.commons.a.a.a;
import com.til.colombia.android.internal.i;
import java.io.ByteArrayInputStream;

final class at implements a {
    final /* synthetic */ ColombiaNativeAudioAdView a;

    at(ColombiaNativeAudioAdView colombiaNativeAudioAdView) {
        this.a = colombiaNativeAudioAdView;
    }

    public final void a(byte[] bArr) {
        if (bArr != null) {
            ((ImageView) this.a.vastResourceView).setImageBitmap(BitmapFactory.decodeStream(new ByteArrayInputStream(bArr)));
            return;
        }
        au auVar = new au(this);
        com.til.colombia.android.utils.a aVar = new com.til.colombia.android.utils.a();
        aVar.a(auVar, this.a.nativeAd.getImageUrl());
        aVar.b = new aw(this);
        try {
            aVar.a();
        } catch (Exception e) {
            String str = i.f;
            StringBuilder stringBuilder = new StringBuilder("is-error:");
            stringBuilder.append(e);
            Log.e(str, stringBuilder.toString());
        }
    }
}
