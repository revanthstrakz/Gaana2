package com.simpl.android.fingerprint.a;

import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import org.json.JSONException;
import org.json.JSONObject;

public final class q implements g {
    private String a;
    private String b;
    private String c;
    private String d;
    private String e;
    private Boolean f;

    public q(String str, String str2, String str3, String str4, String str5, Boolean bool) {
        this.a = str;
        this.b = str2;
        this.c = str3;
        this.d = str4;
        this.e = str5;
        this.f = bool;
    }

    public final JSONObject a() {
        try {
            return new JSONObject().put("id", this.b).put(AccountKitGraphConstants.EMAIL_ADDRESS_KEY, this.a).put("dataSent", this.c).put("body", this.d).put("creator", this.e).put("read", this.f);
        } catch (JSONException e) {
            ThrowableExtension.printStackTrace(e);
            return new JSONObject();
        }
    }

    public final String toString() {
        StringBuilder stringBuilder = new StringBuilder("SimplSms{address='");
        stringBuilder.append(this.a);
        stringBuilder.append('\'');
        stringBuilder.append(", id='");
        stringBuilder.append(this.b);
        stringBuilder.append('\'');
        stringBuilder.append(", dateSent='");
        stringBuilder.append(this.c);
        stringBuilder.append('\'');
        stringBuilder.append(", body='");
        stringBuilder.append(this.d);
        stringBuilder.append('\'');
        stringBuilder.append(", creator='");
        stringBuilder.append(this.e);
        stringBuilder.append('\'');
        stringBuilder.append(", read=");
        stringBuilder.append(this.f);
        stringBuilder.append('}');
        return stringBuilder.toString();
    }
}
