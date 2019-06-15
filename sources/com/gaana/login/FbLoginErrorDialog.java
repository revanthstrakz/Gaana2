package com.gaana.login;

import android.content.Context;
import android.content.DialogInterface.OnCancelListener;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.view.View;
import android.view.View.OnClickListener;
import com.gaana.R;
import com.gaana.login.LoginManager.IOnLoginCompleted;
import com.gaana.view.item.PopupItemView.IDismissPopup;

public class FbLoginErrorDialog extends BottomSheetDialog implements OnClickListener {
    private View mBtnEmailLogin;
    private View mBtnPhoneLogin;
    private Context mContext;
    private View mGoogleLoginView;
    private IDismissPopup mIDismissPopup;
    private View mView;
    private IOnLoginCompleted onLoginCompletedListener;

    public FbLoginErrorDialog(@NonNull Context context) {
        super(context);
        this.mContext = context;
        requestWindowFeature(1);
        initView();
    }

    public FbLoginErrorDialog(@NonNull Context context, @StyleRes int i) {
        super(context, i);
        this.mContext = context;
        requestWindowFeature(1);
        initView();
    }

    protected FbLoginErrorDialog(@NonNull Context context, boolean z, OnCancelListener onCancelListener) {
        super(context, z, onCancelListener);
        this.mContext = context;
        requestWindowFeature(1);
        initView();
    }

    public void setOnLoginCompletedListener(IOnLoginCompleted iOnLoginCompleted) {
        this.onLoginCompletedListener = iOnLoginCompleted;
    }

    private void initView() {
        setContentView((int) R.layout.dialog_fb_login_error);
        this.mView = findViewById(R.id.parentView);
        this.mGoogleLoginView = findViewById(R.id.rl_google_btn);
        findViewById(R.id.onboard_btn_google).setOnClickListener(this);
        this.mGoogleLoginView.setOnClickListener(this);
        this.mBtnEmailLogin = findViewById(R.id.email_login_button);
        this.mBtnEmailLogin.setOnClickListener(this);
        this.mBtnPhoneLogin = findViewById(R.id.phone_login_button);
        this.mBtnPhoneLogin.setOnClickListener(this);
        BottomSheetBehavior.from(this.mView).setState(3);
    }

    /* JADX WARNING: Missing block: B:7:0x0016, code skipped:
            if (r4 != com.gaana.R.id.rl_google_btn) goto L_0x00be;
     */
    public void onClick(android.view.View r4) {
        /*
        r3 = this;
        r4 = r4.getId();
        r0 = 2131297034; // 0x7f09030a float:1.8212002E38 double:1.0530006456E-314;
        if (r4 == r0) goto L_0x0067;
    L_0x0009:
        r0 = 2131297860; // 0x7f090644 float:1.8213677E38 double:1.0530010537E-314;
        if (r4 == r0) goto L_0x002e;
    L_0x000e:
        r0 = 2131297967; // 0x7f0906af float:1.8213894E38 double:1.0530011065E-314;
        if (r4 == r0) goto L_0x001a;
    L_0x0013:
        r0 = 2131298258; // 0x7f0907d2 float:1.8214484E38 double:1.0530012503E-314;
        if (r4 == r0) goto L_0x002e;
    L_0x0018:
        goto L_0x00be;
    L_0x001a:
        r3.dismiss();
        r4 = com.gaana.login.LoginManager.getInstance();
        r0 = r3.mContext;
        r0 = (android.app.Activity) r0;
        r1 = com.gaana.models.User.LoginType.PHONENUMBER;
        r2 = r3.onLoginCompletedListener;
        r4.login(r0, r1, r2);
        goto L_0x00be;
    L_0x002e:
        r4 = r3.mContext;
        r4 = (android.app.Activity) r4;
        r4 = com.utilities.Util.a(r4);
        if (r4 != 0) goto L_0x0039;
    L_0x0038:
        return;
    L_0x0039:
        r4 = com.gaana.analytics.MoEngage.getInstance();
        r0 = "GOOGLE";
        r4.reportLoginStarted(r0);
        r4 = r3.mContext;
        r4 = com.utilities.Util.j(r4);
        if (r4 != 0) goto L_0x0054;
    L_0x004a:
        r4 = com.managers.ap.a();
        r0 = r3.mContext;
        r4.f(r0);
        return;
    L_0x0054:
        r3.dismiss();
        r4 = com.gaana.login.LoginManager.getInstance();
        r0 = r3.mContext;
        r0 = (android.app.Activity) r0;
        r1 = com.gaana.models.User.LoginType.GOOGLE;
        r2 = r3.onLoginCompletedListener;
        r4.login(r0, r1, r2);
        goto L_0x00be;
    L_0x0067:
        r3.dismiss();
        r4 = r3.mContext;
        r4 = r4 instanceof com.gaana.Login;
        r0 = 1;
        if (r4 == 0) goto L_0x00a0;
    L_0x0071:
        r4 = r3.mContext;
        r4 = (com.gaana.Login) r4;
        r4.isLoginViewVisible = r0;
        r4 = r3.mContext;
        r4 = (com.gaana.Login) r4;
        r0 = r3.mContext;
        r0 = (com.gaana.Login) r0;
        r0 = r0.emailId;
        r1 = r3.mContext;
        r1 = (com.gaana.Login) r1;
        r1 = r1.password;
        r0 = com.gaana.login.fragments.EmailLoginFragment.newInstance(r0, r1);
        r1 = 0;
        r4.displayFragment(r0, r1);
        r4 = r3.mContext;
        r4 = (com.gaana.Login) r4;
        r0 = "";
        r4.emailId = r0;
        r4 = r3.mContext;
        r4 = (com.gaana.Login) r4;
        r0 = "";
        r4.password = r0;
        goto L_0x00be;
    L_0x00a0:
        r4 = new android.content.Intent;
        r1 = r3.mContext;
        r2 = com.gaana.Login.class;
        r4.<init>(r1, r2);
        r1 = r3.mContext;
        r1 = r1 instanceof android.app.Activity;
        if (r1 != 0) goto L_0x00b4;
    L_0x00af:
        r1 = 268435456; // 0x10000000 float:2.5243549E-29 double:1.32624737E-315;
        r4.addFlags(r1);
    L_0x00b4:
        r1 = "email_login";
        r4.putExtra(r1, r0);
        r0 = r3.mContext;
        r0.startActivity(r4);
    L_0x00be:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.gaana.login.FbLoginErrorDialog.onClick(android.view.View):void");
    }

    public void setOnDismissListener(IDismissPopup iDismissPopup) {
        this.mIDismissPopup = iDismissPopup;
    }
}
