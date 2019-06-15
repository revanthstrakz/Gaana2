package com.simpl.android.zeroClickSdk;

import android.support.annotation.NonNull;
import com.simpl.approvalsdk.SimplUserApprovalListenerV2;

public interface SimplUserApprovalRequest {
    SimplUserApprovalRequest addParam(@NonNull String str, @NonNull String str2);

    void execute(@NonNull SimplUserApprovalListenerV2 simplUserApprovalListenerV2);
}
