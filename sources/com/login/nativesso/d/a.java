package com.login.nativesso.d;

import android.app.Activity;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.CallbackManager.Factory;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.login.nativesso.activity.DummyActivity;
import com.login.nativesso.i.c;
import java.util.Arrays;

public class a {
    private static a d = new a();
    private CallbackManager a;
    private ProfileTracker b;
    private Activity c;
    private String e;

    public static a a() {
        return d;
    }

    private a() {
    }

    public CallbackManager b() {
        return this.a;
    }

    public void a(Activity activity) {
        this.c = activity;
        this.a = Factory.create();
        FacebookSdk.sdkInitialize(activity);
        LoginManager.getInstance().registerCallback(this.a, new FacebookCallback<LoginResult>() {
            public void onCancel() {
            }

            /* renamed from: a */
            public void onSuccess(LoginResult loginResult) {
                Profile currentProfile = Profile.getCurrentProfile();
                a.this.b = new ProfileTracker() {
                    /* Access modifiers changed, original: protected */
                    public void onCurrentProfileChanged(Profile profile, Profile profile2) {
                        a.this.b.startTracking();
                        a.this.a(profile2);
                    }
                };
                a.this.a(currentProfile);
            }

            public void onError(FacebookException facebookException) {
                ThrowableExtension.printStackTrace(facebookException);
                AccessToken.setCurrentAccessToken(null);
                c.c(a.this.e);
                a.this.e = null;
                if (a.this.c != null) {
                    a.this.c.finish();
                }
            }
        });
    }

    public void a(String str) {
        this.e = str;
        LoginManager.getInstance().logInWithReadPermissions(this.c, Arrays.asList(new String[]{"public_profile", "email"}));
    }

    public void a(String str, String[] strArr) {
        this.e = str;
        LoginManager.getInstance().logInWithReadPermissions(this.c, Arrays.asList(strArr));
    }

    private void a(Profile profile) {
        if (profile != null) {
            String token = AccessToken.getCurrentAccessToken().getToken();
            String id = profile.getId();
            if (this.c != null) {
                ((DummyActivity) this.c).a(token, id);
                this.c = null;
            }
        }
    }

    public static void c() {
        if (FacebookSdk.isInitialized()) {
            LoginManager.getInstance().logOut();
        }
    }
}
