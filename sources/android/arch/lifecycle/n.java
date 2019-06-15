package android.arch.lifecycle;

import android.app.Activity;
import android.app.Application;
import android.arch.lifecycle.Lifecycle.Event;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;

public class n implements e {
    private static final n i = new n();
    private int a = 0;
    private int b = 0;
    private boolean c = true;
    private boolean d = true;
    private Handler e;
    private final f f = new f(this);
    private Runnable g = new Runnable() {
        public void run() {
            n.this.e();
            n.this.f();
        }
    };
    private a h = new a() {
        public void a() {
        }

        public void b() {
            n.this.a();
        }

        public void c() {
            n.this.b();
        }
    };

    static void a(Context context) {
        i.b(context);
    }

    /* Access modifiers changed, original: 0000 */
    public void a() {
        this.a++;
        if (this.a == 1 && this.d) {
            this.f.a(Event.ON_START);
            this.d = false;
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void b() {
        this.b++;
        if (this.b != 1) {
            return;
        }
        if (this.c) {
            this.f.a(Event.ON_RESUME);
            this.c = false;
            return;
        }
        this.e.removeCallbacks(this.g);
    }

    /* Access modifiers changed, original: 0000 */
    public void c() {
        this.b--;
        if (this.b == 0) {
            this.e.postDelayed(this.g, 700);
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void d() {
        this.a--;
        f();
    }

    private void e() {
        if (this.b == 0) {
            this.c = true;
            this.f.a(Event.ON_PAUSE);
        }
    }

    private void f() {
        if (this.a == 0 && this.c) {
            this.f.a(Event.ON_STOP);
            this.d = true;
        }
    }

    private n() {
    }

    /* Access modifiers changed, original: 0000 */
    public void b(Context context) {
        this.e = new Handler();
        this.f.a(Event.ON_CREATE);
        ((Application) context.getApplicationContext()).registerActivityLifecycleCallbacks(new b() {
            public void onActivityCreated(Activity activity, Bundle bundle) {
                ReportFragment.b(activity).a(n.this.h);
            }

            public void onActivityPaused(Activity activity) {
                n.this.c();
            }

            public void onActivityStopped(Activity activity) {
                n.this.d();
            }
        });
    }

    @NonNull
    public Lifecycle getLifecycle() {
        return this.f;
    }
}
