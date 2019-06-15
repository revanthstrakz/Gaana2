package com.bumptech.glide.load.resource.bitmap;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import com.bumptech.glide.f.h;
import com.bumptech.glide.load.engine.a.e;
import com.bumptech.glide.load.engine.q;
import com.bumptech.glide.load.f;
import com.bumptech.glide.load.g;
import java.io.IOException;

public class a<DataType> implements g<DataType, BitmapDrawable> {
    private final g<DataType, Bitmap> a;
    private final Resources b;
    private final e c;

    public a(Resources resources, e eVar, g<DataType, Bitmap> gVar) {
        this.b = (Resources) h.a((Object) resources);
        this.c = (e) h.a((Object) eVar);
        this.a = (g) h.a((Object) gVar);
    }

    public boolean a(DataType dataType, f fVar) throws IOException {
        return this.a.a(dataType, fVar);
    }

    public q<BitmapDrawable> a(DataType dataType, int i, int i2, f fVar) throws IOException {
        q a = this.a.a(dataType, i, i2, fVar);
        if (a == null) {
            return null;
        }
        return p.a(this.b, this.c, (Bitmap) a.c());
    }
}
