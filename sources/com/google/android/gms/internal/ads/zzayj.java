package com.google.android.gms.internal.ads;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import java.util.concurrent.Callable;

final /* synthetic */ class zzayj implements Callable {
    private final Context zzcmy;
    private final zzayh zzeli;

    zzayj(zzayh zzayh, Context context) {
        this.zzeli = zzayh;
        this.zzcmy = context;
    }

    public final Object call() {
        Context context = this.zzcmy;
        if (!((Boolean) zzwu.zzpz().zzd(zzaan.zzcyn)).booleanValue()) {
            return null;
        }
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        Bundle bundle = new Bundle();
        int i = 0;
        if (defaultSharedPreferences.contains("IABConsent_CMPPresent")) {
            bundle.putBoolean("IABConsent_CMPPresent", defaultSharedPreferences.getBoolean("IABConsent_CMPPresent", false));
        }
        String[] strArr = new String[]{"IABConsent_SubjectToGDPR", "IABConsent_ConsentString", "IABConsent_ParsedPurposeConsents", "IABConsent_ParsedVendorConsents"};
        while (i < 4) {
            String str = strArr[i];
            if (defaultSharedPreferences.contains(str)) {
                bundle.putString(str, defaultSharedPreferences.getString(str, null));
            }
            i++;
        }
        return bundle;
    }
}
