package com.google.android.gms.common.api.internal;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.v4.util.ArrayMap;
import com.google.android.gms.internal.common.zze;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.Map.Entry;
import java.util.WeakHashMap;

public final class zza extends Fragment implements LifecycleFragment {
    private static WeakHashMap<Activity, WeakReference<zza>> zzbe = new WeakHashMap();
    private Map<String, LifecycleCallback> zzbf = new ArrayMap();
    private int zzbg = 0;
    private Bundle zzbh;

    public static zza zza(Activity activity) {
        WeakReference weakReference = (WeakReference) zzbe.get(activity);
        if (weakReference != null) {
            zza zza = (zza) weakReference.get();
            if (zza != null) {
                return zza;
            }
        }
        try {
            Object obj = (zza) activity.getFragmentManager().findFragmentByTag("LifecycleFragmentImpl");
            if (obj == null || obj.isRemoving()) {
                obj = new zza();
                activity.getFragmentManager().beginTransaction().add(obj, "LifecycleFragmentImpl").commitAllowingStateLoss();
            }
            zzbe.put(activity, new WeakReference(obj));
            return obj;
        } catch (ClassCastException e) {
            throw new IllegalStateException("Fragment with tag LifecycleFragmentImpl is not a LifecycleFragmentImpl", e);
        }
    }

    public final <T extends LifecycleCallback> T getCallbackOrNull(String str, Class<T> cls) {
        return (LifecycleCallback) cls.cast(this.zzbf.get(str));
    }

    public final void addCallback(String str, @NonNull LifecycleCallback lifecycleCallback) {
        if (this.zzbf.containsKey(str)) {
            StringBuilder stringBuilder = new StringBuilder(59 + String.valueOf(str).length());
            stringBuilder.append("LifecycleCallback with tag ");
            stringBuilder.append(str);
            stringBuilder.append(" already added to this fragment.");
            throw new IllegalArgumentException(stringBuilder.toString());
        }
        this.zzbf.put(str, lifecycleCallback);
        if (this.zzbg > 0) {
            new zze(Looper.getMainLooper()).post(new zzb(this, lifecycleCallback, str));
        }
    }

    public final boolean isCreated() {
        return this.zzbg > 0;
    }

    public final boolean isStarted() {
        return this.zzbg >= 2;
    }

    public final Activity getLifecycleActivity() {
        return getActivity();
    }

    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.zzbg = 1;
        this.zzbh = bundle;
        for (Entry entry : this.zzbf.entrySet()) {
            ((LifecycleCallback) entry.getValue()).onCreate(bundle != null ? bundle.getBundle((String) entry.getKey()) : null);
        }
    }

    public final void onStart() {
        super.onStart();
        this.zzbg = 2;
        for (LifecycleCallback onStart : this.zzbf.values()) {
            onStart.onStart();
        }
    }

    public final void onResume() {
        super.onResume();
        this.zzbg = 3;
        for (LifecycleCallback onResume : this.zzbf.values()) {
            onResume.onResume();
        }
    }

    public final void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        for (LifecycleCallback onActivityResult : this.zzbf.values()) {
            onActivityResult.onActivityResult(i, i2, intent);
        }
    }

    public final void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        if (bundle != null) {
            for (Entry entry : this.zzbf.entrySet()) {
                Bundle bundle2 = new Bundle();
                ((LifecycleCallback) entry.getValue()).onSaveInstanceState(bundle2);
                bundle.putBundle((String) entry.getKey(), bundle2);
            }
        }
    }

    public final void onStop() {
        super.onStop();
        this.zzbg = 4;
        for (LifecycleCallback onStop : this.zzbf.values()) {
            onStop.onStop();
        }
    }

    public final void onDestroy() {
        super.onDestroy();
        this.zzbg = 5;
        for (LifecycleCallback onDestroy : this.zzbf.values()) {
            onDestroy.onDestroy();
        }
    }

    public final void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        super.dump(str, fileDescriptor, printWriter, strArr);
        for (LifecycleCallback dump : this.zzbf.values()) {
            dump.dump(str, fileDescriptor, printWriter, strArr);
        }
    }
}
