package com.facebook.accountkit.ui;

import android.support.annotation.Nullable;
import android.view.View;
import com.facebook.accountkit.AccountKit;
import com.facebook.accountkit.R;
import com.facebook.accountkit.internal.AccountKitController.Logger;
import com.facebook.accountkit.ui.StaticContentFragmentFactory.StaticContentFragment;
import com.facebook.accountkit.ui.TitleFragmentFactory.TitleFragment;

final class VerifiedCodeContentController extends ContentControllerBase {
    private static final LoginFlowState LOGIN_FLOW_STATE = LoginFlowState.VERIFIED;
    private StaticContentFragment bottomFragment;
    private StaticContentFragment centerFragment;
    private TitleFragment footerFragment;
    private TitleFragment headerFragment;
    private StaticContentFragment textFragment;
    private StaticContentFragment topFragment;

    @Nullable
    public View getFocusView() {
        return null;
    }

    VerifiedCodeContentController(AccountKitConfiguration accountKitConfiguration) {
        super(accountKitConfiguration);
    }

    public ContentFragment getBottomFragment() {
        if (this.bottomFragment == null) {
            setBottomFragment(StaticContentFragmentFactory.create(this.configuration.getUIManager(), getLoginFlowState()));
        }
        return this.bottomFragment;
    }

    public void setBottomFragment(@Nullable ContentFragment contentFragment) {
        if (contentFragment instanceof StaticContentFragment) {
            this.bottomFragment = (StaticContentFragment) contentFragment;
        }
    }

    public ContentFragment getCenterFragment() {
        if (this.centerFragment == null) {
            setCenterFragment(StaticContentFragmentFactory.create(this.configuration.getUIManager(), getLoginFlowState(), R.layout.com_accountkit_fragment_verified_code_center));
        }
        return this.centerFragment;
    }

    public void setCenterFragment(@Nullable ContentFragment contentFragment) {
        if (contentFragment instanceof StaticContentFragment) {
            this.centerFragment = (StaticContentFragment) contentFragment;
        }
    }

    public TitleFragment getFooterFragment() {
        if (this.footerFragment == null) {
            setFooterFragment(TitleFragmentFactory.create(this.configuration.getUIManager(), R.string.com_accountkit_return_title, AccountKit.getApplicationName()));
        }
        return this.footerFragment;
    }

    public void setFooterFragment(@Nullable TitleFragment titleFragment) {
        this.footerFragment = titleFragment;
    }

    public TitleFragment getHeaderFragment() {
        if (this.headerFragment == null) {
            setHeaderFragment(TitleFragmentFactory.create(this.configuration.getUIManager(), R.string.com_accountkit_success_title, new String[0]));
        }
        return this.headerFragment;
    }

    public void setHeaderFragment(@Nullable TitleFragment titleFragment) {
        this.headerFragment = titleFragment;
    }

    public LoginFlowState getLoginFlowState() {
        return LOGIN_FLOW_STATE;
    }

    public ContentFragment getTextFragment() {
        if (this.textFragment == null) {
            this.textFragment = StaticContentFragmentFactory.create(this.configuration.getUIManager(), getLoginFlowState());
        }
        return this.textFragment;
    }

    public void setTextFragment(@Nullable ContentFragment contentFragment) {
        if (contentFragment instanceof StaticContentFragment) {
            this.textFragment = (StaticContentFragment) contentFragment;
        }
    }

    public ContentFragment getTopFragment() {
        if (this.topFragment == null) {
            setTopFragment(StaticContentFragmentFactory.create(this.configuration.getUIManager(), getLoginFlowState()));
        }
        return this.topFragment;
    }

    public void setTopFragment(@Nullable ContentFragment contentFragment) {
        if (contentFragment instanceof StaticContentFragment) {
            this.topFragment = (StaticContentFragment) contentFragment;
        }
    }

    /* Access modifiers changed, original: protected */
    public void logImpression() {
        Logger.logUIVerifiedCode(true, this.configuration.getLoginType());
    }
}
