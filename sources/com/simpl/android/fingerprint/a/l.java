package com.simpl.android.fingerprint.a;

import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.simpl.android.fingerprint.commons.utils.JSONUtils;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;

public final class l implements g {
    String a;
    String b;
    ArrayList<String> c;
    ArrayList<String> d;

    public final JSONObject a() {
        try {
            return new JSONObject().put("contactId", this.a).put("name", this.b).put("phoneNumber", JSONUtils.getJSONArray(this.c)).put("emails", JSONUtils.getJSONArray(this.d));
        } catch (JSONException e) {
            ThrowableExtension.printStackTrace(e);
            return new JSONObject();
        }
    }

    public final String toString() {
        StringBuilder stringBuilder = new StringBuilder("SimplContact{contactId='");
        stringBuilder.append(this.a);
        stringBuilder.append('\'');
        stringBuilder.append(", name='");
        stringBuilder.append(this.b);
        stringBuilder.append('\'');
        stringBuilder.append(", phoneNumber=");
        stringBuilder.append(this.c);
        stringBuilder.append(", emails=");
        stringBuilder.append(this.d);
        stringBuilder.append('}');
        return stringBuilder.toString();
    }
}
