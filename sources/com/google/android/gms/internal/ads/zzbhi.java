package com.google.android.gms.internal.ads;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.facebook.internal.ServerProtocol;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONException;
import org.json.JSONObject;

@zzark
public final class zzbhi {
    private static final Pattern zzfas = Pattern.compile("^\\uFEFF?\\s*(\\s*<!--([^-]|(?!-->))*-->)*\\s*<!DOCTYPE(\\s)+html(|(\\s)+[^>]*)>", 2);
    private static final Pattern zzfat = Pattern.compile("^\\uFEFF?\\s*(\\s*<!--([^-]|(?!-->))*-->)*?\\s*<!DOCTYPE[^>]*>", 2);

    public static String zzc(@NonNull String str, @Nullable String... strArr) {
        if (strArr.length == 0) {
            return str;
        }
        StringBuilder stringBuilder = new StringBuilder();
        Matcher matcher = zzfas.matcher(str);
        int i = 0;
        int end;
        if (matcher.find()) {
            end = matcher.end();
            stringBuilder.append(str.substring(0, end));
            int length = strArr.length;
            while (i < length) {
                String str2 = strArr[i];
                if (str2 != null) {
                    stringBuilder.append(str2);
                }
                i++;
            }
            stringBuilder.append(str.substring(end));
        } else {
            if (!zzfat.matcher(str).find()) {
                end = strArr.length;
                while (i < end) {
                    String str3 = strArr[i];
                    if (str3 != null) {
                        stringBuilder.append(str3);
                    }
                    i++;
                }
            }
            stringBuilder.append(str);
        }
        return stringBuilder.toString();
    }

    public static String zzaex() {
        String str = (String) zzwu.zzpz().zzd(zzaan.zzcpu);
        String str2 = "12.4.51-000";
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(ServerProtocol.FALLBACK_DIALOG_PARAM_VERSION, str);
            jSONObject.put("sdk", "Google Mobile Ads");
            jSONObject.put("sdkVersion", str2);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("<script>");
            stringBuilder.append("Object.defineProperty(window,'MRAID_ENV',{get:function(){return ");
            stringBuilder.append(jSONObject.toString());
            stringBuilder.append("}});");
            stringBuilder.append("</script>");
            return stringBuilder.toString();
        } catch (JSONException e) {
            zzbbd.zzc("Unable to build MRAID_ENV", e);
            return null;
        }
    }
}
