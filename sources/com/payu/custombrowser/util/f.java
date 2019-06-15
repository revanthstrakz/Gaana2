package com.payu.custombrowser.util;

import android.app.Activity;
import com.google.api.client.http.HttpMethods;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.payu.custombrowser.PayUCustomBrowserCallback;
import com.payu.custombrowser.b.a;
import com.payu.custombrowser.bean.CustomBrowserResultData;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class f implements a {
    private PayUCustomBrowserCallback a;
    private String b;
    private String c;
    private String d;
    private Activity e;

    public f(Activity activity, PayUCustomBrowserCallback payUCustomBrowserCallback, String str, String str2, String str3) {
        this.a = payUCustomBrowserCallback;
        this.b = str;
        this.c = str2;
        this.d = str3;
        this.e = activity;
        a();
    }

    private void a() {
        Object jSONObject;
        com.payu.custombrowser.bean.a aVar = new com.payu.custombrowser.bean.a();
        aVar.a(HttpMethods.POST);
        JSONArray jSONArray = new JSONArray();
        try {
            jSONObject = new JSONObject(b());
        } catch (JSONException e) {
            ThrowableExtension.printStackTrace(e);
            jSONObject = null;
        }
        jSONArray.put(jSONObject);
        aVar.c(jSONArray.toString());
        aVar.b("https://info.payu.in/merchant/MobileAnalytics");
        new b(this).execute(new com.payu.custombrowser.bean.a[]{aVar});
    }

    private String b() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(CBConstant.COMMAND);
        stringBuilder.append("=");
        stringBuilder.append(CBConstant.PAYMENT_RELATED_DETAILS_FOR_MOBILE_SDK);
        stringBuilder.append("&");
        stringBuilder.append(CBConstant.IMEI);
        stringBuilder.append("=");
        stringBuilder.append(CBUtil.getImei(this.e));
        stringBuilder.append("&");
        stringBuilder.append(CBConstant.UDID);
        stringBuilder.append("=");
        stringBuilder.append(CBUtil.getUdid(this.e));
        stringBuilder.append("&");
        stringBuilder.append(CBConstant.KEY);
        stringBuilder.append("=");
        stringBuilder.append(this.b);
        stringBuilder.append("&");
        stringBuilder.append(CBConstant.HASH);
        stringBuilder.append("=");
        stringBuilder.append(this.c);
        stringBuilder.append("&");
        stringBuilder.append(CBConstant.VAR1);
        stringBuilder.append("=");
        stringBuilder.append(this.d);
        return stringBuilder.toString();
    }

    public void a(String str) {
        CustomBrowserResultData customBrowserResultData = new CustomBrowserResultData();
        customBrowserResultData.setJsonResult(str);
        this.a.isPaymentOptionAvailable(customBrowserResultData);
    }
}
