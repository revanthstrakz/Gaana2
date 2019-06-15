package com.google.android.gms.ads.internal.gmsg;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.text.TextUtils;
import com.facebook.ads.internal.g.e;
import com.google.android.gms.internal.ads.zzahu;
import com.google.android.gms.internal.ads.zzark;
import com.google.android.gms.internal.ads.zzbbd;
import com.google.android.gms.internal.ads.zzbdz;
import com.google.android.gms.internal.ads.zzbff;
import com.google.android.gms.internal.ads.zzbfg;
import com.google.android.gms.internal.ads.zzbgg;
import com.google.android.gms.internal.ads.zzbhc;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@zzark
public final class zzf {
    public static final zzu<zzbgg> zzdet = zzg.zzdfl;
    public static final zzu<zzbgg> zzdeu = zzh.zzdfl;
    public static final zzu<zzbgg> zzdev = zzi.zzdfl;
    public static final zzu<zzbgg> zzdew = new zzl();
    public static final zzu<zzbgg> zzdex = new zzm();
    public static final zzu<zzbgg> zzdey = zzj.zzdfl;
    public static final zzu<Object> zzdez = new zzn();
    public static final zzu<zzbgg> zzdfa = new zzo();
    public static final zzu<zzbgg> zzdfb = zzk.zzdfl;
    public static final zzu<zzbgg> zzdfc = new zzp();
    public static final zzu<zzbgg> zzdfd = new zzq();
    public static final zzu<zzbdz> zzdfe = new zzbff();
    public static final zzu<zzbdz> zzdff = new zzbfg();
    public static final zzu<zzbgg> zzdfg = new zze();
    public static final zzae zzdfh = new zzae();
    public static final zzu<zzbgg> zzdfi = new zzr();
    public static final zzu<zzbgg> zzdfj = new zzs();
    public static final zzu<zzbgg> zzdfk = new zzt();

    static final /* synthetic */ void zzb(zzbhc zzbhc, Map map) {
        PackageManager packageManager = zzbhc.getContext().getPackageManager();
        try {
            try {
                JSONArray jSONArray = new JSONObject((String) map.get("data")).getJSONArray("intents");
                JSONObject jSONObject = new JSONObject();
                for (int i = 0; i < jSONArray.length(); i++) {
                    try {
                        JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                        String optString = jSONObject2.optString("id");
                        String optString2 = jSONObject2.optString("u");
                        String optString3 = jSONObject2.optString("i");
                        String optString4 = jSONObject2.optString("m");
                        String optString5 = jSONObject2.optString(TtmlNode.TAG_P);
                        String optString6 = jSONObject2.optString("c");
                        jSONObject2.optString("f");
                        jSONObject2.optString(e.a);
                        String optString7 = jSONObject2.optString("intent_url");
                        Intent intent = null;
                        if (!TextUtils.isEmpty(optString7)) {
                            try {
                                intent = Intent.parseUri(optString7, 0);
                            } catch (URISyntaxException e) {
                                String str = "Error parsing the url: ";
                                optString7 = String.valueOf(optString7);
                                zzbbd.zzb(optString7.length() != 0 ? str.concat(optString7) : new String(str), e);
                            }
                        }
                        boolean z = true;
                        if (intent == null) {
                            intent = new Intent();
                            if (!TextUtils.isEmpty(optString2)) {
                                intent.setData(Uri.parse(optString2));
                            }
                            if (!TextUtils.isEmpty(optString3)) {
                                intent.setAction(optString3);
                            }
                            if (!TextUtils.isEmpty(optString4)) {
                                intent.setType(optString4);
                            }
                            if (!TextUtils.isEmpty(optString5)) {
                                intent.setPackage(optString5);
                            }
                            if (!TextUtils.isEmpty(optString6)) {
                                String[] split = optString6.split("/", 2);
                                if (split.length == 2) {
                                    intent.setComponent(new ComponentName(split[0], split[1]));
                                }
                            }
                        }
                        if (packageManager.resolveActivity(intent, 65536) == null) {
                            z = false;
                        }
                        try {
                            jSONObject.put(optString, z);
                        } catch (JSONException e2) {
                            zzbbd.zzb("Error constructing openable urls response.", e2);
                        }
                    } catch (JSONException e22) {
                        zzbbd.zzb("Error parsing the intent data.", e22);
                    }
                }
                ((zzahu) zzbhc).zza("openableIntents", jSONObject);
            } catch (JSONException unused) {
                ((zzahu) zzbhc).zza("openableIntents", new JSONObject());
            }
        } catch (JSONException unused2) {
            ((zzahu) zzbhc).zza("openableIntents", new JSONObject());
        }
    }

    static final /* synthetic */ void zzc(zzbhc zzbhc, Map map) {
        String str = (String) map.get("urls");
        if (TextUtils.isEmpty(str)) {
            zzbbd.zzeo("URLs missing in canOpenURLs GMSG.");
            return;
        }
        String[] split = str.split(",");
        Map hashMap = new HashMap();
        PackageManager packageManager = zzbhc.getContext().getPackageManager();
        for (String str2 : split) {
            String[] split2 = str2.split(";", 2);
            boolean z = true;
            if (packageManager.resolveActivity(new Intent(split2.length > 1 ? split2[1].trim() : "android.intent.action.VIEW", Uri.parse(split2[0].trim())), 65536) == null) {
                z = false;
            }
            hashMap.put(str2, Boolean.valueOf(z));
        }
        ((zzahu) zzbhc).zza("openableURLs", hashMap);
    }
}
