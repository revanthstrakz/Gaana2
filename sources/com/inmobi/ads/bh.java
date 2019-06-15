package com.inmobi.ads;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application.ActivityLifecycleCallbacks;
import android.content.Context;
import android.os.Bundle;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Picasso.Builder;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

@TargetApi(14)
public class bh {
    private static final String a = "bh";
    @SuppressLint({"StaticFieldLeak"})
    private static volatile Picasso b;
    private static final Object c = new Object();
    private static List<WeakReference<Context>> d = new ArrayList();
    private static ActivityLifecycleCallbacks e = new ActivityLifecycleCallbacks() {
        public final void onActivityCreated(Activity activity, Bundle bundle) {
        }

        public final void onActivityPaused(Activity activity) {
        }

        public final void onActivityResumed(Activity activity) {
        }

        public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        }

        public final void onActivityStarted(Activity activity) {
        }

        public final void onActivityStopped(Activity activity) {
        }

        public final void onActivityDestroyed(Activity activity) {
            synchronized (bh.c) {
                if (bh.b != null && bh.c(activity)) {
                    activity.getApplication().unregisterActivityLifecycleCallbacks(bh.e);
                    bh.d.remove(activity);
                    if (bh.d.isEmpty()) {
                        bh.a;
                        StringBuilder stringBuilder = new StringBuilder("Picasso instance ");
                        stringBuilder.append(bh.b.toString());
                        stringBuilder.append(" shutdown");
                        bh.b.shutdown();
                        bh.b = null;
                    }
                }
            }
        }
    };

    public static Picasso a(Context context) {
        synchronized (c) {
            if (!c(context)) {
                d.add(new WeakReference(context));
            }
            if (b == null) {
                b = new Builder(context).build();
                if (context instanceof Activity) {
                    ((Activity) context).getApplication().registerActivityLifecycleCallbacks(e);
                }
            }
        }
        return b;
    }

    private static boolean c(Context context) {
        for (int i = 0; i < d.size(); i++) {
            Context context2 = (Context) ((WeakReference) d.get(i)).get();
            if (context2 != null && context2.equals(context)) {
                return true;
            }
        }
        return false;
    }
}
