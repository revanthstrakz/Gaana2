package com.google.android.gms.ads.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public final class zzar implements Creator<zzaq> {
    public final /* synthetic */ Object[] newArray(int i) {
        return new zzaq[i];
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        boolean z = false;
        boolean z2 = z;
        boolean z3 = z2;
        int i = z3;
        boolean z4 = i;
        boolean z5 = z4;
        boolean z6 = z5;
        String str = null;
        float f = 0.0f;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(readHeader)) {
                case 2:
                    z = SafeParcelReader.readBoolean(parcel, readHeader);
                    break;
                case 3:
                    z2 = SafeParcelReader.readBoolean(parcel, readHeader);
                    break;
                case 4:
                    str = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 5:
                    z3 = SafeParcelReader.readBoolean(parcel, readHeader);
                    break;
                case 6:
                    f = SafeParcelReader.readFloat(parcel, readHeader);
                    break;
                case 7:
                    i = SafeParcelReader.readInt(parcel, readHeader);
                    break;
                case 8:
                    z4 = SafeParcelReader.readBoolean(parcel, readHeader);
                    break;
                case 9:
                    z5 = SafeParcelReader.readBoolean(parcel, readHeader);
                    break;
                case 10:
                    z6 = SafeParcelReader.readBoolean(parcel, readHeader);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, readHeader);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new zzaq(z, z2, str, z3, f, i, z4, z5, z6);
    }
}
