package com.moat.analytics.mobile.inm;

import android.app.Activity;
import android.app.Application;
import android.app.Application.ActivityLifecycleCallbacks;
import android.os.Bundle;
import java.lang.ref.WeakReference;

class a {
    static WeakReference<Activity> a;
    private static boolean b;
    private static Application c;
    private static int d;
    private static boolean e;

    private static class a implements ActivityLifecycleCallbacks {
        a() {
        }

        private static void a(boolean z) {
            if (z) {
                p.a(3, "ActivityState", null, "App became visible");
                if (w.a().a == d.ON && !((k) MoatAnalytics.getInstance()).c) {
                    o.a().c();
                    return;
                }
            }
            p.a(3, "ActivityState", null, "App became invisible");
            if (w.a().a == d.ON && !((k) MoatAnalytics.getInstance()).c) {
                o.a().d();
            }
        }

        public void onActivityCreated(Activity activity, Bundle bundle) {
            a.d = 1;
        }

        public void onActivityDestroyed(Activity activity) {
            try {
                if (!(a.d == 3 || a.d == 5)) {
                    if (a.e) {
                        a(false);
                    }
                    a.e = false;
                }
                a.d = 6;
                StringBuilder stringBuilder = new StringBuilder("Activity destroyed: ");
                stringBuilder.append(activity.getClass());
                stringBuilder.append("@");
                stringBuilder.append(activity.hashCode());
                p.a(3, "ActivityState", (Object) this, stringBuilder.toString());
                if (a.b(activity)) {
                    a.a = new WeakReference(null);
                }
            } catch (Exception e) {
                m.a(e);
            }
        }

        public void onActivityPaused(Activity activity) {
            try {
                a.d = 4;
                if (a.b(activity)) {
                    a.a = new WeakReference(null);
                }
                StringBuilder stringBuilder = new StringBuilder("Activity paused: ");
                stringBuilder.append(activity.getClass());
                stringBuilder.append("@");
                stringBuilder.append(activity.hashCode());
                p.a(3, "ActivityState", (Object) this, stringBuilder.toString());
            } catch (Exception e) {
                m.a(e);
            }
        }

        public void onActivityResumed(Activity activity) {
            try {
                a.a = new WeakReference(activity);
                a.d = 3;
                w.a().b();
                StringBuilder stringBuilder = new StringBuilder("Activity resumed: ");
                stringBuilder.append(activity.getClass());
                stringBuilder.append("@");
                stringBuilder.append(activity.hashCode());
                p.a(3, "ActivityState", (Object) this, stringBuilder.toString());
                if (((k) MoatAnalytics.getInstance()).b) {
                    f.a(activity);
                }
            } catch (Exception e) {
                m.a(e);
            }
        }

        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        }

        public void onActivityStarted(Activity activity) {
            try {
                a.a = new WeakReference(activity);
                a.d = 2;
                if (!a.e) {
                    a(true);
                }
                a.e = true;
                StringBuilder stringBuilder = new StringBuilder("Activity started: ");
                stringBuilder.append(activity.getClass());
                stringBuilder.append("@");
                stringBuilder.append(activity.hashCode());
                p.a(3, "ActivityState", (Object) this, stringBuilder.toString());
            } catch (Exception e) {
                m.a(e);
            }
        }

        public void onActivityStopped(Activity activity) {
            try {
                if (a.d != 3) {
                    a.e = false;
                    a(false);
                }
                a.d = 5;
                if (a.b(activity)) {
                    a.a = new WeakReference(null);
                }
                StringBuilder stringBuilder = new StringBuilder("Activity stopped: ");
                stringBuilder.append(activity.getClass());
                stringBuilder.append("@");
                stringBuilder.append(activity.hashCode());
                p.a(3, "ActivityState", (Object) this, stringBuilder.toString());
            } catch (Exception e) {
                m.a(e);
            }
        }
    }

    a() {
    }

    static Application a() {
        return c;
    }

    static void a(Application application) {
        c = application;
        if (!b) {
            b = true;
            c.registerActivityLifecycleCallbacks(new a());
        }
    }

    private static boolean b(Activity activity) {
        return a != null && a.get() == activity;
    }
}
