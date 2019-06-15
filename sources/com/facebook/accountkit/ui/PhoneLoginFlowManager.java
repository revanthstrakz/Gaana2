package com.facebook.accountkit.ui;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import com.facebook.accountkit.PhoneNumber;
import com.facebook.accountkit.internal.AccountKitController;
import com.facebook.accountkit.ui.AccountKitActivity.ResponseType;

class PhoneLoginFlowManager extends LoginFlowManager {
    public static final Creator<PhoneLoginFlowManager> CREATOR = new Creator<PhoneLoginFlowManager>() {
        public PhoneLoginFlowManager createFromParcel(Parcel parcel) {
            return new PhoneLoginFlowManager(parcel);
        }

        public PhoneLoginFlowManager[] newArray(int i) {
            return new PhoneLoginFlowManager[i];
        }
    };
    private PhoneNumber lastUsedPhoneNumber;

    PhoneLoginFlowManager(AccountKitConfiguration accountKitConfiguration) {
        super(LoginType.PHONE);
        this.activityHandler = new ActivityPhoneHandler(accountKitConfiguration);
    }

    private PhoneNumber getLastUsedPhoneNumber() {
        return this.lastUsedPhoneNumber;
    }

    /* Access modifiers changed, original: 0000 */
    public void setLastUsedPhoneNumber(PhoneNumber phoneNumber) {
        this.lastUsedPhoneNumber = phoneNumber;
    }

    public void logInWithPhoneNumber(PhoneNumber phoneNumber, NotificationChannel notificationChannel, ResponseType responseType, @Nullable String str) {
        if (isValid()) {
            setLastUsedPhoneNumber(phoneNumber);
            AccountKitController.logInWithPhoneNumber(phoneNumber, notificationChannel, responseType.getValue(), str);
        }
    }

    public void setConfirmationCode(String str) {
        if (isValid()) {
            AccountKitController.continueLoginWithCode(str);
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void confirmSeamlessLogin() {
        if (isValid()) {
            AccountKitController.continueSeamlessLogin();
        }
    }

    PhoneLoginFlowManager(Parcel parcel) {
        super(parcel);
        this.activityHandler = (ActivityHandler) parcel.readParcelable(ActivityPhoneHandler.class.getClassLoader());
        setLastUsedPhoneNumber((PhoneNumber) parcel.readParcelable(PhoneNumber.class.getClassLoader()));
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeParcelable(this.activityHandler, i);
        parcel.writeParcelable(getLastUsedPhoneNumber(), i);
    }
}
