package com.facebook.accountkit.ui;

import android.content.Intent;
import android.os.Handler;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.v4.content.LocalBroadcastManager;
import com.comscore.utils.Constants;
import com.facebook.accountkit.AccessToken;
import com.facebook.accountkit.AccountKit;
import com.facebook.accountkit.AccountKitError;
import com.facebook.accountkit.AccountKitError.Type;
import com.facebook.accountkit.PhoneLoginModel;
import com.facebook.accountkit.PhoneLoginTracker;
import com.facebook.accountkit.PhoneNumber;
import com.facebook.accountkit.Tracker;
import com.facebook.accountkit.internal.AccountKitController;
import com.facebook.accountkit.internal.InternalAccountKitError;
import com.facebook.accountkit.internal.LoginStatus;
import com.facebook.accountkit.ui.AccountKitActivity.ResponseType;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import java.util.Date;

final class DemoPhoneLoginFlowManager extends PhoneLoginFlowManager {
    public static final Creator<DemoPhoneLoginFlowManager> CREATOR = new Creator<DemoPhoneLoginFlowManager>() {
        public DemoPhoneLoginFlowManager createFromParcel(Parcel parcel) {
            return new DemoPhoneLoginFlowManager(parcel, null);
        }

        public DemoPhoneLoginFlowManager[] newArray(int i) {
            return new DemoPhoneLoginFlowManager[i];
        }
    };
    private static final String MOCK_CONFIRMATION_CODE = "123456";
    private static final int MOCK_NETWORK_DELAY_MS = 2000;
    private AccountKitActivity activity;
    private boolean isValid;
    private DemoPhoneLoginModel loginModel;
    private ActivityPhoneHandler phoneListeners;

    private static class DemoPhoneLoginModel implements PhoneLoginModel {
        public static final Creator<DemoPhoneLoginModel> CREATOR = new Creator<DemoPhoneLoginModel>() {
            public DemoPhoneLoginModel createFromParcel(Parcel parcel) {
                return new DemoPhoneLoginModel(parcel);
            }

            public DemoPhoneLoginModel[] newArray(int i) {
                return new DemoPhoneLoginModel[i];
            }
        };
        private final AccessToken accessToken;
        private final String authState;
        private final String confirmationCode;
        private final PhoneNumber phoneNumber;

        public int describeContents() {
            return 0;
        }

        public String getPrivacyPolicy() {
            return null;
        }

        public String getTermsOfService() {
            return null;
        }

        DemoPhoneLoginModel(PhoneNumber phoneNumber, String str, String str2, AccessToken accessToken) {
            this.phoneNumber = phoneNumber;
            this.authState = str;
            this.confirmationCode = str2;
            this.accessToken = accessToken;
        }

        public String getConfirmationCode() {
            return this.confirmationCode;
        }

        public PhoneNumber getPhoneNumber() {
            return this.phoneNumber;
        }

        public String getFinalAuthState() {
            return this.authState;
        }

        public String getCode() {
            return this.confirmationCode;
        }

        public AccessToken getAccessToken() {
            return this.accessToken;
        }

        public NotificationChannel getNotificationChannel() {
            return NotificationChannel.SMS;
        }

        public long getResendTime() {
            return System.currentTimeMillis();
        }

        DemoPhoneLoginModel(Parcel parcel) {
            this.accessToken = (AccessToken) parcel.readParcelable(AccessToken.class.getClassLoader());
            this.authState = parcel.readString();
            this.confirmationCode = parcel.readString();
            this.phoneNumber = (PhoneNumber) parcel.readParcelable(PhoneNumber.class.getClassLoader());
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeParcelable(this.accessToken, i);
            parcel.writeString(this.authState);
            parcel.writeString(this.confirmationCode);
            parcel.writeParcelable(this.phoneNumber, i);
        }
    }

    /* synthetic */ DemoPhoneLoginFlowManager(Parcel parcel, AnonymousClass1 anonymousClass1) {
        this(parcel);
    }

    public DemoPhoneLoginFlowManager(AccountKitConfiguration accountKitConfiguration, AccountKitActivity accountKitActivity, ActivityPhoneHandler activityPhoneHandler) {
        super(accountKitConfiguration);
        this.isValid = true;
        this.activity = accountKitActivity;
        this.phoneListeners = activityPhoneHandler;
    }

    public void cancel() {
        this.isValid = false;
        broadcastLoginState(LoginStatus.CANCELLED, null);
    }

    public AccessToken getAccessToken() {
        if (this.isValid) {
            return new AccessToken("TEST_ACCESS_TOKEN", "TEST_ACCOUNT_ID", AccountKit.getApplicationId(), Constants.USER_SESSION_INACTIVE_PERIOD, new Date());
        }
        return null;
    }

    public boolean isValid() {
        return this.isValid;
    }

    public void logInWithPhoneNumber(final PhoneNumber phoneNumber, NotificationChannel notificationChannel, ResponseType responseType, String str) {
        if (this.isValid) {
            AccessToken accessToken = null;
            String str2 = responseType == ResponseType.CODE ? "DEMOCODE" : null;
            if (responseType == ResponseType.TOKEN) {
                accessToken = getAccessToken();
            }
            this.loginModel = new DemoPhoneLoginModel(phoneNumber, str, str2, accessToken);
            setLastUsedPhoneNumber(phoneNumber);
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    if (phoneNumber.getPhoneNumber().length() == 10) {
                        DemoPhoneLoginFlowManager.this.broadcastLoginState(LoginStatus.PENDING, null);
                        new Handler().postDelayed(new Runnable() {
                            public void run() {
                                DemoPhoneLoginFlowManager.this.phoneListeners.startSmsTrackerIfPossible(DemoPhoneLoginFlowManager.this.activity);
                                DemoPhoneLoginFlowManager.this.phoneListeners.getSmsTracker().confirmationCodeReceived(DemoPhoneLoginFlowManager.MOCK_CONFIRMATION_CODE);
                            }
                        }, AdaptiveTrackSelection.DEFAULT_MIN_TIME_BETWEEN_BUFFER_REEVALUTATION_MS);
                        return;
                    }
                    DemoPhoneLoginFlowManager.this.broadcastLoginState(LoginStatus.ERROR, new AccountKitError(Type.ARGUMENT_ERROR, new InternalAccountKitError(InternalAccountKitError.INVALID_CREDENTIALS_OR_LOGIN_REQUEST, null, "[Demo] use a 10 digit number")));
                }
            }, AdaptiveTrackSelection.DEFAULT_MIN_TIME_BETWEEN_BUFFER_REEVALUTATION_MS);
        }
    }

    public void setConfirmationCode(final String str) {
        if (this.isValid) {
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    if (str.equals(DemoPhoneLoginFlowManager.MOCK_CONFIRMATION_CODE)) {
                        DemoPhoneLoginFlowManager.this.broadcastLoginState(LoginStatus.SUCCESS, null);
                        return;
                    }
                    DemoPhoneLoginFlowManager.this.broadcastLoginState(LoginStatus.ERROR, new AccountKitError(Type.ARGUMENT_ERROR, new InternalAccountKitError(InternalAccountKitError.INVALID_CREDENTIALS_OR_LOGIN_REQUEST, null, "[Demo] use confirmation code 123456")));
                }
            }, AdaptiveTrackSelection.DEFAULT_MIN_TIME_BETWEEN_BUFFER_REEVALUTATION_MS);
        }
    }

    private void broadcastLoginState(LoginStatus loginStatus, AccountKitError accountKitError) {
        LocalBroadcastManager.getInstance(AccountKitController.getApplicationContext()).sendBroadcast(new Intent(PhoneLoginTracker.ACTION_PHONE_LOGIN_STATE_CHANGED).putExtra(Tracker.EXTRA_LOGIN_MODEL, this.loginModel).putExtra(Tracker.EXTRA_LOGIN_STATUS, loginStatus).putExtra(Tracker.EXTRA_LOGIN_ERROR, accountKitError));
    }

    private DemoPhoneLoginFlowManager(Parcel parcel) {
        super(parcel);
        this.isValid = true;
    }
}
