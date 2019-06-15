package com.paytm.pgsdk;

public interface PaytmCancelTransaction {
    void onCancellationFailure();

    void onCancellationSuccess();
}
