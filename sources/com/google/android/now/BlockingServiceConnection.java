package com.google.android.now;

import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.util.Log;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

class BlockingServiceConnection implements ServiceConnection {
    private static final String TAG = "BlockingServiceConnection";
    private final BlockingQueue<IBinder> mBlockingQueue = new LinkedBlockingQueue();
    private boolean mBound = false;
    private AtomicBoolean mUsed = new AtomicBoolean(false);

    BlockingServiceConnection() {
    }

    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        this.mBound = true;
        this.mBlockingQueue.clear();
        this.mBlockingQueue.add(iBinder);
    }

    public void onServiceDisconnected(ComponentName componentName) {
        this.mBound = false;
    }

    public IBinder getService() throws InterruptedException {
        if (this.mUsed.get()) {
            throw new IllegalStateException();
        }
        this.mUsed.set(true);
        return (IBinder) this.mBlockingQueue.take();
    }

    public void unbindServiceIfConnected(final Context context) {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            public void run() {
                if (BlockingServiceConnection.this.mBound) {
                    context.unbindService(BlockingServiceConnection.this);
                } else {
                    Log.w(BlockingServiceConnection.TAG, "Service disconnected before unbinding");
                }
            }
        });
    }
}
