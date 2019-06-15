package com.google.android.gms.internal.ads;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.search.SearchAdRequest;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;

@zzark
public final class zzwe {
    public static final zzwe zzckj = new zzwe();

    @VisibleForTesting
    protected zzwe() {
    }

    public static zzwb zza(Context context, zzyx zzyx) {
        Context context2;
        List list;
        String zza;
        zzyx zzyx2 = zzyx;
        Date birthday = zzyx.getBirthday();
        long time = birthday != null ? birthday.getTime() : -1;
        String contentUrl = zzyx.getContentUrl();
        int gender = zzyx.getGender();
        Set keywords = zzyx.getKeywords();
        if (keywords.isEmpty()) {
            context2 = context;
            list = null;
        } else {
            list = Collections.unmodifiableList(new ArrayList(keywords));
            context2 = context;
        }
        boolean isTestDevice = zzyx2.isTestDevice(context2);
        int zzqm = zzyx.zzqm();
        Location location = zzyx.getLocation();
        Bundle networkExtrasBundle = zzyx2.getNetworkExtrasBundle(AdMobAdapter.class);
        boolean manualImpressionsEnabled = zzyx.getManualImpressionsEnabled();
        String publisherProvidedId = zzyx.getPublisherProvidedId();
        SearchAdRequest zzqj = zzyx.zzqj();
        zzzs zzzs = zzqj != null ? new zzzs(zzqj) : null;
        context2 = context.getApplicationContext();
        if (context2 != null) {
            String packageName = context2.getPackageName();
            zzwu.zzpv();
            zza = zzbat.zza(Thread.currentThread().getStackTrace(), packageName);
        } else {
            zza = null;
        }
        return new zzwb(8, time, networkExtrasBundle, gender, list, isTestDevice, zzqm, manualImpressionsEnabled, publisherProvidedId, zzzs, location, contentUrl, zzyx.zzql(), zzyx.getCustomTargeting(), Collections.unmodifiableList(new ArrayList(zzyx.zzqn())), zzyx.zzqi(), zza, zzyx.isDesignedForFamilies(), null, zzyx.zzqo(), zzyx.zzqp());
    }
}
