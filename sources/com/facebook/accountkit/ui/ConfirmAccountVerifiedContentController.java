package com.facebook.accountkit.ui;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.text.Html;
import android.view.View;
import android.widget.TextView;
import com.facebook.accountkit.AccountKit;
import com.facebook.accountkit.PhoneLoginModel;
import com.facebook.accountkit.R;
import com.facebook.accountkit.internal.AccountKitController.Logger;
import com.facebook.accountkit.internal.Utility;
import com.facebook.accountkit.ui.LoginFlowBroadcastReceiver.Event;
import com.facebook.accountkit.ui.PrivacyPolicyFragment.OnCompleteListener;
import com.facebook.accountkit.ui.TitleFragmentFactory.TitleFragment;

final class ConfirmAccountVerifiedContentController extends ContentControllerBase implements ButtonContentController {
    private static final ButtonType DEFAULT_BUTTON_TYPE = ButtonType.CONTINUE;
    private static final LoginFlowState LOGIN_FLOW_STATE = LoginFlowState.ACCOUNT_VERIFIED;
    private PrivacyPolicyFragment bottomFragment;
    private ButtonType buttonType = DEFAULT_BUTTON_TYPE;
    private ContentFragment centerFragment;
    TitleFragment footerFragment;
    TitleFragment headerFragment;
    private OnCompleteListener onCompleteListener;
    private ContentFragment textFragment;
    private ContentFragment topFragment;

    public static class BottomFragment extends PrivacyPolicyFragment {
        private static final String ACCOUNT_KIT_URL = "https://www.accountkit.com/faq";

        public static BottomFragment create(@NonNull UIManager uIManager, @NonNull LoginFlowState loginFlowState, @NonNull ButtonType buttonType) {
            BottomFragment bottomFragment = new BottomFragment();
            bottomFragment.getViewState().putParcelable(ViewStateFragment.UI_MANAGER_KEY, uIManager);
            bottomFragment.setLoginFlowState(loginFlowState);
            bottomFragment.setNextButtonType(buttonType);
            return bottomFragment;
        }

        /* Access modifiers changed, original: protected */
        public void updateTermsText(TextView textView, CharSequence charSequence) {
            if (textView != null && getActivity() != null) {
                PhoneLoginModel currentPhoneNumberLogInModel = AccountKit.getCurrentPhoneNumberLogInModel();
                if (currentPhoneNumberLogInModel == null || Utility.isNullOrEmpty(currentPhoneNumberLogInModel.getPrivacyPolicy())) {
                    textView.setText(Html.fromHtml(getString(R.string.com_accountkit_confirmation_code_agreement_instant_verification, new Object[]{charSequence, "https://m.facebook.com/terms", "https://m.facebook.com/about/privacy/", "https://m.facebook.com/help/cookies/update", ACCOUNT_KIT_URL})));
                } else if (Utility.isNullOrEmpty(currentPhoneNumberLogInModel.getTermsOfService())) {
                    textView.setText(Html.fromHtml(getString(R.string.com_accountkit_confirmation_code_agreement_app_privacy_policy_instant_verification, new Object[]{charSequence, "https://m.facebook.com/terms", "https://m.facebook.com/about/privacy/", "https://m.facebook.com/help/cookies/update", currentPhoneNumberLogInModel.getPrivacyPolicy(), AccountKit.getApplicationName(), ACCOUNT_KIT_URL})));
                } else {
                    textView.setText(Html.fromHtml(getString(R.string.com_accountkit_confirmation_code_agreement_app_privacy_policy_and_terms_instant_verification, new Object[]{charSequence, "https://m.facebook.com/terms", "https://m.facebook.com/about/privacy/", "https://m.facebook.com/help/cookies/update", currentPhoneNumberLogInModel.getPrivacyPolicy(), currentPhoneNumberLogInModel.getTermsOfService(), AccountKit.getApplicationName(), ACCOUNT_KIT_URL})));
                }
            }
        }
    }

    @Nullable
    public View getFocusView() {
        return null;
    }

    public boolean isTransient() {
        return false;
    }

    ConfirmAccountVerifiedContentController(AccountKitConfiguration accountKitConfiguration) {
        super(accountKitConfiguration);
    }

    public ContentFragment getBottomFragment() {
        if (this.bottomFragment == null) {
            setBottomFragment(BottomFragment.create(this.configuration.getUIManager(), LOGIN_FLOW_STATE, DEFAULT_BUTTON_TYPE));
        }
        return this.bottomFragment;
    }

    public void setBottomFragment(@Nullable ContentFragment contentFragment) {
        if (contentFragment instanceof BottomFragment) {
            this.bottomFragment = (BottomFragment) contentFragment;
            this.bottomFragment.setOnCompleteListener(getOnCompleteListener());
            this.bottomFragment.setRetryVisible(false);
            updateNextButton();
        }
    }

    public ButtonType getButtonType() {
        return this.buttonType;
    }

    public void setButtonType(ButtonType buttonType) {
        this.buttonType = buttonType;
        updateNextButton();
    }

    public ContentFragment getCenterFragment() {
        if (this.centerFragment == null) {
            setCenterFragment(StaticContentFragmentFactory.create(this.configuration.getUIManager(), getLoginFlowState()));
        }
        return this.centerFragment;
    }

    public void setCenterFragment(@Nullable ContentFragment contentFragment) {
        this.centerFragment = contentFragment;
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
            setHeaderFragment(TitleFragmentFactory.create(this.configuration.getUIManager(), R.string.com_accountkit_account_verified, new String[0]));
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
            setTextFragment(StaticContentFragmentFactory.create(this.configuration.getUIManager(), getLoginFlowState()));
        }
        return this.textFragment;
    }

    public void setTextFragment(@Nullable ContentFragment contentFragment) {
        this.textFragment = contentFragment;
    }

    public ContentFragment getTopFragment() {
        if (this.topFragment == null) {
            setTopFragment(StaticContentFragmentFactory.create(this.configuration.getUIManager(), getLoginFlowState()));
        }
        return this.topFragment;
    }

    public void setTopFragment(@Nullable ContentFragment contentFragment) {
        this.topFragment = contentFragment;
    }

    private void updateNextButton() {
        if (this.topFragment != null && this.bottomFragment != null) {
            this.bottomFragment.setNextButtonType(getButtonType());
        }
    }

    /* Access modifiers changed, original: protected */
    public void logImpression() {
        if (this.bottomFragment != null) {
            Logger.logUIConfirmAccountVerified(true);
        }
    }

    private OnCompleteListener getOnCompleteListener() {
        if (this.onCompleteListener == null) {
            this.onCompleteListener = new OnCompleteListener() {
                public void onRetry(Context context) {
                }

                public void onNext(Context context, String str) {
                    if (ConfirmAccountVerifiedContentController.this.topFragment != null && ConfirmAccountVerifiedContentController.this.bottomFragment != null) {
                        Logger.logUIConfirmSeamlessLoginInteraction(str);
                        LocalBroadcastManager.getInstance(context).sendBroadcast(new Intent(LoginFlowBroadcastReceiver.ACTION_UPDATE).putExtra(LoginFlowBroadcastReceiver.EXTRA_EVENT, Event.CONFIRM_SEAMLESS_LOGIN));
                    }
                }
            };
        }
        return this.onCompleteListener;
    }
}
