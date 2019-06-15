package io.branch.referral;

import android.content.Context;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import io.branch.referral.Branch.b;
import io.branch.referral.Defines.LinkParam;
import java.util.Collection;
import org.json.JSONObject;

class q extends ServerRequest {
    private f g;
    private boolean h = true;
    private b i;
    private boolean j;

    public boolean a() {
        return false;
    }

    public q(String str, JSONObject jSONObject, Context context) {
        super(str, jSONObject, context);
    }

    public f m() {
        return this.g;
    }

    public boolean a(Context context) {
        boolean z = true;
        if (super.b(context)) {
            if (this.h || q()) {
                z = false;
            }
            return z;
        }
        if (this.i != null) {
            this.i.a(null, new e("Trouble creating a URL.", -102));
        }
        return true;
    }

    public void a(af afVar, Branch branch) {
        try {
            String string = afVar.b().getString("url");
            if (this.i != null) {
                this.i.a(string, null);
            }
            b(string);
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
        }
    }

    public void a(int i, String str) {
        if (this.i != null) {
            String n = n();
            b bVar = this.i;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Trouble creating a URL. ");
            stringBuilder.append(str);
            bVar.a(n, new e(stringBuilder.toString(), i));
        }
    }

    public String n() {
        if (!this.b.u().equals("bnc_no_value")) {
            return a(this.b.u());
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("https://bnc.lt/a/");
        stringBuilder.append(this.b.g());
        return a(stringBuilder.toString());
    }

    public void o() {
        if (this.i != null) {
            this.i.a(null, new e("Trouble creating a URL.", -105));
        }
    }

    private boolean q() {
        return this.b.j().equals("bnc_no_value") ^ 1;
    }

    public void b() {
        this.i = null;
    }

    private String a(String str) {
        StringBuilder stringBuilder;
        StringBuilder stringBuilder2;
        StringBuilder stringBuilder3 = new StringBuilder();
        stringBuilder3.append(str);
        stringBuilder3.append("?");
        str = stringBuilder3.toString();
        Collection<String> a = this.g.a();
        if (a != null) {
            for (String str2 : a) {
                if (str2 != null && str2.length() > 0) {
                    stringBuilder = new StringBuilder();
                    stringBuilder.append(str);
                    stringBuilder.append(LinkParam.Tags);
                    stringBuilder.append("=");
                    stringBuilder.append(str2);
                    stringBuilder.append("&");
                    str = stringBuilder.toString();
                }
            }
        }
        String b = this.g.b();
        if (b != null && b.length() > 0) {
            stringBuilder2 = new StringBuilder();
            stringBuilder2.append(str);
            stringBuilder2.append(LinkParam.Alias);
            stringBuilder2.append("=");
            stringBuilder2.append(b);
            stringBuilder2.append("&");
            str = stringBuilder2.toString();
        }
        b = this.g.e();
        if (b != null && b.length() > 0) {
            stringBuilder2 = new StringBuilder();
            stringBuilder2.append(str);
            stringBuilder2.append(LinkParam.Channel);
            stringBuilder2.append("=");
            stringBuilder2.append(b);
            stringBuilder2.append("&");
            str = stringBuilder2.toString();
        }
        b = this.g.f();
        if (b != null && b.length() > 0) {
            stringBuilder2 = new StringBuilder();
            stringBuilder2.append(str);
            stringBuilder2.append(LinkParam.Feature);
            stringBuilder2.append("=");
            stringBuilder2.append(b);
            stringBuilder2.append("&");
            str = stringBuilder2.toString();
        }
        b = this.g.g();
        if (b != null && b.length() > 0) {
            stringBuilder2 = new StringBuilder();
            stringBuilder2.append(str);
            stringBuilder2.append(LinkParam.Stage);
            stringBuilder2.append("=");
            stringBuilder2.append(b);
            stringBuilder2.append("&");
            str = stringBuilder2.toString();
        }
        long c = (long) this.g.c();
        stringBuilder = new StringBuilder();
        stringBuilder.append(str);
        stringBuilder.append(LinkParam.Type);
        stringBuilder.append("=");
        stringBuilder.append(c);
        stringBuilder.append("&");
        str = stringBuilder.toString();
        c = (long) this.g.d();
        stringBuilder = new StringBuilder();
        stringBuilder.append(str);
        stringBuilder.append(LinkParam.Duration);
        stringBuilder.append("=");
        stringBuilder.append(c);
        stringBuilder.append("&");
        str = stringBuilder.toString();
        b = this.g.h();
        if (b == null || b.length() <= 0) {
            return str;
        }
        b = c.b(b.getBytes(), 2);
        stringBuilder2 = new StringBuilder();
        stringBuilder2.append(str);
        stringBuilder2.append("source=android&data=");
        stringBuilder2.append(b);
        return stringBuilder2.toString();
    }

    /* Access modifiers changed, original: 0000 */
    public boolean p() {
        return this.j;
    }

    private void b(String str) {
        JSONObject i = this.g.i();
        if (p() && i != null) {
            new l().a("Branch Share", i, this.b.j());
        }
    }
}
