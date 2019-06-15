package com.simpl.android.fingerprint.a;

import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.simpl.android.fingerprint.commons.utils.JSONUtils;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;

public final class n implements g {
    Boolean a;
    Boolean b;
    ArrayList<String> c;
    String d;
    String e;
    String f;
    String g;
    String h;
    String i;
    ArrayList<String> j;
    String k;
    String l;
    String m;
    private String n;
    private String o;

    public final JSONObject a() {
        try {
            return new JSONObject().put("isRooted", this.a).put("isRoaming", this.b).put("serialNumber", this.d).put("phoneNumber", this.n).put("email", this.o).put("deviceUpTime", this.e).put("androidId", this.f).put("appVersion", this.g).put("ipAddress", this.i).put("availableMemory", this.h).put("simSerialNumber", this.k).put("deviceManufacturer", this.l).put("deviceModel", this.m).put("carrierNetwork", this.c.toString()).put("deviceId", JSONUtils.getJSONArray(this.j));
        } catch (JSONException e) {
            ThrowableExtension.printStackTrace(e);
            return new JSONObject();
        }
    }
}
