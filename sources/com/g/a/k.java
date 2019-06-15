package com.g.a;

import android.content.Context;
import com.google.api.client.http.HttpMethods;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.io.DataInputStream;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
import org.apache.http.entity.mime.MIME;
import org.json.JSONObject;

public class k {
    Context a = null;

    public k(Context context) {
        this.a = context;
    }

    public JSONObject a(String str) {
        try {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("https://eo9jrbahqb.execute-api.us-west-2.amazonaws.com/prod/get_sdk_configuration?installed_app=");
            stringBuilder.append(str);
            HttpsURLConnection httpsURLConnection = (HttpsURLConnection) new URL(stringBuilder.toString()).openConnection();
            httpsURLConnection.setRequestMethod(HttpMethods.GET);
            httpsURLConnection.setRequestProperty(MIME.CONTENT_TYPE, "application/json");
            DataInputStream dataInputStream = new DataInputStream(httpsURLConnection.getInputStream());
            StringBuffer stringBuffer = new StringBuffer();
            while (true) {
                String readLine = dataInputStream.readLine();
                if (readLine != null) {
                    stringBuffer.append(readLine);
                    System.out.println(readLine);
                } else {
                    dataInputStream.close();
                    return new JSONObject(stringBuffer.toString());
                }
            }
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            j.a("Exception : MF_GCONFIG_0730");
            return null;
        }
    }
}
