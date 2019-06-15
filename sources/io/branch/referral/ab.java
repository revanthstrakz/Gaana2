package io.branch.referral;

import android.content.Context;
import com.facebook.internal.AnalyticsEvents;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import io.branch.referral.Branch.e;
import io.branch.referral.Defines.Jsonkey;
import io.branch.referral.Defines.RequestPath;
import org.json.JSONException;
import org.json.JSONObject;

class ab extends w {
    e g;

    public boolean a() {
        return false;
    }

    public String n() {
        return "install";
    }

    public ab(Context context, e eVar, ag agVar, String str) {
        super(context, RequestPath.RegisterInstall.getPath());
        this.g = eVar;
        JSONObject jSONObject = new JSONObject();
        try {
            boolean z;
            if (!str.equals("bnc_no_value")) {
                jSONObject.put(Jsonkey.LinkClickID.getKey(), str);
            }
            if (!agVar.d().equals("bnc_no_value")) {
                jSONObject.put(Jsonkey.AppVersion.getKey(), agVar.d());
            }
            if (this.b.E()) {
                str = agVar.b();
                if (!str.equals("bnc_no_value")) {
                    jSONObject.put(Jsonkey.URIScheme.getKey(), str);
                }
            }
            if (!this.b.p().equals("bnc_no_value")) {
                jSONObject.put(Jsonkey.LinkIdentifier.getKey(), this.b.p());
            }
            if (!this.b.q().equals("bnc_no_value")) {
                jSONObject.put(Jsonkey.AndroidAppLinkURL.getKey(), this.b.q());
            }
            if (!this.b.r().equals("bnc_no_value")) {
                jSONObject.put(Jsonkey.AndroidPushIdentifier.getKey(), this.b.r());
            }
            if (!this.b.n().equals("bnc_no_value")) {
                jSONObject.put(Jsonkey.External_Intent_URI.getKey(), this.b.n());
            }
            if (!this.b.o().equals("bnc_no_value")) {
                jSONObject.put(Jsonkey.External_Intent_Extra.getKey(), this.b.o());
            }
            jSONObject.put(Jsonkey.FaceBookAppLinkChecked.getKey(), this.b.m());
            jSONObject.put(Jsonkey.IsReferrable.getKey(), this.b.v());
            jSONObject.put(Jsonkey.Update.getKey(), agVar.b(true));
            String key = Jsonkey.Debug.getKey();
            if (!this.b.E()) {
                if (!this.b.B()) {
                    z = false;
                    jSONObject.put(key, z);
                    a(jSONObject);
                }
            }
            z = true;
            jSONObject.put(key, z);
            a(jSONObject);
        } catch (JSONException e) {
            ThrowableExtension.printStackTrace(e);
            this.e = true;
        }
    }

    public ab(String str, JSONObject jSONObject, Context context) {
        super(str, jSONObject, context);
    }

    public boolean m() {
        return this.g != null;
    }

    public void a(af afVar, Branch branch) {
        super.a(afVar, branch);
        try {
            this.b.o(afVar.b().getString(Jsonkey.Link.getKey()));
            this.b.j("bnc_no_value");
            this.b.h("bnc_no_value");
            this.b.i("bnc_no_value");
            this.b.k("bnc_no_value");
            this.b.l("bnc_no_value");
            this.b.a(Boolean.valueOf(false));
            if (afVar.b().has(Jsonkey.Data.getKey())) {
                JSONObject jSONObject = new JSONObject(afVar.b().getString(Jsonkey.Data.getKey()));
                if (jSONObject.has(Jsonkey.Clicked_Branch_Link.getKey()) && jSONObject.getBoolean(Jsonkey.Clicked_Branch_Link.getKey()) && this.b.t().equals("bnc_no_value") && this.b.v() == 1) {
                    this.b.n(afVar.b().getString(Jsonkey.Data.getKey()));
                }
            }
            if (afVar.b().has(Jsonkey.LinkClickID.getKey())) {
                this.b.g(afVar.b().getString(Jsonkey.LinkClickID.getKey()));
            } else {
                this.b.g("bnc_no_value");
            }
            if (afVar.b().has(Jsonkey.Data.getKey())) {
                this.b.m(afVar.b().getString(Jsonkey.Data.getKey()));
            } else {
                this.b.m("bnc_no_value");
            }
            if (this.g != null) {
                this.g.onInitFinished(branch.f(), null);
            }
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
        }
    }

    public void a(e eVar) {
        if (eVar != null) {
            this.g = eVar;
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
            stringBuilder.append("Trouble initializing Branch. ");
            stringBuilder.append(str);
            eVar.onInitFinished(jSONObject, new e(stringBuilder.toString(), i));
        }
    }

    public boolean a(Context context) {
        if (super.b(context)) {
            return false;
        }
        if (this.g != null) {
            this.g.onInitFinished(null, new e("Trouble initializing Branch.", -102));
        }
        return true;
    }

    public void b() {
        this.g = null;
    }
}
