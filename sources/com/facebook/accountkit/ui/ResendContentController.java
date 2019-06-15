package com.facebook.accountkit.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.content.LocalBroadcastManager;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.TypefaceSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.facebook.accountkit.R;
import com.facebook.accountkit.internal.AccountKitController;
import com.facebook.accountkit.internal.AccountKitController.Logger;
import com.facebook.accountkit.internal.ExperimentationConfiguration;
import com.facebook.accountkit.internal.Feature;
import com.facebook.accountkit.ui.LoginFlowBroadcastReceiver.Event;
import com.facebook.accountkit.ui.StaticContentFragmentFactory.StaticContentFragment;
import com.facebook.accountkit.ui.TitleFragmentFactory.TitleFragment;
import java.util.List;
import java.util.concurrent.TimeUnit;

final class ResendContentController extends ContentControllerBase {
    private static final LoginFlowState LOGIN_FLOW_STATE = LoginFlowState.RESEND;
    private BottomFragment bottomFragment;
    private StaticContentFragment centerFragment;
    private TitleFragment footerFragment;
    private TitleFragment headerFragment;
    private StaticContentFragment textFragment;
    private StaticContentFragment topFragment;

    public static final class BottomFragment extends ContentFragment {
        private static final String FACEBOOK_NOTIFICATION_CHANNEL;
        private static final String RESEND_TIME_KEY;
        private static final String TAG = "ResendContentController$BottomFragment";
        private static final String VOICE_CALLBACK_NOTIFICATION_CHANNEL;
        private CountDownTimer countDownTimer;
        private OnCompleteListener onCompleteListener;
        private String phoneNumber;
        private TextView verifyPhoneNumberView;

        public interface OnCompleteListener {
            void onConfirmationCodeCallback(Context context);

            void onEdit(Context context);

            void onFacebookNotification(Context context);

            void onResend(Context context);
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

        static {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(TAG);
            stringBuilder.append(".FACEBOOK_NOTIFICATION_CHANNEL");
            FACEBOOK_NOTIFICATION_CHANNEL = stringBuilder.toString();
            stringBuilder = new StringBuilder();
            stringBuilder.append(TAG);
            stringBuilder.append(".VOICE_CALLBACK_NOTIFICATION_CHANNEL");
            VOICE_CALLBACK_NOTIFICATION_CHANNEL = stringBuilder.toString();
            stringBuilder = new StringBuilder();
            stringBuilder.append(TAG);
            stringBuilder.append(".RESEND_TIME_KEY");
            RESEND_TIME_KEY = stringBuilder.toString();
        }

        /* Access modifiers changed, original: protected */
        public View createView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
            return layoutInflater.inflate(R.layout.com_accountkit_fragment_resend_bottom, viewGroup, false);
        }

        /* Access modifiers changed, original: 0000 */
        public LoginFlowState getLoginFlowState() {
            return ResendContentController.LOGIN_FLOW_STATE;
        }

        public boolean areFacebookNotificationsEnabled() {
            return getViewState().getBoolean(FACEBOOK_NOTIFICATION_CHANNEL);
        }

        public boolean areVoiceCallbackNotificationsEnabled() {
            return getViewState().getBoolean(VOICE_CALLBACK_NOTIFICATION_CHANNEL);
        }

        public void setNotificationChannels(List<NotificationChannel> list) {
            getViewState().putBoolean(FACEBOOK_NOTIFICATION_CHANNEL, list.contains(NotificationChannel.FACEBOOK));
            getViewState().putBoolean(VOICE_CALLBACK_NOTIFICATION_CHANNEL, list.contains(NotificationChannel.VOICE_CALLBACK));
            updateNotificationViews();
        }

        public long getResendTime() {
            return getViewState().getLong(RESEND_TIME_KEY);
        }

        public void setResendTime(long j) {
            getViewState().putLong(RESEND_TIME_KEY, j);
            updateResendView();
        }

        /* Access modifiers changed, original: protected */
        public void onViewReadyWithState(View view, Bundle bundle) {
            int i;
            super.onViewReadyWithState(view, bundle);
            View findViewById = view.findViewById(R.id.com_accountkit_resend_button);
            this.verifyPhoneNumberView = (TextView) view.findViewById(R.id.com_accountkit_accountkit_verify_number);
            if (findViewById != null) {
                findViewById.setOnClickListener(new OnClickListener() {
                    public void onClick(View view) {
                        Logger.logUIResendInteraction(Buttons.TRY_AGAIN.name());
                        if (BottomFragment.this.onCompleteListener != null) {
                            BottomFragment.this.onCompleteListener.onResend(view.getContext());
                        }
                    }
                });
            }
            Button button = (Button) view.findViewById(R.id.com_accountkit_send_in_fb_button);
            setButtonText(button, R.string.com_accountkit_button_send_code_in_fb, R.string.com_accountkit_button_send_code_in_fb_details);
            button.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    Logger.logUIResendInteraction(Buttons.FB_NOTIFICATION.name());
                    if (BottomFragment.this.onCompleteListener != null) {
                        BottomFragment.this.onCompleteListener.onFacebookNotification(view.getContext());
                    }
                }
            });
            Button button2 = (Button) view.findViewById(R.id.com_accountkit_send_in_phone_call);
            ExperimentationConfiguration experimentationConfiguration = AccountKitController.getExperimentationConfiguration();
            final boolean booleanValue = experimentationConfiguration.getBooleanValue(Feature.CALLBACK_BUTTON_ALTERNATE_TEXT);
            if (experimentationConfiguration.exists() && booleanValue) {
                i = R.string.com_accountkit_button_send_code_in_call_from_facebook_details;
            } else {
                i = R.string.com_accountkit_button_send_code_in_call_details;
            }
            setButtonText(button2, R.string.com_accountkit_button_send_code_in_call, i);
            button2.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    String name;
                    if (booleanValue) {
                        name = Buttons.CONFIRMATION_CODE_CALLBACK.name();
                    } else {
                        name = Buttons.CONFIRMATION_CODE_CALLBACK_ALTERNATE.name();
                    }
                    Logger.logUIResendInteraction(name);
                    if (BottomFragment.this.onCompleteListener != null) {
                        BottomFragment.this.onCompleteListener.onConfirmationCodeCallback(view.getContext());
                    }
                }
            });
            updateViewStates();
        }

        private void setButtonText(Button button, @StringRes int i, @StringRes int i2) {
            SpannableStringBuilder append = new SpannableStringBuilder(getString(i)).append("\n");
            append.setSpan(new TypefaceSpan("sans-serif-medium"), 0, append.length(), 33);
            int length = append.length();
            append.append(getString(i2));
            append.setSpan(new TypefaceSpan("sans-serif-light"), length, append.length(), 33);
            append.setSpan(new ForegroundColorSpan(ViewUtility.getButtonTextColor(button.getContext(), getUIManager())), length, append.length(), 33);
            button.setText(append);
        }

        public void setPhoneNumber(String str) {
            this.phoneNumber = str;
            updatePhoneNumberView();
        }

        public void setOnCompleteListener(@Nullable OnCompleteListener onCompleteListener) {
            this.onCompleteListener = onCompleteListener;
        }

        public void onStart() {
            super.onStart();
            updateViewStates();
        }

        private void updateViewStates() {
            updatePhoneNumberView();
            updateNotificationViews();
            updateResendView();
        }

        private void updatePhoneNumberView() {
            if (isAdded() && this.phoneNumber != null) {
                SpannableString spannableString = new SpannableString(getString(R.string.com_accountkit_code_sent_to, new Object[]{this.phoneNumber}));
                AnonymousClass4 anonymousClass4 = new ClickableSpan() {
                    public void onClick(View view) {
                        Logger.logUIResendInteraction(Buttons.EDIT_NUMBER.name());
                        if (BottomFragment.this.onCompleteListener != null) {
                            BottomFragment.this.onCompleteListener.onEdit(view.getContext());
                        }
                    }

                    public void updateDrawState(TextPaint textPaint) {
                        super.updateDrawState(textPaint);
                        textPaint.setColor(ViewUtility.getButtonColor(BottomFragment.this.getActivity(), BottomFragment.this.getUIManager()));
                        textPaint.setUnderlineText(false);
                    }
                };
                int indexOf = spannableString.toString().indexOf(this.phoneNumber);
                spannableString.setSpan(anonymousClass4, indexOf, this.phoneNumber.length() + indexOf, 33);
                this.verifyPhoneNumberView.setText(spannableString);
                this.verifyPhoneNumberView.setMovementMethod(LinkMovementMethod.getInstance());
            }
        }

        private void updateNotificationViews() {
            View view = getView();
            if (view != null) {
                int i = 8;
                view.findViewById(R.id.com_accountkit_send_in_fb_button).setVisibility(areFacebookNotificationsEnabled() ? 0 : 8);
                view.findViewById(R.id.com_accountkit_send_in_phone_call).setVisibility(areVoiceCallbackNotificationsEnabled() ? 0 : 8);
                view = view.findViewById(R.id.com_accountkit_other_ways_textview);
                if (areFacebookNotificationsEnabled() || areVoiceCallbackNotificationsEnabled()) {
                    i = 0;
                }
                view.setVisibility(i);
            }
        }

        private void updateResendView() {
            View view = getView();
            if (view != null) {
                view = view.findViewById(R.id.com_accountkit_resend_button);
                if (view != null) {
                    final Button button = (Button) view;
                    long resendTime = getResendTime() - System.currentTimeMillis();
                    if (resendTime < 0) {
                        button.setText(R.string.com_accountkit_button_resend_sms);
                        button.setEnabled(true);
                    } else {
                        button.setEnabled(false);
                        this.countDownTimer = new CountDownTimer(resendTime, TimeUnit.SECONDS.toMillis(1)) {
                            public void onTick(long j) {
                                if (BottomFragment.this.isAdded()) {
                                    button.setText(BottomFragment.this.getString(R.string.com_accountkit_button_resend_code_in, new Object[]{Long.valueOf(TimeUnit.MILLISECONDS.toSeconds(j))}));
                                }
                            }

                            public void onFinish() {
                                button.setText(R.string.com_accountkit_button_resend_sms);
                                button.setEnabled(true);
                            }
                        };
                        this.countDownTimer.start();
                    }
                }
            }
        }

        public void onPause() {
            super.onPause();
            if (this.countDownTimer != null) {
                this.countDownTimer.cancel();
            }
        }
    }

    public static final class HeaderFragment extends TitleFragment {
        public static HeaderFragment create(@NonNull UIManager uIManager, int i, @Nullable String... strArr) {
            HeaderFragment headerFragment = new HeaderFragment();
            headerFragment.getViewState().putParcelable(ViewStateFragment.UI_MANAGER_KEY, uIManager);
            headerFragment.setTitleResourceId(i, strArr);
            return headerFragment;
        }

        /* Access modifiers changed, original: protected */
        public void onViewReadyWithState(View view, Bundle bundle) {
            super.onViewReadyWithState(view, bundle);
            this.titleView.setGravity(16);
        }
    }

    @Nullable
    public View getFocusView() {
        return null;
    }

    ResendContentController(AccountKitConfiguration accountKitConfiguration) {
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
            this.bottomFragment.setOnCompleteListener(new OnCompleteListener() {
                public void onEdit(Context context) {
                    LocalBroadcastManager.getInstance(context).sendBroadcast(new Intent(LoginFlowBroadcastReceiver.ACTION_UPDATE).putExtra(LoginFlowBroadcastReceiver.EXTRA_EVENT, Event.PHONE_RESEND));
                }

                public void onResend(Context context) {
                    LocalBroadcastManager.getInstance(context).sendBroadcast(new Intent(LoginFlowBroadcastReceiver.ACTION_UPDATE).putExtra(LoginFlowBroadcastReceiver.EXTRA_EVENT, Event.PHONE_RESEND));
                }

                public void onFacebookNotification(Context context) {
                    LocalBroadcastManager.getInstance(context).sendBroadcast(new Intent(LoginFlowBroadcastReceiver.ACTION_UPDATE).putExtra(LoginFlowBroadcastReceiver.EXTRA_EVENT, Event.PHONE_RESEND_FACEBOOK_NOTIFICATION));
                }

                public void onConfirmationCodeCallback(Context context) {
                    LocalBroadcastManager.getInstance(context).sendBroadcast(new Intent(LoginFlowBroadcastReceiver.ACTION_UPDATE).putExtra(LoginFlowBroadcastReceiver.EXTRA_EVENT, Event.PHONE_RESEND_VOICE_CALL_NOTIFICATION));
                }
            });
        }
    }

    public ContentFragment getCenterFragment() {
        if (this.centerFragment == null) {
            setCenterFragment(StaticContentFragmentFactory.create(this.configuration.getUIManager(), getLoginFlowState()));
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
            setHeaderFragment(HeaderFragment.create(this.configuration.getUIManager(), R.string.com_accountkit_resend_title, new String[0]));
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
        Logger.logUIResend(true);
    }

    public void setPhoneNumber(@Nullable String str) {
        if (this.bottomFragment != null) {
            this.bottomFragment.setPhoneNumber(str);
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void setNotificationChannels(List<NotificationChannel> list) {
        if (this.bottomFragment != null) {
            this.bottomFragment.setNotificationChannels(list);
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void setResendTime(long j) {
        if (this.bottomFragment != null) {
            this.bottomFragment.setResendTime(j);
        }
    }
}
