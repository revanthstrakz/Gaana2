package com.til.colombia.android.service;

import android.graphics.Bitmap;
import com.til.colombia.android.internal.Log;
import com.til.colombia.android.internal.i;
import com.til.colombia.android.utils.a.b;

final class ce implements b {
    final /* synthetic */ LeadGenActivity a;

    ce(LeadGenActivity leadGenActivity) {
        this.a = leadGenActivity;
    }

    public final void a(Bitmap bitmap) {
        this.a.runOnUiThread(new cf(this, bitmap));
    }

    public final void a() {
        String str = i.f;
        StringBuilder stringBuilder = new StringBuilder("Image downloading failed for url ");
        stringBuilder.append(this.a.item.getImageUrl());
        Log.a(str, stringBuilder.toString());
    }
}
