package com.google.android.gms.search;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;

@Class(creator = "GoogleNowAuthStateCreator")
@Reserved({1000})
public class GoogleNowAuthState extends AbstractSafeParcelable {
    public static final Creator<GoogleNowAuthState> CREATOR = new zza();
    @Field(getter = "getAuthCode", id = 1)
    private String zzbv;
    @Field(getter = "getAccessToken", id = 2)
    private String zzbw;
    @Field(getter = "getNextAllowedTimeMillis", id = 3)
    private long zzbx;

    @Constructor
    GoogleNowAuthState(@Param(id = 1) String str, @Param(id = 2) String str2, @Param(id = 3) long j) {
        this.zzbv = str;
        this.zzbw = str2;
        this.zzbx = j;
    }

    public String getAuthCode() {
        return this.zzbv;
    }

    public String getAccessToken() {
        return this.zzbw;
    }

    public long getNextAllowedTimeMillis() {
        return this.zzbx;
    }

    public String toString() {
        String str = this.zzbv;
        String str2 = this.zzbw;
        long j = this.zzbx;
        StringBuilder stringBuilder = new StringBuilder((74 + String.valueOf(str).length()) + String.valueOf(str2).length());
        stringBuilder.append("mAuthCode = ");
        stringBuilder.append(str);
        stringBuilder.append("\nmAccessToken = ");
        stringBuilder.append(str2);
        stringBuilder.append("\nmNextAllowedTimeMillis = ");
        stringBuilder.append(j);
        return stringBuilder.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        i = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, getAuthCode(), false);
        SafeParcelWriter.writeString(parcel, 2, getAccessToken(), false);
        SafeParcelWriter.writeLong(parcel, 3, getNextAllowedTimeMillis());
        SafeParcelWriter.finishObjectHeader(parcel, i);
    }
}
