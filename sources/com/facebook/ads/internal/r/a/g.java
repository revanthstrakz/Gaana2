package com.facebook.ads.internal.r.a;

import com.facebook.ads.internal.settings.AdInternalSettings;
import java.net.HttpURLConnection;
import java.util.List;
import java.util.Map;

public class g implements r {
    private void a(Map<String, List<String>> map) {
        if (map != null) {
            for (String str : map.keySet()) {
                for (String str2 : (List) map.get(str)) {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(str);
                    stringBuilder.append(":");
                    stringBuilder.append(str2);
                    a(stringBuilder.toString());
                }
            }
        }
    }

    public void a(n nVar) {
        if (nVar != null) {
            a("=== HTTP Response ===");
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Receive url: ");
            stringBuilder.append(nVar.b());
            a(stringBuilder.toString());
            stringBuilder = new StringBuilder();
            stringBuilder.append("Status: ");
            stringBuilder.append(nVar.a());
            a(stringBuilder.toString());
            a(nVar.c());
            stringBuilder = new StringBuilder();
            stringBuilder.append("Content:\n");
            stringBuilder.append(nVar.e());
            a(stringBuilder.toString());
        }
    }

    public void a(String str) {
        System.out.println(str);
    }

    public void a(HttpURLConnection httpURLConnection, Object obj) {
        a("=== HTTP Request ===");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(httpURLConnection.getRequestMethod());
        stringBuilder.append(" ");
        stringBuilder.append(httpURLConnection.getURL().toString());
        a(stringBuilder.toString());
        if (obj instanceof String) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("Content: ");
            stringBuilder.append((String) obj);
            a(stringBuilder.toString());
        }
        a(httpURLConnection.getRequestProperties());
    }

    public boolean a() {
        return AdInternalSettings.isDebugBuild();
    }
}
