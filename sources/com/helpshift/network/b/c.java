package com.helpshift.network.b;

import com.facebook.ads.AudienceNetworkActivity;
import com.helpshift.common.domain.network.j;
import com.helpshift.network.errors.NetworkError;
import java.io.UnsupportedEncodingException;
import org.json.JSONException;
import org.json.JSONObject;

public class c implements g<JSONObject> {
    public e<JSONObject> a(d dVar) {
        try {
            return e.a(new JSONObject(new String(dVar.b, com.helpshift.network.c.c.a(dVar.c, AudienceNetworkActivity.WEBVIEW_ENCODING))), dVar.e);
        } catch (UnsupportedEncodingException e) {
            return e.a(new NetworkError(j.o, e), dVar.e);
        } catch (JSONException e2) {
            return e.a(new NetworkError(j.o, e2), dVar.e);
        }
    }
}
