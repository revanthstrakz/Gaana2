package com.library.custom_glide;

import android.content.Context;
import com.bumptech.glide.c.a;
import com.bumptech.glide.f;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.a.k;
import com.bumptech.glide.load.engine.b.g;
import com.bumptech.glide.load.engine.b.i;
import com.gaana.application.GaanaApplication;

public class GaanaGlide extends a {
    public boolean isManifestParsingEnabled() {
        return false;
    }

    public void applyOptions(Context context, f fVar) {
        fVar.a(new com.bumptech.glide.load.engine.b.f(GaanaApplication.getContext(), 20971520));
        fVar.a(new com.bumptech.glide.request.f().format(DecodeFormat.PREFER_RGB_565).timeout(10000));
        i a = new i.a(context).a();
        int a2 = a.a();
        int b = (int) (0.6d * ((double) a.b()));
        fVar.a(new g((int) (((double) a2) * 0.6d)));
        fVar.a(new k(b));
    }
}
