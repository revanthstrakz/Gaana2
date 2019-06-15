package com.facebook.ads.internal.c;

import com.comscore.android.id.IdHelperAndroid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;

public class b {
    private final List<String> a = new ArrayList();
    private final List<String> b = new ArrayList();

    public enum a {
        REPORT("report"),
        HIDE("hide"),
        NONE(IdHelperAndroid.NO_ID_AVAILABLE);
        
        private final String d;

        private a(String str) {
            this.d = str;
        }

        /* Access modifiers changed, original: 0000 */
        public String a() {
            return this.d;
        }
    }

    public void a() {
        this.a.add("start");
    }

    public void a(int i) {
        this.b.add(String.valueOf(i));
    }

    public void a(a aVar) {
        List list = this.a;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(aVar.a());
        stringBuilder.append("_end");
        list.add(stringBuilder.toString());
    }

    public void a(a aVar, int i) {
        List list = this.a;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(aVar.a());
        stringBuilder.append("_");
        stringBuilder.append(i);
        list.add(stringBuilder.toString());
    }

    public void b() {
        this.a.add("why_am_i_seeing_this");
    }

    public void c() {
        this.a.add("manage_ad_preferences");
    }

    public Map<String, String> d() {
        HashMap hashMap = new HashMap();
        hashMap.put("user_journey", new JSONArray(this.a).toString());
        hashMap.put("options_selected", new JSONArray(this.b).toString());
        return hashMap;
    }

    public boolean e() {
        return (this.a.isEmpty() && this.b.isEmpty()) ? false : true;
    }

    public void f() {
        this.a.clear();
        this.b.clear();
    }
}
