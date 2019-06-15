package com.facebook.accountkit.internal;

import android.os.Parcel;
import android.support.annotation.Nullable;
import com.facebook.accountkit.AccessToken;
import com.facebook.accountkit.AccountKitError;
import com.facebook.accountkit.AccountKitError.Type;
import com.facebook.accountkit.LoginModel;

abstract class LoginModelImpl implements LoginModel {
    private static final int PARCEL_VERSION = 2;
    private AccessToken accessToken;
    private String code;
    private AccountKitError error;
    private long expiresInSeconds;
    private String finalAuthState;
    private String initialAuthState;
    private String loginModelCode;
    private String responseType;
    private LoginStatus status = LoginStatus.EMPTY;

    LoginModelImpl(String str) {
        this.responseType = str;
    }

    public String getCode() {
        return this.code;
    }

    public AccountKitError getError() {
        return this.error;
    }

    /* Access modifiers changed, original: 0000 */
    public String getLoginRequestCode() {
        return this.loginModelCode;
    }

    /* Access modifiers changed, original: 0000 */
    public void setLoginCode(String str) {
        this.loginModelCode = str;
    }

    /* Access modifiers changed, original: 0000 */
    public long getExpiresInSeconds() {
        return this.expiresInSeconds;
    }

    /* Access modifiers changed, original: 0000 */
    public void setExpiresInSeconds(long j) {
        this.expiresInSeconds = j;
    }

    /* Access modifiers changed, original: 0000 */
    public void setStatus(LoginStatus loginStatus) {
        this.status = loginStatus;
    }

    public LoginStatus getStatus() {
        return this.status;
    }

    /* Access modifiers changed, original: 0000 */
    public void setError(AccountKitError accountKitError) {
        this.error = accountKitError;
    }

    public String getResponseType() {
        return this.responseType;
    }

    /* Access modifiers changed, original: 0000 */
    public void setInitialAuthState(String str) {
        this.initialAuthState = str;
    }

    public String getInitialAuthState() {
        return this.initialAuthState;
    }

    /* Access modifiers changed, original: 0000 */
    public void setFinalAuthState(String str) {
        this.finalAuthState = str;
    }

    public String getFinalAuthState() {
        return this.finalAuthState;
    }

    /* Access modifiers changed, original: 0000 */
    public void setCode(String str) {
        this.code = str;
    }

    /* Access modifiers changed, original: 0000 */
    public void setAccessToken(AccessToken accessToken) {
        this.accessToken = accessToken;
    }

    @Nullable
    public AccessToken getAccessToken() {
        return this.accessToken;
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LoginModelImpl)) {
            return false;
        }
        LoginModelImpl loginModelImpl = (LoginModelImpl) obj;
        if (!(this.expiresInSeconds == loginModelImpl.expiresInSeconds && Utility.areObjectsEqual(this.error, loginModelImpl.error) && Utility.areObjectsEqual(this.loginModelCode, loginModelImpl.loginModelCode) && Utility.areObjectsEqual(this.status, loginModelImpl.status) && Utility.areObjectsEqual(this.responseType, loginModelImpl.responseType) && Utility.areObjectsEqual(this.finalAuthState, loginModelImpl.finalAuthState) && Utility.areObjectsEqual(this.code, loginModelImpl.code))) {
            z = false;
        }
        return z;
    }

    LoginModelImpl(Parcel parcel) {
        if (parcel.readInt() == 2) {
            this.error = (AccountKitError) parcel.readParcelable(AccountKitError.class.getClassLoader());
            this.expiresInSeconds = parcel.readLong();
            this.loginModelCode = parcel.readString();
            this.status = LoginStatus.valueOf(parcel.readString());
            this.responseType = parcel.readString();
            this.finalAuthState = parcel.readString();
            this.code = parcel.readString();
            return;
        }
        this.error = new AccountKitError(Type.LOGIN_INVALIDATED);
        this.status = LoginStatus.ERROR;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(2);
        parcel.writeParcelable(this.error, i);
        parcel.writeLong(this.expiresInSeconds);
        parcel.writeString(this.loginModelCode);
        parcel.writeString(this.status.name());
        parcel.writeString(this.responseType);
        parcel.writeString(this.finalAuthState);
        parcel.writeString(this.code);
    }
}
