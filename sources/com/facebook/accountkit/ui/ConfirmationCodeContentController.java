package com.facebook.accountkit.ui;

import android.content.ClipData.Item;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.text.Editable;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnKeyListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import com.facebook.accountkit.PhoneNumber;
import com.facebook.accountkit.R;
import com.facebook.accountkit.internal.AccountKitController.Logger;
import com.facebook.accountkit.internal.Utility;
import com.facebook.accountkit.ui.LoginFlowBroadcastReceiver.Event;
import com.facebook.accountkit.ui.NotifyingEditText.PasteListener;
import com.facebook.accountkit.ui.SkinManager.Skin;
import com.facebook.accountkit.ui.StaticContentFragmentFactory.StaticContentFragment;

final class ConfirmationCodeContentController extends ContentControllerBase implements ButtonContentController {
    private static final LoginFlowState LOGIN_FLOW_STATE = LoginFlowState.CODE_INPUT;
    private static final String NUMERIC_REGEX = "[0-9]+";
    private PrivacyPolicyFragment bottomFragment;
    private ButtonType buttonType;
    private StaticContentFragment centerFragment;
    com.facebook.accountkit.ui.TitleFragmentFactory.TitleFragment footerFragment;
    TitleFragment headerFragment;
    private OnCompleteListener onCompleteListener;
    private StaticContentFragment textFragment;
    private TopFragment topFragment;

    private class OnCompleteListener implements OnCompleteListener, com.facebook.accountkit.ui.PrivacyPolicyFragment.OnCompleteListener {
        private OnCompleteListener() {
        }

        /* synthetic */ OnCompleteListener(ConfirmationCodeContentController confirmationCodeContentController, AnonymousClass1 anonymousClass1) {
            this();
        }

        public void onNext(Context context, String str) {
            if (ConfirmationCodeContentController.this.topFragment != null && ConfirmationCodeContentController.this.bottomFragment != null) {
                String confirmationCode = ConfirmationCodeContentController.this.topFragment.getConfirmationCode();
                Logger.logUIConfirmationCodeInteraction(str, ConfirmationCodeContentController.this.topFragment.getDetectedConfirmationCode(), confirmationCode);
                LocalBroadcastManager.getInstance(context).sendBroadcast(new Intent(LoginFlowBroadcastReceiver.ACTION_UPDATE).putExtra(LoginFlowBroadcastReceiver.EXTRA_EVENT, Event.PHONE_CONFIRMATION_CODE_COMPLETE).putExtra(LoginFlowBroadcastReceiver.EXTRA_CONFIRMATION_CODE, confirmationCode));
            }
        }

        public void onRetry(Context context) {
            LocalBroadcastManager.getInstance(context).sendBroadcast(new Intent(LoginFlowBroadcastReceiver.ACTION_UPDATE).putExtra(LoginFlowBroadcastReceiver.EXTRA_EVENT, Event.PHONE_CONFIRMATION_CODE_RETRY));
        }

        public void onEdit(Context context) {
            LocalBroadcastManager.getInstance(context).sendBroadcast(new Intent(LoginFlowBroadcastReceiver.ACTION_UPDATE).putExtra(LoginFlowBroadcastReceiver.EXTRA_EVENT, Event.PHONE_RESEND));
        }
    }

    public static final class TitleFragment extends com.facebook.accountkit.ui.TitleFragmentFactory.TitleFragment {
        private NotificationChannel notificationChannel;
        private OnCompleteListener onCompleteListener;
        private PhoneNumber phoneNumber;
        private boolean retry = false;

        public interface OnCompleteListener {
            void onEdit(Context context);

            void onRetry(Context context);
        }

        public static TitleFragment create(@NonNull UIManager uIManager, int i, @Nullable String... strArr) {
            TitleFragment titleFragment = new TitleFragment();
            titleFragment.getViewState().putParcelable(ViewStateFragment.UI_MANAGER_KEY, uIManager);
            titleFragment.setTitleResourceId(i, strArr);
            return titleFragment;
        }

        public View createView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
            return layoutInflater.inflate(R.layout.com_accountkit_fragment_title, viewGroup, false);
        }

        /* Access modifiers changed, original: 0000 */
        public void setPhoneNumber(PhoneNumber phoneNumber) {
            this.phoneNumber = phoneNumber;
            setPhoneNumberView();
        }

        /* Access modifiers changed, original: 0000 */
        public void setNotificationChannel(NotificationChannel notificationChannel) {
            this.notificationChannel = notificationChannel;
        }

        /* Access modifiers changed, original: 0000 */
        public void setRetry(boolean z) {
            this.retry = z;
            setPhoneNumberView();
        }

        /* Access modifiers changed, original: 0000 */
        public void setOnCompleteListener(@Nullable OnCompleteListener onCompleteListener) {
            this.onCompleteListener = onCompleteListener;
        }

        /* Access modifiers changed, original: protected */
        public void onViewReadyWithState(View view, Bundle bundle) {
            super.onViewReadyWithState(view, bundle);
            setPhoneNumberView();
        }

        private void setPhoneNumberView() {
            if (isAdded()) {
                switch (this.notificationChannel) {
                    case FACEBOOK:
                        setTitleResourceId(R.string.com_accountkit_facebook_code_entry_title, new String[0]);
                        break;
                    case VOICE_CALLBACK:
                        setTitleResourceId(R.string.com_accountkit_voice_call_code_entry_title, new String[0]);
                        break;
                    default:
                        if (!this.retry) {
                            if (this.phoneNumber != null) {
                                SpannableString spannableString = new SpannableString(getString(R.string.com_accountkit_enter_code_sent_to, new Object[]{this.phoneNumber.toString()}));
                                AnonymousClass1 anonymousClass1 = new ClickableSpan() {
                                    public void onClick(View view) {
                                        Logger.logUIResendInteraction(Buttons.PHONE_NUMBER.name());
                                        if (TitleFragment.this.onCompleteListener != null) {
                                            TitleFragment.this.onCompleteListener.onEdit(view.getContext());
                                        }
                                    }

                                    public void updateDrawState(TextPaint textPaint) {
                                        super.updateDrawState(textPaint);
                                        textPaint.setColor(ViewUtility.getButtonColor(TitleFragment.this.getActivity(), TitleFragment.this.getUIManager()));
                                        textPaint.setUnderlineText(false);
                                    }
                                };
                                int indexOf = spannableString.toString().indexOf(this.phoneNumber.toString());
                                spannableString.setSpan(anonymousClass1, indexOf, this.phoneNumber.toString().length() + indexOf, 33);
                                this.titleView.setText(spannableString);
                                this.titleView.setMovementMethod(LinkMovementMethod.getInstance());
                                break;
                            }
                        }
                        setTitleResourceId(R.string.com_accountkit_verify_confirmation_code_title, new String[0]);
                        break;
                        break;
                }
            }
        }
    }

    public static final class TopFragment extends ContentFragment {
        private static final String DETECTED_CONFIRMATION_CODE_KEY = "detectedConfirmationCode";
        private static final String TEXT_UPDATED_KEY = "textUpdated";
        private EditText[] confirmationCodeViews;
        private com.facebook.accountkit.ui.PrivacyPolicyFragment.OnCompleteListener onCompleteListener;
        private OnConfirmationCodeChangedListener onConfirmationCodeChangedListener;

        interface OnConfirmationCodeChangedListener {
            void onConfirmationCodeChanged();
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

        /* Access modifiers changed, original: 0000 */
        public LoginFlowState getLoginFlowState() {
            return ConfirmationCodeContentController.LOGIN_FLOW_STATE;
        }

        /* Access modifiers changed, original: protected */
        public View createView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
            return layoutInflater.inflate(R.layout.com_accountkit_fragment_confirmation_code_top, viewGroup, false);
        }

        @Nullable
        public View getFocusView() {
            if (this.confirmationCodeViews == null) {
                return null;
            }
            for (EditText editText : this.confirmationCodeViews) {
                if (editText.getText().length() == 0) {
                    return editText;
                }
            }
            return null;
        }

        public void onResume() {
            super.onResume();
            ViewUtility.showKeyboard(getFocusView());
        }

        /* Access modifiers changed, original: protected */
        public void onViewReadyWithState(View view, Bundle bundle) {
            super.onViewReadyWithState(view, bundle);
            r8 = new EditText[6];
            int i = 0;
            r8[0] = (EditText) view.findViewById(R.id.com_accountkit_confirmation_code_1);
            r8[1] = (EditText) view.findViewById(R.id.com_accountkit_confirmation_code_2);
            r8[2] = (EditText) view.findViewById(R.id.com_accountkit_confirmation_code_3);
            r8[3] = (EditText) view.findViewById(R.id.com_accountkit_confirmation_code_4);
            r8[4] = (EditText) view.findViewById(R.id.com_accountkit_confirmation_code_5);
            r8[5] = (EditText) view.findViewById(R.id.com_accountkit_confirmation_code_6);
            View view2 = null;
            for (View view3 : r8) {
                if (view3.getText().length() != 0) {
                    view3.clearFocus();
                } else if (view2 == null) {
                    view2 = view3;
                }
            }
            ViewUtility.showKeyboard(view2);
            this.confirmationCodeViews = r8;
            AnonymousClass1 anonymousClass1 = new OnEditorActionListener() {
                public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                    if (i == 5 && TopFragment.this.isConfirmationCodeValid() && TopFragment.this.onCompleteListener != null) {
                        TopFragment.this.onCompleteListener.onNext(textView.getContext(), Buttons.ENTER_CONFIRMATION_CODE_KEYBOARD.name());
                    }
                    return true;
                }
            };
            AnonymousClass2 anonymousClass2 = new OnKeyListener() {
                public boolean onKey(View view, int i, KeyEvent keyEvent) {
                    EditText editText = (EditText) view;
                    if (i >= 7 && i <= 16 && keyEvent.getAction() == 0) {
                        editText.setText(Character.toString((char) keyEvent.getUnicodeChar()));
                        return true;
                    } else if (i != 67 || keyEvent.getAction() != 0) {
                        return false;
                    } else {
                        if (editText.getText().length() == 0) {
                            editText = TopFragment.this.focusOnPrevious(editText);
                            if (editText != null) {
                                editText.setText("");
                            }
                        } else {
                            editText.setText("");
                        }
                        return true;
                    }
                }
            };
            int length = r8.length;
            while (i < length) {
                final EditText editText = r8[i];
                editText.setRawInputType(18);
                editText.setOnEditorActionListener(anonymousClass1);
                editText.setOnKeyListener(anonymousClass2);
                if (editText instanceof NotifyingEditText) {
                    NotifyingEditText notifyingEditText = (NotifyingEditText) editText;
                    notifyingEditText.setOnSoftKeyListener(anonymousClass2);
                    notifyingEditText.setPasteListener(new PasteListener() {
                        public void onTextPaste() {
                            char[] access$400 = ConfirmationCodeContentController.getConfirmationCodeToPaste(TopFragment.this.getActivity());
                            if (access$400 != null) {
                                for (int i = 0; i < access$400.length; i++) {
                                    r8[i].setText(String.valueOf(access$400[i]));
                                }
                            }
                        }
                    });
                }
                editText.addTextChangedListener(new TextWatcher() {
                    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                    }

                    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                    }

                    public void afterTextChanged(Editable editable) {
                        if (!TopFragment.this.getTextUpdated()) {
                            TopFragment.this.setTextUpdated(true);
                            Logger.logUIConfirmationCodeInteraction(Buttons.CONFIRMATION_CODE_FIRST_DIGIT.name(), null);
                        }
                        if (editable.length() == 1) {
                            TopFragment.this.focusOnNext(editText);
                        }
                        if (TopFragment.this.onConfirmationCodeChangedListener != null) {
                            TopFragment.this.onConfirmationCodeChangedListener.onConfirmationCodeChanged();
                        }
                    }
                });
                i++;
            }
            updateDetectedConfirmationCode();
        }

        @Nullable
        public String getConfirmationCode() {
            if (this.confirmationCodeViews == null) {
                return null;
            }
            StringBuilder stringBuilder = new StringBuilder();
            for (EditText text : this.confirmationCodeViews) {
                stringBuilder.append(text.getText());
            }
            return stringBuilder.toString();
        }

        @Nullable
        public String getDetectedConfirmationCode() {
            return getViewState().getString(DETECTED_CONFIRMATION_CODE_KEY);
        }

        public void setDetectedConfirmationCode(@Nullable String str) {
            getViewState().putString(DETECTED_CONFIRMATION_CODE_KEY, str);
            updateDetectedConfirmationCode();
        }

        public void setOnCompleteListener(@Nullable com.facebook.accountkit.ui.PrivacyPolicyFragment.OnCompleteListener onCompleteListener) {
            this.onCompleteListener = onCompleteListener;
        }

        public void setOnConfirmationCodeChangedListener(@Nullable OnConfirmationCodeChangedListener onConfirmationCodeChangedListener) {
            this.onConfirmationCodeChangedListener = onConfirmationCodeChangedListener;
        }

        public void onRetry() {
            for (EditText text : this.confirmationCodeViews) {
                text.setText("");
            }
            if (this.confirmationCodeViews.length > 0) {
                this.confirmationCodeViews[0].requestFocus();
            }
        }

        private boolean getTextUpdated() {
            return getViewState().getBoolean(TEXT_UPDATED_KEY, false);
        }

        private void setTextUpdated(boolean z) {
            getViewState().putBoolean(TEXT_UPDATED_KEY, z);
        }

        private int getConfirmationCodeViewIndex(View view) {
            if (view != null) {
                int length = this.confirmationCodeViews.length;
                for (int i = 0; i < length; i++) {
                    if (this.confirmationCodeViews[i] == view) {
                        return i;
                    }
                }
            }
            return -1;
        }

        private EditText focusOnNext(View view) {
            int confirmationCodeViewIndex = getConfirmationCodeViewIndex(view);
            if (confirmationCodeViewIndex < this.confirmationCodeViews.length - 1) {
                EditText editText = this.confirmationCodeViews[confirmationCodeViewIndex + 1];
                editText.requestFocus();
                return editText;
            }
            this.confirmationCodeViews[this.confirmationCodeViews.length - 1].setSelection(1);
            return null;
        }

        private EditText focusOnPrevious(View view) {
            int confirmationCodeViewIndex = getConfirmationCodeViewIndex(view);
            if (confirmationCodeViewIndex <= 0) {
                return null;
            }
            EditText editText = this.confirmationCodeViews[confirmationCodeViewIndex - 1];
            editText.requestFocus();
            return editText;
        }

        public boolean isConfirmationCodeValid() {
            if (this.confirmationCodeViews == null) {
                return false;
            }
            for (EditText text : this.confirmationCodeViews) {
                if (text.getText().length() != 1) {
                    return false;
                }
            }
            return true;
        }

        private void updateDetectedConfirmationCode() {
            if (this.confirmationCodeViews != null) {
                String detectedConfirmationCode = getDetectedConfirmationCode();
                if (!Utility.isNullOrEmpty(detectedConfirmationCode)) {
                    int length = detectedConfirmationCode.length();
                    if (length == this.confirmationCodeViews.length) {
                        EditText[] editTextArr = this.confirmationCodeViews;
                        int i = 0;
                        int length2 = editTextArr.length;
                        int i2 = 0;
                        while (i2 < length2) {
                            if (editTextArr[i2].getText().length() == 0) {
                                i2++;
                            } else {
                                return;
                            }
                        }
                        while (i < length) {
                            this.confirmationCodeViews[i].setText(Character.toString(detectedConfirmationCode.charAt(i)));
                            i++;
                        }
                        this.confirmationCodeViews[this.confirmationCodeViews.length - 1].setSelection(1);
                    }
                }
            }
        }
    }

    public boolean isTransient() {
        return false;
    }

    ConfirmationCodeContentController(AccountKitConfiguration accountKitConfiguration) {
        super(accountKitConfiguration);
        if (ViewUtility.isSkin(accountKitConfiguration.getUIManager(), Skin.CONTEMPORARY)) {
            this.buttonType = ButtonType.NEXT;
        } else {
            this.buttonType = ButtonType.CONTINUE;
        }
    }

    public ContentFragment getBottomFragment() {
        if (this.bottomFragment == null) {
            setBottomFragment(PrivacyPolicyFragment.create(this.configuration.getUIManager(), LOGIN_FLOW_STATE, getButtonType()));
        }
        return this.bottomFragment;
    }

    public void setBottomFragment(@Nullable ContentFragment contentFragment) {
        if (contentFragment instanceof PrivacyPolicyFragment) {
            this.bottomFragment = (PrivacyPolicyFragment) contentFragment;
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
            setCenterFragment(StaticContentFragmentFactory.create(this.configuration.getUIManager(), getLoginFlowState(), R.layout.com_accountkit_fragment_confirmation_code_center));
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
        return this.topFragment.getFocusView();
    }

    public com.facebook.accountkit.ui.TitleFragmentFactory.TitleFragment getFooterFragment() {
        if (this.footerFragment == null) {
            setFooterFragment(TitleFragmentFactory.create(this.configuration.getUIManager()));
        }
        return this.footerFragment;
    }

    public void setFooterFragment(@Nullable com.facebook.accountkit.ui.TitleFragmentFactory.TitleFragment titleFragment) {
        this.footerFragment = titleFragment;
    }

    public com.facebook.accountkit.ui.TitleFragmentFactory.TitleFragment getHeaderFragment() {
        if (this.headerFragment == null) {
            setHeaderFragment(TitleFragment.create(this.configuration.getUIManager(), R.string.com_accountkit_confirmation_code_title, new String[0]));
        }
        return this.headerFragment;
    }

    public void setHeaderFragment(@Nullable com.facebook.accountkit.ui.TitleFragmentFactory.TitleFragment titleFragment) {
        if (titleFragment instanceof TitleFragment) {
            this.headerFragment = (TitleFragment) titleFragment;
            this.headerFragment.setOnCompleteListener(getOnCompleteListener());
        }
    }

    public LoginFlowState getLoginFlowState() {
        return LoginFlowState.CODE_INPUT;
    }

    public ContentFragment getTextFragment() {
        if (this.textFragment == null) {
            setTextFragment(StaticContentFragmentFactory.create(this.configuration.getUIManager(), getLoginFlowState()));
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
            setTopFragment(new TopFragment());
        }
        return this.topFragment;
    }

    public void setTopFragment(@Nullable ContentFragment contentFragment) {
        if (contentFragment instanceof TopFragment) {
            this.topFragment = (TopFragment) contentFragment;
            this.topFragment.getViewState().putParcelable(ViewStateFragment.UI_MANAGER_KEY, this.configuration.getUIManager());
            this.topFragment.setOnConfirmationCodeChangedListener(new OnConfirmationCodeChangedListener() {
                public void onConfirmationCodeChanged() {
                    ConfirmationCodeContentController.this.updateNextButton();
                }
            });
            this.topFragment.setOnCompleteListener(getOnCompleteListener());
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void setPhoneNumber(@Nullable PhoneNumber phoneNumber) {
        if (this.headerFragment != null) {
            this.headerFragment.setPhoneNumber(phoneNumber);
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void setNotificationChannel(NotificationChannel notificationChannel) {
        if (this.headerFragment != null) {
            this.headerFragment.setNotificationChannel(notificationChannel);
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void setDetectedConfirmationCode(@Nullable String str) {
        if (this.topFragment != null) {
            this.topFragment.setDetectedConfirmationCode(str);
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void setRetry(boolean z) {
        if (this.headerFragment != null) {
            this.headerFragment.setRetry(z);
        }
        if (this.bottomFragment != null) {
            this.bottomFragment.setRetry(z);
        }
        if (z && this.topFragment != null) {
            this.topFragment.onRetry();
        }
    }

    private void updateNextButton() {
        if (this.topFragment != null && this.bottomFragment != null) {
            this.bottomFragment.setNextButtonEnabled(this.topFragment.isConfirmationCodeValid());
            this.bottomFragment.setNextButtonType(getButtonType());
        }
    }

    /* Access modifiers changed, original: protected */
    public void logImpression() {
        if (this.topFragment != null && this.bottomFragment != null) {
            Logger.logUIConfirmationCodeShown(this.bottomFragment.getRetry());
        }
    }

    private OnCompleteListener getOnCompleteListener() {
        if (this.onCompleteListener == null) {
            this.onCompleteListener = new OnCompleteListener(this, null);
        }
        return this.onCompleteListener;
    }

    private static char[] getConfirmationCodeToPaste(Context context) {
        String currentPasteText = getCurrentPasteText(context);
        return (currentPasteText != null && currentPasteText.length() == 6 && currentPasteText.matches(NUMERIC_REGEX)) ? currentPasteText.toCharArray() : null;
    }

    private static String getCurrentPasteText(Context context) {
        if (context == null) {
            return null;
        }
        ClipboardManager clipboardManager = (ClipboardManager) context.getSystemService("clipboard");
        if (clipboardManager.hasPrimaryClip()) {
            Item itemAt = clipboardManager.getPrimaryClip().getItemAt(0);
            if (itemAt.getText() != null) {
                return itemAt.getText().toString();
            }
        }
        return null;
    }
}
