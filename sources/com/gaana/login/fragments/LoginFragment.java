package com.gaana.login.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import com.gaana.BaseActivity;
import com.gaana.GaanaActivity;
import com.gaana.Login;
import com.gaana.R;
import com.gaana.analytics.MoEngage;
import com.gaana.application.GaanaApplication;
import com.gaana.login.LoginManager;
import com.gaana.models.User.LoginType;
import com.managers.an;
import com.managers.ap;
import com.managers.u;
import com.utilities.Util;
import com.views.CustomButtonView;
import java.util.Calendar;

public class LoginFragment extends Fragment implements OnClickListener {
    public static final String EXTRA_ARG_HIDE_SKIP = "extra_hide_skip";
    private boolean hideSkip;
    private long initialTime;
    protected GaanaApplication mAppState;
    private Context mContext;
    private ImageButton mRlFbLogin;
    private ImageButton mRlGoogleLogin;
    private TextView mTxtContinue;
    private TextView mTxtSignUp;
    private TextView mTxtSubtitle;
    private TextView skipText;

    public static Fragment newInstance(boolean z) {
        LoginFragment loginFragment = new LoginFragment();
        Bundle bundle = new Bundle();
        bundle.putBoolean("extra_hide_skip", z);
        loginFragment.setArguments(bundle);
        return loginFragment;
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_login, viewGroup, false);
        this.mContext = getActivity();
        if (getArguments() != null) {
            this.hideSkip = getArguments().getBoolean("extra_hide_skip", false);
        }
        init(inflate);
        return inflate;
    }

    private void init(View view) {
        this.skipText = (TextView) view.findViewById(R.id.img_cancel_skip);
        this.skipText.setOnClickListener(this);
        this.skipText.setTypeface(Typeface.createFromAsset(this.mContext.getAssets(), "fonts/Roboto-Bold.ttf"));
        if (this.hideSkip) {
            this.skipText.setVisibility(8);
        }
        this.mTxtContinue = (TextView) view.findViewById(R.id.continue_text);
        this.mTxtContinue.setTypeface(Typeface.createFromAsset(this.mContext.getAssets(), "fonts/Roboto-Bold.ttf"));
        this.mRlFbLogin = (ImageButton) view.findViewById(R.id.rl_fb_btn);
        this.mRlFbLogin.setOnClickListener(this);
        this.mRlFbLogin.setOnClickListener(this);
        this.mRlGoogleLogin = (ImageButton) view.findViewById(R.id.rl_google_btn);
        this.mRlGoogleLogin.setOnClickListener(this);
        ((ImageButton) view.findViewById(R.id.email_login)).setOnClickListener(this);
        ((CustomButtonView) view.findViewById(R.id.phone_login_button)).setOnClickListener(this);
        an.a().a("click", "ac", "", an.a().a(an.a().a), "", "LOGIN", "", "");
    }

    public void onClick(View view) {
        Util.a(this.mContext, view);
        ((Login) this.mContext).removeGoogleSignSmartDialog();
        switch (view.getId()) {
            case R.id.email_login /*2131297033*/:
                an.a().a("click", "ac", "", "LOGIN", "", "GAANA", "", "");
                showLoginView();
                break;
            case R.id.img_cancel_skip /*2131297437*/:
                u.a().b("Login", "Skip");
                an.a().a("click", "ac", "", "LOGIN", "", "SKIP", "", "");
                Intent intent = new Intent(this.mContext, GaanaActivity.class);
                if (Login.isSignupFromInside) {
                    intent.setFlags(71303168);
                } else {
                    intent.setFlags(335544320);
                }
                if (LoginManager.getInstance().checkDisableInternationalOnBoarding(this.mContext)) {
                    startActivity(intent);
                }
                ((Login) this.mContext).finish();
                break;
            case R.id.onboard_btn_fb /*2131297859*/:
            case R.id.rl_fb_btn /*2131298257*/:
                MoEngage.getInstance().reportLoginStarted("FB");
                if (Util.j(this.mContext)) {
                    BaseActivity baseActivity = (BaseActivity) this.mContext;
                    String str = ((BaseActivity) this.mContext).currentScreen;
                    String str2 = ((BaseActivity) this.mContext).currentScreen;
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(((BaseActivity) this.mContext).currentScreen);
                    stringBuilder.append(" - FB");
                    baseActivity.sendGAEvent(str, str2, stringBuilder.toString());
                    an.a().a("click", "ac", "", "LOGIN", "", "FB", "", "");
                    this.initialTime = Calendar.getInstance().getTimeInMillis();
                    LoginManager.getInstance().login((Activity) this.mContext, LoginType.FB, (Login) this.mContext);
                    break;
                }
                ap.a().f(this.mContext);
                return;
            case R.id.onboard_btn_google /*2131297860*/:
            case R.id.rl_google_btn /*2131298258*/:
                if (Util.a((Activity) this.mContext)) {
                    MoEngage.getInstance().reportLoginStarted("GOOGLE");
                    if (Util.j(this.mContext)) {
                        LoginManager.getInstance().login((Activity) this.mContext, LoginType.GOOGLE, (Login) this.mContext);
                        break;
                    } else {
                        ap.a().f(this.mContext);
                        return;
                    }
                }
                return;
            case R.id.phone_login_button /*2131297967*/:
                an.a().a("click", "ac", "", "LOGIN", "", "PHONENUMBER", "", "");
                LoginManager.getInstance().login((Activity) this.mContext, LoginType.PHONENUMBER, (Login) this.mContext);
                break;
        }
    }

    public void showLoginView() {
        ((Login) this.mContext).isLoginViewVisible = true;
        ((Login) this.mContext).displayFragment(EmailLoginFragment.newInstance(((Login) this.mContext).emailId, ((Login) this.mContext).password), false);
        ((Login) this.mContext).emailId = "";
        ((Login) this.mContext).password = "";
    }
}
