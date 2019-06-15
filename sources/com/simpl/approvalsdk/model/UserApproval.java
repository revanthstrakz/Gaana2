package com.simpl.approvalsdk.model;

public final class UserApproval {
    private boolean mIsApproved;
    private boolean mIsFirstTransaction;

    public UserApproval(boolean z, boolean z2) {
        this.mIsApproved = z;
        this.mIsFirstTransaction = z2;
    }

    public final boolean isApproved() {
        return this.mIsApproved;
    }

    public final boolean isFirstTransaction() {
        return this.mIsFirstTransaction;
    }

    public final void setIsFirstTransaction(boolean z) {
        this.mIsFirstTransaction = z;
    }
}
