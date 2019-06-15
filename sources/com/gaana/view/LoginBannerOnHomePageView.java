package com.gaana.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.constants.Constants;
import com.dynamicview.f.a;
import com.fragments.BaseGaanaFragment;
import com.gaana.BaseActivity;
import com.gaana.GaanaActivity;
import com.gaana.Login;
import com.gaana.R;
import com.gaana.analytics.MoEngage;
import com.gaana.application.GaanaApplication;
import com.gaana.login.FbLoginErrorDialog;
import com.gaana.login.LoginManager;
import com.gaana.login.LoginManager.IOnLoginCompleted;
import com.gaana.login.LoginManager.LOGIN_STATUS;
import com.gaana.login.UserInfo;
import com.gaana.models.BasicResponse;
import com.gaana.models.BusinessObject;
import com.gaana.models.User.LoginType;
import com.gaana.view.item.BaseItemView.DetailListingItemHolder;
import com.gaana.view.item.BaseItemView.ItemAdViewHolder;
import com.google.android.exoplayer2.C;
import com.managers.aj;
import com.managers.ap;
import com.managers.u;
import com.services.d;
import com.services.f;
import com.services.l.ad;
import com.services.l.s;
import com.utilities.Util;

public class LoginBannerOnHomePageView extends BaseItemView implements OnClickListener, IOnLoginCompleted {
    private LOGIN_BANNER_LIFE LOGIN_BANNER_LIFE_TYPE = LOGIN_BANNER_LIFE.NONE;
    private boolean isTrialUser = false;
    private ViewHolder loginBannerContentViewHolder;
    protected GaanaApplication mAppState;
    private BusinessObject mBusinessObject;
    private Context mContext;
    private a mDynamicView;
    private boolean mRefreshView = true;
    private View mView;

    enum LOGIN_BANNER_LIFE {
        NONE,
        LOGIN_BANNER_APP_LIFE,
        LOGIN_BANNER_HOME_LIFE,
        LOGIN_BANNER_NO_LIFE
    }

    public class LoginBannerContentViewHolder extends ViewHolder {
        public Button mFacebookLoginButton;
        public TextView mLoginBannerFirstLine;
        public TextView mLoginBannerSecondLine;
        public TextView mLoginButton;
        public TextView mSignupButton;

        public LoginBannerContentViewHolder(View view, boolean z) {
            super(view);
            if (z) {
                this.mLoginBannerFirstLine = (TextView) view.findViewById(R.id.login_banner_first_line);
                this.mLoginBannerSecondLine = (TextView) view.findViewById(R.id.login_banner_second_line);
                this.mFacebookLoginButton = (Button) view.findViewById(R.id.fb_onboard_login_btn);
                this.mLoginButton = (TextView) view.findViewById(R.id.pager_login_button);
                this.mSignupButton = (TextView) view.findViewById(R.id.pager_signup_button);
                this.mFacebookLoginButton.setOnClickListener(LoginBannerOnHomePageView.this);
                this.mLoginButton.setOnClickListener(LoginBannerOnHomePageView.this);
                this.mSignupButton.setOnClickListener(LoginBannerOnHomePageView.this);
                return;
            }
            this.mLoginBannerFirstLine = null;
            this.mLoginBannerSecondLine = null;
            this.mFacebookLoginButton = null;
            this.mLoginButton = null;
            this.mSignupButton = null;
        }
    }

    private void toggleLoginSignup(boolean z) {
    }

    public LoginBannerOnHomePageView(Context context, BaseGaanaFragment baseGaanaFragment, a aVar) {
        super(context, baseGaanaFragment);
        this.mContext = context;
        this.mFragment = baseGaanaFragment;
        this.mAppState = (GaanaApplication) this.mContext.getApplicationContext();
        this.mDynamicView = aVar;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (this.mAppState.getCurrentUser().getLoginStatus()) {
            this.mView = null;
            return new ItemAdViewHolder(getEmptyLayout());
        } else if (getLoginBannerLife() == LOGIN_BANNER_LIFE.LOGIN_BANNER_NO_LIFE) {
            this.mView = null;
            return new ItemAdViewHolder(getEmptyLayout());
        } else if (this.LOGIN_BANNER_LIFE_TYPE != LOGIN_BANNER_LIFE.LOGIN_BANNER_HOME_LIFE || !Constants.dB) {
            return new DetailListingItemHolder(getNewView(0, viewGroup));
        } else {
            this.mView = null;
            return new ItemAdViewHolder(getEmptyLayout());
        }
    }

    public View getNewView(int i, ViewGroup viewGroup) {
        this.mView = LayoutInflater.from(this.mContext).inflate(R.layout.view_login_home_page_banner, viewGroup, false);
        return this.mView;
    }

    public View getPopulatedView(int i, ViewHolder viewHolder, ViewGroup viewGroup) {
        return getPopulatedView(i, viewHolder.itemView, viewGroup, null);
    }

    public a getDynamicView() {
        return this.mDynamicView;
    }

    public View getPopulatedView(final int i, View view, ViewGroup viewGroup, BusinessObject businessObject) {
        if (this.mView != null) {
            if (this.mRefreshView) {
                LoginManager.getInstance().checkTrialAvailability(this.mContext, new s() {
                    public void onRetreivalComplete(BusinessObject businessObject) {
                        LoginBannerOnHomePageView.this.mBusinessObject = businessObject;
                        if (LoginBannerOnHomePageView.this.mFragment != null) {
                            LoginBannerOnHomePageView.this.mFragment.notifyItemChanged(i);
                        }
                    }

                    public void onErrorResponse(BusinessObject businessObject) {
                        LoginBannerOnHomePageView.this.mRefreshView = true;
                    }
                });
                this.mRefreshView = false;
            } else if (this.loginBannerContentViewHolder == null) {
                checkAndAddView();
            }
        }
        return view;
    }

    private void checkAndAddView() {
        if (((this.mBusinessObject != null ? 1 : 0) & (this.mBusinessObject instanceof BasicResponse)) != 0) {
            View inflate = LayoutInflater.from(this.mContext).inflate(R.layout.login_banner_on_home_page, null);
            this.loginBannerContentViewHolder = new LoginBannerContentViewHolder(inflate, true);
            LinearLayout linearLayout = (LinearLayout) this.mView.findViewById(R.id.loginBannerSlot);
            if (((BasicResponse) this.mBusinessObject).getResult() == null || !((BasicResponse) this.mBusinessObject).getResult().equalsIgnoreCase("Yes")) {
                this.isTrialUser = false;
                ((LoginBannerContentViewHolder) this.loginBannerContentViewHolder).mLoginBannerFirstLine.setText(getResources().getString(R.string.login_banner_first_line_text_non_trial));
                ((LoginBannerContentViewHolder) this.loginBannerContentViewHolder).mLoginBannerSecondLine.setText(getResources().getString(R.string.login_banner_second_line_text_non_trial));
                linearLayout.addView(inflate);
            } else {
                this.isTrialUser = true;
                linearLayout.addView(inflate);
            }
            if (this.LOGIN_BANNER_LIFE_TYPE == LOGIN_BANNER_LIFE.LOGIN_BANNER_HOME_LIFE) {
                Constants.dB = true;
            }
        }
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.fb_onboard_login_btn) {
            if (this.isTrialUser) {
                u.a().a("Login", "LoginFeed - FreeTrialUser", "FBLogin");
            } else {
                u.a().a("Login", "LoginFeed - NormalUser", "FBLogin");
            }
            loginWithFB();
        } else if (id == R.id.pager_login_button) {
            if (this.isTrialUser) {
                u.a().a("Login", "LoginFeed - FreeTrialUser", "Login");
            } else {
                u.a().a("Login", "LoginFeed - NormalUser", "Login");
            }
            launchOnBoardLoginSignup(false);
        } else if (id == R.id.pager_signup_button) {
            if (this.isTrialUser) {
                u.a().a("Login", "LoginFeed - FreeTrialUser", "Signup");
            } else {
                u.a().a("Login", "LoginFeed - NormalUser", "Signup");
            }
            launchOnBoardLoginSignup(true);
        }
    }

    private void launchOnBoardLoginSignup(boolean z) {
        ((BaseActivity) this.mContext).checkSetLoginStatusFromBottomSheet(new ad() {
            public void onLoginSuccess() {
                LoginBannerOnHomePageView.this.launchHome();
            }
        }, "HOMEPAGE", null, z, true);
    }

    private void loginWithFB() {
        MoEngage.getInstance().reportLoginStarted("FB");
        if (Util.j(this.mContext)) {
            toggleLoginSignup(false);
            LoginManager.getInstance().login((BaseActivity) this.mContext, LoginType.FB, (IOnLoginCompleted) this);
            return;
        }
        ap.a().f(this.mContext);
    }

    public void onLoginCompleted(LOGIN_STATUS login_status, UserInfo userInfo, Bundle bundle) {
        ((BaseActivity) this.mContext).hideProgressDialog();
        switch (login_status) {
            case LOGIN_SUCCEDED:
                MoEngage.getInstance().reportOnLogin(userInfo);
                launchHome();
                return;
            case LOGIN_ERROR_LAUNCH_TRAP_PAGE:
                launchTrapPage(userInfo);
                return;
            case LOGIN_ERROR_AUTHENTICATION_FAILED:
            case LOGIN_FAILURE_SSO:
            case LOGIN_FAILURE_SDK_NOT_INITIALIZED:
                if (userInfo == null || userInfo.getError() == null) {
                    aj.a().a(this.mContext, this.mContext.getString(R.string.login_failed));
                    return;
                } else {
                    aj.a().a(this.mContext, userInfo.getError());
                    return;
                }
            case LOGIN_MANDATORY_FIELD_MISSING:
                if (this.mContext != null && !((Activity) this.mContext).isFinishing()) {
                    new f(this.mContext).a(this.mContext.getResources().getString(R.string.mandatory_field_missing));
                    return;
                }
                return;
            case LOGIN_EMAIL_MISSING_FB:
                if (this.mContext != null && !((Activity) this.mContext).isFinishing()) {
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

    private void launchHome() {
        Intent intent = new Intent(this.mAppState, GaanaActivity.class);
        intent.setFlags(71303168);
        this.mContext.startActivity(intent);
    }

    private void launchTrapPage(UserInfo userInfo) {
        Intent intent = new Intent(this.mContext, Login.class);
        intent.setFlags(C.ENCODING_PCM_MU_LAW);
        intent.putExtra("temp_user_tag", LoginManager.getInstance().getLoginInfo());
        if (!(userInfo == null || userInfo.getError() == null)) {
            intent.putExtra("message", userInfo.getError());
        }
        this.mContext.startActivity(intent);
    }

    public void clearLoginBanner() {
        if (this.mView != null) {
            LinearLayout linearLayout = (LinearLayout) this.mView.findViewById(R.id.loginBannerSlot);
            if (linearLayout != null) {
                linearLayout.removeAllViews();
            }
        }
    }

    private LOGIN_BANNER_LIFE getLoginBannerLife() {
        if (GaanaApplication.sessionHistoryCount < Constants.dv) {
            this.LOGIN_BANNER_LIFE_TYPE = LOGIN_BANNER_LIFE.LOGIN_BANNER_APP_LIFE;
            if (GaanaApplication.sessionHistoryCount == Constants.dv - 1) {
                Constants.dx = GaanaApplication.sessionHistoryCount + Constants.dw;
                d.a().a("PREFERENCE_HOME_FEED_APPLICATION_STATUS", Constants.dx, false);
            }
            return this.LOGIN_BANNER_LIFE_TYPE;
        } else if (GaanaApplication.sessionHistoryCount <= Constants.dx) {
            this.LOGIN_BANNER_LIFE_TYPE = LOGIN_BANNER_LIFE.LOGIN_BANNER_HOME_LIFE;
            if (GaanaApplication.sessionHistoryCount == Constants.dx) {
                Constants.dz = String.valueOf(System.currentTimeMillis());
                d.a().a("PREFERENCE_LAST_TIME_LOGIN_BANNER_SHOWN", Constants.dz, false);
            }
            return this.LOGIN_BANNER_LIFE_TYPE;
        } else {
            this.LOGIN_BANNER_LIFE_TYPE = LOGIN_BANNER_LIFE.LOGIN_BANNER_NO_LIFE;
            return this.LOGIN_BANNER_LIFE_TYPE;
        }
    }

    public void setResfreshStatus(boolean z) {
        this.mRefreshView = z;
    }

    public void setUserVisibleHint(boolean z) {
        if (!z && this.LOGIN_BANNER_LIFE_TYPE == LOGIN_BANNER_LIFE.LOGIN_BANNER_HOME_LIFE) {
            clearLoginBanner();
        }
    }
}
