package com.google.firebase.appindexing.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;

@Class(creator = "MetadataImplCreator")
@Reserved({1000})
public final class zzb extends AbstractSafeParcelable {
    public static final Creator<zzb> CREATOR = new zzw();
    @Field(getter = "getEventStatus", id = 1)
    private int zzaq = 0;
    @Field(getter = "isUploadable", id = 2)
    private final boolean zzef;
    @Field(getter = "isContextOnly", id = 6)
    private final boolean zzeg;
    @Field(getter = "getCompletionToken", id = 3)
    private final String zzel;
    @Field(getter = "getAccountName", id = 4)
    private final String zzem;
    @Field(getter = "getSsbContext", id = 5)
    private final byte[] zzen;

    @Constructor
    zzb(@Param(id = 1) int i, @Param(id = 2) boolean z, @Param(id = 3) String str, @Param(id = 4) String str2, @Param(id = 5) byte[] bArr, @Param(id = 6) boolean z2) {
        this.zzaq = i;
        this.zzef = z;
        this.zzel = str;
        this.zzem = str2;
        this.zzen = bArr;
        this.zzeg = z2;
    }

    public zzb(boolean z, String str, String str2, byte[] bArr, boolean z2) {
        this.zzef = z;
        this.zzel = null;
        this.zzem = null;
        this.zzen = null;
        this.zzeg = false;
    }

    public final void zzf(int i) {
        this.zzaq = i;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        i = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.zzaq);
        SafeParcelWriter.writeBoolean(parcel, 2, this.zzef);
        SafeParcelWriter.writeString(parcel, 3, this.zzel, false);
        SafeParcelWriter.writeString(parcel, 4, this.zzem, false);
        SafeParcelWriter.writeByteArray(parcel, 5, this.zzen, false);
        SafeParcelWriter.writeBoolean(parcel, 6, this.zzeg);
        SafeParcelWriter.finishObjectHeader(parcel, i);
    }

    public final String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("MetadataImpl { ");
        stringBuilder.append("{ eventStatus: '");
        stringBuilder.append(this.zzaq);
        stringBuilder.append("' } ");
        stringBuilder.append("{ uploadable: '");
        stringBuilder.append(this.zzef);
        stringBuilder.append("' } ");
        if (this.zzel != null) {
            stringBuilder.append("{ completionToken: '");
            stringBuilder.append(this.zzel);
            stringBuilder.append("' } ");
        }
        if (this.zzem != null) {
            stringBuilder.append("{ accountName: '");
            stringBuilder.append(this.zzem);
            stringBuilder.append("' } ");
        }
        if (this.zzen != null) {
            stringBuilder.append("{ ssbContext: [ ");
            for (byte b : this.zzen) {
                stringBuilder.append("0x");
                stringBuilder.append(Integer.toHexString(b));
                stringBuilder.append(" ");
            }
            stringBuilder.append("] } ");
        }
        stringBuilder.append("{ contextOnly: '");
        stringBuilder.append(this.zzeg);
        stringBuilder.append("' } ");
        stringBuilder.append("}");
        return stringBuilder.toString();
    }
}
