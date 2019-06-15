package com.paytm.pgsdk;

import android.os.Bundle;

public interface PaytmRefundCallback {
    void onRefundCompleted(Bundle bundle);

    void onRefundFailed(String str);
}
