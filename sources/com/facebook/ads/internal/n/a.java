package com.facebook.ads.internal.n;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Build.VERSION;
import android.support.annotation.Nullable;
import com.google.android.exoplayer2.ExoPlayerLibraryInfo;
import com.google.api.client.http.HttpStatusCodes;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class a {
    private static a a;
    private final SharedPreferences b;

    private a(Context context) {
        this.b = context.getApplicationContext().getSharedPreferences("com.facebook.ads.FEATURE_CONFIG", 0);
    }

    public static boolean A(Context context) {
        return G(context).a("adnw_mapp_markup_impression_after_image_load", false);
    }

    public static boolean B(Context context) {
        return G(context).a("adnw_enable_inline_x_out_on_sdk", false);
    }

    public static boolean C(Context context) {
        return G(context).a("adnw_unique_db_name_per_process", false);
    }

    public static boolean D(Context context) {
        return G(context).a("adnw_log_interstitial_cache_result", false);
    }

    public static boolean E(Context context) {
        return G(context).a("adnw_fail_ad_load_on_cache_failure", false);
    }

    public static boolean F(Context context) {
        return G(context).a("adnw_should_fail_on_cleartext_http_blocked", false);
    }

    public static a G(Context context) {
        if (a == null) {
            synchronized (a.class) {
                if (a == null) {
                    a = new a(context);
                }
            }
        }
        return a;
    }

    private static int a(Context context, String str, int i) {
        int a = G(context).a(str, i);
        return (a < 0 || a >= 101) ? i : a;
    }

    public static boolean a(Context context) {
        return VERSION.SDK_INT >= 14 && b("com.google.android.exoplayer2", ExoPlayerLibraryInfo.TAG) && G(context).a("adnw_enable_exoplayer", false);
    }

    public static boolean b(Context context) {
        return VERSION.SDK_INT >= 18 && G(context).a("adnw_enable_debug_overlay", false);
    }

    private static boolean b(String str, String str2) {
        try {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(str);
            stringBuilder.append(".");
            stringBuilder.append(str2);
            Class.forName(stringBuilder.toString());
            return true;
        } catch (ClassNotFoundException unused) {
            return false;
        }
    }

    public static boolean c(Context context) {
        return G(context).a("adnw_block_lockscreen", false);
    }

    public static boolean d(Context context) {
        return G(context).a("adnw_block_cta_before_impression", false);
    }

    public static boolean e(Context context) {
        return G(context).a("adnw_android_memory_opt", false);
    }

    public static boolean f(Context context) {
        return G(context).a("adnw_android_disable_blur", false);
    }

    public static boolean g(Context context) {
        return G(context).a("adnw_android_disable_playable_precache", false);
    }

    public static boolean h(Context context) {
        return G(context).a("adnw_android_wo_bot_detection_enabled", false);
    }

    public static boolean i(Context context) {
        return VERSION.SDK_INT >= 19 && G(context).a("adnw_enable_iab", false);
    }

    public static boolean j(Context context) {
        return G(context).a("adnw_debug_logging", false);
    }

    public static Set<String> k(Context context) {
        String a = G(context).a("additional_debug_logging_black_list", "");
        HashSet hashSet = new HashSet();
        try {
            JSONArray jSONArray = new JSONArray(a);
            for (int i = 0; i < jSONArray.length(); i++) {
                hashSet.add(jSONArray.getString(i));
            }
        } catch (JSONException unused) {
        }
        return hashSet;
    }

    public static int l(Context context) {
        return a(context, "additional_debug_logging_black_list_percentage", 0);
    }

    public static int m(Context context) {
        return a(context, "additional_debug_logging_sampling_percentage", 100);
    }

    public static long n(Context context) {
        return G(context).a("unified_logging_immediate_delay_ms", 500);
    }

    public static long o(Context context) {
        return ((long) G(context).a("unified_logging_dispatch_interval_seconds", (int) HttpStatusCodes.STATUS_CODE_MULTIPLE_CHOICES)) * 1000;
    }

    public static int p(Context context) {
        return G(context).a("unified_logging_event_limit", -1);
    }

    public static boolean q(Context context) {
        return G(context).a("video_and_endcard_autorotate", "autorotate_disabled").equals("autorotate_enabled");
    }

    public static String r(Context context) {
        return G(context).a("wo_bot_detection_config", "");
    }

    public static int s(Context context) {
        return G(context).a("minimum_elapsed_time_after_impression", -1);
    }

    public static int t(Context context) {
        return G(context).a("stack_trace_sample_rate", 0);
    }

    public static boolean u(Context context) {
        return G(context).a("adnw_top_activity_viewability", false);
    }

    public static boolean v(Context context) {
        return G(context).a("adnw_enhanced_viewability_area_check", false);
    }

    public static boolean w(Context context) {
        return G(context).a("adnw_purge_on_413_response", false);
    }

    public static boolean x(Context context) {
        return G(context).a("adnw_arrows_instead_of_x_skip_button", false);
    }

    public static boolean y(Context context) {
        return G(context).a("adnw_viewability_check_area_based", false);
    }

    @Nullable
    public static String z(Context context) {
        return G(context).a("adnw_logging_endpoint_prefix", "www");
    }

    public int a(String str, int i) {
        str = this.b.getString(str, String.valueOf(i));
        try {
            return str.equals("null") ? i : Integer.valueOf(str).intValue();
        } catch (NumberFormatException unused) {
            return i;
        }
    }

    public long a(String str, long j) {
        str = this.b.getString(str, String.valueOf(j));
        try {
            if (str.equals("null")) {
                return j;
            }
            j = Long.valueOf(str).longValue();
            return j;
        } catch (NumberFormatException unused) {
        }
    }

    @Nullable
    public String a(String str, String str2) {
        str = this.b.getString(str, str2);
        return (str == null || str.equals("null")) ? str2 : str;
    }

    public void a(@Nullable String str) {
        if (str != null && !str.isEmpty() && !str.equals("[]")) {
            Editor edit = this.b.edit();
            JSONObject jSONObject = new JSONObject(str);
            Iterator keys = jSONObject.keys();
            while (keys.hasNext()) {
                String str2 = (String) keys.next();
                edit.putString(str2, jSONObject.getString(str2));
            }
            edit.apply();
        }
    }

    public boolean a(String str, boolean z) {
        str = this.b.getString(str, String.valueOf(z));
        return str.equals("null") ? z : Boolean.valueOf(str).booleanValue();
    }
}
