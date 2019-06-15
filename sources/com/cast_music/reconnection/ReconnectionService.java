package com.cast_music.reconnection;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.BroadcastReceiver.PendingResult;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.NetworkInfo;
import android.os.IBinder;
import android.os.SystemClock;
import com.cast_music.VideoCastManager;
import com.cast_music.b.b;
import com.cast_music.exceptions.NoConnectionException;
import com.cast_music.exceptions.TransientNetworkDisconnectionException;
import com.i.d;
import java.util.Timer;
import java.util.TimerTask;

public class ReconnectionService extends Service {
    private static final String a = b.a(ReconnectionService.class);
    private BroadcastReceiver b;
    private VideoCastManager c;
    private BroadcastReceiver d;
    private boolean e = true;
    private Timer f;
    private TimerTask g;

    public IBinder onBind(Intent intent) {
        return null;
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        b.a(a, "onStartCommand() is called");
        b();
        return 1;
    }

    public void onCreate() {
        b.a(a, "onCreate() is called");
        this.c = VideoCastManager.y();
        if (!(this.c.f() || this.c.g())) {
            this.c.p();
        }
        IntentFilter intentFilter = new IntentFilter("android.intent.action.SCREEN_ON");
        intentFilter.addAction("android.intent.action.SCREEN_OFF");
        this.b = new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                String a = ReconnectionService.a;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("ScreenOnOffBroadcastReceiver: onReceive(): ");
                stringBuilder.append(intent.getAction());
                b.a(a, stringBuilder.toString());
                final PendingResult goAsync = goAsync();
                d.a(new Runnable() {
                    public void run() {
                        if (ReconnectionService.this.d() < 500) {
                            ReconnectionService.this.e();
                            goAsync.finish();
                        }
                    }
                });
            }
        };
        registerReceiver(this.b, intentFilter);
        intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.wifi.STATE_CHANGE");
        this.d = new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                if (intent.getAction().equals("android.net.wifi.STATE_CHANGE")) {
                    boolean isConnected = ((NetworkInfo) intent.getParcelableExtra("networkInfo")).isConnected();
                    ReconnectionService.this.a(isConnected, isConnected ? com.cast_music.b.d.a(context) : null);
                }
            }
        };
        registerReceiver(this.d, intentFilter);
        super.onCreate();
    }

    public void a(boolean z, String str) {
        String str2 = a;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("WIFI connectivity changed to ");
        stringBuilder.append(z ? "enabled" : "disabled");
        b.a(str2, stringBuilder.toString());
        if (!z || this.e) {
            this.e = z;
            return;
        }
        this.e = true;
        if (this.c.c(8)) {
            this.c.d();
            this.c.a(15, str);
        }
    }

    public void onDestroy() {
        b.a(a, "onDestroy()");
        if (this.b != null) {
            unregisterReceiver(this.b);
            this.b = null;
        }
        if (this.d != null) {
            unregisterReceiver(this.d);
            this.d = null;
        }
        c();
        super.onDestroy();
    }

    private void b() {
        b.a(a, "setUpEndTimer(): setting up a timer for the end of current media");
        long d = d();
        if (d <= 0) {
            stopSelf();
            return;
        }
        c();
        this.f = new Timer();
        this.g = new TimerTask() {
            public void run() {
                b.a(ReconnectionService.a, "setUpEndTimer(): stopping ReconnectionService since reached the end of allotted time");
                ReconnectionService.this.e();
            }
        };
        this.f.schedule(this.g, d);
    }

    private void c() {
        if (this.g != null) {
            this.g.cancel();
            this.g = null;
        }
        if (this.f != null) {
            this.f.cancel();
            this.f = null;
        }
    }

    private long d() {
        return this.c.u().a("media-end", 0) - SystemClock.elapsedRealtime();
    }

    private void e() {
        if (this.c.f()) {
            long j = 0;
            try {
                if (!this.c.B()) {
                    j = this.c.J();
                }
            } catch (NoConnectionException | TransientNetworkDisconnectionException e) {
                b.a(a, "Failed to calculate the time left for media due to lack of connectivity", e);
            }
            if (j < 500) {
                stopSelf();
                return;
            }
            this.c.u().a("media-end", Long.valueOf(j + SystemClock.elapsedRealtime()));
            b.a(a, "handleTermination(): resetting the timer");
            b();
            return;
        }
        this.c.S();
        this.c.f(0);
        stopSelf();
    }
}
