package com.google.ads.interactivemedia.v3.internal;

import android.content.Context;
import android.database.ContentObserver;
import android.media.AudioManager;
import android.os.Handler;
import android.provider.Settings.System;

public final class n extends ContentObserver {
    private final Context a;
    private final AudioManager b;
    private final k c;
    private final m d;
    private float e;

    public n(Handler handler, Context context, k kVar, m mVar) {
        super(handler);
        this.a = context;
        this.b = (AudioManager) context.getSystemService("audio");
        this.c = kVar;
        this.d = mVar;
    }

    public void onChange(boolean z) {
        super.onChange(z);
        float c = c();
        if (a(c)) {
            this.e = c;
            d();
        }
    }

    public void a() {
        this.e = c();
        d();
        this.a.getContentResolver().registerContentObserver(System.CONTENT_URI, true, this);
    }

    public void b() {
        this.a.getContentResolver().unregisterContentObserver(this);
    }

    private float c() {
        return this.c.a(this.b.getStreamVolume(3), this.b.getStreamMaxVolume(3));
    }

    private boolean a(float f) {
        return f != this.e;
    }

    private void d() {
        this.d.a(this.e);
    }
}
