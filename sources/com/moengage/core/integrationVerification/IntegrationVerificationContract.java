package com.moengage.core.integrationVerification;

public class IntegrationVerificationContract {

    interface View {
        void dismissLoadingDialog();

        void messageAndButton(String str, int i);

        void showLoadingDialog(String str);
    }

    interface Presenter {
        void checkAndRestoreState();

        void registerDevice();

        void unregisterDevice();
    }
}
