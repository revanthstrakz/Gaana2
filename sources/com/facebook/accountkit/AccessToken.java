package com.facebook.accountkit;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.facebook.accountkit.internal.Utility;
import java.util.Date;

public final class AccessToken implements Parcelable {
    public static final Creator<AccessToken> CREATOR = new Creator<AccessToken>() {
        public AccessToken createFromParcel(Parcel parcel) {
            return new AccessToken(parcel, null);
        }

        public AccessToken[] newArray(int i) {
            return new AccessToken[i];
        }
    };
    private static final long DEFAULT_TOKEN_REFRESH_INTERVAL = 604800;
    private static final int PARCEL_VERSION = 2;
    private final String accountId;
    private final String applicationId;
    private final Date lastRefresh;
    private final String token;
    private final long tokenRefreshIntervalInSeconds;

    public int describeContents() {
        return 0;
    }

    /* synthetic */ AccessToken(Parcel parcel, AnonymousClass1 anonymousClass1) {
        this(parcel);
    }

    public AccessToken(@NonNull String str, @NonNull String str2, @NonNull String str3, long j, @Nullable Date date) {
        this.token = str;
        this.accountId = str2;
        this.applicationId = str3;
        this.tokenRefreshIntervalInSeconds = j;
        if (date == null) {
            date = new Date();
        }
        this.lastRefresh = date;
    }

    public String getAccountId() {
        return this.accountId;
    }

    public String getApplicationId() {
        return this.applicationId;
    }

    public Date getLastRefresh() {
        return this.lastRefresh;
    }

    public String getToken() {
        return this.token;
    }

    public long getTokenRefreshIntervalSeconds() {
        return this.tokenRefreshIntervalInSeconds;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{AccessToken token:");
        stringBuilder.append(tokenToString());
        stringBuilder.append(" accountId:");
        stringBuilder.append(this.accountId);
        stringBuilder.append("}");
        return stringBuilder.toString();
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AccessToken)) {
            return false;
        }
        AccessToken accessToken = (AccessToken) obj;
        if (!(this.tokenRefreshIntervalInSeconds == accessToken.tokenRefreshIntervalInSeconds && Utility.areObjectsEqual(this.accountId, accessToken.accountId) && Utility.areObjectsEqual(this.applicationId, accessToken.applicationId) && Utility.areObjectsEqual(this.lastRefresh, accessToken.lastRefresh) && Utility.areObjectsEqual(this.token, accessToken.token))) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        return ((((((((527 + Utility.getHashCode(this.accountId)) * 31) + Utility.getHashCode(this.applicationId)) * 31) + Utility.getHashCode(this.lastRefresh)) * 31) + Utility.getHashCode(this.token)) * 31) + Utility.getHashCode(Long.valueOf(this.tokenRefreshIntervalInSeconds));
    }

    private String tokenToString() {
        if (this.token == null) {
            return "null";
        }
        return AccountKit.getLoggingBehaviors().isEnabled(LoggingBehavior.INCLUDE_ACCESS_TOKENS) ? this.token : "ACCESS_TOKEN_REMOVED";
    }

    private AccessToken(Parcel parcel) {
        int readInt;
        String readString;
        try {
            readInt = parcel.readInt();
        } catch (ClassCastException unused) {
            readInt = 1;
        }
        try {
            readString = parcel.readString();
        } catch (ClassCastException unused2) {
            parcel.readLong();
            readString = parcel.readString();
        }
        this.token = readString;
        this.accountId = parcel.readString();
        this.lastRefresh = new Date(parcel.readLong());
        this.applicationId = parcel.readString();
        if (readInt == 2) {
            this.tokenRefreshIntervalInSeconds = parcel.readLong();
        } else {
            this.tokenRefreshIntervalInSeconds = DEFAULT_TOKEN_REFRESH_INTERVAL;
        }
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(2);
        parcel.writeString(this.token);
        parcel.writeString(this.accountId);
        parcel.writeLong(this.lastRefresh.getTime());
        parcel.writeString(this.applicationId);
        parcel.writeLong(this.tokenRefreshIntervalInSeconds);
    }
}
