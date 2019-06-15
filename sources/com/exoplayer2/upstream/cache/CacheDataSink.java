package com.exoplayer2.upstream.cache;

import com.exoplayer2.upstream.cache.Cache.CacheException;
import com.google.android.exoplayer2.upstream.DataSink;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.ReusableBufferedOutputStream;
import com.google.android.exoplayer2.util.Util;
import com.utilities.d;
import com.utilities.i;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public final class CacheDataSink implements DataSink {
    private final Cache a;
    private final long b;
    private final int c;
    private final boolean d;
    private DataSpec e;
    private File f;
    private OutputStream g;
    private FileOutputStream h;
    private long i;
    private long j;
    private ReusableBufferedOutputStream k;

    public static class CacheDataSinkException extends CacheException {
        public CacheDataSinkException(IOException iOException) {
            super((Throwable) iOException);
        }
    }

    public CacheDataSink(Cache cache, long j) {
        this(cache, j, com.google.android.exoplayer2.upstream.cache.CacheDataSink.DEFAULT_BUFFER_SIZE, true);
    }

    public CacheDataSink(Cache cache, long j, int i, boolean z) {
        this.a = (Cache) Assertions.checkNotNull(cache);
        this.b = j;
        this.c = i;
        this.d = z;
    }

    public void open(DataSpec dataSpec) throws CacheDataSinkException {
        if (dataSpec.uri.toString().contains("master.m3u8") || dataSpec.uri.toString().contains("index") || dataSpec.length != -1 || dataSpec.isFlagSet(2)) {
            this.e = dataSpec;
            this.j = 0;
            try {
                a();
                return;
            } catch (IOException e) {
                throw new CacheDataSinkException(e);
            }
        }
        this.e = null;
    }

    public void write(byte[] bArr, int i, int i2) throws CacheDataSinkException {
        if (this.e != null) {
            int i3 = 0;
            while (i3 < i2) {
                try {
                    if (this.i == this.b) {
                        b();
                        a();
                    }
                    int min = (int) Math.min((long) (i2 - i3), this.b - this.i);
                    this.g.write(bArr, i + i3, min);
                    i3 += min;
                    long j = (long) min;
                    this.i += j;
                    this.j += j;
                } catch (IOException e) {
                    throw new CacheDataSinkException(e);
                }
            }
        }
    }

    public void close() throws CacheDataSinkException {
        if (this.e != null) {
            try {
                b();
            } catch (IOException e) {
                throw new CacheDataSinkException(e);
            }
        }
    }

    private void a() throws IOException {
        long j;
        if (this.e.length == -1) {
            j = this.b;
        } else {
            j = Math.min(this.e.length - this.j, this.b);
        }
        this.f = this.a.a(this.e.key, this.e.absoluteStreamPosition + this.j, j);
        this.h = new FileOutputStream(this.f);
        if (this.c > 0) {
            if (this.k == null) {
                this.k = new ReusableBufferedOutputStream(this.h, this.c);
            } else {
                this.k.reset(this.h);
            }
            if (d.b()) {
                this.g = i.a(this.k);
            } else {
                this.g = this.k;
            }
        } else if (d.b()) {
            this.g = i.a(this.h);
        } else {
            this.g = this.h;
        }
        this.i = 0;
    }

    private void b() throws IOException {
        if (this.g != null) {
            try {
                this.g.flush();
                if (this.d) {
                    this.h.getFD().sync();
                }
                Util.closeQuietly(this.g);
                this.g = null;
                File file = this.f;
                this.f = null;
                this.a.a(file);
            } catch (Throwable th) {
                Util.closeQuietly(this.g);
                this.g = null;
                File file2 = this.f;
                this.f = null;
                file2.delete();
            }
        }
    }
}
