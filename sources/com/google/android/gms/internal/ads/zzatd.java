package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import com.google.android.gms.ads.internal.gmsg.HttpClient;
import com.google.android.gms.ads.internal.gmsg.zzu;
import com.google.android.gms.ads.internal.gmsg.zzz;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.util.VisibleForTesting;
import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.json.JSONException;
import org.json.JSONObject;

@zzark
public final class zzatd extends zzaxv {
    private static final Object sLock = new Object();
    @VisibleForTesting
    private static final long zzdzr = TimeUnit.SECONDS.toMillis(10);
    @VisibleForTesting
    private static boolean zzdzs;
    private static zzait zzdzt;
    private static HttpClient zzdzu;
    private static zzz zzdzv;
    private static zzu<Object> zzdzw;
    private final Context mContext;
    private final Object zzdsn = new Object();
    private final zzarm zzdvp;
    private final zzasj zzdvq;
    private zzur zzdvs;
    private zzaji zzdzx;

    public zzatd(Context context, zzasj zzasj, zzarm zzarm, zzur zzur) {
        super(true);
        this.zzdvp = zzarm;
        this.mContext = context;
        this.zzdvq = zzasj;
        this.zzdvs = zzur;
        synchronized (sLock) {
            if (!zzdzs) {
                zzdzv = new zzz();
                zzdzu = new HttpClient(context.getApplicationContext(), zzasj.zzbsp);
                zzdzw = new zzatl();
                zzdzt = new zzait(this.mContext.getApplicationContext(), this.zzdvq.zzbsp, (String) zzwu.zzpz().zzd(zzaan.zzcnw), new zzatk(), new zzatj());
                zzdzs = true;
            }
        }
    }

    protected static void zzc(zzaii zzaii) {
        zzaii.zza("/loadAd", zzdzv);
        zzaii.zza("/fetchHttpRequest", zzdzu);
        zzaii.zza("/invalidRequest", zzdzw);
    }

    protected static void zzd(zzaii zzaii) {
        zzaii.zzb("/loadAd", zzdzv);
        zzaii.zzb("/fetchHttpRequest", zzdzu);
        zzaii.zzb("/invalidRequest", zzdzw);
    }

    public final void zzki() {
        zzbbd.zzdn("SdkLessAdLoaderBackgroundTask started.");
        String zzz = zzbv.zzmf().zzz(this.mContext);
        zzasi zzasi = new zzasi(this.zzdvq, -1, zzbv.zzmf().zzx(this.mContext), zzbv.zzmf().zzy(this.mContext), zzz, zzbv.zzmf().zzaa(this.mContext));
        zzasm zzc = zzc(zzasi);
        if ((zzc.errorCode == -2 || zzc.errorCode == 3) && !TextUtils.isEmpty(zzz)) {
            zzbv.zzmf().zzh(this.mContext, zzz);
        }
        long elapsedRealtime = zzbv.zzlm().elapsedRealtime();
        zzbat.zztu.post(new zzate(this, new zzaxg(zzasi, zzc, null, null, zzc.errorCode, elapsedRealtime, zzc.zzdyh, null, this.zzdvs)));
    }

    private final zzasm zzc(zzasi zzasi) {
        zzbv.zzlf();
        String zzzs = zzayh.zzzs();
        JSONObject zza = zza(zzasi, zzzs);
        if (zza == null) {
            return new zzasm(0);
        }
        long elapsedRealtime = zzbv.zzlm().elapsedRealtime();
        Future zzbu = zzdzv.zzbu(zzzs);
        zzbat.zztu.post(new zzatf(this, zza, zzzs));
        try {
            zza = (JSONObject) zzbu.get(zzdzr - (zzbv.zzlm().elapsedRealtime() - elapsedRealtime), TimeUnit.MILLISECONDS);
            if (zza == null) {
                return new zzasm(-1);
            }
            zzasm zza2 = zzatv.zza(this.mContext, zzasi, zza.toString());
            return (zza2.errorCode == -3 || !TextUtils.isEmpty(zza2.zzdyb)) ? zza2 : new zzasm(3);
        } catch (InterruptedException | CancellationException unused) {
            return new zzasm(-1);
        } catch (TimeoutException unused2) {
            return new zzasm(2);
        } catch (ExecutionException unused3) {
            return new zzasm(0);
        }
    }

    private final JSONObject zza(zzasi zzasi, String str) {
        Bundle bundle = zzasi.zzdwg.extras.getBundle("sdk_less_server_data");
        if (bundle == null) {
            return null;
        }
        zzatz zzatz;
        try {
            zzatz = (zzatz) zzbv.zzlq().zzt(this.mContext).get();
        } catch (Exception e) {
            zzbbd.zzc("Error grabbing device info: ", e);
            zzatz = null;
        }
        Context context = this.mContext;
        zzato zzato = new zzato();
        zzato.zzeag = zzasi;
        zzato.zzeah = zzatz;
        JSONObject zza = zzatv.zza(context, zzato);
        if (zza == null) {
            return null;
        }
        Info advertisingIdInfo;
        try {
            advertisingIdInfo = AdvertisingIdClient.getAdvertisingIdInfo(this.mContext);
        } catch (GooglePlayServicesNotAvailableException | GooglePlayServicesRepairableException | IOException | IllegalStateException e2) {
            zzbbd.zzc("Cannot get advertising id info", e2);
            advertisingIdInfo = null;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("request_id", str);
        hashMap.put("request_param", zza);
        hashMap.put("data", bundle);
        if (advertisingIdInfo != null) {
            hashMap.put("adid", advertisingIdInfo.getId());
            hashMap.put(LocationConstants.PARAM_LAT, Integer.valueOf(advertisingIdInfo.isLimitAdTrackingEnabled()));
        }
        try {
            return zzbv.zzlf().zzn(hashMap);
        } catch (JSONException unused) {
            return null;
        }
    }

    public final void onStop() {
        synchronized (this.zzdsn) {
            zzbat.zztu.post(new zzati(this));
        }
    }
}
