package com.google.android.gms.internal.icing;

import android.content.Context;
import android.support.annotation.GuardedBy;
import android.support.v4.content.PermissionChecker;
import android.util.Log;

final class zzbi implements zzbf {
    @GuardedBy("GservicesLoader.class")
    static zzbi zzdb;
    private final Context zzdc;

    static zzbi zzc(Context context) {
        zzbi zzbi;
        synchronized (zzbi.class) {
            if (zzdb == null) {
                zzdb = (PermissionChecker.checkSelfPermission(context, "com.google.android.providers.gsf.permission.READ_GSERVICES") == 0 ? 1 : null) != null ? new zzbi(context) : new zzbi();
            }
            zzbi = zzdb;
        }
        return zzbi;
    }

    private zzbi(Context context) {
        this.zzdc = context;
        this.zzdc.getContentResolver().registerContentObserver(zzay.CONTENT_URI, true, new zzbk(this, null));
    }

    private zzbi() {
        this.zzdc = null;
    }

    private final String zzo(String str) {
        if (this.zzdc == null) {
            return null;
        }
        try {
            return (String) zzbg.zza(new zzbj(this, str));
        } catch (SecurityException e) {
            String str2 = "GservicesLoader";
            String str3 = "Unable to read GServices for: ";
            str = String.valueOf(str);
            Log.e(str2, str.length() != 0 ? str3.concat(str) : new String(str3), e);
            return null;
        }
    }

    public final /* synthetic */ Object zzn(String str) {
        return zzo(str);
    }

    /* Access modifiers changed, original: final|synthetic */
    public final /* synthetic */ String zzp(String str) {
        return zzay.zza(this.zzdc.getContentResolver(), str, null);
    }
}
