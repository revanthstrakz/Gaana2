package com.google.android.gms.cast;

import android.text.TextUtils;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.internal.cast.zzef;
import org.json.JSONException;
import org.json.JSONObject;

public final class zzam {
    public final JSONObject toJson() {
        JSONObject jSONObject = new JSONObject();
        try {
            if (!TextUtils.isEmpty(null)) {
                jSONObject.put("id", null);
            }
            if (!TextUtils.isEmpty(null)) {
                jSONObject.put("entity", null);
            }
            if (!TextUtils.isEmpty(null)) {
                jSONObject.put("name", null);
            }
            String zza = zzef.zza(Integer.valueOf(0));
            if (zza != null) {
                jSONObject.put("repeatMode", zza);
            }
            jSONObject.put("startIndex", 0);
            jSONObject.put("startTime", 0.0d);
        } catch (JSONException unused) {
        }
        return jSONObject;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof zzam) && TextUtils.equals(null, null) && TextUtils.equals(null, null) && TextUtils.equals(null, null) && Objects.equal(null, null) && Objects.equal(null, null);
    }

    public final int hashCode() {
        return Objects.hashCode(null, null, Integer.valueOf(0), null, null, Integer.valueOf(0), null, Integer.valueOf(0), Double.valueOf(0.0d));
    }
}
