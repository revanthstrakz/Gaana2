package io.branch.referral;

import android.content.Context;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import io.branch.referral.Branch.f;
import io.branch.referral.Defines.Jsonkey;
import org.json.JSONException;
import org.json.JSONObject;

class z extends ServerRequest {
    f g;
    int h = 0;

    public boolean a() {
        return false;
    }

    public z(String str, JSONObject jSONObject, Context context) {
        super(str, jSONObject, context);
    }

    public boolean a(Context context) {
        if (!super.b(context)) {
            if (this.g != null) {
                this.g.a(false, new e("Trouble redeeming rewards.", -102));
            }
            return true;
        } else if (this.h > 0) {
            return false;
        } else {
            if (this.g != null) {
                this.g.a(false, new e("Trouble redeeming rewards.", -107));
            }
            return true;
        }
    }

    public void a(af afVar, Branch branch) {
        JSONObject f = f();
        boolean z = false;
        if (f != null && f.has(Jsonkey.Bucket.getKey()) && f.has(Jsonkey.Amount.getKey())) {
            try {
                int i = f.getInt(Jsonkey.Amount.getKey());
                String string = f.getString(Jsonkey.Bucket.getKey());
                if (i > 0) {
                    z = true;
                }
                this.b.a(string, this.b.p(string) - i);
            } catch (JSONException e) {
                ThrowableExtension.printStackTrace(e);
            }
        }
        if (this.g != null) {
            e eVar;
            if (z) {
                eVar = null;
            } else {
                eVar = new e("Trouble redeeming rewards.", -107);
            }
            this.g.a(z, eVar);
        }
    }

    public void a(int i, String str) {
        if (this.g != null) {
            f fVar = this.g;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Trouble redeeming rewards. ");
            stringBuilder.append(str);
            fVar.a(false, new e(stringBuilder.toString(), i));
        }
    }

    public void b() {
        this.g = null;
    }
}
