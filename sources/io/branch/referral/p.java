package io.branch.referral;

import android.content.Context;
import com.facebook.internal.AnalyticsEvents;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import io.branch.referral.Branch.e;
import io.branch.referral.Defines.Jsonkey;
import org.json.JSONException;
import org.json.JSONObject;

class p extends ServerRequest {
    e g;

    public boolean a() {
        return false;
    }

    public p(String str, JSONObject jSONObject, Context context) {
        super(str, jSONObject, context);
    }

    public String e() {
        String str = "";
        try {
            str = f().getString(Jsonkey.ReferralCode.getKey());
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(super.e());
        stringBuilder.append(str);
        return stringBuilder.toString();
    }

    public void a(af afVar, Branch branch) {
        if (this.g != null) {
            e eVar = null;
            try {
                JSONObject b;
                if (afVar.b().has("referral_code")) {
                    b = afVar.b();
                } else {
                    b = new JSONObject();
                    b.put(AnalyticsEvents.PARAMETER_SHARE_ERROR_MESSAGE, "Invalid referral code");
                    eVar = new e("Trouble applying referral code.", -103);
                }
                this.g.onInitFinished(b, eVar);
            } catch (JSONException e) {
                ThrowableExtension.printStackTrace(e);
            }
        }
    }

    public void a(int i, String str) {
        if (this.g != null) {
            e eVar = this.g;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Trouble applying the referral code. ");
            stringBuilder.append(str);
            eVar.onInitFinished(null, new e(stringBuilder.toString(), i));
        }
    }

    public boolean a(Context context) {
        if (super.b(context)) {
            return false;
        }
        if (this.g != null) {
            this.g.onInitFinished(null, new e("Trouble applying the referral code.", -102));
        }
        return true;
    }

    public void b() {
        this.g = null;
    }
}
