package com.bumptech.glide.load.resource.a;

import com.bumptech.glide.load.a.c;
import java.io.IOException;
import java.nio.ByteBuffer;

public class a implements c<ByteBuffer> {
    private final ByteBuffer a;

    public static class a implements com.bumptech.glide.load.a.c.a<ByteBuffer> {
        public c<ByteBuffer> a(ByteBuffer byteBuffer) {
            return new a(byteBuffer);
        }

        public Class<ByteBuffer> a() {
            return ByteBuffer.class;
        }
    }

    public void b() {
    }

    public a(ByteBuffer byteBuffer) {
        this.a = byteBuffer;
    }

    /* renamed from: c */
    public ByteBuffer a() throws IOException {
        this.a.position(0);
        return this.a;
    }
}
