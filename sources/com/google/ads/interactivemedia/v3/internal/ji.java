package com.google.ads.interactivemedia.v3.internal;

import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import com.google.ads.interactivemedia.v3.api.player.VideoProgressUpdate;
import java.util.ArrayList;
import java.util.List;

public abstract class ji implements Callback {
    private final a a;
    protected final long b;
    protected boolean c;
    private List<b> d;

    protected static class a {
        private final Handler a;

        public a(Handler handler) {
            this.a = handler;
        }

        /* Access modifiers changed, original: protected */
        public void a(int i) {
            this.a.removeMessages(i);
        }

        /* Access modifiers changed, original: protected */
        public boolean b(int i) {
            return this.a.sendEmptyMessage(i);
        }

        /* Access modifiers changed, original: protected */
        public boolean a(int i, long j) {
            return this.a.sendEmptyMessageDelayed(i, j);
        }

        /* Access modifiers changed, original: protected */
        public boolean c(int i) {
            return this.a.sendMessageAtFrontOfQueue(Message.obtain(this.a, i));
        }
    }

    public interface b {
        void a(VideoProgressUpdate videoProgressUpdate);
    }

    ji(long j) {
        this(null, j);
    }

    public abstract VideoProgressUpdate a();

    ji(a aVar, long j) {
        this.c = false;
        this.d = new ArrayList(1);
        this.b = j;
        if (aVar != null) {
            this.a = aVar;
        } else {
            this.a = new a(new Handler(this));
        }
    }

    public void a(b bVar) {
        this.d.add(bVar);
    }

    public void b(b bVar) {
        this.d.remove(bVar);
    }

    public void b() {
        if (!this.c) {
            this.c = true;
            this.a.b(1);
        }
    }

    public void c() {
        if (this.c) {
            this.c = false;
            this.a.c(2);
        }
    }

    public boolean handleMessage(Message message) {
        switch (message.what) {
            case 1:
                VideoProgressUpdate a = a();
                for (b a2 : this.d) {
                    a2.a(a);
                }
                this.a.a(1, this.b);
                break;
            case 2:
                this.a.a(1);
                break;
        }
        return true;
    }
}
