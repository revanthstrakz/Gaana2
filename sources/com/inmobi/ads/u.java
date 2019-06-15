package com.inmobi.ads;

import android.graphics.Canvas;
import android.graphics.Movie;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import com.inmobi.ads.t.a;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class u implements t {
    Movie a;
    int b = 0;
    private long c;
    private volatile boolean d = false;
    private a e;
    private ExecutorService f;
    private Runnable g;

    public u(String str) throws IOException {
        File file = new File(str);
        byte[] bArr = new byte[((int) file.length())];
        FileInputStream fileInputStream = new FileInputStream(file);
        int read = fileInputStream.read(bArr);
        fileInputStream.close();
        this.a = Movie.decodeByteArray(bArr, 0, read);
    }

    public final void a() {
        this.f = Executors.newSingleThreadExecutor();
        this.g = new Runnable() {
            public final void run() {
                if (u.this.b + 20 >= u.this.a.duration()) {
                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        public final void run() {
                            u.this.b = 0;
                            u.this.a(false);
                        }
                    });
                }
            }
        };
    }

    public final void a(boolean z) {
        this.d = z;
        if (!this.d) {
            this.c = SystemClock.uptimeMillis() - ((long) this.b);
        }
        if (this.e != null) {
            this.e.a();
        }
    }

    public final int b() {
        return this.a.width();
    }

    public final int c() {
        return this.a.height();
    }

    public final void a(Canvas canvas, float f, float f2) {
        this.a.draw(canvas, f, f2);
        this.f.execute(this.g);
    }

    public final boolean d() {
        return !this.d;
    }

    public final void e() {
        long uptimeMillis = SystemClock.uptimeMillis();
        if (this.c == 0) {
            this.c = uptimeMillis;
        }
        int duration = this.a.duration();
        if (duration == 0) {
            duration = 1000;
        }
        this.b = (int) ((uptimeMillis - this.c) % ((long) duration));
        this.a.setTime(this.b);
    }

    public final void a(a aVar) {
        this.e = aVar;
    }
}
