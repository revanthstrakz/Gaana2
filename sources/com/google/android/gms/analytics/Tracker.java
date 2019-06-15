package com.google.android.gms.analytics;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.measurement.zzau;
import com.google.android.gms.internal.measurement.zzaw;
import com.google.android.gms.internal.measurement.zzcn;
import com.google.android.gms.internal.measurement.zzdf;
import com.google.android.gms.internal.measurement.zzdg;
import com.integralads.avid.library.inmobi.utils.AvidJSONUtil;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

@VisibleForTesting
public class Tracker extends zzau {
    private boolean zzsx;
    private final Map<String, String> zzsy = new HashMap();
    private final Map<String, String> zzsz = new HashMap();
    private final zzcn zzta;
    private final zza zztb;
    private ExceptionReporter zztc;
    private zzdf zztd;

    class zza extends zzau implements zza {
        private boolean zztm;
        private int zztn;
        private long zzto = -1;
        private boolean zztp;
        private long zztq;

        protected zza(zzaw zzaw) {
            super(zzaw);
        }

        /* Access modifiers changed, original: protected|final */
        public final void zzag() {
        }

        public final void setSessionTimeout(long j) {
            this.zzto = j;
            zzai();
        }

        public final void enableAutoActivityTracking(boolean z) {
            this.zztm = z;
            zzai();
        }

        public final synchronized boolean zzah() {
            boolean z;
            z = this.zztp;
            this.zztp = false;
            return z;
        }

        private final void zzai() {
            if (this.zzto >= 0 || this.zztm) {
                zzcb().zza(Tracker.this.zztb);
            } else {
                zzcb().zzb(Tracker.this.zztb);
            }
        }

        public final void zzc(Activity activity) {
            if (this.zztn == 0) {
                if (zzbx().elapsedRealtime() >= this.zztq + Math.max(1000, this.zzto)) {
                    this.zztp = true;
                }
            }
            this.zztn++;
            if (this.zztm) {
                String canonicalName;
                Intent intent = activity.getIntent();
                if (intent != null) {
                    Tracker.this.setCampaignParamsOnNextHit(intent.getData());
                }
                HashMap hashMap = new HashMap();
                hashMap.put("&t", "screenview");
                Tracker tracker = Tracker.this;
                String str = "&cd";
                if (Tracker.this.zztd != null) {
                    zzdf zzk = Tracker.this.zztd;
                    canonicalName = activity.getClass().getCanonicalName();
                    String str2 = (String) zzk.zzaco.get(canonicalName);
                    if (str2 != null) {
                        canonicalName = str2;
                    }
                } else {
                    canonicalName = activity.getClass().getCanonicalName();
                }
                tracker.set(str, canonicalName);
                if (TextUtils.isEmpty((CharSequence) hashMap.get("&dr"))) {
                    Preconditions.checkNotNull(activity);
                    Intent intent2 = activity.getIntent();
                    CharSequence charSequence = null;
                    if (intent2 != null) {
                        String stringExtra = intent2.getStringExtra("android.intent.extra.REFERRER_NAME");
                        if (!TextUtils.isEmpty(stringExtra)) {
                            charSequence = stringExtra;
                        }
                    }
                    if (!TextUtils.isEmpty(charSequence)) {
                        hashMap.put("&dr", charSequence);
                    }
                }
                Tracker.this.send(hashMap);
            }
        }

        public final void zzd(Activity activity) {
            this.zztn--;
            this.zztn = Math.max(0, this.zztn);
            if (this.zztn == 0) {
                this.zztq = zzbx().elapsedRealtime();
            }
        }
    }

    Tracker(zzaw zzaw, String str, zzcn zzcn) {
        super(zzaw);
        if (str != null) {
            this.zzsy.put("&tid", str);
        }
        this.zzsy.put("useSecure", "1");
        this.zzsy.put("&a", Integer.toString(new Random().nextInt(Integer.MAX_VALUE) + 1));
        this.zzta = new zzcn("tracking", zzbx());
        this.zztb = new zza(zzaw);
    }

    /* Access modifiers changed, original: protected|final */
    public final void zzag() {
        this.zztb.zzq();
        String zzaj = zzce().zzaj();
        if (zzaj != null) {
            set("&an", zzaj);
        }
        zzaj = zzce().zzak();
        if (zzaj != null) {
            set("&av", zzaj);
        }
    }

    /* Access modifiers changed, original: final */
    public final void zza(zzdf zzdf) {
        String str;
        boolean z;
        zzq("Loading Tracker config values");
        this.zztd = zzdf;
        boolean z2 = false;
        if (this.zztd.zzaci != null) {
            str = this.zztd.zzaci;
            set("&tid", str);
            zza("trackingId loaded", str);
        }
        if (this.zztd.zzacj >= 0.0d) {
            str = Double.toString(this.zztd.zzacj);
            set("&sf", str);
            zza("Sample frequency loaded", str);
        }
        if (this.zztd.zzack >= 0) {
            int i = this.zztd.zzack;
            setSessionTimeout((long) i);
            zza("Session timeout loaded", Integer.valueOf(i));
        }
        if (this.zztd.zzacl != -1) {
            z = this.zztd.zzacl == 1;
            enableAutoActivityTracking(z);
            zza("Auto activity tracking loaded", Boolean.valueOf(z));
        }
        if (this.zztd.zzacm != -1) {
            z = this.zztd.zzacm == 1;
            if (z) {
                set("&aip", "1");
            }
            zza("Anonymize ip loaded", Boolean.valueOf(z));
        }
        if (this.zztd.zzacn == 1) {
            z2 = true;
        }
        enableExceptionReporting(z2);
    }

    /* JADX WARNING: Missing block: B:13:0x0037, code skipped:
            return;
     */
    public void enableExceptionReporting(boolean r3) {
        /*
        r2 = this;
        monitor-enter(r2);
        r0 = r2.zztc;	 Catch:{ all -> 0x0038 }
        if (r0 == 0) goto L_0x0007;
    L_0x0005:
        r0 = 1;
        goto L_0x0008;
    L_0x0007:
        r0 = 0;
    L_0x0008:
        if (r0 != r3) goto L_0x000c;
    L_0x000a:
        monitor-exit(r2);	 Catch:{ all -> 0x0038 }
        return;
    L_0x000c:
        if (r3 == 0) goto L_0x0028;
    L_0x000e:
        r3 = r2.getContext();	 Catch:{ all -> 0x0038 }
        r0 = java.lang.Thread.getDefaultUncaughtExceptionHandler();	 Catch:{ all -> 0x0038 }
        r1 = new com.google.android.gms.analytics.ExceptionReporter;	 Catch:{ all -> 0x0038 }
        r1.<init>(r2, r0, r3);	 Catch:{ all -> 0x0038 }
        r2.zztc = r1;	 Catch:{ all -> 0x0038 }
        r3 = r2.zztc;	 Catch:{ all -> 0x0038 }
        java.lang.Thread.setDefaultUncaughtExceptionHandler(r3);	 Catch:{ all -> 0x0038 }
        r3 = "Uncaught exceptions will be reported to Google Analytics";
        r2.zzq(r3);	 Catch:{ all -> 0x0038 }
        goto L_0x0036;
    L_0x0028:
        r3 = r2.zztc;	 Catch:{ all -> 0x0038 }
        r3 = r3.zzp();	 Catch:{ all -> 0x0038 }
        java.lang.Thread.setDefaultUncaughtExceptionHandler(r3);	 Catch:{ all -> 0x0038 }
        r3 = "Uncaught exceptions will not be reported to Google Analytics";
        r2.zzq(r3);	 Catch:{ all -> 0x0038 }
    L_0x0036:
        monitor-exit(r2);	 Catch:{ all -> 0x0038 }
        return;
    L_0x0038:
        r3 = move-exception;
        monitor-exit(r2);	 Catch:{ all -> 0x0038 }
        throw r3;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.analytics.Tracker.enableExceptionReporting(boolean):void");
    }

    public void setSessionTimeout(long j) {
        this.zztb.setSessionTimeout(j * 1000);
    }

    public void enableAutoActivityTracking(boolean z) {
        this.zztb.enableAutoActivityTracking(z);
    }

    private static String zza(Entry<String, String> entry) {
        String str = (String) entry.getKey();
        int i = (!str.startsWith("&") || str.length() < 2) ? 0 : 1;
        if (i == 0) {
            return null;
        }
        return ((String) entry.getKey()).substring(1);
    }

    private static void zza(Map<String, String> map, Map<String, String> map2) {
        Preconditions.checkNotNull(map2);
        if (map != null) {
            for (Entry entry : map.entrySet()) {
                String zza = zza(entry);
                if (zza != null) {
                    map2.put(zza, (String) entry.getValue());
                }
            }
        }
    }

    public void send(Map<String, String> map) {
        long currentTimeMillis = zzbx().currentTimeMillis();
        if (zzcb().getAppOptOut()) {
            zzr("AppOptOut is set to true. Not sending Google Analytics hit");
            return;
        }
        boolean isDryRunEnabled = zzcb().isDryRunEnabled();
        Map hashMap = new HashMap();
        zza(this.zzsy, hashMap);
        zza(map, hashMap);
        boolean zzb = zzdg.zzb((String) this.zzsy.get("useSecure"), true);
        Map map2 = this.zzsz;
        Preconditions.checkNotNull(hashMap);
        if (map2 != null) {
            for (Entry entry : map2.entrySet()) {
                String zza = zza(entry);
                if (!(zza == null || hashMap.containsKey(zza))) {
                    hashMap.put(zza, (String) entry.getValue());
                }
            }
        }
        this.zzsz.clear();
        String str = (String) hashMap.get("t");
        if (TextUtils.isEmpty(str)) {
            zzby().zza(hashMap, "Missing hit type parameter");
            return;
        }
        String str2 = (String) hashMap.get("tid");
        if (TextUtils.isEmpty(str2)) {
            zzby().zza(hashMap, "Missing tracking id parameter");
            return;
        }
        boolean z = this.zzsx;
        synchronized (this) {
            if ("screenview".equalsIgnoreCase(str) || "pageview".equalsIgnoreCase(str) || "appview".equalsIgnoreCase(str) || TextUtils.isEmpty(str)) {
                int parseInt = Integer.parseInt((String) this.zzsy.get("&a")) + 1;
                if (parseInt >= Integer.MAX_VALUE) {
                    parseInt = 1;
                }
                this.zzsy.put("&a", Integer.toString(parseInt));
            }
        }
        zzca().zza(new zzp(this, hashMap, z, str, currentTimeMillis, isDryRunEnabled, zzb, str2));
    }

    public String get(String str) {
        zzcl();
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (this.zzsy.containsKey(str)) {
            return (String) this.zzsy.get(str);
        }
        if (str.equals("&ul")) {
            return zzdg.zza(Locale.getDefault());
        }
        if (str.equals("&cid")) {
            return zzcg().zzdr();
        }
        if (str.equals("&sr")) {
            return zzcj().zzel();
        }
        if (str.equals("&aid")) {
            return zzci().zzdf().zzal();
        }
        if (str.equals("&an")) {
            return zzci().zzdf().zzaj();
        }
        if (str.equals("&av")) {
            return zzci().zzdf().zzak();
        }
        if (str.equals("&aiid")) {
            return zzci().zzdf().zzam();
        }
        return null;
    }

    public void set(String str, String str2) {
        Preconditions.checkNotNull(str, "Key should be non-null");
        if (!TextUtils.isEmpty(str)) {
            this.zzsy.put(str, str2);
        }
    }

    public void setSampleRate(double d) {
        set("&sf", Double.toString(d));
    }

    public void setUseSecure(boolean z) {
        set("useSecure", zzdg.zzc(z));
    }

    public void setScreenName(String str) {
        set("&cd", str);
    }

    public void setLocation(String str) {
        set("&dl", str);
    }

    public void setReferrer(String str) {
        set("&dr", str);
    }

    public void setPage(String str) {
        set("&dp", str);
    }

    public void setHostname(String str) {
        set("&dh", str);
    }

    public void setTitle(String str) {
        set("&dt", str);
    }

    public void setLanguage(String str) {
        set("&ul", str);
    }

    public void setEncoding(String str) {
        set("&de", str);
    }

    public void setScreenColors(String str) {
        set("&sd", str);
    }

    public void setScreenResolution(int i, int i2) {
        if (i >= 0 || i2 >= 0) {
            StringBuilder stringBuilder = new StringBuilder(23);
            stringBuilder.append(i);
            stringBuilder.append(AvidJSONUtil.KEY_X);
            stringBuilder.append(i2);
            set("&sr", stringBuilder.toString());
            return;
        }
        zzt("Invalid width or height. The values should be non-negative.");
    }

    public void setViewportSize(String str) {
        set("&vp", str);
    }

    public void setClientId(String str) {
        set("&cid", str);
    }

    public void setAppName(String str) {
        set("&an", str);
    }

    public void setAppId(String str) {
        set("&aid", str);
    }

    public void setAppInstallerId(String str) {
        set("&aiid", str);
    }

    public void setAppVersion(String str) {
        set("&av", str);
    }

    public void setAnonymizeIp(boolean z) {
        set("&aip", zzdg.zzc(z));
    }

    public void setCampaignParamsOnNextHit(Uri uri) {
        if (uri != null && !uri.isOpaque()) {
            String queryParameter = uri.getQueryParameter("referrer");
            if (!TextUtils.isEmpty(queryParameter)) {
                String str = "http://hostname/?";
                queryParameter = String.valueOf(queryParameter);
                uri = Uri.parse(queryParameter.length() != 0 ? str.concat(queryParameter) : new String(str));
                str = uri.getQueryParameter("utm_id");
                if (str != null) {
                    this.zzsz.put("&ci", str);
                }
                str = uri.getQueryParameter("anid");
                if (str != null) {
                    this.zzsz.put("&anid", str);
                }
                str = uri.getQueryParameter("utm_campaign");
                if (str != null) {
                    this.zzsz.put("&cn", str);
                }
                str = uri.getQueryParameter("utm_content");
                if (str != null) {
                    this.zzsz.put("&cc", str);
                }
                str = uri.getQueryParameter("utm_medium");
                if (str != null) {
                    this.zzsz.put("&cm", str);
                }
                str = uri.getQueryParameter("utm_source");
                if (str != null) {
                    this.zzsz.put("&cs", str);
                }
                str = uri.getQueryParameter("utm_term");
                if (str != null) {
                    this.zzsz.put("&ck", str);
                }
                str = uri.getQueryParameter("dclid");
                if (str != null) {
                    this.zzsz.put("&dclid", str);
                }
                str = uri.getQueryParameter("gclid");
                if (str != null) {
                    this.zzsz.put("&gclid", str);
                }
                queryParameter = uri.getQueryParameter("aclid");
                if (queryParameter != null) {
                    this.zzsz.put("&aclid", queryParameter);
                }
            }
        }
    }

    public void enableAdvertisingIdCollection(boolean z) {
        this.zzsx = z;
    }
}
