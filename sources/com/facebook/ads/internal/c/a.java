package com.facebook.ads.internal.c;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.facebook.ads.internal.s.d.b;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class a {
    private static final String[] a = new String[]{"hide_ad", "hide_ad_description", "hide_ad_follow_up_heading", "hide_ad_options", "report_ad", "report_ad_description", "report_ad_follow_up_heading", "report_ad_options", "manage_ad_preferences", "finished_hide_ad", "finished_report_ad", "finished_description", "why_am_i_seeing_this", "ad_choices_uri", "manage_ad_preferences_uri"};
    private static a b;
    private final SharedPreferences c;

    private a(Context context) {
        this.c = context.getApplicationContext().getSharedPreferences("com.facebook.ads.AD_REPORTING_CONFIG", 0);
    }

    public static long a(Context context) {
        return p(context).a("last_updated_timestamp", 0);
    }

    private long a(String str, long j) {
        return this.c.getLong(str, j);
    }

    private String a(String str, String str2) {
        str = this.c.getString(str, str2);
        return (str == null || str.equals("null")) ? str2 : str;
    }

    private static List<c> a(String str) {
        if (TextUtils.isEmpty(str) || str.equalsIgnoreCase("null")) {
            return new ArrayList();
        }
        JSONArray jSONArray = new JSONArray(str);
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject jSONObject = (JSONObject) jSONArray.get(i);
            c cVar = new c(jSONObject.getInt("option_value"), jSONObject.getString("option_text"), jSONObject.optString("children_heading"));
            for (c a : a(jSONObject.optString("children_options"))) {
                cVar.a(a);
            }
            arrayList.add(cVar);
        }
        return arrayList;
    }

    private void a() {
        Editor edit = this.c.edit();
        edit.putLong("last_updated_timestamp", 0);
        edit.apply();
    }

    public static void a(Context context, @Nullable String str) {
        Map b = b(context, str);
        if (b != null && b.size() == a.length) {
            Editor edit = p(context).c.edit();
            for (Object obj : a) {
                edit.putString(obj, (String) b.get(obj));
            }
            edit.putLong("last_updated_timestamp", System.currentTimeMillis());
            edit.apply();
        }
    }

    @Nullable
    private static Map<String, String> b(Context context, @Nullable String str) {
        if (str == null || str.isEmpty() || str.equals("[]")) {
            return null;
        }
        HashMap hashMap = new HashMap();
        try {
            JSONObject jSONObject = new JSONObject(str);
            for (String str2 : a) {
                if (!jSONObject.has(str2)) {
                    return null;
                }
                hashMap.put(str2, jSONObject.getString(str2));
            }
            if (a(jSONObject.getString("report_ad_options")).size() == 0) {
                com.facebook.ads.internal.s.d.a.a(context, "reporting", b.A, new Exception("No report ad options"));
                return null;
            } else if (a(jSONObject.getString("hide_ad_options")).size() != 0) {
                return hashMap;
            } else {
                com.facebook.ads.internal.s.d.a.a(context, "reporting", b.C, new Exception("No hide ad options"));
                return null;
            }
        } catch (JSONException e) {
            com.facebook.ads.internal.s.d.a.a(context, "reporting", b.z, e);
            return null;
        }
    }

    public static boolean b(Context context) {
        return com.facebook.ads.internal.n.a.B(context) && a(context) > 0;
    }

    public static String c(Context context) {
        return p(context).a("hide_ad", "Hide Ad");
    }

    public static String d(Context context) {
        return p(context).a("hide_ad_description", "See fewer ads like this");
    }

    public static c e(Context context) {
        c cVar = new c(q(context));
        try {
            for (c a : a(p(context).a("hide_ad_options", ""))) {
                cVar.a(a);
            }
        } catch (JSONException e) {
            p(context).a();
            com.facebook.ads.internal.s.d.a.a(context, "reporting", b.D, e);
        }
        return cVar;
    }

    public static String f(Context context) {
        return p(context).a("report_ad", "Report Ad");
    }

    public static String g(Context context) {
        return p(context).a("report_ad_description", " Mark ad as offensive or inappropriate");
    }

    public static c h(Context context) {
        c cVar = new c(r(context));
        try {
            for (c a : a(p(context).a("report_ad_options", ""))) {
                cVar.a(a);
            }
        } catch (JSONException e) {
            p(context).a();
            com.facebook.ads.internal.s.d.a.a(context, "reporting", b.B, e);
        }
        return cVar;
    }

    public static String i(Context context) {
        return p(context).a("manage_ad_preferences", "Manage ad preferences");
    }

    public static String j(Context context) {
        return p(context).a("finished_hide_ad", "Ad hidden.");
    }

    public static String k(Context context) {
        return p(context).a("finished_report_ad", "Ad reported.");
    }

    public static String l(Context context) {
        return p(context).a("finished_description", "Your submission is now being reviewed.");
    }

    public static String m(Context context) {
        return p(context).a("why_am_i_seeing_this", "Why am I seeing this?");
    }

    public static String n(Context context) {
        return p(context).a("ad_choices_uri", "");
    }

    public static String o(Context context) {
        return p(context).a("manage_ad_preferences_uri", "");
    }

    private static a p(Context context) {
        if (b == null) {
            synchronized (a.class) {
                if (b == null) {
                    b = new a(context);
                }
            }
        }
        return b;
    }

    private static String q(Context context) {
        return p(context).a("hide_ad_follow_up_heading", "Help us understand what is happening. Why don't you want to see this?");
    }

    private static String r(Context context) {
        return p(context).a("report_ad_follow_up_heading", "Help us understand what is happening. Why is this inappropriate?");
    }
}
