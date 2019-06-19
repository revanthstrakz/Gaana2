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
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import com.facebook.accountkit.PhoneNumber;
import com.facebook.accountkit.R;
import com.facebook.accountkit.internal.AccountKitController;
import com.facebook.accountkit.internal.AccountKitController.Logger;
import com.facebook.accountkit.internal.Utility;
import com.facebook.accountkit.ui.LoginFlowBroadcastReceiver.Event;
import com.facebook.accountkit.ui.PhoneCountryCodeAdapter.ValueData;
import com.facebook.accountkit.ui.SkinManager.Skin;
import com.facebook.accountkit.ui.StaticContentFragmentFactory.StaticContentFragment;
import com.facebook.accountkit.ui.TextContentFragment.NextButtonTextProvider;
import com.facebook.accountkit.ui.TitleFragmentFactory.TitleFragment;

final class PhoneLoginContentController extends ContentControllerBase implements ButtonContentController {
    private static final ButtonType DEFAULT_BUTTON_TYPE = ButtonType.NEXT;
    private static final LoginFlowState LOGIN_FLOW_STATE = LoginFlowState.PHONE_NUMBER_INPUT;
    private BottomFragment bottomFragment;
    private ButtonType buttonType = DEFAULT_BUTTON_TYPE;
    private StaticContentFragment centerFragment;
    private TitleFragment footerFragment;
    private TitleFragment headerFragment;
    private OnCompleteListener onCompleteListener;
    private TextFragment textFragment;
    private TopFragment topFragment;

    interface OnCompleteListener {
        void onNext(Context context, String str);
    }

    public static final class BottomFragment extends ContentFragment {
        private static final String RETRY_KEY = "retry";
        private Button nextButton;
        private boolean nextButtonEnabled;
        private ButtonType nextButtonType = PhoneLoginContentController.DEFAULT_BUTTON_TYPE;
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
            return PhoneLoginContentController.LOGIN_FLOW_STATE;
        }

        /* Access modifiers changed, original: protected */
        public View createView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
            View inflate = layoutInflater.inflate(R.layout.com_accountkit_fragment_phone_login_bottom, viewGroup, false);
            if (!ViewUtility.isSkin(getUIManager(), Skin.CONTEMPORARY)) {
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
                            BottomFragment.this.onCompleteListener.onNext(view.getContext(), Buttons.PHONE_LOGIN_NEXT.name());
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
                return R.string.com_accountkit_button_resend_sms;
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

    enum PhoneNumberSource {
        UNKNOWN,
        APP_SUPPLIED_PHONE_NUMBER,
        APP_SUPPLIED_AND_DEVICE_PHONE_NUMBER,
        DEVICE_PHONE_NUMBER,
        DEVICE_PHONE_NUMBER_AND_APP_NUMBER_NOT_SUPPLIED,
        DEVICE_PHONE_NUMBER_NOT_SUPPLIED
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
            return PhoneLoginContentController.LOGIN_FLOW_STATE;
        }

        /* Access modifiers changed, original: protected */
        public View createView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
            return layoutInflater.inflate(R.layout.com_accountkit_fragment_phone_login_text, viewGroup, false);
        }

        /* Access modifiers changed, original: protected */
        public Spanned getText(String str) {
            return Html.fromHtml(getString(R.string.com_accountkit_phone_login_text, new Object[]{str, ACCOUNT_KIT_URL}));
        }
    }

    public static final class TopFragment extends ContentFragment {
        private static final String APP_SUPPLIED_PHONE_NUMBER_KEY = "appSuppliedPhoneNumber";
        private static final String DEFAULT_COUNTRY_CODE_NUMBER = "defaultCountryCodeNumber";
        private static final String DEVICE_PHONE_NUMBER_KEY = "devicePhoneNumber";
        private static final String INITIAL_COUNTRY_CODE_VALUE_KEY = "initialCountryCodeValue";
        private static final String LAST_PHONE_NUMBER = "lastPhoneNumber";
        private static final String READ_PHONE_STATE_ENABLED = "readPhoneStateEnabled";
        private static final String SMS_BLACKLIST_KEY = "smsBlacklist";
        private static final String SMS_WHITELIST_KEY = "smsWhitelist";
        private CountryCodeSpinner countryCodeView;
        private boolean isPhoneNumberValid;
        private OnCompleteListener onCompleteListener;
        private OnPhoneNumberChangedListener onPhoneNumberChangedListener;
        private EditText phoneNumberView;

        public interface OnPhoneNumberChangedListener {
            void onPhoneNumberChanged();
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
            return PhoneLoginContentController.LOGIN_FLOW_STATE;
        }

        /* Access modifiers changed, original: protected */
        public View createView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
            return layoutInflater.inflate(R.layout.com_accountkit_fragment_phone_login_top, viewGroup, false);
        }

        /* Access modifiers changed, original: protected */
        public void onViewReadyWithState(View view, Bundle bundle) {
            super.onViewReadyWithState(view, bundle);
            PhoneNumber appSuppliedPhoneNumber = getAppSuppliedPhoneNumber();
            PhoneNumber phoneNumber = (PhoneNumber) getViewState().getParcelable(LAST_PHONE_NUMBER);
            final Activity activity = getActivity();
            if (activity != null && (activity instanceof AccountKitActivity)) {
                this.countryCodeView = (CountryCodeSpinner) view.findViewById(R.id.com_accountkit_country_code);
                this.phoneNumberView = (EditText) view.findViewById(R.id.com_accountkit_phone_number);
                if (this.countryCodeView != null) {
                    PhoneCountryCodeAdapter phoneCountryCodeAdapter = new PhoneCountryCodeAdapter(activity, getUIManager(), getSmsBlacklist(), getSmsWhitelist());
                    this.countryCodeView.setAdapter(phoneCountryCodeAdapter);
                    ValueData initialValue = phoneCountryCodeAdapter.getInitialValue(phoneNumber != null ? phoneNumber : appSuppliedPhoneNumber, getDefaultCountryCodeValue());
                    setInitialCountryCodeValue(initialValue);
                    this.countryCodeView.setSelection(initialValue.position);
                    this.countryCodeView.setOnSpinnerEventsListener(new OnSpinnerEventsListener() {
                        public void onSpinnerOpened() {
                            Logger.logUICountryCode(true, ((ValueData) TopFragment.this.countryCodeView.getSelectedItem()).countryCode);
                            ViewUtility.hideKeyboard(activity);
                        }

                        public void onSpinnerClosed() {
                            Logger.logUICountryCode(false, ((ValueData) TopFragment.this.countryCodeView.getSelectedItem()).countryCode);
                            TopFragment.this.getViewState().putParcelable(TopFragment.LAST_PHONE_NUMBER, TopFragment.this.getPhoneNumber());
                            ViewUtility.showKeyboard(TopFragment.this.phoneNumberView);
                        }
                    });
                    if (isReadPhoneStateEnabled() && appSuppliedPhoneNumber == null) {
                        setDevicePhoneNumber(Utility.readPhoneNumberIfAvailable(getActivity().getApplicationContext(), ((ValueData) this.countryCodeView.getSelectedItem()).countryCode));
                    }
                }
                if (this.phoneNumberView != null) {
                    this.phoneNumberView.addTextChangedListener(new TextWatcher() {
                        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                        }

                        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                        }

                        public void afterTextChanged(Editable editable) {
                            boolean z = TopFragment.this.phoneNumberView.getText().length() != 0;
                            if (z != TopFragment.this.isPhoneNumberValid) {
                                TopFragment.this.isPhoneNumberValid = z;
                            }
                            if (TopFragment.this.onPhoneNumberChangedListener != null) {
                                TopFragment.this.onPhoneNumberChangedListener.onPhoneNumberChanged();
                            }
                            TopFragment.this.getViewState().putParcelable(TopFragment.LAST_PHONE_NUMBER, TopFragment.this.getPhoneNumber());
                        }
                    });
                    this.phoneNumberView.setOnEditorActionListener(new OnEditorActionListener() {
                        public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                            if (i != 5 || !TopFragment.this.isPhoneNumberValid) {
                                return false;
                            }
                            if (TopFragment.this.onCompleteListener != null) {
                                TopFragment.this.onCompleteListener.onNext(textView.getContext(), Buttons.PHONE_LOGIN_NEXT_KEYBOARD.name());
                            }
                            return true;
                        }
                    });
                    this.phoneNumberView.setRawInputType(18);
                    String devicePhoneNumber = getDevicePhoneNumber();
                    if (phoneNumber != null) {
                        this.phoneNumberView.setText(phoneNumber.getPhoneNumber());
                    } else if (appSuppliedPhoneNumber != null) {
                        this.phoneNumberView.setText(appSuppliedPhoneNumber.getPhoneNumber());
                    } else if (!Utility.isNullOrEmpty(devicePhoneNumber)) {
                        this.phoneNumberView.setText(devicePhoneNumber);
                    }
                    this.phoneNumberView.setSelection(this.phoneNumberView.getText().length());
                }
            }
        }

        public void setOnCompleteListener(@Nullable OnCompleteListener onCompleteListener) {
            this.onCompleteListener = onCompleteListener;
        }

        public void setReadPhoneStateEnabled(boolean z) {
            getViewState().putBoolean(READ_PHONE_STATE_ENABLED, z);
        }

        public boolean isReadPhoneStateEnabled() {
            return getViewState().getBoolean(READ_PHONE_STATE_ENABLED);
        }

        @Nullable
        public PhoneNumber getAppSuppliedPhoneNumber() {
            return (PhoneNumber) getViewState().getParcelable(APP_SUPPLIED_PHONE_NUMBER_KEY);
        }

        private void setAppSuppliedPhoneNumber(@Nullable PhoneNumber phoneNumber) {
            getViewState().putParcelable(APP_SUPPLIED_PHONE_NUMBER_KEY, phoneNumber);
        }

        @Nullable
        public String getDefaultCountryCodeValue() {
            return getViewState().getString(DEFAULT_COUNTRY_CODE_NUMBER);
        }

        private void setDefaultCountryCodeValue(@Nullable String str) {
            getViewState().putString(DEFAULT_COUNTRY_CODE_NUMBER, str);
        }

        @Nullable
        public String[] getSmsBlacklist() {
            return getViewState().getStringArray(SMS_BLACKLIST_KEY);
        }

        private void setSmsBlacklist(@Nullable String[] strArr) {
            getViewState().putStringArray(SMS_BLACKLIST_KEY, strArr);
        }

        @Nullable
        public String[] getSmsWhitelist() {
            return getViewState().getStringArray(SMS_WHITELIST_KEY);
        }

        private void setSmsWhitelist(@Nullable String[] strArr) {
            getViewState().putStringArray(SMS_WHITELIST_KEY, strArr);
        }

        @Nullable
        public String getDevicePhoneNumber() {
            return getViewState().getString(DEVICE_PHONE_NUMBER_KEY);
        }

        private void setDevicePhoneNumber(@Nullable String str) {
            getViewState().putString(DEVICE_PHONE_NUMBER_KEY, str);
        }

        @Nullable
        public ValueData getInitialCountryCodeValue() {
            return (ValueData) getViewState().getParcelable(INITIAL_COUNTRY_CODE_VALUE_KEY);
        }

        private void setInitialCountryCodeValue(@Nullable ValueData valueData) {
            getViewState().putParcelable(INITIAL_COUNTRY_CODE_VALUE_KEY, valueData);
        }

        @Nullable
        public PhoneNumber getPhoneNumber() {
            try {
                ValueData valueData = (ValueData) this.countryCodeView.getSelectedItem();
                return new PhoneNumber(valueData.countryCode, this.phoneNumberView.getText().toString(), valueData.countryCodeSource);
            } catch (IllegalArgumentException unused) {
                return null;
            }
        }

        public boolean isPhoneNumberValid() {
            return this.isPhoneNumberValid;
        }

        public void setOnPhoneNumberChangedListener(@Nullable OnPhoneNumberChangedListener onPhoneNumberChangedListener) {
            this.onPhoneNumberChangedListener = onPhoneNumberChangedListener;
        }
    }

    public boolean isTransient() {
        return false;
    }

    PhoneLoginContentController(AccountKitConfiguration accountKitConfiguration) {
        super(accountKitConfiguration);
        AccountKitController.initializeLogin();
    }

    public BottomFragment getBottomFragment() {
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
            setCenterFragment(StaticContentFragmentFactory.create(this.configuration.getUIManager(), getLoginFlowState(), R.layout.com_accountkit_fragment_phone_login_center));
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
        return this.topFragment.phoneNumberView;
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
            setHeaderFragment(TitleFragmentFactory.create(this.configuration.getUIManager(), R.string.com_accountkit_phone_login_title, new String[0]));
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
                    if (PhoneLoginContentController.this.bottomFragment == null) {
                        return null;
                    }
                    return PhoneLoginContentController.this.textFragment.getResources().getText(PhoneLoginContentController.this.bottomFragment.getNextButtonTextId()).toString();
                }
            });
        }
    }

    public TopFragment getTopFragment() {
        if (this.topFragment == null) {
            setTopFragment(new TopFragment());
        }
        return this.topFragment;
    }

    public void setTopFragment(@Nullable ContentFragment contentFragment) {
        if (contentFragment instanceof TopFragment) {
            this.topFragment = (TopFragment) contentFragment;
            this.topFragment.getViewState().putParcelable(ViewStateFragment.UI_MANAGER_KEY, this.configuration.getUIManager());
            this.topFragment.setOnPhoneNumberChangedListener(new OnPhoneNumberChangedListener() {
                public void onPhoneNumberChanged() {
                    PhoneLoginContentController.this.updateNextButton();
                }
            });
            this.topFragment.setOnCompleteListener(getOnCompleteListener());
            if (this.configuration != null) {
                if (this.configuration.getInitialPhoneNumber() != null) {
                    this.topFragment.setAppSuppliedPhoneNumber(this.configuration.getInitialPhoneNumber());
                }
                if (this.configuration.getDefaultCountryCode() != null) {
                    this.topFragment.setDefaultCountryCodeValue(this.configuration.getDefaultCountryCode());
                }
                if (this.configuration.getSmsBlacklist() != null) {
                    this.topFragment.setSmsBlacklist(this.configuration.getSmsBlacklist());
                }
                if (this.configuration.getSmsWhitelist() != null) {
                    this.topFragment.setSmsWhitelist(this.configuration.getSmsWhitelist());
                }
                this.topFragment.setReadPhoneStateEnabled(this.configuration.isReadPhoneStateEnabled());
            }
            updateNextButton();
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void setRetry() {
        if (this.headerFragment != null) {
            this.headerFragment.setTitleResourceId(R.string.com_accountkit_phone_login_retry_title, new String[0]);
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

    /* Access modifiers changed, original: protected */
    public void logImpression() {
        if (this.topFragment != null && this.bottomFragment != null) {
            String str;
            ValueData initialCountryCodeValue = this.topFragment.getInitialCountryCodeValue();
            String str2 = null;
            if (initialCountryCodeValue == null) {
                str = null;
            } else {
                str = initialCountryCodeValue.countryCode;
            }
            if (initialCountryCodeValue != null) {
                str2 = initialCountryCodeValue.countryCodeSource;
            }
            Logger.logUIPhoneLoginShown(str, str2, this.bottomFragment.getRetry());
        }
    }

    private OnCompleteListener getOnCompleteListener() {
        if (this.onCompleteListener == null) {
            this.onCompleteListener = new OnCompleteListener() {
                public void onNext(Context context, String str) {
                    if (PhoneLoginContentController.this.topFragment != null && PhoneLoginContentController.this.bottomFragment != null) {
                        PhoneNumber phoneNumber = PhoneLoginContentController.this.topFragment.getPhoneNumber();
                        if (phoneNumber != null) {
                            Logger.logUIPhoneLoginInteraction(str, PhoneLoginContentController.getPhoneNumberSource(phoneNumber, PhoneLoginContentController.this.topFragment.getAppSuppliedPhoneNumber(), PhoneLoginContentController.this.topFragment.getDevicePhoneNumber()).name(), phoneNumber);
                            LocalBroadcastManager.getInstance(context).sendBroadcast(new Intent(LoginFlowBroadcastReceiver.ACTION_UPDATE).putExtra(LoginFlowBroadcastReceiver.EXTRA_EVENT, Event.PHONE_LOGIN_COMPLETE).putExtra(LoginFlowBroadcastReceiver.EXTRA_PHONE_NUMBER, phoneNumber));
                        }
                    }
                }
            };
        }
        return this.onCompleteListener;
    }

    private void updateNextButton() {
        if (this.topFragment != null && this.bottomFragment != null) {
            this.bottomFragment.setNextButtonEnabled(this.topFragment.isPhoneNumberValid());
            this.bottomFragment.setNextButtonType(getButtonType());
        }
    }

    static PhoneNumberSource getPhoneNumberSource(@Nullable PhoneNumber phoneNumber, @Nullable PhoneNumber phoneNumber2, @Nullable String str) {
        if (phoneNumber == null) {
            return PhoneNumberSource.UNKNOWN;
        }
        if (!Utility.isNullOrEmpty(str)) {
            if (phoneNumber2 != null && str.equals(phoneNumber2.getRawPhoneNumber()) && str.equals(phoneNumber.getRawPhoneNumber())) {
                return PhoneNumberSource.APP_SUPPLIED_AND_DEVICE_PHONE_NUMBER;
            }
            if (str.equals(phoneNumber.getRawPhoneNumber())) {
                return PhoneNumberSource.DEVICE_PHONE_NUMBER;
            }
        }
        if (phoneNumber2 != null && phoneNumber2.equals(phoneNumber)) {
            return PhoneNumberSource.APP_SUPPLIED_PHONE_NUMBER;
        }
        if (str == null && phoneNumber2 == null) {
            return PhoneNumberSource.DEVICE_PHONE_NUMBER_AND_APP_NUMBER_NOT_SUPPLIED;
        }
        return PhoneNumberSource.DEVICE_PHONE_NUMBER_NOT_SUPPLIED;
    }
}
