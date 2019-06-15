package com.facebook.accountkit.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import com.facebook.internal.NativeProtocol;

final class SeamlessLoginClient implements ServiceConnection {
    private static final int MIN_PROTOCOL_VERSION = 20161017;
    private static final int REPLY_MESSAGE = 65545;
    private static final int REQUEST_MESSAGE = 65544;
    private final String applicationId;
    private final Context context;
    private final Handler handler = new Handler() {
        public void handleMessage(Message message) {
            SeamlessLoginClient.this.handleMessage(message);
        }
    };
    private CompletedListener listener;
    private final InternalLogger logger;
    private boolean running;
    private Messenger sender;

    public interface CompletedListener {
        void completed(Bundle bundle);
    }

    public SeamlessLoginClient(Context context, String str, InternalLogger internalLogger) {
        this.context = context;
        this.applicationId = str;
        this.logger = internalLogger;
    }

    public void setCompletedListener(CompletedListener completedListener) {
        this.listener = completedListener;
    }

    public boolean start() {
        if (this.running) {
            return false;
        }
        if (!NativeProtocol.validateApplicationForService()) {
            this.logger.logFetchEventError(InternalLogger.EVENT_NAME_FETCH_SEAMLESS_LOGIN_TOKEN, InternalAccountKitError.NO_NATIVE_APP_INSTALLED);
            return false;
        } else if (NativeProtocol.validateProtocolVersionForService(MIN_PROTOCOL_VERSION)) {
            Intent createPlatformServiceIntent = NativeProtocol.createPlatformServiceIntent(this.context);
            if (createPlatformServiceIntent == null) {
                return false;
            }
            this.running = true;
            this.context.bindService(createPlatformServiceIntent, this, 1);
            return true;
        } else {
            this.logger.logFetchEventError(InternalLogger.EVENT_NAME_FETCH_SEAMLESS_LOGIN_TOKEN, InternalAccountKitError.UNSUPPORTED_NATIVE_APP_VERSION);
            return false;
        }
    }

    public boolean isRunning() {
        return this.running;
    }

    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        this.sender = new Messenger(iBinder);
        sendMessage();
    }

    public void onServiceDisconnected(ComponentName componentName) {
        this.sender = null;
        try {
            this.context.unbindService(this);
        } catch (IllegalArgumentException unused) {
        }
        callback(null);
    }

    private void sendMessage() {
        Bundle bundle = new Bundle();
        bundle.putString(NativeProtocol.EXTRA_APPLICATION_ID, this.applicationId);
        Message obtain = Message.obtain(null, 65544);
        obtain.arg1 = MIN_PROTOCOL_VERSION;
        obtain.setData(bundle);
        obtain.replyTo = new Messenger(this.handler);
        try {
            this.sender.send(obtain);
        } catch (RemoteException unused) {
            callback(null);
        }
    }

    private void handleMessage(Message message) {
        if (message.what == 65545) {
            Bundle data = message.getData();
            if (data.getString(NativeProtocol.STATUS_ERROR_TYPE) != null) {
                callback(null);
            } else {
                callback(data);
            }
            try {
                this.context.unbindService(this);
            } catch (IllegalArgumentException unused) {
            }
        }
    }

    private void callback(Bundle bundle) {
        if (this.running) {
            this.running = false;
            if (this.listener != null) {
                this.listener.completed(bundle);
            }
        }
    }
}
