package com.bumptech.glide.load.resource.bitmap;

import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.os.ParcelFileDescriptor;
import com.bumptech.glide.load.e;
import com.bumptech.glide.load.engine.q;
import com.bumptech.glide.load.f;
import com.bumptech.glide.load.g;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.security.MessageDigest;

public class v implements g<ParcelFileDescriptor, Bitmap> {
    public static final e<Long> a = e.a("com.bumptech.glide.load.resource.bitmap.VideoBitmapDecode.TargetFrame", Long.valueOf(-1), new com.bumptech.glide.load.e.a<Long>() {
        private final ByteBuffer a = ByteBuffer.allocate(8);

        public void a(byte[] bArr, Long l, MessageDigest messageDigest) {
            messageDigest.update(bArr);
            synchronized (this.a) {
                this.a.position(0);
                messageDigest.update(this.a.putLong(l.longValue()).array());
            }
        }
    });
    public static final e<Integer> b = e.a("com.bumptech.glide.load.resource.bitmap.VideoBitmapDecode.FrameOption", null, new com.bumptech.glide.load.e.a<Integer>() {
        private final ByteBuffer a = ByteBuffer.allocate(4);

        public void a(byte[] bArr, Integer num, MessageDigest messageDigest) {
            if (num != null) {
                messageDigest.update(bArr);
                synchronized (this.a) {
                    this.a.position(0);
                    messageDigest.update(this.a.putInt(num.intValue()).array());
                }
            }
        }
    });
    private static final a c = new a();
    private final com.bumptech.glide.load.engine.a.e d;
    private final a e;

    static class a {
        a() {
        }

        public MediaMetadataRetriever a() {
            return new MediaMetadataRetriever();
        }
    }

    public boolean a(ParcelFileDescriptor parcelFileDescriptor, f fVar) {
        return true;
    }

    public v(com.bumptech.glide.load.engine.a.e eVar) {
        this(eVar, c);
    }

    v(com.bumptech.glide.load.engine.a.e eVar, a aVar) {
        this.d = eVar;
        this.e = aVar;
    }

    public q<Bitmap> a(ParcelFileDescriptor parcelFileDescriptor, int i, int i2, f fVar) throws IOException {
        long longValue = ((Long) fVar.a(a)).longValue();
        if (longValue >= 0 || longValue == -1) {
            Integer num = (Integer) fVar.a(b);
            MediaMetadataRetriever a = this.e.a();
            try {
                Bitmap frameAtTime;
                a.setDataSource(parcelFileDescriptor.getFileDescriptor());
                if (longValue == -1) {
                    frameAtTime = a.getFrameAtTime();
                } else if (num == null) {
                    frameAtTime = a.getFrameAtTime(longValue);
                } else {
                    frameAtTime = a.getFrameAtTime(longValue, num.intValue());
                }
                a.release();
                parcelFileDescriptor.close();
                return d.a(frameAtTime, this.d);
            } catch (RuntimeException e) {
                throw new IOException(e);
            } catch (Throwable th) {
                a.release();
            }
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Requested frame must be non-negative, or DEFAULT_FRAME, given: ");
            stringBuilder.append(longValue);
            throw new IllegalArgumentException(stringBuilder.toString());
        }
    }
}
