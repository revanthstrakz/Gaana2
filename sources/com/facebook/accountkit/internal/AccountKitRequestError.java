package com.facebook.accountkit.internal;

import com.facebook.accountkit.AccountKitError.Type;
import com.facebook.accountkit.AccountKitException;

final class AccountKitRequestError {
    public static final int INVALID_ERROR_CODE = -1;
    public static final int INVALID_HTTP_STATUS_CODE = -1;
    private final int errorCode;
    private final String errorMessage;
    private final String errorType;
    private final AccountKitException exception;
    private final int requestStatusCode;
    private final int subErrorCode;
    private final String userErrorMessage;

    public AccountKitRequestError(int i, int i2, int i3, String str, String str2, String str3, AccountKitException accountKitException) {
        this.requestStatusCode = i;
        this.errorCode = i2;
        this.errorType = str;
        this.errorMessage = str2;
        this.subErrorCode = i3;
        this.userErrorMessage = str3;
        if (accountKitException != null) {
            this.exception = new AccountKitServiceException(this, accountKitException);
        } else {
            this.exception = new AccountKitServiceException(this, Type.SERVER_ERROR, new InternalAccountKitError(i2, str2));
        }
    }

    public AccountKitRequestError(AccountKitException accountKitException) {
        this(-1, accountKitException.getError().getDetailErrorCode(), -1, null, null, null, accountKitException);
    }

    public int getErrorCode() {
        return this.errorCode;
    }

    public String getErrorMessage() {
        if (this.errorMessage != null) {
            return this.errorMessage;
        }
        return this.exception.getLocalizedMessage();
    }

    public String getErrorType() {
        return this.errorType;
    }

    public AccountKitException getException() {
        return this.exception;
    }

    public int getRequestStatusCode() {
        return this.requestStatusCode;
    }

    public int getSubErrorCode() {
        return this.subErrorCode;
    }

    public String getUserErrorMessage() {
        return this.userErrorMessage;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{HttpStatus: ");
        stringBuilder.append(this.requestStatusCode);
        stringBuilder.append(", errorCode: ");
        stringBuilder.append(this.errorCode);
        stringBuilder.append(", errorType: ");
        stringBuilder.append(this.errorType);
        stringBuilder.append(", errorMessage: ");
        stringBuilder.append(getErrorMessage());
        stringBuilder.append("}");
        return stringBuilder.toString();
    }
}
