package com.bumptech.glide.load.resource.bitmap;

import android.annotation.TargetApi;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory.Options;
import android.os.Build.VERSION;
import android.util.Log;
import com.bumptech.glide.load.DecodeFormat;
import java.io.File;

final class o {
    private static final File a = new File("/proc/self/fd");
    private static volatile o d;
    private volatile int b;
    private volatile boolean c = true;

    static o a() {
        if (d == null) {
            synchronized (o.class) {
                if (d == null) {
                    d = new o();
                }
            }
        }
        return d;
    }

    private o() {
    }

    /* Access modifiers changed, original: 0000 */
    @TargetApi(26)
    public boolean a(int i, int i2, Options options, DecodeFormat decodeFormat, boolean z, boolean z2) {
        if (!z || VERSION.SDK_INT < 26 || decodeFormat == DecodeFormat.PREFER_ARGB_8888_DISALLOW_HARDWARE || z2) {
            return false;
        }
        boolean z3 = i >= 128 && i2 >= 128 && b();
        if (z3) {
            options.inPreferredConfig = Config.HARDWARE;
            options.inMutable = false;
        }
        return z3;
    }

    private synchronized boolean b() {
        int i = this.b + 1;
        this.b = i;
        if (i >= 50) {
            boolean z = false;
            this.b = 0;
            int length = a.list().length;
            if (length < 700) {
                z = true;
            }
            this.c = z;
            if (!this.c && Log.isLoggable("Downsampler", 5)) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Excluding HARDWARE bitmap config because we're over the file descriptor limit, file descriptors ");
                stringBuilder.append(length);
                stringBuilder.append(", limit ");
                stringBuilder.append(700);
                Log.w("Downsampler", stringBuilder.toString());
            }
        }
        return this.c;
    }
}
