package com.gaana.login.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import com.constants.Constants;
import com.gaana.BaseActivity;
import com.gaana.GaanaActivity;
import com.gaana.Login;
import com.gaana.R;
import com.gaana.application.GaanaApplication;
import com.gaana.login.GooglePlusLogin;
import com.gaana.login.LoginManager;
import com.gaana.login.LoginManager.IOnLoginCompleted;
import com.gaana.models.User.LoginMode;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.integralads.avid.library.inmobi.session.internal.InternalAvidAdSessionContext;
import com.library.managers.TaskManager.TaskListner;
import com.managers.aj;
import com.managers.an;
import com.managers.ap;
import com.managers.u;
import com.services.f;
import com.services.f.b;
import com.services.h;
import com.services.j;
import com.services.o;
import com.utilities.Util;
import com.views.CustomButtonView;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.message.BasicNameValuePair;

public class EmailLoginFragment extends Fragment implements OnClickListener {
    public static final String EXTRA_ARG_EMAIL = "extra_email";
    public static final String EXTRA_ARG_PASSWORD = "extra_password";
    private TextInputLayout email_layout;
    private TextWatcher enableLoginButtonWatcher = new TextWatcher() {
        public void afterTextChanged(Editable editable) {
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }
    };
    private LinearLayout header_layout;
    private CustomButtonView mBtnGaanaLogin;
    private Context mContext;
    private b mDialogListner = new b() {
        public void onCancelListner() {
        }

        public void onOkListner(String str) {
            if (EmailLoginFragment.this.isAdded()) {
                EmailLoginFragment.this.getActivity().getSupportFragmentManager().popBackStackImmediate();
                ((Login) EmailLoginFragment.this.mContext).displayFragment(SignupDetailsFragment.newInstance(EmailLoginFragment.this.mEmailAddress, EmailLoginFragment.this.mPassword, null, false), true);
            }
        }
    };
    private f mDialogs;
    private EditText mEditTxtUserEmail;
    private EditText mEditTxtUserPwd;
    private String mEmailAddress = "";
    private b mOkDialogListner = new b() {
        public void onCancelListner() {
        }

        public void onOkListner(String str) {
            if (EmailLoginFragment.this.isAdded()) {
                EmailLoginFragment.this.getActivity().getSupportFragmentManager().popBackStack();
            }
        }
    };
    private String mPassword = "";
    private TextView mTxtForgotPwd;
    private TextView onboard_title;
    private TextInputLayout password_layout;
    private TextView skipText;
    private TextView welcome_title;

    private void disableGaanaLoginButton() {
    }

    public static Fragment newInstance(String str, String str2) {
        EmailLoginFragment emailLoginFragment = new EmailLoginFragment();
        Bundle bundle = new Bundle();
        bundle.putString("extra_email", str);
        bundle.putString("extra_password", str2);
        emailLoginFragment.setArguments(bundle);
        return emailLoginFragment;
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_email_login, viewGroup, false);
        init(inflate);
        return inflate;
    }

    private void init(View view) {
        this.skipText = (TextView) view.findViewById(R.id.img_cancel_skip);
        this.skipText.setOnClickListener(this);
        this.skipText.setTypeface(Typeface.createFromAsset(this.mContext.getAssets(), "fonts/Roboto-Bold.ttf"));
        if (Constants.r != 1) {
            this.skipText.setVisibility(8);
        }
        this.welcome_title = (TextView) view.findViewById(R.id.welcome_text);
        this.welcome_title.setTypeface(Typeface.createFromAsset(this.mContext.getAssets(), "fonts/Roboto-Medium.ttf"));
        this.password_layout = (TextInputLayout) view.findViewById(R.id.password_layout);
        this.email_layout = (TextInputLayout) view.findViewById(R.id.email_layout);
        this.onboard_title = (TextView) view.findViewById(R.id.onboard_title);
        this.header_layout = (LinearLayout) view.findViewById(R.id.header_layout);
        this.mContext = getContext();
        this.mDialogs = new f(getContext());
        this.mEditTxtUserEmail = (EditText) view.findViewById(R.id.onboard_email_id);
        this.mBtnGaanaLogin = (CustomButtonView) view.findViewById(R.id.onboard_btn_email);
        this.mBtnGaanaLogin.setOnClickListener(this);
        this.mTxtForgotPwd = (TextView) view.findViewById(R.id.onboard_email_forgot_pwd);
        this.mTxtForgotPwd.setOnClickListener(this);
        this.mEditTxtUserEmail.addTextChangedListener(this.enableLoginButtonWatcher);
        this.mEditTxtUserEmail.requestFocus();
        this.mEditTxtUserEmail.setOnFocusChangeListener(new OnFocusChangeListener() {
            public void onFocusChange(View view, boolean z) {
                if (z && Constants.A == 1 && Util.a((Login) EmailLoginFragment.this.mContext)) {
                    GooglePlusLogin.getInstance().requestCredentials(true, false);
                    u.a().b("Auto_SignUp", "LoginEmailTap");
                }
            }
        });
        this.mEditTxtUserEmail.setOnEditorActionListener(new OnEditorActionListener() {
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (EmailLoginFragment.this.password_layout.getVisibility() == 0) {
                    EmailLoginFragment.this.password_layout.requestFocus();
                } else {
                    BaseActivity baseActivity = (BaseActivity) EmailLoginFragment.this.mContext;
                    String str = ((BaseActivity) EmailLoginFragment.this.mContext).currentScreen;
                    String str2 = ((BaseActivity) EmailLoginFragment.this.mContext).currentScreen;
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(((BaseActivity) EmailLoginFragment.this.mContext).currentScreen);
                    stringBuilder.append(" - Email");
                    baseActivity.sendGAEvent(str, str2, stringBuilder.toString());
                    EmailLoginFragment.this.verifyUser();
                }
                return true;
            }
        });
        this.mEditTxtUserPwd = (EditText) view.findViewById(R.id.onboard_email_pwd);
        this.mEditTxtUserPwd.setOnEditorActionListener(new OnEditorActionListener() {
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i != 6 && i != 0) {
                    return false;
                }
                Util.a(EmailLoginFragment.this.getContext(), (View) textView);
                EmailLoginFragment.this.verifyUser();
                return true;
            }
        });
        this.mEditTxtUserPwd.addTextChangedListener(this.enableLoginButtonWatcher);
        if (getArguments() != null) {
            String string = getArguments().getString("extra_email");
            if (!TextUtils.isEmpty(string)) {
                this.mEditTxtUserEmail.setText(string);
            }
            string = getArguments().getString("extra_password");
            if (!TextUtils.isEmpty(string)) {
                this.mEditTxtUserPwd.setText(string);
            }
        }
        disableGaanaLoginButton();
        if (this.mContext instanceof Login) {
            ((Login) this.mContext).setIsBackEnabled(true);
        }
    }

    /* JADX WARNING: Missing block: B:14:0x0048, code skipped:
            return false;
     */
    private boolean enablelogInButton() {
        /*
        r4 = this;
        r0 = r4.mEditTxtUserEmail;
        r0 = r0.getText();
        r0 = r0.toString();
        r0 = r0.trim();
        r1 = r4.mEditTxtUserPwd;
        r1 = r1.getText();
        r1 = r1.toString();
        r1 = r1.trim();
        r2 = android.text.TextUtils.isEmpty(r0);
        r3 = 0;
        if (r2 != 0) goto L_0x0048;
    L_0x0023:
        r2 = android.text.TextUtils.isEmpty(r1);
        if (r2 == 0) goto L_0x002a;
    L_0x0029:
        goto L_0x0048;
    L_0x002a:
        r0 = com.services.o.b(r0);
        r0 = r0.booleanValue();
        if (r0 != 0) goto L_0x0035;
    L_0x0034:
        return r3;
    L_0x0035:
        r0 = r1.length();
        r2 = 6;
        if (r0 < r2) goto L_0x0047;
    L_0x003c:
        r0 = r1.length();
        r1 = 14;
        if (r0 <= r1) goto L_0x0045;
    L_0x0044:
        goto L_0x0047;
    L_0x0045:
        r0 = 1;
        return r0;
    L_0x0047:
        return r3;
    L_0x0048:
        return r3;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.gaana.login.fragments.EmailLoginFragment.enablelogInButton():boolean");
    }

    public void isValidGaanaUser(String str, String str2, f fVar, b bVar, IOnLoginCompleted iOnLoginCompleted, Activity activity, boolean z, b bVar2) {
        Util.a((Context) activity, activity.getString(R.string.loading));
        final Activity activity2 = activity;
        final String str3 = str2;
        final f fVar2 = fVar;
        final b bVar3 = bVar2;
        final String str4 = str;
        h.a().a(new TaskListner() {
            private String errorMessage = "";
            private String status = "";

            public void onBackGroundTaskCompleted() {
                Util.b();
                if (this.status != null && this.status.equalsIgnoreCase("1")) {
                    EmailLoginFragment.this.showPasswordLayout();
                } else if (this.status != null && this.status.equalsIgnoreCase("0")) {
                    EmailLoginFragment.this.getActivity().getSupportFragmentManager().popBackStackImmediate();
                    ((Login) activity2).displayFragment(SignupDetailsFragment.newInstance(EmailLoginFragment.this.mEmailAddress, str3, null, false), true);
                } else if (this.status != null && this.status.equalsIgnoreCase(InternalAvidAdSessionContext.AVID_API_LEVEL)) {
                    fVar2.a("", activity2.getString(R.string.already_login_with_phone), Boolean.valueOf(false), bVar3);
                } else if (this.status != null && this.status.equalsIgnoreCase("99")) {
                    if (LoginManager.getInstance().getDefaultLoginMode() == LoginMode.SSO) {
                        LoginMode loginMode = LoginMode.GAANA_SSO_NOT_VERIFIED;
                    }
                    EmailLoginFragment.this.showPasswordLayout();
                } else if (this.status != null && this.status.equalsIgnoreCase("3")) {
                    fVar2.a(TextUtils.isEmpty(this.errorMessage) ? GaanaApplication.getContext().getString(R.string.invalid_email_id) : this.errorMessage);
                }
            }

            public void doBackGroundTask() {
                try {
                    this.status = isUserValid(str4);
                } catch (ClientProtocolException e) {
                    ThrowableExtension.printStackTrace(e);
                } catch (IOException e2) {
                    ThrowableExtension.printStackTrace(e2);
                }
            }

            public String isUserValid(String str) throws ClientProtocolException, IOException {
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
        }, -1);
    }

    private void showPasswordLayout() {
        this.password_layout.setVisibility(0);
        this.email_layout.setVisibility(0);
        this.mTxtForgotPwd.setVisibility(0);
        this.onboard_title.setVisibility(8);
        this.header_layout.setVisibility(0);
    }

    private void verifyUser() {
        this.mEmailAddress = this.mEditTxtUserEmail.getText().toString().trim();
        this.mPassword = this.mEditTxtUserPwd.getText().toString().trim();
        if (o.a(this.mEditTxtUserEmail).booleanValue()) {
            this.mDialogs.a(getContext().getString(R.string.validate_entered_fields));
            return;
        }
        if (this.mTxtForgotPwd.getVisibility() == 0) {
            if (o.a(this.mEditTxtUserEmail).booleanValue()) {
                this.mDialogs.a(getContext().getString(R.string.validate_entered_fields));
                return;
            }
        }
        if (!o.b(this.mEditTxtUserEmail.getText().toString().toLowerCase()).booleanValue()) {
            this.mDialogs.a(this.mContext.getResources().getString(R.string.error_msg_incorrect_emailid));
        } else if (this.mTxtForgotPwd.getVisibility() == 0 && (this.mPassword.length() < 6 || this.mPassword.length() > 14)) {
            this.mDialogs.a(getContext().getString(R.string.valid_password));
        } else if (Util.j(this.mContext)) {
            if (this.mTxtForgotPwd.getVisibility() == 0) {
                Util.a(this.mEmailAddress, this.mPassword, this.mDialogs, this.mDialogListner, (Login) this.mContext, (Activity) this.mContext, false, this.mOkDialogListner);
            } else {
                isValidGaanaUser(this.mEmailAddress, this.mPassword, this.mDialogs, this.mDialogListner, (Login) this.mContext, (Activity) this.mContext, false, this.mOkDialogListner);
            }
        } else {
            ap.a().f(this.mContext);
        }
    }

    public void onClick(View view) {
        Util.a(this.mContext, view);
        ((Login) this.mContext).removeGoogleSignSmartDialog();
        int id = view.getId();
        if (id == R.id.back) {
            try {
                if (!isAdded()) {
                    return;
                }
                if (getActivity().getSupportFragmentManager().getBackStackEntryCount() > 1) {
                    getActivity().getSupportFragmentManager().popBackStack();
                } else {
                    getActivity().finish();
                }
            } catch (IllegalStateException unused) {
            }
        } else if (id == R.id.img_cancel_skip) {
            u.a().b("EmailLogin", "Skip");
            an.a().a("click", "ac", "", "EMAILLOGIN", "", "SKIP", "", "");
            Intent intent = new Intent(GaanaApplication.getContext(), GaanaActivity.class);
            if (Login.isSignupFromInside) {
                intent.setFlags(71303168);
            } else {
                intent.setFlags(335544320);
            }
            if (LoginManager.getInstance().checkDisableInternationalOnBoarding(this.mContext)) {
                startActivity(intent);
            }
            ((Login) this.mContext).finish();
        } else if (id == R.id.onboard_btn_email) {
            BaseActivity baseActivity = (BaseActivity) this.mContext;
            String str = ((BaseActivity) this.mContext).currentScreen;
            String str2 = ((BaseActivity) this.mContext).currentScreen;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(((BaseActivity) this.mContext).currentScreen);
            stringBuilder.append(" - Email");
            baseActivity.sendGAEvent(str, str2, stringBuilder.toString());
            verifyUser();
        } else if (id == R.id.onboard_email_forgot_pwd) {
            if (o.b(this.mEditTxtUserEmail.getText().toString()).booleanValue()) {
                if (o.a(this.mEditTxtUserEmail).booleanValue()) {
                    aj.a().a(this.mContext, this.mContext.getString(R.string.enter_email_first));
                    return;
                }
                u.a().b("Login", "Forgot Password");
                Util.a(this.mEditTxtUserEmail.getText().toString(), this.mPassword, this.mDialogs, this.mDialogListner, (Login) this.mContext, (Activity) this.mContext, true, this.mOkDialogListner);
                return;
            }
            aj.a().a(this.mContext, this.mContext.getString(R.string.enter_correct_email));
        }
    }
}
