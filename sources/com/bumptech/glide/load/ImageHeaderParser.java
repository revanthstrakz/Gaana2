package com.bumptech.glide.load;

import com.bumptech.glide.load.engine.a.b;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public interface ImageHeaderParser {

    public enum ImageType {
        GIF(true),
        JPEG(false),
        RAW(false),
        PNG_A(true),
        PNG(false),
        WEBP_A(true),
        WEBP(false),
        UNKNOWN(false);
        
        private final boolean hasAlpha;

        private ImageType(boolean z) {
            this.hasAlpha = z;
        }

        public boolean hasAlpha() {
            return this.hasAlpha;
        }
    }

    int a(InputStream inputStream, b bVar) throws IOException;

    ImageType a(InputStream inputStream) throws IOException;

    ImageType a(ByteBuffer byteBuffer) throws IOException;
}
