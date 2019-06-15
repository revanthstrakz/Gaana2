package com.google.android.gms.internal.ads;

import android.os.Bundle;
import com.google.ads.mediation.admob.AdMobAdapter;

@zzark
public final class zzbal {
    public static boolean zzf(Bundle bundle) {
        bundle = bundle.getBundle(AdMobAdapter.class.getName());
        if (bundle == null || !bundle.getBoolean("render_test_ad_label", false)) {
            return false;
        }
        return true;
    }
}
