package io.branch.referral;

import android.content.Context;
import com.facebook.internal.AnalyticsEvents;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import io.branch.referral.Branch.e;
import org.json.JSONException;
import org.json.JSONObject;

class r extends ServerRequest {
    e g;

    public boolean a() {
        return false;
    }

    public r(String str, JSONObject jSONObject, Context context) {
        super(str, jSONObject, context);
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
                    b.put(AnalyticsEvents.PARAMETER_SHARE_ERROR_MESSAGE, "Failed to get referral code");
                    eVar = new e("Trouble retrieving the referral code.", -106);
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
            stringBuilder.append("Trouble retrieving the referral code. ");
            stringBuilder.append(str);
            eVar.onInitFinished(null, new e(stringBuilder.toString(), i));
        }
    }

    public boolean a(Context context) {
        if (super.b(context)) {
            return false;
        }
        if (this.g != null) {
            this.g.onInitFinished(null, new e("Trouble retrieving the referral code.", -102));
        }
        return true;
    }

    public void b() {
        this.g = null;
    }
}
