package io.branch.referral;

import android.content.Context;
import com.facebook.internal.AnalyticsEvents;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import io.branch.referral.Branch.e;
import io.branch.referral.Defines.Jsonkey;
import org.json.JSONException;
import org.json.JSONObject;

class v extends ServerRequest {
    e g;
    String h = null;

    public boolean a() {
        return false;
    }

    public boolean c() {
        return true;
    }

    public v(String str, JSONObject jSONObject, Context context) {
        super(str, jSONObject, context);
    }

    public void a(af afVar, Branch branch) {
        try {
            if (f() != null && f().has(Jsonkey.Identity.getKey())) {
                this.b.f(f().getString(Jsonkey.Identity.getKey()));
            }
            this.b.e(afVar.b().getString(Jsonkey.IdentityID.getKey()));
            this.b.o(afVar.b().getString(Jsonkey.Link.getKey()));
            if (afVar.b().has(Jsonkey.ReferringData.getKey())) {
                this.b.n(afVar.b().getString(Jsonkey.ReferringData.getKey()));
            }
            if (this.g != null) {
                this.g.onInitFinished(branch.e(), null);
            }
        } catch (JSONException e) {
            ThrowableExtension.printStackTrace(e);
        }
    }

    public void a(int i, String str) {
        if (this.g != null) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put(AnalyticsEvents.PARAMETER_SHARE_ERROR_MESSAGE, "Trouble reaching server. Please try again in a few minutes");
            } catch (JSONException e) {
                ThrowableExtension.printStackTrace(e);
            }
            e eVar = this.g;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Trouble setting the user alias. ");
            stringBuilder.append(str);
            eVar.onInitFinished(jSONObject, new e(stringBuilder.toString(), i));
        }
    }

    public boolean a(Context context) {
        if (super.b(context)) {
            try {
                String string = f().getString(Jsonkey.Identity.getKey());
                if (string == null || string.length() == 0 || string.equals(this.b.k())) {
                    return true;
                }
                return false;
            } catch (JSONException unused) {
                return true;
            }
        }
        if (this.g != null) {
            this.g.onInitFinished(null, new e("Trouble setting the user alias.", -102));
        }
        return true;
    }

    public void b() {
        this.g = null;
    }
}
