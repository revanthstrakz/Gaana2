package com.simpl.android.zeroClickSdk;

public interface SimplZeroClickTokenListener {
    void onFailure(Throwable th);

    void onSuccess(SimplZeroClickTokenAuthorization simplZeroClickTokenAuthorization);
}
