package com.facebook.accountkit.ui;

import android.os.Parcel;
import android.os.Parcelable;
import com.facebook.accountkit.AccessToken;
import com.facebook.accountkit.AccountKit;

abstract class LoginFlowManager implements Parcelable {
    protected ActivityHandler activityHandler;
    private LoginFlowState flowState;
    private boolean isValid = true;
    private final LoginType loginType;

    public int describeContents() {
        return 0;
    }

    public LoginFlowManager(LoginType loginType) {
        this.loginType = loginType;
        this.flowState = LoginFlowState.NONE;
    }

    public void cancel() {
        this.isValid = false;
        AccountKit.cancelLogin();
    }

    public AccessToken getAccessToken() {
        if (this.isValid) {
            return AccountKit.getCurrentAccessToken();
        }
        return null;
    }

    public boolean isValid() {
        return this.isValid;
    }

    public LoginType getLoginType() {
        return this.loginType;
    }

    public LoginFlowState getFlowState() {
        return this.flowState;
    }

    public final void setFlowState(LoginFlowState loginFlowState) {
        this.flowState = loginFlowState;
    }

    public ActivityHandler getActivityHandler() {
        return this.activityHandler;
    }

    protected LoginFlowManager(Parcel parcel) {
        boolean z = true;
        if (parcel.readByte() != (byte) 1) {
            z = false;
        }
        this.isValid = z;
        this.loginType = LoginType.valueOf(parcel.readString());
        this.flowState = LoginFlowState.values()[parcel.readInt()];
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByte((byte) this.isValid);
        parcel.writeString(this.loginType.name());
        parcel.writeInt(this.flowState.ordinal());
    }
}
