package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import com.facebook.internal.NativeProtocol;
import com.google.android.gms.common.GooglePlayServicesUtilLight;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.dynamite.descriptors.com.google.android.gms.ads.dynamite.ModuleDescriptor;

abstract class zzwt<T> {
    @Nullable
    private static final zzxw zzcli = zzps();

    zzwt() {
    }

    @Nullable
    public abstract T zza(zzxw zzxw) throws RemoteException;

    public abstract T zzpq();

    @Nullable
    public abstract T zzpr() throws RemoteException;

    @Nullable
    private static zzxw zzps() {
        try {
            Object newInstance = zzwj.class.getClassLoader().loadClass("com.google.android.gms.ads.internal.ClientApi").newInstance();
            if (newInstance instanceof IBinder) {
                return zzxx.asInterface((IBinder) newInstance);
            }
            zzbbd.zzeo("ClientApi class is not an instance of IBinder.");
            return null;
        } catch (Exception unused) {
            zzbbd.zzeo("Failed to instantiate ClientApi class.");
            return null;
        }
    }

    @Nullable
    private final T zzpt() {
        if (zzcli == null) {
            zzbbd.zzeo("ClientApi class cannot be loaded.");
            return null;
        }
        try {
            return zza(zzcli);
        } catch (RemoteException e) {
            zzbbd.zzc("Cannot invoke local loader using ClientApi class.", e);
            return null;
        }
    }

    @Nullable
    private final T zzpu() {
        try {
            return zzpr();
        } catch (RemoteException e) {
            zzbbd.zzc("Cannot invoke remote loader.", e);
            return null;
        }
    }

    public final T zzd(Context context, boolean z) {
        T zzpt;
        Object obj = 1;
        if (!z) {
            zzwu.zzpv();
            if (!zzbat.zzc(context, GooglePlayServicesUtilLight.GOOGLE_PLAY_SERVICES_VERSION_CODE)) {
                zzbbd.zzdn("Google Play Services is not available.");
                z = true;
            }
        }
        if (DynamiteModule.getLocalVersion(context, ModuleDescriptor.MODULE_ID) > DynamiteModule.getRemoteVersion(context, ModuleDescriptor.MODULE_ID)) {
            z = true;
        }
        zzaan.initialize(context);
        if (((Boolean) zzwu.zzpz().zzd(zzaan.zzcwv)).booleanValue()) {
            z = false;
        }
        if (z) {
            zzpt = zzpt();
            if (zzpt == null) {
                zzpt = zzpu();
            }
        } else {
            T zzpu = zzpu();
            int i = zzpu == null ? 1 : 0;
            if (i != 0) {
                if (zzwu.zzqc().nextInt(((Integer) zzwu.zzpz().zzd(zzaan.zzcyq)).intValue()) != 0) {
                    obj = null;
                }
                if (obj != null) {
                    Bundle bundle = new Bundle();
                    bundle.putString(NativeProtocol.WEB_DIALOG_ACTION, "dynamite_load");
                    bundle.putInt("is_missing", i);
                    zzwu.zzpv().zza(context, zzwu.zzqb().zzdp, "gmob-apps", bundle, true);
                }
            }
            zzpt = zzpu == null ? zzpt() : zzpu;
        }
        return zzpt == null ? zzpq() : zzpt;
    }
}
