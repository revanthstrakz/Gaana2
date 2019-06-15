package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import android.os.SystemClock;
import android.text.TextUtils;
import com.comscore.streaming.Constants;
import com.google.ads.mediation.AdUrlAdapter;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.formats.NativeAdOptions;
import com.google.android.gms.ads.formats.NativeAdOptions.Builder;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.ads.mediation.MediationAdapter;
import com.google.android.gms.common.util.GmsVersion;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

@zzark
public final class zzaku implements zzaky {
    private final Context mContext;
    private final Object mLock = new Object();
    private final zzalg zzbma;
    private final zzacp zzbnx;
    private final zzbbi zzbob;
    private zzwb zzbqo;
    private final zzwf zzbqu;
    private final boolean zzbum;
    private final String zzdml;
    private final long zzdmm;
    private final zzakr zzdmn;
    private final zzakq zzdmo;
    private final List<String> zzdmp;
    private final List<String> zzdmq;
    private final List<String> zzdmr;
    private final boolean zzdms;
    private final boolean zzdmt;
    private zzalj zzdmu;
    private int zzdmv = -2;
    private zzalp zzdmw;

    public zzaku(Context context, String str, zzalg zzalg, zzakr zzakr, zzakq zzakq, zzwb zzwb, zzwf zzwf, zzbbi zzbbi, boolean z, boolean z2, zzacp zzacp, List<String> list, List<String> list2, List<String> list3, boolean z3) {
        String str2 = str;
        zzakr zzakr2 = zzakr;
        zzakq zzakq2 = zzakq;
        this.mContext = context;
        this.zzbma = zzalg;
        this.zzdmo = zzakq2;
        if ("com.google.ads.mediation.customevent.CustomEventAdapter".equals(str2)) {
            this.zzdml = zzum();
        } else {
            this.zzdml = str2;
        }
        this.zzdmn = zzakr2;
        if (zzakq2.zzdlo != -1) {
            this.zzdmm = zzakq2.zzdlo;
        } else if (zzakr2.zzdlo != -1) {
            this.zzdmm = zzakr2.zzdlo;
        } else {
            this.zzdmm = Constants.HEARTBEAT_STAGE_ONE_INTERVAL;
        }
        this.zzbqo = zzwb;
        this.zzbqu = zzwf;
        this.zzbob = zzbbi;
        this.zzbum = z;
        this.zzdms = z2;
        this.zzbnx = zzacp;
        this.zzdmp = list;
        this.zzdmq = list2;
        this.zzdmr = list3;
        this.zzdmt = z3;
    }

    public final void cancel() {
        synchronized (this.mLock) {
            try {
                if (this.zzdmu != null) {
                    this.zzdmu.destroy();
                }
            } catch (RemoteException e) {
                zzbbd.zzc("Could not destroy mediation adapter.", e);
            }
            this.zzdmv = -1;
            this.mLock.notify();
        }
    }

    private final String zzum() {
        try {
            if (!TextUtils.isEmpty(this.zzdmo.zzdky)) {
                return this.zzbma.zzcq(this.zzdmo.zzdky) ? "com.google.android.gms.ads.mediation.customevent.CustomEventAdapter" : "com.google.ads.mediation.customevent.CustomEventAdapter";
            }
        } catch (RemoteException unused) {
            zzbbd.zzeo("Fail to determine the custom event's version, assuming the old one.");
        }
        return "com.google.ads.mediation.customevent.CustomEventAdapter";
    }

    public final void zzco(int i) {
        synchronized (this.mLock) {
            this.zzdmv = i;
            this.mLock.notify();
        }
    }

    public final void zza(int i, zzalp zzalp) {
        synchronized (this.mLock) {
            this.zzdmv = 0;
            this.zzdmw = zzalp;
            this.mLock.notify();
        }
    }

    @VisibleForTesting
    private static zzalj zza(MediationAdapter mediationAdapter) {
        return new zzamd(mediationAdapter);
    }

    public final zzakx zzj(long j, long j2) {
        zzakx zzakx;
        synchronized (this.mLock) {
            try {
                long elapsedRealtime = SystemClock.elapsedRealtime();
                zzakt zzakt = new zzakt();
                zzayh.zzelc.post(new zzakv(this, zzakt));
                long j3 = this.zzdmm;
                while (this.zzdmv == -2) {
                    long elapsedRealtime2 = SystemClock.elapsedRealtime();
                    long j4 = j3 - (elapsedRealtime2 - elapsedRealtime);
                    long j5 = elapsedRealtime;
                    elapsedRealtime = j2 - (elapsedRealtime2 - j);
                    if (j4 <= 0 || elapsedRealtime <= 0) {
                        zzbbd.zzen("Timed out waiting for adapter.");
                        this.zzdmv = 3;
                    } else {
                        this.mLock.wait(Math.min(j4, elapsedRealtime));
                    }
                    elapsedRealtime = j5;
                }
                zzakx = new zzakx(this.zzdmo, this.zzdmu, this.zzdml, zzakt, this.zzdmv, zzun(), zzbv.zzlm().elapsedRealtime() - elapsedRealtime);
            } catch (InterruptedException unused) {
                this.zzdmv = 5;
            } catch (Throwable th) {
                Throwable th2 = th;
            }
        }
        return zzakx;
    }

    private final zzalp zzun() {
        if (this.zzdmv != 0 || !zzup()) {
            return null;
        }
        try {
            if (!(!zzcp(4) || this.zzdmw == null || this.zzdmw.zzur() == 0)) {
                return this.zzdmw;
            }
        } catch (RemoteException unused) {
            zzbbd.zzeo("Could not get cpm value from MediationResponseMetadata");
        }
        return new zzakw(zzuq());
    }

    private final zzalj zzuo() {
        String str = "Instantiating mediation adapter: ";
        String valueOf = String.valueOf(this.zzdml);
        zzbbd.zzen(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
        if (!(this.zzbum || this.zzdmo.zzuk())) {
            if ("com.google.ads.mediation.admob.AdMobAdapter".equals(this.zzdml)) {
                return zza(new AdMobAdapter());
            }
            if ("com.google.ads.mediation.AdUrlAdapter".equals(this.zzdml)) {
                return zza(new AdUrlAdapter());
            }
            if ("com.google.ads.mediation.admob.AdMobCustomTabsAdapter".equals(this.zzdml)) {
                return new zzamd(new zzanu());
            }
        }
        try {
            return this.zzbma.zzcp(this.zzdml);
        } catch (RemoteException e) {
            valueOf = "Could not instantiate mediation adapter: ";
            String valueOf2 = String.valueOf(this.zzdml);
            zzbbd.zza(valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf), e);
            return null;
        }
    }

    private final void zza(zzakt zzakt) {
        String zzcm = zzcm(this.zzdmo.zzdle);
        try {
            if (this.zzbob.zzeov >= GmsVersion.VERSION_HALLOUMI) {
                if (!this.zzbum) {
                    if (!this.zzdmo.zzuk()) {
                        if (this.zzbqu.zzckl) {
                            this.zzdmu.zza(ObjectWrapper.wrap(this.mContext), this.zzbqo, zzcm, this.zzdmo.zzdku, (zzalm) zzakt);
                            return;
                        } else if (!this.zzdms) {
                            this.zzdmu.zza(ObjectWrapper.wrap(this.mContext), this.zzbqu, this.zzbqo, zzcm, this.zzdmo.zzdku, zzakt);
                            return;
                        } else if (this.zzdmo.zzdli != null) {
                            this.zzdmu.zza(ObjectWrapper.wrap(this.mContext), this.zzbqo, zzcm, this.zzdmo.zzdku, zzakt, new zzacp(zzcn(this.zzdmo.zzdlm)), this.zzdmo.zzdll);
                            return;
                        } else {
                            this.zzdmu.zza(ObjectWrapper.wrap(this.mContext), this.zzbqu, this.zzbqo, zzcm, this.zzdmo.zzdku, zzakt);
                            return;
                        }
                    }
                }
                ArrayList arrayList = new ArrayList(this.zzdmp);
                if (this.zzdmq != null) {
                    for (String str : this.zzdmq) {
                        Object obj = ":false";
                        if (this.zzdmr != null && this.zzdmr.contains(str)) {
                            obj = ":true";
                        }
                        StringBuilder stringBuilder = new StringBuilder((7 + String.valueOf(str).length()) + String.valueOf(obj).length());
                        stringBuilder.append("custom:");
                        stringBuilder.append(str);
                        stringBuilder.append(obj);
                        arrayList.add(stringBuilder.toString());
                    }
                }
                this.zzdmu.zza(ObjectWrapper.wrap(this.mContext), this.zzbqo, zzcm, this.zzdmo.zzdku, zzakt, this.zzbnx, arrayList);
            } else if (this.zzbqu.zzckl) {
                this.zzdmu.zza(ObjectWrapper.wrap(this.mContext), this.zzbqo, zzcm, zzakt);
            } else {
                this.zzdmu.zza(ObjectWrapper.wrap(this.mContext), this.zzbqu, this.zzbqo, zzcm, (zzalm) zzakt);
            }
        } catch (RemoteException e) {
            zzbbd.zzc("Could not request ad from mediation adapter.", e);
            zzco(5);
        }
    }

    private final boolean zzcp(int i) {
        try {
            Bundle zzux;
            if (this.zzbum) {
                zzux = this.zzdmu.zzux();
            } else if (this.zzbqu.zzckl) {
                zzux = this.zzdmu.getInterstitialAdapterInfo();
            } else {
                zzux = this.zzdmu.zzuw();
            }
            if (zzux == null || (zzux.getInt("capabilities", 0) & i) != i) {
                return false;
            }
            return true;
        } catch (RemoteException unused) {
            zzbbd.zzeo("Could not get adapter info. Returning false");
            return false;
        }
    }

    private final boolean zzup() {
        return this.zzdmn.zzdma != -1;
    }

    private final String zzcm(String str) {
        if (str == null || !zzup() || zzcp(2)) {
            return str;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            jSONObject.remove("cpm_floor_cents");
            return jSONObject.toString();
        } catch (JSONException unused) {
            zzbbd.zzeo("Could not remove field. Returning the original value");
            return str;
        }
    }

    private final int zzuq() {
        if (this.zzdmo.zzdle == null) {
            return 0;
        }
        try {
            JSONObject jSONObject = new JSONObject(this.zzdmo.zzdle);
            if ("com.google.ads.mediation.admob.AdMobAdapter".equals(this.zzdml)) {
                return jSONObject.optInt("cpm_cents", 0);
            }
            int optInt = zzcp(2) ? jSONObject.optInt("cpm_floor_cents", 0) : 0;
            if (optInt == 0) {
                optInt = jSONObject.optInt("penalized_average_cpm_cents", 0);
            }
            return optInt;
        } catch (JSONException unused) {
            zzbbd.zzeo("Could not convert to json. Returning 0");
            return 0;
        }
    }

    private static NativeAdOptions zzcn(String str) {
        Builder builder = new Builder();
        if (str == null) {
            return builder.build();
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            int i = 0;
            builder.setRequestMultipleImages(jSONObject.optBoolean("multiple_images", false));
            builder.setReturnUrlsForImageAssets(jSONObject.optBoolean("only_urls", false));
            str = jSONObject.optString("native_image_orientation", "any");
            if ("landscape".equals(str)) {
                i = 2;
            } else if ("portrait".equals(str)) {
                i = 1;
            } else if (!"any".equals(str)) {
                i = -1;
            }
            builder.setImageOrientation(i);
        } catch (JSONException e) {
            zzbbd.zzc("Exception occurred when creating native ad options", e);
        }
        return builder.build();
    }
}
