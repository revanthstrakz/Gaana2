package com.gaana.login.fragments;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import com.android.volley.Request.Priority;
import com.constants.Constants;
import com.gaana.BaseActivity;
import com.gaana.Login;
import com.gaana.R;
import com.gaana.WebViewActivity;
import com.gaana.analytics.MoEngage;
import com.gaana.application.GaanaApplication;
import com.gaana.login.GooglePlusLogin;
import com.gaana.login.LoginInfo;
import com.gaana.login.LoginManager;
import com.gaana.login.LoginManager.IOnLoginCompleted;
import com.gaana.login.LoginManager.LOGIN_STATUS;
import com.gaana.login.UserInfo;
import com.gaana.models.BusinessObject;
import com.gaana.models.User.LoginMode;
import com.gaana.models.User.LoginType;
import com.gaana.view.item.CustomMaterialDialogView;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.i.i;
import com.integralads.avid.library.inmobi.session.internal.InternalAvidAdSessionContext;
import com.library.managers.TaskManager.TaskListner;
import com.managers.URLManager;
import com.managers.aj;
import com.managers.ap;
import com.managers.u;
import com.services.d;
import com.services.f;
import com.services.f.b;
import com.services.h;
import com.services.j;
import com.services.l.af;
import com.services.o;
import com.utilities.Util;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.message.BasicNameValuePair;

public class SignupDetailsFragment extends Fragment implements OnClickListener {
    public static final String EXTRA_ARG_EMAIL = "extra_email";
    public static final String EXTRA_ARG_PASSWORD = "extra_password";
    public static final String EXTRA_ARG_TRAP_PAGE = "extra_trap_page";
    private int FLAG_EMAIL = 1;
    private int FLAG_IS_VALID = 7;
    private int FLAG_PASSWORD = 4;
    private int FLAG_USERNAME = 2;
    private ImageView backImg;
    private String emailAddress = "";
    private TextWatcher enableLoginButtonWatcher = new TextWatcher() {
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void afterTextChanged(Editable editable) {
            if (SignupDetailsFragment.this.enablelogInButton()) {
                SignupDetailsFragment.this.mValidFlag = SignupDetailsFragment.this.mValidFlag | SignupDetailsFragment.this.FLAG_EMAIL;
                SignupDetailsFragment.this.checkEnabilityAdressPasswordEdittext(true);
                SignupDetailsFragment.this.enableSignUpGaanButtonwhileRegistration();
                return;
            }
            SignupDetailsFragment.this.mValidFlag = SignupDetailsFragment.this.mValidFlag & (SignupDetailsFragment.this.FLAG_EMAIL ^ -1);
            SignupDetailsFragment.this.checkEnabilityAdressPasswordEdittext(false);
        }
    };
    private String errorMessage = "";
    private Button mBtnGaanaSignup;
    private CheckBox mCheckBoxTnC;
    private Context mContext;
    private b mDialogListner = new b() {
        public void onOkListner(String str) {
            ((Login) SignupDetailsFragment.this.mContext).initOnBoardLogin();
        }

        public void onCancelListner() {
            SignupDetailsFragment.this.handleForgotPassword(SignupDetailsFragment.this.mEditTxtEmailAddress.getText().toString().trim());
        }
    };
    private f mDialogs;
    private EditText mEditTxtEmailAddress;
    private EditText mEditTxtUserName;
    private EditText mEditTxtUserPwd;
    private TextInputLayout mEmailAddressLayout;
    private TextInputLayout mFullNameLayout;
    private TextInputLayout mPasswordLayout;
    private ProgressDialog mProgressDialog = null;
    private int mValidFlag = 0;

    public static Fragment newInstance(String str, String str2, LoginInfo loginInfo, boolean z) {
        SignupDetailsFragment signupDetailsFragment = new SignupDetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putString("extra_email", str);
        bundle.putString("extra_password", str2);
        bundle.putBoolean(EXTRA_ARG_TRAP_PAGE, z);
        if (loginInfo != null) {
            bundle.putSerializable("temp_user_tag", loginInfo);
        }
        signupDetailsFragment.setArguments(bundle);
        return signupDetailsFragment;
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_signup_details, viewGroup, false);
        init(inflate);
        return inflate;
    }

    private void init(View view) {
        this.mDialogs = new f(this.mContext);
        this.backImg = (ImageView) view.findViewById(R.id.back);
        this.backImg.setOnClickListener(this);
        this.mEditTxtUserName = (EditText) view.findViewById(R.id.onboard_email_fullname);
        this.mEditTxtUserName.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void afterTextChanged(Editable editable) {
                if (o.a(SignupDetailsFragment.this.mEditTxtUserName.getText().toString().trim())) {
                    SignupDetailsFragment.this.mValidFlag = SignupDetailsFragment.this.mValidFlag | SignupDetailsFragment.this.FLAG_USERNAME;
                    SignupDetailsFragment.this.mFullNameLayout.setError(null);
                } else {
                    SignupDetailsFragment.this.mFullNameLayout.setError(SignupDetailsFragment.this.getContext().getString(R.string.alphabets_full_name));
                    SignupDetailsFragment.this.mValidFlag = SignupDetailsFragment.this.mValidFlag & (SignupDetailsFragment.this.FLAG_USERNAME ^ -1);
                }
                SignupDetailsFragment.this.enableSignUpGaanButtonwhileRegistration();
            }
        });
        this.mEditTxtEmailAddress = (EditText) view.findViewById(R.id.onboard_email_address);
        this.mEditTxtEmailAddress.addTextChangedListener(this.enableLoginButtonWatcher);
        this.mEmailAddressLayout = (TextInputLayout) view.findViewById(R.id.onboard_email_address_layout);
        this.mEmailAddressLayout.setErrorEnabled(true);
        this.mEditTxtEmailAddress.setOnEditorActionListener(new OnEditorActionListener() {
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == 5 || i == 0) {
                    if (TextUtils.isEmpty(SignupDetailsFragment.this.mEditTxtEmailAddress.getText().toString())) {
                        SignupDetailsFragment.this.mEmailAddressLayout.setError(SignupDetailsFragment.this.getContext().getString(R.string.required_field));
                    } else if (o.b(SignupDetailsFragment.this.mEditTxtEmailAddress.getText().toString()).booleanValue()) {
                        SignupDetailsFragment.this.mEmailAddressLayout.setError(null);
                        Util.a(SignupDetailsFragment.this.mContext, (View) textView);
                        SignupDetailsFragment.this.registerIfNotGaanaUser(SignupDetailsFragment.this.getLoginInfo(), true);
                        return false;
                    } else {
                        SignupDetailsFragment.this.mEmailAddressLayout.setError(SignupDetailsFragment.this.getContext().getString(R.string.error_msg_incorrect_emailid));
                    }
                }
                return true;
            }
        });
        this.mEditTxtEmailAddress.setOnFocusChangeListener(new OnFocusChangeListener() {
            public void onFocusChange(View view, boolean z) {
                if (z && Constants.A == 1 && Util.a((Login) SignupDetailsFragment.this.mContext)) {
                    GooglePlusLogin.getInstance().requestCredentials(true, false);
                    u.a().b("Auto_SignUp", "SignUpEmailTap");
                }
            }
        });
        this.mEditTxtUserPwd = (EditText) view.findViewById(R.id.onboard_email_pwd);
        this.mEditTxtUserPwd.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void afterTextChanged(Editable editable) {
                if (o.c(SignupDetailsFragment.this.mEditTxtUserPwd.getText().toString())) {
                    SignupDetailsFragment.this.mValidFlag = SignupDetailsFragment.this.mValidFlag | SignupDetailsFragment.this.FLAG_PASSWORD;
                    SignupDetailsFragment.this.mPasswordLayout.setError(null);
                } else {
                    SignupDetailsFragment.this.mPasswordLayout.setError(SignupDetailsFragment.this.getContext().getString(R.string.error_msg_signup_passwd));
                    SignupDetailsFragment.this.mValidFlag = SignupDetailsFragment.this.mValidFlag & (SignupDetailsFragment.this.FLAG_PASSWORD ^ -1);
                }
                SignupDetailsFragment.this.enableSignUpGaanButtonwhileRegistration();
            }
        });
        this.mBtnGaanaSignup = (Button) view.findViewById(R.id.onboard_btn_email);
        this.mBtnGaanaSignup.setOnClickListener(this);
        this.mPasswordLayout = (TextInputLayout) view.findViewById(R.id.onboard_email_pwd_layout);
        this.mPasswordLayout.setErrorEnabled(true);
        this.mFullNameLayout = (TextInputLayout) view.findViewById(R.id.onboard_email_fullname_layout);
        this.mFullNameLayout.setErrorEnabled(true);
        TextView textView = (TextView) view.findViewById(R.id.onboard_footer);
        textView.setOnClickListener(this);
        String string = this.mContext.getResources().getString(R.string.onboard_footer_text);
        String string2 = this.mContext.getResources().getString(R.string.onboard_footer_TnC_text);
        SpannableString spannableString = new SpannableString(string2);
        spannableString.setSpan(new ForegroundColorSpan(this.mContext.getResources().getColor(R.color.gaana_orange_text)), 0, string2.length(), 33);
        textView.setText(string);
        textView.append(" ");
        textView.append(spannableString);
        this.mCheckBoxTnC = (CheckBox) view.findViewById(R.id.agreeTnC);
        this.mCheckBoxTnC.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                SignupDetailsFragment.this.enableSignUpGaanButtonwhileRegistration();
            }
        });
        if (getArguments() != null) {
            String string3 = getArguments().getString("extra_email");
            if (!TextUtils.isEmpty(string3)) {
                this.mEditTxtEmailAddress.setText(string3);
            }
            string3 = getArguments().getString("extra_password");
            if (!TextUtils.isEmpty(string3)) {
                this.mEditTxtUserPwd.setText(string3);
            }
            LoginInfo loginInfo = (LoginInfo) getArguments().getSerializable("temp_user_tag");
            if (loginInfo != null) {
                setLoginInfo(loginInfo);
            }
            if (getArguments().getBoolean(EXTRA_ARG_TRAP_PAGE, false)) {
                textView = (TextView) view.findViewById(R.id.textViewSubHeader);
                ((TextView) view.findViewById(R.id.onboard_email_title)).setText(R.string.complete_your_profile);
                String stringExtra = getActivity().getIntent().getStringExtra("message");
                if (!TextUtils.isEmpty(stringExtra)) {
                    textView.setVisibility(0);
                    textView.setText(stringExtra);
                }
            }
        }
        enableSignUpGaanButtonwhileRegistration();
    }

    private void checkEnabilityAdressPasswordEdittext(boolean z) {
        if (z) {
            this.mEditTxtUserName.setEnabled(true);
            this.mEditTxtUserPwd.setEnabled(true);
            return;
        }
        this.mEditTxtUserName.setEnabled(false);
        this.mEditTxtUserPwd.setEnabled(false);
    }

    private void showProgressDialogCustom(String str) {
        try {
            if (this.mProgressDialog == null) {
                this.mProgressDialog = ProgressDialog.show(this.mContext, "", str, true, false);
                this.mProgressDialog.setCancelable(true);
            } else if (!this.mProgressDialog.isShowing()) {
                this.mProgressDialog.show();
            }
        } catch (Exception unused) {
        }
    }

    private void hideProgressDialog() {
        try {
            if (this.mProgressDialog != null && this.mProgressDialog.isShowing()) {
                this.mProgressDialog.dismiss();
                this.mProgressDialog = null;
            }
        } catch (IllegalArgumentException unused) {
        }
    }

    private void showProgressDialog() {
        try {
            if (this.mProgressDialog == null) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(getContext().getString(R.string.loading));
                stringBuilder.append("\t\t\t\t\t");
                this.mProgressDialog = ProgressDialog.show(this.mContext, "", stringBuilder.toString(), true, false);
                this.mProgressDialog.setCancelable(true);
            } else if (!this.mProgressDialog.isShowing()) {
                this.mProgressDialog.show();
            }
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
        }
    }

    private void startGaanaRegistration(final LoginInfo loginInfo) {
        LoginManager.getInstance().register((Activity) this.mContext, loginInfo, new IOnLoginCompleted() {
            public void onLoginCompleted(LOGIN_STATUS login_status, UserInfo userInfo, Bundle bundle) {
                switch (login_status) {
                    case LOGIN_SUCCEDED:
                        MoEngage.getInstance().reportNewUser();
                        GooglePlusLogin.getInstance().onEmailSaveClicked(loginInfo.getEmailId(), loginInfo.getPassword(), (Login) SignupDetailsFragment.this.mContext);
                        d.a().a("ONBOARD_NEW_USER", true, false);
                        d.a().b("PREFF_CAMPAIGN_PROMO_REFERRAL", false);
                        u.a().c();
                        SignupDetailsFragment.this.hideProgressDialog();
                        if (((Login) SignupDetailsFragment.this.mContext).isLoginStartedForResult) {
                            ((Activity) SignupDetailsFragment.this.mContext).setResult(701);
                        } else if (((Login) SignupDetailsFragment.this.mContext).isShufflePlayResult) {
                            ((Activity) SignupDetailsFragment.this.mContext).setResult(711);
                        } else {
                            ((Login) SignupDetailsFragment.this.mContext).launchHome();
                        }
                        ((Activity) SignupDetailsFragment.this.mContext).finish();
                        return;
                    case LOGIN_ERROR_AUTHENTICATION_FAILED:
                    case LOGIN_REGISTRATION_FAILED:
                        ((BaseActivity) SignupDetailsFragment.this.mContext).sendGAEvent("Registration", "Registration Failure", "Registration - Email");
                        aj.a().a(SignupDetailsFragment.this.mContext, SignupDetailsFragment.this.getContext().getString(R.string.registration_failed));
                        return;
                    case ALREADY_REGISTERED_USER:
                        ((Login) SignupDetailsFragment.this.mContext).setLoginEmailPwd(loginInfo.getEmailId(), loginInfo.getPassword());
                        SignupDetailsFragment.this.mDialogs.a("", SignupDetailsFragment.this.getContext().getString(R.string.already_gaana_plus_login), Boolean.valueOf(true), SignupDetailsFragment.this.getContext().getString(R.string.onboard_login_title1), SignupDetailsFragment.this.getContext().getString(R.string.onboard_email_forgot_pwd_text1), SignupDetailsFragment.this.mDialogListner);
                        return;
                    case LOGIN_REGISTRATION_VERIFY:
                        VerifyOtpFragment newInstance = VerifyOtpFragment.newInstance(loginInfo.getEmailId(), false);
                        newInstance.setLoginCompletedListener((Login) SignupDetailsFragment.this.mContext);
                        ((Login) SignupDetailsFragment.this.mContext).displayFragment(newInstance, false);
                        return;
                    default:
                        return;
                }
            }
        });
    }

    private void registerIfNotGaanaUser(final LoginInfo loginInfo, final boolean z) {
        showProgressDialog();
        h.a().a(new TaskListner() {
            private String state = "";

            public void onBackGroundTaskCompleted() {
                SignupDetailsFragment.this.hideProgressDialog();
                if (this.state != null && this.state.equalsIgnoreCase("1")) {
                    ((Login) SignupDetailsFragment.this.mContext).setLoginEmailPwd(loginInfo.getEmailId(), loginInfo.getPassword());
                    SignupDetailsFragment.this.mDialogs.a("", SignupDetailsFragment.this.getContext().getString(R.string.already_gaana_plus_login), Boolean.valueOf(true), SignupDetailsFragment.this.getContext().getString(R.string.onboard_login_title1), SignupDetailsFragment.this.getContext().getString(R.string.onboard_email_forgot_pwd_text1), SignupDetailsFragment.this.mDialogListner);
                } else if (this.state != null && this.state.equalsIgnoreCase(InternalAvidAdSessionContext.AVID_API_LEVEL)) {
                    SignupDetailsFragment.this.mDialogs.a("", SignupDetailsFragment.this.getContext().getString(R.string.already_login_with_phone), Boolean.valueOf(false), null);
                } else if (this.state == null || !this.state.equals("3")) {
                    if (this.state != null && this.state.equalsIgnoreCase("99") && LoginManager.getInstance().getDefaultLoginMode() == LoginMode.SSO) {
                        loginInfo.setLoginMode(LoginMode.GAANA_SSO_NOT_VERIFIED);
                    }
                    if (z) {
                        SignupDetailsFragment.this.mEditTxtEmailAddress.setText(loginInfo.getEmailId());
                        ((BaseActivity) SignupDetailsFragment.this.mContext).setGoogleAnalyticsScreenName("RegistrationDetailScreen");
                        return;
                    }
                    SignupDetailsFragment.this.startGaanaRegistration(loginInfo);
                } else {
                    SignupDetailsFragment.this.mDialogs.a(TextUtils.isEmpty(SignupDetailsFragment.this.errorMessage) ? GaanaApplication.getContext().getString(R.string.invalid_email_id) : SignupDetailsFragment.this.errorMessage);
                }
            }

            public void doBackGroundTask() {
                try {
                    this.state = SignupDetailsFragment.this.isUserValid(loginInfo.getEmailId());
                } catch (ClientProtocolException e) {
                    ThrowableExtension.printStackTrace(e);
                } catch (IOException e2) {
                    ThrowableExtension.printStackTrace(e2);
                }
            }
        }, -1);
    }

    public void signupWithGaana() {
        String trim = this.mEditTxtUserPwd.getText().toString().trim();
        this.emailAddress = this.mEditTxtEmailAddress.getText().toString();
        this.mDialogs = new f(this.mContext);
        if (o.a(this.mEditTxtUserName, this.mEditTxtEmailAddress, this.mEditTxtUserPwd).booleanValue()) {
            this.mDialogs.a(getContext().getString(R.string.validate_entered_fields));
        } else if (!o.a(this.mEditTxtUserName.getText().toString().trim())) {
            this.mValidFlag &= this.FLAG_USERNAME ^ -1;
            this.mFullNameLayout.setError(getContext().getString(R.string.alphabets_full_name));
        } else if (!o.b(this.emailAddress.toLowerCase()).booleanValue()) {
            this.mValidFlag &= this.FLAG_EMAIL ^ -1;
            this.mDialogs.a(getContext().getString(R.string.email_not_valid));
        } else if (!o.c(trim)) {
            this.mValidFlag &= this.FLAG_PASSWORD ^ -1;
            this.mPasswordLayout.setError(getContext().getString(R.string.error_msg_signup_passwd));
        } else if (!Util.j(this.mContext)) {
            ap.a().f(this.mContext);
        } else if (this.mCheckBoxTnC.isChecked()) {
            this.mPasswordLayout.setError(null);
            registerIfNotGaanaUser(getLoginInfo(), false);
        } else {
            this.mDialogs.a(getContext().getString(R.string.agree_terms_conditions));
        }
    }

    private LoginInfo getLoginInfo() {
        LoginInfo loginInfo = new LoginInfo();
        loginInfo.setLoginType(LoginType.GAANA);
        loginInfo.setFullname(this.mEditTxtUserName.getText().toString().trim());
        loginInfo.setEmailId(this.mEditTxtEmailAddress.getText().toString().trim());
        loginInfo.setPassword(this.mEditTxtUserPwd.getText().toString().trim());
        loginInfo.setLoginMode(LoginManager.getInstance().getDefaultLoginMode());
        loginInfo.setSex("Male");
        return loginInfo;
    }

    private String isUserValid(String str) throws ClientProtocolException, IOException {
        String a = Util.a(Util.b(str), Constants.bv);
        List arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("email", str));
        arrayList.add(new BasicNameValuePair("st", a));
        str = new j().a("https://api.gaana.com/user/email-exists", arrayList);
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        a = ap.a().c(str);
        if (!TextUtils.isEmpty(a) && a.equalsIgnoreCase("3")) {
            this.errorMessage = ap.a().b(str);
        }
        return a;
    }

    public void onClick(View view) {
        Util.a(this.mContext, view);
        ((Login) this.mContext).removeGoogleSignSmartDialog();
        switch (view.getId()) {
            case R.id.back /*2131296487*/:
                try {
                    getActivity().getSupportFragmentManager().popBackStackImmediate();
                    return;
                } catch (IllegalStateException unused) {
                    return;
                }
            case R.id.onboard_btn_email /*2131297858*/:
                signupWithGaana();
                return;
            case R.id.onboard_footer /*2131297872*/:
            case R.id.onboard_footer_TnC /*2131297873*/:
                Intent intent = new Intent(this.mContext, WebViewActivity.class);
                intent.putExtra("EXTRA_WEBVIEW_URL", "https://api.gaana.com/index.php?type=terms_conditions&subtype=app");
                intent.putExtra("EXTRA_WEBVIEW_YOUTUBE", false);
                intent.putExtra("EXTRA_SHOW_ACTIONBAR", true);
                intent.putExtra("EXTRA_SHOW_ACTIONBAR2", true);
                ((Activity) this.mContext).startActivity(intent);
                return;
            default:
                return;
        }
    }

    private void handleForgotPassword(final String str) {
        showProgressDialog();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("https://api.gaana.com/user.php?type=forgotpassword&email=");
        stringBuilder.append(str);
        String stringBuilder2 = stringBuilder.toString();
        UserInfo currentUser = GaanaApplication.getInstance().getCurrentUser();
        if (!(currentUser == null || !currentUser.getLoginStatus() || stringBuilder2.contains(AccountKitGraphConstants.TOKEN_RESPONSE_TYPE))) {
            StringBuilder stringBuilder3 = new StringBuilder();
            stringBuilder3.append(stringBuilder2);
            stringBuilder3.append("&token=");
            stringBuilder3.append(currentUser.getAuthToken());
            stringBuilder2 = stringBuilder3.toString();
        }
        URLManager uRLManager = new URLManager();
        uRLManager.b(Boolean.valueOf(false));
        uRLManager.a(stringBuilder2);
        uRLManager.a(String.class);
        uRLManager.a(Priority.HIGH);
        uRLManager.i(false);
        if (Util.j(this.mContext)) {
            i.a().a(new af() {
                public void onErrorResponse(BusinessObject businessObject) {
                }

                /* JADX WARNING: Removed duplicated region for block: B:15:0x0053  */
                /* JADX WARNING: Removed duplicated region for block: B:14:0x004d  */
                /* JADX WARNING: Removed duplicated region for block: B:14:0x004d  */
                /* JADX WARNING: Removed duplicated region for block: B:15:0x0053  */
                public void onRetreivalComplete(java.lang.Object r7) {
                    /*
                    r6 = this;
                    r0 = "-1";
                    r1 = "";
                    r7 = (java.lang.String) r7;
                    if (r7 == 0) goto L_0x0026;
                L_0x0008:
                    r2 = new org.json.JSONObject;	 Catch:{ JSONException -> 0x0021 }
                    r2.<init>(r7);	 Catch:{ JSONException -> 0x0021 }
                    r7 = "Status";
                    r7 = r2.getString(r7);	 Catch:{ JSONException -> 0x0021 }
                    r0 = "Error";
                    r0 = r2.getString(r0);	 Catch:{ JSONException -> 0x001c }
                    r1 = r0;
                    r0 = r7;
                    goto L_0x0040;
                L_0x001c:
                    r0 = move-exception;
                    r5 = r0;
                    r0 = r7;
                    r7 = r5;
                    goto L_0x0022;
                L_0x0021:
                    r7 = move-exception;
                L_0x0022:
                    com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r7);
                    goto L_0x0040;
                L_0x0026:
                    r7 = com.managers.aj.a();
                    r2 = com.gaana.login.fragments.SignupDetailsFragment.this;
                    r2 = r2.mContext;
                    r3 = com.gaana.login.fragments.SignupDetailsFragment.this;
                    r3 = r3.getContext();
                    r4 = 2131822373; // 0x7f110725 float:1.9277516E38 double:1.0532601975E-314;
                    r3 = r3.getString(r4);
                    r7.a(r2, r3);
                L_0x0040:
                    r7 = com.gaana.login.fragments.SignupDetailsFragment.this;
                    r7.hideProgressDialog();
                    r7 = "1";
                    r7 = r0.equals(r7);
                    if (r7 == 0) goto L_0x0053;
                L_0x004d:
                    r7 = com.gaana.login.fragments.SignupDetailsFragment.this;
                    r7.showForgotPasswordSuccessMessage();
                    goto L_0x0095;
                L_0x0053:
                    r7 = "0";
                    r7 = r0.equals(r7);
                    if (r7 == 0) goto L_0x0095;
                L_0x005b:
                    r7 = "Email not exists";
                    r7 = r1.equalsIgnoreCase(r7);
                    if (r7 == 0) goto L_0x0095;
                L_0x0063:
                    r7 = com.gaana.login.fragments.SignupDetailsFragment.this;
                    r7 = r7.mContext;
                    r7 = (com.gaana.Login) r7;
                    r0 = r5;
                    r1 = "";
                    r7.setSignupEmailPwd(r0, r1);
                    r7 = com.gaana.login.fragments.SignupDetailsFragment.this;
                    r7 = r7.mDialogs;
                    r0 = "";
                    r1 = com.gaana.login.fragments.SignupDetailsFragment.this;
                    r1 = r1.getContext();
                    r2 = 2131821186; // 0x7f110282 float:1.9275108E38 double:1.053259611E-314;
                    r1 = r1.getString(r2);
                    r2 = 1;
                    r2 = java.lang.Boolean.valueOf(r2);
                    r3 = com.gaana.login.fragments.SignupDetailsFragment.this;
                    r3 = r3.mDialogListner;
                    r7.a(r0, r1, r2, r3);
                L_0x0095:
                    return;
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.gaana.login.fragments.SignupDetailsFragment$AnonymousClass9.onRetreivalComplete(java.lang.Object):void");
                }
            }, uRLManager);
        }
    }

    public void setLoginInfo(LoginInfo loginInfo) {
        this.mEditTxtUserName.setText(loginInfo.getFullname());
        this.mEditTxtUserName.setEnabled(false);
        if (TextUtils.isEmpty(loginInfo.getEmailId())) {
            this.mEditTxtEmailAddress.setEnabled(true);
            return;
        }
        this.mEditTxtEmailAddress.setText(loginInfo.getEmailId());
        this.mEditTxtEmailAddress.setEnabled(false);
    }

    private boolean enablelogInButton() {
        String trim = this.mEditTxtEmailAddress.getText().toString().trim();
        if (TextUtils.isEmpty(trim)) {
            return false;
        }
        return o.b(trim).booleanValue();
    }

    private void showForgotPasswordSuccessMessage() {
        new CustomMaterialDialogView(this.mContext, this.mContext.getString(R.string.reset_password), this.mContext.getResources().getString(R.string.forgot_password_success_message), this.mContext.getResources().getString(R.string.forget_password_bottom_success_message)).show();
    }

    private void enableSignUpGaanButtonwhileRegistration() {
        boolean z = (this.mValidFlag & this.FLAG_IS_VALID) >= this.FLAG_IS_VALID;
        this.mBtnGaanaSignup.setEnabled(z);
        if (!z) {
            this.mBtnGaanaSignup.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_rounded_corner_onboard));
            this.mBtnGaanaSignup.setTextColor(getResources().getColor(R.color.white_alfa_55));
        } else if (this.mCheckBoxTnC.isChecked()) {
            this.mBtnGaanaSignup.setBackgroundDrawable(getResources().getDrawable(R.drawable.shape_continue_btn));
            this.mBtnGaanaSignup.setTextColor(getResources().getColor(R.color.white));
        } else {
            this.mBtnGaanaSignup.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_rounded_corner_onboard));
            this.mBtnGaanaSignup.setTextColor(getResources().getColor(R.color.white_alfa_55));
        }
    }

    private boolean validName() {
        return o.a(this.mEditTxtUserName.getText().toString().trim());
    }

    private boolean validPassword() {
        return this.mEditTxtUserPwd.getText().toString().length() >= 6 && this.mEditTxtUserPwd.getText().toString().length() <= 14;
    }
}
