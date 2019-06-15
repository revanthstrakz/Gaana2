package com.til.colombia.android.vast;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import com.til.colombia.android.internal.Log;
import com.til.colombia.android.internal.i;
import com.til.colombia.android.utils.a.b;

final class c implements b {
    final /* synthetic */ b a;

    c(b bVar) {
        this.a = bVar;
    }

    public final void a(Bitmap bitmap) {
        new Handler(Looper.getMainLooper()).post(new d(this, bitmap));
    }

    public final void a() {
        String str = i.f;
        StringBuilder stringBuilder = new StringBuilder("AB:Image downloading failed for url ");
        stringBuilder.append(this.a.b.mResource);
        Log.a(str, stringBuilder.toString());
    }
}
