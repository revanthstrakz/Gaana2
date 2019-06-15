package com.google.android.gms.internal.cast;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.cast.ApplicationMetadata;
import com.google.android.gms.cast.zzad;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;

@Class(creator = "DeviceStatusCreator")
@Reserved({1})
public final class zzdl extends AbstractSafeParcelable {
    public static final Creator<zzdl> CREATOR = new zzdm();
    @Field(getter = "getVolume", id = 2)
    private double zzet;
    @Field(getter = "getMuteState", id = 3)
    private boolean zzeu;
    @Field(getter = "getEqualizerSettings", id = 7)
    private zzad zzyh;
    @Field(getter = "getActiveInputState", id = 4)
    private int zzyi;
    @Field(getter = "getStandbyState", id = 6)
    private int zzyj;
    @Field(getter = "getApplicationMetadata", id = 5)
    private ApplicationMetadata zzyt;

    @Constructor
    zzdl(@Param(id = 2) double d, @Param(id = 3) boolean z, @Param(id = 4) int i, @Param(id = 5) ApplicationMetadata applicationMetadata, @Param(id = 6) int i2, @Param(id = 7) zzad zzad) {
        this.zzet = d;
        this.zzeu = z;
        this.zzyi = i;
        this.zzyt = applicationMetadata;
        this.zzyj = i2;
        this.zzyh = zzad;
    }

    public zzdl() {
        this(Double.NaN, false, -1, null, -1, null);
    }

    public final double getVolume() {
        return this.zzet;
    }

    public final boolean zzey() {
        return this.zzeu;
    }

    public final int getActiveInputState() {
        return this.zzyi;
    }

    public final int getStandbyState() {
        return this.zzyj;
    }

    public final ApplicationMetadata getApplicationMetadata() {
        return this.zzyt;
    }

    public final zzad zzez() {
        return this.zzyh;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeDouble(parcel, 2, this.zzet);
        SafeParcelWriter.writeBoolean(parcel, 3, this.zzeu);
        SafeParcelWriter.writeInt(parcel, 4, this.zzyi);
        SafeParcelWriter.writeParcelable(parcel, 5, this.zzyt, i, false);
        SafeParcelWriter.writeInt(parcel, 6, this.zzyj);
        SafeParcelWriter.writeParcelable(parcel, 7, this.zzyh, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzdl)) {
            return false;
        }
        zzdl zzdl = (zzdl) obj;
        return this.zzet == zzdl.zzet && this.zzeu == zzdl.zzeu && this.zzyi == zzdl.zzyi && zzdk.zza(this.zzyt, zzdl.zzyt) && this.zzyj == zzdl.zzyj && zzdk.zza(this.zzyh, this.zzyh);
    }

    public final int hashCode() {
        return Objects.hashCode(Double.valueOf(this.zzet), Boolean.valueOf(this.zzeu), Integer.valueOf(this.zzyi), this.zzyt, Integer.valueOf(this.zzyj), this.zzyh);
    }
}
