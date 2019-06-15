package com.bumptech.glide.load.engine.b;

import android.annotation.SuppressLint;
import com.bumptech.glide.f.e;
import com.bumptech.glide.load.c;
import com.bumptech.glide.load.engine.b.h.a;
import com.bumptech.glide.load.engine.q;

public class g extends e<c, q<?>> implements h {
    private a a;

    public /* bridge */ /* synthetic */ q b(c cVar, q qVar) {
        return (q) super.b(cVar, qVar);
    }

    public g(int i) {
        super(i);
    }

    public void a(a aVar) {
        this.a = aVar;
    }

    /* Access modifiers changed, original: protected */
    public void a(c cVar, q<?> qVar) {
        if (this.a != null) {
            this.a.b(qVar);
        }
    }

    /* Access modifiers changed, original: protected */
    public int a(q<?> qVar) {
        return qVar.d();
    }

    @SuppressLint({"InlinedApi"})
    public void a(int i) {
        if (i >= 40) {
            a();
        } else if (i >= 20) {
            b(b() / 2);
        }
    }
}
