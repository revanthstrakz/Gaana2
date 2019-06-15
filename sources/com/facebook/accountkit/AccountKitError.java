package com.facebook.accountkit;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.facebook.accountkit.internal.InternalAccountKitError;
import com.google.api.client.http.HttpStatusCodes;

public final class AccountKitError implements Parcelable {
    public static final Creator<AccountKitError> CREATOR = new Creator<AccountKitError>() {
        public AccountKitError createFromParcel(Parcel parcel) {
            return new AccountKitError(parcel, null);
        }

        public AccountKitError[] newArray(int i) {
            return new AccountKitError[i];
        }
    };
    private final Type errorType;
    private final InternalAccountKitError internalError;

    public enum Type {
        NETWORK_CONNECTION_ERROR(100, "A request failed due to a network error"),
        SERVER_ERROR(200, "Server generated an error"),
        LOGIN_INVALIDATED(HttpStatusCodes.STATUS_CODE_MULTIPLE_CHOICES, "The request timed out"),
        INTERNAL_ERROR(400, "An internal consistency error has occurred"),
        INITIALIZATION_ERROR(500, "Initialization error"),
        ARGUMENT_ERROR(600, "Invalid argument provided");
        
        private final int code;
        private final String message;

        private Type(int i, String str) {
            this.code = i;
            this.message = str;
        }

        public String getMessage() {
            return this.message;
        }

        public int getCode() {
            return this.code;
        }

        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(this.code);
            stringBuilder.append(": ");
            stringBuilder.append(this.message);
            return stringBuilder.toString();
        }
    }

    public int describeContents() {
        return 0;
    }

    public AccountKitError(Type type) {
        this(type, null);
    }

    public AccountKitError(Type type, InternalAccountKitError internalAccountKitError) {
        this.errorType = type;
        this.internalError = internalAccountKitError;
    }

    public int getDetailErrorCode() {
        if (this.internalError == null) {
            return -1;
        }
        return this.internalError.getCode();
    }

    public Type getErrorType() {
        return this.errorType;
    }

    public String getUserFacingMessage() {
        if (this.internalError == null) {
            return null;
        }
        return this.internalError.getUserFacingMessage();
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.errorType);
        stringBuilder.append(": ");
        stringBuilder.append(this.internalError);
        return stringBuilder.toString();
    }

    private AccountKitError(Parcel parcel) {
        this.errorType = Type.values()[parcel.readInt()];
        this.internalError = (InternalAccountKitError) parcel.readParcelable(InternalAccountKitError.class.getClassLoader());
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.errorType.ordinal());
        parcel.writeParcelable(this.internalError, i);
    }
}
