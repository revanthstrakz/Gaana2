package com.facebook.accountkit.ui;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import com.facebook.accountkit.internal.AccountKitController;
import com.facebook.accountkit.ui.AccountKitActivity.ResponseType;

class EmailLoginFlowManager extends LoginFlowManager {
    public static final Creator<EmailLoginFlowManager> CREATOR = new Creator<EmailLoginFlowManager>() {
        public EmailLoginFlowManager createFromParcel(Parcel parcel) {
            return new EmailLoginFlowManager(parcel);
        }

        public EmailLoginFlowManager[] newArray(int i) {
            return new EmailLoginFlowManager[i];
        }
    };
    private String email;

    public EmailLoginFlowManager(AccountKitConfiguration accountKitConfiguration) {
        super(LoginType.EMAIL);
        this.activityHandler = new ActivityEmailHandler(accountKitConfiguration);
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String str) {
        this.email = str;
    }

    public void logInWithEmail(ResponseType responseType, @Nullable String str) {
        if (isValid() && this.email != null) {
            AccountKitController.logInWithEmail(this.email, responseType.getValue(), str);
        }
    }

    protected EmailLoginFlowManager(Parcel parcel) {
        super(parcel);
        this.activityHandler = (ActivityHandler) parcel.readParcelable(ActivityEmailHandler.class.getClassLoader());
        this.email = parcel.readString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeParcelable(this.activityHandler, i);
        parcel.writeString(this.email);
    }
}
