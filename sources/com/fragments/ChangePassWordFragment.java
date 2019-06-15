package com.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.actionbar.GenericBackActionBar;
import com.android.volley.Request.Priority;
import com.gaana.BaseActivity;
import com.gaana.R;
import com.gaana.application.GaanaApplication;
import com.gaana.login.LoginInfo;
import com.gaana.login.LoginManager;
import com.gaana.login.UserInfo;
import com.gaana.models.BusinessObject;
import com.gaana.models.User.LoginMode;
import com.gaana.view.item.CustomMaterialDialogView;
import com.i.i;
import com.managers.URLManager;
import com.managers.ap;
import com.managers.u;
import com.services.f;
import com.services.l.af;
import com.services.o;
import com.utilities.Util;
import com.utilities.k;
import java.util.HashMap;

public class ChangePassWordFragment extends BaseGaanaFragment implements OnClickListener {
    private View a = null;
    private ProgressDialog b = null;
    private f c;
    private boolean d = false;
    private boolean e = false;
    private Button f;
    private HashMap<String, String> g;
    private EditText h;
    private EditText i;
    private k j = null;

    public void setGAScreenName(String str, String str2) {
        sendGAScreenName(str, str2);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (this.a == null) {
            super.onCreateView(layoutInflater, viewGroup, bundle);
            this.a = setContentView(R.layout.changepassword_fragment, viewGroup);
            Toolbar toolbar = (Toolbar) this.a.findViewById(R.id.main_toolbar);
            toolbar.setContentInsetsAbsolute(0, 0);
            GenericBackActionBar genericBackActionBar = new GenericBackActionBar(this.mContext, this.mContext.getString(R.string.change_password_title));
            toolbar.removeAllViews();
            toolbar.addView(genericBackActionBar);
        }
        a();
        return this.a;
    }

    private void a() {
        this.f = (Button) this.a.findViewById(R.id.update_button);
        this.f.setOnClickListener(this);
        this.f.setEnabled(false);
        ((TextView) this.a.findViewById(R.id.changepassword_email_forgot_pwd)).setOnClickListener(this);
        this.c = new f(this.mContext);
        final TextInputLayout textInputLayout = (TextInputLayout) this.a.findViewById(R.id.current_password_layout);
        final TextInputLayout textInputLayout2 = (TextInputLayout) this.a.findViewById(R.id.new_password_layout);
        this.h = (EditText) this.a.findViewById(R.id.current_password_edittext);
        this.i = (EditText) this.a.findViewById(R.id.new_password_edittext);
        this.h.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void afterTextChanged(Editable editable) {
                if (ChangePassWordFragment.this.h.getText().toString().length() < 6 || ChangePassWordFragment.this.h.getText().toString().length() > 14) {
                    textInputLayout.setError(ChangePassWordFragment.this.getContext().getString(R.string.valid_password));
                    ChangePassWordFragment.this.d = false;
                    ChangePassWordFragment.this.b();
                    return;
                }
                textInputLayout.setError(null);
                ChangePassWordFragment.this.d = true;
                ChangePassWordFragment.this.b();
            }
        });
        this.i.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void afterTextChanged(Editable editable) {
                if (o.c(ChangePassWordFragment.this.i.getText().toString())) {
                    textInputLayout2.setError(null);
                    ChangePassWordFragment.this.e = true;
                    ChangePassWordFragment.this.b();
                    return;
                }
                textInputLayout2.setError(ChangePassWordFragment.this.getContext().getString(R.string.error_msg_signup_passwd));
                ChangePassWordFragment.this.e = false;
                ChangePassWordFragment.this.b();
            }
        });
    }

    private void b() {
        if (this.e && this.d) {
            this.f.setEnabled(true);
            this.f.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_rounded_corner_onboard_enabled));
            this.f.setTextColor(getResources().getColor(R.color.white));
            return;
        }
        this.f.setEnabled(false);
        this.f.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_rounded_corner_onboard));
        this.f.setTextColor(getResources().getColor(R.color.white_alfa_55));
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.changepassword_email_forgot_pwd) {
            UserInfo currentUser = GaanaApplication.getInstance().getCurrentUser();
            if (!TextUtils.isEmpty(currentUser.getUserProfile().getEmail())) {
                u.a().b("Login", "Forgot Password");
                b(currentUser.getUserProfile().getEmail().trim());
            }
        } else if (id == R.id.update_button) {
            c();
        }
    }

    private void c() {
        if (Util.j(getActivity())) {
            final LoginInfo loginInfo = LoginManager.getInstance().getLoginInfo();
            this.g = new HashMap();
            if (loginInfo.getLoginMode() == LoginMode.SSO) {
                this.g.put("type", "nxtgen_change_password_sso");
            } else {
                this.g.put("type", "nxtgen_change_password");
            }
            this.g.put("oldPassword", this.h.getText().toString().trim());
            this.g.put("newPassword", this.i.getText().toString().trim());
            final UserInfo currentUser = GaanaApplication.getInstance().getCurrentUser();
            if (currentUser != null && currentUser.getLoginStatus()) {
                this.g.put(AccountKitGraphConstants.TOKEN_RESPONSE_TYPE, currentUser.getAuthToken());
            }
            BaseActivity baseActivity = (BaseActivity) this.mContext;
            Boolean valueOf = Boolean.valueOf(true);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(this.mContext.getString(R.string.updating));
            stringBuilder.append("\t\t\t\t\t");
            baseActivity.showProgressDialog(valueOf, stringBuilder.toString());
            URLManager uRLManager = new URLManager();
            uRLManager.a(String.class);
            uRLManager.a(Priority.HIGH);
            uRLManager.b(Boolean.valueOf(false));
            uRLManager.c(1);
            uRLManager.a(this.g);
            uRLManager.a("https://api.gaana.com/user.php?");
            uRLManager.i(false);
            i.a().a(new af() {
                /* JADX WARNING: Removed duplicated region for block: B:25:? A:{SYNTHETIC, RETURN} */
                /* JADX WARNING: Removed duplicated region for block: B:20:0x00fd  */
                public void onRetreivalComplete(java.lang.Object r6) {
                    /*
                    r5 = this;
                    r0 = com.fragments.ChangePassWordFragment.this;
                    r0 = r0.mContext;
                    r0 = (com.gaana.BaseActivity) r0;
                    r0.hideProgressDialog();
                    r6 = (java.lang.String) r6;
                    if (r6 == 0) goto L_0x0029;
                L_0x000d:
                    r0 = new com.google.gson.Gson;
                    r0.<init>();
                    if (r6 == 0) goto L_0x0029;
                L_0x0014:
                    r1 = com.fragments.ChangePassWordFragment.this;
                    r1 = r1.a(r6);
                    if (r1 == 0) goto L_0x0029;
                L_0x001c:
                    r6 = r6.trim();
                    r1 = com.gaana.login.ChangePasswordInfo.class;
                    r6 = r0.fromJson(r6, r1);
                    r6 = (com.gaana.login.ChangePasswordInfo) r6;
                    goto L_0x002a;
                L_0x0029:
                    r6 = 0;
                L_0x002a:
                    if (r6 == 0) goto L_0x00f3;
                L_0x002c:
                    r0 = r6.getStatus();
                    r0 = r0.intValue();
                    r1 = 1;
                    if (r0 != r1) goto L_0x00f3;
                L_0x0037:
                    r0 = r0;
                    r2 = new java.lang.StringBuilder;
                    r2.<init>();
                    r3 = "";
                    r2.append(r3);
                    r3 = com.fragments.ChangePassWordFragment.this;
                    r3 = r3.i;
                    r3 = r3.getText();
                    r3 = r3.toString();
                    r2.append(r3);
                    r2 = r2.toString();
                    r0.setPassword(r2);
                    r0 = com.gaana.login.LoginManager.getInstance();
                    r2 = r0;
                    r0.setLoginInfo(r2);
                    r0 = com.gaana.login.GooglePlusLogin.getInstance();
                    r2 = r1;
                    r2 = r2.getUserProfile();
                    r2 = r2.getEmail();
                    r2 = r2.trim();
                    r3 = com.fragments.ChangePassWordFragment.this;
                    r3 = r3.i;
                    r3 = r3.getText();
                    r3 = r3.toString();
                    r3 = r3.trim();
                    r4 = com.fragments.ChangePassWordFragment.this;
                    r4 = r4.getActivity();
                    r0.onEmailSaveClicked(r2, r3, r4);
                    r0 = com.fragments.ChangePassWordFragment.this;
                    r0 = r0.i;
                    r2 = "";
                    r0.setText(r2);
                    r0 = com.fragments.ChangePassWordFragment.this;
                    r0 = r0.h;
                    r2 = "";
                    r0.setText(r2);
                    r0 = com.managers.aj.a();
                    r2 = com.fragments.ChangePassWordFragment.this;
                    r2 = r2.getActivity();
                    r6 = r6.getMsg();
                    r0.a(r2, r6);
                    r6 = com.managers.u.a();
                    r0 = "Change Password";
                    r2 = "Chaneg Password";
                    r3 = "LoginType - email - Success";
                    r6.a(r0, r2, r3);
                    r6 = r0;
                    r6 = r6.getLoginMode();
                    r0 = com.gaana.models.User.LoginMode.SSO;
                    if (r6 != r0) goto L_0x00dd;
                L_0x00cf:
                    r6 = com.fragments.ChangePassWordFragment.this;
                    r6 = r6.mContext;
                    r6 = (com.gaana.BaseActivity) r6;
                    r0 = new com.fragments.ChangePassWordFragment$3$1;
                    r0.<init>();
                    r6.refreshUser(r0, r1);
                L_0x00dd:
                    r6 = com.fragments.ChangePassWordFragment.this;	 Catch:{ Exception -> 0x011b }
                    r6 = r6.getFragmentManager();	 Catch:{ Exception -> 0x011b }
                    r6 = r6.getBackStackEntryCount();	 Catch:{ Exception -> 0x011b }
                    if (r6 <= 0) goto L_0x011b;
                L_0x00e9:
                    r6 = com.fragments.ChangePassWordFragment.this;	 Catch:{ Exception -> 0x011b }
                    r6 = r6.getFragmentManager();	 Catch:{ Exception -> 0x011b }
                    r6.popBackStack();	 Catch:{ Exception -> 0x011b }
                    goto L_0x011b;
                L_0x00f3:
                    r0 = r6.getStatus();
                    r0 = r0.intValue();
                    if (r0 != 0) goto L_0x011b;
                L_0x00fd:
                    r0 = com.managers.aj.a();
                    r1 = com.fragments.ChangePassWordFragment.this;
                    r1 = r1.getActivity();
                    r6 = r6.getError();
                    r0.a(r1, r6);
                    r6 = com.managers.u.a();
                    r0 = "Change Password";
                    r1 = "Chaneg Password";
                    r2 = "LoginType - email - Failed";
                    r6.a(r0, r1, r2);
                L_0x011b:
                    return;
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.fragments.ChangePassWordFragment$AnonymousClass3.onRetreivalComplete(java.lang.Object):void");
                }

                public void onErrorResponse(BusinessObject businessObject) {
                    ((BaseActivity) ChangePassWordFragment.this.mContext).handleError(businessObject.getVolleyError().getMessage());
                }
            }, uRLManager);
            return;
        }
        ap.a().f(getActivity());
    }

    /* JADX WARNING: Missing exception handler attribute for start block: B:2:0x0006 */
    /* JADX WARNING: Failed to process nested try/catch */
    public boolean a(java.lang.String r2) {
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
        throw new UnsupportedOperationException("Method not decompiled: com.fragments.ChangePassWordFragment.a(java.lang.String):boolean");
    }

    private void b(final String str) {
        e();
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

                /* JADX WARNING: Removed duplicated region for block: B:15:0x0051  */
                /* JADX WARNING: Removed duplicated region for block: B:14:0x004b  */
                /* JADX WARNING: Removed duplicated region for block: B:14:0x004b  */
                /* JADX WARNING: Removed duplicated region for block: B:15:0x0051  */
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
                    goto L_0x003e;
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
                    goto L_0x003e;
                L_0x0026:
                    r7 = com.managers.aj.a();
                    r2 = com.fragments.ChangePassWordFragment.this;
                    r2 = r2.mContext;
                    r3 = com.fragments.ChangePassWordFragment.this;
                    r3 = r3.getContext();
                    r4 = 2131822373; // 0x7f110725 float:1.9277516E38 double:1.0532601975E-314;
                    r3 = r3.getString(r4);
                    r7.a(r2, r3);
                L_0x003e:
                    r7 = com.fragments.ChangePassWordFragment.this;
                    r7.f();
                    r7 = "1";
                    r7 = r0.equals(r7);
                    if (r7 == 0) goto L_0x0051;
                L_0x004b:
                    r7 = com.fragments.ChangePassWordFragment.this;
                    r7.d();
                    goto L_0x008c;
                L_0x0051:
                    r7 = "0";
                    r7 = r0.equals(r7);
                    if (r7 == 0) goto L_0x008c;
                L_0x0059:
                    r7 = "Email not exists";
                    r7 = r1.equalsIgnoreCase(r7);
                    if (r7 == 0) goto L_0x008c;
                L_0x0061:
                    r7 = com.fragments.ChangePassWordFragment.this;
                    r7 = r7.mContext;
                    r7 = (com.gaana.Login) r7;
                    r0 = r5;
                    r1 = "";
                    r7.setSignupEmailPwd(r0, r1);
                    r7 = com.fragments.ChangePassWordFragment.this;
                    r7 = r7.c;
                    r0 = "";
                    r1 = com.fragments.ChangePassWordFragment.this;
                    r1 = r1.getContext();
                    r2 = 2131821186; // 0x7f110282 float:1.9275108E38 double:1.053259611E-314;
                    r1 = r1.getString(r2);
                    r2 = 1;
                    r2 = java.lang.Boolean.valueOf(r2);
                    r3 = 0;
                    r7.a(r0, r1, r2, r3);
                L_0x008c:
                    return;
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.fragments.ChangePassWordFragment$AnonymousClass4.onRetreivalComplete(java.lang.Object):void");
                }
            }, uRLManager);
        }
    }

    private void d() {
        new CustomMaterialDialogView(this.mContext, this.mContext.getString(R.string.reset_password), this.mContext.getResources().getString(R.string.forgot_password_success_message), this.mContext.getResources().getString(R.string.forget_password_bottom_success_message)).show();
    }

    private void e() {
        try {
            if (this.b == null) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(getContext().getString(R.string.loading));
                stringBuilder.append("\t\t\t\t\t");
                this.b = ProgressDialog.show(this.mContext, "", stringBuilder.toString(), true, false);
                this.b.setCancelable(true);
            } else if (!this.b.isShowing()) {
                this.b.show();
            }
        } catch (Exception unused) {
        }
    }

    private void f() {
        try {
            if (this.b != null && this.b.isShowing()) {
                this.b.dismiss();
                this.b = null;
            }
        } catch (IllegalArgumentException unused) {
        }
    }

    public void onDestroyView() {
        if (!(this.a == null || this.a.getParent() == null)) {
            ((ViewGroup) this.a.getParent()).removeView(this.a);
        }
        super.onDestroyView();
    }
}
