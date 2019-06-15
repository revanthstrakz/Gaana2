package com.google.android.gms.internal.icing;

import com.google.ads.mediation.inmobi.InMobiNetworkValues;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.HashMap;
import java.util.Map;

@ShowFirstParty
@VisibleForTesting
public final class zzr {
    @VisibleForTesting
    static final String[] zzy;
    private static final Map<String, Integer> zzz = new HashMap(zzy.length);

    public static String zza(int i) {
        return (i < 0 || i >= zzy.length) ? null : zzy[i];
    }

    public static int zzb(String str) {
        Integer num = (Integer) zzz.get(str);
        if (num != null) {
            return num.intValue();
        }
        StringBuilder stringBuilder = new StringBuilder(44 + String.valueOf(str).length());
        stringBuilder.append("[");
        stringBuilder.append(str);
        stringBuilder.append("] is not a valid global search section name");
        throw new IllegalArgumentException(stringBuilder.toString());
    }

    static {
        r0 = new String[10];
        int i = 0;
        r0[0] = "text1";
        r0[1] = "text2";
        r0[2] = InMobiNetworkValues.ICON;
        r0[3] = "intent_action";
        r0[4] = "intent_data";
        r0[5] = "intent_data_id";
        r0[6] = "intent_extra_data";
        r0[7] = "suggest_large_icon";
        r0[8] = "intent_activity";
        r0[9] = "thing_proto";
        zzy = r0;
        while (i < zzy.length) {
            zzz.put(zzy[i], Integer.valueOf(i));
            i++;
        }
    }
}
