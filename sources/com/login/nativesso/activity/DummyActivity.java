package com.login.nativesso.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import com.gaana.login.LoginManager;
import com.gaana.login.sso.SsoErrorCodes;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.tasks.Task;
import com.login.nativesso.a.x;
import com.login.nativesso.a.z;
import com.login.nativesso.d.a;
import com.login.nativesso.d.b;
import com.login.nativesso.i.c;
import com.login.nativesso.i.e;
import com.truecaller.android.sdk.ITrueCallback;
import com.truecaller.android.sdk.TrueSDK;
import com.truecaller.android.sdk.TrueSdkScope;
import com.truecaller.android.sdk.TrueSdkScope.Builder;
import java.util.Arrays;
import java.util.Locale;

public class DummyActivity extends Activity implements ConnectionCallbacks, OnConnectionFailedListener, ITrueCallback {
    int a = 101;
    b b;
    a c;
    private String d;
    private TrueSdkScope e;
    private String f;
    private String g;
    private String h;

    public void onConnected(Bundle bundle) {
    }

    public void onConnectionFailed(ConnectionResult connectionResult) {
    }

    public void onConnectionSuspended(int i) {
    }

    /* Access modifiers changed, original: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(1);
        setContentView(com.login.nativesso.a.b.activity_dummy);
        this.f = getIntent().getExtras().getString("SignInBy");
        if (this.f.equalsIgnoreCase("googlePlus")) {
            this.g = getIntent().getExtras().getString("login_link_pic");
            String string = getIntent().getExtras().getString("clientId");
            this.b = b.a();
            this.b.a(string, this, this.a);
            this.b.a(this.g);
        } else if (this.f.equalsIgnoreCase(LoginManager.SSO_SOCIAL_LOGIN_TYPE_FACEBOOK)) {
            this.h = getIntent().getExtras().getString("login_link_pic");
            this.c = a.a();
            this.c.a((Activity) this);
            if (getIntent().getExtras().getBoolean("permissionRequired")) {
                this.c.a(this.h, getIntent().getExtras().getStringArray("permission"));
                return;
            }
            this.c.a(this.h);
        } else if (this.f.equalsIgnoreCase("trueCaller")) {
            b();
        }
    }

    private void b() {
        this.e = new Builder(this, this).consentMode(4).consentTitleOption(3).footerType(1).build();
        TrueSDK.init(this.e);
        if (e.a(getApplicationContext())) {
            try {
                String string = getIntent().getExtras().getString("Language");
                if (string != null) {
                    TrueSDK.getInstance().setLocale(new Locale(string));
                }
            } catch (Exception unused) {
            }
            TrueSDK.getInstance().getUserProfile(this);
            return;
        }
        z zVar = (z) com.login.nativesso.b.a.a("TrueCallerLoginCb");
        if (zVar != null) {
            zVar.a(c.a(4012, "TRUECALLER_NOT_INSTALLED"));
        }
    }

    /* Access modifiers changed, original: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        if (this.e != null) {
            TrueSDK.getInstance().onActivityResultObtained(this, i2, intent);
            return;
        }
        if (i == this.a) {
            a(GoogleSignIn.getSignedInAccountFromIntent(intent));
        } else if (i2 == -1) {
            a.a().b().onActivityResult(i, i2, intent);
        } else {
            x xVar = (x) com.login.nativesso.b.a.a("SocialLoginCb");
            if (xVar != null) {
                xVar.onLoginFailure(c.a((int) SsoErrorCodes.REQUEST_FAILED, "REQUEST_FAILED"));
                com.login.nativesso.b.a.b("SocialLoginCb");
            }
            finish();
        }
    }

    private void a(Task<GoogleSignInAccount> task) {
        try {
            GoogleSignInAccount googleSignInAccount = (GoogleSignInAccount) task.getResult(ApiException.class);
            googleSignInAccount.getEmail();
            googleSignInAccount.getIdToken();
            googleSignInAccount.getDisplayName();
            this.d = googleSignInAccount.getId();
            a(googleSignInAccount.getIdToken());
        } catch (ApiException e) {
            Log.i("exception", e.toString());
            x xVar = (x) com.login.nativesso.b.a.a("SocialLoginCb");
            if (xVar != null) {
                xVar.onLoginFailure(c.a((int) SsoErrorCodes.REQUEST_FAILED, "REQUEST_FAILED"));
                com.login.nativesso.b.a.b("SocialLoginCb");
            }
            finish();
        }
    }

    public void a(String str) {
        com.login.nativesso.g.b a = com.login.nativesso.g.b.a();
        x xVar = (x) com.login.nativesso.b.a.a("SocialLoginCb");
        if ("login".equalsIgnoreCase(this.g)) {
            c.a(a.a("channel", (Context) this), a.a("siteId", (Context) this), str, this.d, true, a.a("TGID", (Context) this), a.a("channel", (Context) this), "", xVar);
        } else if ("link".equalsIgnoreCase(this.g)) {
            c.a(str, this.d, LoginManager.SSO_SOCIAL_LOGIN_TYPE_GOOGLE);
        } else if ("pic".equalsIgnoreCase(this.g)) {
            c.b(str, this.d, LoginManager.SSO_SOCIAL_LOGIN_TYPE_GOOGLE);
        }
        this.g = null;
        this.b.b();
        finish();
    }

    public void a(String str, String str2) {
        com.login.nativesso.g.b a = com.login.nativesso.g.b.a();
        x xVar = (x) com.login.nativesso.b.a.a("SocialLoginCb");
        if ("login".equalsIgnoreCase(this.h)) {
            String str3 = getIntent().getExtras().getBoolean("permissionRequired") ? Arrays.asList(getIntent().getExtras().getStringArray("permission")).contains("user_mobile_phone") ? "true" : InternalLogger.EVENT_PARAM_EXTRAS_FALSE : InternalLogger.EVENT_PARAM_EXTRAS_FALSE;
            c.a(a.a("channel", (Context) this), a.a("siteId", (Context) this), str, str2, true, a.a("TGID", (Context) this), a.a("channel", (Context) this), str3, "", xVar);
        } else if ("link".equalsIgnoreCase(this.h)) {
            c.a(str, str2, LoginManager.SSO_SOCIAL_LOGIN_TYPE_FACEBOOK);
        } else if ("pic".equalsIgnoreCase(this.h)) {
            c.b(str, str2, LoginManager.SSO_SOCIAL_LOGIN_TYPE_FACEBOOK);
        }
        this.h = null;
        finish();
    }

    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    public void onDestroy() {
        super.onDestroy();
        a();
    }

    public void a() {
        this.b = null;
        this.c = null;
        this.e = null;
        this.g = null;
        this.h = null;
    }
}
