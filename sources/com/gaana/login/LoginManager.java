package com.gaana.login;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import com.android.volley.Request.Priority;
import com.constants.Constants;
import com.dynamicview.DynamicViewManager;
import com.facebook.GraphResponse;
import com.facebook.internal.ServerProtocol;
import com.fragments.GDPRCantUseAppFragment;
import com.gaana.BaseActivity;
import com.gaana.GaanaActivity;
import com.gaana.Login;
import com.gaana.OnBoardLanguagePreferenceActivityNew;
import com.gaana.R;
import com.gaana.ReferralSignupActivity;
import com.gaana.WebViewActivity;
import com.gaana.analytics.AppsFlyer;
import com.gaana.analytics.MoEngage;
import com.gaana.analytics.UninstallIO;
import com.gaana.application.GaanaApplication;
import com.gaana.login.GooglePlusLogin.OnGooglePlusLoginListner;
import com.gaana.models.BusinessObject;
import com.gaana.models.LinkDeviceResponse;
import com.gaana.models.ProfileData;
import com.gaana.models.ReferralSignup;
import com.gaana.models.User.LoginMode;
import com.gaana.models.User.LoginType;
import com.google.android.exoplayer2.C;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.Builder;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.i.e;
import com.i.i;
import com.i.j;
import com.library.managers.TaskManager.TaskListner;
import com.login.nativesso.e.c;
import com.managers.DownloadManager;
import com.managers.PlayerManager;
import com.managers.URLManager;
import com.managers.URLManager.BusinessObjectType;
import com.managers.ag;
import com.managers.ai;
import com.managers.aj;
import com.managers.ak;
import com.managers.an;
import com.managers.ap;
import com.managers.f;
import com.managers.q;
import com.managers.u;
import com.moengage.ActionMapperConstants;
import com.player_framework.GaanaMusicService;
import com.player_framework.o;
import com.services.d;
import com.services.g;
import com.services.g.a;
import com.services.h;
import com.services.l.af;
import com.services.l.au;
import com.services.l.s;
import com.services.l.x;
import com.services.l.y;
import com.services.m;
import com.services.m.b;
import com.services.n;
import com.simpl.android.fingerprint.SimplFingerprint;
import com.simpl.android.fingerprint.SimplFingerprintListener;
import com.utilities.Util;
import in.til.core.integrations.TILSDKExceptionDto;
import java.io.Serializable;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;

public class LoginManager {
    private static final String PREFF_GAANA_USER_INFO = "PREFF_GAANA_USER_INFO";
    public static final int SHOULD_FETCH_MY_PROFILE_DETAILS = 43200000;
    public static final int SOCIAL_ACCESS_TOKEN_RENEW = 172800000;
    public static final String SSO_MIGRATION_TYPE = "migration_type";
    public static final int SSO_RENEW_TICKET_DURATION = 14400000;
    public static final String SSO_SOCIAL_LOGIN_TYPE_FACEBOOK = "facebook";
    public static final String SSO_SOCIAL_LOGIN_TYPE_GOOGLE = "googleplus";
    private static final String TAG = "LoginManager";
    public static final String TAG_DOB = "dob";
    public static final String TAG_EMAIL = "email";
    public static final String TAG_FBID = "fbid";
    public static final String TAG_FB_ACCESS_TOKEN = "fb_access_token";
    public static final String TAG_FB_MANUAL_DATA = "fb_manual_data";
    public static final String TAG_FB_REAL_TOKEN = "fbrealtoken";
    public static final String TAG_FB_TOKEN = "fbtoken";
    public static final String TAG_FULL_NAME = "fullname";
    public static final String TAG_GENDER = "gender";
    protected static final String TAG_GOOGLEID = "googleid";
    protected static final String TAG_GOOGLE_MANUAL_DATA = "gp_manual_data";
    protected static final String TAG_GOOGLE_REAL_TOKEN = "googlerealtoken";
    protected static final String TAG_GOOGLE_TOKEN = "googletoken";
    protected static final String TAG_KEY = "key";
    public static final String TAG_PASSWORD = "password";
    protected static final String TAG_REFERRER_USER_ID = "referrer_user_id";
    protected static final String TAG_REG_TOKEN = "regtoken";
    public static final String TAG_SSO_TICKET_ID = "sso_ticket_id";
    public static final String TAG_SSO_USER_INFO = "sso_user_info";
    public static final String TAG_SUBTYPE = "subtype";
    public static final String TAG_SUBTYPE_EMAIL = "email";
    public static final String TAG_SUBTYPE_FB = "fb";
    public static final String TAG_SUBTYPE_GAANA = "gaana";
    public static final String TAG_SUBTYPE_GOOGLE = "google";
    public static final String TAG_SUBTYPE_PHONE_LOGIN = "phone_login";
    public static final String TAG_SUBTYPE_SSO = "sso_login";
    public static final String TAG_TYPE = "type";
    public static final String TAG_TYPE_VALUE = "nxtgen_authenticate";
    public static final String TAG_USER_NAME = "username";
    public static boolean isSimplInitialized = false;
    private static LoginManager mLoginManager = null;
    private static boolean userStatusUpdateInProgress = false;
    private final int SSO_SDK_INITIALIZED = 1;
    private final int SSO_SDK_INITIALIZING = 2;
    private final int SSO_SDK_NOT_INITIALIZED = 0;
    private LoginMode defaultLoginMode = LoginMode.SSO;
    private GooglePlusLogin googleLogin = null;
    private long initialTime = 0;
    private boolean isFromLoginonUpgrade = false;
    private boolean isLoginInProcess = false;
    private boolean isMyProfileRefreshing = false;
    private boolean isSilentLogin = false;
    private LoginType loginType;
    private Reference<Activity> mActivityReference;
    private GaanaApplication mAppState;
    private d mDeviceResourceManager;
    private g mFacebookLogin = null;
    a mIFacebookLogin = new a() {
        public String OnAuthrizationSuccess() {
            if (LoginManager.this.mFacebookLogin.e().trim().length() > 2) {
                LoginManager.this.showProgressDialog(false, "Updating user details...");
                d.a().a("PREFERENCE_KEY_POST_TO_FACEBOOK", true, false);
                LoginInfo loginInfo = new LoginInfo();
                loginInfo.setLoginType(LoginType.FB);
                loginInfo.setEmailId(LoginManager.this.mFacebookLogin.b());
                loginInfo.setFbId(LoginManager.this.mFacebookLogin.e());
                loginInfo.setRealToken(LoginManager.this.mFacebookLogin.d());
                loginInfo.setFullname(LoginManager.this.mFacebookLogin.c());
                loginInfo.setDob(LoginManager.this.mFacebookLogin.g());
                loginInfo.setSex(LoginManager.this.mFacebookLogin.f());
                loginInfo.setLoginMode(LoginManager.this.defaultLoginMode);
                Activity activity = (Activity) LoginManager.this.mActivityReference.get();
                if (!TextUtils.isEmpty(LoginManager.this.mFacebookLogin.b())) {
                    GooglePlusLogin.getInstance().buildGoogleClient(null);
                    GooglePlusLogin.getInstance().onFacebookClicked(LoginManager.this.mFacebookLogin.b(), LoginManager.this.mFacebookLogin.c(), activity);
                }
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(LoginManager.this.mFacebookLogin.c());
                stringBuilder.append("##");
                stringBuilder.append(LoginManager.this.mFacebookLogin.b());
                stringBuilder.append("##");
                stringBuilder.append(LoginManager.this.mFacebookLogin.d());
                stringBuilder.append("##");
                stringBuilder.append(LoginManager.this.mFacebookLogin.e());
                String stringBuilder2 = stringBuilder.toString();
                an.a().d("s2s", "ac", "", "FB", stringBuilder2, "SUCCESS", "", "");
                LoginManager.this.showProgressDialog(false, GaanaApplication.getContext().getString(R.string.logging_in));
                LoginManager.this.getLoginClient(LoginType.FB).login(loginInfo, LoginManager.this.mOnLoginCompleted);
                return null;
            }
            h.a().a(new TaskListner() {
                public void onBackGroundTaskCompleted() {
                    LoginManager.this.loginWithFacebook();
                }

                public void doBackGroundTask() {
                    g.a().h();
                }
            }, -1);
            return null;
        }

        public void OnAuthorizationFailed(GraphResponse graphResponse, LOGIN_STATUS login_status) {
            LoginManager.this.loginCompleted(login_status, LoginManager.this.mUserInfo, null);
            if (graphResponse != null) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(graphResponse.getError());
                stringBuilder.append(" - Failure");
                u.a().a("Login", "Code_Msg", stringBuilder.toString());
                an.a().d("s2s", "ac", "", "FB", "FAIL", graphResponse.getError() != null ? graphResponse.getError().toString() : "fb error", "", "");
            }
        }
    };
    private LoginClient mLoginClient;
    x mOnDeviceLinkedListener = new x() {
        public void onDeviceLinkingSuccessful(LinkDeviceResponse linkDeviceResponse) {
            if (LoginManager.this.mUserInfo.getUserSubscriptionData() != null) {
                LoginManager.this.mUserInfo.getUserSubscriptionData().setDevicelinked(true);
                String validUpTo = LoginManager.this.mUserInfo.getUserSubscriptionData().getValidUpTo();
                if (!TextUtils.isEmpty(validUpTo)) {
                    LoginManager.this.mUserInfo.getUserSubscriptionData().setExpiryDate(new Date(Long.parseLong(validUpTo) * 1000));
                }
                validUpTo = LoginManager.this.mUserInfo.getUserSubscriptionData().getValidWithGrace();
                if (!TextUtils.isEmpty(validUpTo)) {
                    LoginManager.this.mUserInfo.getUserSubscriptionData().setExpiryDateWithGrace(new Date(Long.parseLong(validUpTo) * 1000));
                }
                LoginManager.this.mUserInfo.getUserSubscriptionData().updateAccountType();
                LoginManager.this.mUserInfo.getUserSubscriptionData().setProductProperties(linkDeviceResponse.getUserSubscriptionData().getProductProperties());
                LoginManager.this.saveUserInfoInSharedPreff();
                Activity activity = (Activity) LoginManager.this.mActivityReference.get();
                if (!(activity == null || !(activity instanceof GaanaActivity) || activity.isFinishing())) {
                    ((GaanaActivity) activity).updateSideBar();
                }
                if (LoginManager.this.mOnUserStatusUpdatedListener != null) {
                    LoginManager.this.mOnUserStatusUpdatedListener.onUserStatusUpdated();
                }
                LoginManager.this.loginCompleted(LOGIN_STATUS.LOGIN_SUCCEDED, LoginManager.this.mUserInfo, null);
            }
        }

        public void onDeviceLinkingFailed(boolean z) {
            LoginManager.this.mAppState.getCurrentUser().getUserSubscriptionData().setAccountType(1);
            LoginManager.this.mUserInfo.getUserSubscriptionData().setDevicelinked(false);
            LoginManager.getInstance().saveUserInfoInSharedPreff();
            ap.a().a(GaanaApplication.getContext());
            if (z) {
                LoginManager.this.showToast(LoginManager.this.mAppState.getResources().getString(R.string.error_message_device_linking_failed));
            }
            if (LoginManager.this.mOnUserStatusUpdatedListener != null) {
                LoginManager.this.mOnUserStatusUpdatedListener.onUserStatusUpdated();
            }
            LoginManager.this.loginCompleted(LOGIN_STATUS.LOGIN_SUCCEDED, LoginManager.this.mUserInfo, null);
        }
    };
    private IOnLoginCompleted mOnLoginCompleted;
    private au mOnUserStatusUpdatedListener;
    private m mPhoneLogin;
    b mPhoneLoginListner = new b() {
        public void onPhoneLoginSuccess(String str) {
            LoginInfo loginInfo = new LoginInfo();
            loginInfo.setLoginType(LoginType.PHONENUMBER);
            loginInfo.setFbPhoneLoginAccessToken(str);
            loginInfo.setLoginMode(LoginMode.GAANA);
            LoginManager.this.showProgressDialog(false, GaanaApplication.getContext().getString(R.string.logging_in));
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("##");
            stringBuilder.append(str);
            String stringBuilder2 = stringBuilder.toString();
            an.a().d("s2s", "ac", stringBuilder2, "PHONENUMBER", stringBuilder2, "SUCCESS", "", "");
            LoginManager.this.getLoginClient(LoginType.PHONENUMBER).login(loginInfo, LoginManager.this.mOnLoginCompleted);
        }

        public void onPhoneLoginFailed(String str) {
            LoginManager.this.loginCompleted(LOGIN_STATUS.LOGIN_ERROR_AUTHENTICATION_FAILED, LoginManager.this.mUserInfo, null);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(str);
            stringBuilder.append(" - Failure");
            u.a().a("Login", "Code_Msg", stringBuilder.toString());
            an.a().d("s2s", "ac", LoginManager.this.mUserInfo.toString(), "PHONENUMBER", str, "FAIL", "", "");
        }

        public void onPhoneLoginCancel(String str) {
            an.a().d("s2s", "ac", LoginManager.this.mUserInfo.toString(), "PHONENUMBER", str, "FAIL", "", "");
            LoginManager.this.loginCompleted(LOGIN_STATUS.LOGIN_ERROR_AUTHENTICATION_FAILED, LoginManager.this.mUserInfo, null);
        }
    };
    private int mSsoSDKState = 0;
    private UserInfo mUserInfo = new UserInfo();
    OnGooglePlusLoginListner onGooglePlusLoginListner = new OnGooglePlusLoginListner() {
        public void onLoginSuccess(com.models.b bVar) {
            LoginInfo loginInfo = new LoginInfo();
            loginInfo.setLoginType(LoginType.GOOGLE);
            loginInfo.setEmailId(bVar.a());
            loginInfo.setGoogleId(bVar.f());
            loginInfo.setRealToken(bVar.e());
            loginInfo.setFullname(bVar.b());
            loginInfo.setDob(bVar.d());
            loginInfo.setSex(bVar.c());
            loginInfo.setImgUrl(bVar.g());
            loginInfo.setLoginMode(LoginManager.this.defaultLoginMode);
            LoginManager.this.showProgressDialog(false, GaanaApplication.getContext().getString(R.string.logging_in));
            LoginManager.this.getLoginClient(LoginType.GOOGLE).login(loginInfo, LoginManager.this.mOnLoginCompleted);
        }

        public void onLoginFailed(String str) {
            LoginManager.this.loginCompleted(LOGIN_STATUS.LOGIN_ERROR_AUTHENTICATION_FAILED, null, null);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(str);
            stringBuilder.append(" - Failure");
            u.a().a("Login", "Code_Msg", stringBuilder.toString());
            an.a().a("click", "ac", "", "", str, "FAIL", "", "");
        }
    };
    private ak timesPointLogger;

    public interface IOnLoginCompleted {
        void onLoginCompleted(LOGIN_STATUS login_status, UserInfo userInfo, Bundle bundle);
    }

    public enum LOGIN_STATUS {
        LOGIN_SUCCEDED,
        NOT_LOGGEDIN,
        LOGGED_OUT,
        LOGIN_ERROR_AUTHENTICATION_FAILED,
        LOGIN_ERROR_LAUNCH_TRAP_PAGE,
        LOGIN_ERROR_NETWORK,
        LOGIN_ERROR_UNKNOWN,
        LOGIN_REGISTRATION_FAILED,
        LOGIN_FAILURE_SSO,
        LOGIN_FAILURE_SDK_NOT_INITIALIZED,
        LOGIN_REGISTRATION_VERIFY,
        ALREADY_REGISTERED_USER,
        LOGIN_VERIFY_USER,
        LOGIN_MANDATORY_FIELD_MISSING,
        LOGIN_EMAIL_MISSING_FB,
        LAUNCH_GDPR_DELETE_PROGRESS
    }

    public interface SsoSdkInitialized {
        void onError();

        void onSuccess();
    }

    private static class GetGooglePlusAccessToken extends AsyncTask<Void, Void, String> implements ConnectionCallbacks, OnConnectionFailedListener {
        private boolean isUpdateSocialMetaCall;
        private GoogleApiClient mGoogleApiClient;
        LoginInfo mLoginInfo;

        public void onConnectionSuspended(int i) {
        }

        /* Access modifiers changed, original: protected */
        public void onPostExecute(String str) {
        }

        public GetGooglePlusAccessToken(Activity activity, LoginInfo loginInfo, boolean z) {
            this.mLoginInfo = loginInfo;
            this.isUpdateSocialMetaCall = z;
        }

        /* Access modifiers changed, original: protected|varargs */
        public String doInBackground(Void... voidArr) {
            this.mGoogleApiClient = new Builder(GaanaApplication.getContext()).addConnectionCallbacks(this).addApi(Auth.CREDENTIALS_API).addApi(Auth.GOOGLE_SIGN_IN_API, new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().requestIdToken(Constants.bD).requestProfile().build()).build();
            this.mGoogleApiClient.connect();
            return "";
        }

        public void handleResult(String str) {
            if (!str.startsWith("error -")) {
                this.mLoginInfo.setRealToken(str);
                LoginManager.getInstance().setLoginInfo(this.mLoginInfo);
                if (this.isUpdateSocialMetaCall) {
                    JSONObject jSONObject = new JSONObject();
                    try {
                        jSONObject.put("social_user_id", this.mLoginInfo.getGoogleID());
                        jSONObject.put("social_token", str);
                        jSONObject.put("type", LoginManager.TAG_SUBTYPE_GOOGLE);
                        LoginManager.getInstance().updateSocialMeta(jSONObject);
                        return;
                    } catch (JSONException e) {
                        ThrowableExtension.printStackTrace(e);
                        return;
                    }
                }
                LoginManager.getInstance().getLoginClient(LoginType.GOOGLE).loginSilently(this.mLoginInfo, LoginManager.getInstance().getLoginCompletedListener());
            } else if (!this.isUpdateSocialMetaCall) {
                LoginManager.getInstance().loginCompleted(LOGIN_STATUS.LOGIN_ERROR_UNKNOWN, GaanaApplication.getInstance().getCurrentUser(), null);
            }
        }

        public void onConnected(@Nullable Bundle bundle) {
            OptionalPendingResult silentSignIn = Auth.GoogleSignInApi.silentSignIn(this.mGoogleApiClient);
            if (silentSignIn.isDone()) {
                GoogleSignInResult googleSignInResult = (GoogleSignInResult) silentSignIn.get();
                if (googleSignInResult.isSuccess() && googleSignInResult.getSignInAccount() != null) {
                    handleResult(googleSignInResult.getSignInAccount().getIdToken());
                    return;
                } else if (!this.isUpdateSocialMetaCall) {
                    LoginManager.getInstance().loginCompleted(LOGIN_STATUS.LOGIN_ERROR_UNKNOWN, GaanaApplication.getInstance().getCurrentUser(), null);
                    return;
                } else {
                    return;
                }
            }
            silentSignIn.setResultCallback(new ResultCallback<GoogleSignInResult>() {
                public void onResult(GoogleSignInResult googleSignInResult) {
                    if (googleSignInResult.isSuccess() && googleSignInResult.getSignInAccount() != null) {
                        GetGooglePlusAccessToken.this.handleResult(googleSignInResult.getSignInAccount().getIdToken());
                    } else if (!GetGooglePlusAccessToken.this.isUpdateSocialMetaCall) {
                        LoginManager.getInstance().loginCompleted(LOGIN_STATUS.LOGIN_ERROR_UNKNOWN, GaanaApplication.getInstance().getCurrentUser(), null);
                    }
                }
            });
        }

        public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
            if (!this.isUpdateSocialMetaCall) {
                LoginManager.getInstance().loginCompleted(LOGIN_STATUS.LOGIN_ERROR_UNKNOWN, GaanaApplication.getInstance().getCurrentUser(), null);
            }
        }
    }

    public LoginClient getLoginClient(LoginType loginType) {
        if (this.mLoginClient == null || this.mLoginClient.getLoginType() != loginType) {
            switch (loginType) {
                case GAANA:
                    this.mLoginClient = new LoginImplEmail();
                    break;
                case FB:
                    this.mLoginClient = new LoginImplFb();
                    break;
                case GOOGLE:
                    this.mLoginClient = new LoginImplGoogle();
                    break;
                case PHONENUMBER:
                    this.mLoginClient = new LoginImplPhoneNumber();
                    break;
            }
        }
        return this.mLoginClient;
    }

    private LoginManager() {
        init();
    }

    public void setLoginInProcess(boolean z) {
        this.isLoginInProcess = z;
    }

    public void setSilentLoginStatus(boolean z) {
        this.isSilentLogin = z;
    }

    public static LoginManager getInstance() {
        if (mLoginManager == null) {
            mLoginManager = new LoginManager();
        }
        return mLoginManager;
    }

    private void init() {
        this.mAppState = GaanaApplication.getInstance();
        this.mDeviceResourceManager = d.a();
        String c = this.mDeviceResourceManager.c(PREFF_GAANA_USER_INFO, false);
        if (!TextUtils.isEmpty(c)) {
            this.mUserInfo = (UserInfo) n.a(c);
            if (this.mUserInfo != null && this.mUserInfo.getLoginType() != LoginType.PHONENUMBER) {
                GooglePlusLogin.getInstance().buildGoogleClient(null);
            }
        }
    }

    public UserInfo getUserInfo() {
        return this.mUserInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.mUserInfo = userInfo;
    }

    public void setLoginInfo(LoginInfo loginInfo) {
        this.mDeviceResourceManager.a("PREFF_GAANA_LOGIN_INFO", n.a((Serializable) loginInfo), false);
    }

    public LoginInfo getLoginInfo() {
        return (LoginInfo) n.a(this.mDeviceResourceManager.c("PREFF_GAANA_LOGIN_INFO", false));
    }

    public void loginSilently(final Activity activity, final IOnLoginCompleted iOnLoginCompleted, boolean z) {
        if (!this.isLoginInProcess && !this.isSilentLogin) {
            if (activity != null) {
                this.mActivityReference = new WeakReference(activity);
            }
            if (this.mActivityReference == null) {
                this.mActivityReference = new WeakReference(null);
            }
            if (hasTokenExpired() || z) {
                this.isSilentLogin = true;
                this.mOnLoginCompleted = iOnLoginCompleted;
                final LoginInfo loginInfo = getLoginInfo();
                if (loginInfo.getLoginType() == LoginType.GOOGLE) {
                    if (iOnLoginCompleted != null) {
                        new GetGooglePlusAccessToken(activity, loginInfo, false).execute(new Void[0]);
                    } else {
                        this.isSilentLogin = false;
                        this.mUserInfo = new UserInfo();
                        this.mDeviceResourceManager.b("PREFF_GAANA_LOGIN_INFO", false);
                        this.mDeviceResourceManager.b(PREFF_GAANA_USER_INFO, false);
                        if (this.mOnLoginCompleted != null) {
                            this.mOnLoginCompleted.onLoginCompleted(LOGIN_STATUS.LOGGED_OUT, this.mUserInfo, null);
                        }
                    }
                } else if (loginInfo.getLoginType() == LoginType.FB) {
                    g.a().a(activity, loginInfo, false, new af() {
                        public void onRetreivalComplete(Object obj) {
                            loginInfo.setRealToken((String) obj);
                            LoginManager.this.setLoginInfo(loginInfo);
                            LoginManager.this.getLoginClient(loginInfo.getLoginType()).loginSilently(loginInfo, iOnLoginCompleted);
                        }

                        public void onErrorResponse(BusinessObject businessObject) {
                            LoginManager.this.isSilentLogin = false;
                            if (LoginManager.this.mOnLoginCompleted != null) {
                                LoginManager.this.mOnLoginCompleted.onLoginCompleted(LOGIN_STATUS.LOGIN_ERROR_UNKNOWN, LoginManager.this.mUserInfo, null);
                            }
                        }
                    });
                } else {
                    getLoginClient(loginInfo.getLoginType()).loginSilently(loginInfo, iOnLoginCompleted);
                }
            } else {
                if (!userStatusUpdateInProgress) {
                    getUserStatus(activity, new au() {
                        public void onUserStatusUpdated() {
                            if (!Constants.bT) {
                                LoginManager.this.clearSimplSession();
                            } else if (!(LoginManager.isSimplInitialized || GaanaApplication.getInstance().getCurrentUser().getUserProfile() == null || TextUtils.isEmpty(GaanaApplication.getInstance().getCurrentUser().getUserProfile().getPhoneNumber()))) {
                                if (TextUtils.isEmpty(Constants.bM)) {
                                    LoginManager.isSimplInitialized = true;
                                    ag.a(activity).c();
                                } else {
                                    SimplFingerprint.init(activity, GaanaApplication.getInstance().getCurrentUser().getUserProfile().getPhoneNumber(), GaanaApplication.getInstance().getCurrentUser().getUserProfile().getEmail());
                                    SimplFingerprint.getInstance().generateFingerprint(new SimplFingerprintListener() {
                                        public void fingerprintData(String str) {
                                            LoginManager.isSimplInitialized = true;
                                            Constants.bO = str;
                                            Constants.bN = true;
                                            ag.a(activity).c(activity);
                                        }
                                    });
                                }
                            }
                            iOnLoginCompleted.onLoginCompleted(LOGIN_STATUS.LOGIN_SUCCEDED, LoginManager.this.mUserInfo, null);
                        }
                    }, false);
                }
                if (shoudlFetchMyProfile()) {
                    refreshMyProfile(new s() {
                        public void onRetreivalComplete(BusinessObject businessObject) {
                            if (iOnLoginCompleted != null) {
                                iOnLoginCompleted.onLoginCompleted(LOGIN_STATUS.LOGIN_SUCCEDED, LoginManager.this.mUserInfo, null);
                            }
                        }

                        public void onErrorResponse(BusinessObject businessObject) {
                            if (iOnLoginCompleted != null) {
                                iOnLoginCompleted.onLoginCompleted(LOGIN_STATUS.LOGIN_SUCCEDED, LoginManager.this.mUserInfo, null);
                            }
                        }
                    });
                } else if (this.mUserInfo.getLoginStatus()) {
                    if (iOnLoginCompleted != null) {
                        iOnLoginCompleted.onLoginCompleted(LOGIN_STATUS.LOGIN_SUCCEDED, this.mUserInfo, null);
                    }
                } else if (iOnLoginCompleted != null) {
                    iOnLoginCompleted.onLoginCompleted(LOGIN_STATUS.NOT_LOGGEDIN, this.mUserInfo, null);
                }
            }
        }
    }

    public void refreshGaanaPlusStatus(Activity activity, final IOnLoginCompleted iOnLoginCompleted) {
        if (shouldCheckGaanaPlusStatus()) {
            getUserStatus(activity, new au() {
                public void onUserStatusUpdated() {
                    iOnLoginCompleted.onLoginCompleted(LOGIN_STATUS.LOGIN_SUCCEDED, LoginManager.this.mUserInfo, null);
                }
            }, true);
        }
    }

    public void updateSsoTicketAndSocialToken(Activity activity, IOnLoginCompleted iOnLoginCompleted) {
        if (!this.isSilentLogin) {
            LoginInfo loginInfo;
            if (shouldUpdateSocialToken()) {
                loginInfo = getLoginInfo();
                if (loginInfo.getLoginType() == LoginType.FB) {
                    g.a().a(activity, loginInfo, true, null);
                } else if (loginInfo.getLoginType() == LoginType.GOOGLE && activity != null) {
                    new GetGooglePlusAccessToken(activity, loginInfo, true).execute(new Void[0]);
                }
            } else if (shouldRenewSSOToken()) {
                loginInfo = getLoginInfo();
                getLoginClient(loginInfo.getLoginType()).checkSSOValidity(activity, loginInfo);
            }
        }
    }

    public void checkAndMigrateToSSO(boolean z) {
        this.defaultLoginMode = z ? LoginMode.SSO : LoginMode.GAANA;
        if (Util.j(GaanaApplication.getContext()) && this.mUserInfo.getLoginStatus()) {
            LoginInfo loginInfo = getLoginInfo();
            if (z && loginInfo.getLoginType() != LoginType.PHONENUMBER && loginInfo.getLoginMode() == LoginMode.GAANA) {
                migrateUserToSso(loginInfo, (Activity) ai.a());
            } else if (!z && loginInfo.getLoginType() != LoginType.PHONENUMBER && loginInfo.getLoginMode() != LoginMode.GAANA) {
                getLoginClient(loginInfo.getLoginType()).signOutFromSso();
                loginInfo.setLoginMode(LoginMode.GAANA);
                setLoginInfo(loginInfo);
            }
        }
    }

    private IOnLoginCompleted getLoginCompletedListener() {
        return this.mOnLoginCompleted;
    }

    public void loginOnUpgrade(Activity activity) {
        this.isSilentLogin = true;
        this.isFromLoginonUpgrade = true;
        login(activity, getLoginInfo(), null);
    }

    public void login(Activity activity, LoginType loginType, IOnLoginCompleted iOnLoginCompleted) {
        if (!this.isLoginInProcess) {
            this.mOnLoginCompleted = iOnLoginCompleted;
            this.mActivityReference = new WeakReference(activity);
            this.loginType = loginType;
            this.isLoginInProcess = true;
            if (loginType == LoginType.FB) {
                loginWithFacebook();
            } else if (loginType == LoginType.GOOGLE) {
                loginWithGoogle();
            } else if (loginType == LoginType.PHONENUMBER) {
                loginWithPhoneNumber();
            }
        }
    }

    public void setmOnLoginCompleted(IOnLoginCompleted iOnLoginCompleted) {
        this.mOnLoginCompleted = iOnLoginCompleted;
    }

    public void removeOnLoginCompleted() {
        this.mOnLoginCompleted = null;
    }

    public void setmActivityReference(Activity activity) {
        this.mActivityReference = new WeakReference(activity);
    }

    public void login(Activity activity, LoginInfo loginInfo, IOnLoginCompleted iOnLoginCompleted) {
        if (!this.isLoginInProcess) {
            this.mOnLoginCompleted = iOnLoginCompleted;
            this.mActivityReference = new WeakReference(activity);
            this.loginType = loginInfo.getLoginType();
            this.isLoginInProcess = true;
            showProgressDialog(false, GaanaApplication.getContext().getString(R.string.logging_in));
            getLoginClient(loginInfo.getLoginType()).login(loginInfo, iOnLoginCompleted);
        }
    }

    public void register(Activity activity, LoginInfo loginInfo, IOnLoginCompleted iOnLoginCompleted) {
        this.initialTime = Calendar.getInstance().getTimeInMillis();
        this.isLoginInProcess = true;
        this.mOnLoginCompleted = iOnLoginCompleted;
        this.mActivityReference = new WeakReference(activity);
        this.loginType = loginInfo.getLoginType();
        showProgressDialog(false, activity.getString(R.string.registering));
        getLoginClient(loginInfo.getLoginType()).register(loginInfo, iOnLoginCompleted);
    }

    public void logout(Activity activity, IOnLoginCompleted iOnLoginCompleted) {
        if (this.mUserInfo.getLoginStatus()) {
            this.mOnLoginCompleted = iOnLoginCompleted;
            this.mActivityReference = new WeakReference(activity);
            LoginInfo loginInfo = (LoginInfo) n.a(this.mDeviceResourceManager.c("PREFF_GAANA_LOGIN_INFO", false));
            LoginType loginType = loginInfo.getLoginType();
            if ((this.mAppState.getCurrentUser() == null || this.mAppState.getCurrentUser().getUserSubscriptionData().getAccountType() != 2) && this.mAppState.getCurrentUser().getUserSubscriptionData().getAccountType() != 3) {
                d.a().a(Constants.af, 0, false);
            } else {
                d.a().a(Constants.af, 1, false);
            }
            this.mUserInfo = new UserInfo();
            this.mDeviceResourceManager.b("PREFF_GAANA_LOGIN_INFO", false);
            this.mDeviceResourceManager.b(PREFF_GAANA_USER_INFO, false);
            d.a().a("PREFERENCE_CURATED_DIALOG_SHOWN", false, true);
            d.a().a("PREFERENCE_SESSION_TRIAL_COUNT", 0, true);
            d.a().a("PREFERENCE_CURATED_DIALOG_CLOSED", false, true);
            d.a().b("pref_social_token_last_refreshed", false);
            d.a().b("pref_sso_last_refresh_time", false);
            GooglePlusLogin.getInstance().resetCredentials();
            getLoginClient(loginType).logout(loginInfo);
            loginCompleted(LOGIN_STATUS.LOGGED_OUT, this.mUserInfo, null);
            return;
        }
        loginCompleted(LOGIN_STATUS.NOT_LOGGEDIN, this.mUserInfo, null);
    }

    private void checkAndRefreshSubscription(Activity activity, final IOnLoginCompleted iOnLoginCompleted) {
        this.mActivityReference = new WeakReference(activity);
        if (this.mAppState.getCurrentUser().getUserSubscriptionData().getLastUpdateTime() == -1) {
            getUserStatus(activity, new au() {
                public void onUserStatusUpdated() {
                    LoginManager.this.mOnLoginCompleted = iOnLoginCompleted;
                    LoginManager.this.loginCompleted(LOGIN_STATUS.LOGIN_SUCCEDED, LoginManager.this.mUserInfo, null);
                }
            }, true);
        } else if (((double) (new Date().getTime() - this.mAppState.getCurrentUser().getUserSubscriptionData().getLastUpdateTime())) / 3600000.0d >= 12.0d) {
            getUserStatus(activity, new au() {
                public void onUserStatusUpdated() {
                    LoginManager.this.mOnLoginCompleted = iOnLoginCompleted;
                    LoginManager.this.loginCompleted(LOGIN_STATUS.LOGIN_SUCCEDED, LoginManager.this.mUserInfo, null);
                }
            }, true);
        } else {
            this.mOnLoginCompleted = iOnLoginCompleted;
            loginCompleted(LOGIN_STATUS.LOGIN_SUCCEDED, this.mUserInfo, null);
        }
    }

    /* Access modifiers changed, original: protected */
    public void loginWithFacebook() {
        Activity activity = (Activity) this.mActivityReference.get();
        this.mFacebookLogin = g.a();
        if (activity != null) {
            this.mFacebookLogin.a(activity, this.mIFacebookLogin, true);
        }
    }

    private void loginWithGoogle() {
        this.googleLogin = GooglePlusLogin.getInstance();
        Activity activity = (Activity) this.mActivityReference.get();
        if (activity != null) {
            this.googleLogin.login(activity, this.onGooglePlusLoginListner);
        }
    }

    private void loginWithPhoneNumber() {
        Activity activity = (Activity) this.mActivityReference.get();
        this.mPhoneLogin = m.a();
        if (activity != null) {
            this.mPhoneLogin.a(activity, this.mPhoneLoginListner, false);
        }
    }

    public void loginToGaana(final LoginType loginType, String str, LoginInfo loginInfo) {
        final HashMap loginParams = getLoginClient(loginType).getLoginParams(str, loginInfo);
        if (!this.isSilentLogin) {
            this.mDeviceResourceManager.a("PREFF_GAANA_LOGIN_INFO", n.a((Serializable) loginInfo), false);
            Object c = com.managers.d.a().c();
            if (c == null) {
                c = "";
            }
            loginParams.put(TAG_REFERRER_USER_ID, c);
        }
        this.initialTime = Calendar.getInstance().getTimeInMillis();
        showProgressDialog(false, this.mAppState.getString(R.string.logging_in));
        URLManager uRLManager = new URLManager();
        uRLManager.a("https://api.gaana.com/user.php?");
        uRLManager.i(false);
        uRLManager.a(loginParams);
        uRLManager.b(Boolean.valueOf(false));
        uRLManager.a(Priority.IMMEDIATE);
        uRLManager.a(String.class);
        uRLManager.c(1);
        i.a().a(new af() {
            /* JADX WARNING: Removed duplicated region for block: B:72:0x02f9  */
            /* JADX WARNING: Removed duplicated region for block: B:10:0x0034  */
            public void onRetreivalComplete(java.lang.Object r18) {
                /*
                r17 = this;
                r0 = r17;
                r1 = r18;
                r1 = (java.lang.String) r1;
                r2 = "";
                r3 = 0;
                if (r1 == 0) goto L_0x002b;
            L_0x000b:
                r2 = com.utilities.Util.j(r1);
                r1 = new com.google.gson.Gson;
                r1.<init>();
                if (r2 == 0) goto L_0x002b;
            L_0x0016:
                r4 = com.gaana.login.LoginManager.this;
                r4 = r4.isJSONValid(r2);
                if (r4 == 0) goto L_0x002b;
            L_0x001e:
                r4 = r2.trim();
                r5 = com.gaana.login.UserInfo.class;
                r1 = r1.fromJson(r4, r5);
                r1 = (com.gaana.login.UserInfo) r1;
                goto L_0x002c;
            L_0x002b:
                r1 = r3;
            L_0x002c:
                r4 = com.gaana.login.LoginManager.this;
                r4 = r4.getLoginInfo();
                if (r1 == 0) goto L_0x02f9;
            L_0x0034:
                r5 = com.gaana.login.LoginManager.this;
                r5.mUserInfo = r1;
                r5 = r1.getPromo();
                r5 = android.text.TextUtils.isEmpty(r5);
                if (r5 != 0) goto L_0x0068;
            L_0x0043:
                r5 = com.gaana.login.LoginManager.this;
                r5 = r5.mUserInfo;
                r5 = r5.getPromo();
                r6 = "1";
                r5 = r5.equals(r6);
                if (r5 == 0) goto L_0x0068;
            L_0x0055:
                r5 = com.gaana.login.LoginManager.this;
                r5 = r5.mAppState;
                r6 = com.gaana.login.LoginManager.this;
                r6 = r6.mUserInfo;
                r6 = r6.getPromourl();
                r5.setPromoUrl(r6);
            L_0x0068:
                r5 = r1.getStatus();
                r5 = android.text.TextUtils.isEmpty(r5);
                r6 = 0;
                if (r5 != 0) goto L_0x0270;
            L_0x0073:
                r5 = r1.getStatus();
                r7 = "1";
                r5 = r5.equals(r7);
                if (r5 == 0) goto L_0x0270;
            L_0x007f:
                r5 = r1.getAuthToken();
                r5 = android.text.TextUtils.isEmpty(r5);
                if (r5 != 0) goto L_0x0260;
            L_0x0089:
                if (r4 == 0) goto L_0x0260;
            L_0x008b:
                r5 = com.gaana.login.LoginManager.this;
                r5 = r5.mUserInfo;
                if (r5 == 0) goto L_0x0260;
            L_0x0093:
                r2 = com.gaana.login.LoginManager.this;
                r2 = r2.mAppState;
                r2 = r2.getCurrentUser();
                r2 = r2.getUserSubscriptionData();
                r2 = r2.getAccountType();
                r5 = com.gaana.login.LoginManager.this;
                r5 = r5.mUserInfo;
                r7 = 1;
                r5.setLoginStatus(r7);
                r5 = com.gaana.login.LoginManager.this;
                r5 = r5.mUserInfo;
                r8 = com.gaana.login.LoginManager.this;
                r8 = r8.getLoginInfo();
                r8 = r8.getLoginType();
                r5.setLoginType(r8);
                r5 = com.gaana.login.LoginManager.this;
                r5 = r5.mUserInfo;
                r8 = new java.util.Date;
                r8.<init>();
                r5.setLastLoginDateTime(r8);
                r5 = com.gaana.login.LoginManager.this;
                r5 = r5.mUserInfo;
                r8 = java.lang.System.currentTimeMillis();
                r5.setLastMyProfileRefreshTime(r8);
                r5 = com.gaana.login.LoginManager.this;
                r5 = r5.mUserInfo;
                r8 = java.lang.System.currentTimeMillis();
                r5.setLastGaanaPlusRefreshTime(r8);
                r5 = com.services.d.a();
                r8 = java.lang.System.currentTimeMillis();
                r10 = "pref_sso_last_refresh_time";
                r5.a(r8, r10, r6);
                r5 = com.services.d.a();
                r8 = java.lang.System.currentTimeMillis();
                r10 = "pref_social_token_last_refreshed";
                r5.a(r8, r10, r6);
                r5 = com.gaana.login.LoginManager.this;
                r5 = r5.mUserInfo;
                r5 = r5.getUserSubscriptionData();
                r5.updateAccountType();
                r5 = com.gaana.login.LoginManager.this;
                r5 = r5.mUserInfo;
                r5 = r5.getUserSubscriptionData();
                r5.updateExpiryDateAsPerServer();
                r5 = com.gaana.login.LoginManager.this;
                r5 = r5.mUserInfo;
                r5 = r5.getUserSubscriptionData();
                r5.updateExpiryDateWithGrace();
                r5 = com.gaana.login.LoginManager.this;
                r5.saveUserInfoInSharedPreff();
                r5 = com.gaana.login.LoginManager.this;
                r5 = r5.mUserInfo;
                r5 = r5.getUserProfile();
                r5 = r5.getSsoTicketId();
                r5 = android.text.TextUtils.isEmpty(r5);
                if (r5 != 0) goto L_0x015d;
            L_0x0144:
                r5 = com.gaana.login.LoginManager.this;
                r8 = r5;
                r5 = r5.getLoginClient(r8);
                r8 = com.gaana.login.LoginManager.this;
                r8 = r8.mUserInfo;
                r8 = r8.getUserProfile();
                r8 = r8.getSsoTicketId();
                r5.createUnverifiedSSOSession(r8, r4);
            L_0x015d:
                r4 = com.gaana.login.LoginManager.this;
                r4 = r4.isSilentLogin;
                if (r4 == 0) goto L_0x0170;
            L_0x0165:
                r4 = com.gaana.login.LoginManager.this;
                r4 = r4.isFromLoginonUpgrade;
                if (r4 == 0) goto L_0x016e;
            L_0x016d:
                goto L_0x0170;
            L_0x016e:
                r4 = r6;
                goto L_0x01dd;
            L_0x0170:
                r4 = com.managers.ap.a();
                r5 = com.gaana.login.LoginManager.this;
                r5 = r5.mUserInfo;
                r5 = r5.getUserProfile();
                r5 = r5.getUserId();
                r4.a(r5);
                r4 = com.gaana.login.LoginManager.this;
                r4.setUserSpecificPreferences();
                r4 = com.managers.o.a();
                r4.b();
                r4 = com.i.j.a();
                r4 = r4.c();
                r4.a();
                r4 = com.managers.v.a();
                r5 = com.gaana.login.LoginManager.this;
                r5 = r5.mAppState;
                r4.a(r5);
                r4 = com.gaana.login.LoginManager.this;
                r5 = r6;
                r8 = "referrer_user_id";
                r5 = r5.get(r8);
                r5 = (java.lang.CharSequence) r5;
                r5 = android.text.TextUtils.isEmpty(r5);
                r5 = r5 ^ r7;
                r4.sendTrackingEventsOnLoginSuccess(r1, r5);
                com.fcm.a.b();
                r4 = com.managers.d.a();
                r4.d();
                r4 = com.gaana.localmedia.PlaylistSyncManager.getInstance();
                r4.syncOnLogin();
                r4 = com.managers.n.a();
                r4.b();
                r4 = com.gaana.localmedia.FavouriteSyncManager.getInstance();
                r4.performSyncOnLogin();
                r4 = r7;
            L_0x01dd:
                r5 = r6;
                r11 = r5.toString();
                r8 = com.managers.an.a();
                r9 = "s2s";
                r10 = "ac";
                r12 = "GAANA";
                r13 = "";
                r14 = "SUCCESS";
                r15 = "";
                r16 = "";
                r8.d(r9, r10, r11, r12, r13, r14, r15, r16);
                r5 = com.gaana.login.LoginManager.this;
                r5 = r5.mUserInfo;
                r5 = r5.getUserSubscriptionData();
                r5 = r5.getAccountType();
                if (r2 == r5) goto L_0x0217;
            L_0x0208:
                if (r4 != 0) goto L_0x0217;
            L_0x020a:
                r2 = com.managers.q.a();
                r4 = com.gaana.login.LoginManager.this;
                r4 = r4.mUserInfo;
                r2.b(r4);
            L_0x0217:
                r2 = com.managers.ap.a();
                r2 = r2.n();
                if (r2 == 0) goto L_0x022b;
            L_0x0221:
                r1 = com.gaana.login.LoginManager.this;
                r2 = com.gaana.login.LoginManager.this;
                r2 = r2.mOnDeviceLinkedListener;
                r1.checkValidateAndLinkDevice(r2, r7);
                goto L_0x025c;
            L_0x022b:
                r2 = com.gaana.login.LoginManager.this;
                r2 = r2.mUserInfo;
                r2 = r2.getUserSubscriptionData();
                r2 = r2.isDeviceLinked();
                if (r2 == 0) goto L_0x0255;
            L_0x023b:
                r2 = com.gaana.login.LoginManager.this;
                r2 = r2.mUserInfo;
                r2.setDeviceLinkLimitReached(r6);
                r2 = com.gaana.login.LoginManager.this;
                r2 = r2.mOnUserStatusUpdatedListener;
                if (r2 == 0) goto L_0x0255;
            L_0x024c:
                r2 = com.gaana.login.LoginManager.this;
                r2 = r2.mOnUserStatusUpdatedListener;
                r2.onUserStatusUpdated();
            L_0x0255:
                r2 = com.gaana.login.LoginManager.this;
                r4 = com.gaana.login.LoginManager.LOGIN_STATUS.LOGIN_SUCCEDED;
                r2.loginCompleted(r4, r1, r3);
            L_0x025c:
                com.utilities.Util.N();
                goto L_0x0269;
            L_0x0260:
                r1 = com.gaana.login.LoginManager.this;
                r3 = com.gaana.login.LoginManager.LOGIN_STATUS.LOGIN_ERROR_AUTHENTICATION_FAILED;
                r5 = r6;
                r1.handleLoginFailure(r4, r3, r2, r5);
            L_0x0269:
                r1 = com.gaana.login.LoginManager.this;
                r1.isFromLoginonUpgrade = r6;
                goto L_0x0302;
            L_0x0270:
                r3 = r1.getErrorCode();
                r3 = android.text.TextUtils.isEmpty(r3);
                if (r3 != 0) goto L_0x02c1;
            L_0x027a:
                r3 = r1.getErrorCode();
                r5 = "6038";
                r3 = r3.equals(r5);
                if (r3 == 0) goto L_0x02c1;
            L_0x0286:
                r1 = com.gaana.login.LoginManager.this;
                r1 = r1.isSilentLogin;
                if (r1 == 0) goto L_0x02b7;
            L_0x028e:
                r1 = com.managers.ap.a();
                r2 = com.gaana.login.LoginManager.this;
                r2 = r2.mActivityReference;
                if (r2 == 0) goto L_0x02a7;
            L_0x029a:
                r2 = com.gaana.login.LoginManager.this;
                r2 = r2.mActivityReference;
                r2 = r2.get();
                r2 = (android.content.Context) r2;
                goto L_0x02ab;
            L_0x02a7:
                r2 = com.gaana.application.GaanaApplication.getContext();
            L_0x02ab:
                r3 = com.gaana.login.LoginManager.this;
                r3 = r3.mOnLoginCompleted;
                r4 = com.gaana.login.LoginManager.LOGIN_STATUS.LAUNCH_GDPR_DELETE_PROGRESS;
                r1.a(r2, r6, r3, r4);
                goto L_0x0302;
            L_0x02b7:
                r1 = com.gaana.login.LoginManager.this;
                r3 = com.gaana.login.LoginManager.LOGIN_STATUS.LAUNCH_GDPR_DELETE_PROGRESS;
                r5 = r6;
                r1.handleLoginFailure(r4, r3, r2, r5);
                goto L_0x0302;
            L_0x02c1:
                r3 = r1.isNewuserWithInvalidParameter();
                r5 = android.text.TextUtils.isEmpty(r3);
                if (r5 != 0) goto L_0x02ef;
            L_0x02cb:
                r5 = "0";
                r5 = r3.equals(r5);
                if (r5 != 0) goto L_0x02ef;
            L_0x02d3:
                r2 = "1";
                r2 = r3.equals(r2);
                if (r2 == 0) goto L_0x02e1;
            L_0x02db:
                r2 = com.gaana.login.LoginManager.this;
                r2.handleTrapPage(r1);
                goto L_0x0302;
            L_0x02e1:
                r1 = "-1";
                r1 = r3.equals(r1);
                if (r1 == 0) goto L_0x0302;
            L_0x02e9:
                r1 = com.gaana.login.LoginManager.this;
                r1.hideProgressDialog();
                goto L_0x0302;
            L_0x02ef:
                r1 = com.gaana.login.LoginManager.this;
                r3 = com.gaana.login.LoginManager.LOGIN_STATUS.LOGIN_ERROR_AUTHENTICATION_FAILED;
                r5 = r6;
                r1.handleLoginFailure(r4, r3, r2, r5);
                goto L_0x0302;
            L_0x02f9:
                r1 = com.gaana.login.LoginManager.this;
                r3 = com.gaana.login.LoginManager.LOGIN_STATUS.LOGIN_ERROR_UNKNOWN;
                r5 = r6;
                r1.handleLoginFailure(r4, r3, r2, r5);
            L_0x0302:
                return;
                */
                throw new UnsupportedOperationException("Method not decompiled: com.gaana.login.LoginManager$AnonymousClass11.onRetreivalComplete(java.lang.Object):void");
            }

            public void onErrorResponse(BusinessObject businessObject) {
                LoginManager.this.loginCompleted(LOGIN_STATUS.LOGIN_ERROR_UNKNOWN, LoginManager.this.mUserInfo, null);
            }
        }, uRLManager);
    }

    /* JADX WARNING: Missing exception handler attribute for start block: B:2:0x0006 */
    /* JADX WARNING: Failed to process nested try/catch */
    private boolean isJSONValid(java.lang.String r2) {
        /*
        r1 = this;
        r0 = new org.json.JSONObject;	 Catch:{ JSONException -> 0x0006 }
        r0.<init>(r2);	 Catch:{ JSONException -> 0x0006 }
        goto L_0x000b;
    L_0x0006:
        r0 = new org.json.JSONArray;	 Catch:{ JSONException -> 0x000d }
        r0.<init>(r2);	 Catch:{ JSONException -> 0x000d }
    L_0x000b:
        r2 = 1;
        return r2;
    L_0x000d:
        r2 = 0;
        return r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.gaana.login.LoginManager.isJSONValid(java.lang.String):boolean");
    }

    private void setUserSpecificPreferences() {
        d a = d.a();
        if (a.b("PREFERENCE_KEY_DATA_SAVE_MODE", false, false)) {
            int b = a.b("PREFERENCE_KEY_SYNC_QUALITY", 0, true);
            int b2 = a.b("PREFERENCE_KEY_STREAMING_QUALITY", 10000, false);
            a.a("PREFERENCE_LAST_DOWNLOAD_QUALITY_BEFORE_DATA_SAVE_MODE", b, true);
            a.a("PREFERENCE_KEY_SYNC_QUALITY", 0, true);
            Util.b("download_quality", "0");
            a.a("PREFERENCE_LAST_STREAMING_QUALITY_BEFORE_DATA_SAVE_MODE", b2, false);
            a.a("PREFERENCE_KEY_STREAMING_QUALITY", 10000, false);
            PlayerManager.a I = PlayerManager.a(GaanaApplication.getContext()).I();
            if (I != null) {
                I.j();
            }
        }
    }

    private void sendTrackingEventsOnLoginSuccess(UserInfo userInfo, boolean z) {
        String str;
        long timeInMillis;
        if (userInfo.isNewuser()) {
            MoEngage.getInstance().reportNewUser();
            d.a().a("ONBOARD_NEW_USER", true, false);
            if (this.initialTime != 0) {
                timeInMillis = Calendar.getInstance().getTimeInMillis() - this.initialTime;
                str = "";
                if (this.loginType == LoginType.FB) {
                    str = "Facebook";
                    AppsFlyer.getInstance().reportUserRegistration("social.facebook.register");
                } else if (this.loginType == LoginType.GOOGLE) {
                    str = "Google";
                    AppsFlyer.getInstance().reportUserRegistration("social.google.register");
                } else if (this.loginType == LoginType.PHONENUMBER) {
                    str = "Mobile_No";
                    AppsFlyer.getInstance().reportUserRegistration("social.mobileno.register");
                }
                Constants.a("Signup", timeInMillis, str, "Success");
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(this.loginType);
                stringBuilder.append(" - Success");
                u.a().a("Signup", "Signup", stringBuilder.toString());
            }
        } else if (this.initialTime != 0) {
            timeInMillis = Calendar.getInstance().getTimeInMillis() - this.initialTime;
            str = "";
            if (this.loginType == LoginType.FB) {
                str = "Facebook";
                AppsFlyer.getInstance().reportUserLogin("social.facebook.login");
            } else if (this.loginType == LoginType.GOOGLE) {
                str = "Google";
                AppsFlyer.getInstance().reportUserLogin("social.google.login");
            } else if (this.loginType == LoginType.GAANA) {
                str = "Email";
                AppsFlyer.getInstance().reportUserLogin("email.login");
            } else if (this.loginType == LoginType.PHONENUMBER) {
                str = "Mobile_No";
                AppsFlyer.getInstance().reportUserLogin("mobileno.login");
            }
            Constants.a("Login", timeInMillis, str, "Success");
        }
        str = "";
        if (this.loginType == LoginType.FB) {
            str = "Facebook";
        } else if (this.loginType == LoginType.GOOGLE) {
            str = "Google+";
        } else if (this.loginType == LoginType.GAANA) {
            str = "Email";
        } else if (this.loginType == LoginType.PHONENUMBER) {
            str = "Mobile_No";
        }
        u.a().c();
        u a = u.a();
        String str2 = "Login";
        String str3 = "Login";
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append(this.loginType == LoginType.FB ? "FB" : str);
        stringBuilder2.append(" - Success");
        a.a(str2, str3, stringBuilder2.toString());
        if (z) {
            UninstallIO.sendReferredLoginEvent(str);
            MoEngage.getInstance().reportWasReferred(str);
        }
        MoEngage.getInstance().reportOnLogin(this.mUserInfo);
        UninstallIO.trackLoginDetails(GaanaApplication.getContext(), userInfo.getUserProfile());
        q.a().f();
        q.a().a(this.mAppState.getCurrentUser());
        f.v().a(this.mAppState.getCurrentUser());
        q.a().b(this.mAppState.getCurrentUser());
        q.a().c();
        if (this.loginType == LoginType.FB) {
            getTimesPointLogger().a("act5933695", this.mUserInfo.getUserProfile().getUserId(), this.mUserInfo.getUserProfile().getUserId(), null);
        }
    }

    public void showProgressDialog(final boolean z, final String str) {
        if (!this.isSilentLogin) {
            final Activity activity = (Activity) this.mActivityReference.get();
            if (activity != null && !activity.isFinishing()) {
                activity.runOnUiThread(new Runnable() {
                    public void run() {
                        if (activity instanceof BaseActivity) {
                            ((BaseActivity) activity).showProgressDialog(Boolean.valueOf(z), str);
                        } else if (activity instanceof ReferralSignupActivity) {
                            ((ReferralSignupActivity) activity).showProgressDialog(Boolean.valueOf(z), str);
                        }
                    }
                });
            }
        }
    }

    public void hideProgressDialog() {
        if (!this.isSilentLogin) {
            Activity activity = (Activity) this.mActivityReference.get();
            if (activity != null && !activity.isFinishing()) {
                if (activity instanceof BaseActivity) {
                    ((BaseActivity) activity).hideProgressDialog();
                } else if (activity instanceof ReferralSignupActivity) {
                    ((ReferralSignupActivity) activity).hideProgressDialog();
                }
            }
        }
    }

    public void saveUserInfoInSharedPreff() {
        d.a().a(PREFF_GAANA_USER_INFO, n.a(this.mUserInfo), false);
    }

    public void checkValidateAndLinkDevice(final x xVar, boolean z) {
        String devicesCount = this.mUserInfo.getUserSubscriptionData().getDevicesCount();
        if (TextUtils.isEmpty(devicesCount)) {
            devicesCount = "0";
        }
        if (Integer.parseInt(devicesCount) < this.mUserInfo.getUserSubscriptionData().getProductProperties().getDeviceLimit()) {
            this.mUserInfo.setDeviceLinkLimitReached(false);
            linkDevice(xVar, z);
            return;
        }
        this.mUserInfo.setDeviceLinkLimitReached(true);
        try {
            u.a().a(7, "DeviceLimitExceeded");
        } catch (Exception unused) {
        }
        final Activity activity = (Activity) this.mActivityReference.get();
        if (activity != null && !activity.isFinishing()) {
            new com.services.f(activity).a(activity.getResources().getString(R.string.error_title_device_linking_limit_reached), String.format(activity.getResources().getString(R.string.device_linking_limit_reached), new Object[]{Integer.valueOf(this.mAppState.getCurrentUser().getUserSubscriptionData().getProductProperties().getDeviceLimit())}), Boolean.valueOf(true), activity.getResources().getString(R.string.dlg_msg_manage), activity.getResources().getString(R.string.dlg_msg_cancel), new com.services.f.b() {
                public void onOkListner(String str) {
                    LoginManager.this.mUserInfo.getUserSubscriptionData().setAccountType(1);
                    LoginManager.this.mUserInfo.getUserSubscriptionData().setDevicelinked(false);
                    LoginManager.this.saveUserInfoInSharedPreff();
                    Intent intent = new Intent(activity, WebViewActivity.class);
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("https://gaana.com/gaana_plus&token=");
                    stringBuilder.append(LoginManager.this.mUserInfo.getAuthToken());
                    stringBuilder.append("&");
                    stringBuilder.append("deviceId");
                    stringBuilder.append("=");
                    stringBuilder.append(Util.l(GaanaApplication.getContext()));
                    intent.putExtra("EXTRA_WEBVIEW_URL", stringBuilder.toString());
                    intent.putExtra("EXTRA_SHOW_ACTIONBAR", true);
                    activity.startActivityForResult(intent, 708);
                }

                public void onCancelListner() {
                    xVar.onDeviceLinkingFailed(false);
                }
            }, false);
        }
    }

    private void showToast(String str) {
        if (GaanaApplication.getContext() != null) {
            aj.a().a(GaanaApplication.getContext(), str);
        }
    }

    /* Access modifiers changed, original: protected */
    public void handleLoginFailure(LoginInfo loginInfo, LOGIN_STATUS login_status, String str, HashMap<String, String> hashMap) {
        if (this.initialTime != 0) {
            long timeInMillis = Calendar.getInstance().getTimeInMillis() - this.initialTime;
            String str2 = "";
            if (this.loginType == LoginType.FB) {
                str2 = "Facebook";
            } else if (this.loginType == LoginType.GOOGLE) {
                str2 = "Google";
            } else if (this.loginType == LoginType.GAANA) {
                str2 = "Email";
            } else if (this.loginType == LoginType.PHONENUMBER) {
                str2 = "Mobile_No";
            }
            Constants.a("Login", timeInMillis, str2, "Failure");
            u a = u.a();
            String str3 = "Login";
            String str4 = "Login";
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(this.loginType == LoginType.FB ? "FB" : str2);
            stringBuilder.append(" - Failure");
            a.a(str3, str4, stringBuilder.toString());
            if (TextUtils.isEmpty(str)) {
                str = "";
            }
            String str5 = str;
            String hashMap2 = hashMap.toString();
            an a2 = an.a();
            str3 = "s2s";
            str4 = "ac";
            if (this.loginType == LoginType.FB) {
                str2 = "FB";
            }
            a2.d(str3, str4, hashMap2, str2, str5, "FAIL", "", "");
        }
        if (this.loginType == LoginType.FB) {
            showToast(this.mAppState.getString(R.string.login_facebook_failed_please_try_again));
            hideProgressDialog();
            logoutFromClient(login_status, LoginType.FB, loginInfo);
        } else if (this.loginType == LoginType.GOOGLE) {
            showToast(this.mAppState.getString(R.string.login_with_google_plus_failed_try_again_later));
            hideProgressDialog();
            logoutFromClient(login_status, LoginType.GOOGLE, loginInfo);
        } else if (this.loginType == LoginType.PHONENUMBER) {
            showToast(this.mAppState.getString(R.string.login_with_phone_failed_try_again_later));
            hideProgressDialog();
            logoutFromClient(login_status, LoginType.PHONENUMBER, loginInfo);
        } else {
            loginCompleted(login_status, this.mUserInfo, null);
        }
    }

    /* Access modifiers changed, original: protected */
    public void handleSSOLoginFailure(LoginInfo loginInfo, int i, String str) {
        int i2 = i;
        String str2 = str;
        String str3 = "";
        LoginType loginType = loginInfo.getLoginType();
        if (loginType == LoginType.FB) {
            str3 = "Facebook";
        } else if (loginType == LoginType.GOOGLE) {
            str3 = "Google";
        } else if (loginType == LoginType.GAANA) {
            str3 = "Email";
        } else if (loginType == LoginType.PHONENUMBER) {
            str3 = "Mobile_No";
        }
        if (this.initialTime != 0) {
            Constants.a("Login", Calendar.getInstance().getTimeInMillis() - this.initialTime, str3, "Failure");
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(i2);
        stringBuilder.append(" - ");
        stringBuilder.append(str2);
        an.a().a("click", "ac", "", "SSO", stringBuilder.toString(), "FAIL", "", "");
        u a = u.a();
        String str4 = "Login";
        String str5 = "Login - SSO";
        StringBuilder stringBuilder2 = new StringBuilder();
        if (loginType == LoginType.FB) {
            str3 = "FB";
        }
        stringBuilder2.append(str3);
        stringBuilder2.append(" - Failure - ");
        stringBuilder2.append(i2);
        stringBuilder2.append(" - ");
        stringBuilder2.append(str2);
        a.a(str4, str5, stringBuilder2.toString());
    }

    /* Access modifiers changed, original: protected */
    public void handleSSOLoginFailureOnReAuth(LoginInfo loginInfo, int i, String str, boolean z) {
        String str2 = "";
        LoginType loginType = loginInfo.getLoginType();
        if (loginType == LoginType.FB) {
            str2 = "Facebook";
        } else if (loginType == LoginType.GOOGLE) {
            str2 = "Google";
        } else if (loginType == LoginType.GAANA) {
            str2 = "Email";
        } else if (loginType == LoginType.PHONENUMBER) {
            str2 = "Mobile_No";
        }
        String str3 = z ? "SSO - ReAuth - Logout" : "SSO - ReAuth";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(i);
        stringBuilder.append(" - ");
        stringBuilder.append(str);
        an.a().a("click", "ac", "", "SSO", stringBuilder.toString(), "FAIL", "", "");
        u a = u.a();
        String str4 = "Login";
        StringBuilder stringBuilder2 = new StringBuilder();
        if (loginType == LoginType.FB) {
            str2 = "FB";
        }
        stringBuilder2.append(str2);
        stringBuilder2.append(" - Failure - ");
        stringBuilder2.append(i);
        stringBuilder2.append(" - ");
        stringBuilder2.append(str);
        a.a(str4, str3, stringBuilder2.toString());
    }

    private void handleTrapPage(UserInfo userInfo) {
        loginCompleted(LOGIN_STATUS.LOGIN_ERROR_LAUNCH_TRAP_PAGE, userInfo, null);
    }

    private void clear() {
        this.mDeviceResourceManager.b("PREFF_GAANA_LOGIN_INFO", false);
        this.mDeviceResourceManager.b(PREFF_GAANA_USER_INFO, false);
        this.mUserInfo = new UserInfo();
    }

    /* Access modifiers changed, original: protected */
    public void logoutFromFacebook(final LOGIN_STATUS login_status) {
        h.a().a(new TaskListner() {
            public void onBackGroundTaskCompleted() {
                LoginManager.this.clear();
                LoginManager.this.loginCompleted(login_status, LoginManager.this.mUserInfo, null);
            }

            public void doBackGroundTask() {
                g.a().h();
            }
        }, -1);
    }

    /* Access modifiers changed, original: protected */
    public void logoutFromGoogle(final LOGIN_STATUS login_status) {
        h.a().a(new TaskListner() {
            public void onBackGroundTaskCompleted() {
                LoginManager.this.clear();
                LoginManager.this.loginCompleted(login_status, LoginManager.this.mUserInfo, null);
            }

            public void doBackGroundTask() {
                GooglePlusLogin.getInstance().disconnect();
            }
        }, -1);
    }

    private void logoutFromPhoneLogin(final LOGIN_STATUS login_status) {
        h.a().a(new TaskListner() {
            public void onBackGroundTaskCompleted() {
                LoginManager.this.clear();
                LoginManager.this.loginCompleted(login_status, LoginManager.this.mUserInfo, null);
            }

            public void doBackGroundTask() {
                m.a().b();
            }
        }, -1);
    }

    /* Access modifiers changed, original: protected */
    public void logoutFromClient(final LOGIN_STATUS login_status, final LoginType loginType, final LoginInfo loginInfo) {
        h.a().a(new TaskListner() {
            public void onBackGroundTaskCompleted() {
                LoginManager.this.clear();
                LoginManager.this.loginCompleted(login_status, LoginManager.this.mUserInfo, null);
            }

            public void doBackGroundTask() {
                LoginManager.this.getLoginClient(loginType).logout(loginInfo);
            }
        }, -1);
    }

    public boolean hasTokenExpired() {
        return !this.mAppState.isAppInOfflineMode() && Util.j(GaanaApplication.getContext()) && this.mUserInfo.getLoginStatus() && hasGaanaSessionExpired(this.mUserInfo.getLastLoginDateTime());
    }

    private boolean shouldRenewSSOToken() {
        if (!this.mAppState.isAppInOfflineMode() && Util.j(GaanaApplication.getContext()) && this.mUserInfo.getLoginStatus()) {
            if (System.currentTimeMillis() - d.a().b(0, "pref_sso_last_refresh_time", false) > 14400000) {
                return true;
            }
        }
        return false;
    }

    private boolean shouldUpdateSocialToken() {
        if (!this.mAppState.isAppInOfflineMode() && Util.j(GaanaApplication.getContext()) && this.mUserInfo.getLoginStatus()) {
            if (((int) (System.currentTimeMillis() - d.a().b(0, "pref_social_token_last_refreshed", false))) > SOCIAL_ACCESS_TOKEN_RENEW) {
                return true;
            }
        }
        return false;
    }

    private boolean shouldCheckGaanaPlusStatus() {
        if (!this.mAppState.isAppInOfflineMode() && Util.j(GaanaApplication.getContext()) && this.mUserInfo.getLoginStatus()) {
            if (((int) ((System.currentTimeMillis() - this.mUserInfo.getLastGaanaPlusRefreshTime()) / 1000)) > 43200) {
                if (Constants.b) {
                    Log.d("Test", "==>> renewing authtoken");
                }
                return true;
            }
        }
        return false;
    }

    private boolean shoudlFetchMyProfile() {
        if (!this.mAppState.isAppInOfflineMode() && Util.j(GaanaApplication.getContext()) && this.mUserInfo.getLoginStatus()) {
            if (((int) ((System.currentTimeMillis() - this.mUserInfo.getLastMyProfileRefreshTime()) / 1000)) > 43200) {
                if (Constants.b) {
                    Log.d("Test", "==>> renewing authtoken");
                }
                return true;
            }
        }
        return false;
    }

    private boolean hasGaanaSessionExpired(Date date) {
        if ((new Date().getTime() - date.getTime()) / 1000 > 1296000 || !this.mAppState.isAuthenticationStatus()) {
            return true;
        }
        this.mAppState.setAuthenticationStatus(true);
        return false;
    }

    public void linkDevice(final x xVar, boolean z) {
        if (z) {
            showProgressDialog(false, this.mAppState.getString(R.string.linking_this_device_to_your_account));
        }
        String l = Util.l(GaanaApplication.getContext());
        HashMap hashMap = new HashMap();
        hashMap.put("type", "linkdevice");
        hashMap.put("deviceid", l);
        hashMap.put("devicename", Util.a());
        hashMap.put(AccountKitGraphConstants.TOKEN_RESPONSE_TYPE, this.mAppState.getCurrentUser().getAuthToken());
        String a = i.a().a("https://api.gaana.com/gaanaplusservice.php?", hashMap);
        URLManager uRLManager = new URLManager();
        uRLManager.a(a);
        uRLManager.b(Boolean.valueOf(false));
        uRLManager.a(LinkDeviceResponse.class);
        uRLManager.a(Priority.IMMEDIATE);
        uRLManager.i(false);
        i.a().a(new af() {
            public void onRetreivalComplete(Object obj) {
                boolean z;
                LinkDeviceResponse linkDeviceResponse = (LinkDeviceResponse) obj;
                LoginManager.this.hideProgressDialog();
                if (linkDeviceResponse == null || TextUtils.isEmpty(linkDeviceResponse.getStatus()) || !(linkDeviceResponse.getStatus().equalsIgnoreCase("true") || (linkDeviceResponse.getStatus().equalsIgnoreCase(InternalLogger.EVENT_PARAM_EXTRAS_FALSE) && !TextUtils.isEmpty(linkDeviceResponse.getResult()) && linkDeviceResponse.getResult().contains("Already Linked")))) {
                    z = false;
                } else {
                    z = true;
                }
                if (z) {
                    xVar.onDeviceLinkingSuccessful(linkDeviceResponse);
                } else {
                    xVar.onDeviceLinkingFailed(true);
                }
            }

            public void onErrorResponse(BusinessObject businessObject) {
                xVar.onDeviceLinkingFailed(true);
            }
        }, uRLManager);
    }

    public void getUserStatus(Activity activity, final au auVar, final boolean z) {
        userStatusUpdateInProgress = true;
        this.mOnUserStatusUpdatedListener = auVar;
        this.mActivityReference = new WeakReference(activity);
        if (z) {
            showProgressDialog(true, GaanaApplication.getContext().getString(R.string.updating_your_gaana_plus_status));
        }
        HashMap hashMap = new HashMap();
        hashMap.put("type", "getuserstatus");
        hashMap.put(AccountKitGraphConstants.TOKEN_RESPONSE_TYPE, this.mAppState.getCurrentUser().getAuthToken());
        URLManager uRLManager = new URLManager();
        uRLManager.a("https://api.gaana.com/gaanaplusservice_nxtgen.php?");
        uRLManager.a(UserStatusInfo.class);
        uRLManager.a(hashMap);
        uRLManager.b(Boolean.valueOf(false));
        uRLManager.i(false);
        i.a().a(new af() {
            public void onRetreivalComplete(Object obj) {
                LoginManager.this.hideProgressDialog();
                LoginManager.userStatusUpdateInProgress = false;
                if (obj != null) {
                    UserStatusInfo userStatusInfo = (UserStatusInfo) obj;
                    if (LoginManager.this.mAppState.getCurrentUser().getUserSubscriptionData() != null) {
                        if (LoginManager.this.mAppState.getCurrentUser().getUserSubscriptionData().getAccountType() != userStatusInfo.getUserSubscriptionData().getAccountType()) {
                            LoginManager.this.mUserInfo.setUserStatusInfo(userStatusInfo);
                            q.a().b(LoginManager.this.mUserInfo);
                        } else {
                            LoginManager.this.mUserInfo.setUserStatusInfo(userStatusInfo);
                        }
                        Constants.bM = userStatusInfo.getUserSubscriptionData().getZeroClickToken();
                    }
                    LoginManager.this.mUserInfo.setLastGaanaPlusRefreshTime(System.currentTimeMillis());
                    LoginManager.getInstance().saveUserInfoInSharedPreff();
                    if (LoginManager.this.mAppState.isAuthenticationStatus() && z) {
                        if (ap.a().n()) {
                            LoginManager.this.checkValidateAndLinkDevice(LoginManager.this.mOnDeviceLinkedListener, z);
                        } else {
                            if (LoginManager.this.mUserInfo.getUserSubscriptionData().isDeviceLinked()) {
                                LoginManager.this.mUserInfo.setDeviceLinkLimitReached(false);
                            }
                            if (auVar != null) {
                                auVar.onUserStatusUpdated();
                            }
                        }
                    } else if (auVar != null) {
                        auVar.onUserStatusUpdated();
                    }
                    Activity activity = (Activity) LoginManager.this.mActivityReference.get();
                    if (!(activity == null || !(activity instanceof GaanaActivity) || activity.isFinishing())) {
                        ((GaanaActivity) activity).updateSideBar();
                    }
                    u.a().d();
                } else if (auVar != null) {
                    auVar.onUserStatusUpdated();
                }
            }

            public void onErrorResponse(BusinessObject businessObject) {
                LoginManager.this.hideProgressDialog();
                LoginManager.userStatusUpdateInProgress = false;
                if (auVar != null) {
                    auVar.onUserStatusUpdated();
                }
            }
        }, uRLManager);
    }

    public void clearSimplSession() {
        Constants.bP = -1;
        Constants.bO = "";
        Constants.bN = false;
        Constants.bS = "";
        Constants.bQ = 0;
        Constants.bM = "";
        Constants.bR = "";
        isSimplInitialized = false;
    }

    /* Access modifiers changed, original: protected */
    public void loginCompleted(LOGIN_STATUS login_status, UserInfo userInfo, Bundle bundle) {
        this.mAppState.setAuthenticationStatus(true);
        Context context = this.mActivityReference != null ? (Activity) this.mActivityReference.get() : null;
        if (login_status == LOGIN_STATUS.LOGGED_OUT) {
            clearSimplSession();
        }
        if (login_status == LOGIN_STATUS.LOGIN_SUCCEDED) {
            getLoginClient(getInstance().getLoginInfo().getLoginType()).getUserId(getInstance().getLoginInfo(), new af() {
                public void onErrorResponse(BusinessObject businessObject) {
                }

                public void onRetreivalComplete(Object obj) {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("");
                    stringBuilder.append((String) obj);
                    u.a().a(ActionMapperConstants.KEY_SET, "userId", stringBuilder.toString());
                }
            });
        }
        if (!this.isSilentLogin && (login_status == LOGIN_STATUS.LOGIN_SUCCEDED || login_status == LOGIN_STATUS.LOGGED_OUT)) {
            if (GaanaMusicService.u()) {
                o.g(GaanaApplication.getContext());
            }
            com.managers.s.a().c();
        }
        if (!this.isSilentLogin && login_status == LOGIN_STATUS.LOGIN_SUCCEDED) {
            j.a().a("https://apiv2.gaana.com/radio/metadata");
            final LOGIN_STATUS login_status2 = login_status;
            final UserInfo userInfo2 = userInfo;
            final Bundle bundle2 = bundle;
            final Context context2 = context;
            DynamicViewManager.a().a(new y() {
                public void OnDynamicViewDataFetched() {
                    LoginManager.this.hideProgressDialog();
                    LoginManager.this.mDeviceResourceManager.b("SHARED_PREFF_REFERRAL_LINK", false);
                    Util.a(LoginManager.this.mAppState, "APP_WIDGET_UPDATE_ACTION", null);
                    if (LoginManager.this.mOnLoginCompleted != null) {
                        LoginManager.this.mOnLoginCompleted.onLoginCompleted(login_status2, userInfo2, bundle2);
                    }
                    Constants.a(LoginManager.this.mAppState.getCurrentUser());
                    if (LoginManager.this.mOnLoginCompleted != null) {
                        LoginManager.this.mOnLoginCompleted.onLoginCompleted(login_status2, userInfo2, bundle2);
                    }
                    if (GaanaApplication.getInstance().isAppInForeground() && (ap.a().j() || Util.v())) {
                        DownloadManager.c().d();
                    }
                    Intent intent;
                    if (!LoginManager.this.isSilentLogin && login_status2 == LOGIN_STATUS.LOGIN_SUCCEDED && Util.j(GaanaApplication.getContext()) && d.a().b("PREFERENCE_LANGUAGE_ONBOARD", 0, false) == 0) {
                        intent = new Intent(GaanaApplication.getContext(), OnBoardLanguagePreferenceActivityNew.class);
                        intent.putExtra("ONBOARD_SIGNUP_FROM_APP_INSIDE", false);
                        intent.setFlags(603979776);
                        if (context2 != null && (context2 instanceof Login)) {
                            context2.startActivity(intent);
                        } else if (ai.a() != null) {
                            ai.a().startActivity(intent);
                        } else {
                            intent.addFlags(C.ENCODING_PCM_MU_LAW);
                            GaanaApplication.getContext().startActivity(intent);
                        }
                    } else if (!LoginManager.this.isSilentLogin && login_status2 == LOGIN_STATUS.LOGIN_SUCCEDED && Constants.p && Login.isSignupFromInside && Constants.m) {
                        intent = new Intent(GaanaApplication.getContext(), GaanaActivity.class);
                        if (Login.isSignupFromInside) {
                            intent.setFlags(71303168);
                        } else {
                            intent.setFlags(335544320);
                        }
                        if (LoginManager.this.checkDisableInternationalOnBoarding(context2)) {
                            context2.startActivity(intent);
                        }
                    }
                    if (ap.a().i()) {
                        d.a().a("PREFERENCE_SESSION_TRIAL_COUNT", GaanaApplication.sessionHistoryCount, true);
                    }
                    LoginManager.this.isSilentLogin = false;
                    LoginManager.this.isLoginInProcess = false;
                    LoginManager.this.mOnLoginCompleted = null;
                }
            }, context);
        }
        hideProgressDialog();
        if (login_status == LOGIN_STATUS.LOGIN_ERROR_AUTHENTICATION_FAILED && this.isSilentLogin && context != null) {
            ap.a().a(context, false, null, LOGIN_STATUS.LOGGED_OUT);
        } else if (login_status == LOGIN_STATUS.LAUNCH_GDPR_DELETE_PROGRESS && !this.isSilentLogin && (context instanceof AppCompatActivity) && !context.isFinishing()) {
            try {
                GDPRCantUseAppFragment gDPRCantUseAppFragment = new GDPRCantUseAppFragment();
                gDPRCantUseAppFragment.show(((AppCompatActivity) context).getSupportFragmentManager(), gDPRCantUseAppFragment.getClass().getSimpleName());
            } catch (Exception unused) {
            }
        }
        if (login_status == LOGIN_STATUS.LOGIN_SUCCEDED) {
            this.mDeviceResourceManager.b("SHARED_PREFF_REFERRAL_LINK", false);
            Util.a(this.mAppState, "APP_WIDGET_UPDATE_ACTION", null);
        }
        if (login_status == LOGIN_STATUS.LOGGED_OUT || login_status == LOGIN_STATUS.NOT_LOGGEDIN) {
            Util.a(this.mAppState, "APP_WIDGET_UPDATE_ACTION", null);
        }
        Constants.a(this.mAppState.getCurrentUser());
        if (this.mOnLoginCompleted != null) {
            this.mOnLoginCompleted.onLoginCompleted(login_status, userInfo, bundle);
        }
        if (GaanaApplication.getInstance().isAppInForeground() && (ap.a().j() || Util.v())) {
            DownloadManager.c().d();
        }
        Intent intent;
        if (!this.isSilentLogin && login_status == LOGIN_STATUS.LOGIN_SUCCEDED && Util.j(GaanaApplication.getContext()) && d.a().b("PREFERENCE_LANGUAGE_ONBOARD", 0, false) == 0) {
            intent = new Intent(GaanaApplication.getContext(), OnBoardLanguagePreferenceActivityNew.class);
            intent.putExtra("ONBOARD_SIGNUP_FROM_APP_INSIDE", false);
            intent.setFlags(603979776);
            if (context != null && (context instanceof Login)) {
                context.startActivity(intent);
            } else if (ai.a() != null) {
                ai.a().startActivity(intent);
            } else {
                intent.addFlags(C.ENCODING_PCM_MU_LAW);
                GaanaApplication.getContext().startActivity(intent);
            }
        } else if (!this.isSilentLogin && login_status == LOGIN_STATUS.LOGIN_SUCCEDED && Constants.p && Login.isSignupFromInside && Constants.m) {
            intent = new Intent(GaanaApplication.getContext(), GaanaActivity.class);
            if (Login.isSignupFromInside) {
                intent.setFlags(71303168);
            } else {
                intent.setFlags(335544320);
            }
            if (checkDisableInternationalOnBoarding(context)) {
                context.startActivity(intent);
            }
        }
        if (ap.a().i() && login_status == LOGIN_STATUS.LOGIN_SUCCEDED) {
            d.a().a("PREFERENCE_SESSION_TRIAL_COUNT", GaanaApplication.sessionHistoryCount, true);
        }
        this.isSilentLogin = false;
        this.isLoginInProcess = false;
        this.mOnLoginCompleted = null;
    }

    public boolean checkDisableInternationalOnBoarding(Context context) {
        if (!Constants.T) {
            return true;
        }
        Constants.T = false;
        d a = d.a();
        if (a.c("PREF_KEY_REFERRAL_INFO", false) == null) {
            return true;
        }
        ReferralSignup referralSignup = (ReferralSignup) new Gson().fromJson(a.c("PREF_KEY_REFERRAL_INFO", false), ReferralSignup.class);
        a.b("PREF_KEY_REFERRAL_INFO", false);
        Intent intent = new Intent(GaanaApplication.getContext(), ReferralSignupActivity.class);
        intent.setFlags(603979776);
        intent.putExtra("is_first_ap_launch", true);
        intent.putExtra("referralInfo", referralSignup);
        intent.putExtra("FROM_INTERNATIONAL_ONBOARDING", true);
        context.startActivity(intent);
        return false;
    }

    private int getQueuedDownloadedSongCount() {
        return DownloadManager.c().B() + DownloadManager.c().K();
    }

    public void checkTrialAvailability(Context context, s sVar) {
        checkTrialAvailability(context, sVar, "");
    }

    public void checkTrialAvailability(Context context, s sVar, String str) {
        if (Constants.aA) {
            URLManager uRLManager = new URLManager();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("https://api.gaana.com/gaanaplusservice_nxtgen.php?type=get_gtrial&no_downloads=");
            stringBuilder.append(getQueuedDownloadedSongCount());
            stringBuilder.append(ag.a(context).d());
            if (TextUtils.isEmpty(str)) {
                str = "";
            }
            stringBuilder.append(str);
            uRLManager.a(stringBuilder.toString());
            uRLManager.a(BusinessObjectType.TrialProductFeature);
            uRLManager.b(Boolean.valueOf(false));
            uRLManager.b(1);
            uRLManager.i(false);
            i.a().a(sVar, uRLManager);
        } else if (Constants.az) {
            URLManager uRLManager2 = new URLManager();
            uRLManager2.a("https://api.gaana.com/gaanaplusservice.php?type=is_valid_for_trial");
            uRLManager2.a(BusinessObjectType.BasicResponse);
            uRLManager2.b(Boolean.valueOf(false));
            uRLManager2.i(false);
            i.a().a(sVar, uRLManager2);
        } else {
            sVar.onErrorResponse(null);
        }
    }

    public void initSsoSdk(Context context) {
        isSsoSdkInitialized(new SsoSdkInitialized() {
            public void onError() {
            }

            public void onSuccess() {
            }
        });
    }

    public void migrateUserToSso(final LoginInfo loginInfo, final Activity activity) {
        CharSequence charSequence = "";
        final LoginType loginType = loginInfo.getLoginType();
        if (loginType == LoginType.GAANA) {
            charSequence = "email";
        } else if (loginType == LoginType.FB) {
            charSequence = TAG_SUBTYPE_FB;
        } else if (loginType == LoginType.GOOGLE) {
            charSequence = TAG_SUBTYPE_GOOGLE;
        }
        if (!this.isSilentLogin && !TextUtils.isEmpty(charSequence)) {
            String replace = "https://api.gaana.com/user.php?type=nxtgen_session_migration&migration_type=<what>".replace("<what>", charSequence);
            this.isSilentLogin = true;
            com.i.b bVar = new com.i.b(replace, String.class, new e.a() {
                public void onDataRetrieved(Object obj, boolean z) {
                    final String str = (String) obj;
                    final String access$2000 = LoginManager.this.getTicketId(str);
                    StringBuilder stringBuilder;
                    if (!TextUtils.isEmpty(access$2000)) {
                        LoginManager.this.isSsoSdkInitialized(new SsoSdkInitialized() {
                            public void onSuccess() {
                                if (LoginManager.this.getUserState(str) == 1) {
                                    in.til.core.a.b().a(access$2000, new com.login.nativesso.a.n() {
                                        public void onSuccess() {
                                            LoginManager.this.isSilentLogin = false;
                                            d.a().a(System.currentTimeMillis(), "pref_sso_last_refresh_time", false);
                                            d.a().a(System.currentTimeMillis(), "pref_social_token_last_refreshed", false);
                                            loginInfo.setLoginMode(LoginMode.SSO);
                                            LoginManager.this.setLoginInfo(loginInfo);
                                        }

                                        public void onFailure(c cVar) {
                                            LoginManager.this.isSilentLogin = false;
                                            if (cVar.a == 404) {
                                                LoginManager loginManager = LoginManager.this;
                                                int i = cVar.a;
                                                StringBuilder stringBuilder = new StringBuilder();
                                                stringBuilder.append("MIGRATION SSO UNAUTHORIZED ACCESS - ");
                                                stringBuilder.append(Util.X());
                                                loginManager.sendUserLogOutEvent(i, stringBuilder.toString());
                                                ap.a().a(activity, false, null, LOGIN_STATUS.LOGGED_OUT);
                                            }
                                        }

                                        public void onSdkFailure(TILSDKExceptionDto tILSDKExceptionDto) {
                                            LoginManager.this.isSilentLogin = false;
                                        }
                                    });
                                    return;
                                }
                                loginInfo.setLoginMode(LoginMode.GAANA_SSO_NOT_VERIFIED);
                                LoginManager.this.getLoginClient(loginType).createUnverifiedSSOSession(access$2000, loginInfo);
                                LoginManager.this.setLoginInfo(loginInfo);
                                LoginManager.this.isSilentLogin = false;
                            }

                            public void onError() {
                                LoginManager.this.isSilentLogin = false;
                            }
                        });
                    } else if (LoginManager.this.getUserState(str) == 3) {
                        LoginManager.this.isSilentLogin = false;
                        loginInfo.setLoginMode(LoginMode.GAANA_SSO_NOT_VERIFIED);
                        LoginManager.this.setLoginInfo(loginInfo);
                    } else if (LoginManager.this.getUTSFromResponse(str) == 0) {
                        LoginManager.this.isSilentLogin = false;
                        stringBuilder = new StringBuilder();
                        stringBuilder.append("UTS 0 - ");
                        stringBuilder.append(str);
                        u.a().a("Login", "Migration", stringBuilder.toString());
                    } else {
                        LoginManager.this.isSilentLogin = false;
                        access$2000 = "";
                        if (!(LoginManager.this.mUserInfo == null || LoginManager.this.mUserInfo.getUserProfile() == null)) {
                            access$2000 = LoginManager.this.mUserInfo.getUserProfile().getUserId();
                        }
                        ap.a().a(activity, false, null, LOGIN_STATUS.LOGGED_OUT);
                        LoginManager loginManager = LoginManager.this;
                        stringBuilder = new StringBuilder();
                        stringBuilder.append("MIGRATION UNAUTHORIZED ACCESS - ");
                        stringBuilder.append(access$2000);
                        stringBuilder.append(" - ");
                        stringBuilder.append(str);
                        loginManager.sendUserLogOutEvent(-1, stringBuilder.toString());
                    }
                }

                public void onErrorResponse(BusinessObject businessObject) {
                    LoginManager.this.isSilentLogin = false;
                }
            });
            bVar.a(false);
            i.a().a(bVar);
        }
    }

    private String getTicketId(String str) {
        String str2 = "";
        try {
            return new JSONObject(str).optString("ticketId", "");
        } catch (JSONException e) {
            ThrowableExtension.printStackTrace(e);
            return str2;
        }
    }

    private int getUTSFromResponse(String str) {
        try {
            return new JSONObject(str).optInt("uts", 1);
        } catch (JSONException unused) {
            return 1;
        }
    }

    private int getUserState(String str) {
        try {
            return new JSONObject(str).optInt("state", 0);
        } catch (JSONException e) {
            ThrowableExtension.printStackTrace(e);
            return 0;
        }
    }

    public void isSsoSdkInitialized(@NonNull final SsoSdkInitialized ssoSdkInitialized) {
        if (this.mSsoSDKState == 2) {
            ssoSdkInitialized.onError();
            return;
        }
        if (in.til.core.a.b().a(new in.til.core.integrations.c() {
            public void onSdkFailure(TILSDKExceptionDto tILSDKExceptionDto) {
                LoginManager.this.mSsoSDKState = 0;
                ssoSdkInitialized.onError();
            }
        })) {
            this.mSsoSDKState = 1;
            ssoSdkInitialized.onSuccess();
        } else {
            this.mSsoSDKState = 2;
            new Thread(new Runnable() {
                public void run() {
                    in.til.core.a.b().a(GaanaApplication.getContext(), "androidcontentprovidersso.tg.com.gaana", "3dec10996029539e7c6875b2f5d5c509", "gaana.com", new com.login.nativesso.a.s() {
                        public void onSuccess() {
                            LoginManager.this.mSsoSDKState = 1;
                            ssoSdkInitialized.onSuccess();
                        }

                        public void onFailure(c cVar) {
                            LoginManager.this.mSsoSDKState = 0;
                            ssoSdkInitialized.onError();
                        }

                        public void onSdkFailure(TILSDKExceptionDto tILSDKExceptionDto) {
                            LoginManager.this.mSsoSDKState = 0;
                            ssoSdkInitialized.onError();
                        }
                    });
                }
            }).start();
        }
    }

    public LoginMode getDefaultLoginMode() {
        return this.defaultLoginMode;
    }

    public void setDefaultLoginMode(LoginMode loginMode) {
        this.defaultLoginMode = loginMode;
    }

    public void getLoginMode() {
        com.i.b bVar = new com.i.b("https://api.gaana.com/index.php?type=get_login_mode", String.class, new e.a() {
            public void onErrorResponse(BusinessObject businessObject) {
            }

            public void onDataRetrieved(Object obj, boolean z) {
                try {
                    if (new JSONObject((String) obj).optString("login_mode", LoginManager.TAG_SUBTYPE_GAANA).equalsIgnoreCase(ServerProtocol.DIALOG_PARAM_SSO_DEVICE)) {
                        LoginManager.this.defaultLoginMode = LoginMode.SSO;
                    } else {
                        LoginManager.this.defaultLoginMode = LoginMode.GAANA;
                    }
                } catch (JSONException unused) {
                }
            }
        });
        bVar.a(false);
        i.a().a(bVar);
    }

    public void updateSocialMeta(JSONObject jSONObject) {
        com.i.b bVar = new com.i.b("https://api.gaana.com/user.php?type=nxtgen_update_social_meta&social_meta=<meta_content>".replace("<meta_content>", jSONObject.toString()), String.class, new e.a() {
            public void onErrorResponse(BusinessObject businessObject) {
            }

            public void onDataRetrieved(Object obj, boolean z) {
                try {
                    if (new JSONObject((String) obj).optBoolean("status", false)) {
                        d.a().a(System.currentTimeMillis(), "pref_social_token_last_refreshed", false);
                    }
                } catch (JSONException unused) {
                }
            }
        });
        bVar.a(false);
        i.a().a(bVar);
    }

    public void refreshMyProfile(final s sVar) {
        if (!this.isMyProfileRefreshing) {
            this.isMyProfileRefreshing = true;
            URLManager uRLManager = new URLManager();
            uRLManager.b(Boolean.valueOf(false));
            uRLManager.a("https://api.gaana.com/user.php?type=profile");
            i.a().a(new af() {
                public void onRetreivalComplete(Object obj) {
                    try {
                        JSONObject jSONObject = new JSONObject((String) obj);
                        if (jSONObject.has("data")) {
                            ProfileData profileData = (ProfileData) new GsonBuilder().excludeFieldsWithModifiers(8, 4).create().fromJson(jSONObject.getString("data"), ProfileData.class);
                            MyProfile userProfile = LoginManager.this.mUserInfo.getUserProfile();
                            if (!(userProfile == null || profileData == null)) {
                                boolean z;
                                if (TextUtils.isEmpty(profileData.getImg())) {
                                    z = false;
                                } else {
                                    userProfile.setImg(profileData.getImg());
                                    z = true;
                                }
                                if (!TextUtils.isEmpty(profileData.getFullname())) {
                                    userProfile.setFullname(profileData.getFullname());
                                    z = true;
                                }
                                if (!TextUtils.isEmpty(profileData.getSex())) {
                                    userProfile.setSex(profileData.getSex());
                                    z = true;
                                }
                                userProfile.setIs_voice_interaction(profileData.isVoiceInteractionEnabled());
                                LoginManager.this.mUserInfo.setIs_social(profileData.getIs_social_active());
                                if (z) {
                                    LoginManager.this.mUserInfo.setUserProfile(userProfile);
                                }
                            }
                            LoginManager.this.mUserInfo.setLastMyProfileRefreshTime(System.currentTimeMillis());
                            LoginManager.this.saveUserInfoInSharedPreff();
                            if (!(ai.a() == null || !(ai.a() instanceof GaanaActivity) || ((Activity) ai.a()).isFinishing())) {
                                ((GaanaActivity) ai.a()).updateSidebarUserDetails();
                            }
                        }
                    } catch (JSONException e) {
                        ThrowableExtension.printStackTrace(e);
                    }
                    if (sVar != null) {
                        sVar.onRetreivalComplete(null);
                    }
                    LoginManager.this.isMyProfileRefreshing = false;
                }

                public void onErrorResponse(BusinessObject businessObject) {
                    if (sVar != null) {
                        sVar.onErrorResponse(businessObject);
                    }
                    LoginManager.this.isMyProfileRefreshing = false;
                }
            }, uRLManager);
        }
    }

    public void sendUserLogOutEvent(int i, String str) {
        if (i != -1) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(i);
            stringBuilder.append(" ");
            stringBuilder.append(str);
            str = stringBuilder.toString();
        }
        u.a().a("Login", "Logout", str);
    }

    public ak getTimesPointLogger() {
        if (this.timesPointLogger == null) {
            this.timesPointLogger = new ak();
        }
        return this.timesPointLogger;
    }
}
