package com.google.android.gms.internal.icing;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public final class zzu implements Creator<zzs> {
    public final /* synthetic */ Object[] newArray(int i) {
        return new zzs[i];
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        boolean z = false;
        boolean z2 = z;
        String str = null;
        String str2 = str;
        String str3 = str2;
        zzn[] zznArr = str3;
        String str4 = zznArr;
        zzv zzv = str4;
        int i = 1;
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
                    z = SafeParcelReader.readBoolean(parcel, readHeader);
                    break;
                case 4:
                    i = SafeParcelReader.readInt(parcel, readHeader);
                    break;
                case 5:
                    z2 = SafeParcelReader.readBoolean(parcel, readHeader);
                    break;
                case 6:
                    str3 = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 7:
                    zznArr = (zzn[]) SafeParcelReader.createTypedArray(parcel, readHeader, zzn.CREATOR);
                    break;
                case 11:
                    str4 = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 12:
                    zzv = (zzv) SafeParcelReader.createParcelable(parcel, readHeader, zzv.CREATOR);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, readHeader);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new zzs(str, str2, z, i, z2, str3, zznArr, str4, zzv);
    }
}
