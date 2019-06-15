package com.helpshift.j;

import android.text.TextUtils;
import android.util.Log;
import com.helpshift.j.b.b;
import com.helpshift.j.d.a;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONObject;

class e implements Runnable {
    private static final SimpleDateFormat c = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.US);
    private b a;
    private b b;

    e(b bVar, b bVar2) {
        this.a = bVar;
        this.b = bVar2;
    }

    public void run() {
        try {
            if (this.a != null) {
                String format = c.format(new Date(this.a.a));
                if (!TextUtils.isEmpty(this.a.b) && this.a.b.length() > 5000) {
                    this.a.b = this.a.b.substring(0, 5000);
                }
                this.b.a(new a(format, this.a.d, this.a.b, this.a.c, a(this.a.e)));
            }
        } catch (Exception e) {
            Log.e("LogWorkerThread", "Exception in log messages worker : ", e);
        }
    }

    private String a(com.helpshift.j.c.a[] aVarArr) {
        JSONArray jSONArray = new JSONArray();
        if (aVarArr == null || aVarArr.length == 0) {
            return jSONArray.toString();
        }
        for (com.helpshift.j.c.a aVar : aVarArr) {
            if (aVar != null) {
                if (jSONArray.length() > 20) {
                    break;
                }
                JSONObject jSONObject = (JSONObject) aVar.b();
                if (jSONObject.toString().length() <= 5000) {
                    jSONArray.put(jSONObject);
                }
            }
        }
        return jSONArray.toString();
    }
}
