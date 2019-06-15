package com.facebook.accountkit.ui;

import android.os.Parcel;
import android.os.Parcelable;
import com.facebook.accountkit.Tracker;

public abstract class ActivityHandler implements Parcelable {
    protected static final long COMPLETION_UI_DURATION_MS = 2000;
    protected final AccountKitConfiguration configuration;
    protected Tracker tracker;

    public int describeContents() {
        return 0;
    }

    public abstract Tracker getLoginTracker(AccountKitActivity accountKitActivity);

    public abstract void onAccountVerifiedComplete(AccountKitActivity accountKitActivity);

    public abstract void onSentCodeComplete(AccountKitActivity accountKitActivity);

    ActivityHandler(AccountKitConfiguration accountKitConfiguration) {
        this.configuration = accountKitConfiguration;
    }

    protected ActivityHandler(Parcel parcel) {
        this.configuration = (AccountKitConfiguration) parcel.readParcelable(AccountKitConfiguration.class.getClassLoader());
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.configuration, i);
    }
}
