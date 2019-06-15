package com.paytm.pgsdk;

import android.os.Bundle;

public interface PaytmStatusQueryCallback {
    void onStatusQueryCompleted(Bundle bundle);

    void onStatusQueryFailed(String str);
}
