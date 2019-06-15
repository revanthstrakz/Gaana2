package com.google.android.gms.internal.icing;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import com.google.android.gms.common.util.VisibleForTesting;

@ShowFirstParty
@Class(creator = "DocumentSectionCreator")
@Reserved({1000})
public final class zzl extends AbstractSafeParcelable {
    public static final Creator<zzl> CREATOR = new zzm();
    private static final int zzo = Integer.parseInt("-1");
    private static final zzs zzp = new zzt("SsbContext").zzb(true).zzc("blob").zzc();
    @Field(id = 1)
    private final String zzq;
    @Field(id = 3)
    private final zzs zzr;
    @Field(defaultValue = "-1", id = 4)
    public final int zzs;
    @Field(id = 5)
    private final byte[] zzt;

    @Constructor
    zzl(@Param(id = 1) String str, @Param(id = 3) zzs zzs, @Param(id = 4) int i, @Param(id = 5) byte[] bArr) {
        boolean z = i == zzo || zzr.zza(i) != null;
        StringBuilder stringBuilder = new StringBuilder(32);
        stringBuilder.append("Invalid section type ");
        stringBuilder.append(i);
        Preconditions.checkArgument(z, stringBuilder.toString());
        this.zzq = str;
        this.zzr = zzs;
        this.zzs = i;
        this.zzt = bArr;
        if (this.zzs == zzo || zzr.zza(this.zzs) != null) {
            str = (this.zzq == null || this.zzt == null) ? null : "Both content and blobContent set";
        } else {
            int i2 = this.zzs;
            StringBuilder stringBuilder2 = new StringBuilder(32);
            stringBuilder2.append("Invalid section type ");
            stringBuilder2.append(i2);
            str = stringBuilder2.toString();
        }
        if (str != null) {
            throw new IllegalArgumentException(str);
        }
    }

    public zzl(String str, zzs zzs) {
        this(str, zzs, zzo, null);
    }

    @VisibleForTesting
    public zzl(String str, zzs zzs, String str2) {
        this(str, zzs, zzr.zzb(str2), null);
    }

    public zzl(byte[] bArr, zzs zzs) {
        this(null, zzs, zzo, bArr);
    }

    public static zzl zza(byte[] bArr) {
        return new zzl(bArr, zzp);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, this.zzq, false);
        SafeParcelWriter.writeParcelable(parcel, 3, this.zzr, i, false);
        SafeParcelWriter.writeInt(parcel, 4, this.zzs);
        SafeParcelWriter.writeByteArray(parcel, 5, this.zzt, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
