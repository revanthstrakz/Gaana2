package com.android.volley;

import com.google.android.exoplayer2.DefaultLoadControl;

public class c implements k {
    private int a;
    private int b;
    private final int c;
    private final float d;

    public c() {
        this(DefaultLoadControl.DEFAULT_BUFFER_FOR_PLAYBACK_MS, 1, 1.0f);
    }

    public c(int i, int i2, float f) {
        this.a = i;
        this.c = i2;
        this.d = f;
    }

    public int a() {
        return this.a;
    }

    public int b() {
        return this.b;
    }

    public void a(VolleyError volleyError) throws VolleyError {
        this.b++;
        this.a = (int) (((float) this.a) + (((float) this.a) * this.d));
        if (!c()) {
            throw volleyError;
        }
    }

    /* Access modifiers changed, original: protected */
    public boolean c() {
        return this.b <= this.c;
    }
}
