package io.branch.referral;

import android.content.Context;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import io.branch.referral.Branch.f;
import io.branch.referral.Defines.Jsonkey;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

class s extends ServerRequest {
    f g;

    public boolean a() {
        return true;
    }

    public s(String str, JSONObject jSONObject, Context context) {
        super(str, jSONObject, context);
    }

    public String e() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(super.e());
        stringBuilder.append(this.b.j());
        return stringBuilder.toString();
    }

    public void a(af afVar, Branch branch) {
        Iterator keys = afVar.b().keys();
        boolean z = false;
        while (keys.hasNext()) {
            String str = (String) keys.next();
            try {
                JSONObject jSONObject = afVar.b().getJSONObject(str);
                int i = jSONObject.getInt(Jsonkey.Total.getKey());
                int i2 = jSONObject.getInt(Jsonkey.Unique.getKey());
                if (!(i == this.b.q(str) && i2 == this.b.r(str))) {
                    z = true;
                }
                this.b.b(str, i);
                this.b.c(str, i2);
            } catch (JSONException e) {
                ThrowableExtension.printStackTrace(e);
            }
        }
        if (this.g != null) {
            this.g.a(z, null);
        }
    }

    public void a(int i, String str) {
        if (this.g != null) {
            f fVar = this.g;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Trouble retrieving referral counts. ");
            stringBuilder.append(str);
            fVar.a(false, new e(stringBuilder.toString(), i));
        }
    }

    public boolean a(Context context) {
        if (super.b(context)) {
            return false;
        }
        if (this.g != null) {
            this.g.a(false, new e("Trouble retrieving referral counts.", -102));
        }
        return true;
    }

    public void b() {
        this.g = null;
    }
}
