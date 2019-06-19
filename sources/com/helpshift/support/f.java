package com.helpshift.support;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application.ActivityLifecycleCallbacks;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.comscore.measurement.MeasurementDispatcher;
import com.google.android.exoplayer2.C;
import com.helpshift.p.a;
import com.helpshift.util.a.b;
import com.helpshift.util.l;
import com.helpshift.util.n;
import com.helpshift.util.o;
import com.helpshift.util.x;
import java.util.List;

@TargetApi(14)
final class f implements ActivityLifecycleCallbacks {
    static d a;
    static g b;
    static int c;
    static int d;
    static boolean e;
    static boolean f;
    private static f g;

    public void onActivityCreated(Activity activity, Bundle bundle) {
    }

    public void onActivityDestroyed(Activity activity) {
    }

    public void onActivityPaused(Activity activity) {
    }

    public void onActivityResumed(Activity activity) {
    }

    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    private f() {
    }

    public static f a() {
        if (g == null) {
            g = new f();
        }
        return g;
    }

    public void onActivityStarted(Activity activity) {
        final Context applicationContext = activity.getApplicationContext();
        b.a().a(new Runnable() {
            public void run() {
                if (!f.f) {
                    if (f.a == null) {
                        f.a = new d(applicationContext);
                        f.b = f.a.b;
                    }
                    f.c++;
                    if (!f.e) {
                        f.a.f();
                        if (f.a.e().booleanValue()) {
                            Intent intent = new Intent(applicationContext, HSReview.class);
                            intent.setFlags(C.ENCODING_PCM_MU_LAW);
                            applicationContext.startActivity(intent);
                        }
                        o.d().d();
                        o.d().i();
                        o.d().n().a();
                        o.d().g();
                        boolean a = n.a(applicationContext);
                        synchronized (this) {
                            if (a) {
                                try {
                                    if (a.a()) {
                                        long p = f.b.p();
                                        long b = x.b(Float.valueOf(o.c().q().a()));
                                        int i = b - p > MeasurementDispatcher.MILLIS_PER_DAY ? 1 : false;
                                        if (i != 0 && l.c() > 0) {
                                            List a2 = l.a();
                                            if (!(a2 == null || a2.isEmpty())) {
                                                f.b.a(b);
                                                f.a.a(a2);
                                            }
                                        }
                                    }
                                } finally {
                                }
                            }
                        }
                    }
                    f.e = true;
                }
                f.f = false;
            }
        });
    }

    public void onActivityStopped(Activity activity) {
        final boolean z = activity != null && activity.isChangingConfigurations();
        b.a().a(new Runnable() {
            public void run() {
                f.f = z;
                if (!f.f) {
                    f.d++;
                    if (f.c == f.d) {
                        f.e = false;
                        o.d().n().e();
                    }
                }
            }
        });
    }
}
