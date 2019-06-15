package com.simpl.android.zeroClickSdk;

public interface SimplPaymentDueListener {
    void onError(Throwable th);

    void onSuccess(String str);
}
