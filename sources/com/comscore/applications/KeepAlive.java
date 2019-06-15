package com.comscore.applications;

import android.util.Log;
import com.comscore.analytics.Core;
import com.comscore.utils.Constants;
import com.comscore.utils.OfflineMeasurementsCache;
import com.comscore.utils.Storage;
import java.util.HashMap;

public class KeepAlive implements Runnable {
    protected final long a;
    protected long b = -1;
    protected long c;
    private Core d;
    private boolean e = false;
    private boolean f = false;

    public KeepAlive(Core core, long j) {
        this.a = j;
        this.c = this.a;
        this.d = core;
    }

    private long a(Storage storage) {
        String str = storage.get(Constants.LAST_MEASUREMENT_PROCESSED_TIMESTAMP_KEY);
        if (str != null && str.length() > 0) {
            try {
                return Long.parseLong(str);
            } catch (Exception unused) {
            }
        }
        return 0;
    }

    /* Access modifiers changed, original: protected */
    public void a() {
        if (this.d.isEnabled()) {
            this.d.getTaskExecutor().execute(this, this.b - System.currentTimeMillis(), true, this.a);
            this.f = true;
        }
    }

    public void cancel() {
        Log.d("KeepAlive", "cancel()");
        this.d.getTaskExecutor().removeEnqueuedTask(this);
        this.f = false;
    }

    public void processKeepAlive(boolean z) {
        if (this.d.isEnabled() && this.d.isKeepAliveEnabled()) {
            OfflineMeasurementsCache offlineCache = this.d.getOfflineCache();
            long a = a(this.d.getStorage());
            long currentTimeMillis = System.currentTimeMillis() - a;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("processKeepAlive(");
            stringBuilder.append(z);
            stringBuilder.append(") timeSinceLastTransmission=");
            stringBuilder.append(System.currentTimeMillis() - currentTimeMillis);
            stringBuilder.append(" currentTimeout=");
            stringBuilder.append(this.c);
            Log.d("KeepAlive", stringBuilder.toString());
            if (a != 0 && currentTimeMillis > this.c - 1000) {
                Log.d("KeepAlive", "Sending Keep-alive");
                if (z) {
                    offlineCache.saveApplicationMeasurement(EventType.KEEPALIVE, null, true);
                } else {
                    this.d.notify(EventType.KEEPALIVE, new HashMap(), true);
                }
                this.d.getStorage().set(Constants.LAST_MEASUREMENT_PROCESSED_TIMESTAMP_KEY, String.valueOf(System.currentTimeMillis()));
            }
        }
    }

    public void reset() {
        reset(this.a);
    }

    public void reset(long j) {
        if (this.d.isEnabled()) {
            cancel();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("reset:");
            stringBuilder.append(j);
            Log.d("KeepAlive", stringBuilder.toString());
            this.b = System.currentTimeMillis() + j;
            this.c = j;
            if (this.e) {
                start(0);
            }
        }
    }

    public void run() {
        if (this.d.isEnabled() && this.f) {
            Log.d("KeepAlive", "run()");
            sendKeepAlive();
        }
    }

    public void sendKeepAlive() {
        processKeepAlive(false);
    }

    public void start(int i) {
        if (this.d.isEnabled()) {
            cancel();
            this.e = true;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("start(");
            stringBuilder.append(i);
            stringBuilder.append(")");
            Log.d("KeepAlive", stringBuilder.toString());
            if (this.d.isKeepAliveEnabled()) {
                long currentTimeMillis = System.currentTimeMillis();
                if (this.b < currentTimeMillis) {
                    this.b = currentTimeMillis + ((long) i);
                }
                a();
            }
        }
    }

    public void stop() {
        Log.d("KeepAlive", "stop");
        this.e = false;
        cancel();
        processKeepAlive(true);
    }
}
