package com.appsflyer;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import com.google.api.client.http.HttpMethods;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.net.ssl.HttpsURLConnection;
import org.json.JSONException;
import org.json.JSONObject;

final class af extends o {
    private a b;
    private String c;

    interface a {
        void a(String str);

        void a(Map<String, String> map);
    }

    af(Uri uri, h hVar) {
        super(hVar);
        if (!TextUtils.isEmpty(uri.getHost()) && !TextUtils.isEmpty(uri.getPath())) {
            String[] split = uri.getPath().split("/");
            if (uri.getHost().contains("onelink.me") && split.length == 3) {
                this.a = split[1];
                this.c = split[2];
            }
        }
    }

    /* Access modifiers changed, original: final */
    public final void a(@NonNull a aVar) {
        this.b = aVar;
    }

    /* Access modifiers changed, original: final */
    public final boolean c() {
        return (TextUtils.isEmpty(this.a) || TextUtils.isEmpty(this.c)) ? false : true;
    }

    /* Access modifiers changed, original: final */
    public final void a(HttpsURLConnection httpsURLConnection) throws JSONException, IOException {
        httpsURLConnection.setRequestMethod(HttpMethods.GET);
    }

    /* Access modifiers changed, original: final */
    public final String a() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(q.b("https://onelink.%s/shortlink-sdk/v1"));
        stringBuilder.append("/");
        stringBuilder.append(this.a);
        stringBuilder.append("?id=");
        stringBuilder.append(this.c);
        return stringBuilder.toString();
    }

    /* Access modifiers changed, original: final */
    public final void a(String str) {
        try {
            Map hashMap = new HashMap();
            JSONObject jSONObject = new JSONObject(str);
            Iterator keys = jSONObject.keys();
            while (keys.hasNext()) {
                String str2 = (String) keys.next();
                hashMap.put(str2, jSONObject.optString(str2));
            }
            this.b.a(hashMap);
        } catch (JSONException e) {
            this.b.a("Can't parse one link data");
            AFLogger.a("Error while parsing to json ".concat(String.valueOf(str)), e);
        }
    }

    /* Access modifiers changed, original: final */
    public final void b() {
        this.b.a("Can't get one link data");
    }
}
