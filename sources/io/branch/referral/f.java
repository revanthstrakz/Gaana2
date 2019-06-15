package io.branch.referral;

import android.annotation.SuppressLint;
import android.text.TextUtils;
import io.branch.referral.Defines.LinkParam;
import java.util.Collection;
import org.json.JSONException;
import org.json.JSONObject;

class f extends JSONObject {
    private Collection<String> a;
    private String b;
    private int c;
    private String d;
    private String e;
    private String f;
    private String g;
    private int h;

    public Collection<String> a() {
        return this.a;
    }

    public String b() {
        return this.b;
    }

    public int c() {
        return this.c;
    }

    public int d() {
        return this.h;
    }

    public String e() {
        return this.d;
    }

    public String f() {
        return this.e;
    }

    public String g() {
        return this.f;
    }

    public String h() {
        return this.g;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        f fVar = (f) obj;
        if (this.b == null) {
            if (fVar.b != null) {
                return false;
            }
        } else if (!this.b.equals(fVar.b)) {
            return false;
        }
        if (this.d == null) {
            if (fVar.d != null) {
                return false;
            }
        } else if (!this.d.equals(fVar.d)) {
            return false;
        }
        if (this.e == null) {
            if (fVar.e != null) {
                return false;
            }
        } else if (!this.e.equals(fVar.e)) {
            return false;
        }
        if (this.g == null) {
            if (fVar.g != null) {
                return false;
            }
        } else if (!this.g.equals(fVar.g)) {
            return false;
        }
        if (this.f == null) {
            if (fVar.f != null) {
                return false;
            }
        } else if (!this.f.equals(fVar.f)) {
            return false;
        }
        if (this.c != fVar.c || this.h != fVar.h) {
            return false;
        }
        if (this.a == null) {
            if (fVar.a != null) {
                return false;
            }
        } else if (!this.a.toString().equals(fVar.a.toString())) {
            return false;
        }
        return true;
    }

    @SuppressLint({"DefaultLocale"})
    public int hashCode() {
        int i = 0;
        int hashCode = (((((((((this.c + 19) * 19) + (this.b == null ? 0 : this.b.toLowerCase().hashCode())) * 19) + (this.d == null ? 0 : this.d.toLowerCase().hashCode())) * 19) + (this.e == null ? 0 : this.e.toLowerCase().hashCode())) * 19) + (this.f == null ? 0 : this.f.toLowerCase().hashCode())) * 19;
        if (this.g != null) {
            i = this.g.toLowerCase().hashCode();
        }
        hashCode = ((hashCode + i) * 19) + this.h;
        if (this.a != null) {
            for (String toLowerCase : this.a) {
                hashCode = (hashCode * 19) + toLowerCase.toLowerCase().hashCode();
            }
        }
        return hashCode;
    }

    public JSONObject i() {
        JSONObject jSONObject = new JSONObject();
        try {
            StringBuilder stringBuilder;
            if (!TextUtils.isEmpty(this.d)) {
                stringBuilder = new StringBuilder();
                stringBuilder.append("~");
                stringBuilder.append(LinkParam.Channel.getKey());
                jSONObject.put(stringBuilder.toString(), this.d);
            }
            if (!TextUtils.isEmpty(this.b)) {
                stringBuilder = new StringBuilder();
                stringBuilder.append("~");
                stringBuilder.append(LinkParam.Alias.getKey());
                jSONObject.put(stringBuilder.toString(), this.b);
            }
            if (!TextUtils.isEmpty(this.e)) {
                stringBuilder = new StringBuilder();
                stringBuilder.append("~");
                stringBuilder.append(LinkParam.Feature.getKey());
                jSONObject.put(stringBuilder.toString(), this.e);
            }
            if (!TextUtils.isEmpty(this.f)) {
                stringBuilder = new StringBuilder();
                stringBuilder.append("~");
                stringBuilder.append(LinkParam.Stage.getKey());
                jSONObject.put(stringBuilder.toString(), this.f);
            }
            if (has(LinkParam.Tags.getKey())) {
                jSONObject.put(LinkParam.Tags.getKey(), getJSONArray(LinkParam.Tags.getKey()));
            }
            stringBuilder = new StringBuilder();
            stringBuilder.append("~");
            stringBuilder.append(LinkParam.Type.getKey());
            jSONObject.put(stringBuilder.toString(), this.c);
            stringBuilder = new StringBuilder();
            stringBuilder.append("~");
            stringBuilder.append(LinkParam.Duration.getKey());
            jSONObject.put(stringBuilder.toString(), this.h);
        } catch (JSONException unused) {
        }
        return jSONObject;
    }
}
