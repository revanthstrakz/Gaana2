package com.google.android.gms.internal.ads;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import org.json.JSONObject;

final class zzaaf extends zzaac<Long> {
    zzaaf(int i, String str, Long l) {
        super(i, str, l, null);
    }

    public final /* synthetic */ void zza(Editor editor, Object obj) {
        editor.putLong(getKey(), ((Long) obj).longValue());
    }

    public final /* synthetic */ Object zzb(JSONObject jSONObject) {
        return Long.valueOf(jSONObject.optLong(getKey(), ((Long) zzqv()).longValue()));
    }

    public final /* synthetic */ Object zza(SharedPreferences sharedPreferences) {
        return Long.valueOf(sharedPreferences.getLong(getKey(), ((Long) zzqv()).longValue()));
    }
}
