package com.payu.custombrowser.widgets;

import com.google.api.client.http.HttpMethods;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.payu.custombrowser.b.a;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class b implements a {
    private String a;

    public void a(String str) {
    }

    private b() {
    }

    public b(String str) {
        this.a = str;
    }

    public void a() {
        try {
            if (this.a != null && this.a.length() > 0) {
                com.payu.custombrowser.bean.a aVar = new com.payu.custombrowser.bean.a();
                aVar.a(HttpMethods.POST);
                JSONArray jSONArray = new JSONArray();
                jSONArray.put(new JSONObject(this.a));
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("command=EventAnalytics&data=");
                stringBuilder.append(jSONArray.toString());
                aVar.c(stringBuilder.toString());
                aVar.b("https://info.payu.in/merchant/MobileAnalytics");
                new com.payu.custombrowser.util.b(this).execute(new com.payu.custombrowser.bean.a[]{aVar});
            }
        } catch (JSONException e) {
            ThrowableExtension.printStackTrace(e);
        }
    }
}
