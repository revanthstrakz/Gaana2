package com.facebook.accountkit.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import com.facebook.accountkit.AccessToken;
import com.facebook.accountkit.AccountKitError;
import com.facebook.accountkit.EmailLoginModel;

public final class EmailLoginModelImpl extends LoginModelImpl implements EmailLoginModel {
    public static final Creator<EmailLoginModelImpl> CREATOR = new Creator<EmailLoginModelImpl>() {
        public EmailLoginModelImpl createFromParcel(Parcel parcel) {
            return new EmailLoginModelImpl(parcel, null);
        }

        public EmailLoginModelImpl[] newArray(int i) {
            return new EmailLoginModelImpl[i];
        }
    };
    private String email;
    private int interval;

    public int describeContents() {
        return 0;
    }

    @Nullable
    public /* bridge */ /* synthetic */ AccessToken getAccessToken() {
        return super.getAccessToken();
    }

    public /* bridge */ /* synthetic */ String getCode() {
        return super.getCode();
    }

    public /* bridge */ /* synthetic */ AccountKitError getError() {
        return super.getError();
    }

    public /* bridge */ /* synthetic */ String getFinalAuthState() {
        return super.getFinalAuthState();
    }

    public /* bridge */ /* synthetic */ String getInitialAuthState() {
        return super.getInitialAuthState();
    }

    public /* bridge */ /* synthetic */ String getResponseType() {
        return super.getResponseType();
    }

    public /* bridge */ /* synthetic */ LoginStatus getStatus() {
        return super.getStatus();
    }

    public String getEmail() {
        return this.email;
    }

    public int getInterval() {
        return this.interval;
    }

    EmailLoginModelImpl(String str, String str2) {
        super(str2);
        this.email = str;
    }

    /* Access modifiers changed, original: 0000 */
    public void setEmail(String str) {
        this.email = str;
    }

    /* Access modifiers changed, original: 0000 */
    public void setInterval(int i) {
        this.interval = i;
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof EmailLoginModelImpl)) {
            return false;
        }
        EmailLoginModelImpl emailLoginModelImpl = (EmailLoginModelImpl) obj;
        if (!(super.equals(obj) && this.interval == emailLoginModelImpl.interval && Utility.areObjectsEqual(this.email, emailLoginModelImpl.email))) {
            z = false;
        }
        return z;
    }

    private EmailLoginModelImpl(Parcel parcel) {
        super(parcel);
        this.email = parcel.readString();
        this.interval = parcel.readInt();
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeString(this.email);
        parcel.writeInt(this.interval);
    }
}
