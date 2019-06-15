package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import com.google.android.gms.common.GooglePlayServicesUtilLight;
import com.google.android.gms.common.util.VisibleForTesting;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

@zzark
public final class zzaww {
    private final AtomicReference<ThreadPoolExecutor> zzegt = new AtomicReference(null);
    private final Object zzegu = new Object();
    @Nullable
    private String zzegv = null;
    @Nullable
    private String zzegw = null;
    @VisibleForTesting
    private final AtomicBoolean zzegx = new AtomicBoolean(false);
    @VisibleForTesting
    private final AtomicInteger zzegy = new AtomicInteger(-1);
    private final AtomicReference<Object> zzegz = new AtomicReference(null);
    private final AtomicReference<Object> zzeha = new AtomicReference(null);
    private ConcurrentMap<String, Method> zzehb = new ConcurrentHashMap(9);
    private final AtomicReference<zzbik> zzehc = new AtomicReference(null);
    private final List<FutureTask> zzehd = new ArrayList();

    public final boolean zzv(Context context) {
        if (!((Boolean) zzwu.zzpz().zzd(zzaan.zzcqp)).booleanValue() || this.zzegx.get()) {
            return false;
        }
        if (((Boolean) zzwu.zzpz().zzd(zzaan.zzcqy)).booleanValue()) {
            return true;
        }
        if (this.zzegy.get() == -1) {
            zzwu.zzpv();
            if (!zzbat.zzc(context, GooglePlayServicesUtilLight.GOOGLE_PLAY_SERVICES_VERSION_CODE)) {
                zzwu.zzpv();
                if (zzbat.zzbh(context)) {
                    zzbbd.zzeo("Google Play Service is out of date, the Google Mobile Ads SDK will not integrate with Firebase. Admob/Firebase integration requires updated Google Play Service.");
                    this.zzegy.set(0);
                }
            }
            this.zzegy.set(1);
        }
        if (this.zzegy.get() == 1) {
            return true;
        }
        return false;
    }

    public final void zzc(Context context, String str) {
        if (zzv(context)) {
            zzb(context, str, "beginAdUnitExposure");
        }
    }

    public final void zzd(Context context, String str) {
        if (zzv(context)) {
            zzb(context, str, "endAdUnitExposure");
        }
    }

    public final String zzw(Context context) {
        if (!zzv(context)) {
            return "";
        }
        if (!zza(context, "com.google.android.gms.measurement.AppMeasurement", this.zzegz, true)) {
            return "";
        }
        try {
            String str = (String) zzj(context, "getCurrentScreenName").invoke(this.zzegz.get(), new Object[0]);
            if (str == null) {
                str = (String) zzj(context, "getCurrentScreenClass").invoke(this.zzegz.get(), new Object[0]);
            }
            return str != null ? str : "";
        } catch (Exception e) {
            zza(e, "getCurrentScreenName", false);
            return "";
        }
    }

    public final void zze(Context context, String str) {
        if (zzv(context) && (context instanceof Activity) && zza(context, "com.google.firebase.analytics.FirebaseAnalytics", this.zzeha, false)) {
            Method zzk = zzk(context, "setCurrentScreen");
            try {
                Activity activity = (Activity) context;
                zzk.invoke(this.zzeha.get(), new Object[]{activity, str, context.getPackageName()});
            } catch (Exception e) {
                zza(e, "setCurrentScreen", false);
            }
        }
    }

    @Nullable
    public final String zzx(Context context) {
        if (!zzv(context)) {
            return null;
        }
        synchronized (this.zzegu) {
            String str;
            if (this.zzegv != null) {
                str = this.zzegv;
                return str;
            }
            this.zzegv = (String) zza("getGmpAppId", context);
            str = this.zzegv;
            return str;
        }
    }

    @Nullable
    public final String zzy(Context context) {
        if (!zzv(context)) {
            return null;
        }
        long longValue = ((Long) zzwu.zzpz().zzd(zzaan.zzcqu)).longValue();
        if (longValue < 0) {
            return (String) zza("getAppInstanceId", context);
        }
        if (this.zzegt.get() == null) {
            this.zzegt.compareAndSet(null, new ThreadPoolExecutor(((Integer) zzwu.zzpz().zzd(zzaan.zzcqv)).intValue(), ((Integer) zzwu.zzpz().zzd(zzaan.zzcqv)).intValue(), 1, TimeUnit.MINUTES, new LinkedBlockingQueue(), new zzawy(this)));
        }
        Future submit = ((ThreadPoolExecutor) this.zzegt.get()).submit(new zzawx(this, context));
        try {
            return (String) submit.get(longValue, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            submit.cancel(true);
            if (e instanceof TimeoutException) {
                return "TIME_OUT";
            }
            return null;
        }
    }

    @Nullable
    public final String zzz(Context context) {
        if (!zzv(context)) {
            return null;
        }
        Object zza = zza("generateEventId", context);
        if (zza != null) {
            return zza.toString();
        }
        return null;
    }

    @Nullable
    public final String zzaa(Context context) {
        if (!zzv(context)) {
            return null;
        }
        synchronized (this.zzegu) {
            String str;
            if (this.zzegw != null) {
                str = this.zzegw;
                return str;
            }
            this.zzegw = "fa";
            str = this.zzegw;
            return str;
        }
    }

    public final void zzf(Context context, String str) {
        zza(context, "_ac", str);
    }

    public final void zzg(Context context, String str) {
        zza(context, "_ai", str);
    }

    public final void zzh(Context context, String str) {
        zza(context, "_aq", str);
    }

    public final void zza(Context context, String str, String str2, String str3, int i) {
        if (zzv(context)) {
            Bundle zzf = zzf(str, false);
            zzf.putString("_ai", str2);
            zzf.putString("type", str3);
            zzf.putInt("value", i);
            zzb(context, "_ar", zzf);
            StringBuilder stringBuilder = new StringBuilder(75 + String.valueOf(str3).length());
            stringBuilder.append("Log a Firebase reward video event, reward type: ");
            stringBuilder.append(str3);
            stringBuilder.append(", reward value: ");
            stringBuilder.append(i);
            zzaxz.v(stringBuilder.toString());
        }
    }

    public final void zza(Context context, String str, String str2) {
        if (zzv(context)) {
            zzb(context, str, zzf(str2, "_ac".equals(str)));
        }
    }

    private final void zzb(Context context, String str, Bundle bundle) {
        if (zzv(context) && zza(context, "com.google.android.gms.measurement.AppMeasurement", this.zzegz, true)) {
            Method zzab = zzab(context);
            try {
                zzab.invoke(this.zzegz.get(), new Object[]{"am", str, bundle});
            } catch (Exception e) {
                zza(e, "logEventInternal", true);
            }
        }
    }

    private static Bundle zzf(String str, boolean z) {
        Bundle bundle = new Bundle();
        try {
            bundle.putLong("_aeid", Long.parseLong(str));
        } catch (NullPointerException | NumberFormatException e) {
            String str2 = "Invalid event ID: ";
            str = String.valueOf(str);
            zzbbd.zzb(str.length() != 0 ? str2.concat(str) : new String(str2), e);
        }
        if (z) {
            bundle.putInt("_r", 1);
        }
        return bundle;
    }

    private final Method zzab(Context context) {
        Method method = (Method) this.zzehb.get("logEventInternal");
        if (method != null) {
            return method;
        }
        try {
            Method declaredMethod = context.getClassLoader().loadClass("com.google.android.gms.measurement.AppMeasurement").getDeclaredMethod("logEventInternal", new Class[]{String.class, String.class, Bundle.class});
            this.zzehb.put("logEventInternal", declaredMethod);
            return declaredMethod;
        } catch (Exception e) {
            zza(e, "logEventInternal", true);
            return null;
        }
    }

    private final Method zzi(Context context, String str) {
        Method method = (Method) this.zzehb.get(str);
        if (method != null) {
            return method;
        }
        try {
            Method declaredMethod = context.getClassLoader().loadClass("com.google.android.gms.measurement.AppMeasurement").getDeclaredMethod(str, new Class[]{String.class});
            this.zzehb.put(str, declaredMethod);
            return declaredMethod;
        } catch (Exception e) {
            zza(e, str, false);
            return null;
        }
    }

    private final Method zzj(Context context, String str) {
        Method method = (Method) this.zzehb.get(str);
        if (method != null) {
            return method;
        }
        try {
            Method declaredMethod = context.getClassLoader().loadClass("com.google.android.gms.measurement.AppMeasurement").getDeclaredMethod(str, new Class[0]);
            this.zzehb.put(str, declaredMethod);
            return declaredMethod;
        } catch (Exception e) {
            zza(e, str, false);
            return null;
        }
    }

    private final Method zzk(Context context, String str) {
        Method method = (Method) this.zzehb.get(str);
        if (method != null) {
            return method;
        }
        try {
            Method declaredMethod = context.getClassLoader().loadClass("com.google.firebase.analytics.FirebaseAnalytics").getDeclaredMethod(str, new Class[]{Activity.class, String.class, String.class});
            this.zzehb.put(str, declaredMethod);
            return declaredMethod;
        } catch (Exception e) {
            zza(e, str, false);
            return null;
        }
    }

    private final void zzb(Context context, String str, String str2) {
        if (zza(context, "com.google.android.gms.measurement.AppMeasurement", this.zzegz, true)) {
            Method zzi = zzi(context, str2);
            try {
                zzi.invoke(this.zzegz.get(), new Object[]{str});
                StringBuilder stringBuilder = new StringBuilder((37 + String.valueOf(str2).length()) + String.valueOf(str).length());
                stringBuilder.append("Invoke Firebase method ");
                stringBuilder.append(str2);
                stringBuilder.append(", Ad Unit Id: ");
                stringBuilder.append(str);
                zzaxz.v(stringBuilder.toString());
            } catch (Exception e) {
                zza(e, str2, false);
            }
        }
    }

    private final Object zza(String str, Context context) {
        if (!zza(context, "com.google.android.gms.measurement.AppMeasurement", this.zzegz, true)) {
            return null;
        }
        Object invoke;
        try {
            invoke = zzj(context, str).invoke(this.zzegz.get(), new Object[0]);
        } catch (Exception e) {
            zza(e, str, true);
            invoke = null;
        }
        return invoke;
    }

    private final void zza(Exception exception, String str, boolean z) {
        if (!this.zzegx.get()) {
            StringBuilder stringBuilder = new StringBuilder(30 + String.valueOf(str).length());
            stringBuilder.append("Invoke Firebase method ");
            stringBuilder.append(str);
            stringBuilder.append(" error.");
            zzbbd.zzeo(stringBuilder.toString());
            if (z) {
                zzbbd.zzeo("The Google Mobile Ads SDK will not integrate with Firebase. Admob/Firebase integration requires the latest Firebase SDK jar, but Firebase SDK is either missing or out of date");
                this.zzegx.set(true);
            }
        }
    }

    private final boolean zza(Context context, String str, AtomicReference<Object> atomicReference, boolean z) {
        if (atomicReference.get() == null) {
            try {
                atomicReference.compareAndSet(null, context.getClassLoader().loadClass(str).getDeclaredMethod("getInstance", new Class[]{Context.class}).invoke(null, new Object[]{context}));
            } catch (Exception e) {
                zza(e, "getInstance", z);
                return false;
            }
        }
        return true;
    }

    /* Access modifiers changed, original: final|synthetic */
    public final /* synthetic */ String zzac(Context context) throws Exception {
        return (String) zza("getAppInstanceId", context);
    }
}
