package com.facebook.accountkit.ui;

import android.os.Handler;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.NonNull;
import com.facebook.accountkit.AccessToken;
import com.facebook.accountkit.AccountKit;
import com.facebook.accountkit.AccountKitException;
import com.facebook.accountkit.EmailLoginModel;
import com.facebook.accountkit.EmailLoginTracker;
import com.facebook.accountkit.LoginResult;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;

final class ActivityEmailHandler extends ActivityHandler {
    public static final Creator<ActivityEmailHandler> CREATOR = new Creator<ActivityEmailHandler>() {
        public ActivityEmailHandler createFromParcel(Parcel parcel) {
            return new ActivityEmailHandler(parcel);
        }

        public ActivityEmailHandler[] newArray(int i) {
            return new ActivityEmailHandler[i];
        }
    };

    public void onAccountVerifiedComplete(AccountKitActivity accountKitActivity) {
    }

    public ActivityEmailHandler(@NonNull AccountKitConfiguration accountKitConfiguration) {
        super(accountKitConfiguration);
    }

    public EmailLoginTracker getLoginTracker(final AccountKitActivity accountKitActivity) {
        if (getEmailTracker() == null) {
            this.tracker = new EmailLoginTracker() {
                /* Access modifiers changed, original: protected */
                public void onStarted(EmailLoginModel emailLoginModel) {
                    if (accountKitActivity.getContentController() instanceof SendingCodeContentController) {
                        accountKitActivity.pushState(LoginFlowState.SENT_CODE, null);
                    }
                }

                /* Access modifiers changed, original: protected */
                public void onSuccess(EmailLoginModel emailLoginModel) {
                    ContentController contentController = accountKitActivity.getContentController();
                    if ((contentController instanceof EmailVerifyContentController) || (contentController instanceof VerifyingCodeContentController)) {
                        accountKitActivity.pushState(LoginFlowState.VERIFIED, null);
                        accountKitActivity.setFinalAuthState(emailLoginModel.getFinalAuthState());
                        accountKitActivity.setAccessToken(emailLoginModel.getAccessToken());
                        accountKitActivity.setAuthorizationCode(emailLoginModel.getCode());
                        accountKitActivity.setLoginResult(LoginResult.SUCCESS);
                        AccessToken accessToken = emailLoginModel.getAccessToken();
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
                public void onCancel(EmailLoginModel emailLoginModel) {
                    accountKitActivity.setNewLoginFlowManagerAndHandler(null);
                }

                private void finishActivity() {
                    accountKitActivity.sendResult();
                }
            };
        }
        return getEmailTracker();
    }

    public void onEmailLoginComplete(AccountKitActivity accountKitActivity, EmailLoginFlowManager emailLoginFlowManager, String str) {
        accountKitActivity.pushState(LoginFlowState.SENDING_CODE, null);
        emailLoginFlowManager.setEmail(str);
        emailLoginFlowManager.logInWithEmail(this.configuration.getResponseType(), this.configuration.getInitialAuthState());
    }

    public void onEmailVerifyRetry(final AccountKitActivity accountKitActivity) {
        AccountKit.cancelLogin();
        accountKitActivity.popBackStack(LoginFlowState.EMAIL_INPUT, new OnPopListener() {
            public void onContentPopped() {
                ActivityEmailHandler.this.emailVerifySetRetry(accountKitActivity);
            }
        });
    }

    private void emailVerifySetRetry(AccountKitActivity accountKitActivity) {
        ContentController contentController = accountKitActivity.getContentController();
        if (contentController instanceof EmailLoginContentController) {
            ((EmailLoginContentController) contentController).setRetry();
        }
    }

    public void onSentCodeComplete(AccountKitActivity accountKitActivity) {
        if (accountKitActivity.getContentController() instanceof SentCodeContentController) {
            accountKitActivity.pushState(LoginFlowState.EMAIL_VERIFY, null);
        }
    }

    protected ActivityEmailHandler(Parcel parcel) {
        super(parcel);
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
    }

    private EmailLoginTracker getEmailTracker() {
        return (EmailLoginTracker) this.tracker;
    }
}
