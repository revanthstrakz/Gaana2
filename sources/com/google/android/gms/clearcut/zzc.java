package com.google.android.gms.clearcut;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;

@Class(creator = "CollectForDebugParcelableCreator")
public final class zzc extends AbstractSafeParcelable {
    public static final Creator<zzc> CREATOR = new zzd();
    @Field(defaultValue = "false", id = 1)
    private final boolean zzad;
    @Field(id = 3)
    private final long zzae;
    @Field(id = 2)
    private final long zzaf;

    @Constructor
    public zzc(@Param(id = 1) boolean z, @Param(id = 3) long j, @Param(id = 2) long j2) {
        this.zzad = z;
        this.zzae = j;
        this.zzaf = j2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof zzc) {
            zzc zzc = (zzc) obj;
            return this.zzad == zzc.zzad && this.zzae == zzc.zzae && this.zzaf == zzc.zzaf;
        }
    }

    public final int hashCode() {
        return Objects.hashCode(Boolean.valueOf(this.zzad), Long.valueOf(this.zzae), Long.valueOf(this.zzaf));
    }

    public final String toString() {
        StringBuilder stringBuilder = new StringBuilder("CollectForDebugParcelable[skipPersistentStorage: ");
        stringBuilder.append(this.zzad);
        stringBuilder.append(",collectForDebugStartTimeMillis: ");
        stringBuilder.append(this.zzae);
        stringBuilder.append(",collectForDebugExpiryTimeMillis: ");
        stringBuilder.append(this.zzaf);
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        i = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeBoolean(parcel, 1, this.zzad);
        SafeParcelWriter.writeLong(parcel, 2, this.zzaf);
        SafeParcelWriter.writeLong(parcel, 3, this.zzae);
        SafeParcelWriter.finishObjectHeader(parcel, i);
    }
}
