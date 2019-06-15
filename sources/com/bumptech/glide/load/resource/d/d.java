package com.bumptech.glide.load.resource.d;

import android.util.Log;
import com.bumptech.glide.f.a;
import com.bumptech.glide.load.EncodeStrategy;
import com.bumptech.glide.load.engine.q;
import com.bumptech.glide.load.f;
import com.bumptech.glide.load.h;
import java.io.File;
import java.io.IOException;

public class d implements h<c> {
    public EncodeStrategy a(f fVar) {
        return EncodeStrategy.SOURCE;
    }

    public boolean a(q<c> qVar, File file, f fVar) {
        try {
            a.a(((c) qVar.c()).c(), file);
            return true;
        } catch (IOException e) {
            if (Log.isLoggable("GifEncoder", 5)) {
                Log.w("GifEncoder", "Failed to encode GIF drawable data", e);
            }
            return false;
        }
    }
}
