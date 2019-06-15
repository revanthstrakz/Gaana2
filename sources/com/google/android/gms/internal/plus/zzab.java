package com.google.android.gms.internal.plus;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ParseException;
import com.google.android.gms.internal.plus.zzr.zzg;
import java.util.HashSet;

public final class zzab implements Creator<zzg> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        HashSet hashSet = new HashSet();
        int i = 0;
        String str = null;
        String str2 = str;
        int i2 = 0;
        int i3 = i2;
        while (parcel.dataPosition() < validateObjectHeader) {
            Object valueOf;
            int readHeader = SafeParcelReader.readHeader(parcel);
            int fieldId = SafeParcelReader.getFieldId(readHeader);
            if (fieldId != 1) {
                switch (fieldId) {
                    case 3:
                        i3 = SafeParcelReader.readInt(parcel, readHeader);
                        readHeader = 3;
                        break;
                    case 4:
                        str2 = SafeParcelReader.createString(parcel, readHeader);
                        readHeader = 4;
                        break;
                    case 5:
                        str = SafeParcelReader.createString(parcel, readHeader);
                        readHeader = 5;
                        break;
                    case 6:
                        i2 = SafeParcelReader.readInt(parcel, readHeader);
                        readHeader = 6;
                        break;
                    default:
                        SafeParcelReader.skipUnknownField(parcel, readHeader);
                        continue;
                }
                valueOf = Integer.valueOf(readHeader);
            } else {
                i = SafeParcelReader.readInt(parcel, readHeader);
                valueOf = Integer.valueOf(1);
            }
            hashSet.add(valueOf);
        }
        if (parcel.dataPosition() == validateObjectHeader) {
            return new zzg(hashSet, i, str, i2, str2, i3);
        }
        StringBuilder stringBuilder = new StringBuilder(37);
        stringBuilder.append("Overread allowed size end=");
        stringBuilder.append(validateObjectHeader);
        throw new ParseException(stringBuilder.toString(), parcel);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new zzg[i];
    }
}
