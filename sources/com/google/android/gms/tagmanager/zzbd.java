package com.google.android.gms.tagmanager;

import android.os.Build;
import com.google.android.gms.internal.measurement.zza;
import com.google.android.gms.internal.measurement.zzp;
import java.util.Map;

final class zzbd extends zzbq {
    private static final String ID = zza.DEVICE_NAME.toString();

    public zzbd() {
        super(ID, new String[0]);
    }

    public final boolean zznk() {
        return true;
    }

    public final zzp zzc(Map<String, zzp> map) {
        String str = Build.MANUFACTURER;
        Object obj = Build.MODEL;
        if (!(obj.startsWith(str) || str.equals("unknown"))) {
            StringBuilder stringBuilder = new StringBuilder((1 + String.valueOf(str).length()) + String.valueOf(obj).length());
            stringBuilder.append(str);
            stringBuilder.append(" ");
            stringBuilder.append(obj);
            obj = stringBuilder.toString();
        }
        return zzgj.zzj(obj);
    }
}
