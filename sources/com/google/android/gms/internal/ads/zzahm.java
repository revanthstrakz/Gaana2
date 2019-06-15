package com.google.android.gms.internal.ads;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcel;
import android.support.annotation.Nullable;
import android.util.Base64;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.common.util.VisibleForTesting;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@zzark
public final class zzahm {
    private final Map<zzahn, zzaho> zzdhe = new HashMap();
    private final LinkedList<zzahn> zzdhf = new LinkedList();
    @Nullable
    private zzagi zzdhg;

    /* Access modifiers changed, original: final */
    public final void zza(zzagi zzagi) {
        if (this.zzdhg == null) {
            this.zzdhg = zzagi.zztg();
            if (this.zzdhg != null) {
                int i = 0;
                SharedPreferences sharedPreferences = this.zzdhg.getApplicationContext().getSharedPreferences("com.google.android.gms.ads.internal.interstitial.InterstitialAdPool", 0);
                while (this.zzdhf.size() > 0) {
                    zzahn zzahn = (zzahn) this.zzdhf.remove();
                    zzaho zzaho = (zzaho) this.zzdhe.get(zzahn);
                    zza("Flushing interstitial queue for %s.", zzahn);
                    while (zzaho.size() > 0) {
                        zzaho.zzl(null).zzdhl.zzke();
                    }
                    this.zzdhe.remove(zzahn);
                }
                try {
                    HashMap hashMap = new HashMap();
                    for (Entry entry : sharedPreferences.getAll().entrySet()) {
                        if (!((String) entry.getKey()).equals("PoolKeys")) {
                            zzahs zzcc = zzahs.zzcc((String) entry.getValue());
                            zzahn zzahn2 = new zzahn(zzcc.zzbqo, zzcc.zzboa, zzcc.zzdhj);
                            if (!this.zzdhe.containsKey(zzahn2)) {
                                this.zzdhe.put(zzahn2, new zzaho(zzcc.zzbqo, zzcc.zzboa, zzcc.zzdhj));
                                hashMap.put(zzahn2.toString(), zzahn2);
                                zza("Restored interstitial queue for %s.", zzahn2);
                            }
                        }
                    }
                    String[] zzbz = zzbz(sharedPreferences.getString("PoolKeys", ""));
                    int length = zzbz.length;
                    while (i < length) {
                        zzahn zzahn3 = (zzahn) hashMap.get(zzbz[i]);
                        if (this.zzdhe.containsKey(zzahn3)) {
                            this.zzdhf.add(zzahn3);
                        }
                        i++;
                    }
                } catch (IOException | RuntimeException e) {
                    zzbv.zzlj().zza(e, "InterstitialAdPool.restore");
                    zzbbd.zzc("Malformed preferences value for InterstitialAdPool.", e);
                    this.zzdhe.clear();
                    this.zzdhf.clear();
                }
            }
        }
    }

    /* Access modifiers changed, original: final */
    @Nullable
    public final zzahp zza(zzwb zzwb, String str) {
        if (zzca(str)) {
            return null;
        }
        int i = new zzaua(this.zzdhg.getApplicationContext()).zzwx().zzedd;
        zzwb = zzj(zzwb);
        str = zzcb(str);
        zzahn zzahn = new zzahn(zzwb, str, i);
        zzaho zzaho = (zzaho) this.zzdhe.get(zzahn);
        if (zzaho == null) {
            zza("Interstitial pool created at %s.", zzahn);
            zzaho = new zzaho(zzwb, str, i);
            this.zzdhe.put(zzahn, zzaho);
        }
        this.zzdhf.remove(zzahn);
        this.zzdhf.add(zzahn);
        zzaho.zztm();
        while (true) {
            if (this.zzdhf.size() <= ((Integer) zzwu.zzpz().zzd(zzaan.zzcsd)).intValue()) {
                break;
            }
            zzahn zzahn2 = (zzahn) this.zzdhf.remove();
            zzaho zzaho2 = (zzaho) this.zzdhe.get(zzahn2);
            zza("Evicting interstitial queue for %s.", zzahn2);
            while (zzaho2.size() > 0) {
                zzahp zzl = zzaho2.zzl(null);
                if (zzl.zzblw) {
                    zzahq.zzto().zztq();
                }
                zzl.zzdhl.zzke();
            }
            this.zzdhe.remove(zzahn2);
        }
        while (zzaho.size() > 0) {
            zzahp zzl2 = zzaho.zzl(zzwb);
            if (zzl2.zzblw) {
                if (zzbv.zzlm().currentTimeMillis() - zzl2.zzdho > 1000 * ((long) ((Integer) zzwu.zzpz().zzd(zzaan.zzcsf)).intValue())) {
                    zza("Expired interstitial at %s.", zzahn);
                    zzahq.zzto().zztp();
                }
            }
            Object obj = zzl2.zzdhm != null ? " (inline) " : " ";
            StringBuilder stringBuilder = new StringBuilder(34 + String.valueOf(obj).length());
            stringBuilder.append("Pooled interstitial");
            stringBuilder.append(obj);
            stringBuilder.append("returned at %s.");
            zza(stringBuilder.toString(), zzahn);
            return zzl2;
        }
        return null;
    }

    /* Access modifiers changed, original: final */
    public final void zzb(zzwb zzwb, String str) {
        if (this.zzdhg != null) {
            int i = new zzaua(this.zzdhg.getApplicationContext()).zzwx().zzedd;
            zzwb zzj = zzj(zzwb);
            str = zzcb(str);
            zzahn zzahn = new zzahn(zzj, str, i);
            zzaho zzaho = (zzaho) this.zzdhe.get(zzahn);
            if (zzaho == null) {
                zza("Interstitial pool created at %s.", zzahn);
                zzaho = new zzaho(zzj, str, i);
                this.zzdhe.put(zzahn, zzaho);
            }
            zzaho.zza(this.zzdhg, zzwb);
            zzaho.zztm();
            zza("Inline entry added to the queue at %s.", zzahn);
        }
    }

    /* Access modifiers changed, original: final */
    /* JADX WARNING: Missing block: B:8:0x002f, code skipped:
            r5 = r1.size();
     */
    public final void zzth() {
        /*
        r9 = this;
        r0 = r9.zzdhg;
        if (r0 != 0) goto L_0x0005;
    L_0x0004:
        return;
    L_0x0005:
        r0 = r9.zzdhe;
        r0 = r0.entrySet();
        r0 = r0.iterator();
    L_0x000f:
        r1 = r0.hasNext();
        r2 = 0;
        if (r1 == 0) goto L_0x0089;
    L_0x0016:
        r1 = r0.next();
        r1 = (java.util.Map.Entry) r1;
        r3 = r1.getKey();
        r3 = (com.google.android.gms.internal.ads.zzahn) r3;
        r1 = r1.getValue();
        r1 = (com.google.android.gms.internal.ads.zzaho) r1;
        r4 = 2;
        r5 = com.google.android.gms.internal.ads.zzbbd.isLoggable(r4);
        if (r5 == 0) goto L_0x0056;
    L_0x002f:
        r5 = r1.size();
        r6 = r1.zztk();
        if (r6 >= r5) goto L_0x0056;
    L_0x0039:
        r7 = "Loading %s/%s pooled interstitials for %s.";
        r8 = 3;
        r8 = new java.lang.Object[r8];
        r6 = r5 - r6;
        r6 = java.lang.Integer.valueOf(r6);
        r8[r2] = r6;
        r5 = java.lang.Integer.valueOf(r5);
        r6 = 1;
        r8[r6] = r5;
        r8[r4] = r3;
        r4 = java.lang.String.format(r7, r8);
        com.google.android.gms.internal.ads.zzaxz.v(r4);
    L_0x0056:
        r4 = r1.zztl();
        r2 = r2 + r4;
    L_0x005b:
        r4 = r1.size();
        r5 = com.google.android.gms.internal.ads.zzaan.zzcse;
        r6 = com.google.android.gms.internal.ads.zzwu.zzpz();
        r5 = r6.zzd(r5);
        r5 = (java.lang.Integer) r5;
        r5 = r5.intValue();
        if (r4 >= r5) goto L_0x0081;
    L_0x0071:
        r4 = "Pooling and loading one new interstitial for %s.";
        zza(r4, r3);
        r4 = r9.zzdhg;
        r4 = r1.zzb(r4);
        if (r4 == 0) goto L_0x005b;
    L_0x007e:
        r2 = r2 + 1;
        goto L_0x005b;
    L_0x0081:
        r1 = com.google.android.gms.internal.ads.zzahq.zzto();
        r1.zzcn(r2);
        goto L_0x000f;
    L_0x0089:
        r0 = r9.zzdhg;
        if (r0 == 0) goto L_0x00ea;
    L_0x008d:
        r0 = r9.zzdhg;
        r0 = r0.getApplicationContext();
        r1 = "com.google.android.gms.ads.internal.interstitial.InterstitialAdPool";
        r0 = r0.getSharedPreferences(r1, r2);
        r0 = r0.edit();
        r0.clear();
        r1 = r9.zzdhe;
        r1 = r1.entrySet();
        r1 = r1.iterator();
    L_0x00aa:
        r2 = r1.hasNext();
        if (r2 == 0) goto L_0x00de;
    L_0x00b0:
        r2 = r1.next();
        r2 = (java.util.Map.Entry) r2;
        r3 = r2.getKey();
        r3 = (com.google.android.gms.internal.ads.zzahn) r3;
        r2 = r2.getValue();
        r2 = (com.google.android.gms.internal.ads.zzaho) r2;
        r4 = r2.zztn();
        if (r4 == 0) goto L_0x00aa;
    L_0x00c8:
        r4 = new com.google.android.gms.internal.ads.zzahs;
        r4.<init>(r2);
        r2 = r4.zzty();
        r4 = r3.toString();
        r0.putString(r4, r2);
        r2 = "Saved interstitial queue for %s.";
        zza(r2, r3);
        goto L_0x00aa;
    L_0x00de:
        r1 = "PoolKeys";
        r2 = r9.zzti();
        r0.putString(r1, r2);
        r0.apply();
    L_0x00ea:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzahm.zzth():void");
    }

    private final String zzti() {
        try {
            StringBuilder stringBuilder = new StringBuilder();
            Iterator it = this.zzdhf.iterator();
            while (it.hasNext()) {
                stringBuilder.append(Base64.encodeToString(((zzahn) it.next()).toString().getBytes("UTF-8"), 0));
                if (it.hasNext()) {
                    stringBuilder.append("\u0000");
                }
            }
            return stringBuilder.toString();
        } catch (UnsupportedEncodingException unused) {
            return "";
        }
    }

    private static String[] zzbz(String str) {
        try {
            String[] split = str.split("\u0000");
            for (int i = 0; i < split.length; i++) {
                split[i] = new String(Base64.decode(split[i], 0), "UTF-8");
            }
            return split;
        } catch (UnsupportedEncodingException unused) {
            return new String[0];
        }
    }

    private static boolean zzca(String str) {
        try {
            return Pattern.matches((String) zzwu.zzpz().zzd(zzaan.zzcsg), str);
        } catch (RuntimeException e) {
            zzbv.zzlj().zza(e, "InterstitialAdPool.isExcludedAdUnit");
            return false;
        }
    }

    static Set<String> zzh(zzwb zzwb) {
        HashSet hashSet = new HashSet();
        hashSet.addAll(zzwb.extras.keySet());
        Bundle bundle = zzwb.zzcjl.getBundle("com.google.ads.mediation.admob.AdMobAdapter");
        if (bundle != null) {
            hashSet.addAll(bundle.keySet());
        }
        return hashSet;
    }

    static zzwb zzi(zzwb zzwb) {
        zzwb = zzk(zzwb);
        String str = "_skipMediation";
        Bundle bundle = zzwb.zzcjl.getBundle("com.google.ads.mediation.admob.AdMobAdapter");
        if (bundle != null) {
            bundle.putBoolean(str, true);
        }
        zzwb.extras.putBoolean(str, true);
        return zzwb;
    }

    @VisibleForTesting
    private static zzwb zzj(zzwb zzwb) {
        zzwb = zzk(zzwb);
        for (String str : ((String) zzwu.zzpz().zzd(zzaan.zzcsc)).split(",")) {
            zzb(zzwb.zzcjl, str);
            String str2 = "com.google.ads.mediation.admob.AdMobAdapter/";
            if (str.startsWith(str2)) {
                zzb(zzwb.extras, str.replaceFirst(str2, ""));
            }
        }
        return zzwb;
    }

    @VisibleForTesting
    private static String zzcb(String str) {
        try {
            Matcher matcher = Pattern.compile("([^/]+/[0-9]+).*").matcher(str);
            if (matcher.matches()) {
                return matcher.group(1);
            }
        } catch (RuntimeException unused) {
        }
        return str;
    }

    @VisibleForTesting
    private static zzwb zzk(zzwb zzwb) {
        Parcel obtain = Parcel.obtain();
        zzwb.writeToParcel(obtain, 0);
        obtain.setDataPosition(0);
        zzwb = (zzwb) zzwb.CREATOR.createFromParcel(obtain);
        obtain.recycle();
        return zzwb.zzpm();
    }

    private static void zzb(Bundle bundle, String str) {
        while (true) {
            String[] split = str.split("/", 2);
            if (split.length != 0) {
                String str2 = split[0];
                if (split.length == 1) {
                    bundle.remove(str2);
                    return;
                }
                bundle = bundle.getBundle(str2);
                if (bundle != null) {
                    str = split[1];
                } else {
                    return;
                }
            }
            return;
        }
    }

    private static void zza(String str, zzahn zzahn) {
        if (zzbbd.isLoggable(2)) {
            zzaxz.v(String.format(str, new Object[]{zzahn}));
        }
    }
}
