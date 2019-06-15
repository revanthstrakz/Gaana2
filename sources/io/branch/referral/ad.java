package io.branch.referral;

import android.content.Context;
import android.util.Log;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import io.branch.referral.Defines.Jsonkey;
import io.branch.referral.Defines.RequestPath;
import org.json.JSONException;
import org.json.JSONObject;

class ad extends ServerRequest {
    public void a(int i, String str) {
    }

    public boolean a() {
        return false;
    }

    public void b() {
    }

    public ad(Context context) {
        super(context, RequestPath.SendAPPList.getPath());
        ag agVar = new ag(context);
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(Jsonkey.DeviceFingerprintID.getKey(), this.b.h());
            jSONObject.put("apps_data", agVar.c());
            a(jSONObject);
        } catch (JSONException e) {
            ThrowableExtension.printStackTrace(e);
            this.e = true;
        }
    }

    public ad(String str, JSONObject jSONObject, Context context) {
        super(str, jSONObject, context);
    }

    public void a(af afVar, Branch branch) {
        this.b.y();
    }

    public boolean a(Context context) {
        if (super.b(context)) {
            return false;
        }
        Log.i("BranchSDK", "Trouble executing your request. Please add 'android.permission.INTERNET' in your applications manifest file");
        return true;
    }
}
