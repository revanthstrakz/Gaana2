package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;

@zzark
@Class(creator = "RtbVersionInfoParcelCreator")
public final class zzans extends AbstractSafeParcelable {
    public static final Creator<zzans> CREATOR = new zzant();
    @Field(id = 1)
    private final int major;
    @Field(id = 2)
    private final int minor;
    @Field(id = 3)
    private final int zzdov;

    public static zzans zza(zzbiw zzbiw) {
        throw new NoSuchMethodError();
    }

    @Constructor
    zzans(@Param(id = 1) int i, @Param(id = 2) int i2, @Param(id = 3) int i3) {
        this.major = i;
        this.minor = i2;
        this.zzdov = i3;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        i = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.major);
        SafeParcelWriter.writeInt(parcel, 2, this.minor);
        SafeParcelWriter.writeInt(parcel, 3, this.zzdov);
        SafeParcelWriter.finishObjectHeader(parcel, i);
    }

    public final String toString() {
        int i = this.major;
        int i2 = this.minor;
        int i3 = this.zzdov;
        StringBuilder stringBuilder = new StringBuilder(35);
        stringBuilder.append(i);
        stringBuilder.append(".");
        stringBuilder.append(i2);
        stringBuilder.append(".");
        stringBuilder.append(i3);
        return stringBuilder.toString();
    }
}
