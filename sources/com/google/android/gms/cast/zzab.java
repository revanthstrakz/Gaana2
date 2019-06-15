package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;

@ShowFirstParty
@Class(creator = "EqualizerBandSettingsCreator")
@Reserved({1})
public final class zzab extends AbstractSafeParcelable {
    public static final Creator<zzab> CREATOR = new zzac();
    @Field(getter = "getFrequency", id = 2)
    private final float zzct;
    @Field(getter = "getQFactor", id = 3)
    private final float zzcu;
    @Field(getter = "getGainDb", id = 4)
    private final float zzcv;

    @Constructor
    public zzab(@Param(id = 2) float f, @Param(id = 3) float f2, @Param(id = 4) float f3) {
        this.zzct = f;
        this.zzcu = f2;
        this.zzcv = f3;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        i = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeFloat(parcel, 2, this.zzct);
        SafeParcelWriter.writeFloat(parcel, 3, this.zzcu);
        SafeParcelWriter.writeFloat(parcel, 4, this.zzcv);
        SafeParcelWriter.finishObjectHeader(parcel, i);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzab)) {
            return false;
        }
        zzab zzab = (zzab) obj;
        return this.zzct == zzab.zzct && this.zzcu == zzab.zzcu && this.zzcv == zzab.zzcv;
    }

    public final int hashCode() {
        return Objects.hashCode(Float.valueOf(this.zzct), Float.valueOf(this.zzcu), Float.valueOf(this.zzcv));
    }
}
