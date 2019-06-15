package com.bumptech.glide.load.engine;

import android.support.v4.util.Pools.Pool;
import com.bumptech.glide.f.h;
import com.bumptech.glide.load.a.c;
import com.bumptech.glide.load.f;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class o<Data, ResourceType, Transcode> {
    private final Class<Data> a;
    private final Pool<List<Throwable>> b;
    private final List<? extends f<Data, ResourceType, Transcode>> c;
    private final String d;

    public o(Class<Data> cls, Class<ResourceType> cls2, Class<Transcode> cls3, List<f<Data, ResourceType, Transcode>> list, Pool<List<Throwable>> pool) {
        this.a = cls;
        this.b = pool;
        this.c = (List) h.a((Collection) list);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Failed LoadPath{");
        stringBuilder.append(cls.getSimpleName());
        stringBuilder.append("->");
        stringBuilder.append(cls2.getSimpleName());
        stringBuilder.append("->");
        stringBuilder.append(cls3.getSimpleName());
        stringBuilder.append("}");
        this.d = stringBuilder.toString();
    }

    public q<Transcode> a(c<Data> cVar, f fVar, int i, int i2, a<ResourceType> aVar) throws GlideException {
        List list = (List) this.b.acquire();
        try {
            q<Transcode> a = a(cVar, fVar, i, i2, aVar, list);
            return a;
        } finally {
            this.b.release(list);
        }
    }

    private q<Transcode> a(c<Data> cVar, f fVar, int i, int i2, a<ResourceType> aVar, List<Throwable> list) throws GlideException {
        List<Throwable> list2 = list;
        int size = this.c.size();
        q<Transcode> qVar = null;
        for (int i3 = 0; i3 < size; i3++) {
            try {
                qVar = ((f) this.c.get(i3)).a((c) cVar, i, i2, fVar, (a) aVar);
            } catch (GlideException e) {
                list2.add(e);
            }
            if (qVar != null) {
                break;
            }
        }
        if (qVar != null) {
            return qVar;
        }
        throw new GlideException(this.d, new ArrayList(list2));
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("LoadPath{decodePaths=");
        stringBuilder.append(Arrays.toString(this.c.toArray(new f[this.c.size()])));
        stringBuilder.append('}');
        return stringBuilder.toString();
    }
}
