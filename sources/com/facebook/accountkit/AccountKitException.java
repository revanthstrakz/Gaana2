package com.facebook.accountkit;

import com.facebook.accountkit.AccountKitError.Type;
import com.facebook.accountkit.internal.InternalAccountKitError;

public class AccountKitException extends RuntimeException {
    private static final long serialVersionUID = 1;
    private final AccountKitError error;

    public AccountKitException(AccountKitError accountKitError) {
        super(accountKitError.getErrorType().getMessage());
        this.error = accountKitError;
    }

    public AccountKitException(Type type, InternalAccountKitError internalAccountKitError) {
        super(type.getMessage());
        this.error = new AccountKitError(type, internalAccountKitError);
    }

    public AccountKitException(Type type, InternalAccountKitError internalAccountKitError, String str) {
        super(String.format(type.getMessage(), new Object[]{str}));
        this.error = new AccountKitError(type, internalAccountKitError);
    }

    public AccountKitException(Type type, InternalAccountKitError internalAccountKitError, Throwable th) {
        super(type.getMessage(), th);
        this.error = new AccountKitError(type, internalAccountKitError);
    }

    public AccountKitException(Type type, Throwable th) {
        super(type.getMessage(), th);
        this.error = new AccountKitError(type);
    }

    public AccountKitError getError() {
        return this.error;
    }

    public String toString() {
        return this.error.toString();
    }
}
