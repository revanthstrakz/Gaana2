package com.gaana.login.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.gaana.BaseActivity;
import com.gaana.GaanaActivity;
import com.gaana.Login;
import com.gaana.R;
import com.gaana.analytics.MoEngage;
import com.gaana.application.GaanaApplication;
import com.gaana.login.LoginInfo;
import com.gaana.login.LoginManager;
import com.gaana.models.Languages.Language;
import com.gaana.models.User.LoginType;
import com.managers.ap;
import com.managers.u;
import com.utilities.Util;
import java.util.ArrayList;

public class SignupFragment extends Fragment implements OnClickListener {
    public static final String EXTRA_ARG_HIDE_SKIP = "extra_hide_skip";
    public static final String EXTRA_LAUNCH_SIGNUP_DETAILS = "extra_signup_details";
    private TextView cancelImg;
    private boolean hideSkip;
    protected GaanaApplication mAppState;
    private Button mBtnFBSignup;
    private Button mBtnGoogleSignup;
    private Context mContext;
    private RelativeLayout mRlFbBtn;
    private RelativeLayout mRlGoogleBtn;
    private TextView mTxtSubtitle;
    private ArrayList<Language> savedLanguages = null;

    public static Fragment newInstance(boolean z, LoginInfo loginInfo) {
        SignupFragment signupFragment = new SignupFragment();
        Bundle bundle = new Bundle();
        bundle.putBoolean("extra_hide_skip", z);
        bundle.putSerializable("temp_user_tag", loginInfo);
        signupFragment.setArguments(bundle);
        return signupFragment;
    }

    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        if (getArguments() != null) {
            this.hideSkip = getArguments().getBoolean("extra_hide_skip", false);
            LoginInfo loginInfo = (LoginInfo) getArguments().getSerializable("temp_user_tag");
            if (loginInfo != null) {
                ((Login) this.mContext).displayFragment(SignupDetailsFragment.newInstance("", "", loginInfo, true), false);
            }
        }
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_signup, viewGroup, false);
        this.mContext = getActivity();
        init(inflate);
        return inflate;
    }

    private void init(View view) {
        this.cancelImg = (TextView) view.findViewById(R.id.img_cancel);
        this.cancelImg.setOnClickListener(this);
        if (this.hideSkip) {
            this.cancelImg.setVisibility(8);
        }
        this.mRlFbBtn = (RelativeLayout) view.findViewById(R.id.rl_fb_btn);
        this.mRlFbBtn.setOnClickListener(this);
        this.mBtnFBSignup = (Button) view.findViewById(R.id.onboard_btn_fb);
        this.mBtnFBSignup.setOnClickListener(this);
        this.mRlGoogleBtn = (RelativeLayout) view.findViewById(R.id.rl_google_btn);
        this.mBtnGoogleSignup = (Button) view.findViewById(R.id.onboard_btn_google);
        this.mBtnGoogleSignup.setOnClickListener(this);
        this.mRlGoogleBtn.setOnClickListener(this);
        this.mTxtSubtitle = (TextView) view.findViewById(R.id.onboard_subtitle);
        ((Button) view.findViewById(R.id.phone_login_button)).setOnClickListener(this);
        ((Button) view.findViewById(R.id.email_login_button)).setOnClickListener(this);
        if (getActivity().getIntent() != null && getActivity().getIntent().getBooleanExtra("is_login_as_activity_result", false)) {
            String stringExtra = getActivity().getIntent().getStringExtra("Launched_From");
            if (!TextUtils.isEmpty(stringExtra)) {
                this.mTxtSubtitle.setText(stringExtra);
            }
        }
    }

    public void onClick(View view) {
        Util.a(this.mContext, view);
        ((Login) this.mContext).removeGoogleSignSmartDialog();
        BaseActivity baseActivity;
        String str;
        String str2;
        StringBuilder stringBuilder;
        switch (view.getId()) {
            case R.id.email_login_button /*2131297034*/:
                ((Login) getActivity()).displayFragment(SignupDetailsFragment.newInstance("", "", null, false), false);
                break;
            case R.id.img_cancel /*2131297436*/:
                u.a().b("Registration", "Skip");
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
                    baseActivity = (BaseActivity) this.mContext;
                    str = ((BaseActivity) this.mContext).currentScreen;
                    str2 = ((BaseActivity) this.mContext).currentScreen;
                    stringBuilder = new StringBuilder();
                    stringBuilder.append(((BaseActivity) this.mContext).currentScreen);
                    stringBuilder.append(" - FB");
                    baseActivity.sendGAEvent(str, str2, stringBuilder.toString());
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
                        baseActivity = (BaseActivity) this.mContext;
                        str = ((BaseActivity) this.mContext).currentScreen;
                        str2 = ((BaseActivity) this.mContext).currentScreen;
                        stringBuilder = new StringBuilder();
                        stringBuilder.append(((BaseActivity) this.mContext).currentScreen);
                        stringBuilder.append(" - Google Plus");
                        baseActivity.sendGAEvent(str, str2, stringBuilder.toString());
                        LoginManager.getInstance().login((Activity) this.mContext, LoginType.GOOGLE, (Login) this.mContext);
                        break;
                    }
                    ap.a().f(this.mContext);
                    return;
                }
                return;
            case R.id.phone_login_button /*2131297967*/:
                LoginManager.getInstance().login((Activity) this.mContext, LoginType.PHONENUMBER, (Login) this.mContext);
                break;
        }
    }
}
