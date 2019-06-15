package io.branch.referral;

import android.content.Context;
import android.util.Log;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import io.branch.referral.Defines.Jsonkey;
import io.branch.referral.Defines.RequestPath;
import org.json.JSONException;
import org.json.JSONObject;

class aa extends ServerRequest {
    public void a(int i, String str) {
    }

    public boolean a() {
        return false;
    }

    public void b() {
    }

    public aa(Context context) {
        super(context, RequestPath.RegisterClose.getPath());
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(Jsonkey.DeviceFingerprintID.getKey(), this.b.h());
            jSONObject.put(Jsonkey.IdentityID.getKey(), this.b.j());
            jSONObject.put(Jsonkey.SessionID.getKey(), this.b.i());
            if (!this.b.l().equals("bnc_no_value")) {
                jSONObject.put(Jsonkey.LinkClickID.getKey(), this.b.l());
            }
            a(jSONObject);
        } catch (JSONException e) {
            ThrowableExtension.printStackTrace(e);
            this.e = true;
        }
    }

    public aa(String str, JSONObject jSONObject, Context context) {
        super(str, jSONObject, context);
    }

    public boolean a(Context context) {
        if (super.b(context)) {
            return false;
        }
        Log.i("BranchSDK", "Trouble executing your request. Please add 'android.permission.INTERNET' in your applications manifest file");
        return true;
    }

    public void a(af afVar, Branch branch) {
        this.b.m("bnc_no_value");
    }
}
