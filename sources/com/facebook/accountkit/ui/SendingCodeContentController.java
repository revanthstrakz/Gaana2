package com.facebook.accountkit.ui;

import android.support.annotation.Nullable;
import android.view.View;
import com.facebook.accountkit.AccountKitError.Type;
import com.facebook.accountkit.AccountKitException;
import com.facebook.accountkit.R;
import com.facebook.accountkit.internal.AccountKitController.Logger;
import com.facebook.accountkit.internal.InternalAccountKitError;
import com.facebook.accountkit.ui.StaticContentFragmentFactory.StaticContentFragment;
import com.facebook.accountkit.ui.TitleFragmentFactory.TitleFragment;

final class SendingCodeContentController extends ContentControllerBase {
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

    SendingCodeContentController(AccountKitConfiguration accountKitConfiguration) {
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
            setCenterFragment(StaticContentFragmentFactory.create(this.configuration.getUIManager(), getLoginFlowState(), R.layout.com_accountkit_fragment_sending_code_center));
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
            setFooterFragment(TitleFragmentFactory.create(this.configuration.getUIManager()));
        }
        return this.footerFragment;
    }

    public void setFooterFragment(@Nullable TitleFragment titleFragment) {
        this.footerFragment = titleFragment;
    }

    public TitleFragment getHeaderFragment() {
        if (this.headerFragment == null) {
            int i;
            switch (this.configuration.getLoginType()) {
                case EMAIL:
                    i = R.string.com_accountkit_email_loading_title;
                    break;
                case PHONE:
                    i = R.string.com_accountkit_phone_loading_title;
                    break;
                default:
                    throw new AccountKitException(Type.INTERNAL_ERROR, InternalAccountKitError.UNEXPECTED_STATE);
            }
            setHeaderFragment(TitleFragmentFactory.create(this.configuration.getUIManager(), i, new String[0]));
        }
        return this.headerFragment;
    }

    public void setHeaderFragment(@Nullable TitleFragment titleFragment) {
        this.headerFragment = titleFragment;
    }

    public LoginFlowState getLoginFlowState() {
        return LoginFlowState.SENDING_CODE;
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
        Logger.logUISendingCode(true, this.configuration.getLoginType());
    }
}
