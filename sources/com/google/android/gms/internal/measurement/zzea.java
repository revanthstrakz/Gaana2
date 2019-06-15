package com.google.android.gms.internal.measurement;

import android.app.Activity;
import android.app.Application;
import android.app.Application.ActivityLifecycleCallbacks;
import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;
import com.comscore.streaming.Constants;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.gms.common.api.internal.GoogleServices;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.DefaultClock;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.dynamite.DynamiteModule.LoadingException;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.android.gms.measurement.internal.zzcx;
import com.google.android.gms.measurement.internal.zzcy;
import com.til.colombia.android.internal.e;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

public class zzea {
    private static volatile zzea zzadk;
    private final String zzadh;
    private final ExecutorService zzadl;
    private final AppMeasurementSdk zzadm;
    private Map<zzcy, zzd> zzadn;
    private int zzado;
    private boolean zzadp;
    private String zzadq;
    private zzdn zzadr;
    protected final Clock zzrz;

    abstract class zzb implements Runnable {
        final long timestamp;
        final long zzaev;
        private final boolean zzaew;

        zzb(zzea zzea) {
            this(true);
        }

        public abstract void zzgd() throws RemoteException;

        /* Access modifiers changed, original: protected */
        public void zzge() {
        }

        zzb(boolean z) {
            this.timestamp = zzea.this.zzrz.currentTimeMillis();
            this.zzaev = zzea.this.zzrz.elapsedRealtime();
            this.zzaew = z;
        }

        public void run() {
            if (zzea.this.zzadp) {
                zzge();
                return;
            }
            try {
                zzgd();
            } catch (Exception e) {
                zzea.this.zza(e, false, this.zzaew);
                zzge();
            }
        }
    }

    class zze implements ActivityLifecycleCallbacks {
        zze() {
        }

        public final void onActivityCreated(Activity activity, Bundle bundle) {
            zzea.this.zza(new zzex(this, activity, bundle));
        }

        public final void onActivityStarted(Activity activity) {
            zzea.this.zza(new zzey(this, activity));
        }

        public final void onActivityResumed(Activity activity) {
            zzea.this.zza(new zzez(this, activity));
        }

        public final void onActivityPaused(Activity activity) {
            zzea.this.zza(new zzfa(this, activity));
        }

        public final void onActivityStopped(Activity activity) {
            zzea.this.zza(new zzfb(this, activity));
        }

        public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
            zza zza = new zza();
            zzea.this.zza(new zzfc(this, activity, zza));
            Bundle zzl = zza.zzl(50);
            if (zzl != null) {
                bundle.putAll(zzl);
            }
        }

        public final void onActivityDestroyed(Activity activity) {
            zzea.this.zza(new zzfd(this, activity));
        }
    }

    class zza extends zzdr {
        private final AtomicReference<Bundle> zzaet = new AtomicReference();
        private boolean zzaeu;

        zza() {
        }

        public final void zzb(Bundle bundle) {
            synchronized (this.zzaet) {
                try {
                    this.zzaet.set(bundle);
                    this.zzaeu = true;
                    this.zzaet.notify();
                } catch (Throwable th) {
                    this.zzaet.notify();
                }
            }
        }

        /* Access modifiers changed, original: final */
        public final String zzk(long j) {
            return (String) zza(zzl(j), String.class);
        }

        /* Access modifiers changed, original: final */
        public final Bundle zzl(long j) {
            Bundle bundle;
            synchronized (this.zzaet) {
                if (!this.zzaeu) {
                    try {
                        this.zzaet.wait(j);
                    } catch (InterruptedException unused) {
                        return null;
                    }
                }
                bundle = (Bundle) this.zzaet.get();
            }
            return bundle;
        }

        /* Access modifiers changed, original: final */
        public final <T> T zza(Bundle bundle, Class<T> cls) {
            if (bundle == null) {
                return null;
            }
            Object obj = bundle.get(e.o);
            if (obj == null) {
                return null;
            }
            try {
                return cls.cast(obj);
            } catch (ClassCastException e) {
                String str = "Unexpected object type. Expected, Received";
                zzea.this.zzc(5, str, cls.getCanonicalName(), obj.getClass().getCanonicalName(), e);
                Log.w(zzea.this.zzadh, String.format(String.valueOf(str).concat(": %s, %s"), new Object[]{r10, r9}), e);
                throw e;
            }
        }
    }

    static class zzc extends zzdu {
        private final zzcx zzaex;

        zzc(zzcx zzcx) {
            this.zzaex = zzcx;
        }

        public final void onEvent(String str, String str2, Bundle bundle, long j) {
            this.zzaex.interceptEvent(str, str2, bundle, j);
        }

        public final int id() {
            return this.zzaex.hashCode();
        }
    }

    static class zzd extends zzdu {
        private final zzcy zzaey;

        zzd(zzcy zzcy) {
            this.zzaey = zzcy;
        }

        public final void onEvent(String str, String str2, Bundle bundle, long j) {
            this.zzaey.onEvent(str, str2, bundle, j);
        }

        public final int id() {
            return this.zzaey.hashCode();
        }
    }

    public static zzea zza(Context context, String str, String str2, String str3, Bundle bundle) {
        Preconditions.checkNotNull(context);
        if (zzadk == null) {
            synchronized (zzea.class) {
                if (zzadk == null) {
                    zzadk = new zzea(context, str, str2, str3, bundle);
                }
            }
        }
        return zzadk;
    }

    public final AppMeasurementSdk zzga() {
        return this.zzadm;
    }

    private zzea(Context context, String str, String str2, String str3, Bundle bundle) {
        if (str == null || !zze(str2, str3)) {
            this.zzadh = "FA";
        } else {
            this.zzadh = str;
        }
        this.zzrz = DefaultClock.getInstance();
        this.zzadl = new ThreadPoolExecutor(0, 1, 30, TimeUnit.SECONDS, new LinkedBlockingQueue());
        Application application = (Application) context.getApplicationContext();
        if (application == null) {
            Log.w(this.zzadh, "Unable to register lifecycle notifications. Application null.");
        } else {
            application.registerActivityLifecycleCallbacks(new zze());
        }
        this.zzadm = new AppMeasurementSdk(this);
        int i = 0;
        int i2 = (!zzf(context) || zzgb()) ? true : 0;
        if (i2 == 0) {
            this.zzadq = null;
            this.zzadp = true;
            Log.w(this.zzadh, "Disabling data collection. Found google_app_id in strings.xml but Google Analytics for Firebase is missing. Remove this value or add Google Analytics for Firebase to resume data collection.");
            return;
        }
        if (zze(str2, str3)) {
            this.zzadq = str2;
        } else {
            this.zzadq = "fa";
            if (str2 == null || str3 == null) {
                i2 = str2 == null ? 1 : 0;
                if (str3 == null) {
                    i = 1;
                }
                if ((i2 ^ i) != 0) {
                    Log.w(this.zzadh, "Specified origin or custom app id is null. Both parameters will be ignored.");
                }
            } else {
                Log.v(this.zzadh, "Deferring to Google Analytics for Firebase for event data collection. https://goo.gl/J1sWQy");
                this.zzadp = true;
                return;
            }
        }
        zza(new zzeb(this, context, str2, str3, bundle));
    }

    private static boolean zzf(Context context) {
        try {
            GoogleServices.initialize(context);
            if (GoogleServices.getGoogleAppId() != null) {
                return true;
            }
            return false;
        } catch (IllegalStateException unused) {
            return false;
        }
    }

    private static boolean zze(String str, String str2) {
        return (str2 == null || str == null || zzgb()) ? false : true;
    }

    private final void zza(zzb zzb) {
        this.zzadl.execute(zzb);
    }

    /* Access modifiers changed, original: protected|final */
    public final zzdn zzg(Context context) {
        try {
            return zzdo.asInterface(DynamiteModule.load(context, DynamiteModule.PREFER_HIGHEST_OR_REMOTE_VERSION, "com.google.android.gms.measurement.dynamite").instantiate("com.google.android.gms.measurement.internal.AppMeasurementDynamiteService"));
        } catch (LoadingException e) {
            zza(e, true, false);
            return null;
        }
    }

    private static int zzh(Context context) {
        return DynamiteModule.getRemoteVersion(context, "com.google.android.gms.measurement.dynamite");
    }

    private static int zzi(Context context) {
        return DynamiteModule.getLocalVersion(context, "com.google.android.gms.measurement.dynamite");
    }

    private final void zza(Exception exception, boolean z, boolean z2) {
        this.zzadp |= z;
        if (z) {
            Log.w(this.zzadh, "Data collection startup failed. No data will be collected.", exception);
            return;
        }
        String str = "Error with data collection. Data lost.";
        if (z2) {
            zzc(5, str, exception, null, null);
        }
        Log.w(this.zzadh, str, exception);
    }

    private static boolean zzgb() {
        try {
            Class.forName("com.google.firebase.analytics.FirebaseAnalytics");
            return true;
        } catch (ClassNotFoundException unused) {
            return false;
        }
    }

    public final void zza(zzcx zzcx) {
        zza(new zzei(this, zzcx));
    }

    public final void zza(zzcy zzcy) {
        zza(new zzes(this, zzcy));
    }

    public final void zzb(zzcy zzcy) {
        zza(new zzet(this, zzcy));
    }

    public final void logEventInternal(String str, String str2, Bundle bundle) {
        zza(str, str2, bundle, true, true, null);
    }

    public final void logEventInternalNoInterceptor(String str, String str2, Bundle bundle, long j) {
        zza(str, str2, bundle, true, false, Long.valueOf(j));
    }

    private final void zza(String str, String str2, Bundle bundle, boolean z, boolean z2, Long l) {
        zza(new zzeu(this, l, str, str2, bundle, true, z2));
    }

    public final void zza(String str, String str2, Object obj, boolean z) {
        zza(new zzev(this, str, str2, obj, true));
    }

    public final void setConditionalUserProperty(Bundle bundle) {
        zza(new zzew(this, bundle));
    }

    public final void clearConditionalUserProperty(String str, String str2, Bundle bundle) {
        zza(new zzec(this, str, str2, bundle));
    }

    public final List<Bundle> getConditionalUserProperties(String str, String str2) {
        zza zza = new zza();
        zza(new zzed(this, str, str2, zza));
        List list = (List) zza.zza(zza.zzl(DefaultRenderersFactory.DEFAULT_ALLOWED_VIDEO_JOINING_TIME_MS), List.class);
        return list == null ? Collections.emptyList() : list;
    }

    public final void setCurrentScreen(Activity activity, String str, String str2) {
        zza(new zzee(this, activity, str, str2));
    }

    public final void setMeasurementEnabled(boolean z) {
        zza(new zzef(this, z));
    }

    public final void beginAdUnitExposure(String str) {
        zza(new zzeg(this, str));
    }

    public final void endAdUnitExposure(String str) {
        zza(new zzeh(this, str));
    }

    public final String getGmpAppId() {
        zza zza = new zza();
        zza(new zzej(this, zza));
        return zza.zzk(500);
    }

    public final String zzgc() {
        zza zza = new zza();
        zza(new zzek(this, zza));
        return zza.zzk(50);
    }

    public final long generateEventId() {
        zza zza = new zza();
        zza(new zzel(this, zza));
        Long l = (Long) zza.zza(zza.zzl(500), Long.class);
        if (l != null) {
            return l.longValue();
        }
        long nextLong = new Random(System.nanoTime() ^ this.zzrz.currentTimeMillis()).nextLong();
        int i = this.zzado + 1;
        this.zzado = i;
        return nextLong + ((long) i);
    }

    public final String getCurrentScreenName() {
        zza zza = new zza();
        zza(new zzem(this, zza));
        return zza.zzk(500);
    }

    public final String getCurrentScreenClass() {
        zza zza = new zza();
        zza(new zzen(this, zza));
        return zza.zzk(500);
    }

    public final Map<String, Object> getUserProperties(String str, String str2, boolean z) {
        zza zza = new zza();
        zza(new zzeo(this, str, str2, z, zza));
        Bundle zzl = zza.zzl(DefaultRenderersFactory.DEFAULT_ALLOWED_VIDEO_JOINING_TIME_MS);
        if (zzl == null || zzl.size() == 0) {
            return Collections.emptyMap();
        }
        HashMap hashMap = new HashMap(zzl.size());
        for (String str3 : zzl.keySet()) {
            Object obj = zzl.get(str3);
            if ((obj instanceof Double) || (obj instanceof Long) || (obj instanceof String)) {
                hashMap.put(str3, obj);
            }
        }
        return hashMap;
    }

    public final void zzc(int i, String str, Object obj, Object obj2, Object obj3) {
        zza(new zzep(this, false, 5, str, obj, obj2, obj3));
    }

    public final Bundle zza(Bundle bundle, boolean z) {
        zza zza = new zza();
        zza(new zzeq(this, bundle, zza));
        return z ? zza.zzl(DefaultRenderersFactory.DEFAULT_ALLOWED_VIDEO_JOINING_TIME_MS) : null;
    }

    public final int getMaxUserProperties(String str) {
        zza zza = new zza();
        zza(new zzer(this, str, zza));
        Integer num = (Integer) zza.zza(zza.zzl(Constants.HEARTBEAT_STAGE_ONE_INTERVAL), Integer.class);
        if (num == null) {
            return 25;
        }
        return num.intValue();
    }

    public final String getAppIdOrigin() {
        return this.zzadq;
    }
}
