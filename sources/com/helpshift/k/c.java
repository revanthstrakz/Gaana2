package com.helpshift.k;

import android.text.TextUtils;
import com.helpshift.q.d;
import java.util.HashMap;

public class c {
    private HashMap<String, String> a = ((HashMap) this.b.a("etags"));
    private d b;

    protected c(d dVar) {
        this.b = dVar;
        if (this.a == null) {
            this.a = new HashMap();
        }
    }

    public void a(String str, String str2) {
        this.a.put(str2, str);
        this.b.a("etags", this.a);
    }

    public String a(String str) {
        return (String) this.a.get(str);
    }

    public void b(String str) {
        if (this.a.containsKey(str)) {
            this.a.remove(str);
            this.b.a("etags", this.a);
        }
    }

    public Float a() {
        return (Float) this.b.a("server-time-delta");
    }

    public void a(Float f) {
        this.b.a("server-time-delta", f);
    }

    public String b() {
        return (String) this.b.a("current-logged-in-id");
    }

    public void c(String str) throws IllegalArgumentException {
        CharSequence str2;
        if (str2 != null) {
            str2 = str2.trim();
        }
        if (TextUtils.isEmpty(str2)) {
            throw new IllegalArgumentException();
        }
        this.b.a("current-logged-in-id", str2);
    }

    public void d(String str) throws IllegalArgumentException {
        CharSequence str2;
        if (str2 != null) {
            str2 = str2.trim();
        }
        if (TextUtils.isEmpty(str2)) {
            throw new IllegalArgumentException();
        }
        this.b.b("hs-device-id", str2);
    }

    public String c() {
        return (String) this.b.a("hs-device-id");
    }

    public Boolean d() {
        return (Boolean) this.b.a("hs-first-launch");
    }

    public void a(Boolean bool) {
        this.b.a("hs-first-launch", bool);
    }

    public Boolean e() {
        return (Boolean) this.b.a("hs-one-campaign-fetch-successful");
    }

    public void b(Boolean bool) {
        this.b.a("hs-one-campaign-fetch-successful", bool);
    }

    public Boolean f() {
        Boolean bool = (Boolean) this.b.a("hs-device-properties-sync-immediately");
        return bool == null ? Boolean.valueOf(false) : bool;
    }

    public void c(Boolean bool) {
        this.b.a("hs-device-properties-sync-immediately", bool);
    }

    public String g() {
        return (String) this.b.a("sdk-language");
    }

    public void b(String str, String str2) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("hs__change_set_id:");
        stringBuilder.append(str2);
        this.b.a(stringBuilder.toString(), str);
    }

    public String e(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("hs__change_set_id:");
        stringBuilder.append(str);
        return (String) this.b.a(stringBuilder.toString());
    }

    public void f(String str) {
        this.b.b("hs-synced-user-id", str);
    }

    public String h() {
        return (String) this.b.a("hs-synced-user-id");
    }
}
