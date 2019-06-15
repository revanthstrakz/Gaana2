package com.playercache;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.net.wifi.WifiManager.WifiLock;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import androidx.work.ListenableWorker.a;
import androidx.work.Worker;
import androidx.work.WorkerParameters;
import com.gaana.application.GaanaApplication;
import com.google.ads.interactivemedia.v3.api.AdEvent;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.managers.u;
import com.models.PlayerTrack;
import com.player_framework.d;
import com.player_framework.f;
import com.player_framework.m;
import com.player_framework.o;
import com.til.colombia.android.internal.e;
import com.utilities.Util;

public class TrackCacheWorker extends Worker {
    private static f b;
    private PlayerTrack c;
    private WifiLock d;
    private boolean e = true;
    private Context f;
    private Handler g;
    private m h = new m() {
        public void onAdEventUpdate(f fVar, AdEvent adEvent) {
        }

        public void onBufferingUpdate(f fVar, int i) {
        }

        public void onCompletion(f fVar) {
        }

        public void onInfo(f fVar, int i, int i2) {
        }

        public void onPrepared(f fVar) {
            TrackCacheWorker.this.n();
        }

        public void onError(f fVar, int i, int i2) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Buffer not fetched - Server - ");
            stringBuilder.append(i);
            u.a().a("AdvancedStreamingFailure", stringBuilder.toString(), Util.P());
            TrackCacheWorker.this.n();
        }
    };

    public TrackCacheWorker(Context context, WorkerParameters workerParameters) {
        super(context, workerParameters);
        this.f = context;
        this.g = new Handler(Looper.getMainLooper());
        this.d = ((WifiManager) GaanaApplication.getContext().getApplicationContext().getSystemService(e.ad)).createWifiLock(1, "mylock");
    }

    public a k() {
        if (!e.a().c()) {
            return a.a();
        }
        l();
        return a.a();
    }

    public void l() {
        n();
    }

    private void n() {
        this.c = TrackCacheQueueManager.a().b();
        if (this.c != null) {
            a(this.c);
        } else if (this.d.isHeld()) {
            this.d.release();
        }
    }

    private void a(PlayerTrack playerTrack) {
        try {
            if (this.d.isHeld()) {
                this.d.release();
            }
            this.d.acquire();
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
        }
        this.e = true;
        String a = new d(this.f).a(playerTrack);
        if (TextUtils.isEmpty(a)) {
            n();
            return;
        }
        if (!(a.startsWith("http:") || a.startsWith("https:"))) {
            a = Util.l(a);
        }
        this.e = true;
        a(playerTrack, a, false);
    }

    private void a(PlayerTrack playerTrack, String str, boolean z) {
        b(playerTrack, str, z);
    }

    private void b(PlayerTrack playerTrack, String str, boolean z) {
        o.c("LISTENER_KEY_MUSIC_CACHE_SERVICE", this.h);
        a(str, playerTrack, z);
    }

    private void a(final String str, final PlayerTrack playerTrack, final boolean z) {
        if (str != null && str.length() != 0) {
            this.g.post(new Runnable() {
                public void run() {
                    if (TrackCacheWorker.b != null) {
                        TrackCacheWorker.b.w();
                    }
                    TrackCacheWorker.b = new c();
                    TrackCacheWorker.b.x();
                    TrackCacheWorker.b.d(false);
                    TrackCacheWorker.b.e(true);
                    String str = str;
                    if (!(str.startsWith("http:") || str.startsWith("https:"))) {
                        str = Util.l(str);
                        Util.m("akamai");
                    }
                    TrackCacheWorker.b.a(TrackCacheWorker.this.f, str, playerTrack, -2, z);
                }
            });
        }
    }
}
