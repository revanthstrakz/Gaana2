package io.branch.referral;

import android.text.TextUtils;
import com.c.a.a.a.b;
import com.c.a.a.a.c;
import io.branch.referral.Defines.Jsonkey;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class l {
    l() {
    }

    public void a(String str, JSONObject jSONObject, String str2) {
        try {
            c cVar = new c(str);
            if (jSONObject != null) {
                a(cVar, jSONObject, "");
                cVar.a(Jsonkey.BranchIdentity.getKey(), str2);
                b.a().a(cVar);
            }
        } catch (Throwable unused) {
        }
    }

    private void a(c cVar, JSONObject jSONObject, String str) throws JSONException {
        Iterator keys = jSONObject.keys();
        while (keys.hasNext()) {
            String str2 = (String) keys.next();
            Object obj = jSONObject.get(str2);
            if (!str2.startsWith("+")) {
                StringBuilder stringBuilder;
                if (obj instanceof JSONObject) {
                    JSONObject jSONObject2 = (JSONObject) obj;
                    stringBuilder = new StringBuilder();
                    stringBuilder.append(str);
                    stringBuilder.append(str2);
                    stringBuilder.append(".");
                    a(cVar, jSONObject2, stringBuilder.toString());
                } else if (obj instanceof JSONArray) {
                    JSONArray jSONArray = (JSONArray) obj;
                    stringBuilder = new StringBuilder();
                    stringBuilder.append(str2);
                    stringBuilder.append(".");
                    a(cVar, jSONArray, stringBuilder.toString());
                } else {
                    a(cVar, str, str2, jSONObject.getString(str2));
                }
            }
        }
    }

    private void a(c cVar, JSONArray jSONArray, String str) throws JSONException {
        for (int i = 0; i < jSONArray.length(); i++) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("~");
            stringBuilder.append(Integer.toString(i));
            a(cVar, str, stringBuilder.toString(), jSONArray.getString(i));
        }
    }

    private void a(c cVar, String str, String str2, String str3) {
        if (!TextUtils.isEmpty(str3)) {
            if (str2.startsWith("~")) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(str.replaceFirst("~", ""));
                stringBuilder.append(str2.replaceFirst("~", ""));
                cVar.a(stringBuilder.toString(), str3);
                return;
            }
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("$");
            stringBuilder2.append(Jsonkey.IdentityID.getKey());
            if (str2.equals(stringBuilder2.toString())) {
                cVar.a(Jsonkey.ReferringBranchIdentity.getKey(), str3);
            }
        }
    }
}
