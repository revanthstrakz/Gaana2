package com.facebook.accountkit.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.NonNull;
import com.facebook.accountkit.PhoneLoginModel;
import com.facebook.accountkit.PhoneNumber;
import com.facebook.accountkit.ui.NotificationChannel;
import java.util.HashMap;
import java.util.Map;

final class PhoneLoginModelImpl extends LoginModelImpl implements PhoneLoginModel {
    public static final Creator<PhoneLoginModelImpl> CREATOR = new Creator<PhoneLoginModelImpl>() {
        public PhoneLoginModelImpl createFromParcel(Parcel parcel) {
            return new PhoneLoginModelImpl(parcel, null);
        }

        public PhoneLoginModelImpl[] newArray(int i) {
            return new PhoneLoginModelImpl[i];
        }
    };
    private String confirmationCode;
    private Map<String, String> fields;
    @NonNull
    private final NotificationChannel notificationChannel;
    private PhoneNumber phoneNumber;
    private long resendTime;

    public int describeContents() {
        return 0;
    }

    /* synthetic */ PhoneLoginModelImpl(Parcel parcel, AnonymousClass1 anonymousClass1) {
        this(parcel);
    }

    PhoneLoginModelImpl(PhoneNumber phoneNumber, @NonNull NotificationChannel notificationChannel, String str) {
        super(str);
        this.fields = new HashMap();
        this.notificationChannel = notificationChannel;
        this.phoneNumber = phoneNumber;
    }

    /* Access modifiers changed, original: 0000 */
    public void setConfirmationCode(@NonNull String str) {
        Validate.isEquals(getStatus(), LoginStatus.PENDING, "Phone status");
        Validate.sdkInitialized();
        this.confirmationCode = str;
    }

    public PhoneNumber getPhoneNumber() {
        return this.phoneNumber;
    }

    public String getConfirmationCode() {
        return this.confirmationCode;
    }

    public String getPrivacyPolicy() {
        return (String) this.fields.get(AccountKitGraphConstants.PRIVACY_POLICY);
    }

    public String getTermsOfService() {
        return (String) this.fields.get(AccountKitGraphConstants.TERMS_OF_SERVICE);
    }

    public long getResendTime() {
        return this.resendTime;
    }

    /* Access modifiers changed, original: 0000 */
    public void putField(String str, String str2) {
        this.fields.put(str, str2);
    }

    @NonNull
    public NotificationChannel getNotificationChannel() {
        return this.notificationChannel;
    }

    /* Access modifiers changed, original: 0000 */
    public void setPhoneNumber(PhoneNumber phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /* Access modifiers changed, original: 0000 */
    public void setResendTime(long j) {
        this.resendTime = j;
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PhoneLoginModelImpl)) {
            return false;
        }
        PhoneLoginModelImpl phoneLoginModelImpl = (PhoneLoginModelImpl) obj;
        if (!(super.equals(phoneLoginModelImpl) && Utility.areObjectsEqual(this.confirmationCode, phoneLoginModelImpl.confirmationCode) && Utility.areObjectsEqual(this.phoneNumber, phoneLoginModelImpl.phoneNumber) && this.notificationChannel == phoneLoginModelImpl.notificationChannel && this.resendTime == phoneLoginModelImpl.resendTime)) {
            z = false;
        }
        return z;
    }

    private PhoneLoginModelImpl(Parcel parcel) {
        super(parcel);
        this.fields = new HashMap();
        this.phoneNumber = (PhoneNumber) parcel.readParcelable(PhoneNumber.class.getClassLoader());
        this.confirmationCode = parcel.readString();
        this.notificationChannel = NotificationChannel.values()[parcel.readInt()];
        this.fields = new HashMap();
        int readInt = parcel.readInt();
        for (int i = 0; i < readInt; i++) {
            this.fields.put(parcel.readString(), parcel.readString());
        }
        this.resendTime = parcel.readLong();
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeParcelable(this.phoneNumber, i);
        parcel.writeString(this.confirmationCode);
        parcel.writeInt(this.notificationChannel.ordinal());
        parcel.writeInt(this.fields.size());
        for (String str : this.fields.keySet()) {
            parcel.writeString(str);
            parcel.writeString((String) this.fields.get(str));
        }
        parcel.writeLong(this.resendTime);
    }
}
