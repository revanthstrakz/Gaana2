package io.branch.referral;

import android.content.Context;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import io.branch.referral.Branch.f;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

class u extends ServerRequest {
    f g;

    public boolean a() {
        return true;
    }

    public u(String str, JSONObject jSONObject, Context context) {
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
                int i = afVar.b().getInt(str);
                if (i != this.b.p(str)) {
                    z = true;
                }
                this.b.a(str, i);
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
            stringBuilder.append("Trouble retrieving user credits. ");
            stringBuilder.append(str);
            fVar.a(false, new e(stringBuilder.toString(), i));
        }
    }

    public boolean a(Context context) {
        if (super.b(context)) {
            return false;
        }
        if (this.g != null) {
            this.g.a(false, new e("Trouble retrieving user credits.", -102));
        }
        return true;
    }

    public void b() {
        this.g = null;
    }
}
