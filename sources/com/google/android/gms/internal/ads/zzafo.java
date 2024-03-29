package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public final class zzafo implements Creator<zzafn> {
    public final /* synthetic */ Object[] newArray(int i) {
        return new zzafn[i];
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        Parcel parcel2 = parcel;
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        boolean z = false;
        int i = z;
        boolean z2 = i;
        String str = null;
        byte[] bArr = str;
        String[] strArr = bArr;
        String[] strArr2 = strArr;
        long j = 0;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(readHeader)) {
                case 1:
                    z = SafeParcelReader.readBoolean(parcel2, readHeader);
                    break;
                case 2:
                    str = SafeParcelReader.createString(parcel2, readHeader);
                    break;
                case 3:
                    i = SafeParcelReader.readInt(parcel2, readHeader);
                    break;
                case 4:
                    bArr = SafeParcelReader.createByteArray(parcel2, readHeader);
                    break;
                case 5:
                    strArr = SafeParcelReader.createStringArray(parcel2, readHeader);
                    break;
                case 6:
                    strArr2 = SafeParcelReader.createStringArray(parcel2, readHeader);
                    break;
                case 7:
                    z2 = SafeParcelReader.readBoolean(parcel2, readHeader);
                    break;
                case 8:
                    j = SafeParcelReader.readLong(parcel2, readHeader);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel2, readHeader);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel2, validateObjectHeader);
        return new zzafn(z, str, i, bArr, strArr, strArr2, z2, j);
    }
}
