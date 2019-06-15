package com.simpl.android.zeroClickSdk.internal;

import android.content.Context;
import android.support.annotation.NonNull;
import com.simpl.android.zeroClickSdk.SimplPaymentDueListener;
import com.simpl.android.zeroClickSdk.SimplPaymentUrlRequest;
import com.simpl.android.zeroClickSdk.SimplUser;
import com.simpl.android.zeroClickSdk.SimplUserApprovalRequest;
import com.simpl.android.zeroClickSdk.SimplZeroClickTokenListener;
import com.simpl.approvalsdk.SimplUserApprovalListenerV2;

public final class b implements SimplPaymentUrlRequest, SimplUserApprovalRequest, g {
    public final void addFlags(String... strArr) {
    }

    public final SimplUserApprovalRequest addParam(@NonNull String str, @NonNull String str2) {
        return this;
    }

    public final void execute(SimplPaymentDueListener simplPaymentDueListener) {
        if (simplPaymentDueListener != null) {
            simplPaymentDueListener.onError(new Throwable("Something went wrong."));
        }
    }

    public final void execute(@NonNull SimplUserApprovalListenerV2 simplUserApprovalListenerV2) {
        if (simplUserApprovalListenerV2 != null) {
            simplUserApprovalListenerV2.onError(new Throwable("Something went wrong."));
        }
    }

    public final void generateZeroClickToken(@NonNull SimplUser simplUser, @NonNull SimplZeroClickTokenListener simplZeroClickTokenListener) {
        if (simplZeroClickTokenListener != null) {
            simplZeroClickTokenListener.onFailure(new Throwable("Something went wrong."));
        }
    }

    public final void generateZeroClickToken(@NonNull SimplZeroClickTokenListener simplZeroClickTokenListener) {
        if (simplZeroClickTokenListener != null) {
            simplZeroClickTokenListener.onFailure(new Throwable("Something went wrong."));
        }
    }

    public final boolean isSimplApproved() {
        return false;
    }

    public final SimplUserApprovalRequest isUserApproved(@NonNull SimplUser simplUser) {
        return this;
    }

    public final SimplPaymentUrlRequest openRedirectionURL(@NonNull Context context, @NonNull String str) {
        return this;
    }

    public final SimplPaymentUrlRequest openRedirectionURL(@NonNull Context context, @NonNull String str, @NonNull SimplUser simplUser) {
        return this;
    }

    public final void runInSandboxMode() {
    }

    public final void runInStagingMode() {
    }

    public final void setMerchantId(String str) {
    }
}
