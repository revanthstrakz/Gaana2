package com.bumptech.glide.load.resource.d;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.util.Log;
import com.bumptech.glide.b.c;
import com.bumptech.glide.b.d;
import com.bumptech.glide.f.i;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.ImageHeaderParser;
import com.bumptech.glide.load.ImageHeaderParser.ImageType;
import com.bumptech.glide.load.engine.a.e;
import com.bumptech.glide.load.f;
import com.bumptech.glide.load.g;
import com.integralads.avid.library.inmobi.utils.AvidJSONUtil;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.Queue;

public class a implements g<ByteBuffer, c> {
    private static final a a = new a();
    private static final b b = new b();
    private final Context c;
    private final List<ImageHeaderParser> d;
    private final b e;
    private final e f;
    private final a g;
    private final b h;

    static class a {
        a() {
        }

        public com.bumptech.glide.b.a a(com.bumptech.glide.b.a.a aVar, c cVar, ByteBuffer byteBuffer, int i) {
            return new com.bumptech.glide.b.e(aVar, cVar, byteBuffer, i);
        }
    }

    static class b {
        private final Queue<d> a = i.a(0);

        b() {
        }

        public synchronized d a(ByteBuffer byteBuffer) {
            d dVar;
            dVar = (d) this.a.poll();
            if (dVar == null) {
                dVar = new d();
            }
            return dVar.a(byteBuffer);
        }

        public synchronized void a(d dVar) {
            dVar.a();
            this.a.offer(dVar);
        }
    }

    public a(Context context, List<ImageHeaderParser> list, e eVar, com.bumptech.glide.load.engine.a.b bVar) {
        this(context, list, eVar, bVar, b, a);
    }

    a(Context context, List<ImageHeaderParser> list, e eVar, com.bumptech.glide.load.engine.a.b bVar, b bVar2, a aVar) {
        this.c = context.getApplicationContext();
        this.d = list;
        this.f = eVar;
        this.g = aVar;
        this.h = new b(eVar, bVar);
        this.e = bVar2;
    }

    public boolean a(ByteBuffer byteBuffer, f fVar) throws IOException {
        return !((Boolean) fVar.a(i.b)).booleanValue() && com.bumptech.glide.load.b.a(this.d, byteBuffer) == ImageType.GIF;
    }

    public e a(ByteBuffer byteBuffer, int i, int i2, f fVar) {
        d a = this.e.a(byteBuffer);
        try {
            e a2 = a(byteBuffer, i, i2, a, fVar);
            return a2;
        } finally {
            this.e.a(a);
        }
    }

    private e a(ByteBuffer byteBuffer, int i, int i2, d dVar, f fVar) {
        long a = com.bumptech.glide.f.d.a();
        c b = dVar.b();
        if (b.c() <= 0 || b.d() != 0) {
            return null;
        }
        int i3 = i2;
        Config config = fVar.a(i.a) == DecodeFormat.PREFER_RGB_565 ? Config.RGB_565 : Config.ARGB_8888;
        int i4 = i;
        com.bumptech.glide.b.a a2 = this.g.a(this.h, b, byteBuffer, a(b, i4, i3));
        a2.a(config);
        a2.b();
        Bitmap h = a2.h();
        if (h == null) {
            return null;
        }
        c cVar = new c(this.c, a2, this.f, com.bumptech.glide.load.resource.b.a(), i4, i3, h);
        if (Log.isLoggable("BufferGifDecoder", 2)) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Decoded GIF from stream in ");
            stringBuilder.append(com.bumptech.glide.f.d.a(a));
            Log.v("BufferGifDecoder", stringBuilder.toString());
        }
        return new e(cVar);
    }

    private static int a(c cVar, int i, int i2) {
        int min = Math.min(cVar.a() / i2, cVar.b() / i);
        if (min == 0) {
            min = 0;
        } else {
            min = Integer.highestOneBit(min);
        }
        min = Math.max(1, min);
        if (Log.isLoggable("BufferGifDecoder", 2) && min > 1) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Downsampling GIF, sampleSize: ");
            stringBuilder.append(min);
            stringBuilder.append(", target dimens: [");
            stringBuilder.append(i);
            stringBuilder.append(AvidJSONUtil.KEY_X);
            stringBuilder.append(i2);
            stringBuilder.append("], actual dimens: [");
            stringBuilder.append(cVar.b());
            stringBuilder.append(AvidJSONUtil.KEY_X);
            stringBuilder.append(cVar.a());
            stringBuilder.append("]");
            Log.v("BufferGifDecoder", stringBuilder.toString());
        }
        return min;
    }
}
