package com.facebook.accountkit.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.facebook.accountkit.AccountKit;
import com.facebook.accountkit.PhoneLoginModel;
import com.facebook.accountkit.R;
import com.facebook.accountkit.internal.AccountKitController.Logger;
import com.facebook.accountkit.internal.Utility;
import com.facebook.accountkit.ui.SkinManager.Skin;

public class PrivacyPolicyFragment extends ContentFragment {
    protected static final String COOKIE_URL = "https://m.facebook.com/help/cookies/update";
    protected static final String DATA_URL = "https://m.facebook.com/about/privacy/";
    private static final String LOGIN_FLOW_STATE = "login_flow_state";
    private static final String NEXT_BUTTON_TYPE = "next_button_type";
    private static final String RETRY_BUTTON_VISIBLE = "retry button visible";
    private static final String RETRY_KEY = "retry";
    protected static final String TERMS_URL = "https://m.facebook.com/terms";
    private LoginFlowState loginFlowState;
    private Button nextButton;
    private boolean nextButtonEnabled = true;
    private ButtonType nextButtonType;
    private OnCompleteListener onCompleteListener;
    private TextView retryButton;
    private boolean retryButtonVisible = true;
    private TextView termsText;

    public interface OnCompleteListener {
        void onNext(Context context, String str);

        void onRetry(Context context);
    }

    /* Access modifiers changed, original: 0000 */
    public boolean isKeyboardFragment() {
        return true;
    }

    public /* bridge */ /* synthetic */ void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
    }

    public /* bridge */ /* synthetic */ void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    public /* bridge */ /* synthetic */ View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return super.onCreateView(layoutInflater, viewGroup, bundle);
    }

    public /* bridge */ /* synthetic */ void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
    }

    public static PrivacyPolicyFragment create(@NonNull UIManager uIManager, @NonNull LoginFlowState loginFlowState, @NonNull ButtonType buttonType) {
        PrivacyPolicyFragment privacyPolicyFragment = new PrivacyPolicyFragment();
        privacyPolicyFragment.getViewState().putParcelable(ViewStateFragment.UI_MANAGER_KEY, uIManager);
        privacyPolicyFragment.setLoginFlowState(loginFlowState);
        privacyPolicyFragment.setNextButtonType(buttonType);
        return privacyPolicyFragment;
    }

    public LoginFlowState getLoginFlowState() {
        return this.loginFlowState;
    }

    /* Access modifiers changed, original: protected */
    public View createView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        ViewGroup viewGroup2 = (ViewGroup) layoutInflater.inflate(R.layout.com_accountkit_fragment_confirmation_code_bottom, viewGroup, false);
        if (ViewUtility.isSkin(getUIManager(), Skin.CONTEMPORARY)) {
            View findViewById = viewGroup2.findViewById(R.id.com_accountkit_next_button);
            ((ViewGroup) findViewById.getParent()).removeView(findViewById);
            View findViewById2 = viewGroup2.findViewById(R.id.com_accountkit_space);
            ((ViewGroup) findViewById2.getParent()).removeView(findViewById2);
            viewGroup2.addView(findViewById2);
            viewGroup2.addView(findViewById);
        }
        return viewGroup2;
    }

    /* Access modifiers changed, original: protected */
    public void onViewReadyWithState(View view, Bundle bundle) {
        super.onViewReadyWithState(view, bundle);
        this.nextButtonType = ButtonType.values()[bundle.getInt(NEXT_BUTTON_TYPE)];
        this.loginFlowState = LoginFlowState.values()[bundle.getInt(LOGIN_FLOW_STATE)];
        this.retryButtonVisible = bundle.getBoolean(RETRY_BUTTON_VISIBLE, true);
        this.nextButton = (Button) view.findViewById(R.id.com_accountkit_next_button);
        this.retryButton = (TextView) view.findViewById(R.id.com_accountkit_retry_button);
        if (this.nextButton != null) {
            this.nextButton.setEnabled(this.nextButtonEnabled);
            this.nextButton.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    if (PrivacyPolicyFragment.this.onCompleteListener != null) {
                        PrivacyPolicyFragment.this.onCompleteListener.onNext(view.getContext(), Buttons.ENTER_CONFIRMATION_CODE.name());
                    }
                }
            });
            this.nextButton.setText(this.nextButtonType.getValue());
        }
        if (this.retryButton != null) {
            this.retryButton.setVisibility(this.retryButtonVisible ? 0 : 8);
            this.retryButton.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    Logger.logUIResendInteraction(Buttons.DID_NOT_GET_CODE.name());
                    if (PrivacyPolicyFragment.this.onCompleteListener != null) {
                        PrivacyPolicyFragment.this.onCompleteListener.onRetry(view.getContext());
                    }
                }
            });
            this.retryButton.setTextColor(ViewUtility.getButtonColor(getActivity(), getUIManager()));
        }
        this.termsText = (TextView) view.findViewById(R.id.com_accountkit_confirmation_code_agreement);
        if (this.termsText != null) {
            this.termsText.setMovementMethod(new CustomLinkMovement(new OnURLClickedListener() {
                public void onURLClicked(String str) {
                    Logger.logUIConfirmationCodeInteraction(Buttons.POLICY_LINKS.name(), str);
                }
            }));
        }
        updateTermsText(this.termsText, this.nextButton.getText());
    }

    public void onStart() {
        super.onStart();
        updateTermsText(this.termsText, this.nextButton.getText());
    }

    public void setNextButtonType(ButtonType buttonType) {
        this.nextButtonType = buttonType;
        getViewState().putInt(NEXT_BUTTON_TYPE, this.nextButtonType.ordinal());
        if (this.nextButton != null) {
            this.nextButton.setText(buttonType.getValue());
        }
    }

    public void setNextButtonEnabled(boolean z) {
        this.nextButtonEnabled = z;
        if (this.nextButton != null) {
            this.nextButton.setEnabled(z);
        }
    }

    public void setRetryVisible(boolean z) {
        this.retryButtonVisible = z;
        getViewState().putBoolean(RETRY_BUTTON_VISIBLE, this.retryButtonVisible);
        if (this.retryButton != null) {
            this.retryButton.setVisibility(z ? 0 : 8);
        }
    }

    public void setOnCompleteListener(@Nullable OnCompleteListener onCompleteListener) {
        this.onCompleteListener = onCompleteListener;
    }

    public boolean getRetry() {
        return getViewState().getBoolean("retry", false);
    }

    public void setRetry(boolean z) {
        getViewState().putBoolean("retry", z);
    }

    /* Access modifiers changed, original: protected */
    public void updateTermsText(TextView textView, CharSequence charSequence) {
        if (textView != null && getActivity() != null) {
            PhoneLoginModel currentPhoneNumberLogInModel = AccountKit.getCurrentPhoneNumberLogInModel();
            if (currentPhoneNumberLogInModel == null || Utility.isNullOrEmpty(currentPhoneNumberLogInModel.getPrivacyPolicy())) {
                textView.setText(getConfirmationCodeAgreementText(charSequence));
            } else if (Utility.isNullOrEmpty(currentPhoneNumberLogInModel.getTermsOfService())) {
                textView.setText(Html.fromHtml(getString(R.string.com_accountkit_confirmation_code_agreement_app_privacy_policy, new Object[]{charSequence, TERMS_URL, DATA_URL, COOKIE_URL, currentPhoneNumberLogInModel.getPrivacyPolicy(), AccountKit.getApplicationName()})));
            } else {
                textView.setText(Html.fromHtml(getString(R.string.com_accountkit_confirmation_code_agreement_app_privacy_policy_and_terms, new Object[]{charSequence, TERMS_URL, DATA_URL, COOKIE_URL, currentPhoneNumberLogInModel.getPrivacyPolicy(), currentPhoneNumberLogInModel.getTermsOfService(), AccountKit.getApplicationName()})));
            }
        }
    }

    @SuppressLint({"StringFormatMatches"})
    private Spanned getConfirmationCodeAgreementText(CharSequence charSequence) {
        return Html.fromHtml(getString(R.string.com_accountkit_confirmation_code_agreement, new Object[]{charSequence, TERMS_URL, DATA_URL, COOKIE_URL}));
    }

    /* Access modifiers changed, original: protected */
    public void setLoginFlowState(@NonNull LoginFlowState loginFlowState) {
        this.loginFlowState = loginFlowState;
        getViewState().putInt(LOGIN_FLOW_STATE, loginFlowState.ordinal());
    }
}
