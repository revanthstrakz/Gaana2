package com.g.a;

import android.content.Context;
import android.provider.Settings.Secure;
import com.g.a.b.a;
import com.google.api.client.http.HttpMethods;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.http.entity.mime.MIME;
import org.json.JSONObject;

public class i {
    Context a = null;
    k b = null;

    public i(Context context) {
        try {
            this.a = context;
            this.b = new k(this.a);
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            j.a("Exception : MF_WV_1964");
        }
    }

    public String a() {
        try {
            return Secure.getString(this.a.getContentResolver(), "android_id");
        } catch (Exception e) {
            j.a("MFilterItDataPoints ::MF_DI_0702 ");
            ThrowableExtension.printStackTrace(e);
            return "UnAvailable";
        }
    }

    public String b() {
        try {
            a a = b.a(this.a);
            if (a != null) {
                return a.a();
            }
        } catch (Exception e) {
            j.a("MFilterItDataPoints ::MF_GAI_0702 ");
            ThrowableExtension.printStackTrace(e);
        }
        return "UnAvailable";
    }

    public String c() {
        try {
            JSONObject a = this.b.a(this.a.getPackageName());
            if (a.getInt("server1_initiate_request") != 1) {
                return "1";
            }
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(a.getString("server1_url")).openConnection();
            httpURLConnection.setRequestMethod(HttpMethods.POST);
            httpURLConnection.setRequestProperty(MIME.CONTENT_TYPE, "application/json");
            JSONObject jSONObject = new JSONObject();
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            linkedHashMap.put("sdk_gaid", b());
            linkedHashMap.put("sdk_deviceid", a());
            linkedHashMap.put("sdk_installed_app", this.a.getPackageName());
            for (Entry entry : linkedHashMap.entrySet()) {
                jSONObject.put((String) entry.getKey(), entry.getValue());
            }
            httpURLConnection.setDoOutput(true);
            new DataOutputStream(httpURLConnection.getOutputStream()).writeBytes(jSONObject.toString());
            int responseCode = httpURLConnection.getResponseCode();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Datapoints_F Response : ");
            stringBuilder.append(responseCode);
            j.a(stringBuilder.toString());
            return responseCode == 200 ? "1" : "1";
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            j.a("Exception : MF_WV_0730");
            return "1";
        }
    }

    public Map<String, String> a(Map<String, String> map) {
        try {
            j.a("Collect datapoints F");
            String c = c();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("");
            stringBuilder.append(c);
            map.put("sdk_3party_server1_response_code", stringBuilder.toString());
            j.a("Collected datapoints F");
            return map;
        } catch (Exception unused) {
            j.a("Exception while collecting datapoints F");
            return map;
        }
    }
}
