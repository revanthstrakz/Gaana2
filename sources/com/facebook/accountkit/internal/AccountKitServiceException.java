package com.facebook.accountkit.internal;

import com.facebook.accountkit.AccountKitError.Type;
import com.facebook.accountkit.AccountKitException;

final class AccountKitServiceException extends AccountKitException {
    private static final long serialVersionUID = 1;
    private final AccountKitRequestError error;

    public AccountKitServiceException(AccountKitRequestError accountKitRequestError, Type type, InternalAccountKitError internalAccountKitError) {
        super(type, internalAccountKitError);
        this.error = accountKitRequestError;
    }

    public AccountKitServiceException(AccountKitRequestError accountKitRequestError, AccountKitException accountKitException) {
        super(accountKitException.getError());
        this.error = accountKitRequestError;
    }

    public final AccountKitRequestError getRequestError() {
        return this.error;
    }

    public final String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{AccountKitServiceException: httpResponseCode: ");
        stringBuilder.append(this.error.getRequestStatusCode());
        stringBuilder.append(", errorCode: ");
        stringBuilder.append(this.error.getErrorCode());
        stringBuilder.append(", errorType: ");
        stringBuilder.append(this.error.getErrorType());
        stringBuilder.append(", message: ");
        stringBuilder.append(this.error.getErrorMessage());
        stringBuilder.append("}");
        return stringBuilder.toString();
    }
}
