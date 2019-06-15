package com.facebook.accountkit.ui;

final class ActivityErrorHandler {
    private ActivityErrorHandler() {
    }

    static void onErrorRestart(AccountKitActivity accountKitActivity, LoginFlowState loginFlowState) {
        ContentController contentController = accountKitActivity.getContentController();
        if (contentController != null && (contentController instanceof ErrorContentController)) {
            accountKitActivity.onContentControllerDismissed(contentController);
        }
        accountKitActivity.popBackStack(loginFlowState, null);
    }
}
