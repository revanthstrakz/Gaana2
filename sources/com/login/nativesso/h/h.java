package com.login.nativesso.h;

import com.android.volley.AuthFailureError;
import com.android.volley.i.a;
import com.android.volley.i.b;
import java.util.Map;
import org.json.JSONObject;

public class h extends a {
    private Map<String, String> a;

    public h(int i, JSONObject jSONObject, b<JSONObject> bVar, a aVar, Map<String, String> map) {
        super(i, com.login.nativesso.i.b.I, jSONObject, bVar, aVar);
        a(map);
    }

    public void a(Map<String, String> map) {
        this.a = map;
    }

    public Map<String, String> getHeaders() throws AuthFailureError {
        return this.a;
    }
}
