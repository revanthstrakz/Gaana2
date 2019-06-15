package com.facebook.accountkit;

import android.content.Intent;
import com.facebook.accountkit.internal.LoginStatus;

public abstract class PhoneLoginTracker extends Tracker {
    public static final String ACTION_PHONE_LOGIN_STATE_CHANGED = "com.facebook.accountkit.sdk.ACTION_PHONE_LOGIN_STATE_CHANGED";
    private String code;

    /* Access modifiers changed, original: protected */
    public String getActionStateChanged() {
        return ACTION_PHONE_LOGIN_STATE_CHANGED;
    }

    public abstract void onAccountVerified(PhoneLoginModel phoneLoginModel);

    public abstract void onCancel(PhoneLoginModel phoneLoginModel);

    public abstract void onError(AccountKitException accountKitException);

    public abstract void onStarted(PhoneLoginModel phoneLoginModel);

    public abstract void onSuccess(PhoneLoginModel phoneLoginModel);

    public String getCode() {
        return this.code;
    }

    public void setCode(String str) {
        this.code = str;
    }

    /* Access modifiers changed, original: protected */
    public void onReceive(Intent intent) {
        PhoneLoginModel phoneLoginModel = (PhoneLoginModel) intent.getParcelableExtra(Tracker.EXTRA_LOGIN_MODEL);
        LoginStatus loginStatus = (LoginStatus) intent.getSerializableExtra(Tracker.EXTRA_LOGIN_STATUS);
        if (phoneLoginModel != null && loginStatus != null) {
            switch (loginStatus) {
                case PENDING:
                    onStarted(phoneLoginModel);
                    break;
                case ACCOUNT_VERIFIED:
                    onAccountVerified(phoneLoginModel);
                    break;
                case SUCCESS:
                    onSuccess(phoneLoginModel);
                    break;
                case CANCELLED:
                    onCancel(phoneLoginModel);
                    break;
                case ERROR:
                    AccountKitError accountKitError = (AccountKitError) intent.getParcelableExtra(Tracker.EXTRA_LOGIN_ERROR);
                    if (accountKitError != null) {
                        onError(new AccountKitException(accountKitError));
                        break;
                    }
                    break;
            }
        }
    }
}
