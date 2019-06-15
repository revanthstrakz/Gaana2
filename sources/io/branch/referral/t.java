package io.branch.referral;

import android.content.Context;
import io.branch.referral.Branch.c;
import org.json.JSONObject;

class t extends ServerRequest {
    c g;

    public boolean a() {
        return false;
    }

    public t(String str, JSONObject jSONObject, Context context) {
        super(str, jSONObject, context);
    }

    public void a(af afVar, Branch branch) {
        if (this.g != null) {
            this.g.a(afVar.c(), null);
        }
    }

    public void a(int i, String str) {
        if (this.g != null) {
            c cVar = this.g;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Trouble retrieving user credit history. ");
            stringBuilder.append(str);
            cVar.a(null, new e(stringBuilder.toString(), i));
        }
    }

    public boolean a(Context context) {
        if (super.b(context)) {
            return false;
        }
        if (this.g != null) {
            this.g.a(null, new e("Trouble retrieving user credit history.", -102));
        }
        return true;
    }

    public void b() {
        this.g = null;
    }
}
