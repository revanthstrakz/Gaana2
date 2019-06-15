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
import com.google.android.gms.internal.cast.zzdk;

@ShowFirstParty
@Class(creator = "EqualizerSettingsCreator")
@Reserved({1})
public final class zzad extends AbstractSafeParcelable {
    public static final Creator<zzad> CREATOR = new zzae();
    @Field(getter = "getLowShelf", id = 2)
    private final zzab zzcw;
    @Field(getter = "getHighShelf", id = 3)
    private final zzab zzcx;

    @Constructor
    public zzad(@Param(id = 2) zzab zzab, @Param(id = 3) zzab zzab2) {
        this.zzcw = zzab;
        this.zzcx = zzab2;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zzcw, i, false);
        SafeParcelWriter.writeParcelable(parcel, 3, this.zzcx, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzad)) {
            return false;
        }
        zzad zzad = (zzad) obj;
        return zzdk.zza(this.zzcw, zzad.zzcw) && zzdk.zza(this.zzcx, zzad.zzcx);
    }

    public final int hashCode() {
        return Objects.hashCode(this.zzcw, this.zzcx);
    }
}
