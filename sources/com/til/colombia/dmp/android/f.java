package com.til.colombia.dmp.android;

import android.content.Context;
import android.text.TextUtils;
import java.util.LinkedList;
import org.json.JSONException;
import org.json.JSONObject;

public final class f {
    public static final String a = "https://ase.clmbtech.com";
    public static final String b = "https://ade.clmbtech.com";
    public static final String c = "https://tml.clmbtech.com";
    public static final String d = "cde/runningFeed.htm";
    public static final String e = "sdk";
    public static final String f = "uid/syncIds.htm";
    public static final String g = "cde/ae/2658/aea";
    public static final String h = "uid/syncPartner.htm";
    public static final String i = "mv/app-mobver";
    public static final String j = "cde/sdk/config/rootConfig.htm";
    private static LinkedList<String> k;
    private static LinkedList<String> l;
    private static String m;
    private static int n;
    private static int o;

    public static void a(Context context, JSONObject jSONObject) throws JSONException {
        Utils.setPreferences(context, Utils.DMP_PREF, Utils.DMP_DOMAIN, jSONObject.optString("sdkServerAddr"));
        Utils.setPreferences(context, Utils.DMP_PREF, Utils.ADE_DOMAIN, jSONObject.optString("adeServerAddr"));
        Utils.setPreferences(context, Utils.DMP_PREF, Utils.PCR_DOMAIN, jSONObject.optString("PCR_SDK_URL"));
        Utils.setPreferences(context, Utils.DMP_PREF, Utils.DMP_AUDS_UPDATE_REFRESH_TIME, jSONObject.optLong(Utils.DMP_AUDS_UPDATE_REFRESH_TIME));
        boolean optBoolean = jSONObject.optBoolean("personaCapturing", true);
        boolean optBoolean2 = jSONObject.optBoolean("intentCapturing", true);
        Utils.setPreferences(context, Utils.DMP_PREF, Utils.PERSONA_SERVER_DISABLE, optBoolean ^ 1);
        Utils.setPreferences(context, Utils.DMP_PREF, Utils.CP_SERVER_DISABLE, optBoolean2 ^ 1);
    }

    public static void a(Context context) {
        int length;
        String preferences = Utils.getPreferences(context, Utils.DMP_PREF, Utils.DMP_DOMAIN);
        int i = 0;
        if (!TextUtils.isEmpty(preferences)) {
            l = new LinkedList();
            for (Object add : preferences.split(",")) {
                l.add(add);
            }
        }
        preferences = Utils.getPreferences(context, Utils.DMP_PREF, Utils.ADE_DOMAIN);
        if (!TextUtils.isEmpty(preferences)) {
            k = new LinkedList();
            String[] split = preferences.split(",");
            length = split.length;
            while (i < length) {
                k.add(split[i]);
                i++;
            }
        }
        m = Utils.getPreferences(context, Utils.DMP_PREF, Utils.PCR_DOMAIN);
    }

    public static String a() {
        try {
            if (k != null && k.size() > 0) {
                StringBuilder stringBuilder = new StringBuilder("https://");
                LinkedList linkedList = k;
                int i = n;
                n = i + 1;
                stringBuilder.append((String) linkedList.get(i % k.size()));
                return stringBuilder.toString();
            }
        } catch (Exception unused) {
        }
        return "https://ade.clmbtech.com";
    }

    public static String b() {
        try {
            if (l != null && l.size() > 0) {
                StringBuilder stringBuilder = new StringBuilder("https://");
                LinkedList linkedList = l;
                int i = o;
                o = i + 1;
                stringBuilder.append((String) linkedList.get(i % l.size()));
                return stringBuilder.toString();
            }
        } catch (Exception unused) {
        }
        return a;
    }

    private static String c() {
        try {
            if (m != null) {
                StringBuilder stringBuilder = new StringBuilder("https://");
                stringBuilder.append(m);
                return stringBuilder.toString();
            }
        } catch (Exception unused) {
        }
        return c;
    }
}
