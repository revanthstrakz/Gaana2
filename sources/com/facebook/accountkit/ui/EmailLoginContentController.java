package com.facebook.accountkit.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.content.LocalBroadcastManager;
import android.text.Editable;
import android.text.Html;
import android.text.Spanned;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import com.facebook.accountkit.AccountKit;
import com.facebook.accountkit.R;
import com.facebook.accountkit.internal.AccountKitController.Logger;
import com.facebook.accountkit.internal.Utility;
import com.facebook.accountkit.ui.LoginFlowBroadcastReceiver.Event;
import com.facebook.accountkit.ui.SkinManager.Skin;
import com.facebook.accountkit.ui.StaticContentFragmentFactory.StaticContentFragment;
import com.facebook.accountkit.ui.TextContentFragment.NextButtonTextProvider;
import com.facebook.accountkit.ui.TitleFragmentFactory.TitleFragment;
import java.util.List;

final class EmailLoginContentController extends ContentControllerBase implements ButtonContentController {
    private static final ButtonType DEFAULT_BUTTON_TYPE = ButtonType.NEXT;
    private static final LoginFlowState LOGIN_FLOW_STATE = LoginFlowState.EMAIL_INPUT;
    private BottomFragment bottomFragment;
    private ButtonType buttonType = DEFAULT_BUTTON_TYPE;
    private StaticContentFragment centerFragment;
    private TitleFragment footerFragment;
    private TitleFragment headerFragment;
    private OnCompleteListener onCompleteListener;
    private TextFragment textFragment;
    private TopFragment topFragment;

    enum EmailSourceAppSupplied {
        NO_APP_SUPPLIED_EMAIL,
        APP_SUPPLIED_EMAIL_CHANGED,
        APP_SUPPLIED_EMAIL_USED
    }

    enum EmailSourceSelected {
        NO_SELECTABLE_EMAILS,
        SELECTED_CHANGED,
        SELECTED_NOT_USED,
        SELECTED_USED
    }

    public interface OnCompleteListener {
        void onNext(Context context, String str);
    }

    public static final class BottomFragment extends ContentFragment {
        private static final String RETRY_KEY = "retry";
        private Button nextButton;
        private boolean nextButtonEnabled;
        private ButtonType nextButtonType = EmailLoginContentController.DEFAULT_BUTTON_TYPE;
        private OnCompleteListener onCompleteListener;

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

        /* Access modifiers changed, original: 0000 */
        public LoginFlowState getLoginFlowState() {
            return EmailLoginContentController.LOGIN_FLOW_STATE;
        }

        /* Access modifiers changed, original: protected */
        public View createView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
            View inflate = layoutInflater.inflate(R.layout.com_accountkit_fragment_email_login_bottom, viewGroup, false);
            UIManager uIManager = getUIManager();
            if (!(uIManager instanceof SkinManager) || ((SkinManager) uIManager).getSkin() != Skin.CONTEMPORARY) {
                return inflate;
            }
            View findViewById = inflate.findViewById(R.id.com_accountkit_next_button);
            ((ViewGroup) inflate).removeView(findViewById);
            findViewById.setLayoutParams(new LayoutParams(-1, -2));
            return findViewById;
        }

        /* Access modifiers changed, original: protected */
        public void onViewReadyWithState(View view, Bundle bundle) {
            super.onViewReadyWithState(view, bundle);
            this.nextButton = (Button) view.findViewById(R.id.com_accountkit_next_button);
            if (this.nextButton != null) {
                this.nextButton.setEnabled(this.nextButtonEnabled);
                this.nextButton.setOnClickListener(new OnClickListener() {
                    public void onClick(View view) {
                        if (BottomFragment.this.onCompleteListener != null) {
                            BottomFragment.this.onCompleteListener.onNext(view.getContext(), Buttons.EMAIL_LOGIN_NEXT.name());
                        }
                    }
                });
            }
            updateButtonText();
        }

        public void setNextButtonEnabled(boolean z) {
            this.nextButtonEnabled = z;
            if (this.nextButton != null) {
                this.nextButton.setEnabled(z);
            }
        }

        public void setNextButtonType(ButtonType buttonType) {
            this.nextButtonType = buttonType;
            if (this.nextButton != null) {
                this.nextButton.setText(buttonType.getValue());
            }
        }

        @StringRes
        public int getNextButtonTextId() {
            if (getRetry()) {
                return R.string.com_accountkit_resend_email_text;
            }
            return this.nextButtonType.getValue();
        }

        public void setOnCompleteListener(@Nullable OnCompleteListener onCompleteListener) {
            this.onCompleteListener = onCompleteListener;
        }

        public boolean getRetry() {
            return getViewState().getBoolean("retry", false);
        }

        public void setRetry(boolean z) {
            getViewState().putBoolean("retry", z);
            updateButtonText();
        }

        private void updateButtonText() {
            if (this.nextButton != null) {
                this.nextButton.setText(getNextButtonTextId());
            }
        }
    }

    public static final class TopFragment extends ContentFragment {
        private static final String APP_SUPPLIED_EMAIL_KEY = "appSuppliedEmail";
        private static final String SELECTED_EMAIL_KEY = "selectedEmail";
        private AutoCompleteTextView emailView;
        private OnCompleteListener onCompleteListener;
        private OnEmailChangedListener onEmailChangedListener;

        public interface OnEmailChangedListener {
            void onEmailChanged();
        }

        /* Access modifiers changed, original: 0000 */
        public boolean isKeyboardFragment() {
            return false;
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

        /* Access modifiers changed, original: 0000 */
        public LoginFlowState getLoginFlowState() {
            return EmailLoginContentController.LOGIN_FLOW_STATE;
        }

        /* Access modifiers changed, original: protected */
        public View createView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
            return layoutInflater.inflate(R.layout.com_accountkit_fragment_email_login_top, viewGroup, false);
        }

        /* Access modifiers changed, original: protected */
        public void onViewReadyWithState(View view, Bundle bundle) {
            super.onViewReadyWithState(view, bundle);
            this.emailView = (AutoCompleteTextView) view.findViewById(R.id.com_accountkit_email);
            if (this.emailView != null) {
                this.emailView.addTextChangedListener(new TextWatcher() {
                    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                    }

                    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                    }

                    public void afterTextChanged(Editable editable) {
                        if (TopFragment.this.onEmailChangedListener != null) {
                            TopFragment.this.onEmailChangedListener.onEmailChanged();
                        }
                    }
                });
                this.emailView.setOnEditorActionListener(new OnEditorActionListener() {
                    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                        if (i != 5 || Utility.isNullOrEmpty(TopFragment.this.getEmail())) {
                            return false;
                        }
                        if (TopFragment.this.onCompleteListener != null) {
                            TopFragment.this.onCompleteListener.onNext(textView.getContext(), Buttons.EMAIL_LOGIN_NEXT_KEYBOARD.name());
                        }
                        return true;
                    }
                });
                this.emailView.setInputType(33);
                Activity activity = getActivity();
                List deviceEmailsIfAvailable = Utility.getDeviceEmailsIfAvailable(activity.getApplicationContext());
                if (deviceEmailsIfAvailable != null) {
                    this.emailView.setAdapter(new ArrayAdapter(activity, 17367050, deviceEmailsIfAvailable));
                    this.emailView.setOnItemClickListener(new OnItemClickListener() {
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                            TopFragment.this.setSelectedEmail(TopFragment.this.emailView.getText().toString());
                        }
                    });
                }
                String appSuppliedEmail = getAppSuppliedEmail();
                if (!Utility.isNullOrEmpty(appSuppliedEmail)) {
                    this.emailView.setText(appSuppliedEmail);
                    this.emailView.setSelection(appSuppliedEmail.length());
                }
            }
        }

        public String getAppSuppliedEmail() {
            return getViewState().getString(APP_SUPPLIED_EMAIL_KEY);
        }

        public void setAppSuppliedEmail(String str) {
            getViewState().putString(APP_SUPPLIED_EMAIL_KEY, str);
        }

        @Nullable
        public String getEmail() {
            if (this.emailView == null) {
                return null;
            }
            return this.emailView.getText().toString();
        }

        public void setOnCompleteListener(@Nullable OnCompleteListener onCompleteListener) {
            this.onCompleteListener = onCompleteListener;
        }

        public void setOnEmailChangedListener(@Nullable OnEmailChangedListener onEmailChangedListener) {
            this.onEmailChangedListener = onEmailChangedListener;
        }

        public String getSelectedEmail() {
            return getViewState().getString(SELECTED_EMAIL_KEY);
        }

        public void setSelectedEmail(String str) {
            getViewState().putString(SELECTED_EMAIL_KEY, str);
        }

        public void setShowErrorIcon(boolean z) {
            if (this.emailView != null) {
                if (z) {
                    this.emailView.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.com_accountkit_error_exclamation, 0);
                } else {
                    this.emailView.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                }
            }
        }
    }

    public static final class TextFragment extends TextContentFragment {
        private static final String ACCOUNT_KIT_URL = "https://www.accountkit.com/faq";

        /* Access modifiers changed, original: 0000 */
        public boolean isKeyboardFragment() {
            return false;
        }

        public /* bridge */ /* synthetic */ int getContentPaddingBottom() {
            return super.getContentPaddingBottom();
        }

        public /* bridge */ /* synthetic */ int getContentPaddingTop() {
            return super.getContentPaddingTop();
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

        public /* bridge */ /* synthetic */ void onStart() {
            super.onStart();
        }

        public /* bridge */ /* synthetic */ void setContentPaddingBottom(int i) {
            super.setContentPaddingBottom(i);
        }

        public /* bridge */ /* synthetic */ void setContentPaddingTop(int i) {
            super.setContentPaddingTop(i);
        }

        public /* bridge */ /* synthetic */ void setNextButtonTextProvider(NextButtonTextProvider nextButtonTextProvider) {
            super.setNextButtonTextProvider(nextButtonTextProvider);
        }

        /* Access modifiers changed, original: 0000 */
        public LoginFlowState getLoginFlowState() {
            return EmailLoginContentController.LOGIN_FLOW_STATE;
        }

        /* Access modifiers changed, original: protected */
        public View createView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
            return layoutInflater.inflate(R.layout.com_accountkit_fragment_email_login_text, viewGroup, false);
        }

        /* Access modifiers changed, original: protected */
        public Spanned getText(String str) {
            return Html.fromHtml(getString(R.string.com_accountkit_email_login_text, new Object[]{str, AccountKit.getApplicationName(), ACCOUNT_KIT_URL}));
        }
    }

    public boolean isTransient() {
        return false;
    }

    EmailLoginContentController(AccountKitConfiguration accountKitConfiguration) {
        super(accountKitConfiguration);
    }

    public ContentFragment getBottomFragment() {
        if (this.bottomFragment == null) {
            setBottomFragment(new BottomFragment());
        }
        return this.bottomFragment;
    }

    public void setBottomFragment(@Nullable ContentFragment contentFragment) {
        if (contentFragment instanceof BottomFragment) {
            this.bottomFragment = (BottomFragment) contentFragment;
            this.bottomFragment.getViewState().putParcelable(ViewStateFragment.UI_MANAGER_KEY, this.configuration.getUIManager());
            this.bottomFragment.setOnCompleteListener(getOnCompleteListener());
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
            setCenterFragment(StaticContentFragmentFactory.create(this.configuration.getUIManager(), getLoginFlowState(), R.layout.com_accountkit_fragment_email_login_center));
        }
        return this.centerFragment;
    }

    public void setCenterFragment(@Nullable ContentFragment contentFragment) {
        if (contentFragment instanceof StaticContentFragment) {
            this.centerFragment = (StaticContentFragment) contentFragment;
        }
    }

    @Nullable
    public View getFocusView() {
        if (this.topFragment == null) {
            return null;
        }
        return this.topFragment.emailView;
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
            this.headerFragment = TitleFragmentFactory.create(this.configuration.getUIManager(), R.string.com_accountkit_email_login_title, new String[0]);
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
            setTextFragment(new TextFragment());
        }
        return this.textFragment;
    }

    public void setTextFragment(@Nullable ContentFragment contentFragment) {
        if (contentFragment instanceof TextFragment) {
            this.textFragment = (TextFragment) contentFragment;
            this.textFragment.getViewState().putParcelable(ViewStateFragment.UI_MANAGER_KEY, this.configuration.getUIManager());
            this.textFragment.setNextButtonTextProvider(new NextButtonTextProvider() {
                public String getNextButtonText() {
                    if (EmailLoginContentController.this.bottomFragment == null) {
                        return null;
                    }
                    return EmailLoginContentController.this.textFragment.getResources().getText(EmailLoginContentController.this.bottomFragment.getNextButtonTextId()).toString();
                }
            });
        }
    }

    public ContentFragment getTopFragment() {
        if (this.topFragment == null) {
            setTopFragment(new TopFragment());
        }
        return this.topFragment;
    }

    public void setTopFragment(@Nullable ContentFragment contentFragment) {
        if (contentFragment instanceof TopFragment) {
            this.topFragment = (TopFragment) contentFragment;
            this.topFragment.getViewState().putParcelable(ViewStateFragment.UI_MANAGER_KEY, this.configuration.getUIManager());
            this.topFragment.setOnEmailChangedListener(new OnEmailChangedListener() {
                public void onEmailChanged() {
                    EmailLoginContentController.this.updateNextButton();
                }
            });
            this.topFragment.setOnCompleteListener(getOnCompleteListener());
            if (!(this.configuration == null || this.configuration.getInitialEmail() == null)) {
                this.topFragment.setAppSuppliedEmail(this.configuration.getInitialEmail());
            }
            updateNextButton();
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void setRetry() {
        if (this.headerFragment != null) {
            this.headerFragment.setTitleResourceId(R.string.com_accountkit_email_login_retry_title, new String[0]);
        }
        if (this.bottomFragment != null) {
            this.bottomFragment.setRetry(true);
        }
        if (this.textFragment != null) {
            this.textFragment.updateText();
        }
    }

    public void onResume(Activity activity) {
        super.onResume(activity);
        ViewUtility.showKeyboard(getFocusView());
    }

    private void updateNextButton() {
        if (this.topFragment != null && this.bottomFragment != null) {
            this.bottomFragment.setNextButtonEnabled(Utility.isNullOrEmpty(this.topFragment.getEmail()) ^ 1);
            this.bottomFragment.setNextButtonType(getButtonType());
        }
    }

    /* Access modifiers changed, original: protected */
    public void logImpression() {
        if (this.bottomFragment != null) {
            Logger.logUIEmailLoginShown(this.bottomFragment.getRetry());
        }
    }

    private OnCompleteListener getOnCompleteListener() {
        if (this.onCompleteListener == null) {
            this.onCompleteListener = new OnCompleteListener() {
                public void onNext(Context context, String str) {
                    if (EmailLoginContentController.this.topFragment != null) {
                        String email = EmailLoginContentController.this.topFragment.getEmail();
                        if (email != null) {
                            email = email.trim();
                            if (Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                                EmailLoginContentController.this.topFragment.setShowErrorIcon(false);
                                Logger.logUIEmailLoginInteraction(str, EmailLoginContentController.getEmailAppSuppliedSource(EmailLoginContentController.this.topFragment.getAppSuppliedEmail(), email).name(), EmailLoginContentController.getEmailSourceSelected(EmailLoginContentController.this.topFragment.getSelectedEmail(), email, Utility.getDeviceEmailsIfAvailable(EmailLoginContentController.this.topFragment.getActivity().getApplicationContext())).name(), email);
                                LocalBroadcastManager.getInstance(context).sendBroadcast(new Intent(LoginFlowBroadcastReceiver.ACTION_UPDATE).putExtra(LoginFlowBroadcastReceiver.EXTRA_EVENT, Event.EMAIL_LOGIN_COMPLETE).putExtra(LoginFlowBroadcastReceiver.EXTRA_EMAIL, email));
                                return;
                            }
                            EmailLoginContentController.this.topFragment.setShowErrorIcon(true);
                            if (EmailLoginContentController.this.headerFragment != null) {
                                EmailLoginContentController.this.headerFragment.setTitleResourceId(R.string.com_accountkit_email_invalid, new String[0]);
                            }
                        }
                    }
                }
            };
        }
        return this.onCompleteListener;
    }

    static EmailSourceAppSupplied getEmailAppSuppliedSource(String str, String str2) {
        if (Utility.isNullOrEmpty(str)) {
            return EmailSourceAppSupplied.NO_APP_SUPPLIED_EMAIL;
        }
        if (str.equals(str2)) {
            return EmailSourceAppSupplied.APP_SUPPLIED_EMAIL_USED;
        }
        return EmailSourceAppSupplied.APP_SUPPLIED_EMAIL_CHANGED;
    }

    static EmailSourceSelected getEmailSourceSelected(String str, String str2, List<String> list) {
        if (Utility.isNullOrEmpty(str)) {
            if (list == null || list.isEmpty()) {
                return EmailSourceSelected.NO_SELECTABLE_EMAILS;
            }
            return EmailSourceSelected.SELECTED_NOT_USED;
        } else if (str.equals(str2)) {
            return EmailSourceSelected.SELECTED_USED;
        } else {
            return EmailSourceSelected.SELECTED_CHANGED;
        }
    }
}
