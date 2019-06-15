package io.branch.referral;

import android.content.Context;
import android.util.Log;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import io.branch.referral.Branch.h;
import io.branch.referral.Defines.Jsonkey;
import org.json.JSONException;
import org.json.JSONObject;

class x extends ServerRequest {
    private h g;

    public boolean a() {
        return false;
    }

    public x(String str, JSONObject jSONObject, Context context) {
        super(str, jSONObject, context);
    }

    public void a(af afVar, Branch branch) {
        try {
            this.b.d(afVar.b().getString(Jsonkey.SessionID.getKey()));
            this.b.e(afVar.b().getString(Jsonkey.IdentityID.getKey()));
            this.b.o(afVar.b().getString(Jsonkey.Link.getKey()));
            this.b.n("bnc_no_value");
            this.b.m("bnc_no_value");
            this.b.f("bnc_no_value");
            this.b.z();
            if (this.g == null) {
                return;
            }
        } catch (JSONException e) {
            ThrowableExtension.printStackTrace(e);
            if (this.g == null) {
                return;
            }
        } catch (Throwable th) {
            if (this.g != null) {
                this.g.a(true, null);
            }
            throw th;
        }
        this.g.a(true, null);
    }

    public void a(int i, String str) {
        if (this.g != null) {
            h hVar = this.g;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Logout error. ");
            stringBuilder.append(str);
            hVar.a(false, new e(stringBuilder.toString(), i));
        }
    }

    public boolean a(Context context) {
        if (super.b(context)) {
            return false;
        }
        Log.i("BranchSDK", "Trouble executing your request. Please add 'android.permission.INTERNET' in your applications manifest file");
        if (this.g != null) {
            this.g.a(false, new e("Logout failed", -102));
        }
        return true;
    }

    public void b() {
        this.g = null;
    }
}
