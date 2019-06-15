package com.inmobi.ads;

import android.media.MediaPlayer;

public final class av extends MediaPlayer {
    private static final Object d = new Object();
    private static av e;
    private static int f;
    int a = 0;
    int b = 0;
    private av c;

    public static av a() {
        synchronized (d) {
            if (e != null) {
                av avVar = e;
                e = avVar.c;
                avVar.c = null;
                f--;
                return avVar;
            }
            return new av();
        }
    }

    public final void b() {
        if ((3 == this.a ? 1 : 0) == 0) {
            synchronized (d) {
                if (f < 5) {
                    this.c = e;
                    e = this;
                    f++;
                }
            }
        }
    }
}
