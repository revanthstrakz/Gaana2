package com.payu.custombrowser;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.customtabs.CustomTabsClient;
import android.support.customtabs.CustomTabsIntent;
import android.support.customtabs.CustomTabsIntent.Builder;
import android.support.customtabs.CustomTabsServiceConnection;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import com.google.ads.mediation.inmobi.InMobiNetworkValues;
import com.google.android.gms.cast.HlsSegmentFormat;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.payu.custombrowser.bean.b;
import com.payu.custombrowser.util.CBConstant;
import com.payu.custombrowser.util.CBUtil;
import com.payu.custombrowser.util.e;
import com.payu.custombrowser.util.e.j;
import com.payu.custombrowser.util.e.k;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Map;
import org.json.JSONObject;

public class PreLollipopPaymentsActivity extends AppCompatActivity {
    String a = "com.android.chrome";
    boolean b;
    CustomTabsClient c;
    Builder d;
    CustomTabsServiceConnection e;
    com.payu.custombrowser.a.a f;
    private String htmlData;
    private boolean isCustomTabsLaunched = false;
    private String merchantKey;
    private String merchantResponse = null;
    private String payUResponse = null;
    private JSONObject postData;
    private String postDataValue;
    private String response = null;
    private a s;
    private String s2sRetryUrl;
    private String txnId;
    private String url;

    private class a extends e {
        a() {
            super(8080);
        }

        public k a(String str, j jVar, Map<String, String> map, Map<String, String> map2, Map<String, String> map3) {
            if (str.endsWith("/htmldata")) {
                str = PreLollipopPaymentsActivity.this.htmlData;
            } else if (TextUtils.isEmpty(PreLollipopPaymentsActivity.this.url) || PreLollipopPaymentsActivity.this.postData == null) {
                str = null;
            } else {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("<html><head><link rel=\"icon\" type=\"image/png\" href=\"data:image/png;base64,iVBORw0KGgo=\"></head><body> <SCRIPT TYPE=\"text/JavaScript\">var f = document.createElement(\"form\");\nf.setAttribute('method',\"post\");\nf.setAttribute('name',\"dynamic\");\nf.setAttribute('action',\"");
                stringBuilder.append(PreLollipopPaymentsActivity.this.url);
                stringBuilder.append("\");\nvar json = ");
                stringBuilder.append(PreLollipopPaymentsActivity.this.postData.toString());
                stringBuilder.append(";var objVal = Object.keys(json);for(var count=0 ; count<objVal.length;count++){var i = document.createElement(\"input\");i.setAttribute('type',\"hidden\");i.name = objVal[count];i.value = json[objVal[count]];f.appendChild(i);}var button = document.createElement(\"input\");button.setAttribute('type',\"submit\");button.setAttribute('style',\"display: none;\");f.appendChild(button);document.getElementsByTagName('body')[0].appendChild(f);</SCRIPT><SCRIPT TYPE=\"text/JavaScript\">document.forms[\"dynamic\"].submit();</SCRIPT></body></html>");
                str = stringBuilder.toString();
            }
            return new k(str);
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.f = com.payu.custombrowser.a.a.a(getApplicationContext(), "local_cache_analytics");
        if (getIntent().getBundleExtra("data") != null) {
            this.url = getIntent().getBundleExtra("data").getString("url");
            this.txnId = getIntent().getBundleExtra("data").getString(CBConstant.TXNID);
            this.merchantKey = getIntent().getBundleExtra("data").getString(CBConstant.KEY);
            this.htmlData = getIntent().getBundleExtra("data").getString("html");
            this.postDataValue = getIntent().getBundleExtra("data").getString("postdata");
            this.s2sRetryUrl = getIntent().getBundleExtra("data").getString(CBConstant.S2S_RETRY_URL);
            this.s = new a();
            try {
                this.s.a();
            } catch (IOException unused) {
            }
            this.e = new CustomTabsServiceConnection() {
                public void onServiceDisconnected(ComponentName componentName) {
                }

                public void onCustomTabsServiceConnected(ComponentName componentName, CustomTabsClient customTabsClient) {
                    PreLollipopPaymentsActivity.this.c = customTabsClient;
                    PreLollipopPaymentsActivity.this.c.warmup(1);
                    PreLollipopPaymentsActivity.this.d = new Builder();
                    PreLollipopPaymentsActivity.this.d.enableUrlBarHiding();
                    PreLollipopPaymentsActivity.this.d.setShowTitle(false);
                    CustomTabsIntent build = PreLollipopPaymentsActivity.this.d.build();
                    if (PreLollipopPaymentsActivity.this.a != null) {
                        build.intent.setPackage(PreLollipopPaymentsActivity.this.a);
                    }
                    if (!TextUtils.isEmpty(PreLollipopPaymentsActivity.this.htmlData)) {
                        PreLollipopPaymentsActivity.this.a("cb_status", "custom_tabs_load_html");
                        build.launchUrl(PreLollipopPaymentsActivity.this, Uri.parse("http://127.0.0.1:8080/htmldata"));
                        PreLollipopPaymentsActivity.this.isCustomTabsLaunched = true;
                    } else if (!TextUtils.isEmpty(PreLollipopPaymentsActivity.this.s2sRetryUrl)) {
                        PreLollipopPaymentsActivity.this.a("cb_status", "custom_tabs_load_html");
                        build.launchUrl(PreLollipopPaymentsActivity.this, Uri.parse(PreLollipopPaymentsActivity.this.s2sRetryUrl));
                        PreLollipopPaymentsActivity.this.isCustomTabsLaunched = true;
                    } else if (TextUtils.isEmpty(PreLollipopPaymentsActivity.this.url) || TextUtils.isEmpty(PreLollipopPaymentsActivity.this.postDataValue)) {
                        PreLollipopPaymentsActivity.this.isCustomTabsLaunched = false;
                        if (b.SINGLETON.getPayuCustomBrowserCallback() != null) {
                            b.SINGLETON.getPayuCustomBrowserCallback().onCBErrorReceived(104, CBConstant.POST_DATA_OR_HTML_DATA_NOT_PRESENT);
                        }
                        PreLollipopPaymentsActivity.this.a();
                    } else {
                        PreLollipopPaymentsActivity.this.postData = new JSONObject(new CBUtil().getDataFromPostData(PreLollipopPaymentsActivity.this.postDataValue));
                        build.launchUrl(PreLollipopPaymentsActivity.this, Uri.parse("http://127.0.0.1:8080"));
                        PreLollipopPaymentsActivity.this.isCustomTabsLaunched = true;
                    }
                }
            };
            this.b = CustomTabsClient.bindCustomTabsService(this, this.a, this.e);
            return;
        }
        if (getIntent() != null && getIntent().getData() != null && !TextUtils.isEmpty(getIntent().getData().getQuery())) {
            String scheme = getIntent().getData().getScheme();
            this.response = getIntent().getData().getQuery();
            String[] split = this.response.split("[$][|]");
            this.merchantResponse = a(split[0]);
            if (split.length > 1) {
                this.payUResponse = a(split[1]);
            }
            if (scheme.contains("success")) {
                a("trxn_status", "success_transaction_custom_tabs");
                if (b.SINGLETON.getPayuCustomBrowserCallback() != null) {
                    b.SINGLETON.getPayuCustomBrowserCallback().onPaymentSuccess(this.payUResponse, this.merchantResponse);
                }
            } else {
                a("trxn_status", "failure_transaction_custom_tabs");
                if (b.SINGLETON.getPayuCustomBrowserCallback() != null) {
                    b.SINGLETON.getPayuCustomBrowserCallback().onPaymentFailure(this.payUResponse, this.merchantResponse);
                }
            }
        } else if (b.SINGLETON.getPayuCustomBrowserCallback() != null) {
            b.SINGLETON.getPayuCustomBrowserCallback().onCBErrorReceived(102, CBConstant.RESPONSE_NOT_PRESENT);
        }
        a();
    }

    private String a(String str) {
        String str2 = "{\"";
        for (int i = 0; i < str.length(); i++) {
            StringBuilder stringBuilder;
            if (str.charAt(i) == '=') {
                stringBuilder = new StringBuilder();
                stringBuilder.append(str2);
                stringBuilder.append("\":\"");
                str2 = stringBuilder.toString();
            } else if (str.charAt(i) == '&') {
                stringBuilder = new StringBuilder();
                stringBuilder.append(str2);
                stringBuilder.append("\",\"");
                str2 = stringBuilder.toString();
            } else {
                stringBuilder = new StringBuilder();
                stringBuilder.append(str2);
                stringBuilder.append(str.charAt(i));
                str2 = stringBuilder.toString();
            }
        }
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append(str2);
        stringBuilder2.append("\"}");
        return stringBuilder2.toString();
    }

    public void onNewIntent(Intent intent) {
        setIntent(intent);
    }

    public void onDestroy() {
        super.onDestroy();
        if (this.s != null) {
            this.s.b();
        }
        if (this.e != null) {
            unbindService(this.e);
        }
        this.e = null;
    }

    /* Access modifiers changed, original: protected */
    public void onResume() {
        super.onResume();
        if (this.isCustomTabsLaunched) {
            a("user_input", "custom_tabs_cancelled");
            if (b.SINGLETON.getPayuCustomBrowserCallback() != null) {
                b.SINGLETON.getPayuCustomBrowserCallback().onBackApprove();
            }
            this.isCustomTabsLaunched = false;
            a();
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void a(String str, String str2) {
        if (str2 != null) {
            try {
                if (!str2.trim().equalsIgnoreCase("")) {
                    this.f.a(a(getApplicationContext(), str, str2.toLowerCase(), null, Bank.keyAnalytics, Bank.a, ""));
                }
            } catch (Exception e) {
                ThrowableExtension.printStackTrace(e);
            }
        }
    }

    public String a(Context context, String str, String str2, String str3, String str4, String str5, String str6) {
        try {
            Object str32;
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(CBConstant.TXN_ID, this.txnId);
            jSONObject.put("merchant_key", this.merchantKey);
            jSONObject.put("page_type", str6);
            jSONObject.put("event_key", str);
            jSONObject.put("event_value", URLEncoder.encode(str2, "UTF-8"));
            str = "bank";
            if (str32 == null) {
                str32 = "";
            }
            jSONObject.put(str, str32);
            jSONObject.put(InMobiNetworkValues.PACKAGE_NAME, context.getPackageName());
            jSONObject.put(HlsSegmentFormat.TS, CBUtil.getSystemCurrentTime());
            return jSONObject.toString();
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            return "{}";
        }
    }

    private void a() {
        Intent intent = new Intent(this, PrePaymentsActivity.class);
        intent.addFlags(67108864);
        startActivity(intent);
        overridePendingTransition(0, 0);
    }
}
