package com.google.android.gms.wallet.wobs;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;

@Class(creator = "UriDataCreator")
@Reserved({1})
public final class UriData extends AbstractSafeParcelable {
    public static final Creator<UriData> CREATOR = new zzl();
    @Field(id = 3)
    private String description;
    @Field(id = 2)
    private String zzhf;

    public final void writeToParcel(Parcel parcel, int i) {
        i = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.zzhf, false);
        SafeParcelWriter.writeString(parcel, 3, this.description, false);
        SafeParcelWriter.finishObjectHeader(parcel, i);
    }

    @Constructor
    public UriData(@Param(id = 2) String str, @Param(id = 3) String str2) {
        this.zzhf = str;
        this.description = str2;
    }

    UriData() {
    }

    public final String getUri() {
        return this.zzhf;
    }

    public final String getDescription() {
        return this.description;
    }
}
