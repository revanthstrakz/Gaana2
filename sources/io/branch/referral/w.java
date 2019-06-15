package io.branch.referral;

import android.app.Activity;
import android.content.Context;
import io.branch.referral.Branch.g;
import io.branch.referral.Defines.Jsonkey;
import org.json.JSONException;
import org.json.JSONObject;

abstract class w extends ServerRequest {
    public boolean g() {
        return true;
    }

    public abstract boolean m();

    public abstract String n();

    public w(Context context, String str) {
        super(context, str);
    }

    protected w(String str, JSONObject jSONObject, Context context) {
        super(str, jSONObject, context);
    }

    public static boolean a(String str) {
        if (str != null) {
            return str.equalsIgnoreCase("open") || str.equalsIgnoreCase("install");
        } else {
            return false;
        }
    }

    public boolean a(af afVar) {
        if (!(afVar == null || afVar.b() == null || !afVar.b().has(Jsonkey.BranchViewData.getKey()))) {
            try {
                JSONObject jSONObject = afVar.b().getJSONObject(Jsonkey.BranchViewData.getKey());
                String n = n();
                if (Branch.a().b == null || Branch.a().b.get() == null) {
                    return i.a().a(jSONObject, n);
                }
                Context context = (Activity) Branch.a().b.get();
                int i = 1;
                if (context instanceof g) {
                    i = 1 ^ ((g) context).a();
                }
                if (i != 0) {
                    return i.a().a(jSONObject, n, context, Branch.a());
                }
                return i.a().a(jSONObject, n);
            } catch (JSONException unused) {
            }
        }
        return false;
    }

    public void a(af afVar, Branch branch) {
        try {
            if (afVar.b() != null && afVar.b().has(Jsonkey.Data.getKey())) {
                new l().a(this instanceof ab ? "Branch Install" : "Branch Open", new JSONObject(afVar.b().getString(Jsonkey.Data.getKey())), this.b.j());
            }
        } catch (JSONException unused) {
        }
    }

    public void o() {
        if (!this.b.p().equals("bnc_no_value")) {
            try {
                f().put(Jsonkey.LinkIdentifier.getKey(), this.b.p());
            } catch (JSONException unused) {
            }
        }
    }
}
