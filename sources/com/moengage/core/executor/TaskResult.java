package com.moengage.core.executor;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public final class TaskResult implements Parcelable {
    public static final Creator<TaskResult> CREATOR = new Creator<TaskResult>() {
        public TaskResult createFromParcel(Parcel parcel) {
            return new TaskResult(parcel);
        }

        public TaskResult[] newArray(int i) {
            return new TaskResult[i];
        }
    };
    private boolean mIsSuccess;
    private Object mPayload;

    public int describeContents() {
        return 0;
    }

    public TaskResult(boolean z, Object obj) {
        this.mIsSuccess = z;
        this.mPayload = obj;
    }

    protected TaskResult(Parcel parcel) {
        boolean z = true;
        if (parcel.readInt() != 1) {
            z = false;
        }
        this.mIsSuccess = z;
    }

    public Object getPayload() {
        return this.mPayload;
    }

    public TaskResult setPayload(Object obj) {
        this.mPayload = obj;
        return this;
    }

    public boolean isSuccess() {
        return this.mIsSuccess;
    }

    public void setIsSuccess(boolean z) {
        this.mIsSuccess = z;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mIsSuccess);
        parcel.writeParcelable((Parcelable) this.mPayload, i);
    }
}
