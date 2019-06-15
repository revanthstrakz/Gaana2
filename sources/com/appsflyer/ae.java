package com.appsflyer;

import android.app.Activity;
import android.app.Application;
import android.app.Application.ActivityLifecycleCallbacks;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import java.lang.ref.WeakReference;
import java.util.concurrent.RejectedExecutionException;

@RequiresApi(api = 14)
final class ae implements ActivityLifecycleCallbacks {
    private static ae a;
    private boolean b = false;
    private boolean c = true;
    private b d = null;

    class a extends AsyncTask<Void, Void, Void> {
        private WeakReference<Context> a;

        /* Access modifiers changed, original: protected|final|synthetic */
        public final /* synthetic */ Object doInBackground(Object[] objArr) {
            return a();
        }

        public a(WeakReference<Context> weakReference) {
            this.a = weakReference;
        }

        private Void a() {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                AFLogger.a("Sleeping attempt failed (essential for background state verification)\n", e);
            }
            if (ae.this.b && ae.this.c) {
                ae.this.b = false;
                try {
                    ae.this.d.a(this.a);
                } catch (Exception e2) {
                    AFLogger.a("Listener threw exception! ", e2);
                    cancel(true);
                }
            }
            this.a.clear();
            return null;
        }
    }

    interface b {
        void a(Activity activity);

        void a(WeakReference<Context> weakReference);
    }

    public final void onActivityCreated(Activity activity, Bundle bundle) {
    }

    public final void onActivityDestroyed(Activity activity) {
    }

    public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    public final void onActivityStarted(Activity activity) {
    }

    public final void onActivityStopped(Activity activity) {
    }

    ae() {
    }

    static ae a() {
        if (a == null) {
            a = new ae();
        }
        return a;
    }

    public static ae b() {
        if (a != null) {
            return a;
        }
        throw new IllegalStateException("Foreground is not initialised - invoke at least once with parameter init/get");
    }

    public final void a(Application application, b bVar) {
        this.d = bVar;
        if (VERSION.SDK_INT >= 14) {
            application.registerActivityLifecycleCallbacks(a);
        }
    }

    public final void onActivityResumed(Activity activity) {
        this.c = false;
        int i = this.b ^ 1;
        this.b = true;
        if (i != 0) {
            try {
                this.d.a(activity);
            } catch (Exception e) {
                AFLogger.a("Listener threw exception! ", e);
            }
        }
    }

    public final void onActivityPaused(Activity activity) {
        this.c = true;
        try {
            new a(new WeakReference(activity.getApplicationContext())).executeOnExecutor(a.a().b(), new Void[0]);
        } catch (RejectedExecutionException e) {
            AFLogger.a("backgroundTask.executeOnExecutor failed with RejectedExecutionException Exception", e);
        } catch (Throwable e2) {
            AFLogger.a("backgroundTask.executeOnExecutor failed with Exception", e2);
        }
    }
}
