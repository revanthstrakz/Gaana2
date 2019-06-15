package com.bumptech.glide.d;

import com.bumptech.glide.load.ImageHeaderParser;
import java.util.ArrayList;
import java.util.List;

public final class b {
    private final List<ImageHeaderParser> a = new ArrayList();

    public synchronized List<ImageHeaderParser> a() {
        return this.a;
    }

    public synchronized void a(ImageHeaderParser imageHeaderParser) {
        this.a.add(imageHeaderParser);
    }
}
