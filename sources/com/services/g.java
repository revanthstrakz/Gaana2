package com.services;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import com.facebook.AccessToken;
import com.facebook.AccessToken.AccessTokenRefreshCallback;
import com.facebook.CallbackManager;
import com.facebook.CallbackManager.Factory;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphRequest.GraphJSONObjectCallback;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.share.Sharer.Result;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;
import com.facebook.share.widget.ShareDialog.Mode;
import com.gaana.R;
import com.gaana.login.LoginInfo;
import com.gaana.login.LoginManager.LOGIN_STATUS;
import com.gaana.models.BusinessObject;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.library.managers.TaskManager.TaskListner;
import com.managers.aj;
import com.managers.an;
import com.managers.ap;
import com.managers.u;
import com.services.l.af;
import com.services.l.s;
import com.utilities.Util;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

public class g {
    public static boolean a;
    public static int b;
    private static final List<String> c = Arrays.asList(new String[]{"publish_actions"});
    private static g d;
    private a e;
    private Bundle f = new Bundle();
    private List<String> g = new ArrayList();
    private boolean h = false;
    private boolean i = false;
    private boolean j = false;
    private String k = "";
    private String l = "";
    private String m = "";
    private String n = "";
    private String o = "";
    private String p = "";
    private CallbackManager q;
    private s r;
    private String s = "email";

    public interface a {
        void OnAuthorizationFailed(GraphResponse graphResponse, LOGIN_STATUS login_status);

        String OnAuthrizationSuccess();
    }

    public g() {
        this.g.add("public_profile");
        this.g.add("email");
        this.g.add("user_birthday");
    }

    public static g a() {
        if (d == null) {
            d = new g();
        }
        return d;
    }

    public static void a(final Context context, Mode mode, ShareLinkContent shareLinkContent) {
        if (AccessToken.getCurrentAccessToken() != null) {
            ShareDialog shareDialog = new ShareDialog((Activity) context);
            shareDialog.registerCallback(Factory.create(), new FacebookCallback<Result>() {
                public void onCancel() {
                }

                public void onError(FacebookException facebookException) {
                }

                /* renamed from: a */
                public void onSuccess(Result result) {
                    aj.a().a(context, context.getString(R.string.story_published));
                }
            });
            shareDialog.show(shareLinkContent);
        }
    }

    private void c(String str) {
        this.k = str;
    }

    public String b() {
        return this.k;
    }

    private void d(String str) {
        this.l = str;
    }

    public String c() {
        return this.l;
    }

    public String d() {
        return this.m;
    }

    private void e(String str) {
        this.m = str;
    }

    public String e() {
        return this.n;
    }

    private void f(String str) {
        this.n = str;
    }

    public String f() {
        return this.o;
    }

    public void a(String str) {
        this.o = str;
    }

    public String g() {
        return this.p;
    }

    public void b(String str) {
        this.p = str;
    }

    public void a(Activity activity, a aVar, final boolean z) {
        if (!a) {
            a = true;
            if (AccessToken.getCurrentAccessToken() != null) {
                LoginManager.getInstance().logOut();
            }
            this.h = false;
            this.j = false;
            this.e = aVar;
            this.q = Factory.create();
            LoginManager.getInstance().registerCallback(this.q, new FacebookCallback<LoginResult>() {
                /* renamed from: a */
                public void onSuccess(LoginResult loginResult) {
                    if (!z || g.this.a(loginResult.getRecentlyGrantedPermissions(), g.this.s)) {
                        an.a().a("click", "ac", "", "LOGIN", "", "FB", "", "");
                        g.this.i();
                        return;
                    }
                    u.a().a("Login", "Code_Msg", "FB - Missing Email - Failure");
                    an.a().a("click", "ac", "", "FB", "FB - Missing Email - Failure", "FAIL", "", "");
                    g.a = false;
                    if (g.this.e != null) {
                        g.this.e.OnAuthorizationFailed(null, LOGIN_STATUS.LOGIN_MANDATORY_FIELD_MISSING);
                    }
                }

                public void onCancel() {
                    g.a = false;
                    if (g.this.e != null) {
                        g.this.e.OnAuthorizationFailed(null, LOGIN_STATUS.LOGIN_ERROR_AUTHENTICATION_FAILED);
                    }
                }

                public void onError(FacebookException facebookException) {
                    g.a = false;
                    if (g.this.e != null) {
                        g.this.e.OnAuthorizationFailed(null, LOGIN_STATUS.LOGIN_ERROR_AUTHENTICATION_FAILED);
                    }
                }
            });
            LoginManager.getInstance().logInWithReadPermissions(activity, this.g);
        }
    }

    public void h() {
        LoginManager.getInstance().logOut();
    }

    public void a(Activity activity, int i, int i2, Intent intent) {
        if (this.q != null) {
            this.q.onActivityResult(i, i2, intent);
        }
    }

    public void a(Activity activity, a aVar) {
        this.h = true;
        this.e = aVar;
    }

    public void a(Activity activity, String str, Context context, a aVar) {
        if (AccessToken.getCurrentAccessToken() != null) {
            b(activity, str, context, aVar);
            return;
        }
        final Activity activity2 = activity;
        final String str2 = str;
        final Context context2 = context;
        final a aVar2 = aVar;
        a(activity, new s() {
            public void onRetreivalComplete(BusinessObject businessObject) {
                g.this.j = false;
                g.this.r = null;
                if (AccessToken.getCurrentAccessToken() != null) {
                    g.this.b(activity2, str2, context2, aVar2);
                } else if (aVar2 != null) {
                    aVar2.OnAuthorizationFailed(null, LOGIN_STATUS.LOGIN_ERROR_AUTHENTICATION_FAILED);
                }
            }

            public void onErrorResponse(BusinessObject businessObject) {
                if (aVar2 != null) {
                    aVar2.OnAuthorizationFailed(null, LOGIN_STATUS.LOGIN_ERROR_AUTHENTICATION_FAILED);
                }
            }
        });
    }

    private void a(Activity activity, final s sVar) {
        this.j = true;
        this.r = sVar;
        this.q = Factory.create();
        LoginManager.getInstance().registerCallback(this.q, new FacebookCallback<LoginResult>() {
            /* renamed from: a */
            public void onSuccess(LoginResult loginResult) {
                sVar.onRetreivalComplete(null);
            }

            public void onCancel() {
                sVar.onErrorResponse(null);
            }

            public void onError(FacebookException facebookException) {
                sVar.onErrorResponse(null);
            }
        });
        LoginManager.getInstance().logInWithReadPermissions(activity, this.g);
    }

    private void b(Activity activity, final String str, Context context, final a aVar) {
        if (AccessToken.getCurrentAccessToken() != null) {
            if (a(c, AccessToken.getCurrentAccessToken().getPermissions())) {
                a(str, aVar);
            } else {
                this.j = true;
                this.q = Factory.create();
                LoginManager.getInstance().registerCallback(this.q, new FacebookCallback<LoginResult>() {
                    /* renamed from: a */
                    public void onSuccess(LoginResult loginResult) {
                        g.this.a(str, aVar);
                    }

                    public void onCancel() {
                        if (aVar != null) {
                            aVar.OnAuthorizationFailed(null, LOGIN_STATUS.LOGIN_ERROR_AUTHENTICATION_FAILED);
                        }
                    }

                    public void onError(FacebookException facebookException) {
                        if (aVar != null) {
                            aVar.OnAuthorizationFailed(null, LOGIN_STATUS.LOGIN_ERROR_AUTHENTICATION_FAILED);
                        }
                    }
                });
                LoginManager.getInstance().logInWithPublishPermissions(activity, c);
            }
        }
    }

    private void a(final String str, final a aVar) {
        h.a().a(new TaskListner() {
            GraphResponse a = null;

            public void onBackGroundTaskCompleted() {
                g.this.a(this.a, aVar);
            }

            public void doBackGroundTask() {
                this.a = g.this.a(aVar, str);
            }
        }, -1);
    }

    private void a(GraphResponse graphResponse, a aVar) {
        if (graphResponse != null) {
            try {
                if (!TextUtils.isEmpty(graphResponse.getJSONObject().optString("id", "")) && aVar != null) {
                    aVar.OnAuthrizationSuccess();
                } else if (aVar != null) {
                    aVar.OnAuthorizationFailed(null, LOGIN_STATUS.LOGIN_ERROR_AUTHENTICATION_FAILED);
                }
            } catch (Exception e) {
                if (aVar != null) {
                    aVar.OnAuthorizationFailed(null, LOGIN_STATUS.LOGIN_ERROR_AUTHENTICATION_FAILED);
                }
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("JSON error ");
                stringBuilder.append(e.toString());
                Log.e("Facebook Login", stringBuilder.toString());
            }
        } else if (aVar != null) {
            aVar.OnAuthorizationFailed(null, LOGIN_STATUS.LOGIN_ERROR_AUTHENTICATION_FAILED);
        }
    }

    private GraphResponse a(a aVar, String str) {
        Bundle bundle = new Bundle();
        String str2 = "me/music.listens";
        if (aVar == null) {
            bundle.putString("song", str);
        } else {
            str2 = "me/feed";
            bundle.putString("message", str);
            bundle.putString("description", str);
        }
        return new GraphRequest(AccessToken.getCurrentAccessToken(), str2, bundle, HttpMethod.POST).executeAndWait();
    }

    private boolean a(Collection<String> collection, Collection<String> collection2) {
        for (String contains : collection) {
            if (!collection2.contains(contains)) {
                return false;
            }
        }
        return true;
    }

    private void a(JSONObject jSONObject) {
        try {
            if (jSONObject.has("email")) {
                c(jSONObject.getString("email"));
            } else {
                c("");
            }
            if (jSONObject.has("name")) {
                d(jSONObject.getString("name"));
            } else {
                d("");
            }
            f(jSONObject.getString("id"));
            if (jSONObject.has("gender")) {
                a(jSONObject.getString("gender"));
            } else {
                a("");
            }
            if (jSONObject.has("birthday")) {
                b(jSONObject.getString("birthday"));
            } else {
                b("");
            }
            e(AccessToken.getCurrentAccessToken().getToken());
        } catch (JSONException unused) {
        }
    }

    private void i() {
        GraphRequest newMeRequest = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(), new GraphJSONObjectCallback() {
            public void onCompleted(JSONObject jSONObject, GraphResponse graphResponse) {
                g.a = false;
                if (jSONObject != null && AccessToken.getCurrentAccessToken() != null) {
                    g.this.a(jSONObject);
                    if (g.this.e != null) {
                        g.this.e.OnAuthrizationSuccess();
                    }
                } else if (g.this.e != null) {
                    g.this.e.OnAuthorizationFailed(graphResponse, LOGIN_STATUS.LOGIN_ERROR_AUTHENTICATION_FAILED);
                }
            }
        });
        Bundle bundle = new Bundle();
        bundle.putString(GraphRequest.FIELDS_PARAM, "email,name,gender,id,birthday");
        newMeRequest.setParameters(bundle);
        newMeRequest.executeAsync();
    }

    public void a(Activity activity, LoginInfo loginInfo, boolean z, af afVar) {
        if (AccessToken.getCurrentAccessToken() != null) {
            final boolean z2 = z;
            final LoginInfo loginInfo2 = loginInfo;
            final af afVar2 = afVar;
            final Activity activity2 = activity;
            AccessToken.refreshCurrentAccessTokenAsync(new AccessTokenRefreshCallback() {
                public void OnTokenRefreshed(AccessToken accessToken) {
                    if (z2) {
                        if (accessToken != null && !loginInfo2.getRealToken().equals(accessToken.getToken())) {
                            JSONObject jSONObject = new JSONObject();
                            try {
                                jSONObject.put("social_user_id", accessToken.getUserId());
                                jSONObject.put("social_token", accessToken.getToken());
                                jSONObject.put("type", com.gaana.login.LoginManager.TAG_SUBTYPE_FB);
                                loginInfo2.setRealToken(accessToken.getToken());
                                loginInfo2.setFbId(accessToken.getUserId());
                                com.gaana.login.LoginManager.getInstance().setLoginInfo(loginInfo2);
                                com.gaana.login.LoginManager.getInstance().updateSocialMeta(jSONObject);
                            } catch (JSONException e) {
                                ThrowableExtension.printStackTrace(e);
                            }
                        } else if (accessToken != null) {
                            d.a().a(System.currentTimeMillis(), "pref_social_token_last_refreshed", false);
                        }
                    } else if (afVar2 == null) {
                    } else {
                        if (accessToken != null) {
                            afVar2.onRetreivalComplete(accessToken.getToken());
                        } else {
                            afVar2.onErrorResponse(null);
                        }
                    }
                }

                public void OnTokenRefreshFailed(FacebookException facebookException) {
                    if (AccessToken.getCurrentAccessToken() == null) {
                        com.gaana.login.LoginManager instance = com.gaana.login.LoginManager.getInstance();
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append("FB TOKEN NULL - ");
                        stringBuilder.append(Util.X());
                        instance.sendUserLogOutEvent(-1, stringBuilder.toString());
                        ap.a().a(activity2, false, null, LOGIN_STATUS.LOGGED_OUT);
                    }
                    if (afVar2 != null) {
                        afVar2.onErrorResponse(null);
                    }
                }
            });
            return;
        }
        com.gaana.login.LoginManager instance = com.gaana.login.LoginManager.getInstance();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("FB TOKEN NULL - ");
        stringBuilder.append(Util.X());
        instance.sendUserLogOutEvent(-1, stringBuilder.toString());
        ap.a().a((Context) activity, false, null, LOGIN_STATUS.LOGGED_OUT);
    }

    public void a(@NonNull LoginInfo loginInfo) {
        AccessToken.setCurrentAccessToken(new AccessToken(loginInfo.getRealToken(), "183019041719404", loginInfo.getFbId(), null, null, null, null, null));
        d.a().a("pref_fb_legacy_token", true, false);
    }

    private boolean a(Set<String> set, String str) {
        for (String equals : set) {
            if (equals.equals(str)) {
                return true;
            }
        }
        return false;
    }
}
