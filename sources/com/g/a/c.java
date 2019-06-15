package com.g.a;

import android.content.Context;
import android.util.Base64;
import com.google.api.client.http.HttpMethods;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.io.DataOutputStream;
import java.net.URL;
import java.util.Map;
import java.util.Map.Entry;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import org.apache.http.entity.mime.MIME;
import org.json.JSONObject;

public class c {
    Context a = null;
    e b = null;

    public c(Context context) {
        this.a = context;
    }

    private boolean a() {
        try {
            this.b = new e(this.a);
            return this.b.a().equalsIgnoreCase("UnAvailable") ^ 1;
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            j.a("Exception in checkConnectivity");
            return false;
        }
    }

    public boolean a(Map<String, String> map) {
        try {
            j.a("Inside postSQSMessage");
            if (Integer.parseInt((String) map.get("sdk_3party_server1_response_code")) == 0) {
                Map map2 = new i(this.a).a(map2);
                if (Integer.parseInt((String) map2.get("sdk_3party_server1_response_code")) == 0) {
                    j.a("Unable to connect to the 3party Server");
                    return false;
                }
            }
            JSONObject jSONObject = new JSONObject();
            boolean equalsIgnoreCase = new d(this.a).b("mf_is_install", "true").equalsIgnoreCase("true");
            for (Entry entry : map2.entrySet()) {
                jSONObject.put((String) entry.getKey(), entry.getValue());
            }
            if (!a()) {
                return false;
            }
            HttpsURLConnection httpsURLConnection = (HttpsURLConnection) new URL("https://sqs.us-west-2.amazonaws.com/632228229419/mf_sdk_datapoints_queue?").openConnection();
            httpsURLConnection.setRequestMethod(HttpMethods.POST);
            httpsURLConnection.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
            httpsURLConnection.setRequestProperty(MIME.CONTENT_TYPE, "application/x-www-form-urlencoded");
            httpsURLConnection.setHostnameVerifier(new HostnameVerifier() {
                public boolean verify(String str, SSLSession sSLSession) {
                    return str.equals("sqs.us-west-2.amazonaws.com");
                }
            });
            httpsURLConnection.setDoOutput(true);
            DataOutputStream dataOutputStream = new DataOutputStream(httpsURLConnection.getOutputStream());
            String encodeToString = Base64.encodeToString(jSONObject.toString().getBytes("UTF-8"), 0);
            String packageName = this.a.getPackageName();
            if (equalsIgnoreCase) {
                j.a("Fresh install");
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Action=SendMessage&MessageBody=");
                stringBuilder.append(encodeToString);
                stringBuilder.append("&MessageAttribute.1.Name=mf_sdk_version");
                stringBuilder.append("&MessageAttribute.1.Value.StringValue=2.7.1");
                stringBuilder.append("&MessageAttribute.1.Value.DataType=String");
                stringBuilder.append("&MessageAttribute.2.Name=mf_is_install");
                stringBuilder.append("&MessageAttribute.2.Value.StringValue=1");
                stringBuilder.append("&MessageAttribute.2.Value.DataType=String");
                stringBuilder.append("&MessageAttribute.3.Name=installed_app");
                stringBuilder.append("&MessageAttribute.3.Value.StringValue=");
                stringBuilder.append(packageName);
                stringBuilder.append("&MessageAttribute.3.Value.DataType=String");
                dataOutputStream.writeBytes(stringBuilder.toString());
                int responseCode = httpsURLConnection.getResponseCode();
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append("MFilterit Response : ");
                stringBuilder2.append(responseCode);
                j.a(stringBuilder2.toString());
                if (responseCode == 200) {
                    return true;
                }
                return false;
            }
            j.a("It is an update");
            StringBuilder stringBuilder3 = new StringBuilder();
            stringBuilder3.append("Action=SendMessage&MessageBody=");
            stringBuilder3.append(encodeToString);
            stringBuilder3.append("&MessageAttribute.1.Name=mf_sdk_version");
            stringBuilder3.append("&MessageAttribute.1.Value.StringValue=2.7.1");
            stringBuilder3.append("&MessageAttribute.1.Value.DataType=String");
            stringBuilder3.append("&MessageAttribute.2.Name=mf_is_install");
            stringBuilder3.append("&MessageAttribute.2.Value.StringValue=0");
            stringBuilder3.append("&MessageAttribute.2.Value.DataType=String");
            stringBuilder3.append("&MessageAttribute.3.Name=installed_app");
            stringBuilder3.append("&MessageAttribute.3.Value.StringValue=");
            stringBuilder3.append(packageName);
            stringBuilder3.append("&MessageAttribute.3.Value.DataType=String");
            stringBuilder3.toString();
            return true;
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            j.a("Exception : MF_WV_0730");
            return false;
        }
    }
}
