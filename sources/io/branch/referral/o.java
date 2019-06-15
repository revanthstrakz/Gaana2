package io.branch.referral;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import io.branch.referral.Defines.Jsonkey;
import io.branch.referral.i.b;
import org.json.JSONException;
import org.json.JSONObject;

class o extends ServerRequest {
    private final b g = null;

    public void a(int i, String str) {
    }

    public boolean a() {
        return false;
    }

    public void b() {
    }

    public boolean c() {
        return true;
    }

    public o(String str, JSONObject jSONObject, Context context) {
        super(str, jSONObject, context);
    }

    public void a(af afVar, Branch branch) {
        if (afVar.b() != null && afVar.b().has(Jsonkey.BranchViewData.getKey()) && Branch.a().b != null && Branch.a().b.get() != null) {
            String str = "";
            try {
                JSONObject f = f();
                if (f != null && f.has(Jsonkey.Event.getKey())) {
                    str = f.getString(Jsonkey.Event.getKey());
                }
                if (Branch.a().b != null) {
                    Context context = (Activity) Branch.a().b.get();
                    i.a().a(afVar.b().getJSONObject(Jsonkey.BranchViewData.getKey()), str, context, this.g);
                }
            } catch (JSONException unused) {
                if (this.g != null) {
                    this.g.a(-201, "Unable to show branch view. Branch view received is invalid ", str);
                }
            }
        }
    }

    public boolean a(Context context) {
        if (super.b(context)) {
            return false;
        }
        Log.i("BranchSDK", "Trouble executing your request. Please add 'android.permission.INTERNET' in your applications manifest file");
        return true;
    }
}
