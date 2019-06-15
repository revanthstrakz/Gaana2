package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.app.Application.ActivityLifecycleCallbacks;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import com.google.android.gms.ads.internal.zzbv;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@TargetApi(14)
final class zzsu implements ActivityLifecycleCallbacks {
    @Nullable
    private Activity mActivity;
    private Context mContext;
    private final Object mLock = new Object();
    private boolean zzbps = false;
    private boolean zzbxs = true;
    private boolean zzbxt = false;
    private final List<zzsw> zzbxu = new ArrayList();
    private final List<zztj> zzbxv = new ArrayList();
    private Runnable zzbxw;
    private long zzbxx;

    zzsu() {
    }

    public final void onActivityCreated(Activity activity, Bundle bundle) {
    }

    public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    public final void onActivityStopped(Activity activity) {
    }

    public final void zza(Application application, Context context) {
        if (!this.zzbps) {
            application.registerActivityLifecycleCallbacks(this);
            if (context instanceof Activity) {
                setActivity((Activity) context);
            }
            this.mContext = application;
            this.zzbxx = ((Long) zzwu.zzpz().zzd(zzaan.zzcrm)).longValue();
            this.zzbps = true;
        }
    }

    public final void zza(zzsw zzsw) {
        synchronized (this.mLock) {
            this.zzbxu.add(zzsw);
        }
    }

    @Nullable
    public final Activity getActivity() {
        return this.mActivity;
    }

    @Nullable
    public final Context getContext() {
        return this.mContext;
    }

    public final void onActivityStarted(Activity activity) {
        setActivity(activity);
    }

    public final void onActivityResumed(Activity activity) {
        setActivity(activity);
        this.zzbxt = false;
        int i = this.zzbxs ^ 1;
        this.zzbxs = true;
        if (this.zzbxw != null) {
            zzayh.zzelc.removeCallbacks(this.zzbxw);
        }
        synchronized (this.mLock) {
            for (zztj onActivityResumed : this.zzbxv) {
                try {
                    onActivityResumed.onActivityResumed(activity);
                } catch (Exception e) {
                    zzbv.zzlj().zza(e, "AppActivityTracker.ActivityListener.onActivityResumed");
                    zzbbd.zzb("", e);
                }
            }
            if (i != 0) {
                for (zzsw zzs : this.zzbxu) {
                    try {
                        zzs.zzs(true);
                    } catch (Exception e2) {
                        zzbbd.zzb("", e2);
                    }
                }
            } else {
                zzbbd.zzdn("App is still foreground.");
            }
        }
    }

    public final void onActivityPaused(Activity activity) {
        setActivity(activity);
        synchronized (this.mLock) {
            for (zztj onActivityPaused : this.zzbxv) {
                try {
                    onActivityPaused.onActivityPaused(activity);
                } catch (Exception e) {
                    zzbv.zzlj().zza(e, "AppActivityTracker.ActivityListener.onActivityPaused");
                    zzbbd.zzb("", e);
                }
            }
        }
        this.zzbxt = true;
        if (this.zzbxw != null) {
            zzayh.zzelc.removeCallbacks(this.zzbxw);
        }
        Handler handler = zzayh.zzelc;
        zzsv zzsv = new zzsv(this);
        this.zzbxw = zzsv;
        handler.postDelayed(zzsv, this.zzbxx);
    }

    public final void onActivityDestroyed(Activity activity) {
        synchronized (this.mLock) {
            if (this.mActivity == null) {
                return;
            }
            if (this.mActivity.equals(activity)) {
                this.mActivity = null;
            }
            Iterator it = this.zzbxv.iterator();
            while (it.hasNext()) {
                try {
                    if (((zztj) it.next()).zza(activity)) {
                        it.remove();
                    }
                } catch (Exception e) {
                    zzbv.zzlj().zza(e, "AppActivityTracker.ActivityListener.onActivityDestroyed");
                    zzbbd.zzb("", e);
                }
            }
        }
    }

    private final void setActivity(Activity activity) {
        synchronized (this.mLock) {
            if (!activity.getClass().getName().startsWith("com.google.android.gms.ads")) {
                this.mActivity = activity;
            }
        }
    }
}
