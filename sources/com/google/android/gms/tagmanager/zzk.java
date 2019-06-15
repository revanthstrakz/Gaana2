package com.google.android.gms.tagmanager;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import com.google.android.gms.internal.measurement.zza;
import com.google.android.gms.internal.measurement.zzp;
import java.util.Map;

final class zzk extends zzbq {
    private static final String ID = zza.APP_VERSION.toString();
    private final Context zzri;

    public zzk(Context context) {
        super(ID, new String[0]);
        this.zzri = context;
    }

    public final boolean zznk() {
        return true;
    }

    public final zzp zzc(Map<String, zzp> map) {
        try {
            return zzgj.zzj(Integer.valueOf(this.zzri.getPackageManager().getPackageInfo(this.zzri.getPackageName(), 0).versionCode));
        } catch (NameNotFoundException e) {
            String packageName = this.zzri.getPackageName();
            String message = e.getMessage();
            StringBuilder stringBuilder = new StringBuilder((25 + String.valueOf(packageName).length()) + String.valueOf(message).length());
            stringBuilder.append("Package name ");
            stringBuilder.append(packageName);
            stringBuilder.append(" not found. ");
            stringBuilder.append(message);
            zzdi.e(stringBuilder.toString());
            return zzgj.zzqq();
        }
    }
}
