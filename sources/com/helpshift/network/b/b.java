package com.helpshift.network.b;

import com.facebook.ads.AudienceNetworkActivity;
import com.helpshift.common.domain.network.j;
import com.helpshift.network.c.c;
import com.helpshift.network.errors.NetworkError;
import java.io.UnsupportedEncodingException;
import org.json.JSONArray;
import org.json.JSONException;

public class b implements g<JSONArray> {
    public e<JSONArray> a(d dVar) {
        try {
            return e.a(new JSONArray(new String(dVar.b, c.a(dVar.c, AudienceNetworkActivity.WEBVIEW_ENCODING))), dVar.e);
        } catch (UnsupportedEncodingException e) {
            return e.a(new NetworkError(j.o, e), dVar.e);
        } catch (JSONException e2) {
            return e.a(new NetworkError(j.o, e2), dVar.e);
        }
    }
}
