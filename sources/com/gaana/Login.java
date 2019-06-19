package com.gaana;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.widget.ImageView;
import com.constants.Constants;
import com.gaana.application.GaanaApplication;
import com.gaana.login.FbLoginErrorDialog;
import com.gaana.login.GooglePlusLogin;
import com.gaana.login.GooglePlusLogin.OnEmailAutoSignInGoogle;
import com.gaana.login.GooglePlusLogin.OnGooglePlusLoginListner;
import com.gaana.login.LoginInfo;
import com.gaana.login.LoginManager;
import com.gaana.login.LoginManager.IOnLoginCompleted;
import com.gaana.login.LoginManager.LOGIN_STATUS;
import com.gaana.login.UserInfo;
import com.gaana.login.fragments.EmailLoginFragment;
import com.gaana.login.fragments.LoginFragment;
import com.gaana.login.fragments.SignupFragment;
import com.gaana.login.fragments.VerifyOtpFragment;
import com.gaana.models.Languages;
import com.gaana.models.Languages.Language;
import com.gaana.models.User.LoginType;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.managers.aj;
import com.managers.an;
import com.managers.u;
import com.managers.w;
import com.managers.w.a;
import com.services.c;
import com.services.d;
import com.services.f;
import com.services.f.b;
import com.services.m;
import com.utilities.Util;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;

public class Login extends BaseActivity implements IOnLoginCompleted {
    public static final String LAUNCH_EMAIL_LOGIN_SCREEN = "email_login";
    public static boolean isSignupFromInside;
    private final int DO_NOT_SHOW_DIALOG = 1;
    private final int SHOW_DIALOG = 0;
    private int dialogStatus = 0;
    private boolean doubleBackToExitPressedOnce = false;
    public String emailId;
    public Handler handler;
    boolean isBackEnabled = false;
    boolean isEmailLoginScreen = false;
    public boolean isFromDeferredDeepLink = false;
    boolean isLaunchedFromConsentScreen = false;
    public boolean isLoginStartedForResult = false;
    public boolean isLoginViewVisible = false;
    boolean isPhoneLoginScreen = false;
    public boolean isShufflePlayResult;
    private b mDialogListner = new b() {
        public void onCancelListner() {
        }

        public void onOkListner(String str) {
            LoginInfo loginInfo = new LoginInfo();
            loginInfo.setPassword(Login.this.password);
            loginInfo.setEmailId(Login.this.emailId);
        }
    };
    private f mDialogs;
    public ArrayList<Language> mLanguageList = new ArrayList();
    private b mOkDialogListner = new b() {
        public void onCancelListner() {
        }

        public void onOkListner(String str) {
            ((Login) Login.this.mContext).isLoginViewVisible = false;
        }
    };
    private Bitmap mSelectedImage = null;
    private OnEmailAutoSignInGoogle onEmailAutoSignInGoogle = null;
    public String password;
    public Runnable runnable;
    private boolean shouldHideSkip = false;
    private boolean showSmartLockDialog = true;

    public void setSignupEmailPwd(String str, String str2) {
    }

    /* Access modifiers changed, original: protected */
    public void attachBaseContext(Context context) {
        super.attachBaseContext(com.b.b.a(context));
    }

    /* Access modifiers changed, original: protected */
    public void onStart() {
        super.onStart();
        this.handler = new Handler();
        if (!Util.a((Activity) this) || !this.showSmartLockDialog) {
            return;
        }
        if (Constants.t == 1 && this.dialogStatus != 1) {
            this.runnable = new Runnable() {
                public void run() {
                    Login.this.dialogStatus = 1;
                    u.a().b("Auto_SignUp", "Wait time");
                    GooglePlusLogin.getInstance().requestCredentials(true, false);
                }
            };
            this.handler.postDelayed(this.runnable, (long) Constants.s);
        } else if (this.dialogStatus != 1) {
            this.dialogStatus = 1;
            GooglePlusLogin.getInstance().requestCredentials(true, false);
        }
    }

    public void removeGoogleSignSmartDialog() {
        if (((Login) this.mContext).handler != null) {
            ((Login) this.mContext).handler.removeCallbacks(((Login) this.mContext).runnable);
        }
    }

    public void onCreate(Bundle bundle) {
        if (Constants.l) {
            setTheme(R.style.GaanaAppThemeWhite);
        }
        super.onCreate(bundle);
        setContentView((int) R.layout.login);
        LoginManager.getInstance().initSsoSdk(getApplicationContext());
        LoginManager.getInstance().getLoginMode();
        this.mDialogs = new f(this.mContext);
        Intent intent = getIntent();
        if (intent != null) {
            isSignupFromInside = intent.getBooleanExtra("ONBOARD_SIGNUP_FROM_APP_INSIDE", false);
            this.isFromDeferredDeepLink = intent.getBooleanExtra("IS_FROM_DEFERRED_DEEPLINK", false);
            this.isPhoneLoginScreen = intent.getBooleanExtra("DEEPLINKING_PHONE_LOGIN", false);
            this.isEmailLoginScreen = intent.getBooleanExtra(LAUNCH_EMAIL_LOGIN_SCREEN, false);
            this.isLoginStartedForResult = intent.getBooleanExtra("is_login_as_activity_result", false);
            this.isShufflePlayResult = intent.getBooleanExtra("is_shuffle_result", false);
            this.isLaunchedFromConsentScreen = intent.getBooleanExtra("IS_LAUNCHED_FROM_CONSENT_SCREEN", false);
            if (intent.getExtras() != null) {
                this.mLanguageList = (ArrayList) getIntent().getExtras().getSerializable("languageList");
            }
        }
        if (this.isFromDeferredDeepLink) {
            this.shouldHideSkip = true;
            d.a().a("DEFERRED_DEEPLINK_ONBOARDING_STATE", "ONBOARD_STATE_LOGIN", false);
        } else if (d.a().b("PREFERENCE_MANDATORY_SIGNUP", 0, false) == 1) {
            this.shouldHideSkip = true;
        } else if (!isSignupFromInside || Constants.r == 1) {
            this.shouldHideSkip = false;
        } else {
            this.shouldHideSkip = true;
        }
        if (intent != null && intent.getSerializableExtra("temp_user_tag") != null) {
            LoginInfo loginInfo = (LoginInfo) intent.getSerializableExtra("temp_user_tag");
        } else if (this.isEmailLoginScreen) {
            initEmailLoginScreen();
        } else if (this.isPhoneLoginScreen) {
            initPhoneLoginScreen();
        } else {
            this.currentScreen = "Login";
            bundle = getIntent().getExtras();
            initOnBoardLogin();
            LoginManager.getInstance().setmActivityReference(this);
            LoginManager.getInstance().setmOnLoginCompleted(this);
            this.onEmailAutoSignInGoogle = new OnEmailAutoSignInGoogle() {
                public void onEmailSignIn(String str, String str2) {
                    Login.this.setLoginEmailPwd(str, str2);
                    Util.a(str, str2, Login.this.mDialogs, Login.this.mDialogListner, Login.this, (Activity) Login.this.mContext, false, Login.this.mOkDialogListner);
                }
            };
            GooglePlusLogin.getInstance().buildGoogleApiClient(this, null, new OnGooglePlusLoginListner() {
                public void onLoginFailed(String str) {
                }

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
                    loginInfo.setLoginMode(LoginManager.getInstance().getDefaultLoginMode());
                    LoginManager.getInstance().getLoginClient(LoginType.GOOGLE).login(loginInfo, Login.this);
                }
            }, this.onEmailAutoSignInGoogle);
            if (bundle != null) {
                bundle.getBoolean("ONBOARD_SIGNUP");
            }
        }
    }

    /* Access modifiers changed, original: protected */
    public void onResume() {
        super.onResume();
    }

    public void initOnBoardLogin() {
        displayFragment(LoginFragment.newInstance(this.shouldHideSkip), true);
        ((BaseActivity) this.mContext).setGoogleAnalyticsScreenName("LoginScreen");
    }

    public void initOnBoardSignup(@Nullable LoginInfo loginInfo) {
        displayFragment(SignupFragment.newInstance(this.shouldHideSkip, loginInfo), true);
        ((BaseActivity) this.mContext).setGoogleAnalyticsScreenName("RegistrationScreen");
    }

    public void initEmailLoginScreen() {
        LoginManager.getInstance().setmActivityReference(this);
        LoginManager.getInstance().setmOnLoginCompleted(this);
        GooglePlusLogin.getInstance().buildGoogleApiClient(this, null, new OnGooglePlusLoginListner() {
            public void onLoginFailed(String str) {
            }

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
                loginInfo.setLoginMode(LoginManager.getInstance().getDefaultLoginMode());
                LoginManager.getInstance().getLoginClient(LoginType.GOOGLE).login(loginInfo, Login.this);
            }
        }, this.onEmailAutoSignInGoogle);
        displayFragment(EmailLoginFragment.newInstance("", ""), true);
        ((BaseActivity) this.mContext).setGoogleAnalyticsScreenName("LoginScreen");
        an.a().a("click", "ac", "", "Login", "", "Signup", "", "");
    }

    public void initPhoneLoginScreen() {
        this.currentScreen = "Login";
        initOnBoardLogin();
        if (this.isPhoneLoginScreen) {
            this.showSmartLockDialog = false;
        }
        LoginManager.getInstance().setmActivityReference(this);
        LoginManager.getInstance().setmOnLoginCompleted(this);
        this.onEmailAutoSignInGoogle = new OnEmailAutoSignInGoogle() {
            public void onEmailSignIn(String str, String str2) {
                Login.this.setLoginEmailPwd(str, str2);
                Util.a(str, str2, Login.this.mDialogs, Login.this.mDialogListner, Login.this, (Activity) Login.this.mContext, false, Login.this.mOkDialogListner);
            }
        };
        GooglePlusLogin.getInstance().buildGoogleApiClient(this, null, new OnGooglePlusLoginListner() {
            public void onLoginFailed(String str) {
            }

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
                loginInfo.setLoginMode(LoginManager.getInstance().getDefaultLoginMode());
                LoginManager.getInstance().getLoginClient(LoginType.GOOGLE).login(loginInfo, Login.this);
            }
        }, this.onEmailAutoSignInGoogle);
        LoginManager.getInstance().login((Activity) this.mContext, LoginType.PHONENUMBER, (Login) this.mContext);
    }

    public void setLoginEmailPwd(String str, String str2) {
        this.emailId = str;
        this.password = str2;
    }

    public void loginStartedForResult() {
        ((Activity) this.mContext).setResult(701);
    }

    /* Access modifiers changed, original: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        hideProgressDialog();
        if (i == 1) {
            this.dialogStatus = 1;
            m.a().a(i, i2, intent);
        }
        if (i == 706) {
            if (i2 == -1) {
                try {
                    this.mSelectedImage = Constants.a((Context) this, intent.getData());
                    ((ImageView) findViewById(R.id.imgArtwork)).setImageBitmap(this.mSelectedImage);
                    return;
                } catch (FileNotFoundException e) {
                    ThrowableExtension.printStackTrace(e);
                    return;
                }
            }
            this.mSelectedImage = null;
        }
    }

    public void setIsBackEnabled(boolean z) {
        this.isBackEnabled = z;
    }

    public void onBackPressed() {
        if (this.isFromDeferredDeepLink) {
            d.a().b("DEFERRED_DEEPLINK_ONBOARDING_STATE", false);
        }
        if (this.isBackEnabled) {
            super.onBackPressed();
            this.isBackEnabled = false;
        } else if (isSignupFromInside && this.shouldHideSkip) {
            performDoubleClickExit();
        } else {
            if (Constants.p && isSignupFromInside) {
                u.a().b("Registration", "Skip");
                Intent intent = new Intent(GaanaApplication.getContext(), AppLanguageSettingsScreenActivity.class);
                intent.putExtra("ONBOARD_SIGNUP_FROM_APP_INSIDE", false);
                intent.putExtra("languageList", ((Login) this.mContext).mLanguageList);
                if (this.isFromDeferredDeepLink) {
                    intent.putExtra("IS_FROM_DEFERRED_DEEPLINK", true);
                    d.a().a("DEFERRED_DEEPLINK_ONBOARDING_STATE", "ONBOARD_STATE_DISP_LANG", false);
                }
                ((Login) this.mContext).startActivity(intent);
                finish();
            }
            if (getSupportFragmentManager().getBackStackEntryCount() <= 1) {
                finish();
            } else {
                super.onBackPressed();
            }
        }
    }

    private void performDoubleClickExit() {
        if (this.doubleBackToExitPressedOnce) {
            finish();
            return;
        }
        this.doubleBackToExitPressedOnce = true;
        String b = d.a().b("PREFERENCE_BACKPRESSED_MESSAGE", null, false);
        if (!TextUtils.isEmpty(b)) {
            aj.a().a(this.mContext, b);
        }
        new Handler().postDelayed(new Runnable() {
            public void run() {
                Login.this.doubleBackToExitPressedOnce = false;
            }
        }, AdaptiveTrackSelection.DEFAULT_MIN_TIME_BETWEEN_BUFFER_REEVALUTATION_MS);
    }

    public void onRequestPermissionsResult(int i, @NonNull String[] strArr, @NonNull int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        if (i == 104) {
            loginWithGooglePlus();
        }
    }

    public void loginWithGooglePlus() {
        BaseActivity baseActivity = (BaseActivity) this.mContext;
        String str = ((BaseActivity) this.mContext).currentScreen;
        String str2 = ((BaseActivity) this.mContext).currentScreen;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(((BaseActivity) this.mContext).currentScreen);
        stringBuilder.append(" - Google Plus");
        baseActivity.sendGAEvent(str, str2, stringBuilder.toString());
        LoginManager.getInstance().login((Activity) this.mContext, LoginType.GOOGLE, (IOnLoginCompleted) this);
    }

    public void onLoginCompleted(LOGIN_STATUS login_status, UserInfo userInfo, Bundle bundle) {
        VerifyOtpFragment newInstance;
        switch (login_status) {
            case LOGGED_OUT:
                Util.J();
                return;
            case LOGIN_SUCCEDED:
                if (((Activity) this.mContext).getIntent().getBooleanExtra("is_login_as_activity_result", false)) {
                    ((Activity) this.mContext).setResult(701);
                } else if (((Activity) this.mContext).getIntent().getBooleanExtra("is_shuffle_result", false)) {
                    ((Activity) this.mContext).setResult(711);
                } else {
                    if (this.mLanguageList == null) {
                        this.mLanguageList = w.a(this.mAppState).a(this.mContext);
                    }
                    if (this.mLanguageList != null && this.mLanguageList.size() > 0) {
                        w.a(this.mAppState).a(this.mAppState, this.mLanguageList);
                    }
                    launchHome();
                }
                if (this.isLaunchedFromConsentScreen) {
                    Util.z(this.mContext);
                }
                if (userInfo.getLoginType() == LoginType.PHONENUMBER && ((Activity) this.mContext).getIntent().getBooleanExtra("is_login_as_activity_result", false)) {
                    onActivityResult(701, -1, null);
                }
                ((Activity) this.mContext).finish();
                return;
            case LOGIN_ERROR_LAUNCH_TRAP_PAGE:
                launchTrapPage(userInfo);
                return;
            case LOGIN_ERROR_AUTHENTICATION_FAILED:
            case LOGIN_FAILURE_SSO:
            case LOGIN_FAILURE_SDK_NOT_INITIALIZED:
                if (userInfo == null || userInfo.getError() == null) {
                    aj.a().a(this.mContext, this.mContext.getString(R.string.login_failed));
                } else {
                    aj.a().a(this.mContext, userInfo.getError());
                }
                ((BaseActivity) this.mContext).hideProgressDialog();
                return;
            case LOGIN_REGISTRATION_VERIFY:
                if (bundle != null) {
                    newInstance = VerifyOtpFragment.newInstance(bundle.getString("extra_email"), false);
                    newInstance.setLoginCompletedListener(this);
                    ((Login) this.mContext).displayFragment(newInstance, false);
                    return;
                } else if (userInfo == null || userInfo.getError() == null) {
                    aj.a().a(this.mContext, this.mContext.getString(R.string.login_failed));
                    return;
                } else {
                    aj.a().a(this.mContext, userInfo.getError());
                    return;
                }
            case LOGIN_VERIFY_USER:
                if (bundle != null) {
                    newInstance = VerifyOtpFragment.newInstance(bundle.getString("extra_email"), true);
                    newInstance.setLoginCompletedListener(this);
                    ((Login) this.mContext).displayFragment(newInstance, false);
                    return;
                }
                aj.a().a(this.mContext, this.mContext.getString(R.string.login_failed));
                return;
            case LOGIN_ERROR_UNKNOWN:
            case LOGIN_ERROR_NETWORK:
                aj.a().a(this.mContext, this.mContext.getString(R.string.error_msg_unexpected_error));
                return;
            case LOGIN_MANDATORY_FIELD_MISSING:
                if (!isFinishing() && this.mDialogs != null) {
                    this.mDialogs.a(this.mContext.getResources().getString(R.string.mandatory_field_missing));
                    return;
                }
                return;
            case LOGIN_EMAIL_MISSING_FB:
                if (!isFinishing()) {
                    FbLoginErrorDialog fbLoginErrorDialog = new FbLoginErrorDialog(this.mContext);
                    fbLoginErrorDialog.setOnLoginCompletedListener(this);
                    fbLoginErrorDialog.show();
                    return;
                }
                return;
            default:
                return;
        }
    }

    public void launchHome() {
        if (GaanaApplication.sessionHistoryCount == 0 && Util.j(this.mContext) && c.a((Context) this).a((Context) this, this.mAppState, false)) {
            finish();
        } else if (!this.isFromDeferredDeepLink || !Util.j(this.mContext)) {
            launchHomeScreen();
        } else if (!this.isFromDeferredDeepLink) {
        } else {
            if (Constants.T) {
                launchInternationalOnBoarding();
            } else if (d.a().b("PREFERENCE_LANGUAGE_ONBOARD", 0, false) == 0) {
                launchLanguageScreen();
            } else if (Constants.m) {
                launchAppDisplayLanguageScreen();
            }
        }
    }

    private void launchHomeScreen() {
        if (this.isFromDeferredDeepLink) {
            d.a().a("DEFERRED_DEEPLINK_ONBOARDING_STATE", null, false);
        }
        Intent intent = new Intent(this.mContext, GaanaActivity.class);
        intent.setFlags(71303168);
        startActivity(intent);
        finish();
    }

    private void launchInternationalOnBoarding() {
        if (this.isFromDeferredDeepLink) {
            d.a().a("DEFERRED_DEEPLINK_ONBOARDING_STATE", null, false);
            GaanaApplication.onBoardingSkipped = false;
        }
        Constants.X = false;
        Intent intent = new Intent(this.mContext, InternationalOnBoardingActivity.class);
        intent.setFlags(603979776);
        startActivity(intent);
        finish();
    }

    private void launchLanguageScreen() {
        Intent intent = new Intent(this.mContext, OnBoardLanguagePreferenceActivityNew.class);
        intent.setFlags(603979776);
        if (this.isFromDeferredDeepLink) {
            intent.putExtra("IS_FROM_DEFERRED_DEEPLINK", true);
            d.a().a("DEFERRED_DEEPLINK_ONBOARDING_STATE", "ONBOARD_STATE_SONG_LANG", false);
        }
        startActivity(intent);
        finish();
    }

    private void launchAppDisplayLanguageScreen() {
        w.a(GaanaApplication.getInstance()).a((Context) this, new a() {
            public void onLanguagesFetched(Languages languages) {
                if (languages == null || languages.getArrListBusinessObj() == null) {
                    aj.a().a(Login.this, Login.this.getResources().getString(R.string.error_msg_no_connection));
                    return;
                }
                if (languages.getAppDisplayPageNeededToDisplay()) {
                    Constants.p = true;
                }
                Constants.t = languages.getWait_time_switch();
                Constants.s = languages.getWait_time();
                Constants.q = languages.getLogin_switch();
                Constants.r = languages.getLogin_skip();
                Constants.z = languages.getAutologin_email();
                Constants.A = languages.getAutologin_email_switch();
                Constants.B = languages.getMandatory_signup();
                ArrayList arrListBusinessObj = languages.getArrListBusinessObj();
                Iterator it = arrListBusinessObj.iterator();
                String str = null;
                int i = 0;
                while (it.hasNext()) {
                    Language language = (Language) it.next();
                    if (language.isPrefered() == 1) {
                        str = language.getLanguage();
                        i++;
                    }
                }
                if (!Constants.p || (i <= 1 && (i != 1 || str.equalsIgnoreCase("English")))) {
                    Login.this.launchHomeScreen();
                    return;
                }
                Intent intent = new Intent(Login.this, AppLanguageSettingsScreenActivity.class);
                intent.putExtra("languageList", arrListBusinessObj);
                if (Login.this.isFromDeferredDeepLink) {
                    intent.putExtra("IS_FROM_DEFERRED_DEEPLINK", true);
                    d.a().a("DEFERRED_DEEPLINK_ONBOARDING_STATE", "ONBOARD_STATE_DISP_LANG", false);
                }
                Login.this.startActivity(intent);
                Login.this.finish();
            }
        }, false);
    }

    /* Access modifiers changed, original: protected */
    public void onStop() {
        super.onStop();
        if (this.handler != null) {
            this.handler.removeCallbacks(this.runnable);
        }
    }

    /* Access modifiers changed, original: protected */
    public void onDestroy() {
        LoginManager.getInstance().setLoginInProcess(false);
        LoginManager.getInstance().removeOnLoginCompleted();
        this.onEmailAutoSignInGoogle = null;
        super.onDestroy();
    }

    public void launchTrapPage(UserInfo userInfo) {
        Intent intent = new Intent(this.mContext, Login.class);
        intent.setFlags(C.ENCODING_PCM_MU_LAW);
        intent.putExtra("temp_user_tag", LoginManager.getInstance().getLoginInfo());
        if (!(userInfo == null || userInfo.getError() == null)) {
            intent.putExtra("message", userInfo.getError());
        }
        this.mContext.startActivity(intent);
        if (this.mContext instanceof Login) {
            ((Login) this.mContext).finish();
        }
    }

    public void displayFragment(Fragment fragment, boolean z) {
        if (fragment != null) {
            int i = 0;
            this.showSmartLockDialog = !(fragment instanceof VerifyOtpFragment);
            if (z) {
                try {
                    i = getSupportFragmentManager().popBackStackImmediate(fragment.getClass().getName(), 0);
                } catch (IllegalStateException unused) {
                    return;
                }
            }
            if (i == 0) {
                FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
                beginTransaction.replace(R.id.frame_container, fragment);
                beginTransaction.addToBackStack(fragment.getClass().getName());
                beginTransaction.commitAllowingStateLoss();
            }
        }
    }
}
