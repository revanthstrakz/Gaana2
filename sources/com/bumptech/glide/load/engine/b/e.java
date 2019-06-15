package com.bumptech.glide.load.engine.b;

import android.util.Log;
import com.bumptech.glide.a.a;
import com.bumptech.glide.a.a.d;
import com.bumptech.glide.load.c;
import com.bumptech.glide.load.engine.b.a.b;
import java.io.File;
import java.io.IOException;

public class e implements a {
    private static e a;
    private final j b;
    private final File c;
    private final int d;
    private final c e = new c();
    private a f;

    public static synchronized a a(File file, int i) {
        e eVar;
        synchronized (e.class) {
            if (a == null) {
                a = new e(file, i);
            }
            eVar = a;
        }
        return eVar;
    }

    protected e(File file, int i) {
        this.c = file;
        this.d = i;
        this.b = new j();
    }

    private synchronized a a() throws IOException {
        if (this.f == null) {
            this.f = a.a(this.c, 1, 1, (long) this.d);
        }
        return this.f;
    }

    public File a(c cVar) {
        String a = this.b.a(cVar);
        if (Log.isLoggable("DiskLruCacheWrapper", 2)) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Get: Obtained: ");
            stringBuilder.append(a);
            stringBuilder.append(" for for Key: ");
            stringBuilder.append(cVar);
            Log.v("DiskLruCacheWrapper", stringBuilder.toString());
        }
        try {
            d a2 = a().a(a);
            if (a2 != null) {
                return a2.a(0);
            }
            return null;
        } catch (IOException e) {
            if (!Log.isLoggable("DiskLruCacheWrapper", 5)) {
                return null;
            }
            Log.w("DiskLruCacheWrapper", "Unable to get from disk cache", e);
            return null;
        }
    }

    public void a(c cVar, b bVar) {
        String a = this.b.a(cVar);
        this.e.a(a);
        try {
            if (Log.isLoggable("DiskLruCacheWrapper", 2)) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Put: Obtained: ");
                stringBuilder.append(a);
                stringBuilder.append(" for for Key: ");
                stringBuilder.append(cVar);
                Log.v("DiskLruCacheWrapper", stringBuilder.toString());
            }
            a.b b;
            try {
                a a2 = a();
                if (a2.a(a) == null) {
                    b = a2.b(a);
                    if (b == null) {
                        StringBuilder stringBuilder2 = new StringBuilder();
                        stringBuilder2.append("Had two simultaneous puts for: ");
                        stringBuilder2.append(a);
                        throw new IllegalStateException(stringBuilder2.toString());
                    }
                    if (bVar.a(b.a(0))) {
                        b.a();
                    }
                    b.c();
                    this.e.b(a);
                }
            } catch (IOException e) {
                if (Log.isLoggable("DiskLruCacheWrapper", 5)) {
                    Log.w("DiskLruCacheWrapper", "Unable to put to disk cache", e);
                }
            } catch (Throwable th) {
                b.c();
            }
        } finally {
            this.e.b(a);
        }
    }
}
