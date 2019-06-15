package com.facebook.accountkit.ui;

import com.facebook.accountkit.internal.AccountKitController.Logger;

final class EmailSentCodeContentController extends SentCodeContentController {
    EmailSentCodeContentController(AccountKitConfiguration accountKitConfiguration) {
        super(accountKitConfiguration);
    }

    /* Access modifiers changed, original: protected */
    public void logImpression() {
        Logger.logUISentCode(true, LoginType.EMAIL);
    }
}
