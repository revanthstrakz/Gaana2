package com.comscore.android.id;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import java.util.concurrent.LinkedBlockingQueue;

final class b implements ServiceConnection {
    boolean a;
    private final LinkedBlockingQueue<IBinder> b;

    private b() {
        this.a = false;
        this.b = new LinkedBlockingQueue(1);
    }

    /* synthetic */ b(a aVar) {
        this();
    }

    public IBinder getBinder() {
        if (this.a) {
            throw new IllegalStateException();
        }
        this.a = true;
        return (IBinder) this.b.take();
    }

    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        try {
            this.b.put(iBinder);
        } catch (InterruptedException unused) {
        }
    }

    public void onServiceDisconnected(ComponentName componentName) {
    }
}
