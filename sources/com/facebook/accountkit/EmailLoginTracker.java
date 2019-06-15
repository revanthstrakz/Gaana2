package com.facebook.accountkit;

import android.content.Intent;
import com.facebook.accountkit.internal.LoginStatus;

public abstract class EmailLoginTracker extends Tracker {
    public static final String ACTION_EMAIL_LOGIN_STATE_CHANGED = "com.facebook.accountkit.sdk.ACTION_EMAIL_LOGIN_STATE_CHANGED";

    /* Access modifiers changed, original: protected */
    public String getActionStateChanged() {
        return ACTION_EMAIL_LOGIN_STATE_CHANGED;
    }

    public abstract void onCancel(EmailLoginModel emailLoginModel);

    public abstract void onError(AccountKitException accountKitException);

    public abstract void onStarted(EmailLoginModel emailLoginModel);

    public abstract void onSuccess(EmailLoginModel emailLoginModel);

    /* Access modifiers changed, original: protected */
    public void onReceive(Intent intent) {
        EmailLoginModel emailLoginModel = (EmailLoginModel) intent.getParcelableExtra(Tracker.EXTRA_LOGIN_MODEL);
        LoginStatus loginStatus = (LoginStatus) intent.getSerializableExtra(Tracker.EXTRA_LOGIN_STATUS);
        if (emailLoginModel != null && loginStatus != null) {
            switch (loginStatus) {
                case PENDING:
                    onStarted(emailLoginModel);
                    break;
                case SUCCESS:
                    onSuccess(emailLoginModel);
                    break;
                case CANCELLED:
                    onCancel(emailLoginModel);
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
