package com.bumptech.glide.load.resource.c;

import com.bumptech.glide.load.engine.q;
import com.bumptech.glide.load.f;
import com.bumptech.glide.load.g;
import java.io.File;

public class a implements g<File, File> {
    public boolean a(File file, f fVar) {
        return true;
    }

    public q<File> a(File file, int i, int i2, f fVar) {
        return new b(file);
    }
}
