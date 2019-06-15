package com.payu.magicretry;

import android.app.Activity;
import android.content.Context;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import com.google.ads.mediation.inmobi.InMobiNetworkValues;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.payu.custombrowser.util.CBConstant;
import com.payu.magicretry.a.b;
import com.payu.magicretry.a.c;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

public class MagicRetryFragment extends Fragment implements OnClickListener {
    public static boolean a = false;
    private static List<String> n = new ArrayList();
    private static boolean r = false;
    private static String u;
    private static String v;
    String b = CBConstant.PAYU_DOMAIN_TEST;
    String c = "https://secure.payu.in";
    String d;
    private WebView e;
    private String f;
    private Context g;
    private boolean h = true;
    private ProgressBar i;
    private ImageView j;
    private a k;
    private boolean l = false;
    private Map<String, String> m = new HashMap();
    private LinearLayout o;
    private LinearLayout p;
    private boolean q = true;
    private com.payu.magicretry.c.a s;
    private String t = "";
    private int w;

    public interface a {
        void a();

        void b();
    }

    public void a(WebView webView, String str) {
    }

    public void onSaveInstanceState(Bundle bundle) {
    }

    public MagicRetryFragment() {
        this.d = a ? this.b : this.c;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.g = getActivity().getBaseContext();
        this.t = getArguments().getString("transaction_id");
        View inflate = layoutInflater.inflate(b.magicretry_fragment, viewGroup, false);
        a(inflate);
        ArrayList arrayList = new ArrayList();
        arrayList.add("https://secure.payu.in/_payment");
        arrayList.add("https://secure.payu.in/_secure_payment");
        arrayList.add("https://www.payumoney.com/txn/#/user/");
        arrayList.add("https://mpi.onlinesbi.com/electraSECURE/vbv/MPIEntry.jsp");
        arrayList.add("https://netsafe.hdfcbank.com/ACSWeb/com.enstage.entransact.servers.AccessControlServerSSL");
        arrayList.add("https://www.citibank.co.in/acspage/cap_nsapi.so");
        arrayList.add("https://acs.icicibank.com/acspage/cap");
        arrayList.add("https://secure.payu.in/_payment");
        arrayList.add("https://www.citibank.co.in/servlets/TransReq");
        arrayList.add("https://netsafe.hdfcbank.com/ACSWeb/com.enstage.entransact.servers.AccessControlServerSSL");
        arrayList.add("https://netsafe.hdfcbank.com/ACSWeb/jsp/MerchantPost.jsp");
        arrayList.add("https://netsafe.hdfcbank.com/ACSWeb/jsp/SCode.jsp");
        arrayList.add("https://netsafe.hdfcbank.com/ACSWeb/com.enstage.entransact.servers.AccessControlServerSSL");
        arrayList.add("https://netsafe.hdfcbank.com/ACSWeb/jsp/payerAuthOptions.jsp");
        arrayList.add("https://cardsecurity.enstage.com/ACSWeb/EnrollWeb/KotakBank/server/AccessControlServer");
        arrayList.add("https://cardsecurity.enstage.com/ACSWeb/EnrollWeb/KotakBank/server/OtpServer");
        arrayList.add("https://www.citibank.co.in/acspage/cap_nsapi.so");
        arrayList.add("https://acs.icicibank.com/acspage/cap");
        arrayList.add("https://secureonline.idbibank.com/ACSWeb/EnrollWeb/IDBIBank/server/AccessControlServer");
        arrayList.add("https://vpos.amxvpos.com/vpcpay");
        if (getActivity() != null) {
            a(getActivity());
        }
        return inflate;
    }

    public void a(Activity activity) {
        this.s = new com.payu.magicretry.c.a(activity.getApplicationContext(), "local_cache_analytics_mr");
    }

    private void a(View view) {
        this.i = (ProgressBar) view.findViewById(com.payu.magicretry.a.a.magic_reload_progress);
        this.j = (ImageView) view.findViewById(com.payu.magicretry.a.a.retry_btn);
        this.p = (LinearLayout) view.findViewById(com.payu.magicretry.a.a.waiting_dots_parent);
        this.o = (LinearLayout) view.findViewById(com.payu.magicretry.a.a.magic_retry_parent);
        this.o.setVisibility(0);
        this.p.setVisibility(8);
        this.j.setOnClickListener(this);
    }

    public void a(WebView webView) {
        this.e = webView;
    }

    public void onClick(View view) {
        if (view.getId() == com.payu.magicretry.a.a.retry_btn) {
            b();
        }
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            this.k = (a) context;
        } catch (ClassCastException unused) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(context.toString());
            stringBuilder.append(" must implement OnHeadlineSelectedListener");
            throw new ClassCastException(stringBuilder.toString());
        }
    }

    private void b() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("PayUWebViewClient.java Reloading URL: ");
        stringBuilder.append(this.e.getUrl());
        Log.v("#### PAYU", stringBuilder.toString());
        this.f = this.e.getUrl();
        if (this.m.size() <= 0 || !this.m.containsKey(this.e.getUrl())) {
            if (c.a(this.g)) {
                this.l = false;
                this.e.reload();
                b("m_retry_input", "click_m_retry");
                c();
                return;
            }
            c.b(this.g);
        } else if (c.a(this.g)) {
            this.l = false;
            this.e.postUrl(this.e.getUrl(), ((String) this.m.get(this.e.getUrl())).getBytes());
            f();
            b("m_retry_input", "click_m_retry");
            c();
        } else {
            c.b(this.g);
        }
    }

    public void a(Map<String, String> map) {
        this.m = map;
    }

    private void c() {
        this.o.setVisibility(8);
        this.p.setVisibility(0);
        this.i.setVisibility(0);
    }

    private void d() {
        if (isAdded()) {
            if (this.p != null) {
                this.p.setVisibility(8);
            }
            if (this.i != null) {
                this.i.setVisibility(4);
            }
            if (this.o != null) {
                this.o.setVisibility(0);
            }
        }
    }

    public void b(WebView webView, String str) {
        if (getActivity() != null && !getActivity().isFinishing() && !this.l && this.h && this.f != null) {
            this.k.b();
            this.h = true;
        }
    }

    public void c(WebView webView, String str) {
        try {
            b("m_retry_error", URLEncoder.encode(str, "UTF-8"));
            if (this.w == 0) {
                b("mr_version", "1.0.6");
                this.w++;
            }
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
        }
        if (!r) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("WebView URL: ");
            stringBuilder.append(webView.getUrl());
            stringBuilder.append(" FAILING URL: ");
            stringBuilder.append(str);
            com.payu.magicretry.a.a.a("#### PAYU", stringBuilder.toString());
            e();
            if (str == null || !b(str)) {
                this.f = null;
                return;
            }
            this.l = true;
            if (this.k != null) {
                this.k.a();
            }
            b("m_retry_input", "show_m_retry");
            this.f = webView.getUrl();
        }
    }

    public String a(String str, Context context) {
        String str2 = "";
        try {
            String str3 = this.d;
            CookieManager instance = CookieManager.getInstance();
            if (VERSION.SDK_INT < 21) {
                CookieSyncManager.createInstance(context);
                CookieSyncManager.getInstance().sync();
            }
            String cookie = instance.getCookie(str3);
            if (cookie != null) {
                for (String str4 : cookie.split(";")) {
                    if (str4.contains(str)) {
                        str2 = str4.split("=")[1];
                    }
                }
            }
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
        }
        return str2;
    }

    /* Access modifiers changed, original: 0000 */
    public String a(String str, String str2) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("payu_id", a(CBConstant.PAYUID, this.g));
            jSONObject.put(CBConstant.TXN_ID, this.t == null ? "" : this.t);
            jSONObject.put("merchant_key", u);
            jSONObject.put("page_type", "");
            jSONObject.put(CBConstant.KEY, str);
            jSONObject.put("value", str2);
            jSONObject.put(InMobiNetworkValues.PACKAGE_NAME, getActivity().getPackageName());
            jSONObject.put("bank", v == null ? "" : v);
            return jSONObject.toString();
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            return null;
        }
    }

    public void b(String str, String str2) {
        try {
            if (getActivity() != null && isAdded() && !isRemoving() && !isDetached() && this.s != null) {
                this.s.a(a(str, str2.toLowerCase()));
            }
        } catch (Exception unused) {
        }
    }

    private boolean b(String str) {
        if (!this.q) {
            return true;
        }
        for (String str2 : n) {
            if (str != null && str.contains(str2)) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("WHITELISTED URL FOUND.. SHOWING MAGIC RETRY: ");
                stringBuilder.append(str);
                com.payu.magicretry.a.a.a("#### PAYU", stringBuilder.toString());
                return true;
            }
        }
        return false;
    }

    private void e() {
        d();
    }

    public static void a(boolean z) {
        r = z;
    }

    public static void a(List<String> list) {
        n.clear();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("MR Cleared whitelisted urls, length: ");
        stringBuilder.append(n.size());
        com.payu.magicretry.a.a.a("#### PAYU", stringBuilder.toString());
        n.addAll(list);
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append("MR Updated whitelisted urls, length: ");
        stringBuilder2.append(n.size());
        com.payu.magicretry.a.a.a("#### PAYU", stringBuilder2.toString());
    }

    public void b(boolean z) {
        this.q = z;
    }

    public static void a(String str) {
        if (str != null && !str.equalsIgnoreCase("")) {
            String[] split = str.split("\\|");
            for (String str2 : split) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Split Url: ");
                stringBuilder.append(str2);
                com.payu.magicretry.a.a.a("#### PAYU", stringBuilder.toString());
            }
            if (split != null && split.length > 0) {
                a(Arrays.asList(split));
            }
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("Whitelisted URLs from JS: ");
            stringBuilder2.append(str);
            com.payu.magicretry.a.a.a("#### PAYU", stringBuilder2.toString());
        }
    }

    public static void b(String str, Context context) {
        StringBuilder stringBuilder;
        if (str == null) {
            com.payu.magicretry.a.b.a(context, "MR_SETTINGS", "MR_ENABLED", false);
            a(true);
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("MR SP Setting 1) Disable MR: ");
            stringBuilder2.append(r);
            com.payu.magicretry.a.a.a("#### PAYU", stringBuilder2.toString());
            com.payu.magicretry.a.b.a(context, "MR_SETTINGS", "MR_WHITELISTED_URLS", "");
            a(new ArrayList());
            stringBuilder = new StringBuilder();
            stringBuilder.append("MR SP Setting 2) Clear white listed urls, length: ");
            stringBuilder.append(n.size());
            com.payu.magicretry.a.a.a("#### PAYU", stringBuilder.toString());
        } else {
            com.payu.magicretry.a.b.a(context, "MR_SETTINGS", "MR_ENABLED", true);
            a(false);
            StringBuilder stringBuilder3 = new StringBuilder();
            stringBuilder3.append("MR SP Setting 1) Disable MR: ");
            stringBuilder3.append(r);
            com.payu.magicretry.a.a.a("#### PAYU", stringBuilder3.toString());
            com.payu.magicretry.a.b.a(context, "MR_SETTINGS", "MR_WHITELISTED_URLS", str);
            a(str);
            stringBuilder = new StringBuilder();
            stringBuilder.append("MR SP Setting 2) Update white listed urls, length: ");
            stringBuilder.append(n.size());
            com.payu.magicretry.a.a.a("#### PAYU", stringBuilder.toString());
        }
        com.payu.magicretry.a.a.a("#### PAYU", "MR DATA UPDATED IN SHARED PREFERENCES");
    }

    public void a(Context context) {
        a(com.payu.magicretry.a.b.b(context, "MR_SETTINGS", "MR_ENABLED", r ^ 1) ^ 1);
        a(com.payu.magicretry.a.b.b(context, "MR_SETTINGS", "MR_WHITELISTED_URLS", ""));
    }

    private void f() {
        a();
    }

    public void a() {
        CookieManager instance = CookieManager.getInstance();
        if (VERSION.SDK_INT >= 21) {
            instance.removeSessionCookies(null);
        } else {
            instance.removeSessionCookie();
        }
    }
}
