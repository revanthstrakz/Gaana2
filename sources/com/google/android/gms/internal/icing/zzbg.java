package com.google.android.gms.internal.icing;

import android.os.Binder;

public final /* synthetic */ class zzbg {
    public static <V> V zza(zzbh<V> zzbh) {
        long clearCallingIdentity;
        try {
            return zzbh.zzv();
        } catch (SecurityException unused) {
            clearCallingIdentity = Binder.clearCallingIdentity();
            V zzv = zzbh.zzv();
            return zzv;
        } finally {
            Binder.restoreCallingIdentity(clearCallingIdentity);
        }
    }
}
