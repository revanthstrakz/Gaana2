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
import com.facebook.accountkit.EmailLoginModel;
import com.facebook.accountkit.EmailLoginTracker;
import com.facebook.accountkit.Tracker;
import com.facebook.accountkit.internal.AccountKitController;
import com.facebook.accountkit.internal.InternalAccountKitError;
import com.facebook.accountkit.internal.LoginStatus;
import com.facebook.accountkit.ui.AccountKitActivity.ResponseType;
import com.facebook.login.widget.ToolTipPopup;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import java.util.Date;

final class DemoEmailLoginFlowManager extends EmailLoginFlowManager {
    public static final Creator<DemoEmailLoginFlowManager> CREATOR = new Creator<DemoEmailLoginFlowManager>() {
        public DemoEmailLoginFlowManager createFromParcel(Parcel parcel) {
            return new DemoEmailLoginFlowManager(parcel);
        }

        public DemoEmailLoginFlowManager[] newArray(int i) {
            return new DemoEmailLoginFlowManager[i];
        }
    };
    private static final int MOCK_EMAIL_DELAY_MS = 6000;
    private static final int MOCK_NETWORK_DELAY_MS = 2000;
    private boolean isValid = true;
    private DemoEmailLoginModel loginModel;

    private static class DemoEmailLoginModel implements EmailLoginModel {
        public static final Creator<DemoEmailLoginModel> CREATOR = new Creator<DemoEmailLoginModel>() {
            public DemoEmailLoginModel createFromParcel(Parcel parcel) {
                return new DemoEmailLoginModel(parcel);
            }

            public DemoEmailLoginModel[] newArray(int i) {
                return new DemoEmailLoginModel[i];
            }
        };
        private final AccessToken accessToken;
        private final String authState;
        private final String confirmationCode;
        private final String email;

        public int describeContents() {
            return 0;
        }

        public String getCode() {
            return null;
        }

        public DemoEmailLoginModel(String str, String str2, String str3, AccessToken accessToken) {
            this.email = str;
            this.authState = str2;
            this.confirmationCode = str3;
            this.accessToken = accessToken;
        }

        public String getEmail() {
            return this.email;
        }

        public String getFinalAuthState() {
            return this.authState;
        }

        public AccessToken getAccessToken() {
            return this.accessToken;
        }

        DemoEmailLoginModel(Parcel parcel) {
            this.accessToken = (AccessToken) parcel.readParcelable(AccessToken.class.getClassLoader());
            this.authState = parcel.readString();
            this.confirmationCode = parcel.readString();
            this.email = parcel.readString();
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeParcelable(this.accessToken, i);
            parcel.writeString(this.authState);
            parcel.writeString(this.confirmationCode);
            parcel.writeString(this.email);
        }
    }

    public DemoEmailLoginFlowManager(AccountKitConfiguration accountKitConfiguration) {
        super(accountKitConfiguration);
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

    public void logInWithEmail(ResponseType responseType, String str) {
        if (this.isValid) {
            final String email = getEmail();
            AccessToken accessToken = null;
            String str2 = responseType == ResponseType.CODE ? "DEMOCODE" : null;
            if (responseType == ResponseType.TOKEN) {
                accessToken = getAccessToken();
            }
            this.loginModel = new DemoEmailLoginModel(email, str, str2, accessToken);
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    if (email.endsWith("@example.com")) {
                        DemoEmailLoginFlowManager.this.broadcastLoginState(LoginStatus.PENDING, null);
                        new Handler().postDelayed(new Runnable() {
                            public void run() {
                                DemoEmailLoginFlowManager.this.broadcastLoginState(LoginStatus.SUCCESS, null);
                            }
                        }, ToolTipPopup.DEFAULT_POPUP_DISPLAY_TIME);
                        return;
                    }
                    DemoEmailLoginFlowManager.this.broadcastLoginState(LoginStatus.ERROR, new AccountKitError(Type.ARGUMENT_ERROR, new InternalAccountKitError(InternalAccountKitError.INVALID_CONFIRMATION_CODE, null, "[Demo] use *@example.com")));
                }
            }, AdaptiveTrackSelection.DEFAULT_MIN_TIME_BETWEEN_BUFFER_REEVALUTATION_MS);
        }
    }

    private void broadcastLoginState(LoginStatus loginStatus, AccountKitError accountKitError) {
        LocalBroadcastManager.getInstance(AccountKitController.getApplicationContext()).sendBroadcast(new Intent(EmailLoginTracker.ACTION_EMAIL_LOGIN_STATE_CHANGED).putExtra(Tracker.EXTRA_LOGIN_MODEL, this.loginModel).putExtra(Tracker.EXTRA_LOGIN_STATUS, loginStatus).putExtra(Tracker.EXTRA_LOGIN_ERROR, accountKitError));
    }

    protected DemoEmailLoginFlowManager(Parcel parcel) {
        super(parcel);
    }
}
