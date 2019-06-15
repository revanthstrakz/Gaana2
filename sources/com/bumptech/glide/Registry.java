package com.bumptech.glide;

import android.support.v4.util.Pools.Pool;
import com.bumptech.glide.d.a;
import com.bumptech.glide.d.b;
import com.bumptech.glide.d.c;
import com.bumptech.glide.d.e;
import com.bumptech.glide.d.f;
import com.bumptech.glide.load.ImageHeaderParser;
import com.bumptech.glide.load.a.d;
import com.bumptech.glide.load.b.n;
import com.bumptech.glide.load.b.o;
import com.bumptech.glide.load.b.p;
import com.bumptech.glide.load.engine.q;
import com.bumptech.glide.load.g;
import com.bumptech.glide.load.h;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Registry {
    private final p a = new p(this.j);
    private final a b = new a();
    private final e c = new e();
    private final f d = new f();
    private final d e = new d();
    private final com.bumptech.glide.load.resource.e.e f = new com.bumptech.glide.load.resource.e.e();
    private final b g = new b();
    private final com.bumptech.glide.d.d h = new com.bumptech.glide.d.d();
    private final c i = new c();
    private final Pool<List<Throwable>> j = com.bumptech.glide.f.a.a.a();

    public static class MissingComponentException extends RuntimeException {
        public MissingComponentException(String str) {
            super(str);
        }
    }

    public static final class NoImageHeaderParserException extends MissingComponentException {
        public NoImageHeaderParserException() {
            super("Failed to find image header parser.");
        }
    }

    public static class NoModelLoaderAvailableException extends MissingComponentException {
        public NoModelLoaderAvailableException(Object obj) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Failed to find any ModelLoaders for model: ");
            stringBuilder.append(obj);
            super(stringBuilder.toString());
        }

        public NoModelLoaderAvailableException(Class<?> cls, Class<?> cls2) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Failed to find any ModelLoaders for model: ");
            stringBuilder.append(cls);
            stringBuilder.append(" and data: ");
            stringBuilder.append(cls2);
            super(stringBuilder.toString());
        }
    }

    public static class NoResultEncoderAvailableException extends MissingComponentException {
        public NoResultEncoderAvailableException(Class<?> cls) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Failed to find result encoder for resource class: ");
            stringBuilder.append(cls);
            super(stringBuilder.toString());
        }
    }

    public static class NoSourceEncoderAvailableException extends MissingComponentException {
        public NoSourceEncoderAvailableException(Class<?> cls) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Failed to find source encoder for data class: ");
            stringBuilder.append(cls);
            super(stringBuilder.toString());
        }
    }

    public Registry() {
        a(Arrays.asList(new String[]{"Gif", "Bitmap", "BitmapDrawable"}));
    }

    public <Data> Registry a(Class<Data> cls, com.bumptech.glide.load.a<Data> aVar) {
        this.b.a(cls, aVar);
        return this;
    }

    public <Data, TResource> Registry a(Class<Data> cls, Class<TResource> cls2, g<Data, TResource> gVar) {
        a("legacy_append", cls, cls2, gVar);
        return this;
    }

    public <Data, TResource> Registry a(String str, Class<Data> cls, Class<TResource> cls2, g<Data, TResource> gVar) {
        this.c.a(str, gVar, cls, cls2);
        return this;
    }

    public final Registry a(List<String> list) {
        List arrayList = new ArrayList(list);
        arrayList.add(0, "legacy_prepend_all");
        arrayList.add("legacy_append");
        this.c.a(arrayList);
        return this;
    }

    public <TResource> Registry a(Class<TResource> cls, h<TResource> hVar) {
        this.d.a(cls, hVar);
        return this;
    }

    public Registry a(com.bumptech.glide.load.a.c.a aVar) {
        this.e.a(aVar);
        return this;
    }

    public <TResource, Transcode> Registry a(Class<TResource> cls, Class<Transcode> cls2, com.bumptech.glide.load.resource.e.d<TResource, Transcode> dVar) {
        this.f.a(cls, cls2, dVar);
        return this;
    }

    public Registry a(ImageHeaderParser imageHeaderParser) {
        this.g.a(imageHeaderParser);
        return this;
    }

    public <Model, Data> Registry a(Class<Model> cls, Class<Data> cls2, o<Model, Data> oVar) {
        this.a.a(cls, cls2, oVar);
        return this;
    }

    public <Data, TResource, Transcode> com.bumptech.glide.load.engine.o<Data, TResource, Transcode> a(Class<Data> cls, Class<TResource> cls2, Class<Transcode> cls3) {
        com.bumptech.glide.load.engine.o<Data, TResource, Transcode> b = this.i.b(cls, cls2, cls3);
        if (b == null && !this.i.a(cls, cls2, cls3)) {
            List c = c(cls, cls2, cls3);
            if (c.isEmpty()) {
                b = null;
            } else {
                com.bumptech.glide.load.engine.o<Data, TResource, Transcode> oVar = new com.bumptech.glide.load.engine.o(cls, cls2, cls3, c, this.j);
            }
            this.i.a(cls, cls2, cls3, b);
        }
        return b;
    }

    private <Data, TResource, Transcode> List<com.bumptech.glide.load.engine.f<Data, TResource, Transcode>> c(Class<Data> cls, Class<TResource> cls2, Class<Transcode> cls3) {
        ArrayList arrayList = new ArrayList();
        for (Class cls4 : this.c.b(cls, cls2)) {
            for (Class cls5 : this.f.b(cls4, cls3)) {
                arrayList.add(new com.bumptech.glide.load.engine.f(cls, cls4, cls5, this.c.a(cls, cls4), this.f.a(cls4, cls5), this.j));
            }
        }
        return arrayList;
    }

    public <Model, TResource, Transcode> List<Class<?>> b(Class<Model> cls, Class<TResource> cls2, Class<Transcode> cls3) {
        List<Class<?>> a = this.h.a(cls, cls2);
        if (a == null) {
            a = new ArrayList();
            for (Class b : this.a.a((Class) cls)) {
                for (Class cls4 : this.c.b(b, cls2)) {
                    if (!(this.f.b(cls4, cls3).isEmpty() || a.contains(cls4))) {
                        a.add(cls4);
                    }
                }
            }
            this.h.a(cls, cls2, Collections.unmodifiableList(a));
        }
        return a;
    }

    public boolean a(q<?> qVar) {
        return this.d.a(qVar.b()) != null;
    }

    public <X> h<X> b(q<X> qVar) throws NoResultEncoderAvailableException {
        h a = this.d.a(qVar.b());
        if (a != null) {
            return a;
        }
        throw new NoResultEncoderAvailableException(qVar.b());
    }

    public <X> com.bumptech.glide.load.a<X> a(X x) throws NoSourceEncoderAvailableException {
        com.bumptech.glide.load.a a = this.b.a(x.getClass());
        if (a != null) {
            return a;
        }
        throw new NoSourceEncoderAvailableException(x.getClass());
    }

    public <X> com.bumptech.glide.load.a.c<X> b(X x) {
        return this.e.a((Object) x);
    }

    public <Model> List<n<Model, ?>> c(Model model) {
        List a = this.a.a((Object) model);
        if (!a.isEmpty()) {
            return a;
        }
        throw new NoModelLoaderAvailableException(model);
    }

    public List<ImageHeaderParser> a() {
        List a = this.g.a();
        if (!a.isEmpty()) {
            return a;
        }
        throw new NoImageHeaderParserException();
    }
}
