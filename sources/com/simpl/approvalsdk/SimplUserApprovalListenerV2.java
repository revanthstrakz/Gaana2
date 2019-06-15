package com.simpl.approvalsdk;

public interface SimplUserApprovalListenerV2 {
    void onError(Throwable th);

    void onSuccess(boolean z, String str, boolean z2);
}
