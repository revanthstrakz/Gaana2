package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;

@Class(creator = "CountrySpecificationCreator")
@Reserved({1})
@Deprecated
public class CountrySpecification extends AbstractSafeParcelable {
    public static final Creator<CountrySpecification> CREATOR = new zzh();
    @Field(id = 2)
    private String zzi;

    public void writeToParcel(Parcel parcel, int i) {
        i = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.zzi, false);
        SafeParcelWriter.finishObjectHeader(parcel, i);
    }

    @Constructor
    public CountrySpecification(@Param(id = 2) String str) {
        this.zzi = str;
    }

    public String getCountryCode() {
        return this.zzi;
    }
}
