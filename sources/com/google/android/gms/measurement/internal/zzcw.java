package com.google.android.gms.measurement.internal;

import com.facebook.AccessToken;
import com.moe.pushlibrary.providers.MoEDataContract.BaseColumns;

public class zzcw {
    public static final String[] zzaqu = new String[]{"firebase_last_notification", "first_open_time", "first_visit_time", "last_deep_link_referrer", AccessToken.USER_ID_KEY, "first_open_after_install", "lifetime_user_engagement", "google_allow_ad_personalization_signals", "session_number", "session_id"};
    public static final String[] zzaqv = new String[]{"_ln", "_fot", "_fvt", "_ldl", BaseColumns._ID, "_fi", "_lte", "_ap", "_sno", "_sid"};

    protected zzcw() {
    }

    public static String zzco(String str) {
        return zzdw.zza(str, zzaqu, zzaqv);
    }
}
