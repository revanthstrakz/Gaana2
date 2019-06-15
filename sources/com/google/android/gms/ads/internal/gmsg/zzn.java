package com.google.android.gms.ads.internal.gmsg;

import com.google.android.gms.internal.ads.zzbbd;
import java.util.Map;

final class zzn implements zzu<Object> {
    zzn() {
    }

    public final void zza(Object obj, Map<String, String> map) {
        String str = "Received log message: ";
        String valueOf = String.valueOf((String) map.get("string"));
        zzbbd.zzen(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
    }
}
