package com.til.colombia.android.vast;

import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import com.til.colombia.android.commons.a.a.a;
import com.til.colombia.android.internal.i;
import java.io.ByteArrayInputStream;

final class b implements a {
    final /* synthetic */ View a;
    final /* synthetic */ VastCompanionResource b;

    b(VastCompanionResource vastCompanionResource, View view) {
        this.b = vastCompanionResource;
        this.a = view;
    }

    public final void a(byte[] bArr) {
        if (bArr != null) {
            this.b.fillImageView((ImageView) this.a, BitmapFactory.decodeStream(new ByteArrayInputStream(bArr)));
            return;
        }
        c cVar = new c(this);
        com.til.colombia.android.utils.a aVar = new com.til.colombia.android.utils.a();
        aVar.a(cVar, this.b.mResource);
        aVar.b = new e(this);
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
