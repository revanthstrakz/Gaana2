package com.inmobi.commons.core.configs;

import com.google.api.client.http.HttpStatusCodes;
import com.inmobi.commons.core.e.b;
import com.inmobi.commons.core.network.NetworkError.ErrorCode;
import com.inmobi.commons.core.network.d;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import org.json.JSONException;
import org.json.JSONObject;

final class ConfigNetworkResponse {
    private static final String b = "com.inmobi.commons.core.configs.ConfigNetworkResponse";
    Map<String, ConfigResponse> a = new HashMap();
    private Map<String, a> c;
    private d d;
    private d e;
    private long f;

    public static class ConfigResponse {
        ConfigResponseStatus a;
        a b;
        d c;

        public enum ConfigResponseStatus {
            SUCCESS(200),
            NOT_MODIFIED(HttpStatusCodes.STATUS_CODE_NOT_MODIFIED),
            PRODUCT_NOT_FOUND(404),
            INTERNAL_ERROR(500),
            UNKNOWN(-1);
            
            private int a;

            private ConfigResponseStatus(int i) {
                this.a = i;
            }

            public final int getValue() {
                return this.a;
            }

            public static ConfigResponseStatus fromValue(int i) {
                for (ConfigResponseStatus configResponseStatus : values()) {
                    if (configResponseStatus.a == i) {
                        return configResponseStatus;
                    }
                }
                return UNKNOWN;
            }
        }

        public ConfigResponse(JSONObject jSONObject, a aVar) {
            this.b = aVar;
            if (jSONObject != null) {
                StringBuilder stringBuilder;
                try {
                    this.a = ConfigResponseStatus.fromValue(jSONObject.getInt("status"));
                    if (this.a == ConfigResponseStatus.SUCCESS) {
                        this.b.a(jSONObject.getJSONObject("content"));
                        if (!this.b.c()) {
                            this.c = new d(2, "The received config has failed validation.");
                            ConfigNetworkResponse.b;
                            stringBuilder = new StringBuilder("Config type:");
                            stringBuilder.append(this.b.a());
                            stringBuilder.append(" Error code:");
                            stringBuilder.append(this.c.a);
                            stringBuilder.append(" Error message:");
                            stringBuilder.append(this.c.b);
                        }
                    } else if (this.a == ConfigResponseStatus.NOT_MODIFIED) {
                        ConfigNetworkResponse.b;
                        stringBuilder = new StringBuilder("Config type:");
                        stringBuilder.append(this.b.a());
                        stringBuilder.append(" Config not modified");
                    } else {
                        this.c = new d(1, this.a.toString());
                        ConfigNetworkResponse.b;
                        stringBuilder = new StringBuilder("Config type:");
                        stringBuilder.append(this.b.a());
                        stringBuilder.append(" Error code:");
                        stringBuilder.append(this.c.a);
                        stringBuilder.append(" Error message:");
                        stringBuilder.append(this.c.b);
                    }
                } catch (JSONException e) {
                    this.c = new d(2, e.getLocalizedMessage());
                    ConfigNetworkResponse.b;
                    stringBuilder = new StringBuilder("Config type:");
                    stringBuilder.append(this.b.a());
                    stringBuilder.append(" Error code:");
                    stringBuilder.append(this.c.a);
                    stringBuilder.append(" Error message:");
                    stringBuilder.append(this.c.b);
                }
            }
        }

        public final boolean a() {
            return this.c != null;
        }
    }

    ConfigNetworkResponse(Map<String, a> map, d dVar, long j) {
        this.c = map;
        this.d = dVar;
        this.f = j;
        c();
    }

    private void c() {
        StringBuilder stringBuilder;
        if (this.d.a()) {
            for (Entry entry : this.c.entrySet()) {
                ConfigResponse configResponse = new ConfigResponse(null, (a) entry.getValue());
                configResponse.c = new d(0, "Network error in fetching config.");
                this.a.put(entry.getKey(), configResponse);
            }
            this.e = new d(0, this.d.b.b);
            StringBuilder stringBuilder2 = new StringBuilder("Error code:");
            stringBuilder2.append(this.e.a);
            stringBuilder2.append(" Error message:");
            stringBuilder2.append(this.e.b);
            try {
                HashMap hashMap = new HashMap();
                hashMap.put("name", a(this.c));
                hashMap.put("errorCode", String.valueOf(this.d.b.a.getValue()));
                hashMap.put("reason", this.d.b.b);
                hashMap.put("latency", Long.valueOf(this.f));
                b.a();
                b.a("root", "InvalidConfig", hashMap);
                return;
            } catch (Exception e) {
                stringBuilder = new StringBuilder("Error in submitting telemetry event : (");
                stringBuilder.append(e.getMessage());
                stringBuilder.append(")");
                return;
            }
        }
        try {
            JSONObject jSONObject = new JSONObject(this.d.b());
            Iterator keys = jSONObject.keys();
            while (keys.hasNext()) {
                String str = (String) keys.next();
                JSONObject jSONObject2 = jSONObject.getJSONObject(str);
                if (this.c.get(str) != null) {
                    this.a.put(str, new ConfigResponse(jSONObject2, (a) this.c.get(str)));
                }
            }
        } catch (JSONException e2) {
            this.e = new d(2, e2.getLocalizedMessage());
            stringBuilder = new StringBuilder("Error code:");
            stringBuilder.append(this.e.a);
            stringBuilder.append(" Error message:");
            stringBuilder.append(this.e.b);
            try {
                HashMap hashMap2 = new HashMap();
                hashMap2.put("name", a(this.c));
                hashMap2.put("errorCode", "ParsingError");
                hashMap2.put("reason", e2.getLocalizedMessage());
                hashMap2.put("latency", Long.valueOf(this.f));
                b.a();
                b.a("root", "InvalidConfig", hashMap2);
            } catch (Exception e3) {
                stringBuilder = new StringBuilder("Error in submitting telemetry event : (");
                stringBuilder.append(e3.getMessage());
                stringBuilder.append(")");
            }
        }
    }

    public final boolean a() {
        if (!(this.d == null || this.d.b == null)) {
            if (this.d.b.a != ErrorCode.BAD_REQUEST) {
                int value = this.d.b.a.getValue();
                boolean z = 500 <= value && value < 600;
                if (z) {
                    return true;
                }
            }
            return true;
        }
        return false;
    }

    private static String a(Map<String, a> map) {
        String str = "";
        for (String str2 : map.keySet()) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(str);
            stringBuilder.append(str2);
            stringBuilder.append(",");
            str = stringBuilder.toString();
        }
        return str.substring(0, str.length() - 1);
    }
}
