package com.facebook.accountkit.ui;

import android.app.Activity;

public abstract class ContentControllerBase implements ContentController {
    protected final AccountKitConfiguration configuration;

    public boolean isTransient() {
        return true;
    }

    public abstract void logImpression();

    ContentControllerBase(AccountKitConfiguration accountKitConfiguration) {
        this.configuration = accountKitConfiguration;
    }

    public void onPause(Activity activity) {
        ViewUtility.hideKeyboard(activity);
    }

    public void onResume(Activity activity) {
        logImpression();
    }
}
