package com.google.android.gms.internal.cast;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;

@Class(creator = "ApplicationStatusCreator")
@Reserved({1})
public final class zzct extends AbstractSafeParcelable {
    public static final Creator<zzct> CREATOR = new zzcu();
    @Field(getter = "getApplicationStatusText", id = 2)
    private String zzxs;

    @Constructor
    zzct(@Param(id = 2) String str) {
        this.zzxs = str;
    }

    public zzct() {
        this(null);
    }

    public final String zzep() {
        return this.zzxs;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        i = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.zzxs, false);
        SafeParcelWriter.finishObjectHeader(parcel, i);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzct)) {
            return false;
        }
        return zzdk.zza(this.zzxs, ((zzct) obj).zzxs);
    }

    public final int hashCode() {
        return Objects.hashCode(this.zzxs);
    }
}
