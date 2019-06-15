package com.bumptech.glide.load.b;

import android.util.Log;
import com.bumptech.glide.load.a;
import com.bumptech.glide.load.f;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;

public class c implements a<ByteBuffer> {
    public boolean a(ByteBuffer byteBuffer, File file, f fVar) {
        try {
            com.bumptech.glide.f.a.a(byteBuffer, file);
            return true;
        } catch (IOException e) {
            if (Log.isLoggable("ByteBufferEncoder", 3)) {
                Log.d("ByteBufferEncoder", "Failed to write data", e);
            }
            return false;
        }
    }
}
