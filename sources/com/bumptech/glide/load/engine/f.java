package com.bumptech.glide.load.engine;

import android.support.v4.util.Pools.Pool;
import android.util.Log;
import com.bumptech.glide.load.a.c;
import com.bumptech.glide.load.g;
import com.bumptech.glide.load.resource.e.d;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class f<DataType, ResourceType, Transcode> {
    private final Class<DataType> a;
    private final List<? extends g<DataType, ResourceType>> b;
    private final d<ResourceType, Transcode> c;
    private final Pool<List<Throwable>> d;
    private final String e;

    interface a<ResourceType> {
        q<ResourceType> a(q<ResourceType> qVar);
    }

    public f(Class<DataType> cls, Class<ResourceType> cls2, Class<Transcode> cls3, List<? extends g<DataType, ResourceType>> list, d<ResourceType, Transcode> dVar, Pool<List<Throwable>> pool) {
        this.a = cls;
        this.b = list;
        this.c = dVar;
        this.d = pool;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Failed DecodePath{");
        stringBuilder.append(cls.getSimpleName());
        stringBuilder.append("->");
        stringBuilder.append(cls2.getSimpleName());
        stringBuilder.append("->");
        stringBuilder.append(cls3.getSimpleName());
        stringBuilder.append("}");
        this.e = stringBuilder.toString();
    }

    public q<Transcode> a(c<DataType> cVar, int i, int i2, com.bumptech.glide.load.f fVar, a<ResourceType> aVar) throws GlideException {
        return this.c.a(aVar.a(a(cVar, i, i2, fVar)), fVar);
    }

    private q<ResourceType> a(c<DataType> cVar, int i, int i2, com.bumptech.glide.load.f fVar) throws GlideException {
        List list = (List) this.d.acquire();
        try {
            q<ResourceType> a = a((c) cVar, i, i2, fVar, list);
            return a;
        } finally {
            this.d.release(list);
        }
    }

    private q<ResourceType> a(c<DataType> cVar, int i, int i2, com.bumptech.glide.load.f fVar, List<Throwable> list) throws GlideException {
        int size = this.b.size();
        q<ResourceType> qVar = null;
        for (int i3 = 0; i3 < size; i3++) {
            g gVar = (g) this.b.get(i3);
            try {
                if (gVar.a(cVar.a(), fVar)) {
                    qVar = gVar.a(cVar.a(), i, i2, fVar);
                }
            } catch (IOException | OutOfMemoryError | RuntimeException e) {
                if (Log.isLoggable("DecodePath", 2)) {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("Failed to decode data for ");
                    stringBuilder.append(gVar);
                    Log.v("DecodePath", stringBuilder.toString(), e);
                }
                list.add(e);
            }
            if (qVar != null) {
                break;
            }
        }
        if (qVar != null) {
            return qVar;
        }
        throw new GlideException(this.e, new ArrayList(list));
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("DecodePath{ dataClass=");
        stringBuilder.append(this.a);
        stringBuilder.append(", decoders=");
        stringBuilder.append(this.b);
        stringBuilder.append(", transcoder=");
        stringBuilder.append(this.c);
        stringBuilder.append('}');
        return stringBuilder.toString();
    }
}
