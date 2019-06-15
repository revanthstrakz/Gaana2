package com.google.android.gms.internal.ads;

import android.net.Uri;
import android.net.Uri.Builder;
import android.text.TextUtils;
import com.facebook.internal.AnalyticsEvents;
import com.google.android.gms.ads.internal.zzbv;
import com.til.colombia.android.internal.h;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

@zzark
public final class zzatw {
    private int mOrientation = -1;
    private zzaso zzbmy;
    private String zzday;
    private boolean zzdms = false;
    private final zzasi zzdnh;
    private List<String> zzdth;
    private String zzdtk;
    private String zzebb;
    private String zzebc;
    private List<String> zzebd;
    private String zzebe;
    private String zzebf;
    private String zzebg;
    private List<String> zzebh;
    private List<String> zzebi;
    private long zzebj = -1;
    private boolean zzebk = false;
    private final long zzebl = -1;
    private long zzebm = -1;
    private boolean zzebn = false;
    private boolean zzebo = false;
    private boolean zzebp = false;
    private boolean zzebq = true;
    private boolean zzebr = true;
    private String zzebs = "";
    private boolean zzebt = false;
    private zzawd zzebu;
    private List<String> zzebv;
    private List<String> zzebw;
    private boolean zzebx = false;
    private boolean zzeby = false;
    private String zzebz;
    private List<String> zzeca;
    private boolean zzecb;
    private String zzecc;
    private zzawo zzecd;
    private boolean zzece;
    private boolean zzecf;
    private boolean zzecg;
    private boolean zzech;

    private static String zza(Map<String, List<String>> map, String str) {
        List list = (List) map.get(str);
        return (list == null || list.isEmpty()) ? null : (String) list.get(0);
    }

    private static long zzb(Map<String, List<String>> map, String str) {
        List list = (List) map.get(str);
        if (!(list == null || list.isEmpty())) {
            String str2 = (String) list.get(0);
            try {
                return (long) (Float.parseFloat(str2) * 1000.0f);
            } catch (NumberFormatException unused) {
                StringBuilder stringBuilder = new StringBuilder((36 + String.valueOf(str).length()) + String.valueOf(str2).length());
                stringBuilder.append("Could not parse float from ");
                stringBuilder.append(str);
                stringBuilder.append(" header: ");
                stringBuilder.append(str2);
                zzbbd.zzeo(stringBuilder.toString());
            }
        }
        return -1;
    }

    private static List<String> zzc(Map<String, List<String>> map, String str) {
        List list = (List) map.get(str);
        if (!(list == null || list.isEmpty())) {
            String str2 = (String) list.get(0);
            if (str2 != null) {
                return Arrays.asList(str2.trim().split("\\s+"));
            }
        }
        return null;
    }

    private static boolean zzd(Map<String, List<String>> map, String str) {
        List list = (List) map.get(str);
        if (list == null || list.isEmpty()) {
            return false;
        }
        return Boolean.valueOf((String) list.get(0)).booleanValue();
    }

    public zzatw(zzasi zzasi, String str) {
        this.zzebc = str;
        this.zzdnh = zzasi;
    }

    public final zzasm zza(long j, zzaty zzaty) {
        zzasi zzasi = this.zzdnh;
        String str = this.zzebc;
        String str2 = this.zzday;
        List list = this.zzebd;
        List list2 = this.zzebh;
        long j2 = this.zzebj;
        boolean z = this.zzebk;
        List list3 = this.zzdth;
        long j3 = this.zzebm;
        int i = this.mOrientation;
        String str3 = this.zzebb;
        return new zzasm(zzasi, str, str2, list, list2, j2, z, -1, list3, j3, i, str3, j, this.zzebf, this.zzebg, this.zzebn, this.zzebo, this.zzebp, this.zzebq, false, this.zzebs, this.zzebt, this.zzdms, this.zzebu, this.zzebv, this.zzebw, this.zzebx, this.zzbmy, this.zzeby, this.zzebz, this.zzeca, this.zzecb, this.zzecc, this.zzecd, this.zzebe, this.zzebr, this.zzece, this.zzecf, zzaty.zzwu() ? 2 : 1, this.zzecg, this.zzebi, this.zzech, this.zzdtk, zzaty.zzwv(), zzaty.zzww());
    }

    public final void zza(String str, Map<String, List<String>> map, String str2) {
        this.zzday = str2;
        zzl(map);
    }

    public final void zzl(Map<String, List<String>> map) {
        String str;
        this.zzebb = zza((Map) map, "X-Afma-Ad-Size");
        this.zzecc = zza((Map) map, "X-Afma-Ad-Slot-Size");
        List zzc = zzc(map, "X-Afma-Click-Tracking-Urls");
        if (zzc != null) {
            this.zzebd = zzc;
        }
        this.zzebe = zza((Map) map, "X-Afma-Debug-Signals");
        zzc = (List) map.get("X-Afma-Debug-Dialog");
        if (!(zzc == null || zzc.isEmpty())) {
            this.zzebf = (String) zzc.get(0);
        }
        zzc = zzc(map, "X-Afma-Tracking-Urls");
        if (zzc != null) {
            this.zzebh = zzc;
        }
        zzc = zzc(map, "X-Afma-Downloaded-Impression-Urls");
        if (zzc != null) {
            this.zzebi = zzc;
        }
        long zzb = zzb(map, "X-Afma-Interstitial-Timeout");
        if (zzb != -1) {
            this.zzebj = zzb;
        }
        this.zzebk |= zzd(map, "X-Afma-Mediation");
        zzc = zzc(map, "X-Afma-Manual-Tracking-Urls");
        if (zzc != null) {
            this.zzdth = zzc;
        }
        zzb = zzb(map, "X-Afma-Refresh-Rate");
        if (zzb != -1) {
            this.zzebm = zzb;
        }
        zzc = (List) map.get("X-Afma-Orientation");
        if (!(zzc == null || zzc.isEmpty())) {
            str = (String) zzc.get(0);
            if ("portrait".equalsIgnoreCase(str)) {
                this.mOrientation = zzbv.zzlh().zzzx();
            } else if ("landscape".equalsIgnoreCase(str)) {
                this.mOrientation = zzbv.zzlh().zzzw();
            }
        }
        this.zzebg = zza((Map) map, "X-Afma-ActiveView");
        zzc = (List) map.get("X-Afma-Use-HTTPS");
        if (!(zzc == null || zzc.isEmpty())) {
            this.zzebp = Boolean.valueOf((String) zzc.get(0)).booleanValue();
        }
        this.zzebn |= zzd(map, "X-Afma-Custom-Rendering-Allowed");
        this.zzebo = AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_NATIVE.equals(zza((Map) map, "X-Afma-Ad-Format"));
        zzc = (List) map.get("X-Afma-Content-Url-Opted-Out");
        if (!(zzc == null || zzc.isEmpty())) {
            this.zzebq = Boolean.valueOf((String) zzc.get(0)).booleanValue();
        }
        zzc = (List) map.get("X-Afma-Content-Vertical-Opted-Out");
        if (!(zzc == null || zzc.isEmpty())) {
            this.zzebr = Boolean.valueOf((String) zzc.get(0)).booleanValue();
        }
        zzc = (List) map.get("X-Afma-Gws-Query-Id");
        if (!(zzc == null || zzc.isEmpty())) {
            this.zzebs = (String) zzc.get(0);
        }
        str = zza((Map) map, "X-Afma-Fluid");
        if (str != null && str.equals("height")) {
            this.zzebt = true;
        }
        this.zzdms = "native_express".equals(zza((Map) map, "X-Afma-Ad-Format"));
        this.zzebu = zzawd.zzdh(zza((Map) map, "X-Afma-Rewards"));
        if (this.zzebv == null) {
            this.zzebv = zzc(map, "X-Afma-Reward-Video-Start-Urls");
        }
        if (this.zzebw == null) {
            this.zzebw = zzc(map, "X-Afma-Reward-Video-Complete-Urls");
        }
        this.zzebx |= zzd(map, "X-Afma-Use-Displayed-Impression");
        this.zzeby |= zzd(map, "X-Afma-Auto-Collect-Location");
        this.zzebz = zza((Map) map, h.i);
        str = zza((Map) map, "X-Afma-Auto-Protection-Configuration");
        if (str == null || TextUtils.isEmpty(str)) {
            Builder buildUpon = Uri.parse("https://pagead2.googlesyndication.com/pagead/gen_204").buildUpon();
            buildUpon.appendQueryParameter("id", "gmob-apps-blocked-navigation");
            if (!TextUtils.isEmpty(this.zzebf)) {
                buildUpon.appendQueryParameter("debugDialog", this.zzebf);
            }
            String[] strArr = new String[1];
            str = buildUpon.toString();
            StringBuilder stringBuilder = new StringBuilder(31 + String.valueOf(str).length());
            stringBuilder.append(str);
            stringBuilder.append("&navigationURL={NAVIGATION_URL}");
            strArr[0] = stringBuilder.toString();
            this.zzbmy = new zzaso(true, Arrays.asList(strArr));
        } else {
            try {
                this.zzbmy = zzaso.zzl(new JSONObject(str));
            } catch (JSONException e) {
                zzbbd.zzc("Error parsing configuration JSON", e);
                this.zzbmy = new zzaso();
            }
        }
        zzc = zzc(map, "X-Afma-Remote-Ping-Urls");
        if (zzc != null) {
            this.zzeca = zzc;
        }
        str = zza((Map) map, "X-Afma-Safe-Browsing");
        if (!TextUtils.isEmpty(str)) {
            try {
                this.zzecd = zzawo.zzo(new JSONObject(str));
            } catch (JSONException e2) {
                zzbbd.zzc("Error parsing safe browsing header", e2);
            }
        }
        this.zzecb |= zzd(map, "X-Afma-Render-In-Browser");
        str = zza((Map) map, "X-Afma-Pool");
        if (!TextUtils.isEmpty(str)) {
            try {
                this.zzece = new JSONObject(str).getBoolean("never_pool");
            } catch (JSONException e22) {
                zzbbd.zzc("Error parsing interstitial pool header", e22);
            }
        }
        this.zzecf = zzd(map, "X-Afma-Custom-Close-Blocked");
        this.zzecg = zzd(map, "X-Afma-Enable-Omid");
        this.zzech = zzd(map, "X-Afma-Disable-Closable-Area");
        this.zzdtk = zza((Map) map, "X-Afma-Omid-Settings");
    }
}
