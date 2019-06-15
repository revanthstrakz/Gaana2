package com.simpl.android.zeroClickSdk.internal;

import android.content.Context;
import android.support.annotation.NonNull;
import com.simpl.android.zeroClickSdk.SimplPaymentUrlRequest;
import com.simpl.android.zeroClickSdk.SimplUser;
import com.simpl.android.zeroClickSdk.SimplUserApprovalRequest;
import com.simpl.android.zeroClickSdk.SimplZeroClickTokenListener;

public interface g {
    void addFlags(String... strArr);

    void generateZeroClickToken(@NonNull SimplUser simplUser, @NonNull SimplZeroClickTokenListener simplZeroClickTokenListener);

    void generateZeroClickToken(@NonNull SimplZeroClickTokenListener simplZeroClickTokenListener);

    boolean isSimplApproved();

    SimplUserApprovalRequest isUserApproved(@NonNull SimplUser simplUser);

    SimplPaymentUrlRequest openRedirectionURL(@NonNull Context context, @NonNull String str);

    SimplPaymentUrlRequest openRedirectionURL(@NonNull Context context, @NonNull String str, @NonNull SimplUser simplUser);

    void runInSandboxMode();

    void runInStagingMode();

    void setMerchantId(String str);
}
