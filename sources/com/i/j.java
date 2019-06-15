package com.i;

import android.os.Build.VERSION;
import android.text.TextUtils;
import com.android.volley.Request;
import com.android.volley.Request.Priority;
import com.android.volley.h;
import com.android.volley.toolbox.f;
import com.android.volley.toolbox.o;
import com.constants.Constants;
import com.gaana.application.GaanaApplication;
import com.google.ads.mediation.inmobi.InMobiNetworkKeys;
import com.utilities.Util;
import java.util.HashMap;
import java.util.Map;

public class j {
    private static j a;
    private h b;

    public void b() {
    }

    public static j a() {
        if (a == null) {
            a = new j();
        }
        return a;
    }

    private j() {
        b();
    }

    public h c() {
        if (this.b == null) {
            this.b = o.a(GaanaApplication.getContext(), new f(null, g.a()));
        }
        return this.b;
    }

    public <T> void a(Request<T> request) {
        if (request.getPriority() == Priority.HIGH) {
            c().a((Request) request);
        } else {
            c().a((Request) request);
        }
    }

    public void a(Object obj) {
        if (this.b != null) {
            this.b.a(obj);
        }
    }

    public void d() {
        if (this.b != null) {
            this.b.d().b();
        }
    }

    public void a(String str) {
        if (this.b != null) {
            this.b.d().a(str, true);
        }
    }

    public Map<String, String> a(c cVar) {
        HashMap hashMap = new HashMap();
        if (TextUtils.isEmpty(Constants.ct)) {
            Constants.ct = "IN";
        }
        if (!cVar.b()) {
            hashMap.put("Accept-Encoding", "gzip");
        }
        hashMap.put("appId", Constants.bt);
        hashMap.put(InMobiNetworkKeys.COUNTRY, Constants.ct);
        hashMap.put("gps_city", Constants.cC);
        hashMap.put("gps_state", Constants.cB);
        hashMap.put("gps_enable", Constants.cD);
        hashMap.put("deviceType", Constants.bU);
        hashMap.put("appVersion", "V7");
        hashMap.put("deviceTimeInSec", String.valueOf(System.currentTimeMillis() / 1000));
        hashMap.put("deviceTime", Util.u());
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("PHPSESSID=");
        stringBuilder.append(GaanaApplication.getCurrentSessionId());
        hashMap.put("Cookie", stringBuilder.toString());
        hashMap.put("deviceId", Util.l(GaanaApplication.getContext()));
        hashMap.put("deviceOsVersion", VERSION.RELEASE);
        stringBuilder = new StringBuilder();
        stringBuilder.append("gaanaAndroid-");
        stringBuilder.append(Constants.cz);
        hashMap.put("gaanaAppVersion", stringBuilder.toString());
        if (cVar.a() && !TextUtils.isEmpty(Constants.bV)) {
            hashMap.put("display_languageV3", Constants.bV);
        }
        if (cVar.b()) {
            hashMap.put("Gaana-Accept", "application/x-fb");
        }
        return hashMap;
    }
}
