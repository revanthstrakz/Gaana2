package com.google.ads.interactivemedia.v3.internal;

import com.google.ads.interactivemedia.v3.internal.am.b;
import java.util.HashSet;
import org.json.JSONObject;

public class ap extends al {
    public ap(b bVar, HashSet<String> hashSet, JSONObject jSONObject, double d) {
        super(bVar, hashSet, jSONObject, d);
    }

    /* Access modifiers changed, original: protected|varargs */
    /* renamed from: a */
    public String doInBackground(Object... objArr) {
        return this.b.toString();
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: a */
    public void onPostExecute(String str) {
        b(str);
        super.onPostExecute(str);
    }

    private void b(String str) {
        p a = p.a();
        if (a != null) {
            for (g gVar : a.b()) {
                if (this.a.contains(gVar.f())) {
                    gVar.e().b(str, this.c);
                }
            }
        }
    }
}
