package com.bumptech.glide.load.engine;

import com.bumptech.glide.load.a;
import com.bumptech.glide.load.engine.b.a.b;
import com.bumptech.glide.load.f;
import java.io.File;

class c<DataType> implements b {
    private final a<DataType> a;
    private final DataType b;
    private final f c;

    c(a<DataType> aVar, DataType dataType, f fVar) {
        this.a = aVar;
        this.b = dataType;
        this.c = fVar;
    }

    public boolean a(File file) {
        return this.a.a(this.b, file, this.c);
    }
}
