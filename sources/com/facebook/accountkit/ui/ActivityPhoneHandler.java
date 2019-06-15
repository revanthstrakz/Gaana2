package com.facebook.accountkit.ui;

import android.os.Handler;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.NonNull;
import com.facebook.accountkit.AccessToken;
import com.facebook.accountkit.AccountKit;
import com.facebook.accountkit.AccountKitException;
import com.facebook.accountkit.LoginResult;
import com.facebook.accountkit.PhoneLoginModel;
import com.facebook.accountkit.PhoneLoginTracker;
import com.facebook.accountkit.PhoneNumber;
import com.facebook.accountkit.internal.AccountKitController;
import com.facebook.accountkit.internal.Utility;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;

final class ActivityPhoneHandler extends ActivityHandler {
    public static final Creator<ActivityPhoneHandler> CREATOR = new Creator<ActivityPhoneHandler>() {
        public ActivityPhoneHandler createFromParcel(Parcel parcel) {
            return new ActivityPhoneHandler(parcel, null);
        }

        public ActivityPhoneHandler[] newArray(int i) {
            return new ActivityPhoneHandler[i];
        }
    };
    private SmsTracker smsTracker;

    /* synthetic */ ActivityPhoneHandler(Parcel parcel, AnonymousClass1 anonymousClass1) {
        this(parcel);
    }

    ActivityPhoneHandler(@NonNull AccountKitConfiguration accountKitConfiguration) {
        super(accountKitConfiguration);
    }

    public PhoneLoginTracker getLoginTracker(final AccountKitActivity accountKitActivity) {
        if (getPhoneTracker() == null) {
            this.tracker = new PhoneLoginTracker() {
                /* Access modifiers changed, original: protected */
                public void onStarted(PhoneLoginModel phoneLoginModel) {
                    ContentController contentController = accountKitActivity.getContentController();
                    boolean z = contentController instanceof SendingCodeContentController;
                    if (z || (contentController instanceof VerifyingCodeContentController)) {
                        if (phoneLoginModel.getNotificationChannel() == NotificationChannel.SMS) {
                            ActivityPhoneHandler.this.startSmsTrackerIfPossible(accountKitActivity);
                        }
                        if (z) {
                            accountKitActivity.pushState(LoginFlowState.SENT_CODE, null);
                        } else {
                            accountKitActivity.popBackStack(LoginFlowState.CODE_INPUT, new OnPopListener() {
                                public void onContentPopped() {
                                    ContentController contentController = accountKitActivity.getContentController();
                                    if (contentController instanceof ConfirmationCodeContentController) {
                                        ((ConfirmationCodeContentController) contentController).setRetry(true);
                                    }
                                }
                            });
                        }
                    }
                }

                /* Access modifiers changed, original: protected */
                public void onAccountVerified(PhoneLoginModel phoneLoginModel) {
                    if (accountKitActivity.getContentController() instanceof SendingCodeContentController) {
                        accountKitActivity.pushState(LoginFlowState.ACCOUNT_VERIFIED, null);
                    }
                }

                /* Access modifiers changed, original: protected */
                public void onSuccess(PhoneLoginModel phoneLoginModel) {
                    ContentController contentController = accountKitActivity.getContentController();
                    if ((contentController instanceof ConfirmationCodeContentController) || (contentController instanceof VerifyingCodeContentController)) {
                        accountKitActivity.pushState(LoginFlowState.VERIFIED, null);
                        accountKitActivity.setAuthorizationCode(phoneLoginModel.getCode());
                        accountKitActivity.setAccessToken(phoneLoginModel.getAccessToken());
                        accountKitActivity.setLoginResult(LoginResult.SUCCESS);
                        accountKitActivity.setFinalAuthState(phoneLoginModel.getFinalAuthState());
                        AccessToken accessToken = phoneLoginModel.getAccessToken();
                        if (accessToken != null) {
                            accountKitActivity.setTokenRefreshIntervalInSeconds(accessToken.getTokenRefreshIntervalSeconds());
                        }
                        new Handler().postDelayed(new Runnable() {
                            public void run() {
                                AnonymousClass1.this.finishActivity();
                            }
                        }, AdaptiveTrackSelection.DEFAULT_MIN_TIME_BETWEEN_BUFFER_REEVALUTATION_MS);
                    }
                }

                /* Access modifiers changed, original: protected */
                public void onError(AccountKitException accountKitException) {
                    accountKitActivity.pushError(accountKitException.getError());
                }

                /* Access modifiers changed, original: protected */
                public void onCancel(PhoneLoginModel phoneLoginModel) {
                    accountKitActivity.setNewLoginFlowManagerAndHandler(null);
                }

                private void finishActivity() {
                    accountKitActivity.sendResult();
                }
            };
        }
        return getPhoneTracker();
    }

    /* Access modifiers changed, original: 0000 */
    public void onConfirmationCodeComplete(AccountKitActivity accountKitActivity, PhoneLoginFlowManager phoneLoginFlowManager, String str) {
        accountKitActivity.pushState(LoginFlowState.VERIFYING_CODE, null);
        phoneLoginFlowManager.setConfirmationCode(str);
    }

    /* Access modifiers changed, original: 0000 */
    public void onConfirmationCodeRetry(AccountKitActivity accountKitActivity) {
        accountKitActivity.pushState(LoginFlowState.RESEND, getResendOnPushListener());
    }

    private OnPushListener getResendOnPushListener() {
        final PhoneLoginModel currentPhoneNumberLogInModel = AccountKit.getCurrentPhoneNumberLogInModel();
        final String phoneNumber = currentPhoneNumberLogInModel != null ? currentPhoneNumberLogInModel.getPhoneNumber().toString() : null;
        if (phoneNumber == null) {
            return null;
        }
        return new OnPushListener() {
            public void onContentPushed() {
            }

            public void onContentControllerReady(ContentController contentController) {
                if (contentController instanceof ResendContentController) {
                    ResendContentController resendContentController = (ResendContentController) contentController;
                    resendContentController.setPhoneNumber(phoneNumber);
                    resendContentController.setNotificationChannels(ActivityPhoneHandler.this.configuration.getNotificationChannels());
                    resendContentController.setResendTime(currentPhoneNumberLogInModel.getResendTime());
                }
            }
        };
    }

    /* Access modifiers changed, original: 0000 */
    public void onPhoneLoginComplete(AccountKitActivity accountKitActivity, PhoneLoginFlowManager phoneLoginFlowManager, PhoneNumber phoneNumber) {
        accountKitActivity.pushState(LoginFlowState.SENDING_CODE, null);
        phoneLoginFlowManager.logInWithPhoneNumber(phoneNumber, NotificationChannel.SMS, this.configuration.getResponseType(), this.configuration.getInitialAuthState());
    }

    /* Access modifiers changed, original: 0000 */
    public void onResend(final AccountKitActivity accountKitActivity) {
        AccountKit.cancelLogin();
        accountKitActivity.multiPopBackStack(new OnPopListener() {
            public void onContentPopped() {
                if (accountKitActivity.getContentController() instanceof ConfirmationCodeContentController) {
                    accountKitActivity.popBackStack(LoginFlowState.PHONE_NUMBER_INPUT, new OnPopListener() {
                        public void onContentPopped() {
                            ActivityPhoneHandler.this.resendSetRetry(accountKitActivity);
                        }
                    });
                }
            }
        });
    }

    private void resendSetRetry(AccountKitActivity accountKitActivity) {
        ContentController contentController = accountKitActivity.getContentController();
        if (contentController instanceof PhoneLoginContentController) {
            ((PhoneLoginContentController) contentController).setRetry();
            contentController.onResume(accountKitActivity);
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void onResendFacebookNotification(final AccountKitActivity accountKitActivity, final PhoneLoginFlowManager phoneLoginFlowManager) {
        PhoneLoginModel currentPhoneNumberLogInModel = AccountKit.getCurrentPhoneNumberLogInModel();
        if (currentPhoneNumberLogInModel != null) {
            final PhoneNumber phoneNumber = currentPhoneNumberLogInModel.getPhoneNumber();
            accountKitActivity.multiPopBackStack(new OnPopListener() {
                public void onContentPopped() {
                    accountKitActivity.popBackStack(LoginFlowState.SENT_CODE, new OnPopListener() {
                        public void onContentPopped() {
                            accountKitActivity.pushState(LoginFlowState.SENDING_CODE, null);
                            phoneLoginFlowManager.logInWithPhoneNumber(phoneNumber, NotificationChannel.FACEBOOK, ActivityPhoneHandler.this.configuration.getResponseType(), ActivityPhoneHandler.this.configuration.getInitialAuthState());
                        }
                    });
                }
            });
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void onResendVoiceCallNotification(final AccountKitActivity accountKitActivity, final PhoneLoginFlowManager phoneLoginFlowManager) {
        PhoneLoginModel currentPhoneNumberLogInModel = AccountKit.getCurrentPhoneNumberLogInModel();
        if (currentPhoneNumberLogInModel != null) {
            final PhoneNumber phoneNumber = currentPhoneNumberLogInModel.getPhoneNumber();
            accountKitActivity.multiPopBackStack(new OnPopListener() {
                public void onContentPopped() {
                    accountKitActivity.popBackStack(LoginFlowState.SENT_CODE, new OnPopListener() {
                        public void onContentPopped() {
                            accountKitActivity.pushState(LoginFlowState.SENDING_CODE, null);
                            phoneLoginFlowManager.logInWithPhoneNumber(phoneNumber, NotificationChannel.VOICE_CALLBACK, ActivityPhoneHandler.this.configuration.getResponseType(), ActivityPhoneHandler.this.configuration.getInitialAuthState());
                        }
                    });
                }
            });
        }
    }

    /* Access modifiers changed, original: 0000 */
    public OnPushListener getConfirmationCodePushListener(final AccountKitActivity accountKitActivity) {
        return new OnPushListener() {
            public void onContentPushed() {
            }

            public void onContentControllerReady(ContentController contentController) {
                if (contentController instanceof ConfirmationCodeContentController) {
                    PhoneLoginModel currentPhoneNumberLogInModel = AccountKit.getCurrentPhoneNumberLogInModel();
                    if (currentPhoneNumberLogInModel != null) {
                        ConfirmationCodeContentController confirmationCodeContentController = (ConfirmationCodeContentController) contentController;
                        confirmationCodeContentController.setPhoneNumber(currentPhoneNumberLogInModel.getPhoneNumber());
                        confirmationCodeContentController.setNotificationChannel(currentPhoneNumberLogInModel.getNotificationChannel());
                        confirmationCodeContentController.setDetectedConfirmationCode(ActivityPhoneHandler.this.getLoginTracker(accountKitActivity).getCode());
                    }
                }
            }
        };
    }

    public void onSentCodeComplete(AccountKitActivity accountKitActivity) {
        accountKitActivity.pushState(LoginFlowState.CODE_INPUT, null);
    }

    public void onAccountVerifiedComplete(AccountKitActivity accountKitActivity) {
        accountKitActivity.pushState(LoginFlowState.CONFIRM_ACCOUNT_VERIFIED, null);
    }

    /* Access modifiers changed, original: 0000 */
    public void onConfirmSeamlessLogin(AccountKitActivity accountKitActivity, PhoneLoginFlowManager phoneLoginFlowManager) {
        accountKitActivity.pushState(LoginFlowState.CONFIRM_INSTANT_VERIFICATION_LOGIN, null);
        phoneLoginFlowManager.confirmSeamlessLogin();
    }

    /* Access modifiers changed, original: 0000 */
    public SmsTracker getSmsTracker() {
        return this.smsTracker;
    }

    /* Access modifiers changed, original: 0000 */
    public void startSmsTrackerIfPossible(final AccountKitActivity accountKitActivity) {
        try {
            if (this.configuration.isReceiveSMSEnabled() && Utility.hasReceiveSmsPermissions(AccountKitController.getApplicationContext())) {
                if (this.smsTracker == null) {
                    this.smsTracker = new SmsTracker() {
                        /* Access modifiers changed, original: protected */
                        public void confirmationCodeReceived(String str) {
                            ContentController contentController = accountKitActivity.getContentController();
                            if ((contentController instanceof SendingCodeContentController) || (contentController instanceof SentCodeContentController)) {
                                ActivityPhoneHandler.this.getPhoneTracker().setCode(str);
                            } else if (contentController instanceof ConfirmationCodeContentController) {
                                ((ConfirmationCodeContentController) contentController).setDetectedConfirmationCode(str);
                            }
                            ActivityPhoneHandler.this.smsTracker.stopTracking();
                        }
                    };
                }
                this.smsTracker.startTracking();
            }
        } catch (Exception unused) {
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void pauseSmsTracker() {
        if (this.smsTracker != null) {
            this.smsTracker.pauseTracking();
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void stopSmsTracker() {
        if (this.smsTracker != null) {
            this.smsTracker.stopTracking();
        }
    }

    /* Access modifiers changed, original: 0000 */
    public boolean isSmsTracking() {
        return this.smsTracker != null && this.smsTracker.isTracking();
    }

    private ActivityPhoneHandler(Parcel parcel) {
        super(parcel);
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
    }

    private PhoneLoginTracker getPhoneTracker() {
        return (PhoneLoginTracker) this.tracker;
    }
}
