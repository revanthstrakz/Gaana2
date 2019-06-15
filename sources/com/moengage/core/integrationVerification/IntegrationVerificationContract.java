package com.moengage.core.integrationVerification;

public class IntegrationVerificationContract {

    interface Presenter {
        void checkAndRestoreState();

        void registerDevice();

        void unregisterDevice();
    }

    interface View {
        void dismissLoadingDialog();

        void messageAndButton(String str, int i);

        void showLoadingDialog(String str);
    }
}
