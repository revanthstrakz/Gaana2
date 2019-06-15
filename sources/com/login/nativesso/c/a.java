package com.login.nativesso.c;

import android.util.Log;
import com.android.volley.VolleyError;
import com.android.volley.i.b;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import org.json.JSONObject;

public class a implements com.android.volley.i.a, b<JSONObject> {
    /* renamed from: a */
    public void onResponse(JSONObject jSONObject) {
    }

    public void onErrorResponse(VolleyError volleyError) {
        if (volleyError != null) {
            ThrowableExtension.printStackTrace(volleyError);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Error cause :");
            stringBuilder.append(volleyError.getCause());
            stringBuilder.append(" ,Error Message :");
            stringBuilder.append(volleyError.getMessage());
            Log.e("NATIVESSO", stringBuilder.toString());
            if (volleyError.a != null) {
                stringBuilder = new StringBuilder();
                stringBuilder.append("Error Http code :");
                stringBuilder.append(volleyError.a.a);
                Log.e("NATIVESSO", stringBuilder.toString());
            }
        }
    }
}
