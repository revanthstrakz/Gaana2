package com.til.colombia.android.service;

import android.graphics.Bitmap;
import com.til.colombia.android.internal.Log;
import com.til.colombia.android.internal.i;
import com.til.colombia.android.utils.a.b;

final class d implements b {
    final /* synthetic */ Item a;
    final /* synthetic */ b b;

    d(b bVar, Item item) {
        this.b = bVar;
        this.a = item;
    }

    public final void a(Bitmap bitmap) {
        ((NativeItem) this.a).setIcon(bitmap);
    }

    public final void a() {
        String str = i.f;
        StringBuilder stringBuilder = new StringBuilder("Image downloading failed for url ");
        stringBuilder.append(this.a.getImageUrl());
        Log.a(str, stringBuilder.toString());
    }
}
