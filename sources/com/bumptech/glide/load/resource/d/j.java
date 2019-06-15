package com.bumptech.glide.load.resource.d;

import android.util.Log;
import com.bumptech.glide.load.ImageHeaderParser;
import com.bumptech.glide.load.ImageHeaderParser.ImageType;
import com.bumptech.glide.load.engine.a.b;
import com.bumptech.glide.load.engine.q;
import com.bumptech.glide.load.f;
import com.bumptech.glide.load.g;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.List;

public class j implements g<InputStream, c> {
    private final List<ImageHeaderParser> a;
    private final g<ByteBuffer, c> b;
    private final b c;

    public j(List<ImageHeaderParser> list, g<ByteBuffer, c> gVar, b bVar) {
        this.a = list;
        this.b = gVar;
        this.c = bVar;
    }

    public boolean a(InputStream inputStream, f fVar) throws IOException {
        return !((Boolean) fVar.a(i.b)).booleanValue() && com.bumptech.glide.load.b.a(this.a, inputStream, this.c) == ImageType.GIF;
    }

    public q<c> a(InputStream inputStream, int i, int i2, f fVar) throws IOException {
        byte[] a = a(inputStream);
        if (a == null) {
            return null;
        }
        return this.b.a(ByteBuffer.wrap(a), i, i2, fVar);
    }

    private static byte[] a(InputStream inputStream) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(16384);
        try {
            byte[] bArr = new byte[16384];
            while (true) {
                int read = inputStream.read(bArr);
                if (read != -1) {
                    byteArrayOutputStream.write(bArr, 0, read);
                } else {
                    byteArrayOutputStream.flush();
                    return byteArrayOutputStream.toByteArray();
                }
            }
        } catch (IOException e) {
            if (Log.isLoggable("StreamGifDecoder", 5)) {
                Log.w("StreamGifDecoder", "Error reading data from stream", e);
            }
            return null;
        }
    }
}
