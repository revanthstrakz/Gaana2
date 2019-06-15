package com.android.volley.toolbox;

import com.android.volley.ParseError;
import com.android.volley.g;
import com.android.volley.i.a;
import com.android.volley.i.b;
import com.facebook.ads.AudienceNetworkActivity;
import java.io.UnsupportedEncodingException;
import org.json.JSONException;
import org.json.JSONObject;

public class i extends j<JSONObject> {
    /* Access modifiers changed, original: protected */
    /* renamed from: a */
    public void deliverResponse(JSONObject jSONObject) {
    }

    public i(int i, String str, JSONObject jSONObject, b<JSONObject> bVar, a aVar) {
        super(i, str, jSONObject == null ? null : jSONObject.toString(), bVar, aVar);
    }

    /* Access modifiers changed, original: protected */
    public com.android.volley.i<JSONObject> parseNetworkResponse(g gVar) {
        try {
            return com.android.volley.i.a(new JSONObject(new String(gVar.b, d.a(gVar.c, AudienceNetworkActivity.WEBVIEW_ENCODING))), d.a(gVar));
        } catch (UnsupportedEncodingException e) {
            return com.android.volley.i.a(new ParseError(e));
        } catch (JSONException e2) {
            return com.android.volley.i.a(new ParseError(e2));
        }
    }
}
