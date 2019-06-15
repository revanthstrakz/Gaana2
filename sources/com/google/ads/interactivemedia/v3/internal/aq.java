package com.google.ads.interactivemedia.v3.internal;

import android.text.TextUtils;
import com.google.ads.interactivemedia.v3.internal.am.b;
import java.util.HashSet;
import org.json.JSONObject;

public class aq extends al {
    public aq(b bVar, HashSet<String> hashSet, JSONObject jSONObject, double d) {
        super(bVar, hashSet, jSONObject, d);
    }

    /* Access modifiers changed, original: protected|varargs */
    /* renamed from: a */
    public String doInBackground(Object... objArr) {
        if (ac.b(this.b, this.d.b())) {
            return null;
        }
        this.d.a(this.b);
        return this.b.toString();
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: a */
    public void onPostExecute(String str) {
        if (!TextUtils.isEmpty(str)) {
            b(str);
        }
        super.onPostExecute(str);
    }

    private void b(String str) {
        p a = p.a();
        if (a != null) {
            for (g gVar : a.b()) {
                if (this.a.contains(gVar.f())) {
                    gVar.e().a(str, this.c);
                }
            }
        }
    }
}
