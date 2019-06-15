package com.bumptech.glide.request.a;

import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Looper;
import android.os.Message;
import com.bumptech.glide.i;
import com.bumptech.glide.request.b.d;

public final class f<Z> extends g<Z> {
    private static final Handler a = new Handler(Looper.getMainLooper(), new Callback() {
        public boolean handleMessage(Message message) {
            if (message.what != 1) {
                return false;
            }
            ((f) message.obj).a();
            return true;
        }
    });
    private final i b;

    public static <Z> f<Z> a(i iVar, int i, int i2) {
        return new f(iVar, i, i2);
    }

    private f(i iVar, int i, int i2) {
        super(i, i2);
        this.b = iVar;
    }

    public void onResourceReady(Z z, d<? super Z> dVar) {
        a.obtainMessage(1, this).sendToTarget();
    }

    private void a() {
        this.b.clear((i) this);
    }
}
