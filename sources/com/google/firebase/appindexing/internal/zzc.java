package com.google.firebase.appindexing.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public final class zzc implements Creator<zza> {
    public final /* synthetic */ Object[] newArray(int i) {
        return new zza[i];
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        String str = null;
        String str2 = str;
        String str3 = str2;
        String str4 = str3;
        zzb zzb = str4;
        String str5 = zzb;
        Bundle bundle = str5;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(readHeader)) {
                case 1:
                    str = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 2:
                    str2 = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 3:
                    str3 = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 4:
                    str4 = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 5:
                    zzb = (zzb) SafeParcelReader.createParcelable(parcel, readHeader, zzb.CREATOR);
                    break;
                case 6:
                    str5 = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 7:
                    bundle = SafeParcelReader.createBundle(parcel, readHeader);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, readHeader);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new zza(str, str2, str3, str4, zzb, str5, bundle);
    }
}
