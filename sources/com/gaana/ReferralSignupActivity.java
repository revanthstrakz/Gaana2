package com.gaana;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView.ScaleType;
import android.widget.TextView;
import android.widget.Toast;
import com.b.b;
import com.constants.Constants;
import com.gaana.analytics.MoEngage;
import com.gaana.application.GaanaApplication;
import com.gaana.login.FbLoginErrorDialog;
import com.gaana.login.GooglePlusLogin;
import com.gaana.login.LoginManager;
import com.gaana.login.LoginManager.IOnLoginCompleted;
import com.gaana.login.LoginManager.LOGIN_STATUS;
import com.gaana.login.UserInfo;
import com.gaana.models.Languages;
import com.gaana.models.Languages.Language;
import com.gaana.models.ReferralSignup;
import com.gaana.models.User.LoginType;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.library.controls.CircularImageView;
import com.library.controls.CrossFadeImageView.ImageLoadingCompeletedListener;
import com.managers.ag;
import com.managers.aj;
import com.managers.ap;
import com.managers.o;
import com.managers.w;
import com.managers.w.a;
import com.services.c;
import com.services.d;
import com.services.f;
import com.services.g;
import com.services.l.au;
import com.simpl.android.fingerprint.SimplFingerprint;
import com.simpl.android.fingerprint.SimplFingerprintListener;
import com.utilities.Util;
import com.utilities.h;
import java.util.ArrayList;
import java.util.Calendar;

public class ReferralSignupActivity extends Activity implements OnClickListener, IOnLoginCompleted {
    private final int FACEBOOK_LOGIN = 0;
    private final int GOOGLE_LOGIN = 1;
    private int LOGIN_STATE = -1;
    private boolean doubleBackToExitPressedOnce = false;
    private boolean fromInternationalOnboarding = false;
    private long initialTime;
    private GaanaApplication mAppState;
    private Context mContext;
    private ProgressDialog mProgressDialog = null;
    private ReferralSignup mReferralSignupDetails;
    private ArrayList<Language> savedLanguages = null;

    /* Access modifiers changed, original: protected */
    public void attachBaseContext(Context context) {
        super.attachBaseContext(b.a(context));
    }

    /* Access modifiers changed, original: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.referral_signup_screen);
        this.mContext = this;
        this.mAppState = GaanaApplication.getInstance();
        if (bundle != null) {
            this.mReferralSignupDetails = (ReferralSignup) bundle.getSerializable("REFERRAL_SIGNUP_DETAILS");
        }
        if (this.mReferralSignupDetails == null) {
            this.mReferralSignupDetails = (ReferralSignup) getIntent().getSerializableExtra("referralInfo");
        }
        this.fromInternationalOnboarding = getIntent().getBooleanExtra("FROM_INTERNATIONAL_ONBOARDING", false);
        TextView textView = (TextView) findViewById(R.id.referral_signup_header);
        if (this.mReferralSignupDetails != null) {
            String referreeName = this.mReferralSignupDetails.getReferreeName();
            if (TextUtils.isEmpty(referreeName)) {
                textView.setText(R.string.your_friend_has_invited_you_to_gaana);
            } else {
                referreeName = referreeName.split(" ")[0];
                if (referreeName.length() > 1) {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(referreeName.substring(0, 1).toUpperCase());
                    stringBuilder.append(referreeName.substring(1));
                    referreeName = stringBuilder.toString();
                } else {
                    referreeName = referreeName.toUpperCase();
                }
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append(referreeName);
                stringBuilder2.append(getString(R.string.has_invited_you_to_gaana));
                textView.setText(stringBuilder2.toString());
            }
            ((CircularImageView) findViewById(R.id.friend_pic)).bindImage(this.mReferralSignupDetails.getReferreeArtwork(), (ImageLoadingCompeletedListener) new ImageLoadingCompeletedListener() {
                public void onError() {
                }

                public void onImageLoadingCompeleted(Bitmap bitmap) {
                }
            }, ScaleType.CENTER_CROP);
        }
        Button button = (Button) findViewById(R.id.google_onboard_signup_btn);
        ((Button) findViewById(R.id.fb_onboard_signup_btn)).setOnClickListener(this);
        button.setOnClickListener(this);
        w.a(this.mAppState).a(this.mContext, new a() {
            public void onLanguagesFetched(Languages languages) {
                if (languages != null) {
                    ReferralSignupActivity.this.savedLanguages = languages.getArrListBusinessObj();
                }
            }
        });
    }

    /* Access modifiers changed, original: protected */
    public void onStart() {
        super.onStart();
    }

    /* Access modifiers changed, original: protected */
    public void onResume() {
        super.onResume();
    }

    /* Access modifiers changed, original: protected */
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putSerializable("REFERRAL_SIGNUP_DETAILS", this.mReferralSignupDetails);
    }

    public void onBackPressed() {
        if (c.a(this.mContext).a()) {
            finish();
        }
        if (Constants.T && this.mReferralSignupDetails != null) {
            finish();
        }
        onBackPressedHandling();
    }

    public void onBackPressedHandling() {
        if (c.a(this.mContext).a()) {
            finish();
        }
        if (Constants.T && this.mReferralSignupDetails != null) {
            finish();
        }
        performDoubleClickExit();
    }

    private void performDoubleClickExit() {
        if (this.doubleBackToExitPressedOnce) {
            if (VERSION.SDK_INT >= 16) {
                finishAffinity();
            } else {
                finish();
            }
            return;
        }
        this.doubleBackToExitPressedOnce = true;
        aj.a().a(this.mContext, getString(R.string.press_again_to_exit));
        new Handler().postDelayed(new Runnable() {
            public void run() {
                ReferralSignupActivity.this.doubleBackToExitPressedOnce = false;
            }
        }, AdaptiveTrackSelection.DEFAULT_MIN_TIME_BETWEEN_BUFFER_REEVALUTATION_MS);
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.fb_onboard_signup_btn) {
            this.mContext = this;
            MoEngage.getInstance().reportLoginStarted("FB");
            if (Util.j((Context) this)) {
                this.LOGIN_STATE = 0;
                LoginManager.getInstance().login((ReferralSignupActivity) this.mContext, LoginType.FB, (IOnLoginCompleted) this);
            } else {
                ap.a().f((Context) this);
            }
        } else if (id == R.id.google_onboard_signup_btn) {
            this.mContext = this;
            MoEngage.getInstance().reportLoginStarted("GOOGLE");
            if (Util.j(this.mContext)) {
                this.LOGIN_STATE = 1;
                if (h.c((ReferralSignupActivity) this.mContext)) {
                    LoginManager.getInstance().login((ReferralSignupActivity) this.mContext, LoginType.GOOGLE, (IOnLoginCompleted) this);
                }
            } else {
                ap.a().f(this.mContext);
            }
        }
    }

    public void hideProgressDialog() {
        try {
            if (this.mProgressDialog != null && this.mProgressDialog.isShowing()) {
                this.mProgressDialog.dismiss();
            }
        } catch (Exception unused) {
        }
    }

    public void showProgressDialog(Boolean bool, String str) {
        if (!isFinishing()) {
            try {
                StringBuilder stringBuilder;
                if (this.mProgressDialog == null) {
                    stringBuilder = new StringBuilder();
                    stringBuilder.append(str);
                    stringBuilder.append("\t");
                    this.mProgressDialog = ProgressDialog.show(this, "", stringBuilder.toString(), true, bool.booleanValue());
                } else if (this.mProgressDialog.isShowing()) {
                    this.mProgressDialog.dismiss();
                    stringBuilder = new StringBuilder();
                    stringBuilder.append(str);
                    stringBuilder.append("\t");
                    this.mProgressDialog = ProgressDialog.show(this, "", stringBuilder.toString(), true, bool.booleanValue());
                } else {
                    stringBuilder = new StringBuilder();
                    stringBuilder.append(str);
                    stringBuilder.append("\t");
                    this.mProgressDialog = ProgressDialog.show(this, "", stringBuilder.toString(), true, bool.booleanValue());
                }
            } catch (Exception unused) {
            }
        }
    }

    public void onLoginCompleted(LOGIN_STATUS login_status, UserInfo userInfo, Bundle bundle) {
        LOGIN_STATUS login_status2 = LOGIN_STATUS.LOGIN_SUCCEDED;
        switch (login_status) {
            case LOGIN_SUCCEDED:
                if (Constants.T && this.mReferralSignupDetails != null) {
                    setResult(-1);
                } else if (((Activity) this.mContext).getIntent().getBooleanExtra("is_login_as_activity_result", false)) {
                    ((Activity) this.mContext).setResult(701);
                    if (d.a().b("ONBOARD_NEW_USER", false, false)) {
                        launchLoginPreferenceOnBoard(false);
                    }
                } else {
                    launchHome();
                }
                ((Activity) this.mContext).finish();
                return;
            case LOGIN_ERROR_LAUNCH_TRAP_PAGE:
                launchTrapPage(userInfo);
                break;
            case LOGIN_ERROR_AUTHENTICATION_FAILED:
            case LOGIN_FAILURE_SSO:
            case LOGIN_FAILURE_SDK_NOT_INITIALIZED:
                break;
            case LOGIN_MANDATORY_FIELD_MISSING:
                if (!isFinishing()) {
                    new f(this.mContext).a(this.mContext.getResources().getString(R.string.mandatory_field_missing));
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
        if (userInfo == null || userInfo.getError() == null) {
            aj.a().a(this.mContext, getString(R.string.signup_failed_try_again_later));
        } else {
            aj.a().a(this.mContext, userInfo.getError());
        }
        hideProgressDialog();
    }

    private void launchLoginPreferenceOnBoard(boolean z) {
        Intent intent = new Intent(this.mContext, OnBoardLanguagePreferenceActivityNew.class);
        if (z) {
            intent.setFlags(603979776);
        } else {
            intent.putExtra("ONBOARD_LAUNCH_HOME_SCREEN", false);
            intent.setFlags(4194304);
        }
        if (this.savedLanguages != null && d.a().b("PREFERENCE_LANGUAGE_ONBOARD", 0, false) == 1) {
            intent.putExtra("ONBOARD_SAVED_LANG_PREF", this.savedLanguages);
        }
        this.mContext.startActivity(intent);
    }

    private void launchHome() {
        if (Util.j(this.mContext) && !this.mAppState.isAppInOfflineMode() && d.a().b("PREFERENCE_LANGUAGE_ONBOARD", 0, false) == 0) {
            launchLoginPreferenceOnBoard(true);
        } else if (Util.j(this.mContext) && !this.mAppState.isAppInOfflineMode() && d.a().b("ONBOARD_NEW_USER", false, false)) {
            launchLoginPreferenceOnBoard(true);
        } else {
            Intent intent = new Intent(this.mAppState, GaanaActivity.class);
            intent.setFlags(603979776);
            if (this.fromInternationalOnboarding) {
                intent.putExtra("removePaymentScreen", true);
            }
            this.mContext.startActivity(intent);
        }
    }

    private void launchTrapPage(UserInfo userInfo) {
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

    /* Access modifiers changed, original: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        if (i != 708) {
            if (i != 64206) {
                switch (i) {
                    case 701:
                        if (i2 != 0) {
                            o.a().b();
                            return;
                        }
                        return;
                    case 702:
                        break;
                    case GooglePlusLogin.GOOGLE_PLUS_REQUEST_CODE /*703*/:
                        GooglePlusLogin.getInstance().authorizeCallBack(i, i2, intent);
                        return;
                    default:
                        return;
                }
            }
            g.a().a((Activity) this, i, i2, intent);
            if (i2 == 0) {
                g.a = false;
                g.b++;
                g.a().h();
            }
        } else if (i2 != 0) {
            updateUserStatus(new au() {
                public void onUserStatusUpdated() {
                    Intent intent = new Intent(ReferralSignupActivity.this.mContext, GaanaActivity.class);
                    intent.setFlags(71303168);
                    ReferralSignupActivity.this.mContext.startActivity(intent);
                    if (!(LoginManager.isSimplInitialized || !Constants.bT || GaanaApplication.getInstance().getCurrentUser().getUserProfile() == null || TextUtils.isEmpty(GaanaApplication.getInstance().getCurrentUser().getUserProfile().getPhoneNumber()))) {
                        if (TextUtils.isEmpty(Constants.bM)) {
                            ag.a(ReferralSignupActivity.this.mContext).c();
                            LoginManager.isSimplInitialized = true;
                        } else {
                            SimplFingerprint.init(ReferralSignupActivity.this.mContext, GaanaApplication.getInstance().getCurrentUser().getUserProfile().getPhoneNumber(), GaanaApplication.getInstance().getCurrentUser().getUserProfile().getEmail());
                            SimplFingerprint.getInstance().generateFingerprint(new SimplFingerprintListener() {
                                public void fingerprintData(String str) {
                                    LoginManager.isSimplInitialized = true;
                                    Constants.bO = str;
                                    Constants.bN = true;
                                    ag.a(ReferralSignupActivity.this.mContext).c(ReferralSignupActivity.this.mContext);
                                }
                            });
                        }
                    }
                    ReferralSignupActivity.this.finish();
                }
            });
        } else if (this instanceof ReferralSignupActivity) {
            finish();
        }
    }

    public void updateUserStatus(au auVar) {
        if (!this.mAppState.isAppInOfflineMode() && this.mAppState.getCurrentUser().getLoginStatus()) {
            LoginManager.getInstance().getUserStatus(this, auVar, true);
        }
    }

    public void onRequestPermissionsResult(int i, @NonNull String[] strArr, @NonNull int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        if (iArr.length <= 0 || iArr[0] != 0) {
            if (strArr.length <= 0 || !h.a(this, strArr[0])) {
                Toast.makeText(this, R.string.please_enable_contacts_permission_from_settings, 0).show();
            } else {
                h.a(this, strArr[0], i);
            }
        } else if (i == 104) {
            loginWithGooglePlus();
        }
    }

    public void loginWithGooglePlus() {
        this.LOGIN_STATE = 1;
        this.initialTime = Calendar.getInstance().getTimeInMillis();
        LoginManager.getInstance().login((Activity) this.mContext, LoginType.GOOGLE, (IOnLoginCompleted) this);
    }
}
