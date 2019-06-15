package com.bumptech.glide.load.b.a;

import android.support.annotation.Nullable;
import com.bumptech.glide.load.a.h;
import com.bumptech.glide.load.b.g;
import com.bumptech.glide.load.b.m;
import com.bumptech.glide.load.b.n;
import com.bumptech.glide.load.b.o;
import com.bumptech.glide.load.b.r;
import com.bumptech.glide.load.e;
import com.bumptech.glide.load.f;
import com.google.android.exoplayer2.DefaultLoadControl;
import java.io.InputStream;

public class a implements n<g, InputStream> {
    public static final e<Integer> a = e.a("com.bumptech.glide.load.model.stream.HttpGlideUrlLoader.Timeout", Integer.valueOf(DefaultLoadControl.DEFAULT_BUFFER_FOR_PLAYBACK_MS));
    @Nullable
    private final m<g, g> b;

    public static class a implements o<g, InputStream> {
        private final m<g, g> a = new m(500);

        public n<g, InputStream> a(r rVar) {
            return new a(this.a);
        }
    }

    public boolean a(g gVar) {
        return true;
    }

    public a() {
        this(null);
    }

    public a(m<g, g> mVar) {
        this.b = mVar;
    }

    public com.bumptech.glide.load.b.n.a<InputStream> a(g gVar, int i, int i2, f fVar) {
        if (this.b != null) {
            g gVar2 = (g) this.b.a(gVar, 0, 0);
            if (gVar2 == null) {
                this.b.a(gVar, 0, 0, gVar);
            } else {
                gVar = gVar2;
            }
        }
        return new com.bumptech.glide.load.b.n.a(gVar, new h(gVar, ((Integer) fVar.a(a)).intValue()));
    }
}
