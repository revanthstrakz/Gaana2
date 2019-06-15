package io.branch.referral;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.util.Log;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Iterator;
import org.json.JSONObject;

public class m {
    private static boolean a = false;
    private static boolean b = true;
    private static boolean c = true;
    private static String d;
    private static m e;
    private SharedPreferences f;
    private Editor g;
    private JSONObject h;
    private Context i;

    public String a() {
        return "https://api.branch.io/";
    }

    private m(Context context) {
        this.f = context.getSharedPreferences("branch_referral_shared_pref", 0);
        this.g = this.f.edit();
        this.i = context;
        this.h = new JSONObject();
    }

    public static m a(Context context) {
        if (e == null) {
            e = new m(context);
        }
        return e;
    }

    public int b() {
        return d("bnc_timeout", 5500);
    }

    public int c() {
        return d("bnc_retry_count", 3);
    }

    public int d() {
        return d("bnc_retry_interval", 1000);
    }

    public void a(String str) {
        a("bnc_app_version", str);
    }

    public String e() {
        return t("bnc_app_version");
    }

    public String f() {
        String str = null;
        try {
            ApplicationInfo applicationInfo = this.i.getPackageManager().getApplicationInfo(this.i.getPackageName(), 128);
            if (applicationInfo.metaData != null) {
                str = applicationInfo.metaData.getString("io.branch.sdk.ApplicationId");
            }
        } catch (NameNotFoundException unused) {
        }
        return str == null ? t("bnc_app_key") : str;
    }

    public boolean b(String str) {
        d = str;
        String t = t("bnc_branch_key");
        if (str != null && t != null && t.equals(str)) {
            return false;
        }
        H();
        a("bnc_branch_key", str);
        return true;
    }

    public String g() {
        if (d == null) {
            d = t("bnc_branch_key");
        }
        return d;
    }

    public String a(boolean z) {
        String str = z ? "io.branch.sdk.BranchKey" : "io.branch.sdk.BranchKey.test";
        if (!z) {
            A();
        }
        String str2 = null;
        try {
            ApplicationInfo applicationInfo = this.i.getPackageManager().getApplicationInfo(this.i.getPackageName(), 128);
            if (applicationInfo.metaData != null) {
                str = applicationInfo.metaData.getString(str);
                if (str == null && !z) {
                    try {
                        str2 = applicationInfo.metaData.getString("io.branch.sdk.BranchKey");
                    } catch (NameNotFoundException unused) {
                    }
                }
                str2 = str;
            }
        } catch (NameNotFoundException unused2) {
        }
        return str2 == null ? "bnc_no_value" : str2;
    }

    public void c(String str) {
        a("bnc_device_fingerprint_id", str);
    }

    public String h() {
        return t("bnc_device_fingerprint_id");
    }

    public void d(String str) {
        a("bnc_session_id", str);
    }

    public String i() {
        return t("bnc_session_id");
    }

    public void e(String str) {
        a("bnc_identity_id", str);
    }

    public String j() {
        return t("bnc_identity_id");
    }

    public void f(String str) {
        a("bnc_identity", str);
    }

    public String k() {
        return t("bnc_identity");
    }

    public void g(String str) {
        a("bnc_link_click_id", str);
    }

    public String l() {
        return t("bnc_link_click_id");
    }

    public void a(Boolean bool) {
        a("bnc_triggered_by_fb_app_link", bool);
    }

    public boolean m() {
        return u("bnc_triggered_by_fb_app_link");
    }

    public void h(String str) {
        a("bnc_external_intent_uri", str);
    }

    public String n() {
        return t("bnc_external_intent_uri");
    }

    public void i(String str) {
        a("bnc_external_intent_extra", str);
    }

    public String o() {
        return t("bnc_external_intent_extra");
    }

    public void j(String str) {
        a("bnc_link_click_identifier", str);
    }

    public String p() {
        return t("bnc_link_click_identifier");
    }

    public void k(String str) {
        a("bnc_app_link", str);
    }

    public String q() {
        return t("bnc_app_link");
    }

    public void l(String str) {
        a("bnc_push_identifier", str);
    }

    public String r() {
        return t("bnc_push_identifier");
    }

    public String s() {
        return t("bnc_session_params");
    }

    public void m(String str) {
        a("bnc_session_params", str);
    }

    public String t() {
        return t("bnc_install_params");
    }

    public void n(String str) {
        a("bnc_install_params", str);
    }

    public void o(String str) {
        a("bnc_user_url", str);
    }

    public String u() {
        return t("bnc_user_url");
    }

    public int v() {
        return s("bnc_is_referrable");
    }

    public void w() {
        e("bnc_is_referrable", 1);
    }

    public void x() {
        e("bnc_is_referrable", 0);
    }

    public void y() {
        a("bnc_system_read_date", Calendar.getInstance().getTimeInMillis() / 1000);
    }

    public void z() {
        Iterator it = F().iterator();
        while (it.hasNext()) {
            a((String) it.next(), 0);
        }
        a(new ArrayList());
        it = G().iterator();
        while (it.hasNext()) {
            String str = (String) it.next();
            b(str, 0);
            c(str, 0);
        }
        b(new ArrayList());
    }

    private ArrayList<String> F() {
        String t = t("bnc_buckets");
        if (t.equals("bnc_no_value")) {
            return new ArrayList();
        }
        return x(t);
    }

    private void a(ArrayList<String> arrayList) {
        if (arrayList.size() == 0) {
            a("bnc_buckets", "bnc_no_value");
        } else {
            a("bnc_buckets", c((ArrayList) arrayList));
        }
    }

    public void a(String str, int i) {
        ArrayList F = F();
        if (!F.contains(str)) {
            F.add(str);
            a(F);
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("bnc_credit_base_");
        stringBuilder.append(str);
        e(stringBuilder.toString(), i);
    }

    public int p(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("bnc_credit_base_");
        stringBuilder.append(str);
        return s(stringBuilder.toString());
    }

    private ArrayList<String> G() {
        String t = t("bnc_actions");
        if (t.equals("bnc_no_value")) {
            return new ArrayList();
        }
        return x(t);
    }

    private void b(ArrayList<String> arrayList) {
        if (arrayList.size() == 0) {
            a("bnc_actions", "bnc_no_value");
        } else {
            a("bnc_actions", c((ArrayList) arrayList));
        }
    }

    public void b(String str, int i) {
        ArrayList G = G();
        if (!G.contains(str)) {
            G.add(str);
            b(G);
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("bnc_total_base_");
        stringBuilder.append(str);
        e(stringBuilder.toString(), i);
    }

    public void c(String str, int i) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("bnc_balance_base_");
        stringBuilder.append(str);
        e(stringBuilder.toString(), i);
    }

    public int q(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("bnc_total_base_");
        stringBuilder.append(str);
        return s(stringBuilder.toString());
    }

    public int r(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("bnc_balance_base_");
        stringBuilder.append(str);
        return s(stringBuilder.toString());
    }

    private String c(ArrayList<String> arrayList) {
        String str = "";
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            String str2 = (String) it.next();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(str);
            stringBuilder.append(str2);
            stringBuilder.append(",");
            str = stringBuilder.toString();
        }
        return str.substring(0, str.length() - 1);
    }

    private ArrayList<String> x(String str) {
        ArrayList arrayList = new ArrayList();
        Collections.addAll(arrayList, str.split(","));
        return arrayList;
    }

    public int s(String str) {
        return d(str, 0);
    }

    public int d(String str, int i) {
        return e.f.getInt(str, i);
    }

    public String t(String str) {
        return e.f.getString(str, "bnc_no_value");
    }

    public boolean u(String str) {
        return e.f.getBoolean(str, false);
    }

    public void e(String str, int i) {
        e.g.putInt(str, i);
        e.g.apply();
    }

    public void a(String str, long j) {
        e.g.putLong(str, j);
        e.g.apply();
    }

    public void a(String str, String str2) {
        e.g.putString(str, str2);
        e.g.apply();
    }

    public void a(String str, Boolean bool) {
        e.g.putBoolean(str, bool.booleanValue());
        e.g.apply();
    }

    public void v(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("bnc_branch_view_use_");
        stringBuilder.append(str);
        e(stringBuilder.toString(), w(str) + 1);
    }

    public int w(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("bnc_branch_view_use_");
        stringBuilder.append(str);
        return d(stringBuilder.toString(), 0);
    }

    private void H() {
        String l = l();
        String p = p();
        String q = q();
        String r = r();
        this.g.clear();
        g(l);
        j(p);
        k(q);
        l(r);
        e.g.apply();
    }

    public void A() {
        a = true;
    }

    public boolean B() {
        return a;
    }

    public boolean C() {
        return b;
    }

    public JSONObject D() {
        return this.h;
    }

    public boolean E() {
        return a;
    }

    public void b(String str, String str2) {
        if (a) {
            Log.i(str, str2);
        }
    }

    public static void c(String str, String str2) {
        if (e != null) {
            e.b(str, str2);
        } else if (a) {
            Log.i(str, str2);
        }
    }
}
