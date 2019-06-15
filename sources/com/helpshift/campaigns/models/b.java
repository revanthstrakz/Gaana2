package com.helpshift.campaigns.models;

import android.app.Activity;
import com.helpshift.campaigns.models.AnalyticsEvent.a;
import com.helpshift.util.l;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class b implements e {
    public String a;
    public String b;
    public String c;
    public String d;
    public String e;
    public List<ActionModel> f;
    public List<String> g;
    private String h;
    private String i;
    private String j;
    private String k;
    private String l;
    private String m;
    private boolean n;
    private boolean o;
    private long p;
    private long q;

    public b(String str, JSONObject jSONObject, long j, long j2) {
        try {
            this.h = str;
            this.p = j;
            this.q = j2;
            this.a = com.helpshift.campaigns.c.b.a().d.a().a;
            this.i = jSONObject.getString("t");
            this.j = jSONObject.getString("m");
            this.b = jSONObject.optString("ci", "");
            this.c = jSONObject.optString("ic", "");
            JSONObject jSONObject2 = jSONObject.getJSONObject("ss");
            this.k = jSONObject2.getString("bg");
            this.l = jSONObject2.getString("tc");
            this.m = jSONObject2.getString("mc");
            ArrayList arrayList = new ArrayList();
            JSONArray optJSONArray = jSONObject.optJSONArray("bs");
            for (int i = 0; i < optJSONArray.length(); i++) {
                arrayList.add(new ActionModel(optJSONArray.getJSONObject(i)));
            }
            this.f = arrayList;
        } catch (JSONException e) {
            l.a("Helpshift_CampDetailMod", "Exception while creating Campaign Detail Object : ", e);
        }
    }

    public b(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, boolean z, boolean z2, long j, long j2, List<ActionModel> list, List<String> list2) {
        this.h = str;
        this.a = str2;
        this.i = str3;
        this.j = str4;
        this.b = str5;
        this.d = str6;
        this.c = str7;
        this.e = str8;
        this.k = str9;
        this.l = str10;
        this.m = str11;
        this.n = z;
        this.o = z2;
        this.f = list;
        this.g = list2;
        this.p = j;
        this.q = j2;
    }

    public long a() {
        return this.p;
    }

    public long b() {
        return this.q;
    }

    public boolean c() {
        return this.q != Long.MAX_VALUE && System.currentTimeMillis() / 1000 > this.q;
    }

    public boolean d() {
        return this.o;
    }

    public void a(boolean z) {
        this.o = z;
    }

    public boolean e() {
        return this.n;
    }

    public void b(boolean z) {
        this.n = z;
    }

    public String f() {
        return this.m;
    }

    public String g() {
        return this.l;
    }

    public String h() {
        return this.k;
    }

    public String i() {
        return this.j;
    }

    public String j() {
        return this.i;
    }

    public String k() {
        return this.h;
    }

    public void a(int i, Activity activity) {
        if (this.f != null && i >= 0 && i < this.f.size()) {
            ActionModel actionModel = (ActionModel) this.f.get(i);
            actionModel.a(activity);
            com.helpshift.campaigns.c.b.a().e.a(a.g[i], k(), Boolean.valueOf(actionModel.e));
        }
    }

    public boolean equals(Object obj) {
        boolean z = false;
        if (!(obj instanceof b)) {
            return false;
        }
        b bVar = (b) obj;
        boolean z2 = this.h.equals(bVar.h) && this.a.equals(bVar.a) && this.i.equals(bVar.i) && this.j.equals(bVar.j) && this.c.equals(bVar.c) && this.k.equals(bVar.k) && this.l.equals(bVar.l) && this.m.equals(bVar.m) && this.n == bVar.n && this.o == bVar.o && this.p == bVar.p && this.q == bVar.q;
        z2 = this.e == null ? !(z2 && bVar.e == null) : !(z2 && this.e.equals(bVar.e));
        z2 = this.b == null ? !(z2 && bVar.b == null) : !(z2 && this.b.equals(bVar.b));
        z2 = this.d == null ? !(z2 && bVar.d == null) : !(z2 && this.d.equals(bVar.d));
        z2 = this.f == null ? !(z2 && bVar.f == null) : !(z2 && this.f.equals(bVar.f));
        if (this.g == null ? !(z2 && bVar.g == null) : !(z2 && this.g.equals(bVar.g))) {
            z = true;
        }
        return z;
    }
}
