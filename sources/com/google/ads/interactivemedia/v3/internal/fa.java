package com.google.ads.interactivemedia.v3.internal;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import java.io.IOException;
import java.util.concurrent.ExecutorService;

public final class fa {
    private final ExecutorService a;
    private b b;
    private boolean c;

    public interface c {
        void a();

        boolean b();

        void c() throws IOException, InterruptedException;
    }

    public interface a {
        void a(c cVar);

        void a(c cVar, IOException iOException);

        void b(c cVar);
    }

    @SuppressLint({"HandlerLeak"})
    private final class b extends Handler implements Runnable {
        private final c b;
        private final a c;
        private volatile Thread d;

        public b(Looper looper, c cVar, a aVar) {
            super(looper);
            this.b = cVar;
            this.c = aVar;
        }

        public void a() {
            this.b.a();
            if (this.d != null) {
                this.d.interrupt();
            }
        }

        public void run() {
            try {
                this.d = Thread.currentThread();
                if (!this.b.b()) {
                    fs.a(String.valueOf(this.b.getClass().getSimpleName()).concat(".load()"));
                    this.b.c();
                    fs.a();
                }
                sendEmptyMessage(0);
            } catch (IOException e) {
                obtainMessage(1, e).sendToTarget();
            } catch (InterruptedException unused) {
                fe.b(this.b.b());
                sendEmptyMessage(0);
            } catch (Exception e2) {
                Log.e("LoadTask", "Unexpected exception loading stream", e2);
                obtainMessage(1, new d(e2)).sendToTarget();
            } catch (Error e3) {
                Log.e("LoadTask", "Unexpected error loading stream", e3);
                obtainMessage(2, e3).sendToTarget();
                throw e3;
            }
        }

        public void handleMessage(Message message) {
            if (message.what == 2) {
                throw ((Error) message.obj);
            }
            b();
            if (this.b.b()) {
                this.c.b(this.b);
                return;
            }
            switch (message.what) {
                case 0:
                    this.c.a(this.b);
                    break;
                case 1:
                    this.c.a(this.b, (IOException) message.obj);
                    break;
            }
        }

        private void b() {
            fa.this.c = false;
            fa.this.b = null;
        }
    }

    public static final class d extends IOException {
        public d(Exception exception) {
            String simpleName = exception.getClass().getSimpleName();
            String message = exception.getMessage();
            StringBuilder stringBuilder = new StringBuilder((13 + String.valueOf(simpleName).length()) + String.valueOf(message).length());
            stringBuilder.append("Unexpected ");
            stringBuilder.append(simpleName);
            stringBuilder.append(": ");
            stringBuilder.append(message);
            super(stringBuilder.toString(), exception);
        }
    }

    public fa(String str) {
        this.a = ft.a(str);
    }

    public void a(c cVar, a aVar) {
        Looper myLooper = Looper.myLooper();
        fe.b(myLooper != null);
        a(myLooper, cVar, aVar);
    }

    public void a(Looper looper, c cVar, a aVar) {
        fe.b(this.c ^ 1);
        this.c = true;
        this.b = new b(looper, cVar, aVar);
        this.a.submit(this.b);
    }

    public boolean a() {
        return this.c;
    }

    public void b() {
        fe.b(this.c);
        this.b.a();
    }

    public void a(Runnable runnable) {
        if (this.c) {
            b();
        }
        if (runnable != null) {
            this.a.submit(runnable);
        }
        this.a.shutdown();
    }
}
