package com.simpl.android.zeroClickSdk.internal;

import android.support.annotation.NonNull;
import com.simpl.android.zeroClickSdk.SimplUser;
import com.simpl.android.zeroClickSdk.SimplUserApprovalRequest;
import com.simpl.approvalsdk.SimplUserApprovalListenerV2;

final class k implements SimplUserApprovalRequest {
    private com.simpl.approvalsdk.SimplUserApprovalRequest a;

    k(SimplUser simplUser, String str) {
        this.a = new com.simpl.approvalsdk.SimplUserApprovalRequest(simplUser, str);
    }

    public final SimplUserApprovalRequest addParam(@NonNull final String str, @NonNull final String str2) {
        c.a(new a<Void>() {
            public final /* synthetic */ Object a() {
                k.this.a.addParam(str, str2);
                return null;
            }
        });
        return this;
    }

    public final void execute(@NonNull final SimplUserApprovalListenerV2 simplUserApprovalListenerV2) {
        c.a(new a<Void>() {
            public final /* synthetic */ Object a() {
                k.this.a.execute(simplUserApprovalListenerV2);
                return null;
            }
        });
    }
}
