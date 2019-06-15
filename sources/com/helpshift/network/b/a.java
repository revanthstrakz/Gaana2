package com.helpshift.network.b;

import android.os.Handler;
import com.helpshift.network.errors.NetworkError;
import java.util.concurrent.Executor;

public class a implements f {
    private final Executor a;

    private class a implements Runnable {
        private final com.helpshift.network.a.a b;
        private final e c;
        private final Runnable d;

        public a(com.helpshift.network.a.a aVar, e eVar, Runnable runnable) {
            this.b = aVar;
            this.c = eVar;
            this.d = runnable;
        }

        public void run() {
            try {
                if (this.c.a()) {
                    this.b.a(this.c.a);
                } else {
                    this.b.b(this.c.b);
                }
            } catch (Throwable unused) {
            }
            this.b.g();
            if (this.d != null) {
                this.d.run();
            }
        }
    }

    public a(final Handler handler) {
        this.a = new Executor() {
            public void execute(Runnable runnable) {
                handler.post(runnable);
            }
        };
    }

    public void a(com.helpshift.network.a.a aVar, e<?> eVar) {
        this.a.execute(new a(aVar, eVar, null));
    }

    public void a(com.helpshift.network.a.a aVar, NetworkError networkError) {
        this.a.execute(new a(aVar, e.a(networkError, Integer.valueOf(aVar.b())), null));
    }
}
