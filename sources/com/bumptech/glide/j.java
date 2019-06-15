package com.bumptech.glide;

import com.bumptech.glide.f.h;
import com.bumptech.glide.request.b.c;
import com.bumptech.glide.request.b.e;

public abstract class j<CHILD extends j<CHILD, TranscodeType>, TranscodeType> implements Cloneable {
    private e<? super TranscodeType> a = c.a();

    private CHILD c() {
        return this;
    }

    public final CHILD a(e<? super TranscodeType> eVar) {
        this.a = (e) h.a((Object) eVar);
        return c();
    }

    /* Access modifiers changed, original: protected|final */
    /* renamed from: a */
    public final CHILD clone() {
        try {
            return (j) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    /* Access modifiers changed, original: final */
    public final e<? super TranscodeType> b() {
        return this.a;
    }
}
