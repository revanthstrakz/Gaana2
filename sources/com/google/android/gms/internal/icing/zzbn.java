package com.google.android.gms.internal.icing;

import android.util.Log;

final class zzbn extends zzbl<Boolean> {
    zzbn(zzbo zzbo, String str, Boolean bool) {
        super(zzbo, str, bool, null);
    }

    /* Access modifiers changed, original: final|synthetic */
    public final /* synthetic */ Object zza(Object obj) {
        if (obj instanceof Boolean) {
            return (Boolean) obj;
        }
        if (obj instanceof String) {
            String str = (String) obj;
            if (zzay.zzcg.matcher(str).matches()) {
                return Boolean.valueOf(true);
            }
            if (zzay.zzch.matcher(str).matches()) {
                return Boolean.valueOf(false);
            }
        }
        String zzy = super.zzy();
        String valueOf = String.valueOf(obj);
        StringBuilder stringBuilder = new StringBuilder((28 + String.valueOf(zzy).length()) + String.valueOf(valueOf).length());
        stringBuilder.append("Invalid boolean value for ");
        stringBuilder.append(zzy);
        stringBuilder.append(": ");
        stringBuilder.append(valueOf);
        Log.e("PhenotypeFlag", stringBuilder.toString());
        return null;
    }
}
